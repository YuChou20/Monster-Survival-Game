package Data;
import java.awt.*;
import java.io.*;
import javax.imageio.*;


public class FlyingBaLaLa {
	private int BaLaLaHight = 100, BaLaLaWidth = 100;
	public int[] x = new int[10], y = new int[10], width = new int[10], 
				manImg_num = {0,0,0,0,0,0,0,0,0,0}, 
				atkCount = {0,0,0,0,0,0,0,0,0,0}, 
				DeadCount = {0,0,0,0,0,0,0,0,0,0}, 
				HP = {100,100,100,100,100,100,100,100,100,100};
	public int[][] bulletX = {{0,0,0},{0,0,0},{0,0,0},{0,0,0},{0,0,0},{0,0,0},{0,0,0},{0,0,0},{0,0,0},{0,0,0}},
				   bulletY = {{0,0,0},{0,0,0},{0,0,0},{0,0,0},{0,0,0},{0,0,0},{0,0,0},{0,0,0},{0,0,0},{0,0,0}},
				   bombinCount = {{0,0,0},{0,0,0},{0,0,0},{0,0,0},{0,0,0},{0,0,0},{0,0,0},{0,0,0},{0,0,0},{0,0,0}};
	public boolean[] appeared = {false,false,false,false,false,false,false,false,false,false},
					faceRight = {false,false,false,false,false,false,false,false,false,false},
					startAttack = {false,false,false,false,false,false,false,false,false,false},
					isDead = {false,false,false,false,false,false,false,false,false,false},
					isMovable = {false,false,false,false,false,false,false,false,false,false};
	public boolean[][] isShooting = {{false,false,false},{false,false,false},{false,false,false},{false,false,false},{false,false,false},{false,false,false},{false,false,false},{false,false,false},{false,false,false},{false,false,false}},
						isBombing = {{false,false,false},{false,false,false},{false,false,false},{false,false,false},{false,false,false},{false,false,false},{false,false,false},{false,false,false},{false,false,false},{false,false,false}};
	private Image[] BaLaLa_Img = new Image[6], nowImg = new Image[10];
	private Image bullet_img = null, bulletBomb_img = null, Dead_img = null;
	
	public FlyingBaLaLa()
	{	
		try {
			BaLaLa_Img[0] = ImageIO.read(new File("flyingBaLaLa1.png"));
			BaLaLa_Img[1] = ImageIO.read(new File("flyingBaLaLa2.png"));
			BaLaLa_Img[2] = ImageIO.read(new File("flyingBaLaLa3.png"));
			BaLaLa_Img[3] = ImageIO.read(new File("flyingBaLaLa4.png"));
			BaLaLa_Img[4] = ImageIO.read(new File("flyingBaLaLaGoLeft.png"));
			BaLaLa_Img[5] = ImageIO.read(new File("flyingBaLaLaGoRight.png"));
			bullet_img = ImageIO.read(new File("BaLaLa_Bullet.png"));
			bulletBomb_img = ImageIO.read(new File("BaLaLaBulletBomb.png"));
			Dead_img = ImageIO.read(new File("flyingBaLaLaFall.png"));
		} catch (IOException e) {
		}
	}
	
	public void setStart()
	{
		for(int i = 0 ;i < 10;i++)
		{
			HP[i] = 100;
			appeared[i] = false;
			//falling[i] = false;
			startAttack[i] = false;
			isDead[i] = false;

		}
	}
	
	public void produceBaLaLa(int X, int Y)
	{
		int num = 0;
		while(appeared[num])
		{
			num++;
			if(num == 10)
			{
				return;
			}
		}
		HP[num] = 100;
		appeared[num] = true;
		startAttack[num] = false;
		isDead[num] = false;
		DeadCount[num] = 0;
		isMovable[num] = true;
		x[num] = X;
		y[num] = Y;
	}
	
	public void moveBaLaLa(int manX, int manY, int[] groundX,int[] groundY,int[] groundWidth,boolean[] groundAppeared)
	{
	    //System.out.println("x0: " + x[0] + "  x1: " + x[1]+ "  x2: " + x[2]);
		for(int i = 0; i < 10; i++)
		{
			if(appeared[i])
			{
				if(!startAttack[i])
				{
					isMovable[i] = true;
					for(int j = i+1 ; j < 10 ;j++)
					{
						if(appeared[j] && i != j)
						{
							if(x[i] > x[j]-100 && x[i] < x[j]+100)
							{
								isMovable[i] = false;						
							}
						}
					}
					
					if(isMovable[i])
					{
						if(x[i]+25 < manX)
						{
							x[i] += 8;						
						}
						else
						{
							x[i] -= 8;
						}
					}
					
					if(x[i]+25 >= manX-8 && x[i]+25 <= manX+8)
					{
						startAttack[i] = true;
						bulletX[i][0] = x[i] + BaLaLaWidth/2-10;
						bulletX[i][1] = x[i] + BaLaLaWidth/2-10;
						bulletX[i][2] = x[i] + BaLaLaWidth/2-10;
						bulletY[i][0] = y[i] + BaLaLaHight;
						bulletY[i][1] = y[i] + BaLaLaHight;
						bulletY[i][2] = y[i] + BaLaLaHight;
					}
				}
				else
				{
					atkCount[i]++;
					//------------------????-------------------
					if(atkCount[i] == 10)
					{
						isShooting[i][0] = true;
					}
					if(atkCount[i] == 30)
					{
						isShooting[i][1] = true;
					}
					if(atkCount[i] == 50)
					{
						isShooting[i][2] = true;
					}
					//------------------????-------------------
					if(atkCount[i] == 180)//????????
					{
						atkCount[i] = 0;
						startAttack[i] = false;
					}
				}
				if(isDead[i])
				{
					DeadCount[i]++;
					if(DeadCount[i] < 5)
					{
						y[i] -= 15;
					}
					else if(DeadCount[i] < 100)
					{
						y[i] += 15;
					}
					else
					{
						appeared[i] = false;
						isShooting[i][0] = false;
						isShooting[i][1] = false;
						isShooting[i][2] = false;
					}
				}	
			}
		}
	}
	
	
	public void changeMove()
	{
		for(int i = 0; i < 10; i++)
		{
			if(appeared[i])
			{
				if(!isDead[i])
				{
					manImg_num[i]++;
					if(manImg_num[i] == 4)
					{
						manImg_num[i] = 0;
					}
					nowImg[i] = BaLaLa_Img[manImg_num[i]];
				}
				else
				{
					nowImg[i] = Dead_img;
				}
			}
		}
	}
	
