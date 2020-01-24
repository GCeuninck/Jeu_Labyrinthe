package com.project.labyrithe;
import java.io.*;
import java.util.*;

public class Plateau {

    Int_Tuile[][] plateau;
    LinkedList<String[]> liste_tuiles;

    Plateau(){
        initialisationTuiles();
        plateau = new Int_Tuile[7][7];
        generationPlateau();
    }

    public void generationPlateau(){

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

    void initialisationTuiles(){

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

    int tirageAleatoireID(){

        Random rint = new Random();
        Integer rnum = rint.nextInt(liste_tuiles.size());

        while(!liste_tuiles.get(rnum)[3].equals("null") && !liste_tuiles.get(rnum)[4].equals("null")){
            rnum = rint.nextInt(liste_tuiles.size());
        }
        return rnum;
    }

    int tirageAleatoireRot(){
        Random rint = new Random();
        return rint.nextInt(4);
    }

    public void afficherPlateau(){
        for(int i=0; i < plateau.length; i++){
            for(int j=0; j < plateau[i].length; j++){
                System.out.print(plateau[i][j] + " ");
            }
            System.out.println();
        }
    }

    Int_Tuile creerTuile(int id_tuile, String tresor, int rotation, String type_tuile, Integer i, Integer j){
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
            case "Type_L":
                tuile = new Tuile_L(id_tuile, rotation, tresor, i, j);
                break;
            case "Type_T":
                tuile = new Tuile_T(id_tuile, rotation, tresor, i, j);
                break;
            case "Type_I":
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
    Int_Tuile deplacementPlateau(boolean ligne, boolean sens, int num, Int_Tuile tuile_ajout, int rotation){
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
                
                System.out.println("G-->D");
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
                
                System.out.println("D-->G");
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
                
                
                
                System.out.println("H-->B");
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
                
                System.out.println("B-->H");
            }
        }
        
        tuile_mobile.copieCoord(null);

        return tuile_mobile;
    }

}
