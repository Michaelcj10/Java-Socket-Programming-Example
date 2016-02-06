import java.net.*;
import java.io.*;

class ClientSocketDemo {
    private Socket s;
    private BufferedReader local;
    private BufferedReader remote;
    private PrintStream ps;

    private ClientSocketDemo() {
        try {
            s = new Socket("localhost", 2002);
            System.out.println("Client started");

            local = new BufferedReader(new InputStreamReader(System.in));
            remote = new BufferedReader(new InputStreamReader(s.getInputStream()));
            ps = new PrintStream(s.getOutputStream());

            while (true) {

                String remotedata = remote.readLine();
                System.out.println("Server= " + remotedata);
                System.out.println("Type a message to send to the server");
                String localdata = local.readLine();
                ps.println(localdata);

                if (localdata.equals("bye")) {
                    s.close();
                    break;
                }
            }
        } catch (Exception e) {
            System.out.println("Error in Server" + e); //prints out if any exception is thrown
        }
    }

    public static void main(String arg[]) {
        new ClientSocketDemo();
    }
}