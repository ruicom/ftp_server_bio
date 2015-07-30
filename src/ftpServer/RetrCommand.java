package ftpServer;

import java.io.BufferedOutputStream;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.Writer;
import java.net.Socket;

/**
 * �����ļ�������
 * */
public class RetrCommand implements Command{
    
	/**
	 * ftp retr�����ʵ��
	 * ���ܣ���port����֮��ִ�У������������ӣ��������������ݴ��͵��ͻ��� 
	 * */
	@Override
	public String getResult(String data,ControllerThread t) {
		String response = "";
		Socket s;
		String desDir = t.getNowDir()+File.separator+data;
		File file = new File(desDir);
		System.out.println(desDir);
		if(file.exists())
		{
			try {
				 Writer writer = new BufferedWriter(new OutputStreamWriter(t.getSocket().getOutputStream()));
				 writer.write("150 Binary data connection\r\n"); 
				 writer.flush();
				 s = new Socket(t.getDataIp(), Integer.parseInt(t.getDataPort()));
				 BufferedOutputStream dataOut = new BufferedOutputStream(s.getOutputStream());
				 byte[] buf = new byte[1024];
				 InputStream is = new FileInputStream(file); 
				 while(-1 != is.read(buf)) {
					 dataOut.write(buf);
				 }
				 dataOut.flush();
				 s.close();
				response = "220 transfer complete...";
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		else {
		
			response = "220  ���ļ�������";
		}
		return response;
	}

}
