/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import main.Main;
import utils.Observer;
import logika.IHra;

/**
 *
 * @author User
 */
public class Mapa extends AnchorPane implements Observer {

    /**
     *
     */
    private IHra hra;
    
    
    private Circle tecka;
    
    public Mapa(IHra hra) {
    this.hra = hra;
    hra.getHerniPlan().registerObserver(this);
    init();
    
    }
    
    private void init() {
         
         
         ImageView obrazekImageView = new ImageView(new Image(Main.class.getResourceAsStream("/zdroje/mapa.jpg"), 300,300,false,true));
        
        
        tecka = new Circle(10, Paint.valueOf("red"));
         
        this.setTopAnchor(tecka, 0.0);
        this.setLeftAnchor(tecka, 0.0);
         
        this.getChildren().addAll(obrazekImageView, tecka);
        update();
    }
    
    
    public void newGame(IHra novaHra) {
    
     
        
        hra.getHerniPlan().removeObserver(this);
        hra = novaHra;
        hra.getHerniPlan().registerObserver(this);
        update();
    }
    
    @Override
    public void update() {
        this.setTopAnchor(tecka, hra.getHerniPlan().getAktualniProstor().getPosTop());
        this.setLeftAnchor(tecka, hra.getHerniPlan().getAktualniProstor().getPosLeft());
    }
}
