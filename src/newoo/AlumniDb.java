/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package newoo;

import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Properties;

/**
 *
 * @author Pee
 */
public class AlumniDb extends Database {

    String year, firstname, lastname, faculty, branch, his, id;
    String sf = "sF";
    String showAll = "SELECT alumni.Alumni_year Year ,alumni.Alumni_Fname firstname, Alumni_Lname lastname, faculty.name faculty ,branch.Branch_Name branch,alumni.Career_History,alumni.Alumni_Id\n"
            + "FROM alumni,faculty,branch\n"
            + "WHERE alumni.Faculty_ID = faculty.Faculty_ID and alumni.Branch_ID = branch.Branch_ID and alumni.status = 1\n"
            + "ORDER BY alumni.Alumni_year DESC";

    public AlumniDb() {
    }

    public AlumniDb(String year, String firstname, String lastname, String faculty, String branch, String his, String id) {
        this.year = year;
        this.firstname = firstname;
        this.lastname = lastname;
        this.faculty = faculty;
        this.branch = branch;
        this.his = his;
        this.id = id;
    }

    public String getYear() {
        return year;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public String getFaculty() {
        return faculty;
    }

    public String getBranch() {
        return branch;
    }

    public String getHis() {
        return his;
    }

    public String getId() {
        return id;
    }

    public ArrayList<AlumniDb> setList(String q) {

        ResultSet rs;
        ArrayList<AlumniDb> al = new ArrayList<>();
        try {
            Database db = new Database();
            rs = db.querys(q);
            AlumniDb alumniDisplay;
            while (rs.next()) {
                alumniDisplay = new AlumniDb(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7));
                al.add(alumniDisplay);
            }
            return al;
        } catch (SQLException ex) {
            System.out.println(ex.toString());
            return null;
        }

    }

    public String[] setJcom1() {
        String q = "SELECT * FROM faculty WHERE status = 1";
        String value[];
        try {
            Database db = new Database();
            ResultSet rs = db.querys(q);
            int c = 0;
            while (rs.next()) {
                c++;
            }
            rs.beforeFirst();
            value = new String[c + 1];
            value[0] = "showAll";
            int i = 1;
            while (rs.next()) {
                value[i] = rs.getString(2);
                i++;
            }
            return value;
        } catch (SQLException ex) {
            System.out.println(ex.toString());
            return null;
        }
    }

    public String[] setJcombo3(String idF) {
//        getIdf();
//        jComboBox3.removeAllItems();
        String q = "SELECT Branch_ID , Branch_Name FROM branch WHERE Faculty_ID = " + idF + " AND status = 1";
        System.out.println(q);
        String values3[];
        try {
            Database db = new Database();
            ResultSet rs = db.querys(q);
            int c = 0;
            while (rs.next()) {
                c++;
            }
            rs.beforeFirst();
            values3 = new String[c];

            int i = 0;
            while (rs.next()) {
                values3[i] = rs.getString(2);
                i++;
            }
//            for (int j = 0; j < values3.length; j++) {
//                jComboBox3.addItem(values3[j]);
//            }
            return values3;
        } catch (SQLException ex) {

            System.out.println(ex.toString());
            return null;
        }
    }

    public String getIdf(String facultyName) {
        String q = "SELECT Faculty_ID FROM faculty WHERE status = 1 and name = '" + facultyName + "'";
        String idF;
        try {

            Database db = new Database();
            ResultSet rs = db.querys(q);
            rs.next();
            idF = rs.getString(1);
            return idF;
//            jComboBox2.setSelectedIndex(jComboBox1.getSelectedIndex());
        } catch (SQLException ex) {
            System.out.println(ex.toString());
            return null;
        }
    }

    public String getIdB(String brachName, String idF) {

        String q = "SELECT Branch_ID FROM branch WHERE status = 1 and Branch_Name = '" + brachName + "' and Faculty_ID = " + idF;
//jComboBox3.getItemAt(jComboBox3.getSelectedIndex())
        try {

            Database db = new Database();
            ResultSet rs = db.querys(q);
            rs.next();
            String idB = rs.getString(1);
            System.out.println(idB);
//            jButton2.setVisible(true);
//            jComboBox2.setSelectedIndex(jComboBox1.getSelectedIndex());
            return idB;
        } catch (Exception ex) {
            System.out.println(ex.toString());
//            jButton2.setVisible(false);
            return null;
        }
    }

    public String searchF(String facultyName, String idF) {

        String q = "SELECT Faculty_ID FROM faculty WHERE status = 1 and name LIKE '" + facultyName + "'";

        System.out.println(q);

        try {

            Database db = new Database();
            ResultSet rs = db.querys(q);
            rs.next();
            idF = rs.getString(1);
            String showbyF = "SELECT alumni.Alumni_year Year ,alumni.Alumni_Fname firstname, Alumni_Lname lastname, faculty.name faculty ,branch.Branch_Name branch,alumni.Career_History,alumni.Alumni_Id\n"
                    + "FROM alumni,faculty,branch\n"
                    + "WHERE alumni.Faculty_ID = " + idF + " and branch.Faculty_ID = " + idF + " and  faculty.Faculty_ID = " + idF + " and alumni.Branch_ID = branch.Branch_ID and alumni.status = 1\n"
                    + "ORDER BY alumni.Alumni_year DESC";

            return showbyF;
        } catch (Exception ex) {

            System.out.println(ex.toString());
            return showAll;
        }

    }

