package warcraftTD;

import java.awt.Color;
import java.awt.Font;
import java.util.ArrayDeque;

public class Alert {
    private Position pos;
    private Position startPos;
    private Color color;
    private ArrayDeque<String> queue;
    private Font font;
    private int duration;
    private int fadingDuration;
    private double offsetDistance;

    private int tickCounter;

    public Alert(Position pos, int duration, Color color, Font font, int fadingDuration, double offsetDistance,
            float size) {
        this.startPos = new Position(pos);
        this.pos = new Position(pos);
        this.duration = duration;
        this.color = color;
        this.fadingDuration = fadingDuration;
        this.offsetDistance = offsetDistance;
        this.queue = new ArrayDeque<String>();
        this.font = font.deriveFont(size);
        tickCounter = duration;
    }

    public void add(String str) {
        if (!queue.contains(str))
            queue.add(str);
    }

    public boolean isEmpty() {
        return queue.isEmpty();
    }

    private void reset() {
        color = new Color(color.getRed(), color.getGreen(), color.getBlue(), (255));
        tickCounter = duration;
        this.pos = new Position(startPos);
    }

    public void tick() {
        if (!queue.isEmpty()) {
            tickCounter -= 1;

            // easing animation for the color alpha when the text will diseppear
            if (tickCounter < fadingDuration && tickCounter >= 0) {
                double x = (double) tickCounter / (double) fadingDuration;
                double alphaEaseFunction = 1 - Math.pow(1 - x, 3); // ease Out Cubic
                color = new Color(color.getRed(), color.getGreen(), color.getBlue(), (int) (alphaEaseFunction * 255));
            }

            // easing animation for the text position
            double x = (double) (duration - tickCounter) / (double) duration;
            double offsetEaseFunction = 1 - Math.pow(1 - x, 3); // ease Out Cubic
            pos.y = startPos.y + offsetEaseFunction * offsetDistance;
        }
    }

    public void draw() {
        if (!queue.isEmpty()) {
            StdDraw.setPenColor(color);
            StdDraw.setFont(font);
            if (tickCounter <= 0) {
                StdDraw.text(pos.x, pos.y, queue.remove());
                reset();
            } else
                StdDraw.text(pos.x, pos.y, queue.getFirst());
        }
    }
}
