package chessComponent;
import controller.ClickController;
import model.ChessColor;
import model.ChessboardPoint;

import java.awt.*;

public class HorseChessComponent extends ChessComponent{

    private static int rank=5;
    private static int point=5;
    public static int redBeCaught=0;
    public static int blackBeCaught=0;
    public int getRank() {
        return rank;
    }


    public HorseChessComponent(ChessboardPoint chessboardPoint, Point location, ChessColor chessColor, ClickController clickController, int size,int point) {
        super(chessboardPoint, location, chessColor, clickController, size,point);
        if (this.getChessColor() == ChessColor.RED) {
            name = "马";
        } else {
            name = "馬";
        }
    }

}
