import java.io.*;
import java.net.*;

public class ChatServer {
    public static void main(String[] args) throws IOException {
        ServerSocket server = new ServerSocket(5001);
        System.out.println("Server started. Waiting for client...");
        Socket socket = server.accept();
        System.out.println("Client connected.");

        DataInputStream dis = new DataInputStream(socket.getInputStream());
        DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String msgin = "", msgout = "";
        while (!msgin.equalsIgnoreCase("exit")) {
            msgin = dis.readUTF();
            System.out.println("Client: " + msgin);
            System.out.print("Server: ");
            msgout = br.readLine();
            dos.writeUTF(msgout);
            dos.flush();
        }
        dis.close();
        dos.close();
        socket.close();
        server.close();
    }
}
