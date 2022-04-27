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
import javax.swing.JButton;
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

public class ProvincialElections extends JFrame {
	int EId  ;
	int key ;

	String url = "jdbc:oracle:thin:@localhost:1521:orcl";
	

	private JPanel contentPane;
	private JTextField Name;
	private JTextField year;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ProvincialElections frame = new ProvincialElections();
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
	public ProvincialElections() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 815, 529);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(75, 0, 130));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel contentPane_1 = new JPanel();
		contentPane_1.setLayout(null);
		contentPane_1.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane_1.setBackground(new Color(75, 0, 130));
		contentPane_1.setBounds(0, 0, 780, 486);
		contentPane.add(contentPane_1);
		
		JLabel lblNewLabel = new JLabel("ELECTION MANAGEMENT SYSTEM");
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setFont(new Font("Microsoft YaHei UI Light", Font.BOLD, 21));
		lblNewLabel.setBounds(10, 11, 494, 55);
		contentPane_1.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Manage Elections");
		lblNewLabel_1.setForeground(Color.WHITE);
		lblNewLabel_1.setFont(new Font("Microsoft YaHei", Font.PLAIN, 16));
		lblNewLabel_1.setBounds(10, 65, 135, 39);
		contentPane_1.add(lblNewLabel_1);
		
		JLabel lblName_1 = new JLabel("Name");
		lblName_1.setForeground(Color.WHITE);
		lblName_1.setFont(new Font("Microsoft YaHei UI Light", Font.BOLD, 14));
		lblName_1.setBounds(10, 115, 56, 39);
		contentPane_1.add(lblName_1);
		
		Name = new JTextField();
		Name.setColumns(10);
		Name.setBounds(10, 153, 164, 20);
		contentPane_1.add(Name);
		
		JLabel Year = new JLabel("YEAR");
		Year.setForeground(Color.WHITE);
		Year.setFont(new Font("Microsoft YaHei UI Light", Font.BOLD, 14));
		Year.setBounds(10, 172, 56, 39);
		contentPane_1.add(Year);
		
		year = new JTextField();
		year.setColumns(10);
		year.setBounds(10, 210, 164, 20);
		contentPane_1.add(year);
		
		
		JButton btnAddElection = new JButton("ADD ELECTION");
		btnAddElection.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				String url = "jdbc:oracle:thin:@localhost:1521:orcl";
				if(Name.getText().isEmpty() ||  Year.getText().isEmpty()){
					JOptionPane.showMessageDialog(null,"Enter proper Election Name and Year ",null, getDefaultCloseOperation());
					
				}else
				{
					try {
						Connection con = DriverManager.getConnection(url,"shoaib","103219");
						PreparedStatement Add = con.prepareStatement("insert into provincial_elections(p_election_id,p_election_year,election_name) values(? , ? , ? )");
						
						try {
				    		
							Statement st1 = con.createStatement();   
				    		ResultSet Rs1 = st1.executeQuery("select MAX(P_Election_id)+1 from provincial_elections");
				    		Rs1.next();
				    		EId = Rs1.getInt(1);
				    		Add.setInt(1, EId);
				    		
				    	}catch(Exception Ex) {
				    		JOptionPane.showMessageDialog(null,"EID error");
				    			
				    	}
						
						Add.setInt(2,Integer.parseInt( year.getText()));
						Add.setString(3, Name.getText());
						Add.executeUpdate();
						JOptionPane.showMessageDialog(null,"Election Added Successfully ", null, getDefaultCloseOperation());
						DisplayElections(); 
						con.close();
						}
					catch (Exception e1) {
						JOptionPane.showMessageDialog(null,"Didnt added due to wrong data type ", null, getDefaultCloseOperation());
					}
				}
			}
		});
			
		
		btnAddElection.setFont(new Font("Microsoft YaHei", Font.PLAIN, 14));
		btnAddElection.setBounds(10, 272, 164, 33);
		contentPane_1.add(btnAddElection);
		
		JButton btnEditElection = new JButton("EDIT ELECTION");
		btnEditElection.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(key == -1 || Name.getText().isEmpty() || Year.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null, "Missing Information");
					
					
				}else {
					try {
						String url = "jdbc:oracle:thin:@localhost:1521:orcl";
						Connection con = DriverManager.getConnection(url,"shoaib","103219");
						String Query = "update provincial_elections set election_name=? , p_election_year=? where p_election_id=?";
						PreparedStatement update = con.prepareStatement(Query) ;
						
						update.setString(1,Name.getText() );
						update.setString(2,year.getText() );
						update.setInt(3,key );
						update.executeUpdate();
						JOptionPane.showMessageDialog(null,"Updated ");
						DisplayElections();
						
					}catch(Exception e2) {
						JOptionPane.showMessageDialog(null,"Failed ");
					}
				}
			}
			
		});
		btnEditElection.setFont(new Font("Microsoft YaHei", Font.PLAIN, 14));
		btnEditElection.setBounds(10, 315, 164, 33);
		contentPane_1.add(btnEditElection);
		
		JButton btnDeleteElection = new JButton("DELETE ELECTION");
		btnDeleteElection.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (key == -1 || Name.getText().isEmpty() || Year.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null, "PLease Select the Election to be Deleted");
					
					
				}else {
					try {
						String url = "jdbc:oracle:thin:@localhost:1521:orcl";
						Connection con = DriverManager.getConnection(url,"shoaib","103219");
						String Query = "Delete from provincial_elections where p_election_id ="+key;
						Statement del = con.createStatement();
						del.executeUpdate(Query);
						JOptionPane.showMessageDialog(null,"Deleted ");
						DisplayElections();
						
					}catch(Exception e2) {
						JOptionPane.showMessageDialog(null,"Unsuccessfull");
						
					}
				}
			
			}
		});
		btnDeleteElection.setFont(new Font("Microsoft YaHei", Font.PLAIN, 14));
		btnDeleteElection.setBounds(10, 359, 164, 33);
		contentPane_1.add(btnDeleteElection);
		
		JButton btnBack = new JButton("BACK");
		btnBack.setFont(new Font("Microsoft YaHei", Font.PLAIN, 14));
		btnBack.setBounds(10, 431, 106, 33);
		contentPane_1.add(btnBack);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(224, 77, 531, 387);
		contentPane_1.add(scrollPane);
		
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				DefaultTableModel model = (DefaultTableModel)table.getModel();
				int MyIndex = table.getSelectedRow();
				key = Integer.valueOf(model.getValueAt(MyIndex, 0).toString());
				Name.setText(model.getValueAt(MyIndex, 2).toString());
				year.setText(model.getValueAt(MyIndex, 1).toString());
				
			}
		});
		scrollPane.setViewportView(table);
		table.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null},
				{null, null},
				{null, null},
			},
			new String[] {
				"Election_Name", "Election_Year"
			}
		));
		
		
		DisplayElections();
	}
	
	
	private void DisplayElections() {
		try {
			Connection con = DriverManager.getConnection(url,"shoaib","103219");
			Statement s1 = con.createStatement();
			ResultSet rs = s1.executeQuery("Select * from provincial_elections");
			table.setModel(DbUtils.resultSetToTableModel(rs));
			
			
		}catch(Exception e2) {
			
		}
		
	}
	
	
}
