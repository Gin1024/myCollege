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

public class Loaner {

	private Socket socket;
	private DataInputStream dis;
	private DataOutputStream dos;
	
	private JFrame loaner = new JFrame("�������");
	
	private JPanel jp1 = new JPanel();
	private JPanel jp2 = new JPanel();
	private JPanel jp3 = new JPanel();
	
	private JButton confirm = new JButton("Done!");
	private JTextField moneyNumber = new JTextField(20);
	
    public Loaner(Socket socket, DataInputStream dis, DataOutputStream dos){
    	this.socket = socket;
    	this.dis = dis;
		this.dos = dos;
    }
	
	public void createLoaner(){
    	class mylister extends WindowAdapter{
    		public void windowClosing(WindowEvent e){
    			//System.exit(0);
    				
    			//���jp123�������
    			jp1.removeAll();
    			jp2.removeAll();
    			jp3.removeAll();
    		}
    		
    	}
    	
    	loaner.addWindowListener(new mylister());
    	jp1.add(new Label("�����Ǵ������     ��Ϣ��úܣ������������"));
    	jp2.add(new Label("�����"));
    	jp2.add(moneyNumber);
    	jp3.add(confirm);
    	loaner.add(jp1, BorderLayout.NORTH);
    	loaner.add(jp2, BorderLayout.CENTER);
    	loaner.add(jp3, BorderLayout.SOUTH);
    	loaner.pack();
    	loaner.setLocationRelativeTo(null);
    	loaner.setVisible(true);
    	
    	JOptionPane.showMessageDialog(loaner, "��Ϣ��úܣ������������");
    	
    	confirm.addActionListener(e -> {
    		
    		try {
				dos.writeUTF("loan");
				dos.writeUTF("one");
				dos.writeUTF(moneyNumber.getText());
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
    		
    		JOptionPane.showMessageDialog(loaner, "����ɹ��������ѷ��ŵ����ڴ���У�");
    		loan();
    	});
	}
    
    private void loan(){
		
	}
}
