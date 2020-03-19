/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package newoo;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.Properties;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Pee
 */
public class Alumni extends javax.swing.JFrame {

//    private String id, year, firstname, lastname, faculty, branch, his;
    private String idd;
    private String username;
    private int usertype;
    String idF, idB;
    ArrayList<AlumniDb> list;
    private String fNname, password, lName, address;
//    ArrayList<Alumni> list;
//    private ArrayList<Alumni> arrayList;
    String values3[];
    String value[];

    public Alumni(String username, int usertype) {
        this.username = username;
        this.usertype = usertype;
        initComponents();
        System.out.println(username);
        if (usertype != 4) {
            panel1.setVisible(false);
        }
        if (usertype == 1) {
            jLAllinformation.setVisible(false);
            jLabel3.setVisible(false);
        }
        jLabel7.setText("Username : " + username);
        showTable(new AlumniDb().showAll);

//        this.setResizable(false);
        setJcom1();
    }

    public Alumni() {
        initComponents();
        showTable(new AlumniDb().showAll);
        setJcom1();

    }

    private void showTable(String q) {

        list = new AlumniDb().setList(q);
        DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
        model.setRowCount(0);
        Object[] col = new Object[6];
        String t = "";
        for (int i = 0; i < list.size(); i++) {
            col[0] = list.get(i).getYear();
            col[1] = list.get(i).getFirstname();
            col[2] = list.get(i).getLastname();
            col[3] = list.get(i).getFaculty();
            col[4] = list.get(i).getBranch();
            col[5] = list.get(i).getHis();
            model.addRow(col);

        }
    }

    void setJcombo3() {
        getIdf();
        jComboBox3.removeAllItems();

        values3 = new AlumniDb().setJcombo3(idF);
        for (int j = 0; j < values3.length; j++) {
            jComboBox3.addItem(values3[j]);
        }

    }

    void setJcom1() {

        jComboBox1.removeAllItems();
        jComboBox2.removeAllItems();
        value = new AlumniDb().setJcom1();
        System.out.println(Arrays.toString(value));
        try {

            value[0] = "showAll";
            for (int j = 0; j < value.length; j++) {
                jComboBox1.addItem(value[j]);
            }
            for (int j = 1; j < value.length; j++) {
                jComboBox2.addItem(value[j]);

            }
        } catch (Exception ex) {
            System.out.println(ex.toString());
        }
    }

    private void getIdB() {
        getIdf();

        idB = new AlumniDb().getIdB(jComboBox3.getItemAt(jComboBox3.getSelectedIndex()), idF);

        jButton2.setVisible(true);
        System.out.println(idB);
        if (idB == null) {
            jButton2.setVisible(false);
        }

    }

    private void getIdf() {

        idF = new AlumniDb().getIdf(jComboBox2.getItemAt(jComboBox2.getSelectedIndex()));
        System.out.println(idF);
    }

    private void searchF() {

        if (jComboBox1.getSelectedIndex() == 0) {
            showTable(new AlumniDb().showAll);
        } else {
            try {
                System.out.println(new AlumniDb().searchF(jComboBox1.getItemAt(jComboBox1.getSelectedIndex()), idF));
                showTable(new AlumniDb().searchF(jComboBox1.getItemAt(jComboBox1.getSelectedIndex()), idF));
//                new Logs().updateLog(username, "Search : " + jComboBox1.getItemAt(jComboBox1.getSelectedIndex()));
                jComboBox2.setSelectedIndex(jComboBox1.getSelectedIndex());
            } catch (Exception ex) {
                System.out.println(ex.toString());
            }

        }

    }

    private void gotoAllInformation() {
        Information ai = new Information(username, usertype);
        new Logs().updateLog(username, "Go to allinformation");
        ai.setVisible(true);
        ai.setResizable(false);
        this.dispose();
    }

    private void gotoEditFalucty() {
        Faculty facultyDisplay = new Faculty(username, usertype);
        new Logs().updateLog(username, "Go to UpdateF&B");
        facultyDisplay.setVisible(true);
        facultyDisplay.setResizable(false);
        this.dispose();
    }

    private void add() {
        Properties pp = new Properties();
        pp.put("Alumni_Fname", jtffname.getText().trim());
        pp.put("Alumni_Lname", jtflname.getText().trim());
        pp.put("Faculty_ID", idF);
        pp.put("Branch_ID", idB);
        pp.put("Alumni_year", jtfyear.getText().trim());
        pp.put("Career_History", jtfHis.getText().trim());
        pp.put("status", "1");
        new AlumniDb().add(pp);
        showTable(new AlumniDb().showAll);
    }

