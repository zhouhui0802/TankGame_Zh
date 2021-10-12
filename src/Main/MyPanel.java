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

//�ҵ����
class MyPanel extends JPanel implements KeyListener,Runnable
{
	//����һ���ҵ�̹��
	Hero hero=null;
	
	//������˵�̹������
	Vector<EnemyTank> ets=new Vector<EnemyTank>();
	
	//�����������������ݽڵ�
	Vector<Node> nodes=new Vector<Node>();
	
	//��ʼ������̹�˵�����
	int enSize=6;
	
	//����ը���ļ���
	Vector<Bomb> bombs=new Vector<Bomb>();
	
	
	//��������ͼƬ
	Image image1=null;
	Image image2=null;
	Image image3=null;
	
	//���캯��
	public MyPanel(String flag)
	{
		System.out.println("����MyPanel�Ĺ��캯��");
		//��������̹�˵�λ��
		hero=new Hero(100,100);		
		
		if(flag.equals("newGame"))
		{
			System.out.println("��ʼ�µ���Ϸ");
			//��ʼ�����˵�̹��
			for(int i=0;i<enSize;i++)
			{
				EnemyTank et=new EnemyTank((i+1)*50,0);
				et.setDirect(2);
				
				//��MyPanel�ĵ������������õ���̹��
				et.setEts(ets);
				
				//���������߳�
				Thread t=new Thread(et);
				t.start();
				
				//���������һ���ӵ�
				Shot s=new Shot(et.x+10,et.y+30,2);
				
				//��������ӵ�������
				et.ss.add(s);
				Thread t2=new Thread(s);
				t2.start();
				
				//������̹�˼��뵽����̹��������
				ets.add(et);
			}
		}else
		{
			System.out.println("��������һ�ֵ���Ϸ");
			//��.txt�ж�ȡ�����������
			nodes=new Recorder().getNodesAndEnNums();
			for(int i=0;i<nodes.size();i++)
			{
				Node node=nodes.get(i);
				
				EnemyTank et=new EnemyTank(node.x,node.y);
				et.setDirect(node.direct);
				
				//��MyPanel�ĵ������������õ���̹��
				et.setEts(ets);
				
				//���������߳�
				Thread t=new Thread(et);
				t.start();
				
				//���������һ���ӵ�
				Shot s=new Shot(et.x+10,et.y+30,2);
				//��������ӵ�������
				et.ss.add(s);
				Thread t2=new Thread(s);
				t2.start();
				
				//����̹������������
				ets.add(et);
			}
		}
	
		image1=Toolkit.getDefaultToolkit().getImage(Panel.class.getResource("/bomb_1.gif"));
		image2=Toolkit.getDefaultToolkit().getImage(Panel.class.getResource("/bomb_2.gif"));
		image3=Toolkit.getDefaultToolkit().getImage(Panel.class.getResource("/bomb_3.gif"));
		
		//���ſ�ս����
		AePlayWave awp=new AePlayWave("Material/111.wav");
		awp.start();
	}
	
	//������ʾ��Ϣ��̹��
	public void showInfo(Graphics g)
	{
		//System.out.println("������ʾ��Ϣ��̹��");
		//��������̹�˵���Ϣ
		this.drawTank(80, 330, g, 0, 0);
		g.setColor(Color.black);
		g.drawString(Recorder.getEnNum()+"", 110, 350);
		//��������̹�˵���Ϣ
		this.drawTank(130, 330, g, 0, 1);
		g.setColor(Color.black);
		g.drawString(Recorder.getMyLife()+"", 165, 350);
		
		//������ҵ��ܳɼ�
		g.setColor(Color.black);
		Font f=new Font("����",Font.BOLD,20);
		g.setFont(f);
		g.drawString("�����ܳɼ�", 420, 30);		
		this.drawTank(420, 60, g, 0, 0);		
		g.setColor(Color.black);
		g.drawString(Recorder.getAllEnNum()+"", 460, 80);
		//System.out.println("������ʾ��Ϣ��̹�˽���");
	}
	
