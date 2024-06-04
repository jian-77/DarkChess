package chessComponent;
import controller.ClickController;
import model.ChessColor;
import model.ChessboardPoint;

import java.awt.*;

public class CannonChessComponent extends ChessComponent{
    int rank=5;
    public static int redBeCaught=0;
    public static int blackBeCaught=0;

    @Override
    public int getRank() {
        return rank;
    }

    public CannonChessComponent(ChessboardPoint chessboardPoint, Point location, ChessColor chessColor, ClickController clickController, int size,int point) {
        super(chessboardPoint, location, chessColor, clickController, size,point);
        if (this.getChessColor() == ChessColor.RED) {
            name = "炮";
        } else {
            name = "炮";
        }
    }
    public boolean canMoveTo(SquareComponent[][] chessboard, ChessboardPoint destination,SquareComponent squareComponent) {
        SquareComponent destinationChess = chessboard[destination.getX()][destination.getY()];
        if(!(destinationChess instanceof EmptySlotComponent)) {
            if (this.getChessboardPoint().getX() == destination.getX()) {
                int count = 0;
                for (int i = Math.min(this.getChessboardPoint().getY(), destination.getY()) + 1; i < Math.max(this.getChessboardPoint().getY(), destination.getY()); i++) {
                    if (chessboard[this.getChessboardPoint().getX()][i] instanceof EmptySlotComponent) {
                        continue;
                    } else {
                        count += 1;
                    }
                }
                if (count == 1) {
                    return true;
                } else return false;
            } else if (this.getChessboardPoint().getY() == destination.getY()) {
                int count = 0;
                for (int i = Math.min(this.getChessboardPoint().getX(), destination.getX()) + 1; i < Math.max(this.getChessboardPoint().getX(), destination.getX()); i++) {
                    if (chessboard[i][this.getChessboardPoint().getY()] instanceof EmptySlotComponent) {
                        continue;
                    } else {
                        count += 1;
                    }
                }
                if (count == 1) {
                    return true;
                } else return false;
            }
        }
       return false;
    }


        //return destinationChess.isReversal|| destinationChess instanceof EmptySlotComponent;

        //todo: complete this method
    }

