package ftpServer;

import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.net.Socket;
import java.net.UnknownHostException;

public class DirCommand implements Command{

	/**
	 * ��ȡftpĿ¼������ļ��б�
	 * 
	 * */
	@Override
	public String getResult(String data,ControllerThread t) {
		String response = "";
		String desDir = t.getNowDir()+data;
		System.out.println(desDir);
		File dir = new File(desDir);
		if(!dir.exists()) {
		
			response = "210  �ļ�Ŀ¼������";
		
		}
		else 
		{
			StringBuilder dirs = new StringBuilder();
			System.out.println("�ļ�Ŀ¼���£�");
			dirs.append("�ļ�Ŀ¼����:\n");
			String[] lists= dir.list();
			String flag = null;
			for(String name : lists) {
				System.out.println(name);
				File temp = new File(desDir+File.separator+name);
				if(temp.isDirectory()) {
					flag = "d";
				}
				else {
					flag = "f";
				}
				dirs.append("\t");
				dirs.append(flag);
				dirs.append("  ");
				dirs.append(name);
				dirs.append("\n");
				
			}
			
			//�����������ӣ������ݷ��͸��ͻ��ˣ�������Ҫ�ж˿ںź�ip��ַ
			Socket s;
			try {
				 Writer writer = new BufferedWriter(new OutputStreamWriter(t.getSocket().getOutputStream()));
				 response = "150 open ascii mode...\r\n";
				 writer.write(response);
				 writer.flush();
				 s = new Socket(t.getDataIp(), Integer.parseInt(t.getDataPort()));
				 BufferedWriter dataWriter = new BufferedWriter(new OutputStreamWriter(s.getOutputStream()));
				 dataWriter.write(dirs.toString());
				 dataWriter.flush();
				 s.close();
				 response = "220 transfer complete...\r\n";
			} catch (NumberFormatException e) {
				
				e.printStackTrace();
			} catch (UnknownHostException e) {
			
				e.printStackTrace();
			} catch (IOException e) {
				
				e.printStackTrace();
			}
		}
		return response;
	}
	

}
