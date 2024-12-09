package com.magellan.entity;

import java.awt.Graphics2D;

import com.magellan.core.KeyHandler;

public interface Entity {
    void update(KeyHandler keyHandler);
    void render(Graphics2D graphics, int tileSizeX, int tileSizeY);
}
