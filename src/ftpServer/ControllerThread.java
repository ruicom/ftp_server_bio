package ftpServer;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.Writer;
import java.net.Socket;

/**
 * @author onroadrui
 * ���ڴ��������������������߳�
 * ��������:�ڴ���֮��ֱ��socket.close()(�Ĵλ��ֵĹ���)��
 * ����tcp�����establish�Ľ׶Ρ��������ɵش������ݣ�ȫ˫���ģ�
 * */
public class ControllerThread extends Thread{
	
	private int count = 0;
	
	//�ͻ���socket���������socket���һ��tcp����
	private Socket socket;
		
	//��ǰ���߳�����Ӧ���û�
	public static final ThreadLocal<String> USER = new ThreadLocal<String>();

	//�������ӵ�ip
	private String dataIp;
	
	//�������ӵ�port
	private  String dataPort;
	
	//���ڱ���û��Ƿ��Ѿ���¼
	private boolean isLogin = false;
	
	//��ǰĿ¼
	private String nowDir = Share.rootDir;
	

	
	public String getNowDir() {
		return nowDir;
	}

	public void setNowDir(String nowDir) {
		this.nowDir = nowDir;
	}

	public void setIsLogin(boolean t) {
		isLogin = t;
	}
	
	public boolean getIsLogin() {
		return isLogin;
	}
	
	public Socket getSocket() {
		return socket;
	}
	
	public String getDataIp() {
		return dataIp;
	}

	public void setDataIp(String dataIp) {
		this.dataIp = dataIp;
	}

	public String getDataPort() {
		return dataPort;
	}

	public void setDataPort(String dataPort) {
		this.dataPort = dataPort;
	}

	public ControllerThread(Socket socket) {
		this.socket = socket; 
	}

	/**
	 * �������ӵ�����
	 * ���ܣ�
	 * 1���������������ȡ�ͻ��˴��͹���������
	 * 2�����ݿͻ��˴��͹��������������������Ӧ����������
	 * 3����������Ĵ���
	 * 
	 * */
	public void run() {
		BufferedReader reader;	 
		StringBuilder response = null;
		try {
			  reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));	
			  
			  while(true) {
				  response =  new StringBuilder();
				  //��һ�η��ʣ�������������û�ж����ģ����Ի�����ס
				  if(count == 0) 
				  {
					  response.append("220");
				      count++;
				  }
				  else {
					  //���������ر����ӣ�(1)quit���� (2)�������
					  if(!socket.isClosed()) {
						  //���������ѡ��Ȼ����д������ͻ���û�з������ݵ�ʱ�򣬽�������
						  String command = reader.readLine();  
						  if(command !=null) {
						      String[] datas = command.split(" ");
						      Command commandSolver = CommandFactory.createCommand(datas[0]); 
						      //��¼��֤,��û�е�¼������£��޷�ʹ�ó���user,pass֮�������
						      if(loginValiate(commandSolver)) {
							      if(commandSolver == null)
							      {
							    	  response.append("502  ��������ڣ�����������");
							      }
							      else 
							      {
								      String data = "";
								      if(datas.length >=2) {
								    	  data = datas[1];
								      }
								      response.append(commandSolver.getResult(data,this));
							      }
						      }
						      else 
						      {
						    	  response.append("532 ִ�и�������Ҫ��¼�����¼����ִ����Ӧ�Ĳ���\r\n");
						    	  
						      }
						  }   	
					  }
					  else {
						  //�����Ѿ��رգ�����̲߳����д��ڵı�Ҫ
						  break;
					  }
				  }
				  FtpWriter ftpWriter = new FtpWriter(socket.getOutputStream());
				  ftpWriter.sendResponse(response.toString()); 
			  }
		    
		} catch (IOException e) {
			e.printStackTrace();
		}  
		finally {
			 System.out.println("����tcp����");
		}
	     
	}
	
	/**
	 * ���м򵥵ĵ�¼��֤ 
	 * ���ܣ� ��¼��֤,��û�е�¼������£��޷�ʹ�ó���user,pass֮�������
	 * @param command ����Ҫִ�е�����
	 * */
	public  boolean loginValiate(Command command) {
		if(command instanceof UserCommand || command instanceof PassCommand) {
			return true;
		}
		else 
		{
			return isLogin;
		}
	}
	
	
	
}
