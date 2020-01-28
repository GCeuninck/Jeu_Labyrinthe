package com.project.labyrithe;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

class JUnit_TestCartes {

	@Test
	void testInitialiserCartes(){

		Cartes cartes = new Cartes();
		
		assertEquals(24, cartes.liste_cartes.size());
		assertTrue(cartes.liste_cartes.getFirst()[0].equals("Araignee"));
	}
	
	@Test
	void testGetCarte() {
		
		Cartes cartes = new Cartes();
		
		String res = cartes.getCarte();
		
		assertEquals(23, cartes.liste_cartes.size());
	}
}
