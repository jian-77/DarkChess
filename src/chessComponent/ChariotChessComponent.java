package chessComponent;

import controller.ClickController;
import model.ChessColor;
import model.ChessboardPoint;

import java.awt.*;

/**
 * 表示黑红车
 */
public class ChariotChessComponent extends ChessComponent {

    private static int rank=4;
    private static int point=5;
    public static int redBeCaught=0;
    public static int blackBeCaught=0;

    public int getRank() {
        return rank;
    }

    public ChariotChessComponent(ChessboardPoint chessboardPoint, Point location, ChessColor chessColor, ClickController clickController, int size,int point) {
        super(chessboardPoint, location, chessColor, clickController, size,point);
        if (this.getChessColor() == ChessColor.RED) {
            name = "俥";
        } else {
            name = "車";
        }
    }

}
