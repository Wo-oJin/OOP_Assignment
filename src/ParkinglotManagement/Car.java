
package ParkinglotManagement;

abstract class Car{
	Time t = new Time();
	
	protected int year;
	protected int month;
	protected int day;
	protected int time, ptime;
	protected int minute, pminute;
	
	protected int minsum;
	protected int type;
	
	protected String num;
	
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
		System.out.print(num + "번 입차시간: " + year + "-" + month + "-" + day + " " + time + ":" + minute);
	}
	
	public void showFee(int fee, int minsum2) {
		ptime=(minsum2-minsum)/60;
		pminute=(minsum2-minsum)%60;
		System.out.println("**"+num+"번 차량의 요금 합산"+"**");
		System.out.print("주차요금: "+fee+"원");
		System.out.printf("(주차시간 %d시간 %d분)\n",ptime,pminute);
	}
	
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
		System.out.print("승용차 ");
		super.show(y,m,d,h,mn);
		System.out.println(" 배기량: "+Emission);
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
	
	public void show(int y, int m, int d, int h, int mn) {
		charge=capacity+(t.makeMin(y, m, d, h, mn)-minsum)*0.5;
		if(charge>=60)
			charge=60;
		double chargem = charge-capacity;
		System.out.print("전기차 ");
		super.show(y,m,d,h,mn);
		System.out.println(" 충전: "+charge+"KW("+chargem+"KW 충전)");
	}
	
	@Override
	public void showFee(int fee, int minsum2) {
		charge=((minsum2-minsum)*0.5);
		if(60-capacity<=charge)
			charge=60-capacity;
		chargefee=(int)(charge/0.5)*200;
		super.showFee(fee-chargefee, minsum2);
		System.out.printf("충전요금: %d원(현재 충전량: %.1fKW, 충전량: %.1fKW)\n",chargefee, capacity+charge, charge);
		System.out.println("\n총 요금: "+(fee)+"원");
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
		System.out.print("밴 ");
		super.show(y,m,d,h,mn);
		if(size==1)
			System.out.println(" 종류: 대형");
		else if(size==2)
			System.out.println(" 종류: 중형");
		else
			System.out.println(" 종류: 소형");
	}
}