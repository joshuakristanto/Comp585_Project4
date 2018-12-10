package DAO;

import Models.Friends;
import Models.Posts;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import util.DbUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class PostsDao {


    ////////// Supported
    // get posts
    // add posts
    // delete posts

    ////////// Unsupported


    ////////////////////////////// get all posts of a person using a UserName
    public static ObservableList<Posts> searchPosts(String UserName) throws Exception, ClassNotFoundException {
        //this is the SELECT statement
        String selectStmt = "SELECT * FROM Posts WHERE UserName = " + "'" + UserName + "'";

        // execute select statement
        try {
            // getting the resultset containing the friends that will be iterated over
            ResultSet resPosts = DbUtil.dbExecuteQuery(selectStmt);

            //Send ResultSet to the getEmployeeList method and get employee object
            ObservableList<Posts> PostsList = getPostsList(resPosts);

            // return the friend  object
            return PostsList;
        } catch (Exception e) {
            System.out.println("SQL select in friendsDao failed: " + selectStmt);
            throw e;
        }
    }

    // returns the Select * From Posts operation to objects
    private static ObservableList<Posts> getPostsList(ResultSet resSet) throws Exception, ClassNotFoundException {
        //an observable List containing Employee objects
        ObservableList<Posts> PostsList = FXCollections.observableArrayList();

        while (resSet.next()) {
            Posts pst = new Posts();
            pst.setUserName(resSet.getString("UserName"));
            pst.setPostText(resSet.getString("PostText"));
            pst.setPostTime(resSet.getString("PostTime"));

            // add post object to postslist
            PostsList.add(pst);
        }
        //return a PostsList which is a obervablelist of post objects
        return PostsList;
    }


    ///////////////////////////////// add posts with a UserName
    public static void insertPosts(String UserName, String newPostText) throws SQLException, ClassNotFoundException {

        // add the time of the posts
        String newPostTime = new SimpleDateFormat("MM/dd/yyyy_HH:mm:ss").format(Calendar.getInstance().getTime());

        // the addStmt
        String addStmt = "INSERT INTO Posts(UserName, PostText, PostTime) VALUES(" + "'" + UserName + "'" + ", " + "'" + newPostText + "'" + ", " + "'" + newPostTime + "'" + ")";

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


    // IMPORTANT
    /////////////////// delete posts using a UserName AND matching PostText
    /////////////////// you dont want to delete all posts from a single person
    public static void deletePosts(String UserName, String postTextToDelete) throws SQLException, ClassNotFoundException {
        // delete statement
        String deleteStmt = "DELETE FROM Posts WHERE UserName = " + "'" + UserName + "'" + " AND " + "PostText = " + "'" + postTextToDelete + "'";

        // this will delete the PostText
        try {
            DbUtil.dbExecuteUpdate(deleteStmt);

        } catch (SQLException e) {
            System.out.print("Error occurred while DELETE Operation: " + e);
            throw e;
        }
    }

    // Update userName
    public static void updateUserName(String UserName, String newUserName) throws SQLException, ClassNotFoundException{

        //Update stmt
        String updateStmt = "UPDATE Posts SET UserName = " + "'" + newUserName + "'"  + "WHERE UserName = " + "'" + UserName + "'";

        try{
            DbUtil.dbExecuteUpdate(updateStmt);
        }catch (SQLException e){
            System.out.println("Error occurred while updating username to " + newUserName );
        }

    }


}
