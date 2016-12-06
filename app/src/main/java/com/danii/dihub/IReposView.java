package com.danii.dihub;

import java.util.List;

/**
 * Created by danii on 06.12.2016.
 */

public interface IReposView {
    String getUsername();
    String getRepoType();
    //вызываетс onItemClicked из Presenter
    void getOnItemClick();
    //вызывается onItemLongClicked из Presenter
    void getOnItemLongClick();
    //Создает экземпляр ReposAdapter передавая в него reposList
    // и вызывает setAdapter у нашего RecyclerView
    void showList(List<GithubRepo> repoList);
    //выводит toast с ошибкой
    void showError(String errMsg);
}
