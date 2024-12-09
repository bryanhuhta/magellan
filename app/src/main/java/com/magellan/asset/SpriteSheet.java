package com.magellan.asset;

import java.awt.image.BufferedImage;

public class SpriteSheet {
    private BufferedImage sheet;

    private int offsetX;
    private int offsetY;

    private int paddingX;
    private int paddingY;

    private int spriteWidth;
    private int spriteHeight;
    private int spriteRows;
    private int spriteColumns;

    public SpriteSheet(BufferedImage sheet, int spriteWidth, int spriteHeight) {
        this.sheet = sheet;

        offsetX = 0;
        offsetY = 0;

        paddingX = 0;
        paddingY = 0;

        this.spriteWidth = spriteWidth;
        this.spriteHeight = spriteHeight;

        spriteRows = (sheet.getHeight() - offsetY) / (spriteHeight + paddingY);
        spriteColumns = (sheet.getWidth() - offsetX) / (spriteWidth + paddingX);
    }

    public SpriteSheet(BufferedImage sheet, int spriteWidth, int spriteHeight, int offsetX, int offsetY, int paddingX, int paddingY) {
        this.sheet = sheet;

        this.offsetX = offsetX;
        this.offsetY = offsetY;

        this.paddingX = paddingX;
        this.paddingY = paddingY;

        this.spriteWidth = spriteWidth;
        this.spriteHeight = spriteHeight;

        spriteRows = (sheet.getHeight() - offsetY) / (spriteHeight + paddingY);
        spriteColumns = (sheet.getWidth() - offsetX) / (spriteWidth + paddingX);
    }

    public Sprite get(int index) {
        int row = index / spriteColumns;
        int column = index % spriteColumns;

        int x = offsetX + column * (spriteWidth + paddingX);
        int y = offsetY + row * (spriteHeight + paddingY);

        Sprite sprite = new Sprite(sheet.getSubimage(x, y, spriteWidth, spriteHeight));
        return sprite;
    }

    public Sprite[] getRange(int start, int end) {
        Sprite[] sprites = new Sprite[end - start];

        for (int i = start; i < end; i++) {
            sprites[i - start] = get(i);
        }

        return sprites;
    }
}
