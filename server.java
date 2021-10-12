import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.TimeUnit;
import java.io.*;

class Server {
    static byte[] bytes = new byte[1000];
    
    public static void main(String[] args) throws IOException {
        if (args[1].equals("-c")) {
            launchClient(args[1], 8888, "hello");
        } else if (args[1].equals("-s")) {
            launchServer(8888);
        }
    }

    public static void launchServer(int port) {
        long count = 0;
        long time = -1;
        try {
            ServerSocket serverSocket = new ServerSocket(port);
            Socket clientSocket = serverSocket.accept();
            InputStream in = clientSocket.getInputStream();
            // System.out.println("connected");
            long start_time = System.nanoTime();
            byte[] temp_buf = new byte[1000];
            int increment = 0;
            while ((increment = in.read(temp_buf, 0, 1000)) != -1){
                count += increment;
            }
            serverSocket.close();
            time = (System.nanoTime() - start_time) / 1000000000;
            double rate = (double)count/1000000*8/time;
            System.out.println("received=" + count/1000 + " KB rate=" + String.format("%.3f", rate) + " Mbps");
        }catch (IOException e) {
            System.out.println("exception");
        }
        return;
    }

    public static void launchClient(String hostname, int port, String content) {
        try {
            Socket soc = new Socket(hostname, port);
            OutputStream outstream = soc.getOutputStream(); 
            PrintWriter out = new PrintWriter(outstream);
        
            
            out.print(content);
            soc.close();
        }catch (IOException e) {
            System.out.println("exception");
        }
        return;
    }
}

