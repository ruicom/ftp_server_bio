package ftpServer;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;

/**
 * �����̹߳���ı���
 * */
public class Share {
	
	/*��Ŀ¼��·��*/
	public static  String rootDir = "C:"+File.separator;
	
	/*�����¼���û�*/
	public static Map<String,String> users = new HashMap<String,String>();
		
	/*�Ѿ���¼���û�*/
	public static HashSet<String> loginedUser = new HashSet<String>();
	
	/*ӵ��Ȩ�޵��û�*/
	public static HashSet<String> adminUsers = new HashSet<String>();
	
	//��ʼ����Ŀ¼��Ȩ���û����ܹ���¼���û���Ϣ
	public static void init(){
		String path = System.getProperty("user.dir") + "/bin/server.xml";
		
		File file = new File(path);
		SAXBuilder builder = new SAXBuilder();
		try {
			Document parse = builder.build(file);
			Element root = parse.getRootElement();
			
			//���÷�������Ĭ��Ŀ¼
			rootDir = root.getChildText("rootDir");
			System.out.print("rootDir is:");
			System.out.println(rootDir);
			
			//�����¼���û�
			Element usersE = root.getChild("users");
			List<Element> usersEC = usersE.getChildren();
			String username = null;
			String password = null;
			System.out.println("\n�����û�����Ϣ��");
			for(Element user : usersEC) {
				username = user.getChildText("username");
				password = user.getChildText("password");
				System.out.println("�û�����"+username);
				System.out.println("���룺"+password);
				users.put(username,password);
			}
			
			/*
			//ӵ��putȨ�޺�deleteȨ�޵��û�
			System.out.println("\n����Ա�û���");
			Element adminUsersE = root.getChild("adminUsers");
			for(Element adminUserTemp: (List<Element>)adminUsersE.getChildren()) {
				username = adminUserTemp.getText();
				//System.out.println("�û�����"+username);
				adminUsers.add(username);
			}	*/
		} catch (JDOMException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}	
	}

}
