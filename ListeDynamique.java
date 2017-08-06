
public class ListeDynamique {

	private Maillon head;
	private int size = 0;

	/**
	 * Enleve un maillon de la liste
	 * @param indice
	 * @return
	 * @throws Exception
	 */
	public void remove(int indice) throws Exception {

		/**
		 * STRATEGIE: On d�ｿｽroule �ｿｽ partir de la tete, jusqu'a indice -1
		 * Si on a par ex, 1 5 10 15 20 25 dans la liste est qu'on cherche
		 * l'indice 3 (le 15), alors on avance jusqu'au 10, puis, on met le
		 * setNext du 10 au 20. Le maillon 10 est donc enlev�ｿｽ.
		 *
		 * Dans le cas particulier de l'indice 0, on n'a pas besoin de d�ｿｽrouler.
		 */

		if(indice >= size || indice < 0){
			throw new Exception("Indice n'existe pas");
		}

		Maillon tmp = this.head;
		int count = 0;
		this.size--;

		while (indice - 1 > 0 && count != indice - 1 && tmp.getNext() != null) {
			tmp = tmp.getNext();
			count++;
		}

		if (count == indice - 1) {

			tmp.setNext(tmp.getNext().getNext());
		} else if (indice == 0) {

			this.head = this.head.getNext();
		}

	}

	/**
	 * Recupere la valeur �ｿｽ l'indice
	 * @param indice
	 * @return
	 */
	public Navire get(int indice) {
		Maillon tmp = this.head;
		int count = 0;
		while (count != indice && tmp.getNext() != null) {
			tmp = tmp.getNext();
			count++;
		}

		return tmp.getValeur();
	}

	/**
	 * Ajoute un element
	 * @param value
	 */
	public void add(Navire value) {

		/**
		 * Strat�ｿｽgie: On d�ｿｽroule jusqu'�ｿｽ la fin de la liste puis
		 * on ajoute le nouvel element
		 */

		if (this.estVide()) {
			this.head = new Maillon(value);
		} else {

			Maillon tmp = this.head;
			while (tmp.getNext() != null) {
				tmp = tmp.getNext();
			}
			tmp.setNext(new Maillon(value));
		}
		this.size++;

	}
	
	/**
	 * estVide
	 * @return
	 */
	public boolean estVide() {
		return this.size == 0;
	}



	public int getSize(){
		return this.size;
	}



	/**
	 * Represente la class Maillon pour les file et pile a chainage dynamique simple
	 *
	 * @author math
	 */
	public class Maillon {

		//ATTRIBUTS
		private Navire unenavire;
		private Maillon next;

		/**
		 * Constructeur
		 *
		 * @param valeur
		 */
		public Maillon(Navire unenavire) {
			this.unenavire = unenavire;
		}

		/**
		 *
		 * @return
		 */
		public Navire getValeur() {
			return unenavire;
		}

		/**
		 *
		 * @param valeur
		 */
		public void setValeur(Navire unenavire) {
			this.unenavire = unenavire;
		}

		/**
		 *
		 * @return
		 */
		public Maillon getNext() {
			return next;
		}

		/**
		 *
		 * @param next
		 */
		public void setNext(Maillon next) {
			this.next = next;
		}

	}

}
