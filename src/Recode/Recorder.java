package Recode;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.Vector;

import AllTank.EnemyTank;

//记录类，同时可以保存玩家的设置

public class Recorder {
	
	//记录每一关有多少敌人
	private static int enNum=20;
	
	//设置我有多少可用的人
	private static int myLife=3;
	
	//记录总共消灭了多少敌人
	private static int allEnNum=0;
	
	private static FileWriter fw=null;
	private static BufferedWriter bw=null;
	
	private static FileReader fr=null;
	private static BufferedReader br=null;
	
	private Vector<EnemyTank> ets=new Vector<EnemyTank>();
	
	//从文件中恢复记录
	static Vector<Node> nodes=new Vector<Node>();
	
	//完成读取文件
	public Vector<Node> getNodesAndEnNums()
	{
		System.out.println("getNodesAndEnNums函数读取myRecording.txt的文件节点");
		try{
			fr=new FileReader("myRecording.txt");
			br=new BufferedReader(fr);
			String n="";
			//先读取第一行，击毁敌人的数量
			n=br.readLine();
			allEnNum=Integer.parseInt(n);
			System.out.println("击毁敌人的数量allEnNum="+allEnNum);
			
			while((n=br.readLine())!=null)
			{
				String []xyz=n.split(" ");
				Node node=new Node(Integer.parseInt(xyz[0]),Integer.parseInt(xyz[1]),Integer.parseInt(xyz[2]));
				nodes.add(node);
			}
		}catch(Exception e)
		{
			e.printStackTrace();
		}finally
		{
			try{
				br.close();
				fr.close();
			}catch(Exception e)
			{
				e.printStackTrace();
			}
		}
		
		System.out.println("读取myRecording.txt的文件节点结束");
		return nodes;
	}
	
	//保存击毁的敌人的数量和敌人坦克的坐标以及方向
	public void keepRecAndEnemyTank()
	{
		System.out.println("keepRecAndEnemyTank函数将击毁敌人的数量以及敌人的坐标和位置保存");
		try{
			//创建
			fw=new FileWriter("myRecording.txt");
			bw=new BufferedWriter(fw);
			
			bw.write(allEnNum+"\r\n");
			
			//保存当前活动的敌人的坐标和方向
			for(int i=0;i<ets.size();i++)
			{
				//取出第一个坦克
				EnemyTank et=ets.get(i);
				
				if(et.isLive)
				{
					//活的就保存
					String recode=et.x+" "+et.y+" "+et.direct;
					//写入
					bw.write(recode+"\r\n");
				}
			}
		}catch(Exception e)
		{
			e.printStackTrace();
		}finally{
			//关闭流
			try{
				//后开先关闭
				bw.close();
				fw.close();
			}catch(Exception e)
			{
				e.printStackTrace();
			}
		}
		System.out.println("keepRecAndEnemyTank函数将击毁敌人的数量以及敌人的坐标和位置保存结束");
	}
	
	
	//从文件中读取记录，单独读取击毁敌人的数量
	public static void getRecording()
	{
		System.out.println("getRecording()函数将击毁敌人的数量单独读出");
		try{
			fr=new FileReader("myRecording.txt");
			br=new BufferedReader(fr);
			String n=br.readLine();
			allEnNum=Integer.parseInt(n);
		}catch(Exception e)
		{
			e.printStackTrace();
		}finally
		{
			try{
				br.close();
				fr.close();
			}catch(Exception e)
			{
				e.printStackTrace();
			}
		}
		System.out.println("getRecording()函数将击毁敌人的数量单独读出结束");
	}
	
	//把玩家击毁的敌人数量保存到文件中，单个操作一下
	public static void keepRecording()
	{
		System.out.println("keepRecording()函数将击毁敌人的数量单独保存");
		try{
			//创建
			fw=new FileWriter("myRecording.txt");
			bw=new BufferedWriter(fw);
			
			bw.write(allEnNum+"\r\n");
		}catch(Exception e)
		{
			e.printStackTrace();
		}finally{
			//关闭流
			try{
				//后开先关闭
				bw.close();
				fw.close();
			}catch(Exception e)
			{
				e.printStackTrace();
			}
		}
		System.out.println("keepRecording()函数将击毁敌人的数量单独保存结束");
	}
	
	public static int getAllEnNum() {
		return allEnNum;
	}
	public static void setAllEnNum(int allEnNum) {
		Recorder.allEnNum = allEnNum;
	}
	public static int getEnNum() {
		return enNum;
	}
	public static void setEnNum(int enNum) {
		Recorder.enNum = enNum;
	}
	public static int getMyLife() {
		return myLife;
	}
	public static void setMyLife(int myLife) {
		Recorder.myLife = myLife;
	}
	public  Vector<EnemyTank> getEts() {
		return ets;
	}
	public void setEts(Vector<EnemyTank> ets) {
		this.ets = ets;
	}
	
	
	//减少敌人数量
	public static void reduceEnNum()
	{
		enNum--;
	}
	
	//消灭敌人
	public static void addEnNumRec()
	{
		allEnNum++;
	}
}
