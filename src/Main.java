import view.ChessGameFrame;
import view.StartTimeFrame;
import javax.swing.*;
import java.awt.*;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            //ChessGameFrame mainFrame = new ChessGameFrame(720, 720);
           // mainFrame.setVisible(true);
            StartTimeFrame startFrame = new StartTimeFrame(360,360);
            startFrame.setVisible(true);
        });

        Music audioPlayWave = new Music("resources/The Rain .wav");// 开音乐
        //audioPlayWave.start();
    }
}