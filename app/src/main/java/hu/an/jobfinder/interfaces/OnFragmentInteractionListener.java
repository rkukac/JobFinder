package hu.an.jobfinder.interfaces;

import android.support.annotation.StringRes;

import hu.an.jobfinder.model.JobItem;

public interface OnFragmentInteractionListener {
    void onJobSelectedForDetails(boolean fromList, JobItem jobItem);
    void onJobSelectedForFavorites(boolean isSaved, JobItem jobItem);
    void onJobSearch(String description, String location, int pageIndex);
    void onShowMessage(@StringRes int messageRes);
}
