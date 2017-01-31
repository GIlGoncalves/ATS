package caparicapost;

import java.lang.StringBuilder;

public class Report extends ArticleAbstract {
   final String PONTO ="; ";
   final String LIKE = "; Likes: ";
    private int votes;

    public Report(String type, String title, String author, String theme, String topic, String date, String text) {
        super(type, title, author, theme, topic, date, text);
        votes = 0;
    }

    public void AddVote() {
        votes++;
    }

    public int GetVotes() {
        return votes;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.GetTitle());
        sb.append(PONTO);
        sb.append(this.GetAuthor());
        sb.append(PONTO);
        sb.append(this.GetTheme());
        sb.append(PONTO);
        sb.append(this.GetTopic());
        sb.append(PONTO);
        sb.append(this.GetDate());
        sb.append(LIKE);
        sb.append(this.votes);
        return sb.toString();
        //return this.GetTitle() + "; " + this.GetAuthor() + "; " + this.GetTheme() + "; " + this.GetTopic() + "; " + this.GetDate() + "; Likes: " + this.votes;
    }

    public String toStringTheme() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.GetTitle());
        sb.append(PONTO);
        sb.append(this.GetAuthor());
        sb.append(PONTO);
        sb.append(this.GetTopic());
        sb.append(PONTO);
        sb.append(this.GetDate());
        sb.append(LIKE);
        sb.append(this.votes);
        return sb.toString();
        //return this.GetTitle() + "; " + this.GetAuthor() + "; " + this.GetTopic() + "; " +this.GetDate() + "; Likes: " + this.votes;
    }

    public String toStringTopic() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.GetTitle());
        sb.append(PONTO);
        sb.append(this.GetAuthor());
        sb.append(PONTO);
        sb.append(this.GetTheme());
        sb.append(PONTO);
        sb.append(this.GetDate());
        sb.append(LIKE);
        sb.append(this.votes);
        return sb.toString();
        //return this.GetTitle() + "; " + this.GetAuthor() + "; " + this.GetTheme() + "; " +this.GetDate() + "; Likes: " + this.votes;
    }
}
