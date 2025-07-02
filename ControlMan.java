package Data;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import javax.imageio.*;
public class ControlMan implements KeyListener {
	//-----------------------------------------------private-----------------------------------------------
		private int SpeedX = 7, SpeedY = 7, INIT_X_POS = 50, INIT_Y_POS = 550,mode = 1, jumpHight = 22;	
		private Image[] theMan_Imgs = new Image[20];
		private int manImg_num = 0;
	//-----------------------------------------------private-----------------------------------------------
	//-----------------------------------------------public-----------------------------------------------
		public int  manX = INIT_X_POS,manY = INIT_Y_POS, manW = 60, manH = 120, Life = 3, invincible = 0, splatUpCount = 0, splatRCount = 0, splatLCount = 0;
		public boolean leftPress = false, rightPress = false, upPress = false, jumping = false, downPress = false, inTheAir = false, facingRight = true;
		public Image nowMan_Img = null, heart = null, invincible_img = null, DeadHead_img = null, WallLeft_img = null, WallRight_img = null, WallAll_img = null, GAMEOVER_img = null;
		public boolean isDead = false;
		public int DeadCount = 0;
	//-----------------------------------------------public-----------------------------------------------
		
		public ControlMan()
		{
			try {
				bullet_Img = ImageIO.read(new File("bullet.png"));
			} catch (IOException e) {
			}
		}

		
		
		//============================KeyEvent===============================
		public void keyPressed(KeyEvent e) {
			int key = e.getKeyCode();
			if(Life > 0)
			{
				if(key == KeyEvent.VK_A) { 
					if(!leftPress){ manImg_num = 1; }
					//if(!inTheAir){ facingRight = false; }
					leftPress  = true; 
					rightPress = false;
				}
				if(key == KeyEvent.VK_D) {
					if(!rightPress){ manImg_num = 6; }
					//if(!inTheAir){ facingRight = true; }
					leftPress  = false; 
					rightPress = true;
				}
				if(key == KeyEvent.VK_W) {
					upPress = true;
				}
				
				if(key == KeyEvent.VK_S) {
					//manY += ((manY + imgH + SpeedY) <= thePanel.getHeight() ? SpeedY : (thePanel.getHeight() - manY - imgH));
				}
				
				//跳躍
				if(key == KeyEvent.VK_K) {
					if(!inTheAir)//不在空中才能跳
					{
						jumping = true;
					}
				}
				
				if(key == KeyEvent.VK_J)
				{
					//System.out.println("x:" + manX + "    y:" + manY + "   manImg_num:" + manImg_num + "    charger:" + charger);
					if(!isShooting && charger > 10 && !tooHot)
					{
						charger -= 30;
						Shot[nextBullet] = true;
						
						if(upPress)
						{
							bulletX[nextBullet] = manX+15;//bullet initional must set here!
							bulletY[nextBullet] = manY-30+20;
							ShootingTOUp[nextBullet] = true;
							splatUpCount = 3;
						}
						else if(facingRight)
						{
							bulletX[nextBullet] = manX+60-20;
							bulletY[nextBullet] = manY+70;
							ShootingTORight[nextBullet] = true;
							ShootingTOUp[nextBullet] = false;
							splatRCount = 3;
						}
						else
						{
							bulletX[nextBullet] = manX-30+20;
							bulletY[nextBullet] = manY+70;
							ShootingTORight[nextBullet] = false;
							ShootingTOUp[nextBullet] = false;
							splatLCount = 3;
						}
						
						if(nextBullet != 9)
						{
							nextBullet++;
						}
						else
						{
							nextBullet = 0;
						}
					}
					isShooting = true;
				}
			}
		}
		public void keyReleased(KeyEvent e) {
			int key = e.getKeyCode();
			/*有作邊界判定*/
			if(key == KeyEvent.VK_A) { //如(圖片的X位置-移動位置數)小於0　則等同於超出邊界
				leftPress  = false;
			}
			if(key == KeyEvent.VK_D) { //如(圖片的X位置+圖片寬度+移動位置數)大於視窗寬度　則等同於超出邊界
				rightPress = false;
			}
			if(key == KeyEvent.VK_W) {
				upPress = false;
			}
			if(key == KeyEvent.VK_J) { //放開J才可再次射擊
				isShooting = false;
			}
		}
		public void keyTyped(KeyEvent e) {}
		//============================KeyEvent===============================
		
		public void ifInTheAir(int[] groundX,int[] groundY,int[] groundWidth,boolean[] groundAppeared)
		{
			int manFoot = manY+manH;
			if(manY >= 600)
			{
				inTheAir = false;
				return;
			}
			for(int i = 0; i < 5;i ++)
			{
				if(groundAppeared[i])
				{			
					if(manX+45 > groundX[i] && manX+15 < groundX[i]+groundWidth[i] && manFoot >= groundY[i] && manFoot < groundY[i]+20 && !jumping)
					{
						manY = groundY[i]-manH;
						inTheAir = false;
						return;
					}
				}
			}
			
			inTheAir = true;
		}
		
