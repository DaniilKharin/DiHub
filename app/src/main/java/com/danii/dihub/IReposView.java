package com.danii.dihub;

import java.util.List;

/**
 * Created by danii on 06.12.2016.
 */

public interface IReposView {

    String getUserName();
    String getRepoType();
    String getSort();
    //Создает экземпляр ReposAdapter передавая в него reposList
    // и вызывает setAdapter у нашего RecyclerView
    void showList(List<GithubRepo> repoList,final String username);
    //выводит toast с ошибкой
    void showError(String errMsg);

}
