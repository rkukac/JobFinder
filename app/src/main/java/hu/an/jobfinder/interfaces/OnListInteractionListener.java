package hu.an.jobfinder.interfaces;

import hu.an.jobfinder.model.JobItem;

public interface OnListInteractionListener {
    void onItemSelected(JobItem item);
    void onSetItemFavorite(JobItem item);
    void onGetNextItemPage(int pageIndex);
}
