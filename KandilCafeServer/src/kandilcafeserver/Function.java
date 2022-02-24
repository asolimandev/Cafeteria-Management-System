package kandilcafeserver;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Function {    
    public static void Login(Socket socket){
        try{
            OutputStream OS= socket.getOutputStream();
            InputStream IS= socket.getInputStream();
            DataOutputStream writer1 = new DataOutputStream(OS);
            DataInputStream  reader1 = new DataInputStream(IS);
            String Username =  reader1.readUTF();
            String Password =  reader1.readUTF();
            try (Connection Con1 = Database.con()) {
                String sql1="SELECT * FROM user WHERE userName = '"+Username+"' AND userPass = '"+Password+"';";
                ResultSet resultSet1 = Con1.createStatement().executeQuery(sql1);
                if(!resultSet1.next()){
                    writer1.writeInt(0);
                }else{
                    writer1.writeInt(1);
                    writer1.writeInt(resultSet1.getInt("userId"));
                    writer1.writeInt(resultSet1.getInt("authority"));
                }
                socket.close();
            }
        }catch (IOException | SQLException | ClassNotFoundException ex){}
    }
    
    public static void Register(Socket socket){
        try{
            OutputStream OS = socket.getOutputStream();
            InputStream IS = socket.getInputStream();
            DataOutputStream writer = new DataOutputStream(OS);
            DataInputStream  reader = new DataInputStream(IS);
            String Username =  reader.readUTF();
            int auth=0;if(Username.endsWith("Manager")) auth=2;else if(Username.endsWith("Seller")) auth=1;
            String Password =  reader.readUTF();
            try (Connection Con = Database.con()) {
                String sql="INSERT INTO user (userName,userPass,authority) Values ('"+Username+"','"+Password+"','"+auth+"') ;";
                Con.createStatement().executeUpdate(sql);
                writer.writeInt(1);
                socket.close();
            }
        }catch (IOException | SQLException | ClassNotFoundException ex){}
    
    }
    public static void ViewItem(Socket socket) throws IOException, SQLException, ClassNotFoundException{
        OutputStream OS = socket.getOutputStream();
        DataOutputStream writer3 = new DataOutputStream(OS);
        InputStream IS = socket.getInputStream();
        DataInputStream reader3 = new DataInputStream(IS);
        Connection Con = Database.con();
        String sql="SELECT *  FROM item INNER JOIN category ON item.categoryId = category.categoryId;";
        ResultSet res = Con.createStatement().executeQuery(sql);
        while(res.next()){
            if(res.getInt("itemQuantity")!= 0){
                writer3.writeInt(1);
                int Id = res.getInt("itemId");
                writer3.writeInt(Id);
                String Name = res.getString("itemName");
                writer3.writeUTF(Name);
                int Price = res.getInt("itemPrice");
                writer3.writeInt(Price);
                String CatName = res.getString("categoryName");
                writer3.writeUTF(CatName);
                int Quantity = res.getInt("itemQuantity");
                writer3.writeInt(Quantity);
            }
        }
        writer3.writeInt(0);
        socket.close();
    }
    public static void Order(Socket socket){
        try{
            InputStream OS4 = socket.getInputStream();
            DataInputStream reader4 = new DataInputStream(OS4);
            Connection Con4 = Database.con();
            int UserId = reader4.readInt();
            int count = reader4.readInt();
            int ItemId, quantity;
            for(int i =0; i< count; i++){
                ItemId = reader4.readInt();
                quantity=reader4.readInt();
                String sql4_1="INSERT INTO orders (userId,itemId,quantity) Values ('"+UserId+"','"+ItemId+"','"+quantity+"') ;";
                Con4.createStatement().executeUpdate(sql4_1);
                String sql4_2="UPDATE  itme SET itemQuantity = itemQuantity-'"+quantity+"' Where itemId='"+ItemId+"' ;";
                Con4.createStatement().executeUpdate(sql4_1);
            }
            socket.close();
        }catch(IOException | SQLException | ClassNotFoundException ex){}
    }
    public static void EditProfile(Socket socket){
        try{
            OutputStream OS= socket.getOutputStream();
            InputStream IS= socket.getInputStream();
            DataOutputStream writer5 = new DataOutputStream(OS);
            DataInputStream  reader5 = new DataInputStream(IS);
            int UserId = reader5.readInt();
            String UserName = reader5.readUTF();
            String Password = reader5.readUTF();
            String sql5="UPDATE  user SET userName ='"+UserName+"' , userPass='"+Password+"'  Where userId='"+UserId+"' ;";
            try (Connection Con5 = Database.con()) {
                int resultSet5 = Con5.createStatement().executeUpdate(sql5);
                if(resultSet5==0){
                    writer5.writeInt(0);
                }else{
                    writer5.writeInt(1);
                }
                socket.close();
            }
        }catch(IOException | SQLException | ClassNotFoundException ex){}
    }
    public static void writeFeedback(Socket socket) throws IOException, SQLException, ClassNotFoundException{
        OutputStream OS6 = socket.getOutputStream();
        DataOutputStream writer6 = new DataOutputStream(OS6);
        InputStream IS6 = socket.getInputStream();
        DataInputStream reader6 = new DataInputStream(IS6);
        Connection Con6 = Database.con();
        String sql6="SELECT *  FROM feedback INNER JOIN user ON user.userId = feedback.userId;";
        ResultSet res6 = Con6.createStatement().executeQuery(sql6);
        while(res6.next()){
                writer6.writeInt(1);
                int custId = res6.getInt("userId");
                writer6.writeInt(custId);
                String custName = res6.getString("userName");
                writer6.writeUTF(custName);
                String fb = res6.getString("feedback");
                writer6.writeUTF(fb);
        }
        writer6.writeInt(0);
        socket.close();
    }
}

