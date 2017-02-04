/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package radiusclients;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import Socket.SocketServer;
/**
 *
 * @author ZORO
 */
public class Main {
    public static void main(String [] args) throws IOException{
         ServerSocket serverSocket = null;
        Socket socket = null;

        serverSocket = new ServerSocket(1814);
        while (true) {
            try {
                socket = serverSocket.accept();
            } catch (IOException e) {
                System.out.println("I/O error: " + e);
            }
            // new threa for a client
            new SocketServer(socket).start();
        }
    }
}
