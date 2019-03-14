import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.CellStyle;

import jxl.Cell;
import jxl.CellType;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;



class OrderList{
	public String steelType;
	public String norm;
	public int number;
	public double weight;
	public int cars;
	public String stoType;
	public int putWay;
	
	public OrderList(String aType, String aNorm, int aNumber, double aWeight){
		this.steelType = aType;
		this.norm = aNorm;
		this.number = aNumber;
		this.weight = aWeight;
		this.cars = 1;
		
		//判断该订单属于哪一类
		if(aType.equals("HRB400")&&aNorm.equals("20*12")||
				aType.equals("HRB400E抗震")&&aNorm.equals(28*12)||
				aType.equals("HRB400E抗震")&&aNorm.equals("22*12")||
				aType.equals("HRB400E抗震")&&aNorm.equals("20*12")||
				aType.equals("HRB400E抗震")&&aNorm.equals("14*12")||
				aType.equals("HRB400E抗震")&&aNorm.equals("12*12")||
				aType.equals("HTRB600")&&aNorm.equals("12*12")||
				aType.equals("HTRB600E抗震")&&aNorm.equals("18*12")||
				aType.equals("HTRB600E抗震")&&aNorm.equals("16*12")||
				aType.equals("HTRB600E抗震")&&aNorm.equals("22*12")||
				aType.equals("HRB400")&&aNorm.equals("14*12")||
				aType.equals("HRB400E抗震")&&aNorm.equals("32*12")||
				aType.equals("HTRB600E抗震")&&aNorm.equals("28*12")||
				aType.equals("HRB500")&&aNorm.equals("20*12")||
				aType.equals("HRB500E抗震")&&aNorm.equals("20*12")||
				aType.equals("HTRB600E抗震")&&aNorm.equals("14*12")||
				aType.equals("HRB500E抗震")&&aNorm.equals("22*12")||
				aType.equals("HRB400")&&aNorm.equals("22*12")||
				aType.equals("HRB500")&&aNorm.equals("16*12")||
				aType.equals("HRB500E抗震")&&aNorm.equals("32*12")||
				aType.equals("HRB500E抗震")&&aNorm.equals("18*12")||
				aType.equals("HRB400")&&aNorm.equals("12*12")||
				aType.equals("HTRB600E抗震")&&aNorm.equals("32*12")||
				aType.equals("HRB400E抗震")&&aNorm.equals("32*9")||
				aType.equals("HTRB600E抗震")&&aNorm.equals("20*9")||
				aType.equals("HRB500E抗震")&&aNorm.equals("22*9")||
				aType.equals("HTRB600E抗震")&&aNorm.equals("22*9")||
				aType.equals("HTRB600E抗震")&&aNorm.equals("25*9")||
				aType.equals("HRB500")&&aNorm.equals("12*9")||
				aType.equals("HRB500E抗震")&&aNorm.equals("20*9")||
				aType.equals("HRB400E抗震")&&aNorm.equals("22*7")){
			this.stoType = "BP";
		}
		else if(
				aType.equals("HRB400E抗震")&&aNorm.equals("25*14")||
				aType.equals("HRB400E抗震")&&aNorm.equals("25*7")||
				aType.equals("HRB400E抗震")&&aNorm.equals("18*7")||
				aType.equals("HRB400E抗震")&&aNorm.equals("16*7")||
				aType.equals("HTRB600")&&aNorm.equals("25*9")||
				aType.equals("HTRB600E抗震")&&aNorm.equals("20*12")||
				aType.equals("HRB400")&&aNorm.equals("20*7")||
				aType.equals("HRB400")&&aNorm.equals("22*7")||
				aType.equals("HRB400")&&aNorm.equals("32*12")||
				aType.equals("HRB500")&&aNorm.equals("25*12")||
				aType.equals("HRB500E抗震")&&aNorm.equals("25*9")||
				aType.equals("HRB500E抗震")&&aNorm.equals("25*12")||
				aType.equals("HRB500E抗震")&&aNorm.equals("28*12")||
				aType.equals("HRB500E抗震")&&aNorm.equals("14*9")){
			this.stoType = "CP";
		}
		else if(
				aType.equals("HRB400")&&aNorm.equals("28*9")||
				aType.equals("HRB400")&&aNorm.equals("16*12")||
				aType.equals("HRB400E抗震")&&aNorm.equals("25*12")||
				aType.equals("HRB400")&&aNorm.equals("25*12")||
				aType.equals("HRB400E抗震")&&aNorm.equals("16*12")||
				aType.equals("HRB400E抗震")&&aNorm.equals("18*12")||
				aType.equals("HRB400")&&aNorm.equals("18*12")||
				aType.equals("HRB400E抗震")&&aNorm.equals("28*9")||
				aType.equals("HRB400")&&aNorm.equals("32*9")){
			this.stoType = "B";
		}
		else if(
				aType.equals("HRB400")&&aNorm.equals("28*12")||
				aType.equals("HTRB600E抗震")&&aNorm.equals("25*12")){
			this.stoType = "C";
		}
		else{
			this.stoType = "A";
		}
		
		//判断订单的摆放方式
		if(aType.equals("HRB400")&&aNorm==("20*12")||
				aType.equals("HRB400E抗震")&&aNorm.equals("28*12")||
				aType.equals("HRB400E抗震")&&aNorm.equals("22*12")||
				aType.equals("HRB400E抗震")&&aNorm.equals("20*12")||
				aType.equals("HRB400E抗震")&&aNorm.equals("14*12")||
				aType.equals("HRB400E抗震")&&aNorm.equals("12*12")||
				aType.equals("HTRB600")&&aNorm.equals("12*12")||
				aType.equals("HTRB600E抗震")&&aNorm.equals("18*12")||
				aType.equals("HTRB600E抗震")&&aNorm.equals("16*12")||
				aType.equals("HTRB600E抗震")&&aNorm.equals("22*12")||
				aType.equals("HRB400")&&aNorm.equals("14*12")||
				aType.equals("HRB400E抗震")&&aNorm.equals("32*12")||
				aType.equals("HTRB600E抗震")&&aNorm.equals("28*12")||
				aType.equals("HRB400E抗震")&&aNorm.equals("32*9")||
				aType.equals("HRB500E抗震")&&aNorm.equals("25*9")||
				aType.equals("HRB500E抗震")&&aNorm.equals("25*12")||
				aType.equals("HRB500E抗震")&&aNorm.equals("28*12")||
				aType.equals("HTRB600E抗震")&&aNorm.equals("20*12")||
				aType.equals("HRB400")&&aNorm.equals("20*7")||
				aType.equals("HRB500")&&aNorm.equals("25*12")||
				aType.equals("HRB400")&&aNorm.equals("32*12")){
			this.putWay = 2;
		}
		else if(
				aType.equals("HRB500")&&aNorm.equals("20*12")||
				aType.equals("HRB500E抗震")&&aNorm.equals("20*12")||
				aType.equals("HTRB600E抗震")&&aNorm.equals("14*12")||
				aType.equals("HRB500E抗震")&&aNorm.equals("22*12")||
				aType.equals("HRB400")&&aNorm.equals("22*12")||
				aType.equals("HRB500")&&aNorm.equals("16*12")||
				aType.equals("HRB500E抗震")&&aNorm.equals("32*12")||
				aType.equals("HRB500E抗震")&&aNorm.equals("18*12")||
				aType.equals("HRB400")&&aNorm.equals("12*12")||
				aType.equals("HTRB600E抗震")&&aNorm.equals("32*12")||
				aType.equals("HTRB600E抗震")&&aNorm.equals("20*9")||
				aType.equals("HRB500E抗震")&&aNorm.equals("22*9")||
				aType.equals("HTRB600E抗震")&&aNorm.equals("22*9")||
				aType.equals("HTRB600E抗震")&&aNorm.equals("25*9")||
				aType.equals("HRB500")&&aNorm.equals("12*9")||
				aType.equals("HRB500E抗震")&&aNorm.equals("20*9")||
				aType.equals("HRB400E抗震")&&aNorm.equals("22*7")||
				aType.equals("HRB400E抗震")&&aNorm.equals("25*14")||
				aType.equals("HRB400E抗震")&&aNorm.equals("25*7")||
				aType.equals("HRB400E抗震")&&aNorm.equals("18*7")||
				aType.equals("HRB400E抗震")&&aNorm.equals("16*7")||
				aType.equals("HTRB600")&&aNorm.equals("25*9")||
				aType.equals("HRB400")&&aNorm.equals("22*7")||
				aType.equals("HRB500E抗震")&&aNorm.equals("14*9")){
			this.putWay = 1;
		}
		else{
			this.putWay = 0;
		}
	}
		
}

class Storage{
	public String stoType;
	public String stoNumber;
	public String steelType;
	public String norm;
	public int putWay;
	public int maxSto;
	public double presentSto;
	public int width;
	public boolean isPublic;
	public Road road;
	
	public Storage(String aStoType, String aStoNumber, String aSteelType, 
			String aNorm, int aPutWay, int aMaxSto, double aPresentSto, int aWidth, boolean aIsPublic){
		this.stoType = aStoType;
		this.stoNumber = aStoNumber;
		this.steelType = aSteelType;
		this.norm = aNorm;
		this.putWay = aPutWay;
		this.maxSto = aMaxSto;
		this.presentSto = aPresentSto;
		this.width = aWidth;
		this.isPublic = aIsPublic;
	}
	
	public boolean isFull(){
		return (this.presentSto==this.maxSto);
	}
}

class Road{
	public String direction;
	public double length;
	public boolean isUse;
	public double driveTime;//分钟
	public Storage storage;
	
	public Road(String aDirection, Storage aStorage){
		this.direction = aDirection;
		this.storage = aStorage;
		this.isUse = false;
		if(this.storage.width!=0){
			this.length = this.storage.width + 0.8;
		}
		else if(this.direction.equals("NS")) {
			this.length = 12.8;
		}
		else{
			this.length = 0;
		}
		this.driveTime = this.length/5000*60;
	}
	
	public void block(int time){
		this.isUse = true;
		
//		System.out.println("开始占用"+this.isUse);
//		Timer timer = new Timer();
//		timer.schedule(new TimerTask() {
//            public void run() {
//            	System.out.println("结束占用");
//            }
//        },time);
		
		Date a = new Date();
		boolean finish = false;
		
		do{
			Date b = new Date();
			long diff = b.getTime()-a.getTime();
			if(diff>=(time+40)){
				finish = true;
			}
			else{
				finish = false;
			}
		}while(!finish);
		this.isUse = false;
		System.out.println("结束占用");
	}
	
}

class Car implements Runnable{
	public Thread t;
	public double weight;
	public OrderList orderList;
	public Road road;
	public Storage [][] allStorage;
	public Road [][] allRoad;
	public int order;
	public double endTime;
	List<String> list1 = new ArrayList<String>();
	public String list;
	public boolean finish;
	
	public Car(double aWeight, OrderList aOrderList, int aOrder, double aEndTime, Storage [][] aAllStorage, Road [][] aAllRoad, boolean stop) {
		this.weight = aWeight;
		this.orderList = aOrderList;
		this.order = aOrder;
		this.endTime = aEndTime;
		this.allStorage = aAllStorage;
		this.allRoad = aAllRoad;
		this.finish = stop;
//		this.timeList = new String []{"","","","","","","","","","","","","","","","","",
//				"","","","","","","","","","","","","","","","","","","","","","","","",
//				"","","","","","","","","","","","","","","","","","","","","","","",""};
		this.list = "";
		System.out.println("creat a car");
	}
	
