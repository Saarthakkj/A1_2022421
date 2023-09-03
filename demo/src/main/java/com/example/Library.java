package com.example;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.time.LocalTime;

/**
 * Hello world!
 *
 */
public class Library {
   
    //array lists:
    public static List<Book> Books = new ArrayList<> ();
    public static List<member> members = new ArrayList<> ();

    static Integer book_count=0;



    public static void main(String[] args) throws Exception {
        Scanner scanf = new Scanner(System.in);
        System.out.println("Library port is intialized..");
        System.out.println("-----------------");
        System.out.println("1. Enter a librarian");
        System.out.println("2. Enter as a member");
        System.out.println("3. Exit");
        System.out.println("-----------------");
        //System.out.println("Enter your choice");
        int choice = scanf.nextInt();
        scanf.nextLine();
        System.out.println("-----------------");

        //List of members:
        


        while(choice != 3){
            
            switch(choice){
                case 1:
                    //System.out.println("inside case 1");
                    Librarian L = new Librarian();
                    L.displayMenu();
                    //new Librarian();
                    break;
                case 2:
                    //System.out.println("inside Member of Library");

                    System.out.print("Enter your name: ");
                    String name = scanf.nextLine();
                    
                    System.out.print("Enter your phoneno: ");
                    String phoneno = scanf.nextLine();
                    
                    member_manager user = new member_manager(members);
                    boolean exists = user.checkMember(name, phoneno);
                    //Library L_fortime = new Library();
                    if(exists){
                        member M = new member(name, phoneno);
                        M.displaymenu();
                    }
                    else{
                        System.out.println("Member with Name:"+name+" and Phone No: "+phoneno+"doesn't exist.");
                    }
                    
                    break;
                case 3:
                    //don't have to do anything here.
                    
                    break;
                default:
                    System.out.println("Invalid choice");
            }
            System.out.println("Library port is intialized..");
            System.out.println("-----------------");
            System.out.println("1. Enter a librarian");
            System.out.println("2. enter as a member");
            System.out.println("3. exit");
            System.out.println("-----------------");
            //System.out.println("Enter your choice");
            choice = scanf.nextInt();
            scanf.nextLine();
            System.out.println("-----------------");
        }
        scanf.close();
        System.exit(0);
    }
}
