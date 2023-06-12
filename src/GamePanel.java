import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Random;

public class GamePanel extends JPanel implements KeyListener, ActionListener {

    private Timer timer;
    private Nave nave;
    private ArrayList<Inimigos> inimigos;

    public GamePanel(){
        inimigos = new ArrayList<Inimigos>();
        Random random = new Random();
        int screenWidth = Toolkit.getDefaultToolkit().getScreenSize().width;
        int screenHeight = Toolkit.getDefaultToolkit().getScreenSize().height;
        for(int i = 0; i < 10; i++)
        {
            Inimigos inimigo = new Inimigos(
                random.nextInt(10,  screenWidth - 40),
                random.nextInt(-screenHeight, 0)
            );
            inimigos.add(inimigo);
            if(i > 0){
                if (inimigos.get(i).getRetangulo().intersects(inimigos.get(i-1).getRetangulo())){
                    inimigos.remove(i);
                    i--;
                }
            }
        }

        nave = new Nave();
        addKeyListener(this);
        setBackground(Color.BLACK);
        setSize(getPreferredSize());
        setFocusable(true);
        setLayout(null);
       

        timer = new Timer(1,this);
        timer.start();
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D)g;
        g2d.drawImage(nave.getImagem(), nave.getX(), nave.getY(),null);
        ArrayList<Inimigos> removerInimigos = new ArrayList<>();
        for(Inimigos inimigo : inimigos){
            if (inimigo.isVisible())
                g2d.drawImage(inimigo.getImagem(), inimigo.getX(), inimigo.getY(),null);
            else
                removerInimigos.add(inimigo);
        }
        for (Inimigos inimigo : removerInimigos) {
            inimigos.remove(inimigo);
        }
        ArrayList<Tiros> removerTiros = new ArrayList<>();
        for (Tiros tiro : nave.getTiros())
        {
            if (tiro.isVisible())
                g2d.drawImage(tiro.getImagem(), tiro.getX(), tiro.getY(), this);
            else
                removerTiros.add(tiro);
        }
        for (Tiros tiro : removerTiros) {
            nave.getTiros().remove(tiro);
        }
    }

    public void checarColisoes(){
        Rectangle nave = this.nave.getRetangulo();
        for (Inimigos inimigo : inimigos) {
            Rectangle rectangleInimigo = inimigo.getRetangulo();
            if (nave.intersects(rectangleInimigo)) {
                timer.stop();
            }
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
        throw new UnsupportedOperationException("Unimplemented method 'keyTyped'");
    }
    
    @Override
    public void keyPressed(KeyEvent e) {
        int tecla = e.getKeyCode();

        switch (tecla) {
            case KeyEvent.VK_LEFT:
                nave.setDx(-10);
                break;
            case KeyEvent.VK_RIGHT:
                nave.setDx(10);
                break;
            case KeyEvent.VK_UP:
                nave.setDy(-10);
                break;
            case KeyEvent.VK_DOWN:
                nave.setDy(10);
                break;
            case KeyEvent.VK_SPACE:
                nave.atirar();
                break;
        
            default:
                break;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int tecla = e.getKeyCode();

        switch (tecla) {
            case KeyEvent.VK_LEFT:
                nave.setDx(0);
                break;
            case KeyEvent.VK_RIGHT:
                nave.setDx(0);
                break;
            case KeyEvent.VK_UP:
                nave.setDy(0);
                break;
            case KeyEvent.VK_DOWN:
                nave.setDy(0);
                break;
            default:
                break;
        }
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        nave.mover();
        for (Inimigos inimigo : inimigos) {
            inimigo.mover();
        }

        checarColisoes();
        repaint();
    }
}
