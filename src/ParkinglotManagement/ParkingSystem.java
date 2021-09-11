package ParkinglotManagement;
import java.util.Scanner;
import java.util.StringTokenizer;

class ParkingSystem {
	static Parkinglot pl = new Parkinglot();
	ExceptionCheck ek = new ExceptionCheck(pl);
	Time t = new Time();
	Scanner sc = new Scanner(System.in);
	
	int max, flag=0;
	int lastmove=0, lasttime=0;
	
	int year=0;
	int month=0; int day=0;
	int time=-1; int minute=0;
	
	String num=null;
	String cmd=null;
	String kind=null;
	
	public void inputMax(){ 
		System.out.printf("주차장의 최대 수용 차수 입력: ");
		max=sc.nextInt();
		max=ek.maxCheck(max);
		pl.setMax(max);
		System.out.println("주차장에 수용 가능한 차량 수: "+max+"대로 설정 완료");
		sc.nextLine();
	}
	
	public boolean operate() {
		int em = 0, size,cmdcnt=0,index=0;
		double cp;
		int minsum1, minsum2, fee=0;
		int beforeyear=0, beforemonth=0, beforeday=0;
		String command;
		StringTokenizer st;
		
		while(true) {
			System.out.println("\n명령 입력(행위, 차량종류, 차량번호, 입차시간, 선택사항): ");
			command = sc.nextLine();
			st = new StringTokenizer(command);
			cmdcnt=st.countTokens();
		
			cmd=st.nextToken();
		
			if(ek.commandCheck(cmd)) 
				continue;
			if(ek.totalCheck(cmd, cmdcnt))
				continue;

			if(cmd.equals("enter")) {
				if(ek.lotCheck(cmd, max)) 
					continue;
				kind=st.nextToken();
				num=st.nextToken(); year=Integer.valueOf(st.nextToken());
				month=Integer.valueOf(st.nextToken()); day=Integer.valueOf(st.nextToken());
				time=Integer.valueOf(st.nextToken()); minute=Integer.valueOf(st.nextToken());
				if(ek.kindCheck(kind.charAt(0))) 
					continue;
			
				if(ek.carnumCheck(num)) 
					continue;
			
				if(ek.sequenceCheck2(cmd, lastmove, lasttime,t.makeMin(year,month,day,time,minute))) 
					continue;
			
				if(!t.dayValid(year,month,day) || !t.timeValid(time,minute)) {
					System.out.println("잘못된 시간 형식 입력!");
					continue;
				}
				switch(kind.charAt(0)) {
					case 'g':
						em=Integer.valueOf(st.nextToken());
						if(ek.emCheck(em))
							continue;
						psCar pc = new psCar(num,year,month,day,time,minute,em);
						if(ek.duplicateCheck(pc.getType(), pc)) 
							continue;
						pl.setList(pc);
						break;
					case 'e':
						cp=Double.valueOf(st.nextToken());
						if(ek.cpCheck(cp))
							continue;
						ecCar ec = new ecCar(num,year,month,day,time,minute,cp);
						if(ek.duplicateCheck(ec.getType(), ec)) 
							continue;
						pl.setList(ec);
						break;
					case 'v':
						size=Integer.valueOf(st.nextToken());
						if(ek.sizeCheck(size))
							continue;
						Van v = new Van(num,year,month,day,time,minute,size);
						if(ek.duplicateCheck(v.getType(), v)) 
							continue;
						pl.setList(v);
					}
				}
		
			else if(cmd.equals("leave")) { 
				if(ek.lotCheck(cmd, max))
					continue;
				num=st.nextToken(); year=Integer.valueOf(st.nextToken());
				month=Integer.valueOf(st.nextToken()); day=Integer.valueOf(st.nextToken());
				time=Integer.valueOf(st.nextToken()); minute=Integer.valueOf(st.nextToken());
			
				if(ek.carnumCheck(num)) 
					continue;
				
				if(!t.dayValid(year,month,day) || !t.timeValid(time,minute)) {
					System.out.println("잘못된 시간 형식 입력!");
					continue;
				}
			
				if(ek.sequenceCheck2(cmd, lastmove, lasttime,t.makeMin(year,month,day,time,minute))) 
					continue;
			
				index=pl.findCar(num);
				if(index==-1) {
					System.out.println("입력한 번호에 맞는 차량이 주차되어 있지 않음");
					continue;
				}
				minsum1=pl.getList(index).getMinSum();
				minsum2=t.makeMin(year,month,day,time,minute);
			
				switch(pl.getList(index).getType()) {
				case 1:
					psCar pc=(psCar)(pl.getList(index));
					fee=pl.psCarCalculate(pc.getEmission(),minsum1,minsum2);
					pl.getList(index).showFee(fee, minsum2);
					pl.setincome(fee,year,month,day);
					break;
				case 2:
					ecCar c=(ecCar)pl.getList(index);
					cp=c.getCapacity();
					fee=pl.EcCarCalculate(cp, minsum1, minsum2);
					c.showFee(fee, minsum2);
					pl.setincome(fee,year,month,day);
					break;
				case 3:
					Van v=(Van)pl.getList(index);
					size=v.getSize();
					fee=pl.VanCalculate(size, minsum1, minsum2);
					v.showFee(fee, minsum2);
					pl.setincome(fee,year,month,day);
				}
				pl.setleaveList(pl.getList(index));
				pl.reSorting(index);
			}
		
			else if(cmd.equals("show")){
				if(ek.lotCheck(cmd, max))
					continue;
			
				num=st.nextToken(); year=Integer.valueOf(st.nextToken());
				month=Integer.valueOf(st.nextToken()); day=Integer.valueOf(st.nextToken());
				time=Integer.valueOf(st.nextToken()); minute=Integer.valueOf(st.nextToken());
				
				if(ek.carnumCheck(num)) 
					continue;
				
				if(ek.sequenceCheck(lasttime,t.makeMin(year,month,day,time,minute))) 
					continue;
				
				if(!t.dayValid(year,month,day) || !t.timeValid(time,minute)) {
					System.out.println("잘못된 시간 형식 입력!");
					continue;
				}
				index=pl.findCar(num);
				if(index==-1) {
					System.out.println("입력한 번호에 맞는 차량이 주차되어 있지 않음");
					continue;
				}
				pl.getList(index).show(year,month,day,time,minute);
			}
		
			else if(cmd.equals("list")){
				if(ek.lotCheck(cmd, max))
					continue;
			
				year=Integer.valueOf(st.nextToken());
				month=Integer.valueOf(st.nextToken()); day=Integer.valueOf(st.nextToken());
				time=Integer.valueOf(st.nextToken()); minute=Integer.valueOf(st.nextToken());
				
				if(!t.dayValid(year,month,day) || !t.timeValid(time,minute)) {
					System.out.println("잘못된 시간 형식 입력!");
					continue;
				}
			
				if(ek.sequenceCheck(lasttime,t.makeMin(year,month,day,time,minute))) 
					continue;
			
				pl.listSort();
				for(int i=0;i<pl.getcnt();i++)
					pl.getList(i).show(year,month,day,time,minute);
			}
		
			else if(cmd.equals("income")) {
				year=Integer.valueOf(st.nextToken());
				month=Integer.valueOf(st.nextToken()); day=Integer.valueOf(st.nextToken());
				if(!t.dayValid(year,month,day)) {
					System.out.println("잘못된 시간 형식 입력!");
					continue;
				}
				if(year>beforeyear || month>beforemonth || day>beforeday){
					System.out.println("입력하신 날짜의 소득은 알 수 없습니다!");
					year=beforeyear; month=beforemonth; day=beforeday;
					continue;
				}
				System.out.printf("총 수입(%d년 %d월 %d일): %d원\n",year,month,day,pl.getincome(year, month, day));
			}
			else if(cmd.equals("finish")) {
				System.out.println("프로그램이 종료됩니다.");
				return false;
			}
			
			if(cmd.equals("income")) 
				continue;
			
			if(cmd.equals("enter") || cmd.equals("leave"))
				lastmove=t.makeMin(year, month, day, time, minute);
			lasttime=t.makeMin(year, month, day, time, minute);
			beforeyear=year; beforemonth=month; beforeday=day;
			//System.out.printf("마지막 명령 시간: %d년 %d월 %d일 %d시 %d분\n",year,month,day,time,minute);
		}
	}
}