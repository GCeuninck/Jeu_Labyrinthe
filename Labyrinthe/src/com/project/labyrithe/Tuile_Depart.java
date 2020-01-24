package com.project.labyrithe;

//NB: 4
public class Tuile_Depart extends Tuile{

    Tuile_Depart(int id, int rotation, String tresor, Integer x_coord, Integer y_coord){
        Rotation(rotation);
        super.id = id;
        super.tresor = tresor;
        super.x_coord = x_coord;
        super.y_coord = y_coord;
        super.tuile_depart = true;
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
    
    public void actionDepart(Joueur J){
    	
    	if(J.id_depart == this.getId() && J.objectifCompleted()){
    		J.victoire = true;
    	}
    }
}
