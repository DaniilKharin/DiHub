package com.danii.dihub;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ReposActivity extends AppCompatActivity {
    String username,reptypes;
    List<GithubRepo> result;
    RecyclerView recyclerView;
    ReposAdapter reposAdapter;
    Context context;

    DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_repos);
        context=this;
        recyclerView = (RecyclerView) findViewById(R.id.repos_recycle_view);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        username=getIntent().getStringExtra("username");
        reptypes=getIntent().getStringExtra("reptypes");
        recyclerView.addOnItemTouchListener(
                new RecyclerItemClickListener(this, recyclerView ,new RecyclerItemClickListener.OnItemClickListener() {
                    @Override public void onItemClick(View view, int position) {
                        Uri address = Uri.parse(((ReposAdapter)recyclerView.getAdapter()).getItem(position).getHtmlUrl());
                        Intent openlinkIntent = new Intent(Intent.ACTION_VIEW, address);
                        startActivity(openlinkIntent);
                    }

                    @Override public void onLongItemClick(View view, int position) {
                        ClipboardManager clipboard = (ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
                        ClipData clip = ClipData.newPlainText("", ((ReposAdapter)recyclerView.getAdapter()).getItem(position).getHtmlUrl());
                        clipboard.setPrimaryClip(clip);
                    }
                })
        );
        if (savedInstanceState==null) {
            Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
            setSupportActionBar(toolbar);

            dbHelper = new DBHelper(context);
            dbHelper.clearOld();
            result = dbHelper.getAllShops(username, reptypes);
            if (result != null) {
                reposAdapter = new ReposAdapter(result, username,context);
                recyclerView.setAdapter(reposAdapter);
            } else
                getCall(username, reptypes, "full_name");
        }
        else {
            Parcelable[] a = savedInstanceState.getParcelableArray("res");
            result = new ArrayList<GithubRepo>();
            for (Parcelable r:a)
                result.add((GithubRepo)r);
            reposAdapter = new ReposAdapter(result, username,context);
            recyclerView.setAdapter(reposAdapter);
        }
    }

    public void getCall(final String username,final String reptypes,String sort) {
        String BASE_URL = "https://api.github.com" ;
        result = new ArrayList<>();
        Retrofit client = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        GithubAPI service = client.create(GithubAPI.class);

        Call<List<GithubRepo>> call = service.getUser(username,reptypes,sort);

        Callback<List<GithubRepo>> callback= new Callback<List<GithubRepo>>() {

            @Override
            public void onResponse(Call<List<GithubRepo>> call, Response<List<GithubRepo>> response) {
                if (response.body()!=null){
                result.addAll(response.body());
                reposAdapter = new ReposAdapter(result,username,context);
                recyclerView.setAdapter(reposAdapter);
                    for (GithubRepo r :result) {
                        dbHelper.addRepo(r,username,reptypes);
                    }
                    }
                else {
                    Toast.makeText(ReposActivity.this, "Пользователя с таким именем не существует", Toast.LENGTH_SHORT).show();
                    finish();
                }
            }

            @Override
            public void onFailure(Call<List<GithubRepo>>call, Throwable t) {
                Toast.makeText(ReposActivity.this, "Проблемы с подключением к сети", Toast.LENGTH_SHORT).show();
            }};

        call.enqueue(callback);


    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()){
            case R.id.action_back: finish();
                break;
            case R.id.sort_fullname:getCall(username,reptypes,"full_name");
                break;
            case R.id.sort_create_at:getCall(username,reptypes,"created_at");
                break;
        }
        return super.onOptionsItemSelected(item);
    }
    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {

        Parcelable[] a = new Parcelable[1];
        savedInstanceState.putParcelableArray("res",result.toArray(a));
        super.onSaveInstanceState(savedInstanceState);
    }

}



