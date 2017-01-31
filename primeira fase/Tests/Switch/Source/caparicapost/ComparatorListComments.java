package caparicapost;

import java.util.Comparator;

public class ComparatorListComments implements Comparator<CommentClass> {

    @Override
    public int compare(CommentClass c1, CommentClass c2) {
        return c2.GetCommentNumber() - c1.GetCommentNumber();
    }
}
