package caparicapost;

import exceptions.ExceptionChronicleNotFound;
import exceptions.ExceptionNoEditorLoggedIn;
import exceptions.ExceptionNoUserLoggedIn;
import exceptions.ExceptionReportDoesNotExist;
import exceptions.ExceptionUserDoesNotExist;
import exceptions.ExceptionUserLoggedIn;

public interface CaparicaPost {

    public void RegisterUser(String type, String name, String email, String theme) throws ExceptionUserLoggedIn;

    public void LoginUser(String name) throws ExceptionUserDoesNotExist;

    public void LogoutUser() throws ExceptionNoUserLoggedIn;

    public void ListAllUsers() throws ExceptionNoEditorLoggedIn;

    public void ListUserByType(String type);

    public void AddReport(String type, String title, String theme, String topic, String date, String text);

    public void AddChronicle(String type, String title, String theme, String topic, String date, String text);

    public void Assign(String name) throws ExceptionNoEditorLoggedIn;

    public void ListReports();

    public void ListArticleByType(String type);

    public void Like(String title) throws ExceptionReportDoesNotExist;

    public void ListChronicles();

    public void Approve(String title, String date);

    public void AddComment(String title, String text) throws ExceptionChronicleNotFound;

    public void ListComments(String title) throws ExceptionChronicleNotFound;

    public void TopChronicles();

    public void ListTopic(String topic);

    public void ListTheme(String theme);
}
