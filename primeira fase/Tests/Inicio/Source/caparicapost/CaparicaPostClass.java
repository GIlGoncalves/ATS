package caparicapost;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.ArrayList;
import exceptions.ExceptionChronicleNotFound;
import exceptions.ExceptionNoEditorLoggedIn;
import exceptions.ExceptionNoUserLoggedIn;
import exceptions.ExceptionReportDoesNotExist;
import exceptions.ExceptionUserDoesNotExist;
import exceptions.ExceptionUserLoggedIn;

public class CaparicaPostClass implements CaparicaPost {

    User currentUser;

    List<User> userList = new ArrayList<User>();

    List<Article> articleList = new ArrayList<Article>();

    public CaparicaPostClass() {
        currentUser = null;
    }

    public String GregCal(String newDate) {
        String outputDate = null;
        try {
            SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
            Date date = format.parse(newDate);
            GregorianCalendar calendar = new GregorianCalendar();
            calendar.setTime(date);
            outputDate = calendar.get(GregorianCalendar.DAY_OF_MONTH) + "-" + (calendar.get(GregorianCalendar.MONTH) + 1) + "-" + calendar.get(GregorianCalendar.YEAR);
            return outputDate;
        } catch (ParseException e) {
            e.printStackTrace();
            
        }
        return outputDate;
    }

    public void RegisterUser(String type, String name, String email, String theme) throws ExceptionUserLoggedIn {
        if (currentUser != null) {
            throw new ExceptionUserLoggedIn();
        } 
        else if (HasUser(name)) System.out.println("User already exists.\n");
        if (currentUser == null && !HasUser(name)) {
            if (type.equalsIgnoreCase("READER")) userList.add(new Reader(type, name, email)); else if (type.equalsIgnoreCase("EDITOR")) userList.add(new Editor(type, name, email, theme)); else if (type.equalsIgnoreCase("JOURNALIST")) userList.add(new Journalist(type, name, email, theme)); else if (type.equalsIgnoreCase("COLLABORATOR")) userList.add(new Collaborator(type, name, email));
            System.out.println("New user registered.");
            System.out.println();
        }
    }

    public boolean HasUser(String name) {
        boolean hasUser = false;
        for (int i = 0; i < userList.size(); i++) {
            if (name.equalsIgnoreCase(userList.get(i).GetName())) {
                hasUser = true;
            }
        }
        return hasUser;
    }

    public void LoginUser(String name) throws ExceptionUserDoesNotExist {
        if (!HasUser(name)) {
            throw new ExceptionUserDoesNotExist();
        } 
        else if (currentUser != null && currentUser.GetName().equalsIgnoreCase(name)) System.out.println("User already logged in.\n"); else if (currentUser != null) System.out.println("Another user is logged in.\n"); 
        else {
            for (int i = 0; i < userList.size(); i++) {
                if (name.equalsIgnoreCase(userList.get(i).GetName())) {
                    currentUser = userList.get(i);
                    System.out.println("Welcome " + currentUser.GetName() + ".\n");
                }
            }
        }
    }

    public void LogoutUser() throws ExceptionNoUserLoggedIn {
        if (currentUser != null) {
            System.out.println("Goodbye " + currentUser.GetName() + ".\n");
            currentUser = null;
        } else {
            throw new ExceptionNoUserLoggedIn();
        }
    }

    public void ListAllUsers() throws ExceptionNoEditorLoggedIn {
        if (currentUser != null && currentUser.GetType().equalsIgnoreCase("EDITOR")) {
            Collections.sort(userList, new ComparatorUserAlphabetically());
            System.out.println("All users:");
            ListUserByType("EDITOR");
            ListUserByType("JOURNALIST");
            ListUserByType("COLLABORATOR");
            ListUserByType("READER");
        } else {
            throw new ExceptionNoEditorLoggedIn();
        }
    }

    public void ListUserByType(String type) {
        for (int i = 0; i < userList.size(); i++) {
            if (userList.get(i).GetType().equalsIgnoreCase(type) && userList.get(i).GetTheme() != null) System.out.println(userList.get(i).GetName() + "; " + userList.get(i).GetEmail() + "; " + userList.get(i).GetType() + " (" + userList.get(i).GetTheme() + ")"); else if (userList.get(i).GetType().equalsIgnoreCase(type)) System.out.println(userList.get(i).GetName() + "; " + userList.get(i).GetEmail() + "; " + userList.get(i).GetType());
        }
    }

