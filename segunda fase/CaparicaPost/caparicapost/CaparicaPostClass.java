package caparicapost;

import comando.Comando;
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
    final String PONTO =".\n";
    final String ESPACO ="-";
    final String P =".";
    
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
        
        StringBuilder s = new StringBuilder();
                try {
            SimpleDateFormat format = new SimpleDateFormat(Comando.DATE);
            Date date = format.parse(newDate);
            GregorianCalendar calendar = new GregorianCalendar();
            calendar.setTime(date);
            s.append(calendar.get(GregorianCalendar.DAY_OF_MONTH));
            s.append( ESPACO); 
            s.append(calendar.get(GregorianCalendar.MONTH) + 1);
            s.append(ESPACO);
            s.append(calendar.get(GregorianCalendar.YEAR));
            return s.toString();
        } catch (ParseException e) {
            e.printStackTrace();

        }
        return s.toString();
    }

    public void RegisterUser(String type, String name, String email, String theme) throws ExceptionUserLoggedIn {
        if (currentUser != null) {
            throw new ExceptionUserLoggedIn();
        }
        else if (this.userNames.containsKey(name.toUpperCase())) System.out.println(Comando.USERA);
        if (currentUser == null && this.userNames.containsKey(name.toUpperCase())==false) {
            //switch (type.toUpperCase()) {
            
                //case "READER":
                if(type.equalsIgnoreCase(Comando.READER)) {
                    Reader reader = new Reader(type,name,email);
                    this.readers.add(reader);
                    this.userNames.put(name.toUpperCase(),reader);
                }
                
                //case "EDITOR":
                if(type.equalsIgnoreCase(Comando.EDITOR)) {
                    Editor editor = new Editor(type, name, email, theme);
                    this.editors.add(editor);
                    this.userNames.put(name.toUpperCase(),editor);
                }
                //case "JOURNALIST":
                if(type.equalsIgnoreCase(Comando.JOURNALIST)) {
                    Journalist journalist = new Journalist(type, name, email, theme);
                    this.journalists.add(journalist);
                    this.userNames.put(name.toUpperCase(),journalist);
                }
                //case "COLLABORATOR":
                if(type.equalsIgnoreCase(Comando.COLLABORATOR)) {
                    Collaborator collaborator = new Collaborator(type, name, email);
                    this.collaborators.add(collaborator);
                    this.userNames.put(name.toUpperCase(),collaborator);
                }
            //}
            System.out.println(Comando.NEWU);
            System.out.println();
        }
    }

    public void LoginUser(String name) throws ExceptionUserDoesNotExist {
        if (this.userNames.containsKey(name.toUpperCase())==false) {
            throw new ExceptionUserDoesNotExist();
        }
        else if (currentUser != null && currentUser.GetName().equalsIgnoreCase(name))
            System.out.println(Comando.USERLOG);
        else if (currentUser != null)
            System.out.println(Comando.USERAL);
        else {
            StringBuilder s = new StringBuilder();
            currentUser = this.userNames.get(name.toUpperCase());
            s.append(Comando.WELCOME);
            s.append(currentUser.GetName()).append(PONTO);
            
            System.out.println(s.toString());
        }
    }

    public void LogoutUser() throws ExceptionNoUserLoggedIn {
        if (currentUser != null) {
            StringBuilder s = new StringBuilder();
            s.append(Comando.GOOD);
            s.append(currentUser.GetName()).append(PONTO);
            System.out.println(s.toString());
            currentUser = null;
        } else {
            throw new ExceptionNoUserLoggedIn();
        }
    }

    public void ListAllUsers() throws ExceptionNoEditorLoggedIn {
        if (currentUser != null && currentUser.GetType().equalsIgnoreCase(Comando.EDITOR)) {
            System.out.println(Comando.ALLUSER);
            ListUserByType(Comando.EDITOR);
            ListUserByType(Comando.JOURNALIST);
            ListUserByType(Comando.COLLABORATOR);
            ListUserByType(Comando.READER);
        } else {
            throw new ExceptionNoEditorLoggedIn();
        }
    }

    public void ListUserByType(String type) {
        //switch (type.toUpperCase()) {
            //case "EDITOR":
               if(type.equalsIgnoreCase(Comando.EDITOR)) {
                for (User user : this.editors)
                    System.out.println(user.toString());
               }
               
            //case "JOURNALIST":
            if(type.equalsIgnoreCase(Comando.JOURNALIST)){
            
                for (User user : this.journalists)
                    System.out.println(user.toString());
            }
            //case "COLLABORATOR":
            if(type.equalsIgnoreCase(Comando.COLLABORATOR)){
                for (User user : this.collaborators)
                    System.out.println(user.toString());
            }
            //case "READER":
            if(type.equalsIgnoreCase(Comando.READER)) {
                for (User user : this.readers)
                    System.out.println(user.toString());
            }
        //}
    }

    public void AddReport(String type, String title, String theme, String topic, String date, String text) {
        if (currentUser == null || currentUser.GetType().equalsIgnoreCase(Comando.JOURNALIST)==false)
            System.out.println(Comando.NOJORNA);
        else if (this.articleNames.containsKey(title.toUpperCase()))
            System.out.println(Comando.ANOTHER);
        else {
            String author = currentUser.GetName();
            String parsedDate = GregCal(date);
            Report report = new Report(type, title, author, theme, topic, parsedDate, text);
            this.articleNames.put(title.toUpperCase(),report);
            this.reports.add(report);
            System.out.println(Comando.NEWREPOR);
            System.out.println();
        }
    }

    public void ListReports() {
        if (currentUser == null || currentUser.GetType().equalsIgnoreCase(Comando.JOURNALIST)==false)
            System.out.println(Comando.NO);
        else {
            System.out.println(Comando.REPORTPUB);
            Collections.sort(reports,new ComparatorListReportsAlphabetically());
            Collections.sort(reports,new ComparatorListReportsByVotes());
            Collections.sort(chronicles,new ComparatorListReportsAlphabetically());
            Collections.sort(chronicles,new ComparatorListReportsByVotes());
            ListArticleByType(Comando.REPORT);
        }
    }

    public void ListArticleByType(String type) {
        //switch(type.toUpperCase()) {
            //case "REPORT":
            if(type.equalsIgnoreCase(Comando.REPORT)){
                for(Report report : this.reports) {
                    if(currentUser.GetName().equalsIgnoreCase(report.GetAuthor())) {
                        System.out.println(report.toString());
                    }
                }
            }
            //case "CHRONICLE":
            if(type.equalsIgnoreCase(Comando.CHRONICLE)) {
                for(Chronicle chronicle : this.chronicles) {
                    if (currentUser.GetName().equalsIgnoreCase(chronicle.GetAuthor())) 
                     System.out.println(chronicle.toString(((Collaborator) currentUser).GetEditorName()));
                }  
        }
    }

    public void AddChronicle(String type, String title, String theme, String topic, String date, String text) {
        if (currentUser == null || currentUser.GetType().equalsIgnoreCase(Comando.COLLABORATOR)==false)
            System.out.println(Comando.COLLABORATORIS);
        else if (this.articleNames.containsKey(title.toUpperCase()))
            System.out.println(Comando.ANOTHER);
        else {
            String author = currentUser.GetName();
            String parsedDate = GregCal(date);
            Chronicle chronicle = new Chronicle(type, title, author, theme, topic, parsedDate, text);
            this.articleNames.put(title.toUpperCase(),chronicle);
            this.chronicles.add(chronicle);
            System.out.println(Comando.NEWCHRONICLE);
            System.out.println();
        }
    }

    public void ListChronicles() {
        if (currentUser == null || currentUser.GetType().equalsIgnoreCase(Comando.COLLABORATOR)==false)
            System.out.println(Comando.NOCOLLABORATOR);
        else {
            System.out.println(Comando.CHRONICLESW);
            Collections.sort(reports, new ComparatorListChronicles());
            Collections.sort(chronicles, new ComparatorListChronicles());
            ListArticleByType(Comando.CHRONICLE);
        }
    }

    public void Assign(String name) throws ExceptionNoEditorLoggedIn {
        boolean hasCollab = false;
        if (currentUser != null && currentUser.GetType().equalsIgnoreCase(Comando.EDITOR)) {
            User user = this.userNames.get(name.toUpperCase());
            if(user != null && user.GetType().equalsIgnoreCase(Comando.COLLABORATOR)) {
                ((Collaborator) user).SetEditor(currentUser);
                System.out.println(Comando.NEWEDITOR);
                System.out.println();
                hasCollab = true;

            }
        } else {
            throw new ExceptionNoEditorLoggedIn();
        }
        if (hasCollab==false && currentUser != null && currentUser.GetType().equalsIgnoreCase(Comando.EDITOR))
            System.out.println(Comando.NOTEXISTC);
    }

    public void Like(String title) throws ExceptionReportDoesNotExist {
        boolean hasReport = false;
        Article article = this.articleNames.get(title.toUpperCase());
        if(article != null && article.GetType().equalsIgnoreCase(Comando.REPORT)) {
            ((Report) article).AddVote();
            StringBuilder s = new StringBuilder();
            s.append(Comando.LIKES);
            s.append(((Report) article).GetVotes()).append(P);
            
            System.out.println(s.toString());
            System.out.println();
            hasReport = true;
        }
        if (hasReport==false) {
            throw new ExceptionReportDoesNotExist();
        }
    }

    public void Approve(String title, String date) {
        if (currentUser == null || currentUser.GetType().equalsIgnoreCase(Comando.EDITOR)==false)
            System.out.println(Comando.NOEDITOR);
        else if (this.articleNames.containsKey(title.toUpperCase())==false)
                System.out.println(Comando.NOTEXISTCH);
        else if (CanApprove(title)==false) 
            System.out.println(Comando.NOTAUTH);
        else if (((Chronicle) this.articleNames.get(title.toUpperCase())).IsPublished())
            System.out.println(Comando.CHRONICLEAPPR);
        else {
            Article article = this.articleNames.get(title.toUpperCase());
            if(article != null) {
                ((Chronicle) article).Publish();
                String parsedDate = GregCal(date);
                ((Chronicle) article).SetPublishDate(parsedDate);
                System.out.println(Comando.NEWCHRO);
                System.out.println();
            }
        }
    }

    public boolean CanApprove(String title) {
        Article article = this.articleNames.get(title.toUpperCase());
        if(article != null && article.GetType().equalsIgnoreCase(Comando.CHRONICLE)) {
            Chronicle chronicle = (Chronicle) article;
            User user = this.userNames.get(chronicle.GetAuthor().toUpperCase());
            if (user != null && (((Collaborator) user).GetSimpleEditorName().equalsIgnoreCase(currentUser.GetName()))) {
                return true;
            }
        }
        return false;
    }

    public void AddComment(String title, String text) throws ExceptionChronicleNotFound {
        boolean hasChronicle = false;
        if (currentUser == null || currentUser.GetType().equalsIgnoreCase(Comando.READER)==false)
            System.out.println(Comando.NOREADER);
        Article article = this.articleNames.get(title.toUpperCase());
        if(article != null && currentUser != null && article.GetTitle().equalsIgnoreCase(title) 
                && article.GetType().equalsIgnoreCase(Comando.CHRONICLE)) {
                ((Chronicle) article).AddComment(currentUser.GetName(), text);
                System.out.println(Comando.NEWCOMMENT);
                System.out.println();
                hasChronicle = true;
        }
        if (currentUser != null && currentUser.GetType().equalsIgnoreCase(Comando.READER) && hasChronicle==false) {
            throw new ExceptionChronicleNotFound();
        }
    }

    public void ListComments(String title) throws ExceptionChronicleNotFound {
        boolean hasChronicle = false;
        Article article = this.articleNames.get(title.toUpperCase());
        if(article != null && article.GetTitle().equalsIgnoreCase(title) && 
                article.GetType().equalsIgnoreCase(Comando.CHRONICLE)) {
            ((Chronicle) article).PrintComments();
            hasChronicle = true;
        }
        if (hasChronicle==false) {
            throw new ExceptionChronicleNotFound();
        }
    }

    public void TopChronicles() {
        System.out.println(Comando.TOPCHRO);
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
        StringBuilder s = new StringBuilder();
        s.append(topic).append(Comando.ARTICLE);
        System.out.println(s.toString());
        Collections.sort(reports, new ComparatorListTopicTheme());
        Collections.sort(chronicles, new ComparatorListTopicTheme());
        ListTopicByType(Comando.REPORT, topic);
        ListTopicByType(Comando.CHRONICLE, topic);
    }


    public void ListTopicByType(String type, String topic) {
        //switch(type.toUpperCase()) {
            //case "REPORT":
            if(type.equalsIgnoreCase(Comando.REPORT)) {
                for(Report report : this.reports) {
                    if(report.GetTopic().equalsIgnoreCase(topic)) {
                        System.out.println(report.toStringTopic());
                    }
                }
            }
            //case "CHRONICLE":
            if(type.equalsIgnoreCase(Comando.CHRONICLE)) {
                for(Chronicle chronicle : this.chronicles) {
                    if(chronicle.GetTopic().equalsIgnoreCase(topic) && chronicle.IsPublished()) {
                        System.out.println(chronicle.toStringTopic());
                    }
                }
            }
                //break;
        //}
    }

    public void ListTheme(String theme) {
        StringBuilder s = new StringBuilder();
        s.append(theme).append(Comando.ARTICLE);
        System.out.println(s.toString());
        Collections.sort(reports, new ComparatorListTopicTheme());
        Collections.sort(chronicles, new ComparatorListTopicTheme());
        ListThemeByType(Comando.REPORT, theme);
        ListThemeByType(Comando.CHRONICLE, theme);
    }

    public void ListThemeByType(String type, String theme) {
        //switch(type.toUpperCase()) {
            //case "REPORT":
            if(type.equalsIgnoreCase(Comando.REPORT)) {
                for(Report report : this.reports) {
                    if(report.GetTheme().equalsIgnoreCase(theme)) {
                        System.out.println(report.toStringTheme());
                    }
                }
            }
                //break;
            //case "CHRONICLE":
            if(type.equalsIgnoreCase(Comando.CHRONICLE)) {
                for(Chronicle chronicle : this.chronicles) {
                    if(chronicle.GetTheme().equalsIgnoreCase(theme) && chronicle.IsPublished()) {
                        System.out.println(chronicle.toStringTheme());
                    }
                }
            }
                //break;
        //}
    }
}
