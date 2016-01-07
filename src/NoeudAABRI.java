import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;

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

    public boolean insererValeur(T valeur) {
        int comparaisonMin = this.min.compareTo(valeur);
        int comparaisonMax = this.max.compareTo(valeur);


        if (comparaisonMin <= 0 & comparaisonMax >= 0) {
            this.abri.ajouterNoeud(new NoeudABRI<>(valeur));
            return true;
        } else if (comparaisonMin < 0 && comparaisonMin > 0) {
            return this.filsGauche.insererValeur(valeur);
        } else if (comparaisonMax > 0 && comparaisonMax < 0) {
            return this.filsDroit.insererValeur(valeur);
        }

        return false;
    }

    public boolean supprimerValeur(T valeur) {
        int comparaisonMin = this.min.compareTo(valeur);
        int comparaisonMax = this.max.compareTo(valeur);


        if (comparaisonMin <= 0 & comparaisonMax >= 0) {
            if (! this.abri.supprimerValeur(valeur)) {
                System.out.println("La valeur est présente dans un intervalle d'un ABRI, mais n'est pas présente dans celui-ci.");
            }
            return true;
        } else if (this.filsGauche != null && comparaisonMin > 0) {
            return this.filsGauche.supprimerValeur(valeur);
        } else if (this.filsDroit != null && comparaisonMax < 0) {
            return this.filsDroit.supprimerValeur(valeur);
        }

        return false;
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

    public static NoeudAABRI lireFichier(String fichier) throws IOException {

        BufferedReader br;
        br = new BufferedReader(new FileReader(new File(fichier)));

        ArrayList<NoeudAABRI> tabAABRI = new ArrayList<>();

        String ligneCourante;

        while ((ligneCourante = br.readLine()) != null) {

            // Ligne correspondant à un noeud AABRI
            String[] noeudAABRI = ligneCourante.split(";");

            // Valeurs min/max
            String[] stringValeursMinMax = noeudAABRI[0].split(":");
            int[] valeursMinMax = new int[stringValeursMinMax.length];
            for (int i = 0; i < stringValeursMinMax.length; i++) {
                valeursMinMax[i] = Integer.parseInt(stringValeursMinMax[i]);
            }

            // Valeurs ABRI
            String[] stringValeursABRI = noeudAABRI[1].split(":");
            int[] valeursABRI = new int[stringValeursABRI.length];
            for (int i = 0; i < stringValeursABRI.length; i++) {
                valeursABRI[i] = Integer.parseInt(stringValeursABRI[i]);
            }

            NoeudABRI<Integer> abri = new NoeudABRI<>(valeursABRI[0]);

            for (int i = 1; i < valeursABRI.length; i++) {
                abri.ajouterNoeud(new NoeudABRI<>(valeursABRI[i]));
            }

            tabAABRI.add(new NoeudAABRI<Integer>(valeursMinMax[0], valeursMinMax[1], abri));
        }

        NoeudAABRI aabri = tabAABRI.get(0);

        for(int i=1;i<tabAABRI.size();i++) {
            aabri.ajouterNoeud(tabAABRI.get(i));
        }

        br.close();

        return aabri;
    }

    public static NoeudAABRI creerAleatoirementAABRI(int nbNoeuds, int max) throws Exception {

        int[] intervalles = Main.tableauTrie(1,max,nbNoeuds*2);
        System.out.println(Arrays.toString(intervalles));
        NoeudAABRI aabri = new NoeudAABRI(intervalles[0], intervalles[1], NoeudABRI.creerAleatoirementABRI(intervalles[0],intervalles[1]));

        for (int i=2;i<(nbNoeuds*2);i+=2){
            aabri.ajouterNoeud(new NoeudAABRI(intervalles[i], intervalles[i+1], NoeudABRI.creerAleatoirementABRI(intervalles[i],intervalles[i+1])));
        }

        return aabri;
    }

    public static void isAABRI() {
        // TODO
    }

    public static void ecrireFichierAABRI(String nomFichier, NoeudAABRI aabri) {

        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(new File(nomFichier)));
            bw.write(aabri.toString());
            bw.flush();
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
