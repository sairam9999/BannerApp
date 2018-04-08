package com.example.sairam.banner;

import android.content.res.Resources;
import android.graphics.drawable.TransitionDrawable;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {
    ImageView mImgView;
    Timer mContinuousTransitionDrawableTimer;
    TransitionDrawable transition;
    boolean isForward = true;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.transition_layout);
        mContinuousTransitionDrawableTimer = new Timer();

        mImgView = (ImageView)findViewById(R.id.transition_img);
        Resources res = getApplicationContext().getResources();
///       TransitionDrawable transition;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
            transition = (TransitionDrawable) res.getDrawable(R.drawable.transition_drawable, null);
        }else{
            transition = (TransitionDrawable) res.getDrawable(R.drawable.transition_drawable);
        }
        mImgView.setImageDrawable(transition);
//        transition.setCrossFadeEnabled(true);
//        transition.startTransition(5000);
        transition.setCrossFadeEnabled(true);
        ContinuousTransitionDrawableTimerTask mContinuousTransitionDrawableTimerTask = new ContinuousTransitionDrawableTimerTask();
        mContinuousTransitionDrawableTimer.schedule(mContinuousTransitionDrawableTimerTask, 0, 5000);

    }
    public class ContinuousTransitionDrawableTimerTask extends TimerTask {
        @Override
        public void run() {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    if (isForward){
                        isForward = false;
                        transition.startTransition(5000);
                    }else{
                        isForward = true;
                        transition.reverseTransition(5000);
                    }
                }
            });
        }
    }

}
