//online banking
//���û�ͬʱ��¼ ������ͬʱ����
//by GIN ��ۿ�� 71116234
//2018.1.26

package cn.edu.seu.javacourse.sjjing.bighomework;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;


public class Server {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub

		
		System.out.println("����������");
		ServerSocket serverSocket = new ServerSocket(8080);
		Socket socket = null;
		
		//�ػ��߳� ʵ�ֶ��û�ͬʱ��¼
		while(true){
			
			socket = serverSocket.accept();
			ServerThread serverThread = new ServerThread(socket);
			serverThread.start();
			
			
			
		}
	}

}
