package hu.an.jobfinder.activity.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;

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
}
