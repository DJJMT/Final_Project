//package Calendar;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import java.sql.Statement;
import java.util.regex.Pattern;

//CLASS COMBO_BOX ADD ACCOUNT
@SuppressWarnings("serial")
class Combo_Box_Add_Account extends JFrame
{
	//VARIABLES
	JFrame MainPanel = new JFrame();
	JTextField f_name_input = new JTextField();
	JTextField l_name_input = new JTextField();
	JTextField username_input = new JTextField();
	JTextField password_input = new JTextField();
	JTextField access_code_input = new JTextField();
	JTextField group_input = new JTextField();
	JTextField hierarchy_input = new JTextField();
	JButton Add = new JButton("Add");
	JLabel f_name_label = new JLabel("First Name: ");
	JLabel l_name_label = new JLabel("Last Name: ");
	JLabel username_label = new JLabel("Username: ");
	JLabel password_label = new JLabel("Password: ");
	JLabel access_code_label= new JLabel("Access Code: ");
	JLabel group_label = new JLabel("Group Name: ");
	JLabel hierarchy_label = new JLabel("Hierarchy: ");
	
	
	
	//CONSTRUCTOR
	public Combo_Box_Add_Account() throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException
	{
		//MAIN FRAME
		super("Team J's Scheduler | Add Account");
		setBounds(50,50,500,300);
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	    setLayout(null);
		setVisible(true);	
		
		/*LABEL - FIRST_NAME_LABEL*/
		f_name_label.setLayout(null);
		f_name_label.setLocation(20, 10);
		f_name_label.setSize(80,25);
		add(f_name_label);
		  
		//FIIRST NAME INPUT BOX
		f_name_input.setLayout(null);
		f_name_input.setLocation(150,10);
		f_name_input.setSize(210,25);
		add(f_name_input);
		
		/*LABEL - LAST_NAME_LABEL*/
		l_name_label.setLayout(null);
		l_name_label.setLocation(20, 40);
		l_name_label.setSize(80,25);
		add(l_name_label);
		
		//LAST NAME INPUT BOX
		l_name_input.setLayout(null);
		l_name_input.setLocation(150,40);
		l_name_input.setSize(210,25);
		add(l_name_input);
		
		/*LABEL - USERNAME_LABEL*/
		username_label.setLayout(null);
		username_label.setLocation(20, 70);
		username_label.setSize(80,25);
		add(username_label);
		
		//USERNAME INPUT BOX
		username_input.setLayout(null);
		username_input.setLocation(150,70);
		username_input.setSize(210,25);
		add(username_input);
		
		/*LABEL - PASSWORD_LABEL*/
		password_label.setLayout(null);
		password_label.setLocation(20, 100);
		password_label.setSize(80,25);
		add(password_label);
		
		//PASSWORD INPUT BOX
		password_input.setLayout(null);
		password_input.setLocation(150,100);
		password_input.setSize(210,25);
		add(password_input);
		
		/*LABEL - ACCESS_CODE_LABEL*/
		access_code_label.setLayout(null);
		access_code_label.setLocation(20, 130);
		access_code_label.setSize(100,25);
		add(access_code_label);
		
		//ACCESS CODE INPUT BOX
		access_code_input.setLayout(null);
		access_code_input.setLocation(150,130);
		access_code_input.setSize(210,25);
		add(access_code_input);
		
		if(GUI.Level == 1)
		{
			/*LABEL - GROUP_LABEL*/
			group_label.setLayout(null);
			group_label.setLocation(20, 160);
			group_label.setSize(100,25);
			add(group_label);
			
			//GROUP NAME INPUT BOX
			group_input.setLayout(null);
			group_input.setLocation(150,160);
			group_input.setSize(210,25);
			add(group_input);
			
			/*LABEL - HIERARCHY_LABEL*/
			hierarchy_label.setLayout(null);
			hierarchy_label.setLocation(20, 190);
			hierarchy_label.setSize(100,25);
			add(hierarchy_label);
			
			//HIERARCHY INPUT BOX
			hierarchy_input.setLayout(null);
			hierarchy_input.setLocation(150,190);
			hierarchy_input.setSize(210,25);
			add(hierarchy_input);
		}
		
		//CREATE BUTTON
		Add.setLayout(null);
	    Add.setLocation(150, 230);
	    Add.setSize(170, 25);
		Add.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			
			//gets String value from the input of JText
			 String FirstName_Input = f_name_input.getText();
        	 String LastName_Input = l_name_input.getText();
        	 String Username_Input = username_input.getText();
        	 String Password_Input = password_input.getText();
        	 //Pattern used to make sure only certain values are typed
        	 Pattern Letters_Numbers = Pattern.compile("[a-zA-Z0-9]");
        	 Pattern Letters_Only = Pattern.compile("[a-zA-Z]");
        	 Pattern Non_White_Spaces = Pattern.compile("\\S");
        	 
        	 boolean FirstName_hasSpecialChar = Letters_Only.matcher(FirstName_Input).find();
        	 boolean LastName_hasSpecialChar = Letters_Only.matcher(LastName_Input).find();
        	 boolean Username_hasSpecialChar = Letters_Numbers.matcher(Username_Input).find();
        	 boolean Password_hasSpecialChar = Non_White_Spaces.matcher(Password_Input).find();
        	 
        	 //ERROR CHECKING
        	 if( FirstName_hasSpecialChar == false  || LastName_hasSpecialChar == false)//FirstName_Input.equals("") || LastName_Input.equals("") ||
     	   		{
     		//Checks to make sure first and last name are present
     		JOptionPane.showMessageDialog(null,
 				    "Please enter a valid first and last name!",
 				    "Name Error",
 				    JOptionPane.ERROR_MESSAGE);
     	   		}
        	 
        	 if( Username_hasSpecialChar == false)
	        	{
	        		//Checks to make sure Username present
	        		JOptionPane.showMessageDialog(null,
	    				    "Please enter a valid username!",
	    				    "Username Error",
	    				    JOptionPane.ERROR_MESSAGE);
	        	}
        	 
        	 if(Password_hasSpecialChar == false)
	        	{
        		 	//Makes sure the password does not include a "space"
	        		JOptionPane.showMessageDialog(null,
	       				    "User must have a password!",
	       				    "Password Error",
	       				    JOptionPane.ERROR_MESSAGE);
	        	}
        	 if((FirstName_hasSpecialChar == true  && LastName_hasSpecialChar == true) &&
		        	 (Username_hasSpecialChar == true) && 
		        	  Password_hasSpecialChar == true)
        	 {
			//CLOSE WINDOW WHEN CREATE BUTTON CLICKED
			
				try {
					//FUNCTION PERFORMED WHEN CREATE BUTTON CLICKED
					sql_add_account();
					setVisible(false);
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
			}          
		});
		//ADD CREATE BUTTON
	    add(Add);
	    