    private void edit() {
        getIdf();
        Properties pp = new Properties();
        pp.put("Alumni_Fname", jtffname.getText().trim());
        pp.put("Alumni_Lname", jtflname.getText().trim());
        pp.put("Faculty_ID", idF);
        pp.put("Branch_ID", idB);
        pp.put("Alumni_year", jtfyear.getText().trim());
        pp.put("Career_History", jtfHis.getText().trim());
        pp.put("status", "1");
        pp.put("Alumni_Id", idd);
        new AlumniDb().edit(pp);
        showTable(new AlumniDb().showAll);
    }

    private void delete() {
        new AlumniDb().delete(idd, username);
        showTable(new AlumniDb().showAll);

    }

    private void gotoEditProfile(String username) {
//        String q = "SELECT user.Fname,user.Password,user.Lname,user.Address FROM `user` WHERE user.Username = '" + username + "' AND Status = 1";
        try {
//            new Menu(username, usertype).gotoEditProfile(username);
//            Database db = new Database();
//            ResultSet rs = db.querys(q);
//            rs.next();
//            fNname = rs.getString(1);
//            password = rs.getString(2);
//            lName = rs.getString(3);
//            address = rs.getString(4);
//            System.out.println(fNname + " " + lName + " " + password);



            Properties pp = new UserDb().getdetail(username);
            Profile pd = new Profile(username, usertype, pp.getProperty("fname"), pp.getProperty("lname"), pp.getProperty("password"), pp.getProperty("address"));
            System.out.println(pp.getProperty("fname") + pp.getProperty("lname") + pp.getProperty("password") + pp.getProperty("address"));

            pd.setVisible(true);
            pd.setResizable(false);
            this.dispose();
        } catch (Exception ex) {
            System.out.println(ex.toString());
        }
    }

    private void report() {
        new AlumniDb().report();
    }

