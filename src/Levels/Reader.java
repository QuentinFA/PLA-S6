package Levels;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import org.jdom2.Attribute;
import org.jdom2.DataConversionException;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;

import Actions.Action;
import Actions.Color;
import Actions.Coordonnees;
import Entities.Block;
import Game.World;


public class Reader 
{
	public static final String B_LEVEL_NAME = "level_name";
	public static final String B_LENGTH = "length";
	public static final String B_HEIGTH = "heigth";
	public static final String B_WIDTH = "width";
	public static final String B_FLOOR = "floor";
	public static final String B_BLOCK = "block";
	public static final String B_BLOCK_TYPE = "type";
	public static final String B_X = "x";
	public static final String B_Y = "y";
	public static final String B_STARTING_BLOCK = "is_start";
	public static final String B_LEVEL = "level";
	public static final String B_ACTION_LIST = "action_list";
	public static final String B_ACTION = "action";
	public static final String B_FUNCTIONS = "functions";
	public static final String B_MAIN = "main";
	
	public static Reader READER = new Reader();
	
	public static void read(String arg)
	{
		SAXBuilder sxb = new SAXBuilder();
		int length = 0, width = 0;
		String name = "Failling world !";
		Element root;
		List<Element> le;
		Document doc;
		List<Block> lb = new ArrayList<Block>();
		List<Action> la = new ArrayList<Action>();
		Coordonnees bng = null;
		
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
			if(e.getName().equals(B_FLOOR))
			{
				int z;
				
				try
				{
					z = e.getAttribute(B_LEVEL).getIntValue();
				} 
				catch (DataConversionException e1)
				{
					e1.printStackTrace();
					return;
				}
				
				for(Element block : e.getChildren())
				{
					String t = block.getAttribute(B_BLOCK_TYPE).getValue();
					Attribute begin = block.getAttribute(B_STARTING_BLOCK);
					int x = Integer.valueOf(block.getChild(B_X).getValue());
					int y = Integer.valueOf(block.getChild(B_Y).getValue());
					
					if(begin != null)
						bng = new Coordonnees(x, y, z + 1);
					
					try
					{
						Class<?> c = Class.forName("Entities." + t);
						Constructor<?> constructor = c.getConstructor(Coordonnees.class);
						lb.add((Block) constructor.newInstance(new Coordonnees(x, y, z)));
					} 
					catch (ClassNotFoundException | SecurityException | IllegalArgumentException | NoSuchMethodException | InstantiationException | IllegalAccessException | InvocationTargetException e1)
					{
						// TODO Problème avec le XML
						e1.printStackTrace();
					}
				}
			}
			else if(e.getName().equals(B_LENGTH))
				length = Integer.valueOf(e.getValue());
			else if(e.getName().equals(B_WIDTH))
				width = Integer.valueOf(e.getValue());
			else if(e.getName().equals(B_LEVEL_NAME))
				name = e.getValue();
			else if(e.getName().equals(B_ACTION_LIST))
			{
				for(Element a : e.getChildren())
				{
					try
					{
						Class<?> c = Class.forName("Actions." + a.getValue());
						Constructor<?> constructor = c.getConstructor(Color.class);
						la.add((Action) constructor.newInstance(Color.DEFAUT));
					} 
					catch (ClassNotFoundException | NoSuchMethodException | SecurityException | InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e1)
					{
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
			else if(e.getName().equals(B_FUNCTIONS))
			{
				for(Element f : e.getChildren())
				{
					// TODO
				}
			}
		}
		
		World.WORLD = new World(length, width, name, lb, bng);
		System.out.println(la.toString());
		// TODO New GUI
	}
	
}
