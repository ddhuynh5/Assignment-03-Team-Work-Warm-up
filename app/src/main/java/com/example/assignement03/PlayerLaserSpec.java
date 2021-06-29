package com.example.assignement03;

import android.graphics.PointF;

class PlayerLaserSpec extends ObjectSpec {
    // This is all the unique specifications
    // for a player laser
    private static final String tag = "Player Laser";
    private static final String bitmapName = "player_ball";
    private static final float speed = 1.65f;
    private static final PointF relativeScale =
            new PointF(20f, 20f);

    private static final String[] components = new String [] {
            "StdGraphicsComponent",
            "LaserMovementComponent",
            "LaserSpawnComponent"};

    PlayerLaserSpec(){
        super(tag, bitmapName,
                speed, relativeScale,
                components);
    }
}
