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
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

public class ListView extends JPanel {

	private JTextField Yeartxt, Monthtxt, Daytxt, Timetxt, Minutetxt;
	
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
	
	public ListView(ParkingTest pt, Parkinglot pl) {
		
		JPanel newpanel = new JPanel();
		newpanel.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		newpanel.setBounds(0, 0, 398, 603);
		newpanel.setLayout(null);
		
		String header[] = { "차량 종류", "차량 번호", "입차 시간", "차량 정보" };
		Color co = new Color(13099771);
		DefaultTableModel model = new DefaultTableModel(header, 0);
		JTable table = new JTable(model);
		JScrollPane scrollpane = new JScrollPane(table);
		
		newpanel.add(scrollpane);
		scrollpane.setBounds(0, 0, 398, 553);
		scrollpane.getViewport().setBackground(co);
		add(newpanel);
		
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
		JLabel DepClock = new JLabel("조회 시간: ");
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
				
		//시 입력
		JLabel DepTime = new JLabel("시");
		DepTime.setFont(new Font("굴림", Font.PLAIN, 20));
		DepTime.setBounds(205, 287, 29, 37);
		add(DepTime);
		Timetxt = new JTextField();
		Timetxt.setColumns(10);
		Timetxt.setBounds(141, 292, 52, 31);
		add(Timetxt);
				
		//분 입력
		JLabel DepMinute = new JLabel("분");
		DepMinute.setFont(new Font("굴림", Font.PLAIN, 20));
		DepMinute.setBounds(310, 287, 29, 37);
		add(DepMinute);
		Minutetxt = new JTextField();
		Minutetxt.setColumns(10);
		Minutetxt.setBounds(246, 292, 52, 31);
		add(Minutetxt);
		
		JButton Showbtn = new JButton("조회");
		Showbtn.setFont(new Font("굴림", Font.PLAIN, 27));
		Showbtn.setBounds(221, 526, 132, 58);
		add(Showbtn);
		
		JButton OKbtn = new JButton("확인");
		OKbtn.setBounds(149, 563, 78, 33);
		newpanel.add(OKbtn);
		
		JButton beforebtn = new JButton("이전");
		beforebtn.setFont(new Font("굴림", Font.PLAIN, 27));
		beforebtn.setBounds(46, 526, 132, 58);
		add(beforebtn);
		
		newpanel.setVisible(false);
		
		Showbtn.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				ParkingCar=new ArrayList<String[]>();
				int flag = 0;
				int year = 0, month = 0, day = 0, time = 0, minute = 0;
				String tmpY, tmpM, tmpD, tmpT, tmpMN;

				tmpY = Yeartxt.getText(); tmpM = Monthtxt.getText(); tmpD = Daytxt.getText();
				tmpT = Timetxt.getText(); tmpMN = Minutetxt.getText();

				if(tmpY.equals("") | tmpM.equals("") | tmpD.equals("") | tmpT.equals("") | tmpMN.equals("")) {
					JOptionPane.showMessageDialog(null, "빈칸이 있습니다. 다시 확인해주세요.", "입력 오류", JOptionPane.WARNING_MESSAGE);
					flag=1;
				}
				
				if (flag != 1) {
					year = Integer.valueOf(tmpY);
					month = Integer.valueOf(tmpM);
					day = Integer.valueOf(tmpD);
					time = Integer.valueOf(tmpT);
					minute = Integer.valueOf(tmpMN);
					if (ek.sequenceCheck("list", t.makeMin(year, month, day, time, minute)))
						flag = 1;
					else if ((!t.dayValid(year, month, day) || !t.timeValid(time, minute))) {
						JOptionPane.showMessageDialog(null, "유효하지 않은 날짜입니다. 다시 입력하세요.", "입력 시간 오류",JOptionPane.WARNING_MESSAGE);
						flag = 1;
					}
				}
				
				if(flag!=1) {
					newpanel.setVisible(true);
					Showbtn.setVisible(false);
					beforebtn.setVisible(false);
					OKbtn.setVisible(true);
					
					pl.listSort();
					for (int i = 0; i < pl.getcnt(); i++) {
						Car car = pl.getList(i);
						ParkingCar.add(new String[] { car.getTypestr(), car.getNum(), car.getTime(), car.getInfo(year, month, day, time, minute)});
					}

					for (String[] c : ParkingCar) {
						model.addRow(c);
					}
					
					pl.lasttime=t.makeMin(year, month, day, time, minute);
					pl.beforeyear=year; pl.beforemonth=month; pl.beforeday=day;
				}
			}
		});
		
		OKbtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pt.panelch("SEL");
			}
		});
		
		beforebtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pt.panelch("SEL");
			}
		});
	}
}