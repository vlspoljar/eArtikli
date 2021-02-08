package main.java.hr.eartikli.enums;

public enum JedinicaMjere {
	KG("KG"),
	KOM("KOM");

	private String value;

	JedinicaMjere(String value) {
		this.value = value;
	}


	public String getValue() {
		return value;
	}

	public static JedinicaMjere fromValue(String value) {
		for (JedinicaMjere enumeration : JedinicaMjere.values()) {
			if (enumeration.getValue().equals(value)) {
				return enumeration;
			}
		}
		return null;
	}
}
