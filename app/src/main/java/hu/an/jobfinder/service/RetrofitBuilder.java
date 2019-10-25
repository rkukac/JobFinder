package hu.an.jobfinder.service;

import java.util.concurrent.TimeUnit;

import hu.an.jobfinder.service.interfaces.GitHubJobsApi;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.moshi.MoshiConverterFactory;

import static hu.an.jobfinder.service.ApiConfig.BASE_URL;
import static hu.an.jobfinder.service.ApiConfig.TIMEOUT_CONNECT;
import static hu.an.jobfinder.service.ApiConfig.TIMEOUT_READ;

public class RetrofitBuilder {

    public static GitHubJobsApi getSimpleClient() {
        Retrofit retrofit = new Retrofit.Builder()
                .client(getSimpleOkHttpClient())
                .addConverterFactory(MoshiConverterFactory.create())
                .baseUrl(BASE_URL)
                .build();
        return retrofit.create(GitHubJobsApi.class);
    }

    private static OkHttpClient getSimpleOkHttpClient() {
        return new OkHttpClient.Builder()
                .addInterceptor(new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
                .connectTimeout(TIMEOUT_CONNECT, TimeUnit.MILLISECONDS)
                .readTimeout(TIMEOUT_READ, TimeUnit.MILLISECONDS)
                .build();
    }
}
