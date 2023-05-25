package FTPsimpleFileTransfer;

import java.io.*;
import java.net.Socket;

/**
 * @author E.Parominsky 25/05/2023 14:15
 */
public class ClientThread extends Thread {
    private Socket socket;
    private BufferedReader reader;
    private BufferedOutputStream out;
    private BufferedInputStream fileReader;

    public ClientThread(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
            //create the buffered reader
            reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            //create the output buffered reader
            out = new BufferedOutputStream(socket.getOutputStream());

            //read the filename
            String fileName = reader.readLine();
            System.out.println("file name: " + fileName + " has been requested by " + socket.getInetAddress().getHostAddress());
            File file = new File(fileName);
            if (!file.exists()) {
                byte code = (byte) 0;
                out.write(code);
                closeConnection();
            } else {
                //if file exists send 1 and send the file
                out.write((byte) 1);
                //create a buffered input stream variable
                fileReader = new BufferedInputStream(new FileInputStream(file));
                //set the buffer size
                byte[] buffer = new byte[1024];
                //this integer is stores the
                int bytesRead = 0;
                while ((bytesRead = fileReader.read(buffer)) != -1) {
                    out.write(buffer, 0, bytesRead);
//                    System.out.println(bytesRead);
                    out.flush();
                }
                //close the connection after the download is finished
                closeConnection();
            }
        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }

    public void closeConnection() throws IOException {
        fileReader.close();
        out.close();
        socket.close();
        reader.close();
    }
}
