package AI;
import chessComponent.SquareComponent;
import controller.ClickController;
import model.ChessColor;
import view.ChessGameFrame;
import view.Chessboard;

import java.util.Random;

public class AIClickController extends ClickController {
    public AIClickController(Chessboard chessboard) {
        super(chessboard);
    }

    public  void SwapPlayer() {
        System.out.println("wow");
        chessboard.setCurrentColor(chessboard.getCurrentColor() == ChessColor.RED ? ChessColor.RED : ChessColor.BLACK);
        ChessGameFrame.getStatusLabel().setText(String.format("%s's TURN", chessboard.getCurrentColor().getName()));
        if (chessboard.getCurrentColor() == ChessColor.BLACK) {
            for (SquareComponent[] squareComponents : chessboard.getSquareComponents()) {
                for (SquareComponent first : squareComponents) {
                    if (first.isReversal() == true && first.getChessColor() == ChessColor.BLACK) {
                        int n = 0;
                        for (SquareComponent[] squareComponent : chessboard.getSquareComponents()) {
                            for (SquareComponent second : squareComponent) {
                                if (handleSecond(second, first)) {
                                    {
                                        chessboard.swapChessComponents(first, second);
                                        chessboard.clickController.swapPlayer();
                                        n += 1;
                                        break;
                                    }
                                }
                            }
                            break;
                        }
                        if (n==0){
                            int x,y;
                            Random random1 = new Random();
                            Random random2= new Random();
                            while (true) {
                                x= random1.nextInt(8);
                                y= random2.nextInt(8);
                                if (chessboard.getChessComponents()[x][y].isReversal()==false){
                                    break;
                                }
                            }
                            chessboard.getChessComponents()[x][y].setReversal(true);
                            chessboard.getChessComponents()[x][y].repaint();
                        }
                    }
                    else {
                        int x,y;
                        Random random1 = new Random();
                        Random random2= new Random();
                        while (true) {
                            x= random1.nextInt(8);
                            y= random2.nextInt(8);
                            if (chessboard.getChessComponents()[x][y].isReversal()==false){
                                break;
                            }
                        }
                        chessboard.getChessComponents()[x][y].setReversal(true);
                        chessboard.getChessComponents()[x][y].repaint();
                    }
                }
            }
        }
    }
}
