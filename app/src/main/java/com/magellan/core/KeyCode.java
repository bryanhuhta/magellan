package com.magellan.core;

import java.awt.event.KeyEvent;

public enum KeyCode {
    UNKNOWN,
    UP,
    DOWN,
    LEFT,
    RIGHT;

    public static KeyCode fromInt(int code) {
        switch (code) {
            case KeyEvent.VK_UP:
                return UP;
            case KeyEvent.VK_DOWN:
                return DOWN;
            case KeyEvent.VK_LEFT:
                return LEFT;
            case KeyEvent.VK_RIGHT:
                return RIGHT;
            default:
                return UNKNOWN;
        }
    }
}
