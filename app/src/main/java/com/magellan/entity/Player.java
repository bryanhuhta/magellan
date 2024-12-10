package com.magellan.entity;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import com.magellan.asset.Sprite;
import com.magellan.core.KeyCode;
import com.magellan.core.KeyHandler;
import com.magellan.core.Vec2;

public class Player implements Entity {
    private Vec2 position;
    private Sprite[] sprites;

    private static final int SPEED = 4;

    public Player(Vec2 position, Sprite[] sprites) {
        this.position = position;
        this.sprites = sprites;
    }

    @Override
    public void update(KeyHandler keyHandler) {
        Vec2 dir = Vec2.origin();

        if (keyHandler.isKeyPressed(KeyCode.UP)) {
            dir = dir.add(Vec2.up());
        }

        if (keyHandler.isKeyPressed(KeyCode.DOWN)) {
            dir = dir.add(Vec2.down());
        }

        if (keyHandler.isKeyPressed(KeyCode.LEFT)) {
            dir = dir.add(Vec2.left());
        }

        if (keyHandler.isKeyPressed(KeyCode.RIGHT)) {
            dir = dir.add(Vec2.right());
        }

        this.position = position.add(dir.scale(SPEED));
    }

    @Override
    public void render(Graphics2D graphics, int tileSizeX, int tileSizeY) {
        BufferedImage sprite;
        if (position.isDown()) {
            sprite = sprites[1].getImage();
        } else if (position.isUp()) {
            sprite = sprites[4].getImage();
        } else if (position.isLeft()) {
            sprite = sprites[7].getImage();
        } else {
            sprite = sprites[10].getImage();
        }

        graphics.drawImage(sprite, position.getX(), position.getY(), tileSizeX, tileSizeY, null);
    }
}
