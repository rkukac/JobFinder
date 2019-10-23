package hu.an.jobfinder.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import hu.an.jobfinder.R;
import hu.an.jobfinder.fragment.base.JobBaseFragment;

public class JobListFragment extends JobBaseFragment {

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

        return view;
    }

}
