//package Calendar;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

//CLASS COMBO_BOX VIEW SPECIFIC ACCOUNT
@SuppressWarnings("serial")
class Combo_Box_View_Specific_Account extends JFrame
{
	//VARIABLES
	JFrame MainPanel = new JFrame();
	String apps = "";
	static JLabel outputer = new JLabel();
	JTextField lname_input = new JTextField();
	JTextField fname_input = new JTextField();
	JButton Lookup = new JButton("Lookup");
	JLabel lname_label = new JLabel("Last Name to Look Up: ");
	JLabel fname_label = new JLabel("First Name to Look Up: ");
	
	//CONSTRUCTOR
	public Combo_Box_View_Specific_Account() throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException
	{
		//MAIN FRAME
		super("Team J's Scheduler | View Specific Account");
		setBounds(50,50,500,300);
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	    setLayout(null);
		setVisible(true);	
		
		
		  
		  outputer = new JLabel();
	      outputer.setSize(500,20);
	      outputer.setLocation(0,0);
	      outputer.setText("");
	      outputer.setFont(new Font("Serif", Font.BOLD, 12));
	      outputer.setForeground(Color.BLACK);
	      outputer.setVisible(true);
		  add(outputer);
		  
		/*LABEL - LNAME_LABEL*/
		lname_label.setLayout(null);
		lname_label.setLocation(20, 150);
		lname_label.setSize(200,25);
		add(lname_label);  
		  
		//LNAME_INPUT BOX
		lname_input.setLayout(null);
		lname_input.setLocation(170,150);
		lname_input.setSize(210,25);
		add(lname_input);
		
		/*LABEL - FNAME_LABEL*/
		fname_label.setLayout(null);
		fname_label.setLocation(20, 180);
		fname_label.setSize(200,25);
		add(fname_label);  
		  
		//FNAME_INPUT BOX
		fname_input.setLayout(null);
		fname_input.setLocation(170,180);
		fname_input.setSize(210,25);
		add(fname_input);
		
		//CREATE BUTTON
		Lookup.setLayout(null);
	    Lookup.setLocation(150, 215);
	    Lookup.setSize(170, 25);
		Lookup.addActionListener(new ActionListener() {
  	public void actionPerformed(ActionEvent e) {
  		try {
				sql_lookup();
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
      add(Lookup);
      
      //Set button to default button to select with enter key
      getRootPane().setDefaultButton(Lookup);
      
	}
	//LOOKUP FUNCTION WITH SQL QUERIES
	public void sql_lookup() throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException
	{
		Class.forName("com.mysql.jdbc.Driver").newInstance();
		Statement s = GUI.con.createStatement();
		//CHANGE FUNCTION BASED ON HIERARCHY
		if(GUI.Level == 1)
		{
			s.executeQuery ("SELECT * FROM all_users");
		}
		else if(GUI.Level == 2)
		{
			s.executeQuery ("SELECT * FROM all_users WHERE G_Name = \'"+GUI.ugn+"\';");
		}
		ResultSet rs = s.getResultSet();
		boolean found = false;
		int id = 0, hier = 0;
		String input_last_name = lname_input.getText();
		String input_first_name = fname_input.getText();
		String f_name = "", l_name = "", u_name = "", p_word = "", a_code = "", g_name = "";
		while (rs.next() && found == false)
		{
			//SETTING LOCAL VALUES EQUAL TO VALUES FROM DATABASE
			   id = rs.getInt("ID");
		       hier = rs.getInt("Hierarchy");
		       u_name = rs.getString ("User_Name");
		       f_name = rs.getString("First_Name");
		       l_name = rs.getString("Last_Name");
		       p_word = rs.getString("Pass_Word");
		       a_code = rs.getString("Access_Code");
		       g_name = rs.getString("G_Name");
		       //FIND ACCOUNT IN DATABASE ACCORDING TO FIRST AND LAST NAME ENTERED IN GUI
		       if((l_name.equals(input_last_name))&&(f_name.equals(input_first_name)))
		       {
		            found = true;
		            Combo_Box_View_Specific_Account.outputer.setText(id + " " + hier + " " + f_name + " " + l_name + " " + u_name + " " + p_word + " " + a_code + " " + g_name);
		       }			    
		}
		rs.close();
		s.close();
		//GUI.con.close();
		//-------------------------------------||
	}
}
