package ParkinglotManagement;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;

public class ShowCar extends JPanel {

	private JTextField numtxt, Yeartxt, Monthtxt, Daytxt, Timetxt, Minutetxt;

	int lastmove = 0, lasttime = 0, index = 0;
	int minsum1 = 0, minsum2 = 0, fee = 0;
	int em = 0, size = 0;
	double cp = 0;

	Parkinglot pl;
	ExceptionCheck ek;
	Time t = new Time();
	ParkingTest pt;
	Image background=new ImageIcon("image/backimg.jpg").getImage();
	
	public void paintComponent(Graphics g) {
        g.drawImage(background, -15, -50, 398,650,null);
    }

	public ShowCar(ParkingTest pt, Parkinglot pl) {
		setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		setBounds(0, 10, 398, 596);
		setLayout(null);
		this.pt = pt;
		this.pl = pl;
		ek = new ExceptionCheck(pl);

		// 차량 정보 입력
		JLabel DepTitle = new JLabel("차량 정보 입력");
		DepTitle.setForeground(Color.BLACK);
		DepTitle.setFont(new Font("HY견고딕", Font.PLAIN, 30));
		DepTitle.setBounds(103, 44, 219, 37);
		add(DepTitle);

		// 차량 번호 입력
		JLabel DepNum = new JLabel("차량 번호");
		DepNum.setFont(new Font("굴림", Font.PLAIN, 20));
		DepNum.setBounds(42, 205, 104, 37);
		add(DepNum);
		numtxt = new JTextField();
		numtxt.setColumns(10);
		numtxt.setBounds(155, 210, 119, 31);
		add(numtxt);

		// 입차 시간 입력
		JLabel DepClock = new JLabel("조회 시간");
		DepClock.setFont(new Font("굴림", Font.PLAIN, 20));
		DepClock.setBounds(42, 287, 104, 37);
		add(DepClock);

		// 년도 입력
		JLabel DepYear = new JLabel("년");
		DepYear.setFont(new Font("굴림", Font.PLAIN, 20));
		DepYear.setBounds(245, 287, 29, 37);
		add(DepYear);
		Yeartxt = new JTextField();
		Yeartxt.setColumns(10);
		Yeartxt.setBounds(155, 292, 83, 31);
		add(Yeartxt);

		// 달 입력
		JLabel DepMonth = new JLabel("월");
		DepMonth.setFont(new Font("굴림", Font.PLAIN, 20));
		DepMonth.setBounds(219, 349, 29, 37);
		add(DepMonth);
		Monthtxt = new JTextField();
		Monthtxt.setColumns(10);
		Monthtxt.setBounds(155, 349, 52, 31);
		add(Monthtxt);

		// 일 입력
		JLabel DepDay = new JLabel("일");
		DepDay.setFont(new Font("굴림", Font.PLAIN, 20));
		DepDay.setBounds(324, 349, 29, 37);
		add(DepDay);
		Daytxt = new JTextField();
		Daytxt.setColumns(10);
		Daytxt.setBounds(260, 349, 52, 31);
		add(Daytxt);

		// 시 입력
		JLabel DepTime = new JLabel("시");
		DepTime.setFont(new Font("굴림", Font.PLAIN, 20));
		DepTime.setBounds(219, 402, 29, 37);
		add(DepTime);
		Timetxt = new JTextField();
		Timetxt.setColumns(10);
		Timetxt.setBounds(155, 407, 52, 31);
		add(Timetxt);

		// 분 입력
		JLabel DepMinute = new JLabel("분");
		DepMinute.setFont(new Font("굴림", Font.PLAIN, 20));
		DepMinute.setBounds(324, 402, 29, 37);
		add(DepMinute);
		Minutetxt = new JTextField();
		Minutetxt.setColumns(10);
		Minutetxt.setBounds(260, 407, 52, 31);
		add(Minutetxt);

		JButton Showbtn = new JButton("조회");
		Showbtn.setFont(new Font("굴림", Font.PLAIN, 27));
		Showbtn.setBounds(42, 501, 132, 58);
		add(Showbtn);
		
		JButton beforebtn = new JButton("이전");
		beforebtn.setFont(new Font("굴림", Font.PLAIN, 27));
		beforebtn.setBounds(221, 501, 132, 58);
		add(beforebtn);

		Showbtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int flag = 0;
				int year = 0, month = 0, day = 0, time = 0, minute = 0;
				String carnum, tmpY, tmpM, tmpD, tmpT, tmpMN;

				carnum = numtxt.getText();
				tmpY = Yeartxt.getText();
				tmpM = Monthtxt.getText();
				tmpD = Daytxt.getText();
				tmpT = Timetxt.getText();
				tmpMN = Minutetxt.getText();

				if(carnum.equals("") |  tmpY.equals("") | tmpM.equals("") | tmpD.equals("") | tmpT.equals("") | tmpMN.equals("")) {
					JOptionPane.showMessageDialog(null, "빈칸이 있습니다. 다시 확인해주세요.", "입력 오류", JOptionPane.WARNING_MESSAGE);
					flag=1;
				}
				
				if (flag != 1) {
					year = Integer.valueOf(tmpY);
					month = Integer.valueOf(tmpM);
					day = Integer.valueOf(tmpD);
					time = Integer.valueOf(tmpT);
					minute = Integer.valueOf(tmpMN);
					if (ek.carnumCheck(carnum))
						flag = 1;
					else if (ek.sequenceCheck("show", t.makeMin(year, month, day, time, minute)))
						flag = 1;
					else if ((!t.dayValid(year, month, day) || !t.timeValid(time, minute))) {
						JOptionPane.showMessageDialog(null, "유효하지 않은 날짜입니다. 다시 입력하세요.", "입력 시간 오류",JOptionPane.WARNING_MESSAGE);
						flag = 1;
					}
				}

				if (flag != 1) {
					index = pl.findCar(carnum);
					if (index == -1) {
						JOptionPane.showMessageDialog(null, "입력한 번호에 맞는 차량이 존재하지 않음", "차량 조회 오류", JOptionPane.WARNING_MESSAGE);
						flag = 1;
					}

					if (flag != 1) {
						pl.getList(index).show(year, month, day, time, minute);
						pl.lasttime = t.makeMin(year, month, day, time, minute);
						pl.beforeyear=year; pl.beforemonth=month; pl.beforeday=day;
						pt.panelch("SEL");
					}
				}
			}
		});
		
		beforebtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pt.panelch("SEL");
			}
		});
	}
}