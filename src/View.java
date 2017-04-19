import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by Paul on 4/18/17.
 */
public class View extends JFrame {
    private View() throws IOException {
        super("Game Frame");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Screen screen = new Screen();
        getContentPane().add(screen);
        addKeyListener(screen);
        setFocusable(true);
        setFocusTraversalKeysEnabled(false);

        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public static void main(String[] args) throws IOException {
        new View();
    }

}