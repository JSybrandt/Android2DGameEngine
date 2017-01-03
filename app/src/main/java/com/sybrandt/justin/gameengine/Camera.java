package com.sybrandt.justin.gameengine;

import com.sybrandt.justin.gameengine.util.Vec2;

import javax.microedition.khronos.opengles.GL10;

public class Camera {

    public Camera(){

    }
    private Vec2 position = new Vec2();
    private float height = 50;

    public void setGL(GL10 gl){
        gl.glTranslatef(position.X(), position.Y(), -1 * height);
    }
}
