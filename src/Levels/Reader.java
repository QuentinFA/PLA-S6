package Levels;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;

import Actions.Coordonnees;
import Game.Block;
import Game.World;

public class Reader 
{
	public static Reader READER = new Reader();
	
	public void read(String arg)
	{
		//Lecture
		try
		{
			InputStream ips = new FileInputStream(arg); 
			InputStreamReader ipsr = new InputStreamReader(ips);
			BufferedReader br = new BufferedReader(ipsr);
			
			int width = Integer.parseInt(br.readLine());
			int height = Integer.parseInt(br.readLine());
			
			new World(width, height);
			
			Coordonnees pos = new Coordonnees(0, width-1, 0);
			
			String str;
			while ((str = br.readLine()) != null)
			{
				if (!str.isEmpty())
				{
					for (int i=0; i < width; i++)
					{
						char c = str.charAt(i);
						if (Character.getNumericValue(c) == 1)
							World.WORLD.addBlock(new Block(Character.getNumericValue(c), new Coordonnees(pos)));
						//else if ...
						
						pos.x += 1;
					}
					pos.x = 0;
					pos.y -= 1;
				}
				else
				{
					pos.y = width-1;
					pos.z += 1;
				}
			}
			br.close(); 
		}		
		catch (Exception e)
		{
			System.out.println("Impossible d'ouvir le fichier " + arg + " ...");
		}
	}
	
}
