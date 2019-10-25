package hu.an.jobfinder.service.interfaces;

import java.util.List;

import hu.an.jobfinder.service.model.GitHubJobModel;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface GitHubJobsApi {

    @GET("positions.json")
    Call<List<GitHubJobModel>> getPositionList(@Query(value = "description", encoded = true) String description, @Query(value = "location",encoded=true) String location, @Query("page") int page);
}
