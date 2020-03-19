/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package newoo;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Properties;

/**
 *
 * @author Arm
 */
public class CommentDB extends Database {

    private String information_title;
    private String information_detail;
    private String information_date;
    private String name;
    private String comment_contents;
    private String comment_date;
    private String Information_ID;

    public CommentDB() {

    }

    public CommentDB(String name, String comment_contents, String comment_date) {
        this.name = name;
        this.comment_contents = comment_contents;
        this.comment_date = comment_date;

    }

    public String getInformation_title() {
        return information_title;
    }

    public String getInformation_detail() {
        return information_detail;
    }

    public String getInformation_date() {
        return information_date;
    }

    public String getName() {
        return name;
    }

    public String getComment_contents() {
        return comment_contents;
    }

    public String getComment_date() {
        return comment_date;
    }

    void addComment(String Information_Id, String User_ID, String Comment_Contents, String username) {
        Properties pp = new Properties();
        pp.put("Information_Id",Information_Id);
        pp.put("User_ID",User_ID);
        pp.put("Comment_Contents",Comment_Contents);
        pp.put("status","1");
        System.out.println(insert("comment", pp));
//        String query = "INSERT INTO comment (Comment_Id, Information_Id, User_ID, Comment_Contents, Comment_Date, Comment_Status) VALUES (NULL, '" + Information_Id + "', '" + User_ID + "', '" + Comment_Contents + "', CURRENT_TIMESTAMP, '1');";
//        new Logs().updateLog(username, "comment");
//        executeSQLQuery(query, "comment");
    }

    public String getUserID(String username) throws SQLException {
        String queryID = "SELECT Id FROM user WHERE Username = '" + username + "'";
        ResultSet rs = querys(queryID);
        rs.next();
        return rs.getString(1);
    }

    public ResultSet setInformation(String informationid) {
        String query = "SELECT information.Information_Title,information.Information_Detail,information.Information_Date\n"
                + "FROM information \n"
                + "WHERE information.Information_ID = " + informationid;

        ResultSet rs = querys(query);
        return rs;
    }

    public ArrayList<CommentDB> getProductList(String Information_ID) {
        ArrayList<CommentDB> productList = new ArrayList<CommentDB>();
        Database db = new Database();
        String query = "SELECT CONCAT(user.Fname,' ',user.Lname)AS name, comment.Comment_Contents,comment.Comment_Date FROM user ,comment\n"
                + "WHERE   user.Id = comment.User_ID AND comment.Information_Id = " + Information_ID + " AND comment.status = 1";
        Statement st;
        ResultSet rs;

        try {

            rs = db.querys(query);
            CommentDB CommentDB;

            while (rs.next()) {
                CommentDB = new CommentDB(rs.getString("name"), rs.getString("Comment_Contents"), rs.getString("Comment_Date"));
//                System.out.println(rs.getInt("id"));
                productList.add(CommentDB);
                // System.out.println(rs.getString("information_date"));
            }

        } catch (Exception e) {
            e.printStackTrace();

        }

        return productList;

    }

}
