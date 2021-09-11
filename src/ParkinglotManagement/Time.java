package ParkinglotManagement;
import java.util.GregorianCalendar;

class Time {
	
	public boolean dayValid(int yy, int mm, int dd){
		if(yy>=1 && (1<=mm && mm<=12)) {
			if(mm==2) {
				GregorianCalendar gc= new GregorianCalendar();
				if(gc.isLeapYear(yy)) {
					if(1<=dd && dd<=29)
						return true;
					else
						return false;
				}
				else {
					if(1<=dd && dd<=28)
						return true;
					else
						return false;
				}
			}
			else if(mm==1 || mm==3 || mm==5 || mm==7 || mm==8 || mm==10 || mm==12) {
				if(1<=dd && dd<=31)
					return true;
				else
					return false;
			}
			else {
				if(1<=dd && dd<=30)
					return true;
				else
					return false;
			}
		}
		else
			return false;
	}
	
	public boolean timeValid(int hour, int minute) {
		if(0<=hour && hour<=23) {
			if(0<=minute && minute<=59)
				return true;
			else
				return false;
		}
		else
			return false;
	}
	
	public int makeMin(int yy, int mm, int dd, int hour, int min) {
		int i,cnt=0,sum=0;
		GregorianCalendar gc= new GregorianCalendar();
		for(i=1;i<=mm-1;i++) {
			if(i==2){
				if(gc.isLeapYear(yy))
					sum+=29*24*60;
				else
					sum+=28*24*60;
			}
			else if(i==1 || i==3 || i==5 || i==7 || i==8 || i==10 || i==12)
				sum+=31*24*60;
			else
				sum+=30*24*60;
		}
		for(i=1;i<=yy-1;i++) {
			if(gc.isLeapYear(i))
				cnt++;
		}
		sum+=((yy-cnt)*365*24*60 + cnt*366*24*60 + dd*24*60 + hour*60 +min);
		return sum;
	}
}