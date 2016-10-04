package main;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

import campionato.*;

public class MainCampionato {

	public static void main(String[] args) {
		
		Campionato c = new Campionato();
		Giocatore g = new Giocatore();
		Squadra s = new Squadra();
		Partita p = new Partita();
		ServizioCampionato sc = new ServizioCampionato();
		Date d = new Date();
		
		
		///REGISTRA GIOCATORE		
		Giocatore g1 = sc.registraGiocatore(c, "Francesco", "Totti", "FRNTTT76I27R", 40, 4000000);
		Giocatore g2 = sc.registraGiocatore(c, "Francesco", "Totti", "FRNTTT76I27R", 40, 4000000);
		Giocatore g3 = sc.registraGiocatore(c, "Gianluigi", "Buffon", "GNGBFN78I2MC", 38, 5000000);
		Giocatore g4 = sc.registraGiocatore(c, "Mattia", "De Sciglio", "MTTDSC92H28MI", 23, 3000000);
		Giocatore g5 = sc.registraGiocatore(c, "Manuel", "Locatelli", "MNLLCT98D24MI", 18, 600000);
		Giocatore g6 = sc.registraGiocatore(c, "Stephan", "El Shaarawy", "STPELS92H20", 23, 3000000);
		
		///STAMPA GIOCATORI
		System.out.println("\n\n***STAMPA GIOCATORI***\n");
		for (Map.Entry<String, Giocatore> mapG : c.getMapGiocatori().entrySet()){
			System.out.println(mapG.getValue());
		}
		
		System.out.println();
		
		
		////REGISTRA SQUADRA
		Squadra s1 = sc.registraSquadre(c, "Milan");
		Squadra s2 = sc.registraSquadre(c, "Napoli");
		Squadra s3 = sc.registraSquadre(c, "Roma");
		
		////STAMPA SQUADRE
		System.out.println("***STAMPA SQUADRE***");
		for (Squadra sq : c.getSquadre()){
			System.out.println(sq);
		}
		
		
		////ASSEGNA GIOCATORE
		System.out.println("\n\n***ASSEGNA GIOCATORE****");
		//Giocatore g1 = new Giocatore("Francesco", "Totti", 40, 4000000, 0, 0, 0, 0, "FRNTTT76I27R");
		//Giocatore g2 = c.getMapGiocatori().get(c.getMapGiocatori().get(2));
		if (sc.assegnaGiocatore(c, g1, c.getSquadre().get(2))){
			System.out.println("Vero");
		} else {
			System.out.println("Flaso");
		}
		sc.assegnaGiocatore(c, g4, c.getSquadre().get(0));
		sc.assegnaGiocatore(c, g5, c.getSquadre().get(0));
		sc.assegnaGiocatore(c, g6, s3);
		
		///STAMPIAMO I GIOCATORI DELLE RISPETTIVE SQUADRE
		System.out.println("\n\n***GIOCATORI DELLE SQUADRE***");
		for (int i=0; i<c.getSquadre().size(); i++){
			System.out.println(c.getSquadre().get(i).getNome() + " --- " + c.getSquadre().get(i).getGiocatori());
		}
		
		
		System.out.println();
		
		////REGISTRIAMO LA PARTITA
		System.out.println("***REGISTRA PARTITA***");
		
		/// data
		SimpleDateFormat sdf = new SimpleDateFormat("dd MM yyyy");
		Calendar calendar = new GregorianCalendar();
		calendar.set(Calendar.DAY_OF_MONTH, 28);
		calendar.set(Calendar.MONTH, 2);
		calendar.set(Calendar.YEAR, 2011);	//fine data			
		
		/// ammoniti ed espulsi
		List<Giocatore> ammoniti = new ArrayList<Giocatore>(Arrays.asList(g5, g4)); 
		Set<Giocatore> espulsi = new LinkedHashSet<Giocatore>();
		espulsi.add(s1.getGiocatori().get("MTTDSC92H28MI"));
		
		///reti segnate
		List<Giocatore> retiFatte = new ArrayList<Giocatore>(Arrays.asList(g5, g4, g4, g4, g4)); 		
		Partita p1 = sc.registraPartita(c, s1, s2, 5, 0, sdf.format(calendar.getTime()), retiFatte, ammoniti, espulsi);	
		
		/// partita 2
		System.out.println("\n\n   PARTITA 2");
		calendar.set(Calendar.DAY_OF_MONTH, 23);
		calendar.set(Calendar.MONTH, 3);
		retiFatte.removeAll(retiFatte);
		espulsi.removeAll(espulsi);
		ammoniti.removeAll(ammoniti);
		retiFatte.add(g5);
		Partita p2 = sc.registraPartita(c, s1, s3, 1, 0, sdf.format(calendar.getTime()), retiFatte, ammoniti, espulsi);	
		
		/// partita 3
		System.out.println("\n\n   PARTITA 3");
		calendar.set(Calendar.DAY_OF_MONTH, 25);
		calendar.set(Calendar.MONTH, 1);
		retiFatte.removeAll(retiFatte);
		espulsi.removeAll(espulsi);
		ammoniti.removeAll(ammoniti);
		retiFatte = new ArrayList<Giocatore>(Arrays.asList(g1,g1,g1,g1,g1,g1,g1)); 
		Partita p3 = sc.registraPartita(c, s3, s2, 7, 0, sdf.format(calendar.getTime()), retiFatte, ammoniti, espulsi);
		
		////CAPOCANNONIERE	
		/*System.out.println("\n\n *** CAPOCANNONIERE ***");
		Giocatore capocannoniere = sc.getCapocannoniere(c);
		System.out.println(capocannoniere.getNome() + " " + capocannoniere.getCognome() + " " + capocannoniere.getReti() + " reti");
		
		
		//// CAMPIONE
		System.out.println(" *** CAMPIONE ***");
		Squadra campione = sc.getCampione(c);
		System.out.println(campione + " " + campione.getPunti());*/
		
		
		
		
		
		
		
		
		
	}
}
