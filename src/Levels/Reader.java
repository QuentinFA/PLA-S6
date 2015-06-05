package Levels;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jdom2.DataConversionException;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;

import Actions.Coordonnees;
import Game.Block;
import Game.BlueBlock;
import Game.LightBlock;
import Game.NormalBlock;
import Game.RedBlock;
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
	public static final String B_LEVEL = "level";
	
	public static Reader READER = new Reader();
	
	public static void read(String arg)
	{
		SAXBuilder sxb = new SAXBuilder();
		int heigth = 0, length = 0, width = 0;
		String name = "Failling world !";
		Element root;
		List<Element> le;
		Document doc;
		List<Block> lb = new ArrayList<Block>();
		
		try
		{
			doc = sxb.build(new File(arg));
		} 
		catch (JDOMException e)
		{
			e.printStackTrace();
			return;
		} 
		catch (IOException e)
		{
			e.printStackTrace();
			return;
		}
		
		root = doc.getRootElement();
		le = root.getChildren();
		
		for(Element e : le)
		{
			// Récupérationdes blocks d'un étage
			if(e.getName() == B_FLOOR)
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
					int x = Integer.valueOf(block.getChild(B_X).getValue());
					int y = Integer.valueOf(block.getChild(B_Y).getValue());
					switch(t)
					{
						case "Normal" :
							lb.add(new NormalBlock(new Coordonnees(x, y, z)));
							break;
						case "Blue" :
							lb.add(new BlueBlock(new Coordonnees(x, y, z)));
							break;
						case "Light" :
							lb.add(new LightBlock(new Coordonnees(x, y, z)));
							break;
						case "Red" :
						default :
							lb.add(new RedBlock(new Coordonnees(x, y, z)));
							break;
					}
				}
			}
			else if(e.getName() == B_LENGTH)
				length = Integer.valueOf(e.getValue());
			else if(e.getName() == B_WIDTH)
				width = Integer.valueOf(e.getValue());
			else if(e.getName() == B_HEIGTH)
				heigth = Integer.valueOf(e.getValue());
			else if(e.getName() == B_LEVEL_NAME)
				name = e.getValue();
		}
		
		World.WORLD = new World(length, width, name, lb);
	}
	
}
