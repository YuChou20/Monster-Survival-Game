package Data;
import java.awt.*;
import java.io.*;
import javax.imageio.*;

public class Tank{
	private Image[] tank_Imgs = new Image[12]  , bullet_Imgs = new Image[2],
					explor_Imgs = new Image[10]  ,  getHurt_Imgs = new Image[2];
	private int moveX = 2 , moveY = 2, timer = 0,speed = 3, maxNum = 2	, tankN = 0 , temp = 0 , tankW = 160 , tankH = 190 , cannonW = 90,cannonH = 90;
	private int[] tankX = new int[12],tankY = new int[12] , tankHP = new int[12],
				status = new int[12] , des = new int[12], cannonX = new int[12] , 
				cannonY = new int[12] ,timers = new int[12] , cannonStatus = new int[12];
	private boolean[] isExist = new boolean[12] , islaunch = new boolean[12] , 
	facing = new boolean[12] , cannonF = new boolean[12] , isExp = new boolean[12] , ishurt = new boolean[12];
	public Tank(){
		String tank = "Tank";
		try {
			for( int i = 0 ; i < 12 ; ++i){
				tank_Imgs[i] = ImageIO.read(new File(tank + i + ".png"));
				tankX[i] = -200;
				tankY[i] = 510; 
				tankHP[i] = 500;
				status[i] = 0;
				des[i] = -300;
				cannonX[i] = 0; 
				cannonY[i] = 0;
				timers[i] = ((int)(Math.random() * 50) / 2 ) * 2 + 150;
				//System.out.println( i +  ":   " + timers[i] );
				islaunch[i] = false;
				isExist[i] = false;
				isExp[i] = false;
				ishurt[i] = false;
			}
			String exp = "cannon_Explor";
			for(int i = 0 ; i < 10 ; ++i){
				explor_Imgs[i] = ImageIO.read(new File(exp + i + ".png"));
			}
			bullet_Imgs[0] = ImageIO.read(new File("cannonL.png"));
			bullet_Imgs[1] = ImageIO.read(new File("cannonR.png"));
			getHurt_Imgs[0] = ImageIO.read(new File("Tank_getHurt0.png"));
			getHurt_Imgs[1] = ImageIO.read(new File("Tank_getHurt1.png"));
			
		} catch (IOException e) {}
	}
	public void addTank(int playerX){
		int random = (int) ((Math.random()*2)) , rand_des = (int) ((Math.random()*1600));
		//System.out.println("New:" + random);
		if (random == 0 && tankN < maxNum && playerX >= 160){		//add tankL
			++tankN;
			for( int i = 0 ; i < 12 ; ++i){
				if(isExist[i] == false ){
					facing[i] = false;
					isExist[i] = true;
					tankX[i] = -100;
					tankY[i] = 510; 
					tankHP[i] = 200;
					status[i] = 0;
					des[i] = (rand_des % playerX ) /2 * 2;
					//System.out.println("desL:" + des[i]);
					cannonX[i] = des[i];
					break;
				}
			}
		}else if(random == 1 && tankN < maxNum && playerX <= 1340){					//add tankR
			++tankN;
			for( int i = 0 ; i < 12 ; ++i){
				if(isExist[i] == false){
					facing[i] = true;
					isExist[i] = true;
					tankX[i] = 1500;
					tankY[i] = 510;
					tankHP[i] = 200;
					status[i] = 6;
					des[i] = (playerX + (rand_des % (1500 - playerX))) /2 * 2;
					//System.out.println("desL:" + des[i]);
					cannonX[i] = des[i];
					break;
				}
			}
		}
	}
	public void launch(int ind){
		if(facing[ind] == false && islaunch[ind] == false && tankX[ind] >= des[ind]){
			if(timer % timers[ind] == 0){
				cannonF[ind] = false;
				cannonStatus[ind] = 0;
				cannonX[ind] = tankX[ind] + tankW;
				cannonY[ind] = 550;
				timers[ind] = ((int)(Math.random() * 50) / 2 ) * 2 + 150;
				//System.out.println(timers[ind]);
				islaunch[ind] = true;
			}
		}
		else if(facing[ind] == true && islaunch[ind] == false && tankX[ind] <= des[ind]){
			if(timer % timers[ind] == 0){
				cannonF[ind] = true;
				cannonStatus[ind] = 0;
				cannonX[ind] = tankX[ind] - cannonW;
				cannonY[ind] = 550;
				timers[ind] = ((int)(Math.random() * 50) / 2 ) * 2 + 150;
				//System.out.println(timers[ind]);
				islaunch[ind] = true;
			}
		}
	}
	public void moveCannon(Graphics scr , int playerX ,int playerY){
		for( int i = 0 ; i < 12 ; ++i){
			if (islaunch[i]){
				if (isExp[i] == true){
					if(timer%3 == 0){
						++ cannonStatus[i];
					}
					if(cannonStatus[i] >= 8){
						isExp[i] = false;
						islaunch[i] = false;
					}
					if(cannonF[i] == false){
						scr.drawImage(explor_Imgs[cannonStatus[i]],cannonX[i] - 130,400,400,400, null);
					}
					else{
						scr.drawImage(explor_Imgs[cannonStatus[i]],cannonX[i] - 210,400,400,400, null);
					}
				}else{
					if (cannonF[i] == false && islaunch[i] == true){
						///System.out.println(i + "L:" +cannonX[i]);
						cannonX[i] += moveX;
						if(cannonX[i] >= 1560){
							islaunch[i] = false;
						}
						scr.drawImage(bullet_Imgs[0],cannonX[i],cannonY[i],cannonW,cannonH, null);
					}
					else if (cannonF[i] == true && islaunch[i] == true){
						//System.out.println(i + "R:" +cannonX[i]);
						cannonX[i] -= moveX;
						if(cannonX[i] <= -100){
							islaunch[i] = false;
						}
						scr.drawImage(bullet_Imgs[1],cannonX[i],cannonY[i],cannonW,cannonH, null);
					}
					//System.out.println("PlayerX:" );
					if (playerX <= cannonX[i] + cannonW && playerX >= cannonX[i] - 60 && playerY >=  cannonY[i] - 120 && playerY <= cannonY[i] + cannonH){
							isExp[i] = true;
					}
				}
			}
		}
	}
	
