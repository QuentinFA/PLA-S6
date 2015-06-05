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
<<<<<<< HEAD
			   // Mymenu.init();
			}
			else if(change == 2)
			{
				 System.out.println("lalalalalaal change 2222");
					Mymenu = new Menu_Level();
				  //  Mymenu.init();
=======
				Mymenu.init();
			}
			else if(change == 2)
			{
				System.out.println("lalalalalaal change 2222");
				Mymenu = new Menu_Level();
				Mymenu.init();
>>>>>>> 2cbeb62f718326bdd12195f1cb41bba2e7ddd194
			}
			else if(change == 3)
			{
				
			}
		}
		change = 0;
	}
<<<<<<< HEAD
    public abstract boolean gerer();
    public abstract void afficher();
    //public abstract void init();
=======
	public abstract boolean gerer();
	public abstract void afficher();
	public abstract void init();
>>>>>>> 2cbeb62f718326bdd12195f1cb41bba2e7ddd194
}
