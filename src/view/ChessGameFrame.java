package view;

import Bonus.Undo;
import chessComponent.*;
import controller.GameController;
import Bonus.CountDown;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * 这个类表示游戏窗体，窗体上包含：
 * 1 Chessboard: 棋盘
 * 2 JLabel:  标签
 * 3 JButton： 按钮
 */
public class ChessGameFrame extends JFrame {
    protected final int WIDTH;
   protected final int HEIGHT;
    protected final int CHESSBOARD_SIZE;
    protected GameController gameController;
    protected Chessboard chessboard;
    protected static JLabel statusLabel;
    protected static JLabel statusLabel2;
    protected static JLabel statusLabel3;

    protected static JLabel statusLabel4;
    protected  static  JLabel redlabel1;
    protected  static  JLabel redlabel2;
    protected  static  JLabel redlabel3;
    protected  static  JLabel redlabel4;
    protected  static  JLabel redlabel5;
    protected  static  JLabel redlabel6;
    protected  static  JLabel redlabel7;
    protected  static  JLabel blacklabel1;
    protected  static  JLabel blacklabel2;
    protected  static  JLabel blacklabel3;
    protected  static  JLabel blacklabel4;
    protected  static  JLabel blacklabel5;
    protected  static  JLabel blacklabel6;
    protected  static  JLabel blacklabel7;
    public   static  JLabel rednumber1;
    public   static  JLabel rednumber2;
    public   static  JLabel rednumber3;
    public   static  JLabel rednumber4;
    public   static  JLabel rednumber5;
    public   static  JLabel rednumber6;
    public   static  JLabel rednumber7;
    public   static  JLabel blacknumber1;
    public   static  JLabel blacknumber2;
    public   static  JLabel blacknumber3;
    public   static  JLabel blacknumber4;
    public   static  JLabel blacknumber5;
    public   static  JLabel blacknumber6;
    public   static  JLabel blacknumber7;

    protected JLayeredPane layeredPane = new JLayeredPane();
    protected  BackgroundPic backgroundPic = new BackgroundPic((new ImageIcon("src/backgroundPic/img1.png")).getImage());

    public ChessGameFrame(int width, int height) {

        setTitle("2022 CS109 Project Demo"); //设置标题
        this.WIDTH = width;
        this.HEIGHT = height;
        this.CHESSBOARD_SIZE = HEIGHT * 4 / 5;
        this.setLayeredPane(layeredPane);

        setSize(WIDTH, HEIGHT);
        setLocationRelativeTo(null); // Center the window.
        getContentPane().setBackground(Color.WHITE);//窗体背景  这一块可以美化//Color. WHITE
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE); //设置程序关闭按键，如果点击右上方的叉就游戏全部关闭了
        setLayout(null);
        /*JPanel panel = (JPanel) getContentPane();
        panel.setOpaque(false);*/


