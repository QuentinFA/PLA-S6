package UI;

public abstract class Menu {
	public static Menu Mymenu;
	public static int change;
	public static void change_menu()
	{
		if(change != 0)
		{
			if(change == 1)
			{
				System.out.println("lalalalalaal change");
				Mymenu = new Menu_Main();
			}
			else if(change == 2)
			{
				System.out.println("lalalalalaal change 2222");
				Mymenu = new Menu_Level();
			}
			else if(change == 3)
			{
				
			}
		}
		change = 0;
	}
    public abstract boolean gerer();
    public abstract void afficher();

}
