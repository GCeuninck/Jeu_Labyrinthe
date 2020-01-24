package com.project.labyrithe;

public interface Int_Tuile {
	
	
    public void Rotation(int rotation);
    public boolean estAccessible(Plateau plateau_jeu, Integer x, Integer y);
    public boolean[] getAccess();
    public boolean[] getJoueur(Joueur J1, Joueur J2, Joueur J3, Joueur J4);
    public String getTresor();
    public Integer[] getCoord();
    void copieCoord(Int_Tuile tuile);
    public void setCoord(Integer x_coord, Integer y_coord);
    public int getId();
    //public void actionTuile(Plateau plateau_jeu, Joueur joueur);
}
