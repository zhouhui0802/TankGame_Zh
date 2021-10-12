package Recode;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.Vector;

import AllTank.EnemyTank;

//��¼�࣬ͬʱ���Ա�����ҵ�����

public class Recorder {
	
	//��¼ÿһ���ж��ٵ���
	private static int enNum=20;
	
	//�������ж��ٿ��õ���
	private static int myLife=3;
	
	//��¼�ܹ������˶��ٵ���
	private static int allEnNum=0;
	
	private static FileWriter fw=null;
	private static BufferedWriter bw=null;
	
	private static FileReader fr=null;
	private static BufferedReader br=null;
	
	private Vector<EnemyTank> ets=new Vector<EnemyTank>();
	
	//���ļ��лָ���¼
	static Vector<Node> nodes=new Vector<Node>();
	
	//��ɶ�ȡ�ļ�
	public Vector<Node> getNodesAndEnNums()
	{
		System.out.println("getNodesAndEnNums������ȡmyRecording.txt���ļ��ڵ�");
		try{
			fr=new FileReader("myRecording.txt");
			br=new BufferedReader(fr);
			String n="";
			//�ȶ�ȡ��һ�У����ٵ��˵�����
			n=br.readLine();
			allEnNum=Integer.parseInt(n);
			System.out.println("���ٵ��˵�����allEnNum="+allEnNum);
			
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
		
		System.out.println("��ȡmyRecording.txt���ļ��ڵ����");
		return nodes;
	}
	
	//������ٵĵ��˵������͵���̹�˵������Լ�����
	public void keepRecAndEnemyTank()
	{
		System.out.println("keepRecAndEnemyTank���������ٵ��˵������Լ����˵������λ�ñ���");
		try{
			//����
			fw=new FileWriter("myRecording.txt");
			bw=new BufferedWriter(fw);
			
			bw.write(allEnNum+"\r\n");
			
			//���浱ǰ��ĵ��˵�����ͷ���
			for(int i=0;i<ets.size();i++)
			{
				//ȡ����һ��̹��
				EnemyTank et=ets.get(i);
				
				if(et.isLive)
				{
					//��ľͱ���
					String recode=et.x+" "+et.y+" "+et.direct;
					//д��
					bw.write(recode+"\r\n");
				}
			}
		}catch(Exception e)
		{
			e.printStackTrace();
		}finally{
			//�ر���
			try{
				//���ȹر�
				bw.close();
				fw.close();
			}catch(Exception e)
			{
				e.printStackTrace();
			}
		}
		System.out.println("keepRecAndEnemyTank���������ٵ��˵������Լ����˵������λ�ñ������");
	}
	
	
	//���ļ��ж�ȡ��¼��������ȡ���ٵ��˵�����
	public static void getRecording()
	{
		System.out.println("getRecording()���������ٵ��˵�������������");
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
		System.out.println("getRecording()���������ٵ��˵�����������������");
	}
	
	//����һ��ٵĵ����������浽�ļ��У���������һ��
	public static void keepRecording()
	{
		System.out.println("keepRecording()���������ٵ��˵�������������");
		try{
			//����
			fw=new FileWriter("myRecording.txt");
			bw=new BufferedWriter(fw);
			
			bw.write(allEnNum+"\r\n");
		}catch(Exception e)
		{
			e.printStackTrace();
		}finally{
			//�ر���
			try{
				//���ȹر�
				bw.close();
				fw.close();
			}catch(Exception e)
			{
				e.printStackTrace();
			}
		}
		System.out.println("keepRecording()���������ٵ��˵����������������");
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
	
	
	//���ٵ�������
	public static void reduceEnNum()
	{
		enNum--;
	}
	
	//�������
	public static void addEnNumRec()
	{
		allEnNum++;
	}
}
