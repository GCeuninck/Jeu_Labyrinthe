package com.project.labyrithe;
import java.io.*;
import java.util.*;

public class Plateau {

    protected Int_Tuile[][] plateau;
    protected LinkedList<String[]> liste_tuiles;

    Plateau(){
        initialisationTuiles();
        plateau = new Int_Tuile[7][7];
        generationPlateau();
    }

    protected void generationPlateau(){

        int i, j;

        int id = 0;
        String tresor = "null";
        String type_tuile = "";
        int rotation = 0;
        int id_tuile = 0;
        boolean cond;

        //PARCOURS DES CASES FIXES
        for(i = 0; i < plateau.length; i+=2){
            for (j = 0; j < plateau[i].length; j+=2){

                cond = false;

                for(id = 0; id < liste_tuiles.size() && !cond; id++) {

                    if(!liste_tuiles.get(id)[3].equals("null") && !liste_tuiles.get(id)[4].equals("null")){
                        if(Integer.parseInt(liste_tuiles.get(id)[3]) == i && Integer.parseInt(liste_tuiles.get(id)[4]) == j){

                            cond = true;
                            rotation = Integer.parseInt(liste_tuiles.get(id)[5]);
                            id_tuile = Integer.parseInt(liste_tuiles.get(id)[0]);
                            type_tuile = liste_tuiles.get(id)[1];
                            tresor = liste_tuiles.get(id)[2];
                            liste_tuiles.remove(id);
                        }
                    }
                }

                plateau[i][j] = creerTuile(id_tuile, tresor, rotation, type_tuile, i, j);
            }
        }

        //PARCOURS CASES ALEATOIRES
        for(i=0; i < plateau.length; i++) {
            for (j = 0; j < plateau[i].length; j++) {

                if(plateau[i][j] == null) {
                    id = tirageAleatoireID();
                    rotation = tirageAleatoireRot();
                    id_tuile = Integer.parseInt(liste_tuiles.get(id)[0]);
                    type_tuile = liste_tuiles.get(id)[1];
                    tresor = liste_tuiles.get(id)[2];
                    liste_tuiles.remove(id);

                    plateau[i][j] = creerTuile(id_tuile, tresor, rotation, type_tuile, i, j);
                }
            }
        }
    }

    protected void initialisationTuiles(){

        //Récupération des informations des tuiles sous forme de liste
        File file = new File("Repart_Tuiles.csv");
        String ligne;
        liste_tuiles = new LinkedList<String[]>();

        try(BufferedReader br = new BufferedReader(new FileReader(file))){
            while((ligne = br.readLine()) != null){
                liste_tuiles.add(ligne.split(";"));
            }
        }catch (IOException e){
            System.err.format("IOException: %s", e);
        }
        liste_tuiles.removeFirst();
    }

    protected int tirageAleatoireID(){

        Random rint = new Random();
        Integer rnum = rint.nextInt(liste_tuiles.size());

        while(!liste_tuiles.get(rnum)[3].equals("null") && !liste_tuiles.get(rnum)[4].equals("null")){
            rnum = rint.nextInt(liste_tuiles.size());
        }
        return rnum;
    }

    protected int tirageAleatoireRot(){
        Random rint = new Random();
        return rint.nextInt(4);
    }

