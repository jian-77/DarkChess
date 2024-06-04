package view;

import javax.swing.*;
import java.awt.*;

public class BackgroundPic extends JPanel{
    Image img;

    public Image getImg() {
        return img;
    }

    public void setImg(Image img) {
        this.img = img;
    }

    public BackgroundPic(Image img){
        this.img = img;
        this.setOpaque(true);
    }

    public void paintComponent (Graphics g){
        super.paintComponent(g);
        g.drawImage(img,0,0,this.getWidth(),this.getWidth(),this);
    }

}
