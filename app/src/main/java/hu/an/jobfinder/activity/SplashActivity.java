package hu.an.jobfinder.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;

import hu.an.jobfinder.R;
import hu.an.jobfinder.activity.base.BaseActivity;

public class SplashActivity extends BaseActivity {

    //TODO - replace with branded splash

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        new Handler().postDelayed(() -> {
            startActivity(new Intent(this, JobsActivity.class));
            SplashActivity.this.finish();
        }, 3000);
    }

}
