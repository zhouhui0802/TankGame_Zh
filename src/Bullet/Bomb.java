package Bullet;
//炸弹类
public class Bomb {
	
	//定义炸弹的坐标
	public int x,y;
	
	//定义炸弹的生命
	public int life=9;
	
	//判断炸弹是否存活
	public boolean isLive=true;
	
	public Bomb(int x,int y)
	{
		this.x=x;
		this.y=y;
	}
	
	//减少生命值
	public void lifeDown()
	{
		if(life>0)
		{
			life--;
		}else
		{
			this.isLive=false;
		}
	}
	
}
