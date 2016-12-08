package com.danii.dihub;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by User on 07.12.2016.
 */

public class ReposListModel implements IReposModel{


    private DBHelper dbHelper;
    private List<GithubRepo> result;

    public ReposListModel() {
        result = new ArrayList<>();
    }


    @Override
    public List<GithubRepo> getReposList(String username, String reptypes, String sort,DBHelper dbHelper) {
        this.dbHelper=dbHelper;
        //удаляем старые записи из бд
        dbHelper.clearOld();
        //запрс в бд
        result = dbHelper.getAllRepo(username, reptypes);
        //если в бд нужных данных нет спросим в интернете
        if (result == null)
            getCall(username, reptypes, sort);
        //и венем данные в любом случае
        return result;
    }


    private void getCall(final String username,final String reptypes,String sort) {
        String BASE_URL = "https://api.github.com" ;
        Retrofit client = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        GithubAPI service = client.create(GithubAPI.class);

        Call<List<GithubRepo>> call = service.getUser(username,reptypes,sort);
        //делаем синхронный запрос
        try {
            Response<List<GithubRepo>> response= call.execute();
            if (response.body()!=null){
                result = new ArrayList<>();
                result.addAll(response.body());
                for (GithubRepo r :result) {
                    dbHelper.addRepo(r,username,reptypes);
                }
            }
            else{

            }

        } catch (IOException e) {
            e.printStackTrace();
        }}

    @Override
    public String getRepoURL(int id) {
            //возвращаем URL репозитория
        return result.get(id).getHtmlUrl();
    }
}
