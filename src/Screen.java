import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.List;
import java.util.function.Consumer;

/**
 * Created by Paul on 4/18/17.
 */
class Screen extends JPanel {
    static final int HEIGHT = 600;
    static final int WIDTH = 800;

    private List<Square> squareList;
    private int y;

    public Screen() throws IOException {
        squareList = Square.readAll();
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(WIDTH, HEIGHT);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D graphics2D = (Graphics2D) g;
        graphics2D.setStroke(new BasicStroke(5));

        System.out.println(squareList);
        y = 0;
        squareList.forEach(square -> {
            drawSquare(graphics2D, 0, y, square);
            y += square.size;
        });


    }

    private void drawSquare(Graphics2D graphics2D, int x, int y, Square square) {
        graphics2D.setColor(Color.BLACK);
        graphics2D.drawRect(x, y, square.size, square.size);
        graphics2D.setColor(square.color);
        graphics2D.fillRect(x, y, square.size, square.size);
    }
}
