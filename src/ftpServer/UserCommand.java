package ftpServer;

import java.io.IOException;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;

public class UserCommand implements Command{
	
	/**
	 * ftp user�����ʵ��
	 * ���ܣ������Ƿ�������û�������
	 * */
	@Override
	public String getResult(String data,ControllerThread t) {
		String response = "";
		if(Share.users.containsKey(data)) {
			ControllerThread.USER.set(data);
			response = "331";
		}
		else {
			response = "501";
		}
		return response;
	 
	}

}
