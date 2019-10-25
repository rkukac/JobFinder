package hu.an.jobfinder.service.interfaces;

import java.util.List;

import hu.an.jobfinder.service.model.GitHubJobModel;

public interface OnServiceManagerListener {
    void onGetPositionListResult(List<GitHubJobModel> result, int paginationItemMax);
}
