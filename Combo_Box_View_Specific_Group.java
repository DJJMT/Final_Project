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

//CLASS COMBO_BOX VIEW SPECIFIC GROUP 
@SuppressWarnings("serial")
class Combo_Box_View_Specific_Group extends JFrame
{
	//VARIABLES
	JFrame MainPanel = new JFrame();
	String apps = "";
	static JLabel outputer = new JLabel();
	JTextField group_input = new JTextField();
	JButton Lookup = new JButton("Lookup");
	JLabel group_label = new JLabel ("Group to Look Up: ");
	
	//CONSTRUCTOR
	public Combo_Box_View_Specific_Group() throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException
	{
		//MAIN FRAME
		super("Team J's Scheduler | View Specific Group");
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
		  
		/*LABEL - GROUP_LABEL*/
		group_label.setLayout(null);
		group_label.setLocation(20, 150);
		group_label.setSize(200,25);
		add(group_label);   
		 
		//GROUP INPUT BOX
		group_input.setLayout(null);
		group_input.setLocation(150,150);
		group_input.setSize(210,25);
		add(group_input);
		
		//CREATE BUTTON
		Lookup.setLayout(null);
	    Lookup.setLocation(150, 180);
	    Lookup.setSize(170, 25);
		Lookup.addActionListener(new ActionListener() {
	public void actionPerformed(ActionEvent e) {
		try {
				//CALL TO LOOKUP FUNCTION BELOW
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
		
		
	}
	//LOOKUP FUNCTION WITH SQL QUERIES
	public void sql_lookup() throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException
	{
		Class.forName("com.mysql.jdbc.Driver").newInstance();
		Statement s = GUI.con.createStatement();
		s.executeQuery ("SELECT * FROM groups");
		ResultSet rs = s.getResultSet();
		boolean found = false;
		String input_group_name = group_input.getText();
		int g_id = 0, m_count = 0;
		String g_name = "";
		while (rs.next() && found == false)
		{
			//SET LOCAL VALUES EQUAL TO DATABASE VALUES
			g_id = rs.getInt("G_ID"); 
	       	m_count = rs.getInt("Member_Count");
	       	g_name = rs.getString("G_Name");
	       	System.out.println(g_name + " " + input_group_name);
	        if(g_name.equals(input_group_name))
	        {
	        	//SEARCH FOR GROUP IN DATABASE BY GROUP NAME
	            found = true;
	            Combo_Box_View_Specific_Group.outputer.setText(g_id + " " + g_name + " " + m_count);
	        }			    
		}
		rs.close();
		s.close();
		//-------------------------------------||
	}
}
