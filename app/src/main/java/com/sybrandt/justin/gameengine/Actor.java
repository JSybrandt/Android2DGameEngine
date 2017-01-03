package com.sybrandt.justin.gameengine;

import com.sybrandt.justin.gameengine.activities.MainActivity;
import com.sybrandt.justin.gameengine.util.Vec2;
import com.sybrandt.justin.gameengine.geometry.Mesh;
import com.sybrandt.justin.gameengine.geometry.Texture;

import javax.microedition.khronos.opengles.GL10;

public class Actor {

    private Texture texture;
    private Mesh mesh;

    public Actor(Texture texture, Mesh mesh){
        this.texture = texture;
        this.mesh = mesh;
    }

    private Vec2 rotation = new Vec2();
    private Vec2 position = new Vec2();
    private Vec2 scale = new Vec2(1,1);

    private Vec2 velocity = new Vec2();

    public void setGL(GL10 gl){
        gl.glScalef(scale.X(), scale.Y(), 1);

        gl.glRotatef(rotation.X(), 1, 0, 0);
        gl.glRotatef(rotation.Y(), 0, 1, 0);

        gl.glTranslatef(position.X(), position.Y(), 0);
    }

    public void draw(GL10 gl){
        mesh.draw(gl,this);
    }

    public void act(float dt){
        position = position.plus(velocity.scale(dt));
        if(position.X() > 10 || position.X() < -10) {
            velocity.setX(velocity.X()*-1);
            position.setX(Math.max(Math.min(position.X(),10),-10));
        }
        if(position.Y() < -10 || position.Y() > 10) {
            velocity.setY(velocity.Y()*-1);
            position.setY(Math.max(Math.min(position.Y(),10),-10));
        }
    }

    public Texture getTexture(){return texture;}
    public Mesh getMesh(){return mesh;}

    public Vec2 getPosition(){return position;}
    public Vec2 getRotation(){return rotation;}
    public Vec2 getScale(){return scale;}


    public void setPosition(Vec2 pos){this.position = pos;};
    public void setX(float f){position.setX(f);}
    public void setY(float f){position.setY(f);}
    public void setVelocity(Vec2 pos){this.velocity = pos;};
    public void setVX(float f){velocity.setX(f);}
    public void setVY(float f){velocity.setY(f);}
    public void setRotation(Vec2 rot){this.rotation = rot;}
    public void setRotationX(float f){rotation.setX(f);}
    public void setRotationY(float f){rotation.setY(f);}
    public void setScale(Vec2 s){this.rotation = s;}
    public void setScaleX(float f){scale.setX(f);}
    public void setScaleY(float f){scale.setY(f);}
}
