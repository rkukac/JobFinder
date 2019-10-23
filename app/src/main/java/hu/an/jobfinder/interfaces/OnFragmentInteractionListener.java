package hu.an.jobfinder.interfaces;

import hu.an.jobfinder.model.JobItem;

public interface OnFragmentInteractionListener {
    void onJobSelectedForDetails(boolean fromList, JobItem jobItem);
    void onJobSelectedForFavorites(boolean isSaved, JobItem jobItem);
}
