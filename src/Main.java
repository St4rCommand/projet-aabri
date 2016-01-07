import java.io.*;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class Main{

    private static String fichierSource = "./jeux-d-essais/aabri1";

    public static void main(String[] args) throws Exception {

        Scanner sc = new Scanner(System.in);

        int saisie = -1;
        boolean fin = false;

        while (!fin) {

            System.out.println(" === MENU ===");
            System.out.println(" 1 - Fichier vers AABRI (avec affichage à l'écran)");
            System.out.println(" 2 - AABRI vers fichier (avec affichage à l'écran)");
            System.out.println(" 3 - Créer un AABRI aléatoire (enregistré dans le fichier aabriAleatoire.txt, avec affichage à l'écran)");
            System.out.println(" 4 - Ajouter un noeud");
            System.out.println(" 5 - Supprimer un noeud");


            System.out.print("Choix : ");

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

       /* NoeudAABRI aabri = null;
        try {
            aabri = NoeudAABRI.lireFichier(fichierSource);
        } catch (IOException e) {
            e.printStackTrace();
        }

        afficher(aabri);

        // Test insertion valeur
        aabri.insererValeur(15);
        afficher(aabri);

        // Test suppression valeur
        if (! aabri.supprimerValeur(10)) {
            System.out.println("Aucun n'interval ne correspond à cette valeur.");
        }*/
        //afficher(aabri);


//        NoeudAABRI.ecrireFichierAABRI("sortie.txt", aabri);

        /*NoeudABRI abri = null;
        try {
            abri = creerAleatoirementABRI(9, 22);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(abri.toString());*/

        /*NoeudAABRI nouveauAABRI = null;

        try {
            nouveauAABRI = NoeudAABRI.creerAleatoirementAABRI(3, 30);
        } catch (Exception e) {
            e.printStackTrace();
        }
        afficher(nouveauAABRI);
        NoeudAABRI.ecrireFichierAABRI("nouveau.txt", nouveauAABRI);*/

    }

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

    public static void aabriAleatoire() throws Exception {
        int nbNoeuds;
        int valeurMax;
        Scanner sc = new Scanner(System.in);

        System.out.print("Nombre de noeuds : ");
        nbNoeuds = Integer.valueOf(sc.nextLine());

        System.out.print("Valeur maximum : ");
        valeurMax = Integer.valueOf(sc.nextLine());

        NoeudAABRI aabri = NoeudAABRI.creerAleatoirementAABRI(3, 30);

        afficher(aabri);
        NoeudAABRI.ecrireFichierAABRI("aabriAleatoire.txt",aabri);
    }

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

    public static void afficher(NoeudAABRI noeud) {
        System.out.println(noeud.toString());
    }





    public static int[] tableauTrie(int min, int max, int nbValeurs) throws Exception {

        if (max-min <= nbValeurs) {
            throw new Exception("Il n'est pas possible de créer " + nbValeurs + " entre " + min + " et " + max + ".");
        }

        Random rand = new Random();
        int tableauTrie[] = new int[nbValeurs];
        int nbValeursCrees = 0;
        int nouvelleValeur;

        while (nbValeursCrees < nbValeurs) {
            nouvelleValeur = rand.nextInt(max+1-min)+min;

            // TODO la méthode binarysearch ne répond pas à notre problème
            if(nouvelleValeur > 0 & Arrays.binarySearch(tableauTrie, nouvelleValeur) < 0) {
                tableauTrie[nbValeursCrees] = nouvelleValeur;
                nbValeursCrees++;
            }
        }

        Arrays.sort(tableauTrie);

        return tableauTrie;
    }















        public static void afficherABRI(NoeudABRI noeud) {
        System.out.print(noeud);

        // Afficher le fils gauche
        System.out.print(" - FG: ");
        if (noeud.getFilsGauche() != null) {
            System.out.print(noeud.getFilsGauche());
        } else {
            System.out.print("null");
        }

        // Afficher le fils droit
        System.out.print(" - FD: ");
        if (noeud.getFilsDroit() != null) {
            System.out.print(noeud.getFilsDroit());
        } else {
            System.out.print("null");
        }

        System.out.println();

        if (noeud.getFilsGauche() != null) {
            afficherABRI(noeud.getFilsGauche());
        }

        if (noeud.getFilsDroit() != null) {
            afficherABRI(noeud.getFilsDroit());
        }
    }

    public static void afficherAABRI(NoeudAABRI noeud) {
        System.out.println("min : "+noeud.getMin()+" - max : "+noeud.getMax());

        // Afficher le fils gauche
        System.out.print("FG: ");
        if (noeud.getFilsGauche() != null) {
            System.out.println(noeud.getFilsGauche().getMin()+"-"+noeud.getFilsGauche().getMax());
        } else {
            System.out.println("null");
        }

        // Afficher le fils droit
        System.out.print("FD: ");
        if (noeud.getFilsDroit() != null) {
            System.out.println(noeud.getFilsDroit().getMin()+"-"+noeud.getFilsDroit().getMax());
        } else {
            System.out.println("null");
        }
        afficherABRI(noeud.getAbri());

        System.out.println();
        System.out.println();

        if (noeud.getFilsGauche() != null) {
            afficherAABRI(noeud.getFilsGauche());
        }

        if (noeud.getFilsDroit() != null) {
            afficherAABRI(noeud.getFilsDroit());
        }
    }
}