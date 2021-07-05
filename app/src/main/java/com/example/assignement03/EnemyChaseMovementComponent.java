package com.example.assignement03;

import android.graphics.PointF;
import java.util.Random;

class EnemyChaseMovementComponent implements MovementComponent {

    private Random mShotRandom = new Random();

    @Override
    public boolean move(long fps, Transform t, Transform playerTransform) {

        // How high is the screen?
        float screenWidth = t.getmScreenSize().x;
        // Where is the player?
        PointF playerLocation = playerTransform.getLocation();

        // How tall is the enemy
        float height = t.getObjectHeight();
        // Is the enemy facing right?
        boolean facingRight =t.getFacingRight();
        // How far off before the enemy doesn't bother chasing?
        float mChasingDistance = t.getmScreenSize().x / 3f;
        // How far can the AI see?
        float mSeeingDistance = t.getmScreenSize().x / 1.5f;
        // Where is the enemy?
        PointF location = t.getLocation();
        // How fast is the enemy?
        float speed = t.getSpeed();

        // Relative speed difference with player
        float verticalSpeedDifference = .3f;
        float slowDownRelativeToPlayer = 1.8f;
        // Prevent the ship locking on too accurately
        float verticalSearchBounce = 20f;

        // move in the direction of the player
        // but relative to the player's direction of travel
        if (Math.abs(location.x - playerLocation.x)
                > mChasingDistance) {

            if (location.x < playerLocation.x) {
                t.headRight();
            } else if (location.x > playerLocation.x) {
                t.headLeft();
            }
        }

        // Can the enemy "see" the player? If so try and align vertically
        if (Math.abs(location.x - playerLocation.x)
                <= mSeeingDistance) {

            // Use a cast to get rid of unnecessary floats that make enemy judder
            if ((int) location.y - playerLocation.y
                    < -verticalSearchBounce) {

                t.headDown();
            } else if ((int) location.y - playerLocation.y
                    > verticalSearchBounce) {

                t.headUp();
            }

            // Compensate for movement relative to player-
            // but only when in view.
            // Otherwise enemy will disappear miles off to one side
            if(!playerTransform.getFacingRight()){
                location.x += speed * slowDownRelativeToPlayer / fps;
            } else{
                location.x -=  speed * slowDownRelativeToPlayer / fps;
            }
        }
        else{
            // stop vertical movement otherwise alien will
            // disappear off the top or bottom
            t.stopVertical();
        }

        // Moving vertically is slower than horizontally
        // Change this to make game harder
        if(t.headingDown()){
            location.y += speed * verticalSpeedDifference / fps;
        }
        else if(t.headingUp()){
            location.y -= speed * verticalSpeedDifference / fps;
        }

        // Move horizontally
        if(t.headingLeft()){
            location.x -= (speed) / fps;
        }
        if(t.headingRight()){
            location.x += (speed) / fps;
        }

        // Update the collider
        t.updateCollider();

        return true;
    }
}
