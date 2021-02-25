import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Random;


public class Display extends JFrame implements MouseListener {
    public static final int TILE_SIDE_COUNT = 64;
    private Object[][] pixelCollection;
    private ArrayList<Integer> phoneBroken = new ArrayList<Integer>();
    private ArrayList<String> phoneModel = new ArrayList<String>(10);
    private Object selectedPixel;
    Phones phones = new Phones();
    private final Random random = new Random();
    private int CLICK_COUNT = 0;
    private int BROKEN_PIXELS = 0;
    private int CHECKED_PIXELS = phones.getNUMBERS_OF_CHECKED_PHONES();


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
//                    System.out.println("This pixel is broken");
                    this.pixelCollection[row][col] = new Pixels(row, col, 0, Color.BLACK);
                    break;
                }
                case 1: {//Fine pixel
//                    System.out.println("This pixel is perfectly fine");
                    break;
                }
                case 2: {//Will broke pixel
//                    System.out.println("You clicked on the wrong pixel, you may break something if you keep going like that");
                    if (CLICK_COUNT % 3 == 0 && CLICK_COUNT != 0) {
//                        System.out.println("This pixel was 50/50 but you broke it");
                        this.pixelCollection[row][col] = new Pixels(row, col, 0, Color.BLACK);
                        BROKEN_PIXELS++;
                    } CLICK_COUNT++;
                }

        }
        CHECKED_PIXELS++;
        if(CHECKED_PIXELS >= 10) {
            getNewPhone();
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

    private void getNewPhone() {
        phoneModel.add(generateRandModel());
        phoneBroken.add(brokenPhone());

        String phoneStatus = "";

        if (BROKEN_PIXELS <= 2048) {
            phoneStatus = "This phone is fine ";
        } else {
            phoneStatus = "This phone is broken ";
        }

        String phoneFinalStatus = phoneModel.get(phones.getNUMBERS_OF_CHECKED_PHONES()) + " " + phoneStatus + "with " + BROKEN_PIXELS + " broken pixels";

        Modal.render(this, "Phone status", phoneFinalStatus);

        phones.setNUMBERS_OF_CHECKED_PHONES(phones.getNUMBERS_OF_CHECKED_PHONES() + 1);
        new Display();
    }



    //Checking if phone is usable
    private int brokenPhone() {
        if(BROKEN_PIXELS == 2048) {//64x64 = 4096 If we have 50% broken pixels the phone is unusable
            System.out.println("This phone is no longer usable");
            return 0;
        } else {
            BROKEN_PIXELS++;
            return 1;
        }
    }


    //Generates the model of the phone
    protected String generateRandModel() {
        String SALTCHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
        StringBuilder salt = new StringBuilder();
        Random rnd = new Random();
        while (salt.length() < 10) { // length of the random string.
            int index = (int) (rnd.nextFloat() * SALTCHARS.length());
            salt.append(SALTCHARS.charAt(index));
        }
        String saltStr = salt.toString();
        return saltStr;

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
                    case 0:this.pixelCollection[row][col] = new Pixels(row, col, id, Color.RED); BROKEN_PIXELS++; break;
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
