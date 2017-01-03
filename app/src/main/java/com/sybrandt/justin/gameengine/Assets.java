package com.sybrandt.justin.gameengine;

import com.sybrandt.justin.gameengine.geometry.Mesh;
import com.sybrandt.justin.gameengine.geometry.Texture;

import java.util.ArrayList;

/**
 * Created by jsybran on 1/2/2017.
 */
public class Assets {
    private static Assets ourInstance = new Assets();

    public static Assets getInstance() {
        return ourInstance;
    }

    private Assets() {

    }

    ArrayList<Mesh> meshes = new ArrayList<>();
    ArrayList<Texture> textures = new ArrayList<>();


}
