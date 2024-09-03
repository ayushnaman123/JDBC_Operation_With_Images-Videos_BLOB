package in.coder.ai;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import org.apache.commons.io.IOUtils;

import in.coder.util.JdbcUtil;

public class RetrivalImageApp {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet resultSet = null;
		Scanner scanner = null;
		
		int id = 0;
		try {
			connection = JdbcUtil.getJdbcConnection();
			
			String sqlSelectQuery = "Select id, name, image from persons where id=?";
			if(connection!=null)
				pstmt = connection.prepareStatement(sqlSelectQuery);
			
			if(pstmt!=null) {
				scanner = new Scanner(System.in);
				if(scanner!=null) {
					System.out.println("Enter id: ");
					id = scanner.nextInt();
					
				}
				pstmt.setInt(1, id);
				resultSet = pstmt.executeQuery();
				
				
			}
			
			if(resultSet!=null) {
				if(resultSet.next()) {
					System.out.println("ID\tNAME\tIMAGE");
					int pid = resultSet.getInt(1);
					String pname = resultSet.getString(2);
					InputStream is = resultSet.getBinaryStream(3);
					
					File file = new File("copiedvideo3.mp4");
					FileOutputStream fos = new FileOutputStream(file);
					
					IOUtils.copy(is, fos);
					System.out.println(pid+"\t"+pname+"\t"+file.getAbsolutePath());
					fos.close();
				}else {
					System.out.println("Record Not Found for the given ID: "+id);
				}
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
