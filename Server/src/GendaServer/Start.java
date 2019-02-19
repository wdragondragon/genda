package GendaServer;

public class Start {

	 public static void main(String[] args) {
		 Server server[]=new Server[8];
		 AllServer allserver = new AllServer();
		 allserver.BuildPort(server);
//		 server.innit();
	 }
}
