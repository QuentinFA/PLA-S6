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

	public Color stringToColor(String t, Color couleur)
	{
		Color c;
		if(t.equals("ROUGE"))
			c = Color.ROUGE;
		else if(t.equals("BLEU"))
			c =  Color.BLEU;
		else if(t.equals("VERT"))
			c =  Color.VERT;
		else
			c = Color.DEFAUT;
		
		return c;
	}
}