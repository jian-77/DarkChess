package controller;

import Bonus.Undo;
import chessComponent.*;
import model.ChessColor;
import view.Chessboard;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;



/**
 * 这个类主要完成由窗体上组件触发的动作。
 * 例如点击button等
 * ChessGameFrame中组件调用本类的对象，在本类中的方法里完成逻辑运算，将运算的结果传递至chessboard中绘制
 */
public class GameController {
    private Chessboard chessboard;
    private int [][]reversal =new int[8][4];
    public GameController(Chessboard chessboard) {
        this.chessboard = chessboard;
    }
    public int[][] getReversal() {
        return reversal;
    }

    public char[][] loadGameFromFile(String path) {
        try {
            List<String> chessData = Files.readAllLines(Path.of(path));
            System.out.println(chessData);
            return  loadGame(chessData);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
    public  char[][] loadGame(List<String> chessData) {
        char[][] chesses = new char[22][4];
        String s = String.join(",", chessData);
       System.out.print(s);
       String[]sss=s.split(",");
        System.out.println(sss[34]+sss[35]);
        System.out.println(s.length());///////////////
        for (int i=0;i<8;i++){
            for (int j=0;j<4;j++){
                chesses[i][j]=s.charAt(2*(i*4+j));
            }
        }
        chesses[8][0]=s.charAt(64);
        chesses[8][1]='0';
        chesses[8][2]='0';
        chesses[8][3]='0';
        for (int i=9;i<17;i++){
            for (int j=0;j<4;j++){
                chesses[i][j]=s.charAt(2*(i*4+j));
            }
        }
        for (int i=17;i<21;i++){
            for (int j=0;j<4;j++){
                chesses[i][j]=s.charAt(2*(i*4+j));
            }
        }
        for (int i=0;i<21;i++){
            for (int j=0;j<4;j++){
                System.out.print(chesses[i][j]);
            }
        }
        chesses[21][0]=s.charAt(168);
        System.out.println(chesses[21][0]);
        chesses[21][1]=s.charAt(170);
        System.out.println(chesses[21][1]);
        chesses[21][2]=s.charAt(172);
        System.out.println(chesses[21][2]);
        chesses[21][3]=s.charAt(174);
        System.out.println(chesses[21][3]);

        /*  while (s.length()>n){
            Undo undo=new Undo();
//////为了不干扰undo，可以把这个写外面


            n+=64;
        }*/
        return chesses;
    }//以上是将txt文本读取成char【】【】的读档操作

   public ArrayList<Undo> loadUndo(String path){
       try {
           List<String> chessData = Files.readAllLines(Path.of(path));
           String s = String.join(",", chessData);
           int n=176;
           ArrayList<Undo>list=new ArrayList<>();
           while (s.length()>n){
               Undo undo=new Undo();
               for (int i=0;i<22;i++){
                   for (int j=0;j<4;j++){
                       undo.undoSquareArray[i][j]=s.charAt(2*(i*4+j)+n);
                   }
               }
               list.add(undo);
               n+=176;
           }
           return  list;
       } catch (IOException e) {
           e.printStackTrace();
       }


       return null;

   }

    public void saveGame(SquareComponent[][]squareComponents) {
        try {
            FileWriter fileWriter = new FileWriter("C:/Users/见/Documents/test/game.txt");
            BufferedWriter writer = new BufferedWriter(fileWriter);
            List<String> lines = this.convertToList();
            for (String line : lines) {
                writer.write(line);
            }
            writer.close();
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();//这个方法加下面的convertolist方法是保存棋盘和行棋方
        }
        /*try {
            FileWriter fileWriter = new FileWriter("resources/reversal.txt");
            BufferedWriter writer = new BufferedWriter(fileWriter);
            List<String> lines = new ArrayList<>();
            StringBuilder sb = new StringBuilder();
            for (SquareComponent[] ints : chessboard.getChessComponents()) {
                sb.setLength(0);
                for (SquareComponent anInt : ints) {
                    if (anInt.isReversal() == true) {
                        sb.append(1).append(",");
                    } else sb.append(0).append(",");
                }
                sb.setLength(sb.length() - 1);
                lines.add(sb.toString() + "\n");
            }

            for (String line : lines) {
                writer.write(line);
            }
            writer.close();
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();//这一部分是保存是否翻开
        }*/
    }
    public List<String> convertToList() {
        List<String> lines = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        for (SquareComponent[] ints :chessboard.getChessComponents()) {
            sb.setLength(0);
            for (SquareComponent anInt : ints) {
              if (anInt instanceof GeneralChessComponent){
                  if (anInt.getChessColor()== ChessColor.RED){
                      sb.append("G").append(",");
                  }
                  else sb.append("g").append(",");
                }
              if (anInt instanceof MinisterChessComponent){
                    if (anInt.getChessColor()== ChessColor.RED){
                        sb.append("M").append(",");
                    }
                    else sb.append("m").append(",");
                }
                if (anInt instanceof AdvisorChessComponent){
                    if (anInt.getChessColor()== ChessColor.RED){
                        sb.append("A").append(",");
                    }
                    else sb.append("a").append(",");
                }
                if (anInt instanceof HorseChessComponent){
                    if (anInt.getChessColor()== ChessColor.RED){
                        sb.append("H").append(",");
                    }
                    else sb.append("h").append(",");
                }
                if (anInt instanceof CannonChessComponent){
                    if (anInt.getChessColor()== ChessColor.RED){
                        sb.append("P").append(",");
                    }
                    else sb.append("p").append(",");
                }
                if (anInt instanceof ChariotChessComponent){
                    if (anInt.getChessColor()== ChessColor.RED){
                        sb.append("C").append(",");
                    }
                    else sb.append("c").append(",");
                }
                if (anInt instanceof SoldierChessComponent){
                    if (anInt.getChessColor()== ChessColor.RED){
                        sb.append("S").append(",");
                    }
                    else sb.append("s").append(",");
                }
                if (anInt instanceof EmptySlotComponent){
                   sb.append("K").append(",");
                }
            }
            sb.setLength(sb.length() - 1);
            lines.add(sb.toString()+"\n");
        }
        sb.setLength(0);
        if (chessboard.getCurrentColor()==ChessColor.RED){
            sb.append("R").append(",").append("0").append(",").append("0").append(",").append("0");
            lines.add(sb.toString()+"\n");
        }
        if (chessboard.getCurrentColor()==ChessColor.BLACK){
            sb.append("B").append(",").append("0").append(",").append("0").append(",").append("0");
            lines.add(sb.toString()+"\n");
        }
        sb.setLength(0);
        for (SquareComponent[] ints : chessboard.getChessComponents()) {
            sb.setLength(0);
            for (SquareComponent anInt : ints) {
                if (anInt.isReversal() == true) {
                    sb.append(1).append(",");
                } else sb.append(0).append(",");
            }
            sb.setLength(sb.length() - 1);
            lines.add(sb.toString()+"\n");
        }
        sb.setLength(0);
        sb.append(SoldierChessComponent.redBeCaught).append(",").append(SoldierChessComponent.blackBeCaught).append(",").append(CannonChessComponent.redBeCaught).append(",").append(CannonChessComponent.blackBeCaught);
        lines.add(sb.toString()+"\n");
        sb.setLength(0);
        sb.append(HorseChessComponent.redBeCaught).append(",").append(HorseChessComponent.blackBeCaught).append(",").append(ChariotChessComponent.redBeCaught).append(",").append(HorseChessComponent.redBeCaught);
        lines.add(sb.toString()+"\n");
        sb.setLength(0);
        sb.append(MinisterChessComponent.redBeCaught).append(",").append(MinisterChessComponent.blackBeCaught).append(",").append(AdvisorChessComponent.redBeCaught).append(",").append(AdvisorChessComponent.blackBeCaught);
        lines.add(sb.toString()+"\n");
        sb.setLength(0);
        sb.append(GeneralChessComponent.redBeCaught).append(",").append(GeneralChessComponent.blackBeCaught).append(",").append(0).append(",").append(0);
        lines.add(sb.toString()+"\n");

        sb.setLength(0);
        sb.append(chessboard.Rremainingpoint/10).append(",").append(chessboard.Rremainingpoint%10).append(",").append(chessboard.Bremainingpoint/10).append(",").append(chessboard.Bremainingpoint%10);
        lines.add(sb.toString()+"\n");

        for (int n=0;n<chessboard.list.size();n++){
            for (int i=0;i<22;i++){
                sb.setLength(0);
                for (int j=0;j<4;j++){
                    sb.append(String.valueOf(chessboard.list.get(n).undoSquareArray[i][j])).append(",");
                }
                sb.setLength(sb.length()-1);
                lines.add(sb.toString()+"\n");
            }
        }

        return lines;
    }

    public List<String> convertToList1(){
        List<String> lines = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        for (SquareComponent[] ints :chessboard.getChessComponents()) {
            sb.setLength(0);
            for (SquareComponent anInt : ints) {
                if (anInt instanceof GeneralChessComponent){
                    if (anInt.getChessColor()== ChessColor.RED){
                        sb.append("G").append(",");
                    }
                    else sb.append("g").append(",");
                }
                if (anInt instanceof MinisterChessComponent){
                    if (anInt.getChessColor()== ChessColor.RED){
                        sb.append("M").append(",");
                    }
                    else sb.append("m").append(",");
                }
                if (anInt instanceof AdvisorChessComponent){
                    if (anInt.getChessColor()== ChessColor.RED){
                        sb.append("A").append(",");
                    }
                    else sb.append("a").append(",");
                }
                if (anInt instanceof HorseChessComponent){
                    if (anInt.getChessColor()== ChessColor.RED){
                        sb.append("H").append(",");
                    }
                    else sb.append("h").append(",");
                }
                if (anInt instanceof CannonChessComponent){
                    if (anInt.getChessColor()== ChessColor.RED){
                        sb.append("P").append(",");
                    }
                    else sb.append("p").append(",");
                }
                if (anInt instanceof ChariotChessComponent){
                    if (anInt.getChessColor()== ChessColor.RED){
                        sb.append("C").append(",");
                    }
                    else sb.append("c").append(",");
                }
                if (anInt instanceof SoldierChessComponent){
                    if (anInt.getChessColor()== ChessColor.RED){
                        sb.append("S").append(",");
                    }
                    else sb.append("s").append(",");
                }
                if (anInt instanceof EmptySlotComponent){
                    sb.append("K").append(",");
                }
            }
            sb.setLength(sb.length() - 1);
            lines.add(sb.toString()+"\n");
        }
        sb.setLength(0);
        if (chessboard.getCurrentColor()==ChessColor.RED){
            sb.append("R").append(",").append("0").append(",").append("0").append(",").append("0");
            lines.add(sb.toString()+"\n");
        }
        if (chessboard.getCurrentColor()==ChessColor.BLACK){
            sb.append("B").append(",").append("0").append(",").append("0").append(",").append("0");
            lines.add(sb.toString()+"\n");
        }
        sb.setLength(0);
        for (SquareComponent[] ints : chessboard.getChessComponents()) {
            sb.setLength(0);
            for (SquareComponent anInt : ints) {
                if (anInt.isReversal() == true) {
                    sb.append(1).append(",");
                } else sb.append(0).append(",");
            }
            sb.setLength(sb.length() - 1);
            lines.add(sb.toString()+"\n");
        }
        sb.setLength(0);
        sb.append(SoldierChessComponent.redBeCaught).append(",").append(SoldierChessComponent.blackBeCaught).append(",").append(CannonChessComponent.redBeCaught).append(",").append(CannonChessComponent.blackBeCaught);
        lines.add(sb.toString()+"\n");
        sb.setLength(0);
        sb.append(HorseChessComponent.redBeCaught).append(",").append(HorseChessComponent.blackBeCaught).append(",").append(ChariotChessComponent.redBeCaught).append(",").append(HorseChessComponent.redBeCaught);
        lines.add(sb.toString()+"\n");
        sb.setLength(0);
        sb.append(MinisterChessComponent.redBeCaught).append(",").append(MinisterChessComponent.blackBeCaught).append(",").append(AdvisorChessComponent.redBeCaught).append(",").append(AdvisorChessComponent.blackBeCaught);
        lines.add(sb.toString()+"\n");
        sb.setLength(0);
        sb.append(GeneralChessComponent.redBeCaught).append(",").append(GeneralChessComponent.blackBeCaught).append(",").append(0).append(",").append(0);
        lines.add(sb.toString()+"\n");

        sb.setLength(0);
        sb.append(chessboard.Rremainingpoint/10).append(",").append(chessboard.Rremainingpoint%10).append(",").append(chessboard.Bremainingpoint/10).append(",").append(chessboard.Bremainingpoint%10);
        lines.add(sb.toString()+"\n");
        return lines;
    }



    /*public char[][] getchessreversal(){
        char[][] reversalchar = new char[8][4];
        try {
            List<String> reversalData = Files.readAllLines(Path.of("resources/reversal.txt"));
            String s = String.join(",", reversalData);
            for (int i=0;i<8;i++){
                for (int j=0;j<4;j++){
                    reversalchar[i][j]=s.charAt(2*(i*4+j));
                }
            }
        }catch (IOException e) {
            e.printStackTrace();
        }
        return reversalchar;
    }

    public void initialreversal(){
        for (char[]e:getchessreversal()){
            for (char s:e){
                System.out.print(s);
            }
            System.out.println();
        }
        for (int i=0;i<8;i++){
            for (int j=0;j<4;j++){
                if (getchessreversal()[i][j]=='1'){
                    chessboard.getSquareComponent(i,j).setReversal(true);
                    chessboard.getSquareComponent(i,j).repaint();
                }
                else chessboard.getSquareComponent(i,j).setReversal(false);
                chessboard.getSquareComponent(i,j).repaint();
            }

        }
    }
*/
}
