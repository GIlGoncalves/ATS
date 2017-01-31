package caparicapost;


public class Report extends ArticleAbstract {

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
        return this.GetTitle() + "; " + this.GetAuthor() + "; " + this.GetTheme() + "; " + this.GetTopic() + "; " + this.GetDate() + "; Likes: " + this.votes;
    }

    public String toStringTheme() {
        return this.GetTitle() + "; " + this.GetAuthor() + "; " + this.GetTopic() + "; " +this.GetDate() + "; Likes: " + this.votes;
    }

    public String toStringTopic() {
        return this.GetTitle() + "; " + this.GetAuthor() + "; " + this.GetTheme() + "; " +this.GetDate() + "; Likes: " + this.votes;
    }
}
