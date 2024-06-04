package controller;


import Bonus.Undo;
import chessComponent.CannonChessComponent;
import chessComponent.SquareComponent;
import chessComponent.EmptySlotComponent;
import model.ChessColor;
import view.ChessGameFrame;
import view.Chessboard;
import AI.AI;
import Bonus.CountDown;

import javax.swing.*;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.Timer;

public class ClickController {
    protected final Chessboard chessboard;
    protected SquareComponent first;

    public ClickController(Chessboard chessboard) {
        this.chessboard = chessboard;

    }

    public void onClick(SquareComponent squareComponent) {
        System.out.println(chessboard);
        //判断第一次点击
        if (first == null) {
            if (handleFirst(squareComponent)) {
                squareComponent.setSelected(true);
                first = squareComponent;
                first.repaint();
                for (int i = 0; i < 8; i++) {
                    for (int j = 0; j < 4; j++) {
                        if (handleSecond(chessboard.getChessComponents()[i][j], first)) {
                            chessboard.getSquareComponents()[i][j].canMoveTo = true;
                            chessboard.getSquareComponents()[i][j].repaint();
                        }
                    }
                }

            }
        } else {
            if (first == squareComponent) { // 再次点击取消选取
                squareComponent.setSelected(false);
                SquareComponent recordFirst = first;
                first = null;
                recordFirst.repaint();
                for (int i = 0; i < 8; i++) {
                    for (int j = 0; j < 4; j++) {
                        chessboard.getSquareComponents()[i][j].canMoveTo = false;
                        chessboard.getSquareComponents()[i][j].repaint();
                    }
                }
            } else if (handleSecond(squareComponent, first)) {
                //repaint in swap chess method.
                chessboard.swapChessComponents(first, squareComponent);
                chessboard.clickController.swapPlayer();
                first.setSelected(false);
                first = null;
                for (int i = 0; i < 8; i++) {
                    for (int j = 0; j < 4; j++) {
                        chessboard.getSquareComponents()[i][j].canMoveTo = false;
                        chessboard.getSquareComponents()[i][j].repaint();
                    }
                }
            } else {
                JOptionPane.showMessageDialog(null, "invalid click!");
            }
        }
    }


    /**
     * @param squareComponent 目标选取的棋子
     * @return 目标选取的棋子是否与棋盘记录的当前行棋方颜色相同
     */

    protected boolean handleFirst(SquareComponent squareComponent) {
        if (squareComponent.isClicked() == false) {
            squareComponent.setReversal(true);
            squareComponent.setClicked(true);
            System.out.printf("onClick to reverse a chess [%d,%d]\n", squareComponent.getChessboardPoint().getX(), squareComponent.getChessboardPoint().getY());
            squareComponent.repaint();
            chessboard.clickController.swapPlayer();
            return false;
        }
        return squareComponent.getChessColor() == chessboard.getCurrentColor();
    }

    /**
     * @param squareComponent first棋子目标移动到的棋子second
     * @return first棋子是否能够移动到second棋子位置
     */

    protected boolean handleSecond(SquareComponent squareComponent, SquareComponent first) {
        if (!(first instanceof CannonChessComponent)) {
            //没翻开或空棋子，进入if
            if (squareComponent.isClicked() == false && squareComponent.isReversal() == false) {
                //没翻开且非空棋子不能走
                if (!(squareComponent instanceof EmptySlotComponent)) {
                    return false;
                }
            }
            return squareComponent.getChessColor() != chessboard.getCurrentColor() &&
                    first.canMoveTo(chessboard.getChessComponents(), squareComponent.getChessboardPoint(), squareComponent);
        } else {//判断炮的canMoveto
            if (squareComponent.isClicked() == true && squareComponent.isReversal() == true) {//已翻开的棋只能吃不同颜色
                return squareComponent.getChessColor() != chessboard.getCurrentColor() &&
                        first.canMoveTo(chessboard.getChessComponents(), squareComponent.getChessboardPoint(), squareComponent);
            } else {//未翻开的状态
                return first.canMoveTo(chessboard.getChessComponents(), squareComponent.getChessboardPoint(), squareComponent);
            }
        }
    }


