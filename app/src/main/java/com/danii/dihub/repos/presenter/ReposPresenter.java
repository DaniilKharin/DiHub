package com.danii.dihub.repos.presenter;

import android.content.Context;
import android.os.Handler;

import com.danii.dihub.repos.model.GithubRepo;
import com.danii.dihub.repos.repo.ReposDataStoreFactory;
import com.danii.dihub.repos.ReposSubscriber;
import com.danii.dihub.repos.base.IReposModel;
import com.danii.dihub.repos.base.IReposPresenter;
import com.danii.dihub.repos.base.IReposView;
import com.danii.dihub.repos.base.ReposDataStore;
import com.danii.dihub.repos.model.ReposListModel;

import java.util.List;

import rx.Observable;
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

    public ReposPresenter(IReposView reposView) {
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
            repoList.subscribe(new ReposSubscriber(reposModel, reposView));
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
