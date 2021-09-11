package ParkinglotManagement;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;

public class CheckIncome extends JPanel {

	private JTextField Yeartxt, Monthtxt, Daytxt;
	
	int index=0;
	int minsum1=0, minsum2=0;
	int em=0,size=0;
	double cp=0;

	Parkinglot pl;
	ExceptionCheck ek;
	Time t = new Time();
	ParkingTest pt;
	ArrayList<String[]> ParkingCar;
	Image background=new ImageIcon("image/backimg.jpg").getImage();
	
	public void paintComponent(Graphics g) {
        g.drawImage(background, -15, -50, 398,650,null);
    }
	
	public CheckIncome(ParkingTest pt, Parkinglot pl) {
		setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		setBounds(0, 10, 398, 605);
		setLayout(null);
		this.pt = pt;
		this.pl=pl;
		ek = new ExceptionCheck(pl);

		//출차 정보 입력
		JLabel DepTitle = new JLabel("조회 시간 입력");
		DepTitle.setForeground(Color.BLACK);
		DepTitle.setFont(new Font("HY견고딕", Font.PLAIN, 30));
		DepTitle.setBounds(103, 44, 219, 37);
		add(DepTitle);
		
		//입차 시간 입력
		JLabel DepClock = new JLabel("조회 날짜: ");
		DepClock.setFont(new Font("굴림", Font.PLAIN, 20));
		DepClock.setBounds(25, 170, 104, 37);
		add(DepClock);
				
		//년도 입력
		JLabel DepYear = new JLabel("년");
		DepYear.setFont(new Font("굴림", Font.PLAIN, 20));
		DepYear.setBounds(236, 170, 29, 37);
		add(DepYear);
		Yeartxt = new JTextField();
		Yeartxt.setColumns(10);
		Yeartxt.setBounds(141, 175, 83, 31);
		add(Yeartxt);
				
		//달 입력
		JLabel DepMonth = new JLabel("월");
		DepMonth.setFont(new Font("굴림", Font.PLAIN, 20));
		DepMonth.setBounds(205, 229, 29, 37);
		add(DepMonth);
		Monthtxt = new JTextField();
		Monthtxt.setColumns(10);
		Monthtxt.setBounds(141, 234, 52, 31);
		add(Monthtxt);
				
		//일 입력
		JLabel DepDay = new JLabel("일");
		DepDay.setFont(new Font("굴림", Font.PLAIN, 20));
		DepDay.setBounds(310, 229, 29, 37);
		add(DepDay);
		Daytxt = new JTextField();
		Daytxt.setColumns(10);
		Daytxt.setBounds(246, 234, 52, 31);
		add(Daytxt);
				
		JButton Showbtn = new JButton("조회");
		Showbtn.setFont(new Font("굴림", Font.PLAIN, 27));
		Showbtn.setBounds(44, 505, 132, 58);
		add(Showbtn);
		
		JButton OKbtn = new JButton("이전");
		OKbtn.setFont(new Font("굴림", Font.PLAIN, 27));
		OKbtn.setBounds(230, 505, 132, 58);
		add(OKbtn);
		
		Showbtn.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				ParkingCar=new ArrayList<String[]>();
				int flag = 0;
				// if(ek.lotCheck("leave", max))
				// continue;

				int year = 0, month = 0, day = 0;
				String tmpY, tmpM, tmpD;

				tmpY = Yeartxt.getText(); tmpM = Monthtxt.getText(); tmpD = Daytxt.getText();

				if(tmpY.equals("") | tmpM.equals("") | tmpD.equals("")) {
					JOptionPane.showMessageDialog(null, "빈칸이 있습니다. 다시 확인해주세요.", "등록 오류", JOptionPane.WARNING_MESSAGE);
					flag=1;
				}
				
				if (flag != 1) {
					year = Integer.valueOf(tmpY);
					month = Integer.valueOf(tmpM);
					day = Integer.valueOf(tmpD);
					if ((!t.dayValid(year, month, day))) {
						JOptionPane.showMessageDialog(null, "유효하지 않은 날짜입니다. 다시 입력하세요.", "등록 오류",JOptionPane.WARNING_MESSAGE);
						flag = 1;
					}
					else if(ek.incometimeCheck(year,month,day))
						flag=1;
				}
				
				if(flag!=1) {
					String msg=Integer.toString(pl.getincome(year, month, day));
					JOptionPane.showMessageDialog(null, "**"+year+"년 "+month+"월 "+day+"일 수익**\n"+msg+"원", "수익 확인",JOptionPane.INFORMATION_MESSAGE);
					}
				}
			});
		
		OKbtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pt.panelch("SEL");
			}
		});
	}
}