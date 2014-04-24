//package Calendar;

import java.awt.Color;
import java.awt.Font;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JFrame;
import javax.swing.JLabel;

//CLASS ViewAppointment 
@SuppressWarnings("serial")
class View_Appointments_From_Day_Star extends JFrame
{
	//VARIABLES
	JFrame MainPanel = new JFrame();
	JLabel appointments[] = new JLabel[10];
	
	//CONSTRUCTOR
	public View_Appointments_From_Day_Star(int day, int month) throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException
	{
		//MAIN FRAME
		super("Team J's Scheduler | View Appointment");
		setBounds(50,50,500,300);
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	    setLayout(null);
		setVisible(true);	
		
		//NUMBERS
	    for(int k = 0; k < 10; k++) //lines up spots for each box on calendar
	    {
	    	appointments[k] = new JLabel();
	    	appointments[k].setSize(500,20);
	    	appointments[k].setLocation(0,(k*25));
	    	appointments[k].setText("");
	    	appointments[k].setFont(new Font("Serif", Font.BOLD, 12));
	    	appointments[k].setForeground(Color.BLACK);
	    	appointments[k].setVisible(true);
			add(appointments[k]);
	    }
	    
	      //READ APPOINTMENTS AND DISPLAY THEM
		  Class.forName("com.mysql.jdbc.Driver").newInstance();
		  Statement s = GUI.con.createStatement();
		  if(GUI.Level == 1)
		  {
			  s.executeQuery ("SELECT * FROM appointments WHERE A_Date = '2014-"+month+"-"+day+"\';");
		  }
		  else if(GUI.Level == 2 || GUI.Level == 3)
		  {
			  s.executeQuery ("SELECT * FROM appointments WHERE G_Name = \'"+GUI.ugn+"\' AND A_Date = '2014-"+month+"-"+day+"\';");
		  }
		  ResultSet rs = s.getResultSet();
	      String d = "", sd = "";
	      int t = 0;
		  while (rs.next())
		  {
			  	//SET LOCAL VARIABLES EQUAL TO DATABASE VARIABLES
		         d = rs.getString ("A_Date");
		         sd = rs.getString("Title");
		         appointments[t].setText(d + " " + sd);
		         t++;
		  }
		  rs.close();
		  s.close();
		//-------------------------------------||
	}
}
