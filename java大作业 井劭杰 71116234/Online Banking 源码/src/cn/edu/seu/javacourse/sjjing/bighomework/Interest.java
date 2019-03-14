package cn.edu.seu.javacourse.sjjing.bighomework;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Interest extends Thread {

	private String user;
	private String password;
	
	public Interest(String user, String pass){
		this.user = user;
		this.password = pass;
	}
	
	public void run(){
		try {
			countInterest();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void countInterest() throws SQLException, InterruptedException{
		
		int deposit = 0;
		int fixed = 0;
		int demand = 0;
		int loan = 0;
		
		while(true){
			
			//一分钟为一年
			Thread.sleep(60000);
			
			String sql1 = "select deposit, fixed, demand,loan from account where username='"+user+"' and password='"+password+"'";
			Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/bank?" +
				"user=root&password=716897&useUnicode=true&characterEncoding=UTF8");
			Statement pstmt = connection.createStatement();
			ResultSet rs1 = pstmt.executeQuery(sql1);
			if(rs1.next()){
				deposit = rs1.getInt("deposit");
				fixed = rs1.getInt("fixed");
				demand = rs1.getInt("demand");
				loan = rs1.getInt("loan");
			}
		
			
			fixed = (int) (fixed*1.045);
			demand = (int) (demand*1.005);
			loan = (int) (loan*1.12);
			deposit = fixed + demand;
			
			String sql2 = "update account set deposit='"+deposit+"', fixed='"+fixed+"', demand = '"+demand+"', loan='"+loan+"' "
					+ "where username='"+user+"' and password='"+password+"'";
			
			int rs2 = pstmt.executeUpdate(sql2);
		}
	}
}
