package com.company;


import oracle.jdbc.OracleTypes;

import java.sql.*;

public class Main {

    public static void main(String[] args) {
	// write your code here
        // variables
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;

        // Step 1: Loading or
        // registering Oracle JDBC driver class
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
        }
        catch(ClassNotFoundException cnfex) {
            System.out.println("Problem in"
                    + " loading Oracle JDBC driver");
            cnfex.printStackTrace();
        }

        // Step 2: Opening database connection
        try {

            // Step 2.A: Create and
            // get connection using DriverManager class
            connection = DriverManager
                    .getConnection("jdbc:oracle:thin:@localhost:1521:xe",
                            "c##syedm",
                            "syedm123");

            // Step 2.B: Creating JDBC Statement
            statement = connection.createStatement();

            // Step 2.C: Executing SQL and
            // retrieve data into ResultSet
            resultSet = statement.executeQuery("SELECT * FROM testtable");

            System.out.println("ID\tName");
            System.out.println("==\t================");
            // System.out.println("fetch size : " + resultSet.getFetchSize());

            // processing returned data and printing into console
            while(resultSet.next()) {
                System.out.println(resultSet.getInt(1) + "\t" +
                        resultSet.getString(2));
            }
            System.out.println("---- DONE ----");

            CallableStatement cstmt = null;
            try {
                String sql = "{call GETNAME(?, ?)}";
                cstmt = connection.prepareCall(sql);
                cstmt.setInt(1, 1);
                cstmt.registerOutParameter(2, OracleTypes.CURSOR);
                cstmt.execute();
                resultSet = (ResultSet) cstmt.getObject(2);
                while (resultSet.next())   {
                    System.out.println(resultSet.getInt(1) + "\t" +
                            resultSet.getString(2));
                }
            }
            catch(SQLException sqx)  {
                System.out.println("sql exception " + sqx);
            }

        }
        catch(SQLException sqlex){
            sqlex.printStackTrace();
        }
        finally {
            // Step 3: Closing database connection
            try {
                if(null != connection) {
                    // cleanup resources, once after processing
                    resultSet.close();
                    statement.close();

                    // and then finally close connection
                    connection.close();
                }
            }
            catch (SQLException sqlex) {
                sqlex.printStackTrace();
            }
        }
    }
}
