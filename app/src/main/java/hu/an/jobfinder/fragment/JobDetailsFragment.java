package hu.an.jobfinder.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import hu.an.jobfinder.R;
import hu.an.jobfinder.fragment.base.JobBaseFragment;

public class JobDetailsFragment extends JobBaseFragment {

    public JobDetailsFragment() {
    }

    public static JobDetailsFragment newInstance() {
        return new JobDetailsFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_job_details, container, false);
        return view;
    }
}
