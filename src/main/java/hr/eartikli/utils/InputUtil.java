package main.java.hr.eartikli.utils;

import main.java.hr.eartikli.enums.JedinicaMjere;
import main.java.hr.eartikli.model.Artikl;
import main.java.hr.eartikli.model.Cijena;
import main.java.hr.eartikli.model.ProdajnoMjesto;
import main.java.hr.eartikli.model.Stanje;

import java.io.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class InputUtil {

	// constants
	private static final String INPUT_CHARSET = "Windows-1250";
	private static final String INPUT_SEPARATOR = "\\|";

	public static List<Artikl> getArtikli() {
		List<Artikl> artikli = new ArrayList<>();
		try {
			File file = new File("src/main/resources/artikli.txt");
			FileInputStream input = new FileInputStream(file);
			InputStreamReader reader = new InputStreamReader(input, INPUT_CHARSET);
			BufferedReader b = new BufferedReader(reader);
			String readLine = "";
			String[] split;
			Artikl artikl;
			while ((readLine = b.readLine()) != null) {
				split = readLine.split(INPUT_SEPARATOR);
				artikl = new Artikl(split[0], split[1], JedinicaMjere.fromValue(split[2]));
				artikli.add(artikl);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return artikli;
	}

	public static List<ProdajnoMjesto> getProdajnaMjesta() {
		List<ProdajnoMjesto> prodajnaMjesta = new ArrayList<>();
		try {
			File file = new File("src/main/resources/pm.txt");
			FileInputStream input = new FileInputStream(file);
			InputStreamReader reader = new InputStreamReader(input, INPUT_CHARSET);
			BufferedReader b = new BufferedReader(reader);
			String readLine = "";
			String[] split;
			ProdajnoMjesto prodajnoMjesto;
			while ((readLine = b.readLine()) != null) {
				split = readLine.split(INPUT_SEPARATOR);
				prodajnoMjesto = new ProdajnoMjesto(split[0], split[1]);
				prodajnaMjesta.add(prodajnoMjesto);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return prodajnaMjesta;
	}

	public static List<Stanje> getStanja() {
		List<Stanje> stanja = new ArrayList<>();
		try {
			File file = new File("src/main/resources/stanja.txt");
			FileInputStream input = new FileInputStream(file);
			InputStreamReader reader = new InputStreamReader(input, INPUT_CHARSET);
			BufferedReader b = new BufferedReader(reader);
			String readLine = "";
			String[] split;
			Stanje stanje;
			while ((readLine = b.readLine()) != null) {
				split = readLine.split(INPUT_SEPARATOR);
				stanje = new Stanje(split[0], split[1], new BigDecimal(split[2].replaceAll(",", ".")));
				stanja.add(stanje);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return stanja;
	}

	public static List<Cijena> getCjenik() {
		List<Cijena> cjenik = new ArrayList<>();
		try {
			File file = new File("src/main/resources/cjenik.txt");
			FileInputStream input = new FileInputStream(file);
			InputStreamReader reader = new InputStreamReader(input, INPUT_CHARSET);
			BufferedReader b = new BufferedReader(reader);
			String readLine = "";
			String[] split;
			Cijena cijena;
			while ((readLine = b.readLine()) != null) {
				split = readLine.split(INPUT_SEPARATOR);
				cijena = new Cijena(split[0], new BigDecimal(split[1].replaceAll(",", ".")));
				cjenik.add(cijena);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return cjenik;
	}

	public static BigDecimal getTecajValute(String sifraValute) {
		BigDecimal tecajValute = BigDecimal.ZERO;
		if (sifraValute != null) {
			try {
				File file = new File("src/main/resources/tecajna_lista.txt");
				FileInputStream input = new FileInputStream(file);
				InputStreamReader reader = new InputStreamReader(input, "UTF-8");
				BufferedReader b = new BufferedReader(reader);
				String readLine = "";
				String[] split;
				while ((readLine = b.readLine()) != null) {
					if (readLine.contains(sifraValute)) {
						split = readLine.split("       ");
						tecajValute = new BigDecimal(split[2].replaceAll(",", "."));
					}
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return tecajValute;
	}

}
