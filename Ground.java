package Data;
import java.awt.*;
import java.io.*;
import javax.imageio.*;

public class Ground {
 public int[] x = new int[10], y = new int[10], width = new int[10];
 public boolean[] appeared = {false,false,false,false,false,false,false,false,false,false};
 private Image ground_Img = null;
 
 public Ground()
 { 
  x[0] = 0;
  y[0] = 700;
  width[0] = 1500;
  appeared[0] = true;
  x[1] = 0;
  y[1] = 500;
  width[1] = 300;
  appeared[1] = true;
  x[2] = 900;
  y[2] = 500;
  width[2] = 600;
  appeared[2] = true;
  x[3] = 200;
  y[3] = 300;
  width[3] = 600;
  appeared[3] = true;
  x[4] = 1300;
  y[4] = 300;
  width[4] = 200;
  appeared[4] = true;
  try {
   ground_Img = ImageIO.read(new File("ground.png"));
  } catch (IOException e) {
  }
 }
 public void drawGround(Graphics scr,int stageNum)
 {
  if(stageNum == 0)
  {
   scr.drawImage(ground_Img,x[0], y[0], width[0], 20, null);
   scr.drawImage(ground_Img,x[1], y[1], width[1], 20, null);
   scr.drawImage(ground_Img,x[2], y[2], width[2], 20, null);
   scr.drawImage(ground_Img,x[3], y[3], width[3], 20, null);
   scr.drawImage(ground_Img,x[4], y[4], width[4], 20, null);
  }
 }
}