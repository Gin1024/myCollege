//online banking
//���û�ͬʱ��¼ ������ͬʱ����
//by GIN ��ۿ�� 71116234
//2018.1.26

package cn.edu.seu.javacourse.sjjing.bighomework;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;


public class Client {

	public static void main(String[] args) throws Exception{
		// TODO Auto-generated method stub

		Socket socket = new Socket("localhost", 8080);
		//ʵ����������
		InputStream is = socket.getInputStream();
		DataInputStream dis = new DataInputStream(is);
		//ʵ���������
		OutputStream os = socket.getOutputStream();
		DataOutputStream dos = new DataOutputStream(os);
		
		Account a = new Account(socket,dis,dos);
		a.init();
	}

}
