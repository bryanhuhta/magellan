package com.magellan.core;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.HashMap;

public class KeyHandler implements KeyListener {
    private HashMap<KeyCode, Boolean> keys;

    public KeyHandler() {
        keys = new HashMap<>();
    }

    public boolean isKeyPressed(KeyCode ...codes) {
        boolean pressed = true;
        for (KeyCode code : codes) {
            if (!keys.getOrDefault(code, false)) {
                pressed = false;
                break;
            }
        }

        return pressed;
    }

    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();
        switch (code) {
            case KeyEvent.VK_UP, KeyEvent.VK_DOWN, KeyEvent.VK_LEFT, KeyEvent.VK_RIGHT:
                keys.put(KeyCode.fromInt(code), true);
                break;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int code = e.getKeyCode();
        switch (code) {
            case KeyEvent.VK_UP, KeyEvent.VK_DOWN, KeyEvent.VK_LEFT, KeyEvent.VK_RIGHT:
                keys.put(KeyCode.fromInt(code), false);
                break;
        }
    }
}
