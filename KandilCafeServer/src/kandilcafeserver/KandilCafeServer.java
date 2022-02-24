package kandilcafeserver;
import java.io.IOException;
import java.net.ServerSocket;
import javax.swing.JOptionPane;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.net.Socket;
import java.sql.SQLException;

public class KandilCafeServer{
    public int port=8130;
    ServerSocket serverSocket;
    private void  listenForConnection(){
        try{
            serverSocket=new ServerSocket(port);	 
        }catch(Exception e){}
        System.out.println("Server start Listening for connection requests");
        while(true){
            try{
                Socket socket=serverSocket.accept();
                System.out.println("client accepted");
                ClientThread newThread=new ClientThread(socket);
                newThread.start();
            }catch (IOException e){
                JOptionPane.showMessageDialog(null,"Connection Declined!");
            }
        }
    }
    public static void main(String[] args) {
       KandilCafeServer server = new KandilCafeServer();
       server.listenForConnection();
    }
    public class ClientThread extends Thread {
        DataOutputStream writer;
        DataInputStream reader;
        Socket socket;
        ClientThread( Socket socket) {
            this.socket = socket;
            try{
                InputStream IS = socket.getInputStream();
                reader = new DataInputStream(IS);
            }catch(Exception	exc){}
        }
        @Override
        public	void run(){communicate();}
        private	void communicate(){
            while(true)	{
            try	{
                int type = reader.readInt();
                switch(type){
                    case 1:Function.Login(socket);
                        break;
                    case 2:Function.Register(socket);
                        break;
                    case 3:Function.ViewItem(socket);
                        break;
                    case 4:Function.Order(socket);
                        break;
                    case 5:Function.EditProfile(socket);
                        break;
                    case 6:Function.writeFeedback(socket);
                        break;
                }
            }catch (IOException | SQLException | ClassNotFoundException	e){}}
        }
    }
}
