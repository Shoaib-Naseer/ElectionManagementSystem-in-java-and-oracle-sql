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
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import net.proteanit.sql.DbUtils;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class Voters extends JFrame {
	String url = "jdbc:oracle:thin:@localhost:1521:orcl";
	int key ;
	
	private JPanel contentPane;
	private JTextField Age;
	private JTextField Password;
	private JTextField Cnic;
	private JTable table;
	private JTextField Constituency;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Voters frame = new Voters();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	int VId;

	/**
	 * Create the frame.
	 */
	public Voters() {
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 920, 671);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(75, 0, 130));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("ELECTION MANAGEMENT SYSTEM");
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setFont(new Font("Microsoft YaHei UI Light", Font.BOLD, 21));
		lblNewLabel.setBounds(10, 11, 494, 55);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Manage Voters");
		lblNewLabel_1.setForeground(Color.WHITE);
		lblNewLabel_1.setFont(new Font("Microsoft YaHei", Font.PLAIN, 16));
		lblNewLabel_1.setBounds(10, 57, 164, 39);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblName_1_1 = new JLabel("AGE");
		lblName_1_1.setForeground(Color.WHITE);
		lblName_1_1.setFont(new Font("Microsoft YaHei UI Light", Font.BOLD, 14));
		lblName_1_1.setBounds(13, 107, 56, 39);
		contentPane.add(lblName_1_1);
		
		Age = new JTextField();
		Age.setColumns(10);
		Age.setBounds(13, 137, 177, 20);
		contentPane.add(Age);
		
		JLabel lblName_1_2 = new JLabel("GENDER");
		lblName_1_2.setForeground(Color.WHITE);
		lblName_1_2.setFont(new Font("Microsoft YaHei UI Light", Font.BOLD, 14));
		lblName_1_2.setBounds(13, 157, 76, 39);
		contentPane.add(lblName_1_2);
		
		JComboBox Gender = new JComboBox();
		Gender.setModel(new DefaultComboBoxModel(new String[] {"Male", "Female"}));
		Gender.setForeground(new Color(75, 0, 130));
		Gender.setBackground(Color.WHITE);
		Gender.setBounds(13, 191, 111, 22);
		contentPane.add(Gender);
		
		JLabel lblName_1_1_1 = new JLabel("PASSWORD");
		lblName_1_1_1.setForeground(Color.WHITE);
		lblName_1_1_1.setFont(new Font("Microsoft YaHei UI Light", Font.BOLD, 14));
		lblName_1_1_1.setBounds(13, 210, 141, 39);
		contentPane.add(lblName_1_1_1);
		
		Password = new JTextField();
		Password.setColumns(10);
		Password.setBounds(13, 245, 177, 20);
		contentPane.add(Password);
		
		JLabel lblName_1_1_1_1 = new JLabel("CNIC");
		lblName_1_1_1_1.setForeground(Color.WHITE);
		lblName_1_1_1_1.setFont(new Font("Microsoft YaHei UI Light", Font.BOLD, 14));
		lblName_1_1_1_1.setBounds(13, 262, 152, 39);
		contentPane.add(lblName_1_1_1_1);
		
		Cnic = new JTextField();
		Cnic.setColumns(10);
		Cnic.setBounds(13, 299, 177, 20);
		contentPane.add(Cnic);
		DisplayVoters();
		JButton btnAddVoters = new JButton("ADD VOTERS");
		btnAddVoters.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				String url = "jdbc:oracle:thin:@localhost:1521:orcl";
				if(Password.getText().isEmpty() || Constituency.getText().isEmpty() || Age.getText().isEmpty()){
					JOptionPane.showMessageDialog(null,"Incomplete Data ");
					
				}else
				{
					try {
						Connection con = DriverManager.getConnection(url,"shoaib","103219");
						PreparedStatement Add = con.prepareStatement("insert into voters(voter_id,voter_password,constituency_constituency_id,users_cnic,age) values( ?, ? , ? ,?,? )");
						
						try {
							Statement st1 = con.createStatement();   
				    		ResultSet Rs1 = st1.executeQuery("select MAX(voter_id)+1 from voters");
				    		Rs1.next();
				    		VId = Rs1.getInt(1);
				    		Add.setInt(1, VId);
				    	}catch(Exception Ex) {
				    		JOptionPane.showMessageDialog(null,"VoterID error");
				    	}	
						try {
							Add.setString(2, Password.getText());		
						}catch(Exception Ex) {
				    		JOptionPane.showMessageDialog(null,"Paassword error");
				    	}
						try {
							Add.setInt(3,Integer.parseInt( Constituency.getText()));	
					    			
						}catch(Exception Ex) {
				    		JOptionPane.showMessageDialog(null,"Constituency Doesnt Exists");
				    	}
						try {
							if(Cnic.getText().length()==13) {
							Add.setString(4, Cnic.getText());	}
							else {
								JOptionPane.showMessageDialog(null,"PLease Enter a valid 11 number CNIC");
							}
					    			
						}catch(Exception Ex) {
				    		JOptionPane.showMessageDialog(null,"CNIC Error");
				    	}
						try {
							if (Integer.parseInt( Age.getText()) < 18) {
								JOptionPane.showMessageDialog(null, "Age must be greater than 17 ");
							}
							else {
								Add.setInt(5,Integer.parseInt( Age.getText()));
							}
						}catch(Exception Ex) {
				    		JOptionPane.showMessageDialog(null,"Age error");
				    	}
				    	
						Add.executeUpdate();
						JOptionPane.showMessageDialog(null,"Voter Added ", null, getDefaultCloseOperation());
						DisplayVoters(); 
						con.close();
						}
					catch (Exception e1) {
						JOptionPane.showMessageDialog(null,"Voter Didnt Added ");
					}
						
						
			}
				
			}
		});
		btnAddVoters.setFont(new Font("Microsoft YaHei", Font.PLAIN, 14));
		btnAddVoters.setBounds(10, 416, 177, 33);
		contentPane.add(btnAddVoters);
		
		JButton btnEditVoters = new JButton("EDIT VOTERS");
		btnEditVoters.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(key == -1 || Age.getText().isEmpty() || Constituency.getText().isEmpty() || Cnic.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null, "Missing Information");
					
					
				}else {
					try {
						String url = "jdbc:oracle:thin:@localhost:1521:orcl";
						Connection con = DriverManager.getConnection(url,"shoaib","103219");
						String Query = "update voters set voter_password=? , constituency_constituency_id=? , users_cnic =?, age = ? where voter_id=?";
						PreparedStatement update = con.prepareStatement(Query) ;
						
						update.setString(1,Password.getText() );
						update.setInt(2,Integer.parseInt(Constituency.getText()) );

						update.setString(3,Cnic.getText() );
						update.setInt(4, Integer.parseInt(Age.getText()) );
						update.setInt(5, key);
						update.executeUpdate();
						JOptionPane.showMessageDialog(null,"Updated ");
						DisplayVoters();
						
					}catch(Exception e2) {
						JOptionPane.showMessageDialog(null,"Failed ");
					}
				}
			}
				
			
			
		});
		btnEditVoters.setFont(new Font("Microsoft YaHei", Font.PLAIN, 14));
		btnEditVoters.setBounds(10, 461, 177, 33);
		contentPane.add(btnEditVoters);
		
		JButton btnDeleteVoters = new JButton("DELETE VOTERS");
		btnDeleteVoters.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
					if (Cnic.getText().isEmpty() || Constituency.getText().isEmpty() || Age.getText().isEmpty() || Password.getText().isEmpty()) {
						JOptionPane.showMessageDialog(null, "PLease Select the Proper Voter to be Deleted");
						
						
					}else {
						try {
							String url = "jdbc:oracle:thin:@localhost:1521:orcl";
							Connection con = DriverManager.getConnection(url,"shoaib","103219");
							String Query = "Delete from voters where voter_id ="+key;
							Statement del = con.createStatement();
							del.executeUpdate(Query);
							JOptionPane.showMessageDialog(null,"Deleted ");
							DisplayVoters();
							
						}catch(Exception e2) {
							JOptionPane.showMessageDialog(null,"Unsuccessfull");
							
						}
					}
				
			}
		});
		btnDeleteVoters.setFont(new Font("Microsoft YaHei", Font.PLAIN, 14));
		btnDeleteVoters.setBounds(10, 505, 177, 33);
		contentPane.add(btnDeleteVoters);
		
		JButton btnBack = new JButton("BACK");
		btnBack.setFont(new Font("Microsoft YaHei", Font.PLAIN, 14));
		btnBack.setBounds(10, 568, 106, 33);
		contentPane.add(btnBack);
		
		JScrollPane VotersTable = new JScrollPane();
		VotersTable.setBounds(215, 79, 665, 525);
		contentPane.add(VotersTable);
		
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				DefaultTableModel model = (DefaultTableModel)table.getModel();
				int MyIndex = table.getSelectedRow();
				key = Integer.valueOf(model.getValueAt(MyIndex, 0).toString());
				Password.setText(model.getValueAt(MyIndex, 1).toString());
				Constituency.setText(model.getValueAt(MyIndex, 2).toString());
				Cnic.setText(model.getValueAt(MyIndex, 3).toString());
				Age.setText(model.getValueAt(MyIndex, 4).toString());
				
				
			}
		});
		table.setFont(new Font("Tahoma", Font.PLAIN, 10));
		VotersTable.setViewportView(table);
		table.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
			},
			new String[] {
				"Voter_id", "Voter_Password", "Voter_Constituency", "Voter_Cnic", "Voter_Age"
			}
		));
		
		JLabel lblName_1_1_1_1_1 = new JLabel("CONSTITUENCY");
		lblName_1_1_1_1_1.setForeground(Color.WHITE);
		lblName_1_1_1_1_1.setFont(new Font("Microsoft YaHei UI Light", Font.BOLD, 14));
		lblName_1_1_1_1_1.setBounds(10, 314, 152, 39);
		contentPane.add(lblName_1_1_1_1_1);
		
		Constituency = new JTextField();
		Constituency.setColumns(10);
		Constituency.setBounds(10, 351, 177, 20);
		contentPane.add(Constituency);
		DisplayVoters();
	
	}
	private void DisplayVoters() {
		try {
			Connection con = DriverManager.getConnection(url,"shoaib","103219");
			Statement s1 = con.createStatement();
			ResultSet rs = s1.executeQuery("Select * from voters");
			table.setModel(DbUtils.resultSetToTableModel(rs));
			
			
		}catch(Exception e2) {
			
		}
		
	}
	

}
