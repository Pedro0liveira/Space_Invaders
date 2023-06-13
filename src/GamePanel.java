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
    private int nivel, qtdInimigos;
    private ArrayList<Inimigos> inimigos;
    private boolean gameOver;
    private Image i;

    public GamePanel(){
        gameOver = false;
        nivel = 1;
        qtdInimigos = 10;
        inimigos = new ArrayList<Inimigos>();
        Random random = new Random();
        int screenWidth = Toolkit.getDefaultToolkit().getScreenSize().width;
        int screenHeight = Toolkit.getDefaultToolkit().getScreenSize().height;
        for(int i = 0; i < qtdInimigos; i++)
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
        timer = new Timer(20,this);
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
            if (tiro.isVisible()){
                g2d.drawImage(tiro.getImagem(), tiro.getX(), tiro.getY(), this);
            }else{
                removerTiros.add(tiro);
            }
        }
        for (Tiros tiro : removerTiros) {
            nave.getTiros().remove(tiro);
        }
        g2d.setColor(Color.WHITE);
        g2d.drawString("Inimigos vivos: " + inimigos.size(), 5, 15);
        g2d.drawString("Tiros: " + nave.getTiros().size(), 5, 30);
        g2d.drawString("Nível: " + nivel, 5, 45);

        if(gameOver){
            int screenWidth = Toolkit.getDefaultToolkit().getScreenSize().width;
            int screenHeight = Toolkit.getDefaultToolkit().getScreenSize().height;
            ImageIcon ii = new ImageIcon("gameOver.png");
            i = ii.getImage();
            g2d.drawImage(i, ((screenWidth - i.getWidth(null)) / 2),(screenHeight / 2) - i.getHeight(null),null);

            Font medFont = new Font("Consolas", Font.BOLD, 50);
            FontMetrics metr = this.getFontMetrics(medFont);
            g2d.setColor(Color.RED);
            g2d.setFont(medFont);
            g2d.drawString("NIVEL: " + nivel, (screenWidth - metr.stringWidth("NIVEL " + nivel)) / 2, screenHeight / 2);



        }

        g2d.dispose();
    }

    public void checarColisoes(){
        Rectangle retanguloNave = this.nave.getRetangulo();
        for (Inimigos inimigo : inimigos) {
            Rectangle retanguloInimigo = inimigo.getRetangulo();
            for (Tiros tiro : nave.getTiros()){
                Rectangle retanguloTiro = tiro.getRetangulo();
                if (retanguloTiro.intersects(retanguloInimigo))
                {
                    inimigo.setVisible(false);
                    tiro.setVisible(false);
                }
            }
            if (retanguloNave.intersects(retanguloInimigo)) {
                derrota(inimigo);
            }
            
        }
    }
    
    private void levelUp() {
        nivel++;
        qtdInimigos += 2;
        inimigos = new ArrayList<Inimigos>();
        Random random = new Random();
        int screenWidth = Toolkit.getDefaultToolkit().getScreenSize().width;
        int screenHeight = Toolkit.getDefaultToolkit().getScreenSize().height;
        for(int i = 0; i < qtdInimigos; i++)
        {
            Inimigos inimigo = new Inimigos(
                random.nextInt(10,  screenWidth - 40),
                random.nextInt(-screenHeight, 0)
            );
            inimigo.setVelocidade(inimigo.getVelocidade() + 1);
            inimigos.add(inimigo);
            if(i > 0){
                if (inimigos.get(i).getRetangulo().intersects(inimigos.get(i-1).getRetangulo())){
                    inimigos.remove(i);
                    i--;
                }
            }
        }
    }

    public void derrota(Inimigos inimigoDerrota){
        gameOver = true;
        setBackground(Color.WHITE);
        for (Inimigos inimigo : inimigos) {
            if (inimigo != inimigoDerrota) {
                inimigo.setVisible(false);
            }
        }
        for (Tiros tiro : nave.getTiros()) {
            tiro.setVisible(false);
        }
        timer.stop();
    }

    @Override
    public void keyTyped(KeyEvent e) {
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
        for (Tiros tiro : nave.getTiros()) {
            tiro.mover();
        }
        if (inimigos.isEmpty()) {
            levelUp();
        }
        checarColisoes();
        repaint();
    }
}
