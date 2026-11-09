import edu.cymbal.graphics.CanvasAPI;
import edu.cymbal.graphics.Graphics;
import edu.cymbal.graphics.PainterAPI;
import java.awt.Color;

public class AlienScene {

    private CanvasAPI canvas;
    private PainterAPI painter;
    private Color skyColor = new Color(20, 15, 50);

    public AlienScene() {
        canvas = Graphics.createCanvas(800, 600, "Alien Scene");
        painter = canvas.getPainter();
    }

    /**
     * @param color     the color of the circle
     * @param xPosition the x coordinate of the middle of the circle
     * @param yPosition the y coordinate of the middle of the circle
     * @param radius    how big the circle is
     */
    public void drawCircle(Color color, int xPosition, int yPosition, int radius) {
        painter.liftPen();
        painter.moveTo(xPosition, yPosition - radius);
        painter.lowerPen();
        painter.setPenColor(color);
        painter.startFill(color);
        painter.circle(radius);
        painter.finishFill();
    }

    /**
     * @param color     the color of the rectangle
     * @param xPosition the x coordinate of the top left corner
     * @param yPosition the y coordinate of the top left corner
     * @param width     how wide it is
     * @param height    how tall it is
     */
    public void drawRectangle(Color color, int xPosition, int yPosition, int width, int height) {
        painter.liftPen();
        painter.moveTo(xPosition, yPosition);
        painter.lowerPen();
        painter.setPenColor(color);
        painter.startFill(color);
        for (int i = 0; i < 2; i++) {
            painter.forward(width);
            painter.turnRight(90);
            painter.forward(height);
            painter.turnRight(90);
        }
        painter.finishFill();
    }

    /**
     * @param color     the color of the triangle
     * @param xPosition the x coordinate of the bottom left corner
     * @param yPosition the y coordinate of the bottom left corner
     * @param size      the length of each side
     */
    public void drawTriangle(Color color, int xPosition, int yPosition, int size) {
        painter.liftPen();
        painter.moveTo(xPosition, yPosition);
        painter.lowerPen();
        painter.setPenColor(color);
        painter.startFill(color);
        for (int i = 0; i < 3; i++) {
            painter.forward(size);
            painter.turnLeft(120);
        }
        painter.finishFill();
    }

    /**
     * Draws the alien's arms held out in front of its body: two arms hanging
     * down and a bar across the bottom where the hands meet.
     *
     * @param xPosition the x coordinate of the left arm
     * @param yPosition the y coordinate of the top of the arms
     * @param width     how far apart the arms are
     */
    public void drawArms(int xPosition, int yPosition, int width) {
        int depth = width / 2;
        int bar = 3;
        drawRectangle(Color.BLACK, xPosition, yPosition, bar, depth);
        drawRectangle(Color.BLACK, xPosition + width - bar, yPosition, bar, depth);
        drawRectangle(Color.BLACK, xPosition, yPosition - depth + bar, width, bar);
    }

    /**
     * @param bodyColor the color of the body and feet
     * @param xPosition the x coordinate of the middle of the alien
     * @param yPosition the y coordinate of the bottom of its feet
     * @param height    how tall the alien is
     */
    public void drawRoundAlien(Color bodyColor, int xPosition, int yPosition, int height) {
        int radius = height * 3 / 8;
        int footSize = height / 4;
        int footHeight = footSize * 87 / 100;
        int bodyY = yPosition + footHeight + radius - 4;

        drawTriangle(bodyColor, xPosition - footSize - 2, yPosition, footSize);
        drawTriangle(bodyColor, xPosition + 2, yPosition, footSize);

        drawCircle(bodyColor, xPosition, bodyY, radius);

        int eyeX = xPosition - radius * 4 / 5;
        int eyeY = bodyY + radius * 4 / 5;
        drawCircle(Color.WHITE, eyeX, eyeY, radius / 3);
        drawCircle(Color.BLACK, eyeX - radius / 12, eyeY + radius / 8, radius / 9);

        drawRectangle(Color.BLACK, eyeX - radius / 8, eyeY - radius / 7, radius / 4, 2);

        drawArms(xPosition - radius / 4, bodyY + radius / 6, radius / 2);
    }

