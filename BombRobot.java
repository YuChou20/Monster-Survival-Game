package Data;
import java.awt.*;
import java.io.*;
import javax.imageio.*;


public class BombRobot {
	private int robotHight = 100, robotWidth = 150;
	public int[] x = new int[10], y = new int[10], width = new int[10], manImg_num = {0,0,0,0,0,0,0,0,0,0}, HP = {100,100,100,100,100,100,100,100,100,100};;
	public boolean[] appeared = {false,false,false,false,false,false,false,false,false,false},
					faceRight = {false,false,false,false,false,false,false,false,false,false},
					falling = {false,false,false,false,false,false,false,false,false,false},
					startAttack = {false,false,false,false,false,false,false,false,false,false},
					isDead = {false,false,false,false,false,false,false,false,false,false};
	private Image[] GaLaGaLa_Img = new Image[34], nowImg = new Image[10];
	
	public BombRobot()
	{	
		try {
			GaLaGaLa_Img[0] = ImageIO.read(new File("GALAGALA0.png"));
			GaLaGaLa_Img[1] = ImageIO.read(new File("GALAGALA1.png"));
			GaLaGaLa_Img[2] = ImageIO.read(new File("GALAGALA2.png"));
			GaLaGaLa_Img[3] = ImageIO.read(new File("GALAGALA3.png"));
			GaLaGaLa_Img[4] = ImageIO.read(new File("GALAGALA4.png"));
			GaLaGaLa_Img[5] = ImageIO.read(new File("GALAGALA_1.png"));
			GaLaGaLa_Img[6] = ImageIO.read(new File("GALAGALA_2.png"));
			GaLaGaLa_Img[7] = ImageIO.read(new File("GALAGALA_3.png"));
			GaLaGaLa_Img[8] = ImageIO.read(new File("GALAGALA_4.png"));
			GaLaGaLa_Img[9] = ImageIO.read(new File("GALAGALA_5.png"));
			GaLaGaLa_Img[10] = ImageIO.read(new File("GALAGALA_6.png"));
			GaLaGaLa_Img[11] = ImageIO.read(new File("GALAGALA_7.png"));
			GaLaGaLa_Img[12] = ImageIO.read(new File("GALAGALA_8.png"));
			GaLaGaLa_Img[13] = ImageIO.read(new File("BOOM1.png"));
			GaLaGaLa_Img[14] = ImageIO.read(new File("BOOM2.png"));
			GaLaGaLa_Img[15] = ImageIO.read(new File("BOOM3.png"));
			GaLaGaLa_Img[16] = ImageIO.read(new File("BOOM4.png"));
			GaLaGaLa_Img[17] = ImageIO.read(new File("BOOM5.png"));
			GaLaGaLa_Img[18] = ImageIO.read(new File("BOOM6.png"));
			GaLaGaLa_Img[19] = ImageIO.read(new File("BOOM7.png"));
			GaLaGaLa_Img[20] = ImageIO.read(new File("BOOM8.png"));
			GaLaGaLa_Img[21] = ImageIO.read(new File("GALAGALA0Right.png"));
			GaLaGaLa_Img[22] = ImageIO.read(new File("GALAGALA1Right.png"));
			GaLaGaLa_Img[23] = ImageIO.read(new File("GALAGALA2Right.png"));
			GaLaGaLa_Img[24] = ImageIO.read(new File("GALAGALA3Right.png"));
			GaLaGaLa_Img[25] = ImageIO.read(new File("GALAGALA4Right.png"));
			GaLaGaLa_Img[26] = ImageIO.read(new File("GALAGALA_1Right.png"));
			GaLaGaLa_Img[27] = ImageIO.read(new File("GALAGALA_2Right.png"));
			GaLaGaLa_Img[28] = ImageIO.read(new File("GALAGALA_3Right.png"));
			GaLaGaLa_Img[29] = ImageIO.read(new File("GALAGALA_4Right.png"));
			GaLaGaLa_Img[30] = ImageIO.read(new File("GALAGALA_5Right.png"));
			GaLaGaLa_Img[31] = ImageIO.read(new File("GALAGALA_6Right.png"));
			GaLaGaLa_Img[32] = ImageIO.read(new File("GALAGALA_7Right.png"));
			GaLaGaLa_Img[33] = ImageIO.read(new File("GALAGALA_8Right.png"));
		} catch (IOException e) {
		}
	}
	
	public void setStart()
	{
		for(int i = 0 ;i < 10;i++)
		{
			HP[i] = 100;
			appeared[i] = false;
			falling[i] = false;
			startAttack[i] = false;
			isDead[i] = false;

		}
	}
	
