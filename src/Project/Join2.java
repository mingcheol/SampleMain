package Project;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;

import java.awt.event.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.event.*;

import Project.JoinForm.MyEXITListener;
import Project.JoinForm.MyOKListener;
import Project.LoginForm.MyCreateListener;


//병신

public class Join2 extends JFrame {
	private Container contentPane; // 틀에맞는 칸을 만들 패널
	private JTextField idCreateField; // 아이디 생성 칸
	private static JButton idDuplicationBtn; // 아이디중복확인 버튼
	private JPasswordField pwCreateField; // 비밀번호 생성 칸
	private JPasswordField pwCheckField; // 비밀번호 확인 칸
	private JTextField nicName; // 닉네임 칸
	private JTextField birthDay; // 생년월일 칸
	private JTextField tel_Field; // 연락처 칸
	private JTextField email_Field; // 이메일 칸
	private static JLabel copyRight; // 저작권 라벨
	private ImageIcon image = new ImageIcon("Images/현준용철.jpg"); // 배경화면으로 쓸 이미지 로딩
	private JLabel ImageLabel = new JLabel(image); // 이미지를 레이블에 삽입

	private static JButton joinClearButton; // 가입하기 버튼
	private static JButton joinCencelButton; // 가입취소 버튼

	static Dialog msgDlg; // 에러팝업창
	static Label msgLabel; // 에러를 알려주는 라벨????

	public Join2() {
		setTitle("COZY TALK 회원가입");
		setBounds(600, 200, 500, 640);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setResizable(false);
		setLocationRelativeTo(null);

		contentPane = getContentPane();
		contentPane.setLayout(null);

		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		panel.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panel.setBounds(0, 0, 490, 695);
		panel.add(ImageLabel);
		contentPane.add(panel);
		panel.setLayout(null);

		JLabel joinTitle = new JLabel("○○○○○ 회원가입 ○○○○○"); // 타이틀
		joinTitle.setForeground(new Color(255, 153, 153));
		joinTitle.setFont(new Font("맑은고딕", Font.BOLD, 30));
		joinTitle.setBounds(10, 23, 500, 58);
		panel.add(joinTitle);

		JLabel idLabel = new JLabel("아이디 : "); // 아이디 :
		idLabel.setForeground(new Color(255, 153, 153));
		idLabel.setFont(new Font("맑은고딕", Font.BOLD, 17));
		idLabel.setBounds(77, 111, 100, 25);
		panel.add(idLabel);

		idCreateField = new JTextField();
		idCreateField.setBounds(161, 110, 150, 30);
		idCreateField.setFont(new Font("맑은고딕", Font.BOLD, 20));
		panel.add(idCreateField);
		idCreateField.setColumns(10);

		idDuplicationBtn = new JButton("아이디 중복확인");
		idDuplicationBtn.setBackground(new Color(255, 153, 153));
		idDuplicationBtn.setForeground(Color.WHITE);
		idDuplicationBtn.setFont(new Font("맑은고딕", Font.BOLD, 15));
		idDuplicationBtn.setBounds(320, 111, 150, 27);
		panel.add(idDuplicationBtn);

		idDuplicationBtn.addActionListener(new MyCreateListener());

		JLabel pwLabel = new JLabel("비밀번호 :"); // 비밀번호 :
		pwLabel.setForeground(new Color(255, 153, 153));
		pwLabel.setFont(new Font("맑은고딕", Font.BOLD, 17));
		pwLabel.setBounds(59, 172, 100, 20);
		panel.add(pwLabel);

		pwCreateField = new JPasswordField();
		pwCreateField.setColumns(10);
		pwCreateField.setBounds(161, 166, 150, 30);
		pwCreateField.setFont(new Font("맑은고딕", Font.BOLD, 20));
		panel.add(pwCreateField);

		JLabel pwCheckLabel = new JLabel("비밀번호 확인 : "); // 비밀번호 확인 :
		pwCheckLabel.setForeground(new Color(255, 153, 153));
		pwCheckLabel.setFont(new Font("맑은고딕", Font.BOLD, 17));
		pwCheckLabel.setBounds(22, 220, 140, 30);
		panel.add(pwCheckLabel);

		pwCheckField = new JPasswordField();
		pwCheckField.setColumns(10);
		pwCheckField.setBounds(161, 220, 150, 30);
		pwCheckField.setFont(new Font("맑은고딕", Font.BOLD, 20));
		panel.add(pwCheckField);

		JLabel nicNameLabel = new JLabel("닉네임 :"); // 닉네임 :
		nicNameLabel.setForeground(new Color(255, 153, 153));
		nicNameLabel.setFont(new Font("맑은고딕", Font.BOLD, 17));
		nicNameLabel.setBounds(77, 271, 70, 30);
		panel.add(nicNameLabel);

		nicName = new JTextField();
		nicName.setColumns(10);
		nicName.setBounds(161, 270, 150, 30);
		nicName.setFont(new Font("맑은고딕", Font.BOLD, 20));
		panel.add(nicName);

		JLabel birthDayLabel = new JLabel("생년월일 :"); // 생년월일 라벨
		birthDayLabel.setForeground(new Color(255, 153, 153));
		birthDayLabel.setFont(new Font("맑은고딕", Font.BOLD, 17));
		birthDayLabel.setBounds(59, 321, 130, 30);
		panel.add(birthDayLabel);

		birthDay = new JTextField();
		birthDay.setColumns(10);
		birthDay.setBounds(161, 323, 150, 30);
		birthDay.setFont(new Font("맑은고딕", Font.BOLD, 20));
		panel.add(birthDay);

		JLabel tel_Label = new JLabel("연락처 :"); // 연락처 :
		tel_Label.setForeground(new Color(255, 153, 153));
		tel_Label.setFont(new Font("맑은고딕", Font.BOLD, 17));
		tel_Label.setBounds(77, 373, 130, 30);
		panel.add(tel_Label);

		tel_Field = new JTextField();
		tel_Field.setColumns(10);
		tel_Field.setBounds(161, 375, 150, 30);
		tel_Field.setFont(new Font("맑은고딕", Font.BOLD, 20));
		panel.add(tel_Field);

		JLabel email_Label = new JLabel("이메일 :"); // 이메일 :
		email_Label.setForeground(new Color(255, 153, 153));
		email_Label.setFont(new Font("맑은고딕", Font.BOLD, 17));
		email_Label.setBounds(77, 425, 130, 30);
		panel.add(email_Label);

		email_Field = new JTextField();
		email_Field.setColumns(10);
		email_Field.setBounds(161, 425, 150, 30);
		email_Field.setFont(new Font("맑은고딕", Font.BOLD, 20));
		panel.add(email_Field);

		joinClearButton= new JButton("회원가입");
		joinClearButton.setBackground(new Color(255, 153, 153));
		joinClearButton.setForeground(Color.WHITE);
		joinClearButton.setFont(new Font("맑은고딕", Font.BOLD, 20));
		joinClearButton.setBounds(110, 500, 120, 40);
		panel.add(joinClearButton);

		joinCencelButton = new JButton("취소");
		joinCencelButton.setBackground(new Color(255, 153, 153));
		joinCencelButton.setForeground(Color.WHITE);
		joinCencelButton.setFont(new Font("맑은고딕", Font.BOLD, 20));
		joinCencelButton.setBounds(250, 500, 120, 40);
		panel.add(joinCencelButton);

		copyRight = new JLabel();
		copyRight.setBounds(161, 550, 150, 30);
		copyRight.setFont(new Font("맑은고딕", Font.BOLD, 20));
		panel.add(copyRight);
		
		joinClearButton.addActionListener(new joinClearListener());
		joinCencelButton.addActionListener(new joinCencelListener());
		
		setVisible(true);
	}

