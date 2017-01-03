package com.sybrandt.justin.gameengine;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;


import android.opengl.GLSurfaceView.Renderer;
import android.opengl.GLU;
import android.os.SystemClock;
import android.util.FloatProperty;
import android.util.Log;


import com.sybrandt.justin.gameengine.Actor;

import java.util.ArrayList;


public class Game implements Renderer {

    ArrayList<Actor> actors = new ArrayList<>();
    Camera camera = new Camera();


    float lastFrameTime = 0;
    float fpsTimer = 0;
    int fpsCounter = 0;
	public Game() {

	}

    private float getDT(){
        float thisFrameTime = (float) SystemClock.uptimeMillis() / 1000f;
        float ret = 0;
        if(lastFrameTime > 0)
            ret = thisFrameTime - lastFrameTime;
        lastFrameTime = thisFrameTime;
        return  ret;
    }

    public void addActor(Actor actor){ actors.add(actor);}


	public void onSurfaceCreated(GL10 gl, EGLConfig config) {
		// Set the background color to black ( rgba ).
		gl.glClearColor(0.0f, 0.0f, 0.0f, 0.5f);
		// Enable Smooth Shading, default not really needed.
		gl.glShadeModel(GL10.GL_SMOOTH);
		// Depth buffer setup.
		gl.glClearDepthf(1.0f);
		// Enables depth testing.
		gl.glEnable(GL10.GL_DEPTH_TEST);
		// The type of depth testing to do.
		gl.glDepthFunc(GL10.GL_LEQUAL);
		// Really nice perspective calculations.
		gl.glHint(GL10.GL_PERSPECTIVE_CORRECTION_HINT, GL10.GL_NICEST);
	}

    public void updateGame(float dt){
        for(Actor a : actors){
            a.act(dt);
        }
    }

	public void onDrawFrame(GL10 gl) {

        float dt = getDT();
        updateGame(dt);

        fpsCounter += 1;
        fpsTimer += dt;
        if(fpsTimer > 1){
            Log.d("FPS", Integer.toString(fpsCounter));
            fpsTimer = 0;
            fpsCounter = 0;
        }

        // Clears the screen and depth buffer.
        gl.glClear(GL10.GL_COLOR_BUFFER_BIT | GL10.GL_DEPTH_BUFFER_BIT);

        for (Actor a : actors)
        {
            // Replace the current matrix with the identity matrix
            gl.glLoadIdentity();
            camera.setGL(gl);
            a.draw(gl);
        }

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * android.opengl.GLSurfaceView.Renderer#onSurfaceChanged(javax.microedition
	 * .khronos.opengles.GL10, int, int)
	 */
	public void onSurfaceChanged(GL10 gl, int width, int height) {
		// Sets the current view port to the new size.
		gl.glViewport(0, 0, width, height);
		// Select the projection matrix
		gl.glMatrixMode(GL10.GL_PROJECTION);
		// Reset the projection matrix
		gl.glLoadIdentity();
		// Calculate the aspect ratio of the window
		GLU.gluPerspective(gl, 45.0f, (float) width / (float) height, 0.1f,
				1000.0f);
		// Select the modelview matrix
		gl.glMatrixMode(GL10.GL_MODELVIEW);
		// Reset the modelview matrix
		gl.glLoadIdentity();
	}

}
