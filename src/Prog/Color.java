package Prog;

/**
 * Type couleur
 */
public enum Color
{
	DEFAUT,
	ROUGE,
	BLEU,
	VERT;

	public Color stringToColor(String t){
		if(t.equals("ROUGE"))
			return Color.ROUGE;
		else if(t.equals("BLEU"))
			return Color.BLEU;
		else if(t.equals("VERT"))
			return Color.VERT;
		else
			return Color.DEFAUT;
	}
}