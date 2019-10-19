package History;

import genda1.Window;
import Login.*;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.*;
import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import javax.swing.*;
import javax.swing.table.*;

public class historyFrame extends JFrame implements ActionListener {
	public static JTable table;
	public static DefaultTableModel tableM;
	JScrollPane tableN;
	JButton lookwenben;
	JButton shaixuansaiwen, showall;
	JTextField lookcow;
	public static JTextField datef, suduf, dayf;
	static JTextField dangqianye;
	JButton shaixuandate, shaixuansudu, next, before, go, gofirst, golast,
			keephistroy;
	static JButton yemashow;
	Socket socket;
	DataOutputStream out;
	DataInputStream in;
	static Vector vRow1;
	static int fenye = 50;
	static int dangqian = 0;
	static int yenum = 0;

	public static List<String> id = new ArrayList<String>();
	public static List<Vector> allhistory = new ArrayList<Vector>();
	JPanel p = new JPanel();

	public historyFrame() {
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		setBounds(10, 10, 850, screenSize.height * 3 / 4);

		init();
		setVisible(true);
		// setTitle("跟打记录");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);// 设置关闭按钮
	}

	void init() {
		// setLayout(null);
		p.setLayout(null);
		add(p);
		Object name[] = { "", "日期", "速度", "击键", "码长", "字数", "回改", "退格", "错字",
				"选重", "键准", "键法", "打词率", "时间(秒)", "段数" }, a[][] = null;
		tableM = new DefaultTableModel(a, name) {
			private static final long serialVersionUID = 1L;

			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		table = new JTable(tableM);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		tableN = new JScrollPane(table);

		tableN.setBounds(0, 0, 840, this.getHeight() - 105);

		before = new JButton("上一页");
		next = new JButton("下一页");
		dangqianye = new JTextField("跳转");
		go = new JButton("跳转");
		gofirst = new JButton("跳到首页");
		golast = new JButton("跳到尾页");
		keephistroy = new JButton("保存记录");
		yemashow = new JButton("");
		before.setBounds(10, tableN.getY() + tableN.getHeight(), 120, 30);
		yemashow.setBounds(140, tableN.getY() + tableN.getHeight(), 60, 30);
		next.setBounds(210, tableN.getY() + tableN.getHeight(), 120, 30);
		dangqianye.setBounds(340, tableN.getY() + tableN.getHeight(), 60, 30);
		go.setBounds(410, tableN.getY() + tableN.getHeight(), 60, 30);
		gofirst.setBounds(480, tableN.getY() + tableN.getHeight(), 100, 30);
		golast.setBounds(590, tableN.getY() + tableN.getHeight(), 100, 30);
		keephistroy.setBounds(700, tableN.getY() + tableN.getHeight(), 100, 30);
		lookcow = new JTextField("要查看内容的行数");
		lookcow.setBounds(10, tableN.getY() + tableN.getHeight() + 35, 120, 30);
		lookwenben = new JButton("查看跟打内容");
		lookwenben.setBounds(140, tableN.getY() + tableN.getHeight() + 35, 120,
				30);
		datef = new JTextField("具体日期");
		suduf = new JTextField("速度大于");
		dayf = new JTextField("");
		shaixuandate = new JButton("筛选日期");
		shaixuansudu = new JButton("筛选速度");
		shaixuansaiwen = new JButton("筛选赛文成绩");
		showall = new JButton("全");
		datef.setBounds(270, tableN.getY() + tableN.getHeight() + 35, 60, 30);
		suduf.setBounds(450, tableN.getY() + tableN.getHeight() + 35, 60, 30);

		shaixuandate.setBounds(340, tableN.getY() + tableN.getHeight() + 35,
				90, 30);
		shaixuansudu.setBounds(520, tableN.getY() + tableN.getHeight() + 35,
				90, 30);
		shaixuansaiwen.setBounds(620, tableN.getY() + tableN.getHeight() + 35,
				120, 30);
		showall.setBounds(750, tableN.getY() + tableN.getHeight() + 35, 40, 30);
		table.getColumnModel().getColumn(0).setPreferredWidth(50);
		table.getColumnModel().getColumn(1).setPreferredWidth(120);

		historywenbenListener historywenbenlistener = new historywenbenListener(
				lookcow);
		lookwenben.addActionListener(historywenbenlistener);
		historyselect historysl = new historyselect();
		historyfanye historyfanye = new historyfanye();

		shaixuandate.addActionListener(historysl);
		shaixuansudu.addActionListener(historysl);
		shaixuansaiwen.addActionListener(historysl);
		next.addActionListener(historyfanye);
		before.addActionListener(historyfanye);
		gofirst.addActionListener(historyfanye);
		golast.addActionListener(historyfanye);
		go.addActionListener(historyfanye);
		showall.addActionListener(this);
		keephistroy.addActionListener(new WritehistoryListener());

		p.add(tableN);
		p.add(lookwenben);
		p.add(lookcow);
		p.add(datef);
		p.add(suduf);
		p.add(dayf);
		p.add(shaixuandate);
		p.add(shaixuansudu);
		p.add(shaixuansaiwen);
		p.add(next);
		p.add(before);
		p.add(go);
		p.add(dangqianye);
		p.add(golast);
		p.add(gofirst);
		p.add(yemashow);
		p.add(showall);
		p.add(keephistroy);
		request();
		this.setResizable(false);
	}

	public static double aversudu = 0;
	public static double averkey = 0;
	public static double averkeylength = 0;
	public static double avernumber = 0;
	public static double averdeletetext = 0;
	public static double averdelete = 0;
	public static double avermistake = 0;
	public static double averrepeat = 0;
	public static double averkeyaccur = 0;
	public static double averkeymethod = 0;
	public static double averdacilv = 0;
	public static double avertime = 0;
	static int n = 0;
	static int num = 0;

	void request() {
		try {
			socket = new Socket(Window.IP, Login.port);
			socket.setSoTimeout(2000);
			out = new DataOutputStream(socket.getOutputStream());
			in = new DataInputStream(socket.getInputStream());
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			System.out.println("跟打记录网络错误1");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("跟打记录网络错误2");
		}
		try {
			out.writeUTF("跟打记录");
			out.writeUTF(Login.zhanghao.getText());

			clearall();
			while (true) {
				vRow1 = new Vector();
				vRow1.add(++n);
				for (int i = 0; i < 14; i++) {
					vRow1.add(in.readUTF());
				}
				getvROW();
				id.add(in.readUTF());
				allhistory.add(vRow1);
				yenum++;
			}
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			try {
				socket.close();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			System.out.println("排名结束");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			try {
				socket.close();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			System.out.println("排名结束");
		}
		aver();
		showtable();
		yenum = (yenum / fenye);
		yemashow.setText(String.valueOf(dangqian + 1) + "/"
				+ String.valueOf(yenum + 1));
	}

	public static void showtable() {
		Vector vRow1 = new Vector();
		vRow1.add("0");
		vRow1.add("平均成绩");
		vRow1.add(String.format("%.2f", aversudu));
		vRow1.add(String.format("%.2f", averkey));
		vRow1.add(String.format("%.2f", averkeylength));
		vRow1.add(String.format("%.2f", avernumber));
		vRow1.add(String.format("%.2f", averdeletetext));
		vRow1.add(String.format("%.2f", averdelete));
		vRow1.add(String.format("%.2f", avermistake));
		vRow1.add(String.format("%.2f", averrepeat));
		vRow1.add(String.format("%.2f", averkeyaccur));
		vRow1.add(String.format("%.2f", averkeymethod));
		vRow1.add(String.format("%.2f", averdacilv));
		vRow1.add(String.format("%.2f", avertime));
		String duanwei = "";
		switch ((int) (aversudu / 10)) {
		case 0:
		case 1:
		case 2:
			duanwei = "一级";
			break;
		case 3:
			duanwei = "二级";
			break;
		case 4:
			duanwei = "三级";
			break;
		case 5:
			duanwei = "四级";
			break;
		case 6:
		case 7:
			duanwei = "五级";
			break;
		case 8:
			duanwei = "六级";
			break;
		case 9:
			duanwei = "七级";
			break;
		case 10:
			duanwei = "八级";
			break;
		case 11:
			duanwei = "九级";
			break;
		case 12:
			duanwei = "一段";
			break;
		case 13:
			duanwei = "二段";
			break;
		case 14:
			duanwei = "三段";
			break;
		case 15:
			duanwei = "四段";
			break;
		case 16:
			duanwei = "五段";
			break;
		case 17:
			duanwei = "六段";
			break;
		case 18:
			duanwei = "七段";
			break;
		case 19:
			duanwei = "八段";
			break;
		case 20:
			duanwei = "九段";
			break;
		case 21:
			duanwei = "一星";
			break;
		case 22:
			duanwei = "二星";
			break;
		case 23:
			duanwei = "三星";
			break;
		case 24:
			duanwei = "四星";
			break;
		case 25:
			duanwei = "五星";
			break;
		case 26:
			duanwei = "六星";
			break;
		case 27:
			duanwei = "七星";
			break;
		case 28:
			duanwei = "八星";
			break;
		case 29:
			duanwei = "九星";
			break;
		case 30:
			duanwei = "一重";
			break;
		case 31:
			duanwei = "二重";
			break;
		case 32:
			duanwei = "三重";
			break;
		case 33:
			duanwei = "四重";
			break;
		case 34:
			duanwei = "五重";
			break;
		case 35:
			duanwei = "六重";
			break;
		case 36:
			duanwei = "七重";
			break;
		case 37:
			duanwei = "八重";
			break;
		case 38:
			duanwei = "九重";
			break;
		case 39:
			duanwei = "十重";
			break;
		case 40:
		case 41:
		case 42:
		case 43:
		case 44:
			duanwei = "地手";
			break;
		case 45:
		case 46:
		case 47:
		case 48:
		case 49:
			duanwei = "天手";
			break;
		default:
			duanwei = "极手";
			break;
		}
		vRow1.add(duanwei);
		tableM.addRow(vRow1);
		for (int i = fenye * dangqian; i < fenye * (dangqian + 1); i++)
			try {
				tableM.addRow(allhistory.get(i));
			} catch (Exception e) {
				System.out.println("显示跟打历史结束");
				break;
			}
	}

	public static void clear() {
		while (historyFrame.table.getRowCount() > 0)
			historyFrame.tableM.removeRow(0);
	}

	public static void aver() {
		aversudu /= num;
		averkey /= num;
		averkeylength /= num;
		avernumber /= num;
		averdeletetext /= num;
		averdelete /= num;
		avermistake /= num;
		averrepeat /= num;
		averkeyaccur /= num;
		averkeymethod /= num;
		averdacilv /= num;
		avertime /= num;
	}

	void getvROW() {
		aversudu += Double.parseDouble((String) vRow1.get(2));
		averkey += Double.parseDouble((String) vRow1.get(3));
		averkeylength += Double.parseDouble((String) vRow1.get(4));
		avernumber += Double.parseDouble((String) vRow1.get(5));
		averdeletetext += Double.parseDouble((String) vRow1.get(6));
		averdelete += Double.parseDouble((String) vRow1.get(7));
		avermistake += Double.parseDouble((String) vRow1.get(8));
		averrepeat += Double.parseDouble((String) vRow1.get(9));
		averkeyaccur += Double.parseDouble((String) vRow1.get(10));
		averkeymethod += Double.parseDouble((String) vRow1.get(11));
		averdacilv += Double.parseDouble((String) vRow1.get(12));
		avertime += Double.parseDouble((String) vRow1.get(13));
		num++;
	}

	public static void clearall() {
		id.clear();
		allhistory.clear();
		clear();
		n = 0;
		num = 0;
		historyFrame.yenum = 0;
		historyFrame.dangqian = 0;
		historyFrame.aversudu = 0;
		historyFrame.averkey = 0;
		historyFrame.averkeylength = 0;
		historyFrame.avernumber = 0;
		historyFrame.averdeletetext = 0;
		historyFrame.averdelete = 0;
		historyFrame.avermistake = 0;
		historyFrame.averrepeat = 0;
		historyFrame.averkeyaccur = 0;
		historyFrame.averkeymethod = 0;
		historyFrame.averdacilv = 0;
		historyFrame.avertime = 0;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		request();
	}
}
