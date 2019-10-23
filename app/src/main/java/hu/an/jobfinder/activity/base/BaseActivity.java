package hu.an.jobfinder.activity.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import hu.an.jobfinder.model.JobItem;

public abstract class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public void loadFragment(int container, Fragment fragment) {
        getSupportFragmentManager().beginTransaction()
                .replace(container, fragment)
                .commit();
    }

    public void showToastMessage(@StringRes int messageRes) {
        Toast.makeText(this, getString(messageRes), Toast.LENGTH_LONG).show();
    }

    public List<JobItem> getMockList() {
        return new ArrayList<JobItem>() {{
            add(new JobItem("1", "Comp1", "https://jobs.github.com/api", "New", "Title", "sdasdasdasdasdsadsa cdc ds cac  dsac sa cs a"));
            add(new JobItem("2", "Comp2", "https://jobs.github.com/api", "New", "Title", "sdasdasdasdasdsadsa cdc ds cac  dsac sa cs a"));
            add(new JobItem("3", "Comp3", "https://jobs.github.com/api", "New", "Title", "sdasdasdasdasdsadsa cdc ds cac  dsac sa cs a"));
            add(new JobItem("4", "Comp4", "https://jobs.github.com/api", "New", "Title", "sdasdasdasdasdsadsa cdc ds cac  dsac sa cs a"));
            add(new JobItem("5", "Comp5", "https://jobs.github.com/api", "New", "Title", "sdasdasdasdasdsadsa cdc ds cac  dsac sa cs a"));
            add(new JobItem("6", "Comp6", "https://jobs.github.com/api", "New", "Title", "sdasdasdasdasdsadsa cdc ds cac  dsac sa cs a"));
            add(new JobItem("7", "Comp7", "https://jobs.github.com/api", "New", "Title", "sdasdasdasdasdsadsa cdc ds cac  dsac sa cs a"));
        }};
    }
}
