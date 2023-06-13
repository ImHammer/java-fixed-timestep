import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import javax.swing.JFrame;

public class Jogo {
    private JFrame frame;
    private Canvas canvas;
    private BufferStrategy bufferStrategy;
    private boolean running;
    
    public Jogo() {
        frame = new JFrame("Meu Jogo");
        canvas = new Canvas();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);
        frame.setResizable(false);
        frame.setVisible(true);
        
        canvas.setSize(800, 600);
        canvas.setBackground(Color.BLACK);
        frame.add(canvas);
        canvas.createBufferStrategy(2);
        bufferStrategy = canvas.getBufferStrategy();
        
        running = true;
    }
    
    public void start() {
        while (running) {
            update();
            render();
            
            try {
                Thread.sleep(16); // Aproximadamente 60 FPS
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    
    private void update() {
        // Atualizações lógicas do jogo aqui
    }
    
    private void render() {
        Graphics g = bufferStrategy.getDrawGraphics();
        g.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
        
        // Desenhe elementos gráficos aqui
        g.setColor(Color.RED);
        g.fillRect(100, 100, 200, 200);
        
        g.dispose();
        bufferStrategy.show();
    }
    
    public static void main(String[] args) {
        Jogo jogo = new Jogo();
        jogo.start();
    }
}