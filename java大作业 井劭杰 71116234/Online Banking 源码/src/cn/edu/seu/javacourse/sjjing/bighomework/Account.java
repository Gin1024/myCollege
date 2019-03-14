package cn.edu.seu.javacourse.sjjing.bighomework;

import java.awt.BorderLayout;
import java.awt.Label;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Account {

	private Socket socket;
	private DataInputStream dis;
	private DataOutputStream dos;
	
	//GUI
	private JFrame login = new JFrame("��¼");

	private JPanel jp1 = new JPanel();
	private JPanel jp2 = new JPanel();
	private JPanel jp3 = new JPanel();
	
	private JButton registerButton = new JButton("ע��");
	private JButton loginButton = new JButton("��¼");
	
	private JTextField userField = new JTextField(20);
	private JTextField passField = new JTextField(20);
	
	
	
	
	public Account(Socket socket, DataInputStream dis, DataOutputStream dos){
		this.socket = socket;
		this.dis = dis;
		this.dos = dos;
	}
	
	
	
	
	//�����¼����
	public void init()throws Exception{
		
		//��������
		Class.forName("com.mysql.jdbc.Driver");
		
		class mylister extends WindowAdapter{
			//�رմ��������˳�����
			public void windowClosing(WindowEvent e){
				System.exit(0);
			}
		}
		
		
		login.addWindowListener(new mylister());
		
		jp1.add(new Label("�û�����"));
		jp1.add(userField);
		jp2.add(new Label(" ���� �� "));
		jp2.add(passField);
		jp3.add(registerButton);
		jp3.add(loginButton);
		login.add(jp3, BorderLayout.SOUTH);
		login.add(jp2, BorderLayout.CENTER);
		login.add(jp1, BorderLayout.NORTH);
		login.pack();
		login.setLocationRelativeTo(null);
		login.setVisible(true);
		
		
		
		//������ť
		loginButton.addActionListener(e ->{
			try {
				login();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		});
		
		registerButton.addActionListener(e -> {
			try {
				register();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		});
		
		}
	
	
	
	private void register() throws IOException{
		
		
		dos.writeUTF("register");
		dos.writeUTF(userField.getText());
		dos.writeUTF(passField.getText());
		
		while(true){
		String msg1 = dis.readUTF();
		
		if(msg1.equals("hava account"))
			JOptionPane.showMessageDialog(login, "�����˺� ��ֱ�ӵ�½");
		if(msg1.equals("please login"))
			JOptionPane.showMessageDialog(login, "ע��ɹ����¼");
		
		break;
		}
	}
	
	private void login() throws IOException{
		
		
		dos.writeUTF("login");
		dos.writeUTF(userField.getText());
		dos.writeUTF(passField.getText());
		
		while(true){
		String msg2 = dis.readUTF();
		//String msg2 = dis.readUTF();
		
		//���� �鿴����ֵ
		System.out.println(msg2);
		//System.out.println(msg2);
		
		if(msg2.equals("login true")){
			JOptionPane.showMessageDialog(login, "��½�ɹ�");
			Controller controller = new Controller(socket,dis,dos);
			Interest interest = new Interest(userField.getText(), passField.getText());
			controller.createController();
			interest.start();
		}
		if(msg2.equals("login false"))
			JOptionPane.showMessageDialog(login, "��½ʧ��");
		break;
		}
		
	}
	
}
