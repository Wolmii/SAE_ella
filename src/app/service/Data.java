package app.service;

import java.time.LocalDate;
import java.util.ArrayList;

public class Data {
	
	static public Gala gala;
	static public Groupe g1;
	static public Plan plan;
	
	
	public static void main(String[] args){
		Personne p1= new Personne("Regnier","Mathilde");
		Personne p2= new Personne("Le Beaudour","Bastien");
		Personne p3= new Personne("Courtois","Keren");
		ArrayList<Personne> lst= new ArrayList<Personne>(3);
		lst.add(p1);
		lst.add(p2);
		lst.add(p3);
		g1= new Groupe(lst);
		ArrayList<Table> tables= new ArrayList<Table>(30);
		for (int i=0;i<10;i++) {
			tables.add(new PetiteTable());
		}
		for (int i=0;i<20;i++) {
			tables.add(new GrandeTable());
		}
		gala= new Gala("gala1", LocalDate.of(2025, 07, 01), "Lannion");
		plan= new Plan(gala,tables);
    }
}
