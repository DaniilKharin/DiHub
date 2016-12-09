package com.danii.dihub;

import java.util.List;

import rx.Subscriber;

/**
 * Created by User on 09.12.2016.
 */

public class ReposListSubscriber extends Subscriber<List<GithubRepo>> {

    private IReposView reposView;

    public ReposListSubscriber(IReposView reposView) {
        this.reposView = reposView;
    }


    @Override
    public void onCompleted() {

    }

    @Override
    public void onError(Throwable e) {

    }

    @Override
    public void onNext(List<GithubRepo> githubRepos) {
        if (githubRepos != null)
            reposView.showList(githubRepos);
        else
            reposView.showError(R.string.error);
    }
}
