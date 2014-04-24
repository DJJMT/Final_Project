//package Calendar;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.ListSelectionModel;

//CLASS COMBO BOX VIEW ALL GROUPS 
@SuppressWarnings("serial")
class Combo_Box_View_All_Groups extends JFrame
{
	//VARIABLES
	JFrame MainPanel = new JFrame();
	@SuppressWarnings("rawtypes")
	JList list;
	String apps[] = new String[50];
	
	//CONSTRUCTOR
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public Combo_Box_View_All_Groups() throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException
	{
		//MAIN FRAME
		super("Team J's Scheduler | View All Groups");
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
			  s.executeQuery ("SELECT * FROM groups;");
		  }
		  else if(GUI.Level == 2)
		  {
			  s.executeQuery ("SELECT * FROM groups WHERE G_Name = \'"+GUI.ugn+"\';");
		  }
		  ResultSet rs = s.getResultSet();
		  int g_id = 0, m_count = 0, t = 0;
		  String g_name = "";
		  while (rs.next())
		  {
			  g_id = rs.getInt("G_ID"); 
	       	  g_name = rs.getString("G_Name");
	       	  m_count = rs.getInt("Member_Count");
	       	apps[t] = g_id + " " + g_name + " " + m_count; 
	       	t++;
		  }
		  rs.close();
		  s.close();
		//-------------------------------------||
		list = new JList();
		list.setLocation(0, 0);
		list.setSize(460,260);
		list.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
		list.setListData(apps);
		list.setVisible(true);
		add(list);
		
	}
}
