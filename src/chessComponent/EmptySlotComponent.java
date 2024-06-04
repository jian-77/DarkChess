package chessComponent;

import controller.ClickController;
import model.ChessColor;
import model.ChessboardPoint;

import java.awt.*;

/**
 * 这个类表示棋盘上的空棋子的格子
 */
public class EmptySlotComponent extends SquareComponent {
private static int rank=7;

public int getRank(){return  rank;}
    public EmptySlotComponent(ChessboardPoint chessboardPoint, Point location, ClickController listener, int size,int point) {
        super(chessboardPoint, location, ChessColor.NONE, listener, size,point);
    }

    @Override
    public boolean canMoveTo(SquareComponent[][] chessboard, ChessboardPoint destination,SquareComponent squareComponent) {
        return false;
    }

}
