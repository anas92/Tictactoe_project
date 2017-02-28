package tictactoe_project;

import java.sql.*;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

public class dbConnection{
static Connection con ;
static Statement stmt ;
    public dbConnection() {
        try {
          Class.forName("com.mysql.jdbc.Driver").newInstance();
        }
        catch (Exception e) {
          e.printStackTrace();
        }

        try {
        con = DriverManager.getConnection("jdbc:mysql://localhost/tictactoe?useSSL=false","root","iti");
        stmt = con.createStatement() ;
        //String queryString = new String("select * from User");
        //ResultSet rs = stmt.executeQuery(queryString) ;
        //System.out.println("------------DATA-----------");
       /* while(rs.next())
          {
            System.out.println(rs.getString(1));
            System.out.println(rs.getString(2));
            System.out.println(rs.getString(3));
            System.out.println(rs.getString(4));
          }*/
    }
    catch(SQLException ex)
    {
      ex.printStackTrace();
    }
    }

    
 public ResultSet getPlayerByName(String name){
	
                       try {
                        String query = "Select * from User where name =" + "'"+name+"'";
                        System.out.println(query);
			stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			if (rs.next() == false){
				System.out.println("Player does not exist,create new player for "+ name);
				String update = "Insert into User values (" + "'"+name+"'"+",0,0,0)";
				stmt.executeUpdate(update);
				rs = stmt.executeQuery(query);
				rs.next();
			}
                        else
                        {
                          System.out.println(rs.getString(1));
                          System.out.println(rs.getString(2));
                          System.out.println(rs.getString(3));
                          System.out.println(rs.getString(4));
                        
                        }
                            stmt.close();
                            con.close();

                          } catch (Exception e) {
                              e.printStackTrace();
                          }
		return null;
	}   
        public String getWinner(String name){
		try 
                {
                    return getPlayerByName(name).getString("win");
		}
                catch (SQLException e) {
                    e.printStackTrace();
		}
		return "XX";
	}
	
	public String getLoser(String name){
		try {
			return getPlayerByName(name).getString("lose");
		}
                catch (SQLException e) {
			e.printStackTrace();
		}
		return "XX";
	}
       
	public void updateWinner(String name){
		int num = Integer.parseInt(getWinner(name));
		num++;
		String update = "update player set winner_id = " + num+ " where name = "+"'"+name+"'";
		try {
                    
			stmt.executeUpdate(update);
			System.out.println("player, updated!");
		} 
                catch (Exception e) 
                {
			e.printStackTrace();
		}
	};
	
