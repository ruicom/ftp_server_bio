package ftpServer;

/**
 * 命令工厂
 * */
public class CommandFactory {

	/**
	 * 根据客户端传送过来的命令生成相应的命令处理对象
	 * @param 客户端传送过来的对象。
	 * @return Command 根据命令类型生成的命令处理对象。
	 * */
	public static Command createCommand(String type) {
		
		type = type.toUpperCase();
		switch(type)
		{
			case "USER":return new UserCommand();
			
			case "PASS":return new PassCommand();
			
			case "LIST":return new DirCommand();
			
			case "PORT":return new PortCommand();
			
			case "QUIT":return new QuitCommand();
			
			case "RETR":return new RetrCommand();
			
			case "CWD":return new CwdCommand();
			
			case "STOR":return new StoreCommand();
			
			default :return null;
		}
		
	}
}
