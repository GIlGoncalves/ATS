
import java.util.Scanner;
import java.io.*;
import java.util.concurrent.TimeUnit;

import caparicapost.CaparicaPost;
import caparicapost.CaparicaPostClass;
import exceptions.ExceptionChronicleNotFound;
import exceptions.ExceptionNoEditorLoggedIn;
import exceptions.ExceptionNoUserLoggedIn;
import exceptions.ExceptionReportDoesNotExist;
import exceptions.ExceptionUserDoesNotExist;
import exceptions.ExceptionUserLoggedIn;

public class Main {
	private static CaparicaPost cp = new CaparicaPostClass();
	public static void main(String args[]){
		try {
			Long timeafter, timebefore = System.nanoTime();
			EnergyCheckUtils.init();
			double [] before = EnergyCheckUtils.getEnergyStats();
			Scanner in = new Scanner(System.in);
			String cmd = in.next();
			while (!cmd.equalsIgnoreCase("EXIT")){
				switch(cmd.toUpperCase()) {
					case "LOGIN" : Login(cp,in); break;
					case "LOGOUT" : Logout(cp); break;
					case "REGISTER" : Register(cp,in); break;
					case "TOP" : Top(cp,in); break;
					case "LIST" : List(cp,in); break;
					case "LIKE" : Like(cp,in); break;
					case "ASSIGN" : Assign(cp,in); break;
					case "APPROVE" : Approve(cp,in); break;
					case "ADD" : Add(cp,in); break;
				}
				cmd = in.next();
			}
			System.out.println("Exiting.\n");
			PrintWriter writer = new PrintWriter("EnergyTests/"+args[0], "UTF-8");
			double [] after = EnergyCheckUtils.getEnergyStats();
			timeafter = System.nanoTime();
			writer.println("GPU: "+(after[0]-before[0]) + " J");
			writer.println("CPU: "+(after[1]-before[1]) + " J");
			writer.println("Package: "+(after[2]-before[2]) + " J");
			writer.println("Time: "+ ((double)(timeafter - timebefore)/1000000000.0) + " seconds");
			writer.close();
		} catch (Exception e){
			System.out.println(e.getMessage());
		}
	}

	private static void Add(CaparicaPost cp, Scanner in) throws ExceptionChronicleNotFound {
		String type = in.next();
		in.nextLine();
		String title,theme,topic,date,text;
		switch(type.toUpperCase()) {
			case "REPORT": 
			title = in.nextLine();
			theme = in.nextLine();
			topic = in.nextLine();
			date = in.nextLine();
			text = in.nextLine();
			cp.AddReport(type, title, theme, topic, date, text);
			break;

			case "CHRONICLE": 
			title = in.nextLine();
			theme = in.nextLine();
			topic = in.nextLine();
			date = in.nextLine();
			text = in.nextLine();
			cp.AddChronicle(type, title, theme, topic, date, text);
			break;

			case "COMMENT": 
			title = in.nextLine();
			text = in.nextLine();
			try{
				cp.AddComment(title, text);
			} catch (ExceptionChronicleNotFound e){
				System.out.println(e.getMessage());
			}
			break;
		}
	}

	private static void Approve(CaparicaPost cp, Scanner in) {
		in.nextLine();
		String title = in.nextLine();
		String date = in.nextLine();
		cp.Approve(title, date);
	}

	private static void Assign(CaparicaPost cp, Scanner in) throws ExceptionNoEditorLoggedIn {
		String name = in.next();
		try{
			cp.Assign(name);
		} catch (ExceptionNoEditorLoggedIn e){
			System.out.println(e.getMessage());
			System.out.println();
		}
	}

	private static void Logout(CaparicaPost cp) throws ExceptionNoUserLoggedIn {
		try{
			cp.LogoutUser();
		} catch (ExceptionNoUserLoggedIn e){
			System.out.println(e.getMessage());
		}
	}

	private static void Login(CaparicaPost cp, Scanner in) throws ExceptionUserDoesNotExist {
		String name = in.next();
		try{
			cp.LoginUser(name);
		} catch (ExceptionUserDoesNotExist e){
			System.out.println(e.getMessage());
		}
	}

	private static void Like(CaparicaPost cp, Scanner in) throws ExceptionReportDoesNotExist {
		in.nextLine();
		String title = in.nextLine();
		try{
			cp.Like(title);
		} catch (ExceptionReportDoesNotExist e){
			System.out.println(e.getMessage());
		}
	}

	private static void Register(CaparicaPost cp, Scanner in) throws ExceptionUserLoggedIn {
		String theme = null;
		String type = in.next();
		String name = in.next();
		String email = in.next();
		if (type.equalsIgnoreCase("EDITOR")||type.equalsIgnoreCase("JOURNALIST"))
			theme = in.next();
		try{
			cp.RegisterUser(type, name, email, theme);
		} catch (ExceptionUserLoggedIn e){
			System.out.println(e.getMessage());
		}
	}

	private static void Top(CaparicaPost cp, Scanner in) {
		cp.TopChronicles();
	}

	private static void List(CaparicaPost cp, Scanner in) throws ExceptionChronicleNotFound, ExceptionNoEditorLoggedIn {
		String cmd = in.next();
		in.nextLine();
		switch(cmd.toUpperCase()) {
			case "ALL" :
			try{
				cp.ListAllUsers();
			} catch (ExceptionNoEditorLoggedIn e){
				System.out.println(e.getMessage());
			}
			break;

			case "REPORTS" :
			cp.ListReports();
			break;

			case "CHRONICLES" :
			cp.ListChronicles();
			break;

			case "COMMENTS" :
			String title = in.nextLine();
			try{
				cp.ListComments(title);
			} catch (ExceptionChronicleNotFound e){
				System.out.println(e.getMessage());
			}
			break;

			case "TOPIC" : 
			String topic = in.nextLine();
			cp.ListTopic(topic);
			break;

			case "THEME" :
			String theme = in.nextLine();
			cp.ListTheme(theme);
			break;
		}
		System.out.println();
	}
}
