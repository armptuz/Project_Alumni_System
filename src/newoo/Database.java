package newoo;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.SQLException;
import java.util.Enumeration;
import java.util.Properties;
import java.util.Vector;

public class Database implements Myinterface {

    private ResultSet rs;
    private Connection cc;
    private Statement st;
    Vector vector;
    Properties properties;
    private String sql;

    private java.sql.Connection getConnection() {
        java.sql.Connection con;
        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/oopproject?zeroDateTimeBehavior=convertToNull", "root", "");
            return con;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public ResultSet querys(String sql) {
        java.sql.Connection con = getConnection();
        java.sql.Statement st;
        try {
            st = con.createStatement();
            return st.executeQuery(sql);
            //return st.executeUpdate(sql);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public int executeSQLQuery(String query, String message) {
        java.sql.Connection con = getConnection();
        java.sql.Statement st;
        try {
            st = con.createStatement();

            return st.executeUpdate(query);
        } catch (SQLException ex) {
            System.out.println(ex.toString());
        }
        return 0;
    }

    public String testInsert(String tableName, Properties value) {
        String c = "";
        String v = "";

        return "INSERT INTO " + tableName + " ( " + c + " ) VALUES ( " + v + ")";
    }

    @Override
    public String insert(String tableName, Properties value) {
        String c = "";
        String v = "";
        Vector<String> vc = new Vector();
        Vector<String> vv = new Vector();
        Enumeration ec = value.propertyNames();
        while (ec.hasMoreElements()) {
            String temp = ec.nextElement() + "";
            vc.add(temp);
            vv.add(value.getProperty(temp));
        }
        int temp = vc.size();
        int last = temp - 1;
        for (int i = 0; i < temp; i++) {
            if (i == last) {
                c = c + "" + vc.get(i) + " ";
                v = v + "'" + vv.get(i) + "' ";
            } else {
                c = c + "" + vc.get(i) + ", ";
                v = v + "'" + vv.get(i) + "', ";//have ,
            }
        }
        executeSQLQuery("INSERT INTO " + tableName + " ( " + c + " ) VALUES ( " + v + ")", "");
        return "INSERT INTO " + tableName + " ( " + c + " ) VALUES ( " + v + ")";
    }

    @Override
    public String delete(String tableName, String ID, String idTable) {
        String q = "UPDATE " + tableName + " SET status = 0 WHERE " + idTable + " = " + ID;
        executeSQLQuery(q, "DELETE");
        return q;
    }

    @Override
    public String testupdate(String tableName, Properties value, String culumId) {//UPDATE tablename set WHERE culumId = id
        String v = "";
        String id = value.getProperty(culumId);
        Enumeration k = value.propertyNames();//key
        Vector kk = new Vector();
        while (k.hasMoreElements()) {
            kk.add(k.nextElement());
        }
        int temp = kk.size();
        int last = temp - 1;
        for (int i = 0; i < temp; i++) {
            if (((String) kk.get(i)).equals(culumId)) {
            } else if (i == last) {
                v = v + kk.get(i) + " = '" + value.getProperty((String) kk.get(i)) + "' ";
            } else {
                v = v + kk.get(i) + " = '" + value.getProperty((String) kk.get(i)) + "' ,";
            }
        }
        String q = "UPDATE " + tableName + " SET " + v + " WHERE " + culumId + " = " + id;
        executeSQLQuery(q, "");
        return q;
    }

    @Override
    public String update(String tableName, Properties value, String ID) {
        String s1 = ""; //colom name
        String s2 = ""; //Id
        vector = new Vector();
        properties = value;
        Enumeration enumeration = properties.propertyNames();// เก็บชื่อ colum
        while (enumeration.hasMoreElements()) {
            String temp = (String) enumeration.nextElement();// เก็บชื่อ colum ทั้งหมด
            vector.addElement(temp); //vector เก็บ ชื่อ colum
        }
        Enumeration en = vector.elements();//เอาชื่อคอลลั่มเก็บ enum
        String temp = (String) en.nextElement();// เก็บชื่อ colum ตัวแรก
        if (ID.equals(temp)) {// ถ้าถึง id ค่อยทำ
            s2 += " WHERE " + ID + " = '" + properties.getProperty(ID) + "'"; // s2 = where Id = id
        } else {
            s1 += " " + temp + " = '" + properties.getProperty(temp) + "'"; //s1 = set colum = 'value'
            while (en.hasMoreElements()) {
                String temp2 = (String) en.nextElement();
                if (ID.equals(temp2)) { // ถ้าไอดีคอลลั่ม เท่ากับ ไอดีคอลลัม
                    s2 += " WHERE " + ID + " = " + properties.getProperty(ID) + "";
                } else {
                    s1 += "," + temp2 + " = '" + properties.getProperty(temp2) + "'";//s1 = set colum = 'value'
                }
            }
        }
        sql = "UPDATE " + tableName + " SET " + s1 + " " + s2;
        executeSQLQuery(sql, "Inserted");
        return sql;
    }
}
