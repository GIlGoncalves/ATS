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
}
