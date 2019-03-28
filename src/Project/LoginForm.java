package Project;

import java.awt.Component;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.BevelBorder;

public class LoginForm extends JFrame {

	JTextField tf1;
	JPasswordField tf2;
	JButton LoginButton;
	JButton CreateButton;
	ImageIcon image = new ImageIcon("Images/현준용철.jpg");
	JLabel ImageLabel = new JLabel(image);

	public LoginForm() {
		setTitle("COZY TALK");
		setSize(500, 640);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		setLocationRelativeTo(null);

		Container c = getContentPane();
		c.setLayout(new GridLayout());
		c.add(new MainPanel());

		setVisible(true);

		c.setFocusable(true);
		c.requestFocus();
		c.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent m) {
				Component com = (Component) m.getSource();
				com.setFocusable(true);
				com.requestFocus();
			}
		});

		new ServerMain();
	}

	class MainPanel extends JPanel {
		public MainPanel() {
			setLayout(null);

			tf1 = new JTextField("아이디", 15);
			tf2 = new JPasswordField("비밀번호", 15);
			tf2.setEchoChar((char) 0);
			LoginButton = new JButton("LoginForm");
			CreateButton = new JButton("JoinForm");

			tf1.setBounds(105, 270, 180, 20);
			tf2.setBounds(105, 295, 180, 20);
			ImageLabel.setBounds(0, 0, 500, 640);
			LoginButton.setBounds(105, 320, 88, 20);
			CreateButton.setBounds(196, 320, 88, 20);

			add(tf1);
			add(tf2);
			add(LoginButton);
			add(CreateButton);
			add(ImageLabel);
			tf1.addFocusListener(new MyFocusListener());
			tf2.addFocusListener(new MyFocusListener());
			LoginButton.addActionListener(new LoginListener());
			CreateButton.addActionListener(new MyCreateListener());
		}
	}

	class LoginListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if (e.getActionCommand().equals("LoginForm")) {
				System.out.println(tf1.getText());
				int state = 0;
				String loginID = tf1.getText();
				String loginPW = new String(tf2.getPassword());

				String driver = "oracle.jdbc.driver.OracleDriver";
				String url = "jdbc:oracle:thin:@localhost:1521:xe";
				String user = "YOON";
				String password = "2529";

				Connection con = null;
				Statement stmt = null;
				ResultSet rs = null;

				try {
					Class.forName(driver);
					System.out.println("JDBC DRIVER CONNECT");
					con = DriverManager.getConnection(url, user, password);
					System.out.println("DB CONNECT");
					stmt = con.createStatement();
					String sql = "SELECT * FROM USERINFO WHERE IN_ID = '" + loginID + "'";
					rs = stmt.executeQuery(sql);

					while (rs.next()) {
						String gstID = rs.getString("IN_ID");
						String gstPW = rs.getString("IN_PW");
						String gstNAME = rs.getString("IN_NAME");
						if (gstID.equals(loginID) && gstPW.equals(loginPW)) {
							JOptionPane.showMessageDialog(null, gstNAME + "님 환영합니다.", "로그인 성공",
									JOptionPane.INFORMATION_MESSAGE);
							state = 1;
						}
					}
					if (state == 0) {
						JOptionPane.showMessageDialog(null, "다시 입력해주세요..", "로그인 실패", JOptionPane.ERROR_MESSAGE);
					}
				} catch (SQLException s) {
					System.out.println("Error : " + s.toString());
				} catch (ClassNotFoundException s) {
					System.out.println("Error : " + s.toString());
				} finally {
					try {
						rs.close();
						stmt.close();
						con.close();
						System.out.println("DB CLOSE");
					} catch (Exception x) {

					}
				}
			}
			dispose();
		}
	}

	class MyCreateListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if (e.getActionCommand().equals("JoinForm")) {
				new Join2();
			}
		}
	}

	class MyFocusListener implements FocusListener {
		public void focusGained(FocusEvent f) {
			if (f.getSource() == tf1) {
				if (tf1.getText().equals("아이디")) {
					tf1.setText("");
				}
			} else if (f.getSource() == tf2) {
				if (new String(tf2.getPassword()).equals("비밀번호")) {
					tf2.setEchoChar('*');
					tf2.setText("");
				}
			}
		}

		public void focusLost(FocusEvent f) {
			if (tf1.getText().equals("")) {
				tf1.setText("아이디");
			} else if (new String(tf2.getPassword()).equals("")) {
				tf2.setEchoChar((char) 0);
				tf2.setText("비밀번호");
			}
		}
	}
}