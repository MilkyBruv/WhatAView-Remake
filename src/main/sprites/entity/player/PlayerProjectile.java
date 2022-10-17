package main.sprites.entity.player;

import java.awt.image.BufferedImage;

import main.GamePanel;
import main.settings.Settings;
import main.sprites.Tile;
import main.sprites.entity.Sprite;

import java.awt.Rectangle;

public class PlayerProjectile extends Sprite {
    
    private GamePanel gp;

    public BufferedImage image;
    public BufferedImage[] images;

    public Rectangle rect;
    public int width;
    public int height;

    private int speed;
    
    public boolean collided = false;

    private boolean[] dir;

    public PlayerProjectile(boolean[] dir, int playerNum, int x, int y, BufferedImage[] images, GamePanel gp) {
        
        super(gp);

        this.gp = gp;


        this.x = x;
        this.y = y;
        this.drawX = this.x;
        this.drawY = this.y;
        this.width = 4 * Settings.TILE_SCALE;
        this.height = 4 * Settings.TILE_SCALE;
        this.scrollDiff = 0;
        this.lastUpdate = 0f;

        this.collided = false;

        this.speed = 10;

        this.images = images;

        this.dir = dir;

        if (this.dir[0]) {

            this.image = this.images[0];
            this.y -= Settings.TILE_SIZE / 2;
            this.x += Settings.TILE_SIZE / 4;

        } else if (this.dir[1]) {

            this.image = this.images[1];
            this.y += Settings.TILE_SIZE;
            this.x += Settings.TILE_SIZE / 4;

        } else if (this.dir[2]) {

            this.image = this.images[2];
            this.y += Settings.TILE_SIZE / 4;

        } else if (this.dir[3]) {

            this.image = this.images[3];
            this.x += Settings.TILE_SIZE;
            this.y += Settings.TILE_SIZE / 4;

        }

        this.width = this.image.getWidth() * Settings.TILE_SCALE;
        this.height = this.image.getHeight() * Settings.TILE_SCALE;

        this.rect = new Rectangle();
        this.rect.x = this.x;
        this.rect.y = this.y;
        this.rect.width = this.width;
        this.rect.height = this.height;

    }


    
    public void kill() {

        this.collided = true;

        this.x = -Settings.TILE_SIZE;
        this.y = -Settings.TILE_SIZE;
        this.speed = 0;

        this.rect.x = this.x;
        this.rect.y = this.y;
        this.rect.width = 1;
        this.rect.height = 1;

    }



    public void update() {

        // Round pos to nearest scaled pixel
        this.drawX = Settings.TILE_SCALE * (Math.round(this.drawX / Settings.TILE_SCALE));
        this.y = Settings.TILE_SCALE * (Math.round(this.y / Settings.TILE_SCALE));

        this.drawX = this.x - gp.controllerManager.getPlayer(1).x + gp.controllerManager.getPlayer(1).drawX;
        this.drawY = this.y - gp.controllerManager.getPlayer(1).y + gp.controllerManager.getPlayer(1).drawY;

        if (!this.collided) {

            this.width = this.image.getWidth() * Settings.TILE_SCALE;
            this.height = this.image.getHeight() * Settings.TILE_SCALE;

            this.rect = new Rectangle();
            this.rect.x = this.x;
            this.rect.y = this.y;
            this.rect.width = this.width;
            this.rect.height = this.height;

            if (this.dir[0]) {

                this.y -= this.speed;

            } if (this.dir[1]) {

                this.y += this.speed;

            } if (this.dir[2]) {

                this.x -= this.speed;

            } if (this.dir[3]) {

                this.x += this.speed;

            }

            for (Tile tile : gp.spriteManager.allTiles) {
                if (tile != null) {
    
                    if (!tile.id.equals("65") && tile.solid && this.rect.intersects(tile.rect)) {
                        
                        if (tile.id.equals("66")) {

                            tile.kill();

                        }

                        this.kill();
    
                    }
    
                }
    
            }

        }

    }

}
