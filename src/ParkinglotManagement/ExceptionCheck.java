package ParkinglotManagement;
import java.util.Scanner;

import javax.swing.JOptionPane;

public class ExceptionCheck {
	Parkinglot pl;
	Time t=new Time();
	Scanner sc = new Scanner(System.in);
	
	public ExceptionCheck(Parkinglot pl) {
		this.pl=pl;
	}
	
	public boolean duplicateCheck(int type, Car car){
		if(pl.findCar(car.getNum())==-1) {
			if(pl.findleaveCar(car)) {
				JOptionPane.showMessageDialog(null, "차량번호 중복!", "등록 오류", JOptionPane.WARNING_MESSAGE);
				return true;
			}
			else
				return false;
		}
		else {
			JOptionPane.showMessageDialog(null, "차량번호 중복!", "등록 오류", JOptionPane.WARNING_MESSAGE);
			return true;
		}
	}

	public boolean carnumCheck(String num) {
		if(num.length()!=4) {
			JOptionPane.showMessageDialog(null, "잘못된 범위의 차량번호 입력!", "차량번호 오류", 
					JOptionPane.WARNING_MESSAGE);
			return true;
		}
		else
			return false;
	}
	
	public boolean kindCheck(char kind) {
		if(kind !='g' && kind !='e' && kind !='v') {
			JOptionPane.showMessageDialog(null, "잘못된 차종 입력!", "등록 오류", 
					JOptionPane.WARNING_MESSAGE);
			return true;
		}
		else
			return false;
	}
		
	public boolean sequenceCheck(String cmd, int nowtime) {
		if(cmd.equals("leave") | cmd.equals("enter")) {
			if(pl.lastmove+1 <= nowtime) {
				if(pl.lasttime<=nowtime)
					return false;
				else {
					JOptionPane.showMessageDialog(null, "잘못된 시간 입력! 전 명령 시간보다 빠른 시간을 입력하실 수 없습니다.", "입력 시간 오류", JOptionPane.WARNING_MESSAGE);
					return true;
				}
			}
			else {
				JOptionPane.showMessageDialog(null, "잘못된 시간 입력! 전 입/출 명령시간보다 최소 1분 이후에 명령하실 수 있습니다.", "입력 시간 오류", JOptionPane.WARNING_MESSAGE);
				return true;
			}
		}
		else if(cmd.equals("show") | cmd.equals("list")) {
			if(pl.lasttime<=nowtime)
				return false;
			else {
				JOptionPane.showMessageDialog(null, "잘못된 시간 입력! 전 명령 시간보다 빠른 시간을 입력하실 수 없습니다.", "입력 시간 오류", JOptionPane.WARNING_MESSAGE);
				return true;
			}
		}
		else
			return false;
	}
	
	public boolean emCheck(int em) {
		if(em<=0) {
			JOptionPane.showMessageDialog(null, "배기량은 0보다 커야합니다", "등록 오류", JOptionPane.WARNING_MESSAGE);
			return true;
		}
		else
			return false;
	}
	
	public boolean cpCheck(double cp) {
		if(cp<0 || 60<cp) {
			JOptionPane.showMessageDialog(null, "배터리 충전량은 0 이상 60 이하의 값이 되어야합니다.", "등록 오류", JOptionPane.WARNING_MESSAGE);
			return true;
		}
		return false;
	}
	
	public boolean sizeCheck(int size) {
		if(size!=1 && size!=2 && size!=3) {
			JOptionPane.showMessageDialog(null, "크기는 1,2,3 중 하나로 선택하셔야 합니다", "등록 오류", JOptionPane.WARNING_MESSAGE);
			return true;
		}
		return false;
	}
	
	public boolean inputnullcheck(String s) {
		if(s.equals("")) {
			JOptionPane.showMessageDialog(null, "빈칸이 있습니다. 확인해 주신 후 다시 입력하세요.", "입력 오류", JOptionPane.WARNING_MESSAGE);
			return true;
		}
		else
			return false;
	}
	
	public boolean incometimeCheck(int y, int m, int d) {
		if(t.makeMin(y,m,0,0,0)<=t.makeMin(pl.beforeyear,  pl.beforemonth, 0,0,0)) {
			if(d>pl.beforeday) {
				JOptionPane.showMessageDialog(null, "입력하신 날짜의 소득은 알 수 없습니다.", "입력 오류",JOptionPane.WARNING_MESSAGE);
				return true;
			}
			else
				return false;
		}
		else {
			JOptionPane.showMessageDialog(null, "입력하신 날짜의 소득은 알 수 없습니다.", "입력 오류",JOptionPane.WARNING_MESSAGE);
			return true;
		}
	}
}