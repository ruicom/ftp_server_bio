package ftpServer;

import java.io.IOException;
import java.io.Writer;

public class PassCommand implements Command{
	
	/**
     *ftpЭ��pass�����ʵ��
	 * */
	@Override
	public void getResult(String data, Writer writer,ControllerThread t) {
		
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
		try {
			writer.write(response);
			writer.write("\r\n");
			writer.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
