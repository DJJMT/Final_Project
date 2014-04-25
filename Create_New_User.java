//package Calendar;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import java.sql.Statement;
import java.util.regex.Pattern;


//CLASS COMBO_BOX CREATE NEW USER 
@SuppressWarnings("serial")
class Create_New_User extends JFrame
{
	//VARIABLES
	JFrame MainPanel = new JFrame();
	JTextField f_name_input = new JTextField();
	JTextField l_name_input = new JTextField();
	JTextField username_input = new JTextField();
	JPasswordField password_input = new JPasswordField();
	JPasswordField password_veri_input = new JPasswordField();
	JTextField access_code_input = new JTextField();
	JTextField group_input = new JTextField();
	JButton Back = new JButton("Back to Login");
	JButton Create = new JButton("Create");
	JLabel FirstName_Label = new JLabel("First Name: ");
	JLabel LastName_Label = new JLabel("Last Name: ");
	JLabel Username_Label = new JLabel("Username: ");
	JLabel Password_Label = new JLabel("Password: ");
	JLabel Password_Veri_Label = new JLabel("Re-type Password: ");
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
		f_name_input.setLocation(170,10);
		f_name_input.setSize(210,25);
		add(f_name_input);
		
		/*LABEL - LASTNAME_LABEL*/
		LastName_Label.setLayout(null);
		LastName_Label.setLocation(20, 40);
		LastName_Label.setSize(80,25);
		add(LastName_Label);
		
		//LAST NAME INPUT BOX
		l_name_input.setLayout(null);
		l_name_input.setLocation(170,40);
		l_name_input.setSize(210,25);
		add(l_name_input);
		
		/*LABEL - USERNAME_LABEL*/
		Username_Label.setLayout(null);
		Username_Label.setLocation(20, 70);
		Username_Label.setSize(80,25);
		add(Username_Label);
		
		//USERNAME INPUT BOX
		username_input.setLayout(null);
		username_input.setLocation(170,70);
		username_input.setSize(210,25);
		add(username_input);
		
		/*LABEL - PASSWORD_LABEL*/
		Password_Label.setLayout(null);
		Password_Label.setLocation(20, 100);
		Password_Label.setSize(80,25);
		add(Password_Label);
		
		//PASSWORD INPUT BOX
		password_input.setLayout(null);
		password_input.setLocation(170,100);
		password_input.setSize(210,25);
		add(password_input);
		
		//PASSWORD VERI LABEL BOX
		Password_Veri_Label.setLayout(null);
		Password_Veri_Label.setLocation(20, 130);
		Password_Veri_Label.setSize(150,25);
		add(Password_Veri_Label);
		
		//PASSWORD VERI INPUT BOX
		password_veri_input.setLayout(null);
		password_veri_input.setLocation(170,130);
		password_veri_input.setSize(210,25);
		add(password_veri_input);
		
		/*LABEL - ACCESS_CODE_LABEL*/
		Access_Code_Label.setLayout(null);
		Access_Code_Label.setLocation(20, 160);
		Access_Code_Label.setSize(100,25);
		add(Access_Code_Label);
		
		//ACCESS CODE INPUT BOX
		access_code_input.setLayout(null);
		access_code_input.setLocation(170,160);
		access_code_input.setSize(210,25);
		add(access_code_input);
		
		/*LABEL - GROUP_NAME_LABEL*/
		Group_Name_Label.setLayout(null);
		Group_Name_Label.setLocation(20, 190);
		Group_Name_Label.setSize(100,25);
		add(Group_Name_Label);
		
		//GROUP NAME INPUT BOX
		group_input.setLayout(null);
		group_input.setLocation(170,190);
		group_input.setSize(210,25);
		add(group_input);		
		
		//CREATE BUTTON
		Create.setLayout(null);
	    Create.setLocation(150, 230);
	    Create.setSize(150, 25);
	    add(Create);
	    
