package ParkinglotManagement;
import java.util.ArrayList;

public class Parkinglot {
	
	private Car carList[];
	private ArrayList<Car> leaveCarList;
	private int income[][][];
	private int cnt;
	
	public Parkinglot() {
		income=new int[10000][13][32];
		for(int i=0;i<10000;i++) {
			for(int k=0;k<13;k++) {
				for(int j=0;j<32;j++)
					income[i][k][j]=0;
			}
		}
		leaveCarList=new ArrayList<Car>();
	}

	void setMax(int max) {
		this.carList=new Car[max];
	}
	
	void setleaveList(Car car) {
		for(Car c: leaveCarList) {
			if(car.getNum().equals(c.getNum()))
				return;
		}
		leaveCarList.add(car);	
	}
	
	void setincome(int fee,int y, int m, int d) {
		income[y][m][d]+=fee;
	}
	
	int getincome(int y, int m,int d) {
		return income[y][m][d];
	}
	
	int getcnt() {
		return cnt;
	}
	
	void setList(Car car) {
		carList[cnt]=car;
		cnt++;
	}
	
	Car getList(int index) {
		return carList[index];
	}
	
	boolean findleaveCar(Car car){
		for(Car lc: leaveCarList) {
			if(car.equals(lc)) {
				if(car.getType()==lc.getType()) {
					if(car.getType()==1) {
						if(((psCar)car).getEmission() == ((psCar)lc).getEmission())
							return false;
						else 
							return true;
					}
					else if(car.getType() == 3) {
						if(((Van)car).getSize() == ((Van)lc).getSize())
							return false;
						else
							return true;
					}
					else
						return false;
					}
				else
					return true;
				}
			}
		return false;
	}
	
	int findCar(String num) {
		for(int i=0;i<cnt;i++) {
			if(num.equals(carList[i].getNum()))
				return i;
		}
		return -1;
	}
	
	int psCarCalculate(int Em, int minsum1, int minsum2) {
		int result=minsum2-minsum1;
		if(Em<1000) {
			if(result<=30)
				return 250;
			else {
				result-=30;
				if(result%10==0)
					return (result/10)*50+250;
				else
					return (result/10+1)*50+250;
			}
		}
		else{
			if(result<=30)
				return 250;
			else {
				result-=30;
				if(result%10==0)
					return (result/10)*100+500;
				else
					return (result/10+1)*100+500;
			}
		}
	}
	
	int EcCarCalculate(double cp, int minsum1, int minsum2) {
		int result=minsum2-minsum1;
		int sum=0;
		int needchargingT=(int)((60-cp)/0.5);
		
		if(result<=30)
			sum+=500;
		else {
			if(result%10==0)
				sum+= ((result-30)/10)*100+500;
			else
				sum+= ((result-30)/10+1)*100+500;
		}
		if(result>=needchargingT)
			return sum+200*needchargingT;
		else
			return sum+200*result;
	}
	
	int VanCalculate(int size, int minsum1, int minsum2) {
		int result=minsum2-minsum1;
		if(size==1) {
			if(result%10==0)
				return (result/10)*500;
			else
				return (result/10+1)*500;
		}
		else if(size==2){
			if(result%10==0)
				return (result/10)*300;
			else
				return (result/10+1)*300;
		}
		else if(size==3){
			if(result%10==0)
				return (result/10)*200;
			else
				return (result/10+1)*200;
		}
		else 
			return -1;
	}
	
	void listSort() {
		Car temp;
		for(int i=0;i<cnt;i++) {
			for(int k=0;k<cnt-1-i;k++) {
				
				if(carList[k].getType()>carList[k+1].getType()) {
					temp=carList[k];
					carList[k]=carList[k+1];
					carList[k+1]=temp;
				}
				else if(carList[k].getType()==carList[k+1].getType()) {
					if(carList[k].getMinSum()>carList[k].getMinSum()) {
						temp=carList[k];
						carList[k]=carList[k+1];
						carList[k+1]=temp;
					}
				}
			}
		}
	}
	
	void reSorting(int index) {
		for(int i=index;i<cnt-1;i++)
			carList[i]=carList[i+1];
		cnt--;
	}
}