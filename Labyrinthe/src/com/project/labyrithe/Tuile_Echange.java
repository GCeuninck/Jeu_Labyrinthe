package com.project.labyrithe;

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

    //Echange la place avec un autre joueur
    public void actionEchange(Joueur J1, Joueur J2){
    	
    	Integer x_coord_temp = J1.x_coord;
    	Integer y_coord_temp = J1.y_coord;
    	Int_Tuile position_temp = J1.position;
    	
    	J1.x_coord = J2.x_coord;
    	J1.y_coord = J2.y_coord;
    	J1.position = J2.position;
    	
    	J2.x_coord = x_coord_temp;
    	J2.y_coord = y_coord_temp;
    	J2.position = position_temp;
    }

}
