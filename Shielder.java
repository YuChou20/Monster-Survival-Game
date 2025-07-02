package Data;
import java.awt.*;
import java.io.*;
import javax.imageio.*;

public class Shielder{
	private Image[] shielders_Imgs = new Image[4] , current_Imgs = new Image[2];
	private int moveX = 2 , moveY = 2, timer = 0;
	private int[] shielderX = {0,1500} , shielderY = {520,520}, shielderHP = {300,300}, status = {0,0} ;
	private boolean[] isExist = {false,false};
	public Shielder(){
		String imgname = "shielder";
		try {
			for( int i = 0 ; i < 4 ; ++i){
				shielders_Imgs[i] = ImageIO.read(new File(imgname + i + ".png"));
			}
		} catch (IOException e) {}
		current_Imgs[0] = shielders_Imgs[0];
		current_Imgs[1] = shielders_Imgs[0];
	}
	
	public void addShielder(){
		//System.out.println("Hi");
		for( int i = 0 ; i < 2 ; ++i ){
			if (i == 1){
			}
			if(isExist[i] == false){
				isExist[i] = true;			
				shielderX[i] = i * 1600 - 100;
				shielderY[i] = 520;
				shielderHP[i] = 150;
				status[i] = 0;
				current_Imgs[i] = shielders_Imgs[0];
			}
		}
	}
	public boolean isHitted(int ind , int x, int y){
		if(isExist[ind])
		{
			if (x > shielderX[ind] - 30 && x < shielderX[ind] + 140 && y > shielderY[ind] - 25 && y < shielderY[ind] + 90){
				shielderHP[ind] -= 10;
				if(status[ind] == 0)
				{
					current_Imgs[ind] = shielders_Imgs[1];
					status[ind] = 1;
				}
				else if(status[ind] == 2)
				{
					status[ind] = 3;
				}
				if(shielderHP[ind] <= 0){
					isExist[ind] = false; 
				}
				//System.out.println("shielder" + ind + " is hit.");
				return true;
			}
		}
		
		//System.out.println("shielder" + ind + " miss.");
		return false;
	}
	public void moveShielder(Graphics scr , int playerX){
		for( int i = 0 ; i < 2 ; ++i){
			if (isExist[i]){
				if(status[i] == 0){						//未被擊落
					if (i == 0){		//左邊的shielder
						shielderX[i] += moveX;
						if (shielderX[i] >= playerX - 140){
							status[i] = 1;
							current_Imgs[i] = shielders_Imgs[1];
						}
					}else{		//右邊的shielder
						shielderX[i] -= moveX;
						if (shielderX[i] <= playerX + 120){
							status[i] = 1;
							current_Imgs[i] = shielders_Imgs[1];
						}
					}
				}else if(status[i] == 1){				//被擊落(下墜中)
					if(i == 0 && shielderX[0] <= 140){
						isExist[0] = false;
						shielderX[0] = 1600;
						
					}else if(i == 1 && shielderX[1] >= 1360){
						isExist[1] = false;
						shielderX[1] = 1600;
					}
					shielderY[i] += moveY;
					if (shielderY[i] == 600){
						current_Imgs[i] = shielders_Imgs[2];
						status[i] = 2;
					}
				}else if(status[i] == 3){				//被擊落(受到攻擊)
					++timer;
					current_Imgs[i] = shielders_Imgs[3];
					if(timer >= 5){
						status[i] = 2;					//變回未受攻擊狀態
						timer = 0;
						current_Imgs[i] = shielders_Imgs[2];
					}
				}
				scr.drawImage(current_Imgs[i],shielderX[i], shielderY[i], 140, 100, null);
			}
		}
	}
}