//public class NoeudAABRI extends NoeudABR  {
public class NoeudAABRI {
    protected Integer min;
    protected Integer max;

    protected NoeudAABRI filsGauche;
    protected NoeudAABRI filsDroit;

    protected NoeudABRI valeur;

    public NoeudAABRI(int min, int max, NoeudABRI valeur) {
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

    public int getMin() {
        return min;
    }

    public int getMax() {
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
}
