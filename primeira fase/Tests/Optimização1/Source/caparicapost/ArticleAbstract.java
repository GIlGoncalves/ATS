package caparicapost;


public abstract class ArticleAbstract implements Article {

    private String type;

    private String title;

    private String author;

    private String theme;

    private String topic;

    private String date;

    private String text;

    public ArticleAbstract(String type, String title, String author, String theme, String topic, String date, String text) {
        this.type = type;
        this.title = title;
        this.author = author;
        this.theme = theme;
        this.topic = topic;
        this.date = date;
        this.text = text;
    }

    public String GetType() {
        return type;
    }

    public String GetTitle() {
        return title;
    }

    public String GetAuthor() {
        return author;
    }

    public String GetTheme() {
        return theme;
    }

    public String GetTopic() {
        return topic;
    }

    public String GetDate() {
        return date;
    }

    public String GetText() {
        return text;
    }
}
