package DAO;


import Models.Friends;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import util.DbUtil;

import java.sql.ResultSet;
import java.sql.SQLException;

public class FriendsDao {

    /////////////////// SUPPORTED OPERATIONS
    // get all friends of a person
    // add friend
    // delete friend

    ////////////////// NOT SUPPORTED
    // get Friend --- currently no reason for this
    // updating or changing names in this table EX-if friend changes name etc etc


    /////////////// select all friends of a specific person
    public static ObservableList<Friends> searchFriends(String UserName) throws Exception, ClassNotFoundException {
        //this is the SELECT statement
        String selectStmt = "SELECT * FROM Friends WHERE UserName = " + "'" + UserName + "'";

        // execute select statement
        try {
            // getting the resultset containing the friends that will be iterated over
            ResultSet resFriends = DbUtil.dbExecuteQuery(selectStmt);

            // returns a list of friend objects
            ObservableList<Friends> FriendsList = getFriendsList(resFriends);

            // return the friend  object
            return FriendsList;
        } catch (Exception e) {
            System.out.println("SQL select in friendsDao failed: " + selectStmt);
            throw e;
        }


    }

    // returns the Select * From Friends operation to objects
    private static ObservableList<Friends> getFriendsList(ResultSet resSet) throws Exception, ClassNotFoundException {
        //an observable List containing Employee objects
        ObservableList<Friends> FriendsList = FXCollections.observableArrayList();

        while (resSet.next()) {
            Friends frnd = new Friends();
            frnd.setUserName(resSet.getString("UserName"));
            frnd.setFriendUserName(resSet.getString("FriendUserName"));

            // add friend to friendslist
            FriendsList.add(frnd);
        }
        //return a FriendsList which is a obervablelist of friends
        return FriendsList;
    }


    /////// add a friend
    public static void insertFriend(String newUserName, String newFriendUserName) throws SQLException, ClassNotFoundException {
        // the addStmt
        String addStmt = "INSERT INTO Friends(UserName, FriendUserName) VALUES(" + "'" + newUserName + "'" + ", " + "'" + newFriendUserName + "'" + ")";
        // must do the reverse ----- friendships are mututal
        String addStmt_Reverse = "INSERT INTO Friends(UserName, FriendUserName) VALUES(" + "'" + newFriendUserName + "'" + ", " + "'" + newUserName + "'" + ")";

        // add operation
        try {
            DbUtil.dbExecuteUpdate(addStmt);
            DbUtil.dbExecuteUpdate(addStmt_Reverse);

        } catch (SQLException e) {
            System.out.print("Error occurred while DELETE Operation: " + e);
            throw e;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    // Delete friends --- removes friendship of both
    public static void deleteFriend(String UserName, String friendToDeleteUserName) throws SQLException, ClassNotFoundException {
        // delete statment
        String deleteStmt = "DELETE FROM Friends WHERE UserName = " + "'" + UserName + "'" + " AND " + "FriendUserName = " + "'" + friendToDeleteUserName + "'";
        // delete statement for reverse
        String deleteStmt_Reverse = "DELETE FROM Friends WHERE UserName = " + "'" + friendToDeleteUserName + "'" + " AND " + "FriendUserName = " + "'" + UserName + "'";

        // this will delete the friendship
        try {
            DbUtil.dbExecuteUpdate(deleteStmt);
            DbUtil.dbExecuteUpdate(deleteStmt_Reverse);

        } catch (SQLException e) {
            System.out.print("Error occurred while DELETE Operation: " + e);
            throw e;
        }
    }


}
