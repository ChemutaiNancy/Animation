package com.chemutai.animation;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.SuppressLint;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.Window;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class SharedElementActivity extends AppCompatActivity {

    private RelativeLayout revealDemo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getWindow().requestFeature(Window.FEATURE_CONTENT_TRANSITIONS);//enables window transitions

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shared_element);
        
        initPage();
    }

    @Override
    public  boolean onSupportNavigateUp(){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            finishAfterTransition();
        }
        return true;
    }

    private void initPage() {

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Shared Element Transition");

        revealDemo = findViewById(R.id.click_reveal);
        revealDemo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                makeCircularRevealAnimation(revealDemo);
            }
        });

        /*Button btn = findViewById(R.id.exitButton);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    finishAfterTransition();
                }
            }
        });*/


    }

    private void makeCircularRevealAnimation(View view) {

        final TextView txtDesc = findViewById(R.id.txtDesc);

        int centerX = (view.getRight() + view.getLeft())/2;
        int centerY = (view.getTop() + view.getBottom())/2;

        float radius = Math.max(txtDesc.getWidth(), txtDesc.getHeight()) * 2.0f;

        if (txtDesc.getVisibility() == View.INVISIBLE){
            txtDesc.setVisibility(View.VISIBLE);
            txtDesc.setText(R.string.ripple);

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                ViewAnimationUtils.createCircularReveal(txtDesc, centerX, centerY, 0, radius).start();
                //0 is the starting point in which animation will start.
            }
        }
        else {
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
               Animator reveal = ViewAnimationUtils.createCircularReveal(txtDesc, centerX, centerY, radius,0);//satrting point will be the radius
                reveal.addListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        /*super.onAnimationEnd(animation);*/
                        txtDesc.setVisibility(View.INVISIBLE);
                    }
                });
                reveal.start();
            }

        }
    }
}
