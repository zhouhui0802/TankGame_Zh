package Main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Panel;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Vector;

import javax.swing.JPanel;

import AllTank.EnemyTank;
import AllTank.Hero;
import AllTank.Tank;
import Bullet.Bomb;
import Bullet.Shot;
import Recode.Node;
import Recode.Recorder;
import Util.AePlayWave;

//我的面板
class MyPanel extends JPanel implements KeyListener,Runnable
{
	//定义一个我的坦克
	Hero hero=null;
	
	//定义敌人的坦克数组
	Vector<EnemyTank> ets=new Vector<EnemyTank>();
	
	//定义敌人与自身的数据节点
	Vector<Node> nodes=new Vector<Node>();
	
	//初始化敌人坦克的数量
	int enSize=6;
	
	//定义炸弹的集合
	Vector<Bomb> bombs=new Vector<Bomb>();
	
	
	//定义三张图片
	Image image1=null;
	Image image2=null;
	Image image3=null;
	
	//构造函数
	public MyPanel(String flag)
	{
		System.out.println("进入MyPanel的构造函数");
		//定义自身坦克的位置
		hero=new Hero(100,100);		
		
		if(flag.equals("newGame"))
		{
			System.out.println("开始新的游戏");
			//初始化敌人的坦克
			for(int i=0;i<enSize;i++)
			{
				EnemyTank et=new EnemyTank((i+1)*50,0);
				et.setDirect(2);
				
				//将MyPanel的敌人数量交给该敌人坦克
				et.setEts(ets);
				
				//启动敌人线程
				Thread t=new Thread(et);
				t.start();
				
				//给敌人添加一颗子弹
				Shot s=new Shot(et.x+10,et.y+30,2);
				
				//加入敌人子弹容器中
				et.ss.add(s);
				Thread t2=new Thread(s);
				t2.start();
				
				//将敌人坦克加入到敌人坦克容器中
				ets.add(et);
			}
		}else
		{
			System.out.println("接着玩上一局的游戏");
			//从.txt中读取出保存的数据
			nodes=new Recorder().getNodesAndEnNums();
			for(int i=0;i<nodes.size();i++)
			{
				Node node=nodes.get(i);
				
				EnemyTank et=new EnemyTank(node.x,node.y);
				et.setDirect(node.direct);
				
				//将MyPanel的敌人数量交给该敌人坦克
				et.setEts(ets);
				
				//启动敌人线程
				Thread t=new Thread(et);
				t.start();
				
				//给敌人添加一颗子弹
				Shot s=new Shot(et.x+10,et.y+30,2);
				//加入敌人子弹容器中
				et.ss.add(s);
				Thread t2=new Thread(s);
				t2.start();
				
				//加入坦克数组容器中
				ets.add(et);
			}
		}
	
		image1=Toolkit.getDefaultToolkit().getImage(Panel.class.getResource("/bomb_1.gif"));
		image2=Toolkit.getDefaultToolkit().getImage(Panel.class.getResource("/bomb_2.gif"));
		image3=Toolkit.getDefaultToolkit().getImage(Panel.class.getResource("/bomb_3.gif"));
		
		//播放开战声音
		AePlayWave awp=new AePlayWave("Material/111.wav");
		awp.start();
	}
	
	//画出提示信息的坦克
	public void showInfo(Graphics g)
	{
		//System.out.println("画出提示信息的坦克");
		//画出敌人坦克的信息
		this.drawTank(80, 330, g, 0, 0);
		g.setColor(Color.black);
		g.drawString(Recorder.getEnNum()+"", 110, 350);
		//画出自身坦克的信息
		this.drawTank(130, 330, g, 0, 1);
		g.setColor(Color.black);
		g.drawString(Recorder.getMyLife()+"", 165, 350);
		
		//画出玩家的总成绩
		g.setColor(Color.black);
		Font f=new Font("宋体",Font.BOLD,20);
		g.setFont(f);
		g.drawString("您的总成绩", 420, 30);		
		this.drawTank(420, 60, g, 0, 0);		
		g.setColor(Color.black);
		g.drawString(Recorder.getAllEnNum()+"", 460, 80);
		//System.out.println("画出提示信息的坦克结束");
	}
	
