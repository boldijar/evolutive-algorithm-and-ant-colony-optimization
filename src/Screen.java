import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.function.Consumer;

/**
 * Created by Paul on 4/18/17.
 */
class Screen extends JPanel implements KeyListener {
    static final int HEIGHT = 600;
    static final int WIDTH = 800;
    static final int STROKE_RADIUS = 5;

    private List<Square> squareList;
    private List<List<Square>> doubleList;

    private void init() {
        doubleList = ACO.getMock(squareList);
    }

    Screen() throws IOException {
        squareList = Square.readAll();
        init();
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(WIDTH, HEIGHT);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D graphics2D = (Graphics2D) g;
        graphics2D.setStroke(new BasicStroke(STROKE_RADIUS));
        DrawingUtils.drawAll(doubleList, graphics2D);
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
    }

    @Override
    public void keyReleased(KeyEvent e) {
        init();
        repaint();
    }
}
