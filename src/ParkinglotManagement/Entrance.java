package ParkinglotManagement;


import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Color;
import javax.swing.JPanel;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTextField;
import javax.swing.JRadioButton;
import javax.swing.border.LineBorder;
import javax.swing.JButton;

public class Entrance extends JPanel {

	private JTextField numtxt, Infotxt;
	private JTextField Yeartxt, Monthtxt, Daytxt, Timetxt, Minutetxt;
	private ButtonGroup btnGroup;
	Image background=new ImageIcon("image/backimg.jpg").getImage();
	
	public void paintComponent(Graphics g) {
        g.drawImage(background, -15, -50, 398,650,null);
    }
	
	int lastmove = 0, lasttime = 0, flag = 0;
	int em = 0, size = 0;
	double cp = 0;

	Parkinglot pl;
	ParkingTest pt;
	ExceptionCheck ek;
	Time t = new Time();

	public Entrance(ParkingTest pt, Parkinglot pl) {
		setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		setBounds(0, 0, 398, 605);
		setLayout(null);
		this.pt = pt;
		this.pl = pl;
		this.ek = new ExceptionCheck(pl);

		// 입차 정보 입력
		JLabel EntTitle = new JLabel("입차 정보 입력");
		EntTitle.setForeground(Color.BLACK);
		EntTitle.setFont(new Font("HY견고딕", Font.PLAIN, 30));
		EntTitle.setBounds(103, 44, 219, 37);
		add(EntTitle);

		// 차량 종류 입력
		JLabel EntType = new JLabel("차량 종류");
		EntType.setFont(new Font("굴림", Font.PLAIN, 20));
		EntType.setBounds(42, 115, 104, 37);
		add(EntType);

		btnGroup = new ButtonGroup(); // 라디오 그룹

		// 승용차 정보 입력
		JRadioButton gsCarbtn = new JRadioButton("승용차");
		gsCarbtn.setFont(new Font("굴림", Font.PLAIN, 14));
		gsCarbtn.setBounds(42, 158, 69, 23);
		gsCarbtn.setSelected(true);
		btnGroup.add(gsCarbtn);
		add(gsCarbtn);

		// 전기 자동차 정보 입력
		JRadioButton ecCarbtn = new JRadioButton("전기 자동차");
		ecCarbtn.setFont(new Font("굴림", Font.PLAIN, 14));
		ecCarbtn.setBounds(138, 158, 110, 23);
		ecCarbtn.setSelected(true);
		btnGroup.add(ecCarbtn);
		add(ecCarbtn);

		// 밴 정보 입력
		JRadioButton Vanbtn = new JRadioButton("밴");
		Vanbtn.setFont(new Font("굴림", Font.PLAIN, 14));
		Vanbtn.setBounds(267, 158, 45, 23);
		Vanbtn.setSelected(true);
		btnGroup.add(Vanbtn);
		add(Vanbtn);

		// 차량 번호 입력
		JLabel EntNum = new JLabel("차량 번호");
		EntNum.setFont(new Font("굴림", Font.PLAIN, 20));
		EntNum.setBounds(42, 205, 104, 37);
		add(EntNum);
		numtxt = new JTextField();
		numtxt.setColumns(10);
		numtxt.setBounds(155, 210, 119, 31);
		add(numtxt);

		// 추가 정보 입력
		JLabel EntInfo = new JLabel("추가 정보");
		EntInfo.setFont(new Font("굴림", Font.PLAIN, 20));
		EntInfo.setBounds(42, 277, 104, 37);
		add(EntInfo);
		Infotxt = new JTextField();
		Infotxt.setColumns(10);
		Infotxt.setBounds(155, 277, 119, 31);
		add(Infotxt);

		JLabel hintText = new JLabel("** 승용차: 가솔린 / 전기자동차: 충전량 / 밴: 차량 크기 **");
		hintText.setForeground(Color.RED);
		hintText.setBounds(42, 314, 313, 23);
		add(hintText);

		// 입차 시간 입력
		JLabel EntClock = new JLabel("입차 시간");
		EntClock.setFont(new Font("굴림", Font.PLAIN, 20));
		EntClock.setBounds(42, 347, 104, 37);
		add(EntClock);

		// 년도 입력
		JLabel EntYear = new JLabel("년");
		EntYear.setFont(new Font("굴림", Font.PLAIN, 20));
		EntYear.setBounds(245, 347, 29, 37);
		add(EntYear);
		Yeartxt = new JTextField();
		Yeartxt.setColumns(10);
		Yeartxt.setBounds(155, 352, 83, 31);
		add(Yeartxt);

		// 달 입력
		JLabel EntMonth = new JLabel("월");
		EntMonth.setFont(new Font("굴림", Font.PLAIN, 20));
		EntMonth.setBounds(219, 403, 29, 37);
		add(EntMonth);
		Monthtxt = new JTextField();
		Monthtxt.setColumns(10);
		Monthtxt.setBounds(155, 408, 52, 31);
		add(Monthtxt);

		// 일 입력
		JLabel EntDay = new JLabel("일");
		EntDay.setFont(new Font("굴림", Font.PLAIN, 20));
		EntDay.setBounds(326, 403, 29, 37);
		add(EntDay);
		Daytxt = new JTextField();
		Daytxt.setColumns(10);
		Daytxt.setBounds(260, 408, 52, 31);
		add(Daytxt);

		// 시 입력
		JLabel EntTime = new JLabel("시");
		EntTime.setFont(new Font("굴림", Font.PLAIN, 20));
		EntTime.setBounds(219, 458, 29, 37);
		add(EntTime);
		Timetxt = new JTextField();
		Timetxt.setColumns(10);
		Timetxt.setBounds(155, 463, 52, 31);
		add(Timetxt);

		// 분 입력
		JLabel EntMinute = new JLabel("분");
		EntMinute.setFont(new Font("굴림", Font.PLAIN, 20));
		EntMinute.setBounds(326, 458, 29, 37);
		add(EntMinute);
		Minutetxt = new JTextField();
		Minutetxt.setColumns(10);
		Minutetxt.setBounds(260, 463, 52, 31);
		add(Minutetxt);

		// 입차 완료
		JButton Entrancebtn = new JButton("등록");
		// Entrancebtn.setForeground(Color.BLACK);
		// Entrancebtn.setBackground(Color.BLACK);
		Entrancebtn.setFont(new Font("굴림", Font.PLAIN, 27));
		Entrancebtn.setBounds(28, 516, 132, 58);
		add(Entrancebtn);
		
		JButton OKbtn = new JButton("이전");
		OKbtn.setFont(new Font("굴림", Font.PLAIN, 27));
		OKbtn.setBounds(231, 516, 132, 58);
		add(OKbtn);

		Entrancebtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int flag = 0;
				int year = 0, month = 0, day = 0, time = 0, minute = 0;
				char type = 0;
				String strtype = null;
				String carnum, temp, tmpY, tmpM, tmpD, tmpT, tmpMN;

				carnum = numtxt.getText();
				tmpY = Yeartxt.getText();
				tmpM = Monthtxt.getText();
				tmpD = Daytxt.getText();
				tmpT = Timetxt.getText();
				tmpMN = Minutetxt.getText();

				if (gsCarbtn.isSelected()) {
					type = 'g';
					strtype = "passenger car";
				} else if (ecCarbtn.isSelected()) {
					type = 'e';
					strtype = "electronic car";
				} else if (Vanbtn.isSelected()) {
					type = 'v';
					strtype = "Van";
				}

				if (flag!=1 & (carnum.equals("") | tmpY.equals("") | tmpM.equals("") | tmpD.equals("") | tmpT.equals("")| tmpMN.equals(""))) {
					JOptionPane.showMessageDialog(null, "빈칸이 있습니다. 다시 확인해주세요.", "등록 오류", JOptionPane.WARNING_MESSAGE);
					flag = 1;
				}

				if (flag != 1) {
					year = Integer.valueOf(tmpY);
					month = Integer.valueOf(tmpM);
					day = Integer.valueOf(tmpD);
					time = Integer.valueOf(tmpT);
					minute = Integer.valueOf(tmpMN);
					if (ek.carnumCheck(carnum))
						flag = 1;
					else if (ek.sequenceCheck("enter", t.makeMin(year, month, day, time, minute)))
						flag = 1;
					else if ((!t.dayValid(year, month, day) || !t.timeValid(time, minute))) {
						JOptionPane.showMessageDialog(null, "유효하지 않은 날짜입니다. 다시 입력하세요.", "등록 오류",JOptionPane.WARNING_MESSAGE);
						flag = 1;
					}
				}

				if (flag != 1) {
					switch (type) {
					case 'g':
						temp = Infotxt.getText();
						em = Integer.valueOf(temp);
						if (ek.emCheck(em)) {
							flag = 1;
							break;
						}
						psCar pc = new psCar(carnum, year, month, day, time, minute, em);

						if (ek.duplicateCheck(pc.getType(), pc)) {
							flag = 1;
							break;
						}
						pl.setList(pc);
						break;
					case 'e':
						temp = Infotxt.getText();
						cp = Integer.valueOf(temp);
						if (ek.cpCheck(cp)) {
							flag = 1;
							break;
						}
						ecCar ec = new ecCar(carnum, year, month, day, time, minute, cp);
						if (ek.duplicateCheck(ec.getType(), ec)) {
							flag = 1;
							break;
						}
						pl.setList(ec);

						break;
					case 'v':
						temp = Infotxt.getText();
						size = Integer.valueOf(temp);
						if (ek.sizeCheck(size)) {
							flag = 1;
							break;
						}
						Van v = new Van(carnum, year, month, day, time, minute, size);
						if (ek.duplicateCheck(v.getType(), v)) {
							flag = 1;
							break;
						}
						pl.setList(v);
					}
					if(flag!=1) {
						pl.lastmove = t.makeMin(year, month, day, time, minute);
						pl.lasttime = t.makeMin(year, month, day, time, minute);
						pl.beforeyear=year; pl.beforemonth=month; pl.beforeday=day;
						JOptionPane.showMessageDialog(null, carnum+"번 차량 입차가 완료되었습니다.", "차량 등록 완료",JOptionPane.INFORMATION_MESSAGE);
						pt.panelch("SEL");
					}
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