package rendering;

import javax.swing.*;
import java.io.IOException;
import java.util.List;

/**
 * Created by Paul on 4/18/17.
 */
public abstract class View extends JFrame implements ScreenLoadListener {
    public View() throws IOException {
        super("Game Frame");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Screen screen = new Screen(this);
        getContentPane().add(screen);
        addKeyListener(screen);
        setFocusable(true);
        setFocusTraversalKeysEnabled(false);

        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

}