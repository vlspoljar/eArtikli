package main.java.hr.eartikli.utils;

import main.java.hr.eartikli.model.*;

import java.io.*;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class OutputUtil {

	// constants
	private static final String OUTPUT_CHARSET = "UTF-8";
	private static final String OUTPUT_SEPARATOR = "\t";
	private static final String NEW_LINE = "\n";
	private static final String NUMBER_FORMAT = "###,##0.000";
	private static final DecimalFormat NUMBER_FORMATTER = new DecimalFormat(NUMBER_FORMAT);
	private static final String CURRENCY_FORMAT = "###,##0.00";
	private static final DecimalFormat CURRENCY_FORMATTER = new DecimalFormat(CURRENCY_FORMAT);

	// input lists
	private static List<Artikl> artikli = InputUtil.getArtikli();
	private static List<ProdajnoMjesto> prodajnaMjesta = InputUtil.getProdajnaMjesta();
	private static List<Stanje> stanja = InputUtil.getStanja();
	private static List<Cijena> cjenik = InputUtil.getCjenik();
	private static List<StanjeExt> stanjaExt = getStanjaExt();

	private static BigDecimal tecajValute;

	// fields
	private String sifraValute;
	private String datumValute;

	// constructors
	public OutputUtil(String sifraValute, String datumValute) {
		this.sifraValute = sifraValute;
		this.datumValute = datumValute;
	}

	private static List<StanjeExt> getStanjaExt() {
		List<StanjeExt> stanjaExt = new ArrayList<>();
		StanjeExt stanjeExt;
		Optional<Cijena> cijena;
		BigDecimal ukupnaVrijednostArtikla;
		for (Stanje stanje : stanja) {
			cijena = Optional.of(cjenik.stream().filter(c -> c.getSifraArtikla().equals(stanje.getSifraArtikla())).findAny().orElseGet(Cijena::new));
			ukupnaVrijednostArtikla = cijena.get().getCijenaArtikla().multiply(stanje.getKolicinaArtikla());
			stanjeExt = new StanjeExt(stanje.getSifraArtikla(), stanje.getSifraProdajnogMjesta(), stanje.getKolicinaArtikla(), cijena.get().getCijenaArtikla(), ukupnaVrijednostArtikla);
			stanjaExt.add(stanjeExt);
		}
		return stanjaExt;
	}

	public void writeArtikli() {
		try {
			File file = new File("src/main/resources/vrijednost zalihe - artikli.txt");
			FileOutputStream output = new FileOutputStream(file);
			OutputStreamWriter writer = new OutputStreamWriter(output, OUTPUT_CHARSET);
			StringBuilder sb;
			Optional<Cijena> cijena;
			BigDecimal ukupnaVrijednostArtiklaKn, ukupnaVrijednostArtiklaValuta;
			List<Artikl> sortiraniArtikli = artikli.stream().sorted(Comparator.comparing(Artikl::getSifra)).collect(Collectors.toList());
			for (Artikl artikl : sortiraniArtikli) {
				sb = new StringBuilder();
				sb.append(artikl.getSifra());
				sb.append(OUTPUT_SEPARATOR);
				sb.append(artikl.getNaziv());
				sb.append(OUTPUT_SEPARATOR);
				cijena = Optional.of(cjenik.stream().filter(c -> c.getSifraArtikla().equals(artikl.getSifra())).findAny().orElseGet(Cijena::new));
				sb.append(CURRENCY_FORMATTER.format(cijena.get().getCijenaArtikla()));
				sb.append(OUTPUT_SEPARATOR);
				sb.append(NUMBER_FORMATTER.format(stanjaExt.stream().filter(s -> s.getSifraArtikla().equals(artikl.getSifra())).map(Stanje::getKolicinaArtikla).reduce(BigDecimal.ZERO, BigDecimal::add)));
				sb.append(OUTPUT_SEPARATOR);
				sb.append(artikl.getJedinicaMjere());
				sb.append(OUTPUT_SEPARATOR);
				ukupnaVrijednostArtiklaKn = stanjaExt.stream().filter(s -> s.getSifraArtikla().equals(artikl.getSifra())).map(StanjeExt::getUkupnaVrijednostArtikla).reduce(BigDecimal.ZERO, BigDecimal::add);
				sb.append(CURRENCY_FORMATTER.format(ukupnaVrijednostArtiklaKn));
				sb.append(OUTPUT_SEPARATOR);
				ukupnaVrijednostArtiklaValuta = ukupnaVrijednostArtiklaKn.divide(tecajValute, 6, RoundingMode.HALF_UP);
				sb.append(CURRENCY_FORMATTER.format(ukupnaVrijednostArtiklaValuta));
				sb.append(OUTPUT_SEPARATOR);
				sb.append(stanjaExt.stream().filter(s -> s.getSifraArtikla().equals(artikl.getSifra())).map(Stanje::getSifraProdajnogMjesta).distinct().count());
				sb.append(NEW_LINE);
				writer.write(String.valueOf(sb));
			}
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void writeProdajnaMjesta() {
		try {
			File file = new File("src/main/resources/vrijednost zalihe - PM.txt");
			FileOutputStream output = new FileOutputStream(file);
			OutputStreamWriter writer = new OutputStreamWriter(output, OUTPUT_CHARSET);
			StringBuilder sb;
			BigDecimal ukupnaVrijednostZaliheKn, ukupnaVrijednostZaliheValuta;
			List<ProdajnoMjesto> sortiranaProdajnaMjesta = prodajnaMjesta.stream().sorted(Comparator.comparing(ProdajnoMjesto::getSifra)).collect(Collectors.toList());
			for (ProdajnoMjesto prodajnoMjesto : sortiranaProdajnaMjesta) {
				sb = new StringBuilder();
				sb.append(prodajnoMjesto.getSifra());
				sb.append(OUTPUT_SEPARATOR);
				sb.append(prodajnoMjesto.getNaziv());
				sb.append(OUTPUT_SEPARATOR);
				ukupnaVrijednostZaliheKn = stanjaExt.stream().filter(s -> s.getSifraProdajnogMjesta().equals(prodajnoMjesto.getSifra())).map(StanjeExt::getUkupnaVrijednostArtikla).reduce(BigDecimal.ZERO, BigDecimal::add);
				sb.append(CURRENCY_FORMATTER.format(ukupnaVrijednostZaliheKn));
				sb.append(OUTPUT_SEPARATOR);
				ukupnaVrijednostZaliheValuta = ukupnaVrijednostZaliheKn.divide(tecajValute, 6, RoundingMode.HALF_UP);
				sb.append(CURRENCY_FORMATTER.format(ukupnaVrijednostZaliheValuta));
				sb.append(OUTPUT_SEPARATOR);
				sb.append(stanjaExt.stream().filter(s -> s.getSifraProdajnogMjesta().equals(prodajnoMjesto.getSifra())).map(Stanje::getSifraArtikla).distinct().count());
				sb.append(NEW_LINE);
				writer.write(String.valueOf(sb));
			}
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void getTecajnaLista() throws IOException {
		if (this.datumValute != null) {
			BufferedInputStream b = null;
			FileOutputStream output = null;
			try {
				URL url = new URL("http://hnb.hr/tecajn/f" + this.datumValute + ".dat");
				File file = new File("src/main/resources/tecajna_lista.txt");
				b = new BufferedInputStream(url.openStream());
				output = new FileOutputStream(file);
				final byte data[] = new byte[1024];
				int count;
				while ((count = b.read(data, 0, 1024)) != -1) {
					output.write(data, 0, count);
				}
			} finally {
				if (b != null) {
					b.close();
				}
				if (output != null) {
					output.close();
				}
			}
			tecajValute = InputUtil.getTecajValute(sifraValute);
		}
	}

}
