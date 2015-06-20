package Entities;

import java.util.List;
import java.util.ListIterator;
import java.util.Stack;

import org.jsfml.graphics.IntRect;
import org.jsfml.graphics.Sprite;
import org.jsfml.system.Vector2f;

import Game.Controler;
import Game.Interpreter;
import Game.Ressources;
import Game.Ressources.TEXTURE;
import Game.World;
import Prog.Action;
import Prog.Color;
import Prog.Coordonnees;
import Prog.Procedure;
import Prog.Prog;
import UI.Graphic;
import UI.Gui;

/**
 * Classe des personnages du jeu (main ou fork). Represente dans le jeu par un chat.
 *
 */
public class Character extends Entities
{	
	private int orientation; 
	private Sprite bulle = null;
	private Color couleur = Color.DEFAUT;
	private Chest coffre  = null;
	private Action actionCourante = null;
	private int compteurActions;	//Permet de donner le nombre d'étoiles à la fin de la resolution d'un niveau
	private List<Procedure> actionList; //Liste des procedures que le personnage effectue

	Stack<ListIterator<Prog>> pile = new Stack<ListIterator<Prog>>(); //Pile permettant de savoir dans l'interpreteur ou en est le personnage dans ses actions
	Stack<Integer> pileFor = new Stack<Integer>(); //Pile pour savoir a quel for le personnage est

	/**
	 * Constructeur d'un personnage
	 * @param pos : Position initiale du personnage
	 * @param ori : Orientation du personnage
	 */
	public Character(Coordonnees pos, int ori) 
	{
		super(pos);
		orientation = ori;
		compteurActions = 0;
		sprite.setTexture(Ressources.TEXTURE.getTexture(TEXTURE.PERSO));
		setTextureOrientation();
	}

	public void setBulle(Sprite spr) {bulle = spr;}

	public Coordonnees getCoord() {return coord;}
	public void setCoord(Coordonnees pos) {coord = pos;}

	public int getOrientation() {return orientation;}
	public void setOrientation(int ori) 
	{
		orientation = ori;
		setTextureOrientation();
	}
	public void setTextureOrientation() {sprite.setTextureRect(new IntRect(247, 329+82*orientation, 81, 81));}

	/**
	 * Change la couleur du personnage. Utilise pour le ifthen/else
	 * @param c Couleur que l'on va donner au personnage
	 */
	public void setColor(Color c) 
	{
		couleur = c;
		if (c == Color.BLEU)
			this.sprite.setColor(org.jsfml.graphics.Color.CYAN);
		else if (c== Color.ROUGE)
			this.sprite.setColor(org.jsfml.graphics.Color.RED);
		else if (c == Color.VERT)
			this.sprite.setColor(org.jsfml.graphics.Color.GREEN);
		else
			this.sprite.setColor(org.jsfml.graphics.Color.WHITE);
	}
	public Color getColor() {return couleur;}

	public Chest getChest() {return coffre;}
	public void setChest(Chest c) {coffre = c;}

	public void setActionCourante(Action a) {actionCourante = a;}
	public Action getAction () {return actionCourante;}

	public void incrementNbActions() {compteurActions++;}
	public int getNbActions() {return compteurActions;}

	/**
	 * Initialise la liste de procedures du personnage et sa pile. Detecte si le personnage est un personnage fork ou non (pas la meme procedure de depart)
	 */
	public void setMain() 
	{
		actionList = Prog.clone_actionList(Gui.GUI.getFinalActionList());

		ListIterator<Prog> it;

		if (World.WORLD.getCharacterList().size() == 1)
			it = actionList.get(0).getListProcedure().listIterator();
		else
			it = actionList.get(actionList.size()-1).getListProcedure().listIterator();	

		pile.clear();
		pile.push(it);
	}

	public Stack<ListIterator<Prog>> getPile() {return pile;}
	public Stack<Integer> getPileFor() {return pileFor;}

	public void setTextureRect(IntRect rect) {sprite.setTextureRect(rect);}

	/**
	 * Fonction appele en boucle pour chaque personnage. Si on est en train de jouer et qu'aucune action n'est en cours, le personnage demande au control-
	 * ler si il peut tenter de faire une action (workOver). 
	 * @return  Inutile
	 */
	public boolean gerer() 
	{
		if (World.WORLD.isPlaying())
		{
			if (actionCourante == null)
				setTextureOrientation();

			if (actionCourante != null)
			{
				if (actionCourante.execute(this))
				{
					actionCourante = null;
					Controler.CONTROLER.workOver(this);
				}
			}
			else 
				Controler.CONTROLER.workOver(this);
		}

		return true;
	}

	public void afficher()
	{
		Graphic.SFML.draw(sprite);
		if (bulle != null)
			bulle.setPosition(new Vector2f(sprite.getPosition().x, sprite.getPosition().y-60));
	}

	/**
	 * Fonction appele par le controler pour dire au personnage qu'il peut effectuer sa prochaine action. Celui-ci demande a l'interpreteur qu'elle est cette prochaine action et si elle existe, l'execute
	 */
	public void next()
	{
		Action a=null;
		try {a = Interpreter.INTERPRETER.eval(this);}
		catch(StackOverflowError e) 
		{throw new StackOverflowError();}

		if (a!=null)
			use_Action(a);
	}

	/**
	 *  Effectue l'action pour le personnage
	 * @param a Action a effectuer par le personnage
	 * @throws CloneNotSupportedException 
	 */
	public void use_Action(Action a)
	{	
		try {actionCourante = (Action) a.clone();}
		catch (CloneNotSupportedException e) {e.printStackTrace();}
	}
}