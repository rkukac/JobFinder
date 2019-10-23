package hu.an.jobfinder.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import hu.an.jobfinder.R;
import hu.an.jobfinder.fragment.base.JobBaseFragment;

public class JobSearchFragment extends JobBaseFragment {

    public JobSearchFragment() {
    }

    public static JobSearchFragment newInstance() {
        return new JobSearchFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_job_search, container, false);
        return view;
    }
}
