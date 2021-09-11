package ParkinglotManagement;

public class ParkingTest{
	
	public static void main(String[] args) {
		ParkingSystem ps = new ParkingSystem();
		
		boolean flag=true;
		
		ps.inputMax();
		
		while(flag) {
			flag=ps.operate();
		}
	}
}