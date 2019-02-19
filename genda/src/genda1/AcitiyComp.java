package genda1;

public class AcitiyComp extends Thread{
	
	public void run(){
		while(true){
			try {
				sleep(100);
				if(GendaListener.sign==1){
					GendaListener.comp.setTimeTwo();
					
					Window.gendaListener.sudu = GendaListener.comp.getSpeed(GendaListener.str1.length(),0);		//速度计算算法
					Window.suduButton.setText(String.format("%.2f",Window.gendaListener.sudu));
					Window.gendaListener.KeysuduButton.setText(String.format("%.2f",Window.gendaListener.KeyNumber/GendaListener.comp.getSecond()));
					Window.gendaListener.Keylength.setText(String.format("%.2f", Window.gendaListener.KeyNumber/GendaListener.str1.length()));
				}
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}