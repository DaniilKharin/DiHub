package com.danii.dihub;

/**
 * Created by danii on 06.12.2016.
 */

public interface IReposPresenter {
    //Вызывается из View при создании
    //в реализации будет вызывать из Model getCashedReposList
    //если та возвратит null то вызовет getReposList
    //если и он будет null
    //вызвать из View showError
    //иначе
    //вызвать showList из того-же View

    void onCreate(String username,String repotype);
    //Вызывается из View при выборе в меню другой сортировки в остальном аналогичен onCreate
    void onSort();
    // вызывает из Model getRepoURL
    // и открывает URL в браузере
    void onItemClicked(int id);
    // вызывает из Model getRepoURL
    // и копирует URL в буфер обмена
    void onItemLongClicked(int id);
    //Стандартные методы
    void onConfigurationChanged(IReposView view);
    void onDestroy(boolean isChangingConfig);
}
