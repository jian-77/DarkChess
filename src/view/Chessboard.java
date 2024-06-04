package view;



import AI.AI;
import AI.AIClickController;
import Bonus.Undo;
import chessComponent.*;
import model.*;
import controller.ClickController;
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Random;
import controller.GameController;



/**
 * 这个类表示棋盘组建，其包含：
 * SquareComponent[][]: 4*8个方块格子组件
 */
public class Chessboard extends JComponent {

    public int Rremainingpoint=60;
    public int Bremainingpoint=60;
    protected static final int ROW_SIZE = 8;
    protected static final int COL_SIZE = 4;

    public GameController gameController;

    public  ArrayList<Undo>list=new ArrayList<>();



    public SquareComponent[][] getSquareComponents() {
        return squareComponents;
    }

    protected final SquareComponent[][] squareComponents = new SquareComponent[ROW_SIZE][COL_SIZE];

    //todo: you can change the initial player
    protected ChessColor currentColor = ChessColor.BLACK;

    public final ClickController clickController=new ClickController(this);
   
    protected final int CHESS_SIZE;


    public Chessboard(int width, int height) {
        setLayout(null); // Use absolute layout.
        setSize(width+2, height);
        CHESS_SIZE = (height - 6) / 8;
        SquareComponent.setSpacingLength(CHESS_SIZE / 12);
        System.out.printf("chessboard [%d * %d], chess size = %d\n", width, height, CHESS_SIZE);
        initAllChessOnBoard();
    }



    public SquareComponent[][] getChessComponents() {return squareComponents;}

    public ChessColor getCurrentColor() {
        return currentColor;
    }

    public void setCurrentColor(ChessColor currentColor) {
        this.currentColor = currentColor;
    }
    public SquareComponent getSquareComponent(int a,int b) {
         return squareComponents[a][b];
    }
    public ClickController getClickController() {
        return clickController;
    }


    /**
     * 将SquareComponent 放置在 ChessBoard上。里面包含移除原有的component及放置新的component
     * @param squareComponent
     */
    public void putChessOnBoard(SquareComponent squareComponent) {
        int row = squareComponent.getChessboardPoint().getX(), col = squareComponent.getChessboardPoint().getY();
        if (squareComponents[row][col] != null) {
            remove(squareComponents[row][col]);
        }
        add(squareComponents[row][col] = squareComponent);
        repaint();
    }

