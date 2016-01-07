import java.io.*;
import java.util.*;

public class Main{

    // ./jeux-d-essais/aabri1

    public static void main(String[] args) throws Exception {

        Scanner sc = new Scanner(System.in);

        int saisie;
        boolean fin = false;

        while (!fin) {

            System.out.println(" === MENU ===");
            System.out.println(" 1 - Fichier vers AABRI (avec affichage à l'écran)");
            System.out.println(" 2 - AABRI vers fichier (avec affichage à l'écran)");
            System.out.println(" 3 - Créer un AABRI aléatoire (enregistré dans le fichier aabriAleatoire, avec affichage à l'écran)");
            System.out.println(" 4 - Ajouter un noeud");
            System.out.println(" 5 - Supprimer un noeud");
            System.out.println("\n 0 - Quitter");


            System.out.print(" \nChoix : ");

            try {
                saisie = Integer.valueOf(sc.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Veuillez saisir un nombre entre _ et _.");
                saisie = -1;
            }

            switch (saisie) {
                case 1:
                    fichierVersAABRI();
                    break;
                case 2:
                    aabriVersFichier();
                    break;
                case 3:
                    aabriAleatoire();
                    break;
                case 4:
                    ajouterNoeud();
                    break;
                case 5:
                    supprimerNoeud();
                    break;
                case 0:
                    fin = true;
                    break;
            }

        }

    }

    /**
     * Charger un AABRI et l'afficher
     */
    public static void fichierVersAABRI() {
        String fichier = "";
        Scanner sc = new Scanner(System.in);

        System.out.print("Nom du fichier à charger : ");
        fichier = sc.nextLine();

        try {
            NoeudAABRI aabri = NoeudAABRI.lireFichier(fichier);
            afficher(aabri);
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Aucun fichier trouvé.");
        }
    }

    /**
     * Charger un AABRI et l'enregistrer dans un fichier
     */
    public static void aabriVersFichier() {
        String fichierSource = "";
        String fichierDestination = "";
        Scanner sc = new Scanner(System.in);

        System.out.print("Nom du fichier à charger : ");
        fichierSource = sc.nextLine();

        System.out.print("Emplacement et nom du fichier destination : ");
        fichierDestination = sc.nextLine();

        try {
            NoeudAABRI aabri = NoeudAABRI.lireFichier(fichierSource);
            afficher(aabri);
            NoeudAABRI.ecrireFichierAABRI(fichierDestination,aabri);
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Aucun fichier trouvé.");
        }
    }

    /**
     * Otenir un AABRI aléatoire et l'enregistrer dans un fichier
     *
     * @throws Exception
     */
    public static void aabriAleatoire() throws Exception {
        int nbNoeuds;
        int valeurMax;
        Scanner sc = new Scanner(System.in);

        System.out.print("Nombre de noeuds : ");
        nbNoeuds = Integer.valueOf(sc.nextLine());

        System.out.print("Valeur maximum (minimum le double du nombre de noeuds+2) : ");
        valeurMax = Integer.valueOf(sc.nextLine());
        System.out.println(nbNoeuds+" ."+ valeurMax);
        NoeudAABRI aabri = NoeudAABRI.creerAleatoirementAABRI(nbNoeuds, valeurMax);

        afficher(aabri);
        NoeudAABRI.ecrireFichierAABRI("aabriAleatoire",aabri);
    }

    /**
     * Ajouter un noeud à un AABRI
     */
    public static void ajouterNoeud() {
        String fichier = "";
        Scanner sc = new Scanner(System.in);

        System.out.print("Nom du fichier à charger : ");
        fichier = sc.nextLine();

        NoeudAABRI aabri;
        try {
            aabri = NoeudAABRI.lireFichier(fichier);
            afficher(aabri);

            System.out.print("Valeur à ajouter : ");
            int valeur = Integer.valueOf(sc.nextLine());


            if (! aabri.insererValeur(valeur)) {
                System.out.println("Aucun interval ne correspond pour l'insertion.");
            }

            afficher(aabri);
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Aucun fichier trouvé.");
        }

    }

    /**
     * Supprimer un noeud d'un AABRI
     */
    public static void supprimerNoeud() {
        String fichier = "";
        Scanner sc = new Scanner(System.in);

        System.out.print("Nom du fichier à charger : ");
        fichier = sc.nextLine();

        NoeudAABRI aabri;
        try {
            aabri = NoeudAABRI.lireFichier(fichier);
            afficher(aabri);

            System.out.print("Valeur à supprimer : ");
            int valeur = Integer.valueOf(sc.nextLine());


            if (! aabri.supprimerValeur(valeur)) {
                System.out.println("Aucun interval ne correspond à cette valeur.");
            }

            afficher(aabri);
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Aucun fichier trouvé.");
        }
    }

    /**
     * Afficher un AABRI sous la même forme que dans un fichier
     *
     * @param noeud
     */
    public static void afficher(NoeudAABRI noeud) {
        System.out.println(noeud.toString());
    }


    /**
     * Obtenir un tableau trié en fonction des paramètres
     *
     * @param min
     * @param max
     * @param nbValeurs
     * @return
     * @throws Exception
     */
    public static List<Integer> tableauTrie(int min, int max, int nbValeurs) throws Exception {

        if (max-min <= (nbValeurs)) {
            throw new Exception("Il n'est pas possible de créer " + nbValeurs + " entre " + min + " et " + max + ".");
        }

        Random rand = new Random();
        List<Integer> tableauTrie = new ArrayList<Integer>();
        int nbValeursCrees = 0;
        int nouvelleValeur;

        while (nbValeursCrees < nbValeurs) {
            nouvelleValeur = rand.nextInt(max+1-min)+min;

            if(nouvelleValeur > 0 & ! tableauTrie.contains(nouvelleValeur)) {
                tableauTrie.add(nouvelleValeur);
                nbValeursCrees++;
                System.out.println(nouvelleValeur);
            }
        }

        Collections.sort(tableauTrie);

        System.out.println(tableauTrie.get(3));

        return tableauTrie;
    }


}