package ftpServer;

import java.io.File;
import java.io.IOException;
import java.io.Writer;

/**
 * 改变工作目录
 * */
public class CwdCommand implements Command{

	/**
	 * cwd处理程序
	 * 功能：修改服务器当前访问的目录
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
			response = "550 目录不存在";
		}
		
		return response;
	}

	
}
