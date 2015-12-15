import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

import static java.lang.System.exit;

public class Main{

    private static String fichierSource = "./jeux-d-essais/aabri1";

    public static void main(String[] args) {

        NoeudAABRI aabri = null;
        try {
            aabri = lireFichier(fichierSource);
        } catch (IOException e) {
            e.printStackTrace();
        }

        afficher(aabri);

        ecrireFichier("sortie.txt", aabri);

        /*NoeudABRI abri = null;
        try {
            abri = creerAleatoirementABRI(9, 22);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(abri.toString());*/

        NoeudAABRI nouveauAABRI = null;

        try {
            nouveauAABRI = creerAleatoirementAABRI(3, 30);
        } catch (Exception e) {
            e.printStackTrace();
        }
        afficher(nouveauAABRI);
        ecrireFichier("nouveau.txt", nouveauAABRI);

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

            tabAABRI.add(new NoeudAABRI(valeursMinMax[0], valeursMinMax[1], abri));
        }

        NoeudAABRI aabri = tabAABRI.get(0);

        for(int i=1;i<tabAABRI.size();i++) {
            aabri.ajouterNoeud(tabAABRI.get(i));
        }

        br.close();

        return aabri;
    }

    public static void afficher(NoeudAABRI noeud) {
        System.out.println(noeud.toString());
    }

    public static void ecrireFichier(String nomFichier, NoeudAABRI aabri) {

        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(new File(nomFichier)));
            bw.write(aabri.toString());
            bw.flush();
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    public static NoeudAABRI creerAleatoirementAABRI(int nbNoeuds, int max) throws Exception {

        int[] intervalles = tableauTrie(1,max,nbNoeuds*2);
        System.out.println(Arrays.toString(intervalles));
        NoeudAABRI aabri = new NoeudAABRI(intervalles[0], intervalles[1], creerAleatoirementABRI(intervalles[0],intervalles[1]));

        for (int i=2;i<(nbNoeuds*2);i+=2){
            aabri.ajouterNoeud(new NoeudAABRI(intervalles[i], intervalles[i+1], creerAleatoirementABRI(intervalles[i],intervalles[i+1])));
        }

        return aabri;
    }

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
                if (Arrays.binarySearch(tableauTrie, nouvelleValeur) >= 0) {
                    System.out.println("tu te fous de ma gueule");
                }
                tableauTrie[nbValeursCrees] = nouvelleValeur;
                nbValeursCrees++;
            }
        }

        Arrays.sort(tableauTrie);

        return tableauTrie;
    }

    public static void isAABRI() {
        // TODO
    }













    /*    public static void afficherABRI(NoeudABRI noeud) {
        System.out.print(noeud.getValeur());

        // Afficher le fils gauche
        System.out.print(" - FG: ");
        if (noeud.getFilsGauche() != null) {
            System.out.print(noeud.getFilsGauche().getValeur());
        } else {
            System.out.print("null");
        }

        // Afficher le fils droit
        System.out.print(" - FD: ");
        if (noeud.getFilsDroit() != null) {
            System.out.print(noeud.getFilsDroit().getValeur());
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
        afficherABRI(noeud.getValeur());

        System.out.println();
        System.out.println();

        if (noeud.getFilsGauche() != null) {
            afficherAABRI(noeud.getFilsGauche());
        }

        if (noeud.getFilsDroit() != null) {
            afficherAABRI(noeud.getFilsDroit());
        }
    }*/
}