	public void produceBombRobot(int X, int Y)
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
		falling[num] = false;
		startAttack[num] = false;
		isDead[num] = false;
		x[num] = X;
		y[num] = Y;
		
	}
	
	public void moveBombRobote(int manX, int manY, int[] groundX,int[] groundY,int[] groundWidth,boolean[] groundAppeared)
	{
		for(int i = 0; i < 10; i++)
		{
			if(appeared[i])
			{
				if(!startAttack[i])
				{
					if(!falling[i])
					{
						if(!faceRight[i])
						{
							x[i] -= 5;
							if(x[i] < 0)
							{
								faceRight[i] = true;
								manImg_num[i] = 22;
							}
							if(manX > x[i]-50-60 && manX < x[i]+175 && manY > y[i]-80 && manY < y[i]+350)
							{
								falling[i] = true;
							}
						}
						else
						{
							x[i] += 5;
							if(x[i] > 1350)
							{
								faceRight[i] = false;
								manImg_num[i] = 1;
							}
							if(manX > x[i]-25-60 && manX < x[i]+200 && manY > y[i]-80 && manY < y[i]+350)
							{
								falling[i] = true;
							}
						}
					}
					else
					{
						y[i] += 8;
						int robotFoot = y[i]+robotHight;
						for(int j = 0; j < 5;j ++)
						{
							if(groundAppeared[j])
							{		
								if(!faceRight[i])
								{
									if(x[i]+45 > groundX[j] && x[i]+30 < groundX[j]+groundWidth[j] && robotFoot >= groundY[j] && robotFoot < groundY[j]+20)
									{
										y[i] = groundY[j]-robotHight;
										falling[i] = false;
										startAttack[i] = true;
										break;
									}
								}
								else
								{
									if(x[i]+120 > groundX[j] && x[i]+105 < groundX[j]+groundWidth[j] && robotFoot >= groundY[j] && robotFoot < groundY[j]+20)
									{
										y[i] = groundY[j]-robotHight;
										falling[i] = false;
										startAttack[i] = true;
										break;
									}
								}
							}
						}
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
				if(!startAttack[i])
				{
					if(!falling[i])
					{
						if(!faceRight[i])
						{
							manImg_num[i]++;
							if(manImg_num[i] >= 5)
							{
								manImg_num[i] = 1;
							}		
							nowImg[i] = GaLaGaLa_Img[manImg_num[i]];
						}
						else
						{
							manImg_num[i]++;
							if(manImg_num[i] >= 26)
							{
								manImg_num[i] = 22;
							}		
							nowImg[i] = GaLaGaLa_Img[manImg_num[i]];
						}
					}
					else
					{
						if(!faceRight[i])
						{
							nowImg[i] = GaLaGaLa_Img[0];
							manImg_num[i] = 4;
						}
						else
						{
							nowImg[i] = GaLaGaLa_Img[21];
							manImg_num[i] = 25;
						}
						
					}
				}
				else
				{
					nowImg[i] = GaLaGaLa_Img[manImg_num[i]];
					manImg_num[i]++;
					if(manImg_num[i] == 34)//¦³ÂI¤p½ÆÂø
					{
						manImg_num[i] = 13;
					}
					if(manImg_num[i] == 14)
					{
						isDead[i] = true;
					}
					if(manImg_num[i] == 21)
					{
						appeared[i] = false;
					}				
				}
			}
		}
	}
	
	public void drawBombRobot(Graphics scr)
	{
		for(int i = 0; i < 10; i++)
		{
			if(appeared[i])
			{
				if(!startAttack[i])
				{
					scr.drawImage(nowImg[i],x[i], y[i], robotWidth, robotHight, null);
				}
				else
				{
					if(!isDead[i])
					{
						scr.drawImage(nowImg[i],x[i], y[i], robotWidth, robotHight, null);
					}
					else
					{
						if(!faceRight[i])
						{
							scr.drawImage(nowImg[i],x[i]-125, y[i]-175, 350, 350, null);
						}
						else
						{
							scr.drawImage(nowImg[i],x[i]-50, y[i]-175, 350, 350, null);
						}
					}
				}
			}
		}
	}
	
	public boolean isHitPlayer(int num, int manX, int manY)
	{
		if(isDead[num] && appeared[num])
		{
			if(!faceRight[num])
			{
				if(manX > x[num]-50-60 && manX < x[num]+175 && manY > y[num]-200 && manY < y[num]+100)
				{				
					System.out.println("Bomb hit Player");
					return true;
				}
			}
			else
			{
				if(manX > x[num]-25-60 && manX < x[num]+200 && manY > y[num]-200 && manY < y[num]+100)
				{
					System.out.println("Bomb hit Player");
					return true;
				}
			}
		}
		return false;
	}
	
	public boolean isHitted(int num, int manBulletX, int manBulletY)
	{
		if(appeared[num])
		{
			if(!faceRight[num])
			{
				if(manBulletX > x[num]-25 && manBulletX < x[num]+120 && manBulletY > y[num]-25 && manBulletY < y[num]+90 )
				{
					HP[num] -=26;
					if(HP[num] <= 0)
					{
						falling[num] = true;
					}					
					return true;
				}
			}
			else
			{
				if(manBulletX > x[num] && manBulletX < x[num]+145 && manBulletY > y[num]-25 && manBulletY < y[num]+90 )
				{
					HP[num]-=26;
					if(HP[num] <= 0)
					{
						falling[num] = true;
					}	
					return true;
				}
			}
		}
		return false;
	}
}



