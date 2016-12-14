package com.danii.dihub;

import android.content.Context;
import android.os.Handler;

import java.util.List;

import rx.Observable;
import rx.Subscriber;
import rx.schedulers.Schedulers;


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


        ReposDataStoreFactory reposDataStoreFactory = new ReposDataStoreFactory(reposView.getContext());
        ReposDataStore reposDataStore = reposDataStoreFactory.create(reposModel.getUserName(), reposModel.getRepoType(), reposModel.getSort());
        Observable<List<GithubRepo>> repoList= reposDataStore.reposList();
        if (ready)
            repoList.subscribe(new ReposSubscriber(reposModel));
        repoList.subscribeOn(Schedulers.io());


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