    protected void afficherPlateau(Jeu jeu){
        boolean[] accesTuile;
        int i;
        int j;

        for(i=0; i < plateau.length; i++){
            for(j=0; j < plateau[i].length; j++){
                accesTuile = plateau[i][j].getAccess();

                if(accesTuile[0] == true && j == plateau[i].length-1){
                    System.out.println("|-------###-------|");
                }else if(accesTuile[0] == false && j == plateau[i].length-1){
                    System.out.println("|-----------------|");
                }else if(accesTuile[0] == true){
                    System.out.print("|-------###-------|");
                }else if(accesTuile[0] == false){
                    System.out.print("|-----------------|");
                }

            }


            for(j=0; j < plateau[i].length; j++){
                accesTuile = plateau[i][j].getAccess();
                String temp = "";

                if(plateau[i][j].getId() == jeu.J1.position.getId()){
                    temp = temp.concat(String.format("|%-9s", jeu.J1.couleur));
                }else{
                    temp = temp.concat(String.format("|%-9s", ""));
                }

                if(plateau[i][j].getId() == jeu.J2.position.getId()){
                    temp = temp.concat(String.format("%8s|", jeu.J2.couleur));
                }else{
                    temp = temp.concat(String.format("%-8s|", ""));
                }

                if(j != plateau[i].length - 1){
                    System.out.print(temp);
                }else{
                    System.out.println(temp);
                }

            }


            for(j=0; j < plateau[i].length; j++){
                accesTuile = plateau[i][j].getAccess();
                String temp = "";

                if(accesTuile[3] == true){
                    temp = temp.concat("#");
                }else if(accesTuile[3] == false){
                    temp = temp.concat("|");
                }

                if(plateau[i][j].isSpecial()){
                    if(plateau[i][j].isRotation()){
                        temp = temp.concat(String.format("%-4s%-13s", "", "ROTATION"));
                    }else if(plateau[i][j].isEchange()){
                        temp = temp.concat(String.format("%-4s%-13s", "", "ECHANGE"));
                    }else if(plateau[i][j].isDepart()){
                        temp = temp.concat(String.format("%-6s%-11s", "", "DEPART"));
                    }
                }else if(plateau[i][j].getTresor().equals("null")){
                    temp = temp.concat(String.format("%-17s", ""));
                }else{
                    temp = temp.concat(String.format("%-2s%-15s", "",plateau[i][j].getTresor()));
                }

                if(accesTuile[1] == true){
                    temp = temp.concat("#");
                }else if(accesTuile[1] == false){
                    temp = temp.concat("|");
                }

                if(j != plateau[i].length - 1){
                    System.out.print(temp);
                }else{
                    System.out.println(temp);
                }
            }

            for(j=0; j < plateau[i].length; j++){
                accesTuile = plateau[i][j].getAccess();
                String temp = "";

                if(plateau[i][j].getId() == jeu.J4.position.getId()){
                    temp = temp.concat(String.format("|%-9s", jeu.J4.couleur));
                }else{
                    temp = temp.concat(String.format("|%-9s", ""));
                }

                if(plateau[i][j].getId() == jeu.J3.position.getId()){
                    temp = temp.concat(String.format("%8s|", jeu.J3.couleur));
                }else{
                    temp = temp.concat(String.format("%-8s|", ""));
                }

                if(j != plateau[i].length - 1){
                    System.out.print(temp);
                }else{
                    System.out.println(temp);
                }

            }

            for(j=0; j < plateau[i].length; j++){
                accesTuile = plateau[i][j].getAccess();

                if(accesTuile[2] == true && j == plateau[i].length-1){
                    System.out.println("|-------###-------|");
                }else if(accesTuile[2] == false && j == plateau[i].length-1){
                    System.out.println("|-----------------|");
                }else if(accesTuile[2] == true){
                    System.out.print("|-------###-------|");
                }else if(accesTuile[2] == false){
                    System.out.print("|-----------------|");
                }

            }
        }
    }

    protected Int_Tuile creerTuile(int id_tuile, String tresor, int rotation, String type_tuile, Integer i, Integer j){
        Int_Tuile tuile;

        switch (type_tuile) {

            case "Tuile_Depart":
                tuile = new Tuile_Depart(id_tuile, rotation, tresor, i, j);
                break;
            case "Tuile_Echange":
                tuile = new Tuile_Echange(id_tuile, rotation, tresor, i, j);
                break;
            case "Tuile_Rotation":
                tuile = new Tuile_Rotation(id_tuile, rotation, tresor, i, j);
                break;
            case "Tuile_L":
                tuile = new Tuile_L(id_tuile, rotation, tresor, i, j);
                break;
            case "Tuile_T":
                tuile = new Tuile_T(id_tuile, rotation, tresor, i, j);
                break;
            case "Tuile_I":
                tuile = new Tuile_I(id_tuile, rotation, tresor, i, j);
                break;
            default:
                tuile = new Tuile_T(id_tuile, rotation, tresor, i, j);
                break;
        }

        return tuile;
    }

