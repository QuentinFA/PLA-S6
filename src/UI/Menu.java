package UI;

public abstract class Menu 
{
	public static Menu Mymenu;
	
	public enum MENU
	{
		MAIN,
		LEVEL,
		MAP
	}
	
	public static void change_menu(MENU m)
	{
		if (m == MENU.MAIN)
			Mymenu = new Menu_Main();
		else if (m == MENU.LEVEL)
			Mymenu = new Menu_Level();
		else if (m == MENU.MAP)
			Mymenu = new Menu_Map();
	}
	
    public abstract boolean gerer();
    public abstract void afficher();
}
