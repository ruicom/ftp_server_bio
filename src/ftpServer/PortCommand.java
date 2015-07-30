package ftpServer;

import java.io.IOException;
import java.io.Writer;

public class PortCommand implements Command{

	/**
	 * ftp协议port命令处理程序
	 * 功能：解析存储客户端发送过来的用于创建数据连接的端口号和ip地址。传输过来的数据<br>
	 *     格式如下：10,21,32,192,12,8.  前面4位是ip地址的组成部分。后面需<br>
	 *     要进行运算生成端口号：12*256+8。端口号用16为表示，12是高8位，8是低8位。
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
