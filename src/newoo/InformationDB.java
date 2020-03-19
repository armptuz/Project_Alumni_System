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
import javax.naming.Context;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

/**
 *
 * @author Arm
 */
public class InformationDB extends Database {

    private String information_id;
    private String information_title;
    private String information_date;
    String id;
    String username;
    int usertype;

    InformationDB() {

    }

    public InformationDB(String information_id, String information_title, String information_date) {
        this.information_id = information_id;
        this.information_title = information_title;
        this.information_date = information_date;
    }

    public String getInformation_id() {
        return information_id;
    }

    public String getInformation_title() {
        return information_title;
    }

    public String getInformation_date() {
        return information_date;
    }

    public void add(String id, String Information_Title, String Information_Detail, String username) {
        Properties pp = new Properties();
        pp.put("User_ID", id);
        pp.put("Information_Title", Information_Title);
        pp.put("Information_Detail", Information_Detail);
//        pp.put("Information_Date","Default");
        pp.put("status", "1");
        System.out.println(insert("information", pp));
//        System.out.println(id+""+Information_Title+""+Information_Detail+""+username);
//    String query = "INSERT INTO `information` (`Information_ID`, `User_ID`, `Information_Date`, `Information_Title`, `Information_Detail`, `Information_Status`) VALUES (NULL, '" + id + "', CURRENT_TIMESTAMP, '" + Information_Title + "', '" + Information_Detail + "', '1');";
//    new Logs().updateLog(username, query);
//    executeSQLQuery(query, "intserted");

    }

    public void edit(String Information_Title, String Information_Detail, String Information_ID, String username) {
        Properties pp = new Properties();
//        pp.put("User_ID", id);
        pp.put("Information_Title", Information_Title);
        pp.put("Information_Detail", Information_Detail);
        pp.put("status", "1");
        pp.put("Information_ID", Information_ID);
//        System.out.println(update("information", pp, "Information_ID"));
        System.out.println(testupdate("information", pp, "Information_ID"));
//        String query = "UPDATE information SET information.Information_Title = '" + Information_Title + "' , information.Information_Detail = '" + Information_Detail + "' WHERE Information_ID = " + Information_ID;
//        executeSQLQuery(query, "UPDATED");
//        new Logs().updateLog(username, query);
    }

    public void delete(String Information_ID, String username) {
//        
        System.out.println(delete("information", Information_ID, "Information_ID"));
    }

    public String getinformationID(String username) throws SQLException {
        String queryID = "SELECT Id FROM user WHERE Username = '" + username + "'";
        ResultSet rs = querys(queryID);
        rs.next();
        return rs.getString(1);
    }

    public String getinformationDetail(String informationID) throws SQLException {
        String query = "SELECT Information_Detail FROM information WHERE Information_ID =" + informationID;
        Database db = new Database();
        ResultSet rs = db.querys(query);
        rs.next();
        return rs.getString(1);
    }

    public ArrayList<InformationDB> getProductList() {
        ArrayList<InformationDB> productList = new ArrayList<InformationDB>();
        Database db = new Database();
        String query = "SELECT Information_ID,Information_Title,Information_Date FROM information WHERE information.status = 1";
        Statement st;
        ResultSet rs;

        try {

            rs = db.querys(query);
            InformationDB AllInformation1;

            while (rs.next()) {
                AllInformation1 = new InformationDB(rs.getString("information_id"), rs.getString("information_title"), rs.getString("information_date"));

                productList.add(AllInformation1);

            }

        } catch (Exception e) {
            e.printStackTrace();

        }

        return productList;

    }

}
