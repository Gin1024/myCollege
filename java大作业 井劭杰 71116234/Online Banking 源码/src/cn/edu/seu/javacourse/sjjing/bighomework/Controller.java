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
		
	private JFrame controller = new JFrame("������");
	private JPanel jp1 = new JPanel();
	private JPanel jp2 = new JPanel();
		
	private JButton fixedD = new JButton("���ڴ��");
	private JButton demandD = new JButton("���ڴ��");
	private JButton fixedW = new JButton("����ȡ��");
	private JButton demandW = new JButton("����ȡ��");
	private JButton showMoney = new JButton("��ѯ���");
	private JButton loan = new JButton("����");
	private JButton repay = new JButton("����");
		
	
	public Controller(Socket socket, DataInputStream dis, DataOutputStream dos){
		this.socket = socket;
		this.dis = dis;
		this.dos = dos;
	}
	
	
	
	
	public void createController(){
			
			
		class mylister extends WindowAdapter{
			public void windowClosing(WindowEvent e){
				//System.exit(0);
				//ע�͵�����˼�� �ر��������������������   controller����Ҫ
					
				//���jp12�������
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
		jp2.add(new Label("��ӭ����������cooooooool��online banking"));
		controller.add(jp1, BorderLayout.CENTER);
		controller.add(jp2, BorderLayout.NORTH);
		controller.pack();
		controller.setLocationRelativeTo(null);
		controller.setVisible(true);
			
		//������ť
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
			
			String msg = "����cooooooool�˻����Ϊ��"+deposit+" ���ж��ڴ�"+fixed+" ���ڴ�"+demand+"";
			JOptionPane.showMessageDialog(controller,msg);
		});
			
	}
}
