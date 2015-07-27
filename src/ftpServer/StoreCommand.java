package ftpServer;

import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.io.Writer;
import java.net.Socket;



public class StoreCommand implements Command{

	@Override
	public void getResult(String data, Writer writer, ControllerThread t) {
			try{ 
				writer.write("150 Binary data connection\r\n"); 
				writer.flush();
				RandomAccessFile inFile = new 
				RandomAccessFile(t.getNowDir()+"/"+data,"rw");
				//��������
				Socket tempSocket = new Socket(t.getDataIp(),Integer.parseInt(t.getDataPort())); 
				InputStream inSocket 
				= tempSocket.getInputStream(); 
				byte byteBuffer[] = new byte[1024]; 
				int amount; 
				//�����ֻ����������޷��ӿͻ�������������ȡ���ݣ�����Ϊ�ͻ���û�з�������ô
				while((amount =inSocket.read(byteBuffer) )!= -1){ 
					inFile.write(byteBuffer, 0, amount); 
				} 
				System.out.println("������ɣ��ر����ӡ�����");
				inFile.close();
				inSocket.close();
				tempSocket.close();
				//�Ͽ���������
				
				writer.write("226 transfer complete\r\n"); 
				writer.flush();
			} 
			catch(IOException e){
				e.printStackTrace();
			} 
			
		
	
	}
}
