/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EstacionDeServicio;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author SrDeLorean
 */
public class EstacionDeServicio {
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) throws IOException {
        int port = 10012;
        ServerSocket ss = new ServerSocket(port);
        String line;
        Socket sc = ss.accept();
        DataInputStream in = new DataInputStream(sc.getInputStream());
        DataOutputStream out = new DataOutputStream(sc.getOutputStream());
        while(true){
            line= in.readUTF();
            System.out.println(line);
        }
    }
}