	//重绘制
	public void paint(Graphics g)
	{
		super.paint(g);
		g.fillRect(0, 0, 400, 300);

		//画出提示信息
		this.showInfo(g);
		
		//画出自己的坦克
		if(hero.isLive)
		{
			this.drawTank(hero.getX(),hero.getY(), g,this.hero.direct, 0);
		}
		
		
		//从ss中取出每颗子弹，画出自身坦克子弹
		for(int i=0;i<hero.ss.size();i++)
		{
			Shot myShot=hero.ss.get(i);
			//画出每一颗子弹
			if(myShot!=null&&myShot.isLive==true)
			{
				g.draw3DRect(myShot.x, myShot.y,1, 1 ,false);
			}
			
			if(myShot.isLive==false)
			{
				//从ss中删除掉该子弹
				hero.ss.remove(myShot);
			}
		}
		
		//画出炸弹
		for(int i=0;i<bombs.size();i++)
		{
			System.out.println("bombs.size()="+bombs.size());
			//取出炸弹
			Bomb b=bombs.get(i);
			
			if(b.life>6)
			{
				g.drawImage(image1, b.x, b.y, 30,30,this);
			}else if(b.life>3)
			{
				g.drawImage(image2, b.x, b.y, 30,30,this);
			}else{
				g.drawImage(image3, b.x, b.y, 30,30,this);
			}
			System.out.println("b.life="+b.life);
			//让b的生命值减小
			b.lifeDown();
			
			if(b.life==0)
			{
				bombs.remove(b);
			}
			
		}
		
		//画出敌人的坦克
		for(int i=0;i<ets.size();i++)
		{
			EnemyTank et=ets.get(i);
			if(et.isLive)
			{
				this.drawTank(et.getX(), et.getY(), g, et.getDirect(), 1);
				//再画出敌人的子弹
				for(int j=0;j<et.ss.size();j++)
				{
					//取出子弹
					Shot enemyShot=et.ss.get(j);
					if(enemyShot.isLive)
					{
						g.draw3DRect(enemyShot.x, enemyShot.y, 1, 1, false);
					}else
					{
						et.ss.remove(enemyShot);
					}
				}
			}
			
		}
	}
	
	//判断敌人的子弹是否击中我
	public void hitMe()
	{
		//取出每一个敌人的坦克
		for(int i=0;i<this.ets.size();i++)
		{
			//取出坦克
			EnemyTank et=ets.get(i);
			
			//取出每一颗子弹
			for(int j=0;j<et.ss.size();j++)
			{
				//取出子弹
				Shot enemyShot=et.ss.get(j);
				if(hero.isLive)
				{
					if(this.hitTank(enemyShot, hero))
					{
						//减少敌人的数量
						Recorder.reduceEnNum();
						
						//增加我的记录
						Recorder.addEnNumRec();
					}
				}
			}
		}
	}
	
	
	//判断我的子弹是否击中坦克
	public void hitEnemyTank()
	{
		//判断是否击中敌人的坦克
		for(int i=0;i<hero.ss.size();i++)
		{
			//取出子弹
			Shot myShot=hero.ss.get(i);
			if(myShot.isLive)
			{
				//取出每个坦克
				for(int j=0;j<ets.size();j++)
				{
					//取出每个坦克，与它进行判断
					EnemyTank et=ets.get(j);
					
					if(et.isLive)
					{
						if(this.hitTank(myShot, et))
						{
							System.out.println("击中才调用");
							//减少敌人的数量
							Recorder.reduceEnNum();
							//增加我的记录
							Recorder.addEnNumRec();
						}
					}
				}
			}
		}
	}
	
	
	//写一个专门的函数判断子弹是否击中坦克
	public boolean hitTank(Shot s,Tank et)
	{
		boolean  b2=false;
		
		//判断坦克的方向
		switch(et.direct)
		{
		case 0:
		case 2:
			if(s.x>et.x&&s.x<et.x+20&&s.y>et.y&&s.y<et.y+30)
			{
				//击中
				//子弹死亡
				s.isLive=false;
				
				//敌人坦克死亡
				et.isLive=false;
				
				//减少敌人的数量
				b2=true;
				//创建一颗炸弹，放入Vector
				Bomb b=new Bomb(et.x,et.y);
				//放入Vector中
				bombs.add(b);
			}
			break;
		case 1:
		case 3:
			if(s.x>et.x&&s.x<et.x+30&&s.y>et.y&&s.y<et.y+20)
			{
				//击中
				//子弹死亡
				s.isLive=false;
				//敌人坦克死亡
				et.isLive=false;
				b2=true;
				//创建一颗炸弹，放入Vector
				Bomb b=new Bomb(et.x,et.y);
				//放入Vector中
				bombs.add(b);
			}
		}
		
		return b2;
	}
	
