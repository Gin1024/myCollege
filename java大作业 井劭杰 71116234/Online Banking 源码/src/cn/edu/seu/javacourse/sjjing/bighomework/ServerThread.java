package cn.edu.seu.javacourse.sjjing.bighomework;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.nio.file.attribute.DosFileAttributes;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ServerThread extends Thread {

	private Socket socket = null;
	private String user=null;
	private String password = null;
	
	public ServerThread(Socket socket){
		this.socket = socket;
	}
	
	
	//run()里与客户端信息交互 根据客户端发送的信息 选择不同的操作
	public void run(){
		OutputStream os = null;
		InputStream is = null;
		try {
			os = socket.getOutputStream();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			is = socket.getInputStream();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		DataOutputStream dos = new DataOutputStream(os);
		DataInputStream dis = new DataInputStream(is);
		
		
		//String msg1;
		//String msg2;
		//String msg3;
		try {
			while(true){
				String msg1 = dis.readUTF();
				String msg2=dis.readUTF();
				String msg3=dis.readUTF();
				
				//注册
				if(msg1.equals("register")){
					if(cheak(msg2, msg3))
						dos.writeUTF("please login");
					else
						dos.writeUTF("hava account");
					
						
				}
				
				
				//登录
				if (msg1.equals("login")) {
					
					//测试
					System.out.println("receive");
					
					
					if(validate(msg2, msg3)){
						
						//测试
						System.out.println("done?");
						
						dos.writeUTF("login true");
						user = msg2;
						password = msg3;
					}
					if(!validate(msg2, msg3)){
						dos.writeUTF("login false");
					}
					
				}
				
				
				//定期存款
				if(msg1.equals("fixedD")){
					int money = Integer.parseInt(msg3);
					fixedDeposit(money);
				}
				
				
				//活期存款
				if(msg1.equals("demandD")){
					int money = Integer.parseInt(msg3);
					demandDeposit(money);
				}
				
				//定期取款
				if(msg1.equals("fixedW")){
					int money = Integer.parseInt(msg3);
					if(fixedWithdraw(money)){
						dos.writeUTF("true");
					}
					else {
						dos.writeUTF("false");
					}
				}
				
				
				//活期取款
				if(msg1.equals("demandW")){
					int money = Integer.parseInt(msg3);
					if(demandWithdraw(money)){
						dos.writeUTF("true");
					}
					else {
						dos.writeUTF("false");
					}
				}
				
				
				//查询余额
				if(msg1.equals("show money")){
					showMoney(dos);
				}
				
				
				
				//贷款
				if(msg1.equals("loan")){
					int money = Integer.parseInt(msg3);
					loan(money);
				}
				
				
				//查询贷款
				if(msg1.equals("showLoan")){
					showLoan(dos);
				}
				
				
				//还款
				if(msg1.equals("repay")){
					int money = Integer.parseInt(msg3);
					repay(money);
				}
				
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
	}
	
	//检查是否已有账户
	private boolean validate(String userName, String userPass){
		String sql = "select * from account where username='"+userName+"' and password='"+userPass+"'";
		try {
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/bank?" + 
					"user=root&password=716897&useUnicode=true&characterEncoding=UTF8");
			Statement pstmt = conn.createStatement();
			ResultSet rs = pstmt.executeQuery(sql);
			
			if(rs.next())
				return true;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return false;
	}
	
	//注册方法
	private Boolean cheak(String userName, String userPass) throws Exception{
		//已有账号 直接登陆
		if(validate(userName, userPass))
			return false;
		
		//开始注册
		else{
			String sql = "insert ignore into account values (?,?,?,?,?,?,?)";
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/bank?" +
					"user=root&password=716897&useUnicode=true&characterEncoding=UTF8");
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, userName);
			pstmt.setString(2, userPass);
			pstmt.setInt(3, 0);
			pstmt.setInt(4, 0);
			pstmt.setInt(5, 0);
			pstmt.setInt(6, 0);
			pstmt.setInt(7, 0);
			pstmt.executeUpdate();
			return true;
		}
			
	}

	//定期存款方法
	private void fixedDeposit(int money) throws SQLException{
		
		int deposit = 0;
		int fixed = 0;
		
		String sql1 = "select deposit, fixed from account where username='"+user+"' and password='"+password+"'";
		//String sql2 = "select fixed from account where username='"+user+"' and password='"+password+"'";
		
		Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/bank?" +
				"user=root&password=716897&useUnicode=true&characterEncoding=UTF8");
		Statement pstmt = connection.createStatement();
		ResultSet rs1 = pstmt.executeQuery(sql1);
		//ResultSet rs2 = pstmt.executeQuery(sql2);
		
		if(rs1.next()){
		deposit = rs1.getInt("deposit") + money;
		fixed = rs1.getInt("fixed") + money;
		}
		
		String sql3 = "update account set deposit='"+deposit+"', fixed='"+fixed+"' where username='"+user+"' and password='"+password+"'";
		int rs=pstmt.executeUpdate(sql3);
	}

	//活期存款方法
	private void demandDeposit(int money) throws SQLException{
		int deposit = 0;
		int demand = 0;
		
		String sql1 = "select deposit, demand from account where username='"+user+"' and password='"+password+"'";
		Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/bank?" +
				"user=root&password=716897&useUnicode=true&characterEncoding=UTF8");
		Statement pstmt = connection.createStatement();
		ResultSet rs1 = pstmt.executeQuery(sql1);
		//ResultSet rs2 = pstmt.executeQuery(sql2);
		
		if(rs1.next()){
		deposit = rs1.getInt("deposit") + money;
		demand = rs1.getInt("demand") + money;
		}
		
		String sql3 = "update account set deposit='"+deposit+"', demand='"+demand+"' where username='"+user+"' and password='"+password+"'";
		int rs=pstmt.executeUpdate(sql3);
	}
	
	//定期取款方法
	private Boolean fixedWithdraw(int money) throws SQLException{
		int deposit = 0;
		int fixed = 0;
		
		String sql1 = "select deposit, fixed from account where username='"+user+"' and password='"+password+"'";
		Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/bank?" +
				"user=root&password=716897&useUnicode=true&characterEncoding=UTF8");
		Statement pstmt = connection.createStatement();
		ResultSet rs1 = pstmt.executeQuery(sql1);
		//ResultSet rs2 = pstmt.executeQuery(sql2);
		
		if(rs1.next()){
		deposit = rs1.getInt("deposit");
		fixed = rs1.getInt("fixed");
		}
		
		if(money<=fixed){
			deposit = deposit - money;
			fixed = fixed - money;
			String sql3 = "update account set deposit='"+deposit+"', fixed='"+fixed+"' where username='"+user+"' and password='"+password+"'";
			int rs=pstmt.executeUpdate(sql3);
			return true;
		}
		else {
			return false;
		}
		
		
	
	}
	
	//活期取款方法
	private Boolean demandWithdraw(int money) throws SQLException{
		int deposit = 0;
		int demand = 0;
		
		String sql1 = "select deposit, demand from account where username='"+user+"' and password='"+password+"'";
		Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/bank?" +
				"user=root&password=716897&useUnicode=true&characterEncoding=UTF8");
		Statement pstmt = connection.createStatement();
		ResultSet rs1 = pstmt.executeQuery(sql1);
		//ResultSet rs2 = pstmt.executeQuery(sql2);
		
		if(rs1.next()){
		deposit = rs1.getInt("deposit") ;
		demand = rs1.getInt("demand") ;
		}
		
		if(money<=demand){
			deposit = deposit - money;
			demand = demand - money;
			String sql3 = "update account set deposit='"+deposit+"', demand='"+demand+"' where username='"+user+"' and password='"+password+"'";
		    int rs=pstmt.executeUpdate(sql3);
		    return true;
		}
		else {
			return false;
		}
		
	}
	
	//查询余额方法
	private void showMoney(DataOutputStream dos) throws SQLException, IOException{
		String sql1 = "select deposit, fixed, demand from account where username='"+user+"' and password='"+password+"'";
		Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/bank?" +
				"user=root&password=716897&useUnicode=true&characterEncoding=UTF8");
		Statement pstmt = connection.createStatement();
		ResultSet rs1 = pstmt.executeQuery(sql1);
		if(rs1.next()){
			int deposit = rs1.getInt("deposit");
			int fixed = rs1.getInt("fixed");
			int demand = rs1.getInt("demand");
			
			dos.writeInt(deposit);
			dos.writeInt(fixed);
			dos.writeInt(demand);
		}
	}

	//贷款方法
	private void loan(int money) throws SQLException{
		
		int repay = money/10;
		int deposit = 0;
		int demand = 0;
		
		String sql1 = "select deposit, demand from account where username='"+user+"' and password='"+password+"'";
		String sql2 = "update account set loan='"+money+"', repay='"+repay+"' where username='"+user+"' and password='"+password+"'";
		Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/bank?" +
				"user=root&password=716897&useUnicode=true&characterEncoding=UTF8");
		Statement pstmt = connection.createStatement();
		int rs = pstmt.executeUpdate(sql2);
		ResultSet rs1 = pstmt.executeQuery(sql1);
		if(rs1.next()){
			deposit = rs1.getInt("deposit") + money;
			demand = rs1.getInt("demand") + money;
			}
		String sql3 = "update account set deposit='"+deposit+"', demand='"+demand+"' where username='"+user+"' and password='"+password+"'";
		rs = pstmt.executeUpdate(sql3);
	}

	//查询贷款金额
	private void showLoan(DataOutputStream dos) throws SQLException, IOException{
		String sql1 = "select loan, repay from account where username='"+user+"' and password='"+password+"'";
		Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/bank?" +
				"user=root&password=716897&useUnicode=true&characterEncoding=UTF8");
		Statement pstmt = connection.createStatement();
		ResultSet rs1 = pstmt.executeQuery(sql1);
		if(rs1.next()){
			int loan = rs1.getInt("loan");
			int repay = rs1.getInt("repay");
			dos.writeInt(loan);
			dos.writeInt(repay);
		}
	}

	//还款方法
	private void repay(int money) throws SQLException{
		int deposit = 0;
		int demand = 0;
		int loan = 0;
		String sql3=null;
		
		String sql1 = "select deposit, demand, loan from account where username='"+user+"' and password='"+password+"'";
		Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/bank?" +
				"user=root&password=716897&useUnicode=true&characterEncoding=UTF8");
		Statement pstmt = connection.createStatement();
		ResultSet rs1 = pstmt.executeQuery(sql1);
		
		if(rs1.next()){
		deposit = rs1.getInt("deposit") - money;
		demand = rs1.getInt("demand") - money;
		loan = rs1.getInt("loan") - money;
		}
		
		if(loan<=0){
			money = 0;
			sql3 = "update account set deposit='"+deposit+"', demand='"+demand+"',loan ='"+loan+"', repay='"+money+"'"
					+ " where username='"+user+"' and password='"+password+"'";
		}
		else {
			sql3 = "update account set deposit='"+deposit+"', demand='"+demand+"',loan ='"+loan+"'"
				+ " where username='"+user+"' and password='"+password+"'";
		}
			
		int rs=pstmt.executeUpdate(sql3);
	}
}

