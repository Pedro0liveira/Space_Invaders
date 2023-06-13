import java.awt.Image;
import java.awt.Rectangle;

import javax.swing.ImageIcon;

public class Tiros {
    private int x, y, width, height, velocidade;
    private Image imagem;
    private boolean visible;

    public Tiros(int x, int y){
        setVisible(true);
        setX(x);
        setY(y);
        setVelocidade(15);
        ImageIcon i = new ImageIcon("bullet.png");
        setImagem(i.getImage());
        setWidth(getImagem().getWidth(null));
        setHeight(getImagem().getHeight(null));
    }
    
    public void mover(){
        setY(y - getVelocidade());
        setVisible(y < 0 ? false : true);
    }

    public Rectangle getRetangulo() {
        return new Rectangle(getX(), getY(), getWidth(), getHeight());
    }
    
    public int getX() {
      return this.x;
    }
    public void setX(int value) {
      this.x = value;
    }

    public int getY() {
      return this.y;
    }
    public void setY(int value) {
      this.y = value;
    }

    public Image getImagem() {
      return this.imagem;
    }
    public void setImagem(Image value) {
      this.imagem = value;
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

    public int getVelocidade() {
      return this.velocidade;
    }
    public void setVelocidade(int value) {
      this.velocidade = value;
    }

    public boolean isVisible() {
      return this.visible;
    }
    public void setVisible(boolean value) {
      this.visible = value;
    }
}
