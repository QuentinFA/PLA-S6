package Game;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.jsfml.audio.Music;
import org.jsfml.graphics.Texture;
import org.jsfml.system.Vector2f;

public class Ressources 
{
	public enum TEXTURE
	{
		BLOCK(),
		BOUTON_PLAY(),
		BOUTON_SOUND(),
		BOUTON_FLECHE(),
		TITLE(),
		MONDE(),
		NBR_LEVEL(),
		ACTION(),
		PERSO(),
		AURA(),
		NUAGE(),
		CURSOR(),
		RETURN_MENU(),
		CHEST(),
		GUI_MAIN(),
		BLOCK_OCCUPIED(),
		PROC1(),
		PROC2(),
		PLAY_ACTION(),
		RETRY_ACTION(),
		END_OF_GAME(),
		END_OF_GAME_TEXT(),
		NEXT(),
		STAR_FULL(),
		STAR_EMPTY(),
		CHOIX_COULEUR(),
		FORK(),
		FAST_FORWARD();

		private Texture texture;
		
		private TEXTURE() {texture = new Texture();}
		public static void loadFromFile(TEXTURE t, Path p) throws IOException {t.texture.loadFromFile(p);}
		
		public static Texture getTexture(TEXTURE t) {return t.texture;}
		public static Vector2f getHalfSize(TEXTURE t) {return new Vector2f(t.texture.getSize().x/2.f, t.texture.getSize().y/2.f);}
	}
	
	public enum MUSIC
	{
		MARIO();
		
		private Music music;
		
		private MUSIC() {music = new Music();}
		public static void loadFromFile(MUSIC m, Path p) throws IOException {m.music.openFromFile(p);}
		
		public static Music getMusic(MUSIC m) {return m.music;}
	}
	
	public static void initialiser() throws IOException
	{
		TEXTURE.loadFromFile(TEXTURE.BLOCK, Paths.get("images/block.png"));
		TEXTURE.loadFromFile(TEXTURE.BOUTON_PLAY, Paths.get("images/boutonPlay.png"));
		TEXTURE.loadFromFile(TEXTURE.BOUTON_SOUND, Paths.get("images/boutonSound.png"));
		TEXTURE.loadFromFile(TEXTURE.BOUTON_FLECHE, Paths.get("images/fleches.png"));
		TEXTURE.loadFromFile(TEXTURE.TITLE, Paths.get("images/title.png"));
		TEXTURE.loadFromFile(TEXTURE.NUAGE, Paths.get("images/nuages.png"));
		TEXTURE.loadFromFile(TEXTURE.AURA, Paths.get("images/aura.png"));
		
		TEXTURE.loadFromFile(TEXTURE.MONDE, Paths.get("images/monde.png"));
		TEXTURE.loadFromFile(TEXTURE.NBR_LEVEL, Paths.get("images/nbr_level.png"));
		TEXTURE.loadFromFile(TEXTURE.ACTION, Paths.get("images/action.png"));
		TEXTURE.loadFromFile(TEXTURE.PERSO, Paths.get("images/personnage.png"));
		TEXTURE.loadFromFile(TEXTURE.RETURN_MENU, Paths.get("images/return.png"));
		TEXTURE.loadFromFile(TEXTURE.CHEST, Paths.get("images/chest.png"));
		TEXTURE.loadFromFile(TEXTURE.GUI_MAIN, Paths.get("images/gui_main.png"));
		TEXTURE.loadFromFile(TEXTURE.BLOCK_OCCUPIED, Paths.get("images/block_occupied.png"));
		TEXTURE.loadFromFile(TEXTURE.PROC1, Paths.get("images/proc1.png"));
		TEXTURE.loadFromFile(TEXTURE.PROC2, Paths.get("images/proc2.png"));
		TEXTURE.loadFromFile(TEXTURE.PLAY_ACTION, Paths.get("images/play_action.png"));
		TEXTURE.loadFromFile(TEXTURE.RETRY_ACTION, Paths.get("images/retry_action.png"));
		TEXTURE.loadFromFile(TEXTURE.END_OF_GAME, Paths.get("images/end_of_game.png"));
		TEXTURE.loadFromFile(TEXTURE.NEXT, Paths.get("images/next.png"));
		TEXTURE.loadFromFile(TEXTURE.STAR_FULL, Paths.get("images/star_full.png"));
		TEXTURE.loadFromFile(TEXTURE.CHOIX_COULEUR, Paths.get("images/choix_couleur.png"));
		TEXTURE.loadFromFile(TEXTURE.FORK, Paths.get("images/fork.png"));
		TEXTURE.loadFromFile(TEXTURE.FAST_FORWARD, Paths.get("images/fast_forward.png"));
		
		int lower = 0;
		int higher = 4;

		int random = (int)(Math.random() * (higher-lower)) + lower;
		switch(random%4) 
		{
			case 1 : MUSIC.loadFromFile(MUSIC.MARIO, Paths.get("audio/audio1.wav")); break;
			case 2 : MUSIC.loadFromFile(MUSIC.MARIO, Paths.get("audio/audio2.wav")); break;	
			case 3 : MUSIC.loadFromFile(MUSIC.MARIO, Paths.get("audio/audio3.wav")); break;
			default : MUSIC.loadFromFile(MUSIC.MARIO, Paths.get("audio/audio.wav"));
		}
	}
}
