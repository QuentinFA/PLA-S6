package LevelsRW;

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
import Entities.Blocks.BlockComparator;
import Game.World;
import Prog.Action;
import Prog.Coordonnees;
import Prog.Orientation;

/**
 * Ecriture du XML correspondant à un objet World
 * Not up to date
 */
@Deprecated
public class Writer
{
	public static Writer WRITER = new Writer();
	
	/**
	 * Creation d'un niveau au format XML
	 * @param fileName : Le nom du fichier.xml
	 * @param levelName : Le nom du niveau
	 * @param blockList : La liste des blocs composant le niveau
	 * @param startingOrientation : L'orientation de debut
	 * @param startingPosition : La position de depart (doit correspondre aux coordonnees d'un bloc existant)
	 * @param allowedActions : Liste des actions autorisees pour ce niveau
	 * @param nbActionMain : Le nombre d'actions possibles dans la procedure main
	 * @param nbProcedures : Le nombre de procedure autorisees pour le niveau
	 */
	public static void write(String fileName, World w, int nbActionMain, int nbProcedures)
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
		
		for (Block b : w.getBlockList())
		{
			if (b.getCoord().getZ() != floor_level)
			{
				content.add(floor);
				floor = new Element(BeaconXML.B_FLOOR);
				floor_level++;
				floor.setAttribute(new Attribute(BeaconXML.B_LEVEL, String.valueOf(floor_level)));
			}
			Element block = new Element(BeaconXML.B_BLOCK);
			if (b.getCoord().equals(new Coordonnees(w.getStartingCoord().getX(), w.getStartingCoord().getY(), w.getStartingCoord().getZ() - 1)))
				block.setAttribute(BeaconXML.B_STARTING_BLOCK, "true");
			block.setAttribute(BeaconXML.B_BLOCK_TYPE, b.getClass().getSimpleName());
			block.addContent(new Element(BeaconXML.B_X).setText(String.valueOf(b.getCoord().getX())));
			block.addContent(new Element(BeaconXML.B_Y).setText(String.valueOf(b.getCoord().getY())));
			
			floor.addContent(block);
		}
		
		content.add(floor);
		
		Element a_elem = new Element(BeaconXML.B_ACTION_LIST);
		
		for (Action a : w.getActionList())
			a_elem.addContent(new Element(BeaconXML.B_ACTION).setText(a.getClass().getSimpleName()));
		
		content.add(a_elem);
		
		Element f = new Element(BeaconXML.B_FUNCTIONS);
		f.addContent(new Element(BeaconXML.B_MAIN).setAttribute(BeaconXML.B_ACTION_MAIN,
				String.valueOf(nbActionMain)));
		
		content.add(f);
		
		root.setContent(content);
		
		//Ecriture du fichier
		try
		{
			XMLOutputter out = new XMLOutputter(Format.getPrettyFormat());
			out.output(doc, new FileOutputStream(fileName));
		}
		catch (java.io.IOException e){}
	}
}
