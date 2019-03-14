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
	private JFrame login = new JFrame("登录");

	private JPanel jp1 = new JPanel();
	private JPanel jp2 = new JPanel();
	private JPanel jp3 = new JPanel();
	
	private JButton registerButton = new JButton("注册");
	private JButton loginButton = new JButton("登录");
	
	private JTextField userField = new JTextField(20);
	private JTextField passField = new JTextField(20);
	
	
	
	
	public Account(Socket socket, DataInputStream dis, DataOutputStream dos){
		this.socket = socket;
		this.dis = dis;
		this.dos = dos;
	}
	
	
	
	
	//构造登录界面
	public void init()throws Exception{
		
		//加载驱动
		Class.forName("com.mysql.jdbc.Driver");
		
		class mylister extends WindowAdapter{
			//关闭窗口立即退出程序
			public void windowClosing(WindowEvent e){
				System.exit(0);
			}
		}
		
		
		login.addWindowListener(new mylister());
		
		jp1.add(new Label("用户名："));
		jp1.add(userField);
		jp2.add(new Label(" 密码 ： "));
		jp2.add(passField);
		jp3.add(registerButton);
		jp3.add(loginButton);
		login.add(jp3, BorderLayout.SOUTH);
		login.add(jp2, BorderLayout.CENTER);
		login.add(jp1, BorderLayout.NORTH);
		login.pack();
		login.setLocationRelativeTo(null);
		login.setVisible(true);
		
		
		
		//监听按钮
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
			JOptionPane.showMessageDialog(login, "已有账号 请直接登陆");
		if(msg1.equals("please login"))
			JOptionPane.showMessageDialog(login, "注册成功请登录");
		
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
		
		//测试 查看变量值
		System.out.println(msg2);
		//System.out.println(msg2);
		
		if(msg2.equals("login true")){
			JOptionPane.showMessageDialog(login, "登陆成功");
			Controller controller = new Controller(socket,dis,dos);
			Interest interest = new Interest(userField.getText(), passField.getText());
			controller.createController();
			interest.start();
		}
		if(msg2.equals("login false"))
			JOptionPane.showMessageDialog(login, "登陆失败");
		break;
		}
		
	}
	
}
