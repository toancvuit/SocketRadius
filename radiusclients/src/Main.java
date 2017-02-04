/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import Socket.SocketServer;
import radiusclients.Radiusclients;
import AddUser.AddUser;
import java.sql.SQLException;
/**
 *
 * @author ZORO
 */
public class Main {
    public static void main(String [] args) throws IOException, SQLException{
         ServerSocket serverSocket = null;
        Socket socket = null;
        AddUser x =new AddUser();
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
