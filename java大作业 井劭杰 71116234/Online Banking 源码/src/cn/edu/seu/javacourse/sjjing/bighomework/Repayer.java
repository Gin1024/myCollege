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

public class Repayer {

	private Socket socket;
	private DataInputStream dis;
	private DataOutputStream dos;
	
	private JFrame repayer = new JFrame("还款界面");
	
	private JPanel jp1 = new JPanel();
	private JPanel jp2 = new JPanel();
	private JPanel jp3 = new JPanel();
	
	private JButton confirm = new JButton("Done!");
	private JTextField moneyNumber = new JTextField(20);
	
	public Repayer(Socket socket, DataInputStream dis, DataOutputStream dos){
		this.socket = socket;
		this.dis = dis;
		this.dos = dos;
	}

	public void createRepayer() throws IOException{
		class mylister extends WindowAdapter{
    		public void windowClosing(WindowEvent e){
    			//System.exit(0);
    				
    			//清除jp123里的内容
    			jp1.removeAll();
    			jp2.removeAll();
    			jp3.removeAll();
    		}
    		
    	}
		
		dos.writeUTF("showLoan");
		dos.writeUTF("true");
		dos.writeUTF("true");
    	
		int loan = dis.readInt();
		int repay = dis.readInt();
		
    	repayer.addWindowListener(new mylister());
    	jp1.add(new Label("这里是还款界面 您的欠款金额为："+ loan));
    	
    	//最后一次还款时判断 更改最小还款金额
    	if(loan>=repay)
    		jp1.add(new Label("您的最低还款金额为："+ repay));
    	else
    		jp1.add(new Label("您的最低还款金额为：" + loan));
    	
    	jp2.add(new Label("还款金额："));
    	jp2.add(moneyNumber);
    	jp3.add(confirm);
    	repayer.add(jp1, BorderLayout.NORTH);
    	repayer.add(jp2, BorderLayout.CENTER);
    	repayer.add(jp3, BorderLayout.SOUTH);
    	repayer.pack();
    	repayer.setLocationRelativeTo(null);
    	repayer.setVisible(true);
    	
    	confirm.addActionListener(e -> {
    		
    		try {
				dos.writeUTF("repay");
				dos.writeUTF("one");
				dos.writeUTF(moneyNumber.getText());
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			
    		JOptionPane.showMessageDialog(repayer, "还款成功！");
    		
    	});
		}
	
}
