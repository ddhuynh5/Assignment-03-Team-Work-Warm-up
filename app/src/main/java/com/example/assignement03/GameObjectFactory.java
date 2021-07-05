package com.example.assignement03;

import android.content.Context;
import android.graphics.PointF;

class GameObjectFactory {
    private Context mContext;
    private PointF mScreenSize;
    private GameEngine mGameEngineReference;

    GameObjectFactory(Context c, PointF screenSize,
                      GameEngine gameEngine) {

        this.mContext = c;
        this.mScreenSize = screenSize;
        mGameEngineReference = gameEngine;
    }

    GameObject create(ObjectSpec spec) {

        GameObject object = new GameObject();

        int numComponents = spec.getComponents().length;

        final float HIDDEN = -2000f;

        object.setmTag(spec.getTag());

        // Configure the speed relative to the screen size
        float speed = mScreenSize.x / spec.getSpeed();

        // Configure the object size relative to screen size
        PointF objectSize =
                new PointF(mScreenSize.x / spec.getScale().x,
                        mScreenSize.y / spec.getScale().y);

        // Set the location to somewhere off-screen
        PointF location = new PointF(HIDDEN, HIDDEN);

        object.setTransform(new Transform(speed, objectSize.x,
                objectSize.y, location, mScreenSize));

        // More code here next...
        // Loop through and add/initialize all the components
        for (int i = 0; i < numComponents; i++) {
            switch (spec.getComponents()[i]) {
                case "PlayerInputComponent":
                    object.setInput(new PlayerInputComponent
                            (mGameEngineReference));
                    break;
                case "StdGraphicsComponent":
                    object.setGraphics(new StdGraphicsComponent(),
                            mContext, spec, objectSize);
                    break;
                case "PlayerMovementComponent":
                    object.setMovement(new PlayerMovementComponent());
                    break;
                case "LaserMovementComponent":
                    object.setMovement(new LaserMovementComponent());
                    break;
                case "PlayerSpawnComponent":
                    object.setSpawner(new PlayerSpawnComponent());
                    break;
                case "LaserSpawnComponent":
                    object.setSpawner(new LaserSpawnComponent());
                    break;
                case "BackgroundGraphicsComponent":
                    object.setGraphics(new BackgroundGraphicsComponent(),
                            mContext, spec, objectSize);
                    break;
                case "BackgroundMovementComponent":
                    object.setMovement(new BackgroundMovementComponent());
                    break;
                case "BackgroundSpawnComponent":
                    object.setSpawner(new BackgroundSpawnComponent());
                    break;
                case "EnemyChaseMovementComponent":
                    object.setMovement(new EnemyChaseMovementComponent());
                    break;
                case "EnemyHorizontalSpawnComponent":
                    object.setSpawner(
                            new EnemyHorizontalSpawnComponent());
                    break;
                case "EnemyVerticalSpawnComponent":
                    object.setSpawner(
                            new EnemyVerticalSpawnComponent());
                    break;
                case "GoldSpawnComponent":
                    object.setSpawner(
                            new GoldSpawnComponent());
                    break;
                default:
                    // Error unidentified component
                    break;
            }
        }
        // Return the completed GameObject to the Level class
        return object;
    }
}