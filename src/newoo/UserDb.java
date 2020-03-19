/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package newoo;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.security.AuthProvider;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Properties;
import javax.swing.JOptionPane;

/**
 *
 * @author Pee
 */
public class UserDb extends Database {

    private String username;
    private int usertype;
    private String id, fname, lname, address, type, tel, usern, password;
    String showAll = "SELECT * FROM `user` WHERE Status = 1";
    private String userid;
    ArrayList<UserDb> list;
//    String value[] = {"User","Admin","Member","Emoloyee"};

    UserDb() {
        getList("SELECT * FROM `user` WHERE Status = 1");
    }

    public String getId() {
        return id;
    }

    public String getFname() {
        return fname;
    }

    public String getLname() {
        return lname;
    }

    public String getAddress() {
        return address;
    }

    public String gettype() {
        return type;
    }

    public String getUsern() {
        return usern;
    }

    public String getTel() {
        return tel;
    }

    public String getPassword() {
        return password;
    }

    public UserDb(String id, String fname, String lname, String address, String type, String tel, String usern, String password) {
        this.id = id;
        this.fname = fname;
        this.lname = lname;
        this.address = address;
        this.type = type;
        this.tel = tel;
        this.usern = usern;
        this.password = password;
    }

    public void delete(String userId) {
        System.out.println(delete("user", userId, "Id"));
//        String q = "UPDATE user SET status = 0 WHERE Id = " + userId;
//        try {
//            Database db = new Database();
//            db.executeSQLQuery(q, "DELETE");
//            getList("SELECT * FROM `user` WHERE Status = 1");
//        } catch (Exception ex) {
//            System.out.println(ex.toString());
//        }
    }