        //Set button to default button to select with enter key
        getRootPane().setDefaultButton(Create);
	    
	    
	    Create.addActionListener (new ActionListener() {
			public void actionPerformed(ActionEvent e){
				
				 String FirstName_Input = f_name_input.getText();
	        	 String LastName_Input = l_name_input.getText();
	        	 String Username_Input = username_input.getText();
	        	 String Access_Input = access_code_input.getText();
	        	 String Password_Input = password_input.getText();
	        	 String Password_Veri_Input = password_veri_input.getText();
	        	
	        	//If this line gets printed, checking is correct.
	             //Print outs to test code in console   
	             System.out.println("inside Action Performed");
	             System.out.println(FirstName_Input + " " + LastName_Input+ " " + Username_Input +" " + Password_Input + " " + Access_Input);           	
	    	        
	             //Only allows certain characters to be present in a given field. 
	        	 Pattern Letters_Numbers = Pattern.compile("[a-zA-Z0-9]");
	        	 Pattern Letters_Only = Pattern.compile("[a-zA-Z]");
	        	 Pattern Non_White_Spaces = Pattern.compile("\\S");
	        	 
	        	 //Boolean to determine if the given field has the correct values
	        	 boolean FirstName_hasSpecialChar = Letters_Only.matcher(FirstName_Input).find();
	        	 boolean LastName_hasSpecialChar = Letters_Only.matcher(LastName_Input).find();
	        	 boolean Username_hasSpecialChar = Letters_Numbers.matcher(Username_Input).find(); 
	        	 boolean Password_hasSpecialChar = Non_White_Spaces.matcher(Password_Input).find();
	        	    
	        	 //Checks to make sure first and last name have only Letters, and no other characters
	        	 if( FirstName_hasSpecialChar == false  || LastName_hasSpecialChar == false)
	        	   	{
	        		//Checks to make sure first and last name are present
	        		JOptionPane.showMessageDialog(null,
	    				    "Please enter a valid first and last name!",
	    				    "Name Error",
	    				    JOptionPane.ERROR_MESSAGE);
	        	   	}
	        	 
	        	//Checks to make sure Username has only letters and/or numbers
	        	if( Username_hasSpecialChar == false)
	        	{
	        		//Checks to make sure Username present
	        		JOptionPane.showMessageDialog(null,
	    				    "Please enter a valid username!",
	    				    "Username Error",
	    				    JOptionPane.ERROR_MESSAGE);
	        	}
	        	
	        	 //Checks password and verification password to make sure they are the same.   
	        	if(!(Password_Input.equals(Password_Veri_Input)) || Password_hasSpecialChar == false)
	        	{
	        		JOptionPane.showMessageDialog(null,
	       				    "Passwords do not match!",
	       				    "Password Error",
	       				    JOptionPane.ERROR_MESSAGE);
	        	}
	        	
			//This is the when statement that when all are true, the system will allow the user to Create new account
			if((FirstName_hasSpecialChar == true  && LastName_hasSpecialChar == true) &&
		        	 (Username_hasSpecialChar == true) && 
		        	 (Password_Input.equals(Password_Veri_Input) && 
		        	  Password_hasSpecialChar == true))	 
				{		
		        		//If this line gets printed, checking is correct. Goes to console
						System.out.println("Inside error check's 'if'");
		                System.out.println(FirstName_Input + " " + LastName_Input+ " " + Username_Input +" " + Password_Input + " " + Access_Input);
					   
						//try statement to add to the sql database
							try {
								new LOGIN();
								sql_add_account();
								setVisible(false);
							} catch (InstantiationException
									| IllegalAccessException
									| ClassNotFoundException | SQLException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
				}	
			}
			}
		);
		//tells user in console when they have entered the new user registration page for testing.
		System.out.println("Entered New User Regristration");
		
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
		//Used for testing to let user know they are inside of the sql_add_acount method
		System.out.println("Inside sql_add_account");
		System.out.println(f_name_input.getText() + l_name_input.getText() + username_input.getText() + password_input.getText() + access_code_input.getText() + group_input.getText() );
		
		Class.forName("com.mysql.jdbc.Driver").newInstance();
		Statement s = GUI.con.createStatement();
		

		
		
		//Inserts the values into the Database
		s.executeUpdate ("INSERT INTO all_users VALUES(default,"+ "4" +","+"\'"+f_name_input.getText()+"\',"+"\'"+l_name_input.getText()+"\',"+"\'"+username_input.getText()+"\',"+"\'"+password_input.getText()+"\',"+"\'"+access_code_input.getText()+"\',"+ "\'" + group_input.getText()+"\'"+");");
		//Get Member Account number
	
//		//s.executeUpdate("INSERT INTO groups VALUES(default," +"\'"+group_input.getText()+"\',"+"0"+");");
//		s.executeQuery ("SELECT Member_Count FROM groups WHERE G_Name = \'"+group_input.getText()+"\';");
//		ResultSet rs = s.getResultSet();
//		int count = 1;
//		while(rs.next())
//		{
//		count = rs.getInt("Member_Count");
//		count++;
//		}
//		s.executeUpdate("UPDATE groups SET Member_Count = \'"+count+"\' WHERE G_NAME = 'Test'");
//		rs.close();
		s.close();
	}
}
