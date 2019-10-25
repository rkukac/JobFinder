package hu.an.jobfinder.activity.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

public abstract class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public void loadFragment(int container, Fragment fragment) {
        getSupportFragmentManager().beginTransaction()
                .replace(container, fragment)
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                .addToBackStack(null)
                .commit();
    }

    public void showToastMessage(@StringRes int messageRes) {
        Toast.makeText(this, getString(messageRes), Toast.LENGTH_LONG).show();
    }
}
