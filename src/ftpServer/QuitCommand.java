package ftpServer;

import java.io.IOException;
import java.io.Writer;

public class QuitCommand implements Command{

	/**
	 * ftp–≠“Èquit√¸¡Ó
	 * */
	@Override
	public String getResult(String data, ControllerThread t) {
	
			try {
				t.getSocket().close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return "221 goodbye.";
	}
	

}
