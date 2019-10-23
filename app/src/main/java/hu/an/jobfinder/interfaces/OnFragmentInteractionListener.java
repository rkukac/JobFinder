package hu.an.jobfinder.interfaces;

import android.support.annotation.StringRes;

import hu.an.jobfinder.model.JobItem;

public interface OnFragmentInteractionListener {
    void onJobSelectedForDetails(boolean fromList, JobItem jobItem);
    void onJobSelectedForFavorites(JobItem jobItem);
    void onJobSearch(String description, String location, int pageIndex);
    void onShowMessage(@StringRes int messageRes);
    void onGetNextItemPage(int pageIndex);
}
