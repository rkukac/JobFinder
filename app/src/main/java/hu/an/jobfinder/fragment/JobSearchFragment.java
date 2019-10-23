package hu.an.jobfinder.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatEditText;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import hu.an.jobfinder.R;
import hu.an.jobfinder.fragment.base.JobBaseFragment;

public class JobSearchFragment extends JobBaseFragment implements View.OnClickListener {

    private AppCompatEditText mEditTextDescription;
    private AppCompatEditText mEditTextLocation;

    public JobSearchFragment() {
    }

    public static JobSearchFragment newInstance() {
        return new JobSearchFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_job_search, container, false);
        view.findViewById(R.id.bt_search).setOnClickListener(this);
        mEditTextDescription = view.findViewById(R.id.et_description);
        mEditTextLocation = view.findViewById(R.id.et_location);
        return view;
    }

    @Override
    public void onClick(View v) {
        if (v != null && R.id.bt_search == v.getId()) {
            startSearch();
        }
    }

    private void startSearch() {
        if ((mEditTextDescription.getText() != null && !mEditTextDescription.getText().toString().isEmpty()) ||
                (mEditTextLocation.getText() != null && !mEditTextLocation.getText().toString().isEmpty())) {
            search(mEditTextDescription.getText().toString().trim(), mEditTextLocation.getText().toString().trim(), 0);
        } else {
            showMessage(R.string.alert_message_missing_search_params);
        }
    }
}
