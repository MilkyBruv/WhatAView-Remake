package main.settings;

public class Settings {

    // File settings
    public static final String baseDir = System.getProperty("user.dir");

    // Screen settings
    public static final int FPS = 60;

    public static final int BASE_TILE_SIZE = 8;
    public static final int TILE_SCALE = 4;
    public static final int TILE_SIZE = BASE_TILE_SIZE * TILE_SCALE;

    public static final int TILED_WIDTH = 32;
    public static final int TILED_HEIGHT = 20;
    public static final int BASE_SCREEN_WIDTH = TILED_WIDTH * BASE_TILE_SIZE;
    public static final int BASE_SCREEN_HEIGHT = TILED_HEIGHT * BASE_TILE_SIZE;
    public static final int SCREEN_WIDTH = TILED_WIDTH * TILE_SIZE;
    public static final int SCREEN_HEIGHT = TILED_HEIGHT * TILE_SIZE;

    public static final int PARTICLE_WAIT = 10000000;

    public static final int SHAKE_WAIT = 20000000;
    public static final int SHAKE_AMT = 3;

    public static float PLAYER_SPEED = 1f;
    public static int PLAYER_JUMP = 14 / TILE_SCALE;
    public static int PLAYER_DOUBLE_JUMP = 12 / TILE_SCALE;
    public static float PLAYER_GRAVITY = 0.500f;
    public static float PLAYER_MAX_FALL_SPEED = 14;
    public static float PLAYER_ACCELERATION = 0.005f / TILE_SCALE;
    public static float PLAYER_FRICTION = -0.2f;

}
