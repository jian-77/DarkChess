package chessComponent;

import controller.ClickController;
import model.ChessColor;
import model.ChessboardPoint;

import java.awt.*;

public class AdvisorChessComponent extends ChessComponent {

    private static int rank=2;
    private static int point=10;
    public static int redBeCaught=0;
    public static int blackBeCaught=0;
    public int getRank() {
        return rank;
    }


    public AdvisorChessComponent(ChessboardPoint chessboardPoint, Point location, ChessColor chessColor, ClickController clickController, int size,int point) {
        super(chessboardPoint, location, chessColor, clickController, size,point);
        if (this.getChessColor() == ChessColor.RED) {
            name = "士";
        } else {
            name = "仕";
        }
    }
}
