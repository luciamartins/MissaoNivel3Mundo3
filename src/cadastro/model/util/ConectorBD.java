package cadastro.model.util;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.DriverManager;

public class ConectorBD {
    
    public Connection getConnection() throws Exception {
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        return DriverManager.getConnection("jdbc:sqlserver://localhost:55967;databaseName=loja;encrypt=true;trustServerCertificate =true;",
                "loja", "loja");
    }
    
    public PreparedStatement getPrepared(String sql) throws Exception {
        Connection con = getConnection();
        return con.prepareStatement(sql);
    }
    
    public ResultSet getSelect(String sql) throws Exception{
        PreparedStatement ps = getPrepared(sql);
        return ps.executeQuery();
    }
    
    public void close(Connection conn) throws Exception {
        if(conn != null){
            try{
                conn.close();
            }catch(SQLException e){
                e.printStackTrace();
            }
        }     
    }
    
    public void close(Statement st) throws Exception {
        if(st != null){
            try{
                st.close();
            }catch(SQLException e){
                e.printStackTrace();
            }
        } 
    }

    public void close(ResultSet rs) throws Exception {
        if(rs != null){
            try{
                rs.close();
            }catch(SQLException e){
                e.printStackTrace();
            }
        }
    }
    
}
