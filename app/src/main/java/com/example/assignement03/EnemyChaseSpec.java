package com.example.assignement03;

import android.graphics.PointF;

public class EnemyChaseSpec extends ObjectSpec{
    // This is all the unique specifications
    // for an alien that chases the player
    private static final String tag = "Enemy";
    private static final String bitmapName = "enemy_chase";
    private static final float speed = 4f;
    private static final PointF relativeScale =
            new PointF(15f, 15f);

    private static final String[] components = new String [] {
            "StdGraphicsComponent",
            "EnemyChaseMovementComponent",
            "EnemyHorizontalSpawnComponent"};

    EnemyChaseSpec(){
        super(tag, bitmapName,
                speed, relativeScale,
                components);
    }
}
