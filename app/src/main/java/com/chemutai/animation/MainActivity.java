package com.chemutai.animation;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.transition.Slide;
import android.util.Pair;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import static android.app.ActivityOptions.makeSceneTransitionAnimation;

public class MainActivity extends AppCompatActivity {

    private ImageView logo, profilePic;
    private TextView txtShared;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        logo = findViewById(R.id.imgLogo);
        profilePic = findViewById(R.id.imgBus);
        txtShared = findViewById(R.id.txtSharedElement);
        
        setUpWindowAnimations();
    }

    private void setUpWindowAnimations() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
            Slide slideTransition = new Slide();
            slideTransition.setSlideEdge(Gravity.LEFT);
            slideTransition.setDuration(1000);

            getWindow().setEnterTransition(slideTransition);

            getWindow().setExitTransition(slideTransition);

            getWindow().setAllowReturnTransitionOverlap(false);//for overlap
        }
    }

    public void checkRippleAnimation(View view){
        Intent intent = new Intent(this, RippleActivity.class);
        startActivity(intent);
    }

    public void sharedElementTransition(View view) {
        Pair[] pair = new Pair[3];
        pair[0] = new Pair<View, String>(logo, "logoShared");
        pair[1] = new Pair<View, String>(profilePic, "profile_pic_shared");
        pair[2] = new Pair<View, String>(txtShared, "ripple_shared");
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
            ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(this,pair);
            Intent i = new Intent(MainActivity.this, SharedElementActivity.class);
            startActivity(i, options.toBundle());
        }
    }

    public void explodeTransitionByJava(View view) {

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
            ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(this);
            Intent i = new Intent(MainActivity.this, TransitionActivity.class);
            i.putExtra(Constants.KEY_ANIM_TYPE, Constants.TransitionType.ExplodeJava);
            i.putExtra(Constants.KEY_TITLE, " Explode By Java");
            startActivity(i, options.toBundle());
        }
    }

    public void explodeTransitionByXML(View view) {

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
            ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(this);
            Intent i = new Intent(MainActivity.this, TransitionActivity.class);
            i.putExtra(Constants.KEY_ANIM_TYPE, Constants.TransitionType.ExplodeXML);
            i.putExtra(Constants.KEY_TITLE, " Explode By XML");
            startActivity(i, options.toBundle());
        }

    }

    public void slideTransitionByCode(View view) {

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
            ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(this);
            Intent i = new Intent(MainActivity.this, TransitionActivity.class);
            i.putExtra(Constants.KEY_ANIM_TYPE, Constants.TransitionType.SlideJava);
            i.putExtra(Constants.KEY_TITLE, " Slide By Java Code");
            startActivity(i, options.toBundle());
        }
    }

    public void slideTransitionByXML(View view) {

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
            ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(this);
            Intent i = new Intent(MainActivity.this, TransitionActivity.class);
            i.putExtra(Constants.KEY_ANIM_TYPE, Constants.TransitionType.SlideXML);
            i.putExtra(Constants.KEY_TITLE, " Slide By XML");
            startActivity(i, options.toBundle());
        }
    }

    public void fadeTransitionBJava(View view) {

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
            ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(this);
            Intent i = new Intent(MainActivity.this, TransitionActivity.class);
            i.putExtra(Constants.KEY_ANIM_TYPE, Constants.TransitionType.FadeJava);
            i.putExtra(Constants.KEY_TITLE, " Fade By Java Code");
            startActivity(i, options.toBundle());
        }
    }

    public void fadeTransitionBXML(View view) {

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
            ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(this);
            Intent i = new Intent(MainActivity.this, TransitionActivity.class);
            i.putExtra(Constants.KEY_ANIM_TYPE, Constants.TransitionType.FadeXMl);
            i.putExtra(Constants.KEY_TITLE, " Fade By XML");
            startActivity(i, options.toBundle());
        }
    }


}
