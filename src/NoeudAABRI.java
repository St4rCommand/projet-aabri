//public class NoeudAABRI extends NoeudABR  {
public class NoeudAABRI<C extends NoeudArbre> {
    protected T min;
    protected T max;

    protected NoeudAABRI filsGauche;
    protected NoeudAABRI filsDroit;

    protected C<T> valeur;

    public NoeudAABRI(T min, T max, NoeudABRI<T> valeur) {
        this.min = min;
        this.max = max;
        this.valeur = valeur;
    }

    public void ajouterNoeud(NoeudAABRI noeud) {
        int value = this.min.compareTo(noeud.getMin());

        if (value > 0 ) {

            if (this.filsGauche == null) {
                this.filsGauche = noeud;
            } else {
                this.filsGauche.ajouterNoeud(noeud);
            }
            // Si la valeur du noeud courant est supérieure à la valeur du noeud à ajouter
        } else if (value < 0) {

            if (this.filsDroit == null) {
                this.filsDroit = noeud;
            } else {
                this.filsDroit.ajouterNoeud(noeud);
            }
        }
    }

    public void insererEntier(T valeurABRI) {
        int value = this.min.compareTo(valeur);


        if (valeurABRI >= this.min & valeurABRI <= this.max) {
            this.valeur.ajouterNoeud(new NoeudABRI<Integer>(valeurABRI));
        } else if (valeurABRI < min) {
            this.filsGauche.insererEntier(valeurABRI);
        } else if (valeurABRI > max) {
            this.filsDroit.insererEntier(valeurABRI);
        }
    }


    /*public boolean isAABRI(){

        return isAABRI(0,0);
    }*/

    /*
    private boolean isAABRI(int minPrec, int maxPrec){

        boolean isAABRI = true;

        if (this.min <= minPrec) {
            isAABRI = false;
        }

        if ()

        // ABR (sur les valeurs min)

        // interval min/max disjoints
        // ABRI est un ABRI

        return false;
    }*/

    public T getMin() {
        return min;
    }

    public T getMax() {
        return max;
    }

    public NoeudABRI getValeur() {
        return valeur;
    }

    public NoeudAABRI getFilsGauche() {
        return filsGauche;
    }

    public NoeudAABRI getFilsDroit() {
        return filsDroit;
    }

    public String toString() {
        String string = this.min+":"+this.max+";"+this.valeur.toString()+"\n";

        if (this.filsGauche != null)
            string = string.concat(this.filsGauche.toString());

        if (this.filsDroit != null)
            string = string.concat(this.filsDroit.toString());


        return string;
    }
}
