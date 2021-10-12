package AllTank;
import java.util.Vector;

import Bullet.Shot;

//我的坦克
public class Hero extends Tank
{

	//子弹类
	public Shot s=null;
	
	//增加子弹向量
	public Vector<Shot> ss=new Vector<Shot>();
	
	public Hero(int x, int y) {
		super(x, y);
	}
	
	//开火
	public void shotEnemy()
	{

		//坦克方向 0向上 1表示右  2表示下 3表示左
		switch(this.direct)
		{
		case 0:
			s=new Shot(x+10,y,0);
			ss.add(s);
			break;
		case 1:
			s=new Shot(x+30,y+10,1);
			ss.add(s);
			break;
		case 2:
			s=new Shot(x+10,y+30,2);
			ss.add(s);
			break;
		case 3:
			s=new Shot(x,y+10,3);
			ss.add(s);
			break;
		}
		
		// 	启动子弹线程
		Thread t=new Thread(s);
		t.start();
	}
	
	
	//坦克向上移动
	public void moveUp()
	{
		y-=speed;
	}
	
	//坦克向右移动
	public void moveRight()
	{
		x+=speed;
	}
	
	//坦克向下移动
	public void moveDown()
	{
		y+=speed;
	}
	
	//坦克向左移动
	public void moveLeft()
	{
		x-=speed;
	}
	
}
