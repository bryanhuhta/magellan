package com.magellan.entity;

import java.awt.Color;
import java.awt.Graphics2D;
import com.magellan.core.KeyCode;
import com.magellan.core.KeyHandler;
import com.magellan.physics.Vec2;

public class Player implements Entity {
    private Vec2 position;
    private static final int SPEED = 4;

    public Player(Vec2 position) {
        this.position = position;
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
    public void render(Graphics2D graphics) {
        graphics.setColor(Color.BLACK);
        graphics.fillRect(position.getX(), position.getY(), 50, 50);
    }
}
