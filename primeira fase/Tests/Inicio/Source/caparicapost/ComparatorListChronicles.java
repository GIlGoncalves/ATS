package caparicapost;

import java.util.Comparator;

public class ComparatorListChronicles implements Comparator<Article> {

    @Override
    public int compare(Article a1, Article a2) {
        if (a1.GetType().equalsIgnoreCase("CHRONICLE") && a2.GetType().equalsIgnoreCase("CHRONICLE")) {
            if (((Chronicle) a1).GetTotalComments() == ((Chronicle) a2).GetTotalComments()) {
                //String title1 = a1.GetTitle();
                //String title2 = a2.GetTitle();
                //return title1.compareToIgnoreCase(title2);
                return 0;
            }
            return ((Chronicle) a2).GetTotalComments() - ((Chronicle) a1).GetTotalComments();
        }
        return 0;
    }
}
