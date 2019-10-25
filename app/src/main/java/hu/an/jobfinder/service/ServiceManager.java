package hu.an.jobfinder.service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import hu.an.jobfinder.service.interfaces.GitHubJobsApi;
import hu.an.jobfinder.service.interfaces.OnServiceManagerListener;
import hu.an.jobfinder.service.model.GitHubJobModel;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static hu.an.jobfinder.service.ApiConfig.API_PAGINATION_MAX_ITEM;

public class ServiceManager {

    private ExecutorService mProcessExecutor = Executors.newSingleThreadExecutor();
    private OnServiceManagerListener mServiceManagerListener;

    private static ServiceManager mInstance;

    public static ServiceManager getInstance() {
        if (mInstance == null) {
            mInstance = new ServiceManager();
        }
        return mInstance;
    }

    public void addServiceManagerListener(OnServiceManagerListener listener) {
        mServiceManagerListener = listener;
    }

    public void removeServiceManagerListener() {
        mServiceManagerListener = null;
    }

    public void getPositionList(String description, String location, int pageIndex) {
        mProcessExecutor.execute(() -> innerGetPositionList(description, location, pageIndex));
    }

    private void innerGetPositionList(String description, String location, int pageIndex) {
        GitHubJobsApi api = RetrofitBuilder.getSimpleClient();
        Call<List<GitHubJobModel>> apiRequest = api.getPositionList(description, location, pageIndex);
        apiRequest.enqueue(new Callback<List<GitHubJobModel>>() {
            @Override
            public void onResponse(Call<List<GitHubJobModel>> call, Response<List<GitHubJobModel>> response) {
                mProcessExecutor.execute(() -> handleGetPositionListResponse(response));
            }

            @Override
            public void onFailure(Call<List<GitHubJobModel>> call, Throwable t) {
                mProcessExecutor.execute(() -> handleGetPositionListFailure(call));
            }
        });
    }

    private void handleGetPositionListResponse(Response<List<GitHubJobModel>> response) {
        if (response.raw().code() == 200 && response.body() != null) {
            sendGetPositionListResult(response.body());
        } else {
            sendGetPositionListResult(new ArrayList<>());
        }
    }

    private void handleGetPositionListFailure(Call<List<GitHubJobModel>> call) {
        sendGetPositionListResult(new ArrayList<>());
    }

    private void sendGetPositionListResult(List<GitHubJobModel> result) {
        if (mServiceManagerListener != null) {
            mServiceManagerListener.onGetPositionListResult(result, API_PAGINATION_MAX_ITEM);
        }
    }
}