    public void AddReport(String type, String title, String theme, String topic, String date, String text) {
        if (currentUser == null || !currentUser.GetType().equalsIgnoreCase("JOURNALIST")) System.out.println("No journalist is logged in.\n"); else if (HasArticle(title, "ALL")) System.out.println("There is another article with that title.\n"); else {
            String author = currentUser.GetName();
            String parsedDate = GregCal(date);
            articleList.add(new Report(type, title, author, theme, topic, parsedDate, text));
            System.out.println("New report published.");
            System.out.println();
        }
    }

    public void ListReports() {
        if (currentUser == null || !currentUser.GetType().equalsIgnoreCase("JOURNALIST")) System.out.println("No journalist is logged in."); else {
            System.out.println("Reports published:");
            Collections.sort(articleList, new ComparatorListReportsAlphabetically());
            Collections.sort(articleList, new ComparatorListReportsByVotes());
            ListArticleByType("REPORT");
        }
    }

    public void ListArticleByType(String type) {
        if (type.equalsIgnoreCase("REPORT")) {
            for (int i = 0; i < articleList.size(); i++) {
                if (articleList.get(i).GetType().equalsIgnoreCase(type) && currentUser.GetName().equalsIgnoreCase(articleList.get(i).GetAuthor())) System.out.println(articleList.get(i).GetTitle() + "; " + articleList.get(i).GetAuthor() + "; " + articleList.get(i).GetTheme() + "; " + articleList.get(i).GetTopic() + "; " + articleList.get(i).GetDate() + "; Likes: " + ((Report) articleList.get(i)).GetVotes());
            }
        } else if (type.equalsIgnoreCase("CHRONICLE")) {
            for (int i = 0; i < articleList.size(); i++) {
                if (articleList.get(i).GetType().equalsIgnoreCase(type) && currentUser.GetName().equalsIgnoreCase(articleList.get(i).GetAuthor())) System.out.println(articleList.get(i).GetTitle() + "; " + ((Collaborator) currentUser).GetEditorName() + articleList.get(i).GetTheme() + "; " + articleList.get(i).GetTopic() + "; " + articleList.get(i).GetDate() + ((Chronicle) articleList.get(i)).GetPublishDate() + ((Chronicle) articleList.get(i)).GetTotalCommentsString());
            }
        }
    }

    public void AddChronicle(String type, String title, String theme, String topic, String date, String text) {
        if (currentUser == null || !currentUser.GetType().equalsIgnoreCase("COLLABORATOR")) System.out.println("No collaborator is logged in.\n"); else if (HasArticle(title, "ALL")) System.out.println("There is another article with that title.\n"); else {
            String author = currentUser.GetName();
            String parsedDate = GregCal(date);
            articleList.add(new Chronicle(type, title, author, theme, topic, parsedDate, text));
            System.out.println("New chronicle added.");
            System.out.println();
        }
    }

    public void ListChronicles() {
        if (currentUser == null || !currentUser.GetType().equalsIgnoreCase("COLLABORATOR")) System.out.println("No collaborator is logged in."); else {
            System.out.println("Chronicles written:");
            Collections.sort(articleList, new ComparatorListChronicles());
            ListArticleByType("CHRONICLE");
        }
    }

    public void Assign(String name) throws ExceptionNoEditorLoggedIn {
        boolean hasCollab = false;
        if (currentUser != null && currentUser.GetType().equalsIgnoreCase("EDITOR")) {
            for (int i = 0; i < userList.size(); i++) {
                if (userList.get(i).GetName().equalsIgnoreCase(name) && userList.get(i).GetType().equalsIgnoreCase("COLLABORATOR")) {
                    ((Collaborator) userList.get(i)).SetEditor(currentUser);
                    System.out.println("New editor assignment.");
                    System.out.println();
                    hasCollab = true;
                }
            }
        } else {
            throw new ExceptionNoEditorLoggedIn();
        }
        if (!hasCollab && currentUser != null && currentUser.GetType().equalsIgnoreCase("EDITOR")) System.out.println("Collaborator does not exist.\n");
    }

