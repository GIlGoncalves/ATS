package caparicapost;

import comando.Comando;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.lang.StringBuilder;

public class Chronicle extends ArticleAbstract {
    final String PONTO ="; ";
    final String CHRO  ="Chronicle comments:";
    final String VAZIO ="";
    
    
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
            StringBuilder sb= new StringBuilder();
            sb.append(PONTO);
            sb.append(publishDate);
            sb.append(PONTO);
            return sb.toString();
            //return "; " + publishDate + "; ";
        } else {
            return VAZIO;
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
            StringBuilder sb= new StringBuilder();
            sb.append(Comando.COMM).append(totalComments);
            return sb.toString();
        } else {
            return VAZIO;
        }
    }

    public void PrintComments() {
        StringBuilder sb= new StringBuilder();
        System.out.println(CHRO );
        Collections.sort(commentList, new ComparatorListComments());
        for (int i = 0; i < commentList.size(); i++) {
            sb.setLength(0);
            sb.append(commentList.get(i).GetAuthor());
            sb.append(PONTO);
            sb.append(commentList.get(i).GetText());
            System.out.println(sb.toString());
            //System.out.println(commentList.get(i).GetAuthor() + "; " + commentList.get(i).GetText());
        }
    }

    public String toString(String editorName) {
        StringBuilder sb= new StringBuilder();
        sb.append(this.GetTitle());
        sb.append(PONTO);
        sb.append(editorName);
        sb.append(this.GetTheme());
        sb.append(PONTO);
        sb.append(this.GetTopic());
        sb.append(PONTO);
        sb.append(this.GetDate());
        sb.append(this.GetPublishDate());
        sb.append(this.GetTotalCommentsString());
        return sb.toString();
        //return this.GetTitle() + "; " + editorName + this.GetTheme() + "; " + this.GetTopic() + "; " + this.GetDate() + this.GetPublishDate() + this.GetTotalCommentsString();
    }

    public String toString(){
        StringBuilder sb= new StringBuilder();
        sb.append(this.GetTitle());
        sb.append(PONTO);
        sb.append(this.GetAuthor());
        sb.append(PONTO);
        sb.append(this.GetTheme());
        sb.append(PONTO);
        sb.append(this.GetTopic());
        sb.append(this.GetPublishDate());
        sb.append(this.GetTotalCommentsString());
        return sb.toString();
        //return this.GetTitle() + "; " + this.GetAuthor() + "; " + this.GetTheme() + "; " + this.GetTopic() + this.GetPublishDate() + this.GetTotalCommentsString();
    }

    public String toStringTopic(){
        StringBuilder sb= new StringBuilder();
        sb.append(this.GetTitle());
        sb.append(PONTO);
        sb.append(this.GetAuthor());
        sb.append(PONTO);
        sb.append(this.GetTheme());
        sb.append(this.GetPublishDate());
        sb.append(this.GetTotalCommentsString());
        return sb.toString();
        //return this.GetTitle() + "; " + this.GetAuthor() + "; " + this.GetTheme() + this.GetPublishDate() + this.GetTotalCommentsString();
    }

    public String toStringTheme(){
        StringBuilder sb= new StringBuilder();
        sb.append(this.GetTitle());
        sb.append(PONTO);
        sb.append(this.GetAuthor());
        sb.append(PONTO);
        sb.append(this.GetTopic());
        sb.append(this.GetPublishDate());
        sb.append(this.GetTotalCommentsString());
        return sb.toString();
        //return this.GetTitle() + "; " + this.GetAuthor() + "; " + this.GetTopic() + this.GetPublishDate() + this.GetTotalCommentsString();
    }
}
