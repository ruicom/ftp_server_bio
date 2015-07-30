package ftpServer;

import java.io.File;
import java.io.IOException;
import java.io.Writer;

/**
 * �ı乤��Ŀ¼
 * */
public class CwdCommand implements Command{

	/**
	 * cwd�������
	 * ���ܣ��޸ķ�������ǰ���ʵ�Ŀ¼
	 * */
	@Override
	public String getResult(String data,ControllerThread t) {
		String response = "";
		String dir = t.getNowDir() +File.separator+data;
		File file = new File(dir);
		
		if((file.exists())&&(file.isDirectory())) {
			String nowDir =t.getNowDir() +File.separator+data;
			t.setNowDir(nowDir);
			response = "250 CWD command succesful";	
		}
		else 
		{
			response = "550 Ŀ¼������";
		}
		
		return response;
	}

	
}
