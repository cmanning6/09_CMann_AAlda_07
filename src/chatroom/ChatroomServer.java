/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chatroom;

import java.io.IOException;
import java.net.Socket;
import java.net.ServerSocket;
import java.util.Vector;
import java.io.ObjectOutputStream;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author hwang
 */
public class ChatroomServer {
    static Socket       sk  = null;
    static ServerSocket ssk = null;
    static int         port = 9999;
    
    static int  numConnection = 0;

    static SharedOuts sOuts = new SharedOuts();

    
    public static void main( String args[] ) {
        try {
            
            ssk = new ServerSocket(port);
            while ( true ) {
                sk = ssk.accept();
                System.out.printf("%d connections.\n", ++numConnection);
                new Thread ( new SAgent( sk, sOuts )).start();    
            }
        }
        catch ( Exception e ) {e.printStackTrace();}
    }
    
}

class  SharedOuts {
    
    ArrayList<ObjectOutputStream> vOut =  null;
    ArrayList<String> users = null;

    
    public SharedOuts() {
	vOut = new ArrayList();
        users = new ArrayList();
    }
    public void add( ObjectOutputStream out, String uN ) { 
        vOut.add( out );
        users.add(uN);
    }
    public void remove (int index) { 
        vOut.remove(vOut.get(index));
        users.remove(users.get(index));
    }
    public int size() { return vOut.size(); }

    public void broadcast( Object obj ) {
	Iterator<ObjectOutputStream>  itr = vOut.iterator();
        Iterator<String> uItr = users.iterator();

	while ( itr.hasNext() ) {
            try {
                System.out.println("Writing: " + obj);
                itr.next().writeObject(obj);
            } catch (IOException ex) {
		ex.printStackTrace();
            }
        }
        System.out.print("Current Users: " + users.toString() + "\n");
    }
    
    public void updateUsers(String evnt) {
        //get users into a plain string with only commas
        broadcast(evnt);
        broadcast(users.clone());
    }
}

class SAgent implements Runnable {

    Socket sk = null;
    ObjectOutputStream out = null;
    ObjectInputStream   in = null;
    SharedOuts sout = null;
    String userName = "John Doe";

    public SAgent ( Socket sk, SharedOuts sout ) {
	this.sk = sk; this.sout = sout;
	try {
	    out = new ObjectOutputStream ( sk.getOutputStream()) ;
	    in= new ObjectInputStream ( sk.getInputStream() );
	} catch ( Exception e ) { e.printStackTrace(); }
    }
  
  public void run() {
    try {
        userName = (String) in.readObject();
        sout.add( out, userName);
        sout.updateUsers("JOIN");
        sout.broadcast("WRITE " + userName + " has connected!");
        while (true ) {

                Object obj = in.readObject() ;
                sout.broadcast( "WRITE " + obj + " ( outs = " + sout.size() + ")" );
        }
    } 
    catch ( Exception e) {}
    finally {
        sout.remove(sout.users.indexOf(userName));
        sout.updateUsers( "DISCONNECT");
        sout.broadcast("WRITE " + userName + " has disconnected.");
    }
  }
}
