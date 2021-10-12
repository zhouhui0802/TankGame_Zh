package AllTank;
import java.util.Vector;

import Bullet.Shot;

//�ҵ�̹��
public class Hero extends Tank
{

	//�ӵ���
	public Shot s=null;
	
	//�����ӵ�����
	public Vector<Shot> ss=new Vector<Shot>();
	
	public Hero(int x, int y) {
		super(x, y);
	}
	
	//����
	public void shotEnemy()
	{

		//̹�˷��� 0���� 1��ʾ��  2��ʾ�� 3��ʾ��
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
		
		// 	�����ӵ��߳�
		Thread t=new Thread(s);
		t.start();
	}
	
	
	//̹�������ƶ�
	public void moveUp()
	{
		y-=speed;
	}
	
	//̹�������ƶ�
	public void moveRight()
	{
		x+=speed;
	}
	
	//̹�������ƶ�
	public void moveDown()
	{
		y+=speed;
	}
	
	//̹�������ƶ�
	public void moveLeft()
	{
		x-=speed;
	}
	
}
