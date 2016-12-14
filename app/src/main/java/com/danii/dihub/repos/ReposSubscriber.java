package com.danii.dihub.repos;

import com.danii.dihub.repos.base.IReposModel;
import com.danii.dihub.repos.base.IReposView;
import com.danii.dihub.repos.model.GithubRepo;

import java.util.List;

import rx.Subscriber;

/**
 * Created by danii on 13.12.2016.
 */

public class ReposSubscriber extends Subscriber<List<GithubRepo>> {
    IReposModel reposModel;
    IReposView reposView;

    public ReposSubscriber(IReposModel reposModel,IReposView reposView) {
        this.reposModel = reposModel;
        this.reposView=reposView;
    }

    @Override
    public void onCompleted() {

    }

    @Override
    public void onError(Throwable e) {

    }

    @Override
    public void onNext(List<GithubRepo> githubRepos) {
        reposModel.setReposList(githubRepos);
        reposView.showList(githubRepos);
    }
}