	class MyCreateListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if (e.getActionCommand().equals("아이디 중복확인")) {
				// 데이터베이스 테이블에 있는 유저아이디와 비교해서 getText~equal해서 맞으면 alert띄우면됨(경고창)
				JOptionPane.showMessageDialog(null, "아이디가 중복됩니다. 다시입력해주세요.", "아이디 중복 확인", JOptionPane.ERROR_MESSAGE);
				ServerMain.launch();
			}
		}
	}

	class joinClearListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if (e.getActionCommand().equals("회원가입")) {
				String in_id = idCreateField.getText();
				String in_pw = pwCreateField.getText();
				String in_name = nicName.getText();
				String driver = "oracle.jdbc.driver.OracleDriver";
				String url = "jdbc:oracle:thin:@localhost:1521:xe";
				String user = "YOON";
				String password = "2529";

				Connection con = null;
				Statement stmt = null;
				int rs = 0;

				try {
					Class.forName(driver);
					System.out.println("JDBC DRIVER 로딩 성공");
					con = DriverManager.getConnection(url, user, password);
					System.out.println("DB 로딩 성공");
					stmt = con.createStatement();
					String sql = "INSERT INTO USERINFO VALUES('" + in_id + "', '" + in_pw + "', '" + in_name + "')";
					rs = stmt.executeUpdate(sql);
					if (rs == 1) {
						// System.out.println("INSERT SUCCESS");
						JOptionPane.showMessageDialog(null, "회원가입을 축하드립니다", "회원가입 성공", JOptionPane.INFORMATION_MESSAGE);
					}
				} catch (ClassNotFoundException s) {
					System.out.println("JDBC연결 실패" + s.getMessage());
				} catch (SQLException s) {
					JOptionPane.showMessageDialog(null, "다시입력해주세요.", "회원가입 실패", JOptionPane.ERROR_MESSAGE);
				} finally {
					try {
						stmt.close();
						con.close();
						System.out.println("DB CLOSE");
						dispose();
					} catch (Exception x) {

					}
				}
			}
		}
	}

	class joinCencelListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if (e.getActionCommand().equals("취소")) {
				dispose();
			}
		}
	}

}
