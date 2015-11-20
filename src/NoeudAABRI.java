public class NoeudAABRI<T> extends NoeudABR {
    protected int min;
    protected int max;

    protected NoeudAABRI<T> filsGauche;
    protected NoeudAABRI<T> filsDroit;

    public void setFilsGauche(NoeudAABRI<T> filsGauche) {
        // TODO appeler la fonction parente
    }

    public void setFilsDroits(NoeudAABRI<T> filsDroits) {
        // TODO appeler la fonction parente
    }

    public int getMin() {
        return min;
    }

    public void setMin(int min) {
        this.min = min;
    }

    public int getMax() {
        return max;
    }

    public void setMax(int max) {
        this.max = max;
    }
}
