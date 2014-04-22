//package Calendar;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.ListSelectionModel;

//CLASS COMBO_BOX ALL ACCOUNTS
@SuppressWarnings("serial")
class Combo_Box_View_All_Accounts extends JFrame
{
	//VARIABLES
	JFrame MainPanel = new JFrame();
	@SuppressWarnings("rawtypes")
	JList list;
	String apps[] = new String[50];
	
	//CONSTRUCTOR
	@SuppressWarnings("unchecked")
	public Combo_Box_View_All_Accounts() throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException
	{
		//MAIN FRAME
		super("Team J's Scheduler | View All Appointment");
		setBounds(50,50,500,300);
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	    setLayout(null);
		setVisible(true);	
		
		//LISTBOX
		//READ APPOINTMENTS AND DISPLAY THEM
		  Class.forName("com.mysql.jdbc.Driver").newInstance();
		  Statement s = GUI.con.createStatement();
		  //CHANGE FUNCTION BASED ON HIERARCHY
		  if(GUI.Level == 1)
		  {
			  s.executeQuery ("SELECT * FROM all_users;");
		  }
		  else if(GUI.Level == 2)
		  {
			  s.executeQuery ("SELECT * FROM all_users WHERE G_Name = \'" +GUI.ugn+"\';");
		  }
		  ResultSet rs = s.getResultSet();
		  int h = 0, id = 0, t = 0;
			String d_u_name = "", d_p_word = "", d_f_name = "", d_l_name = "",g_name = "", a_code = "";
		  while (rs.next())
		  {
			  id = rs.getInt("ID"); 
	       	  h = rs.getInt("Hierarchy");
	       	  d_f_name = rs.getString("First_Name");
	       	  d_l_name = rs.getString("Last_Name");
	       	  d_u_name = rs.getString("User_Name");
	       	  d_p_word = rs.getString("Pass_Word");
	       	  a_code = rs.getString("Access_Code");
	       	  g_name = rs.getString("G_Name");
	       	apps[t] = id + " " + h + " " + d_f_name + " " + d_l_name + " " + d_u_name + " " + d_p_word + " " + a_code + " " + g_name; 
	       	t++;
		  }
		  rs.close();
		  s.close();
		//-------------------------------------||
		list = new JList<Object>();
		list.setLocation(0, 0);
		list.setSize(460,260);
		list.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
		list.setListData(apps);
		list.setVisible(true);
		add(list);
		
	}
}