        //addChangeTheme();
        //addBackgroundPic();
        addBackgroundPic1();
        addChessboard();
        addLabel();
        addCountDownButton();
        addLoadButton();
        addLabel2();
        addLabel3();
        addSaveGameButton();
        addRestartButton();
        changeBackgroundPic();
        addLabel4();
        addredlabel1();
        addCheetModeButton();
        addUndoButton();
        addredlabel1();
        addredlabel2();
        addredlabel3();
        addredlabel4();
        addredlabel5();
        addredlabel6();
        addredlabel7();
        addblacklabel1();
        addblacklabel2();
        addblacklabel3();
        addblacklabel4();
        addblacklabel5();
        addblacklabel6();
        addblacklabel7();
        addreplaybutton();
        addReturnButton();
        /*ImageIcon icon = new ImageIcon("C:\\Users\\LuoXinyi\\IdeaProjects\\Project\\src\\backgroundPic\\img3.png");
        JLabel label = new JLabel(icon);
        label.setBounds(0,0,WIDTH,HEIGHT);
        getLayeredPane().add(label);*/


    }

    public void changeBackgroundPic(){
        JComboBox <String> comboBox = new JComboBox<>();
        ArrayList<String> themeList = new ArrayList<String>();
        themeList.add("sky night");
        themeList.add("garden");
        themeList.add("tea pot");
        comboBox.addItem(themeList.get(0));
        comboBox.addItem(themeList.get(1));
        comboBox.addItem(themeList.get(2));
        comboBox.setLocation(WIDTH * 3 / 5-20, HEIGHT / 10+520);
        comboBox.setSize(200, 30);
        comboBox.setFont(new Font("Rockwell", Font.BOLD, 20));
        comboBox.setEditable(true);
        layeredPane.add(comboBox,JLayeredPane.MODAL_LAYER);


        //注册监听
        comboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println(comboBox.getSelectedItem());
                if (comboBox.getSelectedItem().equals("tea pot")){
                    addBackgroundPic3();
                } else if (comboBox.getSelectedItem().equals("sky night")){
                    addBackgroundPic1();
                    statusLabel.setForeground(Color.GREEN);
                    statusLabel2.setForeground(Color.MAGENTA);
                    statusLabel3.setForeground(Color.WHITE);
                    statusLabel4.setForeground(Color.MAGENTA);
                    redlabel1.setForeground(Color.MAGENTA);
                    redlabel2.setForeground(Color.MAGENTA);
                    redlabel3.setForeground(Color.MAGENTA);
                    redlabel4.setForeground(Color.MAGENTA);
                    redlabel5.setForeground(Color.MAGENTA);
                    redlabel6.setForeground(Color.MAGENTA);
                    redlabel7.setForeground(Color.MAGENTA);
                    blacklabel1.setForeground(Color.WHITE);
                    blacklabel2.setForeground(Color.WHITE);
                    blacklabel3.setForeground(Color.WHITE);
                    blacklabel4.setForeground(Color.WHITE);
                    blacklabel5.setForeground(Color.WHITE);
                    blacklabel6.setForeground(Color.WHITE);
                    blacklabel7.setForeground(Color.WHITE);
                } else if (comboBox.getSelectedItem().equals("garden")){
                    addBackgroundPic2();
                }
            }
            //src/backgroundPic/img"++".png

        });
    }

    public void addBackgroundPic2(){
        layeredPane.remove(backgroundPic);
        backgroundPic.setImg((new ImageIcon("resources/img2.png")).getImage());
        backgroundPic.setBounds(0,0,WIDTH,HEIGHT);
        layeredPane.add(backgroundPic, JLayeredPane.DEFAULT_LAYER);
    }

    public void addBackgroundPic3(){
        layeredPane.remove(backgroundPic);
        backgroundPic.setImg((new ImageIcon("resources/img3.png")).getImage());
        backgroundPic.setBounds(0,0,WIDTH,HEIGHT);
        layeredPane.add(backgroundPic, JLayeredPane.DEFAULT_LAYER);}


    public void addBackgroundPic1(){
        layeredPane.remove(backgroundPic);
        backgroundPic.setImg((new ImageIcon("resources/img1.png")).getImage());
        backgroundPic.setBounds(0,0,WIDTH,HEIGHT);
        layeredPane.add(backgroundPic, JLayeredPane.DEFAULT_LAYER);

    }

    //public void addChangeTheme(){}

    /**
     * 在游戏窗体中添加棋盘
     */
    protected void addChessboard() {
        chessboard = new Chessboard(CHESSBOARD_SIZE / 2, CHESSBOARD_SIZE);;//相对大小，改变窗体大小就会等比改变棋盘大小
        gameController = new GameController(chessboard);
        chessboard.gameController=gameController;
        Undo.chessboard=chessboard;
        CountDown.chessboard=chessboard;////新加的方法；
        chessboard.setLocation(HEIGHT / 10, HEIGHT / 10);//棋盘位置
        layeredPane.add(chessboard,JLayeredPane.MODAL_LAYER);//用awt的方法把棋盘加载到窗体上
    }

    /**
     * 在游戏窗体中添加标签
     */
    protected void addLabel() {
        statusLabel = new JLabel("BLACK's TURN");
        statusLabel.setLocation(WIDTH * 3 / 5, HEIGHT / 10);
        statusLabel.setSize(200, 60);
        statusLabel.setFont(new Font("Rockwell", Font.BOLD, 20));
        layeredPane.add(statusLabel,JLayeredPane.MODAL_LAYER);
    }

    public static JLabel getStatusLabel() {
        return statusLabel;
    }
    public static JLabel getStatusLabel2() {
        return statusLabel2;
    }

    public static JLabel getStatusLabel3() {
        return statusLabel3;
    }
    public static JLabel getStatusLabel4() {
        return statusLabel4;
    }


    /**
     * 在游戏窗体中增加一个按钮，如果按下的话就会显示Hello, world!
     */
