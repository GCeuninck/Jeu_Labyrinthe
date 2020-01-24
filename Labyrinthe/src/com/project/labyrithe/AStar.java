package com.project.labyrithe;

import java.util.*;

public class AStar {
    private final List<Noeud> open;
    private final List<Noeud> closed;
    private final List<Noeud> chemin;
    private final Plateau plateau_jeu;
    private Noeud courant;
    private final Integer x_depart;
    private final Integer y_depart;
    private Integer x_fin, y_fin;
 
    // Noeud class for convienience
    @SuppressWarnings("rawtypes")
	static class Noeud implements Comparable {
        public Noeud parent;
        public Integer x, y;
        public double g; // Score de depart a un point d arrivee  du chemin genere
        public double h; // Score estime du depart a fin
        Noeud(Noeud parent, Integer x, Integer y, double g, double h) {
            this.parent = parent;
            this.x = x;
            this.y = y;
            this.g = g;
            this.h = h;
       }
       // Compare by f value (g + h)
       @Override
       public int compareTo(Object o) {
           Noeud that = (Noeud) o;
           return (int)((this.g + this.h) - (that.g + that.h));
       }
   }
 
    AStar(Plateau plateau, Integer x_depart, Integer y_depart) {
        this.open = new ArrayList<>();
        this.closed = new ArrayList<>();
        this.chemin = new ArrayList<>();
        this.plateau_jeu = plateau;
        this.courant = new Noeud(null, x_depart, y_depart, 0, 0);
        this.x_depart = x_depart;
        this.y_depart = y_depart;
    }
    /*
    ** Finds path to xend/yend or returns null
    **
    ** @param (int) xend coordinates of the target position
    ** @param (int) yend
    ** @return (List<Noeud> | null) the path
    */
    public List<Noeud> findPathTo(Integer x_fin, Integer y_fin) {
        this.x_fin = x_fin;
        this.y_fin = y_fin;
        this.closed.add(this.courant);
        addNeigborsToOpenList();
        while (this.courant.x != this.x_fin || this.courant.y != this.y_fin) {
            if (this.open.isEmpty()) { // Nothing to examine
                return null;
            }
            this.courant = this.open.get(0); // get first Noeud (lowest f score)
            this.open.remove(0); // remove it
            this.closed.add(this.courant); // and add to the closed
            addNeigborsToOpenList();
        }
        this.chemin.add(0, this.courant);
        while (this.courant.x != this.x_depart || this.courant.y != this.y_depart) {
            this.courant = this.courant.parent;
            this.chemin.add(0, this.courant);
        }
        return this.chemin;
    }
    /*
    ** Looks in a given List<> for a Noeud
    **
    ** @return (bool) NeightborInListFound
    */
    private static boolean findNeighborInList(List<Noeud> array, Noeud noeud) {
        return array.stream().anyMatch((n) -> (n.x == noeud.x && n.y == noeud.y));
    }
    /*
    ** Calulate distance between this.now and xend/yend
    **
    ** @return (int) distance
    */
    private double distance(int dx, int dy) {
    	
    	double h = Math.abs(this.courant.x + dx - this.x_fin) + Math.abs(this.courant.y + dy - this.y_fin);
        return  h; // return h  "Manhattan distance"
   
    }
    @SuppressWarnings("unchecked")
	private void addNeigborsToOpenList() {
        Noeud noeud;
        for (int x = -1; x <= 1; x++) {
            for (int y = -1; y <= 1; y++) {                
                noeud = new Noeud(this.courant, this.courant.x + x, this.courant.y + y, this.courant.g, this.distance(x, y));
                if ((x != 0 || y != 0) // not this.now
                    && this.courant.x + x >= 0 && this.courant.x + x < this.plateau_jeu.plateau.length // check maze boundaries
                    && this.courant.y + y >= 0 && this.courant.y + y < this.plateau_jeu.plateau[0].length
                    && !findNeighborInList(this.open, noeud) && !findNeighborInList(this.closed, noeud) // if not already done
                	&& this.plateau_jeu.plateau[this.courant.x + x][this.courant.y + y].estAccessible(plateau_jeu, x, y)) {  // check if square is walkable
	                	noeud.g = noeud.parent.g + 1.; // Horizontal/vertical cost = 1.0
	 
	                    this.open.add(noeud);
                }
            }
        }
        Collections.sort(this.open);
    }
}