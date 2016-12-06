package com.danii.dihub;

import android.support.annotation.Nullable;

import java.util.List;

/**
 * Created by danii on 06.12.2016.
 */

public interface IReposModel {
    //Загрузка Списка репозиториев из интернета
    List<GithubRepo> getReposList(String username,String repotype);
    //Загрузка Списка репозиториев из кеша(SQLite DB)
    //если кеша с такими параметрами нет то возвращает NULL
    List<GithubRepo> getCashedReposList(String username, String repotype);
    //возвращает url репозитория с id
    String getRepoURL(int id);
}
