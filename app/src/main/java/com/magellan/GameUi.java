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

    public GameUi() {
        window = new JFrame("Magellan");
    }

    public void init(KeyHandler keyHandler) {
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setSize(new Dimension(1200, 800));
        window.setLocationRelativeTo(null);
        window.setResizable(false);
        window.setVisible(true);

        this.setDoubleBuffered(true);
        this.setPreferredSize(new Dimension(1200, 800));

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
                component.render(graphics);
            }
        }

        graphics.dispose();
    }
}
