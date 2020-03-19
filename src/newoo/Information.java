package newoo;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

public class Information extends javax.swing.JFrame {

   
    String username;
    int usertype;
    String id;

    public Information(String username, int usertype) {
        initComponents();
        ShowTable();
        this.username = username;
        this.usertype = usertype;
        jLusername.setText("Username :" + username);
        if ( usertype != 4) {
            jPanel1.setVisible(false);
        }
        try {
           
            id = new InformationDB().getinformationID(this.username);
            System.out.println("xxxx ID " + id);
        } catch (Exception e) {

        }
    }

    public Information() {
        initComponents();
        ShowTable();

    }

    public void ShowTable() {
        ArrayList<InformationDB> list = new InformationDB().getProductList();
        DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
        Object[] row = new Object[5];
        for (int i = 0; i < list.size(); i++) {
            row[0] = list.get(i).getInformation_id();
            row[1] = list.get(i).getInformation_title();
            row[2] = list.get(i).getInformation_date();

            jTable1.setAutoResizeMode(jTable1.AUTO_RESIZE_OFF);
            jTable1.getColumnModel().getColumn(0).setPreferredWidth(400);
            jTable1.getColumnModel().getColumn(1).setPreferredWidth(200);

            model.addRow(row);
        }
    }

    void refresh() {
        DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
        model.setRowCount(0);
        ShowTable();

    }

       public void add() {
        try {
              Database db = new Database();
            if (jTxtopic.equals("") || jTxdetail.equals("")) {
                     JOptionPane.showMessageDialog(this,
                        "กรอกข้อมูลให้ครบ.",
                        "",
                        JOptionPane.ERROR_MESSAGE);
                     
            } else {
                new InformationDB().add(id,jTxtopic.getText(),jTxdetail.getText(),username);
                refresh();
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this,
                    "กรอกข้อมูลให้ครบ.",
                    "",
                    JOptionPane.ERROR_MESSAGE);
            System.out.println("Error");
        }
    }

    private void edit() {
        new InformationDB().edit(jTxtopic.getText(),jTxdetail.getText(),jTxinformationID.getText(),username);
        refresh();
    }

    private void delete() {
         new InformationDB().delete(jTxinformationID.getText(),username);   
        refresh();
    }

    private void logOut() {
        Home h = new Home();
        new Logs().updateLog(username, "Logout");
        h.setVisible(true);
        h.setResizable(false);
        this.dispose();
    }

    private void back() {
        Menu h2 = new Menu(username, usertype);
        new Logs().updateLog(username, "GO to Home2");
        h2.setVisible(true);
        h2.setResizable(false);
        this.dispose();
    }

    private void gotoTopic() {

        if (jTxinformationID.getText().equals("")) {
            JOptionPane.showMessageDialog(this,
                    "เลือกข่าวที่ต้องการดู.",
                    "",
                    JOptionPane.ERROR_MESSAGE);

        } else {

            System.out.println("  เลือกข่าว  " + jTxinformationID.getText());
            Comment IC = new Comment(jTxinformationID.getText(), username, usertype);
            new Logs().updateLog(username, "Go to Information");
            IC.setVisible(true);
            IC.setResizable(false);
            this.dispose();

        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jBaddinformation = new javax.swing.JButton();
        jBdelete = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jTxtopic = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTxdetail = new javax.swing.JTextArea();
        jBeditinformation = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        jTxinformationID = new javax.swing.JTextField();
        jLusername = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "", "หัวข้อข่าวสาร", "วันที่ลงข่าวสาร"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                true, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
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
            jTable1.getColumnModel().getColumn(0).setMinWidth(0);
            jTable1.getColumnModel().getColumn(0).setPreferredWidth(0);
            jTable1.getColumnModel().getColumn(0).setMaxWidth(0);
            jTable1.getColumnModel().getColumn(1).setMinWidth(450);
            jTable1.getColumnModel().getColumn(1).setPreferredWidth(450);
            jTable1.getColumnModel().getColumn(1).setMaxWidth(450);
            jTable1.getColumnModel().getColumn(2).setMinWidth(300);
            jTable1.getColumnModel().getColumn(2).setPreferredWidth(300);
            jTable1.getColumnModel().getColumn(2).setMaxWidth(300);
        }

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("ข่าวสาร");

        jButton1.setText("หน้าแรก");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel3.setText("เนื้อหาข่าว");

        jBaddinformation.setText("Add");
        jBaddinformation.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBaddinformationActionPerformed(evt);
            }
        });

        jBdelete.setText("delete");
        jBdelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBdeleteActionPerformed(evt);
            }
        });

        jLabel2.setText("หัวข้อข่าว");

        jTxdetail.setColumns(20);
        jTxdetail.setRows(5);
        jScrollPane2.setViewportView(jTxdetail);

        jBeditinformation.setText("edit");
        jBeditinformation.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBeditinformationActionPerformed(evt);
            }
        });

        jLabel4.setText("ID");

        jTxinformationID.setEditable(false);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(jLabel4)
                        .addComponent(jLabel2)))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(12, 12, 12)
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 389, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jTxtopic, javax.swing.GroupLayout.PREFERRED_SIZE, 387, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(28, 28, 28)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jBdelete)
                            .addComponent(jBaddinformation)
                            .addComponent(jBeditinformation)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jTxinformationID, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(67, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jTxinformationID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(17, 17, 17)
                        .addComponent(jBaddinformation)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jBeditinformation)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jBdelete))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(jTxtopic, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap())
        );

        jLusername.setText("Username : Thitiwas");

        jButton2.setText("GO");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jButton1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLusername)
                .addGap(31, 31, 31))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jButton2)))
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGap(91, 91, 91)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jLusername))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 391, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton2)
                .addGap(24, 24, 24)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked

        try {
            int i = jTable1.getSelectedRow();
            TableModel model = jTable1.getModel();
            jTxinformationID.setText(model.getValueAt(i, 0).toString());
            jTxtopic.setText(model.getValueAt(i, 1).toString());

            jTxdetail.setText(new InformationDB().getinformationDetail(jTxinformationID.getText()));

        } catch (Exception e) {
            System.out.println(" " + e.toString());
        }
    }//GEN-LAST:event_jTable1MouseClicked

    private void jBaddinformationActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBaddinformationActionPerformed
        //Add
        add();
    }//GEN-LAST:event_jBaddinformationActionPerformed

    private void jBeditinformationActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBeditinformationActionPerformed
        edit();

    }//GEN-LAST:event_jBeditinformationActionPerformed

    private void jBdeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBdeleteActionPerformed
        delete();
    }//GEN-LAST:event_jBdeleteActionPerformed

    private void jTable1MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseEntered

    }//GEN-LAST:event_jTable1MouseEntered

    private void jTable1MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MousePressed
        // TODO add your handling code here:

    }//GEN-LAST:event_jTable1MousePressed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        gotoTopic();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        back();
    }//GEN-LAST:event_jButton1ActionPerformed

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
            java.util.logging.Logger.getLogger(Information.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Information.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Information.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Information.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Information().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jBaddinformation;
    private javax.swing.JButton jBdelete;
    private javax.swing.JButton jBeditinformation;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLusername;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextArea jTxdetail;
    private javax.swing.JTextField jTxinformationID;
    private javax.swing.JTextField jTxtopic;
    // End of variables declaration//GEN-END:variables

}