//通过点击button触发的事件都可以放在gamecontroller中
    protected void addCountDownButton() {
        JButton button = new JButton("start countdown");
        button.setLocation(WIDTH * 3 / 5, HEIGHT / 10 + 90);
        button.setSize(170, 50);
        button.setFont(new Font("Rockwell", Font.BOLD, 15));
        layeredPane.add(button,JLayeredPane.MODAL_LAYER);
        AtomicBoolean press= new AtomicBoolean(false);
        button.addActionListener((e) -> {
           if (press.get() ==false) {
               CountDown.mode = true;
               CountDown.startCountDown();
               press.set(true);
           }
           else {
               CountDown.mode=false;
               press.set(false);
           }

        });
    }

// 点击按钮触发事件
    protected void addLoadButton() {
        JButton button = new JButton("Load");
        button.setLocation(WIDTH * 3 / 5, HEIGHT / 10 + 210);
        button.setSize(170, 50);
        button.setFont(new Font("Rockwell", Font.BOLD, 15));
        button.setBackground(Color.LIGHT_GRAY);
        layeredPane.add(button,JLayeredPane.MODAL_LAYER);

        button.addActionListener(e -> {//设置触发事件
            System.out.println("Click load");
            JFileChooser fileChooser = new JFileChooser("C:/Users/见/Documents/test");
            int result=fileChooser.showOpenDialog(this);
            if (result!=JFileChooser.APPROVE_OPTION){
                return;
            }
            File file=fileChooser.getSelectedFile();
            int index=file.getName().lastIndexOf(".");
            String filename=file.getName();
            String extention=filename.substring(index+1).toLowerCase();
            if (extention.equals("txt")) {
                int line=0;
                int n=0;
                int chessError=0;
                String content="";
                try {
                    FileReader fileReader= new FileReader(file);
                    BufferedReader reader=new BufferedReader(fileReader);
                    while ((content=reader.readLine())!=null){
                        line++;
                        String[] strings=content.split(",");
                        if (line>=1&&line<=8){/////////////////////讨论1-8行的情况
                            if (strings.length!=4) {
                                JOptionPane.showMessageDialog(this, "Incorrect Chessboard!");
                                n+=1;
                                break;
                            }
                            for (String s:strings){
                                String[] strings1={"p","P","m","M","s","S","h","H","c","C","g","G","a","A","K"};
                                for (int i=0;i<strings1.length;i++){
                                    if (s.equals(strings1[i])){chessError++;}
                                }
                            }
                        }/////////////////////////////////////
                        if (chessError%4!=0){JOptionPane.showMessageDialog(this, "Incorrect ChessComponent!");break;}
                       if (line==9){/////////讨论第九行的情况
                           if (!(strings[0].equals("R"))&&!(strings[0].equals("B"))){n+=1;JOptionPane.showMessageDialog(this, "Lack of players");break;}
                       }/////////////////
                    }
                    if (n==0&&chessError==32){
                        String path = file.getAbsolutePath();
                        chessboard.initialGameByCharacters(gameController.loadGameFromFile(path));
                        chessboard.list=gameController.loadUndo(path);
                        System.out.println(chessboard.list.size());
                        repaint();
                    }
                } catch (FileNotFoundException ex) {
                    throw new RuntimeException(ex);
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }

            }
            else JOptionPane.showMessageDialog(this, "Incorrect format!");
        });

    }

    protected void addRestartButton(){
        JButton button = new JButton("Restart");
        button.setLocation(WIDTH * 3 / 5, HEIGHT / 10 + 330 );
        button.setSize(90,60 );
        button.setFont(new Font("Rockwell", Font.BOLD, 15));
        layeredPane.add(button,JLayeredPane.MODAL_LAYER);


        button.addActionListener(e -> {
            CountDown.mode=false;
            CountDown.count[0]=15;
            this.getStatusLabel4().setText(String.format("倒计时:%d", CountDown.count[0]));
            chessboard.initAllChessOnBoard();
            for (int i=chessboard.list.size()-1;i>=0;i--){
                chessboard.list.remove(i);
            }
            chessboard.initiallBeCaught();

        });

    }

    protected void addSaveGameButton() {
        JButton button = new JButton("Save");
        button.setLocation(WIDTH * 3 / 5 + 100, HEIGHT / 10 +330);
        button.setSize(90, 60);
        button.setFont(new Font("Rockwell", Font.BOLD, 15));
        button.setBackground(Color.LIGHT_GRAY);
        layeredPane.add(button,JLayeredPane.MODAL_LAYER);

        button.addActionListener(e -> {//设置触发事件
            System.out.println("Click load");
            //String path = JOptionPane.showInputDialog(this, "Input Path here");
            gameController.saveGame(chessboard.getSquareComponents());
        });

    }
    protected void addLabel2(){
        statusLabel2= new JLabel("RED's remaining point is 60");
        statusLabel2.setLocation(WIDTH * 3 / 5-30, HEIGHT / 10+460);
        statusLabel2.setSize(400, 60);
        statusLabel2.setFont(new Font("Rockwell", Font.BOLD, 20));
        layeredPane.add(statusLabel2,JLayeredPane.MODAL_LAYER);
    }
    protected void addLabel3(){
        statusLabel3 = new JLabel("BLACK's remaining point is 60");
        statusLabel3.setLocation(WIDTH * 3 / 5-45, HEIGHT / 10+430);
        statusLabel3.setSize(400, 60);
        statusLabel3.setFont(new Font("Rockwell", Font.BOLD, 20));
        layeredPane.add(statusLabel3,JLayeredPane.MODAL_LAYER);
    }
    protected void addLabel4() {
        statusLabel4 = new JLabel("倒计时:15");
        statusLabel4.setLocation(WIDTH * 3 / 5, HEIGHT / 10 + 40);
        statusLabel4.setSize(200, 60);
        statusLabel4.setFont(new Font("宋体", Font.BOLD, 30));
        layeredPane.add(statusLabel4,JLayeredPane.MODAL_LAYER);
    }
    protected void addredlabel1(){
        redlabel1=new JLabel("兵");
        redlabel1.setLocation(WIDTH * 3 / 5-110, HEIGHT / 10 + 90);
        redlabel1.setSize(60,60);
        redlabel1.setFont(new Font("宋体", Font.BOLD, 30));
        layeredPane.add(redlabel1,JLayeredPane.MODAL_LAYER);
        redlabel1.setForeground(Color.MAGENTA);

        rednumber1=new JLabel("x0");
        rednumber1.setLocation(WIDTH * 3 / 5-80, HEIGHT / 10 + 90);
        rednumber1.setSize(60,60);
        rednumber1.setFont(new Font("Times New Roman", Font.BOLD, 30));
        layeredPane.add(rednumber1,JLayeredPane.MODAL_LAYER);
        rednumber1.setForeground(Color.MAGENTA);

    }
    protected void addredlabel2(){
        redlabel2=new JLabel("炮");
        redlabel2.setLocation(WIDTH * 3 / 5-110, HEIGHT / 10 + 160);
        redlabel2.setSize(60,60);
        redlabel2.setFont(new Font("宋体", Font.BOLD, 30));
        layeredPane.add(redlabel2,JLayeredPane.MODAL_LAYER);
        redlabel2.setForeground(Color.MAGENTA);

        rednumber2=new JLabel("x0");
        rednumber2.setLocation(WIDTH * 3 / 5-80, HEIGHT / 10 + 160);
        rednumber2.setSize(60,60);
        rednumber2.setFont(new Font("Times New Roman", Font.BOLD, 30));
        layeredPane.add(rednumber2,JLayeredPane.MODAL_LAYER);
        rednumber2.setForeground(Color.MAGENTA);
    }
    protected void addredlabel3(){
        redlabel3=new JLabel("马");
        redlabel3.setLocation(WIDTH * 3 / 5-110, HEIGHT / 10 + 230);
        redlabel3.setSize(60,60);
        redlabel3.setFont(new Font("宋体", Font.BOLD, 30));
        layeredPane.add(redlabel3,JLayeredPane.MODAL_LAYER);
        redlabel3.setForeground(Color.MAGENTA);

        rednumber3=new JLabel("x0");
        rednumber3.setLocation(WIDTH * 3 / 5-80, HEIGHT / 10 + 230);
        rednumber3.setSize(60,60);
        rednumber3.setFont(new Font("Times New Roman", Font.BOLD, 30));
        layeredPane.add(rednumber3,JLayeredPane.MODAL_LAYER);
        rednumber3.setForeground(Color.MAGENTA);
    }
    protected void addredlabel4(){
        redlabel4=new JLabel("车");
        redlabel4.setLocation(WIDTH * 3 / 5-110, HEIGHT / 10 +300);
        redlabel4.setSize(60,60);
        redlabel4.setFont(new Font("宋体", Font.BOLD, 30));
        layeredPane.add(redlabel4,JLayeredPane.MODAL_LAYER);
        redlabel4.setForeground(Color.MAGENTA);

        rednumber4=new JLabel("x0");
        rednumber4.setLocation(WIDTH * 3 / 5-80, HEIGHT / 10 + 300);
        rednumber4.setSize(60,60);
        rednumber4.setFont(new Font("Times New Roman", Font.BOLD, 30));
        layeredPane.add(rednumber4,JLayeredPane.MODAL_LAYER);
        rednumber4.setForeground(Color.MAGENTA);
    }
    protected void addredlabel5(){
        redlabel5=new JLabel("相");
        redlabel5.setLocation(WIDTH * 3 / 5-110, HEIGHT / 10 + 370);
        redlabel5.setSize(60,60);
        redlabel5.setFont(new Font("宋体", Font.BOLD, 30));
        layeredPane.add(redlabel5,JLayeredPane.MODAL_LAYER);
        redlabel5.setForeground(Color.MAGENTA);

        rednumber5=new JLabel("x0");
        rednumber5.setLocation(WIDTH * 3 / 5-80, HEIGHT / 10 + 370);
        rednumber5.setSize(60,60);
        rednumber5.setFont(new Font("Times New Roman", Font.BOLD, 30));
        layeredPane.add(rednumber5,JLayeredPane.MODAL_LAYER);
        rednumber5.setForeground(Color.MAGENTA);
    }
    protected void addredlabel6() {
        redlabel6 = new JLabel("仕");
        redlabel6.setLocation(WIDTH * 3 / 5 - 110, HEIGHT / 10 + 440);
        redlabel6.setSize(60, 60);
        redlabel6.setFont(new Font("宋体", Font.BOLD, 30));
        layeredPane.add(redlabel6, JLayeredPane.MODAL_LAYER);
        redlabel6.setForeground(Color.MAGENTA);

        rednumber6=new JLabel("x0");
        rednumber6.setLocation(WIDTH * 3 / 5-80, HEIGHT / 10 + 440);
        rednumber6.setSize(60,60);
        rednumber6.setFont(new Font("Times New Roman", Font.BOLD, 30));
        layeredPane.add(rednumber6,JLayeredPane.MODAL_LAYER);
        rednumber6.setForeground(Color.MAGENTA);
    }
    protected void addredlabel7(){
        redlabel7=new JLabel("帅");
        redlabel7.setLocation(WIDTH * 3 / 5-110, HEIGHT / 10 +510);
        redlabel7.setSize(60,60);
        redlabel7.setFont(new Font("宋体", Font.BOLD, 30));
        layeredPane.add(redlabel7,JLayeredPane.MODAL_LAYER);
        redlabel7.setForeground(Color.MAGENTA);

        rednumber7=new JLabel("x0");
        rednumber7.setLocation(WIDTH * 3 / 5-80, HEIGHT / 10 + 510);
        rednumber7.setSize(60,60);
        rednumber7.setFont(new Font("Times New Roman", Font.BOLD, 30));
        layeredPane.add(rednumber7,JLayeredPane.MODAL_LAYER);
        rednumber7.setForeground(Color.MAGENTA);
    }
    protected void addblacklabel1(){
        blacklabel1=new JLabel("将");
        blacklabel1.setLocation(WIDTH * 3 / 5-440, HEIGHT / 10 +90);
        blacklabel1.setSize(60,60);
        blacklabel1.setFont(new Font("宋体", Font.BOLD, 30));
        layeredPane.add(blacklabel1,JLayeredPane.MODAL_LAYER);
        blacklabel1.setForeground(Color.MAGENTA);

        blacknumber1=new JLabel("0x");
        blacknumber1.setLocation(WIDTH * 3 / 5-470, HEIGHT / 10 + 90);
        blacknumber1.setSize(60,60);
        blacknumber1.setFont(new Font("Times New Roman", Font.BOLD, 30));
        layeredPane.add(blacknumber1,JLayeredPane.MODAL_LAYER);
        blacknumber1.setForeground(Color.MAGENTA);
    }
    protected void addblacklabel2(){
        blacklabel2=new JLabel("士");
        blacklabel2.setLocation(WIDTH * 3 / 5-440, HEIGHT / 10 +160);
        blacklabel2.setSize(60,60);
        blacklabel2.setFont(new Font("宋体", Font.BOLD, 30));
        layeredPane.add(blacklabel2,JLayeredPane.MODAL_LAYER);
        blacklabel2.setForeground(Color.MAGENTA);

        blacknumber2=new JLabel("0x");
        blacknumber2.setLocation(WIDTH * 3 / 5-470, HEIGHT / 10 + 160);
        blacknumber2.setSize(60,60);
        blacknumber2.setFont(new Font("Times New Roman", Font.BOLD, 30));
        layeredPane.add(blacknumber2,JLayeredPane.MODAL_LAYER);
        blacknumber2.setForeground(Color.MAGENTA);
    }
    protected void addblacklabel3(){
        blacklabel3=new JLabel("象");
        blacklabel3.setLocation(WIDTH * 3 / 5-440, HEIGHT / 10 +230);
        blacklabel3.setSize(60,60);
        blacklabel3.setFont(new Font("宋体", Font.BOLD, 30));
        layeredPane.add(blacklabel3,JLayeredPane.MODAL_LAYER);
        blacklabel3.setForeground(Color.MAGENTA);

        blacknumber3=new JLabel("0x");
        blacknumber3.setLocation(WIDTH * 3 / 5-470, HEIGHT / 10 + 230);
        blacknumber3.setSize(60,60);
        blacknumber3.setFont(new Font("Times New Roman", Font.BOLD, 30));
        layeredPane.add(blacknumber3,JLayeredPane.MODAL_LAYER);
        blacknumber3.setForeground(Color.MAGENTA);
    }
    protected void addblacklabel4(){
        blacklabel4=new JLabel("車");
        blacklabel4.setLocation(WIDTH * 3 / 5-440, HEIGHT / 10 +300);
        blacklabel4.setSize(60,60);
        blacklabel4.setFont(new Font("宋体", Font.BOLD, 30));
        layeredPane.add(blacklabel4,JLayeredPane.MODAL_LAYER);
        blacklabel4.setForeground(Color.MAGENTA);

        blacknumber4=new JLabel("0x");
        blacknumber4.setLocation(WIDTH * 3 / 5-470, HEIGHT / 10 + 300);
        blacknumber4.setSize(60,60);
        blacknumber4.setFont(new Font("Times New Roman", Font.BOLD, 30));
        layeredPane.add(blacknumber4,JLayeredPane.MODAL_LAYER);
        blacknumber4.setForeground(Color.MAGENTA);
    }
    protected void addblacklabel5(){
        blacklabel5=new JLabel("马");
        blacklabel5.setLocation(WIDTH * 3 / 5-440, HEIGHT / 10 +370);
        blacklabel5.setSize(60,60);
        blacklabel5.setFont(new Font("宋体", Font.BOLD, 30));
        layeredPane.add(blacklabel5,JLayeredPane.MODAL_LAYER);
        blacklabel5.setForeground(Color.MAGENTA);

        blacknumber5=new JLabel("0x");
        blacknumber5.setLocation(WIDTH * 3 / 5-470, HEIGHT / 10 + 370);
        blacknumber5.setSize(60,60);
        blacknumber5.setFont(new Font("Times New Roman", Font.BOLD, 30));
        layeredPane.add(blacknumber5,JLayeredPane.MODAL_LAYER);
        blacknumber5.setForeground(Color.MAGENTA);
    }
    protected void addblacklabel6(){
        blacklabel6=new JLabel("炮");
        blacklabel6.setLocation(WIDTH * 3 / 5-440, HEIGHT / 10 +440);
        blacklabel6.setSize(60,60);
        blacklabel6.setFont(new Font("宋体", Font.BOLD, 30));
        layeredPane.add(blacklabel6,JLayeredPane.MODAL_LAYER);
        blacklabel6.setForeground(Color.MAGENTA);

        blacknumber6=new JLabel("0x");
        blacknumber6.setLocation(WIDTH * 3 / 5-470, HEIGHT / 10 + 440);
        blacknumber6.setSize(60,60);
        blacknumber6.setFont(new Font("Times New Roman", Font.BOLD, 30));
        layeredPane.add(blacknumber6,JLayeredPane.MODAL_LAYER);
        blacknumber6.setForeground(Color.MAGENTA);
    }
    protected void addblacklabel7(){
        blacklabel7=new JLabel("卒");
        blacklabel7.setLocation(WIDTH * 3 / 5-440, HEIGHT / 10 +510);
        blacklabel7.setSize(60,60);
        blacklabel7.setFont(new Font("宋体", Font.BOLD, 30));
        layeredPane.add(blacklabel7,JLayeredPane.MODAL_LAYER);
        blacklabel7.setForeground(Color.MAGENTA);

        blacknumber7=new JLabel("0x");
        blacknumber7.setLocation(WIDTH * 3 / 5-470, HEIGHT / 10 + 510);
        blacknumber7.setSize(60,60);
        blacknumber7.setFont(new Font("Times New Roman", Font.BOLD, 30));
        layeredPane.add(blacknumber7,JLayeredPane.MODAL_LAYER);
        blacknumber7.setForeground(Color.MAGENTA);
    }

    private void addCheetModeButton() {
        JButton button = new JButton("Cheeting Mode");
        button.setLocation(WIDTH * 3 / 5, HEIGHT / 10 + 150);
        button.setSize(170, 50);
        button.setFont(new Font("Rockwell", Font.BOLD, 15));
        add(button);
        layeredPane.add(button,JLayeredPane.MODAL_LAYER);

        button.addActionListener(e -> {//设置触发事件
            if (SquareComponent.getCheetingmode()==false){
                SquareComponent.setCheetingmode(true);
                JOptionPane.showMessageDialog(this,"Cheeting Mode is On");}
            else{
                SquareComponent.setCheetingmode(false);
                JOptionPane.showMessageDialog(this,"Cheeting Mode is Off");
            }
        });
    }
    private void addUndoButton(){
        JButton button = new JButton("Undo");
        button.setLocation(WIDTH * 3 / 5, HEIGHT / 10 + 270);
        button.setSize(170, 50);
        button.setFont(new Font("Rockwell", Font.BOLD, 20));
        add(button);
        layeredPane.add(button,JLayeredPane.MODAL_LAYER);

        button.addActionListener(e -> {//设置触发事件
            if (chessboard.list.size()==1){
                JOptionPane.showMessageDialog(this,"已经是第一步棋了");}
            else{
                Undo.undo();
            }
        });
}
    private void addreplaybutton(){
        JButton button = new JButton("replay");
        button.setLocation(WIDTH * 3 / 5, HEIGHT / 10 + 390);
        button.setSize(170, 50);
        button.setFont(new Font("Rockwell", Font.BOLD, 20));
        add(button);
        layeredPane.add(button,JLayeredPane.MODAL_LAYER);

        button.addActionListener(e -> {//设置触发事件
           Undo.replay();
        });
    }


    private void addReturnButton(){
        JButton button = new JButton("Back to previous");
        button.setLocation(WIDTH * 3 / 5, HEIGHT / 10 +560);
        button.setSize(170, 50);
        button.setFont(new Font("Rockwell", Font.BOLD, 15));
        layeredPane.add(button, JLayeredPane.MODAL_LAYER);

        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                StartTimeFrame mainFrame = new StartTimeFrame(360, 360);
                mainFrame.setVisible(true);
            }
        });
    }

    /*ImageIcon backgroundPic = new ImageIcon("C:\\Users\\LuoXinyi\\IdeaProjects\\Project\\src\\backgroundPic\\img3.png");
    JLabel img = new JLabel(backgroundPic);*/
}
