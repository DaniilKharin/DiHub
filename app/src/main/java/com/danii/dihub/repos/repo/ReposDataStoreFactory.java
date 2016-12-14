package com.danii.dihub.repos.repo;

import android.content.Context;
import android.support.annotation.NonNull;

import com.danii.dihub.repos.base.ReposDataStore;

import javax.inject.Singleton;

/**
 * Created by User on 09.12.2016.
 */
@Singleton
public class ReposDataStoreFactory {

    Context context;

    public ReposDataStoreFactory(@NonNull Context context) {
        this.context = context.getApplicationContext();
    }


    public ReposDataStore create(String userName, String repoType, String sort) {
        DBReposDataStore userDataStore;
        userDataStore = new DBReposDataStore(userName, repoType, sort, context);
        if (userDataStore.isExists()) {
            return userDataStore;
        } else {
            return new NWReposDataStore(userName, repoType, sort, context);
        }


    }


}

