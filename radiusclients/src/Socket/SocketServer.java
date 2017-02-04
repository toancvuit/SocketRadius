package Socket;

import java.net.*;
import java.io.*;
public class SocketServer extends Thread
{
   private Socket socket;
   private String authenMessage;
   private Boolean messagChange;
   public SocketServer(Socket clientSocket) throws IOException
   {
      socket = clientSocket;
      //socket.setSoTimeout(5000);
      System.out.print("Client Connect!" + socket.getInetAddress() + " port:"+ socket.getPort());
      this.messagChange = false;
      this.authenMessage = null;
   }

   public void run()
   {
      InputStream inp = null;
        BufferedReader brinp = null;
        DataOutputStream out = null;
        try {
            inp = socket.getInputStream();
            brinp = new BufferedReader(new InputStreamReader(inp));
            out = new DataOutputStream(socket.getOutputStream());
        } catch (IOException e) {
            return;
        }
        String key;
        while (true) {
            try {
                System.out.print("Waiting to get input from client");
                key = brinp.readLine();
                switch(key.toUpperCase()) {
                    case "QUIT":    
                        socket.close();
                        break;
                    case "SENDDATA":
                        String data;
                        out.writeBytes("READY" + '\n');
                        out.flush();
                        System.out.println("Wait Client send data");
                        data = brinp.readLine();
                        if ( data.indexOf("UserName:")!=0 && data.indexOf("Password:")!= 0 ) {
                            this.authenMessage = data;
                            this.messagChange = true;
                        } else {
                            System.err.println("UserName or Password uncorrect");
                            out.writeBytes("ERROR CODE");
                            out.flush();
                        }
                        
                        
                }
                System.out.print("Terminal Client");
            } catch (IOException e) {
                e.printStackTrace();
                return;
            }
        }
   }
    public String authenMessage() {
            return this.authenMessage;
   }
    
    public Boolean isMessageChange() {
        return this.messagChange;
    }
    public void setMessageChange(Boolean values) {
        this.messagChange = values;
    }
//    static final int PORT = 1978;
//   public static void main(String [] args) throws IOException
//   {
//       ServerSocket serverSocket = null;
//        Socket socket = null;
//
//        try {
//            serverSocket = new ServerSocket(PORT);
//        } catch (IOException e) {
//        }
//        while (true) {
//            try {
//                socket = serverSocket.accept();
//            } catch (IOException e) {
//                System.out.println("I/O error: " + e);
//            }
//            // new threa for a client
//            new SocketServer(socket).start();
//        }
//   }
}