public class NoeudABRI<T extends Comparable> extends NoeudArbre {

    protected NoeudABRI<T> filsGauche;
    protected NoeudABRI<T> filsDroit;

    public NoeudABRI (T pValeur) {
        this.valeur = pValeur;
    }

/*    public void setFilsGauche(NoeudABRI<T> filsGauche) {
    }

    public void setFilsDroits(NoeudABRI<T> filsDroits) {
    }*/

    public void ajouterNoeud(NoeudABRI<T> noeud) {
        int value = this.valeur.compareTo(noeud.valeur);

        if (value == 0) {

        // Si la valeur du noeud courant est inférieure à la valeur du noeud à ajouter
        } else if (value < 0 ) {

            if (this.filsGauche == null) {
                this.filsGauche = noeud;
            } else {
                this.filsGauche.ajouterNoeud(noeud);
            }
        // Si la valeur du noeud courant est supérieure à la valeur du noeud à ajouter
        } else if (value > 0) {

            if (this.filsDroit == null) {
                this.filsDroit = noeud;
            } else {
                this.filsDroit.ajouterNoeud(noeud);
            }
        }
    }

    @Override
    public NoeudABRI<T> getFilsGauche() {
        return filsGauche;
    }

    @Override
    public NoeudABRI<T> getFilsDroit() {
        return filsDroit;
    }

    /*    protected int min;
    protected int max;

    public NoeudABRI(int min, int max) {
        this.min = min;
        this.max = max;
    }

    public int getMin() {
        return min;
    }

    public void setMin(int min) {
        this.min = min;
    }

    public int getMax() {
        return max;
    }

    public void setMax(int max) {
        this.max = max;
    }*/

    public String toString() {
        String string = this.valeur.toString();

        if (this.filsGauche != null)
            string = string.concat(":"+this.filsGauche.toString());
        if (this.filsDroit != null)
            string = string.concat(":"+this.filsDroit.toString());

        return string;
    }
}
