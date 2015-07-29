package ftpServer;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * ftp���������
 * ���ܣ�1����ʼ��ȫ����Ϣ
 *     2������serverSocket������Ӧ�Ͽ�
 *     3�������������Ӵ����߳�
 * */
public class FtpServer {
	
	private int port;
	
	ServerSocket serverSocket;
	
	/**
	 * ����ServerSocket����ʼ��ȫ����Ϣ
	 * @param port    �˿ں�
	 **/
	public FtpServer(int port) throws IOException {
		 
		serverSocket = new ServerSocket(port);
		//��ʼ��ϵͳ��Ϣ
		  Share.init();
	}
	

	/**
	 * ����tcp listend�׶Σ��ȴ���Ӧ����������
	 * */
	public void listen() throws IOException {
		 
		  Socket socket = null;
	      while(true) {
	    	//����ǽ�������,�������ֵĹ��̣������ӽ�����֮������socket֮���ͨѶ��ֱ��ͨ�������еģ�������ͨ����һ��
	    	  socket = serverSocket.accept();
	    	  ControllerThread thread = new ControllerThread(socket);
	    	  thread.start();
	      } 
	}
	
	public static void main(String args[]) throws IOException {
		FtpServer ftpServer = new FtpServer(21);
		ftpServer.listen();
	}
	
	
}
