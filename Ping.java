import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.ArrayList;

public class Ping {
    public static void main(String[] args) throws IOException {
        //default ip address
        String host = "";
        ArrayList<Integer> portList = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.print("What is the ip? ");
        //gets the ip from the buffered reader
        String hostFromReader = reader.readLine().trim();
        if(!hostFromReader.isEmpty()) {
            host = hostFromReader;
        }
        System.out.print("What is the timeout? ");
        int timeOut = Integer.parseInt(reader.readLine());
        System.out.print("From what port? ");
        int portStart = Integer.parseInt(reader.readLine());
        System.out.print("To what port? ");
        //the program will check ports t to n where n is the value of portLimit and t is the value portStart
        int portLimit = Integer.parseInt(reader.readLine());
        //iterates over numbers from 1 to n and pings the ports with those numbers
        for(int i = portStart; i <= portLimit; i++) {
            System.out.print("Pinging port " + i + ": ");
            //uses a try loop and a socket to see if the port is occupied
            try (Socket socket = new Socket()) {
                socket.connect(new InetSocketAddress(host, i), timeOut);
                portList.add(i);
                System.out.print("successful!\n");
            }
            //fails to ping
            catch (IOException error) {
                System.out.print("failure!\n");
            }

        }
        //prints out all the used ports
        System.out.print("Ports running :");
        for (Integer port : portList) {
            System.out.print(" " + port);
        }
    }
}
