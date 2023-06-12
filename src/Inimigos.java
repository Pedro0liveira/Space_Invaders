import java.awt.Image;
import java.awt.Rectangle;

import javax.swing.ImageIcon;

public class Inimigos {
    private Image imagem;
    private int x, y, width, height, velocidade;
    private boolean visible;

    public Inimigos(int x, int y){
        setVisible(true);
        setX(x);
        setY(y);
        setVelocidade(1);
        ImageIcon i = new ImageIcon("inimigo.png");
        setImagem(i.getImage());
        setWidth(getImagem().getWidth(null));
        setHeight(getImagem().getHeight(null));
        imagem = imagem.getScaledInstance(width/5,height/5,0);
    }

    public void mover(){
        if (y < 1024)
            setY(y+velocidade);
        else 
            setY(0);
    }
    public Rectangle getRetangulo() {
        return new Rectangle(x, y, width/10, height/10);
    }

    public Image getImagem() {
      return this.imagem;
    }
    public void setImagem(Image value) {
      this.imagem = value;
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

    public int getVelocidade(){
        return this.velocidade;
    }

    public void setVelocidade(int value){
        this.velocidade = value;
    }

    public boolean isVisible() {
      return this.visible;
    }
    public void setVisible(boolean value) {
      this.visible = value;
    }
}
