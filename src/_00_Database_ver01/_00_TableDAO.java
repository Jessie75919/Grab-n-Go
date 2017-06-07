package _00_Database_ver01;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import _00_init.GlobalService;

public class _00_TableDAO {
      PreparedStatement pstmt;
      PreparedStatement pstmt1;
      Statement stmt;
      DataSource ds ;
      
      
      // 由JNDI取得DataSource物件
      private void getDataSource(){
    	  try {
			Context cot = new InitialContext();
			ds = (DataSource) cot.lookup(GlobalService.JNDI_DB_NAME);
		} catch (NamingException e) {
			System.out.println("JNDI gets error");
			e.printStackTrace();
		}
      }
      
      public int insertMember(){
    	  getDataSource();
    	  System.out.println();
    	  try(Connection con = ds.getConnection();)
    	  {
    		  
			
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	  
    	  
    	  return -1;
      }
      
      
      
    
      private String[] readSqlFiles(String sqlFileName){
    	  
    	  try(
    			FileInputStream fis = new FileInputStream(sqlFileName);
    			InputStreamReader isr = new InputStreamReader(fis, "UFT-8");
    			BufferedReader br = new BufferedReader(isr);
    	  ){
    		  
    	  } catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	  
    	  
    	  return null;
      }
      
      
}
