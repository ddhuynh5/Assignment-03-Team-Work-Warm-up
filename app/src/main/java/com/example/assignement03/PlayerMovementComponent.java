package com.example.assignement03;

import android.graphics.PointF;

class PlayerMovementComponent implements MovementComponent {

    @Override
    public boolean move(long fps, Transform t,
                        Transform playerTransform){
        // How high is the screen?
        float screenHeight = t.getmScreenSize().y;
        // How wide is the screen?
        float screenWidth = t.getmScreenSize().x;
        // Where is the player?
        PointF location = t.getLocation();
        // How fast is it going
        float speed = t.getSpeed();
        // How tall is the player
        float height = t.getObjectHeight();
        // How wide is the player
        float width = t.getObjectWidth();

        // Move the player up or down if needed
        if(t.headingDown()){
            location.y += speed / fps;
        }
        else if(t.headingUp()){
            location.y -= speed / fps;
        }

        // Move the player right or left
        if (t.headingRight()){
            location.x += speed / fps*2;
        }
        else if (t.headingLeft()){
            location.x -= speed / fps*2;
        }

        // Make sure the player can't go off the screen
        if(location.y > screenHeight - height){
            location.y = screenHeight - height;
        }
        else if(location.y < 0){
            location.y = 0;
        }
        else if (location.x > screenWidth - width){
            location.x = screenWidth - width;
        }
        else if (location.x < 0) {
            location.x = 0;
        }

        // Update the collider
        t.updateCollider();


        return true;
    }
}
