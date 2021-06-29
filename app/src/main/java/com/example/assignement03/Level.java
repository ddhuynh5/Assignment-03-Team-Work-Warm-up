package com.example.assignement03;

import android.content.Context;
import android.graphics.PointF;

import java.util.ArrayList;

class Level {

    // Keep track of specific types
    public static final int BACKGROUND_INDEX = 0;
    public static final int PLAYER_INDEX = 1;
    public static final int FIRST_PLAYER_LASER = 2;
    public static final int LAST_PLAYER_LASER = 4;
    public static int mNextPlayerLaser;
    public static final int FIRST_ENEMY = 5;
    public static final int SECOND_ENEMY = 6;
    public static final int THIRD_ENEMY = 7;
    public static final int FOURTH_ENEMY = 8;
    public static final int FIFTH_ENEMY = 9;
    public static final int SIXTH_ENEMY = 10;
    public static final int LAST_ENEMY = 10;

    // This will hold all the instances of GameObject
    private ArrayList<GameObject> objects;
    public Level(Context context,
                 PointF mScreenSize,
                 GameEngine ge){

        objects = new ArrayList<>();
        GameObjectFactory factory = new GameObjectFactory(
                context, mScreenSize, ge);

        buildGameObjects(factory);
    }

    ArrayList<GameObject> buildGameObjects(
            GameObjectFactory factory){

        objects.clear();
        objects.add(BACKGROUND_INDEX, factory
                .create(new BackgroundSpec()));

        objects.add(PLAYER_INDEX, factory
                .create(new PlayerSpec()));

        // Spawn the player's lasers
        for (int i = FIRST_PLAYER_LASER;
             i != LAST_PLAYER_LASER + 1; i++) {

            objects.add(i, factory.create(new PlayerLaserSpec()));
        }

        mNextPlayerLaser = FIRST_PLAYER_LASER;

        // Create some enemies
        objects.add(FIRST_ENEMY, factory
                .create(new EnemyChaseSpec()));
        objects.add(SECOND_ENEMY, factory
                .create(new EnemyChaseSpec()));
        objects.add(THIRD_ENEMY, factory
                .create(new EnemyChaseSpec()));
        objects.add(FOURTH_ENEMY, factory
                .create(new EnemyChaseSpec()));
        objects.add(FIFTH_ENEMY, factory
                .create(new EnemyChaseSpec()));
        objects.add(SIXTH_ENEMY, factory
                .create(new EnemyChaseSpec()));

        return objects;
    }

    ArrayList<GameObject> getGameObjects(){
        return objects;
    }


}
