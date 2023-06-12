import javax.swing.JFrame;

public class GameFrame extends JFrame{
    private GamePanel panel;

    public GameFrame(){
        panel = new GamePanel();
        add(panel);
        setTitle("Space Invaders");
        setExtendedState(MAXIMIZED_BOTH);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        setVisible(true);
    }
}

