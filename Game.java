import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import java.io.*;
import javax.imageio.*;
import java.util.Random;
import java.awt.Point;
import java.awt.event.MouseListener;
import Data.ControlMan;
import Data.Ground;
import Data.Shielder;	//modify
import Data.Tank;		//modify
import Data.BombRobot;	//modify
import Data.FlyingBaLaLa;//modify2
import Data.Rule;
public class Game
{
	static FlyingBaLaLa flyingBaLaLa = new FlyingBaLaLa();	//modify2
	static Shielder shielder = new Shielder();	//modify
	static Tank tank = new Tank();				//modify
	static BombRobot bombRobot = new BombRobot(); //modify
	static MyPanel thePanel = new MyPanel();
	static ControlMan controlMan = new ControlMan();
	static Ground ground = new Ground();
	static Rule rule = new Rule();
	static int stageNum = 0;
	static int GameMode = 0;//0:選單 1:解說 2:遊戲開始 3:GameOver畫面
	static Random randomG;		//modify
    public static void main(String[] args)
    {
		randomG = new Random(1);
        thePanel.setSize(1500, 800);
		thePanel.setBackground(Color.gray);	//modify
		thePanel.addKeyListener(controlMan);		
        thePanel.setVisible(true);			
		thePanel.addWindowListener(new WindowAdapter() {public void windowClosing(WindowEvent event) {System.exit(0);}});	
		thePanel.addMouseListener(thePanel);	
		thePanel.addMouseListener(rule);
		controlMan.loadImgs();
		
		/* flyingBaLaLa.produceBaLaLa(0, 1400, 50);	//modify2
		flyingBaLaLa.produceBaLaLa(1, 800, 50);	//modify2
		flyingBaLaLa.produceBaLaLa(2, 0, 50);   //modify2
		flyingBaLaLa.produceBaLaLa(3, 1400, 50);	//modify2
		flyingBaLaLa.produceBaLaLa(4, 800, 50);	//modify2
		flyingBaLaLa.produceBaLaLa(5, 0, 50);   //modify2 */
		GameMode = 0;/////////
		
		thePanel.startTimer();	
		
    }
	
	static class MyPanel extends Frame implements MouseListener	//我自己的面板，用於繪圖和實現繪圖區域
	{
		private int timeCounter = 0;
		private Image iBuffer;
		private Graphics gBuffer;
		//===========================================DoubleBuffer===========================================
		//過載paint(Graphics scr)函式：
		private Image START_img = null, GAMERULES_img = null, QUIT_img = null, Cacher_img = null;
		private Color myColor;
		private Point point;
		private int[] moveX = new int[3];
		private boolean isOPing = false;
		private int WallMove = 0;
		MyPanel()
		{
			try {
				START_img = ImageIO.read(new File("STARTGAME.png"));
				GAMERULES_img = ImageIO.read(new File("GAMERULES.png"));
				QUIT_img = ImageIO.read(new File("QUIT.png"));
				Cacher_img = ImageIO.read(new File("Cacher.png"));
			} catch (IOException e) {
			}
			moveX[0] = 0;
			moveX[1] = 0;
			moveX[2] = 0;
		}
		
		
		public void paint(Graphics scr)
		{		
			if(GameMode == 0)
			{
				scr.drawImage(controlMan.WallAll_img, 0, 25, 1500, 775, null);
				if(timeCounter % 6 == 0)
				{
					myColor = new Color(randomG.nextInt(200)+55, randomG.nextInt(200)+55, randomG.nextInt(200)+55); // Color white	
				}
				scr.setColor(myColor);
				point = java.awt.MouseInfo.getPointerInfo().getLocation();
				//System.out.println("Px: " + this.getX() + "  MX: " + point.x);
				if(point.x - this.getX() > 1055 && point.y - this.getY() > 200 && point.y - this.getY() < 300)
				{
					if(moveX[0] >= -100)
					{
						moveX[0] += -5;
					}
					scr.fillRect(1155+moveX[0] ,205, 290, 90);				
				}
				else
				{
					if(moveX[0] < 0)
					{
						moveX[0] += 5;
					}
				}
				
				if(point.x - this.getX() > 1055 && point.y - this.getY() > 400 && point.y - this.getY() < 500)
				{
					if(moveX[1] >= -100)
					{
						moveX[1] += -5;
					}
					scr.fillRect(1155+moveX[1] ,405, 290, 90);				
				}
				else
				{
					if(moveX[1] < 0)
					{
						moveX[1] += 5;
					}
				}
				
				if(point.x - this.getX() > 1055 && point.y - this.getY() > 600 && point.y - this.getY() < 700)
				{
					if(moveX[2] >= -100)
					{
						moveX[2] += -5;
					}
					scr.fillRect(1155+moveX[2] ,605, 290, 90);				
				}
				else
				{
					if(moveX[2] < 0)
					{
						moveX[2] += 5;
					}
				}
				
				scr.drawImage(START_img, 1150+moveX[0], 200, 300, 100, null);
				scr.drawImage(GAMERULES_img, 1150+moveX[1], 400, 300, 100, null);
				scr.drawImage(QUIT_img, 1150+moveX[2], 600, 300, 100, null);
				scr.drawImage(Cacher_img, 1350, 162, 150, 174, null);
				scr.drawImage(Cacher_img, 1350, 362, 150, 174, null);
				scr.drawImage(Cacher_img, 1350, 562, 150, 174, null);
			}
			if(GameMode == 1)
			{
				GameMode = rule.show(scr);
			}
			if(GameMode == 2)
			{
				ground.drawGround(scr,stageNum);
				shielder.moveShielder(scr,controlMan.manX);		//modify
				tank.moveTank(scr,controlMan.manX);				//modify
				tank.moveCannon(scr,controlMan.manX,controlMan.manY);
				bombRobot.drawBombRobot(scr); //modify
				flyingBaLaLa.drawImg(scr,controlMan.manX,controlMan.manY); //modify2 modify4
				
				scr.drawImage(controlMan.nowMan_Img, controlMan.manX, controlMan.manY, controlMan.manW, controlMan.manH, null);
				controlMan.drawBullet(scr,thePanel.getWidth());  //modify3
			}
			if(GameMode == 3)
			{
				scr.drawImage(controlMan.WallAll_img, 0, 25, 1500, 775, null);
				scr.drawImage(controlMan.GAMEOVER_img, 100, 50, 1300, 200, null);
				if(timeCounter % 100 == 0)
				{
					GameMode =0;
				}
			}
			// if(isOPing)
			// {
				// scr.drawImage(WallRight_img,0, 25, 1500, 775, null);
				// scr.drawImage(WallLeft_img,0, 25, 1500, 775, null);
			// }
		}
		
