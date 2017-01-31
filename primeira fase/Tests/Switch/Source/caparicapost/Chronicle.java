package caparicapost;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Chronicle extends ArticleAbstract {

    User editor;

    private String publishDate;

    private int totalComments;

    private boolean isPublished;

    List<CommentClass> commentList = new ArrayList<CommentClass>();

    public Chronicle(String type, String title, String author, String theme, String topic, String date, String text) {
        super(type, title, author, theme, topic, date, text);
        editor = null;
        publishDate = null;
        totalComments = 0;
        isPublished = false;
    }

    public void Publish() {
        isPublished = true;
    }

    public boolean IsPublished() {
        return isPublished;
    }

    public void SetPublishDate(String publishDate) {
        this.publishDate = publishDate;
    }

    public String GetPublishDate() {
        if (isPublished) {
            return "; " + publishDate + "; ";
        } else {
            return "";
        }
    }

    public String GetSimplePublishDate() {
        return publishDate;
    }

    public void AddComment(String author, String text) {
        commentList.add(new CommentClass(author, text, totalComments));
        totalComments++;
    }

    public int GetTotalComments() {
        return totalComments;
    }

    public String GetTotalCommentsString() {
        if (isPublished) {
            return "Comments: " + totalComments;
        } else {
            return "";
        }
    }

    public void PrintComments() {
        System.out.println("Chronicle comments:");
        Collections.sort(commentList, new ComparatorListComments());
        for (int i = 0; i < commentList.size(); i++) System.out.println(commentList.get(i).GetAuthor() + "; " + commentList.get(i).GetText());
    }
}
