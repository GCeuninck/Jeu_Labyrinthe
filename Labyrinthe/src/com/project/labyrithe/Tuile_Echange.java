package com.project.labyrithe;

import java.util.Scanner;

//NB: 1
public class Tuile_Echange extends Tuile{

    Tuile_Echange(int id, int rotation, String tresor, Integer x_coord, Integer y_coord){
        Rotation(rotation);
        super.id = id;
        super.tresor = tresor;
        super.x_coord = x_coord;
        super.y_coord = y_coord;
        super.tuile_echange = true;
    }

    public void Rotation(int rotation){

        super.rotation = rotation;
        switch(rotation) {
            case 0:
                super.haut = true;
                super.droit = false;
                super.bas = false;
                super.gauche = false;
                break;

            case 1:
                super.haut = false;
                super.droit = true;
                super.bas = false;
                super.gauche = false;
                break;

            case 2:
                super.haut = false;
                super.droit = false;
                super.bas = true;
                super.gauche = false;
                break;

            case 3:
                super.haut = false;
                super.droit = false;
                super.bas = false;
                super.gauche = true;
                break;
        }
    }

    //Echange la place d'un joueur avec un autre
    public void Action(Jeu jeu){
    	
    	Joueur J_temp = null;
    	
    	
    	Scanner scanner = new Scanner(System.in);

        int inputJ1 = 0;
        int inputJ2 = 0;

        while(inputJ1 < 1 && inputJ1 > 4 ) {
        	System.out.println("Selectionnez un joueur (Bleu = 1, Vert = 2, Jaune = 3, Rouge = 4)");
            inputJ1 = scanner.nextInt();
        }
        
        while((inputJ2 < 1 && inputJ2 > 4) && inputJ2 == inputJ1) {
        	System.out.println("Selectionnez un autre joueur (Bleu = 1, Vert = 2, Jaune = 3, Rouge = 4)");
            inputJ2 = scanner.nextInt();
        }

        if(inputJ1 == 1) {
        	
        	J_temp = jeu.J1;
        	switch(inputJ2) {
        		case 2: 
        			jeu.J1.x_coord = jeu.J2.x_coord;
        			jeu.J1.y_coord = jeu.J2.y_coord;
        			
        			jeu.J2.x_coord = J_temp.x_coord;
        			jeu.J2.y_coord = J_temp.y_coord;
        			break;
        		case 3: 
        			jeu.J1.x_coord = jeu.J3.x_coord;
        			jeu.J1.y_coord = jeu.J3.y_coord;
        			
        			jeu.J3.x_coord = J_temp.x_coord;
        			jeu.J3.y_coord = J_temp.y_coord;
        			break;
        		case 4: 
        			jeu.J1.x_coord = jeu.J4.x_coord;
        			jeu.J1.y_coord = jeu.J4.y_coord;
        			
        			jeu.J4.x_coord = J_temp.x_coord;
        			jeu.J4.y_coord = J_temp.y_coord;
        			break;
        		default: break;
        	}
        }
        else if(inputJ1 == 2) {
        	
        	J_temp = jeu.J2;
        	switch(inputJ2) {
        		case 1: 
        			jeu.J2.x_coord = jeu.J1.x_coord;
        			jeu.J2.y_coord = jeu.J1.y_coord;
        			
        			jeu.J1.x_coord = J_temp.x_coord;
        			jeu.J1.y_coord = J_temp.y_coord;
        			break;
        		case 3: 
        			jeu.J2.x_coord = jeu.J3.x_coord;
        			jeu.J2.y_coord = jeu.J3.y_coord;
        			
        			jeu.J3.x_coord = J_temp.x_coord;
        			jeu.J3.y_coord = J_temp.y_coord;
        			break;
        		case 4: 
        			jeu.J2.x_coord = jeu.J4.x_coord;
        			jeu.J2.y_coord = jeu.J4.y_coord;
        			
        			jeu.J4.x_coord = J_temp.x_coord;
        			jeu.J4.y_coord = J_temp.y_coord;
        			break;
        		default: break;
        	}
        }
        else if(inputJ1 == 3) {
        	
        	J_temp = jeu.J3;
        	switch(inputJ2) {
        		case 1: 
        			jeu.J3.x_coord = jeu.J1.x_coord;
        			jeu.J3.y_coord = jeu.J1.y_coord;
        			
        			jeu.J1.x_coord = J_temp.x_coord;
        			jeu.J1.y_coord = J_temp.y_coord;
        			break;
        		case 2: 
        			jeu.J3.x_coord = jeu.J2.x_coord;
        			jeu.J3.y_coord = jeu.J2.y_coord;
        			
        			jeu.J2.x_coord = J_temp.x_coord;
        			jeu.J2.y_coord = J_temp.y_coord;
        			break;
        		case 4: 
        			jeu.J3.x_coord = jeu.J4.x_coord;
        			jeu.J3.y_coord = jeu.J4.y_coord;
        			
        			jeu.J4.x_coord = J_temp.x_coord;
        			jeu.J4.y_coord = J_temp.y_coord;
        			break;
        		default: break;
        	}
        }
        else if(inputJ1 == 4) {
        	
        	J_temp = jeu.J4;
        	switch(inputJ2) {
        		case 1: 
        			jeu.J4.x_coord = jeu.J1.x_coord;
        			jeu.J4.y_coord = jeu.J1.y_coord;
        			
        			jeu.J1.x_coord = J_temp.x_coord;
        			jeu.J1.y_coord = J_temp.y_coord;
        			break;
        		case 2: 
        			jeu.J4.x_coord = jeu.J2.x_coord;
        			jeu.J4.y_coord = jeu.J2.y_coord;
        			
        			jeu.J2.x_coord = J_temp.x_coord;
        			jeu.J2.y_coord = J_temp.y_coord;
        			break;
        		case 3: 
        			jeu.J4.x_coord = jeu.J3.x_coord;
        			jeu.J4.y_coord = jeu.J3.y_coord;
        			
        			jeu.J3.x_coord = J_temp.x_coord;
        			jeu.J3.y_coord = J_temp.y_coord;
        			break;
        		default: break;
        	}
        }
      
        //scanner.close();
    }

}
