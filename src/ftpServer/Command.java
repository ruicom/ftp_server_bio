package ftpServer;

import java.io.Writer;

interface Command {

	/**
	 * @param data    ��ftp�ͻ��˽��յĳ�ftp����֮�������
	 * @param writer  ���������
	 * @param t       ������������Ӧ�Ĵ����߳�
	 * */
	public void getResult(String data,Writer writer,ControllerThread t);
	
}
