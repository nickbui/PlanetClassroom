/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;



import Model.GameDialog;
import Model.LevelData;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.AnimationTimer;



import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;

import javafx.fxml.Initializable;
import javafx.geometry.Point2D;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;


import javafx.scene.input.KeyCode;


import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;


/**
 * FXML Controller class
 *
 * @author Nicholas
 */
public class SpaceGameMainController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML private AnchorPane anchorPane;
    boolean turned = false;
    private Point2D playerVelocity = new Point2D(0,0);
    private  boolean canJump = true;
    private HashMap<KeyCode, Boolean> keys = new HashMap<KeyCode,Boolean>();
    private ArrayList<Node> platforms = new ArrayList<Node>();
    private ArrayList<Node> quizzes = new ArrayList<Node>();
    
    private Node player;
    private int levelWidth;
    
    private Pane appRoot = new Pane();
    private Pane gameRoot = new Pane();
    private Pane uiRoot = new Pane();
    private Pane quizRoot = new Pane();

    private boolean dialogEvent = false, running = true;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        initContent();
        Scene scene = new Scene(appRoot);
        scene.setOnKeyPressed(event ->keys.put(event.getCode(), true));
        scene.setOnKeyReleased(event ->keys.put(event.getCode(), false));
        
        Stage primaryStage = new Stage();
        primaryStage.setScene(scene);
        primaryStage.show();
        
        AnimationTimer timer = new AnimationTimer(){
            @Override
            public void handle(long now) {
                if(running){
                    try {
                        update();
                    } catch (IOException ex) {
                        Logger.getLogger(SpaceGameMainController.class.getName()).log(Level.SEVERE, null, ex);
                       }
                    }
                if(dialogEvent){
                    dialogEvent = false;
                    keys.keySet().forEach(key -> keys.put(key, false));
                    
                    GameDialog dialog = new GameDialog();
                    
                    dialog.setOnCloseRequest(event ->{
                        if(dialog.isCorrect()){
                            System.out.println("Correct");
                        }
                        else{
                            System.out.println("Incorrect");
                        }
                        running = true;
                    });
                    dialog.open();
                }
                }
                
        };
        timer.start();
    }
   
    public void update() throws IOException{
        if(isPressed(KeyCode.SPACE) && player.getTranslateY() >= 5){
            jumpPlayer();
        }
        if(isPressed(KeyCode.LEFT)&& player.getTranslateX() >=5){
            movePlayerX(-5);
        }
        if(isPressed(KeyCode.RIGHT)&& player.getTranslateX() + 40 <= levelWidth -5){
            movePlayerX(5);
        }
        if(playerVelocity.getY()<10){
            playerVelocity = playerVelocity.add(0,1);
        }
        movePlayerY((int)(playerVelocity.getY()));
        
         for(Node quiz : quizzes){
            if(player.getBoundsInParent().intersects(quiz.getBoundsInParent())){
                quiz.getProperties().put("alive", false);
                dialogEvent= true;
                running =false;
            }
        }
         for(Iterator<Node> it = quizzes.iterator(); it.hasNext();){
             Node quiz = it.next();
             if(!(Boolean)quiz.getProperties().get("alive")){
                 it.remove();
                 gameRoot.getChildren().remove(quiz);
             }
         }
    }
    
    private void initContent(){
        Rectangle bg = new Rectangle(1280, 720);
        levelWidth = LevelData.LEVEL1[0].length()* 60;
        
        for(int i = 0; i< LevelData.LEVEL1.length; i++){
            String line = LevelData.LEVEL1[i];
            for(int j = 0; j < line.length(); j++) {
                switch(line.charAt(j)){
                    case '0':
                        break;
                    case '1':
                        Node platform = createEntity(j*60,i*60,60,60,Color.BROWN);
                        platforms.add(platform);
                        break;
                    case '2':
                        Node quiz = createEntity(j*60,i*60, 60, 60, Color.GOLD);
                        quizzes.add(quiz);
                        break;
                }
            }
        }
        
        player = createEntity(0,600,40,40, Color.BLUE);
        
        
        player.translateXProperty().addListener((obs,old,newValue) -> {
            int offset = newValue.intValue();
            if(offset > 640 && offset < levelWidth -640){
                gameRoot.setLayoutX(-(offset-640));
            }
        });
        appRoot.getChildren().addAll(bg,gameRoot, uiRoot);
    }
    
    private Node createEntity(int x, int y, int w, int h, Color color){
        Rectangle entity = new Rectangle(w,h);
        entity.setTranslateX(x);
        entity.setTranslateY(y);
        entity.setFill(color);
        entity.getProperties().put("alive", true);
        
        gameRoot.getChildren().add(entity);
        return entity;
    }
    
    private void movePlayerX(int value) throws IOException{
        boolean movingRight = value >0;
        for(int i =0; i<Math.abs(value); i++){
            for(Node platform: platforms){
                if(player.getBoundsInParent().intersects(platform.getBoundsInParent())){
                    if(player.getTranslateX() + 40 == platform.getTranslateX()){
                        return;
                    }
                }
            }
            player.setTranslateX(player.getTranslateX() + (movingRight ? 1: -1));
                
        }
    }
    
    private void movePlayerY(int value){
        boolean movingDown = value>0;
        
        for(int i =0;i < Math.abs(value); i++){
            for(Node platform: platforms){
                if(player.getBoundsInParent().intersects(platform.getBoundsInParent())){
                    if(movingDown){
                        if(player.getTranslateY() + 40 == platform.getTranslateY()){
                            player.setTranslateY(player.getTranslateY()-1);
                            canJump = true;
                            return;
                        }
                    }
                    else{
                        if(player.getTranslateY() == platform.getTranslateY()+60) {
                            return;
                        }
                    }
                }
            }
            player.setTranslateY(player.getTranslateY()+(movingDown ? 1 : -1));
        }
    }
    
    private void jumpPlayer(){
        if(canJump){
            playerVelocity = playerVelocity.add(0,-30);
            canJump=false;
        }
    }
    
    private boolean isPressed(KeyCode key){
        return keys.getOrDefault(key, false);
    }
    
    public void quizScreen() throws IOException{
         FXMLLoader scienceLoader = new FXMLLoader(getClass().getResource("/View/ScienceGameView.fxml"));
         Parent scienceRoot = (Parent) scienceLoader.load();
        quizRoot.getChildren().add(scienceRoot);
    }
    
