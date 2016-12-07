package com.danii.dihub;



import java.util.List;

/**
 * Created by danii on 06.12.2016.
 */

public interface IReposModel {
    //Загрузка Списка репозиториев из кеша(SQLite DB)
    //или из интернета
    //если ни кеша с такими параметрами ни репозитория в интернете нет то возвращает NULL
    List<GithubRepo> getReposList(String username,String repotype,String sort,DBHelper dbHelper);

    //возвращает url репозитория с id
    String getRepoURL(int id);
}
