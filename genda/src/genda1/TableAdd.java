package genda1;

import java.util.Vector;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class TableAdd {
	DefaultTableModel tableM;
	GendaListener gendaListener;
	JTable table;
	int KeyNumber,mistake,deleteNumber,deleteTextNumber,i=1;
	double sudu,second,length,time;
	public void addRow(){
		sudu = gendaListener.getSudu();
		KeyNumber = (int)(gendaListener.getKeyNumber());
		second = gendaListener.getSecond();
		length = gendaListener.getLength();
		mistake = (int)(gendaListener.getMistake());
		deleteNumber = (int)(gendaListener.getDeleteNumber());
		deleteTextNumber = (int)(gendaListener.getDeleteTextNumber());
		time = gendaListener.comp.getSecond();
		Vector vRow1 = new Vector();
		vRow1.add(i++);
		vRow1.add(String.format("%.2f", sudu));
		vRow1.add(String.format("%.2f",KeyNumber/second));
		vRow1.add(String.format("%.2f", KeyNumber/length));
		vRow1.add((int)length);
		vRow1.add(deleteTextNumber);
		vRow1.add(deleteNumber);
		vRow1.add(mistake);
		vRow1.add((int)gendaListener.getRepeat());
		vRow1.add(String.format("%.2f",AchievementListener.Keyaccuracy*100)+"%");
		vRow1.add(String.format("%.2f",AchievementListener.dacilv*100)+"%");
		vRow1.add(time);
		tableM.addRow(vRow1);
		//移动光标
		int row = table.getRowCount() - 1;//这里获取的是最后一行，当然也可以根据不同的需要获取到不同的行
//		table.setRowSelectionInterval(row, row); //显示当前成绩高亮
		table.scrollRectToVisible(table.getCellRect(row, 0, true));
	}
	public void init(DefaultTableModel t,GendaListener t1,JTable t2){
		tableM = t;
		gendaListener = t1;
		table = t2;
	}
}
