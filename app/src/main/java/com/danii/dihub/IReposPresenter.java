package com.danii.dihub;

import android.content.Context;

/**
 * Created by danii on 06.12.2016.
 */

public interface IReposPresenter {
    //Вызывается из View при запросе данных
    //в реализации будет вызывать из Model getReposList
    //если и он будет null
    //вызвать из View showError
    //иначе
    //вызвать showList из того-же View
    void onQuery(Context cont);

    // вызывает из Model getRepoURL
    // и открывает URL в браузере
    String onItemClicked(int id);
    // вызывает из Model getRepoURL
    // и копирует URL в буфер обмена
    void isReady(boolean ready);

    void setUserName(String UserName);

    String getUserName();

    void setRepoType(String RepoType);

    void setSort(String sort);

}
