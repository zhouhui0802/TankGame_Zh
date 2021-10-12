package Bullet;

//子弹类
public class Shot implements Runnable{
	
	//子弹的横坐标
	public int x;
	
	//子弹的纵坐标
	public int y;
	
	//子弹的方向
	public int direct;
	
	//子弹的速度
	public int speed=1;
	
	//判断子弹是否存活
	public boolean isLive=true;
	
	public Shot(int x,int y,int direct)
	{
		this.x=x;
		this.y=y;
		this.direct=direct;
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		while(true)
		{
			try{
				Thread.sleep(50);
			}catch(Exception e)
			{
				
			}
			switch(direct)
			{
			case 0:
				y-=speed;
				break;
			case 1:
				x+=speed;
				break;
			case 2:
				y+=speed;
				break;
			case 3:
				x-=speed;
				break;
			}
					
			//判断该子弹是否碰到边界
			if(x<0||x>400||y<0||y>300)
			{
				this.isLive=false;
				break;
			}
		}
	}
}
