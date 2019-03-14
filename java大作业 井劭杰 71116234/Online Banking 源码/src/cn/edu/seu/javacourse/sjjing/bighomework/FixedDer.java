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

public class FixedDer {

	private Socket socket;
	private DataInputStream dis;
	private DataOutputStream dos;
	
	private JFrame fixedDer = new JFrame("定期存款界面");
	
	private JPanel jp1 = new JPanel();
	private JPanel jp2 = new JPanel();
	private JPanel jp3 = new JPanel();
	
	private JButton confirm = new JButton("Done!");
	private JTextField moneyNumber = new JTextField(20);
	
	public FixedDer(Socket socket, DataInputStream dis, DataOutputStream dos){
		this.socket = socket;
		this.dis = dis;
		this.dos = dos;
	}
	
	public void createFixedDer(){
		
		class mylister extends WindowAdapter{
			public void windowClosing(WindowEvent e){
				//System.exit(0);
					
				//清除jp678里的内容
				jp1.removeAll();
				jp2.removeAll();
				jp3.removeAll();
			}
			
		}
		
		fixedDer.addWindowListener(new mylister());
		jp1.add(new Label("这里是定期存款界面"));
		jp2.add(new Label("存款金额："));
		jp2.add(moneyNumber);
		jp3.add(confirm);
		fixedDer.add(jp1, BorderLayout.NORTH);
		fixedDer.add(jp2, BorderLayout.CENTER);
		fixedDer.add(jp3, BorderLayout.SOUTH);
		fixedDer.pack();
		fixedDer.setLocationRelativeTo(null);
		fixedDer.setVisible(true);
		
		confirm.addActionListener(e -> {
			
			try {
				dos.writeUTF("fixedD");
				dos.writeUTF("one");
				dos.writeUTF(moneyNumber.getText());
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			
			JOptionPane.showMessageDialog(fixedDer, "存款成功！");
			
		});
	}
}
	
    
