package GendaServer;
import java.io.*;
import java.net.Socket;

public class AllServer {
	int portNum[] = {1111,2222,3333,4444,5555,6666,7777,8888};
	Thread serverRun[] = new Thread[10];
	public void BuildPort(Server server[]){
		int i;
		for(i=0;i<portNum.length;i++){
			server[i] = new Server(portNum[i]);
			serverRun[i] = new Thread(server[i]);
			serverRun[i].start();
		}
		LinkMyself linkmyself = new LinkMyself(portNum);
		linkmyself.start();
	}
	
}
