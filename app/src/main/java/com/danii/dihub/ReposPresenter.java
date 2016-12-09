package com.danii.dihub;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Handler;

import java.util.List;

import rx.Subscriber;


/**
 * Created by User on 07.12.2016.
 */

public class ReposPresenter implements IReposPresenter {

    private IReposModel reposModel;
    private IReposView reposView;
    private Handler h;
    private Thread t;
    private boolean ready = false;

    ReposPresenter(IReposView reposView) {
        this.reposView = reposView;
        this.reposModel = new ReposListModel();

    }




    @Override
    public void onQuery(final Context cont) {
        //в новом потоке

        AsyncTask<Void, Void, Void> asyncTask = new AsyncTask<Void, Void, Void>() {

            @Override
            protected Void doInBackground(Void... params) {
                ReposDataStoreFactory reposDataStoreFactory = new ReposDataStoreFactory(reposView.getContext());
                ReposDataStore reposDataStore = reposDataStoreFactory.create(reposModel.getUserName(), reposModel.getRepoType(), reposModel.getSort());
                if (ready) {
                    reposDataStore.reposList().subscribe((Subscriber<? super List<GithubRepo>>) reposModel);
                } else
                    try {
                        throw new InterruptedException();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                return null;
            }

            @Override
            protected void onPostExecute(Void v) {
                List<GithubRepo> githubRepoList = reposModel.getReposList();
                reposView.showList(githubRepoList);

            }
        };
        asyncTask.execute();

    }


    @Override
    public String onItemClicked(int id) {
        return reposModel.getRepoURL(id);
    }

    @Override
    public void isReady(boolean ready) {
        this.ready = ready;
    }

    @Override
    public void setUserName(String UserName) {
        reposModel.setUserName(UserName);
    }

    @Override
    public String getUserName() {
        return reposModel.getUserName();
    }

    @Override
    public void setRepoType(String RepoType) {
        reposModel.setRepoType(RepoType);
    }

    @Override
    public void setSort(String sort) {
        reposModel.setSort(sort);
    }


}
