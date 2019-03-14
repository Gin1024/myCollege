package cn.edu.seu.javacourse.sjjing.bighomework;

import java.awt.BorderLayout;
import java.awt.Label;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class Controller {

	
	private Socket socket;
	private DataInputStream dis;
	private DataOutputStream dos;
	
	
	//for controller
		
	private JFrame controller = new JFrame("主界面");
	private JPanel jp1 = new JPanel();
	private JPanel jp2 = new JPanel();
		
	private JButton fixedD = new JButton("定期存款");
	private JButton demandD = new JButton("活期存款");
	private JButton fixedW = new JButton("定期取款");
	private JButton demandW = new JButton("活期取款");
	private JButton showMoney = new JButton("查询存款");
	private JButton loan = new JButton("贷款");
	private JButton repay = new JButton("还款");
		
	
	public Controller(Socket socket, DataInputStream dis, DataOutputStream dos){
		this.socket = socket;
		this.dis = dis;
		this.dos = dos;
	}
	
	
	
	
	public void createController(){
			
			
		class mylister extends WindowAdapter{
			public void windowClosing(WindowEvent e){
				//System.exit(0);
				//注释掉的意思是 关闭这个窗口立即结束程序   controller不需要
					
				//清除jp12里的内容
				jp1.removeAll();
				jp2.removeAll();
			}
		}
		controller.addWindowListener(new mylister());
		jp1.add(fixedD);
		jp1.add(demandD);
		jp1.add(fixedW);
		jp1.add(demandW);
		jp1.add(showMoney);
		jp1.add(loan);
		jp1.add(repay);
		jp2.add(new Label("欢迎来到酷酷酷酷酷cooooooool的online banking"));
		controller.add(jp1, BorderLayout.CENTER);
		controller.add(jp2, BorderLayout.NORTH);
		controller.pack();
		controller.setLocationRelativeTo(null);
		controller.setVisible(true);
			
		//监听按钮
		fixedD.addActionListener(e -> {
			FixedDer fixedDer = new FixedDer(socket,dis,dos);
			fixedDer.createFixedDer();
		});
		demandD.addActionListener(e -> {
			DemandDer demandDer = new DemandDer(socket,dis ,dos);
			demandDer.createDemandDer();
		});
		fixedW.addActionListener(e -> {
			FixedWer fixedWer = new FixedWer(socket,dis , dos);
			fixedWer.createFixedWer();
		});
		demandW.addActionListener(e -> {
			DemandWer demandWer = new DemandWer(socket,dis,dos);
			demandWer.createDemandWer();
		});
		loan.addActionListener(e -> {
			Loaner loaner = new Loaner(socket, dis, dos);
			loaner.createLoaner();
		});
		repay.addActionListener(e -> {
			Repayer repayer = new Repayer(socket ,dis, dos);
			try {
				repayer.createRepayer();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		});
		showMoney.addActionListener(e -> {
		
			int deposit = 0;
			int fixed = 0;
			int demand = 0;
			
			try {
				dos.writeUTF("show money");
				dos.writeUTF("true");
				dos.writeUTF("true");
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			try {
				deposit = dis.readInt();
				fixed = dis.readInt();
				demand = dis.readInt();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			String msg = "您的cooooooool账户余额为："+deposit+" 其中定期存款："+fixed+" 活期存款："+demand+"";
			JOptionPane.showMessageDialog(controller,msg);
		});
			
	}
}
