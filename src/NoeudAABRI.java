//public class NoeudAABRI extends NoeudABR  {
public class NoeudAABRI<T extends Comparable> {
    protected T min;
    protected T max;

    protected NoeudAABRI<T> filsGauche;
    protected NoeudAABRI<T> filsDroit;

    protected NoeudABRI<T> abri;

    public NoeudAABRI(T min, T max, NoeudABRI<T> abri) {
        this.min = min;
        this.max = max;
        this.abri = abri;
    }

    public void ajouterNoeud(NoeudAABRI noeud) {
        int value = this.min.compareTo(noeud.getMin());

        if (value > 0 ) {

            if (this.filsGauche == null) {
                this.filsGauche = noeud;
            } else {
                this.filsGauche.ajouterNoeud(noeud);
            }
            // Si la abri du noeud courant est supérieure à la abri du noeud à ajouter
        } else if (value < 0) {

            if (this.filsDroit == null) {
                this.filsDroit = noeud;
            } else {
                this.filsDroit.ajouterNoeud(noeud);
            }
        }
    }

    public void insererValeur(T valeur) {
        int comparaisonMin = this.min.compareTo(valeur);
        int comparaisonMax = this.max.compareTo(valeur);


        if (comparaisonMin <= 0 & comparaisonMax >= 0) {
            this.abri.ajouterNoeud(new NoeudABRI<>(valeur));
        } else if (comparaisonMin > 0) {
            this.filsGauche.insererValeur(valeur);
        } else if (comparaisonMax < 0) {
            this.filsDroit.insererValeur(valeur);
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

    public NoeudABRI getAbri() {
        return abri;
    }

    public NoeudAABRI getFilsGauche() {
        return filsGauche;
    }

    public NoeudAABRI getFilsDroit() {
        return filsDroit;
    }

    public String toString() {
        String string = this.min+":"+this.max+";"+this.abri.toString()+"\n";

        if (this.filsGauche != null)
            string = string.concat(this.filsGauche.toString());

        if (this.filsDroit != null)
            string = string.concat(this.filsDroit.toString());


        return string;
    }
}
