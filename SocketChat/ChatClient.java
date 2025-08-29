import java.io.*;
import java.net.*;

public class ChatClient {
    public static void main(String[] args) throws IOException {
        Socket socket = new Socket("localhost", 5000);
        DataInputStream dis = new DataInputStream(socket.getInputStream());
        DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String msgin = "", msgout = "";
        while (!msgout.equalsIgnoreCase("exit")) {
            System.out.print("Client: ");
            msgout = br.readLine();
            dos.writeUTF(msgout);
            dos.flush();
            msgin = dis.readUTF();
            System.out.println("Server: " + msgin);
        }
        dis.close();
        dos.close();
        socket.close();
    }
}
