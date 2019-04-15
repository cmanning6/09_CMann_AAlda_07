/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chatroom;

import java.io.IOException;
import java.net.ServerSocket;
import java.io.ObjectOutputStream;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
/**
 *
 * @author hwang
 * Modified by: Chad Manning
 * Course: CMPS 3390
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
    
    ArrayList<String> users = null;
    Map<String, ObjectOutputStream> uMap;

    
    public SharedOuts() {
        users = new ArrayList();
        uMap = new HashMap();
    }
    public void add( ObjectOutputStream out, String uN ) { 
        users.add(uN);
        uMap.put(uN, out);
    }
    public void remove (String uN) { 
        users.remove(uN);
        uMap.remove(uN);
    }
    public int size() { return uMap.size(); }

    /**
     * Sends message to every user regardless of sendList
     * @param obj : obj to be broadcasted to all users 
     */
    public void broadcast( Object obj) {
	for ( String u : users ) {
            try {
                System.out.println(obj);
                uMap.get(u).writeObject(obj);
            } catch (IOException ex) {
		ex.printStackTrace();
            }
        }
        System.out.print("Current Users: " + users.toString() + "\n");
    }
    
    /**
     * Sends message to only users on the sendList
     * @param obj : message to be sent
     * @param sendList : Users who will receive message
     */
    public void selectiveBroadcast( Object obj, ArrayList sendList) {
	for ( String usr : users ) {
            try {
                if (sendList.contains(usr)) {
                    System.out.println(obj);
                    uMap.get(usr).writeObject(obj);
                }
            } catch (IOException ex) {
		ex.printStackTrace();
            }
        }
        System.out.print("Current Users: " + users.toString() + "\n");
    }
    
    /**
     * Sends updated user list to all clients after a connection or disconnection
     * @param evnt : either a JOIN or DISCONNECT string
     */
    public void updateUsers(String evnt) {
        //get users into a plain string with only commas
	for ( String u : users ) {
            try {
                System.out.println(evnt);
                uMap.get(u).writeObject(evnt);
                uMap.get(u).writeObject(users.clone());
            } catch (IOException ex) {
		ex.printStackTrace();
            }
        }
        System.out.print("Current Users: " + users.toString() + "\n");
    }
}

class SAgent implements Runnable {

    Socket sk = null;
    ObjectOutputStream out = null;
    ObjectInputStream   in = null;
    SharedOuts sout = null;
    String userName = "John Doe";
    ArrayList<String> sendList = null;

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
        sout.updateUsers("JOIN " + userName);
        sout.broadcast("WRITE " + userName + " has connected!");
        while (true ) {

                sendList = (ArrayList<String>) in.readObject();
                sout.selectiveBroadcast( "WRITE " + in.readObject(), sendList);
        }
    } 
    catch ( Exception e) {}
    finally {
        sout.remove(userName);
        sout.updateUsers( "DISCONNECT " + userName);
        sout.broadcast("WRITE " + userName + " has disconnected.");
    }
  }
}
