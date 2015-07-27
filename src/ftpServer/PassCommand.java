package ftpServer;

import java.io.IOException;
import java.io.Writer;

public class PassCommand implements Command{
	
	@Override
	public void getResult(String data, Writer writer,ControllerThread t) {
		
		System.out.println("execute the pass command");
		System.out.println("the data is "+data);
		//»ñµÃÓÃ»§Ãû
		String key = ControllerThread.USER.get();
		String pass = Share.users.get(key);
		
		String response = null;
		if(pass.equals(data)) {
			System.out.println("µÇÂ¼³É¹¦");
			Share.loginedUser.add(key);
			t.setIsLogin(true);
			response = "230 User "+key+" logged in";
		}
		else {
			System.out.println("µÇÂ¼Ê§°Ü£¬ÃÜÂë´íÎó");
			response = "530   ÃÜÂë´íÎó";
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
