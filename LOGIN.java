//package Calendar;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

//CLASS LOGIN
@SuppressWarnings("serial")
class LOGIN extends JFrame
{
	//VARIABLES
	JFrame MainPanel = new JFrame();
	JButton Login = new JButton("LOGIN");
	JButton Create = new JButton("CREATE NEW USER");
	JTextField Username_Box = new JTextField();
	JTextField Password_Box = new JTextField();
	private JLabel Username_Label;
    private JLabel Password_Label;

	public LOGIN()
	{
		//MAIN FRAME
		super("Team J's Scheduler | Login Page");
		setBounds(50,50,350,350);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    setLayout(null);
		setVisible(true);

		//LOGIN BUTTON
		Login.setLayout(null);
	    Login.setLocation(2, 285);
	    Login.setSize(170, 25);
		Login.addActionListener(new ActionListener() {
    	public void actionPerformed(ActionEvent e) {
    	    setVisible(false);
    	    try {
					login_function();
				} catch (InstantiationException | IllegalAccessException
						| ClassNotFoundException | SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
     		}          
    	});
	        add(Login);

		//CREATE BUTTON
		Create.setLayout(null);
	    Create.setLocation(173, 285);
	    Create.setSize(170, 25);
		Create.addActionListener(new ActionListener() {
    	public void actionPerformed(ActionEvent e) {
    			try {
    				//CALL TO CREATE NEW USER FUNCTION BELOW
    				setVisible(false);
					new Create_New_User();
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
	    
	    /*LABEL - USERNAME_LABEL*/
    	Username_Label = new JLabel("USERNAME: ");
    	Username_Label.setLayout(null);
    	Username_Label.setLocation(20, 100);
    	Username_Label.setSize(80,25);
    	Username_Label.setForeground(Color.BLACK);
    	add(Username_Label);

		//USERNAME BOX
		Username_Box.setLayout(null);
		Username_Box.setLocation(120,100);
		Username_Box.setSize(210,25);
		add(Username_Box);
		
		/*LABEL - PASSWORD_LABEL*/
	    Password_Label = new JLabel("PASSWORD: ");
		Password_Label.setLayout(null);
		Password_Label.setLocation(20, 150);
		Password_Label.setSize(80,25);
		Password_Label.setForeground(Color.BLACK);
		add(Password_Label);
		
		//PASSWORD BOX
		Password_Box.setLayout(null);
		Password_Box.setLocation(120,150);
		Password_Box.setSize(210,25);
		add(Password_Box);
	}
	//LOG IN FUNCTION WITH SQL QUERIES
	public void login_function() throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException
	{
		//READ HIERARCHY FROM DATABASE AND DECIDE ON FUNCTION--||
		Class.forName("com.mysql.jdbc.Driver").newInstance();
		Statement s = GUI.con.createStatement();
		s.executeQuery ("SELECT Hierarchy, User_Name, Pass_Word, G_Name FROM all_users");
		ResultSet rs = s.getResultSet();
		boolean found = false;
		int h = 0;
		String d_u_name = "", d_p_word = "";
		String u_name = Username_Box.getText();
		String p_word = Password_Box.getText();
		String g_name = "";
		while (rs.next() && found == false)
		{
			//SET LOCAL VALUES EQUAL TO DATABASE VALUES
		       h = rs.getInt("Hierarchy");
		       d_u_name = rs.getString ("User_Name");
		       d_p_word = rs.getString ("Pass_Word");
		       g_name = rs.getString("G_Name");
		       if(d_u_name.equals(u_name))
		       {
		            found = true;
		       }			    
		}
		rs.close();
		s.close();
		//GUI.con.close();
		//-------------------------------------||
		
		if(p_word.equals(d_p_word))
		{
			//CHOOSE USER TYPE---------------------------||
			if(h == 1)
			{
				new GUI(1,g_name);
			}
			else if(h == 2)
			{
				new GUI(2,g_name);
			}
			else if(h == 3)
			{
				new GUI(3,g_name);
			}
			//END CHOOSE USER TYPE-----------------------||
		}
		else
		{
			System.out.println("Password Incorrect");
		}
	}
}
