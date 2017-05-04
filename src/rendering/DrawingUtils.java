package rendering;

import java.awt.*;
import java.util.List;


/**
 * Created by Paul on 4/19/17.
 */
public class DrawingUtils {


    static void drawAll(List<List<Square>> doubleList, Graphics2D graphics2D) {
        float maxStackSize = maxStackHeight(doubleList);
        if (maxStackSize > Screen.HEIGHT) {
            float ratio = Screen.HEIGHT / maxStackSize;
            graphics2D.scale(ratio, ratio);
        } else {
            graphics2D.scale(1, 1);
        }
        int x = 0;
        for (List<Square> list : doubleList) {
            int y = 0;
            int maxWidth = maxSize(list);
            drawStack(list, graphics2D, x, maxWidth);
            x += maxWidth;
        }
    }

    private static int maxStackHeight(List<List<Square>> doubleList) {
        int maxHeight = 0;
        for (List<Square> list : doubleList) {
            int stackHeight = sizesSum(list);
            maxHeight = Math.max(maxHeight, stackHeight);
        }
        return maxHeight;
    }

    private static int maxSize(List<Square> list) {
        int maxWidth = 0;
        for (Square square : list) {
            maxWidth = Math.max(maxWidth, square.size);
        }
        return maxWidth;
    }

    private static int sizesSum(List<Square> list) {
        int size = 0;
        for (Square square : list) {
            size += square.size;
        }
        return size;
    }

    private static void drawStack(List<Square> list, Graphics2D graphics2D, int x, int maxWidth) {
        int y = 0;
        for (Square square : list) {
            int centeredX = x + (maxWidth - square.size) / 2;
            drawSquare(graphics2D, centeredX, y, square);
            y += square.size;
        }
    }

    private static void drawSquare(Graphics2D graphics2D, int x, int y, Square square) {
        graphics2D.setColor(square.color);
        graphics2D.fillRect(x, y, square.size, square.size);
        graphics2D.setColor(Color.BLACK);
        graphics2D.drawRect(x + Screen.STROKE_RADIUS / 2, y + Screen.STROKE_RADIUS / 2, square.size - Screen.STROKE_RADIUS / 2, square.size - Screen.STROKE_RADIUS / 3);

    }
}
