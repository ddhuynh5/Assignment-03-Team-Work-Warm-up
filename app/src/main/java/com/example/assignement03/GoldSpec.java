package com.example.assignement03;

import android.graphics.PointF;

public class GoldSpec extends ObjectSpec{
    // This is all the unique specifications
    // for a gold coin
    private static final String tag = "Gold Coin";
    private static final String bitmapName = "gold";
    private static final float speed = 0f;
    private static final PointF relativeScale =
            new PointF(30f, 30f);

    private static final String[] components = new String [] {
            "GoldSpawnComponent",
            "StdGraphicsComponent",};

    GoldSpec() {
        super(tag, bitmapName,
                speed, relativeScale,
                components);
    }
}
