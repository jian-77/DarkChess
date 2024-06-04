package chessComponent;

import controller.ClickController;
import model.ChessColor;
import model.ChessboardPoint;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;

/**
 * 这个类是一个抽象类，主要表示8*4棋盘上每个格子的棋子情况。
 * 有两个子类：
 * 1. EmptySlotComponent: 空棋子
 * 2. ChessComponent: 表示非空棋子
 */
public abstract class SquareComponent extends JComponent {
    private int rank;
    private static final Color squareColor = new Color(250, 220, 190);
    protected static int spacingLength;
    protected static final Font CHESS_FONT = new Font("宋体", Font.BOLD, 36);

    /**
     * chessboardPoint: 表示8*4棋盘中，当前棋子在棋格对应的位置，如(0, 0), (1, 0)等等
     * chessColor: 表示这个棋子的颜色，有红色，黑色，无色三种
     * isReversal: 表示是否翻转
     * selected: 表示这个棋子是否被选中
     */
    private ChessboardPoint chessboardPoint;
    protected final ChessColor chessColor;
    protected boolean isReversal;
    private boolean selected;
    private int point;
    public boolean canMoveTo;


    private boolean isClicked;



    private static boolean cheetingmode;

    /**
     * handle click event
     */
    public final ClickController clickController;


    protected SquareComponent(ChessboardPoint chessboardPoint, Point location, ChessColor chessColor, ClickController clickController, int size, int point) {
        enableEvents(AWTEvent.MOUSE_EVENT_MASK);
        setLocation(location);
        setSize(size, size);
        this.chessboardPoint = chessboardPoint;
        this.chessColor = chessColor;
        this.selected = false;
        this.clickController = clickController;
        this.isReversal = false;
        this.point=point;
    }
    public int getRank(){return getRank();}
    public boolean isReversal() {
        return isReversal;
    }

    public void setReversal(boolean reversal) {
        isReversal = reversal;
    }

    public static void setSpacingLength(int spacingLength) {
        SquareComponent.spacingLength = spacingLength;
    }

    public ChessboardPoint getChessboardPoint() {
        return chessboardPoint;
    }

    public void setChessboardPoint(ChessboardPoint chessboardPoint) {
        this.chessboardPoint = chessboardPoint;
    }

    public ChessColor getChessColor() {
        return chessColor;
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }
    public int getPoint() {return point;}

    public static void setCheetingmode(boolean b) {
       cheetingmode=b;
    }
    public  static boolean getCheetingmode() {
        return cheetingmode;
    }
    public boolean isClicked() {
        return isClicked;
    }

    public void setClicked(boolean clicked) {
        isClicked = clicked;
    }


    /**
     * @param another 主要用于和另外一个棋子交换位置
     *                <br>
     *                调用时机是在移动棋子的时候，将操控的棋子和对应的空位置棋子(EmptySlotComponent)做交换
     */
    public void swapLocation(SquareComponent another) {
        ChessboardPoint chessboardPoint1 = getChessboardPoint(), chessboardPoint2 = another.getChessboardPoint();
        Point point1 = getLocation(), point2 = another.getLocation();
        setChessboardPoint(chessboardPoint2);
        setLocation(point2);
        another.setChessboardPoint(chessboardPoint1);
        another.setLocation(point1);
    }

    /**
     * @param e 响应鼠标监听事件
     *          <br>
     *          当接收到鼠标动作的时候，这个方法就会自动被调用，调用监听者的onClick方法，处理棋子的选中，移动等等行为。
     */
    @Override
    protected void processMouseEvent(MouseEvent e) {//只要鼠标点击就会使用该方法
        super.processMouseEvent(e);
        if (e.getID() == MouseEvent.MOUSE_PRESSED) {
            System.out.printf("Click [%d,%d]\n", chessboardPoint.getX(), chessboardPoint.getY());//给出坐标
            clickController.onClick(this);
        }
        if (this.getCheetingmode() == true) {
            if (this.isClicked==false) {
                if (e.getID() == MouseEvent.MOUSE_ENTERED) {
                    this.setReversal(true);
                   repaint();
                }
                if (e.getID() == MouseEvent.MOUSE_EXITED) {
                    this.setReversal(false);
                    repaint();
                }
            }
        }
    }


    /**
     * @param chessboard  棋盘
     * @param destination 目标位置，如(0, 0), (0, 1)等等
     * @return this棋子对象的移动规则和当前位置(chessboardPoint)能否到达目标位置
     * <br>
     * 这个方法主要是检查移动的合法性，如果合法就返回true，反之是false。
     */
    //todo: Override this method for Cannon
    public boolean canMoveTo(SquareComponent[][] chessboard, ChessboardPoint destination,SquareComponent squareComponent) {
        SquareComponent destinationChess = chessboard[destination.getX()][destination.getY()];
        if ((Math.abs(this.getChessboardPoint().getX()-destination.getX())+Math.abs(this.getChessboardPoint().getY()-destination.getY()))==1){
            if (this.getRank()<=squareComponent.getRank()){
                return destinationChess.isReversal|| destinationChess instanceof EmptySlotComponent;
            }
        }
        return false;
        //todo: complete this method
    }

@Override
    protected void paintComponent(Graphics g) {
        super.paintComponents(g);
        System.out.printf("repaint chess [%d,%d]\n", chessboardPoint.getX(), chessboardPoint.getY());
        g.setColor(squareColor);
        g.fillRect(1, 1, this.getWidth() - 2, this.getHeight() - 2);

        if (canMoveTo){
            g.setColor(Color.BLACK);
            Graphics2D g2 = (Graphics2D) g;
            g2.setStroke(new BasicStroke(4f));
            g2.drawOval(spacingLength, spacingLength, getWidth() - 2 * spacingLength, getHeight() - 2 * spacingLength);
        }
    }

}
