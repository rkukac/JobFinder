package hu.an.jobfinder.adapter;

import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.List;

import hu.an.jobfinder.R;
import hu.an.jobfinder.interfaces.OnListInteractionListener;
import hu.an.jobfinder.model.JobItem;

public class JobListAdapter extends RecyclerView.Adapter<JobListAdapter.ViewHolder> {

    private final List<JobItem> mValues;
    private final OnListInteractionListener mListener;
    private final boolean mIsEnablePagination;
    private int mPageIndex;
    private boolean mIsLoading;

    public JobListAdapter(List<JobItem> items, OnListInteractionListener listener, boolean isEnablePagination) {
        mValues = items;
        mListener = listener;
        mPageIndex = 0;
        mIsEnablePagination = isEnablePagination;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_job_list, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
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
            holder.mItem.setFavorite(holder.mItem.isFavorite());
            if (mListener != null) {
                mListener.onSetItemFavorite(holder.mItem);
            }
            holder.mImageViewFavorite.setImageResource(holder.mItem.isFavorite() ? R.drawable.star_filled_24 : R.drawable.star_border_24);
        });
    }

    private void getNextItemPage() {
        if (!mIsLoading && mIsEnablePagination && mListener != null) {
            mIsLoading = true;
            mPageIndex++;
            mListener.onGetNextItemPage(mPageIndex);
            //TODO - start progress
        }
    }

    public void addNextPageItems(List<JobItem> items){
        if (mIsLoading){
            mIsLoading = false;
            mValues.addAll(items);
            notifyDataSetChanged();
            //TODO - hide progress
        }
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final AppCompatTextView mTextViewTitle;
        public final AppCompatTextView mTextViewCompany;
        public final AppCompatTextView mTextViewLocation;
        public final ImageView mImageViewFavorite;
        public JobItem mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            mTextViewTitle = (AppCompatTextView) view.findViewById(R.id.tv_item_title);
            mTextViewCompany = (AppCompatTextView) view.findViewById(R.id.tv_item_company);
            mTextViewLocation = (AppCompatTextView) view.findViewById(R.id.tv_item_location);
            mImageViewFavorite = (ImageView) view.findViewById(R.id.iv_favorite);
        }
    }
}