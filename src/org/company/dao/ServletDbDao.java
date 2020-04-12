package org.company.dao;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.company.model.ServletDbModel;
public class ServletDbDao {

	private String url;
	private String user;
	private String password;
	private Connection connection;

	public ServletDbDao(String url,String user,String password){
		this.url = url;
		this.user = user;
		this.password = password;
	}

	protected void connect()throws SQLException {
		if(connection == null || connection.isClosed()) {
			//open connection
			try {
				Class.forName("org.postgresql.Driver");
			}catch(ClassNotFoundException e) {
				throw new SQLException(e);
			}
			connection = DriverManager.getConnection(url, user, password);
		}
	}

	/**
	 * Insert
	 */
	public void ServletDbDaos(ServletDbModel model)throws SQLException {
		connect();	 	
		PreparedStatement statement = null;
		try {

			String sql = "INSERT INTO issue_tracker(issue_id,issue_title,assignee,priority) VALUES(?,?,?,?);"; 		      
			statement = connection.prepareStatement(sql);
			statement.setInt(1, model.getIssueId());
			statement.setString(2, model.getIssueTitle());
			statement.setString(3, model.getAssignee());
			statement.setString(4, model.getPriority());
			statement.executeUpdate();
			System.out.println("insert success");
		}catch (SQLException e) {

		}finally {
			try {
				if(connection!=null) {
					connection.close();
				}
			}catch (Exception e) {
				
			}
			try {
				if(statement!=null) {
					statement.close();
				}
			}catch (Exception e) {
				
			}
		}
	}

	/**
	 * Fetch all data
	 */	
	public List<ServletDbModel> listIssues() throws SQLException {
		List<ServletDbModel> listModel = new ArrayList<>();
		connect();	 	
		Statement statement = null;
		try {
			String sql = "SELECT * FROM issue_tracker order by issue_id;";	
			statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery(sql);
			while(resultSet.next()){
				Integer issueId = resultSet.getInt("issue_id");
				String issueTitle = resultSet.getString("issue_title");
				String assignee = resultSet.getString("assignee");
				String priority = resultSet.getString("priority");
				ServletDbModel model = new ServletDbModel(issueId, issueTitle, assignee, priority);
				listModel.add(model );                
			}
			System.out.println("fetch success");
		}catch (SQLException e) {

		}finally {
			try {
				if(connection!=null) {
					connection.close();
				}
			}catch (Exception e) {
				// TODO: handle exception
			}
			try {
				if(statement!=null) {
					statement.close();
				}
			}catch (Exception e) {
				// TODO: handle exception
			}
		}
		return listModel;
	}	

	/**
	 * Fetch particular data
	 */	
	public String title(Integer issueIdOne) throws SQLException {
		String title = null;
		connect();	 	
		PreparedStatement statement = null;
		try {
			String sql = "SELECT * FROM issue_tracker where issue_id=?;";	
			statement = connection.prepareStatement(sql);
			statement.setInt(1, issueIdOne);
			ResultSet resultSet = statement.executeQuery();
			while(resultSet.next()){
				Integer issueId = resultSet.getInt("issue_id");
				String issueTitle = resultSet.getString("issue_title");
				String assignee = resultSet.getString("assignee");
				String priority = resultSet.getString("priority");
				title = resultSet.getString("issue_title");           
			}
			System.out.println("fetch success");
		}catch (SQLException e) {

		}finally {
			try {
				if(connection!=null) {
					connection.close();
				}
			}catch (Exception e) {
				// TODO: handle exception
			}
			try {
				if(statement!=null) {
					statement.close();
				}
			}catch (Exception e) {
				// TODO: handle exception
			}
		}
		return title;
	}	
	
	/**
	 * update particular data
	 */	
	public void update(ServletDbModel model) throws SQLException {
		
		connect();	 	
		PreparedStatement statement = null;
		try {
			String sql = "UPDATE issue_tracker SET issue_title=?,assignee=?,priority=? where issue_id=?;";	
			statement = connection.prepareStatement(sql);
			statement.setString(1, model.getIssueTitle());
			statement.setString(2, model.getAssignee());
			statement.setString(3, model.getPriority());
			statement.setInt(4, model.getIssueId());
			statement.executeUpdate();
			System.out.println("update success");
		}catch (SQLException e) {

		}finally {
			try {
				if(connection!=null) {
					connection.close();
				}
			}catch (Exception e) {
				// TODO: handle exception
			}
			try {
				if(statement!=null) {
					statement.close();
				}
			}catch (Exception e) {
				// TODO: handle exception
			}
		}
		
	}

	/**
	 * Delete particular data from db
	 * @param issueId
	 * @throws SQLException 
	 */
	public void delete(Integer issueId) throws SQLException {
		connect();	 	
		PreparedStatement statement = null;
		try {
			String sql = "DELETE FROM issue_tracker where issue_id=?;";
			statement = connection.prepareStatement(sql);
			statement.setInt(1, issueId);
			statement.executeUpdate();
			System.out.println("delete success");
		}catch (SQLException e) {

		}finally {
			try {
				if(connection!=null) {
					connection.close();
				}
			}catch (Exception e) {
				// TODO: handle exception
			}
			try {
				if(statement!=null) {
					statement.close();
				}
			}catch (Exception e) {
				// TODO: handle exception
			}
		}
		
	}	
}
