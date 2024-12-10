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
        Vec2 oldDir = new Vec2(direction);

        if (keyHandler.isKeyPressed(KeyCode.UP)) {
            dir = dir.add(Vec2.up());
            direction = Vec2.up();
        } else if (keyHandler.isKeyPressed(KeyCode.DOWN)) {
            dir = dir.add(Vec2.down());
            direction = Vec2.down();
        } else if (keyHandler.isKeyPressed(KeyCode.LEFT)) {
            dir = dir.add(Vec2.left());
            direction = Vec2.left();
        } else if (keyHandler.isKeyPressed(KeyCode.RIGHT)) {
            dir = dir.add(Vec2.right());
            direction = Vec2.right();
        }

        if (keyHandler.isAnyKeyPressed(KeyCode.UP, KeyCode.DOWN, KeyCode.LEFT, KeyCode.RIGHT)) {
            // If we changed directions, force an update. Otherwise, continue
            // with the animation loop.
            boolean force = !direction.equals(oldDir);
            updateAnimatedSpriteIndex(force);
        } else {
            updateStillSpriteIndex();
        }

        this.position = position.add(dir.scale(SPEED));
    }

    @Override
    public void render(Graphics2D graphics, int tileSizeX, int tileSizeY) {
        BufferedImage sprite = sprites[spriteIndex].getImage();
        graphics.drawImage(sprite, position.getX(), position.getY(), tileSizeX, tileSizeY, null);
    }

    private void updateAnimatedSpriteIndex(boolean force) {
        if (spriteCounter < (SPEED * 2) && !force) {
            spriteCounter++;
            return;
        }

        int offset = 0;
        if (direction.isDown()) {
            offset = 0;
        } else if (direction.isUp()) {
            offset = 3;
        } else if (direction.isLeft()) {
            offset = 6;
        } else if (direction.isRight()) {
            offset = 9;
        }

        final int spriteCount = 3;
        spriteIndex = spriteIndex >= offset && spriteIndex < offset + spriteCount
            ? ((spriteIndex+1) % spriteCount) + offset
            : offset;
        spriteCounter = 0;
    }

    private void updateStillSpriteIndex() {
        if (direction.isDown()) {
            spriteIndex = 1;
        } else if (direction.isUp()) {
            spriteIndex = 4;
        } else if (direction.isLeft()) {
            spriteIndex = 7;
        } else if (direction.isRight()) {
            spriteIndex = 10;
        }
    }
}