    public int swapPlayer()  {
        CountDown.count[0]=16;
        if (AI.mode == false) {
               Undo undo=new Undo();
            chessboard.setCurrentColor(chessboard.getCurrentColor() == ChessColor.BLACK ? ChessColor.RED : ChessColor.BLACK);
            ChessGameFrame.getStatusLabel().setText(String.format("%s's TURN", chessboard.getCurrentColor().getName()));
             // List<String>strings=chessboard.gameController.convertToList();
            List<String>strings=chessboard.gameController.convertToList1();
            try {
                FileWriter fileWriter = new FileWriter("resources/game.txt");
                BufferedWriter writer = new BufferedWriter(fileWriter);
                List<String> lines = strings;
                for (String line : lines) {
                    writer.write(line);
                }
                writer.close();
                fileWriter.close();
            } catch (IOException e) {
                e.printStackTrace();//这个方法加下面的convertolist方法是保存棋盘和行棋方
            }
           try {
                List<String> chessData = Files.readAllLines(Path.of("resources/game.txt"));
                undo.undoSquareArray=chessboard.gameController.loadGame(chessData);
                chessboard.list.add(undo);
                try{
                    FileWriter fileWriter = new FileWriter("resources/game.txt");
                    fileWriter.write("");
                    fileWriter.flush();
                    fileWriter.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

            return 0;
        } else {
            chessboard.setCurrentColor(chessboard.getCurrentColor() == ChessColor.BLACK ? ChessColor.RED : ChessColor.BLACK);
            ChessGameFrame.getStatusLabel().setText(String.format("%s's TURN", chessboard.getCurrentColor().getName()));
            Timer timer = new Timer();
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    AI();
                    timer.cancel();
                }
            }, 500, 1000);

          /*  if (chessboard.getCurrentColor() == ChessColor.RED) {
                for (SquareComponent[] squareComponents : chessboard.getSquareComponents()) {
                    for (SquareComponent first : squareComponents) {
                        if (first.isReversal() == true && first.getChessColor() == ChessColor.RED) {
                            for (SquareComponent[] squareComponent : chessboard.getSquareComponents()) {
                                for (SquareComponent second : squareComponent) {
                                    if (!(second instanceof EmptySlotComponent)) {
                                        if (handleSecond(second, first)) {
                                            {
                                                chessboard.swapChessComponents(first, second);
                                                chessboard.clickController.swapPlayer();
                                                return 0;
                                            }
                                        }
                                    }
                                }
                            }

                        }///////////////
                    }
                }
                int x, y, a, b;
                Random random1 = new Random();
                int c = 0;
                for (SquareComponent[] squareComponent : chessboard.getSquareComponents()) {
                    for (SquareComponent second : squareComponent) {
                        if (!(second instanceof EmptySlotComponent) && second.isReversal() == false)
                            c += 1;
                    }
                }
                if (c == 0) {
                    while (true) {
                        a = random1.nextInt(8);
                        b = random1.nextInt(4);
                        if (chessboard.getSquareComponents()[a][b].getChessColor() == ChessColor.RED) {
                            break;
                        }
                    }
                    for (SquareComponent[] squareComponent : chessboard.getSquareComponents()) {
                        for (SquareComponent second : squareComponent) {
                            if (handleSecond(second, chessboard.getSquareComponents()[a][b])) {
                                {
                                    chessboard.swapChessComponents(chessboard.getSquareComponents()[a][b], second);
                                    chessboard.clickController.swapPlayer();
                                    return 0;
                                }
                            }
                        }
                    }

                } else {
                    while (true) {
                        ArrayList<String> contain = new ArrayList<>();
                        while (true) {
                            x = random1.nextInt(8);
                            y = random1.nextInt(4);
                            int n = 0;
                            for (String s : contain) {
                                if (s.equals(String.valueOf(x) + String.valueOf(y))) {
                                    n += 1;
                                }
                            }
                            if (n == 0) {
                                contain.add(String.valueOf(x) + String.valueOf(y));
                                break;
                            } else {
                                continue;
                            }
                        }////这个循环打破后说明找到了一个不重复操作的棋子
                        if (chessboard.getChessComponents()[x][y].isReversal() == false && !(chessboard.getChessComponents()[x][y] instanceof EmptySlotComponent)) {
                            break;
                        }
                    }
                    chessboard.getChessComponents()[x][y].setReversal(true);
                    chessboard.getChessComponents()[x][y].setClicked(true);
                    chessboard.getChessComponents()[x][y].repaint();
                    chessboard.clickController.swapPlayer();
                    return 0;
                }
            }
            return 0;
        }
    }*/
        }
        return 0;
    }//////////

    public int AI(){
        if (chessboard.getCurrentColor() == ChessColor.RED) {
            for (SquareComponent[] squareComponents : chessboard.getSquareComponents()) {
                for (SquareComponent first : squareComponents) {
                    if (first.isReversal() == true && first.getChessColor() == ChessColor.RED) {
                        for (SquareComponent[] squareComponent : chessboard.getSquareComponents()) {
                            for (SquareComponent second : squareComponent) {
                                if (!(second instanceof EmptySlotComponent)) {
                                    if (handleSecond(second, first)) {
                                        {
                                            chessboard.swapChessComponents(first, second);
                                            chessboard.clickController.swapPlayer();
                                            return 0;
                                        }
                                    }
                                }
                            }
                        }

                    }///////////////
                }
            }
            int x, y, a, b;
            Random random1 = new Random();
            int c = 0;
            for (SquareComponent[] squareComponent : chessboard.getSquareComponents()) {
                for (SquareComponent second : squareComponent) {
                    if (!(second instanceof EmptySlotComponent) && second.isReversal() == false)
                        c += 1;
                }
            }
            if (c == 0) {
                while (true) {
                    a = random1.nextInt(8);
                    b = random1.nextInt(4);
                    if (chessboard.getSquareComponents()[a][b].getChessColor() == ChessColor.RED) {
                        for (SquareComponent[] squareComponent : chessboard.getSquareComponents()) {
                            for (SquareComponent second : squareComponent) {
                                if (handleSecond(second, chessboard.getSquareComponents()[a][b])) {
                                    {
                                        chessboard.swapChessComponents(chessboard.getSquareComponents()[a][b], second);
                                        chessboard.clickController.swapPlayer();
                                        return 0;
                                    }
                                }
                            }
                        }

                    }
                }


            } else {
                while (true) {
                    ArrayList<String> contain = new ArrayList<>();
                    while (true) {
                        x = random1.nextInt(8);
                        y = random1.nextInt(4);
                        int n = 0;
                        for (String s : contain) {
                            if (s.equals(String.valueOf(x) + String.valueOf(y))) {
                                n += 1;
                            }
                        }
                        if (n == 0) {
                            contain.add(String.valueOf(x) + String.valueOf(y));
                            break;
                        } else {
                            continue;
                        }
                    }////这个循环打破后说明找到了一个不重复操作的棋子
                    if (chessboard.getChessComponents()[x][y].isReversal() == false && !(chessboard.getChessComponents()[x][y] instanceof EmptySlotComponent)) {
                        break;
                    }
                }
                chessboard.getChessComponents()[x][y].setReversal(true);
                chessboard.getChessComponents()[x][y].setClicked(true);
                chessboard.getChessComponents()[x][y].repaint();
                chessboard.clickController.swapPlayer();
                return 0;
            }
        }
        return 0;
    }
}
/////AI 还存在的问题，没有棋子课翻后只能走空格，






