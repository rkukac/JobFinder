package hu.an.jobfinder.fragment;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import hu.an.jobfinder.R;
import hu.an.jobfinder.adapter.JobListAdapter;
import hu.an.jobfinder.fragment.base.JobBaseFragment;
import hu.an.jobfinder.interfaces.OnListInteractionListener;
import hu.an.jobfinder.model.JobItem;

public class JobListFragment extends JobBaseFragment implements OnListInteractionListener {

    private RecyclerView mRecycleView;

    public JobListFragment() {
    }

    public static JobListFragment newInstance() {
        return new JobListFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_job_list, container, false);
        if (view instanceof RecyclerView) {
            mRecycleView = (RecyclerView) view;
            mRecycleView.setLayoutManager(new LinearLayoutManager(view.getContext()));
            mRecycleView.setAdapter(new JobListAdapter(new ArrayList<>(), this, true));
        }
        return view;
    }

    @Override
    public void onItemSelected(JobItem item) {

    }

    @Override
    public void onSetItemFavorite(JobItem item) {

    }

    @Override
    public void onGetNextItemPage(int pageIndex) {
        getNextItemPage(pageIndex);
    }

    public void addNextPageItems(List<JobItem> items) {
        if (mRecycleView != null && mRecycleView.getAdapter() instanceof JobListAdapter) {
            ((JobListAdapter) mRecycleView.getAdapter()).addNextPageItems(items);
        }
    }
}
