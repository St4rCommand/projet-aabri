import javax.xml.transform.sax.SAXSource;
import java.io.*;
import java.util.ArrayList;

public class Main{

    private static String cheminFichier = "./jeux-d-essais/aabri1";

    public static void main(String[] args) throws IOException {
        // TODO
        lireFichier();
    }

    public static void lireFichier() throws IOException {

        BufferedReader br;
        br = new BufferedReader(new FileReader(new File(cheminFichier)));

        ArrayList<NoeudAABRI> tabAABRI = new ArrayList<>();

        String ligneCourante;

        while ((ligneCourante = br.readLine()) != null) {
            System.out.println(ligneCourante);


            // Ligne correspondant à un noeud AABRI
            String[] noeudAABRI = ligneCourante.split(";");

            // Valeurs min/max
            String[] stringValeursMinMax = noeudAABRI[0].split(":");
            int[] valeursMinMax = new int[stringValeursMinMax.length];
            for (int i = 0; i < stringValeursMinMax.length; i++) {
                valeursMinMax[i] = Integer.parseInt(stringValeursMinMax[i]);
            }

            System.out.println();

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

        afficherAABRI(aabri);

        br.close();
    }

    public static void afficherABRI(NoeudABRI noeud) {
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
    }




}