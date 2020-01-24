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
    
    public void Echange(Joueur J1, Joueur J2) {
    	Int_Tuile pos_temp = J1.position;
    	
    	if(J2.special_echange) {
	    	J1.position = J2.position;
			J2.position = pos_temp;
			J2.special_echange = false;
    	}
    }

    //Echange la place d'un joueur avec un autre
    public void Action(Jeu jeu){
    	
    	Scanner scanner = new Scanner(System.in);

        int inputJ1 = 0;
        int inputJ2 = 0;

        while(inputJ1 < 1 || inputJ1 > 4 ) {
        	System.out.println("Selectionnez un joueur (Bleu = 1, Vert = 2, Jaune = 3, Rouge = 4)");
            inputJ1 = scanner.nextInt();
        }
        
        while((inputJ2 < 1 || inputJ2 > 4) || inputJ2 == inputJ1) {
        	System.out.println("Selectionnez un autre joueur (Bleu = 1, Vert = 2, Jaune = 3, Rouge = 4)");
            inputJ2 = scanner.nextInt();
        }

        if(inputJ1 == 1 && jeu.J1.special_echange) {
        	
        	jeu.J1.special_echange = false;        	
        	switch(inputJ2) {
        		case 2: 
        			Echange(jeu.J1, jeu.J2);
        			break;
        		case 3: 
        			Echange(jeu.J1, jeu.J3);
        			break;
        		case 4: 
        			Echange(jeu.J1, jeu.J4);
        			break;
        		default: break;
        	}
        }
        else if(inputJ1 == 2 && jeu.J2.special_echange) {

        	jeu.J2.special_echange = false;
        	switch(inputJ2) {
        		case 1: 
        			Echange(jeu.J2, jeu.J1);
        			break;
        		case 3: 
        			Echange(jeu.J2, jeu.J3);
        			break;
        		case 4: 
        			Echange(jeu.J2, jeu.J4);
        			break;
        		default: break;
        	}
        }
        else if(inputJ1 == 3 && jeu.J3.special_echange) {
        	
        	jeu.J3.special_echange = false;
        	switch(inputJ2) {
        		case 1: 
        			Echange(jeu.J3, jeu.J1);
        			break;
        		case 2: 
        			Echange(jeu.J3, jeu.J2);
        			break;
        		case 4: 
        			Echange(jeu.J3, jeu.J4);
        			break;
        		default: break;
        	}
        }
        else if(inputJ1 == 4 && jeu.J4.special_echange) {
        	
        	jeu.J4.special_echange = false;
        	switch(inputJ2) {
        		case 1: 
        			Echange(jeu.J4, jeu.J1);
        			break;
        		case 2: 
        			Echange(jeu.J4, jeu.J2);
        			break;
        		case 3: 
        			Echange(jeu.J4, jeu.J3);
        			break;
        		default: break;
        	}
        }
      
        //scanner.close();
    }

}
