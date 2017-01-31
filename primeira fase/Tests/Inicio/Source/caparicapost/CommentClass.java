package caparicapost;


public class CommentClass {

    private String author;

    private String text;

    private int commentNumber;

    public CommentClass(String author, String text, int commentNumber) {
        this.author = author;
        this.text = text;
        this.commentNumber = commentNumber;
    }

    public String GetAuthor() {
        return author;
    }

    public String GetText() {
        return text;
    }

    public int GetCommentNumber() {
        return commentNumber;
    }
}
