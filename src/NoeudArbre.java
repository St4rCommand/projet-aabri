public class NoeudArbre<T extends Comparable> {

    protected NoeudArbre<T> filsGauche;
    protected NoeudArbre<T> filsDroit;
    protected T valeur;

    public NoeudArbre() {
    }

    public NoeudArbre(T valeur, NoeudArbre<T> filsDroit, NoeudArbre<T> filsGauche) {
        this.valeur = valeur;
        this.filsDroit = filsDroit;
        this.filsGauche = filsGauche;
    }

    public NoeudArbre<T> getFilsGauche() {
        return filsGauche;
    }

    public NoeudArbre<T> getFilsDroit() {
        return filsDroit;
    }

    public T getValeur() {
        return valeur;
    }

    public String toString() {
        return valeur.toString();
    }
}
