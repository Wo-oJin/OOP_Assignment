package ParkinglotManagement;
import java.util.Scanner;

public class ExceptionCheck {
	Parkinglot pl;
	Scanner sc = new Scanner(System.in);
	
	public ExceptionCheck(Parkinglot pl) {
		this.pl=pl;
	}
	
	public boolean totalCheck(String cmd, int n) {
		if(cmd.equals("enter")) {
			if(n!=9) {
				System.out.println("필요한 입력은 총 9개입니다. 다시 확인해주시고 입력해주세요");
				return true;
			}
			else 
				return false;
		}
		else if(cmd.equals("leave")) {
			if(n!=7){
				System.out.println("필요한 입력은 총 7개입니다. 다시 확인해주시고 입력해주세요");
				return true;
			}
			else 
				return false;
		}
		else if(cmd.equals("show")) {
			if(n!=7){
				System.out.println("필요한 입력은 총 7개입니다. 다시 확인해주시고 입력해주세요");
				return true;
			}
			else 
				return false;
		}
		else if(cmd.equals("list")) {
			if(n!=6){
				System.out.println("필요한 입력은 총 6개입니다. 다시 확인해주시고 입력해주세요");
				return true;
			}
			else 
				return false;
		}
		else{
			if(n!=4){
				System.out.println("필요한 입력은 총 4개입니다. 다시 확인해주시고 입력해주세요");
				return true;
			}
			else 
				return false;
		}
	}
	
	public boolean commandCheck(String cmd) {
		if(!cmd.equals("enter") && !cmd.equals("leave") && !cmd.equals("show") && !cmd.equals("list") 
				&& !cmd.equals("income") && !cmd.equals("finish") && !cmd.equals("lasttime")) {
			System.out.println("잘못된 행위 입력!");
			return true;
		}
		else
			return false;
	}
	
	public boolean duplicateCheck(int type, Car car){
		if(pl.findCar(car.getNum())==-1) {
			if(pl.findleaveCar(car)) {
				System.out.println("차량 번호 중복!");
				return true;
			}
			else
				return false;
		}
		else {
			System.out.println("차량 번호 중복!");
			return true;
		}
	}

	public boolean carnumCheck(String num) {
		if(num.length()!=4) {
			System.out.println("잘못된 범위의 차량 번호 입력!");
			return true;
		}
		else
			return false;
	}
	
	public boolean kindCheck(char kind) {
		if(kind !='g' && kind !='e' && kind !='v') {
			System.out.println("잘못된 차종 입력!");
			return true;
		}
		else
			return false;
	}
	
	public boolean sequenceCheck(int lasttime, int nowtime) {
		if(lasttime<=nowtime) 
			return false;
		else {
			System.out.println("잘못된 시간 입력! 이전 명령 시간보다 앞선 시간 명령은 입력하실 수 없습니다.");
			return true;
		}
	}
	
	public boolean sequenceCheck2(String cmd, int movetime, int lasttime, int nowtime) {
		if(cmd.equals("show") || cmd.equals("leave")) {
			if(movetime+1 <= nowtime && lasttime <= nowtime)
				return false;
			else {
				System.out.println("잘못된 시간 입력! 입/출차는 이전 입/출차 시간보다 최소 1분 후에 하실 수 있으며 찾기 입력보다 같거나 늦은 시간에 하셔야 합니다.");
				return true;
			}
		}
		else if(movetime+1 <= nowtime)
			return false;
		else {
			System.out.println("잘못된 시간 입력! 입/출차는 이전 입/출차 시간보다 최소 1분 후에 하실 수 있습니다!");
			return true;
		}
	}
	
	public boolean lotCheck(String cmd, int max) {
		if(!cmd.equals("enter") && pl.getcnt()==0) {
			System.out.println("주차장이 비었습니다.");
			return true;
		}
		else if(cmd.equals("enter") && pl.getcnt()==max) {
			System.out.println("주차장이 꽉찼습니다");
			return true;
		}
		return false;
	}
	
	public boolean emCheck(int em) {
		if(em<=0) {
			System.out.println("배기량은 0보다 커야합니다.");
			return true;
		}
		else
			return false;
	}
	
	public boolean cpCheck(double cp) {
		if(cp<0 || 60<cp) {
			System.out.println("배터리 충전량은 0이상 60이하의 값이 되어야 합니다.");
			return true;
		}
		return false;
	}
	
	public boolean sizeCheck(int size) {
		if(size!=1 && size!=2 && size!=3) {
			System.out.println("크기는 1(대형), 2(중형), 3(소형) 중 하나로 선택해야 합니다. 크기를 다시 입력해주세요");
			return true;
		}
		return false;
	}
	
	public int maxCheck(int max) {
		while(max<=0) {
			System.out.print("주차 가능한 차량은 0대 이상이어야 합니다. 다시 입력해주세요: ");
			max=sc.nextInt();
		}
		return max;
	}
}