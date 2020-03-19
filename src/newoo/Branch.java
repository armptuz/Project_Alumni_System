/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package newoo;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Pee
 */
public class Branch extends javax.swing.JFrame {

    private String faclutyId;
//    private String branchId, branchName;
//    private String showAll;
    private String id;
    private String username;
    private int usertype;
    ArrayList<BrachDb> list;
//    public Branch(String branchId, String branchName) {
//        this.branchId = branchId;
//        this.branchName = branchName;
//    }
//
//    public String getBranchId() {
//        return branchId;
//    }
//
//    public String getBranchName() {
//        return branchName;
//    }

    public Branch(String faclutyId, String username, int usertype) {
        this.faclutyId = faclutyId;
        this.username = username;
        this.usertype = usertype;
//        String showAll = "SELECT * FROM `branch` WHERE Faculty_ID = " + faclutyId + " AND status = 1";
        initComponents();
        showTable(new BrachDb(faclutyId).showAll);
        jTable1.setAutoResizeMode(JTable.AUTO_RESIZE_LAST_COLUMN);
        jTable1.getColumnModel().getColumn(1).setPreferredWidth(754);

    }

    public Branch() {
        initComponents();
        jTable1.setAutoResizeMode(JTable.AUTO_RESIZE_LAST_COLUMN);
        jTable1.getColumnModel().getColumn(1).setPreferredWidth(754);
    }

//    private ArrayList<BrachDb> arrayList;
//    private void cusList(String q) {
//
//        arrayList = new ArrayList<>();
//
//        ResultSet rs;
//
//        try {
//            Database db = new Database();
//            rs = db.querys(q);
//            Branch facultyDisplay;
//            while (rs.next()) {
//                facultyDisplay = new Branch(rs.getString(1), rs.getString(3));
//                arrayList.add(facultyDisplay);
//            }
//
//        } catch (SQLException ex) {
//            System.out.println(ex.toString());
//        }
//
//    }
    private void showTable(String q) {
//        cusList(q);
        list = new BrachDb(faclutyId).getList(new BrachDb(faclutyId).showAll);
        DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
        model.setRowCount(0);
        Object[] col = new Object[2];
        String t = "";
        for (int i = 0; i < list.size(); i++) {
            col[0] = list.get(i).getBranchId();
            col[1] = list.get(i).getBranchName();

            model.addRow(col);

        }
    }

    private void add() {
        new BrachDb(faclutyId).add(jtf1.getText().trim(), username);
        showTable(new BrachDb(faclutyId).showAll);
//        String q = "INSERT INTO branch (`Faculty_ID`, `Branch_Name`,status) VALUES (" + faclutyId + ",'" + jtf1.getText().trim() + "',1)";
//        try {
//            Database db = new Database();
//            db.executeSQLQuery(q, "INSERTED");
//            new Logs().updateLog(username, "INSERTED : " + jtf1.getText().trim() + ",\nfId : " + faclutyId);
//            showTable("SELECT * FROM `branch` WHERE Faculty_ID = " + faclutyId + " AND status = 1");
//        } catch (Exception ex) {
//            System.out.println(ex.toString());
//        }
    }

    private void edit() {
        new BrachDb(faclutyId).edit(jtf1.getText().trim(), username, id);
        showTable(new BrachDb(faclutyId).showAll);
//        String q = "UPDATE branch SET Branch_Name = '" + jtf1.getText().trim() + "' WHERE Branch_ID = " + id;
//        try {
//            Database db = new Database();
//            db.executeSQLQuery(q, "INSERTED");
//            new Logs().updateLog(username, "UPDATED idB : " + jtf1.getText().trim());
//            showTable("SELECT * FROM `branch` WHERE Faculty_ID = " + faclutyId + " AND status = 1");
//        } catch (Exception ex) {
//            System.out.println(ex.toString());
//        }
    }

    private void delete() {
        new BrachDb(faclutyId).delete(id, username);
        showTable(new BrachDb(faclutyId).showAll);

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jtf1 = new javax.swing.JTextField();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Id", "Branch"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);

        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Branch");

        jtf1.setText(" ");

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

        jButton1.setText("BACK");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane1))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(115, 115, 115)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jButton4)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(jtf1, javax.swing.GroupLayout.PREFERRED_SIZE, 292, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(67, 67, 67)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jButton2)
                                            .addComponent(jButton3)))))
                            .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jButton1)))
                        .addGap(0, 218, Short.MAX_VALUE)))
                .addContainerGap())
        );

        layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jButton2, jButton3, jButton4});

        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 374, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 59, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton2)
                    .addComponent(jLabel1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton3)
                    .addComponent(jtf1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton4)
                .addGap(1, 1, 1)
                .addComponent(jButton1)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed

        add();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        edit();
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        delete();
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        Faculty facultyDisplay = new Faculty(username, usertype);
        new Logs().updateLog(username, "go back to FacultyDisplay");
        facultyDisplay.setVisible(true);
        facultyDisplay.setResizable(false);
        this.dispose();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        int i = jTable1.getSelectedRow();
        jtf1.setText(jTable1.getValueAt(i, 1).toString());
        id = jTable1.getValueAt(i, 0).toString();
    }//GEN-LAST:event_jTable1MouseClicked

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
            java.util.logging.Logger.getLogger(Branch.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Branch.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Branch.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Branch.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Branch().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField jtf1;
    // End of variables declaration//GEN-END:variables
}
