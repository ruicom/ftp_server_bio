package ftpServer;

import java.io.IOException;
import java.io.Writer;

public class PortCommand implements Command{

	/**
	 * ftpЭ��port��������
	 * ���ܣ������洢�ͻ��˷��͹��������ڴ����������ӵĶ˿ںź�ip��ַ���������������<br>
	 *     ��ʽ���£�10,21,32,192,12,8.  ǰ��4λ��ip��ַ����ɲ��֡�������<br>
	 *     Ҫ�����������ɶ˿ںţ�12*256+8���˿ں���16Ϊ��ʾ��12�Ǹ�8λ��8�ǵ�8λ��
	 * */
	@Override
	public String getResult(String data,ControllerThread t) {
		String response = "200 the port an ip have been transfered";
		String[] iAp =  data.split(",");
		String ip = iAp[0]+"."+iAp[1]+"."+iAp[2]+"."+iAp[3];
		String port = Integer.toString(256*Integer.parseInt(iAp[4])+Integer.parseInt(iAp[5]));
		System.out.println("ip is "+ip);
		System.out.println("port is "+port);
		t.setDataIp(ip);
		t.setDataPort(port);
		return response;
		
	}

}
