package DAO;

import Models.Profiles;
import Models.Settings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import util.DbUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class SettingsDao {

////////////// Supported

    // get settings        gets all settings with a username --- the list return contains an object that lets you access all settings
    // you can update each field of settings


////////////// Unsupported


    ////// get list of settings from a UserName
    public static ObservableList<Settings> searchSettings(String UserName) throws Exception, ClassNotFoundException {
        //this is the SELECT statement
        String selectStmt = "SELECT * FROM Settings WHERE UserName = " + "'" + UserName + "'";

        // execute select statement
        try {
            // getting the resultset containing the friends that will be iterated over
            ResultSet resSettings = DbUtil.dbExecuteQuery(selectStmt);

            //Send ResultSet to the getEmployeeList method and get employee object
            ObservableList<Settings> SettingsList = getSettingsList(resSettings);

            // return the friend  object
            return SettingsList;
        } catch (Exception e) {
            System.out.println("SQL select in friendsDao failed: " + selectStmt);
            throw e;
        }
    }

    // returns all settings to objects
    private static ObservableList<Settings> getSettingsList(ResultSet resSet) throws Exception, ClassNotFoundException {
        //an observable List containing Employee objects
        ObservableList<Settings> SettingsList = FXCollections.observableArrayList();

        while (resSet.next()) {
            Settings stngs = new Settings();
            stngs.setUserName(resSet.getString("UserName"));
            stngs.setFriends(resSet.getString("Friends"));
            stngs.setStatus(resSet.getString("Status"));
            stngs.setPosts(resSet.getString("Posts"));
            stngs.setAge(resSet.getString("Age"));


            // add post object to postslist
            SettingsList.add(stngs);
        }
        //return a PostsList which is a obervablelist of post objects
        return SettingsList;
    }

    // update friends
// REMEMBER ONLY Y for yes and N for no
    public static void updateFriendsVisible(String UserName, String newFriendsVisible) throws SQLException, ClassNotFoundException {

        // if newFriendsVisible is not a Y or N it will be casted to Y

        if ((newFriendsVisible.equals("Y")) || (newFriendsVisible.equals("N"))) {
            // input is fine
        } else {
            newFriendsVisible = "Y";
        }

        // update statement
        String updateStmt = "UPDATE Settings SET Friends= " + "'" + newFriendsVisible + "'" + " WHERE UserName = " + "'" + UserName + "'";
        // this will delete the PostText
        try {
            DbUtil.dbExecuteUpdate(updateStmt);

        } catch (SQLException e) {
            System.out.print("Error occurred while DELETE Operation: " + e);
            throw e;
        }
    }

// update status

    public static void updateStatusVisible(String UserName, String newStatusVisible) throws SQLException, ClassNotFoundException {

        // if newFriendsVisible is not a Y or N it will be casted to Y

        if ((newStatusVisible.equals("Y")) || (newStatusVisible.equals("N"))) {
            // input is fine
        } else {

            newStatusVisible = "Y";
        }

        // update statement
        String updateStmt = "UPDATE Settings SET Status = " + "'" + newStatusVisible + "'" + " WHERE UserName = " + "'" + UserName + "'";
        // this will delete the PostText
        try {
            DbUtil.dbExecuteUpdate(updateStmt);

        } catch (SQLException e) {
            System.out.print("Error occurred while DELETE Operation: " + e);
            throw e;
        }
    }
// update posts

    public static void updatePostsVisible(String UserName, String newPostsVisible) throws SQLException, ClassNotFoundException {

        // if newFriendsVisible is not a Y or N it will be casted to Y

        if ((newPostsVisible.equals("Y")) || (newPostsVisible.equals("N"))) {
            // input is fine
        } else {

            newPostsVisible = "Y";
        }

        // update statement
        String updateStmt = "UPDATE Settings SET Posts = " + "'" + newPostsVisible + "'" + " WHERE UserName = " + "'" + UserName + "'";
        // this will delete the PostText
        try {
            DbUtil.dbExecuteUpdate(updateStmt);

        } catch (SQLException e) {
            System.out.print("Error occurred while DELETE Operation: " + e);
            throw e;
        }
    }


// update age


    public static void updateAgeVisible(String UserName, String newAgeVisible) throws SQLException, ClassNotFoundException {

        // if newFriendsVisible is not a Y or N it will be casted to Y

        if ((newAgeVisible.equals("Y")) || (newAgeVisible.equals("N"))) {
            // input is fine
        } else {

            newAgeVisible = "Y";
        }

        // update statement
        String updateStmt = "UPDATE Settings SET Age = " + "'" + newAgeVisible + "'" + " WHERE UserName = " + "'" + UserName + "'";
        // this will delete the PostText
        try {
            DbUtil.dbExecuteUpdate(updateStmt);

        } catch (SQLException e) {
            System.out.print("Error occurred while DELETE Operation: " + e);
            throw e;
        }
    }


//////////// this defaults all settings to visible
    public static void addUserToSettings(String UserName) throws SQLException, ClassNotFoundException {


        // add the time of the posts
        String newPostTime = new SimpleDateFormat("MM/dd/yyyy_HH:mm:ss").format(Calendar.getInstance().getTime());

        String Friends = "Y";
        String Status = "Y";
        String Posts = "Y";
        String Age = "Y";


        // the addStmt
        String addStmt = "INSERT INTO Settings(UserName, Friends, Status, Posts, Age) VALUES(" + "'" + UserName + "'" + "," + "'" + Friends + "'" + "," + "'" + Status + "'" + "," + "'" + Posts +"'" + "," + "'" + Age + "'" + ")";

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








}
