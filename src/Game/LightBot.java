package Game;

import java.io.IOException;

import Game.Ressources.MUSIC;
import UI.Graphic;
import UI.Gui;
import UI.Menu;
import UI.Menu.MENU;

public class LightBot
{
	public static void main(String[] args)
	{
		Graphic.SFML.initialiser(1240, 780);
		
		try {Ressources.initialiser();}
		catch (IOException e) {e.printStackTrace();}
		
		Menu.change_menu(MENU.MAIN);
		Ressources.MUSIC.getMusic(MUSIC.MARIO).play();
		Ressources.MUSIC.getMusic(MUSIC.MARIO).setLoop(true);
		
		//TEST INTERPRETEUR
	
		/*Character c = new Character(new Coordonnees(0,0,0),0);
		Procedure main = new Procedure(Color.DEFAUT, TypeProcedure.COMMUN);
		Procedure p1=  new Procedure(Color.DEFAUT, TypeProcedure.COMMUN);
		Procedure p2 =  new Procedure(Color.DEFAUT, TypeProcedure.COMMUN);
		Interpreter.INTERPRETER = new Interpreter();
		For f = new For(5);
		Action a = new Forward(Color.DEFAUT);
		Action a2 = new Light(Color.DEFAUT);
		Action a3 = new Left_turn(Color.DEFAUT);
		Action a4 = new Right_turn(Color.DEFAUT);
		ReturnProc b = new ReturnProc();
		
		
		main.addProg(a);
		p1.addProg(a2);
		p2.addProg(a2);
	//	p1.addProg(b);
		p1.addProg(a3);
		main.addProg(f);
		main.addProg(p1);
		p2.addProg(a4);
		
		main.addProg(a4);
		main.addProg(p2);*/
		/*main.addProg(p1);
		p1.addProg(p2);
		p2.addProg(p1);
		c.setMain(main);
		while(c.next()) {}
			
		System.out.println("FINI"); */
		
		
		while (!Input.INPUT.gerer()) //Boucle principale
		{
			if (Graphic.SFML.gerer()) 
				return;
			
			if (Menu.Mymenu != null)
				Menu.Mymenu.gerer();
			
			if (Gui.GUI != null)
				Gui.GUI.gerer();
			
			if (World.WORLD != null)
				World.WORLD.gerer();
			
			Graphic.SFML.afficher();
		}
	}
}
