package hu.an.jobfinder.provider;

import java.util.ArrayList;
import java.util.List;

import hu.an.jobfinder.handler.PreferencesHandler;
import hu.an.jobfinder.interfaces.OnJobProviderListener;
import hu.an.jobfinder.model.JobItem;

public class JobProvider {

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
    }

    public void removeJobProviderListener() {
        mJobProviderListener = null;
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
        //TODO - add service call
        boolean isNewCall = false;
        if ((mCurrentDescription != null && !mCurrentDescription.equals(description)) || (mCurrentLocation != null && !mCurrentLocation.equals(location))) {
            isNewCall = true;
        }
        if (isNewCall) {
            mCurrentPageIndex = 0;
            mCurrentDescription = description;
            mCurrentLocation = location;
        } else {
            mCurrentPageIndex = pageIndex;
        }
        notifyNewJobList(getMockList(), mCurrentPageIndex, true);
    }

    private List<JobItem> getMockList() {
        return new ArrayList<JobItem>() {{
            add(new JobItem("1", "Comp1", "https://jobs.github.com/api", "New", "Title", "sdasdasdasdasdsadsa cdc ds cac  dsac sa cs a"));
            add(new JobItem("2", "Comp2", "https://jobs.github.com/api", "New", "Title", "sdasdasdasdasdsadsa cdc ds cac  dsac sa cs a"));
            add(new JobItem("3", "Comp3", "https://jobs.github.com/api", "New", "Title", "sdasdasdasdasdsadsa cdc ds cac  dsac sa cs a"));
            add(new JobItem("4", "Comp4", "https://jobs.github.com/api", "New", "Title", "sdasdasdasdasdsadsa cdc ds cac  dsac sa cs a"));
            add(new JobItem("5", "Comp5", "https://jobs.github.com/api", "New", "Title", "sdasdasdasdasdsadsa cdc ds cac  dsac sa cs a"));
            add(new JobItem("6", "Comp6", "https://jobs.github.com/api", "New", "Title", "sdasdasdasdasdsadsa cdc ds cac  dsac sa cs a"));
            add(new JobItem("7", "Comp7", "https://jobs.github.com/api", "New", "Title", "sdasdasdasdasdsadsa cdc ds cac  dsac sa cs a"));
        }};
    }
}
