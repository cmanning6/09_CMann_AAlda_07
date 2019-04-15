/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chatroom;
/**
 *
 * @author hwang
 */
import java.io.IOException;
import java.net.Socket;
import java.net.ServerSocket;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import javax.swing.*;


class CAgent implements Runnable{
    ObjectInputStream in;
    JTextArea  taMsgBrd;
    JFrame client;
    JPanel panel;
    ArrayList<String> currUsers;
    ArrayList<JCheckBox> userBoxes;

    public CAgent ( ObjectInputStream in, JTextArea brd, JPanel list) {
        this.in = in;
        taMsgBrd = brd;
        panel = list;
        currUsers = new ArrayList();
        userBoxes = new ArrayList();
    }
    public void run ()  {
        Object obj = null;
        String msg = null;

        try {
            while ( true ) {
              msg = (String) in.readObject();
              switch(msg.split(" ")[0]) {
                  case "JOIN" :
                  case "DISCONNECT":
                    currUsers = (ArrayList<String>) in.readObject();
                    userBoxes.add(new JCheckBox());
                    userBoxes.get(0).setText(currUsers.get(0));
                    panel.add(userBoxes.get(0));
                    break;
                  case "WRITE" :
                    taMsgBrd.append ( msg.split(" ", 2)[1] + "\n");
                    break;
              }
              taMsgBrd.append(currUsers.toString() + "\n");
            }
        }
        catch (Exception e) { e.printStackTrace(); }
    }
}

public class ChatroomClient extends JFrame {
    String userName = "John Doe";
    /**
     * Creates new form ChatroomClient
     */
    public ChatroomClient() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        taMsgBrd = new javax.swing.JTextArea();
        tfName = new javax.swing.JTextField();
        tfMessage = new javax.swing.JTextField();
        jPanel1 = new javax.swing.JPanel();
        jCheckBox2 = new javax.swing.JCheckBox();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Chatroom Client");

        taMsgBrd.setEditable(false);
        taMsgBrd.setColumns(20);
        taMsgBrd.setRows(5);
        jScrollPane1.setViewportView(taMsgBrd);

        tfName.setText("John Doe");
        tfName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tfNameActionPerformed(evt);
            }
        });

        tfMessage.setText("Hello, everyone!");
        tfMessage.setEnabled(false);
        tfMessage.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tfMessageActionPerformed(evt);
            }
        });

        jPanel1.addContainerListener(new java.awt.event.ContainerAdapter() {
            public void componentAdded(java.awt.event.ContainerEvent evt) {
                jPanel1ComponentAdded(evt);
            }
        });

        jCheckBox2.setText("jCheckBox2");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 130, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jCheckBox2)
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addGap(135, 135, 135)
                    .addComponent(jCheckBox2)
                    .addContainerGap(165, Short.MAX_VALUE)))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(tfName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(tfMessage))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 434, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 324, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tfName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tfMessage, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(15, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tfNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tfNameActionPerformed
        // TODO add your handling code here:
        tfName.setEnabled (false);
        tfMessage.setEnabled(true);
        userName = tfName.getText();
        try {
            System.out.println(host);
            sk = new Socket( host, port); 
            in = new ObjectInputStream(sk.getInputStream());
            out = new ObjectOutputStream( sk.getOutputStream() );
            new Thread ( new CAgent( in, taMsgBrd, jPanel1)).start();
            out.writeObject(userName);
        } catch ( Exception e ) { e.printStackTrace();}
    }//GEN-LAST:event_tfNameActionPerformed

    private void tfMessageActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tfMessageActionPerformed
        // TODO add your handling code here
        try {
            out.writeObject( tfName.getText() + ": "+ tfMessage.getText() );
            tfMessage.setText("");
        } catch (Exception e ) { }
    }//GEN-LAST:event_tfMessageActionPerformed

    private void jPanel1ComponentAdded(java.awt.event.ContainerEvent evt) {//GEN-FIRST:event_jPanel1ComponentAdded
        // TODO add your handling code here:
        revalidate();
        repaint();
    }//GEN-LAST:event_jPanel1ComponentAdded

    /**
     * @param args the command line arguments
     */
    public static void main(String args[] ) {
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
            java.util.logging.Logger.getLogger(ChatroomClient.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ChatroomClient.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ChatroomClient.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ChatroomClient.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new ChatroomClient().setVisible(true);
            }
        }
        );
    }
    Socket sk = null;
    ObjectOutputStream out = null;
    ObjectInputStream  in = null;
    // String host = "delphi.cs.csub.edu";
    String host = "localhost";
    int    port = 9999;
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JCheckBox jCheckBox2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea taMsgBrd;
    private javax.swing.JTextField tfMessage;
    private javax.swing.JTextField tfName;
    // End of variables declaration//GEN-END:variables
}