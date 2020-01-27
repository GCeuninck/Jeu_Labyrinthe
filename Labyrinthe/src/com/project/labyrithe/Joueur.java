package com.project.labyrithe;
import java.util.*;

import com.project.labyrithe.AStar.Noeud;

public class Joueur {
    boolean victoire;
    String couleur;
    Int_Tuile position;
    Integer x_coord, y_coord;
    Integer id_depart;
    LinkedList<String> cartes;


    Joueur(Plateau plateau_jeu, String couleur, Integer x_coord, Integer y_coord){
        this.victoire = false;
        this.couleur = couleur;
        this.id_depart = plateau_jeu.plateau[x_coord][y_coord].getId();
        this.position = plateau_jeu.plateau[x_coord][y_coord];
        this.x_coord = x_coord;
        this.y_coord = y_coord;
        this.cartes = new LinkedList<String>();
    }
    
    public void afficherJoueur(){

    	System.out.println(this.couleur + " : " + this.position + " x,y :" + (this.x_coord + 1) + (this.y_coord + 1));
    }
    
    boolean deplacementJoueur(Plateau plateau_jeu, Integer x, Integer y) {

        boolean deplacementFait = false;
    	AStar as = new AStar(plateau_jeu, this.x_coord, this.y_coord);
    	
    	List<Noeud> chemin = as.findPathTo(x, y);
   
    	if (chemin != null) {
    		this.x_coord = x;
    		this.y_coord = y;
    		this.position = plateau_jeu.plateau[x][y];
    		deplacementFait = true;
    	}

    	return deplacementFait;
    }

    void tirerCartes(Cartes paquet_cartes){

        this.cartes.add(paquet_cartes.getCarte());
        this.cartes.add(paquet_cartes.getCarte());
        this.cartes.add(paquet_cartes.getCarte());
    }

    void objectifCheck(){

        String tresor_case = this.position.getTresor();

        if(this.cartes.contains(tresor_case)){
            this.cartes.remove(tresor_case);
        }
    }
    
    public boolean objectifCompleted() {
    	
    	boolean res = false;
    	
    	if(this.cartes.isEmpty()) {
    		res = true;
    		System.out.println("Voictoire " + this.couleur + "!");
    	}
    	
    	return res;
    }

    void actionCheck(Jeu jeu){
    	
        if(this.position.isSpecial()) {
        	this.position.Action(jeu);
        }
    }

    public void updateJoueur() {
    	
    	this.x_coord = this.position.getCoord()[0];
    	this.y_coord = this.position.getCoord()[1];
    }
    
    void replaceJoueur(Plateau plateau_jeu, boolean ligne, boolean sens, int num) {
    	
    	if (ligne && sens) {
            
            this.x_coord = num-1;
            this.y_coord = 0;
            System.out.println("G-->D");
        } else if (ligne && !sens) {
        	
        	this.x_coord = 6;
            this.y_coord = num-1;
            System.out.println("D-->G");
        } else if (!ligne && sens){
         
        	this.x_coord = 0;
            this.y_coord = num-1;
            System.out.println("H-->B");
        } else if (!ligne && !sens){
       
        	this.x_coord = 6;
            this.y_coord = num-1;
            System.out.println("B-->H");
        }
    	
    	this.position = plateau_jeu.plateau[this.x_coord][this.y_coord];
    }
}
