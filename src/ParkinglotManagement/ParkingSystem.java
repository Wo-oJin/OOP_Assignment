package ParkinglotManagement;

import java.awt.Color;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;

public class ParkingSystem extends JPanel {
	
	private ParkingTest operate;
	private Parkinglot pl;
	Image background=new ImageIcon("image/lionimg.jpg").getImage();
	
	public void paintComponent(Graphics g) {
        g.drawImage(background, 0, -3, 398,650,null);
    }
	
	public ParkingSystem(ParkingTest pt) {
		setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		setBounds(0, 10, 398, 650);
		operate = pt;
		this.pl=pt.lot;
		setLayout(null);
		
		JLabel showLotcondition = new JLabel("현재 주차된 차량 수: "+pl.getcnt()+"/"+pl.getMax()+"대");
		showLotcondition.setForeground(Color.RED);
		showLotcondition.setFont(new Font("굴림", Font.PLAIN, 20));
		showLotcondition.setBounds(59, 582, 312, 30);
		add(showLotcondition);
		
		JButton makeENT = new JButton("입 차");
		makeENT.setBounds(38, 204, 127, 84);
		makeENT.setFont(new Font("굴림", Font.PLAIN, 17));
		add(makeENT);
		makeENT.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				if(pl.getcnt()==pl.getMax())
					JOptionPane.showMessageDialog(null, "주차장이 꽉 차있습니다.", "입차 에러", JOptionPane.WARNING_MESSAGE);
				else
					operate.panelch("ENT");
			}
		});
		
		JButton makeDEP = new JButton("출 차");
		makeDEP.setFont(new Font("굴림", Font.PLAIN, 17));
		makeDEP.setBounds(223, 204, 127, 84);
		add(makeDEP);
		makeDEP.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				if(pl.getcnt()==0)
					JOptionPane.showMessageDialog(null, "주차장이 비어있습니다.", "출차 에러", JOptionPane.WARNING_MESSAGE);
				else
					operate.panelch("DEP");
			}
		});
		
		JButton makeShow = new JButton("차량 조회");
		makeShow.setBounds(38, 340, 127, 84);
		makeShow.setFont(new Font("굴림", Font.PLAIN, 17));
		add(makeShow);
		makeShow.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				if(pl.getcnt()==0)
					JOptionPane.showMessageDialog(null, "주차장이 비어있습니다.", "차량 정보 확인 에러", JOptionPane.WARNING_MESSAGE);
				else
					operate.panelch("SHOW");
			}
		});
		
		JButton makeList = new JButton("주차장 확인");
		makeList.setBounds(223, 340, 127, 84);
		makeList.setFont(new Font("굴림", Font.PLAIN, 17));
		add(makeList);
		makeList.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				if(pl.getcnt()==0)
					JOptionPane.showMessageDialog(null, "주차장이 비어있습니다.", "차량 정보 확인 에러", JOptionPane.WARNING_MESSAGE);
				else
					operate.panelch("LIST");
			}
		});
		
		JButton makeIncome = new JButton("수익 확인");
		makeIncome.setBounds(38, 478, 127, 84);
		makeIncome.setFont(new Font("굴림", Font.PLAIN, 17));
		add(makeIncome);
		makeIncome.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				operate.panelch("INCOME");
			}
		});
		
		JButton makeFinish = new JButton("종 료");
		makeFinish.setBounds(223, 478, 127, 84);
		makeFinish.setFont(new Font("굴림", Font.PLAIN, 17));
		add(makeFinish);
		
		makeFinish.addActionListener(new ActionListener() { 
			public void actionPerformed(ActionEvent e) {
				int result=JOptionPane.showConfirmDialog(null, "프로그램을 종료하시겠습니까?", "종료", JOptionPane.YES_NO_OPTION);
				if(result==JOptionPane.YES_OPTION)
					System.exit(0);
			}
		});
	}
}