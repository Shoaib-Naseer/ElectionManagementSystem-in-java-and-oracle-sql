package database_Project;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class Login extends JFrame {

	private JPanel contentPane;
	private JTextField cnic;
	private JPasswordField password;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login frame = new Login();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Login() {
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(75, 0, 130));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("ELECTION MANAGEMENT SYSTEM");
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setFont(new Font("Microsoft YaHei UI Light", Font.BOLD, 19));
		lblNewLabel.setBounds(43, 26, 344, 39);
		contentPane.add(lblNewLabel);
		
		JLabel lblName = new JLabel("PASSWORD");
		lblName.setForeground(Color.WHITE);
		lblName.setFont(new Font("Microsoft YaHei UI Light", Font.BOLD, 15));
		lblName.setBounds(43, 160, 94, 39);
		contentPane.add(lblName);
		
		JLabel lblName_1 = new JLabel("CNIC");
		lblName_1.setForeground(Color.WHITE);
		lblName_1.setFont(new Font("Microsoft YaHei UI Light", Font.BOLD, 15));
		lblName_1.setBounds(43, 117, 76, 39);
		contentPane.add(lblName_1);
		
		JComboBox UserType = new JComboBox();
		UserType.setForeground(new Color(75, 0, 130));
		UserType.setBackground(new Color(255, 255, 255));
		UserType.setModel(new DefaultComboBoxModel(new String[] {"ADMIN", "USER"}));
		UserType.setBounds(270, 94, 94, 22);
		contentPane.add(UserType);
		 
		JButton btnLogin = new JButton("Login");
		btnLogin.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(UserType.getSelectedIndex() == -1) {
					JOptionPane.showMessageDialog(null, "PLease select admin or user !");
				}
				else if(UserType.getSelectedIndex() == 0){
					if(cnic.getText().isEmpty() || password.getText().isEmpty() ) {
						JOptionPane.showMessageDialog(null,"PLease Enter the Cnic and Password Both ");
					}
					else if(cnic.getText().equals("Admin") && password.getText().equals("admin"))
					{
						
						new Candidate().setVisible(true);
						dispose();
						
					}else {
						JOptionPane.showMessageDialog(null, "Wrong Username or Password ");
						cnic.setText("");
						password.setText("");
					}
					
				}else {
					String Query = " select * from voters where users_cnic= "+cnic.getText().toString()+"and voter_password= "+password.getText().toString()+"";
					try {
						String url = "jdbc:oracle:thin:@localhost:1521:orcl";
						Connection con = DriverManager.getConnection(url,"shoaib","103219");
						Statement st1 = con.createStatement(); 
						ResultSet Rs1 = st1.executeQuery(Query);
						if(Rs1.next()) {
							new NationalVoting(Rs1.getInt(1)).setVisible(true);
							dispose();
						}
						else {
							JOptionPane.showMessageDialog(null, "Invalid user cnic or password ");
						}
					}catch(Exception e1) {
						JOptionPane.showMessageDialog(null,"Invalid Credentials");
				}
				
				}}
		});
		btnLogin.setFont(new Font("Microsoft YaHei", Font.PLAIN, 14));
		btnLogin.setBounds(254, 202, 110, 33);
		contentPane.add(btnLogin);
		
		cnic = new JTextField();
		cnic.setBounds(153, 128, 211, 20);
		contentPane.add(cnic);
		cnic.setColumns(10);
		
		password = new JPasswordField();
		password.setBounds(153, 171, 211, 20);
		contentPane.add(password);
	}
}
