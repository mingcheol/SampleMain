package Project;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import javax.swing.event.*;
import java.sql.*;

public class JoinForm extends JFrame {
	JButton OKbutton;
	JButton EXITbutton;
	JTextField jt1;
	JTextField jt2;
	JTextField jt3;
	ImageIcon image = new ImageIcon("Images/현준용철.jpg");
	JLabel ImageLabel = new JLabel(image);

	public JoinForm() {
		setTitle("CREATE");
		setSize(400, 600);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setResizable(false);
		setLocationRelativeTo(null);

		Container container = getContentPane();
		container.setLayout(null);

		JLabel jb1 = new JLabel("ID");
		jt1 = new JTextField(15);
		JLabel jb2 = new JLabel("PW");
		jt2 = new JTextField(15);
		JLabel jb3 = new JLabel("NAME");
		jt3 = new JTextField(15);
		JButton bt1 = new JButton("OK");
		JButton bt2 = new JButton("EXIT");

		container.add(jb1);
		jb1.setBounds(73, 80, 70, 70);
		container.add(jt1);
		jt1.setBounds(130, 107, 100, 15);
		container.add(jb2);
		jb2.setBounds(73, 100, 70, 70);
		container.add(jt2);
		jt2.setBounds(130, 127, 100, 15);
		container.add(jb3);
		jb3.setBounds(73, 120, 70, 70);
		container.add(jt3);
		jt3.setBounds(130, 147, 100, 15);
		container.add(bt1);
		bt1.setBounds(73, 180, 75, 20);
		container.add(bt2);
		bt2.setBounds(154, 180, 75, 20);
		container.add(ImageLabel);
		ImageLabel.setBounds(0, 0, 400, 600);

		bt2.addActionListener(new MyEXITListener());
		bt1.addActionListener(new MyOKListener());

		setVisible(true);
	}

	class MyOKListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if (e.getActionCommand().equals("OK")) {
				String in_id = jt1.getText();
				String in_pw = jt2.getText();
				String in_name = jt3.getText();
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

	class MyEXITListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if (e.getActionCommand().equals("EXIT")) {
				dispose();
			}
		}
	}
}
