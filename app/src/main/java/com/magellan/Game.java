package com.magellan;

import java.util.ArrayList;
import java.util.logging.Logger;

import com.magellan.core.KeyHandler;
import com.magellan.entity.Entity;
import com.magellan.entity.Player;
import com.magellan.physics.Vec2;

public class Game {
    private GameUi ui;
    private KeyHandler keyHandler;
    private ArrayList<Entity> entities = new ArrayList<>();

    private Logger logger;

    private final static int TARGET_FPS = 60;
    private final static long NS_PER_FRAME = 1_000_000_000 / TARGET_FPS;

    public Game(Logger logger, KeyHandler keyHandler) {
        this.logger = logger;
        this.keyHandler = keyHandler;
        ui = new GameUi();
    }

    public void run() {
        long lastTs = System.nanoTime();
        long currentTs;
        long delta;

        long fpsTimer = 0;
        int renderedFrames = 0;

        ui.init(keyHandler);
        entities.add(new Player(new Vec2(100, 300)));

        while (true) {
            currentTs = System.nanoTime();
            delta = currentTs - lastTs;

            if (delta >= NS_PER_FRAME) {
                lastTs = currentTs;
                fpsTimer += delta;
                renderedFrames++;

                update();
                render();

            }

            if (fpsTimer >= 1_000_000_000) {
                logger.fine("FPS: " + renderedFrames);
                renderedFrames = 0;
                fpsTimer = 0;
            }
        }
    }

    private void update() {
        for (Entity entity : entities) {
            entity.update(keyHandler);
        }
    }

    private void render() {
        ui.draw(entities);
    }
}