    private void seachY(KeyEvent evt) {

//        new Logs().updateLog(username, "Search : " + jTextField3.getText().trim());
//        jComboBox1.setSelectedIndex(0);
//        String q = "SELECT alumni.Alumni_year Year ,alumni.Alumni_Fname firstname, Alumni_Lname lastname, faculty.Faculty_Name faculty ,branch.Branch_Name branch,alumni.Career_History,alumni.Alumni_Id\n"
//                + "FROM alumni,faculty,branch\n"
//                + "WHERE alumni.Faculty_ID = faculty.Faculty_ID and alumni.Branch_ID = branch.Branch_ID and alumni.Alumni_year LIKE '%" + jTextField3.getText().trim() + "%' and alumni.status = 1\n"
//                + "ORDER BY alumni.Alumni_year DESC";
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
//            new Logs().updateLog(username, "Search : " + jTextField3.getText().trim());
//            jComboBox1.setSelectedIndex(0);

            showTable(new AlumniDb().seachY(jTextField3.getText().trim()));

        }
    }

    private void seachName(KeyEvent evt) {
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {

            showTable(new AlumniDb().seachName(jTextField2.getText().trim(), jTextField2.getText().trim()));

        }
    }

    private void getIdinList(int i) {
        idd = list.get(i).getId();
        System.out.println(idd);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLAllinformation = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox<>();
        jLabel6 = new javax.swing.JLabel();
        jTextField3 = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jLabel7 = new javax.swing.JLabel();
        panel1 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        jtffname = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jtflname = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        jtfyear = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jComboBox2 = new javax.swing.JComboBox<>();
        jComboBox3 = new javax.swing.JComboBox<>();
        jtfHis = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        jButton5 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jTextField2 = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("ALUMNI");
        jLabel1.setToolTipText("");

        jLAllinformation.setForeground(new java.awt.Color(0, 153, 255));
        jLAllinformation.setText("ดูข่าวสาร");
        jLAllinformation.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLAllinformationMouseClicked(evt);
            }
        });

        jLabel3.setForeground(new java.awt.Color(0, 153, 255));
        jLabel3.setText("แกไขข้อมูลส่วนตัว");
        jLabel3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel3MouseClicked(evt);
            }
        });

        jButton1.setText("LOGOUT");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel5.setText("คณะ");

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "showall", "คณะศิลปศาสตร์", "คณะวิทยาศาสตร์และเทคโนโลยี", "คณะครุศาสตร์อุตสาหกรรม", "คณะวิศวกรรมศาสตร์", "คณะบริหารธุรกิจ", "คณะเทคโนโลยีคหกรรมศาสตร์", "คณะอุตสาหกรรมสิ่งทอ", "วิทยาลัยนานาชาติ" }));
        jComboBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox1ActionPerformed(evt);
            }
        });

        jLabel6.setText("ปี");

        jTextField3.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextField3KeyPressed(evt);
            }
        });

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Year", "Firstname", "Lastname", "Faculty", "Branch", "history carrer"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jTable1MouseEntered(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jTable1MousePressed(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);
        if (jTable1.getColumnModel().getColumnCount() > 0) {
            jTable1.getColumnModel().getColumn(0).setMinWidth(3);
            jTable1.getColumnModel().getColumn(0).setPreferredWidth(1);
            jTable1.getColumnModel().getColumn(1).setPreferredWidth(50);
            jTable1.getColumnModel().getColumn(2).setPreferredWidth(50);
            jTable1.getColumnModel().getColumn(3).setPreferredWidth(150);
            jTable1.getColumnModel().getColumn(4).setPreferredWidth(150);
            jTable1.getColumnModel().getColumn(5).setPreferredWidth(50);
        }

        jLabel7.setText(" ");
        jLabel7.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel7MouseClicked(evt);
            }
        });

        jLabel8.setText("Year");

        jLabel9.setText("Firstname");

        jLabel10.setText("Branch");

        jLabel11.setText("Lastname");

        jtfyear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtfyearActionPerformed(evt);
            }
        });

        jLabel12.setText("Faculty");

        jButton2.setText("INSERT");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setText("UPDATE");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton4.setText("DELETE");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jComboBox2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "showall", "คณะศิลปศาสตร์", "คณะวิทยาศาสตร์และเทคโนโลยี", "คณะครุศาสตร์อุตสาหกรรม", "คณะวิศวกรรมศาสตร์", "คณะบริหารธุรกิจ", "คณะเทคโนโลยีคหกรรมศาสตร์", "คณะอุตสาหกรรมสิ่งทอ", "วิทยาลัยนานาชาติ" }));
        jComboBox2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox2ActionPerformed(evt);
            }
        });

        jComboBox3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox3ActionPerformed(evt);
            }
        });

        jLabel13.setText("History carrer");

        jButton5.setText("UPDATE F&B");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jButton6.setText("REPORT");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panel1Layout = new javax.swing.GroupLayout(panel1);
        panel1.setLayout(panel1Layout);
        panel1Layout.setHorizontalGroup(
            panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel10)
                    .addComponent(jLabel8)
                    .addComponent(jLabel9)
                    .addComponent(jLabel12))
                .addGroup(panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panel1Layout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addGroup(panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jtfyear)
                                .addComponent(jComboBox3, 0, 248, Short.MAX_VALUE))
                            .addComponent(jtffname, javax.swing.GroupLayout.PREFERRED_SIZE, 248, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel11)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panel1Layout.createSequentialGroup()
                                .addGroup(panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel13)
                                    .addComponent(jButton2))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(panel1Layout.createSequentialGroup()
                                        .addComponent(jButton3)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jButton4)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jButton5))
                                    .addGroup(panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(jtflname)
                                        .addComponent(jtfHis, javax.swing.GroupLayout.PREFERRED_SIZE, 224, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                    .addGroup(panel1Layout.createSequentialGroup()
                        .addGap(59, 59, 59)
                        .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, 248, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(159, 159, 159)
                        .addComponent(jButton6)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panel1Layout.setVerticalGroup(
            panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel1Layout.createSequentialGroup()
                .addGroup(panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panel1Layout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addComponent(jLabel12))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jComboBox2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton6, javax.swing.GroupLayout.Alignment.TRAILING))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panel1Layout.createSequentialGroup()
                        .addGroup(panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jtffname, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel9))
                        .addGroup(panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panel1Layout.createSequentialGroup()
                                .addGap(13, 13, 13)
                                .addComponent(jLabel10))
                            .addGroup(panel1Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jComboBox3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel8)
                            .addComponent(jtfyear, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panel1Layout.createSequentialGroup()
                        .addGroup(panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jtflname, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panel1Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 6, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel11)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jtfHis, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel13))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton2)
                            .addComponent(jButton3)
                            .addComponent(jButton4)
                            .addComponent(jButton5))))
                .addContainerGap(34, Short.MAX_VALUE))
        );

        jTextField2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField2ActionPerformed(evt);
            }
        });
        jTextField2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextField2KeyPressed(evt);
            }
        });

        jLabel4.setText("ชื่อ หรือ นามสกุล");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLAllinformation)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel7))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(jLabel3))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 225, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(38, 38, 38)
                                .addComponent(jLabel5)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel6)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jButton1)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jScrollPane1)
                    .addComponent(panel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLAllinformation)
                    .addComponent(jLabel7))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel3)
                .addGap(15, 15, 15)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addGap(17, 17, 17)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 160, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(panel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButton1)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jLabel3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel3MouseClicked
        gotoEditProfile(username);

    }//GEN-LAST:event_jLabel3MouseClicked

    private void jLabel7MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel7MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jLabel7MouseClicked

    private void jComboBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox1ActionPerformed
        searchF();
        setJcombo3();

        jTextField3.setText("");

    }//GEN-LAST:event_jComboBox1ActionPerformed

    private void jTextField3KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField3KeyPressed
        seachY(evt);

    }//GEN-LAST:event_jTextField3KeyPressed

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        int i = jTable1.getSelectedRow();
        if (evt.getClickCount() == 2) {
            JOptionPane jp = new JOptionPane();
            jp.showMessageDialog(this,
                    jTable1.getValueAt(i, jTable1.getSelectedColumn()).toString(),
                    jTable1.getValueAt(i, 1).toString() + " " + jTable1.getValueAt(i, 2).toString(), JOptionPane.PLAIN_MESSAGE);

        }
        jtfyear.setText(jTable1.getValueAt(i, 0).toString());
        jtffname.setText(jTable1.getValueAt(i, 1).toString());
        jtflname.setText(jTable1.getValueAt(i, 2).toString());
        getIdinList(i);
        String fa = jTable1.getValueAt(i, 3).toString();
        System.out.println(fa);
        for (int j = 0; j < value.length; j++) {
            if (value[j].equals(fa)) {
                System.out.println(j);

                jComboBox2.setSelectedIndex(j - 1);

                jButton2.setVisible(true);
                break;
            }

        }

        String br = jTable1.getValueAt(i, 4).toString();
        System.out.println(br);
        for (int j = 0; j < values3.length; j++) {
            if (values3[j].equals(br)) {
                jComboBox3.setSelectedIndex(j);

                break;
            }

        }

        jtfHis.setText(jTable1.getValueAt(i, 5).toString());
    }//GEN-LAST:event_jTable1MouseClicked

    private void jtfyearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtfyearActionPerformed

    }//GEN-LAST:event_jtfyearActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        add();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jComboBox3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox3ActionPerformed
        getIdB();


    }//GEN-LAST:event_jComboBox3ActionPerformed

    private void jComboBox2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox2ActionPerformed
        setJcombo3();

    }//GEN-LAST:event_jComboBox2ActionPerformed

    private void jTextField2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField2KeyPressed
        seachName(evt);
    }//GEN-LAST:event_jTextField2KeyPressed

    private void jTextField2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField2ActionPerformed


    }//GEN-LAST:event_jTextField2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        edit();
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        delete();

    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        Home h = new Home();
        new Logs().updateLog(username, "Logout");
        h.setVisible(true);
        h.setResizable(false);
        this.dispose();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jTable1MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseEntered
        System.out.println("ss");
    }//GEN-LAST:event_jTable1MouseEntered

    private void jTable1MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MousePressed

    }//GEN-LAST:event_jTable1MousePressed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        gotoEditFalucty();
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jLAllinformationMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLAllinformationMouseClicked
        gotoAllInformation();
    }//GEN-LAST:event_jLAllinformationMouseClicked

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        report();
    }//GEN-LAST:event_jButton6ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Alumni.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Alumni.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Alumni.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Alumni.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Alumni().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JComboBox<String> jComboBox2;
    private javax.swing.JComboBox<String> jComboBox3;
    private javax.swing.JLabel jLAllinformation;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jtfHis;
    private javax.swing.JTextField jtffname;
    private javax.swing.JTextField jtflname;
    private javax.swing.JTextField jtfyear;
    private javax.swing.JPanel panel1;
    // End of variables declaration//GEN-END:variables
}
