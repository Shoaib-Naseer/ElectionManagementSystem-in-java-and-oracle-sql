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

public class Provincial_Candidates extends JFrame {

	private JPanel contentPane;
	private JTextField Name;
	private JTextField Age;
	private JTextField Constituency;
	private JTextField PoliticalParty;
	private JTextField ElectionId;
	private JTable table;

	String url = "jdbc:oracle:thin:@localhost:1521:orcl";
	int CId  ;
	int key ;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Provincial_Candidates frame = new Provincial_Candidates();
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
	public Provincial_Candidates() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 909, 667);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(75, 0, 130));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel contentPane_1 = new JPanel();
		contentPane_1.setLayout(null);
		contentPane_1.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane_1.setBackground(new Color(75, 0, 130));
		contentPane_1.setBounds(34, 0, 914, 631);
		contentPane.add(contentPane_1);
		
		JLabel lblNewLabel = new JLabel("ELECTION MANAGEMENT SYSTEM");
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setFont(new Font("Microsoft YaHei UI Light", Font.BOLD, 21));
		lblNewLabel.setBounds(10, 30, 494, 55);
		contentPane_1.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Manage Candidates");
		lblNewLabel_1.setForeground(Color.WHITE);
		lblNewLabel_1.setFont(new Font("Microsoft YaHei", Font.PLAIN, 16));
		lblNewLabel_1.setBounds(10, 84, 164, 39);
		contentPane_1.add(lblNewLabel_1);
		
		JLabel lblName_1 = new JLabel("NAME");
		lblName_1.setForeground(Color.WHITE);
		lblName_1.setFont(new Font("Microsoft YaHei UI Light", Font.BOLD, 14));
		lblName_1.setBounds(10, 119, 56, 39);
		contentPane_1.add(lblName_1);
		
		Name = new JTextField();
		Name.setColumns(10);
		Name.setBounds(10, 150, 177, 20);
		contentPane_1.add(Name);
		
		JLabel lblName_1_1 = new JLabel("AGE");
		lblName_1_1.setForeground(Color.WHITE);
		lblName_1_1.setFont(new Font("Microsoft YaHei UI Light", Font.BOLD, 14));
		lblName_1_1.setBounds(10, 169, 56, 39);
		contentPane_1.add(lblName_1_1);
		
		Age = new JTextField();
		Age.setColumns(10);
		Age.setBounds(10, 199, 177, 20);
		contentPane_1.add(Age);
		
		JButton btnAddElection = new JButton("ADD CANDIDATES");
		btnAddElection.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String url = "jdbc:oracle:thin:@localhost:1521:orcl";
				if(Name.getText().isEmpty() || Constituency.getText().isEmpty() || Age.getText().isEmpty() || PoliticalParty.getText().isEmpty() || ElectionId.getText().isEmpty()){
					JOptionPane.showMessageDialog(null,"Incomplete Data ");
					
				}else
				{
					try {
						Connection con = DriverManager.getConnection(url,"shoaib","103219");
						PreparedStatement Add = con.prepareStatement("insert into provincial_candidates(p_candidate_id,p_candidate_name,constituency_constituency_id,political_party_id,Age,election_id) values(? , ? , ? ,?,?,? )");
						
						try {
				    		
							Statement st1 = con.createStatement();   
				    		ResultSet Rs1 = st1.executeQuery("select MAX(p_candidate_id)+1 from provincial_candidates");
				    		Rs1.next();
				    		CId = Rs1.getInt(1);
				    		Add.setInt(1, CId);
				    		
				    	}catch(Exception Ex) {
				    		JOptionPane.showMessageDialog(null,"CID error");
				    	}
						
						
						try {
							Add.setString(2, Name.getText());		
						}catch (Exception e1) {
							JOptionPane.showMessageDialog(null,"Invalid NAME ", null, getDefaultCloseOperation());
						}
						try {
							Add.setInt(3,Integer.parseInt( Constituency.getText()));		
						}catch (Exception e1) {
							JOptionPane.showMessageDialog(null,"Invalid Constituency Id ", null, getDefaultCloseOperation());
						}
						try {
							Add.setInt(4,Integer.parseInt( PoliticalParty.getText()));		
						}catch (Exception e1) {
							JOptionPane.showMessageDialog(null,"Invalid Political Id ", null, getDefaultCloseOperation());
						}	
						try {
							Add.setInt(5,Integer.parseInt( Age.getText()));	
						}catch (Exception e1) {
							JOptionPane.showMessageDialog(null,"Invalid Age ", null, getDefaultCloseOperation());
						}	
						try {
							Add.setInt(6,Integer.parseInt( ElectionId.getText()));	
						}catch (Exception e1) {
							JOptionPane.showMessageDialog(null,"Invalid Election id ", null, getDefaultCloseOperation());
						}	
				    	
				    	
						
						Add.executeUpdate();
						JOptionPane.showMessageDialog(null,"Candidate Added ", null, getDefaultCloseOperation());
						DisplayCandidates(); 
						con.close();
						}
					catch (Exception e1) {
						JOptionPane.showMessageDialog(null,"Didnt Added ", null, getDefaultCloseOperation());
					}
						
						
			}
				
				
			}
		});
		btnAddElection.setFont(new Font("Microsoft YaHei", Font.PLAIN, 14));
		btnAddElection.setBounds(10, 426, 177, 33);
		contentPane_1.add(btnAddElection);
		
		JButton btnDeleteElection = new JButton("DELETE CANDIDATES");
		btnDeleteElection.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (key == -1 ||Name.getText().isEmpty() || Constituency.getText().isEmpty() || Age.getText().isEmpty() || PoliticalParty.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null, "PLease Select the Proper Candidate to be Deleted");
					
					
				}else {
					try {
						String url = "jdbc:oracle:thin:@localhost:1521:orcl";
						Connection con = DriverManager.getConnection(url,"shoaib","103219");
						String Query = "Delete from provincial_candidates where p_candidate_id ="+key;
						Statement del = con.createStatement();
						del.executeUpdate(Query);
						JOptionPane.showMessageDialog(null,"Deleted ");
						DisplayCandidates();
						
					}catch(Exception e2) {
						JOptionPane.showMessageDialog(null,"Unsuccessfull");
						
					}
				}
				
			}
		});
		btnDeleteElection.setFont(new Font("Microsoft YaHei", Font.PLAIN, 14));
		btnDeleteElection.setBounds(10, 514, 177, 33);
		contentPane_1.add(btnDeleteElection);
		
		JButton btnBack = new JButton("BACK");
		btnBack.setFont(new Font("Microsoft YaHei", Font.PLAIN, 14));
		btnBack.setBounds(10, 587, 106, 33);
		contentPane_1.add(btnBack);
		
		JLabel lblName_1_1_1 = new JLabel("CONSTITUENCY ID");
		lblName_1_1_1.setForeground(Color.WHITE);
		lblName_1_1_1.setFont(new Font("Microsoft YaHei UI Light", Font.BOLD, 14));
		lblName_1_1_1.setBounds(10, 220, 141, 39);
		contentPane_1.add(lblName_1_1_1);
		
		Constituency = new JTextField();
		Constituency.setColumns(10);
		Constituency.setBounds(10, 255, 177, 20);
		contentPane_1.add(Constituency);
		
		JLabel lblName_1_1_1_1 = new JLabel("POLITICAL PARTY ID");
		lblName_1_1_1_1.setForeground(Color.WHITE);
		lblName_1_1_1_1.setFont(new Font("Microsoft YaHei UI Light", Font.BOLD, 14));
		lblName_1_1_1_1.setBounds(10, 270, 152, 39);
		contentPane_1.add(lblName_1_1_1_1);
		
		PoliticalParty = new JTextField();
		PoliticalParty.setColumns(10);
		PoliticalParty.setBounds(10, 307, 177, 20);
		contentPane_1.add(PoliticalParty);
		
		JButton btnEditCandidates = new JButton("EDIT CANDIDATES");
		btnEditCandidates.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(key == -1 || Age.getText().isEmpty() || Constituency.getText().isEmpty() || PoliticalParty.getText().isEmpty() || ElectionId.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null, "Missing Information");
					
					
				}else {
					try {
						String url = "jdbc:oracle:thin:@localhost:1521:orcl";
						Connection con = DriverManager.getConnection(url,"shoaib","103219");
						String Query = "update PROVINCIAL_candidates set p_candidate_name=? , constituency_constituency_id=? , political_party_id =?, age = ?, election_id=? where p_candidate_id=?";
						PreparedStatement update = con.prepareStatement(Query) ;
						try {
							update.setString(1,Name.getText() );
						}catch(Exception e3) {
							JOptionPane.showMessageDialog(null, "Name error ");
						}
						try {
							update.setInt(2,Integer.parseInt(Constituency.getText()) );
						}catch(Exception e3) {
							JOptionPane.showMessageDialog(null, "Constituency error ");
						}
						try {
							update.setString(3,PoliticalParty.getText() );
						}catch(Exception e3) {
							JOptionPane.showMessageDialog(null, "party error ");
						}
						try {
							update.setInt(4, Integer.parseInt(Age.getText()) );
						}catch(Exception e3) {
							JOptionPane.showMessageDialog(null, "Age error ");
						}
						try {
							update.setInt(5, Integer.parseInt(ElectionId.getText()) );
						}catch(Exception e3) {
							JOptionPane.showMessageDialog(null, "ElectionId error ");
						}
						try {
							update.setInt(6, key);
						}catch(Exception e3) {
							JOptionPane.showMessageDialog(null, "Key error ");
						}
						
						update.executeUpdate();
						JOptionPane.showMessageDialog(null,"Updated ");
						DisplayCandidates();
						
					}catch(Exception e2) {
						JOptionPane.showMessageDialog(null,"Failed ");
					}
				}
		
			}
		});
		btnEditCandidates.setFont(new Font("Microsoft YaHei", Font.PLAIN, 14));
		btnEditCandidates.setBounds(10, 470, 177, 33);
		contentPane_1.add(btnEditCandidates);
		
		JLabel lblName_1_1_1_1_1 = new JLabel("ELECTION ID");
		lblName_1_1_1_1_1.setForeground(Color.WHITE);
		lblName_1_1_1_1_1.setFont(new Font("Microsoft YaHei UI Light", Font.BOLD, 14));
		lblName_1_1_1_1_1.setBounds(10, 322, 152, 39);
		contentPane_1.add(lblName_1_1_1_1_1);
		
		ElectionId = new JTextField();
		ElectionId.setColumns(10);
		ElectionId.setBounds(10, 359, 177, 20);
		contentPane_1.add(ElectionId);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(234, 96, 592, 490);
		contentPane_1.add(scrollPane);
		
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
		
				DefaultTableModel model = (DefaultTableModel)table.getModel();
				int MyIndex = table.getSelectedRow();
				key = Integer.valueOf(model.getValueAt(MyIndex, 0).toString());
				Name.setText(model.getValueAt(MyIndex, 1).toString());
				Constituency.setText(model.getValueAt(MyIndex, 2).toString());
				PoliticalParty.setText(model.getValueAt(MyIndex, 4).toString());
				Age.setText(model.getValueAt(MyIndex, 3).toString());
				ElectionId.setText(model.getValueAt(MyIndex,5).toString());
			
			}
			
		});
		scrollPane.setViewportView(table);
		table.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null, null, null},
				{null, null, null, null, null},
			},
			new String[] {
				"Candidate_Id", "age", "Candiadate political party", "Candidate_Constituency", "Candidate_Name"
			}
		) {
			boolean[] columnEditables = new boolean[] {
				true, true, false, true, true
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		table.getColumnModel().getColumn(1).setPreferredWidth(31);
		table.getColumnModel().getColumn(2).setResizable(false);
		table.getColumnModel().getColumn(2).setPreferredWidth(144);
		table.getColumnModel().getColumn(3).setPreferredWidth(133);
		table.getColumnModel().getColumn(4).setPreferredWidth(142);
		DisplayCandidates();
	}

	private void DisplayCandidates() {
		try {
			Connection con = DriverManager.getConnection(url,"shoaib","103219");
			Statement s1 = con.createStatement();
			ResultSet rs = s1.executeQuery("Select * from PROVINCIAL_candidates");
			table.setModel(DbUtils.resultSetToTableModel(rs));
			
			
		}catch(Exception e2) {
			
		}
		
	}
}
