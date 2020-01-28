package com.project.labyrithe;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

class JUnit_TestJoueur {

	@Test
	void testAfficherJoueur() {
		Plateau plateau_jeu = new Plateau();
		Joueur J1 = new Joueur(plateau_jeu, "Bleu", 0, 0);
		
		J1.afficherJoueur(); // A FAIRE
	}
	
	@Test
	void testAfficherCartes() {
		Plateau plateau_jeu = new Plateau();
		Joueur J1 = new Joueur(plateau_jeu, "Bleu", 0, 0);
		Cartes cartes = new Cartes();
		J1.tirerCartes(cartes);
		
		J1.afficherCartes(); // A FAIRE
	}
	
	@Test
	void testTirerCartes() {
		Plateau plateau_jeu = new Plateau();
		Joueur J1 = new Joueur(plateau_jeu, "Bleu", 0, 0);
		Cartes cartes = new Cartes();
		J1.tirerCartes(cartes);
		
		assertEquals(3, J1.cartes.size());
	}
	
	@Test
	void testObjectifCheck() {
		Plateau plateau_jeu = new Plateau();
		Joueur J1 = new Joueur(plateau_jeu, "Bleu", 0, 2);
		Cartes cartes = new Cartes();
		J1.tirerCartes(cartes);
		J1.cartes.removeFirst();
		J1.cartes.addFirst("Casque");
		J1.objectifCheck();
		
		assertEquals(2, J1.cartes.size());
	}

	@Test
	void testDeplacementJoueur() {
		Plateau plateau_jeu = new Plateau();
		Joueur J1 = new Joueur(plateau_jeu, "Bleu", 0, 0);
		
		while(!(plateau_jeu.plateau[0][1].estAccessible(plateau_jeu, 0, 1))) {
			plateau_jeu = new Plateau();
		}
		
		assertTrue(J1.deplacementJoueur(plateau_jeu, 0, 1));
	}
	
	@Test
	void testObjectifCompleted() {
		Plateau plateau_jeu = new Plateau();
		Joueur J1 = new Joueur(plateau_jeu, "Bleu", 0, 0);
		Cartes cartes = new Cartes();
		J1.tirerCartes(cartes);
		J1.cartes.clear();
		
		assertTrue(J1.objectifCompleted());
	}
	
	@Test
	void testActionCheckDepart() {
		Plateau plateau_jeu = new Plateau();
		Joueur J1 = new Joueur(plateau_jeu, "Bleu", 0, 0);
		Cartes cartes = new Cartes();
		Jeu jeu = new Jeu(plateau_jeu, J1, null, null, null, cartes);
		J1.tirerCartes(cartes);
		J1.cartes.clear();
		
		J1.actionCheck(jeu);
		
		assertTrue(J1.victoire);
	}
	
	@Test
	void testActionCheckEchange() {
		Plateau plateau_jeu = new Plateau();
		plateau_jeu.plateau[0][1] = new Tuile_Echange(6, 3, null, 0, 1);
		Joueur J1 = new Joueur(plateau_jeu, "Bleu", 0, 1);
		Joueur J2 = new Joueur(plateau_jeu, "Vert", 0, 6);
		Jeu jeu = new Jeu(plateau_jeu, J1, J2, null, null, null);
		
		System.out.println("testActionCheckEchange :");
		J1.actionCheck(jeu); // mettre 1 puis 2
		
		assertEquals(6, J1.y_coord);
		assertEquals(1, J2.y_coord);
	}
	
	@Test
	void testActionCheckRotation() {
		Plateau plateau_jeu = new Plateau();
		plateau_jeu.plateau[0][1] = new Tuile_Rotation(5, 3, "null", 0, 1);
		plateau_jeu.plateau[1][1] = new Tuile_L(16, 0, "null", 1, 1);
		Joueur J1 = new Joueur(plateau_jeu, "Bleu", 0, 1);
		Joueur J2 = new Joueur(plateau_jeu, "Rouge", 6, 6);
		Joueur J3 = new Joueur(plateau_jeu, "Vert", 0, 6);
		Joueur J4 = new Joueur(plateau_jeu, "Jaune", 6, 0);
		Jeu jeu = new Jeu(plateau_jeu, J1, J2, J3, J4, null);
		
		System.out.println("testActionCheckRotation :");
		J1.actionCheck(jeu); // mettre 2 puis 2 puis 2
		
		assertEquals(2, plateau_jeu.plateau[1][1].getRotation());
	}
	
	@Test
	void testUpdateJoueur() {
		Plateau plateau_jeu = new Plateau();
		Joueur J1 = new Joueur(plateau_jeu, "Bleu", 0, 1);
		J1.position = plateau_jeu.plateau[1][2];
				
		J1.updateJoueur();
		
		assertEquals(1, J1.x_coord);
		assertEquals(2, J1.y_coord);
	}
	
	@Test
	void testReplaceJoueur() {
		Plateau plateau_jeu = new Plateau();
		Joueur J1 = new Joueur(plateau_jeu, "Bleu", 0, 1);
		Joueur J2 = new Joueur(plateau_jeu, "Rouge", 1, 0);
		
		J1.replaceJoueur(plateau_jeu, false, false, 2); //Colonne, Decroissant, 2
		assertEquals(6, J1.x_coord);
		assertEquals(1, J1.y_coord);
		
		J1.replaceJoueur(plateau_jeu, false, true, 2); // Colonne, Croissant, 2
		assertEquals(0, J1.x_coord);
		assertEquals(1, J1.y_coord);
		
		J2.replaceJoueur(plateau_jeu, true, false, 2); // Ligne, Decroissant, 2
		assertEquals(1, J2.x_coord);
		assertEquals(6, J2.y_coord);
		
		J2.replaceJoueur(plateau_jeu, true, true, 2); // Ligne, Croissant, 2
		assertEquals(1, J2.x_coord);
		assertEquals(0, J2.y_coord);
	}
}
