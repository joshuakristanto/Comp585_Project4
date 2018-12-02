package DAO;

import Models.Posts;
import Models.Profiles;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import util.DbUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class ProfilesDao {


    ///// Supported
    // get profile
    // add profile
    // delete profile
    // update age
    // update status


    ////  Unsupported


    /// returns a profile object based the UserName
    public static ObservableList<Profiles> searchProfiles(String UserName) throws Exception, ClassNotFoundException {
        //this is the SELECT statement
        String selectStmt = "SELECT * FROM Profiles WHERE UserName = " + "'" + UserName + "'";

        // execute select statement
        try {
            // getting the resultset containing the friends that will be iterated over
            ResultSet resProfiles = DbUtil.dbExecuteQuery(selectStmt);

            //Send ResultSet to the getEmployeeList method and get employee object
            ObservableList<Profiles> ProfilesList = getProfilesList(resProfiles);

            // return the friend  object
            return ProfilesList;
        } catch (Exception e) {
            System.out.println("SQL select in friendsDao failed: " + selectStmt);
            throw e;
        }
    }

    // returns the Select * From Posts operation to objects
    private static ObservableList<Profiles> getProfilesList(ResultSet resSet) throws Exception, ClassNotFoundException {
        //an observable List containing Employee objects
        ObservableList<Profiles> ProfilesList = FXCollections.observableArrayList();

        while (resSet.next()) {
            Profiles prof = new Profiles();
            prof.setUserName(resSet.getString("UserName"));
            prof.setFirstName(resSet.getString("FirstName"));
            prof.setLastName(resSet.getString("LastName"));
            prof.setAge(resSet.getString("Age"));
            prof.setEmail(resSet.getString("Email"));
            prof.setPassword(resSet.getString("Password"));
            prof.setStatus(resSet.getString("Status"));
            prof.setSettingsVisible(resSet.getString("SettingsVisible"));


            // add post object to postslist
            ProfilesList.add(prof);
        }
        //return a PostsList which is a obervablelist of post objects
        return ProfilesList;
    }


    ///////////// will return ALL profiles in a list
    public static ObservableList<Profiles> getAllProfiles(String UserName) throws Exception, ClassNotFoundException {
        //this is the SELECT statement
        String selectStmt = "SELECT * FROM Profiles";

        // execute select statement
        try {
            // getting the resultset containing the friends that will be iterated over
            ResultSet resProfiles = DbUtil.dbExecuteQuery(selectStmt);

            //Send ResultSet to the getEmployeeList method and get employee object
            ObservableList<Profiles> AllProfilesList = getProfilesList(resProfiles);

            // return the friend  object
            return AllProfilesList;
        } catch (Exception e) {
            System.out.println("SQL select in friendsDao failed: " + selectStmt);
            throw e;
        }
    }


/////////////////////// add a profile

    public static void addProfile(String newFirstName, String newLastName, String newAge, String newUserName, String newEmail, String newPassword, String newStatus, String newSettingsVisible) throws SQLException, ClassNotFoundException {


        // the addStmt
        String addStmt = "INSERT INTO Profiles(FirstName, LastName, Age, UserName, Email, Password, Status, SettingsVisible) VALUES(" + "'" + newFirstName + "'" + ", " + "'" + newLastName + "'" + ", " + "'" + newAge + "'" + ", " + "'" + newUserName + "'" + ", " + "'" + newEmail + "'" + ", " + "'" + newPassword + "'" + ", " + "'" + newStatus + "'" + ", " + "'" + newSettingsVisible + "'" + ")";

        // add operation
        try {
            DbUtil.dbExecuteUpdate(addStmt);

        } catch (SQLException e) {
            System.out.print("Error occurred while Operation: " + e);
            throw e;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /////// delete profiles                  if you want
    public static void deleteProfiles(String UserName) throws SQLException, ClassNotFoundException {
        // delete statement
        String deleteStmt = "DELETE FROM Profiles WHERE UserName = " + "'" + UserName + "'";

        // this will delete the PostText
        try {
            DbUtil.dbExecuteUpdate(deleteStmt);

        } catch (SQLException e) {
            System.out.print("Error occurred while DELETE Operation: " + e);
            throw e;
        }
    }

////update or reset password

    public static void updatePassword(String UserName, String newPassword) throws SQLException, ClassNotFoundException {


        // update statement
        String updateStmt = "UPDATE Profiles SET Password = " + "'" + newPassword + "'" + " WHERE UserName = " + "'" + UserName + "'";
        // this will update the passsword
        try {
            DbUtil.dbExecuteUpdate(updateStmt);

        } catch (SQLException e) {
            System.out.print("Error occurred while DELETE Operation: " + e);
            throw e;
        }
    }


    /////////// update Age
    public static void updateAge(String UserName, String newAge) throws SQLException, ClassNotFoundException {

        // update statement
        String updateStmt = "UPDATE Profiles SET Age = " + "'" + newAge + "'" + " WHERE UserName = " + "'" + UserName + "'";
        // this will update the passsword
        try {
            DbUtil.dbExecuteUpdate(updateStmt);

        } catch (SQLException e) {
            System.out.print("Error occurred while DELETE Operation: " + e);
            throw e;
        }
    }

    /////////// update status
    public static void updateStatus(String UserName, String newStatus) throws SQLException, ClassNotFoundException {

        // update statement
        String updateStmt = "UPDATE Profiles SET Status = " + "'" + newStatus + "'" + " WHERE UserName = " + "'" + UserName + "'";
        // this will update the status
        try {
            DbUtil.dbExecuteUpdate(updateStmt);

        } catch (SQLException e) {
            System.out.print("Error occurred while Operation: " + e);
            throw e;
        }
    }


}