	public void run() {
		
		String path = "C:\\Users\\lenovo\\Desktop\\enter.txt";
		System.out.println("running a car");
		
		//xy保存车的目的地的位置
		int x=0;
		int y=0;
		boolean allFull = true;//专有仓位是否全满
		
//		System.out.println(this.order);
		
		try {
			Thread.sleep(this.order*1);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("入园时间:"+(int)(this.endTime/60)+":"+(int)(this.endTime-(int)(this.endTime/60)*60));
		
		//确定目的地
		do{
			loop1:for(int a = 0; a<6; a++) {
				loop2:for(int b = 0; b<12; b++) {
					if((this.allStorage[a][b].steelType.equals(this.orderList.steelType))&&(this.allStorage[a][b].norm.equals(this.orderList.norm))&&
							(this.allStorage[a][b].maxSto - this.allStorage[a][b].presentSto>=this.weight)) {
						this.road = this.allStorage[a][b].road;
						System.out.println("找到空仓位"+" "+a+" "+b);
						x=a;
						y=b;
						this.allStorage[a][b].presentSto = this.allStorage[a][b].presentSto + this.weight;
						allFull = false;
						break loop1;
					}
				}
			}
			loop3:for(int a = 0; a<6; a++) {
				loop4:for(int b = 22; b>11; b--) {
					if((this.allStorage[a][b].steelType.equals(this.orderList.steelType))&&(this.allStorage[a][b].norm.equals(this.orderList .norm))&&
							(this.allStorage[a][b].maxSto - this.allStorage[a][b].presentSto>=this.weight)) {
						this.road = this.allStorage[a][b].road;
						System.out.println("找到空仓位"+" "+a+" "+b);
						x=a;
						y=b;
						this.allStorage[a][b].presentSto = this.allStorage[a][b].presentSto + this.weight;
						allFull = false;
						break loop3;
					}
				}
			}
			//专有仓位已满，开始寻找公共仓位
			//A类找B类公共仓位
			if(allFull){
				if(this.orderList.stoType.equals("A")){
					for(int i = 0; i<10; i++){
						if((this.allStorage[0][i].steelType.equals(this.orderList.steelType))&&(this.allStorage[0][i].norm.equals(this.orderList.norm))&&
								(this.allStorage[0][i].maxSto - this.allStorage[0][i].presentSto>=this.weight)){
							this.road = this.allStorage[0][i].road;
							System.out.println("找到公共空仓位"+" "+0+" "+i);
							x=0;
							y=i;
							this.allStorage[0][i].presentSto = this.allStorage[0][i].presentSto + this.weight;
							allFull = false;
						}
					}
					for(int i = 0; i<10; i++){
						if(this.allStorage[0][i].steelType.equals("")){
							this.road = this.allStorage[0][i].road;
							System.out.println("找到公共空仓位"+" "+0+" "+i);
							x=0;
							y=i;
							if(this.orderList.putWay==1){
								this.allStorage[0][i].putWay=1;
								this.allStorage[0][i].maxSto=555400;
								this.allStorage[0][i].steelType=this.orderList.steelType;
								this.allStorage[0][i].norm=this.orderList.norm;
							}
							else{
								this.allStorage[0][i].putWay=2;
								this.allStorage[0][i].maxSto=5551500;
								this.allStorage[0][i].steelType=this.orderList.steelType;
								this.allStorage[0][i].norm=this.orderList.norm;
							}
							this.allStorage[0][i].presentSto = this.allStorage[0][i].presentSto + this.weight;
							allFull = false;
						}
					}
				}
			}
			//A类找C类公共仓位
			if(allFull){
				if(this.orderList.stoType.equals("A")){
					for(int i = 0; i<6; i++){
						if((this.allStorage[1][i].steelType.equals(this.orderList.steelType))&&(this.allStorage[1][i].norm.equals(this.orderList.norm))&&
								(this.allStorage[1][i].maxSto - this.allStorage[1][i].presentSto>=this.weight)){
							this.road = this.allStorage[1][i].road;
							System.out.println("找到公共空仓位"+" "+1+" "+i);
							x=1;
							y=i;
							this.allStorage[1][i].presentSto = this.allStorage[1][i].presentSto + this.weight;
							allFull = false;
						}
					}
					for(int i = 0; i<6; i++){
						if(this.allStorage[1][i].steelType.equals("")){
							this.road = this.allStorage[1][i].road;
							System.out.println("找到公共空仓位"+" "+1+" "+i);
							x=1;
							y=i;
							if(this.orderList.putWay==1){
								this.allStorage[1][i].putWay=1;
								this.allStorage[1][i].maxSto=555400;
								this.allStorage[1][i].steelType=this.orderList.steelType;
								this.allStorage[1][i].norm=this.orderList.norm;
							}
							else{
								this.allStorage[1][i].putWay=2;
								this.allStorage[1][i].maxSto=5551500;
								this.allStorage[1][i].steelType=this.orderList.steelType;
								this.allStorage[1][i].norm=this.orderList.norm;
							}
							this.allStorage[1][i].presentSto = this.allStorage[1][i].presentSto + this.weight;
							allFull = false;
						}
					}
				}
			}
			//B类找B类公共仓位
			if(allFull){
				if(this.orderList.stoType.equals("B")||this.orderList.stoType.equals("BP")){
					for(int i = 0; i<10; i++){
						if((this.allStorage[0][i].steelType.equals(this.orderList.steelType))&&(this.allStorage[0][i].norm.equals(this.orderList.norm))&&
								(this.allStorage[0][i].maxSto - this.allStorage[0][i].presentSto>=this.weight)){
							this.road = this.allStorage[0][i].road;
							System.out.println("找到公共空仓位"+" "+0+" "+i);
							x=0;
							y=i;
							this.allStorage[0][i].presentSto = this.allStorage[0][i].presentSto + this.weight;
							allFull = false;
						}
					}
					for(int i = 0; i<10; i++){
						if(this.allStorage[0][i].steelType.equals("")){
							this.road = this.allStorage[0][i].road;
							System.out.println("找到公共空仓位"+" "+0+" "+i);
							x=0;
							y=i;
							if(this.orderList.putWay==1){
								this.allStorage[0][i].putWay=1;
								this.allStorage[0][i].maxSto=555400;
								this.allStorage[0][i].steelType=this.orderList.steelType;
								this.allStorage[0][i].norm=this.orderList.norm;
							}
							else{
								this.allStorage[0][i].putWay=2;
								this.allStorage[0][i].maxSto=5551500;
								this.allStorage[0][i].steelType=this.orderList.steelType;
								this.allStorage[0][i].norm=this.orderList.norm;
							}
							this.allStorage[0][i].presentSto = this.allStorage[0][i].presentSto + this.weight;
							allFull = false;
						}
					}
				}
			}
			//B类找C类公共仓位
			if(allFull){
				if(this.orderList.stoType.equals("B")||this.orderList.stoType.equals("BP")){
					for(int i = 0; i<6; i++){
						if((this.allStorage[1][i].steelType.equals(this.orderList.steelType))&&(this.allStorage[1][i].norm.equals(this.orderList.norm))&&
								(this.allStorage[1][i].maxSto - this.allStorage[1][i].presentSto>=this.weight)){
							this.road = this.allStorage[1][i].road;
							System.out.println("找到公共空仓位"+" "+1+" "+i);
							x=1;
							y=i;
							this.allStorage[1][i].presentSto = this.allStorage[1][i].presentSto + this.weight;
							allFull = false;
						}
					}
					for(int i = 0; i<6; i++){
						if(this.allStorage[1][i].steelType.equals("")){
							this.road = this.allStorage[1][i].road;
							System.out.println("找到公共空仓位"+" "+1+" "+i);
							x=1;
							y=i;
							if(this.orderList.putWay==1){
								this.allStorage[1][i].putWay=1;
								this.allStorage[1][i].maxSto=555400;
								this.allStorage[1][i].steelType=this.orderList.steelType;
								this.allStorage[1][i].norm=this.orderList.norm;
							}
							else{
								this.allStorage[1][i].putWay=2;
								this.allStorage[1][i].maxSto=5551500;
								this.allStorage[1][i].steelType=this.orderList.steelType;
								this.allStorage[1][i].norm=this.orderList.norm;
							}
							this.allStorage[1][i].presentSto = this.allStorage[1][i].presentSto + this.weight;
							allFull = false;
						}
					}
				}
			}
			//C类找C类公共仓位
			if(allFull){
				if(this.orderList.stoType.equals("C")||this.orderList.stoType.equals("CP")){
					for(int i = 0; i<6; i++){
						if((this.allStorage[1][i].steelType.equals(this.orderList.steelType))&&(this.allStorage[1][i].norm.equals(this.orderList.norm))&&
								(this.allStorage[1][i].maxSto - this.allStorage[1][i].presentSto>=this.weight)){
							this.road = this.allStorage[1][i].road;
							System.out.println("找到公共空仓位"+" "+1+" "+i);
							x=1;
							y=i;
							this.allStorage[1][i].presentSto = this.allStorage[1][i].presentSto + this.weight;
							allFull = false;
						}
					}
					for(int i = 0; i<6; i++){
						if(this.allStorage[1][i].steelType.equals("")){
							this.road = this.allStorage[1][i].road;
							System.out.println("找到公共空仓位"+" "+1+" "+i);
							x=1;
							y=i;
							if(this.orderList.putWay==1){
								this.allStorage[1][i].putWay=1;
								this.allStorage[1][i].maxSto=555400;
								this.allStorage[1][i].steelType=this.orderList.steelType;
								this.allStorage[1][i].norm=this.orderList.norm;
							}
							else{
								this.allStorage[1][i].putWay=2;
								this.allStorage[1][i].maxSto=5551500;
								this.allStorage[1][i].steelType=this.orderList.steelType;
								this.allStorage[1][i].norm=this.orderList.norm;
							}
							this.allStorage[1][i].presentSto = this.allStorage[1][i].presentSto + this.weight;
							allFull = false;
						}
					}
				}
			}
			//C类找B类公共仓位
			if(allFull){
				if(this.orderList.stoType.equals("C")||this.orderList.stoType.equals("CP")){
					for(int i = 0; i<10; i++){
						if((this.allStorage[0][i].steelType.equals(this.orderList.steelType))&&(this.allStorage[0][i].norm.equals(this.orderList.norm))&&
								(this.allStorage[0][i].maxSto - this.allStorage[0][i].presentSto>=this.weight)){
							this.road = this.allStorage[0][i].road;
							System.out.println("找到公共空仓位"+" "+0+" "+i);
							x=0;
							y=i;
							this.allStorage[0][i].presentSto = this.allStorage[0][i].presentSto + this.weight;
							allFull = false;
						}
					}
					for(int i = 0; i<10; i++){
						if(this.allStorage[0][i].steelType.equals("")){
							this.road = this.allStorage[0][i].road;
							System.out.println("找到公共空仓位"+" "+0+" "+i);
							x=0;
							y=i;
							if(this.orderList.putWay==1){
								this.allStorage[0][i].putWay=1;
								this.allStorage[0][i].maxSto=555400;
								this.allStorage[0][i].steelType=this.orderList.steelType;
								this.allStorage[0][i].norm=this.orderList.norm;
							}
							else{
								this.allStorage[0][i].putWay=2;
								this.allStorage[0][i].maxSto=5551500;
								this.allStorage[0][i].steelType=this.orderList.steelType;
								this.allStorage[0][i].norm=this.orderList.norm;
							}
							this.allStorage[0][i].presentSto = this.allStorage[0][i].presentSto + this.weight;
							allFull = false;
						}
					}
				}
			}
			if(allFull){
				System.out.println("仓位全满，正在等待！");
			}
		}while(allFull);
		
		loop5:for(int a = 0; a<14; a++) {
			loop6:for(int b = 0; b<27; b++) {
				if(this.allRoad[a][b]==this.allStorage[x][y].road) {
					System.out.println("找到目的地"+" "+a+" "+b);
					x=a;
					y=b;
					break loop5;
				}
			}
		}
		
		//开始移动
		for(int i = 13; i > x-1; i--) {
			//走到目的地所在的行
			endTime = (endTime + this.allRoad[i][14].driveTime);
		}
		
		//左转或右转
		//之后每一步都要检测后一段路是否堵塞，如果堵塞且边上车道为同向车道则超车，否则等待到堵塞解除再通行，这段时间内用while循环检测道路堵塞是否解除，同时堵塞自身所在道路
		//如果后一步到达目的地且堵塞，只能等待，同时堵塞自身所在道路
		if(y<14){
			//左转
			//单车道，堵塞只能等待
			if(x==1||x==5){
				for(int i = 13; i>0; i--){
					if(this.allRoad[x+1][i-1].isUse){
						Date a = new Date();
						do{
							this.allRoad[x+1][i].isUse = true;
						}while(this.allRoad[x+1][i-1].isUse);
						Date b = new Date();
						long diff = b.getTime()-a.getTime();
						this.allRoad[x+1][i].isUse = false;
						
						BufferedWriter writer;
						try {
							writer = new BufferedWriter(new OutputStreamWriter(
									new FileOutputStream(path, true)));
							writer.write("\r\n "+this.allRoad[x+1][i].storage.stoNumber);
					        writer.write("\n "+String.valueOf((int)(endTime%(13*60)/60)+8)+":"+String.valueOf((int)(endTime-(int)(endTime/60)*60)));
					        endTime = (endTime + this.allRoad[x+1][i-1].driveTime + diff);
					        writer.write("\n "+String.valueOf((int)(endTime%(13*60)/60)+8)+":"+String.valueOf((int)(endTime-(int)(endTime/60)*60)));
					        writer.close();
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						
						
						
					}
					else{
						endTime = (endTime + this.allRoad[x+1][i-1].driveTime);
					}
				}
				for(int i = 0; i<y-1; i++){
					if(this.allRoad[x][i+1].isUse){
						Date a = new Date();
						do{
							this.allRoad[x][i].isUse = true;
						}while(this.allRoad[x][i+1].isUse);
						Date b = new Date();
						long diff = b.getTime()-a.getTime();
						this.allRoad[x][i].isUse = false;
						BufferedWriter writer;
						try {
							writer = new BufferedWriter(new OutputStreamWriter(
									new FileOutputStream(path, true)));
							writer.write("\n "+this.allRoad[x][i].storage.stoNumber);
					        writer.write("\n "+String.valueOf((int)(endTime%(13*60)/60)+8)+":"+String.valueOf((int)(endTime-(int)(endTime/60)*60)));
					        endTime =  (endTime + this.allRoad[x][i+1].driveTime + diff);
					        writer.write("\n "+String.valueOf((int)(endTime%(13*60)/60)+8)+":"+String.valueOf((int)(endTime-(int)(endTime/60)*60)));
					        writer.close();
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						
						
					}
					else{
						endTime = (endTime + this.allRoad[x][i+1].driveTime);
					}
				}
				if(this.allRoad[x][y].isUse) {
					
					Date a = new Date();
					System.out.println("waiting"+(int)(endTime/60)+":"+(int)(endTime-(int)(endTime/60)*60));
					do {
						try {
							Thread.sleep((long)(0.1+Math.random()*(20-0.1+1)));
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}while(this.allRoad[x][y].isUse);
					Date b = new Date();
					long diff = b.getTime()-a.getTime();
					BufferedWriter writer;
					try {
						writer = new BufferedWriter(new OutputStreamWriter(
								new FileOutputStream(path, true)));
						writer.write("\n "+this.allRoad[x][y].storage.stoNumber);
				        writer.write("\n "+String.valueOf((int)(endTime%(13*60)/60)+8)+":"+String.valueOf((int)(endTime-(int)(endTime/60)*60)));
				        endTime = (endTime + this.allRoad[x][y].driveTime + diff);
				        writer.write("\n "+String.valueOf((int)(endTime%(13*60)/60)+8)+":"+String.valueOf((int)(endTime-(int)(endTime/60)*60)));
				        writer.close();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

					
					
					System.out.println("finish waiting"+(int)(endTime/60)+":"+(int)(endTime-(int)(endTime/60)*60));
				}
				else{
					endTime = (endTime + this.allRoad[x][y].driveTime);
				}
				//到达目标仓位，开始卸货，堵塞道路
				BufferedWriter writer;
				try {
					writer = new BufferedWriter(new OutputStreamWriter(
							new FileOutputStream(path, true)));
					writer.write("\n "+this.allRoad[x][y].storage.stoNumber);
			        writer.write("\n "+String.valueOf((int)(endTime%(13*60)/60)+8)+":"+String.valueOf((int)(endTime-(int)(endTime/60)*60)));
			        endTime = (endTime - this.weight/50*15);
			        writer.write("\n "+String.valueOf((int)(endTime%(13*60)/60)+8)+":"+String.valueOf((int)(endTime-(int)(endTime/60)*60)));
			        writer.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				String.join("-", list,String.valueOf((int)(endTime/60))+":"+String.valueOf((int)(endTime-(int)(endTime/60)*60)));
				this.allRoad[x][y].block((int) ( this.weight/50*15));
				//卸货完成，解除堵塞,开始出园
				for(int i = y; i<13; i++){
					if(this.allRoad[x][i+1].isUse){
						Date a = new Date();
						do{
							this.allRoad[x][i].isUse = true;
						}while(this.allRoad[x][i+1].isUse);
						Date b = new Date();
						long diff = b.getTime()-a.getTime();
						this.allRoad[x][i].isUse = false;
						BufferedWriter writer1;
						try {
							writer1 = new BufferedWriter(new OutputStreamWriter(
									new FileOutputStream(path, true)));
							writer1.write("\n "+this.allRoad[x][i].storage.stoNumber);
					        writer1.write("\n "+String.valueOf((int)(endTime%(13*60)/60)+8)+":"+String.valueOf((int)(endTime-(int)(endTime/60)*60)));
					        endTime =  (endTime + this.allRoad[x][i+1].driveTime + diff);
					        writer1.write("\n "+String.valueOf((int)(endTime%(13*60)/60)+8)+":"+String.valueOf((int)(endTime-(int)(endTime/60)*60)));
					        writer1.close();
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}

						
						String.join("-", list,String.valueOf((int)(endTime/60))+":"+String.valueOf((int)(endTime-(int)(endTime/60)*60)));
					}
					else{
						endTime =  (endTime + this.allRoad[x][i+1].driveTime);
					}
				}
				
				for(int i = x; i<14; i++){
					endTime =  (endTime + this.allRoad[i][14].driveTime);
				}
				//离开园区
				BufferedWriter writer1;
				
				if(finish){
					try {
						writer1 = new BufferedWriter(new OutputStreamWriter(
								new FileOutputStream(path, true)));
						writer1.write("\n "+"OUT");
				        writer1.write("\n "+String.valueOf((int)(endTime%(13*60)/60)+8)+":"+String.valueOf((int)(endTime-(int)(endTime/60)*60)));
				        writer1.write("\r\n");
				        writer1.close();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					System.out.println("alread leave at "+(int)(endTime/60)+":"+(int)(endTime-(int)(endTime/60)*60));
				}
				
				
				
			}
			
			if(x==2||x==6){
				for(int i = 13; i>y+1; i--){
					if(this.allRoad[x][i-1].isUse){
						Date a = new Date();
						do{
							this.allRoad[x][i].isUse = true;
						}while(this.allRoad[x][i-1].isUse);
						Date b = new Date();
						long diff = b.getTime()-a.getTime();
						this.allRoad[x][i].isUse = false;
						BufferedWriter writer1;
						try {
							writer1 = new BufferedWriter(new OutputStreamWriter(
									new FileOutputStream(path, true)));
							writer1.write("\r\n "+this.allRoad[x][i].storage.stoNumber);
					        writer1.write("\n "+String.valueOf((int)(endTime%(13*60)/60)+8)+":"+String.valueOf((int)(endTime-(int)(endTime/60)*60)));
					        endTime =  (endTime + this.allRoad[x][i-1].driveTime + diff);
					        writer1.write("\n "+String.valueOf((int)(endTime%(13*60)/60)+8)+":"+String.valueOf((int)(endTime-(int)(endTime/60)*60)));
					        writer1.close();
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						
						String.join("-", list,String.valueOf((int)(endTime/60))+":"+String.valueOf((int)(endTime-(int)(endTime/60)*60)));
					}
					else{
						endTime = (endTime + this.allRoad[x][i-1].driveTime);
					}
				}
				if(this.allRoad[x][y].isUse) {
					
					Date a = new Date();
					System.out.println("waiting"+(int)(endTime/60)+":"+(int)(endTime-(int)(endTime/60)*60));
					do {
						try {
							Thread.sleep((long)(0.1+Math.random()*(20-0.1+1)));
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}while(this.allRoad[x][y].isUse);
					Date b = new Date();
					long diff = b.getTime()-a.getTime();
					BufferedWriter writer1;
					try {
						writer1 = new BufferedWriter(new OutputStreamWriter(
								new FileOutputStream(path, true)));
						writer1.write("\n "+this.allRoad[x][y].storage.stoNumber);
				        writer1.write("\n "+String.valueOf((int)(endTime%(13*60)/60)+8)+":"+String.valueOf((int)(endTime-(int)(endTime/60)*60)));
				        endTime = (endTime + this.allRoad[x][y].driveTime + diff);
				        writer1.write("\n "+String.valueOf((int)(endTime%(13*60)/60)+8)+":"+String.valueOf((int)(endTime-(int)(endTime/60)*60)));
				        writer1.close();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
					
					String.join("-", list,String.valueOf((int)(endTime/60))+":"+String.valueOf((int)(endTime-(int)(endTime/60)*60)));
					System.out.println("finish waiting"+(int)(endTime/60)+":"+(int)(endTime-(int)(endTime/60)*60));
				}
				else{
					endTime = (endTime + this.allRoad[x][y].driveTime);
				}
				//到达目标仓位，开始卸货，堵塞道路
				BufferedWriter writer1;
				try {
					writer1 = new BufferedWriter(new OutputStreamWriter(
							new FileOutputStream(path, true)));
					writer1.write("\n "+this.allRoad[x][y].storage.stoNumber);
			        writer1.write("\n "+String.valueOf((int)(endTime%(13*60)/60)+8)+":"+String.valueOf((int)(endTime-(int)(endTime/60)*60)));
			        endTime =  (endTime - this.weight/50*15);
			        writer1.write("\n "+String.valueOf((int)(endTime%(13*60)/60)+8)+":"+String.valueOf((int)(endTime-(int)(endTime/60)*60)));
			        writer1.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				
				String.join("-", list,String.valueOf((int)(endTime/60))+":"+String.valueOf((int)(endTime-(int)(endTime/60)*60)));
				this.allRoad[x][y].block((int) (this.weight/50*15));
				//卸货完成，解除堵塞,开始出园
				for(int i = y; i>0; i--){
					if(this.allRoad[x][i-1].isUse){
						Date a = new Date();
						do{
							this.allRoad[x][i].isUse = true;
						}while(this.allRoad[x][i-1].isUse);
						Date b = new Date();
						long diff = b.getTime()-a.getTime();
						this.allRoad[x][i].isUse = false;
						BufferedWriter writer11;
						try {
							writer11 = new BufferedWriter(new OutputStreamWriter(
									new FileOutputStream(path, true)));
							writer11.write("\n "+this.allRoad[x][y].storage.stoNumber);
					        writer11.write("\n "+String.valueOf((int)(endTime%(13*60)/60)+8)+":"+String.valueOf((int)(endTime-(int)(endTime/60)*60)));
					        endTime =  (endTime + this.allRoad[x][i-1].driveTime + diff);
					        writer11.write("\n "+String.valueOf((int)(endTime%(13*60)/60)+8)+":"+String.valueOf((int)(endTime-(int)(endTime/60)*60)));
					        writer11.close();
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						
						
						String.join("-", list,String.valueOf((int)(endTime/60))+":"+String.valueOf((int)(endTime-(int)(endTime/60)*60)));
					}
					else{
						endTime = (endTime + this.allRoad[x][i-1].driveTime);
					}
				}
				for(int i = 0; i<13; i++){
					if(this.allRoad[x-1][i+1].isUse){
						Date a = new Date();
						do{
							this.allRoad[x-1][i].isUse = true;
						}while(this.allRoad[x-1][i+1].isUse);
						Date b = new Date();
						long diff = b.getTime()-a.getTime();
						this.allRoad[x-1][i].isUse = false;
						BufferedWriter writer11;
						try {
							writer11 = new BufferedWriter(new OutputStreamWriter(
									new FileOutputStream(path, true)));
							writer11.write("\n "+this.allRoad[x][y].storage.stoNumber);
					        writer11.write("\n "+String.valueOf((int)(endTime%(13*60)/60)+8)+":"+String.valueOf((int)(endTime-(int)(endTime/60)*60)));
					        endTime =  (endTime + this.allRoad[x-1][i+1].driveTime + diff);
					        writer11.write("\n "+String.valueOf((int)(endTime%(13*60)/60)+8)+":"+String.valueOf((int)(endTime-(int)(endTime/60)*60)));
					        writer11.close();
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						
						
						String.join("-", list,String.valueOf((int)(endTime/60))+":"+String.valueOf((int)(endTime-(int)(endTime/60)*60)));
					}
					else{
						endTime =  (endTime + this.allRoad[x-1][i+1].driveTime);
					}
				}
				for(int i = x-1; i<14; i++){
					endTime =  (endTime + this.allRoad[i][14].driveTime);
				}
				//离开园区
				BufferedWriter writer;
				
				if(finish){
					try {
						writer = new BufferedWriter(new OutputStreamWriter(
								new FileOutputStream(path, true)));
						writer.write("\n "+"OUT");
				        writer.write("\n "+String.valueOf((int)(endTime%(13*60)/60)+8)+":"+String.valueOf((int)(endTime-(int)(endTime/60)*60)));
				        writer.write("\r\n");
				        writer.close();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					System.out.println("alread leave at "+(int)(endTime/60)+":"+(int)(endTime-(int)(endTime/60)*60));
				}
				
				
				
				
			}
			//双车道，可以超车
			if(x==9){
				for(int i = 13; i>y+1; i--){
					if(this.allRoad[x][i-1].isUse&&this.allRoad[x+1][i-1].isUse){
						//双车道都被堵塞
						Date a = new Date();
						do{
							this.allRoad[x][i].isUse = true;
						}while(this.allRoad[x][i-1].isUse&&this.allRoad[x+1][i-1].isUse);
						Date b = new Date();
						long diff = b.getTime()-a.getTime();
						this.allRoad[x][i].isUse = false;
						BufferedWriter writer11;
						try {
							writer11 = new BufferedWriter(new OutputStreamWriter(
									new FileOutputStream(path, true)));
							writer11.write("\r\n "+this.allRoad[x][i].storage.stoNumber);
					        writer11.write("\n "+String.valueOf((int)(endTime%(13*60)/60)+8)+":"+String.valueOf((int)(endTime-(int)(endTime/60)*60)));
					        endTime =  (endTime + this.allRoad[x][i-1].driveTime + diff);
					        writer11.write("\n "+String.valueOf((int)(endTime%(13*60)/60)+8)+":"+String.valueOf((int)(endTime-(int)(endTime/60)*60)));
					        writer11.close();
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						
						
						String.join("-", list,String.valueOf((int)(endTime/60))+":"+String.valueOf((int)(endTime-(int)(endTime/60)*60)));
					}
					else{
						endTime =  (endTime + this.allRoad[x][i-1].driveTime);
					}
				}
					
				if(this.allRoad[x][y].isUse) {
					
						Date a = new Date();
						System.out.println("waiting"+(int)(endTime/60)+":"+(int)(endTime-(int)(endTime/60)*60));
						do {
							try {
								Thread.sleep((long)(0.1+Math.random()*(40-0.1+1)));
							} catch (InterruptedException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						}while(this.allRoad[x][y].isUse);
						Date b = new Date();
						long diff = b.getTime()-a.getTime();
						BufferedWriter writer11;
						try {
							writer11 = new BufferedWriter(new OutputStreamWriter(
									new FileOutputStream(path, true)));
							writer11.write("\n "+this.allRoad[x][y].storage.stoNumber);
					        writer11.write("\n "+String.valueOf((int)(endTime%(13*60)/60)+8)+":"+String.valueOf((int)(endTime-(int)(endTime/60)*60)));
					        endTime = (endTime + this.allRoad[x][y].driveTime + diff);
					        writer11.write("\n "+String.valueOf((int)(endTime%(13*60)/60)+8)+":"+String.valueOf((int)(endTime-(int)(endTime/60)*60)));
					        writer11.close();
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						
						
						String.join("-", list,String.valueOf((int)(endTime/60))+":"+String.valueOf((int)(endTime-(int)(endTime/60)*60)));
						System.out.println("finish waiting"+(int)(endTime/60)+":"+(int)(endTime-(int)(endTime/60)*60));
				}
					else{
						endTime = (endTime + this.allRoad[x][y].driveTime);
					}
				
				//到达目标仓位，开始卸货，堵塞道路
				BufferedWriter writer11;
				try {
					writer11 = new BufferedWriter(new OutputStreamWriter(
							new FileOutputStream(path, true)));
					writer11.write("\n "+this.allRoad[x][y].storage.stoNumber);
			        writer11.write("\n "+String.valueOf((int)(endTime%(13*60)/60)+8)+":"+String.valueOf((int)(endTime-(int)(endTime/60)*60)));
			        endTime =  (endTime - this.weight/50*15);
			        writer11.write("\n "+String.valueOf((int)(endTime%(13*60)/60)+8)+":"+String.valueOf((int)(endTime-(int)(endTime/60)*60)));
			        writer11.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				
				String.join("-", list,String.valueOf((int)(endTime/60))+":"+String.valueOf((int)(endTime-(int)(endTime/60)*60)));
				this.allRoad[x][y].block((int) (this.weight/50*15));
				//卸货完成，解除堵塞,开始出园
				for(int i = y; i>0; i--){
					if(this.allRoad[x][i-1].isUse&&this.allRoad[x+1][i-1].isUse){
						//双车道都被堵塞
						Date a = new Date();
						do{
							this.allRoad[x][i].isUse = true;
						}while(this.allRoad[x][i-1].isUse&&this.allRoad[x+1][i-1].isUse);
						Date b = new Date();
						long diff = b.getTime()-a.getTime();
						this.allRoad[x][i].isUse = false;
						BufferedWriter writer111;
						try {
							writer111 = new BufferedWriter(new OutputStreamWriter(
									new FileOutputStream(path, true)));
							writer111.write("\n "+this.allRoad[x][i].storage.stoNumber);
					        writer111.write("\n "+String.valueOf((int)(endTime%(13*60)/60)+8)+":"+String.valueOf((int)(endTime-(int)(endTime/60)*60)));
					        endTime =  (endTime + this.allRoad[x][i-1].driveTime + diff);
					        writer111.write("\n "+String.valueOf((int)(endTime%(13*60)/60)+8)+":"+String.valueOf((int)(endTime-(int)(endTime/60)*60)));
					        writer111.close();
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						
						
						String.join("-", list,String.valueOf((int)(endTime/60))+":"+String.valueOf((int)(endTime-(int)(endTime/60)*60)));
					}
					else{
						endTime =  (endTime + this.allRoad[x][i-1].driveTime);
					}
				}
				for(int i = 0; i<13; i++){
					if(this.allRoad[1][i+1].isUse){
						Date a = new Date();
						do{
							this.allRoad[1][i].isUse = true;
						}while(this.allRoad[1][i+1].isUse);
						Date b = new Date();
						long diff = b.getTime()-a.getTime();
						this.allRoad[1][i].isUse = false;
						BufferedWriter writer111;
						try {
							writer111 = new BufferedWriter(new OutputStreamWriter(
									new FileOutputStream(path, true)));
							writer111.write("\n "+this.allRoad[1][i].storage.stoNumber);
					        writer111.write("\n "+String.valueOf((int)(endTime%(13*60)/60)+8)+":"+String.valueOf((int)(endTime-(int)(endTime/60)*60)));
					        endTime = (endTime + this.allRoad[1][i+1].driveTime + diff);
					        writer111.write("\n "+String.valueOf((int)(endTime%(13*60)/60)+8)+":"+String.valueOf((int)(endTime-(int)(endTime/60)*60)));
					        writer111.close();
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						
						
						String.join("-", list,String.valueOf((int)(endTime/60))+":"+String.valueOf((int)(endTime-(int)(endTime/60)*60)));
					}
					else{
						endTime =  (endTime + this.allRoad[1][i+1].driveTime);
					}
				}
				for(int i = 0; i<20; i++){
					endTime =  (endTime + this.allRoad[1][14].driveTime);
				}
				//离开园区
				BufferedWriter writer;
				
				if(finish){
					try {
						writer = new BufferedWriter(new OutputStreamWriter(
								new FileOutputStream(path, true)));
						writer.write("\n "+"OUT");
				        writer.write("\n "+String.valueOf((int)(endTime%(13*60)/60)+8)+":"+String.valueOf((int)(endTime-(int)(endTime/60)*60)));
				        writer.write("\r\n");
				        writer.close();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					System.out.println("alread leave at "+(int)(endTime/60)+":"+(int)(endTime-(int)(endTime/60)*60));
				}
				
				
				
			}
			if(x==10){
				for(int i = 13; i>y+1; i--){
					if(this.allRoad[x][i-1].isUse&&this.allRoad[x-1][i-1].isUse){
						//双车道都被堵塞
						Date a = new Date();
						do{
							this.allRoad[x][i].isUse = true;
						}while(this.allRoad[x][i-1].isUse&&this.allRoad[x-1][i-1].isUse);
						Date b = new Date();
						long diff = b.getTime()-a.getTime();
						this.allRoad[x][i].isUse = false;
						BufferedWriter writer111;
						try {
							writer111 = new BufferedWriter(new OutputStreamWriter(
									new FileOutputStream(path, true)));
							writer111.write("\r\n "+this.allRoad[x][i].storage.stoNumber);
					        writer111.write("\n "+String.valueOf((int)(endTime%(13*60)/60)+8)+":"+String.valueOf((int)(endTime-(int)(endTime/60)*60)));
					        endTime = (endTime + this.allRoad[x][i-1].driveTime + diff);
					        writer111.write("\n "+String.valueOf((int)(endTime%(13*60)/60)+8)+":"+String.valueOf((int)(endTime-(int)(endTime/60)*60)));
					        writer111.close();
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						
						
						String.join("-", list,String.valueOf((int)(endTime/60))+":"+String.valueOf((int)(endTime-(int)(endTime/60)*60)));
					}
					else{
						endTime = (endTime + this.allRoad[x][i-1].driveTime);
					}
				}
				
				
				if(this.allRoad[x][y].isUse) {
					
					Date a = new Date();
					System.out.println("waiting"+(int)(endTime/60)+":"+(int)(endTime-(int)(endTime/60)*60));
					do {
						try {
							Thread.sleep((long)(0.1+Math.random()*(40-0.1+1)));
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}while(this.allRoad[x][y].isUse);
					Date b = new Date();
					long diff = b.getTime()-a.getTime();
					BufferedWriter writer111;
					try {
						writer111 = new BufferedWriter(new OutputStreamWriter(
								new FileOutputStream(path, true)));
						writer111.write("\n "+this.allRoad[x][y].storage.stoNumber);
				        writer111.write("\n "+String.valueOf((int)(endTime%(13*60)/60)+8)+":"+String.valueOf((int)(endTime-(int)(endTime/60)*60)));
				        endTime = (endTime + this.allRoad[x][y].driveTime + diff);
				        writer111.write("\n "+String.valueOf((int)(endTime%(13*60)/60)+8)+":"+String.valueOf((int)(endTime-(int)(endTime/60)*60)));
				        writer111.close();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
					
					String.join("-", list,String.valueOf((int)(endTime/60))+":"+String.valueOf((int)(endTime-(int)(endTime/60)*60)));
					System.out.println("finish waiting"+(int)(endTime/60)+":"+(int)(endTime-(int)(endTime/60)*60));
				}
				else{
					endTime = (endTime + this.allRoad[x][y].driveTime);
				}
				//到达目标仓位，开始卸货，堵塞道路
				BufferedWriter writer111;
				try {
					writer111 = new BufferedWriter(new OutputStreamWriter(
							new FileOutputStream(path, true)));
					writer111.write("\n "+this.allRoad[x][y].storage.stoNumber);
			        writer111.write("\n "+String.valueOf((int)(endTime%(13*60)/60)+8)+":"+String.valueOf((int)(endTime-(int)(endTime/60)*60)));
			        System.out.println("开始卸货 at "+(int)(endTime/60)+":"+(int)(endTime-(int)(endTime/60)*60));
			        endTime =  (endTime - this.weight/50*15);
			        writer111.write("\n "+String.valueOf((int)(endTime%(13*60)/60)+8)+":"+String.valueOf((int)(endTime-(int)(endTime/60)*60)));
			        writer111.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				
				
				String.join("-", list,String.valueOf((int)(endTime/60))+":"+String.valueOf((int)(endTime-(int)(endTime/60)*60)));
				this.allRoad[x][y].block((int) (this.weight/50*15));
				
				
				//卸货完成，解除堵塞,开始出园
				for(int i = y; i>0; i--){
					if(this.allRoad[x][i-1].isUse&&this.allRoad[x-1][i-1].isUse){
						//双车道都被堵塞
						Date a = new Date();
						do{
							this.allRoad[x][i].isUse = true;
						}while(this.allRoad[x][i-1].isUse&&this.allRoad[x-1][i-1].isUse);
						Date b = new Date();
						long diff = b.getTime()-a.getTime();
						this.allRoad[x][i].isUse = false;
						BufferedWriter writer1111;
						try {
							writer1111 = new BufferedWriter(new OutputStreamWriter(
									new FileOutputStream(path, true)));
							writer1111.write("\n "+this.allRoad[x][i].storage.stoNumber);
					        writer1111.write("\n "+String.valueOf((int)(endTime%(13*60)/60)+8)+":"+String.valueOf((int)(endTime-(int)(endTime/60)*60)));
					        endTime =  (endTime + this.allRoad[x][i-1].driveTime + diff);
					        writer1111.write("\n "+String.valueOf((int)(endTime%(13*60)/60)+8)+":"+String.valueOf((int)(endTime-(int)(endTime/60)*60)));
					        writer1111.close();
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						
						
						String.join("-", list,String.valueOf((int)(endTime/60))+":"+String.valueOf((int)(endTime-(int)(endTime/60)*60)));
					}
					else{
						endTime =  (endTime + this.allRoad[x][i-1].driveTime);
					}
				}
				for(int i = 0; i<13; i++){
					if(this.allRoad[5][i+1].isUse){
						Date a = new Date();
						do{
							this.allRoad[5][i].isUse = true;
						}while(this.allRoad[5][i+1].isUse);
						Date b = new Date();
						long diff = b.getTime()-a.getTime();
						this.allRoad[5][i].isUse = false;
						BufferedWriter writer1111;
						try {
							writer1111 = new BufferedWriter(new OutputStreamWriter(
									new FileOutputStream(path, true)));
							writer1111.write("\n "+this.allRoad[5][i].storage.stoNumber);
					        writer1111.write("\n "+String.valueOf((int)(endTime%(13*60)/60)+8)+":"+String.valueOf((int)(endTime-(int)(endTime/60)*60)));
					        endTime = (endTime + this.allRoad[5][i+1].driveTime + diff);
					        writer1111.write("\n "+String.valueOf((int)(endTime%(13*60)/60)+8)+":"+String.valueOf((int)(endTime-(int)(endTime/60)*60)));
					        writer1111.close();
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						
						
						String.join("-", list,String.valueOf((int)(endTime/60))+":"+String.valueOf((int)(endTime-(int)(endTime/60)*60)));
					}
					else{
						endTime =  (endTime + this.allRoad[5][i+1].driveTime);
					}
				}
				for(int i = 0; i<14; i++){
					endTime =  (endTime + this.allRoad[1][14].driveTime);
				}
				//离开园区
				BufferedWriter writer;
				
				if(finish){
					try {
						writer = new BufferedWriter(new OutputStreamWriter(
								new FileOutputStream(path, true)));
						this.list = this.list + " OUT" + String.valueOf((int)(endTime/60))+":"+String.valueOf((int)(endTime-(int)(endTime/60)*60));
						writer.write("\n "+"OUT");
				        writer.write("\n "+String.valueOf((int)(endTime%(13*60)/60)+8)+":"+String.valueOf((int)(endTime-(int)(endTime/60)*60)));
				        writer.write("\r\n");
				        writer.close();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					System.out.println("alread leave at "+(int)(endTime/60)+":"+(int)(endTime-(int)(endTime/60)*60));
					System.out.println(this.list);
				}
				
				
				
			}
		}
		else{
			//右转
			//单车道，堵塞只能等待
			if(x==6){
				for(int i = 15; i<26; i++){
					if(this.allRoad[x-1][i+1].isUse){
						Date a = new Date();
						do{
							this.allRoad[x-1][i].isUse = true;
						}while(this.allRoad[x-1][i+1].isUse);
						Date b = new Date();
						long diff = b.getTime()-a.getTime();
						this.allRoad[x-1][i].isUse = false;
						BufferedWriter writer1111;
						try {
							writer1111 = new BufferedWriter(new OutputStreamWriter(
									new FileOutputStream(path, true)));
							writer1111.write("\r\n "+this.allRoad[x-1][i].storage.stoNumber);
					        writer1111.write("\n "+String.valueOf((int)(endTime%(13*60)/60)+8)+":"+String.valueOf((int)(endTime-(int)(endTime/60)*60)));
					        endTime =  (endTime + this.allRoad[x-1][i+1].driveTime + diff);
					        writer1111.write("\n "+String.valueOf((int)(endTime%(13*60)/60)+8)+":"+String.valueOf((int)(endTime-(int)(endTime/60)*60)));
					        writer1111.close();
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						
						
						String.join("-", list,String.valueOf((int)(endTime/60))+":"+String.valueOf((int)(endTime-(int)(endTime/60)*60)));
					}
					else{
						endTime =  (endTime + this.allRoad[x-1][i+1].driveTime);
					}
				}
				for(int i = 25; i>y+1; i--){
					if(this.allRoad[x][i-1].isUse){
						Date a = new Date();
						do{
							this.allRoad[x][i].isUse = true;
						}while(this.allRoad[x][i-1].isUse);
						Date b = new Date();
						long diff = b.getTime()-a.getTime();
						this.allRoad[x][i].isUse = false;
						BufferedWriter writer1111;
						try {
							writer1111 = new BufferedWriter(new OutputStreamWriter(
									new FileOutputStream(path, true)));
							writer1111.write("\n "+this.allRoad[x][i].storage.stoNumber);
					        writer1111.write("\n "+String.valueOf((int)(endTime%(13*60)/60)+8)+":"+String.valueOf((int)(endTime-(int)(endTime/60)*60)));
					        endTime =  (endTime + this.allRoad[x][i-1].driveTime + diff);
					        writer1111.write("\n "+String.valueOf((int)(endTime%(13*60)/60)+8)+":"+String.valueOf((int)(endTime-(int)(endTime/60)*60)));
					        writer1111.close();
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						
						
						String.join("-", list,String.valueOf((int)(endTime/60))+":"+String.valueOf((int)(endTime-(int)(endTime/60)*60)));
					}
					else{
						endTime = (endTime + this.allRoad[x][i-1].driveTime);
					}
				}
				if(this.allRoad[x][y].isUse) {
					
					Date a = new Date();
					System.out.println("waiting"+(int)(endTime/60)+":"+(int)(endTime-(int)(endTime/60)*60));
					do {
						try {
							Thread.sleep((long)(0.1+Math.random()*(20-0.1+1)));
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}while(this.allRoad[x][y].isUse);
					Date b = new Date();
					long diff = b.getTime()-a.getTime();
					BufferedWriter writer1111;
					try {
						writer1111 = new BufferedWriter(new OutputStreamWriter(
								new FileOutputStream(path, true)));
						writer1111.write("\n "+this.allRoad[x][y].storage.stoNumber);
				        writer1111.write("\n "+String.valueOf((int)(endTime%(13*60)/60)+8)+":"+String.valueOf((int)(endTime-(int)(endTime/60)*60)));
				        endTime = (endTime + this.allRoad[x][y].driveTime + diff);
				        writer1111.write("\n "+String.valueOf((int)(endTime%(13*60)/60)+8)+":"+String.valueOf((int)(endTime-(int)(endTime/60)*60)));
				        writer1111.close();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
					
					String.join("-", list,String.valueOf((int)(endTime/60))+":"+String.valueOf((int)(endTime-(int)(endTime/60)*60)));
					System.out.println("finish waiting"+(int)(endTime/60)+":"+(int)(endTime-(int)(endTime/60)*60));
				}
				else{
					endTime = (endTime + this.allRoad[x][y].driveTime);
				}
				//到达目标仓位，开始卸货，堵塞道路
				BufferedWriter writer1111;
				try {
					writer1111 = new BufferedWriter(new OutputStreamWriter(
							new FileOutputStream(path, true)));
					writer1111.write("\n "+this.allRoad[x][y].storage.stoNumber);
			        writer1111.write("\n "+String.valueOf((int)(endTime%(13*60)/60)+8)+":"+String.valueOf((int)(endTime-(int)(endTime/60)*60)));
			        endTime = (endTime - this.weight/50*15);
			        writer1111.write("\n "+String.valueOf((int)(endTime%(13*60)/60)+8)+":"+String.valueOf((int)(endTime-(int)(endTime/60)*60)));
			        writer1111.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				
				String.join("-", list,String.valueOf((int)(endTime/60))+":"+String.valueOf((int)(endTime-(int)(endTime/60)*60)));
				this.allRoad[x][y].block((int) (this.weight/50*15));
				//卸货完成，解除堵塞,开始出园
				for(int i = y; i>15; i--){
					if(this.allRoad[x][i-1].isUse){
						Date a = new Date();
						do{
							this.allRoad[x][i].isUse = true;
						}while(this.allRoad[x][i-1].isUse);
						Date b = new Date();
						long diff = b.getTime()-a.getTime();
						this.allRoad[x][i].isUse = false;
						BufferedWriter writer11111;
						try {
							writer11111 = new BufferedWriter(new OutputStreamWriter(
									new FileOutputStream(path, true)));
							writer11111.write("\n "+this.allRoad[x][i].storage.stoNumber);
					        writer11111.write("\n "+String.valueOf((int)(endTime%(13*60)/60)+8)+":"+String.valueOf((int)(endTime-(int)(endTime/60)*60)));
					        endTime =  (endTime + this.allRoad[x][i-1].driveTime + diff);
					        writer11111.write("\n "+String.valueOf((int)(endTime%(13*60)/60)+8)+":"+String.valueOf((int)(endTime-(int)(endTime/60)*60)));
					        writer11111.close();
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						
						
						String.join("-", list,String.valueOf((int)(endTime/60))+":"+String.valueOf((int)(endTime-(int)(endTime/60)*60)));
					}
					else{
						endTime = (endTime + this.allRoad[x][i-1].driveTime);
					}
				}
				for(int i = x; i<14; i++){
					endTime = (endTime + this.allRoad[i][14].driveTime);
				}
				//离开园区
				BufferedWriter writer;
				
				if(finish){
					try {
						writer = new BufferedWriter(new OutputStreamWriter(
								new FileOutputStream(path, true)));
						writer.write("\n "+"OUT");
				        writer.write("\n "+String.valueOf((int)(endTime%(13*60)/60)+8)+":"+String.valueOf((int)(endTime-(int)(endTime/60)*60)));
				        writer.write("\r\n");
				        writer.close();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					System.out.println("alread leave at "+(int)(endTime/60)+":"+(int)(endTime-(int)(endTime/60)*60));
				}
				
				
				
			}
			
			if(x==5){
				for(int i = 15; i<y-1; i++){
					if(this.allRoad[x][i+1].isUse){
						Date a = new Date();
						do{
							this.allRoad[x][i].isUse = true;
						}while(this.allRoad[x][i+1].isUse);
						Date b = new Date();
						long diff = b.getTime()-a.getTime();
						this.allRoad[x][i].isUse = false;
						BufferedWriter writer11111;
						try {
							writer11111 = new BufferedWriter(new OutputStreamWriter(
									new FileOutputStream(path, true)));
							writer11111.write("\r\n "+this.allRoad[x][i].storage.stoNumber);
					        writer11111.write("\n "+String.valueOf((int)(endTime%(13*60)/60)+8)+":"+String.valueOf((int)(endTime-(int)(endTime/60)*60)));
					        endTime = (endTime + this.allRoad[x][i+1].driveTime + diff);
					        writer11111.write("\n "+String.valueOf((int)(endTime%(13*60)/60)+8)+":"+String.valueOf((int)(endTime-(int)(endTime/60)*60)));
					        writer11111.close();
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						
						
						String.join("-", list,String.valueOf((int)(endTime/60))+":"+String.valueOf((int)(endTime-(int)(endTime/60)*60)));
					}
					else{
						endTime = (endTime + this.allRoad[x][i+1].driveTime);
					}
				}
				if(this.allRoad[x][y].isUse) {
					
					Date a = new Date();
					System.out.println("waiting"+(int)(endTime/60)+":"+(int)(endTime-(int)(endTime/60)*60));
					do {
						try {
							Thread.sleep((long)(0.1+Math.random()*(10-0.1+1)));
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}while(this.allRoad[x][y].isUse);
					Date b = new Date();
					long diff = b.getTime()-a.getTime();
					BufferedWriter writer11111;
					try {
						writer11111 = new BufferedWriter(new OutputStreamWriter(
								new FileOutputStream(path, true)));
						writer11111.write("\n "+this.allRoad[x][y].storage.stoNumber);
				        writer11111.write("\n "+String.valueOf((int)(endTime%(13*60)/60)+8)+":"+String.valueOf((int)(endTime-(int)(endTime/60)*60)));
				        endTime = (endTime + this.allRoad[x][y].driveTime + diff);
				        writer11111.write("\n "+String.valueOf((int)(endTime%(13*60)/60)+8)+":"+String.valueOf((int)(endTime-(int)(endTime/60)*60)));
				        writer11111.close();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
					
					String.join("-", list,String.valueOf((int)(endTime/60))+":"+String.valueOf((int)(endTime-(int)(endTime/60)*60)));
					System.out.println("finish waiting"+(int)(endTime/60)+":"+(int)(endTime-(int)(endTime/60)*60));
				}
				else{
					endTime = (endTime + this.allRoad[x][y].driveTime);
				}
				//到达目标仓位，开始卸货，堵塞道路
				BufferedWriter writer11111;
				try {
					writer11111 = new BufferedWriter(new OutputStreamWriter(
							new FileOutputStream(path, true)));
					writer11111.write("\n "+this.allRoad[x][y].storage.stoNumber);
			        writer11111.write("\n "+String.valueOf((int)(endTime%(13*60)/60)+8)+":"+String.valueOf((int)(endTime-(int)(endTime/60)*60)));
			        endTime = (endTime - this.weight/50*15);
			        writer11111.write("\n "+String.valueOf((int)(endTime%(13*60)/60)+8)+":"+String.valueOf((int)(endTime-(int)(endTime/60)*60)));
			        writer11111.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				
				String.join("-", list,String.valueOf((int)(endTime/60))+":"+String.valueOf((int)(endTime-(int)(endTime/60)*60)));
				this.allRoad[x][y].block((int) (this.weight/50*15));
				//卸货完成，解除堵塞,开始出园
				for(int i = y; i<26; i++){
					if(this.allRoad[x][i+1].isUse){
						Date a = new Date();
						do{
							this.allRoad[x][i].isUse = true;
						}while(this.allRoad[x][i+1].isUse);
						Date b = new Date();
						long diff = b.getTime()-a.getTime();
						this.allRoad[x][i].isUse = false;
						BufferedWriter writer111111;
						try {
							writer111111 = new BufferedWriter(new OutputStreamWriter(
									new FileOutputStream(path, true)));
							writer111111.write("\n "+this.allRoad[x][i].storage.stoNumber);
					        writer111111.write("\n "+String.valueOf((int)(endTime%(13*60)/60)+8)+":"+String.valueOf((int)(endTime-(int)(endTime/60)*60)));
					        endTime =  (endTime + this.allRoad[x][i+1].driveTime + diff);
					        writer111111.write("\n "+String.valueOf((int)(endTime%(13*60)/60)+8)+":"+String.valueOf((int)(endTime-(int)(endTime/60)*60)));
					        writer111111.close();
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						
						
						String.join("-", list,String.valueOf((int)(endTime/60))+":"+String.valueOf((int)(endTime-(int)(endTime/60)*60)));
					}
					else{
						endTime =  (endTime + this.allRoad[x][i-1].driveTime);
					}
				}
				for(int i = 25; i>15; i--){
					if(this.allRoad[x+1][i-1].isUse){
						Date a = new Date();
						do{
							this.allRoad[x+1][i].isUse = true;
						}while(this.allRoad[x+1][i-1].isUse);
						Date b = new Date();
						long diff = b.getTime()-a.getTime();
						this.allRoad[x+1][i].isUse = false;
						BufferedWriter writer111111;
						try {
							writer111111 = new BufferedWriter(new OutputStreamWriter(
									new FileOutputStream(path, true)));
							writer111111.write("\n "+this.allRoad[x+1][i].storage.stoNumber);
					        writer111111.write("\n "+String.valueOf((int)(endTime%(13*60)/60)+8)+":"+String.valueOf((int)(endTime-(int)(endTime/60)*60)));
					        endTime =  (endTime + this.allRoad[x+1][i-1].driveTime + diff);
					        writer111111.write("\n "+String.valueOf((int)(endTime%(13*60)/60)+8)+":"+String.valueOf((int)(endTime-(int)(endTime/60)*60)));
					        writer111111.close();
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						
						
						String.join("-", list,String.valueOf((int)(endTime/60))+":"+String.valueOf((int)(endTime-(int)(endTime/60)*60)));
					}
					else{
						endTime = (endTime + this.allRoad[x+1][i-1].driveTime);
					}
				}
				for(int i = x; i<14; i++){
					endTime =  (endTime + this.allRoad[i][14].driveTime);
				}
				//离开园区
				BufferedWriter writer;
				
				if(finish){
					try {
						writer = new BufferedWriter(new OutputStreamWriter(
								new FileOutputStream(path, true)));
						writer.write("\n "+"OUT");
				        writer.write("\n "+String.valueOf((int)(endTime%(13*60)/60)+8)+":"+String.valueOf((int)(endTime-(int)(endTime/60)*60)));
				        writer.write("\r\n");
				        writer.close();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					System.out.println("alread leave at "+(int)(endTime/60)+":"+(int)(endTime-(int)(endTime/60)*60));
				}
				
				
				
			}
			//双车道，可以超车
			if(x==9){
				for(int i = 15; i<y-1; i++){
					if(this.allRoad[x][i+1].isUse&&this.allRoad[x+1][i+1].isUse){
						//双车道都被堵塞
						Date a = new Date();
						do{
							this.allRoad[x][i].isUse = true;
						}while(this.allRoad[x][i+1].isUse&&this.allRoad[x+1][i+1].isUse);
						Date b = new Date();
						long diff = b.getTime()-a.getTime();
						this.allRoad[x][i].isUse = false;
						BufferedWriter writer111111;
						try {
							writer111111 = new BufferedWriter(new OutputStreamWriter(
									new FileOutputStream(path, true)));
							writer111111.write("\r\n "+this.allRoad[x][i].storage.stoNumber);
					        writer111111.write("\n "+String.valueOf((int)(endTime%(13*60)/60)+8)+":"+String.valueOf((int)(endTime-(int)(endTime/60)*60)));
					        endTime = (endTime + this.allRoad[x][i+1].driveTime + diff);
					        writer111111.write("\n "+String.valueOf((int)(endTime%(13*60)/60)+8)+":"+String.valueOf((int)(endTime-(int)(endTime/60)*60)));
					        writer111111.close();
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						
						
						String.join("-", list,String.valueOf((int)(endTime/60))+":"+String.valueOf((int)(endTime-(int)(endTime/60)*60)));
					}
					else{
						endTime =  (endTime + this.allRoad[x][i+1].driveTime);
					}
				}
				if(this.allRoad[x][y].isUse) {
					
					Date a = new Date();
					System.out.println("waiting"+(int)(endTime/60)+":"+(int)(endTime-(int)(endTime/60)*60));
					do {
						try {
							Thread.sleep((long)(0.1+Math.random()*(40-0.1+1)));
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}while(this.allRoad[x][y].isUse);
					Date b = new Date();
					long diff = b.getTime()-a.getTime();
					BufferedWriter writer111111;
					try {
						writer111111 = new BufferedWriter(new OutputStreamWriter(
								new FileOutputStream(path, true)));
						writer111111.write("\n "+this.allRoad[x][y].storage.stoNumber);
				        writer111111.write("\n "+String.valueOf((int)(endTime%(13*60)/60)+8)+":"+String.valueOf((int)(endTime-(int)(endTime/60)*60)));
				        endTime = (endTime + this.allRoad[x][y].driveTime + diff);
				        writer111111.write("\n "+String.valueOf((int)(endTime%(13*60)/60)+8)+":"+String.valueOf((int)(endTime-(int)(endTime/60)*60)));
				        writer111111.close();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
					
					String.join("-", list,String.valueOf((int)(endTime/60))+":"+String.valueOf((int)(endTime-(int)(endTime/60)*60)));
					System.out.println("finish waiting"+(int)(endTime/60)+":"+(int)(endTime-(int)(endTime/60)*60));
				}
				else{
					endTime = (endTime + this.allRoad[x][y].driveTime);
				}
				//到达目标仓位，开始卸货，堵塞道路
				BufferedWriter writer111111;
				try {
					writer111111 = new BufferedWriter(new OutputStreamWriter(
							new FileOutputStream(path, true)));
					writer111111.write("\n "+this.allRoad[x][y].storage.stoNumber);
			        writer111111.write("\n "+String.valueOf((int)(endTime%(13*60)/60)+8)+":"+String.valueOf((int)(endTime-(int)(endTime/60)*60)));
			        endTime =  (endTime - this.weight/50*15);
			        writer111111.write("\n "+String.valueOf((int)(endTime%(13*60)/60)+8)+":"+String.valueOf((int)(endTime-(int)(endTime/60)*60)));
			        writer111111.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				endTime =  (endTime + this.weight/50*15);
				
				String.join("-", list,String.valueOf((int)(endTime/60))+":"+String.valueOf((int)(endTime-(int)(endTime/60)*60)));
				this.allRoad[x][y].block((int) (this.weight/50*15));
				//卸货完成，解除堵塞,开始出园
				for(int i = y; i<26; i++){
					if(this.allRoad[x][i+1].isUse&&this.allRoad[x+1][i+1].isUse){
						//双车道都被堵塞
						Date a = new Date();
						do{
							this.allRoad[x][i].isUse = true;
						}while(this.allRoad[x][i+1].isUse&&this.allRoad[x+1][i+1].isUse);
						Date b = new Date();
						long diff = b.getTime()-a.getTime();
						this.allRoad[x][i].isUse = false;
						BufferedWriter writer1111111;
						try {
							writer1111111 = new BufferedWriter(new OutputStreamWriter(
									new FileOutputStream(path, true)));
							writer1111111.write("\n "+this.allRoad[x][i].storage.stoNumber);
					        writer1111111.write("\n "+String.valueOf((int)(endTime%(13*60)/60)+8)+":"+String.valueOf((int)(endTime-(int)(endTime/60)*60)));
					        endTime = (endTime + this.allRoad[x][i+1].driveTime + diff);
					        writer1111111.write("\n "+String.valueOf((int)(endTime%(13*60)/60)+8)+":"+String.valueOf((int)(endTime-(int)(endTime/60)*60)));
					        writer1111111.close();
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						
						
						String.join("-", list,String.valueOf((int)(endTime/60))+":"+String.valueOf((int)(endTime-(int)(endTime/60)*60)));
					}
					else{
						endTime =  (endTime + this.allRoad[x][i+1].driveTime);
					}
				}
				for(int i = 25; i>15; i--){
					if(this.allRoad[6][i-1].isUse){
						Date a = new Date();
						do{
							this.allRoad[6][i].isUse = true;
						}while(this.allRoad[6][i-1].isUse);
						Date b = new Date();
						long diff = b.getTime()-a.getTime();
						this.allRoad[6][i].isUse = false;
						BufferedWriter writer1111111;
						try {
							writer1111111 = new BufferedWriter(new OutputStreamWriter(
									new FileOutputStream(path, true)));
							writer1111111.write("\n "+this.allRoad[6][i].storage.stoNumber);
					        writer1111111.write("\n "+String.valueOf((int)(endTime%(13*60)/60)+8)+":"+String.valueOf((int)(endTime-(int)(endTime/60)*60)));
					        endTime = (endTime + this.allRoad[6][i-1].driveTime + diff);
					        writer1111111.write("\n "+String.valueOf((int)(endTime%(13*60)/60)+8)+":"+String.valueOf((int)(endTime-(int)(endTime/60)*60)));
					        writer1111111.close();
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						
						
						String.join("-", list,String.valueOf((int)(endTime/60))+":"+String.valueOf((int)(endTime-(int)(endTime/60)*60)));
					}
					else{
						endTime =  (endTime + this.allRoad[6][i-1].driveTime);
					}
				}
				for(int i = 0; i<11; i++){
					endTime = (endTime + this.allRoad[1][14].driveTime);
				}
				//离开园区
				BufferedWriter writer;
				
				if(finish){
					try {
						writer = new BufferedWriter(new OutputStreamWriter(
								new FileOutputStream(path, true)));
						writer.write("\n "+"OUT");
				        writer.write("\n "+String.valueOf((int)(endTime%(13*60)/60)+8)+":"+String.valueOf((int)(endTime-(int)(endTime/60)*60)));
				        writer.write("\r\n");
				        writer.close();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					System.out.println("alread leave at "+(int)(endTime/60)+":"+(int)(endTime-(int)(endTime/60)*60));
				}
				
				
				
			}
			if(x==10){
				for(int i = 15; i<y-1; i++){
					if(this.allRoad[x][i+1].isUse&&this.allRoad[x-1][i+1].isUse){
						//双车道都被堵塞
						Date a = new Date();
						do{
							this.allRoad[x][i].isUse = true;
						}while(this.allRoad[x][i+1].isUse&&this.allRoad[x-1][i+1].isUse);
						Date b = new Date();
						long diff = b.getTime()-a.getTime();
						this.allRoad[x][i].isUse = false;
						BufferedWriter writer1111111;
						try {
							writer1111111 = new BufferedWriter(new OutputStreamWriter(
									new FileOutputStream(path, true)));
							writer1111111.write("\r\n "+this.allRoad[x][i].storage.stoNumber);
					        writer1111111.write("\n "+String.valueOf((int)(endTime%(13*60)/60)+8)+":"+String.valueOf((int)(endTime-(int)(endTime/60)*60)));
					        endTime =  (endTime + this.allRoad[x][i+1].driveTime + diff);
					        writer1111111.write("\n "+String.valueOf((int)(endTime%(13*60)/60)+8)+":"+String.valueOf((int)(endTime-(int)(endTime/60)*60)));
					        writer1111111.close();
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						
						
						String.join("-", list,String.valueOf((int)(endTime/60))+":"+String.valueOf((int)(endTime-(int)(endTime/60)*60)));
					}
					else{
						endTime =  (endTime + this.allRoad[x][i+1].driveTime);
					}
				}
				if(this.allRoad[x][y].isUse) {
					
						Date a = new Date();
						System.out.println("waiting"+(int)(endTime/60)+":"+(int)(endTime-(int)(endTime/60)*60));
						do {
							try {
								Thread.sleep((long)(0.1+Math.random()*(40-0.1+1)));
							} catch (InterruptedException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						}while(this.allRoad[x][y].isUse);
						Date b = new Date();
						long diff = b.getTime()-a.getTime();
						BufferedWriter writer1111111;
						try {
							writer1111111 = new BufferedWriter(new OutputStreamWriter(
									new FileOutputStream(path, true)));
							writer1111111.write("\n "+this.allRoad[x][y].storage.stoNumber);
					        writer1111111.write("\n "+String.valueOf((int)(endTime%(13*60)/60)+8)+":"+String.valueOf((int)(endTime-(int)(endTime/60)*60)));
					        endTime = (endTime + this.allRoad[x][y].driveTime + diff);
					        writer1111111.write("\n "+String.valueOf((int)(endTime%(13*60)/60)+8)+":"+String.valueOf((int)(endTime-(int)(endTime/60)*60)));
					        writer1111111.close();
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						
						
						String.join("-", list,String.valueOf((int)(endTime/60))+":"+String.valueOf((int)(endTime-(int)(endTime/60)*60)));
						System.out.println("finish waiting"+(int)(endTime/60)+":"+(int)(endTime-(int)(endTime/60)*60));
				}
				else{
						endTime = (endTime + this.allRoad[x][y].driveTime);
				}
				//到达目标仓位，开始卸货，堵塞道路
				BufferedWriter writer1111111;
				try {
					writer1111111 = new BufferedWriter(new OutputStreamWriter(
							new FileOutputStream(path, true)));
					writer1111111.write("\n "+this.allRoad[x][y].storage.stoNumber);
			        writer1111111.write("\n "+String.valueOf((int)(endTime%(13*60)/60)+8)+":"+String.valueOf((int)(endTime-(int)(endTime/60)*60)));
			        endTime =  (endTime - this.weight/50*15);
			        writer1111111.write("\n "+String.valueOf((int)(endTime%(13*60)/60)+8)+":"+String.valueOf((int)(endTime-(int)(endTime/60)*60)));
			        writer1111111.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				
				String.join("-", list,String.valueOf((int)(endTime/60))+":"+String.valueOf((int)(endTime-(int)(endTime/60)*60)));
				this.allRoad[x][y].block((int) (this.weight/50*15));
				//卸货完成，解除堵塞,开始出园
				for(int i = y; i<26; i++){
					if(this.allRoad[x][i+1].isUse&&this.allRoad[x-1][i+1].isUse){
						//双车道都被堵塞
						Date a = new Date();
						do{
							this.allRoad[x][i].isUse = true;
						}while(this.allRoad[x][i+1].isUse&&this.allRoad[x-1][i+1].isUse);
						Date b = new Date();
						long diff = b.getTime()-a.getTime();
						this.allRoad[x][i].isUse = false;
						BufferedWriter writer11111111;
						try {
							writer11111111 = new BufferedWriter(new OutputStreamWriter(
									new FileOutputStream(path, true)));
							writer11111111.write("\n "+this.allRoad[x][i].storage.stoNumber);
					        writer11111111.write("\n "+String.valueOf((int)(endTime%(13*60)/60)+8)+":"+String.valueOf((int)(endTime-(int)(endTime/60)*60)));
					        endTime =  (endTime + this.allRoad[x][i+1].driveTime + diff);
					        writer11111111.write("\n "+String.valueOf((int)(endTime%(13*60)/60)+8)+":"+String.valueOf((int)(endTime-(int)(endTime/60)*60)));
					        writer11111111.close();
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						
						
						String.join("-", list,String.valueOf((int)(endTime/60))+":"+String.valueOf((int)(endTime-(int)(endTime/60)*60)));
					}
					else{
						endTime =  (endTime + this.allRoad[x][i+1].driveTime);
					}
				}
				for(int i = 25; i>15; i--){
					if(this.allRoad[6][i-1].isUse){
						Date a = new Date();
						do{
							this.allRoad[6][i].isUse = true;
						}while(this.allRoad[6][i-1].isUse);
						Date b = new Date();
						long diff = b.getTime()-a.getTime();
						this.allRoad[6][i].isUse = false;
						BufferedWriter writer11111111;
						try {
							writer11111111 = new BufferedWriter(new OutputStreamWriter(
									new FileOutputStream(path, true)));
							writer11111111.write("\n "+this.allRoad[6][i].storage.stoNumber);
					        writer11111111.write("\n "+String.valueOf((int)(endTime%(13*60)/60)+8)+":"+String.valueOf((int)(endTime-(int)(endTime/60)*60)));
					        endTime = (endTime + this.allRoad[6][i-1].driveTime + diff);
					        writer11111111.write("\n "+String.valueOf((int)(endTime%(13*60)/60)+8)+":"+String.valueOf((int)(endTime-(int)(endTime/60)*60)));
					        writer11111111.close();
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						
						
						String.join("-", list,String.valueOf((int)(endTime/60))+":"+String.valueOf((int)(endTime-(int)(endTime/60)*60)));
					}
					else{
						endTime = (endTime + this.allRoad[6][i-1].driveTime);
					}
				}
				for(int i = 0; i<12; i++){
					endTime =  (endTime + this.allRoad[1][14].driveTime);
				}
				//离开园区
				BufferedWriter writer;
				
				if(finish){
					try {
						writer = new BufferedWriter(new OutputStreamWriter(
								new FileOutputStream(path, true)));
						writer.write("\n "+"OUT");
				        writer.write("\n "+String.valueOf((int)(endTime%(13*60)/60)+8)+":"+String.valueOf((int)(endTime-(int)(endTime/60)*60)));
				        writer.write("\r\n");
				        writer.close();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					System.out.println("alread leave at "+(int)(endTime/60)+":"+(int)(endTime-(int)(endTime/60)*60));
				}
				
				
				
			}
			
		}
		
		System.out.println(list);
		}
	
	public void start () {
	      System.out.println("Starting a car");
	      if (t == null) {
	         t = new Thread (this);
	         t.start ();
	      }
	   }
}

public class LogisticsPark {
	
	
	public static void main(String[] args) throws BiffException, IOException {
		
		Storage fakeSto = new Storage("","","all","all",3,100,0,0,true);//一个不存在的仓位
		Road fakeRoad = new Road("",fakeSto);//一条不存在的道路
		fakeSto.road = fakeRoad;
		
		//初始化仓位
		Storage s00 = new Storage("B","BP","all","all",3,100,0,12,true);
		Storage s01 = new Storage("B","BP","all","all",3,100,0,12,true);
		Storage s02 = new Storage("B","BP","all","all",3,100,0,12,true);
		Storage s03 = new Storage("B","BP","all","all",3,100,0,12,true);
		Storage s04 = new Storage("B","BP","all","all",3,100,0,12,true);
		Storage s05 = new Storage("B","BP","all","all",3,100,0,12,true);
		Storage s06 = new Storage("B","BP","all","all",3,100,0,12,true);
		Storage s07 = new Storage("B","BP","all","all",3,100,0,12,true);
		Storage s08 = new Storage("","","","",3,100,0,0,true);
		Storage s09 = new Storage("","","","",3,100,0,0,true);
		Storage s010 = new Storage("C","C16","HRB400","28*12",2,551500,0,12,false);
		Storage s011 = new Storage("C","C4","HTRB600E抗震","25*12",2,551500,0,12,false);
		Storage s012 = new Storage("","","","",3,100,0,0,true);
		Storage s013 = new Storage("","","","",3,100,0,0,true);
		Storage s014 = new Storage("","","","",3,100,0,0,true);
		Storage s015 = new Storage("","","","",3,100,0,0,true);
		Storage s016 = new Storage("","","","",3,100,0,0,true);
		Storage s017 = new Storage("","","","",3,100,0,0,true);
		Storage s018 = new Storage("","","","",3,100,0,0,true);
		Storage s019 = new Storage("","","","",3,100,0,0,true);
		Storage s020 = new Storage("","","","",3,100,0,0,true);
		Storage s021 = new Storage("","","","",3,100,0,0,true);
		Storage s022 = new Storage("","","","",3,100,0,0,true);
		
		Storage s10 = new Storage("C","CP","all","all",3,100,0,12,true);
		Storage s11 = new Storage("C","CP","all","all",3,100,0,12,true);
		Storage s12 = new Storage("C","CP","all","all",3,100,0,12,true);
		Storage s13 = new Storage("C","CP","all","all",3,100,0,12,true);
		Storage s14 = new Storage("C","CP","all","all",3,100,0,12,true);
		Storage s15 = new Storage("","","","",3,100,0,0,true);
		Storage s16 = new Storage("B","B1","HRB400","28*9",2,5551500,0,9,false);
		Storage s17 = new Storage("B","B1","HRB400","28*9",2,5551500,0,9,false);
		Storage s18 = new Storage("B","B8","HRB400E抗震","28*9",2,5551500,0,9,false);
		Storage s19 = new Storage("B","B9","HRB400","32*9",2,5551500,0,9,false);
		Storage s110 = new Storage("B","B2","HRB400","16*12",2,5551500,0,12,true);
		Storage s111 = new Storage("B","B5","HTRB600E抗震","25*12",2,5551500,0,12,true);
		Storage s112 = new Storage("","","","",3,100,0,0,true);
		Storage s113 = new Storage("","","","",3,100,0,0,true);
		Storage s114 = new Storage("","","","",3,100,0,0,true);
		Storage s115 = new Storage("","","","",3,100,0,0,true);
		Storage s116 = new Storage("","","","",3,100,0,0,true);
		Storage s117 = new Storage("","","","",3,100,0,0,true);
		Storage s118 = new Storage("","","","",3,100,0,0,true);
		Storage s119 = new Storage("","","","",3,100,0,0,true);
		Storage s120 = new Storage("","","","",3,100,0,0,true);
		Storage s121 = new Storage("","","","",3,100,0,0,true);
		Storage s122 = new Storage("","","","",3,100,0,0,true);
		
		Storage s20 = new Storage("A","A8","HRB400E抗震","16*9",2,5551500,0,9,false);
		Storage s21 = new Storage("A","A8","HRB400E抗震","16*9",2,5551500,0,9,false);
		Storage s22 = new Storage("A","A8","HRB400E抗震","16*9",2,5551500,0,9,false);
		Storage s23 = new Storage("A","A8","HRB400E抗震","16*9",2,5551500,0,9,false);
		Storage s24 = new Storage("A","A12","HRB400E抗震","25*9",2,5551500,0,9,false);
		Storage s25 = new Storage("A","A12","HRB400E抗震","25*9",2,5551500,0,9,false);
		Storage s26 = new Storage("A","A12","HRB400E抗震","25*9",2,5551500,0,9,false);
		Storage s27 = new Storage("A","A12","HRB400E抗震","25*9",2,5551500,0,9,false);
		Storage s28 = new Storage("A","A12","HRB400E抗震","25*9",2,5551500,0,9,false);
		Storage s29 = new Storage("A","A13","HRB400E抗震","20*9",2,5551500,0,9,false);
		Storage s210 = new Storage("A","A13","HRB400E抗震","20*9",2,5551500,0,9,false);
		Storage s211 = new Storage("A","A13","HRB400E抗震","20*9",2,5551500,0,9,false);
		Storage s212 = new Storage("A","A13","HRB400E抗震","20*9",2,5551500,0,9,false);
		Storage s213 = new Storage("A","A14","HRB400E抗震","22*9",2,5551500,0,9,false);
		Storage s214 = new Storage("A","A14","HRB400E抗震","22*9",2,5551500,0,9,false);
		Storage s215 = new Storage("A","A14","HRB400E抗震","22*9",2,5551500,0,9,false);
		Storage s216 = new Storage("A","A14","HRB400E抗震","22*9",2,5551500,0,9,false);
		Storage s217 = new Storage("A","A14","HRB400E抗震","22*9",2,5551500,0,9,false);
		Storage s218 = new Storage("B","B3","HRB400E抗震","25*12",2,5551500,0,12,false);
		Storage s219 = new Storage("B","B4","HRB400E抗震","20*9",2,5551500,0,12,false);
		Storage s220 = new Storage("B","B6","HRB400E抗震","18*12",2,5551500,0,12,false);
		Storage s221 = new Storage("B","B7","HRB400","18*12",2,5551500,0,12,false);
		Storage s222 = new Storage("","","","",3,100,0,0,true);
		
		Storage s30 = new Storage("A","A6","HRB400","25*9",2,5551500,0,9,false);
		Storage s31 = new Storage("A","A6","HRB400","25*9",2,5551500,0,9,false);
		Storage s32 = new Storage("A","A6","HRB400","25*9",2,5551500,0,9,false);
		Storage s33 = new Storage("A","A6","HRB400","25*9",2,5551500,0,9,false);
		Storage s34 = new Storage("A","A6","HRB400","25*9",2,5551500,0,9,false);
		Storage s35 = new Storage("A","A6","HRB400","25*9",2,5551500,0,9,false);
		Storage s36 = new Storage("A","A7","HRB400E抗震","12*9",2,5551500,0,9,false);
		Storage s37 = new Storage("A","A7","HRB400E抗震","12*9",2,5551500,0,9,false);
		Storage s38 = new Storage("A","A7","HRB400E抗震","12*9",2,5551500,0,9,false);
		Storage s39 = new Storage("A","A7","HRB400E抗震","12*9",2,5551500,0,9,false);
		Storage s310 = new Storage("A","A7","HRB400E抗震","12*9",2,5551500,0,9,false);
		Storage s311 = new Storage("A","A7","HRB400E抗震","12*9",2,5551500,0,9,false);
		Storage s312 = new Storage("A","A9","HRB400E抗震","14*9",2,5551500,0,9,false);
		Storage s313 = new Storage("A","A9","HRB400E抗震","14*9",2,5551500,0,9,false);
		Storage s314 = new Storage("A","A9","HRB400E抗震","14*9",2,5551500,0,9,false);
		Storage s315 = new Storage("A","A9","HRB400E抗震","14*9",2,5551500,0,9,false);
		Storage s316 = new Storage("A","A9","HRB400E抗震","14*9",2,5551500,0,9,false);
		Storage s317 = new Storage("A","A9","HRB400E抗震","14*9",2,5551500,0,9,false);
		Storage s318 = new Storage("A","A10","HRB400","22*9",2,5551500,0,9,false);
		Storage s319 = new Storage("A","A10","HRB400","22*9",2,5551500,0,9,false);
		Storage s320 = new Storage("A","A10","HRB400","22*9",2,5551500,0,9,false);
		Storage s321 = new Storage("A","A10","HRB400","22*9",2,5551500,0,9,false);
		Storage s322 = new Storage("A","A10","HRB400","22*9",2,5551500,0,9,false);
		
		Storage s40 = new Storage("A","A8","HRB400E抗震","16*9",2,5551500,0,9,false);
		Storage s41 = new Storage("A","A4","HRB400","20*9",2,5551500,0,9,false);
		Storage s42 = new Storage("A","A4","HRB400","20*9",2,5551500,0,9,false);
		Storage s43 = new Storage("A","A4","HRB400","20*9",2,5551500,0,9,false);
		Storage s44 = new Storage("A","A4","HRB400","20*9",2,5551500,0,9,false);
		Storage s45 = new Storage("A","A4","HRB400","20*9",2,5551500,0,9,false);
		Storage s46 = new Storage("A","A2","HRB400","16*9",2,5551500,0,9,false);
		Storage s47 = new Storage("A","A2","HRB400","16*9",2,5551500,0,9,false);
		Storage s48 = new Storage("A","A2","HRB400","16*9",2,5551500,0,9,false);
		Storage s49 = new Storage("A","A2","HRB400","16*9",2,5551500,0,9,false);
		Storage s410 = new Storage("A","A2","HRB400","16*9",2,5551500,0,9,false);
		Storage s411 = new Storage("A","A2","HRB400","16*9",2,5551500,0,9,false);
		Storage s412 = new Storage("A","A5","HRB400","18*9",2,5551500,0,9,false);
		Storage s413 = new Storage("A","A5","HRB400","18*9",2,5551500,0,9,false);
		Storage s414 = new Storage("A","A5","HRB400","18*9",2,5551500,0,9,false);
		Storage s415 = new Storage("A","A5","HRB400","18*9",2,5551500,0,9,false);
		Storage s416 = new Storage("A","A5","HRB400","18*9",2,5551500,0,9,false);
		Storage s417 = new Storage("A","A5","HRB400","18*9",2,5551500,0,9,false);
		Storage s418 = new Storage("A","A5","HRB400","18*9",2,5551500,0,9,false);
		Storage s419 = new Storage("A","A6","HRB400","25*9",2,5551500,0,9,false);
		Storage s420 = new Storage("A","A6","HRB400","25*9",2,5551500,0,9,false);
		Storage s421 = new Storage("A","A6","HRB400","25*9",2,5551500,0,9,false);
		Storage s422 = new Storage("","","","",3,100,0,0,true);
		
		Storage s50 = new Storage("A","A8","HRB400E抗震","16*9",2,5551500,0,9,false);
		Storage s51 = new Storage("A","A8","HRB400E抗震","16*9",2,5551500,0,9,false);
		Storage s52 = new Storage("A","A1","HRB400","12*9",2,5551500,0,9,false);
		Storage s53 = new Storage("A","A1","HRB400","12*9",2,5551500,0,9,false);
		Storage s54 = new Storage("A","A1","HRB400","12*9",2,5551500,0,9,false);
		Storage s55 = new Storage("A","A1","HRB400","12*9",2,5551500,0,9,false);
		Storage s56 = new Storage("A","A1","HRB400","12*9",2,5551500,0,9,false);
		Storage s57 = new Storage("A","A1","HRB400","12*9",2,5551500,0,9,false);
		Storage s58 = new Storage("A","A1","HRB400","12*9",2,5551500,0,9,false);
		Storage s59 = new Storage("A","A1","HRB400","12*9",2,5551500,0,9,false);
		Storage s510 = new Storage("A","A1","HRB400","12*9",2,5551500,0,9,false);
		Storage s511 = new Storage("A","A1","HRB400","12*9",2,5551500,0,9,false);
		Storage s512 = new Storage("A","A3","HRB400","14*9",2,5551500,0,9,false);
		Storage s513 = new Storage("A","A3","HRB400","14*9",2,5551500,0,9,false);
		Storage s514 = new Storage("A","A3","HRB400","14*9",2,5551500,0,9,false);
		Storage s515 = new Storage("A","A3","HRB400","14*9",2,5551500,0,9,false);
		Storage s516 = new Storage("A","A3","HRB400","14*9",2,5551500,0,9,false);
		Storage s517 = new Storage("A","A3","HRB400","14*9",2,5551500,0,9,false);
		Storage s518 = new Storage("A","A3","HRB400","14*9",2,5551500,0,9,false);
		Storage s519 = new Storage("A","A6","HRB400","25*9",2,5551500,0,9,false);
		Storage s520 = new Storage("A","A6","HRB400","25*9",2,5551500,0,9,false);
		Storage s521 = new Storage("A","A6","HRB400","25*9",2,5551500,0,9,false);
		Storage s522 = new Storage("","","","",3,100,0,0,true);
		//仓位初始化完成
		
		//初始化道路
		Road r00 = new Road("",fakeSto);
		Road r01 = new Road("",fakeSto);
		Road r02 = new Road("",fakeSto);
		Road r03 = new Road("",fakeSto);
		Road r04 = new Road("",fakeSto);
		Road r05 = new Road("",fakeSto);
		Road r06 = new Road("",fakeSto);
		Road r07 = new Road("",fakeSto);
		Road r08 = new Road("",fakeSto);
		Road r09 = new Road("",fakeSto);
		Road r010 = new Road("",fakeSto);
		Road r011 = new Road("",fakeSto);
		Road r012 = new Road("",fakeSto);
		Road r013 = new Road("",fakeSto);
		Road r014 = new Road("",fakeSto);
		Road r015 = new Road("",fakeSto);
		Road r016 = new Road("",fakeSto);
		Road r017 = new Road("",fakeSto);
		Road r018 = new Road("",fakeSto);
		Road r019 = new Road("",fakeSto);
		Road r020 = new Road("",fakeSto);
		Road r021 = new Road("",fakeSto);
		Road r022 = new Road("",fakeSto);
		Road r023 = new Road("",fakeSto);
		Road r024 = new Road("",fakeSto);
		Road r025 = new Road("",fakeSto);
		Road r026 = new Road("",fakeSto);
		
		
		Road r10 = new Road("NS",fakeSto);
		Road r11 = new Road("E",s00);
		Road r12 = new Road("E",s01);
		Road r13 = new Road("E",s02);
		Road r14 = new Road("E",s03);
		Road r15 = new Road("E",s04);
		Road r16 = new Road("E",s05);
		Road r17 = new Road("E",s06);
		Road r18 = new Road("E",s07);
		Road r19 = new Road("E",s08);
		Road r110 = new Road("E",s09);
		Road r111 = new Road("E",s010);
		Road r112 = new Road("E",s011);
		Road r113 = new Road("E",fakeSto);
		Road r114 = new Road("NS",fakeSto);
		Road r115 = new Road("",fakeSto);
		Road r116 = new Road("",fakeSto);
		Road r117 = new Road("",fakeSto);
		Road r118 = new Road("",fakeSto);
		Road r119 = new Road("",fakeSto);
		Road r120 = new Road("",fakeSto);
		Road r121 = new Road("",fakeSto);
		Road r122 = new Road("",fakeSto);
		Road r123 = new Road("",fakeSto);
		Road r124 = new Road("",fakeSto);
		Road r125 = new Road("",fakeSto);
		Road r126 = new Road("",fakeSto);
		
		Road r20 = new Road("NS",fakeSto);
		Road r21 = new Road("W",s10);
		Road r22 = new Road("W",s11);
		Road r23 = new Road("W",s12);
		Road r24 = new Road("W",s13);
		Road r25 = new Road("W",s14);
		Road r26 = new Road("W",s15);
		Road r27 = new Road("W",s16);
		Road r28 = new Road("W",s17);
		Road r29 = new Road("W",s18);
		Road r210 = new Road("W",s19);
		Road r211 = new Road("W",s110);
		Road r212 = new Road("W",s111);
		Road r213 = new Road("W",fakeSto);
		Road r214 = new Road("NS",fakeSto);
		Road r215 = new Road("",fakeSto);
		Road r216 = new Road("",fakeSto);
		Road r217 = new Road("",fakeSto);
		Road r218 = new Road("",fakeSto);
		Road r219 = new Road("",fakeSto);
		Road r220 = new Road("",fakeSto);
		Road r221 = new Road("",fakeSto);
		Road r222 = new Road("",fakeSto);
		Road r223 = new Road("",fakeSto);
		Road r224 = new Road("",fakeSto);
		Road r225 = new Road("",fakeSto);
		Road r226 = new Road("",fakeSto);
		
		Road r30 = new Road("NS",fakeSto);
		Road r31 = new Road("",fakeSto);
		Road r32 = new Road("",fakeSto);
		Road r33 = new Road("",fakeSto);
		Road r34 = new Road("",fakeSto);
		Road r35 = new Road("",fakeSto);
		Road r36 = new Road("",fakeSto);
		Road r37 = new Road("",fakeSto);
		Road r38 = new Road("",fakeSto);
		Road r39 = new Road("",fakeSto);
		Road r310 = new Road("",fakeSto);
		Road r311 = new Road("",fakeSto);
		Road r312 = new Road("",fakeSto);
		Road r313 = new Road("",fakeSto);
		Road r314 = new Road("NS",fakeSto);
		Road r315 = new Road("",fakeSto);
		Road r316 = new Road("",fakeSto);
		Road r317 = new Road("",fakeSto);
		Road r318 = new Road("",fakeSto);
		Road r319 = new Road("",fakeSto);
		Road r320 = new Road("",fakeSto);
		Road r321 = new Road("",fakeSto);
		Road r322 = new Road("",fakeSto);
		Road r323 = new Road("",fakeSto);
		Road r324 = new Road("",fakeSto);
		Road r325 = new Road("",fakeSto);
		Road r326 = new Road("",fakeSto);
		
		Road r40 = new Road("NS",fakeSto);
		Road r41 = new Road("",fakeSto);
		Road r42 = new Road("",fakeSto);
		Road r43 = new Road("",fakeSto);
		Road r44 = new Road("",fakeSto);
		Road r45 = new Road("",fakeSto);
		Road r46 = new Road("",fakeSto);
		Road r47 = new Road("",fakeSto);
		Road r48 = new Road("",fakeSto);
		Road r49 = new Road("",fakeSto);
		Road r410 = new Road("",fakeSto);
		Road r411 = new Road("",fakeSto);
		Road r412 = new Road("",fakeSto);
		Road r413 = new Road("",fakeSto);
		Road r414 = new Road("NS",fakeSto);
		Road r415 = new Road("",fakeSto);
		Road r416 = new Road("",fakeSto);
		Road r417 = new Road("",fakeSto);
		Road r418 = new Road("",fakeSto);
		Road r419 = new Road("",fakeSto);
		Road r420 = new Road("",fakeSto);
		Road r421 = new Road("",fakeSto);
		Road r422 = new Road("",fakeSto);
		Road r423 = new Road("",fakeSto);
		Road r424 = new Road("",fakeSto);
		Road r425 = new Road("",fakeSto);
		Road r426 = new Road("",fakeSto);
		
		Road r50 = new Road("NS",fakeSto);
		Road r51 = new Road("E",s20);
		Road r52 = new Road("E",s21);
		Road r53 = new Road("E",s22);
		Road r54 = new Road("E",s23);
		Road r55 = new Road("E",s24);
		Road r56 = new Road("E",s25);
		Road r57 = new Road("E",s26);
		Road r58 = new Road("E",s27);
		Road r59 = new Road("E",s28);
		Road r510 = new Road("E",s29);
		Road r511 = new Road("E",s210);
		Road r512 = new Road("E",s211);
		Road r513 = new Road("E",fakeSto);
		Road r514 = new Road("NS",fakeSto);
		Road r515 = new Road("E",s212);
		Road r516 = new Road("E",s213);
		Road r517 = new Road("E",s214);
		Road r518 = new Road("E",s215);
		Road r519 = new Road("E",s216);
		Road r520 = new Road("E",s217);
		Road r521 = new Road("E",s218);
		Road r522 = new Road("E",s219);
		Road r523 = new Road("E",s220);
		Road r524 = new Road("E",s221);
		Road r525 = new Road("E",s222);
		Road r526 = new Road("NS",fakeSto);
		
		Road r60 = new Road("NS",fakeSto);
		Road r61 = new Road("W",s30);
		Road r62 = new Road("W",s31);
		Road r63 = new Road("W",s32);
		Road r64 = new Road("W",s33);
		Road r65 = new Road("W",s34);
		Road r66 = new Road("W",s35);
		Road r67 = new Road("W",s36);
		Road r68 = new Road("W",s37);
		Road r69 = new Road("W",s38);
		Road r610 = new Road("W",s39);
		Road r611 = new Road("W",s310);
		Road r612 = new Road("W",s311);
		Road r613 = new Road("W",fakeSto);
		Road r614 = new Road("NS",fakeSto);
		Road r615 = new Road("W",s312);
		Road r616 = new Road("W",s313);
		Road r617 = new Road("W",s314);
		Road r618 = new Road("W",s315);
		Road r619 = new Road("W",s316);
		Road r620 = new Road("W",s317);
		Road r621 = new Road("W",s318);
		Road r622 = new Road("W",s319);
		Road r623 = new Road("W",s320);
		Road r624 = new Road("W",s321);
		Road r625 = new Road("W",s322);
		Road r626 = new Road("NS",fakeSto);
		
		Road r70 = new Road("NS",fakeSto);
		Road r71 = new Road("",fakeSto);
		Road r72 = new Road("",fakeSto);
		Road r73 = new Road("",fakeSto);
		Road r74 = new Road("",fakeSto);
		Road r75 = new Road("",fakeSto);
		Road r76 = new Road("",fakeSto);
		Road r77 = new Road("",fakeSto);
		Road r78 = new Road("",fakeSto);
		Road r79 = new Road("",fakeSto);
		Road r710 = new Road("",fakeSto);
		Road r711 = new Road("",fakeSto);
		Road r712 = new Road("",fakeSto);
		Road r713 = new Road("",fakeSto);
		Road r714 = new Road("NS",fakeSto);
		Road r715 = new Road("",fakeSto);
		Road r716 = new Road("",fakeSto);
		Road r717 = new Road("",fakeSto);
		Road r718 = new Road("",fakeSto);
		Road r719 = new Road("",fakeSto);
		Road r720 = new Road("",fakeSto);
		Road r721 = new Road("",fakeSto);
		Road r722 = new Road("",fakeSto);
		Road r723 = new Road("",fakeSto);
		Road r724 = new Road("",fakeSto);
		Road r725 = new Road("",fakeSto);
		Road r726 = new Road("NS",fakeSto);
		
		Road r80 = new Road("NS",fakeSto);
		Road r81 = new Road("",fakeSto);
		Road r82 = new Road("",fakeSto);
		Road r83 = new Road("",fakeSto);
		Road r84 = new Road("",fakeSto);
		Road r85 = new Road("",fakeSto);
		Road r86 = new Road("",fakeSto);
		Road r87 = new Road("",fakeSto);
		Road r88 = new Road("",fakeSto);
		Road r89 = new Road("",fakeSto);
		Road r810 = new Road("",fakeSto);
		Road r811 = new Road("",fakeSto);
		Road r812 = new Road("",fakeSto);
		Road r813 = new Road("",fakeSto);
		Road r814 = new Road("NS",fakeSto);
		Road r815 = new Road("",fakeSto);
		Road r816 = new Road("",fakeSto);
		Road r817 = new Road("",fakeSto);
		Road r818 = new Road("",fakeSto);
		Road r819 = new Road("",fakeSto);
		Road r820 = new Road("",fakeSto);
		Road r821 = new Road("",fakeSto);
		Road r822 = new Road("",fakeSto);
		Road r823 = new Road("",fakeSto);
		Road r824 = new Road("",fakeSto);
		Road r825 = new Road("",fakeSto);
		Road r826 = new Road("NS",fakeSto);
		
		Road r90 = new Road("NS",fakeSto);
		Road r91 = new Road("W",s40);
		Road r92 = new Road("W",s41);
		Road r93 = new Road("W",s42);
		Road r94 = new Road("W",s43);
		Road r95 = new Road("W",s44);
		Road r96 = new Road("W",s45);
		Road r97 = new Road("W",s46);
		Road r98 = new Road("W",s47);
		Road r99 = new Road("W",s48);
		Road r910 = new Road("W",s49);
		Road r911 = new Road("W",s410);
		Road r912 = new Road("W",s411);
		Road r913 = new Road("W",fakeSto);
		Road r914 = new Road("NS",fakeSto);
		Road r915 = new Road("E",s412);
		Road r916 = new Road("E",s413);
		Road r917 = new Road("E",s414);
		Road r918 = new Road("E",s415);
		Road r919 = new Road("E",s416);
		Road r920 = new Road("E",s417);
		Road r921 = new Road("E",s418);
		Road r922 = new Road("E",s419);
		Road r923 = new Road("E",s420);
		Road r924 = new Road("E",s421);
		Road r925 = new Road("E",s422);
		Road r926 = new Road("NS",fakeSto);
		
		Road r1000 = new Road("NS",fakeSto);
		Road r1001 = new Road("W",s50);
		Road r1002 = new Road("W",s51);
		Road r1003 = new Road("W",s52);
		Road r1004 = new Road("W",s53);
		Road r1005 = new Road("W",s54);
		Road r1006 = new Road("W",s55);
		Road r1007 = new Road("W",s56);
		Road r1008 = new Road("W",s57);
		Road r1009 = new Road("W",s58);
		Road r1010 = new Road("W",s59);
		Road r1011 = new Road("W",s510);
		Road r1012 = new Road("W",s511);
		Road r1013 = new Road("W",fakeSto);
		Road r1014 = new Road("NS",fakeSto);
		Road r1015 = new Road("E",s512);
		Road r1016 = new Road("E",s513);
		Road r1017 = new Road("E",s514);
		Road r1018 = new Road("E",s515);
		Road r1019 = new Road("E",s516);
		Road r1020 = new Road("E",s517);
		Road r1021 = new Road("E",s518);
		Road r1022 = new Road("E",s519);
		Road r1023 = new Road("E",s520);
		Road r1024 = new Road("E",s521);
		Road r1025 = new Road("E",s522);
		Road r1026 = new Road("NS",fakeSto);
		
		Road r1100 = new Road("NS",fakeSto);
		Road r1101 = new Road("",fakeSto);
		Road r1102 = new Road("",fakeSto);
		Road r1103 = new Road("",fakeSto);
		Road r1104 = new Road("",fakeSto);
		Road r1105 = new Road("",fakeSto);
		Road r1106 = new Road("",fakeSto);
		Road r1107 = new Road("",fakeSto);
		Road r1108 = new Road("",fakeSto);
		Road r1109 = new Road("",fakeSto);
		Road r1110 = new Road("",fakeSto);
		Road r1111 = new Road("",fakeSto);
		Road r1112 = new Road("",fakeSto);
		Road r1113 = new Road("",fakeSto);
		Road r1114 = new Road("NS",fakeSto);
		Road r1115 = new Road("",fakeSto);
		Road r1116 = new Road("",fakeSto);
		Road r1117 = new Road("",fakeSto);
		Road r1118 = new Road("",fakeSto);
		Road r1119 = new Road("",fakeSto);
		Road r1120 = new Road("",fakeSto);
		Road r1121 = new Road("",fakeSto);
		Road r1122 = new Road("",fakeSto);
		Road r1123 = new Road("",fakeSto);
		Road r1124 = new Road("",fakeSto);
		Road r1125 = new Road("",fakeSto);
		Road r1126 = new Road("NS",fakeSto);
		
		Road r1200 = new Road("NS",fakeSto);
		Road r1201 = new Road("E",fakeSto);
		Road r1202 = new Road("E",fakeSto);
		Road r1203 = new Road("E",fakeSto);
		Road r1204 = new Road("E",fakeSto);
		Road r1205 = new Road("E",fakeSto);
		Road r1206 = new Road("E",fakeSto);
		Road r1207 = new Road("E",fakeSto);
		Road r1208 = new Road("E",fakeSto);
		Road r1209 = new Road("E",fakeSto);
		Road r1210 = new Road("E",fakeSto);
		Road r1211 = new Road("E",fakeSto);
		Road r1212 = new Road("E",fakeSto);
		Road r1213 = new Road("E",fakeSto);
		Road r1214 = new Road("NS",fakeSto);
		Road r1215 = new Road("W",fakeSto);
		Road r1216 = new Road("W",fakeSto);
		Road r1217 = new Road("W",fakeSto);
		Road r1218 = new Road("W",fakeSto);
		Road r1219 = new Road("W",fakeSto);
		Road r1220 = new Road("W",fakeSto);
		Road r1221 = new Road("W",fakeSto);
		Road r1222 = new Road("W",fakeSto);
		Road r1223 = new Road("W",fakeSto);
		Road r1224 = new Road("W",fakeSto);
		Road r1225 = new Road("W",fakeSto);
		Road r1226 = new Road("NS",fakeSto);
		
		Road r1300 = new Road("NS",fakeSto);
		Road r1301 = new Road("E",fakeSto);
		Road r1302 = new Road("E",fakeSto);
		Road r1303 = new Road("E",fakeSto);
		Road r1304 = new Road("E",fakeSto);
		Road r1305 = new Road("E",fakeSto);
		Road r1306 = new Road("E",fakeSto);
		Road r1307 = new Road("E",fakeSto);
		Road r1308 = new Road("E",fakeSto);
		Road r1309 = new Road("E",fakeSto);
		Road r1310 = new Road("E",fakeSto);
		Road r1311 = new Road("E",fakeSto);
		Road r1312 = new Road("E",fakeSto);
		Road r1313 = new Road("E",fakeSto);
		Road r1314 = new Road("NS",fakeSto);
		Road r1315 = new Road("W",fakeSto);
		Road r1316 = new Road("W",fakeSto);
		Road r1317 = new Road("W",fakeSto);
		Road r1318 = new Road("W",fakeSto);
		Road r1319 = new Road("W",fakeSto);
		Road r1320 = new Road("W",fakeSto);
		Road r1321 = new Road("W",fakeSto);
		Road r1322 = new Road("W",fakeSto);
		Road r1323 = new Road("W",fakeSto);
		Road r1324 = new Road("W",fakeSto);
		Road r1325 = new Road("W",fakeSto);
		Road r1326 = new Road("NS",fakeSto);
		//道路初始化完成
		
		//仓位绑定道路
		s00.road = r11;
		s01.road = r12;
		s02.road = r13;
		s03.road = r14;
		s04.road = r15;
		s05.road = r16;
		s06.road = r17;
		s07.road = r18;
		s08.road = r19;
		s09.road = r110;
		s010.road = r111;
		s011.road = r112;
		
		s10.road = r21;
		s11.road = r22;
		s12.road = r23;
		s13.road = r24;
		s14.road = r25;
		s15.road = r26;
		s16.road = r27;
		s17.road = r28;
		s18.road = r29;
		s19.road = r210;
		s110.road = r211;
		s111.road = r212;
		
		s20.road = r51;
		s21.road = r52;
		s22.road = r53;
		s23.road = r54;
		s24.road = r55;
		s25.road = r56;
		s26.road = r57;
		s27.road = r58;
		s28.road = r59;
		s29.road = r510;
		s210.road = r511;
		s211.road = r512;
		s212.road = r515;
		s213.road = r516;
		s214.road = r517;
		s215.road = r518;
		s216.road = r519;
		s217.road = r520;
		s218.road = r521;
		s219.road = r522;
		s220.road = r523;
		s221.road = r524;
		s222.road = r525;
		
		s30.road = r61;
		s31.road = r62;
		s32.road = r63;
		s33.road = r64;
		s34.road = r65;
		s35.road = r66;
		s36.road = r67;
		s37.road = r68;
		s38.road = r69;
		s39.road = r610;
		s310.road = r611;
		s311.road = r612;
		s312.road = r615;
		s313.road = r616;
		s314.road = r617;
		s315.road = r618;
		s316.road = r619;
		s317.road = r620;
		s318.road = r621;
		s319.road = r622;
		s320.road = r623;
		s321.road = r624;
		s322.road = r625;
		
		s40.road = r91;
		s41.road = r92;
		s42.road = r93;
		s43.road = r94;
		s44.road = r95;
		s45.road = r96;
		s46.road = r97;
		s47.road = r98;
		s48.road = r99;
		s49.road = r910;
		s410.road = r911;
		s411.road = r912;
		s412.road = r915;
		s413.road = r916;
		s414.road = r917;
		s415.road = r918;
		s416.road = r919;
		s417.road = r920;
		s418.road = r921;
		s419.road = r922;
		s420.road = r923;
		s421.road = r924;
		s422.road = r925;
		
		s50.road = r1001;
		s51.road = r1002;
		s52.road = r1003;
		s53.road = r1004;
		s54.road = r1005;
		s55.road = r1006;
		s56.road = r1007;
		s57.road = r1008;
		s58.road = r1009;
		s59.road = r1010;
		s510.road = r1011;
		s511.road = r1012;
		s512.road = r1015;
		s513.road = r1016;
		s514.road = r1017;
		s515.road = r1018;
		s516.road = r1019;
		s517.road = r1020;
		s518.road = r1021;
		s519.road = r1022;
		s520.road = r1023;
		s521.road = r1024;
		s522.road = r1025;
		//绑定完成
		
		//初始化仓位矩阵
		Storage [][] allStorage;
		allStorage = new Storage[][] {
			{s00,s01,s02,s03,s04,s05,s06,s07,s08,s09,s010,s011,s012,s013,s014,s015,s016,s017,s018,s019,s020,s021,s022},
			{s10,s11,s12,s13,s14,s15,s16,s17,s18,s19,s110,s111,s112,s113,s114,s115,s116,s117,s118,s119,s120,s121,s122},
			{s20,s21,s22,s23,s24,s25,s26,s27,s28,s29,s210,s211,s212,s213,s214,s215,s216,s217,s218,s219,s220,s221,s222},
			{s30,s31,s32,s33,s34,s35,s36,s37,s38,s39,s310,s311,s312,s313,s314,s315,s316,s317,s318,s319,s320,s321,s322},
			{s40,s41,s42,s43,s44,s45,s46,s47,s48,s49,s410,s411,s412,s413,s414,s415,s416,s417,s418,s419,s420,s421,s422},
			{s50,s51,s52,s53,s54,s55,s56,s57,s58,s59,s510,s511,s512,s513,s514,s515,s516,s517,s518,s519,s520,s521,s522}
		};
		//初始化道路矩阵
		Road [][] allRoad;
		allRoad = new Road[][] {
			{r00,r01,r02,r03,r04,r05,r06,r07,r08,r09,r010,r011,r012,r013,r014,r015,r016,r017,r018,r019,r020,r021,r022,r023,r024,r025,r026},
			{r10,r11,r12,r13,r14,r15,r16,r17,r18,r19,r110,r111,r112,r113,r114,r115,r116,r117,r118,r119,r120,r121,r122,r123,r124,r125,r126},
			{r20,r21,r22,r23,r24,r25,r26,r27,r28,r29,r210,r211,r212,r213,r214,r215,r216,r217,r218,r219,r220,r221,r222,r223,r224,r225,r226},
			{r30,r31,r32,r33,r34,r35,r36,r37,r38,r39,r310,r311,r312,r313,r314,r315,r316,r317,r318,r319,r320,r321,r322,r323,r324,r325,r326},
			{r40,r41,r42,r43,r44,r45,r46,r47,r48,r49,r410,r411,r412,r413,r414,r415,r416,r417,r418,r419,r420,r421,r422,r423,r424,r425,r426},
			{r50,r51,r52,r53,r54,r55,r56,r57,r58,r59,r510,r511,r512,r513,r514,r515,r516,r517,r518,r519,r520,r521,r522,r523,r524,r525,r526},
			{r60,r61,r62,r63,r64,r65,r66,r67,r68,r69,r610,r611,r612,r613,r614,r615,r616,r617,r618,r619,r620,r621,r622,r623,r624,r625,r626},
			{r70,r71,r72,r73,r74,r75,r76,r77,r78,r79,r710,r711,r712,r713,r714,r715,r716,r717,r718,r719,r720,r721,r722,r723,r724,r725,r726},
			{r80,r81,r82,r83,r84,r85,r86,r87,r88,r89,r810,r811,r812,r813,r814,r815,r816,r817,r818,r819,r820,r821,r822,r823,r824,r825,r826},
			{r90,r91,r92,r93,r94,r95,r96,r97,r98,r99,r910,r911,r912,r913,r914,r915,r916,r917,r918,r919,r920,r921,r922,r923,r924,r925,r926},
			{r1000,r1001,r1002,r1003,r1004,r1005,r1006,r1007,r1008,r1009,r1010,r1011,r1012,r1013,r1014,r1015,r1016,r1017,r1018,r1019,r1020,r1021,r1022,r1023,r1024,r1025,r1026},
			{r1100,r1101,r1102,r1103,r1104,r1105,r1106,r1107,r1108,r1109,r1110,r1111,r1112,r1113,r1114,r1115,r1116,r1117,r1118,r1119,r1120,r1121,r1122,r1123,r1124,r1125,r1126},
			{r1200,r1201,r1202,r1203,r1204,r1205,r1206,r1207,r1208,r1209,r1210,r1211,r1212,r1213,r1214,r1215,r1216,r1217,r1218,r1219,r1220,r1221,r1222,r1223,r1224,r1225,r1226},
			{r1300,r1301,r1302,r1303,r1304,r1305,r1306,r1307,r1308,r1309,r1310,r1311,r1312,r1313,r1314,r1315,r1316,r1317,r1318,r1319,r1320,r1321,r1322,r1323,r1324,r1325,r1326}
		};
		
		//开始测试
//		System.out.println("开始测试");
//		OrderList newOrderList1 = new OrderList("HRB400","20*7",5,650);
//		OrderList newOrderList2 = new OrderList("HRB400","32*12",15,510);
//		OrderList newOrderList3 = new OrderList("HRB400","12*9",34,59.772);
//		for(int i=0; i<newOrderList1.cars; i++){
//			Car newCar = new Car(newOrderList1.weight/newOrderList1.cars, newOrderList1, i,allStorage, allRoad);
//			newCar.start();
//		}
//		for(int i=0; i<newOrderList2.cars; i++){
//			Car newCar = new Car(newOrderList2.weight/newOrderList2.cars, newOrderList2, i,allStorage, allRoad);
//			newCar.start();
//		}
//		for(int i=0; i<newOrderList3.cars; i++){
//			Car newCar = new Car(newOrderList3.weight/newOrderList3.cars, newOrderList3, i,allStorage, allRoad);
//			newCar.start();
//		}
		
//		Date a = new Date();
//		
//		for(int i=0;i<100;i++) {
//			System.out.print("hhhhhhhhhhhhhhhhhh");
//		}
//		
//		Date b = new Date();
//		long diff = b.getTime()-a.getTime();
//		System.out.println("");
//		System.out.println(diff);
		
		
		File file = new File("C:\\Users\\lenovo\\Desktop\\新建文件夹\\outer.xls");
		// 创建输入流，读取Excel
	    InputStream is = new FileInputStream(file.getAbsolutePath());
	    // jxl提供的Workbook类
	    Workbook wb = Workbook.getWorkbook(is);
	    // 只有一个sheet,直接处理
	    //创建一个Sheet对象
	    Sheet sheet = wb.getSheet(0);
	    // 得到所有的行数
	    int rows = sheet.getRows();
	    // 所有的数据
	    // 越过第一行 它是列名称
	    
	    double endTime = 0;
	    int order = 0;
	    double totalWeight = 0;
	    boolean stop = false;
	    
	    for(int t=0; t<50; t++){
	    	Date a = new Date();
			boolean finish = false;
			
			do{
				Date b = new Date();
				long diff = b.getTime()-a.getTime();
				if(diff>=20){
					finish = true;
				}
				else{
					finish = false;
				}
			}while(!finish);

	    	for (int j =187+100*t; j <254+100*t; j++) {
		    	

	    		
	    		// 得到每一行的单元格的数据
		        Cell[] cells = sheet.getRow(j);
		        Cell[] cells1 = sheet.getRow(j+1);
		        
		        
		        String today = new String(cells[1].getContents().toString());
		        String str1 = new String(cells[3].getContents().toString());
		        String str2 = new String(cells[4].getContents().toString());
		        int int1 = (int) Double.parseDouble(cells[5].getContents());
		        double dou1 = Double.parseDouble(cells[6].getContents());
		        double dou2 = Double.parseDouble(cells1[6].getContents());
		        
		        
		        
		        OrderList newOrderList = new OrderList(str1,str2,int1,dou1);
		        //System.out.println(cells[3].getContents()+cells[4].getContents()+(int) Double.parseDouble(cells[5].getContents())+Double.parseDouble(cells[6].getContents()));
				
					
					Date a1 = new Date();
					boolean finish1 = false;
					
					do{
						Date b = new Date();
						long diff = b.getTime()-a1.getTime();
						if(diff>=60){
							finish1 = true;
						}
						else{
							finish1 = false;
						}
					}while(!finish1);
					
					totalWeight = totalWeight + newOrderList.weight;
					if(totalWeight + dou2 >50){
						stop = true;
					}
					Car newCar = new Car((-newOrderList.weight), newOrderList, 0, endTime, allStorage, allRoad, stop);
					totalWeight = 0;
					stop = false;
					newCar.start();
					endTime = endTime + order*1;
					order = order + 1;

		    }
	    }		
	}
		
}












