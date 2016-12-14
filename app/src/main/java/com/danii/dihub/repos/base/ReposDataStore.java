package com.danii.dihub.repos.base;

import com.danii.dihub.repos.model.GithubRepo;

import java.util.List;

import rx.Observable;

/**
 * Created by User on 09.12.2016.
 */

public interface ReposDataStore {

    Observable<List<GithubRepo>> reposList();

}
