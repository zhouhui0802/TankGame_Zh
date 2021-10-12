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
	
	//定义一个开始面板
	MyStartPanel msp=null;
	
	//开始对菜单进行部署
	JMenuBar jmb=null;
	
	//开始游戏
	JMenu jm1=null;
	JMenuItem jmi1=null;
	
	//退出系统
	JMenuItem jmi2=null;
	
	//存盘退出
	JMenuItem jmi3=null;
	JMenuItem jmi4=null;
	
	
	//构造函数
	public MyTankGame()
	{

		jmb=new JMenuBar();
		jm1=new JMenu("游戏(G)");
		//设置菜单栏的快捷方式
		jm1.setMnemonic('G');
		

		
		jmi1=new JMenuItem("开始新的游戏(N)");
		jmi2=new JMenuItem("退出游戏(E)");
		jmi3=new JMenuItem("存盘退出游戏(C)");
		jmi4=new JMenuItem("继续上局游戏(S)");
		
		//设置菜单条的快捷方式
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

		
		//对jmi1相对应
		jm1.add(jmi1);
		jm1.add(jmi2);
		jm1.add(jmi3);
		jm1.add(jmi4);
		jmb.add(jm1);
		
		//调用开始我的开始界面的提示界面
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
			//创建战场面板
			mp=new MyPanel("newGame");
			
			//启动线程
			Thread t=new Thread(mp);
			t.start();
			
			//先删除旧的面板
			this.remove(msp);
			this.add(mp);
			
			//注册监听
			this.addKeyListener(mp);
			
			//显示，刷新JFrame
			this.setVisible(true);
			
		}else if(arg0.getActionCommand().equals("exit"))
		{
			//用户单击了退出系统菜单
			//保存击毁敌人的数量
			Recorder.keepRecording();
			System.exit(0);
			
		}else if(arg0.getActionCommand().equals("saveExit"))
		{
			//对存盘进行处理
			//保存敌人的数量与坐标
			Recorder rd=new Recorder();
			rd.setEts(mp.ets);
			rd.keepRecAndEnemyTank();
			System.exit(0);
			
		}else if(arg0.getActionCommand().equals("conGame"))
		{
			//创建战场面板
			mp=new MyPanel("con");
			
			//启动线程
			Thread t=new Thread(mp);
			t.start();
			
			//先删除旧的面板
			this.remove(msp);
			this.add(mp);
			
			//注册监听
			this.addKeyListener(mp);
			
			//显示，刷新JFrame
			this.setVisible(true);
		}
	}

}




