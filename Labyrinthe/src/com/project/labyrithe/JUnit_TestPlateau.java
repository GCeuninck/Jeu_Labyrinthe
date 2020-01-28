package com.project.labyrithe;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class JUnit_TestPlateau {

	@Test
	void testGenerationPlateau() {
		Plateau plateau_jeu = new Plateau();
		
		assertEquals(7, plateau_jeu.plateau.length);
		assertEquals(7, plateau_jeu.plateau[0].length);
		assertEquals(3, plateau_jeu.plateau[6][6].getId());
		assertEquals("Casque", plateau_jeu.plateau[0][2].getTresor());
		assertEquals(3, plateau_jeu.plateau[2][0].getRotation());
	}

	@Test
	void testInitialisationTuiles() {
		Plateau plateau_jeu = new Plateau();
		assertEquals(1, plateau_jeu.liste_tuiles.size()); // reste tuile mobile uniquement
		
	}
	
	@Test
	void testTirageAleatoireRot() {
		Plateau plateau_jeu = new Plateau();
		
		boolean res = plateau_jeu.tirageAleatoireRot() < 4 && plateau_jeu.tirageAleatoireRot() >= 0;
		
		assertTrue(res);
	}
	
	@Test
	void testAfficherPlateau() {
		Plateau plateau_jeu = new Plateau();
		Joueur J1 = new Joueur(plateau_jeu, "Bleu", 0, 2);
		Joueur J2 = new Joueur(plateau_jeu, "Vert", 0, 2);
		Joueur J3 = new Joueur(plateau_jeu, "Jaune", 0, 2);
		Joueur J4 = new Joueur(plateau_jeu, "Rouge", 0, 2);
		Jeu jeu = new Jeu(plateau_jeu, J1, J2, J3, J4, null);
		
		jeu.plateau_jeu.afficherPlateau(jeu);
	}
	
	@Test
	void TestCreerTuile() {
		Int_Tuile tuile = new Tuile_Rotation(5, 2, "null", 4, 5);
		
		assertEquals(5,tuile.getId());
		assertEquals(2,tuile.getRotation());
		assertEquals("null",tuile.getTresor());
		assertEquals(4,tuile.getCoord()[0]);
		assertEquals(5,tuile.getCoord()[1]);
	}
	
	@Test
	void testDeplacementPlateau() {
		Plateau plateau_jeu = new Plateau();
		Joueur J1 = new Joueur(plateau_jeu, "Bleu", 1, 0);
		Joueur J2 = new Joueur(plateau_jeu, "Vert", 0, 1);
		Joueur J3 = new Joueur(plateau_jeu, "Jaune", 1, 6);
		Joueur J4 = new Joueur(plateau_jeu, "Rouge", 6, 1);
		Jeu jeu = new Jeu(plateau_jeu, J1, J2, J3, J4, null);
		jeu.tuile_mobile = jeu.plateau_jeu.creerTuile(Integer.parseInt(jeu.plateau_jeu.liste_tuiles.getFirst()[0]), jeu.plateau_jeu.liste_tuiles.getFirst()[2], 0, jeu.plateau_jeu.liste_tuiles.getFirst()[1], null, null);

		Integer oldID = jeu.plateau_jeu.plateau[1][0].getId();
		jeu.tuile_mobile = jeu.plateau_jeu.deplacementPlateau(jeu, true, false, 2, jeu.tuile_mobile, 0);
		Integer newID = jeu.plateau_jeu.plateau[1][0].getId();
		
		assertNotEquals(oldID, newID);
		assertEquals(1, J1.x_coord);
		assertEquals(6, J1.y_coord);
	}
}
