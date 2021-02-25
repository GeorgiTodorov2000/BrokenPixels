import java.awt.*;

public class Pixels {
    public static final int TILE_SIZE = 10;


    private int row;
    private int col;
    private Color color;
    private int id;



    public Pixels(int row, int col, int id, Color color) {
        this.row = row;
        this.col = col;
        this.color = color;
        this.id = id;
    }

    public Pixels() {}

    public int getRow() { return row; }

    public int getCol() {
        return col;
    }

    public int getId() {
        return id;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public Color getColor() {
        return color;
    }

    public void render(Graphics g) {
        int tileX = this.col * TILE_SIZE;
        int tileY = this.row * TILE_SIZE;


        g.setColor(this.color);

        g.fillRect(tileX, tileY, TILE_SIZE, TILE_SIZE);
    }
}
