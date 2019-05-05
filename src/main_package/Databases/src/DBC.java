import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

public class DBC {
	static final String DRIVER_NAME="com.mysql.jdbc.Driver";
	static final String DB_URL="jdbc:mysql://localhost:3306/db1";
	static final String USERNAME="root";
	static final String PASSWORD="";
	static final String table_name="java_table_1";
	public static void main(String[] args) {
		Connection conn=null;
		Statement stmt=null;

		try{
			conn=(Connection)DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
			stmt = conn.createStatement();
			if(conn!=null)
			{
				System.out.println("Connected.");
				String query="insert into "+table_name+" values(?,?,?,?);";
				String del="delete from "+table_name;
			    //PreparedStatement delstatement=(PreparedStatement)conn.prepareStatement(del);
				//delstatement.executeUpdate();
				stmt.execute(del);
				System.out.println("previous data deleted");
				PreparedStatement ps=(PreparedStatement)conn.prepareStatement(query);
				ps.setString(1,"1");
				ps.setString(2,"hello");
				ps.setString(3,"20");
				ps.setString(4,"M");
				
				int rowid=ps.executeUpdate();
				if(rowid>0)
				{
					System.out.println("data inserted");
					String select="select * from "+table_name;
					stmt=(Statement) conn.createStatement();
					ResultSet rs=stmt.executeQuery(select);
					int count=0;
					while(rs.next()){						
						String id=rs.getString(1);
						String name=rs.getString(2);
						String age=rs.getString(3);
						String sex=rs.getString(4);
						System.out.println(id+" "+name+" "+age+" "+sex);
					}
					
				}
			}
			else{
				System.out.println("Fail.");
			}
		}
		catch(SQLException e){
			e.printStackTrace();
		}
		
	}

}
