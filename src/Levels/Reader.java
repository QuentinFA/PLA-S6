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
import Entities.Blocks.NormalBlock;
import Entities.Blocks.TeleporterBlock;
import Entities.Blocks.Chests.ChestForward;
import Game.World;
import Prog.Action;
import Prog.Color;
import Prog.Coordonnees;
import Prog.Orientation;
import Prog.Procedure;
import Prog.Prog;
import Prog.NormalActions.Forward;
import UI.Gui;
import UI.Menu;

/**
 * Lecture d'un XML et création du niveau correspondant
 *
 */
public class Reader 
{
	public static final String PACKAGE_ACTION = Forward.class.getPackage().getName() + ".";
	public static final String PACKAGE_BLOCK = NormalBlock.class.getPackage().getName() + ".";
	public static final String PACKAGE_BLOCK_CHEST = ChestForward.class.getPackage().getName() + ".";
	public static final String CLASS_TP = "TeleporterBlock";
	public static final String PACKAGE_PROG = Action.class.getPackage().getName() + ".";
	
	public static Reader READER = new Reader();
	
	/**
	 * Lecture d'un fichie XML correspondant à un niveau
	 * @param arg : Le nom du fichiers
	 */
	public static void read(String arg)
	{
		SAXBuilder sxb = new SAXBuilder();
		int nbA = 0, dir = 0, min = 0, max = 0;
		String name = "Failling world !";
		Element root;
		List<Element> le;
		Document doc;
		List<Block> lb = new ArrayList<Block>();
		List<Action> la = new ArrayList<Action>();
		List<Procedure> listSolution = new ArrayList<Procedure>();
		Coordonnees bng = new Coordonnees();
		
		//Initialisation pre-cr�ation
		TeleporterBlock.initialiseStack();
		
		// Chacun son style informatique, merci de ne pas changer
		try 
		{
			doc = sxb.build(new File(arg));} //Troll
		catch (JDOMException | IOException e)
		{
			e.printStackTrace();
			return;
		}
		
		root = doc.getRootElement();
		le = root.getChildren();
		
		for (Element e : le)
		{
			// Recuperation des blocks d'un etage
			if (e.getName().equals(BeaconXML.B_FLOOR))
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
				
				for (Element block : e.getChildren())
				{
					// Récupération des valeurs carctérisant le bloc
					String t = block.getAttribute(BeaconXML.B_BLOCK_TYPE).getValue();
					Attribute begin = block.getAttribute(BeaconXML.B_STARTING_BLOCK);
					int x = Integer.valueOf(block.getChild(BeaconXML.B_X).getValue());
					int y = Integer.valueOf(block.getChild(BeaconXML.B_Y).getValue());
					
					// Si c'est la position de départ
					if (begin != null)
						bng = new Coordonnees(x, y, z + 1);
					
					try
					{
						Class<?> c = Class.forName(PACKAGE_BLOCK + t);
						if(t.equals(CLASS_TP))
						{
							// Si c'est un téléporteur (données supplémentaires)
							String d = block.getAttribute(BeaconXML.B_TP_DEST).getValue();
							Constructor<?> constructor = 
									c.getConstructor(Coordonnees.class, TeleporterBlock.class);
							Constructor<?> constructor2 = c.getConstructor(Coordonnees.class);
							int xx = Integer.valueOf(d.split(",")[0]),
									yy = Integer.valueOf(d.split(",")[1]),
									zz = Integer.valueOf(d.split(",")[2]);
							Block tp = (Block) constructor.newInstance(new Coordonnees(x, y, z), 
									constructor2.newInstance(new Coordonnees(xx, yy, zz)));
							lb.add(tp);
							lb.add(((TeleporterBlock) tp).getDest());
							((TeleporterBlock) tp).lier();
						}
						else
						{
							// Sinon : bloc normal
							Constructor<?> constructor = c.getConstructor(Coordonnees.class);
							lb.add((Block) constructor.newInstance(new Coordonnees(x, y, z)));
						}
					} 
					catch (SecurityException | IllegalArgumentException 
							| NoSuchMethodException | InstantiationException | IllegalAccessException 
							| InvocationTargetException e1)
					{
						System.out.println("Invalid XML format :\n\t" + e1.toString());
						return;
					}
					catch (ClassNotFoundException e1)
					{
						try
						{
							Class<?> c = Class.forName(PACKAGE_BLOCK_CHEST + t);							
							Constructor<?> constructor = c.getConstructor(Coordonnees.class);
							lb.add((Block) constructor.newInstance(new Coordonnees(x, y, z)));
						} catch (ClassNotFoundException | NoSuchMethodException | SecurityException 
								| InstantiationException | IllegalAccessException | IllegalArgumentException 
								| InvocationTargetException e2)
						{
							System.out.println("Invalid XML format :\n\t" + e2.toString());
						}
					}
				}
			}
			else if (e.getName().equals(BeaconXML.B_LEVEL_NAME))
				name = e.getValue();
			else if (e.getName().equals(BeaconXML.B_ACTION_LIST))
			{
				for(Element a : e.getChildren())
					try
					{
						Class<?> c = Class.forName(PACKAGE_ACTION + a.getValue());
						Constructor<?> constructor = c.getConstructor(Color.class);
						la.add((Action) constructor.newInstance(Color.DEFAUT));
					} 
					catch (ClassNotFoundException | NoSuchMethodException | SecurityException 
							| InstantiationException | IllegalAccessException | IllegalArgumentException 
							| InvocationTargetException e1)
					{
						System.out.println("Invalid XML format :\n\t" + e1.toString());
						return;
					}
			}
			else if (e.getName().equals(BeaconXML.B_FUNCTIONS))
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
				}
			}
			else if (e.getName().equals(BeaconXML.B_STARTING_DIRECTION))
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
			else if(e.getName().equals(BeaconXML.B_SCORE))
			{
				for(Element el : e.getChildren())
				{
					if(el.getName().equals(BeaconXML.B_SCORE_MIN))
						min = Integer.valueOf(el.getValue());
					if(el.getName().equals(BeaconXML.B_SCORE_MAX))
						max = Integer.valueOf(el.getValue());
				}
			}
			
			else if(e.getName().equals(BeaconXML.B_SOLUTION))
			{	
				int index=0;
				for(Element proc : e.getChildren())
				{
					try{
						if(proc.getName().equals(BeaconXML.B_MAIN_SOL))
						{	
							Class<?> c = Class.forName(PACKAGE_PROG + "Procedure");
							Constructor<?> constructor = c.getConstructor(Color.class, int.class);
							listSolution.add((Procedure) constructor.newInstance(Color.DEFAUT,index));
						}
						else if(proc.getName().equals(BeaconXML.B_PROC1_SOL))
						{
							index++;
							Class<?> c = Class.forName(PACKAGE_PROG + "Procedure");
							Constructor<?> constructor = c.getConstructor(Color.class, int.class);
							listSolution.add((Procedure) constructor.newInstance(Color.DEFAUT,index));
						}
						else if(proc.getName().equals(BeaconXML.B_PROC2_SOL))
						{
							index++;
							Class<?> c = Class.forName(PACKAGE_PROG + "Procedure");
							Constructor<?> constructor = c.getConstructor(Color.class, int.class);
							listSolution.add((Procedure) constructor.newInstance(Color.DEFAUT,index));
						}
						else if(proc.getName().equals(BeaconXML.B_FORK_SOL))
						{	
							index++;
							Class<?> c = Class.forName(PACKAGE_PROG + "Procedure");
							Constructor<?> constructor = c.getConstructor(Color.class, int.class);
							listSolution.add((Procedure) constructor.newInstance(Color.DEFAUT,index));
						}
					}
					catch (ClassNotFoundException | NoSuchMethodException | SecurityException | InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e1){
								e1.printStackTrace();
					}
						
					for(Element act : proc.getChildren())
					{
						try {
							//Recuperation de la couleur dans xml
							String coolraoul = act.getAttribute(BeaconXML.B_ACTION_SOL_COLOR).getValue();
							Color cool;
							cool = Color.stringToColor(coolraoul);
							
							//Recuperation de la valeur du for
							Attribute des = act.getAttribute(BeaconXML.B_FOR_LOOP);
								
							//Ajout de l'actions dans la liste de procedure
							if( des == null){
								Class<?> c = Class.forName(PACKAGE_ACTION + act.getValue());
								Constructor<?> constructor = c.getConstructor(Color.class);
								listSolution.get(index).getListProcedure().add((Prog) constructor.newInstance(cool));
							}
							else
							{
								int valDes = Integer.valueOf(des.getValue());
								Class<?> c = Class.forName(PACKAGE_ACTION + act.getValue());
								Constructor<?> constructor = c.getConstructor(Color.class, int.class);
								listSolution.get(index).getListProcedure().add((Prog) constructor.newInstance(cool,valDes));
							}
						} 
						catch (ClassNotFoundException | NoSuchMethodException | SecurityException| InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e1){
							e1.printStackTrace();
						}
					}
				}
			}
		}
		
		Set<Block> set = new HashSet<Block>();
		set.addAll(lb);
		lb = new ArrayList<Block>(set);
		
		World.WORLD = new World(name, lb, bng, dir, la, min, max, listSolution);
		Gui.GUI = new Gui(nbA);
		
		Menu.Mymenu = null;
	}
	
}
