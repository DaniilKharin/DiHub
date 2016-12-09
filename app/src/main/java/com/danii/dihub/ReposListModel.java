package com.danii.dihub;


import java.util.ArrayList;
import java.util.List;

import rx.Subscriber;

/**
 * Created by User on 07.12.2016.
 */

public class ReposListModel extends Subscriber<List<GithubRepo>> implements IReposModel {

    private String userName, repoType, sort;

    private List<GithubRepo> githubRepoList;

    public ReposListModel() {
        githubRepoList = new ArrayList<>();
    }


    @Override
    public List<GithubRepo> getReposList() {
        return githubRepoList;
    }




    @Override
    public String getRepoURL(int id) {
        //возвращаем URL репозитория
        return githubRepoList.get(id).getHtmlUrl();
    }

    @Override
    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Override
    public void setRepoType(String repoType) {
        this.repoType = repoType;
    }

    @Override
    public void setSort(String sort) {
        this.sort = sort;
    }

    @Override
    public String getUserName() {
        return userName;
    }

    @Override
    public String getRepoType() {
        return repoType;
    }

    @Override
    public String getSort() {
        return sort;
    }


    @Override
    public void onCompleted() {

    }

    @Override
    public void onError(Throwable e) {

    }

    @Override
    public void onNext(List<GithubRepo> githubRepos) {
        this.githubRepoList = githubRepos;

    }
}