	public void updateLoses(String name){
		int num = Integer.parseInt(getLoser(name));
		num++;
		String update = "update game_player set User_id = " + num+ " where name = "+"'"+name+"'";
		try {
			stmt.executeUpdate(update);
			System.out.println("game_player, updated!");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	};
        public void insertPlayer(String name,int mode,char shape_type){
            
                try {
                    con = DriverManager.getConnection("jdbc:mysql://localhost/tictactoe?useSSL=false","root","iti");
                    stmt = con.createStatement() ;
                } catch (SQLException ex) {
                    Logger.getLogger(dbConnection.class.getName()).log(Level.SEVERE, null, ex);
                }
                
                String insert = "INSERT INTO User SET name='"+name+"', mode ="+ mode+", shape_type='"+shape_type+"'";
		try 
                {
                    stmt.executeUpdate(insert);
                    System.out.println("User, inserteed!");
                  
                }
        
            catch (Exception ex) {
                ex.printStackTrace();
              }
              finally{
                    try {
                        stmt.close();
                        con.close();
                    } catch (SQLException ex) {
                        Logger.getLogger(dbConnection.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    
                }
              
    }
        
        public void insertGame(int mode){
            
                try {
                    con = DriverManager.getConnection("jdbc:mysql://localhost/tictactoe?useSSL=false","root","iti");
                    stmt = con.createStatement() ;
                } catch (SQLException ex) {
                    Logger.getLogger(dbConnection.class.getName()).log(Level.SEVERE, null, ex);
                }
                
                String insert = "INSERT INTO Game SET  mode ="+mode;
		try 
                {
                    stmt.executeUpdate(insert);
                    System.out.println("Game, inserteed!");
                  
                }
        
            catch (Exception ex) {
                ex.printStackTrace();
              }
              finally{
                    try {
                        stmt.close();
                        con.close();
                    } catch (SQLException ex) {
                        Logger.getLogger(dbConnection.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    
                }
              
            }
         public void updateGame(String name,int id){
            
                try {
                    con = DriverManager.getConnection("jdbc:mysql://localhost/tictactoe?useSSL=false","root","iti");
                    stmt = con.createStatement() ;
                } catch (SQLException ex) {
                    Logger.getLogger(dbConnection.class.getName()).log(Level.SEVERE, null, ex);
                }
                
                String insert = "UPDATE Game set name='"+name+"' where id="+id;
		try 
                {
                    stmt.executeUpdate(insert);
                    System.out.println("Game, updated!");
                  
                }
                catch (Exception ex) {
                ex.printStackTrace();
              }
              finally{
                    try {
                        stmt.close();
                        con.close();
                    } catch (SQLException ex) {
                        Logger.getLogger(dbConnection.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    
                }
              
            }
            public HashMap<Integer,String> selectGameByName(String name){
            
                try {
                    con = DriverManager.getConnection("jdbc:mysql://localhost/tictactoe?useSSL=false","root","iti");
                    stmt = con.createStatement() ;
                } catch (SQLException ex) {
                    Logger.getLogger(dbConnection.class.getName()).log(Level.SEVERE, null, ex);
                }
                
                String select = "SELECT shape_type , position from Step INNER JOIN Game on Step.game_id=Game.id INNER JOIN User on Step.user_id=User.id WHERE Game.name='"+name+"'";
                HashMap<Integer,String> myData = new HashMap<Integer,String>();
		try 
                {
                    ResultSet rs=stmt.executeQuery(select);
                    
                    while (rs.next()) {
                        String  shape_type = rs.getString("shape_type");
                        int position = rs.getInt("position");
                        myData.put(position,shape_type);
                        System.out.println(shape_type + "\t" + position);
                    }
                                    
                }
                catch (Exception ex) {
                ex.printStackTrace();
              }
              finally{
                    try {
                        stmt.close();
                        con.close();
                    } catch (SQLException ex) {
                        Logger.getLogger(dbConnection.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    
                }
              return myData;
            }
            public void insertGamePlayer(int game_id,int user_id){
            
                try {
                    con = DriverManager.getConnection("jdbc:mysql://localhost/tictactoe?useSSL=false","root","iti");
                    stmt = con.createStatement() ;
                } catch (SQLException ex) {
                    Logger.getLogger(dbConnection.class.getName()).log(Level.SEVERE, null, ex);
                }
                
                String insert = "INSERT INTO Game_Player SET  game_id="+game_id+" , user_id="+user_id;
		try 
                {
                    stmt.executeUpdate(insert);
                    System.out.println("Game_Player, inserteed!");
                  
                }
        
            catch (Exception ex) {
                ex.printStackTrace();
              }
              finally{
                    try {
                        stmt.close();
                        con.close();
                    } catch (SQLException ex) {
                        Logger.getLogger(dbConnection.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    
                }
              
            }
            public void insertStep(int game_id,int user_id ,int position){
            
                try {
                    con = DriverManager.getConnection("jdbc:mysql://localhost/tictactoe?useSSL=false","root","iti");
                    stmt = con.createStatement() ;
                } catch (SQLException ex) {
                    Logger.getLogger(dbConnection.class.getName()).log(Level.SEVERE, null, ex);
                }
                
                String insert = "INSERT INTO Step set game_id="+game_id+" , user_id="+user_id+" , position="+position;
		try 
                {
                    stmt.executeUpdate(insert);
                    System.out.println("Step, inserteed!");
                  
                }
        
            catch (Exception ex) {
                ex.printStackTrace();
              }
              finally{
                    try {
                        stmt.close();
                        con.close();
                    } catch (SQLException ex) {
                        Logger.getLogger(dbConnection.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    
                }
              
            }

                public int getMaxGameId(){
                try {
                    con = DriverManager.getConnection("jdbc:mysql://localhost/tictactoe?useSSL=false","root","iti");
                    stmt = con.createStatement() ;
                } catch (SQLException ex) {
                    Logger.getLogger(dbConnection.class.getName()).log(Level.SEVERE, null, ex);
                }
                
                String selectMaxID = "select max(id) from Game";
                int maxid=0;
		try 
                {
                    ResultSet rs= stmt.executeQuery(selectMaxID);
                    rs.next();
                    System.out.println("MaxGameID: "+rs.getInt(1));
                    maxid=rs.getInt(1); 
                }
                catch (Exception ex) {
                    ex.printStackTrace();
                    }
                finally{
                    try {
                        stmt.close();
                        con.close();
                    } catch (SQLException ex) {
                        Logger.getLogger(dbConnection.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    
                }
              return maxid;
            }
                 
               public int getMaxPlayerId(){
                try {
                    con = DriverManager.getConnection("jdbc:mysql://localhost/tictactoe?useSSL=false","root","iti");
                    stmt = con.createStatement() ;
                } catch (SQLException ex) {
                    Logger.getLogger(dbConnection.class.getName()).log(Level.SEVERE, null, ex);
                }
                
                String selectMaxID = "select max(id) from User";
                int maxid=0;
		try 
                {
                    ResultSet rs= stmt.executeQuery(selectMaxID);
                    rs.next();
                    System.out.println("MaxPlayerID: "+rs.getInt(1));
                    maxid=rs.getInt(1); 
                }
                catch (Exception ex) {
                    ex.printStackTrace();
                    }
                finally{
                    try {
                        stmt.close();
                        con.close();
                    } catch (SQLException ex) {
                        Logger.getLogger(dbConnection.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    
                }
              return maxid;
            } 
//               public static void main(String[] args)
//               {
//                    dbConnection dd=new dbConnection();
//                    HashMap<Integer,String> data=dd.selectGameByName("testname");
//                    System.out.println("-----------------");
//                    for (Map.Entry<Integer,String> entry : data.entrySet()) 
//                    {
//                       Integer key = entry.getKey();
//                       String value = entry.getValue();
//                       System.out.println(key + "\t" + value);   
//                    }
//               }
}
