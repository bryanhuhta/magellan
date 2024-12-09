package com.magellan;

import java.util.logging.ConsoleHandler;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.LogRecord;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

import com.magellan.asset.AssetManager;
import com.magellan.core.KeyHandler;

public class App {
    public static void main(String[] args) throws Exception {
        Logger logger = configureLogger();
        KeyHandler keyHandler = new KeyHandler();

        AssetManager assetManager;
        try {
            assetManager = new AssetManager("assets");
        } catch (Exception e) {
            logger.severe("Failed to load assets: " + e.getMessage());
            throw e;
        }
        logger.info(assetManager.basePath());

        Game game = new Game(logger, keyHandler, assetManager);
        game.run();
    }

    private static Logger configureLogger() {
        Handler handler = new ConsoleHandler();
        handler.setFormatter(new SimpleFormatter() {
            @Override
            public String format(LogRecord record) {
                // Custom format string without line breaks
                return String.format("%1$tF %1$tT [%4$s] %2$s: %5$s%n",
                    record.getMillis(),
                    record.getSourceClassName(),
                    record.getSourceMethodName(),
                    record.getLevel().getLocalizedName(),
                    record.getMessage()
                );
            }
        });
        handler.setLevel(Level.ALL);

        Logger logger = Logger.getLogger(App.class.getName());
        logger.addHandler(handler);
        logger.setUseParentHandlers(false);
        logger.setLevel(Level.ALL);

        return logger;
    }
}
