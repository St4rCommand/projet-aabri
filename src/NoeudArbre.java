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

    public T[] parcoursInfixe() {
        // TODO
        return null;
    }

    public T[] parcoursSuffixe() {
        // TODO
        return null;
    }

    public T[] parcoursPostfix() {
        // TODO
        return null;
    }

    public void noeudPresent(NoeudArbre noeudArbre) {
        // TODO
    }

    public boolean isFeuille() {
        return filsGauche == null && filsDroit == null;
    }

    public boolean isABR() {
        // TODO
        return true;
    }

    public boolean isABRI() {
        // TODO
        return true;
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

    public void setValeur(T valeur) {
        this.valeur = valeur;
    }

    public String toString() {
        return valeur.toString();
    }
}