    public void Like(String title) throws ExceptionReportDoesNotExist {
        boolean hasReport = false;
        for (int i = 0; i < articleList.size(); i++) {
            if (articleList.get(i).GetTitle().equalsIgnoreCase(title) && articleList.get(i).GetType().equalsIgnoreCase("REPORT")) {
                ((Report) articleList.get(i)).AddVote();
                System.out.println("Likes: " + ((Report) articleList.get(i)).GetVotes() + ".");
                System.out.println();
                hasReport = true;
            }
        }
        if (!hasReport) {
            throw new ExceptionReportDoesNotExist();
        }
    }

    public void Approve(String title, String date) {
        if (currentUser == null || !currentUser.GetType().equalsIgnoreCase("EDITOR")) System.out.println("No editor is logged in.\n"); else if (!HasArticle(title, "CHRONICLE")) System.out.println("Chronicle does not exist.\n"); else if (!CanApprove(title)) System.out.println("Not authorized to approve this chronicle.\n"); else if (((Chronicle) GetArticle(title, "CHRONICLE")).IsPublished()) System.out.println("Chronicle already approved.\n"); else {
            for (int i = 0; i < articleList.size(); i++) {
                if (articleList.get(i).GetTitle().equalsIgnoreCase(title)) {
                    ((Chronicle) articleList.get(i)).Publish();
                    String parsedDate = GregCal(date);
                    ((Chronicle) articleList.get(i)).SetPublishDate(parsedDate);
                    System.out.println("New chronicle published.");
                    System.out.println();
                }
            }
        }
    }

