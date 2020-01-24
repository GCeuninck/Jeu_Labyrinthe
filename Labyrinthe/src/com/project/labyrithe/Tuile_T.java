package com.project.labyrithe;

//NB: 17
public class Tuile_T extends Tuile{

    Tuile_T(int id, int rotation, String tresor, Integer x_coord, Integer y_coord){
        Rotation(rotation);
        super.id = id;
        super.tresor = tresor;
        super.x_coord = x_coord;
        super.y_coord = y_coord;
    }

    public void Rotation(int rotation){

        super.rotation = rotation;
        switch(rotation) {
            case 0:
                super.haut = false;
                super.droit = true;
                super.bas = true;
                super.gauche = true;
                break;

            case 1:
                super.haut = true;
                super.droit = false;
                super.bas = true;
                super.gauche = true;
                break;

            case 2:
                super.haut = true;
                super.droit = true;
                super.bas = false;
                super.gauche = true;
                break;

            case 3:
                super.haut = true;
                super.droit = true;
                super.bas = true;
                super.gauche = false;
                break;
        }
    }
    
    public void Action(Jeu jeu){
    	
    }

}
