package com.project.labyrithe;

import java.util.Scanner;

//NB: 1
public class Tuile_Rotation extends Tuile{

    Tuile_Rotation(int id, int rotation, String tresor, Integer x_coord, Integer y_coord){
        Rotation(rotation);
        super.id = id;
        super.tresor = tresor;
        super.x_coord = x_coord;
        super.y_coord = y_coord;
        super.tuile_rotation = true;
    }

    public void Rotation(int rotation){

        super.rotation = rotation;
        switch(rotation) {
            case 0:
                super.haut = true;
                super.droit = true;
                super.bas = false;
                super.gauche = false;
                break;

            case 1:
                super.haut = false;
                super.droit = true;
                super.bas = true;
                super.gauche = false;
                break;

            case 2:
                super.haut = false;
                super.droit = false;
                super.bas = true;
                super.gauche = true;
                break;

            case 3:
                super.haut = true;
                super.droit = false;
                super.bas = false;
                super.gauche = true;
                break;
        }
    }

    public void Action(Jeu jeu){
    	
    	Scanner scanner = new Scanner(System.in);

    	int input_ligne = -1;
        int input_colonne = -1;
        int rotation = -1;

        while(input_ligne % 2 == 1 && input_colonne%2 == 1) {
        	System.out.println("Selectionnez une coordonnee ligne mobile");
            input_ligne = scanner.nextInt();
            
            System.out.println("Selectionnez une coordonnee colonne mobile");
            input_colonne = scanner.nextInt();
        }
        
        while(rotation < 0 && rotation > 3) {
        	System.out.println("Selectionnez une rotation valide (0 a 3");
            rotation = scanner.nextInt();
        }
    	
    	jeu.plateau_jeu.plateau[input_ligne-1][input_colonne-1].Rotation(rotation);
    	
    	//scanner.close();
    }
}
