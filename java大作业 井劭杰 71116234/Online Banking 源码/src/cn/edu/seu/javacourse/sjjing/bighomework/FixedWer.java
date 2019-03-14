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
import javax.swing.JTextField;

public class FixedWer {

	private Socket socket;
	private DataInputStream dis;
	private DataOutputStream dos;
	
	private JFrame fixedWer = new JFrame("定期取款界面");
	
	private JPanel jp1 = new JPanel();
	private JPanel jp2 = new JPanel();
	private JPanel jp3 = new JPanel();
	
	private JButton confirm = new JButton("Done!");
	private JTextField moneyNumber = new JTextField(20);
	
    public FixedWer(Socket socket, DataInputStream dis, DataOutputStream dos){
    	this.socket = socket;
    	this.dis = dis;
		this.dos = dos;
    }
	
	public void createFixedWer(){
    	
    	class mylister extends WindowAdapter{
			public void windowClosing(WindowEvent e){
				//System.exit(0);
					
				//清除jp678里的内容
				jp1.removeAll();
				jp2.removeAll();
				jp3.removeAll();
			}
			
		}
		
		fixedWer.addWindowListener(new mylister());
		jp1.add(new Label("这里是定期取款界面"));
		jp2.add(new Label("取款金额："));
		jp2.add(moneyNumber);
		jp3.add(confirm);
		fixedWer.add(jp1, BorderLayout.NORTH);
		fixedWer.add(jp2, BorderLayout.CENTER);
		fixedWer.add(jp3, BorderLayout.SOUTH);
		fixedWer.pack();
		fixedWer.setLocationRelativeTo(null);
		fixedWer.setVisible(true);
		
		confirm.addActionListener(e -> {
			
			String msg = null;
			
			try {
				dos.writeUTF("fixedW");
				dos.writeUTF("one");
				dos.writeUTF(moneyNumber.getText());
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			try {
				msg = dis.readUTF();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			if(msg.equals("true"))
				JOptionPane.showMessageDialog(fixedWer, "取款成功！");
			else
				JOptionPane.showMessageDialog(fixedWer, "余额不足！");
			
		});
	}
	
}
