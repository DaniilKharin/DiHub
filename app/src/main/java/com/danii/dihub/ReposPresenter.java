package com.danii.dihub;

import android.content.Context;
import android.os.Handler;

import java.util.List;


/**
 * Created by User on 07.12.2016.
 */

public class ReposPresenter implements IReposPresenter {

    private IReposModel reposModel;
    private IReposView reposView;
    private Handler h;
    private Thread t;
    private boolean ready = false;

    ReposPresenter(IReposView reposView) {
        this.reposView = reposView;
        this.reposModel = new ReposListModel();
    }

    class RunShow implements Runnable {
        List<GithubRepo> list;

        RunShow(List<GithubRepo> list) {
            this.list = list;
        }

        @Override
        public void run() {

            if (list != null)
                //отправляем данные в view
                reposView.showList(list);
            else
                reposView.showError(R.string.error);
        }
    }

    class RunLoad implements Runnable {
        DBHelper dbHelper;

        RunLoad(DBHelper dbHelper) {
            this.dbHelper = dbHelper;
        }

        @Override
        public void run() {
            //спрашивает у Model Список репозиториев
            List<GithubRepo> list = reposModel.getReposList(dbHelper);

            if (ready) {
                RunShow r = new RunShow(list);
                h.post(r);
            } else
                try {
                    throw new InterruptedException();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }



            //
        }

    }


    @Override
    public void onQuery(Context cont) {
        //в новом потоке
        h = new Handler();
        DBHelper dbHelper = new DBHelper(cont);
        t = new Thread(new RunLoad(dbHelper));
        t.start();


    }


    @Override
    public String onItemClicked(int id) {
        return reposModel.getRepoURL(id);
    }

    @Override
    public void isReady(boolean ready) {
        this.ready = ready;
    }

    @Override
    public void setUserName(String UserName) {
        reposModel.setUserName(UserName);
    }

    @Override
    public String getUserName() {
        return reposModel.getUserName();
    }

    @Override
    public void setRepoType(String RepoType) {
        reposModel.setRepoType(RepoType);
    }

    @Override
    public void setSort(String sort) {
        reposModel.setSort(sort);
    }


}
