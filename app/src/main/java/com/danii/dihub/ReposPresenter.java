package com.danii.dihub;

import android.content.Context;
import android.os.Handler;

import java.util.List;

import static android.R.id.list;

/**
 * Created by User on 07.12.2016.
 */

public class ReposPresenter implements IReposPresenter {

    private IReposModel reposModel;
    private IReposView  reposView;
    private  Handler h;
    ReposPresenter(IReposView reposView){
        this.reposView=reposView;
        this.reposModel=new ReposModel();
    }

    class RunShow implements Runnable {
        List<GithubRepo> list;
        RunShow( List<GithubRepo> list){
            this.list=list;
        }
        @Override
        public void run() {
            if (list!=null)
                reposView.showList(list, reposView.getUserName()) ;
        }}
        class RunLoad implements Runnable {
            DBHelper dbHelper;
            RunLoad(DBHelper dbHelper ){
                this.dbHelper=dbHelper;
            }
            @Override
            public void run() {
                List<GithubRepo> list =reposModel.getReposList(reposView.getUserName(),reposView.getRepoType(),reposView.getSort(),dbHelper);
                RunShow r = new RunShow(list);
                h.post(r);
            }
    }
    @Override
    public void onQuery(Context cont) {
        h =new Handler();
        DBHelper dbHelper= new DBHelper(cont);
        Thread t  = new Thread(new RunLoad(dbHelper));
        t.start();




    }



    @Override
    public String onItemClicked(int id) {
           return reposModel.getRepoURL(id);
    }



}