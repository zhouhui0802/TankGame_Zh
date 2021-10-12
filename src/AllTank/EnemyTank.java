package AllTank;
import java.util.Vector;

import Bullet.Shot;

//敌人的坦克
public class EnemyTank extends Tank implements Runnable
{


	public int times=0;
	
	//定义一个向量，可以访问到MyPanel上所有的敌人坦克
	public Vector<EnemyTank> ets=new Vector<EnemyTank>();
	
	//定义一个向量，可以存放敌人的子弹
	public Vector<Shot> ss=new Vector<Shot>();

	
	public EnemyTank(int x, int y) {
		super(x, y);
	}
	
	//得到MyPanel的敌人的坦克向量
	public void setEts(Vector<EnemyTank> vv)
	{
		this.ets=vv;
	}
	
	//判断是否碰到了敌人的坦克
	public boolean  isTouchOtherEnemy()
	{
		//System.out.println("开始判断敌人之间是否碰撞");
		boolean b=false;
		
		switch(this.direct)
		{
		case 0:
			//我的坦克向上
			//取出所有的敌人坦克
			for(int i=0;i<ets.size();i++)
			{
				//取出第一个坦克
				EnemyTank et=ets.get(i);
				//如果不是自己
				if(et!=this)
				{
					//如果敌人的方向是向下或者向上
					if(et.direct==0||et.direct==2)
					{
						if(this.x>=et.x&&this.x<=et.x+20&&this.y>=et.y&&this.y<=et.y+30)
						{
							return true;
						}
						if(this.x+20>=et.x&&this.x+20<=et.x+20&&this.y>=et.y&&this.y<=et.y+30)
						{
							return true;
						}
					}
					if(et.direct==3||et.direct==1)
					{
						if(this.x>=et.x&&this.x<=et.x+30&&this.y>=et.y&&this.y<=et.y+20)
						{
							return true;
						}
						if(this.x+20>=et.x&&this.x+20<=et.x+30&&this.y>=et.y&&this.y<=et.y+20)
						{
							return true;
						}
					}
				}
			}
			break;
		case 1:
			//坦克向右
			//取出所有的敌人坦克
			for(int i=0;i<ets.size();i++)
			{
				//取出第一个坦克
				EnemyTank et=ets.get(i);
				//如果不是自己
				if(et!=this)
				{
					//如果敌人的方向是向下或者向上
					if(et.direct==0||et.direct==2)
					{
						if(this.x+30>=et.x&&this.x+30<=et.x+20&&this.y>=et.y&&this.y<=et.y+30)
						{
							return true;
						}
						if(this.x+30>=et.x&&this.x+30<=et.x+20&&this.y+20>=et.y&&this.y+20<=et.y+30)
						{
							return true;
						}
					}
					if(et.direct==3||et.direct==1)
					{
						if(this.x+30>=et.x&&this.x+30<=et.x+30&&this.y>=et.y&&this.y<=et.y+20)
						{
							return true;
						}
						if(this.x+30>=et.x&&this.x+30<=et.x+30&&this.y+20>=et.y&&this.y+20<=et.y+20)
						{
							return true;
						}
					}
				}
			}
			break;
		case 2:
			//坦克向下
			//取出所有的敌人坦克
			for(int i=0;i<ets.size();i++)
			{
				//取出第一个坦克
				EnemyTank et=ets.get(i);
				//如果不是自己
				if(et!=this)
				{
					//如果敌人的方向是向下或者向上
					if(et.direct==0||et.direct==2)
					{
						if(this.x>=et.x&&this.x<=et.x+20&&this.y+30>=et.y&&this.y+30<=et.y+30)
						{
							return true;
						}
						if(this.x+20>=et.x&&this.x+20<=et.x+20&&this.y+30>=et.y&&this.y+30<=et.y+30)
						{
							return true;
						}
					}
					if(et.direct==3||et.direct==1)
					{
						if(this.x>=et.x&&this.x<=et.x+30&&this.y+30>=et.y&&this.y+30<=et.y+20)
						{
							return true;
						}
						if(this.x+20>=et.x&&this.x+20<=et.x+30&&this.y+30>=et.y&&this.y+30<=et.y+20)
						{
							return true;
						}
					}
				}
			}
			break;
		case 3:
			//坦克向左
			//取出所有的敌人坦克
			for(int i=0;i<ets.size();i++)
			{
				//取出第一个坦克
				EnemyTank et=ets.get(i);
				//如果不是自己
				if(et!=this)
				{
					//如果敌人的方向是向下或者向上
					if(et.direct==0||et.direct==2)
					{
						if(this.x>=et.x&&this.x<=et.x+20&&this.y>=et.y&&this.y<=et.y+30)
						{
							return true;
						}
						if(this.x>=et.x&&this.x<=et.x+20&&this.y+20>=et.y&&this.y+20<=et.y+30)
						{
							return true;
						}
					}
					if(et.direct==3||et.direct==1)
					{
						if(this.x>=et.x&&this.x<=et.x+30&&this.y>=et.y&&this.y<=et.y+20)
						{
							return true;
						}
						if(this.x>=et.x&&this.x<=et.x+30&&this.y+20>=et.y&&this.y+20<=et.y+20)
						{
							return true;
						}
					}
				}
			}
			break;
		}
		//System.out.println("判断敌人之间是否碰撞结束");
		return b;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		while(true)
		{
			//开启敌人坦克的线程，让其自动移动
			switch(this.direct)
			{
			case 0:
				//说明坦克正在向上
				for(int i=0;i<30;i++)
				{
					if(y>0&&!this.isTouchOtherEnemy())
					{
						y-=speed;
					}
					try{
						Thread.sleep(50);
					}catch(Exception e)
					{
						e.printStackTrace();
					}
					
				}
				break;
			case 1:
				//向右
				for(int i=0;i<30;i++)
				{
					if(x<400&&!this.isTouchOtherEnemy())
					{
						x+=speed;
					}

					try{
						Thread.sleep(50);
					}catch(Exception e)
					{
						e.printStackTrace();
					}
					
				}
				break;
			case 2:
				//向下
				for(int i=0;i<30;i++)
				{
					if(y<300&&!this.isTouchOtherEnemy())
					{
						y+=speed;
					}
					try{
						Thread.sleep(50);
					}catch(Exception e)
					{
						e.printStackTrace();
					}
					
				}
				break;
			case 3:
				//向左
				for(int i=0;i<30;i++)
				{
					if(x>0&&!this.isTouchOtherEnemy())
					{
						x-=speed;
					}
					try{
						Thread.sleep(50);
					}catch(Exception e)
					{
						e.printStackTrace();
					}
					
				}
				break;
			}
			
			
			this.times++;
			if(times%2==0)
			{
				if(isLive)
				{
					if(ss.size()<5)
					{
						Shot s=null;
						
						//没有子弹
						//添加
						switch(direct)
						{
						case 0:
							//创建一颗子弹
							s=new Shot(x+10,y,0);
							//把子弹加入向量
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
						
						//启动子弹
						Thread t=new Thread(s);
						t.start();
					}
				}
			}
			
			//敌人坦克碰撞之后，让坦克随机产生一个新的方向
			this.direct=(int)(Math.random()*4);
			
			//判断敌人谈了是否死亡
			if(this.isLive==false)
			{
				//让敌人坦克死亡后，退出线程
				break;
			}
		}//while(true)函数结束
	}//线程run函数结束
	
}
