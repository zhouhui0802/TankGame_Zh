package Main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Panel;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.util.Vector;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

import Recode.Node;
import Recode.Recorder;
import Util.AePlayWave;
import AllTank.EnemyTank;
import AllTank.Hero;
import AllTank.Tank;
import Bullet.Bomb;
import Bullet.Shot;


public class MyTankGame extends JFrame implements ActionListener{

	MyPanel mp=null;
	
	//����һ����ʼ���
	MyStartPanel msp=null;
	
	//��ʼ�Բ˵����в���
	JMenuBar jmb=null;
	
	//��ʼ��Ϸ
	JMenu jm1=null;
	JMenuItem jmi1=null;
	
	//�˳�ϵͳ
	JMenuItem jmi2=null;
	
	//�����˳�
	JMenuItem jmi3=null;
	JMenuItem jmi4=null;
	
	
	//���캯��
	public MyTankGame()
	{

		jmb=new JMenuBar();
		jm1=new JMenu("��Ϸ(G)");
		//���ò˵����Ŀ�ݷ�ʽ
		jm1.setMnemonic('G');
		

		
		jmi1=new JMenuItem("��ʼ�µ���Ϸ(N)");
		jmi2=new JMenuItem("�˳���Ϸ(E)");
		jmi3=new JMenuItem("�����˳���Ϸ(C)");
		jmi4=new JMenuItem("�����Ͼ���Ϸ(S)");
		
		//���ò˵����Ŀ�ݷ�ʽ
		jmi1.setMnemonic('N');
		jmi2.setMnemonic('E');
		jmi3.setMnemonic('C');
		jmi4.setMnemonic('S');
		
		jmi1.addActionListener(this);
		jmi1.setActionCommand("newgame");
		jmi2.addActionListener(this);
		jmi2.setActionCommand("exit");
		jmi3.addActionListener(this);
		jmi3.setActionCommand("saveExit");
		jmi4.addActionListener(this);
		jmi4.setActionCommand("conGame");

		
		//��jmi1���Ӧ
		jm1.add(jmi1);
		jm1.add(jmi2);
		jm1.add(jmi3);
		jm1.add(jmi4);
		jmb.add(jm1);
		
		//���ÿ�ʼ�ҵĿ�ʼ�������ʾ����
		msp=new MyStartPanel();
		Thread t=new Thread(msp);
		t.start();
		
		this.setJMenuBar(jmb);
		this.add(msp);
		this.setSize(600,500);
		this.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		if(arg0.getActionCommand().equals("newgame"))
		{
			//����ս�����
			mp=new MyPanel("newGame");
			
			//�����߳�
			Thread t=new Thread(mp);
			t.start();
			
			//��ɾ���ɵ����
			this.remove(msp);
			this.add(mp);
			
			//ע�����
			this.addKeyListener(mp);
			
			//��ʾ��ˢ��JFrame
			this.setVisible(true);
			
		}else if(arg0.getActionCommand().equals("exit"))
		{
			//�û��������˳�ϵͳ�˵�
			//������ٵ��˵�����
			Recorder.keepRecording();
			System.exit(0);
			
		}else if(arg0.getActionCommand().equals("saveExit"))
		{
			//�Դ��̽��д���
			//������˵�����������
			Recorder rd=new Recorder();
			rd.setEts(mp.ets);
			rd.keepRecAndEnemyTank();
			System.exit(0);
			
		}else if(arg0.getActionCommand().equals("conGame"))
		{
			//����ս�����
			mp=new MyPanel("con");
			
			//�����߳�
			Thread t=new Thread(mp);
			t.start();
			
			//��ɾ���ɵ����
			this.remove(msp);
			this.add(mp);
			
			//ע�����
			this.addKeyListener(mp);
			
			//��ʾ��ˢ��JFrame
			this.setVisible(true);
		}
	}

}




