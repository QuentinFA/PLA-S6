package Levels;

import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

import org.jdom2.Attribute;
import org.jdom2.Content;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;

import Entities.Block;
import Entities.BlockComparator;
import Game.World;
import Prog.Action;
import Prog.Coordonnees;
import Prog.Orientation;

public class Writer
{
	public static Writer WRITER = new Writer();
	
	/**
	 * Création d'un niveau au format XML
	 * @param fileName : Le nom du fichier.xml
	 * @param levelName : Le nom du niveau
	 * @param blockList : La liste des blocs composant le niveau
	 * @param startingOrientation : L'orientation de début
	 * @param startingPosition : La position de départ (doit correspondre aux coordonnées d'un bloc existant)
	 * @param allowedActions : Liste des actions autorisées pour ce niveau
	 * @param nbActionMain : Le nombre d'actions possibles dans la procédure main
	 * @param nbProcedures : Le nombre de procédure autorisées pour le niveau
	 */
	public static void write(String fileName, World w, List<Action> allowedActions,
			int nbActionMain, int nbProcedures)
	{
		Element root = new Element(BeaconXML.B_MAP);
		Document doc = new Document(root);
		List<Content> content = new ArrayList<Content>();
		String strtDir;
		int floor_level;
		
		switch(w.getStartingOrientation())
		{
			case Orientation.NORTH :
				strtDir = "NORTH";
				break;
			case Orientation.EAST :
				strtDir = "EAST";
				break;
			case Orientation.SOUTH :
				strtDir = "SOUTH";
				break;
			case Orientation.WEST :
			default :
				strtDir = "WEST";
				break;
		}
		
		content.add(new Element(BeaconXML.B_LEVEL_NAME).setText(w.getName()));
		content.add(new Element(BeaconXML.B_STARTING_DIRECTION).setText(strtDir));
		
		w.getBlockList().sort(new BlockComparator());
		
		Element floor = new Element(BeaconXML.B_FLOOR);
		floor_level = 0;
		floor.setAttribute(new Attribute(BeaconXML.B_LEVEL, String.valueOf(floor_level)));
		
		for(Block b : w.getBlockList())
		{
			if(b.getCoord().getZ() != floor_level)
			{
				content.add(floor);
				floor = new Element(BeaconXML.B_FLOOR);
				floor_level++;
				floor.setAttribute(new Attribute(BeaconXML.B_LEVEL, String.valueOf(floor_level)));
			}
			Element block = new Element(BeaconXML.B_BLOCK);
			if(b.getCoord().equals(new Coordonnees(w.getStartingPosition().getX(), 
					w.getStartingPosition().getY(),
					w.getStartingPosition().getZ() - 1)))
				block.setAttribute(BeaconXML.B_STARTING_BLOCK, "true");
			block.setAttribute(BeaconXML.B_BLOCK_TYPE, b.getClass().getSimpleName());
			block.addContent(new Element(BeaconXML.B_X).setText(String.valueOf(b.getCoord().getX())));
			block.addContent(new Element(BeaconXML.B_Y).setText(String.valueOf(b.getCoord().getY())));
			
			floor.addContent(block);
		}
		
		content.add(floor);
		
		Element a_elem = new Element(BeaconXML.B_ACTION_LIST);
		
		for(Action a : allowedActions)
			a_elem.addContent(new Element(BeaconXML.B_ACTION).setText(a.getClass().getSimpleName()));
		
		content.add(a_elem);
		
		Element f = new Element(BeaconXML.B_FUNCTIONS);
		f.addContent(new Element(BeaconXML.B_MAIN).setAttribute(BeaconXML.B_ACTION_MAIN, String.valueOf(nbActionMain)));
		if(nbProcedures > 0)
			f.addContent(new Element(BeaconXML.B_PROCEDURE).setAttribute(BeaconXML.B_PROCEDURE_NB, String.valueOf(nbProcedures)));
		
		content.add(f);
		
		root.setContent(content);
		
		// Ecriture du fichier
		try
		{
			XMLOutputter out = new XMLOutputter(Format.getPrettyFormat());
			out.output(doc, new FileOutputStream(fileName));
		}
		catch (java.io.IOException e){}
	}
}
