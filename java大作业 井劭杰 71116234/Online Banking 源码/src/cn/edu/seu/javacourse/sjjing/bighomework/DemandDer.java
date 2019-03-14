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

public class DemandDer {

	private Socket socket;
	private DataInputStream dis;
	private DataOutputStream dos;
	
	private JFrame demandDer = new JFrame("活期存款界面");
	
	private JPanel jp1 = new JPanel();
	private JPanel jp2 = new JPanel();
	private JPanel jp3 = new JPanel();
	
	private JButton confirm = new JButton("Done!");
	private JTextField moneyNumber = new JTextField(20);
	
    public DemandDer(Socket socket, DataInputStream dis, DataOutputStream dos){
    	this.socket = socket;
    	this.dis = dis;
		this.dos = dos;
    }
	
	public void createDemandDer(){
		
    	class mylister extends WindowAdapter{
			public void windowClosing(WindowEvent e){
				//System.exit(0);
					
				//清除jp91011里的内容
				jp1.removeAll();
				jp2.removeAll();
				jp3.removeAll();
			}
			
		}
		
		demandDer.addWindowListener(new mylister());
		jp1.add(new Label("这里是活期存款界面"));
		jp2.add(new Label("存款金额："));
		jp2.add(moneyNumber);
		jp3.add(confirm);
		demandDer.add(jp1, BorderLayout.NORTH);
		demandDer.add(jp2, BorderLayout.CENTER);
		demandDer.add(jp3, BorderLayout.SOUTH);
		demandDer.pack();
		demandDer.setLocationRelativeTo(null);
		demandDer.setVisible(true);
		
		confirm.addActionListener(e -> {
			
			try {
				dos.writeUTF("demandD");
				dos.writeUTF("one");
				dos.writeUTF(moneyNumber.getText());
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			
			
			JOptionPane.showMessageDialog(demandDer, "存款成功！");
			demandDeposit();
		});
	}
    
    private void demandDeposit(){
		
	}
}
	

