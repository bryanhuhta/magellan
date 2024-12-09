package com.magellan.asset;

import java.awt.image.BufferedImage;

public class Sprite {
    private BufferedImage image;

    public Sprite(BufferedImage image) {
        this.image = image;
    }

    public BufferedImage getImage() {
        return image;
    }
}
