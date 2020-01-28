package com.project.labyrithe;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class JUnis_TestTuile {

	@Test
	void testGetAccess() {
		Plateau plateau_jeu = new Plateau();
		
		boolean[] res = plateau_jeu.plateau[0][2].getAccess();
		
		assertFalse(res[0]);
		assertTrue(res[1]);
		assertTrue(res[2]);
		assertTrue(res[3]);
	}
	
	@Test
	void testEstAccessible() {
		Plateau plateau_jeu = new Plateau();
		
		plateau_jeu.plateau[0][1] = new Tuile_I(43, 1, null, 0, 1);
		
		assertTrue(plateau_jeu.plateau[0][1].estAccessible(plateau_jeu, 0, 1));
		assertTrue(plateau_jeu.plateau[0][1].getAccess()[3]);
		assertTrue(plateau_jeu.plateau[0][0].getAccess()[1]);
	}
	
	@Test
	void testGetJoueur() {
		Plateau plateau_jeu = new Plateau();
		Joueur J1 = new Joueur(plateau_jeu, "Bleu", 0, 0);
		
		assertTrue(plateau_jeu.plateau[0][0].getJoueur(J1));
		assertFalse(plateau_jeu.plateau[0][1].getJoueur(J1));
	}
	
	@Test
	void testGetTresor() {
		Plateau plateau_jeu = new Plateau();
		
		assertTrue(plateau_jeu.plateau[0][2].getTresor().equals("Casque"));
	}
	
	@Test
	void testGetCoord() {
		Plateau plateau_jeu = new Plateau();
		
		Integer[] coord = plateau_jeu.plateau[0][0].getCoord();
		
		assertEquals(0, coord[0]);
		assertEquals(0, coord[1]);
	}
	
	@Test
	void testSetCoord() {
		Plateau plateau_jeu = new Plateau();
		
		plateau_jeu.plateau[1][1].setCoord(0, 0);
		
		assertEquals(0, plateau_jeu.plateau[1][1].getCoord()[0]);
		assertEquals(0, plateau_jeu.plateau[1][1].getCoord()[1]);
	}
	
	@Test
	void testCopieCoord() {
		Plateau plateau_jeu = new Plateau();
		
		plateau_jeu.plateau[1][1].copieCoord(plateau_jeu.plateau[0][0]);
		
		assertEquals(0, plateau_jeu.plateau[1][1].getCoord()[0]);
		assertEquals(0, plateau_jeu.plateau[1][1].getCoord()[1]);
	}
	
	@Test
	void testGetId() {
		Plateau plateau_jeu = new Plateau();
		
		assertEquals(1, plateau_jeu.plateau[0][0].getId());
	}
	
	@Test
	void testIsSpecial() {
		Plateau plateau_jeu = new Plateau();
		
		plateau_jeu.plateau[0][0] = new Tuile_Depart(1, 0, null, 0, 0);
		assertTrue(plateau_jeu.plateau[0][0].isSpecial());
		
		plateau_jeu.plateau[0][1] = new Tuile_Rotation(5, 0, null, 0, 1);
		assertTrue(plateau_jeu.plateau[0][1].isSpecial());
		
		plateau_jeu.plateau[1][1] = new Tuile_Echange(6, 0, null, 1, 1);
		assertTrue(plateau_jeu.plateau[1][1].isSpecial());
		
		plateau_jeu.plateau[0][3] = new Tuile_I(43, 0, null, 0, 3);
		assertFalse(plateau_jeu.plateau[0][3].isSpecial());
	}

	@Test
	void testIsRotation() {
		Plateau plateau_jeu = new Plateau();
		
		plateau_jeu.plateau[0][1] = new Tuile_Rotation(5, 0, null, 0, 1);
		assertTrue(plateau_jeu.plateau[0][1].isRotation());
		
		plateau_jeu.plateau[0][3] = new Tuile_I(43, 0, null, 0, 3);
		assertFalse(plateau_jeu.plateau[0][3].isRotation());
	}
	
	@Test
	void testIsEchange() {
		Plateau plateau_jeu = new Plateau();
		
		plateau_jeu.plateau[1][1] = new Tuile_Echange(6, 0, null, 1, 1);
		assertTrue(plateau_jeu.plateau[1][1].isEchange());
		
		plateau_jeu.plateau[0][3] = new Tuile_I(43, 0, null, 0, 3);
		assertFalse(plateau_jeu.plateau[0][3].isEchange());
	}
	
	@Test
	void testIsDepart() {
		Plateau plateau_jeu = new Plateau();
		
		plateau_jeu.plateau[0][0] = new Tuile_Depart(1, 0, null, 0, 0);
		assertTrue(plateau_jeu.plateau[0][0].isDepart());
		
		plateau_jeu.plateau[0][3] = new Tuile_I(43, 0, null, 0, 3);
		assertFalse(plateau_jeu.plateau[0][3].isDepart());
	}
	
	@Test
	void testAfficherTuile() {
		Plateau plateau_jeu = new Plateau();
		
		plateau_jeu.plateau[1][1].afficherTuile(); //A FAIRE
	}
	
	@Test
	void testGetRotation() {
		Plateau plateau_jeu = new Plateau();
		
		plateau_jeu.plateau[0][3] = new Tuile_I(43, 3, null, 0, 3);
		assertEquals(3, plateau_jeu.plateau[0][3].getRotation());
	}
	
	@Test
	void testRotation() {
		Plateau plateau_jeu = new Plateau();
		
		plateau_jeu.plateau[0][3] = new Tuile_I(43, 3, null, 0, 3);
		plateau_jeu.plateau[0][3].Rotation(1);
		
		assertEquals(1, plateau_jeu.plateau[0][3].getRotation());
	}
}
