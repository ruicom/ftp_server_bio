package ftpServer;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;

public class FtpWriter {

	private Writer writer;
	
	public FtpWriter(Writer writer) {
		this.writer = writer;
	}
	
	public FtpWriter(OutputStream os) {
		 this.writer = new BufferedWriter(new OutputStreamWriter(os));
	}
	
	public void sendResponse(String response) throws IOException {
		writer.write(response);
		writer.write("\r\n");
		writer.flush();
	}
}
