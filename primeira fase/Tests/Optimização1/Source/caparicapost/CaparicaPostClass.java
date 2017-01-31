package caparicapost;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import exceptions.ExceptionChronicleNotFound;
import exceptions.ExceptionNoEditorLoggedIn;
import exceptions.ExceptionNoUserLoggedIn;
import exceptions.ExceptionReportDoesNotExist;
import exceptions.ExceptionUserDoesNotExist;
import exceptions.ExceptionUserLoggedIn;

public class CaparicaPostClass implements CaparicaPost {

    User currentUser;
    
    TreeMap<String, User> userNames;
    TreeSet<Reader> readers;
    TreeSet<Editor> editors;
    TreeSet<Journalist> journalists;
    TreeSet<Collaborator> collaborators;

    TreeMap<String,Article> articleNames;
    ArrayList<Report> reports;
    ArrayList<Chronicle> chronicles;

    public CaparicaPostClass() {
        userNames = new TreeMap<>();
        readers = new TreeSet<>(new ComparatorUserAlphabetically());
        editors = new TreeSet<>(new ComparatorUserAlphabetically());
        journalists = new TreeSet<>(new ComparatorUserAlphabetically());
        collaborators = new TreeSet<>(new ComparatorUserAlphabetically());

        articleNames = new TreeMap<>();
        reports = new ArrayList<>();
        chronicles = new ArrayList<>();

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
            switch (type.toUpperCase()) {
                case "READER":
                    Reader reader = new Reader(type,name,email);
                    this.readers.add(reader);
                    this.userNames.put(name.toUpperCase(),reader);
                    break;
                case "EDITOR":
                    Editor editor = new Editor(type, name, email, theme);
                    this.editors.add(editor);
                    this.userNames.put(name.toUpperCase(),editor);
                    break;
                case "JOURNALIST":
                    Journalist journalist = new Journalist(type, name, email, theme);
                    this.journalists.add(journalist);
                    this.userNames.put(name.toUpperCase(),journalist);
                    break;
                case "COLLABORATOR":
                    Collaborator collaborator = new Collaborator(type, name, email);
                    this.collaborators.add(collaborator);
                    this.userNames.put(name.toUpperCase(),collaborator);
                    break;
            }
            System.out.println("New user registered.");
            System.out.println();
        }
    }

    public boolean HasUser(String name) {
        return this.userNames.containsKey(name.toUpperCase());
    }

    public void LoginUser(String name) throws ExceptionUserDoesNotExist {
        if (!HasUser(name)) {
            throw new ExceptionUserDoesNotExist();
        }
        else if (currentUser != null && currentUser.GetName().equalsIgnoreCase(name))
            System.out.println("User already logged in.\n");
        else if (currentUser != null)
            System.out.println("Another user is logged in.\n");
        else {
            currentUser = this.userNames.get(name.toUpperCase());
            System.out.println("Welcome " + currentUser.GetName() + ".\n");
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
        switch (type.toUpperCase()) {
            case "EDITOR":
                for (User user : this.editors)
                    System.out.println(user.toString());
                break;
            case "JOURNALIST":
                for (User user : this.journalists)
                    System.out.println(user.toString());
                break;
            case "COLLABORATOR":
                for (User user : this.collaborators)
                    System.out.println(user.toString());
                break;
            case "READER":
                for (User user : this.readers)
                    System.out.println(user.toString());
                break;
        }
    }

    public void AddReport(String type, String title, String theme, String topic, String date, String text) {
        if (currentUser == null || !currentUser.GetType().equalsIgnoreCase("JOURNALIST"))
            System.out.println("No journalist is logged in.\n");
        else if (HasArticle(title, "ALL"))
            System.out.println("There is another article with that title.\n");
        else {
            String author = currentUser.GetName();
            String parsedDate = GregCal(date);
            Report report = new Report(type, title, author, theme, topic, parsedDate, text);
            this.articleNames.put(title.toUpperCase(),report);
            this.reports.add(report);
            System.out.println("New report published.");
            System.out.println();
        }
    }

    public void ListReports() {
        if (currentUser == null || !currentUser.GetType().equalsIgnoreCase("JOURNALIST"))
            System.out.println("No journalist is logged in.");
        else {
            System.out.println("Reports published:");
            Collections.sort(reports,new ComparatorListReportsAlphabetically());
            Collections.sort(reports,new ComparatorListReportsByVotes());
            Collections.sort(chronicles,new ComparatorListReportsAlphabetically());
            Collections.sort(chronicles,new ComparatorListReportsByVotes());
            ListArticleByType("REPORT");
        }
    }

    public void ListArticleByType(String type) {
        switch(type.toUpperCase()) {
            case "REPORT":
                for(Report report : this.reports) {
                    if(currentUser.GetName().equalsIgnoreCase(report.GetAuthor())) {
                        System.out.println(report.toString());
                    }
                }
                break;
            case "CHRONICLE":
                for(Chronicle chronicle : this.chronicles) {
                    if (currentUser.GetName().equalsIgnoreCase(chronicle.GetAuthor())) {
                        System.out.println(chronicle.toString(((Collaborator) currentUser).GetEditorName()));
                    }
                }
                break;
        }
    }

    public void AddChronicle(String type, String title, String theme, String topic, String date, String text) {
        if (currentUser == null || !currentUser.GetType().equalsIgnoreCase("COLLABORATOR"))
            System.out.println("No collaborator is logged in.\n");
        else if (HasArticle(title, "ALL"))
            System.out.println("There is another article with that title.\n");
        else {
            String author = currentUser.GetName();
            String parsedDate = GregCal(date);
            Chronicle chronicle = new Chronicle(type, title, author, theme, topic, parsedDate, text);
            this.articleNames.put(title.toUpperCase(),chronicle);
            this.chronicles.add(chronicle);
            System.out.println("New chronicle added.");
            System.out.println();
        }
    }

    public void ListChronicles() {
        if (currentUser == null || !currentUser.GetType().equalsIgnoreCase("COLLABORATOR"))
            System.out.println("No collaborator is logged in.");
        else {
            System.out.println("Chronicles written:");
            Collections.sort(reports, new ComparatorListChronicles());
            Collections.sort(chronicles, new ComparatorListChronicles());
            ListArticleByType("CHRONICLE");
        }
    }

    public void Assign(String name) throws ExceptionNoEditorLoggedIn {
        boolean hasCollab = false;
        if (currentUser != null && currentUser.GetType().equalsIgnoreCase("EDITOR")) {
            User user = this.userNames.get(name.toUpperCase());
            if(user != null && user.GetType().equalsIgnoreCase("COLLABORATOR")) {
                ((Collaborator) user).SetEditor(currentUser);
                System.out.println("New editor assignment.");
                System.out.println();
                hasCollab = true;

            }
        } else {
            throw new ExceptionNoEditorLoggedIn();
        }
        if (!hasCollab && currentUser != null && currentUser.GetType().equalsIgnoreCase("EDITOR"))
            System.out.println("Collaborator does not exist.\n");
    }

    public void Like(String title) throws ExceptionReportDoesNotExist {
        boolean hasReport = false;
        Article article = this.articleNames.get(title.toUpperCase());
        if(article != null && article.GetType().equalsIgnoreCase("REPORT")) {
            ((Report) article).AddVote();
            System.out.println("Likes: " + ((Report) article).GetVotes() + ".");
            System.out.println();
            hasReport = true;
        }
        if (!hasReport) {
            throw new ExceptionReportDoesNotExist();
        }
    }

    public void Approve(String title, String date) {
        if (currentUser == null || !currentUser.GetType().equalsIgnoreCase("EDITOR"))
            System.out.println("No editor is logged in.\n");
        else if (!HasArticle(title, "CHRONICLE"))
                System.out.println("Chronicle does not exist.\n");
        else if (!CanApprove(title)) System.out.println("Not authorized to approve this chronicle.\n");
        else if (((Chronicle) GetArticle(title, "CHRONICLE")).IsPublished())
            System.out.println("Chronicle already approved.\n");
        else {
            Article article = this.articleNames.get(title.toUpperCase());
            if(article != null) {
                ((Chronicle) article).Publish();
                String parsedDate = GregCal(date);
                ((Chronicle) article).SetPublishDate(parsedDate);
                System.out.println("New chronicle published.");
                System.out.println();
            }
        }
    }

    public boolean CanApprove(String title) {
        Article article = this.articleNames.get(title.toUpperCase());
        if(article != null && article.GetType().equalsIgnoreCase("CHRONICLE")) {
            Chronicle chronicle = (Chronicle) article;
            User user = this.userNames.get(chronicle.GetAuthor().toUpperCase());
            if (user != null && (((Collaborator) user).GetSimpleEditorName().equalsIgnoreCase(currentUser.GetName()))) {
                return true;
            }
        }
        return false;
    }

    public boolean HasArticle(String title, String type) {
        Article article = this.articleNames.get(title.toUpperCase());
        switch (type.toUpperCase()) {
            case "ALL":
                if (article != null) {
                    return true;
                } else {
                    return false;
                }
            default:
                if (article != null && article.GetType().equalsIgnoreCase(type)) {
                    return true;
                } else {
                    return false;
                }
        }
    }

    public Article GetArticle(String title, String type) {
        Article article = this.articleNames.get(title.toUpperCase());
        switch (type.toUpperCase()) {
            case "ALL":
                return article;
            default:
                if (article.GetType().equalsIgnoreCase(type)) {
                    return article;
                } else {
                    return null;
                }
        }
    }

    public void AddComment(String title, String text) throws ExceptionChronicleNotFound {
        boolean hasChronicle = false;
        if (currentUser == null || !currentUser.GetType().equalsIgnoreCase("READER"))
            System.out.println("No reader is logged in.\n");
        Article article = this.articleNames.get(title.toUpperCase());
        if(article != null && currentUser != null && article.GetTitle().equalsIgnoreCase(title) && article.GetType().equalsIgnoreCase("CHRONICLE")) {
                ((Chronicle) article).AddComment(currentUser.GetName(), text);
                System.out.println("New comment added.");
                System.out.println();
                hasChronicle = true;
        }
        if (currentUser != null && currentUser.GetType().equalsIgnoreCase("READER") && !hasChronicle) {
            throw new ExceptionChronicleNotFound();
        }
    }

    public void ListComments(String title) throws ExceptionChronicleNotFound {
        boolean hasChronicle = false;
        Article article = this.articleNames.get(title.toUpperCase());
        if(article != null && article.GetTitle().equalsIgnoreCase(title) && article.GetType().equalsIgnoreCase("CHRONICLE")) {
            ((Chronicle) article).PrintComments();
            hasChronicle = true;
        }
        if (!hasChronicle) {
            throw new ExceptionChronicleNotFound();
        }
    }

    public void TopChronicles() {
        System.out.println("Top chronicles:");
        Collections.sort(reports, new ComparatorListChronicles());
        Collections.sort(chronicles,new ComparatorListChronicles());
        int timesPrinted = 0;
        for(Chronicle chronicle : this.chronicles) {
            if(chronicle.IsPublished() && timesPrinted < 10) {
                System.out.println(chronicle.toString());
                timesPrinted++;
            }
        }
        System.out.println();
    }

    public void ListTopic(String topic) {
        System.out.println(topic + " articles:");
        Collections.sort(reports, new ComparatorListTopicTheme());
        Collections.sort(chronicles, new ComparatorListTopicTheme());
        ListTopicByType("REPORT", topic);
        ListTopicByType("CHRONICLE", topic);
    }


    public void ListTopicByType(String type, String topic) {
        switch(type.toUpperCase()) {
            case "REPORT":
                for(Report report : this.reports) {
                    if(report.GetTopic().equalsIgnoreCase(topic)) {
                        System.out.println(report.toStringTopic());
                    }
                }
                break;
            case "CHRONICLE":
                for(Chronicle chronicle : this.chronicles) {
                    if(chronicle.GetTopic().equalsIgnoreCase(topic) && chronicle.IsPublished()) {
                        System.out.println(chronicle.toStringTopic());
                    }
                }
                break;
        }
    }

    public void ListTheme(String theme) {
        System.out.println(theme + " articles:");
        Collections.sort(reports, new ComparatorListTopicTheme());
        Collections.sort(chronicles, new ComparatorListTopicTheme());
        ListThemeByType("REPORT", theme);
        ListThemeByType("CHRONICLE", theme);
    }

    public void ListThemeByType(String type, String theme) {
        switch(type.toUpperCase()) {
            case "REPORT":
                for(Report report : this.reports) {
                    if(report.GetTheme().equalsIgnoreCase(theme)) {
                        System.out.println(report.toStringTheme());
                    }
                }
                break;
            case "CHRONICLE":
                for(Chronicle chronicle : this.chronicles) {
                    if(chronicle.GetTheme().equalsIgnoreCase(theme) && chronicle.IsPublished()) {
                        System.out.println(chronicle.toStringTheme());
                    }
                }
                break;
        }
    }
}
