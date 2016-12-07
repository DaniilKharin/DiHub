package com.danii.dihub;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;



public class ReposActivity extends AppCompatActivity implements IReposView {
    String username,reptypes,sort;
    List<GithubRepo> result;
    RecyclerView recyclerView;
    ReposAdapter reposAdapter;
    Context context;

    DBHelper dbHelper;
    ReposPresenter reposPresenter;

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
        sort="full_name";
        if (reposPresenter==null)
            reposPresenter = new ReposPresenter(this);

        recyclerView.addOnItemTouchListener(
                new RecyclerItemClickListener(this, recyclerView ,new RecyclerItemClickListener.OnItemClickListener() {
                    @Override public void onItemClick(View view, int position) {
                        Uri address = Uri.parse(reposPresenter.onItemClicked(position));
                        Intent openlinkIntent = new Intent(Intent.ACTION_VIEW, address);
                        startActivity(openlinkIntent);
                    }

                    @Override public void onLongItemClick(View view, int position) {
                        ClipboardManager clipboard = (ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
                        ClipData clip = ClipData.newPlainText("", reposPresenter.onItemClicked(position));
                        clipboard.setPrimaryClip(clip);
                    }
                })
        );
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if (savedInstanceState==null) {
            //запрос репозиториев у Presentera
            reposPresenter.onQuery(getApplicationContext());
        }
        else {
            Parcelable[] a = savedInstanceState.getParcelableArray("res");
            result = new ArrayList<GithubRepo>();
            for (Parcelable r:a)
                result.add((GithubRepo)r);
            showList(result,username);
        }
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
            case R.id.sort_fullname:
                sort="full_name";
                reposPresenter.onQuery(this);
                break;
            case R.id.sort_create_at:
                sort="created_at";
                reposPresenter.onQuery(this);
                break;
        }
        return super.onOptionsItemSelected(item);
    }
    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {

        Parcelable[] a = new Parcelable[1];
        if (recyclerView.getAdapter()!=null){
            result = ((ReposAdapter) recyclerView.getAdapter()).getItems();
            savedInstanceState.putParcelableArray("res", result.toArray(a));
        }
        super.onSaveInstanceState(savedInstanceState);
    }

    @Override
    public void onPause(){
        reposPresenter.isReady(false);
        super.onPause();
    }
    @Override
    public void onResume(){
        reposPresenter.isReady(true);
        super.onResume();
    }


    @Override
    public String getUserName() {
        return username;
    }

    @Override
    public String getRepoType() {
        return reptypes;
    }

    @Override
    public String getSort() {
        return sort;
    }

    @Override
    public void showList(List<GithubRepo> repoList, final String username) {
        reposAdapter = new ReposAdapter(repoList,username,getApplicationContext());
        recyclerView.setAdapter(reposAdapter);
    }

    @Override
    public void showError(String errMsg) {
        Toast.makeText(ReposActivity.this, errMsg, Toast.LENGTH_SHORT).show();
        finish();
    }
}