    /**
     * 交换chess1 chess2的位置
     * @param chess1
     * @param chess2
     */
    public void swapChessComponents(SquareComponent chess1, SquareComponent chess2) {
        // Note that chess1 has higher priority, 'destroys' chess2 if exists.
        if (!(chess2 instanceof EmptySlotComponent)) {
            //被吃的一方减去相应的分数
            if (chess2.getChessColor()==ChessColor.RED){
                Rremainingpoint-=chess2.getPoint();
                if (chess2 instanceof SoldierChessComponent){
                    ((SoldierChessComponent) chess2).redBeCaught+=1;
                    ChessGameFrame.rednumber1.setText("x" +String.valueOf(((SoldierChessComponent) chess2).redBeCaught));
                }
                if (chess2 instanceof CannonChessComponent){
                    ((CannonChessComponent) chess2).redBeCaught+=1;
                    ChessGameFrame.rednumber2.setText("x" +String.valueOf(((CannonChessComponent) chess2).redBeCaught));
                }
                if (chess2 instanceof HorseChessComponent){
                    ((HorseChessComponent) chess2).redBeCaught+=1;
                    ChessGameFrame.rednumber3.setText("x" +String.valueOf(((HorseChessComponent) chess2).redBeCaught));
                }
                if (chess2 instanceof ChariotChessComponent){
                    ((ChariotChessComponent) chess2).redBeCaught+=1;
                    ChessGameFrame.rednumber4.setText("x" +String.valueOf(((ChariotChessComponent) chess2).redBeCaught));
                }
                if (chess2 instanceof MinisterChessComponent){
                    ((MinisterChessComponent) chess2).redBeCaught+=1;
                    ChessGameFrame.rednumber5.setText("x" +String.valueOf(((MinisterChessComponent) chess2).redBeCaught));
                }
                if (chess2 instanceof AdvisorChessComponent){
                    ((AdvisorChessComponent) chess2).redBeCaught+=1;
                    ChessGameFrame.rednumber6.setText("x" +String.valueOf(((AdvisorChessComponent) chess2).redBeCaught));
                }
                if (chess2 instanceof GeneralChessComponent){
                    ((GeneralChessComponent) chess2).redBeCaught+=1;
                    ChessGameFrame.rednumber7.setText("x" +String.valueOf(((GeneralChessComponent) chess2).redBeCaught));
                }
            }
            if (chess2.getChessColor()==ChessColor.BLACK){
                Bremainingpoint-=chess2.getPoint();
                if (chess2 instanceof SoldierChessComponent){
                    ((SoldierChessComponent) chess2).blackBeCaught+=1;
                    ChessGameFrame.blacknumber7.setText(String.valueOf(((SoldierChessComponent) chess2).blackBeCaught)+"x");
                }
                if (chess2 instanceof CannonChessComponent){
                    ((CannonChessComponent) chess2).blackBeCaught+=1;
                    ChessGameFrame.blacknumber6.setText(String.valueOf(((CannonChessComponent) chess2).blackBeCaught)+"x");
                }
                if (chess2 instanceof HorseChessComponent){
                    ((HorseChessComponent) chess2).blackBeCaught+=1;
                    ChessGameFrame.blacknumber5.setText(String.valueOf(((HorseChessComponent) chess2).blackBeCaught)+"x");
                }
                if (chess2 instanceof ChariotChessComponent){
                    ((ChariotChessComponent) chess2).blackBeCaught+=1;
                    ChessGameFrame.blacknumber4.setText(String.valueOf(((ChariotChessComponent) chess2).blackBeCaught)+"x");
                }
                if (chess2 instanceof MinisterChessComponent){
                    ((MinisterChessComponent) chess2).blackBeCaught+=1;
                    ChessGameFrame.blacknumber3.setText(String.valueOf(((MinisterChessComponent) chess2).blackBeCaught)+"x");
                }
                if (chess2 instanceof AdvisorChessComponent){
                    ((AdvisorChessComponent) chess2).blackBeCaught+=1;
                    ChessGameFrame.blacknumber2.setText(String.valueOf(((AdvisorChessComponent) chess2).blackBeCaught)+"x");
                }
                if (chess2 instanceof GeneralChessComponent){
                    ((GeneralChessComponent) chess2).blackBeCaught+=1;
                    ChessGameFrame.blacknumber1.setText(String.valueOf(((GeneralChessComponent) chess2).blackBeCaught)+"x");
                }
            }
            recreatpoint();
            remove(chess2);

            add(chess2 = new EmptySlotComponent(chess2.getChessboardPoint(), chess2.getLocation(), clickController, CHESS_SIZE,chess2.getPoint()));
        }//移到空位置的走法。0
        chess1.swapLocation(chess2);
        int row1 = chess1.getChessboardPoint().getX(), col1 = chess1.getChessboardPoint().getY();
        squareComponents[row1][col1] = chess1;
        int row2 = chess2.getChessboardPoint().getX(), col2 = chess2.getChessboardPoint().getY();
        squareComponents[row2][col2] = chess2;
        //只重新绘制chess1 chess2，其他不变
        chess1.repaint();
        chess2.repaint();
        //赢了之后弹窗
        if (Rremainingpoint<=0){
            JOptionPane.showMessageDialog(null,"BLACK Win");
            initAllChessOnBoard();
            initiallBeCaught();
            Rremainingpoint=60;
            Bremainingpoint=60;
            recreatpoint();
        }
        if (Bremainingpoint<=0){
            JOptionPane.showMessageDialog(null,"RED Win");
            initAllChessOnBoard();
            initiallBeCaught();
            Rremainingpoint=60;
            Bremainingpoint=60;
            recreatpoint();
        }
    }


