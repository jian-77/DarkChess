package Bonus;

import view.ChessGameFrame;
import controller.ClickController;
import view.Chessboard;

import java.util.Timer;
import java.util.TimerTask;

public class CountDown {
    public static Chessboard chessboard;
    public static int[] count = {16};
    public static boolean mode;

    public static void startCountDown() {

            Timer timer = new Timer();
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    if (mode==false){
                        timer.cancel();
                    }
                    else {
                    count[0] -= 1;
                    ChessGameFrame.getStatusLabel4().setText(String.format("倒计时:%d", count[0]));
                    if (count[0] <= 0) {
                        ClickController clickController = new ClickController(chessboard);
                        clickController.swapPlayer();
                        count[0] = 15;
                    }
                    }
                }
            }, 0, 1000);
            try {
                Thread.sleep(1000);
            } catch (Exception ex) {
                timer.cancel();
            }
        }
    }
