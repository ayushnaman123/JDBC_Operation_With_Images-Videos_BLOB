package in.coder.ai;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

import in.coder.util.JdbcUtil;

public class UpdateApp {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Connection connection = null;
		PreparedStatement pstmt = null;
		Scanner scanner = null;
		
		int id =0;
		String image = null;
		
		int rowsAffected = 0;
		
		
		try {
			connection = JdbcUtil.getJdbcConnection();
			String sqlUpdateQuery = "Update persons set image=? where id=?";
			if(connection!=null) {
				pstmt = connection.prepareStatement(sqlUpdateQuery);
			}
			
			if(pstmt!=null) {
				scanner = new Scanner(System.in);
				
				if(scanner!=null) {
					System.out.println("Enter id: ");
					id = scanner.nextInt();
					System.out.println("Enter the image path: ");
					image = scanner.next();
					
				}
				
				if(image!=null) {
					pstmt.setBinaryStream(1, new FileInputStream(new File(image)));
					pstmt.setInt(2, id);
					rowsAffected = pstmt.executeUpdate();
					System.out.println("Number of rows Affected:: "+rowsAffected);
				}
				
				
			}
		} catch (IOException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			try {
				JdbcUtil.cleanUp(connection, pstmt, null);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			scanner.close();
		}

	}

}
