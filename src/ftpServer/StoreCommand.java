package ftpServer;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.io.RandomAccessFile;
import java.io.Writer;
import java.net.Socket;



public class StoreCommand implements Command{

    /**
     * ftp协议store命令的实现
     * 功能：开启数据连接，主动发起tcp连接，接受“客户端”发送的数据流，存储到服务器磁盘。
     * */	
	@Override
	public String getResult(String data, ControllerThread t) {
		    String response = "";
			try{ 
				Writer writer = new BufferedWriter(new OutputStreamWriter(t.getSocket().getOutputStream()));
				writer.write("150 Binary data connection\r\n"); 
				writer.flush();
				RandomAccessFile inFile = new 
				RandomAccessFile(t.getNowDir()+"/"+data,"rw");
				//数据连接
				Socket tempSocket = new Socket(t.getDataIp(),Integer.parseInt(t.getDataPort())); 
				InputStream inSocket 
				= tempSocket.getInputStream(); 
				byte byteBuffer[] = new byte[1024]; 
				int amount; 
				
				while((amount =inSocket.read(byteBuffer) )!= -1){ 
					inFile.write(byteBuffer, 0, amount); 
				} 
				System.out.println("传输完成，关闭连接。。。");
				inFile.close();
				inSocket.close();
				tempSocket.close();
				//断开数据连接
				
				response = ("226 transfer complete"); 
				
			
			} 
			catch(IOException e){
				e.printStackTrace();
			} 
			return response;
			

	}
}
