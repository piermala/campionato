package campionato;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class ServizioCampionato {

	
	/// REGISTRA GIOCATORI   ---->  prova con map
	public Giocatore registraGiocatore(Campionato c, String nome, String cognome, String codiceFiscale, int età, double stipendio){
		
		///verifichiamo che il giocatore non esista già, esaminando il suo codice fiscale
		Giocatore g = null;
		
		if (c.getMapGiocatori().containsKey(codiceFiscale)){
			g = c.getMapGiocatori().get(codiceFiscale);
		}
		
		
		if (g==null){
			g = new Giocatore(nome, cognome, età, stipendio, 0, 0, 0, 0, codiceFiscale);
			c.getMapGiocatori().put(codiceFiscale, g);
			System.out.println("Il giocatore è stato registrato!");
			return g;
		} else {
			System.out.println("Il GIOCATORE ESISTE GIA'!");
			return g;			
		}
	}
	
	
	/// REGISTRA SQUADRE
	public Squadra registraSquadre(Campionato c, String nome){
		/// verifichiamo che la squadra non esista già
		Squadra s = null;
		List<Squadra> squadre = c.getSquadre();
		for (Squadra sTemp : squadre){
			if (sTemp.getNome().equals(nome)){
				s = sTemp;
			}
		}
		
		///una volta verificato, registriamo la squadra
		if (s==null){
			s = new Squadra(nome, 0, 0, 0, 0);
			squadre.add(s);
			System.out.println("La squadra è stata registrata! ");
			return s;
		} else {
			System.out.println("LA SQUADRA ESISTE GIA'!");
			return s;
		}
	}
	
	
	/// ASSEGNA GIOCATORE ---->  WORKING!
	public boolean assegnaGiocatore(Campionato c, Giocatore g, Squadra s){
		
		/// verifichiamo se il Giocatore esiste  ---> WORKING!
		boolean giocatoreEsistente = false;
		boolean squadraEsistente = false;
		
		Map<String, Giocatore> mapGiocatori = c.getMapGiocatori();
		
		for (Map.Entry<String, Giocatore> mGioc : mapGiocatori.entrySet()){
			if (mGioc.getKey().equals(g.getCodiceFiscale())){
				giocatoreEsistente = true;
			}
		}
		
		
		/// una volta verificata l'esistenza del giocatore, verifichiamo se la squadra esiste
		if (giocatoreEsistente){
			List<Squadra> squadre = c.getSquadre();
			for (Squadra sqTemp : squadre){
				if (sqTemp.equals(s)){
					squadraEsistente = true;
				}
			}
		} else {
			System.out.println("IL GIOCATORE NON ESISTE!");
		}
		
		
		/// una volta verificato che il giocatore e la squadra esistono, assegnamo la squadra al giocatore
		if (squadraEsistente){
			s.getGiocatori().put(g.getCodiceFiscale(), g);
			return true;
		} else {
			System.out.println("LA SQUADRA NON ESISTE!");
			return false;
		}
		
	}
	
	
	///REGISTRA PARTITA
	public Partita registraPartita(Campionato c, Squadra squadraCasa, Squadra squadraOspite, int retiCasa, int retiOspite, String data, List<Giocatore> retiFatte, List<Giocatore> ammoniti, Set<Giocatore> espulsi){
		
		Partita p = null;
		char risFinale = 0;
		
		///registriamo la data della partita
		System.out.println(data);
		
		/// le squadre che si affrontano. stampiamo anche il risultato		
		if (squadraOspite != squadraCasa){
			System.out.print(squadraCasa.getNome() + " - ");
			System.out.print(squadraOspite.getNome());
			p = new Partita(data, squadraCasa, squadraOspite, retiCasa, retiOspite, risFinale);
			System.out.println(" " + Integer.toString(retiCasa) + "-" + Integer.toString(retiOspite));
			/// 1 X 2   P.S. Aggiungiamo anche i punti alle squadre che si affrontano
			if (retiCasa > retiOspite){
				risFinale = '1';
			} else if (retiCasa < retiOspite){
				risFinale = '2';
			} else {
				risFinale = 'X';
			}
			
			/// Aggiungiamo le reti fatte e subite alle due squadre
			squadraCasa.setRetiFatte(retiCasa);
			squadraOspite.setRetiFatte(retiOspite);
			squadraCasa.setRetiSubite(retiOspite);
			squadraOspite.setRetiSubite(retiCasa);
			System.out.println(risFinale);
		} else {
			System.out.println("LA SQUADRA OSPITE NON PUO' ESSERE UGUALE ALLA SQUADRA DI CASA!");
		}
		
		/// ammoniti ed espulsi
		System.out.println("Ammoniti: " + ammoniti);
		System.out.println("Espulsi: " + espulsi);		
		
		
		///controlliamo che non siano presenti gol di una squadra che non abbia segnato
		boolean ok = false;
		if (retiFatte.size() == retiCasa + retiOspite){
			ok = true;
		} else {
			System.out.println("Il numero delle reti totali è diverso da quello delle reti segnate!");
		}
		
		if (ok){
			System.out.println("Reti: " + retiFatte.toString());
		}
		
		return p;
	}
	
}	