		//過載update(Graphics scr)函式：
		public void update(Graphics scr)
		{
			if(iBuffer==null)
			{
				iBuffer=createImage(this.getSize().width,this.getSize().height);
				gBuffer=iBuffer.getGraphics();
			}
			gBuffer.setColor(getBackground());
			gBuffer.fillRect(0,0,this.getSize().width,this.getSize().height);
			paint(gBuffer);
			scr.drawImage(iBuffer,0,0,this);
		}
		//===========================================DoubleBuffer===========================================
		public void startTimer(){
			while(true)
			{
				try {
				Thread.sleep(15);
				}
					catch(InterruptedException e) {
				}
				thePanel.repaint();
				timeCounter ++;
				if(timeCounter > 1000)
				{
					timeCounter = 0;
				}
				
				if(GameMode == 2)
				{
					if(controlMan.DeadCount == 130)
					{
						GameMode = 3;
					}
				
					
					//------------------------------Man-------------------------------
					controlMan.ifInTheAir(ground.x,ground.y,ground.width,ground.appeared);
					controlMan.moveTheMan(thePanel.getWidth());
					if(timeCounter % 2 == 0)
					{
						controlMan.changeMove();			
					}
					  //----------------------------wether man is hitted---------------------
						for(int i = 0; i < 10; i++)
						{
						  controlMan.isHitted(bombRobot.isHitPlayer(i, controlMan.manX, controlMan.manY));
						}
						for(int i = 0; i < 10; i++)
						{
						   for(int j = 0; j < 3; j++)
						   {
								controlMan.isHitted(flyingBaLaLa.isHitPlayer(i, j, controlMan.manX, controlMan.manY));
						   }
						}
						for(int i = 0; i < 10; i++)
						{
						  controlMan.isHitted(tank.isHitPlayer(i));
						}
					  //----------------------------wether man is hitted---------------------
					
					//------------------------------Man-------------------------------
					
					//------------------------------bombRobot-------------------------------
					bombRobot.moveBombRobote(controlMan.manX, controlMan.manY, ground.x, ground.y, ground.width, ground.appeared);//modify
					if(timeCounter % 4 == 0)//modify
					{
						bombRobot.changeMove();	//modify
					}
					for(int i = 0 ; i < 10; i++)//modify4
					{
						if(controlMan.Shot[i])
						{
							for(int j = 0; j < 10; j++)
							{
								boolean buffer = bombRobot.isHitted(j, controlMan.bulletX[i], controlMan.bulletY[i]);
								controlMan.BulletIsHitted(i,buffer);
							}
						}
					}
					if (timeCounter % 200 == 0){
						if(randomG.nextInt(2) >= 1)
						{
							bombRobot.produceBombRobot(0, randomG.nextInt(500)+50);
						}
						else
						{
							bombRobot.produceBombRobot(1500, randomG.nextInt(500)+50);
						}							
					}
					//------------------------------bombRobot-------------------------------
					
					//------------------------------FlyingBaLaLa-------------------------------
					flyingBaLaLa.moveBaLaLa(controlMan.manX, controlMan.manY, ground.x, ground.y, ground.width, ground.appeared);//modify
					if(timeCounter % 4 == 0)//modify2
					{
						flyingBaLaLa.changeMove();	//modify2
					}
					for(int i = 0 ; i < 10; i++)//modify4
					{
						if(controlMan.Shot[i])
						{
							for(int j = 0; j < 10; j++)
							{
								boolean buffer = flyingBaLaLa.isHitted(j, controlMan.bulletX[i], controlMan.bulletY[i]);
								controlMan.BulletIsHitted(i,buffer);
							}
						}
					}
					if (timeCounter % 500 == 0){
						if(randomG.nextInt(2) >= 1)
						{
							flyingBaLaLa.produceBaLaLa(-100,50);		
						}
						else
						{
							flyingBaLaLa.produceBaLaLa(1500,50);	
						}
					}
					//------------------------------FlyingBaLaLa-------------------------------
					
					//------------------------------Tank---------------------------------------
					for(int i = 0; i < 10; i++)
					{
						if(controlMan.Shot[i])
						{
							for(int j = 0; j < 10; j++)
							{
								boolean buffer = tank.isHitted(j, controlMan.bulletX[i], controlMan.bulletY[i]);
								controlMan.BulletIsHitted(i,buffer);
							}
						}
					}
					if (timeCounter % 600 == 0){	//modify
						tank.addTank(controlMan.manX);
					}	
					//------------------------------Tank---------------------------------------
					
					//------------------------------Shielder---------------------------------------
					for(int i = 0; i < 10; i++)
					{
						if(controlMan.Shot[i])
						{
							for(int j = 0; j < 2; j++)
							{
								boolean buffer = shielder.isHitted(j, controlMan.bulletX[i], controlMan.bulletY[i]);
								controlMan.BulletIsHitted(i,buffer);
							}
						}
					}
									
					if (timeCounter % 3000 == 0){	//modify
						shielder.addShielder();		//modify
					}								//modify
					//------------------------------Shielder---------------------------------------
				}
				
			} 
		}
		
		
		public void mouseEntered(MouseEvent e){}
		public void mouseClicked(MouseEvent e)
		{
			if(GameMode == 0)
			{
				if(point.x - this.getX() > 1055 && point.y - this.getY() > 200 && point.y - this.getY() < 300)
				{
					controlMan.DeadCount = 0;
					controlMan.setStart();
					bombRobot.setStart();
					isOPing = true;
					GameMode = 2;
				}
				if(point.x - this.getX() > 1055 && point.y - this.getY() > 400 && point.y - this.getY() < 500)
				{
					GameMode = 1;	
				}
				if(point.x - this.getX() > 1055 && point.y - this.getY() > 600 && point.y - this.getY() < 700)
				{
					System.exit(0);			
				}
			}	
		}
		public void mouseExited(MouseEvent e){}
		public void mousePressed(MouseEvent e){
		}
		public void mouseReleased(MouseEvent e){}
	}
	
	

	
	
}


