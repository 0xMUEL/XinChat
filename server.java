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
        try {
            ServerSocket serverSocket = new ServerSocket(port);
            Socket clientSocket = serverSocket.accept();
            InputStream in = clientSocket.getInputStream();
            byte[] temp_buf = new byte[1000];
            if (in.read(temp_buf, 0, 1000) != -1){
                System.out.println(temp_buf);
            }
            serverSocket.close();
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

