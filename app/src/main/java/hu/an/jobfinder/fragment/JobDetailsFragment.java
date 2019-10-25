package hu.an.jobfinder.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatTextView;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import hu.an.jobfinder.R;
import hu.an.jobfinder.fragment.base.JobBaseFragment;
import hu.an.jobfinder.model.JobItem;

public class JobDetailsFragment extends JobBaseFragment {

    private AppCompatTextView mTextViewTitle;
    private AppCompatTextView mTextViewCompany;
    private AppCompatTextView mTextViewLocation;
    private AppCompatTextView mTextViewDescription;
    private ImageView mImageViewFavorite;

    private JobItem mJobItem;

    public JobDetailsFragment() {
    }

    public static JobDetailsFragment newInstance() {
        return new JobDetailsFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_job_details, container, false);
        mTextViewTitle = view.findViewById(R.id.tv_title);
        mTextViewCompany = view.findViewById(R.id.tv_company);
        mTextViewLocation = view.findViewById(R.id.tv_location);
        mTextViewDescription = view.findViewById(R.id.tv_description);
        mImageViewFavorite = view.findViewById(R.id.iv_favorite);
        setView();
        return view;
    }

    private void setView() {
        if (mJobItem != null) {
            mTextViewTitle.setText(mJobItem.getTitle());
            if (mJobItem.getCompanyUrl().isEmpty()) {
                mTextViewCompany.setText(mJobItem.getCompany());
            } else {
                mTextViewCompany.setText(Html.fromHtml("<a href=\"" + mJobItem.getCompanyUrl() + "\">" + mJobItem.getCompany() + "</a>"));
                mTextViewCompany.setClickable(true);
                mTextViewCompany.setMovementMethod(LinkMovementMethod.getInstance());
            }
            mTextViewLocation.setText(mJobItem.getLocation());
            mTextViewDescription.setText(Html.fromHtml(mJobItem.getDescription()));
            mImageViewFavorite.setImageResource(mJobItem.isFavorite() ? R.drawable.star_filled_24 : R.drawable.star_border_24);
            mImageViewFavorite.setOnClickListener(v -> {
                mJobItem.setFavorite(!mJobItem.isFavorite());
                selectedForFavorites(mJobItem);
                mImageViewFavorite.setImageResource(mJobItem.isFavorite() ? R.drawable.star_filled_24 : R.drawable.star_border_24);
            });
        }
    }

    public void setJobItem(boolean fromFavoriteList, JobItem jobItem) {
        mJobItem = jobItem;
    }
}
