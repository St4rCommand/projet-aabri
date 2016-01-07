import java.util.Random;

public class NoeudABRI<T extends Comparable> extends NoeudArbre {

    protected NoeudABRI<T> filsGauche = null;
    protected NoeudABRI<T> filsDroit = null;

    public NoeudABRI (T valeur) {
        this.valeur = valeur;
    }

    /**
     * Insérer un noeud dans l'arbre où le sous-arbre
     * La fonction ne fait rien si la abri est déjà présente
     * La fonction ne fait rien si la abri
     *
     * @param noeud
     */
    public void ajouterNoeud(NoeudABRI<T> noeud) {
        int value = this.valeur.compareTo(noeud.valeur);

        if (value == 0) {

        // Si la abri du noeud courant est inférieure à la abri du noeud à ajouter
        } else if (value < 0 ) {

            if (this.filsGauche == null) {
                this.filsGauche = noeud;
            } else {
                this.filsGauche.ajouterNoeud(noeud);
            }
        // Si la abri du noeud courant est supérieure à la abri du noeud à ajouter
        } else if (value > 0) {

            if (this.filsDroit == null) {
                this.filsDroit = noeud;
            } else {
                this.filsDroit.ajouterNoeud(noeud);
            }
        }
    }

    /**
     * Supprimer une valeur d'un ABRI
     *
     * @param valeur
     * @return
     */
    public boolean supprimerValeur(T valeur) {
        int value = this.valeur.compareTo(valeur);

        if (this.filsDroit != null && value > 0) {

            if (this.filsDroit.getValeur() == valeur && this.filsDroit.getFilsGauche() == null && this.filsDroit.getFilsDroit() == null) {
                this.filsDroit = null;
                return true;
            } else {
                return this.filsDroit.supprimerValeur(valeur);
            }


        } else if (this.filsGauche != null && value < 0) {

            if (this.filsGauche.getValeur() == valeur && this.filsGauche.getFilsGauche() == null && this.filsGauche.getFilsDroit() == null) {
                this.filsGauche = null;
                return true;
            } else {
                return this.filsGauche.supprimerValeur(valeur);
            }

        } else if (value == 0) {
            if (this.filsDroit == null && this.filsGauche != null) {

                this.valeur = this.filsGauche.getValeur();
                this.filsDroit = this.filsGauche.getFilsDroit();
                this.filsGauche = this.filsGauche.getFilsGauche();

            } else if (this.filsGauche == null && this.filsDroit != null) {

                this.valeur = this.filsDroit.getValeur();
                this.filsGauche = this.filsDroit.getFilsGauche();
                this.filsDroit = this.filsDroit.getFilsDroit();

            } else if (this.filsGauche != null && this.filsDroit != null) {
                this.filsDroit.supprimerMaxValeur(valeur);
                this.valeur = valeur;

            }
            return true;
        }

        return false;
    }

    /**
     * Supprimer une valeur d'un ABRI
     *
     * @param valeur
     */
    private void supprimerMaxValeur(T valeur) {
        if (this.getFilsGauche() == null) {
            this.valeur = this.filsDroit.getValeur();
            this.filsGauche = this.filsDroit.getFilsGauche();
            this.filsDroit = this.filsDroit.getFilsDroit();
        } else {
            supprimerMaxValeur(valeur);
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

    public String toString() {
        String string = this.valeur.toString();

        if (this.filsGauche != null)
            string = string.concat(":"+this.filsGauche.toString());
        if (this.filsDroit != null)
            string = string.concat(":"+this.filsDroit.toString());

        return string;
    }

    /**
     * Créer un ABRI aléatoire
     *
     * @param min
     * @param max
     * @return
     * @throws Exception
     */
    public static NoeudABRI creerAleatoirementABRI(int min, int max) throws Exception {
        if (min >= max)
            throw new Exception("Max doit être supérieur à min ("+min+">="+max+").");

        Random rand = new Random();
        int nbValeurs = 0;

        while (nbValeurs == 0) {
            nbValeurs = rand.nextInt(max+1-min)+min;
        }

        NoeudABRI abri = new NoeudABRI(rand.nextInt(max+1-min)+min);
        nbValeurs--;

        for(int i=nbValeurs;i>0;i--) {
            abri.ajouterNoeud(new NoeudABRI(rand.nextInt(max+1-min)+min));
        }

        return abri;
    }
}
