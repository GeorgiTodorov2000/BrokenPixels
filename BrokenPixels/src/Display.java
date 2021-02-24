import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Random;

public class Display extends JFrame implements MouseListener {
    public static final int TILE_SIDE_COUNT = 64;
    private Object[][] pixelCollection;
    private Object selectedPixel;
    private final Random random = new Random();
    private int CLICK_COUNT = 0;

    public Display() {

        this.pixelCollection = new Object[TILE_SIDE_COUNT][TILE_SIDE_COUNT];
        pixelCreator();

        this.setSize(800, 800);
        this.setVisible(true);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.addMouseListener(this);


    }

    @Override
    public void mouseClicked(MouseEvent e) {
        int row = this.getBoardDimensionBasedOnCoordinates(e.getY());
        int col = this.getBoardDimensionBasedOnCoordinates(e.getX());
            this.selectedPixel = this.pixelCollection[row][col];
            Pixels pixels = (Pixels) this.selectedPixel;
            switch (pixels.getId()) {
                case 0: {//Broken pixel
                    System.out.println("This pixel is broken");
                    this.pixelCollection[row][col] = new Pixels(row, col, 0, Color.BLACK);
                    break;
                }
                case 1: {//Fine pixel
                    System.out.println("This pixel is perfectly fine");
                    break;
                }
                case 2: {//Will broke pixel
                    System.out.println("You clicked on the wrong pixel, you may break something if you keep going like that");
                    if (CLICK_COUNT % 3 == 0 && CLICK_COUNT != 0) {
                        System.out.println("This pixel was 50/50 but you broke it");
                        this.pixelCollection[row][col] = new Pixels(row, col, 0, Color.BLACK);

                    } CLICK_COUNT++;
                }


        }


        if(this.hasBoardPixel(row, col)) {
            this.selectedPixel = this.getBoardPixel(row, col);
        }
        repaint();
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    // Painting the game board
    public void paint(Graphics g) {
        super.paint(g);

        for(int row = 1; row < 64; ++row) {
            for(int col = 1; col < 64; ++col) {
                if(this.hasBoardPixel(row, col)) {

                    Pixels pixels = (Pixels) this.getBoardPixel(row, col);
                    pixels.render(g);
                }
            }
        }
    }



    //Pixel creator
    private void pixelCreator() {
        int randomColor = random.nextInt(3);
        int id = random.nextInt(3);
        for(int row = 1; row < 64; ++row) {
            for(int col = 1; col < 64; ++col) {

                switch (randomColor) {
                    case 0:this.pixelCollection[row][col] = new Pixels(row, col, id, Color.RED); break;
                    case 1:this.pixelCollection[row][col] = new Pixels(row, col, id, Color.GREEN); break;
                    case 2:this.pixelCollection[row][col] = new Pixels(row, col, id, Color.BLUE); break;
                }
                randomColor = random.nextInt(3);
                id = random.nextInt(3);
            }
        }
    }

    private Object getBoardPixel(int row, int col) {
        return this.pixelCollection[row][col];
    }
    private boolean hasBoardPixel(int row, int col) {
        return this.getBoardPixel(row, col) != null;
    }
    private int getBoardDimensionBasedOnCoordinates(int coordinates) {
        return coordinates / 10;
    }
}
