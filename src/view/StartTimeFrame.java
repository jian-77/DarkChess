package view;

import javax.swing.*;
import java.awt.*;
import AI.AI;

public class StartTimeFrame extends JFrame {
    private int WIDTH;
    private int HEIGHT;

    JLayeredPane layeredPane = new JLayeredPane();
    BackgroundPic backgroundPic = new BackgroundPic((new ImageIcon("resources/start.png")).getImage());


    public StartTimeFrame(int width, int height){
        this.WIDTH = width;
        this.HEIGHT = height;
        this.setLayeredPane(layeredPane);

        setSize(WIDTH, HEIGHT);
        setLocationRelativeTo(null);
        getContentPane().setBackground(Color.WHITE);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLayout(null);
        setVisible(true);

        addPVPStartButton();
        addPVAStartButton();
        addBackgroundPic();
    }

    public void addPVPStartButton(){
        JButton button = new JButton("PVP");
        button.setLocation(WIDTH * 1 / 5 - 20, HEIGHT / 10 + 70);
        button.setSize(100, 60);
        button.setFont(new Font("Rockwell", Font.BOLD, 15));
        button.setBackground(Color.LIGHT_GRAY);
        layeredPane.add(button,JLayeredPane.MODAL_LAYER);

        setVisible(true);

        button.addActionListener(e -> {
            ChessGameFrame mainFrame = new ChessGameFrame(800, 720);
            mainFrame.setVisible(true);
            repaint();
        });
    }

    public void addPVAStartButton(){
        JButton button = new JButton("PVA");
        button.setLocation(WIDTH * 3 / 5 , HEIGHT / 10 + 70);
        button.setSize(100, 60);
        button.setFont(new Font("Rockwell", Font.BOLD, 15));
        button.setBackground(Color.LIGHT_GRAY);
        layeredPane.add(button,JLayeredPane.MODAL_LAYER);

        setVisible(true);

        button.addActionListener(e -> {
            ChessGameFrame mainFrame = new ChessGameFrame(800, 720);
            mainFrame.setVisible(true);
            repaint();
            AI.startAIMode();
        });
    }

    public void addBackgroundPic(){
        backgroundPic.setBounds(0,0,WIDTH,HEIGHT);
        layeredPane.add(backgroundPic, JLayeredPane.DEFAULT_LAYER);
    }
}