	//画出坦克的函数
	public void drawTank(int x,int y, Graphics g,int direct,int type)
	{
		//判断坦克类型
		switch(type)
		{
		case 0:
			g.setColor(Color.cyan);
			break;
		case 1:
			g.setColor(Color.yellow);
			break;
		}
		
		//判断坦克方向
		switch(direct)
		{
		//向上
		case 0:
			//画出我的坦克，封装成一个函数
			//画出左边的矩形
			g.fill3DRect(x, y, 5, 30,false);
			//画出右边的矩形
			g.fill3DRect(x+15, y, 5, 30,false);
			//画出中间的矩形
			g.fill3DRect(x+5,y+5, 10, 20,false);
			//画出圆形
			g.fillOval(x+5, y+10, 10, 10);
			//画出线段
			g.drawLine(x+10,y+15,x+10,y);
			break;
		case 1:
			//炮筒向右
			//画出上面的矩形
			g.fill3DRect(x, y, 30, 5,false);
			//画出下面的边的矩形
			g.fill3DRect(x, y+15, 30, 5,false);
			//画出中间的矩形
			g.fill3DRect(x+5,y+5, 20, 10,false);
			//画出圆形
			g.fillOval(x+10, y+5, 10, 10);
			//画出线段
			g.drawLine(x+15,y+10,x+30,y+10);
			break;
		case 2:
			//炮筒向下
			//画出左边的矩形
			g.fill3DRect(x, y, 5, 30,false);
			//画出右边的边的矩形
			g.fill3DRect(x+15, y, 5, 30,false);
			//画出中间的矩形
			g.fill3DRect(x+5,y+5, 10, 20,false);
			//画出圆形
			g.fillOval(x+5, y+10, 10, 10);
			//画出线段
			g.drawLine(x+10,y+15,x+10,y+30);
			break;
		case 3:
			//炮筒向左
			//画出左边的矩形
			g.fill3DRect(x, y, 30, 5,false);
			//画出右边的边的矩形
			g.fill3DRect(x, y+15, 30, 5,false);
			//画出中间的矩形
			g.fill3DRect(x+5,y+5, 20, 10,false);
			//画出圆形
			g.fillOval(x+10, y+5, 10, 10);
			//画出线段
			g.drawLine(x+15,y+10,x,y+10);
		}
		
		
	}

	@Override
	public void keyPressed(KeyEvent arg0) {
		// TODO Auto-generated method stub
		//设置我的坦克方向
		if(arg0.getKeyCode()==KeyEvent.VK_W)
		{
			//向上
			this.hero.setDirect(0);	
			this.hero.moveUp();
		}else if(arg0.getKeyCode()==KeyEvent.VK_D)
		{
			//向右
			this.hero.setDirect(1);
			this.hero.moveRight();
		}else if(arg0.getKeyCode()==KeyEvent.VK_S)
		{
			//向下
			this.hero.setDirect(2);
			this.hero.moveDown();
		}else if(arg0.getKeyCode()==KeyEvent.VK_A)
		{
			//向左
			this.hero.setDirect(3);
			this.hero.moveLeft();
		}else if(arg0.getKeyCode()==KeyEvent.VK_J)
		{
			//开火
			if(this.hero.ss.size()<=5)
			{
				this.hero.shotEnemy();
			}

		}
		
		//必须重新绘制panel
		this.repaint();
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		//每隔100毫秒去重绘制
		while(true)
		{
			try{
				Thread.sleep(100);
			}catch(Exception e)
			{
				
			}
			
			//判断我是否击中敌人的坦克
			this.hitEnemyTank();
			
			//判断敌人是否击中我
			this.hitMe();
				
			//重绘制
			this.repaint();
		}
	}
}


