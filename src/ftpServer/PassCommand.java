package ftpServer;

import java.io.IOException;
import java.io.Writer;

public class PassCommand implements Command{
	
	/**
     *ftpЭ��pass�����ʵ��
	 * */
	@Override
	public String getResult(String data,ControllerThread t) {
		
		System.out.println("execute the pass command");
		System.out.println("the data is "+data);
		//����û���
		String key = ControllerThread.USER.get();
		String pass = Share.users.get(key);
		
		String response = null;
		if(pass.equals(data)) {
			System.out.println("��¼�ɹ�");
			Share.loginedUser.add(key);
			t.setIsLogin(true);
			response = "230 User "+key+" logged in";
		}
		else {
			System.out.println("��¼ʧ�ܣ��������");
			response = "530   �������";
		}
		return response;
		
	}

}