    public ArrayList getList(String q) {

        ArrayList<UserDb> arrayList = new ArrayList<>();

        ResultSet rs;

        try {
            Database db = new Database();
            rs = db.querys(q);
            UserDb facultyDisplay;
            while (rs.next()) {
                facultyDisplay = new UserDb(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8));
                arrayList.add(facultyDisplay);
            }
            return arrayList;
        } catch (SQLException ex) {
            System.out.println(ex.toString());
            return null;
        }

    }

    public Properties getdetail(String username) {
        Properties pp = new Properties();

        String q = "SELECT user.Fname,user.Password,user.Lname,user.Address FROM `user` WHERE user.Username = '" + username + "' AND Status = 1";

        try {

            Database db = new Database();
            ResultSet rs = db.querys(q);
            rs.next();
//            fNname = rs.getString(1);
//            password = rs.getString(2);
//            lName = rs.getString(3);
//            address = rs.getString(4);
//            System.out.println(fNname + " " + lName + " " + password);
            pp.put("fname", rs.getString("Fname"));
            pp.put("password", rs.getString("Password"));
            pp.put("lname", rs.getString("Lname"));
            pp.put("address", rs.getString("Address"));
//            pp.put("type", rs.getString(5));
//            pp.put("tel", rs.getString(6));
//            pp.put("username", rs.getString(7));
//            pp.put("password", rs.getString(8));

        } catch (SQLException ex) {
            System.out.println(ex.toString());
        }
        return pp;
    }

    public Properties checkLogin(String username, String password) {
        Properties pp = new Properties();
        String q = "SELECT Username , Password ,Type ,Id FROM `user` WHERE Status = 1 and Username = '"
                + username + "' and Password = '" + password + "'";
        try {
            Database db = new Database();
            ResultSet rs = db.querys(q);
            rs.next();
            pp.put("username", rs.getString(1));
            pp.put("password", rs.getString(2));
            pp.put("type", rs.getString(3));
            pp.put("id", rs.getString(4));
            return pp;
        } catch (SQLException ex) {
            return null;
        }
    }

    void report() {
        String q = "SELECT  user.Username , logs.Log_Date , logs.Log_Detail,logs.Log_Id FROM user,logs \n"
                + "WHERE user.Id = logs.User_Id\n"
                + "ORDER BY `logs`.`Log_Date` DESC";
        try {
            Database db = new Database();
            ResultSet rs = db.querys(q);
            String path = "C:\\Users\\Pee\\Desktop\\ReportLogsFile.txt";
            File file = new File(path);
            try {
                FileWriter writer = new FileWriter(file, false);
                int i = 1;
                writer.write("USERNAME || DATE || DETAIL \n");
                while (rs.next()) {

                    writer.write("" + i++);
                    writer.write(" ");
                    writer.write(rs.getString(1));
                    writer.write(" || ");
                    writer.write(rs.getString(2));
                    writer.write(" || ");
                    writer.write(rs.getString(3));
                    writer.write("\n");

                }
                writer.close();

                System.out.println("Write success!");
            } catch (IOException | SQLException ex) {
                System.out.println(ex.toString());
            }

        } catch (Exception ex) {
            System.out.println(ex.toString());
        }
    }

    void add(String fname, String lname, String address, String indexType, String tel, String username, String password) {

        Properties pp = new Properties();
        pp.put("Fname", fname);
        pp.put("Lname", lname);
        pp.put("Address", address);
        pp.put("Type", indexType);
        pp.put("Tel", tel);
        pp.put("Username", username);
        pp.put("Password", password);
        pp.put("Status", "1");
        System.out.println(insert("user", pp));

    }

    boolean checkUsername(String username) {

        try {
            Database db = new Database();
            String q = "SELECT Username FROM user WHERE status = 1 AND Username = '" + username + "'";
            ResultSet rs = db.querys(q);
            rs.next();

            if (rs == null) {
                System.out.println(rs.getString("Username"));
                return true;

            } else {
                return false;
            }
        } catch (Exception ex) {
            System.out.println(ex.toString());
            return false;
        }

    }

    void edit(String fname, String lname, String address, String indexType, String tel, String username, String password, String userid) {

        Properties pp = new Properties();
        pp.put("Fname", fname);
        pp.put("Lname", lname);
        pp.put("Address", address);
        pp.put("Type", indexType);
        pp.put("Tel", tel);
        pp.put("Username", username);
        pp.put("Password", password);
        pp.put("Status", "1");
        pp.put("Id", userid);
        System.out.println(testupdate("user", pp, "Id"));

    }

    Properties getProfile(String fName, String lName) {
        Properties pp = new Properties();
        String q1 = "SELECT alumni.Alumni_year Year ,alumni.Alumni_Fname firstname, Alumni_Lname lastname, faculty.name faculty ,branch.Branch_Name branch,alumni.Career_History,alumni.Alumni_Id\n"
                + "FROM alumni,faculty,branch\n"
                + "WHERE alumni.Faculty_ID = faculty.Faculty_ID and alumni.Branch_ID = branch.Branch_ID and (alumni.Alumni_Fname = '" + fName + "' AND alumni.Alumni_Lname = '" + lName + "') and alumni.status = 1 \n"
                + "ORDER BY alumni.Alumni_year DESC";
        String q2 = "";
        try {
            Database db = new Database();
            ResultSet rs = db.querys(q1);
            rs.next();
            if (rs != null) {
                pp.put("fname", rs.getString(2));
                pp.put("lname", rs.getString(3));
//            pp.put("address", rs.getString(4));
                pp.put("f", rs.getString(4));
                pp.put("b", rs.getString(5));
                pp.put("his", rs.getString(6));
                return pp;
            } else {
                return null;
            }

//            jtfname.setText(rs.getString(2));
//            jtflname.setText(rs.getString(3));
//            jtaaddress.setText(address);
//            jtffalcuty.setText(rs.getString(4));
//            jtfbranch.setText(rs.getString(5));
//            jtfhis.setText(rs.getString(6));
//            jp.setText(password);
        } catch (SQLException ex) {
            System.out.println(ex.toString());
            return null;
        }

    }

    void editHis(String his, String fName, String lName) {
        try {
            String q = "UPDATE `alumni` SET Career_History = '" + his + "' WHERE Alumni_Fname = '" + fName + "' AND  Alumni_Lname = '"
                    + lName + "' AND status = 1";
            Database db = new Database();
            db.executeSQLQuery(q, "UPDATE HIS");
//                if (db.executeSQLQuery(q, "UPDATE HIS") != 0) {
//                    
//                    new Logs().updateLog(username, "UPDATE my job to" + jtfhis.getText());
//                }

        } catch (Exception ex) {
            System.out.println(ex.toString());
        }
    }
}
