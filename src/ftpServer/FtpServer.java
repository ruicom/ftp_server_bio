package ftpServer;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * ftp服务器入口
 * 功能：1、初始化全局信息
 *     2、开启serverSocket监听相应断开
 *     3、创建控制连接处理线程
 * */
public class FtpServer {
	
	private int port;
	
	ServerSocket serverSocket;
	
	/**
	 * 创建ServerSocket，初始化全局信息
	 * @param port    端口号
	 **/
	public FtpServer(int port) throws IOException {
		 
		serverSocket = new ServerSocket(port);
		//初始化系统信息
		  Share.init();
	}
	

	/**
	 * 进入tcp listend阶段，等待相应的连接请求
	 * */
	public void listen() throws IOException {
		 
		  Socket socket = null;
	      while(true) {
	    	//这个是建立连接,三次握手的过程，当连接建立了之后，两个socket之间的通讯是直接通过流进行的，不用再通过这一步
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