    //Ligne = true : mouvement sur ligne ; Ligne = false : mouvement sur colonne
    //Sens = true : petit vers grand ; Sens = false : grand vers petit
    //Num : indice de la ligne à déplacer;
    protected Int_Tuile deplacementPlateau(Jeu jeu, boolean ligne, boolean sens, int num, Int_Tuile tuile_ajout, int rotation){
        tuile_ajout.Rotation(rotation);
        Int_Tuile tuile_mobile = null;
        int i;
        if(num%2 == 0) {

            if (ligne && sens) { // G -> D
                tuile_mobile = plateau[num - 1][6];
                
                for(i = 0; i<=5; i++) {
                	plateau[num - 1][i].copieCoord(plateau[num - 1][i + 1]);
                }
                
                for (i = 5; i >= 0; i--) {
                	
                	plateau[num - 1][i + 1] = plateau[num - 1][i];
                }
                plateau[num - 1][0] = tuile_ajout;
                plateau[num - 1][0].setCoord(num-1, 0);

            } else if (ligne && !sens) {
                tuile_mobile = plateau[num - 1][0];
                
                for(i = 5; i >= 0; i--) {
                	plateau[num - 1][i + 1].copieCoord(plateau[num - 1][i]);
                }
                
                for (i = 0; i <= 5; i++) {
                	
                    plateau[num - 1][i] = plateau[num - 1][i + 1];
                }
                plateau[num - 1][6] = tuile_ajout;
                plateau[num - 1][6].setCoord(num-1, 6);

            } else if (!ligne && sens){
                tuile_mobile = plateau[6][num - 1];
                
                for(i = 0; i<=5; i++) {
                	plateau[i][num - 1].copieCoord(plateau[i + 1][num - 1]);
                }
            
                for (i = 5; i >= 0; i--) {
                	plateau[i + 1][num - 1] = plateau[i][num - 1];
                }
                plateau[0][num - 1] = tuile_ajout;
                plateau[0][num - 1].setCoord(0, num-1);

            } else if (!ligne && !sens){
                tuile_mobile = plateau[0][num - 1];
                
                for(i = 5; i >= 0; i--) {
                	plateau[i + 1][num - 1].copieCoord(plateau[i][num - 1]);
                }
                
                for (i = 0; i <= 5; i++) {
                    plateau[i][num - 1] = plateau[i + 1][num - 1];
                }
                plateau[6][num - 1] = tuile_ajout;
                plateau[6][num - 1].setCoord(6, num-1);

            }
        }
        
        tuile_mobile.copieCoord(null);
        checkJoueurPostDeplacement(jeu, ligne, sens, num, tuile_mobile);
        
        
        return tuile_mobile;
    }
    
    protected void checkJoueurPostDeplacement(Jeu jeu, boolean ligne, boolean sens, int num, Int_Tuile tuile_mobile) {
    	
    	if(tuile_mobile.getJoueur(jeu.J1)) {
        	jeu.J1.replaceJoueur(jeu.plateau_jeu, ligne, sens, num);
        }
    	
    	if(tuile_mobile.getJoueur(jeu.J2)) {
        	jeu.J2.replaceJoueur(jeu.plateau_jeu, ligne, sens, num);
        }
    	
    	if(tuile_mobile.getJoueur(jeu.J3)) {
        	jeu.J3.replaceJoueur(jeu.plateau_jeu, ligne, sens, num);
        }
    	
    	if(tuile_mobile.getJoueur(jeu.J4)) {
        	jeu.J4.replaceJoueur(jeu.plateau_jeu, ligne, sens, num);
        }
    	
    	jeu.J1.updateJoueur();
    	jeu.J2.updateJoueur();
    	jeu.J3.updateJoueur();
    	jeu.J4.updateJoueur();
    }

}
