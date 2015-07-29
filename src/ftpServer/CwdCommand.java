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
	public void getResult(String data, Writer writer, ControllerThread t) {
		String dir = t.getNowDir() +File.separator+data;
		File file = new File(dir);
		try {
			if((file.exists())&&(file.isDirectory())) {
				String nowDir =t.getNowDir() +File.separator+data;
				t.setNowDir(nowDir);
				writer.write("250 CWD command succesful");	
			}
			else 
			{
				writer.write("550 Ŀ¼������");
			}
			writer.write("\r\n");
			writer.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	
}