//Timer timer = new Timer();	 
//schedule(TimerTask task, long delay, long period)
//timer.schedule(new DrawTask(), 1000, 10);
//timer.cancel();

	/* static class ControlMan implements KeyListener {
	//-----------------------------------------------private-----------------------------------------------
		private int SpeedX = 7, SpeedY = 7, INIT_X_POS = 50, INIT_Y_POS = 600,mode = 1, jumpHight = 22;	
		private Image nowMan_Img = null;
		private Image[] theMan_Imgs = new Image[20];
		private int manImg_num = 0;
	//-----------------------------------------------private-----------------------------------------------
	//-----------------------------------------------public-----------------------------------------------
		public int  manX = INIT_X_POS,manY = INIT_Y_POS, manW = 55, manH = 100;
		public boolean leftPress = false, rightPress = false, upPress = false, jumping = false, downPress = false, inTheAir = false, facingRight = true;	
	//-----------------------------------------------public-----------------------------------------------
		
		ControlMan()
		{
			Gun theGun = new Gun();
		}
		
		
		//============================KeyEvent===============================
		public void keyPressed(KeyEvent e) {
			int key = e.getKeyCode();

			if(key == KeyEvent.VK_A) { 
				if(!leftPress){ manImg_num = 1; }
				if(!inTheAir){ facingRight = false; }
				leftPress  = true;
			}
			if(key == KeyEvent.VK_D) {
				if(!rightPress){manImg_num = 6;};	
				if(!inTheAir){ facingRight = true; }				
				rightPress = true;
			}
			if(key == KeyEvent.VK_W) {
				if(!inTheAir)//不在空中才能跳
				{
					jumping = true;
				}
			}
			if(key == KeyEvent.VK_S) {
				//manY += ((manY + imgH + SpeedY) <= thePanel.getHeight() ? SpeedY : (thePanel.getHeight() - manY - imgH));
			}
		}
		public void keyReleased(KeyEvent e) {
			int key = e.getKeyCode();
			//有作邊界判定
			if(key == KeyEvent.VK_A) { //如(圖片的X位置-移動位置數)小於0　則等同於超出邊界
				leftPress  = false;
			}
			if(key == KeyEvent.VK_D) { //如(圖片的X位置+圖片寬度+移動位置數)大於視窗寬度　則等同於超出邊界
				rightPress = false;
			}
		}
		public void keyTyped(KeyEvent e) {}
		//============================KeyEvent===============================
		
		public void ifInTheAir()
		{
			if(manY >= 600)
			{
				inTheAir = false;
			}
			else
			{
				inTheAir = true;
			}
		}
		
		public void moveTheMan(MyPanel thePanel)
		{
			if(leftPress)
			{
				manX -= ((manX - SpeedX) >= 0 ? SpeedX : manX);
			}
			else if(rightPress)
			{
				manX += ((manX + manW + SpeedX) <= thePanel.getWidth() ? SpeedX : (thePanel.getWidth() - manX - manW));
			}
			
			if(jumping)
			{
				manY -= jumpHight + 3;
				if(jumpHight >= 8)
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
				manImg_num++;
				if(manImg_num == 5)
				{
					manImg_num = 0;
				}		
				nowMan_Img = theMan_Imgs[manImg_num];
			}
			else if(rightPress)
			{
				manImg_num++;
				if(manImg_num == 10)
				{
					manImg_num = 5;
				}		
				nowMan_Img = theMan_Imgs[manImg_num];
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
		
		private class Gun implements KeyListener {//有在考慮是否搬出去成為獨立class但有可能會降低效率導致lag
			private int bulletX[10],bulletY[10];
			private Image bullet_Img = null;
			public boolean isShooting = false;
			public boolean Shot[10] = {false,false,false,false,false,false,false,false,false,false};
			public boolean ShootingTORight[10] = {false,false,false,false,false,false,false,false,false,false};
			public int nextBullet = 0;
			public int charger = 10;
			
			Gun(){
				try {
					bullet_Img = ImageIO.read(new File("bullet.png"));
				} catch (IOException e) {
				}
			}
			//============================KeyEvent===============================
			public void keyPressed(KeyEvent e) {
				int key = e.getKeyCode();
				if(key == KeyEvent.VK_J)
				{
					if(!isShooting && charger != 0)
					{
						charger--;
						bulletX[nextBullet] = manX;
						bulletY[nextBullet] = manY;
						Shot[nextBullet] = true;
						
						if(facingRight)
						{
							ShootingTORight[10] = true;
						}
						else
						{
							ShootingTORight[10] = false;
						}
						
						if(nextBullet != 10)
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
			public void keyReleased(KeyEvent e) {
				int key = e.getKeyCode();
				if(key == KeyEvent.VK_J)
				{
					isShooting = false;
				}
			}
			public void keyTyped(KeyEvent e) {}
			//============================KeyEvent===============================
			
			public void drawBullet(Graphics scr)
			{
				for(int i = 0; i < 10 ;i++)
				{
					if(Shot[i])
					{
						scr.drawImage(bullet_Img, bulletX[i], bulletY[i], 20, 20, null);
						if(ShootingTORight[i]){ bulletX[i] += 12; }
						else{ bulletX[i] -= 12; }
						if(bulletX[i] > thePanel.getWidth() || bulletX[i] < 0)
						{
							Shot[i] = false;
						}
					}
				}
			}
			
		}
	} */
