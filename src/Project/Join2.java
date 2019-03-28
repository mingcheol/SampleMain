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


//����

public class Join2 extends JFrame {
	private Container contentPane; // Ʋ���´� ĭ�� ���� �г�
	private JTextField idCreateField; // ���̵� ���� ĭ
	private static JButton idDuplicationBtn; // ���̵��ߺ�Ȯ�� ��ư
	private JPasswordField pwCreateField; // ��й�ȣ ���� ĭ
	private JPasswordField pwCheckField; // ��й�ȣ Ȯ�� ĭ
	private JTextField nicName; // �г��� ĭ
	private JTextField birthDay; // ������� ĭ
	private JTextField tel_Field; // ����ó ĭ
	private JTextField email_Field; // �̸��� ĭ
	private static JLabel copyRight; // ���۱� ��
	private ImageIcon image = new ImageIcon("Images/���ؿ�ö.jpg"); // ���ȭ������ �� �̹��� �ε�
	private JLabel ImageLabel = new JLabel(image); // �̹����� ���̺� ����

	private static JButton joinClearButton; // �����ϱ� ��ư
	private static JButton joinCencelButton; // ������� ��ư

	static Dialog msgDlg; // �����˾�â
	static Label msgLabel; // ������ �˷��ִ� ��????

	public Join2() {
		setTitle("COZY TALK ȸ������");
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

		JLabel joinTitle = new JLabel("�ۡۡۡۡ� ȸ������ �ۡۡۡۡ�"); // Ÿ��Ʋ
		joinTitle.setForeground(new Color(255, 153, 153));
		joinTitle.setFont(new Font("�������", Font.BOLD, 30));
		joinTitle.setBounds(10, 23, 500, 58);
		panel.add(joinTitle);

		JLabel idLabel = new JLabel("���̵� : "); // ���̵� :
		idLabel.setForeground(new Color(255, 153, 153));
		idLabel.setFont(new Font("�������", Font.BOLD, 17));
		idLabel.setBounds(77, 111, 100, 25);
		panel.add(idLabel);

		idCreateField = new JTextField();
		idCreateField.setBounds(161, 110, 150, 30);
		idCreateField.setFont(new Font("�������", Font.BOLD, 20));
		panel.add(idCreateField);
		idCreateField.setColumns(10);

		idDuplicationBtn = new JButton("���̵� �ߺ�Ȯ��");
		idDuplicationBtn.setBackground(new Color(255, 153, 153));
		idDuplicationBtn.setForeground(Color.WHITE);
		idDuplicationBtn.setFont(new Font("�������", Font.BOLD, 15));
		idDuplicationBtn.setBounds(320, 111, 150, 27);
		panel.add(idDuplicationBtn);

		idDuplicationBtn.addActionListener(new MyCreateListener());

		JLabel pwLabel = new JLabel("��й�ȣ :"); // ��й�ȣ :
		pwLabel.setForeground(new Color(255, 153, 153));
		pwLabel.setFont(new Font("�������", Font.BOLD, 17));
		pwLabel.setBounds(59, 172, 100, 20);
		panel.add(pwLabel);

		pwCreateField = new JPasswordField();
		pwCreateField.setColumns(10);
		pwCreateField.setBounds(161, 166, 150, 30);
		pwCreateField.setFont(new Font("�������", Font.BOLD, 20));
		panel.add(pwCreateField);

		JLabel pwCheckLabel = new JLabel("��й�ȣ Ȯ�� : "); // ��й�ȣ Ȯ�� :
		pwCheckLabel.setForeground(new Color(255, 153, 153));
		pwCheckLabel.setFont(new Font("�������", Font.BOLD, 17));
		pwCheckLabel.setBounds(22, 220, 140, 30);
		panel.add(pwCheckLabel);

		pwCheckField = new JPasswordField();
		pwCheckField.setColumns(10);
		pwCheckField.setBounds(161, 220, 150, 30);
		pwCheckField.setFont(new Font("�������", Font.BOLD, 20));
		panel.add(pwCheckField);

		JLabel nicNameLabel = new JLabel("�г��� :"); // �г��� :
		nicNameLabel.setForeground(new Color(255, 153, 153));
		nicNameLabel.setFont(new Font("�������", Font.BOLD, 17));
		nicNameLabel.setBounds(77, 271, 70, 30);
		panel.add(nicNameLabel);

		nicName = new JTextField();
		nicName.setColumns(10);
		nicName.setBounds(161, 270, 150, 30);
		nicName.setFont(new Font("�������", Font.BOLD, 20));
		panel.add(nicName);

		JLabel birthDayLabel = new JLabel("������� :"); // ������� ��
		birthDayLabel.setForeground(new Color(255, 153, 153));
		birthDayLabel.setFont(new Font("�������", Font.BOLD, 17));
		birthDayLabel.setBounds(59, 321, 130, 30);
		panel.add(birthDayLabel);

		birthDay = new JTextField();
		birthDay.setColumns(10);
		birthDay.setBounds(161, 323, 150, 30);
		birthDay.setFont(new Font("�������", Font.BOLD, 20));
		panel.add(birthDay);

		JLabel tel_Label = new JLabel("����ó :"); // ����ó :
		tel_Label.setForeground(new Color(255, 153, 153));
		tel_Label.setFont(new Font("�������", Font.BOLD, 17));
		tel_Label.setBounds(77, 373, 130, 30);
		panel.add(tel_Label);

		tel_Field = new JTextField();
		tel_Field.setColumns(10);
		tel_Field.setBounds(161, 375, 150, 30);
		tel_Field.setFont(new Font("�������", Font.BOLD, 20));
		panel.add(tel_Field);

		JLabel email_Label = new JLabel("�̸��� :"); // �̸��� :
		email_Label.setForeground(new Color(255, 153, 153));
		email_Label.setFont(new Font("�������", Font.BOLD, 17));
		email_Label.setBounds(77, 425, 130, 30);
		panel.add(email_Label);

		email_Field = new JTextField();
		email_Field.setColumns(10);
		email_Field.setBounds(161, 425, 150, 30);
		email_Field.setFont(new Font("�������", Font.BOLD, 20));
		panel.add(email_Field);

		joinClearButton= new JButton("ȸ������");
		joinClearButton.setBackground(new Color(255, 153, 153));
		joinClearButton.setForeground(Color.WHITE);
		joinClearButton.setFont(new Font("�������", Font.BOLD, 20));
		joinClearButton.setBounds(110, 500, 120, 40);
		panel.add(joinClearButton);

		joinCencelButton = new JButton("���");
		joinCencelButton.setBackground(new Color(255, 153, 153));
		joinCencelButton.setForeground(Color.WHITE);
		joinCencelButton.setFont(new Font("�������", Font.BOLD, 20));
		joinCencelButton.setBounds(250, 500, 120, 40);
		panel.add(joinCencelButton);

		copyRight = new JLabel();
		copyRight.setBounds(161, 550, 150, 30);
		copyRight.setFont(new Font("�������", Font.BOLD, 20));
		panel.add(copyRight);
		
		joinClearButton.addActionListener(new joinClearListener());
		joinCencelButton.addActionListener(new joinCencelListener());
		
		setVisible(true);
	}

