package chessComponent;

import controller.ClickController;
import model.ChessColor;
import model.ChessboardPoint;

import java.awt.*;


public class GeneralChessComponent extends ChessComponent {

    private static int rank=1;
    private static int point=30;
    public static int redBeCaught=0;
    public static int blackBeCaught=0;
    public int getRank() {
        return rank;
    }
    public GeneralChessComponent(ChessboardPoint chessboardPoint, Point location, ChessColor chessColor, ClickController clickController, int size,int point) {
        super(chessboardPoint, location, chessColor, clickController, size,point);
        if (this.getChessColor() == ChessColor.RED) {
            name = "帅";
        } else {
            name = "将";
        }
    }
    public boolean canMoveTo(SquareComponent[][] chessboard, ChessboardPoint destination,SquareComponent squareComponent) {
        SquareComponent destinationChess = chessboard[destination.getX()][destination.getY()];
        if ((Math.abs(this.getChessboardPoint().getX()-destination.getX())+Math.abs(this.getChessboardPoint().getY()-destination.getY()))==1){
            if ((!(squareComponent instanceof SoldierChessComponent) )){
                return destinationChess.isReversal|| destinationChess instanceof EmptySlotComponent;
            }
        }
        return false;
    }



}
