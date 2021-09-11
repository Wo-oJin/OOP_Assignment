package ParkinglotManagement;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class ParkingTest extends JFrame {
	public JPanel SEL, ENT, DEP, SHOW, LIST, INCOME;
	public Parkinglot lot = new Parkinglot();
	public ExceptionCheck ek = new ExceptionCheck(lot);
	
	public ParkingTest() {
		int input=0;
		if (lot.getMax() == 0) {
			String msg = "주차장에 수용 가능한 차량의 최대 개수";
			try{
			input = Integer.valueOf(JOptionPane.showInputDialog(msg));
			}
			catch(NumberFormatException e) {
				JOptionPane.showMessageDialog(null, "프로그램을 종료합니다.", "프로그램 종료",JOptionPane.WARNING_MESSAGE);
				System.exit(0);
			}
			while (input <= 0) {
				JOptionPane.showMessageDialog(null, "주차 가능한 차량은 1대 이상이어야 합니다.", "등록 오류", JOptionPane.WARNING_MESSAGE);
				input = Integer.valueOf(JOptionPane.showInputDialog(msg));
			}
			JOptionPane.showMessageDialog(null, "주차 가능한 차량: " + input + "대로 설정 완료!", "설정 완료",JOptionPane.INFORMATION_MESSAGE);
			lot.setMax(input);
		}
	}
	
	public static void main(String[] args) {
		ParkingTest pt = new ParkingTest();
		pt.SEL = new ParkingSystem(pt);
		pt.add(pt.SEL);
		pt.setTitle("주차장 관리 프로그램");
		pt.setBounds(0, 10, 398, 650);
		pt.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		pt.setVisible(true);
		pt.setResizable(false);
		pt.setLocationRelativeTo(null);
	}
	
	public void panelch(String pname) {
		
		if(pname.equals("SEL")) {
			SEL = new ParkingSystem(this);
			getContentPane().removeAll();
			getContentPane().add(SEL);
			revalidate();
			repaint();			
			}
		else if(pname.equals("ENT")) {
			ENT = new Entrance(this, lot);
			getContentPane().removeAll();
			getContentPane().add(ENT);
			revalidate();
			repaint();	
		}
		else if(pname.equals("DEP")) {
			DEP = new Departure(this,lot);
			getContentPane().removeAll();
			getContentPane().add(DEP);
			revalidate();
			repaint();
		}
		
		else if(pname.equals("SHOW")) {
			SHOW = new ShowCar(this,lot);
			getContentPane().removeAll();
			getContentPane().add(SHOW);
			revalidate();
			repaint();
		}
		else if(pname.equals("LIST")) {
			LIST= new ListView(this,lot);
			getContentPane().removeAll();
			getContentPane().add(LIST);
			revalidate();
			repaint();
		}
		else if(pname.equals("INCOME")) {
			INCOME= new CheckIncome(this,lot);
			getContentPane().removeAll();
			getContentPane().add(INCOME);
			revalidate();
			repaint();
		}
	}
}