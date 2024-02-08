package applicationformcombo;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.HashMap;
import java.util.Scanner;
import javafx.application.Application;
import javafx.scene.*;
import javafx.stage.Stage;
import javafx.scene.control.*;
import javafx.geometry.Pos;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.*;

class Customer{
    private int ID;
    private String name;
    private String add;
    
     public Customer(int i,String s,String a){
        ID = i;
        name = s;
        add = a;
        
    }
     public int getID(){return ID;}
     public String getName(){return name;}
     public String getAddress(){return add;};
     public void setAddress(String a){add=a;}
}

public class ApplicationFormCombo extends Application
{
    int count =0;
    public void start(Stage stage){
            Font f = new Font("Times New Roman",20);
            HashMap<Integer,Customer> hm = new HashMap();
       
            Label l1 = new Label("Customer ID");l1.setFont(f);
            Label l2 = new Label("Name");l2.setFont(f);
            Label l3 = new Label("Address");l3.setFont(f);
            
            ChoiceBox<Integer> cust = new ChoiceBox();
            
            TextField name = new TextField();
            name.setPrefColumnCount(20);
            
            TextField add = new TextField();
            add.setPrefColumnCount(20);
            
            name.setFont(f);
            add.setFont(f);
            
            Button b1 = new Button("Create");
            b1.setFont(f);
            Button b2 = new Button("Open");
            b2.setFont(f);
            Button b3 = new Button("Save");
            b3.setFont(f);
            
            b1.setOnAction(e->{
            cust.getItems().add(++count);
            cust.setValue(count);
            name.setText("");
            add.setText("");
            });
            
            b3.setOnAction(e->{
                Customer ch =  new Customer(count,name.getText(),add.getText());
                hm.put(count,ch);
          
                
                try(PrintStream ps = new PrintStream(new FileOutputStream("C:\\Users\\Dev Singhal\\Desktop\\Test6.txt"))){
                    for(Customer c : hm.values()){
                        ps.println(c.getID());
                        ps.println(c.getName());
                        ps.println(c.getAddress());
                    }
                }
                catch(Exception ae){
                System.out.println(ae);
                }
                
            });
            
           cust.valueProperty().addListener(e->{
              int c = cust.getValue();
              Customer h = hm.get(c);
              
              name.setText(h.getName());
              add.setText(h.getAddress());
           });
           
           TextArea ta = new TextArea();
           ta.setText("No file opened");
           
            b2.setOnAction(e->{
                String str5= " ";
            try(Scanner sc1 = new Scanner(new FileInputStream("C:\\Users\\Dev Singhal\\Desktop\\Test6.txt"));){
              while(sc1.hasNext()){
                str5 = str5 + sc1.nextLine();
              }
              ta.setText("HELLO \n" + str5 );
              System.out.println();
            }
            catch(Exception ce){
            }
            });
            HBox hb1 = new HBox();
            hb1.getChildren().addAll(l1,cust);
            hb1.setAlignment(Pos.CENTER);
            
            HBox hb2 = new HBox();
            hb2.getChildren().addAll(l2,name);
            hb2.setAlignment(Pos.CENTER);
            
            HBox hb3 = new HBox();
            hb3.getChildren().addAll(l3,add);
            hb3.setAlignment(Pos.CENTER);
            
            HBox hb4 = new HBox();
            hb4.getChildren().addAll(b1,b2,b3);
            hb4.setAlignment(Pos.CENTER);
            
            VBox vb = new VBox();
            vb.getChildren().addAll(hb1,hb2,hb3,hb4,ta);
            vb.setAlignment(Pos.CENTER);
            
            try(Scanner sc = new Scanner(new FileInputStream("C:\\Users\\Dev Singhal\\Desktop\\Test6.txt"))){
                int i1 = 0;
                String i2;
                String i3;
              
                while(sc.hasNext()){
                    i1= sc.nextInt();
                    i2 = sc.next();
                    i3 = sc.next();
                    hm.put(i1,new Customer(i1,i2,i3));
                }
                if(i1>count) count=i1;
                cust.getItems().add(i1);
                
                
            }
            catch(Exception v){
            }
            Scene sc = new Scene(vb,500,500);
            stage.setScene(sc);
            stage.show();
            
    }


    public static void main(String[] args) {
        launch(args);
    }
    
}