//   @FXML public void handle(KeyEvent event) throws IOException {
//       System.out.println(event.getCode());
//       Timeline t = new Timeline();
//       t.setCycleCount(1);
//       switch(event.getCode()){
//           case RIGHT:
//               t.getKeyFrames().add(new KeyFrame(Duration.millis(100),
//               e ->{
//                    spaceman.setX(spaceman.getX()+5);
//                    File file = new File("src/View/images/spaceman/spaceman walking 3.png");
//                    Image image = new Image(file.toURI().toString());
//                    spaceman.setImage(image);
//                    spaceman.setFitHeight(63);
//                    spaceman.setFitWidth(64);
//               }));
//                t.getKeyFrames().add(new KeyFrame(Duration.millis(400),
//               e ->{
//                      File runFile = new File("src/View/images/spaceman/spacemanstill.png");
//                      Image runImage = new Image(runFile.toURI().toString());
//                       spaceman.setImage(runImage);
//               }));
//               event.consume();
//               t.play();
//               if(spaceman.getBoundsInParent().intersects(card.getBoundsInParent())){
//              
//                   System.out.println("We touched");
//                   
//               }
//               turned = false;
//               break;
//           case LEFT:
//               t.getKeyFrames().add(new KeyFrame(Duration.millis(100),
//               e ->{
//                    spaceman.setX(spaceman.getX()-5);
//                    File file = new File("src/View/images/spaceman/spaceman walking 3 flip.png");
//                    Image image = new Image(file.toURI().toString());
//                    spaceman.setImage(image);
//                    spaceman.setFitHeight(63);
//                    spaceman.setFitWidth(64);
//               }));
//                t.getKeyFrames().add(new KeyFrame(Duration.millis(400),
//               e ->{
//                      File runFile = new File("src/View/images/spaceman/spacemanstill left.png");
//                      Image runImage = new Image(runFile.toURI().toString());
//                      spaceman.setImage(runImage);
//               }));
//               event.consume();
//               t.play();
//               turned= true;
//               break;
//           case UP:
//               if(turned == false) {
//               t.getKeyFrames().add(new KeyFrame(Duration.millis(300),
//               e ->{
//                        spaceman.setY(spaceman.getY()-50);
//                        File file = new File("src/View/images/spaceman/Spacemanwalking 2.png");
//                        Image image = new Image(file.toURI().toString());
//                        spaceman.setImage(image);
//                        spaceman.setFitHeight(63);
//                        spaceman.setFitWidth(64);
//               }));
//                t.getKeyFrames().add(new KeyFrame(Duration.millis(500),
//               e ->{
//                        spaceman.setY(spaceman.getY()+50); 
//                         File runFile = new File("src/View/images/spaceman/spacemanstill.png");
//                        Image runImage = new Image(runFile.toURI().toString());
//                        spaceman.setImage(runImage);
//               }));
//               event.consume();
//               t.play();
//               
//       } else if(turned == true) {
//            t.getKeyFrames().add(new KeyFrame(Duration.millis(300),
//               e ->{
//                        spaceman.setY(spaceman.getY()-50);
//                        File file = new File("src/View/images/spaceman/spaceman walking 3 flip.png");
//                        Image image = new Image(file.toURI().toString());
//                        spaceman.setImage(image);
//                        spaceman.setFitHeight(63);
//                        spaceman.setFitWidth(64);
//               }));
//                t.getKeyFrames().add(new KeyFrame(Duration.millis(500),
//               e ->{
//                        spaceman.setY(spaceman.getY()+50); 
//                         File runFile = new File("src/View/images/spaceman/spacemanstill left.png");
//                        Image runImage = new Image(runFile.toURI().toString());
//                        spaceman.setImage(runImage);
//               }));
//               event.consume();
//               t.play();
//       }
//       } 
//    }
   

     
}