    /**
     * @param bodyColor the color of the body and feet
     * @param xPosition the x coordinate of the middle of the alien
     * @param yPosition the y coordinate of the bottom of its feet
     * @param height    how tall the alien is
     */
    public void drawSquareAlien(Color bodyColor, int xPosition, int yPosition, int height) {
        int side = height * 3 / 4;
        int footSize = height / 4;
        int footHeight = footSize * 87 / 100;
        int bodyTop = yPosition + footHeight + side - 4;

        drawTriangle(bodyColor, xPosition - footSize - 2, yPosition, footSize);
        drawTriangle(bodyColor, xPosition + 2, yPosition, footSize);

        drawRectangle(bodyColor, xPosition - side / 2, bodyTop, side, side);

        int eyeX = xPosition + side / 2 + side / 8;
        int eyeY = bodyTop - side / 6;
        drawCircle(Color.WHITE, eyeX, eyeY, side / 5);
        drawCircle(Color.BLACK, eyeX + side / 20, eyeY + side / 12, side / 15);

        drawRectangle(Color.BLACK, eyeX - side / 12, eyeY - side / 11, side / 6, 2);

        drawArms(xPosition - side / 6, bodyTop - side / 4, side / 3);
    }

    /**
     * @param leafColor the color of the leaves
     * @param xPosition the x coordinate of the middle of the trunk
     * @param yPosition the y coordinate of the bottom of the trunk
     * @param height    how tall the tree is
     */
    public void drawTree(Color leafColor, int xPosition, int yPosition, int height) {
        int trunkWidth = height / 12;
        int trunkHeight = height / 2;
        drawRectangle(new Color(110, 70, 40), xPosition - trunkWidth / 2, yPosition + trunkHeight, trunkWidth, trunkHeight);

        int count = 5 + (int) (Math.random() * 3);
        for (int i = 0; i < count; i++) {
            int leafSize = (int) (height * (0.2 + Math.random() * 0.55));
            int shift = (int) ((Math.random() - 0.5) * height * 0.4);
            int leafY = yPosition + trunkHeight - leafSize / 3 + i * (height / 10);
            drawTriangle(leafColor, xPosition + shift - leafSize / 2, leafY, leafSize);
        }
    }

    /**
     * Draws a ring in the sky seen from an angle, so the left side shows the green
     * surface with water on it and the right side shows the grey outside.
     *
     * @param xPosition the x coordinate of the middle of the grey circle
     * @param yPosition the y coordinate of the middle of the ring
     * @param radius    how big the ring is
     */
    public void drawRing(int xPosition, int yPosition, int radius) {
        drawCircle(new Color(150, 150, 160), xPosition, yPosition, radius);

        int shift = radius / 4;
        int greenX = xPosition - shift;
        drawCircle(new Color(70, 170, 80), greenX, yPosition, radius);

        int holeRadius = radius * 7 / 8;
        int holeX = greenX + radius - holeRadius;
        drawCircle(skyColor, holeX, yPosition, holeRadius);

        int far = radius * 7 / 8;
        int near = radius * 3 / 4;
        int up = radius * 7 / 16;
        drawCircle(new Color(60, 120, 230), greenX - far, yPosition, radius / 16);
        drawCircle(new Color(60, 120, 230), greenX - near, yPosition + up, radius / 16);
        drawCircle(new Color(60, 120, 230), greenX - near, yPosition - up, radius / 16);
    }

    /**
     * @param count how many stars to draw
     */
    public void drawStars(int count) {
        for (int i = 0; i < count; i++) {
            int starX = (int) (Math.random() * 780) - 390;
            int starY = (int) (Math.random() * 320) - 30;
            int starSize = 1 + (int) (Math.random() * 2);
            drawCircle(new Color(255, 240, 150), starX, starY, starSize);
        }
    }

    public static void main(String[] args) {
        AlienScene scene = new AlienScene();
        scene.canvas.setBackgroundColor(scene.skyColor);

        scene.drawStars(40);

        scene.drawCircle(new Color(90, 40, 130), 0, -1050, 1000);

        scene.drawRing(220, 190, 60);

        Color gold = new Color(230, 180, 40);
        Color red = new Color(210, 50, 50);
        Color purple = new Color(140, 60, 190);
        Color pink = new Color(255, 110, 180);
        Color lime = new Color(160, 230, 60);

        scene.drawTree(lime, -30, -55, 150);
        scene.drawTree(new Color(40, 150, 90), 30, -60, 120);

        scene.drawSquareAlien(red, -220, -80, 110);
        scene.drawSquareAlien(purple, -110, -62, 70);

        scene.drawRoundAlien(gold, 180, -72, 110);
        scene.drawRoundAlien(pink, 280, -95, 70);

        scene.canvas.awaitClose();
    }
}