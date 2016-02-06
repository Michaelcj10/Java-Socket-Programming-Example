import java.net.*;
import java.io.*;

class ServerSocketDemo {
    private ServerSocket ss;
    private Socket s;
    private BufferedReader local;
    private BufferedReader remote;
    private PrintStream ps;

    private ServerSocketDemo() {
        try {
            ss = new ServerSocket(2002);
            s = ss.accept();

            System.out.println("Server started");
            local = new BufferedReader(new InputStreamReader(System.in));
            remote = new BufferedReader(new InputStreamReader(s.getInputStream()));
            ps = new PrintStream(s.getOutputStream());

            while (true) {
                System.out.println("Type a message to send to the client");
                String localdata = local.readLine();
                ps.println(localdata); //sends the data to output stream
                String remotedata = remote.readLine();
                System.out.println("Client:= " + remotedata);
                if (remotedata.equals("bye")) {
                    System.out.println("Client Disconnected");
                    break;
                }
            }
        } catch (Exception e) {
            System.out.println("Error in Server" + e);
        }
    }

    public static void main(String arg[]) {
        new ServerSocketDemo();
    }
}