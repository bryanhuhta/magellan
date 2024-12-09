package com.magellan;

import java.util.ArrayList;
import java.util.logging.Logger;

import com.magellan.asset.AssetManager;
import com.magellan.asset.Sprite;
import com.magellan.core.KeyHandler;
import com.magellan.core.Vec2;
import com.magellan.entity.Entity;
import com.magellan.entity.Player;

public class Game {
    private GameUi ui;
    private KeyHandler keyHandler;
    private AssetManager assetManager;
    private Logger logger;

    private ArrayList<Entity> entities = new ArrayList<>();

    private final static int TARGET_FPS = 60;
    private final static long NS_PER_FRAME = 1_000_000_000 / TARGET_FPS;

    public Game(Logger logger, KeyHandler keyHandler, AssetManager assetManager) {
        this.logger = logger;
        this.keyHandler = keyHandler;
        this.assetManager = assetManager;
        ui = new GameUi();
    }

    public void run() {
        long lastTs = System.nanoTime();
        long currentTs;
        long delta;

        long fpsTimer = 0;
        int renderedFrames = 0;

        ui.init(keyHandler);

        // Add player.
        Sprite[] playerSprites = new Sprite[] {
            // Down animations.
            assetManager.getSprite("characters", 0),
            assetManager.getSprite("characters", 1),
            assetManager.getSprite("characters", 2),

            // Up animations.
            assetManager.getSprite("characters", 3),
            assetManager.getSprite("characters", 4),
            assetManager.getSprite("characters", 5),

            // Left animations.
            assetManager.getSprite("characters", 6),
            assetManager.getSprite("characters", 7),
            assetManager.getSprite("characters", 8),

            // Right animations.
            assetManager.getSprite("characters", 9),
            assetManager.getSprite("characters", 10),
            assetManager.getSprite("characters", 11)
        };
        Player player = new Player(new Vec2(100, 300), playerSprites);
        entities.add(player);

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
