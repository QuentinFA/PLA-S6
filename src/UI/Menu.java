package UI;

public abstract class Menu 
{
	public static Menu Mymenu;
	
	public enum MENU
	{
		MAIN,
		LEVEL
	}
	
	public static void change_menu(MENU m)
	{
		if (m == MENU.MAIN)
			Mymenu = new Menu_Main();
		else if (m == MENU.LEVEL)
			Mymenu = new Menu_Level();
	}
	
    public abstract void gerer();
    public abstract void afficher();
}
