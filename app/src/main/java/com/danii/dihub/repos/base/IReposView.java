package com.danii.dihub.repos.base;

import android.content.Context;

import com.danii.dihub.repos.model.GithubRepo;

import java.util.List;

/**
 * Created by danii on 06.12.2016.
 */

public interface IReposView {

    //Создает экземпляр ReposAdapter передавая в него reposList
    // и вызывает setAdapter у нашего RecyclerView
    void showList(List<GithubRepo> repoList);

    //выводит toast с ошибкой
    void showError(int msgErrId);

    Context getContext();
}
