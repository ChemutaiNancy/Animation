package com.chemutai.animation;

import android.os.Build;
import android.provider.SyncStateContract;
import android.support.v4.view.animation.FastOutLinearInInterpolator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.transition.Explode;
import android.transition.Fade;
import android.transition.Slide;
import android.transition.Transition;
import android.transition.TransitionInflater;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.animation.AnticipateOvershootInterpolator;
import android.widget.Button;

import static com.chemutai.animation.Constants.KEY_TITLE;

public class TransitionActivity extends AppCompatActivity {

    Constants.TransitionType type;
    String toolbarTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        getWindow().requestFeature(Window.FEATURE_CONTENT_TRANSITIONS);
        
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transition);
        
        initPage();
        
        initAnimation();

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setAllowEnterTransitionOverlap(false);//for overlap
        }
    }


    private void initPage() {

        type = (Constants.TransitionType) getIntent().getSerializableExtra(Constants.KEY_ANIM_TYPE);
        toolbarTitle = getIntent().getExtras().getString(Constants.KEY_TITLE);
        Button btnExit = findViewById(R.id.exit_button);
        btnExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                   // finish();//no reverse transition
                    finishAfterTransition();
                }
            }
        });

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(toolbarTitle);
    }

    @Override
    public boolean onSupportNavigateUp(){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            finishAfterTransition();//reverse transition--executes reverse animation
        }
        return true;
    }

    private void initAnimation() {

        switch (type){
            case ExplodeJava:{
                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
                    Explode enterTransition = new Explode();
                    enterTransition.setDuration(getResources().getInteger(R.integer.anim_duration_long));
                    getWindow().setEnterTransition(enterTransition);
                    break;
                }
            }

            case ExplodeXML:{

                if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    Transition  enterTransition = TransitionInflater.from(this).inflateTransition(R.transition.explode);
                    getWindow().setEnterTransition(enterTransition);
                    break;
                }

            }

            case SlideJava:{
                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
                    Slide enterTransition = new Slide();
                    enterTransition.setSlideEdge(Gravity.TOP);
                    enterTransition.setDuration(getResources().getInteger(R.integer.anim_duration_very_long));
                    enterTransition.setInterpolator(new AnticipateOvershootInterpolator());
                    getWindow().setEnterTransition(enterTransition);
                    break;
                }

            }

            case SlideXML:{
                if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    Transition  enterTransition = TransitionInflater.from(this).inflateTransition(R.transition.slide);
                    getWindow().setEnterTransition(enterTransition);
                    break;
                }
            }

            case FadeJava:{
                if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    Fade enterTransition = new Fade();
                    enterTransition.setDuration(getResources().getInteger(R.integer.anim_duration_medium));
                    getWindow().setEnterTransition(enterTransition);
                    break;
                }
            }

            case FadeXMl:{
                if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
                    Transition enterTransition = TransitionInflater.from(this).inflateTransition(R.transition.fade);
                    getWindow().setEnterTransition(enterTransition);
                    break;
                }
            }
        }
    }
}
