/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package newoo;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Properties;

/**
 *
 * @author Pee
 */
public class BrachDb extends Database{

    private String branchId, branchName;
    String facultyId, showAll;

    public BrachDb(String branchId, String branchName) {
        this.branchId = branchId;
        this.branchName = branchName;
    }

    public String getBranchId() {
        return branchId;
    }

    public String getBranchName() {
        return branchName;
    }

    public ArrayList<BrachDb> getList(String q) {

        ArrayList<BrachDb> arrayList = new ArrayList<>();

        ResultSet rs;

        try {
            Database db = new Database();
            rs = db.querys(q);
            BrachDb facultyDisplay;
            while (rs.next()) {
                facultyDisplay = new BrachDb(rs.getString(1), rs.getString(3));
                arrayList.add(facultyDisplay);
            }

        } catch (SQLException ex) {
            System.out.println(ex.toString());
        }
        return arrayList;
    }

    public BrachDb(String facultyId) {
        this.facultyId = facultyId;
        showAll = "SELECT * FROM `branch` WHERE Faculty_ID = " + facultyId + " AND status = 1";
    }

    void add(String Branch_Name, String username) {
        Properties pp = new Properties();
        pp.put("Faculty_ID",facultyId);
        pp.put("Branch_Name",Branch_Name);
        pp.put("status","1");
        System.out.println(insert("branch", pp));
//        String q = "INSERT INTO branch (`Faculty_ID`, `Branch_Name`,status) VALUES (" + facultyId + ",'" + Branch_Name + "',1)";
//        try {
//            Database db = new Database();
//            db.executeSQLQuery(q, "INSERTED");
//            new Logs().updateLog(username, "INSERTED : " + Branch_Name + ",\nfId : " + facultyId);
////            showTable("SELECT * FROM `branch` WHERE Faculty_ID = " + facultyId + " AND status = 1");
//        } catch (Exception ex) {
//            System.out.println(ex.toString());
//        }
    }

    void edit(String Branch_Name, String username, String id) {
        Properties pp = new Properties();
        pp.put("Branch_ID",id);
        pp.put("Branch_Name",Branch_Name);
        System.out.println(testupdate("branch", pp, "Branch_ID"));
//        String q = "UPDATE branch SET Branch_Name = '" + Branch_Name + "' WHERE Branch_ID = " + id;
//        try {
//            Database db = new Database();
//            db.executeSQLQuery(q, "INSERTED");
//            new Logs().updateLog(username, "UPDATED idB : " + Branch_Name);
////            showTable("SELECT * FROM `branch` WHERE Faculty_ID = " + facultyId + " AND status = 1");
//        } catch (Exception ex) {
//            System.out.println(ex.toString());
//        }
    }

    void delete(String id, String username) {
        System.out.println(delete("branch", id, "Branch_ID"));
//        String q = "UPDATE branch SET status = 0 WHERE Branch_ID = " + id;
//        try {
//            Database db = new Database();
//            db.executeSQLQuery(q, "DELETED");
//            new Logs().updateLog(username, "DELETED Bid : " + id);
////            showTable("SELECT * FROM `branch` WHERE Faculty_ID = " + facultyId + " AND status = 1");
//        } catch (Exception ex) {
//
//        }
    }
}
