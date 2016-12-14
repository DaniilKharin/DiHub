package com.danii.dihub;

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
