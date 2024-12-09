package com.magellan;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;

import com.magellan.core.KeyHandler;
import com.magellan.entity.Entity;

public class GameUi extends JPanel {
    private JFrame window;

    private List<Entity> components;

    private static final int TILE_SIZE_X = 16;
    private static final int TILE_SIZE_Y = 24;
    private static final int SCALE = 3;
    private static final int SCALED_TILE_SIZE_X = TILE_SIZE_X * SCALE;
    private static final int SCALED_TILE_SIZE_Y = TILE_SIZE_Y * SCALE;

    private static final Dimension DIMENSION = new Dimension(
        SCALED_TILE_SIZE_X * 25, // 1200px
        SCALED_TILE_SIZE_Y * 10  //  720px, ideally we want 768px
    );

    public GameUi() {
        window = new JFrame("Magellan");
    }

    public void init(KeyHandler keyHandler) {
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setSize(DIMENSION);
        window.setLocationRelativeTo(null);
        window.setResizable(false);
        window.setVisible(true);

        this.setDoubleBuffered(true);
        this.setPreferredSize(DIMENSION);

        this.addKeyListener(keyHandler);
        this.setFocusable(true);

        window.add(this);
        window.pack();
    }

    public void draw(List<Entity> components) {
        this.components = components;
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D graphics = (Graphics2D) g;

        if (components != null) {
            for (Entity component : components) {
                component.render(graphics, SCALED_TILE_SIZE_X, SCALED_TILE_SIZE_Y);
            }
        }

        graphics.dispose();
    }
}