		public void moveTheMan(int PanelWidth)
		{
			if(leftPress)
			{
				manX -= ((manX - SpeedX) >= 0 ? SpeedX : manX);
			}
			else if(rightPress)
			{
				manX += ((manX + manW + SpeedX) <= PanelWidth ? SpeedX : (PanelWidth - manX - manW));
			}
			
			if(jumping)
			{
				manY -= jumpHight + 7;
				if(jumpHight >= -4)
				{
					jumpHight -= 1;
				}
				else
				{
					jumping = false;
					jumpHight = 22;
				}
			}
			
			if(inTheAir)
			{
				manY += 8;
			}
			
			if(invincible != 0)
			{
				invincible--;
			}
			
			if(Life <= 0 && DeadCount != 130)
			{
				DeadCount++;
			}
		}
		public void loadImgs() 
		{
			try {
				theMan_Imgs[0] = ImageIO.read(new File("people00.png"));
				theMan_Imgs[1] = ImageIO.read(new File("people01.png"));
				theMan_Imgs[2] = ImageIO.read(new File("people02.png"));
				theMan_Imgs[3] = ImageIO.read(new File("people03.png"));
				theMan_Imgs[4] = ImageIO.read(new File("people04.png"));
				theMan_Imgs[5] = ImageIO.read(new File("people00_right.png"));
				theMan_Imgs[6] = ImageIO.read(new File("people01_right.png"));
				theMan_Imgs[7] = ImageIO.read(new File("people02_right.png"));
				theMan_Imgs[8] = ImageIO.read(new File("people03_right.png"));
				theMan_Imgs[9] = ImageIO.read(new File("people04_right.png"));
				theMan_Imgs[10] = ImageIO.read(new File("people_jump.png"));
				theMan_Imgs[11] = ImageIO.read(new File("people_jump_right.png"));
				theMan_Imgs[12] = ImageIO.read(new File("people1.png"));
				theMan_Imgs[13] = ImageIO.read(new File("people1_right.png"));
				gunEnergy_Img[0] = ImageIO.read(new File("gunEnergy_green.png"));
 				gunEnergy_Img[1] = ImageIO.read(new File("gunEnergy_yellow.png"));
				gunEnergy_Img[2] = ImageIO.read(new File("gunEnergy_red.png"));
				gunEnergy_Img[3] = ImageIO.read(new File("gunEnergy_Darkred.png"));
				gunChargeBar = ImageIO.read(new File("gunChargeBar.png"));
				heart = ImageIO.read(new File("heart.png"));
				invincible_img = ImageIO.read(new File("protect.png"));
				gunSplatUp_img = ImageIO.read(new File("splatUP.png"));
				gunSplatR_img = ImageIO.read(new File("splatR.png"));
				gunSplatL_img = ImageIO.read(new File("splatL.png"));
				DeadHead_img = ImageIO.read(new File("DeadHead.png"));
				WallLeft_img = ImageIO.read(new File("WallLeft.png"));
				WallRight_img = ImageIO.read(new File("WallRight.png"));
				WallAll_img = ImageIO.read(new File("WallAll.png"));
				GAMEOVER_img = ImageIO.read(new File("GAMEOVER.png"));
			} catch (IOException e) {
			}
		}
		
		public void changeMove() 
		{
			if(inTheAir || jumping)
			{
				if(facingRight)
				{
					nowMan_Img = theMan_Imgs[11];
				}
				else
				{
					nowMan_Img = theMan_Imgs[10];
				}
				
			}
			else if(leftPress)
			{
				if(!inTheAir){ facingRight = false; }
				if(!facingRight)//沒有這個left 和 right可能會一起做
				{
					manImg_num++;
					if(manImg_num == 5)
					{
						manImg_num = 0;
					}		
					nowMan_Img = theMan_Imgs[manImg_num];
				}
			}
			else if(rightPress)
			{
				if(!inTheAir){ facingRight = true; }
				if(facingRight)
				{
					manImg_num++;
					if(manImg_num == 10)
					{
						manImg_num = 5;
					}		
					nowMan_Img = theMan_Imgs[manImg_num];
				}
			}
			else
			{
				if(facingRight)
				{
					nowMan_Img = theMan_Imgs[13];
				}
				else
				{
					nowMan_Img = theMan_Imgs[12];
				}
			}
		}
		
		public void isHitted(boolean youAreKilled)
		{
			if(youAreKilled)
			{
				if(invincible <= 0)
				{
					System.out.println("is heart");
					Life--;
					if(Life == 0)
					{
						isDead = true;
					}
					else
					{
						invincible = 100;//give invincible time 100
					}
				}
				
			}
		}
		
		public void printData()
		{
			System.out.println("x:" + manX + "    y:" + manY + "   manImg_num:" + manImg_num + "    jumpHight:" + jumpHight);
		}
		
