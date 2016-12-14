package com.danii.dihub.repos.base;


import com.danii.dihub.repos.model.GithubRepo;

import java.util.List;

/**
 * Created by danii on 06.12.2016.
 */

public interface IReposModel {
    //Загрузка Списка репозиториев из кеша(SQLite DB)
    //или из интернета
    //если ни кеша с такими параметрами ни репозитория в интернете нет то возвращает NULL
    List<GithubRepo> getReposList();
    void setReposList(List<GithubRepo> reposList);
    //возвращает url репозитория с id
    String getRepoURL(int id);


    void setUserName(String UserName);

    void setRepoType(String RepoType);

    void setSort(String sort);

    String getUserName();

    String getRepoType();

    String getSort();
}