    public void add(Properties value) {
        Properties pp = value;
        System.out.println(insert("alumni", pp));
    }

    public void edit(Properties value) {
        Properties pp = value;
        System.out.println( update("alumni", pp, "Alumni_Id"));
        System.out.println(testupdate("alumni", value, "Alumni_Id"));
//        
    }

    public void delete(String idd, String username) {
        System.out.println(delete("alumni", idd,"Alumni_Id"));
//        String q = "UPDATE alumni SET status = 0 WHERE Alumni_Id = " + idd + "";
//        try {
//            Database db = new Database();
//            db.executeSQLQuery(q, "DELETED");
//            new Logs().updateLog(username, "DELETED id : " + idd);
////            showTable("SELECT alumni.Alumni_year Year ,alumni.Alumni_Fname firstname, Alumni_Lname lastname, faculty.Faculty_Name faculty ,branch.Branch_Name branch,alumni.Career_History,alumni.Alumni_Id\n"
////                    + "FROM alumni,faculty,branch\n"
////                    + "WHERE alumni.Faculty_ID = faculty.Faculty_ID and alumni.Branch_ID = branch.Branch_ID and alumni.status = 1\n"
////                    + "ORDER BY alumni.Alumni_year DESC");
//        } catch (Exception ex) {
//            System.out.println(ex.toString());
//        }
    }

    public void report() {
//        showTable("SELECT alumni.Alumni_year Year ,alumni.Alumni_Fname firstname, Alumni_Lname lastname, faculty.Faculty_Name faculty ,branch.Branch_Name branch,alumni.Career_History,alumni.Alumni_Id\n"
//                + "FROM alumni,faculty,branch\n"
//                + "WHERE alumni.Faculty_ID = faculty.Faculty_ID and alumni.Branch_ID = branch.Branch_ID and alumni.status = 1\n"
//                + "ORDER BY alumni.Alumni_year DESC");
        ArrayList<AlumniDb> list = setList(showAll);
        String path = "C:\\Users\\Pee\\Desktop\\ReportAlumniFile.txt";
        File file = new File(path);

        FileWriter writer;
        try {

            writer = new FileWriter(file, false);  //True = Append to file, false = Overwrite
//            writer.write("\n");
            writer.write("YEAR || FIRSTNAME || LASTNAME || FACULTY || BRANCH || HISTORY CARRER \n");
            for (int i = 0; i < list.size(); i++) {

                writer.write(list.get(i).getYear());
                writer.write(" || ");
                writer.write(list.get(i).getFirstname());
                writer.write(" || ");
                writer.write(list.get(i).getLastname());
                writer.write(" || ");
                writer.write(list.get(i).getFaculty());
                writer.write(" || ");
                writer.write(list.get(i).getBranch());
                writer.write(" || ");
                if (list.get(i).getHis().equals("")) {
                    writer.write("ว่าง");
                } else {
                    writer.write(list.get(i).getHis());
                }
//                writer.write("||");
                writer.write("\n");
            }

//			writer.write("Welcome thaicreate.com 2\r\n");
            writer.close();

            System.out.println("Write success!");

        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public String seachY(String year) {
        String q = "SELECT alumni.Alumni_year Year ,alumni.Alumni_Fname firstname, Alumni_Lname lastname, faculty.name faculty ,branch.Branch_Name branch,alumni.Career_History,alumni.Alumni_Id\n"
                + "FROM alumni,faculty,branch\n"
                + "WHERE alumni.Faculty_ID = faculty.Faculty_ID and alumni.Branch_ID = branch.Branch_ID and alumni.Alumni_year LIKE '%" + year + "%' and alumni.status = 1\n"
                + "ORDER BY alumni.Alumni_year DESC";
//        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
//            new Logs().updateLog(username, "Search : " + jTextField3.getText().trim());
//            jComboBox1.setSelectedIndex(0);
//
//            return q;
//
//        }
        return q;
    }

    public String seachName(String name,String lname) {
//        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
        String q = "SELECT alumni.Alumni_year Year ,alumni.Alumni_Fname firstname, Alumni_Lname lastname, faculty.name faculty ,branch.Branch_Name branch,alumni.Career_History,alumni.Alumni_Id\n"
                + "FROM alumni,faculty,branch\n"
                + "WHERE alumni.Faculty_ID = faculty.Faculty_ID and alumni.Branch_ID = branch.Branch_ID and (alumni.Alumni_Fname LIKE '%" + name + "%'OR alumni.Alumni_Lname LIKE '%" + lname + "%') and alumni.status = 1 \n"
                + "ORDER BY alumni.Alumni_year DESC";
//            new Logs().updateLog(username, "Search : " + jTextField2.getText().trim());
        return q;

//        }
    }
}
