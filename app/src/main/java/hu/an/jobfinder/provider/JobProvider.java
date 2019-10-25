package hu.an.jobfinder.provider;

import java.util.ArrayList;
import java.util.List;

import hu.an.jobfinder.handler.PreferencesHandler;
import hu.an.jobfinder.interfaces.OnJobProviderListener;
import hu.an.jobfinder.model.JobItem;
import hu.an.jobfinder.service.ServiceManager;
import hu.an.jobfinder.service.interfaces.OnServiceManagerListener;
import hu.an.jobfinder.service.model.GitHubJobModel;

public class JobProvider implements OnServiceManagerListener {

    private OnJobProviderListener mJobProviderListener;

    private String mCurrentDescription;
    private String mCurrentLocation;
    private int mCurrentPageIndex;

    private static JobProvider mInstance;

    public static JobProvider getInstance() {
        if (mInstance == null) {
            mInstance = new JobProvider();
        }
        return mInstance;
    }

    public void addJobProviderListener(OnJobProviderListener listener) {
        mJobProviderListener = listener;
        ServiceManager.getInstance().addServiceManagerListener(this);
    }

    public void removeJobProviderListener() {
        mJobProviderListener = null;
        ServiceManager.getInstance().removeServiceManagerListener();
    }

    private void notifyNewJobList(List<JobItem> list, int pageIndex, boolean hasNextPage) {
        if (mJobProviderListener != null) {
            mJobProviderListener.onNewJobList(list, pageIndex, hasNextPage);
        }
    }

    public void getFavoriteItems() {
        notifyNewJobList(getFavoriteList(), 0, false);
    }

    private List<JobItem> getFavoriteList() {
        return PreferencesHandler.getInstance().getJobList(PreferencesHandler.KEY_FAVORITE_LIST);
    }

    public void setFavoriteItem(JobItem jobItem) {
        List<JobItem> currentList = getFavoriteList();
        int matchIndex = -1;
        for (int i = 0; i < currentList.size(); i++) {
            if (currentList.get(i).getId().equals(jobItem.getId()) && !jobItem.isFavorite()) {
                matchIndex = i;
                break;
            }
        }
        if (matchIndex > -1) {
            currentList.remove(matchIndex);
        } else if (jobItem.isFavorite()) {
            currentList.add(jobItem);
        }
        PreferencesHandler.getInstance().setJobList(PreferencesHandler.KEY_FAVORITE_LIST, currentList);
    }

    public void getJobsFromApi(int pageIndex) {
        getJobsFromApi(mCurrentDescription, mCurrentLocation, pageIndex);
    }

    public void getJobsFromApi(String description, String location, int pageIndex) {
        boolean isNewCall = false;
        if ((description != null && !description.equals(mCurrentDescription)) || (location != null && !location.equals(mCurrentLocation))) {
            isNewCall = true;
        }
        if (isNewCall) {
            mCurrentPageIndex = 0;
            mCurrentDescription = description;
            mCurrentLocation = location;
        } else {
            mCurrentPageIndex = pageIndex;
        }
        ServiceManager.getInstance().getPositionList(mCurrentDescription, mCurrentLocation, mCurrentPageIndex);
    }

    @Override
    public void onGetPositionListResult(List<GitHubJobModel> result, int paginationItemMax) {
        processServiceResponse(result, paginationItemMax);
    }

    private void processServiceResponse(List<GitHubJobModel> result, int paginationItemMax) {
        if (result.size() > 0) {
            notifyNewJobList(convertList(result), mCurrentPageIndex, result.size() == paginationItemMax);
        } else {
            notifyNewJobList(new ArrayList<>(), mCurrentPageIndex, false);
        }
    }

    private List<JobItem> convertList(List<GitHubJobModel> result) {
        List<JobItem> list = new ArrayList<>();
        List<JobItem> favorites = new ArrayList<>(getFavoriteList());
        for (GitHubJobModel model : result) {
            boolean isMatch = false;
            for (JobItem favorite : favorites) {
                if (favorite.getId().equals(model.getId())) {
                    list.add(favorite);
                    isMatch = true;
                    break;
                }
            }
            if (!isMatch) {
                list.add(new JobItem(model.getId(), model.getCompany(), model.getCompanyUrl(), model.getLocation(), model.getTitle(), model.getDescription()));
            }
        }
        return list;
    }
}
