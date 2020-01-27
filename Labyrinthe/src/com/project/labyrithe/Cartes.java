package com.project.labyrithe;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Random;

public class Cartes {
    
	protected LinkedList<String[]> liste_cartes;

    Cartes(){
        initialiserCartes();
    }

    void initialiserCartes(){
        //Recuperation des informations des cartes dans une liste
        File file = new File("Cartes.csv");
        String ligne;
        liste_cartes = new LinkedList<String[]>();

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            while ((ligne = br.readLine()) != null) {
                liste_cartes.add(ligne.split(";"));
            }
        } catch (IOException e) {
            System.err.format("IOException: %s", e);
        }
    }

    String getCarte(){

        Random rint = new Random();
        Integer rnum = rint.nextInt(liste_cartes.size());
        String[] nom = liste_cartes.get(rnum);
        liste_cartes.remove(liste_cartes.get(rnum));
        return nom[0];
    }

}
