package rendering;

import java.awt.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Paul on 4/18/17.
 */
public class Square {
    public final int size;
    public final Color color;

    public Square(int size, Color color) {
        this.size = size;
        this.color = color;
    }

    public static List<Square> readAll() throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new FileReader("squares2"));
        String line;
        List<Square> squares = new ArrayList<>();
        while ((line = bufferedReader.readLine()) != null) {
            String[] split = line.split("#");
            int size = Integer.valueOf(split[0]);
            Color color = Color.decode("#" + split[1]);
            squares.add(new Square(size, color));
        }
        return squares;
    }

    @Override
    public String toString() {
        return "rendering.Square{" +
                "size=" + size +
                ", color=" + color +
                '}';
    }
}
