package kandilcafe;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.URISyntaxException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
public class firstGUI{
    static String host="localhost";
    static int port=8130;
    static boolean flag=false;
    public static int UserId;
    public static String UserName;
    public static Text msg;
    public static int auth;
    public static TextField textField;
    public static PasswordField passwordField;
    public static TextField Username;
    static Stage stage1;
    static Scene scene;
    private static final String IDLE_BUTTON_STYLE1 = "-fx-background-color: #a5efa5;";
    private static final String HOVERED_BUTTON_STYLE1 = "-fx-background-color: #00ff00;";
    private static final String IDLE_BUTTON_STYLE2 = "-fx-background-color: #ed8282;";
    private static final String HOVERED_BUTTON_STYLE2 = "-fx-background-color: #f44265;";
    private static final String IDLE_BUTTON_STYLE_EXIT = "-fx-background-color: transparent;";
    private static final String HOVERED_BUTTON_STYLE_EXIT = " -fx-background-color: #ff0000;";
    public static Scene S(Stage stage)throws SQLException, ClassNotFoundException{
        firstGUI.stage1 = stage;

        Image img = new Image("logo.png");
        ImageView logo = new ImageView(img);
        logo.setFitHeight(400);
        logo.setFitWidth(400);

        Label l = new Label("LOGIN");
        l.setFont(Font.font("tahoma",FontWeight.BOLD,FontPosture.ITALIC,30));

        Username = new TextField();
        Username.setPromptText("Username");
        Username.setPrefHeight(60);
        Username.setPrefWidth(350);
        Username.setFont(Font.font("tahoma",FontWeight.BLACK,FontPosture.ITALIC,18));

        textField = new TextField();
        textField.setPromptText("Password");
        textField.setPrefHeight(60);
        textField.setPrefWidth(350);
        textField.setFont(Font.font("tahoma",FontWeight.BLACK,FontPosture.ITALIC,18));
        textField.setManaged(false);
        textField.setVisible(false);
        passwordField = new PasswordField();
        passwordField.setPromptText("Password");
        passwordField.setPrefHeight(60);
        passwordField.setPrefWidth(350);
        passwordField.setFont(Font.font("tahoma",FontWeight.BLACK,FontPosture.ITALIC,18));
        CheckBox checkBox = new CheckBox("Show/Hide password");
        textField.managedProperty().bind(checkBox.selectedProperty());
        textField.visibleProperty().bind(checkBox.selectedProperty());
        passwordField.managedProperty().bind(checkBox.selectedProperty().not());
        passwordField.visibleProperty().bind(checkBox.selectedProperty().not());
        textField.textProperty().bindBidirectional(passwordField.textProperty());

        Button btn1 = new Button("Login");
        btn1.setPrefWidth(130);
        btn1.setCursor(Cursor.HAND);
        btn1.setStyle("-fx-font-size: 16pt;-fx-font-family: 'Times New Roman'; -fx-base: rgb(O, 145, 47); -fx-background-color: #a5efa5;");
        btn1.setFont(Font.font("Times New Roman",FontWeight.BOLD,18));

        Button btn2 = new Button("Register");
        btn2.setFont(Font.font("Times New Roman",FontWeight.BOLD,18));
        btn2.setPrefWidth(130);
        btn2.setCursor(Cursor.HAND);
        btn2.setStyle("-fx-font-size: 16pt;-fx-font-family: 'Times New Roman'; -fx-base: rgb(O, 145, 47); -fx-background-color: #ed8282;");
        
        Button exit = new Button("Exit");
        exit.setFont(Font.font("Times New Roman",FontWeight.BOLD,18));
        exit.setPrefWidth(80);
        exit.setCursor(Cursor.HAND);
        exit.setStyle("-fx-font-size: 16pt;-fx-font-family: 'Times New Roman'; -fx-base: rgb(O, 145, 47); -fx-background-color: #ff0000;");

        btn1.setStyle(IDLE_BUTTON_STYLE1);
        btn1.setOnMouseEntered(e -> btn1.setStyle(HOVERED_BUTTON_STYLE1));
        btn1.setOnMouseExited(e -> btn1.setStyle(IDLE_BUTTON_STYLE1));

        btn2.setStyle(IDLE_BUTTON_STYLE2);
        btn2.setOnMouseEntered(e -> btn2.setStyle(HOVERED_BUTTON_STYLE2));
        btn2.setOnMouseExited(e -> btn2.setStyle(IDLE_BUTTON_STYLE2));

        exit.setStyle(IDLE_BUTTON_STYLE_EXIT);
        exit.setOnMouseEntered(e -> exit.setStyle(HOVERED_BUTTON_STYLE_EXIT));
        exit.setOnMouseExited(e -> exit.setStyle(IDLE_BUTTON_STYLE_EXIT));

        msg = new Text("");
        msg.setFont(Font.font("tahoma",FontWeight.BOLD,FontPosture.ITALIC,18));
        
        BorderPane bp2 = new BorderPane();
        bp2.setRight(btn1);
        bp2.setLeft(checkBox);
        BorderPane.setAlignment(btn1, Pos.CENTER_RIGHT);
        BorderPane.setAlignment(checkBox, Pos.TOP_LEFT);
        
        BorderPane bp3 = new BorderPane();
        bp3.setRight(btn2);
        bp3.setLeft(msg);
        BorderPane.setAlignment(btn2, Pos.CENTER_RIGHT);
        BorderPane.setAlignment(msg, Pos.CENTER_LEFT);
        VBox vb = new VBox();
        vb.getChildren().addAll(logo,l);
        vb.setAlignment(Pos.CENTER);
        vb.setPadding(new Insets(10));
        vb.setSpacing(10);

        BorderPane bp = new BorderPane();
        bp.setPadding(new Insets(10));
        bp.setCenter(logo);
        BorderPane bp4 = new BorderPane();
        bp4.setPadding(new Insets(10));
        bp4.setRight(exit);

        VBox vbox = new VBox(10);
        vbox.getChildren().addAll(bp4,logo,l,Username,passwordField, textField);
        vbox.setAlignment(Pos.CENTER);
        vbox.setSpacing(10);

        GridPane root = new GridPane();
        root.setStyle("-fx-background-color: #4286f4");
        root.setMinSize(400, 200);
        root.setPadding(new Insets(10, 10, 10, 10));
        root.setVgap(5);
        root.setHgap(5);
        root.setAlignment(Pos.CENTER);
        root.add(vbox, 0, 0);
        root.add(bp2, 0, 1);
        root.add(bp3, 0, 2);
        root.setVgap(10);

        Scene scene1 = new Scene(root, 900, 750);

        btn1.setOnAction(e->{
            msg.setText("");
            if(flag==false){
                try{
                    Socket s=new Socket(host,port);
                    System.out.println("Connected to Server");
                    InputStream IS= s.getInputStream();
                    OutputStream OS= s.getOutputStream();
                    DataInputStream reader=new DataInputStream(IS);
                    DataOutputStream writer=new DataOutputStream(OS);
                    writer.writeInt(1);
                    writer.writeUTF(Username.getText());
                    writer.writeUTF(passwordField.getText());
                    int Status = reader.readInt();
                    if(Status == 0){
                        msg.setFill(Color.RED);
                        msg.setText("Wrong Username/Passowrd");
                    }else{
                        UserName = Username.getText();
                        UserId = reader.readInt();
                        auth = reader.readInt();
                        secondGUI.UserId=UserId;
                        secondGUI.UserName=UserName;
                        secondGUI.content=0;
                        secondGUI.auth=auth;
                        msg.setFill(Color.GREEN);
                        msg.setText("Successful Login");
                        stage.setScene(secondGUI.S(stage));
                    }
                }catch(IOException exc){
                }catch(ClassNotFoundException | SQLException | URISyntaxException ex) {
                    Logger.getLogger(firstGUI.class.getName()).log(Level.SEVERE, null, ex);
                }
            }else{ 
                if(Username.getText().length() >= 3 && passwordField.getText().length() >= 3 ){
                    UserName = Username.getText();
                    try{
                        Socket s=new Socket(host,port);
                        System.out.println("Connected to Server\n");
                        InputStream IS= s.getInputStream();
                        OutputStream OS=s.getOutputStream();
                        DataInputStream reader=new DataInputStream(IS);
                        DataOutputStream writer=new DataOutputStream(OS);
                        writer.writeInt(2);
                        writer.writeUTF(Username.getText());
                        writer.writeUTF(passwordField.getText());
                        int Status = reader.readInt();
                        if(Status == 1){
                            btn2.fire();
                            msg.setFill(Color.GREEN);
                            msg.setText("Successful Registeration");
                        }
                    }catch	(IOException	exc){
                    }
                }else{
                    msg.setFill(Color.RED);
                    msg.setText("Invalid Data");
                }
            }
        });
        btn2.setOnAction(e->{
            msg.setText("");
            if(flag==false){
                l.setText("REGISTER");
                btn1.setText("Register");
                btn2.setText("Login");
                flag=true;
            }else{
                l.setText("LOGIN");
                btn1.setText("Login");
                btn2.setText("Register");
                flag=false;
            }
        });
        exit.setOnAction(e->{
            System.exit(0);
        });
        firstGUI.scene = scene1;
        return scene1;
    }
    public static void logout(){stage1.setScene(scene);}
}
