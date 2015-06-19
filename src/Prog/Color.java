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

	public void stringToColor(String t, Color couleur){
		if(t.equals("ROUGE"))
			couleur = Color.ROUGE;
		else if(t.equals("BLEU"))
			couleur =  Color.BLEU;
		else if(t.equals("VERT"))
			couleur =  Color.VERT;
		else
			couleur = Color.DEFAUT;
	}
}