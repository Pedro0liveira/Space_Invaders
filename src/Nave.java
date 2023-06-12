import java.awt.Image;
import java.awt.Rectangle;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import java.awt.Toolkit;

public class Nave {
    private int x, y, dx, dy, width,height;
    private Image imagem;
    private boolean visible;
    private ArrayList<Tiros> tiros;

    public Nave(){
        setVisible(true);
        setTiros(new ArrayList<>());
        ImageIcon i = new ImageIcon("naveNivel1.png");
        setImagem(i.getImage());
        setWidth(getImagem().getWidth(null));
        setHeight(getImagem().getHeight(null));
        int screenWidth = Toolkit.getDefaultToolkit().getScreenSize().width;
        int screenHeight = Toolkit.getDefaultToolkit().getScreenSize().height;
        setX(screenWidth / 2 - getWidth() / 2);
        setY(screenHeight / 2);
        setDx(0);
        setDy(0);
    }

    public void mover(){
        x += dx;
        y += dy;
    }

    public void atirar(){
        getTiros().add(new Tiros(getX() + 67, getY()));
    }

    public Rectangle getRetangulo() {
        return new Rectangle(x + 20, y - 20, width-50, height-70);
    }

    public int getX() {
      return this.x;
    }
    public void setX(int value) {
      this.x = value;
    }

    public int getDx() {
      return this.dx;
    }
    public void setDx(int value) {
      this.dx = value;
    }

    public int getY() {
      return this.y;
    }
    public void setY(int value) {
      this.y = value;
    }

    public int getDy() {
      return this.dy;
    }
    public void setDy(int value) {
      this.dy = value;
    }

    public int getWidth() {
      return this.width;
    }
    public void setWidth(int value) {
      this.width = value;
    }

    public int getHeight() {
      return this.height;
    }
    public void setHeight(int value) {
      this.height = value;
    }

    public Image getImagem() {
      return this.imagem;
    }
    public void setImagem(Image value) {
      this.imagem = value;
    }

    public boolean isVisible() {
      return this.visible;
    }
    public void setVisible(boolean value) {
      this.visible = value;
    }

    public ArrayList<Tiros> getTiros() {
      return this.tiros;
    }
    public void setTiros(ArrayList<Tiros> value) {
      this.tiros = value;
    }
}
