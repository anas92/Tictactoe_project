package dbconnection;
import java.sql.*;

public class DbConnection{
    private static final String USERNAME ="root";
    private static final String PASSWORD ="";
    private static final String CONN_STRING= "jdbc:mysql://localhost:1521/DbConnection";
    
    public void connect() {
        try{
            
    Connection con=DriverManager.getConnection(CONN_STRING,USERNAME,PASSWORD);
    System.out.println("connected");
    Statement stmt=(Statement)con.createStatement();
//    String query;
//    query=new String("select* from player");
//    ResultSet rs=stmt.executeQuery(query);
//    while(rs.next()){
//      System.out.println(rs.getString(1));
//    }
//    stmt.close();
//    con.close();
//
  }    
  catch(SQLException ex){
    ex.printStackTrace();
    System.err.println(ex);
  }
    }
 public ResultSet get(String name){
		try {
                        Connection con=DriverManager.getConnection(CONN_STRING,USERNAME,PASSWORD);
			String query = "Select * from player where name =" + "'"+name+"'";
			Statement stmt = (Statement)con.createStatement();
			ResultSet resultSet = stmt.executeQuery(query);
			if (resultSet.next() == false){
				System.out.println("Player does not exist,create new player for "+ name);
				String update = "Insert into playerinfo values (" + "'"+name+"'"+",0,0,0)";
				stmt.executeUpdate(update);
				resultSet = stmt.executeQuery(query);
				resultSet.next();
			}
			
			return resultSet;
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.print("failed to query");
		}
		return null;
	}   
 public String getWins(String name){
		try 
                {
                    return get(name).getString("win");
		}
                catch (SQLException e) {
                    e.printStackTrace();
		}
		return "XX";
	}
	
	public String getLoses(String name){
		try {
			return get(name).getString("lose");
		}
                catch (SQLException e) {
			e.printStackTrace();
		}
		return "XX";
	}
//	public String getTies(String name){
//		try {
//			return get(name).getString("tie");
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		return "XX";
//	}
	
	public void updateWins(String name){
		int num = Integer.parseInt(getWins(name));
		num++;
		String update = "update player set winner_id = " + num+ " where name = "+"'"+name+"'";
		try {
                    
                        Connection con=DriverManager.getConnection(CONN_STRING,USERNAME,PASSWORD);
                        Statement stmt = (Statement)con.createStatement();
			stmt.executeUpdate(update);
			System.out.println("Record updated!");
		} 
                catch (Exception e) 
                {
			e.printStackTrace();
		}
	};
	
	public void updateLoses(String name){
		int num = Integer.parseInt(getLoses(name));
		num++;
		String update = "update player_game set winner_id = " + num+ " where name = "+"'"+name+"'";
		try {
                        Connection con=DriverManager.getConnection(CONN_STRING,USERNAME,PASSWORD);
                        Statement stmt = (Statement)con.createStatement();
			stmt.executeUpdate(update);
			System.out.println("Record updated!");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	};
	
//	public void updateTies(String name){
//		int num = Integer.parseInt(getTies(name));
//		num++;
//		String update = "update playerinfo set tie = " + num+ " where name = "+"'"+name+"'";
//		try {
//                        Connection con=DriverManager.getConnection(CONN_STRING,USERNAME,PASSWORD);
//                        Statement stmt = (Statement)con.createStatement();
//			stmt.executeUpdate(update);
//			System.out.println("Record updated!");
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	};
	
	public static void main(String arg[]){
		DbConnection db = new DbConnection();
		db.connect();
//		db.updateWins("test");
//		db.updateLoses("test");
//		db.updateTies("test");
//		System.out.println(db.getWins("test"));
//		System.out.println(db.getLoses("test"));
//		System.out.println(db.getTies("test"));
	}
}