    //FIXME:   Initialize chessboard for testing only.
    public void initAllChessOnBoard() {
        int number = 0;
        ArrayList<Integer> randomlist = new ArrayList<>(32);
        for (int i = 0; i < squareComponents.length; i++) {
            for (int j = 0; j < squareComponents[i].length; j++) {
                ChessColor color = null;
                Random random1 = new Random();
                while (true) {
                    number = random1.nextInt(32);
                    if (randomlist.contains(number)) {
                        continue;
                    } else {
                        randomlist.add(number);
                        break;
                    }
                }//生成0-31 32个不重复随机数代表32个棋子
                if (number % 2 == 0) {
                    color = ChessColor.RED;//偶数棋子为红色，奇数棋子为黑色
                } else {
                    color = ChessColor.BLACK;
                }
                SquareComponent squareComponent = null;
                if (number >= 0 && number <= 1) {
                    squareComponent = new GeneralChessComponent(new ChessboardPoint(i, j), calculatePoint(i, j), color, clickController, CHESS_SIZE,30);
                }
                if (number>=2&&number<=5){
                    squareComponent=new AdvisorChessComponent(new ChessboardPoint(i, j), calculatePoint(i, j), color, clickController, CHESS_SIZE,10);
                }
                if (number>=6&&number<=9){
                    squareComponent=new MinisterChessComponent(new ChessboardPoint(i, j), calculatePoint(i, j), color, clickController, CHESS_SIZE,5);
                }
                if (number>=10&&number<=13){
                    squareComponent=new ChariotChessComponent(new ChessboardPoint(i, j), calculatePoint(i, j), color, clickController, CHESS_SIZE,5);
                }
                if (number>=14&&number<=17){
                    squareComponent=new HorseChessComponent(new ChessboardPoint(i, j), calculatePoint(i, j), color, clickController, CHESS_SIZE,5);
                }
                if (number>=18&&number<=21){
                    squareComponent=new CannonChessComponent(new ChessboardPoint(i, j), calculatePoint(i, j), color, clickController, CHESS_SIZE,5);
                }
                if (number>=22&&number<=31){
                    squareComponent=new SoldierChessComponent(new ChessboardPoint(i, j), calculatePoint(i, j), color, clickController, CHESS_SIZE,1);
                }
                squareComponent.setVisible(true);
                putChessOnBoard(squareComponent);

            }

        }



    }
    public  void initialGameByCharacters(char[][]chars){
        SquareComponent squareComponent=null;
        for (int i=0;i<squareComponents.length;i++){
            for (int j=0;j<squareComponents[i].length;j++){
                switch (chars[i][j]) {
                    case 'G':
                        squareComponent = new GeneralChessComponent(new ChessboardPoint(i, j), calculatePoint(i, j), ChessColor.RED, clickController, CHESS_SIZE, 30);
                        break;
                    case 'g':
                        squareComponent = new GeneralChessComponent(new ChessboardPoint(i, j), calculatePoint(i, j), ChessColor.BLACK, clickController, CHESS_SIZE, 30);
                        break;
                    case 'M':
                        squareComponent = new MinisterChessComponent(new ChessboardPoint(i, j), calculatePoint(i, j), ChessColor.RED, clickController, CHESS_SIZE, 30);
                        break;
                    case 'm':
                        squareComponent = new MinisterChessComponent(new ChessboardPoint(i, j), calculatePoint(i, j), ChessColor.BLACK, clickController, CHESS_SIZE, 30);
                        break;
                    case 'A':
                        squareComponent = new AdvisorChessComponent(new ChessboardPoint(i, j), calculatePoint(i, j), ChessColor.RED, clickController, CHESS_SIZE, 30);
                        break;
                    case 'a':
                        squareComponent = new AdvisorChessComponent(new ChessboardPoint(i, j), calculatePoint(i, j), ChessColor.BLACK, clickController, CHESS_SIZE, 30);
                        break;
                    case 'H':
                        squareComponent = new HorseChessComponent(new ChessboardPoint(i, j), calculatePoint(i, j), ChessColor.RED, clickController, CHESS_SIZE, 30);
                        break;
                    case 'h':
                        squareComponent = new HorseChessComponent(new ChessboardPoint(i, j), calculatePoint(i, j), ChessColor.BLACK, clickController, CHESS_SIZE, 30);
                        break;
                    case 'P':
                        squareComponent = new CannonChessComponent(new ChessboardPoint(i, j), calculatePoint(i, j), ChessColor.RED, clickController, CHESS_SIZE, 30);
                        break;
                    case 'p':
                        squareComponent = new CannonChessComponent(new ChessboardPoint(i, j), calculatePoint(i, j), ChessColor.BLACK, clickController, CHESS_SIZE, 30);
                        break;
                    case 'C':
                        squareComponent = new ChariotChessComponent(new ChessboardPoint(i, j), calculatePoint(i, j), ChessColor.RED, clickController, CHESS_SIZE, 30);
                        break;
                    case 'c':
                        squareComponent = new ChariotChessComponent(new ChessboardPoint(i, j), calculatePoint(i, j), ChessColor.BLACK, clickController, CHESS_SIZE, 30);
                        break;
                    case 'S':
                        squareComponent = new SoldierChessComponent(new ChessboardPoint(i, j), calculatePoint(i, j), ChessColor.RED, clickController, CHESS_SIZE, 30);
                        break;
                    case 's':
                        squareComponent = new SoldierChessComponent(new ChessboardPoint(i, j), calculatePoint(i, j), ChessColor.BLACK, clickController, CHESS_SIZE, 30);
                        break;
                    case 'K':
                        squareComponent = new EmptySlotComponent(new ChessboardPoint(i, j), calculatePoint(i, j), clickController, CHESS_SIZE, 30);
                        break;
                }
                        squareComponent.setVisible(true);
                        putChessOnBoard(squareComponent);
                }

            }
        if ((chars[8][0]=='R'&&getCurrentColor()==ChessColor.BLACK)||chars[8][0]=='B'&&getCurrentColor()==ChessColor.RED){
            setCurrentColor(getCurrentColor() == ChessColor.BLACK ? ChessColor.RED : ChessColor.BLACK);
            ChessGameFrame.getStatusLabel().setText(String.format("%s's TURN", getCurrentColor().getName()));
        }
        for (int i=9;i<17;i++){
            for (int j=0;j<4;j++) {
                if (chars[i][j]=='1'){
                    getSquareComponent(i-9, j).setReversal(true);
                    getSquareComponent(i-9, j).repaint();
                }
                else {getSquareComponent(i-9, j).setReversal(false);
                getSquareComponent(i-9, j).repaint();}
            }
                }
        SoldierChessComponent.redBeCaught=chars[17][0]-48;
        ChessGameFrame.rednumber1.setText("x" +String.valueOf(SoldierChessComponent.redBeCaught));
        SoldierChessComponent.blackBeCaught=chars[17][1]-48;
        ChessGameFrame.blacknumber7.setText(String.valueOf(SoldierChessComponent.blackBeCaught)+"x");
        CannonChessComponent.redBeCaught=chars[17][2]-48;
        ChessGameFrame.rednumber2.setText("x"+String.valueOf(CannonChessComponent.redBeCaught));
        CannonChessComponent.blackBeCaught=chars[17][3]-48;
        ChessGameFrame.blacknumber6.setText(String.valueOf(CannonChessComponent.blackBeCaught)+"x");
        HorseChessComponent.redBeCaught=chars[18][0]-48;
        ChessGameFrame.rednumber3.setText("x"+String.valueOf(HorseChessComponent.redBeCaught));
        HorseChessComponent.blackBeCaught=chars[18][1]-48;
        ChessGameFrame.blacknumber5.setText(String.valueOf(HorseChessComponent.blackBeCaught+"x"));
        ChariotChessComponent.redBeCaught=chars[18][2]-48;
        ChessGameFrame.rednumber4.setText("x"+String.valueOf(ChariotChessComponent.redBeCaught));
        ChariotChessComponent.blackBeCaught=chars[18][3]-48;
        ChessGameFrame.blacknumber4.setText(String.valueOf(ChariotChessComponent.blackBeCaught+"x"));
        MinisterChessComponent.redBeCaught=chars[19][0]-48;
        ChessGameFrame.rednumber5.setText("x"+String.valueOf(MinisterChessComponent.redBeCaught));
        MinisterChessComponent.blackBeCaught=chars[19][1]-48;
        ChessGameFrame.blacknumber3.setText(String.valueOf(MinisterChessComponent.blackBeCaught)+"x");
        AdvisorChessComponent.redBeCaught=chars[19][2]-48;
        ChessGameFrame.rednumber6.setText("x"+String.valueOf(AdvisorChessComponent.redBeCaught));
        AdvisorChessComponent.blackBeCaught=chars[19][3]-48;
        ChessGameFrame.blacknumber2.setText(String.valueOf(AdvisorChessComponent.blackBeCaught)+"x");
        GeneralChessComponent.redBeCaught=chars[20][0]-48;
        ChessGameFrame.rednumber7.setText("x"+String.valueOf(GeneralChessComponent.redBeCaught));
        GeneralChessComponent.blackBeCaught=chars[20][1]-48;
        ChessGameFrame.blacknumber1.setText(String.valueOf(GeneralChessComponent.blackBeCaught)+"x");

        if (chars[21][0]==0){String s=String.valueOf(chars[21][1]);Rremainingpoint=Integer.parseInt(s);}
        if (chars[21][2]==0){String s=String.valueOf(chars[21][3]);Bremainingpoint=Integer.parseInt(s);}
        if (chars[21][0]!=0){String s=String.valueOf(chars[21][0])+String.valueOf(chars[21][1]);Rremainingpoint=Integer.parseInt(s);}
        if (chars[21][2]!=0){String s=String.valueOf(chars[21][2])+String.valueOf(chars[21][3]);Bremainingpoint=Integer.parseInt(s);}
        recreatpoint();
        }

