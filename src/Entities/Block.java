package Entities;

/**
 * Classe identifiant les blocs affichable a l'ecran.
 * @author edwin
 *
 */
public abstract class Block extends Entities implements Cloneable
{
	public Object clone() throws CloneNotSupportedException {return super.clone();} 
	
	/**
	 * Préparation de la texture
	 */
	public void initialiser() {}
	
	/**
	 * Action effectuée  lorsqu'un personnage interagie avec un block
	 * @param p : Le personnage se trouvant sur le bloc
	 */
	public abstract void perform(Character p);
}
