package warcraftTD;

import java.awt.Color;
import java.awt.Font;
import java.util.ArrayDeque;

public class Alert {
    private Position pos;
    private int duration;
    private Color color;
    private double size;
    public ArrayDeque<String> queue;
    private Font font;

    private int tickCounter;

    public Alert(Position pos, int duration, Color color, Font font, double size) {
        this.pos = pos;
        this.duration = duration;
        this.color = color;
        this.size = size;
        this.font = font;
        this.queue = new ArrayDeque<String>();

        tickCounter = 0;
    }

    public void add(String str) {
        queue.add(str);
    }

    public void draw() {
        if (!queue.isEmpty()) {
            System.out.println("drawing");
            StdDraw.setPenColor(color);
            StdDraw.setFont(font);
            if (tickCounter <= 0) {
                StdDraw.text(pos.x, pos.y, queue.remove());
                tickCounter = duration;
            } else
                StdDraw.text(pos.x, pos.y, queue.getFirst());
            tickCounter -= 1;
        }
    }
}
