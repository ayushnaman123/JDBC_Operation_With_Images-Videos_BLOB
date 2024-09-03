package in.coder.ai;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

import in.coder.util.JdbcUtil;

public class InsertApp {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Connection connection = null;
		PreparedStatement pstmt = null;
		
		Scanner scanner = null;
		
		String name = null;
		String image = null;
		int rowsAffected = 0;
		
		
		try {
			connection = JdbcUtil.getJdbcConnection();
			String sqlInsertQuery = "Insert into persons(`name`,`image`) values(?,?)";
			if(connection!=null)
				pstmt = connection.prepareStatement(sqlInsertQuery);
			
			if(pstmt!=null) {
				scanner = new Scanner(System.in);
				
				if(scanner!=null) {
					System.out.println("Enter name: ");
					name = scanner.next();
					
					System.out.println("Enter image: ");
					image = scanner.next();
					
				}
				
				pstmt.setString(1, name);
				pstmt.setBinaryStream(2, new FileInputStream(new File(image)));
				
				rowsAffected = pstmt.executeUpdate();
				System.out.println("No of rows Affected:: "+rowsAffected);
			}
		} catch (IOException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			try {
				JdbcUtil.getJdbcConnection();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			scanner.close();
		}
		
		
		
	}

}
