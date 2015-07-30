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
     * ftpЭ��store�����ʵ��
     * ���ܣ������������ӣ���������tcp���ӣ����ܡ��ͻ��ˡ����͵����������洢�����������̡�
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
				//��������
				Socket tempSocket = new Socket(t.getDataIp(),Integer.parseInt(t.getDataPort())); 
				InputStream inSocket 
				= tempSocket.getInputStream(); 
				byte byteBuffer[] = new byte[1024]; 
				int amount; 
				
				while((amount =inSocket.read(byteBuffer) )!= -1){ 
					inFile.write(byteBuffer, 0, amount); 
				} 
				System.out.println("������ɣ��ر����ӡ�����");
				inFile.close();
				inSocket.close();
				tempSocket.close();
				//�Ͽ���������
				
				response = ("226 transfer complete"); 
				
			
			} 
			catch(IOException e){
				e.printStackTrace();
			} 
			return response;
			

	}
}
