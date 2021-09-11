package ParkinglotManagement;

import javax.swing.JOptionPane;

abstract class Car{
	Time t = new Time();
	
	int year;
	int month;
	int day;
	int time, ptime;
	int minute, pminute;
	
	int minsum;
	int type;
	
	String num,msg;
	
	public Car(String n, int y, int mo, int d, int t, int mi){
		num=n; year=y;
		month=mo; day=d;
		time=t; minute=mi;
		minsum=this.t.makeMin(y,mo,d,t,mi);
	}
	
	public int getType() {
		return type;
	}
	
	public String getNum() {
		return num;
	}
	
	public int getMinSum() {
		return minsum;
	}
	
	public void show(int y, int m, int d, int h, int mn) {
		msg = String.format(num + "번 입차시간: " + year + "-" + month + "-" + day + " " + time + ":" + minute);		
		JOptionPane.showMessageDialog(null, msg, "차량 정보", JOptionPane.INFORMATION_MESSAGE);
	}
	
	public void showFee(int fee, int minsum2) {
		ptime=(minsum2-minsum)/60;
		pminute=(minsum2-minsum)%60;
		msg = String.format("**"+num+"번 차량의 요금 합산"+"**");
		JOptionPane.showMessageDialog(null, msg, "출차 완료", JOptionPane.INFORMATION_MESSAGE);
		msg = String.format("주차요금: "+fee+"원\n주차시간: "+ptime+"시간 "+pminute+"분");
		JOptionPane.showMessageDialog(null, msg, "출차 완료", JOptionPane.INFORMATION_MESSAGE);
	}
	
	public String infotoStr(int y, int m, int d, int h, int mn) {
		return msg = String.format(num + "번 입차시간: " + year + "-" + month + "-" + day + " " + time + ":" + minute);
	}
	
	public String getTime() {
		return year+"/"+month+"/"+day+"/"+time+"/"+minute;
	}
	
	abstract String getTypestr();
	abstract String getInfo(int y, int m, int d, int h, int mn);
	
	@Override 
	public boolean equals(Object o){
        if (!(o instanceof Car))
            return false; 
        Car c = (Car) o;
        return c.num.equals(this.num);
    }
}

class psCar extends Car{
	private int Emission; 
	private int type; 
	
	public psCar(String n, int y, int mo, int d, int t, int mi, int em) {
		super(n,y,mo,d,t,mi);
		Emission=em;
		type=1;
	}
	
	@Override
	public int getType() {
		return this.type;
	}
	
	public int getEmission() {
		return Emission;
	}
	
	public void show(int y, int m, int d, int h, int mn) {
		super.show(y,m,d,h,mn);
		msg="차량 종류: "+getTypestr()+"\n"+getInfo(y,m,d,h,mn);
		JOptionPane.showMessageDialog(null, msg, "차량 조회", JOptionPane.INFORMATION_MESSAGE);
	}
	
	public String getTypestr() {
		return "승용차";
	}
	
	public String getInfo(int y, int m, int d, int h, int mn) {
		return "배기량: "+ Emission;
	}
	
	public String infotoStr(int y, int m, int d, int h, int mn) {
		return msg = String.format(num + "번 입차시간: " + year + "-" + month + "-" + day + " " + time + ":" + minute);
	}
}

class ecCar extends Car{
	private double capacity;
	private double charge;
	private int chargefee;
	private int type;
	
	public ecCar(String n, int y, int mo, int d, int t, int mi, double cp){
		super(n,y,mo,d,t,mi);
		capacity=cp;
		type=2;
	}
	
	public double getCapacity() {
		return capacity;
	}
	
	@Override
	public int getType() {
		return this.type;
	}
	
	public String getTypestr() {
		return "전기차";
	}
	
	public String getInfo(int y, int m, int d, int h, int mn) {
		charge=capacity+(t.makeMin(y, m, d, h, mn)-minsum)*0.5;
		if(charge>60)
			charge=60;
		return "배터리량: "+charge;
	}
	
	public void show(int y, int m, int d, int h, int mn) {
		charge=capacity+(t.makeMin(y, m, d, h, mn)-minsum)*0.5;
		if(charge>=60)
			charge=60;
		double chargem = charge-capacity;
		super.show(y,m,d,h,mn);
		msg = "차량 종류: "+getTypestr()+"\n충전: "+charge+"KW("+chargem+"KW 충전)";
		JOptionPane.showMessageDialog(null, msg, "출차 완료", JOptionPane.INFORMATION_MESSAGE);
	}
	
	@Override
	public void showFee(int fee, int minsum2) {
		charge=((minsum2-minsum)*0.5);
		if(60-capacity<=charge)
			charge=60-capacity;
		chargefee=(int)(charge/0.5)*200;
		super.showFee(fee-chargefee, minsum2);
		msg = String.format("충전요금: %d원(현재 충전량: %.1fKW, 충전량: %.1fKW)\n",chargefee, capacity+charge, charge);
		JOptionPane.showMessageDialog(null, msg, "출차 완료", JOptionPane.INFORMATION_MESSAGE);
		msg = String.format("\n총 요금: "+(fee)+"원");
		JOptionPane.showMessageDialog(null, msg, "출차 완료", JOptionPane.INFORMATION_MESSAGE);
	}
	
	public String infotoStr(int y, int m, int d, int h, int mn) {
		return msg = String.format(num + "번 입차시간: " + year + "-" + month + "-" + day + " " + time + ":" + minute);
	}
}

class Van extends Car{
	private int size; //1: 대 2:중 3:소
	private int type;
	
	public Van(String n, int y, int mo, int d, int t, int mi, int s){
		super(n,y,mo,d,t,mi);
		size=s;
		type=3;
	}
	
	public int getSize() {
		return size;
	}
	
	@Override
	public int getType() {
		return this.type;
	}
	
	public void show(int y, int m, int d, int h, int mn) {
		super.show(y,m,d,h,mn);
		msg="차량 종류: "+getTypestr()+"\n"+getInfo(y,m,d,h,mn);
		JOptionPane.showMessageDialog(null, msg, "차량 정보 조회", JOptionPane.INFORMATION_MESSAGE);
	}
	
	public String getInfo(int y, int m, int d, int h, int mn) {
		if(size==1)
			return "차량 크기: 대형";
		else if(size==2)
			return "차량 크기: 중형";
		else
			return "차량 크기: 소형";
	}
	
	public String getTypestr() {
		return "밴";
	}
	
	public String infotoStr(int y, int m, int d, int h, int mn) {
		return msg = String.format(num + "번 입차시간: " + year + "-" + month + "-" + day + " " + time + ":" + minute);
	}
}