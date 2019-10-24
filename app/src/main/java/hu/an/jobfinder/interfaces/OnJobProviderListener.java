package hu.an.jobfinder.interfaces;

import java.util.List;

import hu.an.jobfinder.model.JobItem;

public interface OnJobProviderListener {
    void onNewJobList(List<JobItem> list, int pageIndex, boolean hasNextPage);
}
