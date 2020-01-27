package com.project.labyrithe;
import java.util.Scanner;

public class Jeu {
    protected Plateau plateau_jeu;
    protected Int_Tuile tuile_mobile;
    protected Joueur J1, J2, J3, J4;
    protected Cartes cartes;

    Jeu(Plateau plateau, Joueur J1, Joueur J2, Joueur J3, Joueur J4, Cartes cartes){
        this.plateau_jeu = plateau;
        this.J1 = J1;
        this.J2 = J2;
        this.J3 = J3;
        this.J4 = J4;
        this.cartes = cartes;
    }

    public static void main(String[] args) {
        Plateau plateau = new Plateau();
        Joueur J1 = new Joueur(plateau, "Bleu", 0, 0);
        Joueur J2 = new Joueur(plateau, "Vert", 0, 6);
        Joueur J3 = new Joueur(plateau, "Jaune", 6, 6);
        Joueur J4 = new Joueur(plateau, "Rouge", 6, 0);
        Cartes cartes = new Cartes();

        Jeu jeu = new Jeu(plateau, J1, J2, J3, J4, cartes);

        //Tirer les cartes pour les joueurs
        jeu.J1.tirerCartes(jeu.cartes);
        jeu.J2.tirerCartes(jeu.cartes);
        jeu.J3.tirerCartes(jeu.cartes);
        jeu.J4.tirerCartes(jeu.cartes);

        jeu.J1.afficherJoueur();
        jeu.J1.afficherCartes();
                
        jeu.J2.afficherJoueur();
        jeu.J2.afficherCartes();
        
        jeu.J3.afficherJoueur();
        jeu.J3.afficherCartes();
        
        jeu.J4.afficherJoueur();
        jeu.J4.afficherCartes();

        jeu.tuile_mobile = jeu.plateau_jeu.creerTuile(Integer.parseInt(jeu.plateau_jeu.liste_tuiles.getFirst()[0]), jeu.plateau_jeu.liste_tuiles.getFirst()[2], 0, jeu.plateau_jeu.liste_tuiles.getFirst()[1], null, null);

        plateau.afficherPlateau();
        System.out.println("Tuile restante : " + jeu.tuile_mobile);       

        //jeu.testDeplacement(J1, J2, J3, J4);

        //jeu.testTresor();

        while(!J1.victoire && !J2.victoire && !J3.victoire && !J4.victoire){

            if(!J1.victoire && !J2.victoire && !J3.victoire && !J4.victoire){
                jeu.tourJoueur(J1);
            }
            if(!J1.victoire && !J2.victoire && !J3.victoire && !J4.victoire){
                jeu.tourJoueur(J2);
            }
            if(!J1.victoire && !J2.victoire && !J3.victoire && !J4.victoire){
                jeu.tourJoueur(J3);
            }
            if(!J1.victoire && !J2.victoire && !J3.victoire && !J4.victoire){
                jeu.tourJoueur(J4);
            }

        }
    }

    void tourJoueur(Joueur joueur){
    	
    	System.out.println("Tour " + joueur.couleur);
    	
    	joueur.afficherJoueur();
    	joueur.afficherCartes();
    	
        Scanner scanner = new Scanner(System.in);

        boolean inputLine;
        boolean inputSelect;

        int inputL = -1;
        int inputS = -1;
        int inputN = -1;
        int inputR = -1;
        int inputX = -1;
        int inputY = -1;

        System.out.println("Selection - Ligne : 1 | Colonne : 2");
        while(inputL != 1 && inputL != 2) {
            inputL = scanner.nextInt();
        }
        if(inputL == 1) {inputLine = true;}
        else {inputLine = false;}

        System.out.println("Selection - Croissant : 1 | Decroissant : 2");
        while(inputS != 1 && inputS != 2){
            inputS = scanner.nextInt();
        }
        if(inputS == 1) {inputSelect = true;}
        else {inputSelect = false;}

        System.out.println("Selection - Numero de ligne/colonne a bouger: ");
        while(inputN != 2 && inputN != 4 && inputN != 6){
            inputN = scanner.nextInt();
        }

        System.out.println("Selection - Rotation à appliquer à la tuile restante: ");
        while(inputR != 0 && inputR != 1 && inputR != 2 && inputR != 3){
            inputR = scanner.nextInt();
        }

        this.tuile_mobile = this.plateau_jeu.deplacementPlateau(this , inputLine, inputSelect, inputN, this.tuile_mobile, inputR);
        this.plateau_jeu.afficherPlateau();
        System.out.println("Tuile restante: " + this.tuile_mobile);

        System.out.println("Entrer la case de destination (ligne ; colonne): ");

        System.out.println("Entrer Ligne (1 a 7) : ");
        inputX = scanner.nextInt();
        System.out.println("Entrer Colonne (1 a 7) : ");
        inputY = scanner.nextInt();

        while (!joueur.deplacementJoueur(this.plateau_jeu, inputX - 1, inputY - 1)){
            System.out.println("DEPLACEMENT IMPOSSIBLE");

            System.out.println("Entrer Ligne (1 a 7) : ");
            inputX = scanner.nextInt();

            System.out.println("Entrer Colonne (1 a 7) : ");
            inputY = scanner.nextInt();
        }

        joueur.actionCheck(this);
        joueur.objectifCheck();
        joueur.updateJoueur();
        joueur.afficherJoueur();
    }

    void testDeplacement(Joueur J1, Joueur J2, Joueur J3, Joueur J4){
        this.tuile_mobile = this.plateau_jeu.deplacementPlateau(this, true, true, 2, this.tuile_mobile, 0);         //Deplace 2e ligne vers droite
        this.plateau_jeu.afficherPlateau();
        System.out.println("Tuile restante: " + this.tuile_mobile);

        System.out.println();

        this.tuile_mobile = this.plateau_jeu.deplacementPlateau(this, true, false, 2, this.tuile_mobile, 3);        //Deplace 2e ligne vers gauche
        this.plateau_jeu.afficherPlateau();
        System.out.println("Tuile restante: " + this.tuile_mobile);

        System.out.println();

        this.tuile_mobile = this.plateau_jeu.deplacementPlateau(this, false, true, 2, this.tuile_mobile, 2);        //Deplace 2e colonne vers bas
        this.plateau_jeu.afficherPlateau();
        System.out.println("Tuile restante: " + this.tuile_mobile);

        System.out.println();

        this.tuile_mobile = this.plateau_jeu.deplacementPlateau(this, false, false, 2, this.tuile_mobile, 0);        //Deplace 2e colonne vers haut
        this.plateau_jeu.afficherPlateau();
        System.out.println("Tuile restante: " + this.tuile_mobile);
    }

    void testTresor(){
        //TEST 1 ELEMENT A RECUP
        this.J1.cartes.add("Epee");
        System.out.println("Tresor (2,0): " + this.plateau_jeu.plateau[2][0].getTresor());
        System.out.println("J1 Cartes: " + this.J1.cartes);
        this.J1.afficherJoueur();
        this.J1.deplacementJoueur(this.plateau_jeu, 2, 0);
        this.J1.objectifCheck();
        this.J1.afficherJoueur();
        System.out.println("J1 Cartes: " + this.J1.cartes);
        System.out.println();System.out.println();System.out.println();
    }

}
