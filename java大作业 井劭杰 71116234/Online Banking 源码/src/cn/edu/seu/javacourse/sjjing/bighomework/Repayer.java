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
	
	private JFrame repayer = new JFrame("�������");
	
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
    				
    			//���jp123�������
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
    	jp1.add(new Label("�����ǻ������ ����Ƿ����Ϊ��"+ loan));
    	
    	//���һ�λ���ʱ�ж� ������С������
    	if(loan>=repay)
    		jp1.add(new Label("������ͻ�����Ϊ��"+ repay));
    	else
    		jp1.add(new Label("������ͻ�����Ϊ��" + loan));
    	
    	jp2.add(new Label("�����"));
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
			
			
    		JOptionPane.showMessageDialog(repayer, "����ɹ���");
    		
    	});
		}
	
}
