package com.example.assignement03;

public class GoldSpawnComponent implements SpawnComponent{
    @Override
    public void spawn(Transform playerTransform, Transform t) {
        // spawn gold at last location of enemy when it dies
        t.setLocation(t.getLocation().x, t.getLocation().y);
    }
}
