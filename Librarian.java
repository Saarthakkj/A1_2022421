import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;
import java.util.Iterator;

class Book{ // implementation of books (can make a separate file if needed.)
    private  String name;
    private String author;
    private Integer id;
    static Integer copies=0;

    public void setCopies(Integer copies) {
        Book.copies = copies;
    }

    public String getAuthor() {
        return author;
    }
    public String getName() {
        return name;
    }
    public Integer getId() {
        return id;
    }
    public Integer getCopies() {
        return copies;
    }


    public Book(String Name, String Author, Integer Id , Integer copy){
        this.name = Name;
        this.author = Author; 
        this.id = Id;
        copies += copy;
    }


    public String toString(){
        // no need to add copies in this toString method
        return String.format("Id: "+ this.getId() + "\nName: " + this.getName() + "\nAuthor: "+ this.getAuthor() +"\n");
    }
}

class book_manager{
    public List<Book> Books = new ArrayList<> ();

    public  book_manager(List<Book> books){
        this.Books = books;
    }
}
class methods{
    public void addmember(String name, String phoneno,List<member> members){
        // List<member> members = new ArrayList<> ();

        //when adding a member: fine is 0
        member m =new member(name , phoneno);
        m.setFine(0);
        members.add(m);
        //member_manager manager = new member_manager(members);

        //try run:
        // boolean exists = manager.checkMember("Saarthak", "1234");
        // System.out.println("Member exists: " + exists);
    }
    public void removemember(String name, String phoneno, List<member> members){
        Iterator<member> iterator = members.iterator();
        while (iterator.hasNext()) {
            member m1 = iterator.next();
            if (m1.getName().equals(name) && m1.getPhoneno().equals(phoneno)) {
                iterator.remove();
            }
        }
        System.out.println("Removed successfuly: "+ name + " " + phoneno);
    }

    public void addbook(String name, String author, Integer id, Integer copies, List<Book> Books){
        Books.add(new Book(name , author , id , copies));
    }

    public void removebook(String name, String author, List<Book> Books){
        Iterator<Book> iterator = Books.iterator();
        boolean found = false;
        while (iterator.hasNext()) {
            Book b1 = iterator.next();
            if (b1.getName().equals(name) && b1.getAuthor().equals(author)) {
                iterator.remove();
                System.out.println("Book removed successfuly: "+ name + " " + author);
                found = true;
            }
        }
        if(!found){
            System.out.println("Book not found");
        }
    }
}


public class Librarian extends methods{
    public void displayMenu() throws Exception {
        Scanner scanf = new Scanner(System.in);

        System.out.println("------------------");
        System.out.println("1. Register a member");
        System.out.println("2. Remove a member");
        System.out.println("3. Add a book");
        System.out.println("4. Remove a book");
        System.out.println("5. View all members along with their books and fines to be paid");
        System.out.println("6. View all books");
        System.out.println("7. Back");
        System.out.println("-------------------");

        //System.out.println("Enter your choice");
        int choice = scanf.nextInt();
        scanf.nextLine();

        methods functions = new methods();

        
        if(choice ==7){
            Library.main(null);
        }
        // Rest of your code here
        while (choice != 7){
            

            switch(choice){
                case 1:
                    //System.out.println("inside case 1 of librarian");
                    System.out.println("-------------------");

                    System.out.print("Name: ");
                    String name = scanf.nextLine();
                    System.out.print("Age: ");
                    String age = scanf.nextLine();
                    System.out.print("Phone number: ");
                    String phoneno = scanf.nextLine();
                    System.out.println("-------------------");
                    String ss1 = name.substring(0,3);
                    String ss2 = phoneno.substring(0,5);
                    String ID = ss1 + ss2;
                    System.out.println("Member Successfully Registered with "+ ID +"!");

                    functions.addmember(name, phoneno , Library.members);
                    break;
                case 2:
                    System.out.println("Removing a member");
                    System.out.print("Name: ");
                    String name1 = scanf.nextLine();
                    System.out.print("Phone number: ");
                    String phoneno1 = scanf.nextLine();
                    functions.removemember(name1, phoneno1, Library.members);
                    break;
                case 3: //adding a book

                    System.out.println("adding a book");
                    System.out.println("-------------------");

                    System.out.print("Name: ");
                    String name_booka = scanf.nextLine();
                    System.out.print("Author: ");
                    String author_a = scanf.nextLine();
                    System.out.print("Number of copies: ");
                    Integer copies = scanf.nextInt();
                    System.out.println("-------------------");
                  
                    System.out.println("Book added successfuly!");

                    // for (Book book : Library.Books) {
                    //     System.out.println(book.toString());
                    // }
                    //System.out.println("\nUpdated\n");

                    Library.book_count+=1;

                    functions.addbook(name_booka, author_a , Library.book_count, copies  ,Library.Books);
                    break;
                case 4:
                    System.out.println("removing a book");
                    System.out.print("Name: ");
                    String name_bookr = scanf.nextLine();
                    System.out.print("Author: ");
                    String author_r = scanf.nextLine();
                    functions.removebook(name_bookr, author_r, Library.Books);
                    break;
                case 5:
                    System.out.println("viewing all members with books and fines");
                    Library.members.forEach((m)->System.out.println(m.toString()));
                    break;
                case 6:
                    //System.out.println("viewing all books");
                    System.out.println("-------------------");
                    Library.Books.forEach((b)->System.out.println(b.toString()));
                    break;
                case 7:
                    System.out.println("Back");
                    //Library.main(new String[0]);  
                    break;
                default:
                    System.out.println("invalid choice");
            }
            System.out.println("-------------------");
            System.out.println("1. Register a member");
            System.out.println("2. Remove a member");
            System.out.println("3. Add a book");
            System.out.println("4. remove a book");
            System.out.println("5. View all members along with their book and fines to be paid");
            System.out.println("6. View all books");
            System.out.println("7. Back");
            System.out.println("-------------------");
            choice = scanf.nextInt();
            scanf.nextLine();
            if(choice ==7){
            Library.main(null);
            }
        //break;
    }
        scanf.close();
    }
}
