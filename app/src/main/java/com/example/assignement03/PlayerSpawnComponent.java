package com.example.assignement03;

class PlayerSpawnComponent implements SpawnComponent {

    @Override
    public void spawn(Transform playerTransform, Transform t) {

        // Spawn in the centre of the screen
        t.setLocation(
                t.getmScreenSize().x/2,
                t.getmScreenSize().y/2);
    }
}
