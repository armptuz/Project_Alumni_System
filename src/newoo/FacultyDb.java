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
public class FacultyDb extends Database {

    private String id, faculty;
    String showAll = "SELECT * FROM faculty WHERE status = 1";

    public FacultyDb(String id, String faculty) {
        this.id = id;
        this.faculty = faculty;
    }

    public String getId() {
        return id;
    }

    public String getFaculty() {
        return faculty;
    }

    public ArrayList<FacultyDb> getList(String q) {

        ArrayList<FacultyDb> arrayList = new ArrayList<>();

        ResultSet rs;

        try {
            Database db = new Database();
            rs = db.querys(q);
            FacultyDb facultyDisplay;
            while (rs.next()) {
                facultyDisplay = new FacultyDb(rs.getString(1), rs.getString(2));
                arrayList.add(facultyDisplay);
            }

        } catch (SQLException ex) {
            System.out.println(ex.toString());
        }
        return arrayList;
    }

    public void add(String facultyName, String username) {
        Properties pp = new Properties();
        pp.put("name", facultyName);
        pp.put("status", "1");
        System.out.println(insert("faculty", pp));
//        String q = "INSERT INTO faculty (Faculty_Name,status) VALUES ('" + facultyName + "' , 1)";
//        try {
//            Database db = new Database();
//            db.executeSQLQuery(q, "INSERTED");
//            new Logs().updateLog(username, "INSERTED F : " + facultyName);
////            showTable("SELECT * FROM faculty WHERE status = 1");
//        } catch (Exception ex) {
//            System.out.println(ex.toString());
//        }
    }

    public void edit(String facultyName, String username, String id) {
        Properties px = new Properties();
        
//        px.put("status", "1");
        
        px.put("Faculty_ID",id);
        px.put("name", facultyName);
//        System.out.println(update("faculty", px, "Faculty_ID"));
    System.out.println(testupdate("faculty", px, "Faculty_ID"));
//        String q = "UPDATE faculty SET Faculty_Name = '" + facultyName + "' WHERE Faculty_ID = " + id;
//        try {
//            Database db = new Database();
//            new Logs().updateLog(username, "UPDATED fId : " + facultyName);
//            db.executeSQLQuery(q, "INSERTED");
////            showTable(showAll);
//        } catch (Exception ex) {
//
//        }
    }

    public void delete(String id, String username) {
        System.out.println(delete("faculty", id, "Faculty_ID"));
    }

    public FacultyDb() {
    }
}
