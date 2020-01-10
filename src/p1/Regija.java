package p1;

import java.io.BufferedOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class Regija implements Serializable{
	private String naziv;
	private int natalitet;
	private int mortalitet;
	private int migracioniSaldo;
	
	

	public Regija(String naziv, int natalitet, int mortalitet) {
		super();
		this.naziv = naziv;
		this.natalitet = natalitet;
		this.mortalitet = mortalitet;
	}

	public String getNaziv() {
		return naziv;
	}

	public void setNaziv(String naziv) {
		if (naziv == null || naziv.length() < 3) {
			throw new DemografijaException("Naziv regije je NULL ili ima mane od 3 slova");
		} else
			this.naziv = naziv;
	}

	public int getNatalitet() {
		return natalitet;
	}

	public void setNatalitet(int natalitet) {
		if (natalitet <= 0)
			throw new DemografijaException("Natalitet je < ili = 0");
		else
			this.natalitet = natalitet;
	}

	public int getMortalitet() {
		return mortalitet;
	}

	public void setMortalitet(int mortalitet) {
		if (natalitet <= 0)
			throw new DemografijaException("Mortalitet je < ili = 0");
		else
			this.mortalitet = mortalitet;
	}

	public int getMigracioniSaldo() {
		return migracioniSaldo;
	}

	public void setMigracioniSaldo(int migracioniSaldo) {
		this.migracioniSaldo = migracioniSaldo;
	}

	@Override
	public String toString() {
		return "Naziv regije: " + this.naziv + "; natalitet = " + this.natalitet + "; mortalitet = " + this.mortalitet
				+ "; promena broja stanovnika je: " + (this.natalitet - this.mortalitet + this.migracioniSaldo);
	}
	
	@Override
	public boolean equals(Object obj) {
		if(!(obj instanceof Regija)) return false;
		return ((Regija)obj).getNaziv().equals(this.naziv);
	}
	
	public static void sacuvajRegiju(Regija regija, String filename) {
		try {
			ObjectOutputStream out = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(filename)));
			
			out.writeObject(regija);
			
			out.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