        //Set button to default button to select with enter key
        getRootPane().setDefaultButton(Add);
	}
	
	//ADD ACCOUNT FUNCTION WITH SQL QUERIES
	public void sql_add_account() throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException
	{
		Class.forName("com.mysql.jdbc.Driver").newInstance();
		Statement s = GUI.con.createStatement();
		//CHANGE FUNCTIONS DEPENDING ON HIERARCHY
		if(GUI.Level == 1)
		{
			s.executeUpdate ("INSERT INTO all_users VALUES(default,"+ hierarchy_input.getText() +","+"\'"+f_name_input.getText()+"\',"+"\'"+l_name_input.getText()+"\',"+"\'"+username_input.getText()+"\',"+"\'"+password_input.getText()+"\',"+"\'"+access_code_input.getText()+"\',"+ "\'" + group_input.getText()+"\'"+");");
		}
		else if(GUI.Level == 2)
		{
			s.executeUpdate ("INSERT INTO all_users VALUES(default,"+ "3" +","+"\'"+f_name_input.getText()+"\',"+"\'"+l_name_input.getText()+"\',"+"\'"+username_input.getText()+"\',"+"\'"+password_input.getText()+"\',"+"\'"+access_code_input.getText()+"\',"+ "\'" + GUI.ugn +"\'"+");");
		}
		
		//The New Account will have a group name, if this group name exists, increment the count of the group by 1. If not, add the new group to groups 	
		s.executeQuery ("SELECT Member_Count FROM groups WHERE G_Name = \'"+group_input.getText()+"\';"); 
		ResultSet rs = s.getResultSet();
		int count = 0;
		while(rs.next())
		{
			count = rs.getInt("Member_Count");
			count++;
		}
		
		if (count == 0)
			s.executeUpdate("INSERT INTO groups VALUES(default," +"\'"+group_input.getText()+"\',"+"1"+");");
		else
			s.executeUpdate("UPDATE groups SET Member_Count = \'"+count+"\' WHERE G_NAME = \'"+group_input.getText()+"\';");
		rs.close();
		s.close();
	}
}
