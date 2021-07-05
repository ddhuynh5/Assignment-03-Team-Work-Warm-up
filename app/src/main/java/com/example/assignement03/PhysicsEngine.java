package com.example.assignement03;

import java.util.ArrayList;
import android.graphics.PointF;
import android.graphics.RectF;

class PhysicsEngine {

    // This signature and much more will
    // change later in the project
    boolean update(long fps, ArrayList<GameObject> objects, GameState gs, ParticleSystem ps){

        // Update all the GameObjects
        for (GameObject object : objects) {
            if (object.checkActive()) {
                object.update(fps, objects.get(Level.PLAYER_INDEX).getTransform());
            }
        }

        if(ps.mIsRunning){
            ps.update(fps);
        }

        return detectCollisions(gs, objects, ps);
    }
    // Collision detection method will go here
    private boolean detectCollisions(
            GameState mGameState,
            ArrayList<GameObject> objects,
            ParticleSystem ps ){

        boolean playerHit = false;
        for(GameObject go1 : objects) {

            if(go1.checkActive()){
                // The ist object is active
                // so worth checking

                for(GameObject go2 : objects) {

                    if(go2.checkActive()){

                        // The 2nd object is active
                        // so worth checking
                        if(RectF.intersects(
                                go1.getTransform().getCollider(),
                                go2.getTransform().getCollider())){

                            // switch goes here
                            // There has been a collision
                            // - but does it matter
                            switch (go1.getTag() + " with " + go2.getTag()){
                                case "Player with Enemy":
                                    playerHit = true;
                                    mGameState.loseLife();

                                    break;

                                case "Player Laser with Enemy":
                                    mGameState.increaseScore();
                                    // Enemy explodes into particles
                                    ps.emitParticles(
                                            new PointF(
                                                    go2.getTransform().getLocation().x,
                                                    go2.getTransform().getLocation().y
                                            )
                                    );
                                    // allow enemy to drop gold on death
                                    /*go2.setInactive();;
                                    go2.spawn(objects.get(Level
                                    .GOLD_INDEX).getTransform());*/

                                    // Respawn the enemy
                                    go2.setInactive();
                                    go2.spawn(objects.get(Level
                                            .PLAYER_INDEX).getTransform());

                                    go1.setInactive();

                                    break;

                                /*case "Player with Gold Coin":
                                    break;*/

                                default:
                                    break;
                            }
                        }
                    }
                }
            }
        }
        return playerHit;
    }
}