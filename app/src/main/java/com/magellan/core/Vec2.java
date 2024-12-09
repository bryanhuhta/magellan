package com.magellan.core;

public class Vec2 {
    private int x;
    private int y;

    public Vec2(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Vec2(Vec2 other) {
        this.x = other.x;
        this.y = other.y;
    }

    public static Vec2 origin() {
        return new Vec2(0, 0);
    }

    public static Vec2 up() {
        return new Vec2(0, -1);
    }

    public static Vec2 down() {
        return new Vec2(0, 1);
    }

    public static Vec2 left() {
        return new Vec2(-1, 0);
    }

    public static Vec2 right() {
        return new Vec2(1, 0);
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Vec2 add(Vec2 other) {
        return new Vec2(x + other.x, y + other.y);
    }

    public Vec2 scale(int scalar) {
        return new Vec2(x * scalar, y * scalar);
    }

    public boolean isUp() {
        return y < 0;
    }

    public boolean isDown() {
        return y > 0;
    }

    public boolean isLeft() {
        return x < 0;
    }

    public boolean isRight() {
        return x > 0;
    }
}
