package com.sybrandt.justin.gameengine.activities;

import android.graphics.BitmapFactory;
import android.opengl.GLSurfaceView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

import com.sybrandt.justin.gameengine.Actor;
import com.sybrandt.justin.gameengine.Game;
import com.sybrandt.justin.gameengine.R;
import com.sybrandt.justin.gameengine.util.Vec2;
import com.sybrandt.justin.gameengine.geometry.SimplePlane;
import com.sybrandt.justin.gameengine.geometry.Texture;

import java.util.Random;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);

        // Remove the title bar from the window.
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);

        // Make the windows into full screen mode.
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        // Create a OpenGL view.
        GLSurfaceView view = new GLSurfaceView(this);

        // Creating and attaching the renderer.
        Game renderer = new Game();
        view.setRenderer(renderer);
        setContentView(view);

        // Create a new plane.
        SimplePlane plane = new SimplePlane(1, 1);

        Texture texture1 = new Texture(BitmapFactory.decodeResource(getResources(), R.drawable.jay));
        Texture texture2 = new Texture(BitmapFactory.decodeResource(getResources(), R.drawable.android));

        Random rand = new Random();
        for(int i = 0; i < 1000; i++){
            Actor t = new Actor((i%2==0?texture1:texture2),plane);
            t.setVelocity(new Vec2(rand.nextFloat()*10, rand.nextFloat()*10));
            renderer.addActor(t);
        }

    }
}