	class MyCreateListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if (e.getActionCommand().equals("���̵� �ߺ�Ȯ��")) {
				// �����ͺ��̽� ���̺� �ִ� �������̵�� ���ؼ� getText~equal�ؼ� ������ alert�����(���â)
				JOptionPane.showMessageDialog(null, "���̵� �ߺ��˴ϴ�. �ٽ��Է����ּ���.", "���̵� �ߺ� Ȯ��", JOptionPane.ERROR_MESSAGE);
				ServerMain.launch();
			}
		}
	}

	class joinClearListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if (e.getActionCommand().equals("ȸ������")) {
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
					System.out.println("JDBC DRIVER �ε� ����");
					con = DriverManager.getConnection(url, user, password);
					System.out.println("DB �ε� ����");
					stmt = con.createStatement();
					String sql = "INSERT INTO USERINFO VALUES('" + in_id + "', '" + in_pw + "', '" + in_name + "')";
					rs = stmt.executeUpdate(sql);
					if (rs == 1) {
						// System.out.println("INSERT SUCCESS");
						JOptionPane.showMessageDialog(null, "ȸ�������� ���ϵ帳�ϴ�", "ȸ������ ����", JOptionPane.INFORMATION_MESSAGE);
					}
				} catch (ClassNotFoundException s) {
					System.out.println("JDBC���� ����" + s.getMessage());
				} catch (SQLException s) {
					JOptionPane.showMessageDialog(null, "�ٽ��Է����ּ���.", "ȸ������ ����", JOptionPane.ERROR_MESSAGE);
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
			if (e.getActionCommand().equals("���")) {
				dispose();
			}
		}
	}

}
