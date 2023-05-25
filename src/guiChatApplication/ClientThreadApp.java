package guiChatApplication;


import java.io.*;
import java.net.Socket;
import java.util.Date;

public class ClientThreadApp extends Thread {
    private Socket socket;
    private boolean isStop;
    private BufferedReader in;
    private PrintWriter out;
//    private File file;
    final static String CRLF = "\r\n";

    public ClientThreadApp(Socket socket) {
        this.socket = socket;
        this.isStop = false;
    }

    @Override
    public void run() {
        try {
            while (!isStop) {
                in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())), true);
                String line;
                String httpHeader = ""; // stores the http header
                String htmlFile = ""; // stores the required html file
                while (true) {
                    line = in.readLine(); // read each line
                    if (/*line == null ||*/ line.equals(CRLF) || line.equals("")) { // end of header is reached
                        break;
                    }
                    httpHeader = httpHeader + line + "\n"; //add a new line to the header
                    if (line.contains("GET")) {
                        // extract the html filename
                        int beginIndex = line.indexOf("/");
                        int endIndex = line.indexOf(" HTTP");
                        htmlFile = line.substring(beginIndex + 1, endIndex);
                    }
                }
//                System.out.println(httpHeader);
                System.out.println(htmlFile);

                processRequest(htmlFile);
                closeConnection();
            }
        } catch (Exception e) {
            System.out.println(e.toString());
            e.printStackTrace();
        }
    }

    public void closeConnection() throws IOException {
        isStop = true;
        in.close();
        out.close();
        socket.close();
    }

    private void processRequest(String htmlFile) throws Exception {
        File file = new File(htmlFile);
        if (file.exists()) {
            //create a BufferedReader to read the html html content
            BufferedReader reader = new BufferedReader(new FileReader(file));

            //sent the HTTP head (HTTP 200)
            out.print("HTTP/1.0 200 OK" + CRLF);
            Date date = new Date();
            out.print("Date: " + date.toString() + CRLF);
            out.print("Server: java tiny web server" + CRLF);
            out.print("Content-Type: text/html" + CRLF);
            out.print("Content-Length: " + file.length() + CRLF);
            out.print("Content-Type: text/html: charset=iso-8859-1" + CRLF);
            // end of http header

            String line = "";
            while ((line = reader.readLine()) != null) { // read the line from the html file
                out.println(line); // write the line to the socket connection
            }
        } else { // if file doesn't exist
            // sent the HTTP head (404 Not Found)
            out.print("HTTP/1.1 404 Not Found" + CRLF);
            Date date = new Date();
            out.println("Date: " + date.toString() + CRLF);

            out.println("Server: java tiny web server" + CRLF);
            out.println("Connection: close" + CRLF);
            out.println("Content-Type: text/html: charset=iso-8859-1" + CRLF);
            // end of http header

            //send file not found message
            out.println("<html><head>");
            out.println("<title>404 Not Found</title>");
            out.println("</head><body>");
            out.println("<h1>Not Found</h1>");
            out.println("<p>The request URL / " + htmlFile + " was not found on this sever.</p>");
            out.println("</body></html>");
            out.println(CRLF);
        }

    }


}
