package org;


import java.sql.*;

public class DbConnectivity {

    static Connection connection;
    Statement statement;
    ResultSet resultSet;
    PreparedStatement preparedStatement;

    public DbConnectivity(){
        try{
            String url = "jdbc:mysql://localhost:3306/banking_app";
            String user = "admin";
            String password = "admin";

            connection = DriverManager.getConnection(url,user,password);
            statement = connection.createStatement();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public ResultSet getSelectResultSet(String sql) {
        try{
            resultSet = statement.executeQuery(sql);
            return resultSet;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void executeUpdateOrInsertStatement(StringBuilder sql, Object... params){
        try{
            preparedStatement = connection.prepareStatement(sql.toString());

            for (int i = 0; i < params.length; i++) {
                if (params[i] instanceof Integer){
                    preparedStatement.setInt(i+1, (Integer) params[i]);
                }
                else if (params[i] instanceof String){
                    preparedStatement.setString(i+1, (String) params[i]);
                }
                else if(params[i] instanceof Double){
                    preparedStatement.setDouble(i+1, (Double) params[i]);
                }
            }
            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected>0){
                System.out.println("Rows affected : "+rowsAffected);
            } else {
                System.out.println("No rows affected");
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public ResultSet executeQueryWithPlaceHolders(StringBuilder sql, Object... params) {
        try {
            preparedStatement = connection.prepareStatement(sql.toString());

            for (int i = 0; i < params.length; i++) {
                if (params[i] instanceof Integer) {
                    preparedStatement.setInt(i + 1, (Integer) params[i]);
                }
                else if (params[i] instanceof String){
                    preparedStatement.setString(i+1, (String) params[i]);
                }
                else if(params[i] instanceof Double){
                    preparedStatement.setDouble(i+1, (Double) params[i]);
                }
            }
            resultSet = preparedStatement.executeQuery();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return resultSet;
    }
}
