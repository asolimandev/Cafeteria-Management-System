package kandilcafe;
import java.awt.Desktop;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.Socket;
import java.net.URISyntaxException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.geometry.Insets;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;
import javafx.util.Duration;

public class secondGUI {
    static int port=8130;
    static String host="localhost";
    public static int content=0;
    public static int  UserId = firstGUI.UserId;
    public static String  UserName = firstGUI.UserName;
    public static int auth=firstGUI.auth;
    static int sleep=10;
    static TableView<Item> table = new TableView<>();
    static TableView<Item> table1 = new TableView<>();
    static TableView<Feedback> table10 = new TableView<>();
    static TableView<Item> table11 = new TableView<>();
    static ObservableList<Item> data = FXCollections.observableArrayList();
    static ObservableList<Item> data1 = FXCollections.observableArrayList();
    static ObservableList<Feedback> data10 = FXCollections.observableArrayList();
    static ObservableList<Item> data11 = FXCollections.observableArrayList();
    private static final String IDLE_BUTTON_STYLE_BACK = "-fx-background-color: #ed8282;";
    private static final String HOVERED_BUTTON_STYLE_BACK = "-fx-background-color: #f44265;";
    private static final String IDLE_BUTTON_STYLE1 = "-fx-background-color: #a5efa5;";
    private static final String HOVERED_BUTTON_STYLE1 = "-fx-background-color: #00ff00;";
    static Scene scene;
    