		//======================================================GUN==========================================================
		public int[] bulletX = new int[10],bulletY = new int[10];
		private Image bullet_Img = null, gunChargeBar = null, gunSplatUp_img = null, gunSplatR_img = null, gunSplatL_img = null;
		private Image[] gunEnergy_Img = new Image[4];
		public boolean isShooting = false,//不讓玩家按住J就可以連射
						tooHot = false;//過熱機制
		public boolean[] Shot = {false,false,false,false,false,false,false,false,false,false};
		public boolean[] ShootingTORight = {false,false,false,false,false,false,false,false,false,false},
						 ShootingTOUp = {false,false,false,false,false,false,false,false,false,false};
		public int nextBullet = 0;
		public int charger = 300;
		
		
		public void BulletIsHitted(int num, boolean isHittedSomething)
		{
			if(isHittedSomething)
			{
				Shot[num] = false;
			}
		}
		//======================================================GUN==========================================================
		
		public void drawBullet(Graphics scr, int PanelWidth)
		{
			//=====================================畫出剩餘血量 and invincible protect=======================================
			if(Life > 2)
			{
				scr.drawImage(heart,110, 50, 30, 30, null);
			}
			if(Life > 1)
			{
				scr.drawImage(heart,70, 50, 30, 30, null);
			}
			if(Life > 0)
			{
				scr.drawImage(heart,30, 50, 30, 30, null);
			}
			if(invincible > 0)
			{
				scr.drawImage(invincible_img ,manX-120, manY-90, 300, 300, null);
			}
			//=====================================畫出剩餘血量 and invincible protect=======================================

			//=====================================射擊子彈相關===============================================
			if(charger < 300){charger+=2;}
			else{tooHot = false;}//tooHot cancle
			for(int i = 0; i < 10 ;i++)
			{
				//System.out.println("X: " + bulletX[i] + " Y: " + bulletY[i] + " charger: " + charger + " nextBullet: "+nextBullet);
				if(Shot[i])
				{
					//----------------------------畫出子彈 與 移動---------------------------------
					//bulletX,Y initional in line 70
					if(ShootingTOUp[i])
					{
						bulletY[i] -= 20; 
					}
					else if(ShootingTORight[i]){
						bulletX[i] += 20; 
					}
					else{ 
						bulletX[i] -= 20; 
					}
					scr.drawImage(bullet_Img,bulletX[i], bulletY[i], 30, 30, null);
					//----------------------------畫出子彈 與 移動---------------------------------
					
					//----------------------------子彈是否消失-------------------------------------
					if(bulletX[i] > PanelWidth || bulletX[i] < 0 || bulletY[i] < -50)
					{
						Shot[i] = false;
					}
					//----------------------------子彈是否消失-------------------------------------
				}
			}
			//----------------------------開槍特效----------------------
			if(splatUpCount > 0)
			{
				splatUpCount--;
				scr.drawImage(gunSplatUp_img,manX+15, manY-20, 30, 30, null);
			}
			if(splatRCount > 0)
			{
				splatRCount--;
				scr.drawImage(gunSplatR_img,manX+60, manY+65, 30, 30, null);
			}
			if(splatLCount > 0)
			{
				splatLCount--;
				scr.drawImage(gunSplatL_img,manX-30, manY+65, 30, 30, null);
			}
			
			//----------------------------開槍特效----------------------
			//=====================================射擊子彈相關===============================================
			
			//=====================================槍的剩餘能量相關===============================================
			if(!tooHot)
			{
				if(charger > 200)
				{
					scr.drawImage(gunEnergy_Img[0],60, 80, charger/2, 25, null);
				}
				else if(charger > 100)
				{
					scr.drawImage(gunEnergy_Img[1],60, 80, charger/2, 25, null);
				}
				else if(charger > 0)
				{
					scr.drawImage(gunEnergy_Img[2],60, 80, charger/2, 25, null);
				}
				else
				{
					tooHot = true;
				}
			}
			else
			{
				scr.drawImage(gunEnergy_Img[3],60, 80, charger/2, 25, null);
			}
			
			
			scr.drawImage(gunChargeBar,30, 80, 200, 30, null);
			
			//=====================================槍的剩餘能量相關===============================================
			
			//=====================================死亡動畫===============================================
			if(Life <= 0 || invincible != 0)
			{
				scr.drawImage(DeadHead_img,manX+1, manY+3, 60, 80, null);
			}
			if(DeadCount != 0)
			{
				scr.drawImage(WallRight_img,650-DeadCount*5, 25, 1500, 775, null);
				scr.drawImage(WallLeft_img,-650+DeadCount*5, 25, 1500, 775, null);
			}
			
			//=====================================死亡動畫===============================================		
		}
		
		
		public void setStart()
		{
			manX = INIT_X_POS;manY = INIT_Y_POS; manW = 60; manH = 120; Life = 3; invincible = 0; splatUpCount = 0; splatRCount = 0; splatLCount = 0;DeadCount = 0;
		}
		
	}