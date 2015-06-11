package Levels;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.jdom2.Attribute;
import org.jdom2.DataConversionException;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;

import Entities.Block;
import Game.World;
import Prog.Action;
import Prog.Color;
import Prog.Coordonnees;
import Prog.Orientation;
import UI.Gui;
import UI.Menu;


public class Reader 
{
	public static final String PACKAGE_ACTION = "Prog.";
	public static final String PACKAGE_BLOCK = "Entities.";
	
	public static Reader READER = new Reader();
	
	public static void read(String arg)
	{
		SAXBuilder sxb = new SAXBuilder();
		int nbA = 0, nbP = 0, dir = 0;
		String name = "Failling world !";
		Element root;
		List<Element> le;
		Document doc;
		List<Block> lb = new ArrayList<Block>();
		List<Action> la = new ArrayList<Action>();
		Coordonnees bng = new Coordonnees();
		
		try
		{
			doc = sxb.build(new File(arg));
		} 
		catch (JDOMException | IOException e)
		{
			e.printStackTrace();
			return;
		}
		
		root = doc.getRootElement();
		le = root.getChildren();
		
		for(Element e : le)
		{
			// Recuperation des blocks d'un etage
			if(e.getName().equals(BeaconXML.B_FLOOR))
			{
				int z;
				
				try
				{
					z = e.getAttribute(BeaconXML.B_LEVEL).getIntValue();
				} 
				catch (DataConversionException e1)
				{
					e1.printStackTrace();
					return;
				}
				
				for(Element block : e.getChildren())
				{
					String t = block.getAttribute(BeaconXML.B_BLOCK_TYPE).getValue();
					Attribute begin = block.getAttribute(BeaconXML.B_STARTING_BLOCK);
					int x = Integer.valueOf(block.getChild(BeaconXML.B_X).getValue());
					int y = Integer.valueOf(block.getChild(BeaconXML.B_Y).getValue());
					
					if(begin != null)
						bng = new Coordonnees(x, y, z + 1);
					
					try
					{
						Class<?> c = Class.forName(PACKAGE_BLOCK + t);
						Constructor<?> constructor = c.getConstructor(Coordonnees.class);
						lb.add((Block) constructor.newInstance(new Coordonnees(x, y, z)));
					} 
					catch (ClassNotFoundException | SecurityException | IllegalArgumentException | NoSuchMethodException | InstantiationException | IllegalAccessException | InvocationTargetException e1)
					{
						System.out.println("Invalid XML format :\n\t" + e1.toString());
						return;
					}
				}
			}
			else if(e.getName().equals(BeaconXML.B_LEVEL_NAME))
				name = e.getValue();
			else if(e.getName().equals(BeaconXML.B_ACTION_LIST))
			{
				for(Element a : e.getChildren())
				{
					try
					{
						Class<?> c = Class.forName(PACKAGE_ACTION + a.getValue());
						Constructor<?> constructor = c.getConstructor(Color.class);
						la.add((Action) constructor.newInstance(Color.DEFAUT));
					} 
					catch (ClassNotFoundException | NoSuchMethodException | SecurityException | InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e1)
					{
						System.out.println("Invalid XML format :\n\t" + e1.toString());
						return;
					}
				}
			}
			else if(e.getName().equals(BeaconXML.B_FUNCTIONS))
			{
				for(Element f : e.getChildren())
				{
					if(f.getName().equals(BeaconXML.B_MAIN))
					{
						try {
							nbA = f.getAttribute(BeaconXML.B_ACTION_MAIN).getIntValue();
						} 
						catch (DataConversionException e1) {
 							e1.printStackTrace();
						}
					}
					else if(f.getName().equals(BeaconXML.B_PROCEDURE))
					{
						try
						{
							nbP = f.getAttribute(BeaconXML.B_PROCEDURE_NB).getIntValue();
						} 
						catch (DataConversionException e1)
						{
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}
				}
			}
			else if(e.getName().equals(BeaconXML.B_STARTING_DIRECTION))
			{
				switch(e.getValue())
				{
					case "NORTH" :
						dir = Orientation.NORTH;
						break;
					case "SOUTH" :
						dir = Orientation.SOUTH;
						break;
					case "EAST" :
						dir = Orientation.EAST;
						break;
					case "WEST" :
					default :
						dir = Orientation.WEST;
						break;
				}
			}
		}
		
		Set<Block> set = new HashSet<Block>();
		set.addAll(lb);
		lb = new ArrayList<Block>(set);
		System.out.println(nbP);
		Menu.Mymenu = null;
		World.WORLD = new World(name, lb, bng, dir, la);
		Gui.GUI = new Gui(nbA, nbP);
	}
	
}
