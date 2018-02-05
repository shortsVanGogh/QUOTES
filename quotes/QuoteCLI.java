package quotes;

import java.util.*;
import java.io.*;
//import quotes.QuoteList;
//import quotes.QuoteSaxParser;
//import quotes.QuoteSaxHandler;
//import quotes.Quote;

public class QuoteCLI{
  public static QuoteList quoteList;
  private static ArrayList<String> searchList = new ArrayList<String>();
  
  public static void init(){
    QuoteSaxParser qParser = new QuoteSaxParser ("quotes/quotes.xml");
    quoteList = qParser.getQuoteList();
  }
  
  public static void printMenu(){
    System.out.println("MAIN MENU:\n1)Search\n2)Get random quote\n3)Exit application\n");
  }
  
  public static void printRecentSearches(){
    if (searchList==null || searchList.isEmpty()){
      System.out.println("--------------------\n|No recent searches|\n--------------------\n");
      return;
    }
    
    System.out.println("Recent Searches: "+ searchList);
  }
  
  public static void main(String[] args){
    init();
    System.out.println("WELCOME TO QUOTE-CLI\n\n");
    Scanner sc = new Scanner(System.in);
    Integer choice = 0;
    while (choice!=3){
      printMenu();
      printRecentSearches();
      choice = sc.nextInt();
      if (choice==1)
        doSearch();
      else if (choice==2)
        doRandom();
      else if (choice==3){
        System.out.println("\"Thanks for stopping by\"-James W. Edwards\n");
        return;
      }
      else
        System.out.println("invalid choice. try again\n");
    }
  }
  
//asks for search mode/text, uses method from QuoteList
  public static void doSearch(){
    System.out.println("0) Search by author\n1) Search by quote text\n2) Search by both\n3) Back");
    Scanner sc = new Scanner(System.in);
    Integer mode = sc.nextInt();
    if (mode==3) return;
    else if (mode>=4||mode<0) System.out.println("Invalid search mode. Returning to main menu\n\n");
    System.out.println("(press enter to search)\nSearch text: ");
    sc = new Scanner(System.in);
    String searchText = sc.nextLine();
    searchList.add(searchText);
    QuoteList results = quoteList.search(searchText, mode);
    System.out.println(results);
    
  }
  
//uses method from QuoteList  
  public static void doRandom(){
    System.out.println("Your random quote is:\n");
    Quote rando = quoteList.getRandomQuote();
    System.out.println(rando);
  }
  
}