package hu.an.jobfinder.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;

import java.util.ArrayList;
import java.util.List;

import hu.an.jobfinder.R;
import hu.an.jobfinder.interfaces.OnListInteractionListener;
import hu.an.jobfinder.model.JobItem;

public class JobListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private final int VIEW_TYPE_ITEM = 0;
    private final int VIEW_TYPE_LOADING = 1;

    private final List<JobItem> mValues;
    private final OnListInteractionListener mListener;
    private final boolean mIsFavoriteList;
    private int mPageIndex;
    private boolean mHasNextPage;
    private boolean mIsLoading;

    public JobListAdapter(OnListInteractionListener listener, boolean isFavoriteList) {
        mValues = new ArrayList<>();
        if (!isFavoriteList) {
            mValues.add(null);
        }
        mIsLoading = true;
        mIsFavoriteList = isFavoriteList;
        mHasNextPage = true;
        mListener = listener;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == VIEW_TYPE_ITEM) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false);
            return new ItemViewHolder(view);
        } else {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_loading, parent, false);
            return new LoadingViewHolder(view);
        }
    }

    @Override
    public int getItemViewType(int position) {
        return mValues.get(position) == null ? VIEW_TYPE_LOADING : VIEW_TYPE_ITEM;
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof ItemViewHolder) {
            onBindItem((ItemViewHolder) holder, position);
        } else if (holder instanceof LoadingViewHolder) {
            onBindLoading((LoadingViewHolder) holder, position);
        }
    }

    private void onBindItem(ItemViewHolder holder, int position) {
        holder.mItem = mValues.get(position);
        holder.mTextViewTitle.setText(mValues.get(position).getTitle());
        holder.mTextViewCompany.setText(mValues.get(position).getCompany());
        holder.mTextViewLocation.setText(mValues.get(position).getLocation());
        holder.mView.setOnClickListener(v -> {
            if (mListener != null) {
                mListener.onItemSelected(holder.mItem);
            }
        });
        holder.mImageViewFavorite.setImageResource(holder.mItem.isFavorite() ? R.drawable.star_filled_24 : R.drawable.star_border_24);
        holder.mImageViewFavorite.setOnClickListener(v -> {
            holder.mItem.setFavorite(!holder.mItem.isFavorite());
            if (mListener != null) {
                mListener.onSetItemFavorite(holder.mItem);
            }
            if (mIsFavoriteList) {
                removeItem(position);
            } else {
                holder.mImageViewFavorite.setImageResource(holder.mItem.isFavorite() ? R.drawable.star_filled_24 : R.drawable.star_border_24);
            }
        });
    }

    private void onBindLoading(LoadingViewHolder holder, int position) {
    }

    private void removeItem(int position) {
        mValues.remove(position);
        notifyItemRemoved(position);
        notifyItemRangeChanged(position, mValues.size());
    }

    public void getNextItemPage() {
        if (!mIsLoading && mHasNextPage && !mIsFavoriteList && mListener != null) {
            mIsLoading = true;
            mPageIndex++;
            mValues.add(null);
            notifyItemInserted(mValues.size() - 1);
            mListener.onGetNextItemPage(mPageIndex);
        }
    }

    public void addNextPageItems(List<JobItem> items, int pageIndex, boolean hasNextPage) {
        if (mIsLoading) {
            mIsLoading = false;
            mPageIndex = pageIndex;
            mHasNextPage = hasNextPage;
            if (mValues.size() > 0 && mValues.get(mValues.size() - 1) == null) {
                mValues.remove(mValues.size() - 1);
            }
            mValues.addAll(items);
            notifyItemRangeInserted(mValues.size() - items.size(), mValues.size());
        }
    }

    public boolean isLoading() {
        return mIsLoading;
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public void resetLoading() {
        if (isLoading()){
            mIsLoading = false;
            if (mValues.size() > 0 && mValues.get(mValues.size() - 1) == null) {
                mValues.remove(mValues.size() - 1);
            }
        }
    }

    public class ItemViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final AppCompatTextView mTextViewTitle;
        public final AppCompatTextView mTextViewCompany;
        public final AppCompatTextView mTextViewLocation;
        public final ImageView mImageViewFavorite;
        public JobItem mItem;

        public ItemViewHolder(View view) {
            super(view);
            mView = view;
            mTextViewTitle = view.findViewById(R.id.tv_item_title);
            mTextViewCompany = view.findViewById(R.id.tv_item_company);
            mTextViewLocation = view.findViewById(R.id.tv_item_location);
            mImageViewFavorite = view.findViewById(R.id.iv_favorite);
        }
    }

    public class LoadingViewHolder extends RecyclerView.ViewHolder {

        public final ProgressBar mProgressBar;

        public LoadingViewHolder(@NonNull View view) {
            super(view);
            mProgressBar = view.findViewById(R.id.progressBar);
        }
    }
}