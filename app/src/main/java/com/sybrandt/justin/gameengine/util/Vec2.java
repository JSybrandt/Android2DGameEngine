package com.sybrandt.justin.gameengine.util;


public class Vec2 {
    
    private static float eps = 0.001f;
    private float x, y;
    public Vec2(){
        x = y = 0;
    }
    public Vec2(float X, float Y){
        x=X;
        y=Y;
    }

    public Vec2(double X, double Y){
        x=(float)X;
        y=(float)Y;
    }
    public float X(){return x;}
    public float Y(){return y;}
    public void setX(float f){x = f;}
    public void setY(float f){y = f;}

    public Vec2 plus(Vec2 other){
        return new Vec2(x+other.x,y+other.y);
    }

    public Vec2 minus(Vec2 other){
        return new Vec2(x-other.x,y-other.y);
    }

    public Vec2 rotate(float rads){
        return new Vec2(
                x*Math.cos(rads)-y* Math.sin(rads),
                x*Math.sin(rads)+y*Math.cos(rads)
        );
    }

    public Vec2 scale(float scale){
        return new Vec2(x*scale, y*scale);
    }

    public float magnitude(){
        return (float)Math.sqrt(x*x+y*y);
    }
    public float dot(Vec2 other){return x*other.x + y*other.y;}
    public Vec2 unit(){
        return scale(1/magnitude());
    }

    @Override
    public String toString(){return "("+x+","+y+")";}

    @Override
    public boolean equals(Object other)
    {
        if(other instanceof Vec2) {
            Vec2 otherVec = (Vec2) other;
            return (Math.abs(x - otherVec.x) < eps) && (Math.abs(y - otherVec.y) < eps);
        }
        else
            return false;
    }

    public float angle(Vec2 other){
        return  (float)Math.acos(this.dot(other) / (this.magnitude() * other.magnitude()));
    }

    
}
