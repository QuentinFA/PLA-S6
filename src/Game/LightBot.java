package Game;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import Game.Ressources.MUSIC;
import Prog.Action;
import Prog.ReturnProc;
import Prog.Color;
import Prog.Coordonnees;
import Prog.For;
import Prog.Forward;
import Prog.Left_turn;
import Prog.Light;
import Prog.Procedure;
import Prog.Prog;
import Prog.Right_turn;
import Prog.TypeProcedure;
import UI.Graphic;
import UI.Gui;
import UI.Menu;
import UI.Menu.MENU;
import Entities.Character;
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
		/*
		Character c = new Character(new Coordonnees(0,0,0),0);
		Procedure main = new Procedure(Color.DEFAUT, TypeProcedure.COMMUN);
		Procedure p1=  new Procedure(Color.DEFAUT, TypeProcedure.COMMUN);
		Procedure p2 =  new Procedure(Color.DEFAUT, TypeProcedure.COMMUN);
		For f = new For(5);
		Action a = new Forward(Color.DEFAUT);
		Action a2 = new Light(Color.DEFAUT);
		Action a3 = new Left_turn(Color.DEFAUT);
		Action a4 = new Right_turn(Color.DEFAUT);
		ReturnProc b = new ReturnProc();
		
		Interpreter.INTERPRETER = new Interpreter();
		main.addProg(a);
		p1.addProg(a2);
		p2.addProg(a2);
	//	p1.addProg(b);
		p1.addProg(a3);
		main.addProg(f);
		main.addProg(p1);
		p2.addProg(a4);

		main.addProg(a4);
		main.addProg(p2);
		c.setMain(main);
		while(c.next()) {}
			
		System.out.println("FINI");*/
		
		
		while (!Input.INPUT.gerer()) //Boucle principale
		{
			if (Graphic.SFML.gerer()) 
				return;
			
			if (Menu.Mymenu != null)
				Menu.Mymenu.gerer();
			
			if (Gui.GUI != null)
				Gui.GUI.gerer();
			
//			if (World.WORLD != null)
//				World.WORLD.gerer();
			
			Graphic.SFML.afficher();
		}
	}
}
