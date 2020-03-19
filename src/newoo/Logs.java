/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package newoo;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

public class Logs extends Database{

    public void updateLog(String username, String detail) {
        String userId = "";
        try {
            Database db = new Database();
            ResultSet rs = db.querys("SELECT Id FROM user WHERE Username = '" + username + "'");
            rs.next();
            userId = "" + (rs.getInt(1));
        } catch (Exception ex) {
            System.out.println(ex.toString());
        }
        Properties pp = new Properties();
        pp.put("User_Id",userId);
        pp.put("log_Detail",detail);
        System.out.println(insert("logs", pp));
//        String q = "INSERT INTO logs (User_Id,Log_date,log_Detail) VALUES (" + userId + ",now(),'" + detail + "')";
//        try {
//            Database db = new Database();
//            db.executeSQLQuery(q, "LOGIN");
//        } catch (Exception ex) {
//            System.out.println(ex.toString());
//        }
    }

    public void report() {
        String q = "SELECT user.Username , logs.Log_Date , logs.Log_Detail FROM user,logs ORDER BY `logs`.`Log_Date` DESC";
        try {
            Database db = new Database();
            ResultSet rs = db.querys(q);
            while (rs.next()) {
                try {
                    String path = "C:\\Users\\Pee\\Desktop\\ReportLogsFile.txt";
                    File file = new File(path);

                    FileWriter writer;
                    writer = new FileWriter(file, false);
                    writer.write("USERNAME || DATE || DETAIL");
                    writer.write(rs.getString(1));
                    writer.write(" || ");
                    writer.write(rs.getString(2));
                    writer.write(" || ");
                    writer.write(rs.getString(3));
                    writer.write("\n");

                } catch (IOException | SQLException ex) {
                    System.out.println(ex.toString());
                }
            }
        } catch (SQLException ex) {
            System.out.println(ex.toString());
        }
    }
}
