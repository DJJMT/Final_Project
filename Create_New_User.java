//package Calendar;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import java.sql.Statement;

//CLASS COMBO_BOX CREATE NEW USER
@SuppressWarnings("serial")
class Create_New_User extends JFrame
{
	//VARIABLES
	JFrame MainPanel = new JFrame();
	JTextField f_name_input = new JTextField();
	JTextField l_name_input = new JTextField();
	JTextField username_input = new JTextField();
	JTextField password_input = new JTextField();
	JTextField access_code_input = new JTextField();
	JTextField group_input = new JTextField();
	JButton Create = new JButton("Create");
	JButton Back = new JButton("Back to Login");
	JLabel FirstName_Label = new JLabel("First Name: ");
	JLabel LastName_Label = new JLabel("Last Name: ");
	JLabel Username_Label = new JLabel("Username: ");
	JLabel Password_Label = new JLabel("Password: ");
	JLabel Access_Code_Label = new JLabel("Access Code: ");
	JLabel Group_Name_Label = new JLabel("Group Name: ");
	
	//CONSTRUCTOR
	public Create_New_User() throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException
	{
		//MAIN FRAME
		super("Team J's Scheduler | Create New User");
		setBounds(50,50,500,300);
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	    setLayout(null);
		setVisible(true);	
		  
		/*LABEL - FIRSTNAME_LABEL*/
		FirstName_Label.setLayout(null);
		FirstName_Label.setLocation(20, 10);
		FirstName_Label.setSize(80,25);
		add(FirstName_Label);
		
		//FIRST NAME INPUT BOX
		f_name_input.setLayout(null);
		f_name_input.setLocation(150,10);
		f_name_input.setSize(210,25);
		add(f_name_input);
		
		/*LABEL - LASTNAME_LABEL*/
		LastName_Label.setLayout(null);
		LastName_Label.setLocation(20, 40);
		LastName_Label.setSize(80,25);
		add(LastName_Label);
		
		//LAST NAME INPUT BOX
		l_name_input.setLayout(null);
		l_name_input.setLocation(150,40);
		l_name_input.setSize(210,25);
		add(l_name_input);
		
		/*LABEL - USERNAME_LABEL*/
		Username_Label.setLayout(null);
		Username_Label.setLocation(20, 70);
		Username_Label.setSize(80,25);
		add(Username_Label);
		
		//USERNAME INPUT BOX
		username_input.setLayout(null);
		username_input.setLocation(150,70);
		username_input.setSize(210,25);
		add(username_input);
		
		/*LABEL - PASSWORD_LABEL*/
		Password_Label.setLayout(null);
		Password_Label.setLocation(20, 100);
		Password_Label.setSize(80,25);
		add(Password_Label);
		
		//PASSWORD INPUT BOX
		password_input.setLayout(null);
		password_input.setLocation(150,100);
		password_input.setSize(210,25);
		add(password_input);
		
		/*LABEL - ACCESS_CODE_LABEL*/
		Access_Code_Label.setLayout(null);
		Access_Code_Label.setLocation(20, 130);
		Access_Code_Label.setSize(100,25);
		add(Access_Code_Label);
		
		//ACCESS CODE INPUT BOX
		access_code_input.setLayout(null);
		access_code_input.setLocation(150,130);
		access_code_input.setSize(210,25);
		add(access_code_input);
		
		/*LABEL - GROUP_NAME_LABEL*/
		Group_Name_Label.setLayout(null);
		Group_Name_Label.setLocation(20, 160);
		Group_Name_Label.setSize(80,25);
		add(Group_Name_Label);
		
		//GROUP NAME INPUT BOX
		group_input.setLayout(null);
		group_input.setLocation(150,160);
		group_input.setSize(210,25);
		add(group_input);		
		
		//CREATE BUTTON
		Create.setLayout(null);
	    Create.setLocation(150, 230);
	    Create.setSize(150, 25);
		Create.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			//ERROR CHECKING SECTION
			
			//CLOSE WINDOW WHEN CREATE BUTTON CLICKED
			setVisible(false);
				try {
					sql_add_account();
				} catch (InstantiationException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (IllegalAccessException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}          
		});
	    add(Create);
	    
	  //BACK BUTTON
	  Back.setLayout(null);
	  Back.setLocation(310, 230);
	  Back.setSize(150, 25);
	  Back.addActionListener(new ActionListener() {
		  public void actionPerformed(ActionEvent e) {
			  //CLOSE WINDOW WHEN CREATE BUTTON CLICKED
	  			setVisible(false);
	  			new LOGIN();
	  			}          
	  		});
	  	    add(Back);
	}
	//ADD ACCOUNT FUNCTION WITH SQL QUERIES
	public void sql_add_account() throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException
	{
		Class.forName("com.mysql.jdbc.Driver").newInstance();
		Statement s = GUI.con.createStatement();
		s.executeUpdate ("INSERT INTO all_users VALUES(default,"+ "4" +","+"\'"+f_name_input.getText()+"\',"+"\'"+l_name_input.getText()+"\',"+"\'"+username_input.getText()+"\',"+"\'"+password_input.getText()+"\',"+"\'"+access_code_input.getText()+"\',"+ "\'" + group_input.getText()+"\'"+");");
		s.close();
	}
}
