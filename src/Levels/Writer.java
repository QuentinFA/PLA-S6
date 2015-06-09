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

import Actions.Action;
import Actions.Color;
import Actions.Coordonnees;
import Actions.Forward;
import Actions.Orientation;
import Entities.Block;
import Entities.BlockComparator;
import Entities.NormalBlock;

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
	public static void write(String fileName, String levelName, List<Block> blockList,
			int startingOrientation, Coordonnees startingPosition, List<Action> allowedActions,
			int nbActionMain, int nbProcedures)
	{
		Element root = new Element(BeaconXML.B_MAP);
		Document doc = new Document(root);
		List<Content> content = new ArrayList<Content>();
		String strtDir;
		int floor_level;
		
		switch(startingOrientation)
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
		
		content.add(new Element(BeaconXML.B_LEVEL_NAME).setText(levelName));
		content.add(new Element(BeaconXML.B_STARTING_DIRECTION).setText(strtDir));
		
		blockList.sort(new BlockComparator());
		
		Element floor = new Element(BeaconXML.B_FLOOR);
		floor_level = 0;
		floor.setAttribute(new Attribute(BeaconXML.B_LEVEL, String.valueOf(floor_level)));
		
		for(Block b : blockList)
		{
			if(b.getCoord().getZ() != floor_level)
			{
				content.add(floor);
				floor = new Element(BeaconXML.B_FLOOR);
				floor_level++;
				floor.setAttribute(new Attribute(BeaconXML.B_LEVEL, String.valueOf(floor_level)));
			}
			Element block = new Element(BeaconXML.B_BLOCK);
			if(b.getCoord().equals(startingPosition))
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
	
	public static void main(String args[])
	{
		List<Block> bl = new ArrayList<Block>();
		bl.add(new NormalBlock(new Coordonnees(0, 0, 0)));
		bl.add(new NormalBlock(new Coordonnees(1, 0, 0)));
		bl.add(new NormalBlock(new Coordonnees(1, 0, 1)));
		
		List<Action> ba = new ArrayList<Action>();
		ba.add(new Forward(Color.DEFAUT));
		
		write("levels/test.xml", "test", bl, Orientation.NORTH, new Coordonnees(0, 0, 0), ba, 1, 1);
	}
}
