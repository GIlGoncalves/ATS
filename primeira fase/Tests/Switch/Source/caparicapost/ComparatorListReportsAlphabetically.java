package caparicapost;

import java.util.Comparator;

public class ComparatorListReportsAlphabetically implements Comparator<Article> {

    @Override
    public int compare(Article a1, Article a2) {
        String title1 = a1.GetTitle();
        String title2 = a2.GetTitle();
        return title1.compareToIgnoreCase(title2);
    }
}
