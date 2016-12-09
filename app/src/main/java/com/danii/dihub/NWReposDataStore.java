package com.danii.dihub;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;
import rx.Subscriber;

/**
 * Created by User on 09.12.2016.
 */


public class NWReposDataStore implements ReposDataStore {

    private String userName;
    private String repoType;
    private String sort;


    public NWReposDataStore(String userName, String repoType, String sort) {
        this.userName = userName;
        this.repoType = repoType;
        this.sort = sort;
    }


    private List<GithubRepo> getCall(final String username, final String reptypes, String sort) {
        String BASE_URL = "https://api.github.com";
        Retrofit client = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        GithubAPI service = client.create(GithubAPI.class);
        List<GithubRepo> result = new ArrayList<>();
        Call<List<GithubRepo>> call = service.getUser(username, reptypes, sort);
        //делаем синхронный запрос
        try {
            Response<List<GithubRepo>> response = call.execute();
            if (response.body() != null) {
                result.addAll(response.body());
            } else {

            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public Observable<List<GithubRepo>> reposList() {
        return Observable.create(
                new Observable.OnSubscribe<List<GithubRepo>>() {
                    @Override
                    public void call(Subscriber<? super List<GithubRepo>> sub) {
                        //вынести вызов в отдельный поток
                        sub.onNext(getCall(userName, repoType, sort));
                        sub.onCompleted();
                    }
                }
        );
    }

}
