package main.sprites;

import java.awt.image.BufferedImage;
import java.awt.Graphics2D;
import java.awt.Rectangle;

import main.GamePanel;
import main.settings.Settings;
import main.sprites.entity.Sprite;
import main.utils.Utils;
import main.settings.TileSettings;

public class Tile extends Sprite {
    
    public int x;
    public int y;
    public String id;
    public boolean solid;
    public boolean dangerous;
    public BufferedImage image;
    public Rectangle rect;

    public boolean destroyed;

    public Tile(int x, int y, String id, boolean solid, boolean dangerous, BufferedImage image, GamePanel gp) {

        super(gp);

        this.x = x;
        this.y = y;
        this.drawX = this.x;
        this.drawY = this.y + this.scrollDiff;
        this.width = Settings.BASE_TILE_SIZE;
        this.height = Settings.BASE_TILE_SIZE;
        this.lastUpdate = 0f;
        this.scrollDiff = 0;
        this.id = id;
        this.solid = solid;
        this.dangerous = dangerous;
        this.image = image;

        this.rect = new Rectangle();
        this.rect.x = this.x;
        this.rect.y = this.y;
        this.rect.width = Settings.BASE_TILE_SIZE;
        this.rect.height = Settings.BASE_TILE_SIZE;

        // If the tile is a platform
        if (this.id.equals(TileSettings.PLATFORM_ID)) {

            this.rect.width = Settings.BASE_TILE_SIZE;
            this.rect.height = 2;

        }

        // If the tile is a rope
        if (this.id.equals(TileSettings.ROPE_ID)) {

            this.rect.x = this.x + 3;
            this.rect.y = this.y;
            this.rect.width = 2;
            this.rect.height = Settings.BASE_TILE_SIZE;

        }
        
        this.destroyed = false;

    }


    
    public void kill() {

        this.destroyed = true;
        this.x = -Settings.BASE_TILE_SIZE * 2;
        this.y = -Settings.BASE_TILE_SIZE * 2;
        this.rect.x = this.x;
        this.rect.y = this.y;
        this.rect.width = this.image.getWidth();
        this.rect.height = this.image.getHeight();

    }



    public void limitScrolling() {

        if (gp.spriteManager.lockedLeft) {

            this.drawX = this.x;

        } else if (gp.spriteManager.lockedRight) {

            this.drawX = this.x - gp.spriteManager.totalMapWidth / 2;

        } else {

            this.drawX = this.x - gp.controllerManager.getPlayer(1).x + gp.controllerManager.getPlayer(1).drawX;

        }

        if (gp.spriteManager.lockedTop) {

            this.drawY = this.y;

        } else if (gp.spriteManager.lockedBottom) {

            this.drawY = this.y - gp.spriteManager.totalMapHeight / 2;

        } else {

            this.drawY = this.y - gp.controllerManager.getPlayer(1).y + gp.controllerManager.getPlayer(1).drawY;

        }

    }



    public void update() {

        this.limitScrolling();

        // If the tile is a platform
        if (this.id.equals(TileSettings.PLATFORM_ID)) {

            this.rect.x = this.x;
            this.rect.y = this.y;
            this.rect.width = Settings.BASE_TILE_SIZE;
            this.rect.height = 2;

        }

        // If the tile is a rope
        if (this.id.equals(TileSettings.ROPE_ID)) {

            this.rect.x = this.x + 3;
            this.rect.y = this.y;
            this.rect.width = 2;
            this.rect.height = Settings.BASE_TILE_SIZE;

        }

        if (!this.id.equals(TileSettings.PLATFORM_ID) && !this.id.equals(TileSettings.ROPE_ID)) {

            this.rect.x = this.x;
            this.rect.y = this.y;
            this.rect.width = Settings.BASE_TILE_SIZE;
            this.rect.height = Settings.BASE_TILE_SIZE;

        }

    }
    


    public void draw(Graphics2D g2) {
        
        if (Utils.inRange(this.drawX, -Settings.BASE_TILE_SIZE, Settings.BASE_SCREEN_WIDTH) && Utils.inRange(this.drawY, -Settings.BASE_TILE_SIZE, Settings.BASE_SCREEN_HEIGHT)) {

            g2.drawImage(this.image, this.drawX, this.drawY, Settings.BASE_TILE_SIZE, Settings.BASE_TILE_SIZE, null);

        }

    }

}
