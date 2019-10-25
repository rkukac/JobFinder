package hu.an.jobfinder.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
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

    private static final String BUNDLE_KEY_LIST_IS_FAVORITE = "KeyListIsFavorite";

    private RecyclerView mRecycleView;
    private JobListAdapter mAdapter;
    private boolean mIsFavoriteList;
    private boolean mIsAdapterInited = false;
    private List<JobItem> mPendingList;
    private int mPendingPageIndex = 0;
    private boolean mPendingHasNextPage = true;

    public JobListFragment() {
    }

    public static JobListFragment newInstance(boolean isFavoriteList) {
        JobListFragment fragment = new JobListFragment();
        Bundle bundle = new Bundle();
        bundle.putBoolean(BUNDLE_KEY_LIST_IS_FAVORITE, isFavoriteList);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mIsFavoriteList = getArguments().getBoolean(BUNDLE_KEY_LIST_IS_FAVORITE, false);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_job_list, container, false);
        if (view instanceof RecyclerView) {
            mRecycleView = (RecyclerView) view;
            mRecycleView.setLayoutManager(new LinearLayoutManager(view.getContext()));
            mRecycleView.addOnScrollListener(new RecyclerView.OnScrollListener() {
                @Override
                public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                    super.onScrollStateChanged(recyclerView, newState);
                }

                @Override
                public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                    super.onScrolled(recyclerView, dx, dy);
                    if (dy > 0) {
                        LinearLayoutManager linearLayoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();

                        if (mAdapter != null && !mAdapter.isLoading()) {
                            if (linearLayoutManager != null && linearLayoutManager.findLastCompletelyVisibleItemPosition() == mAdapter.getItemCount() - 1) {
                                //bottom of list!
                                new Handler().post(() -> mAdapter.getNextItemPage());
                            }
                        }
                    }
                }
            });
            if (mAdapter == null) {
                mAdapter = new JobListAdapter(this, mIsFavoriteList);
            }
            if (mPendingList != null && mPendingList.size() > 0) {
                mAdapter.addNextPageItems(mPendingList, mPendingPageIndex, mPendingHasNextPage);
                mPendingList.clear();
            }
            mRecycleView.setAdapter(mAdapter);
            mIsAdapterInited = true;
            if (mIsFavoriteList) {
                getFavoriteList();
            }
        }
        return view;
    }

    @Override
    public void onItemSelected(JobItem item) {
        selectedForDetails(mIsFavoriteList, item);
    }

    @Override
    public void onSetItemFavorite(JobItem item) {
        selectedForFavorites(item);
    }

    @Override
    public void onGetNextItemPage(int pageIndex) {
        getNextItemPage(pageIndex);
    }

    @Override
    public void addNextPageItems(List<JobItem> items, int pageIndex, boolean hasNextPage) {
        if (mIsAdapterInited) {
            if (mAdapter != null) {
                mAdapter.addNextPageItems(items, pageIndex, hasNextPage);
            }
        } else {
            if (mPendingList == null) {
                mPendingList = new ArrayList<>();
            }
            mPendingList.addAll(items);
            mPendingPageIndex = pageIndex;
            mPendingHasNextPage = hasNextPage;
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        if (mAdapter != null) {
            mAdapter.resetLoading();
        }
    }

    @Override
    public void resetAdapter() {
        mAdapter = null;
    }
}
