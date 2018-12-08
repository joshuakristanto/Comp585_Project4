package util;

import com.sun.rowset.CachedRowSetImpl;


import javax.sql.rowset.CachedRowSet;
import javax.xml.transform.Result;
import java.sql.*;

public class DbUtil {

    private static String url = "jdbc:sqlite:./.idea/fbLiteInfo.db";
    private static Connection conn = null;

    public static void dbConnect() throws SQLException {

        try {

            conn = DriverManager.getConnection(url);
            System.out.println("Connection to sqlite db has been established");

        } catch(Exception e) {
            System.out.println("sqlite db connection has failed ");
        }


    }

    public static void dbDisconnect() throws SQLException {

        try {
            if(conn != null && conn.isClosed() != true) {
                conn.close();
            }

        } catch(Exception e) {
            System.out.println("sqlite db connection could not be closed");
        }



    }

    public static ResultSet dbExecuteQuery(String newQuery) throws SQLException {

        Statement stmt = null;
        ResultSet resSet = null;
        CachedRowSetImpl cachedRowSet = null;


        try {
            dbConnect();
            System.out.println("Select Statement to be executed " + newQuery + "\n");

            // the purpose of this is to create the statement
            stmt = conn.createStatement();

            System.out.printf("success at stmt creation");

            // now we will execute the statement
            resSet = stmt.executeQuery(newQuery);

            System.out.println("executed query");

            // prevents a close connection next error
            cachedRowSet = new CachedRowSetImpl();
            cachedRowSet.populate(resSet);

        } catch(SQLException e) {
            System.out.println("the statement " + newQuery + " could not be executed");
            throw e;
        } finally {
            if(resSet != null) {
                resSet.close();
            }

            if(stmt != null) {
                stmt.close();
            }

            dbDisconnect();
        }

        return cachedRowSet;
    }

    // this is for updating, adding, and deleting from the sqlite db
    public static void dbExecuteUpdate(String sqlStatement) throws SQLException, ClassNotFoundException{


        //Declare statement as null
        Statement stmt = null;
        try {
            //Connect to DB (Establish Oracle Connection)
            dbConnect();
            //Create Statement
            stmt = conn.createStatement();
            //Run executeUpdate operation with given sql statement
            stmt.executeUpdate(sqlStatement);
        } catch (SQLException e) {
            System.out.println("Issue occured during the execution of this statement: " + sqlStatement);
            throw e;
        } finally {
            if (stmt != null) {
                //Close statement
                stmt.close();
            }
            //Close connection
            dbDisconnect();
        }
    }




}
