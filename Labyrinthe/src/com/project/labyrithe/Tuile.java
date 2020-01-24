package com.project.labyrithe;

public abstract class Tuile implements Int_Tuile{
    String tresor = null;
    boolean haut;       //Autorise deplacement du joueur
    boolean bas;
    boolean gauche;
    boolean droit;
    int rotation = 0;   //Permet de connaitre le sens dans lequel on place la tuile dans le labyrinthe
    int id;
    Integer x_coord, y_coord;
    
    boolean tuile_depart = false;
	boolean tuile_echange = false;
	boolean tuile_rotation = false;
    
    public boolean[] getAccess() {
    	
    	boolean res[] = {false, false, false, false};
    	
    	if(this.haut) {
    		res[0] = true;
    	}
    	if(this.droit) {
    		res[1] = true;
    	}
    	if(this.bas) {
    		res[2] = true;
    	}
    	if(this.gauche) {
    		res[3] = true;
    	}
    	
    	return res;
    }
    
    public boolean estAccessible(Plateau plateau_jeu, Integer x, Integer y) {
    	
    	boolean res = false;
    	
    	if(x == 0 && y == -1) {
    		if(this.droit == true && plateau_jeu.plateau[this.x_coord][this.y_coord + 1].getAccess()[3] == true) {
    			res = true;
    		}
    	}
    	else if (x == 0 && y == +1) {
    		if(this.gauche == true && plateau_jeu.plateau[this.x_coord][this.y_coord - 1].getAccess()[1] == true) {
    			res = true;
    		}
    	}
    	else if (x == -1 && y == 0) {
    		if(this.bas == true && plateau_jeu.plateau[this.x_coord + 1][this.y_coord].getAccess()[0] == true) {
    			res = true;
    		}
    	}
    	else if (x == +1 && y == 0) {
    		if(this.haut == true && plateau_jeu.plateau[this.x_coord - 1][this.y_coord].getAccess()[2] == true) {
    			res = true;
    		}
    	}
    	
    	return res;
    }
    
    public boolean getJoueur(Joueur J) {
    	
    	boolean res = false;
    	
    	if(J.position.getId() == this.getId()) {
    		res = true;
    	}
    	
    	return res;
    }

	public String getTresor(){
		return this.tresor;
	}

    public Integer[] getCoord() {
    	
    	Integer[] coord = {null, null};
    	
    	coord[0] = this.x_coord;
    	coord[1] = this.y_coord;
    	
    	return coord;
    }
    
    public void copieCoord(Int_Tuile tuile) {
    	
    	if(tuile != null) {
	    	this.x_coord = tuile.getCoord()[0];
	    	this.y_coord = tuile.getCoord()[1];
    	}else {
    		this.x_coord = null;
    		this.y_coord = null;
    	}
    }
    
    public void setCoord(Integer x_coord, Integer y_coord) {
    	    	
    	this.x_coord = x_coord;
    	this.y_coord = y_coord;
    }

	public int getId(){
    	return this.id;
	}
	
	public boolean isSpecial() {
		
		boolean res = false;
		
		if(this.tuile_depart || this.tuile_echange || this.tuile_rotation) {
			res = true;
		}
		
		return res;
	}

	@Override
	public String toString() {
		return String.format(" -%s:%s : %s,%s (%s)-  ", this.tresor, this.id, this.x_coord, this.y_coord, this.rotation);
	}
}
