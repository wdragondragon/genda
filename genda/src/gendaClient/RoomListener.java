package gendaClient;
import genda1.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTextArea;

public class RoomListener implements ActionListener{

	JTextArea accept;
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getActionCommand()=="一房"){
			accept.setText("已选中一号房\n");
			Window.RoomNum = 1;
		}
		else if(e.getActionCommand()=="二房"){
			accept.setText("已选中二号房\n");
			Window.RoomNum = 2;
		}
		else if(e.getActionCommand()=="三房"){
			accept.setText("已选中三号房\n");
			Window.RoomNum = 3;
		}
		else if(e.getActionCommand()=="四房"){
			accept.setText("已选中四号房\n");
			Window.RoomNum = 4;
		}
		else if(e.getActionCommand()=="五房"){
			accept.setText("已选中五号房\n");
			Window.RoomNum = 5;
		}
		else if(e.getActionCommand()=="六房"){
			accept.setText("已选中六号房\n");
			Window.RoomNum = 6;
		}
		else if(e.getActionCommand()=="七房"){
			accept.setText("已选中七号房\n");
			Window.RoomNum = 7;
		}
		else if(e.getActionCommand()=="八房"){
			accept.setText("已选中八号房\n");
			Window.RoomNum = 8;
		}		
	}
	public void setAccept(JTextArea t){
		accept = t;
	}
}
