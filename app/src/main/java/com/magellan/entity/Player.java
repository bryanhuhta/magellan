package com.magellan.entity;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import com.magellan.asset.Sprite;
import com.magellan.core.KeyCode;
import com.magellan.core.KeyHandler;
import com.magellan.core.Vec2;

public class Player implements Entity {
    private Vec2 position;
    private Vec2 direction;

    private Sprite[] sprites;
    private int spriteIndex;
    private int spriteCounter;

    private static final int SPEED = 4;

    public Player(Vec2 position, Sprite[] sprites) {
        this.position = position;
        this.direction = Vec2.down();

        this.sprites = sprites;
        this.spriteIndex = 0;
        this.spriteCounter = 0;
    }

    @Override
    public void update(KeyHandler keyHandler) {
        Vec2 dir = Vec2.origin();

        if (keyHandler.isKeyPressed(KeyCode.UP)) {
            spriteIndex = direction.isUp() ? spriteIndex+1 : 0;
            dir = dir.add(Vec2.up());
            direction = Vec2.up();
        } else if (keyHandler.isKeyPressed(KeyCode.DOWN)) {
            spriteIndex = direction.isDown() ? spriteIndex+1 : 0;
            dir = dir.add(Vec2.down());
            direction = Vec2.down();
        } else if (keyHandler.isKeyPressed(KeyCode.LEFT)) {
            spriteIndex = direction.isLeft() ? spriteIndex+1 : 0;
            dir = dir.add(Vec2.left());
            direction = Vec2.left();
        } else if (keyHandler.isKeyPressed(KeyCode.RIGHT)) {
            spriteIndex = direction.isRight() ? spriteIndex+1 : 0;
            dir = dir.add(Vec2.right());
            direction = Vec2.right();
        }

        this.position = position.add(dir.scale(SPEED));
    }

    @Override
    public void render(Graphics2D graphics, int tileSizeX, int tileSizeY) {
        BufferedImage sprite = sprites[(spriteIndex % 3)].getImage();

        if (spriteCounter > 10) {
            spriteCounter = 0;

            if (direction.isDown()) {
                spriteIndex = spriteIndex >= 0 && spriteIndex < 3
                ? (spriteIndex+1) % 3
                : 0;
            } else if (direction.isUp()) {
                spriteIndex = spriteIndex >= 3 && spriteIndex < 6
                    ? (spriteIndex+1) % 3 + 3
                    : 3;
            } else if (direction.isLeft()) {
                spriteIndex = spriteIndex >= 6 && spriteIndex < 9
                    ? (spriteIndex+1) % 3 + 6
                    : 6;
            } else {
                spriteIndex = spriteIndex >= 9 && spriteIndex < 12
                    ? (spriteIndex+1) % 3 + 9
                    : 9;
            }
        } else {
            spriteCounter++;
        }

        graphics.drawImage(sprite, position.getX(), position.getY(), tileSizeX, tileSizeY, null);
    }
}
