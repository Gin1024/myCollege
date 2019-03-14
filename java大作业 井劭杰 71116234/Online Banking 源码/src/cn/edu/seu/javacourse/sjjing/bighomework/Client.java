//online banking
//多用户同时登录 多任务同时进行
//by GIN 井劭杰 71116234
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
		//实例化输入流
		InputStream is = socket.getInputStream();
		DataInputStream dis = new DataInputStream(is);
		//实例化输出流
		OutputStream os = socket.getOutputStream();
		DataOutputStream dos = new DataOutputStream(os);
		
		Account a = new Account(socket,dis,dos);
		a.init();
	}

}
