package caparicapost;

import comando.Comando;
import java.util.Comparator;

public class ComparatorListReportsByVotes implements Comparator<Article> {

    @Override
    public int compare(Article a1, Article a2) {
        if (a1.GetType().equalsIgnoreCase(Comando.REPORT) && a2.GetType().equalsIgnoreCase(Comando.REPORT)) {
            return ((Report) a2).GetVotes() - ((Report) a1).GetVotes();
        }
        return 0;
    }
}
