//TEAM J || SCHEDULER

//PACKAGE
//package Calendar;

//IMPORTS
import java.io.*;
import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

import java.awt.geom.*;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

//CLASS GUI 
@SuppressWarnings({ "serial", "unused" })
public class GUI extends JFrame
{
	//VARIABLES
	JFrame MainPanel = new JFrame();
	JLabel N[] = new JLabel[37];  //numbers
	JLabel month_label = new JLabel();
	JButton Next_Button = new JButton("Next >>");
	JButton Previous_Button = new JButton("<< Previous");
	JButton Go = new JButton("Go!");
	JButton Logout_Button = new JButton("LOGOUT");
	@SuppressWarnings("rawtypes")
	JComboBox Operations;
	JLabel Star[] = new JLabel[37];
	final int font_size = 21;
	static int month_value = 4;
	static java.sql.Connection con;
	int active_days[] = new int[37];
	int month_value_offsets[] = {2,5,5,1,3,-1,1,4,0,2,5,0};
	static int Level = 0;
	static String ugn = ""; //User Group Name
	 
	
	//CONSTRUCTOR
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public GUI(int level, String ugn_in) throws SQLException, InstantiationException, IllegalAccessException, ClassNotFoundException
	{
		
		//MAIN FRAME
		super("Team J's Scheduler");
		setBounds(50,50,700,705);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    setLayout(null);
	    
	    //SET USER LEVEL & GROUP
	    Level = level;
	    ugn = ugn_in;
	    
		//NUMBERS
	    int nh = 0, nv = 0; //NUMBERS HORIZONTAL, NUMBERS VERTICAL
	    for(int k = 0; k < 37; k++) //LINES UP SPOTS FOR EACH BOX ON CALENDAR 
	    {
	    	N[k] = new JLabel();
			N[k].setSize(30,30);
			N[k].setLocation(0+(nh*100),75 + (nv * 100));
			N[k].setText("");
			N[k].setFont(new Font("Serif", Font.BOLD, font_size));
			N[k].setForeground(Color.BLACK);
			N[k].setVisible(true);
			add(N[k]);
			nh++;
			//WHERE WEEKS SPLIT OFF BETWEEN ROWS
			if(k == 6 || k == 13 || k == 20 || k == 27 || k == 34)
			{
				nh = 0;
				nv++;
			}
	    }

	    //LOGOUT BUTTON
	    Logout_Button.setLayout(null);
	    Logout_Button.setLocation(0,0);
	    Logout_Button.setSize(100,25);
	    Logout_Button.addActionListener(new ActionListener(){
	    	public void actionPerformed(ActionEvent e)
	    	{
	    		System.exit(0); 
	    	}
	    	
	    });
	    add(Logout_Button);
	    
		//NEXT BUTTON
		Next_Button.setLayout(null);
	    Next_Button.setLocation(420, 40);
	    Next_Button.setSize(130, 25);
		Next_Button.addActionListener(new ActionListener() {
    	public void actionPerformed(ActionEvent e) {
    			try {
					month_chooser(true);
				} catch (InstantiationException | IllegalAccessException
						| ClassNotFoundException | SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
     		}          
    	});
        add(Next_Button);

		//PREVIOUS BUTTON
		Previous_Button.setLayout(null);
        Previous_Button.setLocation(130, 40);
        Previous_Button.setSize(130, 25);
		Previous_Button.addActionListener(new ActionListener() {
    	public void actionPerformed(ActionEvent e) {
    			try {
					month_chooser(false);
				} catch (InstantiationException | IllegalAccessException
						| ClassNotFoundException | SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
     		}          
    	});
        add(Previous_Button);

		//MONTH LABEL
		month_label = new JLabel();
		month_label.setSize(135,25);
		month_label.setLocation(280,40);
		month_label.setText("April");
		month_label.setFont(new Font("Serif", Font.BOLD, font_size));
		month_label.setForeground(Color.BLACK);
		add(month_label);
		
		//COMBO BOX
		//A => ADMINISTRATOR, M => MANAGER, U => USER
		String [] options_A = {"-- Choose Option --","View All Accounts", "View Specific Account", "View All Groups", "View Specific Group","Add New Appointment", "Remove Appointment", "Change Appointment Information", "Add Account", "Remove Account", "Change Account Information", "Add Group", "Remove Group", "Change Group Information"};
		String [] options_M = {"-- Choose Option --","View All Accounts in Group", "View Specific Account in Group", "---", "View Group Information","Add New Appointment", "Remove Appointment", "Change Appointment Information", "Add Account", "Remove Account", "Change Account Information", "---", "---", "---"};
		String [] options_U = {"-- No Options Available --","---", "---", "---", "---","---", "---", "---", "---", "---", "---", "---", "---", "---"};
		//CHANGE COMBO BOX OPTIONS BASED ON HIERARCHY
		if(level == 1)
		{
			Operations = new JComboBox(options_A);
		}
		else if(level == 2)
		{
			Operations = new JComboBox(options_M);
		}
		else if(level == 3)
		{
			Operations = new JComboBox(options_U);
		}
		Operations.setLocation(400,0);
		Operations.setSize(200,25);
		//ADD COMBO BOX
		add(Operations);
		
		//GO BUTTON
		Go.setLayout(null);
	    Go.setLocation(600, 0);
	    Go.setSize(100, 25);
		Go.addActionListener(new ActionListener() {
    	public void actionPerformed(ActionEvent e) {
    			int choice = Operations.getSelectedIndex();
    			try {
					ComboBoxOps(choice);
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
	    add(Go);
	    
		//STARS----------------------------------------------------||
	    int h = 0, v = 0;
	    for(int m = 0; m < 37; m++)
	    {
		    //STAR LABEL
		    Star[m] = new JLabel();
		    ImageIcon icon = new ImageIcon("star.png","Star");  //PUT star.png IN FOLDER WITH OTHER FILES FOR PROGRAM
		    Star[m].setLocation(0 + (h*100), 100 + (100 * v));
		    Star[m].setSize(100, 100);
		    Star[m].setIcon(icon); 
		    Star[m].setName(String.valueOf(m));
		    Star[m].addMouseListener(new MouseAdapter() {
		    	public void mouseClicked(MouseEvent e) {
		    			String id = e.getComponent().getName();
		    			int current_day = (Integer.parseInt(id) - month_value_offsets[month_value-1]);
						try {
							new View_Appointments_From_Day_Star(current_day, month_value);
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
		    add(Star[m]);
		    Star[m].setVisible(false);
		    h++;
		    if(m == 6 || m == 13 || m == 20 || m == 27 || m == 34)
		    {
		    	h = 0;
		    	v++;
		    }
		    
	    }
	    
	  //SET NUMBERS FOR APRIL--------------------------------------------------------------------||
	  for(int i = 0; i < 30; i++)
	  {
		  N[i+2].setText(String.valueOf(i+1));
	  }
	  
	  //READ APPOINTMENTS AND HIGHLIGHT STARS
	  Class.forName("com.mysql.jdbc.Driver").newInstance();
	  Statement s = GUI.con.createStatement();
	  if(level == 1)
	  {
		  s.executeQuery ("SELECT A_Date FROM appointments WHERE A_Date LIKE '2014-04-%';");
	  }
	  else if(level == 2 || level == 3)
	  {
		  s.executeQuery ("SELECT A_Date FROM appointments WHERE G_Name = \'"+ ugn +"\' AND A_Date LIKE '2014-04-%';");
	  }
	  ResultSet rs = s.getResultSet();
	  boolean found = false;
      String d = "", sd = "";	 
	  while (rs.next() && found == false)
	  {
	         d = rs.getString ("A_Date");
	         sd = d.substring(8,10);	 
	         for(int g = 0; g < 37; g++)
	         {
	        	 if(g+1 == Integer.parseInt(sd))
	        	 {
	        		 Star[g+2].setVisible(true);
	        	 }
	         }
	  }
	  rs.close();
	  s.close();
	//-------------------------------------||
	    
		setVisible(true);
	}
	

	//PAINT LINES AND LAYOUT
	@Override
	public void paint (Graphics g)
	{
		super.paint(g);
   		Graphics2D g2 = (Graphics2D) g;
		g2.setPaint(Color.blue);
      		g2.draw(new Line2D.Double(0, 100, 700, 100));	
		g2.setPaint(Color.black);
		//DRAW VERTICAL
		for(int i = 100; i < 800; i = i + 100)
		{
			g2.draw(new Line2D.Double(i,100,i,700));
		}
		for(int i2 = 0; i2 < 5; i2++)
		{
			g2.draw(new Line2D.Double(0,100+(100*(i2+1)),700,100+(100*(i2+1))));	
		}
	}
	
	//COMBO BOX FUNCTION---------------------------------------------------||
	public void ComboBoxOps(int selection) throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException
	{
			//VIEW ALL ACCOUNTS
			if(selection == 1)
			{
				new Combo_Box_View_All_Accounts();
			}
			//VIEW SPECIFIC ACCOUNT
			else if(selection == 2)
			{
				new Combo_Box_View_Specific_Account();
			}
			//VIEW ALL GROUPS
			else if(selection == 3 && Level == 1)
			{
				new Combo_Box_View_All_Groups();
			}
			//VIEW SPECIFIC GROUP
			else if(selection == 4 && Level == 1)
			{
				new Combo_Box_View_Specific_Group();
			}
			else if(selection == 4 && Level == 2)
			{
				new Combo_Box_View_All_Groups();
			}
			//ADD NEW APPOINTMENT
			else if(selection == 5)
			{
				new Combo_Box_Add_Appointment();
			}
			//REMOVE APPOINTMENT
			else if(selection == 6)
			{
				new Combo_Box_Remove_Appointment();
			}
			//CHANGE APPOINTMENT INFORMATION
			else if(selection == 7 && Level == 1)
			{
				new Combo_Box_Change_Appointment_Information();
			}
			else if(selection == 7 && Level == 2)
			{
				new M_Combo_Box_Change_Appointment_Information();
			}
			//ADD ACCOUNT
			else if(selection == 8)
			{
				new Combo_Box_Add_Account();
			}
			//REMOVE ACCOUNT
			else if(selection == 9)
			{
				new Combo_Box_Remove_Account();
			}
			//CHANGE ACCOUNT INFORMATION
			else if(selection == 10 && Level == 1)
			{
				new Combo_Box_Change_Account_Information();
			}
			else if(selection == 10 && Level == 2)
			{
				new M_Combo_Box_Change_Account_Information();
			}
			//ADD GROUP
			else if(selection == 11 && Level == 1)
			{
				new Combo_Box_Add_Group();
			}
			//REMOVE GROUP
			else if(selection == 12 && Level == 1)
			{
				new Combo_Box_Remove_Group();
			}
			//CHANGE GROUP INFORMATION
			else if(selection == 13 && Level == 1)
			{
				new Combo_Box_Change_Group_Information();
			}
	}
	
	//MONTH CHOOSER FUNCTION
	public void month_chooser(boolean direction) throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException
	{
		//NEXT
		boolean necessary = false;
		if(direction == true && month_value != 12)
		{
			month_value++;
			necessary = true;
		}
		else if(direction == false && month_value != 1)
		{
			month_value--;
			necessary = true;
		}
			
		if(necessary == true)
		{
			//RESET ALL ACTIVE DAYS
			for(int y = 0; y < 37; y++)
			{
				active_days[y] = 0;
			}
			//CHOOSE MONTH
			if(month_value == 1)
			{
				month_label.setText("January");
				int i = 0;
				for(i = 0; i < 37; i++)
				{
					N[i].setText("");
				}
				for(i = 0; i < 31; i++)
				{
					N[i+3].setText(String.valueOf(i+1));
				}
				for(i = 0; i < 37; i++)
				{
					Star[i].setVisible(false);
				}
				//READ APPOINTMENTS AND HIGHLIGHT STARS
				  Class.forName("com.mysql.jdbc.Driver").newInstance();
				  Statement s = GUI.con.createStatement();
				  if(	Level == 1)
				  {
					  s.executeQuery ("SELECT A_Date FROM appointments WHERE A_Date LIKE '2014-01-%';");
				  }
				  else if(Level == 2 || Level == 3)
				  {
					  s.executeQuery ("SELECT A_Date FROM appointments WHERE G_Name = \'"+ugn+"\' AND A_Date LIKE '2014-01-%';");
				  }
				  ResultSet rs = s.getResultSet();
				  boolean found = false;
			      String d = "", sd = "";	 
				  while (rs.next() && found == false)
				  {
				         d = rs.getString ("A_Date");
				         sd = d.substring(8,10);	 
				         for(int g = 0; g < 37; g++)
				         {
				        	 if(g+1 == Integer.parseInt(sd))
				        	 {
				        		 Star[g+3].setVisible(true);
				        		 
				        	 }
				         }
				  }
				  rs.close();
				  s.close();
				//-------------------------------------||
				repaint();
			}
			else if(month_value == 2)
				{
					month_label.setText("February");
					int i = 0;
					for(i = 0; i < 37; i++)
					{
						N[i].setText("");
					}
					for(i = 0; i < 28; i++)
					{
						N[i+6].setText(String.valueOf(i+1));
					}
					for(i = 0; i < 37; i++)
					{
						Star[i].setVisible(false);
					}
					//READ APPOINTMENTS AND HIGHLIGHT STARS
					  Class.forName("com.mysql.jdbc.Driver").newInstance();
					  Statement s = GUI.con.createStatement();
					  if(Level == 1)
					  {
						  s.executeQuery ("SELECT A_Date FROM appointments WHERE A_Date LIKE '2014-02-%';");
					  }
					  else if(Level == 2 || Level == 3)
					  {
						  s.executeQuery ("SELECT A_Date FROM appointments WHERE G_Name = \'"+ugn+"\' AND A_Date LIKE '2014-02-%';");
					  }
					  ResultSet rs = s.getResultSet();
					  boolean found = false;
				      String d = "", sd = "";	 
					  while (rs.next() && found == false)
					  {
					         d = rs.getString ("A_Date");
					         sd = d.substring(8,10);	 
					         for(int g = 0; g < 37; g++)
					         {
					        	 if(g+1 == Integer.parseInt(sd))
					        	 {
					        		 Star[g+6].setVisible(true);
					        	 }
					         }
					  }
					  rs.close();
					  s.close();
					//-------------------------------------||
					repaint();
				}
				else if(month_value == 3)
				{
					month_label.setText("March");
					int i = 0;
					for(i = 0; i < 37; i++)
					{
						N[i].setText("");
					}
					for(i = 0; i < 31; i++)
					{
						N[i+6].setText(String.valueOf(i+1));
					}
					for(i = 0; i < 37; i++)
					{
						Star[i].setVisible(false);
					}
					//READ APPOINTMENTS AND HIGHLIGHT STARS
					  Class.forName("com.mysql.jdbc.Driver").newInstance();
					  Statement s = GUI.con.createStatement();
					  if(Level == 1)
					  {
						  s.executeQuery ("SELECT A_Date FROM appointments WHERE A_Date LIKE '2014-03-%';");
					  }
					  else if(Level == 2 || Level == 3)
					  {
						  s.executeQuery ("SELECT A_Date FROM appointments WHERE G_Name = \'"+ugn+"\' AND A_Date LIKE '2014-03-%';");
					  }
					  ResultSet rs = s.getResultSet();
					  boolean found = false;
				      String d = "", sd = "";	 
					  while (rs.next() && found == false)
					  {
					         d = rs.getString ("A_Date");
					         sd = d.substring(8,10);	 
					         for(int g = 0; g < 37; g++)
					         {
					        	 if(g+1 == Integer.parseInt(sd))
					        	 {
					        		 Star[g+6].setVisible(true);
					        	 }
					         }
					  }
					  rs.close();
					  s.close();
					//-------------------------------------||
					repaint();
				}
				else if(month_value == 4)
				{
					month_label.setText("April");
					int i = 0;
					for(i = 0; i < 37; i++)
					{
						N[i].setText("");
					}
					for(i = 0; i < 30; i++)
					{
						N[i+2].setText(String.valueOf(i+1));
					}
					for(i = 0; i < 37; i++)
					{
						Star[i].setVisible(false);
					}
					//READ APPOINTMENTS AND HIGHLIGHT STARS
					  Class.forName("com.mysql.jdbc.Driver").newInstance();
					  Statement s = GUI.con.createStatement();
					  if(Level == 1)
					  {
						  s.executeQuery ("SELECT A_Date FROM appointments WHERE A_Date LIKE '2014-04-%';");
					  }
					  else if(Level == 2 || Level == 3)
					  {
						  s.executeQuery ("SELECT A_Date FROM appointments WHERE G_Name = \'"+ugn+"\' AND A_Date LIKE '2014-04-%';");
					  }
					  ResultSet rs = s.getResultSet();
					  boolean found = false;
				      String d = "", sd = "";	 
					  while (rs.next() && found == false)
					  {
					         d = rs.getString ("A_Date");
					         sd = d.substring(8,10);	 
					         for(int g = 0; g < 37; g++)
					         {
					        	 if(g+1 == Integer.parseInt(sd))
					        	 {
					        		 Star[g+2].setVisible(true);
					        	 }
					         }
					  }
					  rs.close();
					  s.close();
					//-------------------------------------||
					  repaint();
				}
				else if(month_value == 5)
				{
					month_label.setText("May");
					int i = 0;
					for(i = 0; i < 37; i++)
					{
						N[i].setText("");
					}
					for(i = 0; i < 31; i++)
					{
						N[i+4].setText(String.valueOf(i+1));
					}
					for(i = 0; i < 37; i++)
					{
						Star[i].setVisible(false);
					}
					//READ APPOINTMENTS AND HIGHLIGHT STARS
					  Class.forName("com.mysql.jdbc.Driver").newInstance();
					  Statement s = GUI.con.createStatement();
					  if(Level == 1)
					  {
						  s.executeQuery ("SELECT A_Date FROM appointments WHERE A_Date LIKE '2014-05-%';");
					  }
					  else if(Level == 2 || Level == 3)
					  {
						  s.executeQuery ("SELECT A_Date FROM appointments WHERE G_Name = \'"+ugn+"\' AND A_Date LIKE '2014-05-%';");
					  }
					  ResultSet rs = s.getResultSet();
					  boolean found = false;
				      String d = "", sd = "";	 
					  while (rs.next() && found == false)
					  {
					         d = rs.getString ("A_Date");
					         sd = d.substring(8,10);	 
					         for(int g = 0; g < 37; g++)
					         {
					        	 if(g+1 == Integer.parseInt(sd))
					        	 {
					        		 Star[g+4].setVisible(true);
					        	 }
					         }
					  }
					  rs.close();
					  s.close();
					//-------------------------------------||
					repaint();
				}
				else if(month_value == 6)
				{
					month_label.setText("June");
					int i = 0;
					for(i = 0; i < 37; i++)
					{
						N[i].setText("");
					}
					for(i = 0; i < 30; i++)
					{
						N[i+0].setText(String.valueOf(i+1));
					}
					for(i = 0; i < 37; i++)
					{
						Star[i].setVisible(false);
					}
					//READ APPOINTMENTS AND HIGHLIGHT STARS
					  Class.forName("com.mysql.jdbc.Driver").newInstance();
					  Statement s = GUI.con.createStatement();
					  if(Level == 1)
					  {
						  s.executeQuery ("SELECT A_Date FROM appointments WHERE A_Date LIKE '2014-06-%';");
					  }
					  else if(Level == 2 || Level == 3)
					  {
						  s.executeQuery ("SELECT A_Date FROM appointments WHERE G_Name = \'"+ugn+"\' AND A_Date LIKE '2014-06-%';");
					  }
					  ResultSet rs = s.getResultSet();
					  boolean found = false;
				      String d = "", sd = "";	 
					  while (rs.next() && found == false)
					  {
					         d = rs.getString ("A_Date");
					         sd = d.substring(8,10);	 
					         for(int g = 0; g < 37; g++)
					         {
					        	 if(g+1 == Integer.parseInt(sd))
					        	 {
					        		 Star[g+0].setVisible(true);
					        	 }
					         }
					  }
					  rs.close();
					  s.close();
					//-------------------------------------||
					repaint();
				}
				else if(month_value == 7)
				{
					month_label.setText("July");
					int i = 0;
					for(i = 0; i < 37; i++)
					{
						N[i].setText("");
					}
					for(i = 0; i < 31; i++)
					{
						N[i+2].setText(String.valueOf(i+1));
					}
					for(i = 0; i < 37; i++)
					{
						Star[i].setVisible(false);
					}
					//READ APPOINTMENTS AND HIGHLIGHT STARS
					  Class.forName("com.mysql.jdbc.Driver").newInstance();
					  Statement s = GUI.con.createStatement();
					  if(Level == 1)
					  {
						  s.executeQuery ("SELECT A_Date FROM appointments WHERE A_Date LIKE '2014-07-%';");
					  }
					  else if(Level == 2 || Level == 3)
					  {
						  s.executeQuery ("SELECT A_Date FROM appointments WHERE G_Name = \'"+ugn+"\' AND A_Date LIKE '2014-07-%';");
					  }
					  ResultSet rs = s.getResultSet();
					  boolean found = false;
				      String d = "", sd = "";	 
					  while (rs.next() && found == false)
					  {
					         d = rs.getString ("A_Date");
					         sd = d.substring(8,10);	 
					         for(int g = 0; g < 37; g++)
					         {
					        	 if(g+1 == Integer.parseInt(sd))
					        	 {
					        		 Star[g+2].setVisible(true);
					        	 }
					         }
					  }
					  rs.close();
					  s.close();
					//-------------------------------------||
					repaint();
				}
				else if(month_value == 8)
				{
					month_label.setText("August");
					int i = 0;
					for(i = 0; i < 37; i++)
					{
						N[i].setText("");
					}
					for(i = 0; i < 31; i++)
					{
						N[i+5].setText(String.valueOf(i+1));
					}
					for(i = 0; i < 37; i++)
					{
						Star[i].setVisible(false);
					}
					//READ APPOINTMENTS AND HIGHLIGHT STARS
					  Class.forName("com.mysql.jdbc.Driver").newInstance();
					  Statement s = GUI.con.createStatement();
					  if(Level == 1)
					  {
						  s.executeQuery ("SELECT A_Date FROM appointments WHERE A_Date LIKE '2014-08-%';");
					  }
					  else if(Level == 2 || Level == 3)
					  {
						  s.executeQuery ("SELECT A_Date FROM appointments WHERE G_Name = \'"+ugn+"\' AND A_Date LIKE '2014-08-%';");
					  }
					  ResultSet rs = s.getResultSet();
					  boolean found = false;
				      String d = "", sd = "";	 
					  while (rs.next() && found == false)
					  {
					         d = rs.getString ("A_Date");
					         sd = d.substring(8,10);	 
					         for(int g = 0; g < 37; g++)
					         {
					        	 if(g+1 == Integer.parseInt(sd))
					        	 {
					        		 Star[g+5].setVisible(true);
					        	 }
					         }
					  }
					  rs.close();
					  s.close();
					//-------------------------------------||
					repaint();
				}
				else if(month_value == 9)
				{
					month_label.setText("September");
					int i = 0;
					for(i = 0; i < 37; i++)
					{
						N[i].setText("");
					}
					for(i = 0; i < 30; i++)
					{
						N[i+1].setText(String.valueOf(i+1));
					}
					for(i = 0; i < 37; i++)
					{
						Star[i].setVisible(false);
					}
					//READ APPOINTMENTS AND HIGHLIGHT STARS
					  Class.forName("com.mysql.jdbc.Driver").newInstance();
					  Statement s = GUI.con.createStatement();
					  if(Level == 1)
					  {
						  s.executeQuery ("SELECT A_Date FROM appointments WHERE A_Date LIKE '2014-09-%';");
					  }
					  else if(Level == 2 || Level == 3)
					  {
						  s.executeQuery ("SELECT A_Date FROM appointments WHERE G_Name = \'"+ugn+"\' AND A_Date LIKE '2014-09-%';");
					  }
					  ResultSet rs = s.getResultSet();
					  boolean found = false;
				      String d = "", sd = "";	 
					  while (rs.next() && found == false)
					  {
					         d = rs.getString ("A_Date");
					         sd = d.substring(8,10);	 
					         for(int g = 0; g < 37; g++)
					         {
					        	 if(g+1 == Integer.parseInt(sd))
					        	 {
					        		 Star[g+1].setVisible(true);
					        	 }
					         }
					  }
					  rs.close();
					  s.close();
					//-------------------------------------||
					repaint();
				}
				else if(month_value == 10)
				{
					month_label.setText("October");
					int i = 0;
					for(i = 0; i < 37; i++)
					{
						N[i].setText("");
					}
					for(i = 0; i < 31; i++)
					{
						N[i+3].setText(String.valueOf(i+1));
					}
					for(i = 0; i < 37; i++)
					{
						Star[i].setVisible(false);
					}
					//READ APPOINTMENTS AND HIGHLIGHT STARS
					  Class.forName("com.mysql.jdbc.Driver").newInstance();
					  Statement s = GUI.con.createStatement();
					  if(Level == 1)
					  {
						  s.executeQuery ("SELECT A_Date FROM appointments WHERE A_Date LIKE '2014-10-%';");
					  }
					  else if(Level == 2 || Level == 3)
					  {
						  s.executeQuery ("SELECT A_Date FROM appointments WHERE G_Name = \'"+ugn+"\' AND A_Date LIKE '2014-10-%';");
					  }
					  ResultSet rs = s.getResultSet();
					  boolean found = false;
				      String d = "", sd = "";	 
					  while (rs.next() && found == false)
					  {
					         d = rs.getString ("A_Date");
					         sd = d.substring(8,10);	 
					         for(int g = 0; g < 37; g++)
					         {
					        	 if(g+1 == Integer.parseInt(sd))
					        	 {
					        		 Star[g+3].setVisible(true);
					        	 }
					         }
					  }
					  rs.close();
					  s.close();
					//-------------------------------------||
					repaint();
				}
				else if(month_value == 11)
				{
					month_label.setText("November");
					int i = 0;
					for(i = 0; i < 37; i++)
					{
						N[i].setText("");
					}
					for(i = 0; i < 30; i++)
					{
						N[i+6].setText(String.valueOf(i+1));
					}
					for(i = 0; i < 37; i++)
					{
						Star[i].setVisible(false);
					}
					//READ APPOINTMENTS AND HIGHLIGHT STARS
					  Class.forName("com.mysql.jdbc.Driver").newInstance();
					  Statement s = GUI.con.createStatement();
					  if(Level == 1)
					  {
						  s.executeQuery ("SELECT A_Date FROM appointments WHERE A_Date LIKE '2014-10-%';");
					  }
					  else if(Level == 2 || Level == 3)
					  {
						  s.executeQuery ("SELECT A_Date FROM appointments WHERE G_Name = \'"+ugn+"\' AND A_Date LIKE '2014-10-%';");
					  }
					  ResultSet rs = s.getResultSet();
					  boolean found = false;
				      String d = "", sd = "";	 
					  while (rs.next() && found == false)
					  {
					         d = rs.getString ("A_Date");
					         sd = d.substring(8,10);	 
					         for(int g = 0; g < 37; g++)
					         {
					        	 if(g+1 == Integer.parseInt(sd))
					        	 {
					        		 Star[g+6].setVisible(true);
					        	 }
					         }
					  }
					  rs.close();
					  s.close();
					//-------------------------------------||
					repaint();
				}
				else if(month_value == 12)
				{
					month_label.setText("December");
					int i = 0;
					for(i = 0; i < 37; i++)
					{
						N[i].setText("");
					}
					for(i = 0; i < 31; i++)
					{
						N[i+1].setText(String.valueOf(i+1));
					}
					for(i = 0; i < 37; i++)
					{
						Star[i].setVisible(false);
					}
					//READ APPOINTMENTS AND HIGHLIGHT STARS
					  Class.forName("com.mysql.jdbc.Driver").newInstance();
					  Statement s = GUI.con.createStatement();
					  if(Level == 1)
					  {
						  s.executeQuery ("SELECT A_Date FROM appointments WHERE A_Date LIKE '2014-10-%';");
					  }
					  else if(Level == 2 || Level == 3)
					  {
						  s.executeQuery ("SELECT A_Date FROM appointments WHERE G_Name = \'"+ugn+"\' AND A_Date LIKE '2014-10-%';");
					  }
					  ResultSet rs = s.getResultSet();
					  boolean found = false;
				      String d = "", sd = "";	 
					  while (rs.next() && found == false)
					  {
					         d = rs.getString ("A_Date");
					         sd = d.substring(8,10);	 
					         for(int g = 0; g < 37; g++)
					         {
					        	 if(g+1 == Integer.parseInt(sd))
					        	 {
					        		 Star[g+1].setVisible(true);
					        	 }
					         }
					  }
					  rs.close();
					  s.close();
					//-------------------------------------||
					repaint();
				}
		}
	}
	
	//MAIN
	public static void main(String[] args) throws SQLException
	{
		con = DriverManager.getConnection("jdbc:mysql://99.3.32.166:3306/scheduler","root","mysql");
		new LOGIN();		
	}
}
