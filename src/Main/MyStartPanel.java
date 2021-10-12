package Main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.JPanel;

//就是一个提示作用,加上必要的闪烁
class MyStartPanel extends JPanel implements Runnable
{
	
	int times=0;
	
	public void paint(Graphics g)
	{
		super.paint(g);
		g.fillRect(0, 0, 400, 300);
		
		if(times%2==0)
		{
			//提示信息
			g.setColor(Color.yellow);
			//开关信息的字体
			Font myFont=new Font("华文新魏",Font.BOLD,30);
			g.setFont(myFont);
			g.drawString("stage: 1", 150, 150);
		}

	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		while(true)
		{
			try{
				Thread.sleep(100);
			}catch(Exception e)
			{
				e.printStackTrace();
			}
			
			times++;
			//重画
			this.repaint();
		}
	}
}
