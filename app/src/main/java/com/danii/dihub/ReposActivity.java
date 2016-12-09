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

    List<GithubRepo> viewedList;
    RecyclerView recyclerView;
    ReposAdapter reposAdapter;

    ReposPresenter reposPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_repos);

        recyclerView = (RecyclerView) findViewById(R.id.repos_recycle_view);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        if (reposPresenter == null)
            reposPresenter = new ReposPresenter(this);
        reposPresenter.setUserName(getIntent().getStringExtra("username"));
        reposPresenter.setRepoType(getIntent().getStringExtra("reptypes"));
        reposPresenter.setSort("full_name");

        recyclerView.addOnItemTouchListener(
                new RecyclerItemClickListener(this, recyclerView, new RecyclerItemClickListener.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {
                        Uri address = Uri.parse(reposPresenter.onItemClicked(position));
                        Intent openlinkIntent = new Intent(Intent.ACTION_VIEW, address);
                        startActivity(openlinkIntent);
                    }

                    @Override
                    public void onLongItemClick(View view, int position) {
                        ClipboardManager clipboard = (ClipboardManager) getApplicationContext().getSystemService(Context.CLIPBOARD_SERVICE);
                        ClipData clip = ClipData.newPlainText("", reposPresenter.onItemClicked(position));
                        clipboard.setPrimaryClip(clip);
                    }
                })
        );
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if (savedInstanceState != null)
            if (savedInstanceState.getParcelableArray("res") != null) {
                //восстановление при перевороте Model не затрагивает
                Parcelable[] a = savedInstanceState.getParcelableArray("res");
                viewedList = new ArrayList<>();
                for (Parcelable r : a)
                    viewedList.add((GithubRepo) r);
                showList(viewedList);
            } else
                //запрос репозиториев у Presentera
                reposPresenter.onQuery(getApplicationContext());
        else
            //запрос репозиториев у Presentera
            reposPresenter.onQuery(getApplicationContext());
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.action_back:
                finish();
                break;
            case R.id.sort_fullname:
                reposPresenter.setSort("full_name");
                reposPresenter.onQuery(this);
                break;
            case R.id.sort_create_at:
                reposPresenter.setSort("created_at");
                reposPresenter.onQuery(this);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        Parcelable[] a = new Parcelable[1];
        if (recyclerView.getAdapter() != null) {
            viewedList = ((ReposAdapter) recyclerView.getAdapter()).getItems();
            savedInstanceState.putParcelableArray("res", viewedList.toArray(a));
        }
        super.onSaveInstanceState(savedInstanceState);
    }

    @Override
    public void onPause() {
        reposPresenter.isReady(false);
        super.onPause();
    }

    @Override
    public void onResume() {
        reposPresenter.isReady(true);
        super.onResume();
    }


    @Override
    public void showList(List<GithubRepo> repoList) {
        reposAdapter = new ReposAdapter(repoList, reposPresenter.getUserName(), getApplicationContext());
        recyclerView.setAdapter(reposAdapter);
    }

    @Override
    public void showError(int msgErrId) {
        Toast.makeText(ReposActivity.this, getApplicationContext().getResources().getString(msgErrId), Toast.LENGTH_SHORT).show();
        finish();
    }

    @Override
    public Context getContext() {
        return getApplicationContext();
    }
}



