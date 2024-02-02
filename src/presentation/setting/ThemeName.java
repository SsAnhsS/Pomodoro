package presentation.setting;

public enum ThemeName {
	APPLE("src/data/images/apple.png"), 
	BLUEBERRY("src/data/images/blueberries.png"), 
	CARROT("src/data/images/carrot.png"), 
	CHERRY("src/data/images/cherries.png"), 
	LEMON("src/data/images/lemon.png"), 
	OLIVE("src/data/images/olives.png"), 
	ORANGE("src/data/images/orange.png"), 
	TOMATO("src/data/images/tomato.png");
	
	private String file;
	
	private ThemeName(String file) {
		this.file = file;
	}
	
	public String getFile() {
		return file;
	}
}