	public void drawImg(Graphics scr,  int manX,  int manY)//////
	{
		for(int i = 0; i < 10; i++)
		{
			if(appeared[i])
			{
				if(!isDead[i])
				{
					scr.drawImage(nowImg[i],x[i], y[i], BaLaLaWidth, BaLaLaHight, null);
				}
				else
				{
					scr.drawImage(nowImg[i],x[i], y[i], BaLaLaWidth*13/10, BaLaLaHight*13/10, null);
				}
				if(startAttack[i])
				{					
					if(isShooting[i][0])
					{
						if(bulletY[i][0] < 800 && !isHitPlayer(i,0,manX,manY))
						{
							scr.drawImage(bullet_img,bulletX[i][0], bulletY[i][0], 20, 35, null);
							bulletY[i][0] += 8;
						}
						else
						{
							isShooting[i][0] = false;
							isBombing[i][0] = true;
						}
					}
					else if(isBombing[i][0])
					{
						bombinCount[i][0]++;
						scr.drawImage(bulletBomb_img,bulletX[i][0]-bombinCount[i][0]*2, bulletY[i][0]-bombinCount[i][0]*2
													, 35+bombinCount[i][0]*4, 35+bombinCount[i][0]*4, null);
						if(bombinCount[i][0] >= 10)
						{
							isBombing[i][0] = false;
							bombinCount[i][0] = 0;
						}
					}
					
					if(isShooting[i][1])
					{
						if(bulletY[i][1] < 800 && !isHitPlayer(i,1,manX,manY))
						{
							scr.drawImage(bullet_img,bulletX[i][1], bulletY[i][1], 20, 35, null);
							bulletY[i][1] += 8;
						}
						else
						{
							isShooting[i][1] = false;
							isBombing[i][1] = true;
						}
					}
					else if(isBombing[i][1])
					{
						bombinCount[i][1]++;
						scr.drawImage(bulletBomb_img,bulletX[i][1]-bombinCount[i][1]*2, bulletY[i][1]-bombinCount[i][1]*2, 35+bombinCount[i][1]*4, 35+bombinCount[i][1]*4, null);
						if(bombinCount[i][1] >= 10)
						{
							isBombing[i][1] = false;
							bombinCount[i][1] = 0;
						}
					}
					
					if(isShooting[i][2])
					{
						if(bulletY[i][2] < 800 && !isHitPlayer(i,2,manX,manY))
						{
							scr.drawImage(bullet_img,bulletX[i][2], bulletY[i][2], 20, 35, null);
							bulletY[i][2] += 8;
						}
						else
						{
							isShooting[i][2] = false;
							isBombing[i][2] = true;
						}
					}
					else if(isBombing[i][2])
					{
						bombinCount[i][2]++;
						scr.drawImage(bulletBomb_img,bulletX[i][2]-bombinCount[i][2]*2, bulletY[i][2]-bombinCount[i][2]*2, 35+bombinCount[i][2]*4, 35+bombinCount[i][2]*4, null);
						if(bombinCount[i][2] >= 10)
						{
							isBombing[i][2] = false;
							bombinCount[i][2] = 0;
						}
					}
				}
			}
		}
	}
	
	public boolean isHitPlayer(int Flying_num,int Bullet_num, int manX, int manY)//?????????????
	{
		if(isShooting[Flying_num][Bullet_num])
		{
			if(bulletX[Flying_num][Bullet_num] > manX-18 && bulletX[Flying_num][Bullet_num] < manX+48 
			&& bulletY[Flying_num][Bullet_num] > manY-32 && bulletY[Flying_num][Bullet_num] < manY+98)
			{
				System.out.println("BaLaLa hit Player");
				return true;
			}
		}
		return false;
	}
	
	public boolean isHitted(int num, int manBulletX, int manBulletY)//modify5
	{
		if(!isDead[num])
		{
			if(manBulletX > x[num]-25 && manBulletX < x[num]+95 && manBulletY > y[num]-25 && manBulletY < y[num]+100 )
			{
				HP[num] -= 10;
				if(HP[num] <= 0)
				{
					isDead[num] = true;
				}				
				return true;
			}
		}
		return false;
	}
}



