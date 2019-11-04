package Login;


import genda1.Example;
import genda1.Window;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.io.*;
import java.net.Socket;


import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.UIManager;

import keep.KeyPassword;
import keep.readWrite;


public class DengluListener implements ActionListener{
	Login login;
	heartThread heartThread;
	int num[] = {0,0,0,0,0,0};
	DengluListener(Login login){
		this.login = login;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		denglu();
	}
	public void denglu() {
		if (Login.dengluSign == 0) {
			try {
				Login.socket = new Socket(Window.IP, Login.port);
				
				Login.socket.setSoTimeout(500);
				Login.out = new DataOutputStream(Login.socket.getOutputStream());
				Login.in = new DataInputStream(Login.socket.getInputStream());
//				ObjectOutputStream outputToServer = new ObjectOutputStream(Login.out);
//				ObjectInputStream inputByServer = new ObjectInputStream(Login.in);
				Login.out.writeUTF(Login.banben);
				String what = Login.in.readUTF();
				Login.socket.setSoTimeout(0);
				if(!what.substring(0,4).equals("�汾��ȷ")){
					UIManager.put("OptionPane.yesButtonText", "�Զ�����");
					UIManager.put("OptionPane.noButtonText", "�ֶ�����");
					int n = JOptionPane.showConfirmDialog(null, what, "������ʾ", JOptionPane.YES_NO_OPTION);
					if (n == JOptionPane.YES_OPTION) {
						// ......
						if(Example.systemname.length()>=7&&Example.systemname.substring(0,7).equals("Windows"))
							Runtime.getRuntime().exec("����.exe");
						else
							Runtime.getRuntime().exec("java -jar update.jar");
						System.exit(0);
					} else if (n == JOptionPane.NO_OPTION) {
						// ......
						Runtime.getRuntime().exec("rundll32 url.dll,FileProtocolHandler http://39.96.83.89/new.zip");
						return;
					}
				}
				Login.zxbanben = what.substring(what.indexOf("���°汾")+4);
				Window.zxbanben.setText(Window.zxbanben.getText()+Login.zxbanben);
				
				String message = "%1%" + Login.zhanghao.getText() + "%" + Login.mima.getText() + "%-999" + "%��" + "%��" + "%��";
				message = KeyPassword.convertMD5(message);
				Login.out.writeUTF(message);
				message = Login.in.readUTF();
				System.out.println(message);
				int pos = 0;
				for (int i = 0; i < 6; i++) {
					pos = message.indexOf('%', pos) + 1;
					if (pos != -1)
						num[i] = pos;
					else break;
				}
				int i = Integer.parseInt(message.substring(num[0], num[1] - 1));    //���ܵ�¼���

				int zishu = Integer.parseInt(message.substring(num[1], num[2] - 1));        //������������
				int rightnum = Integer.parseInt(message.substring(num[2], num[3] - 1));
				int misnum = Integer.parseInt(message.substring(num[3], num[4] - 1));
				int datenum = Integer.parseInt(message.substring(num[4], num[5] - 1));
				Window.Email = message.substring(num[5]);
				Login.socket.setSoTimeout(0);
				if (i == 1) {
					Login.dengluSign = 1;
					Window.fontallnum = zishu;
					Window.rightnum = rightnum;
					Window.misnum = misnum;
					Window.datenum = datenum;
//					JOptionPane.showMessageDialog(new JTextArea(),"��¼�ɹ�");
					Login.confirm.setText("�˳���¼");
					Window.denglu.setText("��ӭ��" + Login.zhanghao.getText());
					Window.wenben.setText("��¼�ɹ�" + "\n" + "��ӭ��" + Login.zhanghao.getText() + "\n����������Ⱥ:974172771");
//					SendQQMessage.sendmessage(Login.zhanghao.getText() + "�����ߣ���������Ϣֻ�ᷢ�͵�����������Ⱥ��");
					Login.zhanghao.setEditable(false);
					Login.mima.setEditable(false);
					readWrite.setzm();
					login.setVisible(false);

					Window.everydaySign = false;

					heartThread = new heartThread(this);//����������
					heartThread.start();
				} else if (i == 2)
					JOptionPane.showMessageDialog(new JTextArea(), "�������");
				else if (i == 3)
					JOptionPane.showMessageDialog(new JTextArea(), "�˻�������");
			} catch (Exception e1) {
				e1.printStackTrace();
				JOptionPane.showMessageDialog(new JTextArea(), "��������2");
			}
		} else if (Login.dengluSign == 1) {
			try {
				Login.out.writeUTF("�Ͽ�");
				Login.zhanghao.setEditable(true);
				Login.mima.setEditable(true);
				Login.confirm.setText("��¼");
				Window.denglu.setText("��¼");
				Login.dengluSign = 0;
				Login.in.close();
				Login.out.close();
				Login.socket.close();
				heartThread.stop();
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				JOptionPane.showMessageDialog(new JTextArea(), "��������1");
			}
		} else {
			JOptionPane.showMessageDialog(new JTextArea(), "���ѵ�¼");
		}
	}
}
