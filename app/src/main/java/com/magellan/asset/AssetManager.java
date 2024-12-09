package com.magellan.asset;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.HashMap;

import javax.imageio.ImageIO;

public class AssetManager {
    private Path root;
    private HashMap<String, SpriteSheet> spriteSheets;

    public AssetManager(String basePath) throws URISyntaxException, IOException {
        root = getFolderPath(basePath);

        spriteSheets = loadSpriteSheets(root);
    }

    public String basePath() {
        return root.toString();
    }

    public Sprite getSprite(String name, int index) {
        return spriteSheets.get(name).get(index);
    }

    private Path getFolderPath(String dir) throws URISyntaxException, IOException {
        URI uri = getClass().getClassLoader().getResource(dir).toURI();

        if ("jar".equals(uri.getScheme())) {
            FileSystem fileSystem = FileSystems.newFileSystem(uri, Collections.emptyMap(), null);
            return fileSystem.getPath("path/to/folder/inside/jar");
        } else {
            return Paths.get(uri);
        }
    }

    private HashMap<String, SpriteSheet> loadSpriteSheets(Path root) throws IOException {
        HashMap<String, SpriteSheet> spriteSheets = new HashMap<>();
        Path spritesDir = root.resolve("sprites");

        for (Path path : Files.newDirectoryStream(spritesDir)) {
            if (!Files.isRegularFile(path)) {
                continue;
            }

            String name = path.getFileName().toString();
            BufferedImage sheet = ImageIO.read(path.toFile());

            switch (name) {
                case "characters.png":
                    spriteSheets.put("characters", new SpriteSheet(sheet, 16, 24, 9, 42, 1, 1));
                    break;
                default:
                    spriteSheets.put(name, new SpriteSheet(sheet, 16, 16));
                    break;
            }

            SpriteSheet spriteSheet = new SpriteSheet(sheet, 16, 16);
            spriteSheets.put(name, spriteSheet);
        }

        return spriteSheets;
    }
}
