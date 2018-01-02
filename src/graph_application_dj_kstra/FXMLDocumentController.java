/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package graph_application_dj_kstra;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import dj_kstra.Dijkstra;
import dj_kstra.Edge;
import dj_kstra.Vertex;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javax.swing.JOptionPane;

/**
 *
 * @author Me
 */
public class FXMLDocumentController implements Initializable {
    
    String[] user_names={"user 1","user 2","user 3","user 4","user 5","user 6"};
    String[] router_names={"router 0","router 1","router 2","router 3","router 4","router 5"};
    Dijkstra d;
    Vertex startVertex=null,endVertex=null;
    List<Vertex> listVertex=null;
    boolean show_textArea=false;

     @FXML
    private ImageView custemImageView;
     
    @FXML
    private JFXComboBox<String> from_Combox;

    @FXML
    private JFXComboBox<String> to_Combox;
    
    @FXML
    private JFXButton excuteButton;
    
    @FXML
    private ImageView espritLogo;

    @FXML
    void showResult(ActionEvent event) {
        
        System.out.println("from:   "+from_Combox.getValue());
        System.out.println("to:    "+to_Combox.getItems().indexOf(to_Combox.getValue()));
        
        switch (from_Combox.getValue()){
            case "user 1": startVertex=listVertex.get(0);break;
            case "user 2": startVertex=listVertex.get(1);break;
            case "user 3": startVertex=listVertex.get(1);break;
            case "user 4": startVertex=listVertex.get(2);break;
            case "user 5": startVertex=listVertex.get(3);break;
            case "user 6": startVertex=listVertex.get(4);break;
        }


        switch (to_Combox.getValue()){
            case "user 1": endVertex=listVertex.get(0);break;
            case "user 2": endVertex=listVertex.get(1);break;
            case "user 3": endVertex=listVertex.get(1);break;
            case "user 4": endVertex=listVertex.get(2);break;
            case "user 5": endVertex=listVertex.get(3);break;
            case "user 6": endVertex=listVertex.get(4);break;
        }
        
        System.out.println("start router "+startVertex.getName());
        System.out.println("end router "+endVertex.getName());
        
        d = new Dijkstra();
        d.computePaths(startVertex);     
	//System.out.println("Distance to " + endVertex + ": " + endVertex.minDistance);
	List<Vertex> path = d.getShortestPathTo(endVertex);
	System.out.println("Path: " + path);
        
         
        Stage dialog = new Stage();
dialog.initStyle(StageStyle.UTILITY);
dialog.initModality(Modality.APPLICATION_MODAL);
dialog.setMinHeight(200);
dialog.setMinWidth(400);
dialog.setMaxWidth(400);
VBox vbox=new VBox();
Button details=new Button("Show more details");
TextArea textArea =new TextArea();
textArea.setVisible(show_textArea);
textArea.setText(Log.show());



details.setOnAction(event1->{
    show_textArea=!show_textArea;
    textArea.setVisible(show_textArea);
});


vbox.getChildren().add(new Text("Distance to " + endVertex + ": " + endVertex.minDistance));
vbox.getChildren().add(new Text("Path: " + path));
vbox.getChildren().add(textArea);
vbox.getChildren().add(details);

Scene scene = new Scene(vbox);
dialog.setScene(scene);


scene.heightProperty().addListener(listener->{
textArea.setMinHeight(scene.getHeight()-100);
});





dialog.showAndWait();
        
         /*
        Stage dialogStage = new Stage();
        dialogStage.initModality(Modality.WINDOW_MODAL);
        dialogStage.setWidth(400);
        dialogStage.setHeight(200);
        
        Label exitLabel = new Label("Are you sure you want to exit?");
        exitLabel.setAlignment(Pos.BASELINE_CENTER);

                Button yesBtn = new Button("Yes");
                yesBtn.setOnAction((event1) -> {
                    dialogStage.close();
                });
                Button noBtn = new Button("No");

                noBtn.setOnAction((even1)->  dialogStage.close());

                HBox hBox = new HBox();
                hBox.setAlignment(Pos.BASELINE_CENTER);
                hBox.setSpacing(40.0);
                hBox.getChildren().addAll(yesBtn, noBtn);

                VBox vBox = new VBox();
                vBox.setSpacing(40.0);
                vBox.getChildren().addAll(exitLabel, hBox);

                dialogStage.setScene(new Scene(vBox));
                dialogStage.show();
            */

        
        

    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
               
        custemImageView.setImage( new Image(Graph_application_dj_kstra.class.getResourceAsStream("/graph_application_dj_kstra/graph.png")));
        espritLogo.setImage(new Image(Graph_application_dj_kstra.class.getResourceAsStream("/graph_application_dj_kstra/espritLogo.jpg")));
        
        from_Combox.getItems().addAll(user_names);
        from_Combox.getSelectionModel().selectFirst();
        
        to_Combox.getItems().addAll(user_names);
        to_Combox.getSelectionModel().selectFirst();
        
        
        listVertex=new ArrayList<>();
        //listVertex.addAll(Arrays.asList(v0,v1,v2,v3));
        for (String name : router_names) {
            listVertex.add(new Vertex(name));
            }


        listVertex.get(0).adjacencies = new Edge[]{ new Edge(listVertex.get(1), 2),new Edge(listVertex.get(5), 1),new Edge(listVertex.get(4), 5) };
        listVertex.get(1).adjacencies = new Edge[]{ new Edge(listVertex.get(0), 2),new Edge(listVertex.get(5), 1),new Edge(listVertex.get(2), 2) };
        listVertex.get(2).adjacencies = new Edge[]{ new Edge(listVertex.get(1), 2),new Edge(listVertex.get(5), 2),new Edge(listVertex.get(3), 13) };
        listVertex.get(3).adjacencies = new Edge[]{ new Edge(listVertex.get(2), 13),new Edge(listVertex.get(5), 5),new Edge(listVertex.get(4), 7) };
        listVertex.get(4).adjacencies = new Edge[]{ new Edge(listVertex.get(3), 7),new Edge(listVertex.get(5), 3),new Edge(listVertex.get(0), 5) };
        listVertex.get(5).adjacencies = new Edge[]{ new Edge(listVertex.get(0), 1),new Edge(listVertex.get(1), 1),new Edge(listVertex.get(2), 2),new Edge(listVertex.get(3), 5),new Edge(listVertex.get(4), 3) };

    
        
        
        
        
    
    }    
    
    
    
}
