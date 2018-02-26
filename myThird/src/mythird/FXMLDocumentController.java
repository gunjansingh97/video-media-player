/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mythird;


import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.beans.binding.Bindings;
import javafx.beans.property.DoubleProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.FileChooser;
import javafx.util.Duration;

/**
 *
 * @author gunjan
 */
public class FXMLDocumentController implements Initializable {
    
    
   MediaPlayer mediaplayer;
    Media media;
    @FXML
    private Slider slide;
    @FXML
    private Slider mainslide;
    
    @FXML
    MediaView mediaview;
    @FXML
    private Label label;
    private String path1;
    @FXML
    private void handleButtonAction(ActionEvent event) {
       FileChooser filechooser=new FileChooser();
       FileChooser.ExtensionFilter extensionfilter =  new FileChooser.ExtensionFilter("select file(*.mp3,*.mp4,*.vlc)", "*.mp4","*.vlc","*.mp3");
       filechooser.getExtensionFilters().add(extensionfilter);
       File file=filechooser.showOpenDialog(null);
       path1=file.toURI().toString();
       if(path1!=null){
           media =new Media(path1);
       mediaplayer=new MediaPlayer(media);
       mediaview.setMediaPlayer(mediaplayer);
      DoubleProperty width= mediaview.fitWidthProperty();
      DoubleProperty height= mediaview.fitHeightProperty();
       width.bind(Bindings.selectDouble(mediaview.sceneProperty(),"width" ));
       height.bind(Bindings.selectDouble(mediaview.sceneProperty(),"height" ));
       mediaplayer.stop();
      slide.setValue(mediaplayer.getVolume()*100);
       slide.valueProperty().addListener(new InvalidationListener() {
               @Override
               public void invalidated(Observable observable) {
                  mediaplayer.setVolume(slide.getValue()/100);
               }
           });
       mainslide.setOnMouseClicked(new EventHandler<MouseEvent>() {
               @Override
               public void handle(MouseEvent event) {
mediaplayer.seek(Duration.seconds(mainslide.getValue()));               }
           });
       mediaplayer.currentTimeProperty().addListener(new ChangeListener<Duration>() {
               @Override
               public void changed(ObservableValue<? extends Duration> observable, Duration oldValue, Duration newValue) {
               mainslide.setValue(newValue.toSeconds());
               }
           });
       
       mediaplayer.stop();
       mediaplayer.play();
       }
               }
    @FXML
    private void playbb(ActionEvent event){
        mediaplayer.play();
        mediaplayer.setRate(1);
    }
    @FXML
    private void pauseb(ActionEvent event){
        mediaplayer.pause();
    }
    @FXML
    private void stopb(ActionEvent event){
        mediaplayer.stop();
    }
    @FXML
    private void slowestb(ActionEvent event){
        mediaplayer.setRate(0.5);
    }
    @FXML
    private void slowerb(ActionEvent event){
        mediaplayer.setRate(0.75);
    }
    @FXML
    private void fasterb(ActionEvent event){
        mediaplayer.setRate(1.5);
    }
    @FXML
    private void fastestb(ActionEvent event){
        mediaplayer.setRate(2.0);
    }@FXML
    private void reloadb(ActionEvent event){
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
