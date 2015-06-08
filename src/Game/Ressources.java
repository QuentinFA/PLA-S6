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
		NUAGE();
		
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
		
		MUSIC.loadFromFile(MUSIC.MARIO, Paths.get("audio/audio.wav"));
	}
}
