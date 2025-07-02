package Data;
import java.awt.*;
import java.io.*;
import javax.imageio.*;
import java.awt.event.*;
import java.awt.Point;

public class Rule extends Frame implements MouseListener{
	private Image[] tank_Imgs = new Image[6]  , bullet_Imgs = new Image[2] , run_Imgs = new Image[5],
			boomer_Imgs = new Image[20] , next_Imgs = new Image[4] , page_Imgs = new Image[2] , flying_Imgs = new Image[4],
			shielder_Imgs = new Image[3] , jump_Imgs = new Image[2] , word_Imgs = new Image[6];
	private int page = 0 , index = 0 , timer = 0,tmp = 0 ,gamemode = 1;
	private Image gr_Imgs , back_Imgs;
	private Point point;
	public Rule(){
		try {
				
				next_Imgs[0] = ImageIO.read(new File("left.png"));
				next_Imgs[1] = ImageIO.read(new File("left_select.png"));
				next_Imgs[2] = ImageIO.read(new File("right.png"));
				next_Imgs[3] = ImageIO.read(new File("right_select.png"));
				page_Imgs[0] = ImageIO.read(new File("page.png"));
				page_Imgs[1] = ImageIO.read(new File("page_select.png"));
				jump_Imgs[0] = ImageIO.read(new File("people1_right.png"));
				jump_Imgs[1] = ImageIO.read(new File("people_jump_right.png"));
				back_Imgs = ImageIO.read(new File("BACK.png"));
				gr_Imgs = ImageIO.read(new File("GAMERULES.png"));
				for( int i = 0 ; i < 5 ; ++i){
					run_Imgs[i] = ImageIO.read(new File("people0" + i + "_right.png"));
				}
				for(int i = 0 ; i < 3 ; ++i){
					shielder_Imgs[i] = ImageIO.read(new File("shielder" + i + ".png"));
				}
				for(int i = 1 ; i <= 4 ; ++i){
					flying_Imgs[i-1] = ImageIO.read(new File("flyingBaLaLa" + i + ".png"));
					boomer_Imgs[i-1] = ImageIO.read(new File("GALAGALA" + i + "Right.png"));
				}
				for(int i = 1 ; i <= 8 ; ++i){
					boomer_Imgs[i+3] = ImageIO.read(new File("GALAGALA_" + i + "Right.png"));
				}
				for(int i = 1 ; i <= 8 ; ++i){
					boomer_Imgs[i+11] = ImageIO.read(new File("BOOM" + i + ".png"));
				}
				for(int i = 0 ; i < 6 ; ++i){
					tank_Imgs[i] = ImageIO.read(new File("Tank" + i + ".png"));
					word_Imgs[i] = ImageIO.read(new File("word" + i + ".png"));
				}
			} catch (IOException e) {}
	}
	public int show(Graphics scr){
		++timer;
		if(timer == 100000){
			timer = 0;
		}
		if(timer % 5 == 0){
			++index;
		}
		scr.drawImage(gr_Imgs,500,60,500,120, null);
		scr.drawImage(back_Imgs,1200,600,300,200, null);
		point = point = java.awt.MouseInfo.getPointerInfo().getLocation();
		int locx = point.x - this.getX() , locy = point.y - this.getY();
		
		System.out.println( "MX: " + locx + " MY: "+ locy);
		if( locx >= 80 && locx <= 180 && locy >= 400 && locy <= 500){
			scr.drawImage(next_Imgs[1],80,400,100,100, null);
		}else {
			scr.drawImage(next_Imgs[0],80,400,100,100, null);
		}
		if( locx >= 820 && locx <= 920 && locy >= 400 && locy <= 500){
			scr.drawImage(next_Imgs[3],820,400,100,100, null);
		}else{
			scr.drawImage(next_Imgs[2],820,400,100,100, null);
		}
		
		for(int i = 0 ; i < 6 ; ++i){
			if (page == i){
				scr.drawImage(page_Imgs[1],300 + 80 * i,650,30,30, null);
			}else{
				scr.drawImage(page_Imgs[0],300 + 80 * i,650,30,30, null);
			}
		}
		if (page == 0){
			if (index == 5){
				index = 0;
			}
			scr.drawImage(word_Imgs[page],1000,350,400,260, null);
			scr.drawImage(run_Imgs[index],400,220,180,360, null);
		}else if(page == 1){
			scr.drawImage(word_Imgs[page],950,250,500,360, null);
			if (index > 40){
				index = 0;
			}
			if (index <= 10){
				scr.drawImage(jump_Imgs[0],480,460,60,120, null);
				scr.drawImage(jump_Imgs[0],480,460,60,120, null);
			}else if(index <= 25){
				scr.drawImage(jump_Imgs[1],480,460-(index-10)*15,60,120, null);
			}else if(index <= 40){
				scr.drawImage(jump_Imgs[1],480,235+(index-25)*15,60,120, null);
			}
		}
		else if(page == 2){
			scr.drawImage(word_Imgs[page],950,250,500,360, null);
			tmp = index / 2;
			if(tmp > 25){
				tmp = 0;
				index = 0;
			}
			if(tmp <= 3){
				scr.drawImage(boomer_Imgs[tmp],380,220,200,190, null);
			}else if(tmp <= 8){
				scr.drawImage(boomer_Imgs[3],380,220+(tmp-3)*30,200,190, null);
			}else if(tmp <= 16){
				tmp = tmp / 2 + 3;
				scr.drawImage(boomer_Imgs[tmp],380,400,200,190, null);
				if(tmp == 11){
					scr.drawImage(boomer_Imgs[12],280,250,450,390, null);
				}
			}else if(tmp <= 24){
				tmp = tmp - 17;
				scr.drawImage(boomer_Imgs[tmp+12],280,250,450,390, null);
			}
		}
		else if(page == 3){
			scr.drawImage(word_Imgs[page],950,250,500,360, null);
			if(index > 86){
				index = 0;
			}
			if(index <= 20){
				scr.drawImage(shielder_Imgs[0],380,180,280,200, null);
			}else if (index <= 60){
				scr.drawImage(shielder_Imgs[1],380,180 + (index - 20) * 5 ,280,200, null);
			}else if(index <= 85){
				scr.drawImage(shielder_Imgs[2],380,380,280,200, null);
			}
		}
		else if(page == 4){
			scr.drawImage(word_Imgs[page],950,250,500,360, null);
			if(index == 6){
				index = 0;
			}
			scr.drawImage(tank_Imgs[index],400,220,200,360, null);
		}else if(page == 5){
			scr.drawImage(word_Imgs[page],950,250,500,360, null);
			if(index == 4){
				index = 0;
			}
			scr.drawImage(flying_Imgs[index],400,320,200,200, null);
		}
		return gamemode;
	}
	public void mouseEntered(MouseEvent e){}
	public void mouseClicked(MouseEvent e){
		point = point = java.awt.MouseInfo.getPointerInfo().getLocation();
		int locx = point.x - this.getX() , locy = point.y - this.getY();
		System.out.println(page);
		if( locx >= 80 && locx <= 180 && locy >= 400 && locy <= 500){
			System.out.println( "page--" );
			--page;
			index = 0;
			timer = 0;
			if(page == -1){
				page = 5;
			}
		}
		if( locx >= 820 && locx <= 920 && locy >= 400 && locy <= 500){
			System.out.println( "page++" );
			++page;
			index = 0;
			timer = 0;
			if(page == 6){
				page = 0;
			}
		}
		if( locx >= 1200 && locx <= 1500 && locy >= 600 && locy <= 800){
			gamemode = 0;
		}
	}
	public void mouseExited(MouseEvent e){}
	public void mousePressed(MouseEvent e){}
	public void mouseReleased(MouseEvent e){
	}
}