package Bonus;

import view.Chessboard;

import java.util.Timer;
import java.util.TimerTask;

public class Undo {
     public char[][] undoSquareArray=new char[22][4];
     public static Chessboard chessboard;
     public static void undo(){
          int index=chessboard.list.size();
          char[][]chars=chessboard.list.get(index-2).undoSquareArray;
          chessboard.initialGameByCharacters(chars);
          chessboard.list.remove(index-1);
          System.out.println(chessboard.list.size());
     }
     public static void replay(){
          Timer timer=new Timer();
          final int[] count = {0};
               timer.schedule(new TimerTask() {
                    @Override
                    public void run() {
                         if (count[0]<chessboard.list.size()) {
                              char[][] chars = chessboard.list.get(count[0]).undoSquareArray;
                              chessboard.initialGameByCharacters(chars);
                              count[0]++;
                         }
                         else timer.cancel();
                    }
               }, 100, 200);
          }
     }

