/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chatroom;
/**
 *
 * @author hwang
 * Modified by: Chad Manning
 * Course: CMPS 3390
 */
import java.net.Socket;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import javax.swing.*;


class CAgent implements Runnable{
    ObjectInputStream in;
    JTextArea  taMsgBrd;
    String userName;
    ChatroomClient cl;
    ArrayList<String> currUsers;
    JCheckBox[] userBoxes;

    public CAgent ( ObjectInputStream in, ChatroomClient client, JCheckBox[] cbk) {
        this.in = in;
        cl = client;
        taMsgBrd = cl.taMsgBrd;
        userName = cl.userName;
        currUsers = new ArrayList();
        userBoxes = cbk;
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
                    currUsers.remove(userName);
                    for (int i=0; i < 10; ++i)
                        if (i > currUsers.size()-1) {
                            userBoxes[i].setText("");
                            userBoxes[i].setSelected(false);
                            userBoxes[i].setEnabled(false);
                        }
                        else {
                            userBoxes[i].setText(currUsers.get(i));
                            userBoxes[i].setSelected(true);
                            userBoxes[i].setEnabled(true);
                        }
                    break;
                  case "WRITE" :
                    taMsgBrd.append ( msg.split(" ", 2)[1] + "\n");
                    break;
              }
            }
        }
        catch (Exception e) { e.printStackTrace(); }
    }
}

public class ChatroomClient extends JFrame {
    String userName = "John Doe";
    JCheckBox[] cbk = new JCheckBox[10];
    ArrayList<String> sendList = new ArrayList();
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
        jCheckBox1 = new javax.swing.JCheckBox();
        jCheckBox2 = new javax.swing.JCheckBox();
        jCheckBox3 = new javax.swing.JCheckBox();
        jCheckBox4 = new javax.swing.JCheckBox();
        jCheckBox5 = new javax.swing.JCheckBox();
        jCheckBox6 = new javax.swing.JCheckBox();
        jCheckBox7 = new javax.swing.JCheckBox();
        jCheckBox8 = new javax.swing.JCheckBox();
        jCheckBox9 = new javax.swing.JCheckBox();
        jCheckBox10 = new javax.swing.JCheckBox();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Chatroom Client");
        setResizable(false);

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

        jCheckBox1.setEnabled(false);

        jCheckBox2.setEnabled(false);

        jCheckBox3.setEnabled(false);

        jCheckBox4.setEnabled(false);

        jCheckBox5.setEnabled(false);

        jCheckBox6.setEnabled(false);

        jCheckBox7.setEnabled(false);

        jCheckBox8.setEnabled(false);

        jCheckBox9.setEnabled(false);

        jCheckBox10.setEnabled(false);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jCheckBox1)
                    .addComponent(jCheckBox2)
                    .addComponent(jCheckBox3)
                    .addComponent(jCheckBox4)
                    .addComponent(jCheckBox5)
                    .addComponent(jCheckBox6)
                    .addComponent(jCheckBox7)
                    .addComponent(jCheckBox8)
                    .addComponent(jCheckBox9)
                    .addComponent(jCheckBox10))
                .addGap(0, 53, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jCheckBox1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jCheckBox2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jCheckBox3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jCheckBox4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jCheckBox5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jCheckBox6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jCheckBox7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jCheckBox8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jCheckBox9)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jCheckBox10)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jCheckBox1.getAccessibleContext().setAccessibleName("");
        jCheckBox2.getAccessibleContext().setAccessibleName("");
        jCheckBox3.getAccessibleContext().setAccessibleName("");
        jCheckBox4.getAccessibleContext().setAccessibleName("");
        jCheckBox5.getAccessibleContext().setAccessibleName("");
        jCheckBox6.getAccessibleContext().setAccessibleName("");

        jLabel1.setText("Users");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(tfName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(tfMessage))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 445, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 324, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 303, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(tfName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(tfMessage, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel1.getAccessibleContext().setAccessibleName("");
        jPanel1.getAccessibleContext().setAccessibleDescription("");
        jPanel1.getAccessibleContext().setAccessibleParent(jPanel1);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tfNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tfNameActionPerformed
        // TODO add your handling code here:
        tfName.setEnabled (false);
        tfMessage.setEnabled(true);
        userName = tfName.getText();
        cbk[0] = jCheckBox1;
        cbk[1] = jCheckBox2;
        cbk[2] = jCheckBox3;
        cbk[3] = jCheckBox4;
        cbk[4] = jCheckBox5;
        cbk[5] = jCheckBox6;
        cbk[6] = jCheckBox7;
        cbk[7] = jCheckBox8;
        cbk[8] = jCheckBox9;
        cbk[9] = jCheckBox10;

        try {
            System.out.println(host);
            sk = new Socket( host, port); 
            in = new ObjectInputStream(sk.getInputStream());
            out = new ObjectOutputStream( sk.getOutputStream() );
            new Thread ( new CAgent( in, this, cbk)).start();
            out.writeObject(userName);
        } catch ( Exception e ) { e.printStackTrace();}
    }//GEN-LAST:event_tfNameActionPerformed

    private void tfMessageActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tfMessageActionPerformed
        // TODO add your handling code here
        try {
            //If user is selected add them to send list
            sendList.add(userName);
            for (JCheckBox bx : cbk)
                if (bx.isEnabled() && bx.isSelected())
                    sendList.add(bx.getText());
            
            String msg =  tfName.getText() + ": "+ tfMessage.getText(); 
            out.writeObject(sendList.clone());
            out.writeObject(msg);
            tfMessage.setText("");
            sendList.clear();
        } catch (Exception e ) { }
    }//GEN-LAST:event_tfMessageActionPerformed

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
    private javax.swing.JCheckBox jCheckBox1;
    private javax.swing.JCheckBox jCheckBox10;
    private javax.swing.JCheckBox jCheckBox2;
    private javax.swing.JCheckBox jCheckBox3;
    private javax.swing.JCheckBox jCheckBox4;
    private javax.swing.JCheckBox jCheckBox5;
    private javax.swing.JCheckBox jCheckBox6;
    private javax.swing.JCheckBox jCheckBox7;
    private javax.swing.JCheckBox jCheckBox8;
    private javax.swing.JCheckBox jCheckBox9;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    public javax.swing.JTextArea taMsgBrd;
    private javax.swing.JTextField tfMessage;
    private javax.swing.JTextField tfName;
    // End of variables declaration//GEN-END:variables
}
