package hu.an.jobfinder.activity;

import android.os.Bundle;
import android.support.v7.widget.AppCompatTextView;
import android.util.SparseArray;
import android.view.View;
import android.widget.LinearLayout;

import hu.an.jobfinder.R;
import hu.an.jobfinder.activity.base.BaseActivity;
import hu.an.jobfinder.fragment.JobDetailsFragment;
import hu.an.jobfinder.fragment.JobFavoritesFragment;
import hu.an.jobfinder.fragment.JobListFragment;
import hu.an.jobfinder.fragment.JobSearchFragment;
import hu.an.jobfinder.fragment.base.JobBaseFragment;
import hu.an.jobfinder.interfaces.OnFragmentInteractionListener;
import hu.an.jobfinder.model.JobItem;

public class JobsActivity extends BaseActivity implements OnFragmentInteractionListener, View.OnClickListener {

    private static final int FRAGMENT_KEY_SEARCH = 0;
    private static final int FRAGMENT_KEY_LIST = 1;
    private static final int FRAGMENT_KEY_DETAILS = 2;
    private static final int FRAGMENT_KEY_FAVORITES = 3;

    private SparseArray<JobBaseFragment> mFragmentMap;
    private int mIndexCurrentFragment;
    private int mIndexPrevFragment;
    private int mIndexNextFragment;

    private AppCompatTextView mTextViewPrevTitle;
    private AppCompatTextView mTextViewNextTitle;
    private LinearLayout mLayoutPrev;
    private LinearLayout mLayoutNext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jobs);
        init(savedInstanceState);
    }

    private void init(Bundle savedInstanceState) {
        //TODO - add with databinding
        mTextViewPrevTitle = findViewById(R.id.tv_prev_title);
        mTextViewNextTitle = findViewById(R.id.tv_next_title);
        mLayoutPrev = findViewById(R.id.ll_prev);
        mLayoutNext = findViewById(R.id.ll_next);
        mLayoutNext.setOnClickListener(this);
        mLayoutPrev.setOnClickListener(this);

        initFragments();
        mIndexPrevFragment = -1;
        mIndexNextFragment = -1;
        //if (savedInstanceState == null) {
        //mFragmentJobSearch = JobSearchFragment.newInstance();
        setFragment(FRAGMENT_KEY_SEARCH);

        //}
    }

    private void initFragments() {
        mFragmentMap = new SparseArray<>();
        mFragmentMap.put(FRAGMENT_KEY_SEARCH, JobSearchFragment.newInstance());
        mFragmentMap.put(FRAGMENT_KEY_LIST, JobListFragment.newInstance());
        mFragmentMap.put(FRAGMENT_KEY_DETAILS, JobDetailsFragment.newInstance());
        mFragmentMap.put(FRAGMENT_KEY_FAVORITES, JobFavoritesFragment.newInstance());
    }

    private void setFragment(int fragmentKey) {
        setNavigation(fragmentKey);
        mIndexCurrentFragment = fragmentKey;
        loadFragment(R.id.container, mFragmentMap.get(fragmentKey));
    }

    private void setNavigation(int fragmentKey) {
        switch (fragmentKey) {
            case FRAGMENT_KEY_SEARCH:
                mIndexPrevFragment = -1;
                mIndexNextFragment = FRAGMENT_KEY_FAVORITES;
                break;
            case FRAGMENT_KEY_LIST:
                mIndexPrevFragment = FRAGMENT_KEY_SEARCH;
                mIndexNextFragment = FRAGMENT_KEY_FAVORITES;
                break;
            case FRAGMENT_KEY_DETAILS:
                mIndexPrevFragment = mIndexCurrentFragment == FRAGMENT_KEY_LIST ? FRAGMENT_KEY_LIST : FRAGMENT_KEY_FAVORITES;
                mIndexNextFragment = -1;
                break;
            case FRAGMENT_KEY_FAVORITES:
                mIndexPrevFragment = mIndexCurrentFragment == FRAGMENT_KEY_LIST ? FRAGMENT_KEY_LIST : FRAGMENT_KEY_SEARCH;
                mIndexNextFragment = -1;
                break;
        }
        setNavigationView();
    }

    private void setNavigationView() {
        if (mIndexPrevFragment > -1) {
            mTextViewPrevTitle.setText(getNavigationText(mIndexPrevFragment));
        }
        mLayoutPrev.setVisibility(mIndexPrevFragment == -1 ? View.GONE : View.VISIBLE);
        if (mIndexNextFragment > -1) {
            mTextViewNextTitle.setText(getNavigationText(mIndexNextFragment));
        }
        mLayoutNext.setVisibility(mIndexNextFragment == -1 ? View.GONE : View.VISIBLE);
    }

    private String getNavigationText(int fragmentIndex) {
        switch (fragmentIndex) {
            default:
                return "";
            case FRAGMENT_KEY_SEARCH:
                return getString(R.string.title_search);
            case FRAGMENT_KEY_LIST:
                return getString(R.string.title_search_list);
            case FRAGMENT_KEY_FAVORITES:
                return getString(R.string.title_favorites);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    public void onJobSelectedForDetails(boolean fromList, JobItem jobItem) {

    }

    @Override
    public void onJobSelectedForFavorites(boolean isSaved, JobItem jobItem) {

    }

    @Override
    public void onClick(View v) {
        if (v != null) {
            switch (v.getId()) {
                case R.id.ll_prev:
                    setFragment(mIndexPrevFragment);
                    break;
                case R.id.ll_next:
                    setFragment(mIndexNextFragment);
                    break;
            }
        }
    }
}