	//�ػ���
	public void paint(Graphics g)
	{
		super.paint(g);
		g.fillRect(0, 0, 400, 300);

		//������ʾ��Ϣ
		this.showInfo(g);
		
		//�����Լ���̹��
		if(hero.isLive)
		{
			this.drawTank(hero.getX(),hero.getY(), g,this.hero.direct, 0);
		}
		
		
		//��ss��ȡ��ÿ���ӵ�����������̹���ӵ�
		for(int i=0;i<hero.ss.size();i++)
		{
			Shot myShot=hero.ss.get(i);
			//����ÿһ���ӵ�
			if(myShot!=null&&myShot.isLive==true)
			{
				g.draw3DRect(myShot.x, myShot.y,1, 1 ,false);
			}
			
			if(myShot.isLive==false)
			{
				//��ss��ɾ�������ӵ�
				hero.ss.remove(myShot);
			}
		}
		
		//����ը��
		for(int i=0;i<bombs.size();i++)
		{
			System.out.println("bombs.size()="+bombs.size());
			//ȡ��ը��
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
			//��b������ֵ��С
			b.lifeDown();
			
			if(b.life==0)
			{
				bombs.remove(b);
			}
			
		}
		
		//�������˵�̹��
		for(int i=0;i<ets.size();i++)
		{
			EnemyTank et=ets.get(i);
			if(et.isLive)
			{
				this.drawTank(et.getX(), et.getY(), g, et.getDirect(), 1);
				//�ٻ������˵��ӵ�
				for(int j=0;j<et.ss.size();j++)
				{
					//ȡ���ӵ�
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
	
	//�жϵ��˵��ӵ��Ƿ������
	public void hitMe()
	{
		//ȡ��ÿһ�����˵�̹��
		for(int i=0;i<this.ets.size();i++)
		{
			//ȡ��̹��
			EnemyTank et=ets.get(i);
			
			//ȡ��ÿһ���ӵ�
			for(int j=0;j<et.ss.size();j++)
			{
				//ȡ���ӵ�
				Shot enemyShot=et.ss.get(j);
				if(hero.isLive)
				{
					if(this.hitTank(enemyShot, hero))
					{
						//���ٵ��˵�����
						Recorder.reduceEnNum();
						
						//�����ҵļ�¼
						Recorder.addEnNumRec();
					}
				}
			}
		}
	}
	
	
	//�ж��ҵ��ӵ��Ƿ����̹��
	public void hitEnemyTank()
	{
		//�ж��Ƿ���е��˵�̹��
		for(int i=0;i<hero.ss.size();i++)
		{
			//ȡ���ӵ�
			Shot myShot=hero.ss.get(i);
			if(myShot.isLive)
			{
				//ȡ��ÿ��̹��
				for(int j=0;j<ets.size();j++)
				{
					//ȡ��ÿ��̹�ˣ����������ж�
					EnemyTank et=ets.get(j);
					
					if(et.isLive)
					{
						if(this.hitTank(myShot, et))
						{
							System.out.println("���вŵ���");
							//���ٵ��˵�����
							Recorder.reduceEnNum();
							//�����ҵļ�¼
							Recorder.addEnNumRec();
						}
					}
				}
			}
		}
	}
	
	
	//дһ��ר�ŵĺ����ж��ӵ��Ƿ����̹��
	public boolean hitTank(Shot s,Tank et)
	{
		boolean  b2=false;
		
		//�ж�̹�˵ķ���
		switch(et.direct)
		{
		case 0:
		case 2:
			if(s.x>et.x&&s.x<et.x+20&&s.y>et.y&&s.y<et.y+30)
			{
				//����
				//�ӵ�����
				s.isLive=false;
				
				//����̹������
				et.isLive=false;
				
				//���ٵ��˵�����
				b2=true;
				//����һ��ը��������Vector
				Bomb b=new Bomb(et.x,et.y);
				//����Vector��
				bombs.add(b);
			}
			break;
		case 1:
		case 3:
			if(s.x>et.x&&s.x<et.x+30&&s.y>et.y&&s.y<et.y+20)
			{
				//����
				//�ӵ�����
				s.isLive=false;
				//����̹������
				et.isLive=false;
				b2=true;
				//����һ��ը��������Vector
				Bomb b=new Bomb(et.x,et.y);
				//����Vector��
				bombs.add(b);
			}
		}
		
		return b2;
	}
	
	//����̹�˵ĺ���
	public void drawTank(int x,int y, Graphics g,int direct,int type)
	{
		//�ж�̹������
		switch(type)
		{
		case 0:
			g.setColor(Color.cyan);
			break;
		case 1:
			g.setColor(Color.yellow);
			break;
		}
		
		//�ж�̹�˷���
		switch(direct)
		{
		//����
		case 0:
			//�����ҵ�̹�ˣ���װ��һ������
			//������ߵľ���
			g.fill3DRect(x, y, 5, 30,false);
			//�����ұߵľ���
			g.fill3DRect(x+15, y, 5, 30,false);
			//�����м�ľ���
			g.fill3DRect(x+5,y+5, 10, 20,false);
			//����Բ��
			g.fillOval(x+5, y+10, 10, 10);
			//�����߶�
			g.drawLine(x+10,y+15,x+10,y);
			break;
		case 1:
			//��Ͳ����
			//��������ľ���
			g.fill3DRect(x, y, 30, 5,false);
			//��������ıߵľ���
			g.fill3DRect(x, y+15, 30, 5,false);
			//�����м�ľ���
			g.fill3DRect(x+5,y+5, 20, 10,false);
			//����Բ��
			g.fillOval(x+10, y+5, 10, 10);
			//�����߶�
			g.drawLine(x+15,y+10,x+30,y+10);
			break;
		case 2:
			//��Ͳ����
			//������ߵľ���
			g.fill3DRect(x, y, 5, 30,false);
			//�����ұߵıߵľ���
			g.fill3DRect(x+15, y, 5, 30,false);
			//�����м�ľ���
			g.fill3DRect(x+5,y+5, 10, 20,false);
			//����Բ��
			g.fillOval(x+5, y+10, 10, 10);
			//�����߶�
			g.drawLine(x+10,y+15,x+10,y+30);
			break;
		case 3:
			//��Ͳ����
			//������ߵľ���
			g.fill3DRect(x, y, 30, 5,false);
			//�����ұߵıߵľ���
			g.fill3DRect(x, y+15, 30, 5,false);
			//�����м�ľ���
			g.fill3DRect(x+5,y+5, 20, 10,false);
			//����Բ��
			g.fillOval(x+10, y+5, 10, 10);
			//�����߶�
			g.drawLine(x+15,y+10,x,y+10);
		}
		
		
	}

	@Override
	public void keyPressed(KeyEvent arg0) {
		// TODO Auto-generated method stub
		//�����ҵ�̹�˷���
		if(arg0.getKeyCode()==KeyEvent.VK_W)
		{
			//����
			this.hero.setDirect(0);	
			this.hero.moveUp();
		}else if(arg0.getKeyCode()==KeyEvent.VK_D)
		{
			//����
			this.hero.setDirect(1);
			this.hero.moveRight();
		}else if(arg0.getKeyCode()==KeyEvent.VK_S)
		{
			//����
			this.hero.setDirect(2);
			this.hero.moveDown();
		}else if(arg0.getKeyCode()==KeyEvent.VK_A)
		{
			//����
			this.hero.setDirect(3);
			this.hero.moveLeft();
		}else if(arg0.getKeyCode()==KeyEvent.VK_J)
		{
			//����
			if(this.hero.ss.size()<=5)
			{
				this.hero.shotEnemy();
			}

		}
		
		//�������»���panel
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
		//ÿ��100����ȥ�ػ���
		while(true)
		{
			try{
				Thread.sleep(100);
			}catch(Exception e)
			{
				
			}
			
			//�ж����Ƿ���е��˵�̹��
			this.hitEnemyTank();
			
			//�жϵ����Ƿ������
			this.hitMe();
				
			//�ػ���
			this.repaint();
		}
	}
}