    public boolean CanApprove(String title) {
        Article tmp = null;
        for (int i = 0; i < articleList.size(); i++) {
            if (articleList.get(i).GetTitle().equalsIgnoreCase(title) && articleList.get(i).GetType().equalsIgnoreCase("CHRONICLE")) {
                tmp = articleList.get(i);
            }
        }
        for (int n = 0; n < userList.size(); n++) {
            if (userList.get(n).GetName().equalsIgnoreCase(tmp.GetAuthor())) {
                if (((Collaborator) userList.get(n)).GetSimpleEditorName().equalsIgnoreCase(currentUser.GetName())) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean HasArticle(String title, String type) {
        for (int i = 0; i < articleList.size(); i++) {
            if (type.equalsIgnoreCase("REPORT") || type.equalsIgnoreCase("CHRONICLE")) {
                if (articleList.get(i).GetTitle().equalsIgnoreCase(title) && articleList.get(i).GetType().equalsIgnoreCase(type)) {
                    return true;
                }
            } else {
                if (type.equalsIgnoreCase("ALL")) {
                    if (articleList.get(i).GetTitle().equalsIgnoreCase(title)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public Article GetArticle(String title, String type) {
        for (int i = 0; i < articleList.size(); i++) {
            if (type.equalsIgnoreCase("REPORT") || type.equalsIgnoreCase("CHRONICLE")) {
                if (articleList.get(i).GetTitle().equalsIgnoreCase(title) && articleList.get(i).GetType().equalsIgnoreCase(type)) {
                    return articleList.get(i);
                }
            } else {
                if (type.equalsIgnoreCase("ALL")) {
                    if (articleList.get(i).GetTitle().equalsIgnoreCase(title)) {
                        return articleList.get(i);
                    }
                }
            }
        }
        return null;
    }

    public void AddComment(String title, String text) throws ExceptionChronicleNotFound {
        boolean hasChronicle = false;
        if (currentUser == null || !currentUser.GetType().equalsIgnoreCase("READER")) System.out.println("No reader is logged in.\n");
        for (int i = 0; i < articleList.size(); i++) {
            if (currentUser != null && articleList.get(i).GetTitle().equalsIgnoreCase(title) && articleList.get(i).GetType().equalsIgnoreCase("CHRONICLE")) {
                ((Chronicle) articleList.get(i)).AddComment(currentUser.GetName(), text);
                System.out.println("New comment added.");
                System.out.println();
                hasChronicle = true;
            }
        }
        if (currentUser != null && currentUser.GetType().equalsIgnoreCase("READER") && !hasChronicle) {
            throw new ExceptionChronicleNotFound();
        }
    }

    public void ListComments(String title) throws ExceptionChronicleNotFound {
        boolean hasChronicle = false;
        for (int i = 0; i < articleList.size(); i++) {
            if (articleList.get(i).GetTitle().equalsIgnoreCase(title) && articleList.get(i).GetType().equalsIgnoreCase("CHRONICLE")) {
                ((Chronicle) articleList.get(i)).PrintComments();
                hasChronicle = true;
            }
        }
        if (!hasChronicle) {
            throw new ExceptionChronicleNotFound();
        }
    }

    public void TopChronicles() {
        System.out.println("Top chronicles:");
        Collections.sort(articleList, new ComparatorListChronicles());
        int timesPrinted = 0;
        for (int i = 0; i < articleList.size(); i++) {
            if (articleList.get(i).GetType().equalsIgnoreCase("CHRONICLE") && ((Chronicle) articleList.get(i)).IsPublished() && timesPrinted < 10) {
                System.out.println(articleList.get(i).GetTitle() + "; " + articleList.get(i).GetAuthor() + "; " + articleList.get(i).GetTheme() + "; " + articleList.get(i).GetTopic() + ((Chronicle) articleList.get(i)).GetPublishDate() + ((Chronicle) articleList.get(i)).GetTotalCommentsString());
                timesPrinted++;
            }
        }
        System.out.println();
    }

    public void ListTopic(String topic) {
        System.out.println(topic + " articles:");
        Collections.sort(articleList, new ComparatorListTopicTheme());
        ListTopicByType("REPORT", topic);
        ListTopicByType("CHRONICLE", topic);
    }

    public void ListTopicByType(String type, String topic) {
        for (int i = 0; i < articleList.size(); i++) {
            if (articleList.get(i).GetTopic().equalsIgnoreCase(topic) && articleList.get(i).GetType().equalsIgnoreCase(type) && articleList.get(i).GetType().equalsIgnoreCase("REPORT")) System.out.println(articleList.get(i).GetTitle() + "; " + articleList.get(i).GetAuthor() + "; " + articleList.get(i).GetTheme() + "; " + articleList.get(i).GetDate() + "; Likes: " + ((Report) articleList.get(i)).GetVotes()); else if (articleList.get(i).GetTopic().equalsIgnoreCase(topic) && articleList.get(i).GetType().equalsIgnoreCase(type) && articleList.get(i).GetType().equalsIgnoreCase("CHRONICLE") && ((Chronicle) articleList.get(i)).IsPublished()) System.out.println(articleList.get(i).GetTitle() + "; " + articleList.get(i).GetAuthor() + "; " + articleList.get(i).GetTheme() + ((Chronicle) articleList.get(i)).GetPublishDate() + ((Chronicle) articleList.get(i)).GetTotalCommentsString());
        }
    }

    public void ListTheme(String theme) {
        System.out.println(theme + " articles:");
        Collections.sort(articleList, new ComparatorListTopicTheme());
        ListThemeByType("REPORT", theme);
        ListThemeByType("CHRONICLE", theme);
    }

    public void ListThemeByType(String type, String theme) {
        for (int i = 0; i < articleList.size(); i++) {
            if (articleList.get(i).GetTheme().equalsIgnoreCase(theme) && articleList.get(i).GetType().equalsIgnoreCase(type) && articleList.get(i).GetType().equalsIgnoreCase("REPORT")) System.out.println(articleList.get(i).GetTitle() + "; " + articleList.get(i).GetAuthor() + "; " + articleList.get(i).GetTopic() + "; " + articleList.get(i).GetDate() + "; Likes: " + ((Report) articleList.get(i)).GetVotes()); else if (articleList.get(i).GetTheme().equalsIgnoreCase(theme) && articleList.get(i).GetType().equalsIgnoreCase(type) && articleList.get(i).GetType().equalsIgnoreCase("CHRONICLE") && ((Chronicle) articleList.get(i)).IsPublished()) System.out.println(articleList.get(i).GetTitle() + "; " + articleList.get(i).GetAuthor() + "; " + articleList.get(i).GetTopic() + ((Chronicle) articleList.get(i)).GetPublishDate() + ((Chronicle) articleList.get(i)).GetTotalCommentsString());
        }
    }
}