	public boolean isHitPlayer(int ind)
	{
		if(isExp[ind])
		{
			System.out.println("tank hit Player");
			return true;
		}
		return false;
	}
	
	public boolean isHitted(int ind , int x, int y){
		if(isExist[ind])
		{
			if ( x > tankX[ind] - 20 && x < tankX[ind] + tankW - 10 && y > tankY[ind] - 20 && y < tankY[ind] + tankH - 10){
				ishurt[ind] = true;
				tankHP[ind] -= 10;
				if(tankHP[ind] == 0){
					isExist[ind] = false;
					--tankN;
				}
				//System.out.println("tank" + ind + " is hit.");
				return true;
			}
			//System.out.println("tank" + ind + " miss.");
		}
		return false;
	}
	public void moveTank(Graphics scr ,int playerX){
		++timer;
		if (timer % speed == 0){
			for( int i = 0 ; i < 12 ; ++i){
				if (isExist[i] == true){
					if (facing[i] == false){
						if(tankX[i] <= des[i]){
							++status[i];
							tankX[i] += moveX;
							if (status[i] >= 6){
								status[i] = 0;
							}
							if ( tankX[i] == 2000){
								tankX[i] = 0;
							}
						}else if(tankX[i] >= des[i]){
							if (playerX <= tankX[i] ){
								facing[i] = true;
								status[i] += 6;
							}
							launch(i);
						}
					}else{
						if(tankX[i] >= des[i]){
							++status[i];
							tankX[i] -= moveX;
							if (status[i] >= 11){
								status[i] = 6;
							}
							if ( tankX[i] == -200){
								tankX[i] = 1600;
							}
						}else if(tankX[i] <= des[i]){
							if (playerX >= tankX[i] ){
								facing[i] = false;
								status[i] -= 6;
							}
							launch(i);
						}
					}
				}
			}
		}
		for( int i = 0 ; i < 12 ; ++i){
			if (isExist[i] == true){
				scr.drawImage(tank_Imgs[status[i]],tankX[i],tankY[i],tankW,tankH, null);
				if (ishurt[i] == true){
					if(facing[i] == false){
						scr.drawImage(getHurt_Imgs[0],tankX[i],tankY[i],tankW,tankH, null);
					}else{
						scr.drawImage(getHurt_Imgs[1],tankX[i],tankY[i],tankW,tankH, null);
					}
					if(timer % 5 == 0){
						ishurt[i] = false;
					}
				}
			}
		}
		if(timer == 100000){
			timer = 0;
		}
		/*++temp;
		if(temp == 10){
			temp = 0;
		}
		scr.drawImage(getHurt_Imgs[1],400,510,160,190, null);*/
	}
}