    public static Scene S(Stage stage) throws IOException, ClassNotFoundException, SQLException, URISyntaxException{
        Image img0 = new Image("main.png");
        ImageView logo0 = new ImageView(img0);
        //logo0.setFitHeight(500);
        //logo0.setFitWidth(707);
        
        Image img = new Image("back.png");
        ImageView logo = new ImageView(img);
        logo.setFitHeight(20);
        logo.setFitWidth(20);
        
        Image img1 = new Image("logo1.png");
        ImageView logo1 = new ImageView(img1);
        logo1.setFitHeight(40);
        logo1.setFitWidth(40);
        
        Image img2 = new Image("logo2.png");
        ImageView logo2 = new ImageView(img2);
        logo2.setFitHeight(40);
        logo2.setFitWidth(40);
        
        Image img3 = new Image("logo3.png");
        ImageView logo3 = new ImageView(img3);
        logo3.setFitHeight(40);
        logo3.setFitWidth(40);
        
        Image img4 = new Image("logo4.png");
        ImageView logo4 = new ImageView(img4);
        logo4.setFitHeight(40);
        logo4.setFitWidth(40);
        
        Menu menu1 = new Menu("Profile");
        menu1.setStyle("-fx-font-family: Eras Bold ITC;-fx-font-weight: bold;-fx-font-size: 18;");
        menu1.setGraphic(logo1);
        Menu menu2 = new Menu("Order");
        menu2.setStyle("-fx-font-family: Eras Bold ITC;-fx-font-weight: bold;-fx-font-size: 18;");
        menu2.setGraphic(logo2);
        Menu menu3 = new Menu("Manage");
        menu3.setStyle("-fx-font-family: Eras Bold ITC;-fx-font-weight: bold;-fx-font-size: 18;");
        menu3.setGraphic(logo3);
        Menu menu4 = new Menu("Information");
        menu4.setStyle("-fx-font-family: Eras Bold ITC;-fx-font-weight: bold;-fx-font-size: 18;");
        menu4.setGraphic(logo4);
        
        MenuItem m11 = new MenuItem("Edit Profile");
        MenuItem m12 = new MenuItem("Logout");
        m12.setStyle("-fx-text-fill: red;");
        //m12.setStyle("-fx-text-fill: white;-fx-background-color: white;-fx-font: Eras Bold ITC;-fx-font-family: Eras Bold ITC;-fx-font-weight: bold;-fx-font-size: 15;");

        
        MenuItem m21 = new MenuItem("Menu Items");
        MenuItem m22 = new MenuItem("Create Order");
        MenuItem m23 = new MenuItem("Shopping Cart");
        
        MenuItem m31 = new MenuItem("Salesmen");
        MenuItem m32 = new MenuItem("Update Data");
        MenuItem m33 = new MenuItem("Profit Analysis");
        MenuItem m34 = new MenuItem("Sales Reports");
        
        MenuItem m41 = new MenuItem("Write Feedback");
        MenuItem m42 = new MenuItem("Customers Feedback");
        MenuItem m43 = new MenuItem("Help");
        MenuItem m44 = new MenuItem("About KandilCafé");
        
        menu1.getItems().addAll(m11,m12);
        if(auth==0){
            menu2.getItems().addAll(m21,m23);
        }else if(auth==1){
            menu2.getItems().addAll(m22);
        }
        menu3.getItems().addAll(m31,m32,m33,m34);
        if(auth==0){
            menu4.getItems().addAll(m41,m43,m44);
        }else if(auth==1){
            menu4.getItems().addAll(m43,m44);
        }else{
            menu4.getItems().addAll(m42,m43,m44);
        }
        
        MenuBar mb = new MenuBar();
        if(auth==0){
            mb.getMenus().addAll(menu1,menu2,menu4);
        }else if(auth==1){
            mb.getMenus().addAll(menu1,menu2,menu4);
        }else{
            mb.getMenus().addAll(menu1,menu3,menu4);
        }
        
        String name, authority;
        
        if(UserName.indexOf("Manager")!=-1){
            name=UserName.substring(0, UserName.indexOf("Manager"));
        }else if(UserName.indexOf("Seller")!=-1){
            name=UserName.substring(0, UserName.indexOf("Seller"));
        }else{
            name=UserName;
        }
        switch(auth){
            case 1: authority="Seller";
                break;
            case 2: authority="Manager";
                    break;
            default:authority="Customer";
                break;
        }
                 
        Text t1 = new Text("Welcome "+name+" ");
        t1.setUnderline(true);
        t1.setFont(Font.font("Times New Roman",FontWeight.BOLD,20));
        t1.setFill(javafx.scene.paint.Color.BLACK);
        Text t2 = new Text("("+authority+")");
        t2.setUnderline(true);
        t2.setFont(Font.font("tahoma",FontWeight.BOLD,14));
        t2.setFill(javafx.scene.paint.Color.WHITE);
        TextFlow tf1 = new TextFlow();
        tf1.getChildren().addAll(t1,t2);
        
        Text t3 = new Text();
        t3.setUnderline(true);
        t3.setFont(Font.font("tahoma",FontWeight.BOLD,14));
        t3.setFill(javafx.scene.paint.Color.WHITE);
        Text t4 = new Text();
        t4.setUnderline(true);
        t4.setFont(Font.font("Times New Roman",FontWeight.BOLD,20));
        t4.setFill(javafx.scene.paint.Color.BLACK);
        
        Timeline clock = new Timeline(new KeyFrame(Duration.ZERO, e -> {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("eeee, dd MMMM yyyy ");
            t3.setText(LocalDateTime.now().format(formatter));
            DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern(" HH:mm:ss a");
            t4.setText(LocalDateTime.now().format(formatter2));          
        }), new KeyFrame(Duration.seconds(1)));
        clock.setCycleCount(Animation.INDEFINITE);
        clock.play();
        
        TextFlow tf2 = new TextFlow();
        tf2.getChildren().addAll(t3,t4);
        
        Button back = new Button("Back");
        back.setFont(Font.font("Times New Roman",FontWeight.BOLD,18));
        back.setPrefHeight(40);
        back.setPrefWidth(120);
        back.setGraphic(logo);
        back.setCursor(Cursor.HAND);
        
        back.setStyle(IDLE_BUTTON_STYLE_BACK);
        back.setOnMouseEntered(e -> back.setStyle(HOVERED_BUTTON_STYLE_BACK));
        back.setOnMouseExited(e -> back.setStyle(IDLE_BUTTON_STYLE_BACK));
        
        VBox bvb=new VBox();
        bvb.setSpacing(10);
        bvb.getChildren().addAll(tf1);
        
        AnchorPane ap = new AnchorPane();
        ap.getChildren().addAll(bvb,tf2);
        AnchorPane.setLeftAnchor(bvb, 1d);
        AnchorPane.setRightAnchor(tf2, 1d);
        ap.setPadding(new Insets(5));
        
        VBox vb0 = new VBox();
        vb0.getChildren().addAll(mb,ap);
        
        //content0
        VBox content0 = new VBox();
        content0.getChildren().addAll(logo0);
        content0.setAlignment(Pos.CENTER);
        content0.setPadding(new Insets(10));
        content0.setSpacing(20);
        //content0.setVgrow(logo0, Priority.ALWAYS);

        
        //content 1
        Label l = new Label("Edit Profile");
        l.setFont(Font.font("tahoma",FontWeight.BOLD,FontPosture.ITALIC,30));
        
        TextField Username = new TextField(UserName);
        Username.setPromptText("New Username");
        Username.setPrefHeight(60);
        Username.setPrefWidth(700);
        Username.setFont(Font.font("tahoma",FontWeight.BLACK,FontPosture.ITALIC,18));

        TextField textField = new TextField();
        textField.setPromptText("New Password");
        textField.setPrefHeight(60);
        textField.setPrefWidth(350);
        textField.setFont(Font.font("tahoma",FontWeight.BLACK,FontPosture.ITALIC,18));
        textField.setManaged(false);
        textField.setVisible(false);
        PasswordField passwordField = new PasswordField();
        passwordField.setPromptText("New Password");
        passwordField.setPrefHeight(60);
        passwordField.setPrefWidth(350);
        passwordField.setFont(Font.font("tahoma",FontWeight.BLACK,FontPosture.ITALIC,18));
        CheckBox checkBox = new CheckBox("Show/Hide password");
        textField.managedProperty().bind(checkBox.selectedProperty());
        textField.visibleProperty().bind(checkBox.selectedProperty());
        passwordField.managedProperty().bind(checkBox.selectedProperty().not());
        passwordField.visibleProperty().bind(checkBox.selectedProperty().not());
        textField.textProperty().bindBidirectional(passwordField.textProperty());

        Button btn1 = new Button("Submit");
        btn1.setPrefWidth(130);
        btn1.setCursor(Cursor.HAND);
        btn1.setStyle("-fx-font-size: 16pt;-fx-font-family: 'Times New Roman'; -fx-base: rgb(O, 145, 47); -fx-background-color: #a5efa5;");
        btn1.setFont(Font.font("Times",FontWeight.BOLD,18));
        btn1.setStyle(IDLE_BUTTON_STYLE1);
        btn1.setOnMouseEntered(e -> btn1.setStyle(HOVERED_BUTTON_STYLE1));
        btn1.setOnMouseExited(e -> btn1.setStyle(IDLE_BUTTON_STYLE1));

        Text msg = new Text();
        msg.setFont(Font.font("tahoma",FontWeight.BOLD,FontPosture.ITALIC,18));
        BorderPane bp2 = new BorderPane();
        bp2.setRight(btn1);
        bp2.setLeft(checkBox);
        bp2.setCenter(msg);
        BorderPane.setAlignment(btn1, Pos.CENTER_RIGHT);
        BorderPane.setAlignment(checkBox, Pos.TOP_LEFT);

        VBox vb = new VBox();
        vb.getChildren().addAll(l);
        vb.setAlignment(Pos.CENTER);
        vb.setPadding(new Insets(30));
        vb.setSpacing(10);

        GridPane content1 = new GridPane();
        content1.setVgap(10);
        content1.add(vb, 0, 0);
        content1.setAlignment(Pos.CENTER);
        content1.add(Username, 0, 1);
        content1.add(passwordField, 0, 2);
        content1.add(textField, 0, 2);
        content1.add(bp2, 0, 3);        
        content1.setAlignment(Pos.CENTER);
        content1.setPadding(new Insets(10));
        
        //contetn2
        Socket	s=new Socket(host,port);
        InputStream IS= s.getInputStream();
        OutputStream OS= s.getOutputStream();
        DataOutputStream writer1 =new DataOutputStream(OS);
        DataInputStream reader1=new DataInputStream(IS);
        writer1.writeInt(3);                
        TableColumn Id= new TableColumn("Id");	
        Id.setCellValueFactory(new PropertyValueFactory<>("Id"));  
        Id.setStyle( "-fx-alignment: CENTER;-fx-font-size: 16pt;-fx-font-family: 'Script MT Bold';");
        TableColumn Name= new TableColumn("Name");	
        Name.setCellValueFactory(new PropertyValueFactory<>("Name"));
        Name.setStyle( "-fx-alignment: LEFT;-fx-font-size: 16pt;-fx-font-family: 'Script MT Bold';");
        TableColumn Price  = new TableColumn("Price");
        Price.setCellValueFactory(new PropertyValueFactory<>("Price"));
        Price.setStyle( "-fx-alignment: CENTER;-fx-font-size: 16pt;-fx-font-family: 'Script MT Bold';");
        TableColumn Category =	new TableColumn("Category");
        Category.setCellValueFactory(new PropertyValueFactory<>("Category"));
        Category.setStyle( "-fx-alignment: LEFT;-fx-font-size: 16pt;-fx-font-family: 'Script MT Bold';");
        TableColumn Quantity =	new TableColumn("Quantity");
        Quantity.setCellValueFactory(new PropertyValueFactory<>("Quantity"));
        Quantity.setStyle( "-fx-alignment: CENTER;-fx-font-size: 14pt;-fx-font-family: 'Script MT Bold';");
        TableColumn btnn = new TableColumn("Add to Cart");
        btnn.setCellValueFactory(new PropertyValueFactory<>("AddButton"));
        btnn.setStyle( "-fx-alignment: CENTER;-fx-font-size: 14pt;-fx-font-family: 'Script MT Bold';");
        

        
        table.getColumns().addAll(Id, Name,Price,Category,Quantity,btnn);

        table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        int IdT,PriceT,QuantityT;String NameT,CatNameT;
        while(reader1.readInt() == 1){
            IdT = reader1.readInt();
            NameT = reader1.readUTF();            
            PriceT = reader1.readInt();
            CatNameT = reader1.readUTF();
            QuantityT = reader1.readInt();
            data.add(new Item(IdT, NameT, PriceT, CatNameT, QuantityT ));
        }
        table.setItems(data);

        Label l2 = new Label("Menu Items");
        l2.setFont(Font.font("Script MT Bold",FontWeight.BOLD,FontPosture.ITALIC,30));
        
        Button cart = new Button("Proceed to Cart");
        cart.setPrefWidth(230);
        cart.setCursor(Cursor.HAND);
        cart.setStyle("-fx-font-size: 16pt;-fx-font-family: 'Times New Roman'; -fx-base: rgb(O, 145, 47); -fx-background-color: #a5efa5;");
        cart.setFont(Font.font("Times",FontWeight.BOLD,18));
        cart.setStyle(IDLE_BUTTON_STYLE1);
        cart.setOnMouseEntered(e -> cart.setStyle(HOVERED_BUTTON_STYLE1));
        cart.setOnMouseExited(e -> cart.setStyle(IDLE_BUTTON_STYLE1));
        
        VBox content2 = new VBox();
        content2.getChildren().addAll(l2,table,cart);
        content2.setAlignment(Pos.CENTER);
        content2.setPadding(new Insets(10));
        content2.setSpacing(20);
       
        
        //coontent3
        Label l3 = new Label("Create Order");
        l3.setFont(Font.font("tahoma",FontWeight.BOLD,FontPosture.ITALIC,30));
        
        Button createOrder = new Button("Proceed to Cart");
        createOrder.setPrefWidth(230);
        createOrder.setCursor(Cursor.HAND);
        createOrder.setStyle("-fx-font-size: 16pt;-fx-font-family: 'Times New Roman'; -fx-base: rgb(O, 145, 47); -fx-background-color: #a5efa5;");
        createOrder.setFont(Font.font("Times",FontWeight.BOLD,18));
        createOrder.setStyle(IDLE_BUTTON_STYLE1);
        createOrder.setOnMouseEntered(e -> createOrder.setStyle(HOVERED_BUTTON_STYLE1));
        createOrder.setOnMouseExited(e -> createOrder.setStyle(IDLE_BUTTON_STYLE1));
        
        VBox content3 = new VBox();
        content3.getChildren().addAll(l3);
        content3.setAlignment(Pos.CENTER);
        content3.setPadding(new Insets(10));
        content3.setSpacing(20);
        
        //content4
                
        TableColumn NameC= new TableColumn("Name");	
        NameC.setCellValueFactory(new PropertyValueFactory<>("Name"));
        NameC.setStyle( "-fx-alignment: left;-fx-font-size: 16pt;-fx-font-family: 'Script MT Bold';");
        TableColumn PriceC  = new TableColumn("Price");
        PriceC.setCellValueFactory(new PropertyValueFactory<>("Price"));
        PriceC.setStyle( "-fx-alignment: center;-fx-font-size: 16pt;-fx-font-family: 'Script MT Bold';");
        TableColumn CategoryC =	new TableColumn("Category");
        CategoryC.setCellValueFactory(new PropertyValueFactory<>("Category"));
        CategoryC.setStyle( "-fx-alignment: left;-fx-font-size: 16pt;-fx-font-family: 'Script MT Bold';");
        TableColumn btnn1 = new TableColumn("Remove");
        btnn1.setCellValueFactory(new PropertyValueFactory<>("RemoveButton"));
        btnn1.setStyle( "-fx-alignment: CENTER;-fx-font-size: 16pt;-fx-font-family: 'Script MT Bold';");
        
        table1.getColumns().addAll(NameC,PriceC,CategoryC,btnn1);
        table1.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        Label l4 = new Label("Shopping Cart");
        l4.setFont(Font.font("Script MT Bold",FontWeight.BOLD,FontPosture.ITALIC,30));
        
        Button back4 = new Button("Back to Menu");
        back4.setGraphic(logo);
        back4.setPrefWidth(230);
        back4.setCursor(Cursor.HAND);
        back4.setStyle("-fx-font-size: 16pt;-fx-font-family: 'Times New Roman'; -fx-base: rgb(O, 145, 47); -fx-background-color: #a5efa5;");
        back4.setFont(Font.font("Times",FontWeight.BOLD,18));
        back4.setStyle(IDLE_BUTTON_STYLE_BACK);
        back4.setOnMouseEntered(e -> back4.setStyle(HOVERED_BUTTON_STYLE_BACK));
        back4.setOnMouseExited(e -> back4.setStyle(IDLE_BUTTON_STYLE_BACK));
        
        Button order = new Button("Submit Order");
        order.setPrefWidth(230);
        order.setCursor(Cursor.HAND);
        order.setStyle("-fx-font-size: 16pt;-fx-font-family: 'Times New Roman'; -fx-base: rgb(O, 145, 47); -fx-background-color: #a5efa5;");
        order.setFont(Font.font("Times",FontWeight.BOLD,18));
        order.setStyle(IDLE_BUTTON_STYLE1);
        order.setOnMouseEntered(e -> order.setStyle(HOVERED_BUTTON_STYLE1));
        order.setOnMouseExited(e -> order.setStyle(IDLE_BUTTON_STYLE1));
        
        HBox hb4 = new HBox();
        hb4.getChildren().addAll(back4,order);
        hb4.setAlignment(Pos.CENTER);
        
        VBox content4 = new VBox();
        content4.getChildren().addAll(l4,table1,hb4);
        content4.setAlignment(Pos.CENTER);
        content4.setPadding(new Insets(10));
        content4.setSpacing(20);
        
        //content5
        Label l5 = new Label("Salesmen");
        l5.setFont(Font.font("tahoma",FontWeight.BOLD,FontPosture.ITALIC,30));
        
        VBox content5 = new VBox();
        content5.getChildren().addAll(l5);
        content5.setAlignment(Pos.CENTER);
        content5.setPadding(new Insets(10));
        content5.setSpacing(20);
        
        //content6
        Label l6 = new Label("Update Data");
        l6.setFont(Font.font("tahoma",FontWeight.BOLD,FontPosture.ITALIC,30));
        
        VBox content6 = new VBox();
        content6.getChildren().addAll(l6);
        content6.setAlignment(Pos.CENTER);
        content6.setPadding(new Insets(10));
        content6.setSpacing(20);
        
        //content7
        Label l7 = new Label("Profit Analysis");
        l7.setFont(Font.font("tahoma",FontWeight.BOLD,FontPosture.ITALIC,30));
        
        VBox content7 = new VBox();
        content7.getChildren().addAll(l7);
        content7.setAlignment(Pos.CENTER);
        content7.setPadding(new Insets(10));
        content7.setSpacing(20);
        
        //content8
        Label l8 = new Label("Sales Reports");
        l8.setFont(Font.font("tahoma",FontWeight.BOLD,FontPosture.ITALIC,30));
        
        VBox content8 = new VBox();
        content8.getChildren().addAll(l8);
        content8.setAlignment(Pos.CENTER);
        content8.setPadding(new Insets(10));
        content8.setSpacing(20);
        
        //content9
        Label l9 = new Label("Write Feedback");
        l9.setFont(Font.font("tahoma",FontWeight.BOLD,FontPosture.ITALIC,30));
        
        TextArea t9 = new TextArea();
        t9.setFont(Font.font("Script MT Bold",FontWeight.BOLD,FontPosture.ITALIC,20));
        t9.setStyle("-fx-background-color: rgba(53,89,119,0.4);");
        
        Button btn9 = new Button("Submit Feedback");
        btn9.setPrefWidth(230);
        btn9.setCursor(Cursor.HAND);
        btn9.setStyle("-fx-font-size: 16pt;-fx-font-family: 'Times New Roman'; -fx-base: rgb(O, 145, 47); -fx-background-color: #a5efa5;");
        btn9.setFont(Font.font("Times",FontWeight.BOLD,18));
        btn9.setStyle(IDLE_BUTTON_STYLE1);
        btn9.setOnMouseEntered(e -> btn9.setStyle(HOVERED_BUTTON_STYLE1));
        btn9.setOnMouseExited(e -> btn9.setStyle(IDLE_BUTTON_STYLE1));
        
        Text msg9 = new Text();
        msg9.setFont(Font.font("tahoma",FontWeight.BOLD,FontPosture.ITALIC,18));
        
        VBox content9 = new VBox();
        content9.getChildren().addAll(l9,t9,btn9,msg9);
        content9.setAlignment(Pos.CENTER);
        content9.setPadding(new Insets(10));
        content9.setSpacing(20);
        
        //content10              
        TableColumn custIdT= new TableColumn("Id");	
        custIdT.setCellValueFactory(new PropertyValueFactory<>("Id"));  
        custIdT.setStyle( "-fx-alignment: CENTER;-fx-font-size: 16pt;-fx-font-family: 'Script MT Bold';");
        TableColumn custNameT= new TableColumn("Customer Name");	
        custNameT.setCellValueFactory(new PropertyValueFactory<>("Name"));
        custNameT.setStyle( "-fx-alignment: LEFT;-fx-font-size: 16pt;-fx-font-family: 'Script MT Bold';");
        TableColumn custFbT =	new TableColumn("Feedback");
        custFbT.setCellValueFactory(new PropertyValueFactory<>("Category"));
        custFbT.setStyle( "-fx-alignment: LEFT;-fx-font-size: 16pt;-fx-font-family: 'Script MT Bold';");
        table10.getColumns().addAll(custIdT, custNameT,custFbT);
        table10.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        Label l10 = new Label("Customers Feedback");
        l10.setFont(Font.font("tahoma",FontWeight.BOLD,FontPosture.ITALIC,30));
        
        VBox content10 = new VBox();
        content10.getChildren().addAll(l10,table10);
        content10.setAlignment(Pos.CENTER);
        content10.setPadding(new Insets(10));
        content10.setSpacing(20);
        
        //content11
        Text t11_1 = new Text("Explanation of the Basic Functionalities:\n\n");
        t11_1.setFont(Font.font("Berlin Sans FB",FontWeight.BOLD,FontPosture.ITALIC,32));
        t11_1.setFill(Color.BLACK);
        Text t11_2 = new Text("Login: ");
        t11_2.setFont(Font.font("Berlin Sans FB",FontWeight.BOLD,FontPosture.ITALIC,22));
        t11_2.setFill(Color.GREEN);
        Text t11_3 = new Text("To login you have to enter a valid username and password otherwise go ahead and register a new account.\n");
        t11_3.setFont(Font.font("Berlin Sans FB",FontWeight.BOLD,FontPosture.ITALIC,22));
        t11_3.setFill(Color.BLACK);
        Text t11_4 = new Text("Register: ");
        t11_4.setFont(Font.font("Berlin Sans FB",FontWeight.BOLD,FontPosture.ITALIC,22));
        t11_4.setFill(Color.BLUE);
        Text t11_5 = new Text("to register just enter a usename not less than 3 characters and a password of any length.\n");
        t11_5.setFont(Font.font("Berlin Sans FB",FontWeight.BOLD,FontPosture.ITALIC,22));
        t11_5.setFill(Color.BLACK);
        Text t11_6 = new Text("Navigation: ");
        t11_6.setFont(Font.font("Berlin Sans FB",FontWeight.BOLD,FontPosture.ITALIC,22));
        t11_6.setFill(Color.ORANGE);
        Text t11_7 = new Text("to Navigate the menu bar just click on one of the shown icons at the top you'll be propmt with a list of each menu. Choose your desired one to contiue using the system.\n");
        t11_7.setFont(Font.font("Berlin Sans FB",FontWeight.BOLD,FontPosture.ITALIC,22));
        t11_7.setFill(Color.BLACK);
        Text t11_8 = new Text("Edit Profile: ");
        t11_8.setFont(Font.font("Berlin Sans FB",FontWeight.BOLD,FontPosture.ITALIC,22));
        t11_8.setFill(Color.GREEN);
        Text t11_9 = new Text("to edit your profile go to the Profile menu item and press on Edit profile option then select your new username or password or both.\n");
        t11_9.setFont(Font.font("Berlin Sans FB",FontWeight.BOLD,FontPosture.ITALIC,22));
        t11_9.setFill(Color.BLACK);
        Text t11_10 = new Text("Logout: ");
        t11_10.setFont(Font.font("Berlin Sans FB",FontWeight.BOLD,FontPosture.ITALIC,22));
        t11_10.setFill(Color.RED);
        Text t11_11 = new Text("to logout of your profile just select Profile from the menu bar then select the second option Logout written in red color.");
        t11_11.setFont(Font.font("Berlin Sans FB",FontWeight.BOLD,FontPosture.ITALIC,22));
        t11_11.setFill(Color.BLACK);
        
        TextFlow tf11 = new TextFlow();
        tf11.setStyle("-fx-background-color: rgba(255,255,255,1);");
        tf11.setPadding(new Insets(20));
        tf11.getChildren().addAll(t11_1,t11_2,t11_3,t11_4,t11_5,t11_6,t11_7,t11_8,t11_9,t11_10,t11_11);
        
        Label l11 = new Label("Help");
        l11.setFont(Font.font("tahoma",FontWeight.BOLD,FontPosture.ITALIC,30));
        
        VBox content11 = new VBox();
        content11.getChildren().addAll(l11,tf11);
        content11.setAlignment(Pos.CENTER);
        content11.setPadding(new Insets(10));
        content11.setSpacing(20);
        
        //content12
        Text t12_1 = new Text("KandilCafé\u2122 Management System was developed in 2019 by the team members: Eng. Abdelrahman Moustafa Kandil, Eng. Yassen Adel, Eng. Ahmed Eldaly, As the final project for the course CS244 Advanced Programming Applications. Under the supervision of Dr. Mohamed Magdy and Eng. Shaimaa Ahmed in College of Computing and Information Technology at Arab Academy for Science, Technology & Maritime Transport. ");
        t12_1.setFont(Font.font("Freestyle Script",FontWeight.BOLD,FontPosture.ITALIC,32));
        Text t12_2 = new Text("\n\nI've to mention some websites that helped us build this systme and during the implementation of this project:\n");
        t12_2.setFont(Font.font("Freestyle Script",FontWeight.BOLD,FontPosture.ITALIC,32));
        Text t12_3 = new Text("\nAnd reference to all that beautiful icons' websites:\n");
        t12_3.setFont(Font.font("Freestyle Script",FontWeight.BOLD,FontPosture.ITALIC,32));
        Hyperlink hl1 = new Hyperlink("Oracle");
        hl1.setFont(Font.font("Freestyle Script",FontWeight.BOLD,FontPosture.ITALIC,32));
        hl1.setOnAction(e->{
            try {
                Desktop.getDesktop().browse(new URL("https://docs.oracle.com/javase/8/javase-clienttechnologies.htm").toURI());
            } catch (MalformedURLException ex) {
                Logger.getLogger(secondGUI.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException | URISyntaxException ex) {
                Logger.getLogger(secondGUI.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        Hyperlink hl2 = new Hyperlink("Stackoverflow");
        hl2.setFont(Font.font("Freestyle Script",FontWeight.BOLD,FontPosture.ITALIC,32));
        hl2.setOnAction(e->{
            try {
                Desktop.getDesktop().browse(new URL("https://stackoverflow.com/").toURI());
            } catch (MalformedURLException ex) {
                Logger.getLogger(secondGUI.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException | URISyntaxException ex) {
                Logger.getLogger(secondGUI.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        
        Hyperlink hl3 = new Hyperlink("Tutorialspoint");
        hl3.setFont(Font.font("Freestyle Script",FontWeight.BOLD,FontPosture.ITALIC,32));
        hl3.setOnAction(e->{
            try {
                Desktop.getDesktop().browse(new URL("https://www.tutorialspoint.com/javafx/").toURI());
            } catch (MalformedURLException ex) {
                Logger.getLogger(secondGUI.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException | URISyntaxException ex) {
                Logger.getLogger(secondGUI.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        
        Hyperlink hl4 = new Hyperlink("Geeksforgeeks");
        hl4.setFont(Font.font("Freestyle Script",FontWeight.BOLD,FontPosture.ITALIC,32));
        hl4.setOnAction(e->{
            try {
                Desktop.getDesktop().browse(new URL("https://www.geeksforgeeks.org/").toURI());
            } catch (MalformedURLException ex) {
                Logger.getLogger(secondGUI.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException | URISyntaxException ex) {
                Logger.getLogger(secondGUI.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        
        Hyperlink hl5 = new Hyperlink("Iconarchive");
        hl5.setFont(Font.font("Freestyle Script",FontWeight.BOLD,FontPosture.ITALIC,32));
        hl5.setOnAction(e->{
            try {
                Desktop.getDesktop().browse(new URL("http://www.iconarchive.com/show/papirus-status-icons-by-papirus-team/avatar-default-icon.html").toURI());
            } catch (MalformedURLException ex) {
                Logger.getLogger(secondGUI.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException | URISyntaxException ex) {
                Logger.getLogger(secondGUI.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        
        Hyperlink hl6 = new Hyperlink("Pixbay");
        hl6.setFont(Font.font("Freestyle Script",FontWeight.BOLD,FontPosture.ITALIC,32));
        hl6.setOnAction(e->{
            try {
                Desktop.getDesktop().browse(new URL("https://pixabay.com/vectors/shopping-cart-shopping-icon-1105049/").toURI());
            } catch (MalformedURLException ex) {
                Logger.getLogger(secondGUI.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException | URISyntaxException ex) {
                Logger.getLogger(secondGUI.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        
        Hyperlink hl7 = new Hyperlink("Ideastodone");
        hl7.setFont(Font.font("Freestyle Script",FontWeight.BOLD,FontPosture.ITALIC,32));
        hl7.setOnAction(e->{
            try {
                Desktop.getDesktop().browse(new URL(" https://ideastodone.com/multi-edit/").toURI());
            } catch (MalformedURLException ex) {
                Logger.getLogger(secondGUI.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException | URISyntaxException ex) {
                Logger.getLogger(secondGUI.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        
        Hyperlink hl8 = new Hyperlink("Dribbble");
        hl8.setFont(Font.font("Freestyle Script",FontWeight.BOLD,FontPosture.ITALIC,32));
        hl8.setOnAction(e->{
            try {
                Desktop.getDesktop().browse(new URL("https://dribbble.com/shots/2009940-Feedback-Icon").toURI());
            } catch (MalformedURLException ex) {
                Logger.getLogger(secondGUI.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException | URISyntaxException ex) {
                Logger.getLogger(secondGUI.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        
        Hyperlink hl9 = new Hyperlink("Flaticon");
        hl9.setFont(Font.font("Freestyle Script",FontWeight.BOLD,FontPosture.ITALIC,32));
        hl9.setOnAction(e->{
            try {
                Desktop.getDesktop().browse(new URL("https://www.flaticon.com/free-icon/back-arrow_340").toURI());
            } catch (MalformedURLException ex) {
                Logger.getLogger(secondGUI.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException | URISyntaxException ex) {
                Logger.getLogger(secondGUI.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        Hyperlink hl10 = new Hyperlink("Fotojet");
        hl10.setFont(Font.font("Freestyle Script",FontWeight.BOLD,FontPosture.ITALIC,32));
        hl10.setOnAction(e->{
            try {
                Desktop.getDesktop().browse(new URL("https://www.fotojet.com/apps/").toURI());
            } catch (MalformedURLException ex) {
                Logger.getLogger(secondGUI.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException | URISyntaxException ex) {
                Logger.getLogger(secondGUI.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        Text t12_4 = new Text("\nAbdelrahman Moustafa Kandil");
        t12_4.setFont(Font.font("Kunstler Script",FontWeight.BOLD,FontPosture.ITALIC,32));
        t12_4.setUnderline(true);
        
        
        TextFlow tf12 = new TextFlow();
        tf12.getChildren().addAll(t12_1,t12_2,hl1,hl2,hl3,hl4,t12_3,hl5,hl6,hl7,hl8,hl9,hl10,t12_4);
        tf12.setStyle("-fx-background-color: rgba(255,255,255,1);");
        tf12.setPadding(new Insets(20));
        
        Label l12 = new Label("About KandilCafé");
        l12.setFont(Font.font("tahoma",FontWeight.BOLD,FontPosture.ITALIC,30));
        
        VBox content12 = new VBox();
        content12.getChildren().addAll(l12,tf12);
        content12.setAlignment(Pos.CENTER);
        content12.setPadding(new Insets(10));
        content12.setSpacing(20);
        
        //Main content
        BorderPane root = new BorderPane();
        root.setTop(vb0);
        root.setCenter(content0);
        root.setStyle("-fx-background-color: #4286f4");
        
        scene =new Scene( root, 900, 750);
        logo0.fitWidthProperty().bind(scene.widthProperty().subtract(40)); 
        logo0.fitHeightProperty().bind(scene.heightProperty().subtract(120));
        
        //content set on action
        m11.setOnAction(e->{
            root.getChildren().removeAll(content1,content2,content3);
            try {
                Thread.sleep(sleep);
            }catch (InterruptedException ex) {
                Logger.getLogger(secondGUI.class.getName()).log(Level.SEVERE, null, ex);
            }
            root.setCenter(content1);
            if(content==0)bvb.getChildren().add(back);
            content=1;
        });
        m12.setOnAction(e->{
            data1.clear();
            data.clear();
            table.getColumns().clear();
            table1.getColumns().clear();
            table.setItems(data);
            table1.setItems(data1);
            firstGUI.UserId=0;
            firstGUI.UserName="";
            firstGUI.auth=0;
            firstGUI.flag=false;
            firstGUI.textField.setText("");
            firstGUI.passwordField.setText("");
            firstGUI.Username.setText("");
            firstGUI.msg.setText("");
            content=0;
            firstGUI.logout();
        });
        m21.setOnAction((ActionEvent e) -> {
            root.getChildren().removeAll(content0,content1,content2,content3,content4,content5,content6,content7,content8,content9,content10,content11,content12);
            try {
                Thread.sleep(sleep);
            } catch (InterruptedException ex) {
                Logger.getLogger(secondGUI.class.getName()).log(Level.SEVERE, null, ex);
            }
            root.setCenter(content2);
            if(content==0)bvb.getChildren().add(back);
            content=2;
        });
        m22.setOnAction(e->{
            root.getChildren().removeAll(content0,content1,content2,content3,content4,content5,content6,content7,content8,content9,content10,content11,content12);
            try {
                Thread.sleep(sleep);
            } catch (InterruptedException ex) {
                Logger.getLogger(secondGUI.class.getName()).log(Level.SEVERE, null, ex);
            }
            root.setCenter(content3);
            if(content==0)bvb.getChildren().add(back);
            content=3;
        });
        m23.setOnAction(e->{
            root.getChildren().removeAll(content0,content1,content2,content3,content4,content5,content6,content7,content8,content9,content10,content11,content12);
            try {
                Thread.sleep(sleep);
            } catch (InterruptedException ex) {
                Logger.getLogger(secondGUI.class.getName()).log(Level.SEVERE, null, ex);
            }
            root.setCenter(content4);
            if(content==0)bvb.getChildren().add(back);
            content=4;
        });
        m31.setOnAction(e->{
            root.getChildren().removeAll(content0,content1,content2,content3,content4,content5,content6,content7,content8,content9,content10,content11,content12);
            try {
                Thread.sleep(sleep);
            } catch (InterruptedException ex) {
                Logger.getLogger(secondGUI.class.getName()).log(Level.SEVERE, null, ex);
            }
            root.setCenter(content5);
            if(content==0)bvb.getChildren().add(back);
            content=5;
        });
        m32.setOnAction(e->{
            root.getChildren().removeAll(content0,content1,content2,content3,content4,content5,content6,content7,content8,content9,content10,content11,content12);
            try {
                Thread.sleep(sleep);
            } catch (InterruptedException ex) {
                Logger.getLogger(secondGUI.class.getName()).log(Level.SEVERE, null, ex);
            }
            root.setCenter(content6);
            if(content==0)bvb.getChildren().add(back);
            content=6;
        });
        m33.setOnAction(e->{
            root.getChildren().removeAll(content0,content1,content2,content3,content4,content5,content6,content7,content8,content9,content10,content11,content12);
            try {
                Thread.sleep(sleep);
            } catch (InterruptedException ex) {
                Logger.getLogger(secondGUI.class.getName()).log(Level.SEVERE, null, ex);
            }
            root.setCenter(content7);
            if(content==0)bvb.getChildren().add(back);
            content=7;
        });
        m34.setOnAction(e->{
            root.getChildren().removeAll(content0,content1,content2,content3,content4,content5,content6,content7,content8,content9,content10,content11,content12);
            try {
                Thread.sleep(sleep);
            } catch (InterruptedException ex) {
                Logger.getLogger(secondGUI.class.getName()).log(Level.SEVERE, null, ex);
            }
            root.setCenter(content8);
            if(content==0)bvb.getChildren().add(back);
            content=8;
        });
        m41.setOnAction(e->{
            root.getChildren().removeAll(content0,content1,content2,content3,content4,content5,content6,content7,content8,content9,content10,content11,content12);
            try {
                Thread.sleep(sleep);
            } catch (InterruptedException ex) {
                Logger.getLogger(secondGUI.class.getName()).log(Level.SEVERE, null, ex);
            }
            root.setCenter(content9);
            if(content==0)bvb.getChildren().add(back);
            content=9;
        });
        m42.setOnAction(e->{
            root.getChildren().removeAll(content0,content1,content2,content3,content4,content5,content6,content7,content8,content9,content10,content11,content12);
            try {
                Thread.sleep(sleep);
            } catch (InterruptedException ex) {
                Logger.getLogger(secondGUI.class.getName()).log(Level.SEVERE, null, ex);
            }
            try{
                Socket	s10=new Socket(host,port);
                InputStream IS10= s10.getInputStream();
                OutputStream OS10= s10.getOutputStream();
                DataOutputStream writer10 =new DataOutputStream(OS10);
                DataInputStream reader10=new DataInputStream(IS10);
                writer10.writeInt(6);  
                int custId; String custName,custFb;
                while(reader10.readInt() == 1){
                    custId = reader10.readInt();
                    custName = reader10.readUTF();            
                    custFb = reader10.readUTF();
                    data10.add(new Feedback(custId, custName, custFb));
                }
                table10.setItems(data10);
            }catch(IOException exc){
                }
            root.setCenter(content10);
            if(content==0)bvb.getChildren().add(back);
            content=10;
        });
        m43.setOnAction(e->{
            root.getChildren().removeAll(content0,content1,content2,content3,content4,content5,content6,content7,content8,content9,content10,content11,content12);
            try {
                Thread.sleep(sleep);
            } catch (InterruptedException ex) {
                Logger.getLogger(secondGUI.class.getName()).log(Level.SEVERE, null, ex);
            }
            root.setCenter(content11);
            if(content==0)bvb.getChildren().add(back);
            content=11;
        });
        m44.setOnAction(e->{
            root.getChildren().removeAll(content0,content1,content2,content3,content4,content5,content6,content7,content8,content9,content10,content11,content12);
            try {
                Thread.sleep(sleep);
            } catch (InterruptedException ex) {
                Logger.getLogger(secondGUI.class.getName()).log(Level.SEVERE, null, ex);
            }
            root.setCenter(content12);
            if(content==0)bvb.getChildren().add(back);
            content=12;
        });
        back.setOnAction(e->{
            root.getChildren().removeAll(content0,content1,content2,content3,content4,content5,content6,content7,content8,content9,content10,content11,content12);
            bvb.getChildren().remove(back);
            root.setCenter(content0);
            content=0;
        });
        btn1.setOnAction(e->{
            if(UserName.length()>=3){
                try{
                    UserName = Username.getText();
                    Socket ss=new Socket(host,port);
                    InputStream IS2= ss.getInputStream();
                    OutputStream OS2=ss.getOutputStream();
                    DataInputStream reader2=new DataInputStream(IS2);
                    DataOutputStream writer2=new DataOutputStream(OS2);
                    writer2.writeInt(5);
                    writer2.writeInt(UserId);
                    writer2.writeUTF(Username.getText());
                    writer2.writeUTF(passwordField.getText());
                    if(reader2.readInt()==1){
                        msg.setFill(Color.GREEN);
                        msg.setText("Successfully Updated Profile");
                        t1.setText("Welcome "+UserName+" ");
                    }else{
                        msg.setFill(Color.RED);
                        msg.setText("Error");
                    }
                }catch(Exception ex){}
            }
        });
        btn9.setOnAction(e->{ //Write feedback
            if(t9.getText().length()<=250){
                try{
                    UserName = Username.getText();
                    Socket ss=new Socket(host,port);
                    InputStream IS2= ss.getInputStream();
                    OutputStream OS2=ss.getOutputStream();
                    DataInputStream reader9=new DataInputStream(IS2);
                    DataOutputStream writer9=new DataOutputStream(OS2);
                    writer9.writeInt(6);
                    writer9.writeInt(UserId);
                    writer9.writeUTF(t9.getText());
                    if(reader9.readInt()==1){
                        msg9.setFill(Color.GREEN);
                        msg9.setText("Feedback Submitted Successfully");
                        t9.setText("");
                    }else{
                        msg9.setFill(Color.RED);
                        msg9.setText("Error");
                    }
                }catch(Exception ex){}
            }else{
                msg9.setFill(Color.RED);
                msg9.setText("write no more than 250 characters");
            }
        });
        cart.setOnAction(e->{
            m23.fire();
        });
        back4.setOnAction((ActionEvent e) -> {
            root.getChildren().removeAll(content1,content2,content3);
            try {
                Thread.sleep(sleep);
            } catch (InterruptedException ex) {
                Logger.getLogger(secondGUI.class.getName()).log(Level.SEVERE, null, ex);
            }
            root.setCenter(content2);
            if(content==0)bvb.getChildren().add(back);
            content=2;
        });
        order.setOnAction(e->{
            if(data1.size()>0){
                try{
                    Socket s1=new Socket(host,port);
                    InputStream IS1= s1.getInputStream();
                    OutputStream OS1 = s1.getOutputStream();
                    DataOutputStream write1 =new DataOutputStream(OS1);
                    int size= data1.size();
                    write1.writeInt(4);
                    write1.writeInt(UserId);
                    write1.writeInt(size);
                    for(int i=0; i< size; i++){
                        write1.writeInt(data1.get(i).Id);
                        write1.writeInt(1);
                    }
                }catch(IOException ex){}
                data1.clear();
                table1.setItems(data1);
            }
        });
        return scene;
    }
    public static class Item {
    int Id;
    int Price;
    int Quantity;
    String Category; 
    String Name;
    Button AddButton = new Button("Add To Cart");
    Button RemoveButton = new Button("Remove");
    Item(int id,String Item, int p,String cat, int q){
        Id=id; Price=p; Quantity=q; Category=cat; Name=Item;
        AddButton.setStyle(IDLE_BUTTON_STYLE1+"-fx-font-size: 14pt;-fx-font-family: 'Script MT Bold';");
        AddButton.setCursor(Cursor.HAND);
        AddButton.setOnMouseEntered(e -> AddButton.setStyle(HOVERED_BUTTON_STYLE1+"-fx-font-size: 14pt;-fx-font-family: 'Script MT Bold';"));
        AddButton.setOnMouseExited(e -> AddButton.setStyle(IDLE_BUTTON_STYLE1+"-fx-font-size: 14pt;-fx-font-family: 'Script MT Bold';"));
        AddButton.setOnAction(e->{
            secondGUI.data1.add(this);
            secondGUI.table1.setItems(secondGUI.data1);
        });
        RemoveButton.setStyle(IDLE_BUTTON_STYLE_BACK+"-fx-font-size: 14pt;-fx-font-family: 'Script MT Bold';");
        RemoveButton.setCursor(Cursor.HAND);
        RemoveButton.setOnMouseEntered(e -> AddButton.setStyle(HOVERED_BUTTON_STYLE_BACK+"-fx-font-size: 14pt;-fx-font-family: 'Script MT Bold';"));
        RemoveButton.setOnMouseExited(e -> AddButton.setStyle(IDLE_BUTTON_STYLE_BACK+"-fx-font-size: 14pt;-fx-font-family: 'Script MT Bold';"));
        RemoveButton.setOnAction(e->{
            secondGUI.data1.remove(this);
            secondGUI.table1.setItems(secondGUI.data1);
        });
    }
    public String getCategory(){return Category;}
    public String getName() {return Name;}
    public int getQuantity(){return Quantity;}
    public int getPrice(){return Price;}
    public int getId(){return Id;}
    public Button getAddButton(){return AddButton;}
    public Button getRemoveButton(){return RemoveButton;}
    }
    public static class Feedback {
    int custId;
    String custName; 
    String custFb;
    Feedback(int id,String name,String fb){
        custId=id; custName=name; custFb=fb;
    }
    public int getUserId(){return custId;}
    public String getUserName(){return custName;}
    public String getFeedback(){return custFb;}
    }
}