        public void initiallBeCaught(){
            SoldierChessComponent.redBeCaught=0;
            ChessGameFrame.rednumber1.setText("x" +String.valueOf(SoldierChessComponent.redBeCaught));
            SoldierChessComponent.blackBeCaught=0;
            ChessGameFrame.blacknumber7.setText(String.valueOf(SoldierChessComponent.blackBeCaught)+"x");
            CannonChessComponent.redBeCaught=0;
            ChessGameFrame.rednumber2.setText("x"+String.valueOf(CannonChessComponent.redBeCaught));
            CannonChessComponent.blackBeCaught=0;
            ChessGameFrame.blacknumber6.setText(String.valueOf(CannonChessComponent.blackBeCaught)+"x");
            HorseChessComponent.redBeCaught=0;
            ChessGameFrame.rednumber3.setText("x"+String.valueOf(HorseChessComponent.redBeCaught));
            HorseChessComponent.blackBeCaught=0;
            ChessGameFrame.blacknumber5.setText(String.valueOf(HorseChessComponent.blackBeCaught+"x"));
            ChariotChessComponent.redBeCaught=0;
            ChessGameFrame.rednumber4.setText("x"+String.valueOf(ChariotChessComponent.redBeCaught));
            ChariotChessComponent.blackBeCaught=0;
            ChessGameFrame.blacknumber4.setText(String.valueOf(ChariotChessComponent.blackBeCaught+"x"));
            MinisterChessComponent.redBeCaught=0;
            ChessGameFrame.rednumber5.setText("x"+String.valueOf(MinisterChessComponent.redBeCaught));
            MinisterChessComponent.blackBeCaught=0;
            ChessGameFrame.blacknumber3.setText(String.valueOf(MinisterChessComponent.blackBeCaught)+"x");
            AdvisorChessComponent.redBeCaught=0;
            ChessGameFrame.rednumber6.setText("x"+String.valueOf(AdvisorChessComponent.redBeCaught));
            AdvisorChessComponent.blackBeCaught=0;
            ChessGameFrame.blacknumber2.setText(String.valueOf(AdvisorChessComponent.blackBeCaught)+"x");
            GeneralChessComponent.redBeCaught=0;
            ChessGameFrame.rednumber7.setText("x"+String.valueOf(GeneralChessComponent.redBeCaught));
            GeneralChessComponent.blackBeCaught=0;
            ChessGameFrame.blacknumber1.setText(String.valueOf(GeneralChessComponent.blackBeCaught)+"x");
        }





    /**
     * 绘制棋盘格子
     * @param g
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.fillRect(0, 0, this.getWidth(), this.getHeight());
        ((Graphics2D) g).setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
    }

    /**
     * 将棋盘上行列坐标映射成Swing组件的Point
     * @param row 棋盘上的行
     * @param col 棋盘上的列
     * @return
     */
    public Point calculatePoint(int row, int col) {
        return new Point(col * CHESS_SIZE + 3, row * CHESS_SIZE + 3);
    }

    /**
     * 通过GameController调用该方法
     * 转换成8*4的数组
     * @param
     */

    public void recreatpoint () {
        ChessGameFrame.getStatusLabel2().setText(String.format("RED's remaining point is %d",Rremainingpoint));
        ChessGameFrame.getStatusLabel3().setText(String.format("BLACK's remaining point is %d",Bremainingpoint));
    }
}
