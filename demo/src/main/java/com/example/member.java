package com.example;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.time.LocalTime;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;




//import java.util.Iterator;

class member_manager{
    //private member[] members;
    private List<member> members;
    
    public  member_manager(List<member> members){
        this.members = members;
    }
    public boolean checkMember(String name, String phoneNumber) {
        for (member member : members) {
            if (member.getName().equals(name) && member.getPhoneno().equals(phoneNumber)) {
                return true; // Name and phone number match
            }
        }
        return false; // No match found
    }
}


public class member{
    static Integer count_for_ret =0;
    private LocalTime time_book1 , time_book2;
    //new code: 
    private HashMap<String, ArrayList<Book>> issuedBooks;

    private ArrayList<Book> booksformember;
    private Integer Fine =0;
   //identifiers of members;
    String name;
    String phoneno;
    String ID;
    public String generateID(){
        String ss1 = name.substring(0,3);
        String ss2 = phoneno.substring(0,5);
        return String.format(ss1+ss2);
    }

    public String getID() {
        return ID;
    }
    public void setID(String iD) {
        ID = iD;
    }

        //constructor for member
    //thros Exce is for displaymenu method as Library.main(null) is used inside it.
    public member(String name, String phoneno ){
        //Scanner scanf = new Scanner(System.in);
        this.name = name;
        this.phoneno = phoneno;

        //new code: 
        this.issuedBooks = new HashMap<String, ArrayList<Book>>();
        this.booksformember = new ArrayList<Book>();
        //old code: this.issuedBooks = new ArrayList<>();
        this.ID = generateID();
    }

    public HashMap<String, ArrayList<Book>> getIssuedBooks() {
        return issuedBooks;
    }


    public String getName() {
        return name;
    }

    public String getPhoneno() {
        return phoneno;
    }

    public Integer getFine() {
        return Fine;
    }

    public void setFine(Integer fine) {
        Fine = fine;
    }


     public void check(String name, String phoneno){
        System.out.printf("Recieved: " + name + " " + phoneno);
        //checking whether the given member exist or not
        //MUST be added by a librarian.
    }
    public void available_books(){

        for (int i = 0; i < Library.Books.size(); i++) {
            Book b = Library.Books.get(i);
            System.out.println(b.toString());
        }
    }
    public void issuebook(Book book , List<Book> Books){
        //adding to issued books:
        booksformember.add(book);
        Integer count = book.getCopies();
        book.setCopies(count-1);

        System.out.println("inside the issuebook , count of issuing book " + count);

        //what if that was the last copy of the book?
        if(count-1==0){
            //System.out.println("inside the if-else statment");
            Iterator<Book> iterator = Books.iterator();
            boolean found = false;
            while (iterator.hasNext()) {
                //System.out.println("inside the while loop");
                Book b1 = iterator.next();
                if (b1.getName().equals(book.getName()) && b1.getId().equals(book.getId())) {
                    iterator.remove();
                    //System.out.println("Book removed successfuly: "+ b1.getName() );
                    found = true;
                }
            }
        if(!found){
            System.out.println("Book not found");
        }
        }
        
    }

    public void returnBook(Integer bookid ,  LocalTime curr , LocalTime ret){
        Iterator<Book> iterator = booksformember.iterator();
        boolean found = false;
        while (iterator.hasNext()) {
            Book b1 = iterator.next();
            if (b1.getId() == bookid) {
                Book b2 = booksformember.get(booksformember.indexOf(b1));
                Library.Books.add(b2);
                iterator.remove();
                //System.out.println("Book removed successfuly: "+ b1.getName() );
                found = true;
                Duration duration = Duration.between(curr , ret);
                int second =  (int) duration.getSeconds() % 60;
                System.out.println("Seconds book was issued: " + second);
                if(second > 10){
                    Fine = (second-10)*3;
                }else{
                    Fine =0;
                }
            }
        }

        if(!found){
            System.out.println("Book not found");
        }


        //calculating fine:
        // if(ret_m == curr_m ){
        //     if(ret-curr > 10){
        //         Fine = (ret-curr-10)*3;
        //     }else{
        //         Fine =0;
        //     }
        // }else{
        //     Duration duration = Duration.between()
        // }
    }

    public String toString(){
        return String.format("\nName: " + this.getName() + "\nPhone no: " + this.phoneno +"\nFine: "+ this.getFine() +"\n" +"Issued books: " + this.booksformember.toString());
    }

    public Integer calculateFine(LocalTime time_book1 , LocalTime time_r_book1){
        Duration duration = Duration.between(time_book1 , time_r_book1);
        int second =  (int) duration.getSeconds() % 60;
        //System.out.println("Seconds book was issued: " + second);
        if(second > 10){
            Fine = (second-10)*3;
        }else{
            Fine =0;
        }
        return Fine;
    }


    //throws exception for case==6

    public void displaymenu() throws Exception{

        

        Scanner scanf = new Scanner(System.in);
        //ss1 : substring1
        
        System.out.printf("Welcome " + name + " Member ID: " + ID + "\n");
        System.out.println("----------------");
        System.out.println("1. List Available Books");
        System.out.println("2. List my book");
        System.out.println("3. Issue a book");
        System.out.println("4. Return a book");
        System.out.println("5. Pay a fine");
        System.out.println("6. Back");
        System.out.println("----------------");
        int choice = scanf.nextInt();
        scanf.nextLine();

        if(choice ==6){
            Library.main(null);
        }

        while( choice !=6){
            switch(choice){
                case 1:
                    System.out.println("listing available books");
                    System.out.println("----------------");
                    available_books();
                    break;
                case 2:
                    System.out.println("listing my issued books");
                    System.out.println("----------------");
                    if(booksformember.size()==0){
                        System.out.println("No books issued");
                    }else{
                        System.out.println(booksformember.toString());
                    }
                    //new code hre:
                    break;
                case 3:
                //check that currunet n_books are less than 2
                
                    boolean condition = true;
                    if(booksformember.size()==2){
                        System.out.println("Cannot issue more than two books");
                        condition = false;
                        break;
                    }
                    if(booksformember.size() ==1){
                        //check no fine due already
                        if(Fine!=0){
                            System.out.println("Fine is due, can't issue books");
                        }
                        //calculating fine on already issued book (if fine!=0)
                        LocalTime temp_time = LocalTime.now();
                        Fine =  calculateFine(time_book1 , temp_time);
                        if(this.Fine !=0){
                            System.out.println("Fine is due on 1st book, can't issue books");
                            condition = false;
                            break;
                        }
                    }
                
                    if(condition){
                        System.out.println("issuing a book");
                        System.out.print("Book ID: ");
                        Integer bookid = scanf.nextInt();
                        scanf.nextLine();
                        System.out.print("Book name: ");
                        String bookname = scanf.nextLine();
                        //scanf.nextLine();

                        boolean found = false;
                        for (Book book : Library.Books) {
                            if (book.getId() == bookid && book.getName().equals(bookname)) {
                                found = true;
                                issuebook(book , Library.Books);
                                System.out.println("-----------------");
                                System.out.println("Book issued successfully!");
                                System.out.println("-----------------");
                                break;
                            }
                        }
                        if (!found){
                            System.out.println("Book not found!.");
                        }

                        time_book1 = LocalTime.now();
                        
                        System.out.println("book issued second: " + time_book1.getSecond());
                        break;
                    }
                    
                case 4:
                    
                    System.out.println("returning a boook");
                    System.out.println("----------------");
                    System.out.println("Book ID: ");
                    Integer returned_bookid = scanf.nextInt();
                    scanf.nextLine();
                    count_for_ret+=1;
                    if(count_for_ret ==1){
                        LocalTime temp_time2 = LocalTime.now();
                        returnBook(returned_bookid , time_book1 , temp_time2)  ;
                        System.out.println("Book returning second" + temp_time2.getSecond());
                        break;
                    }else if(count_for_ret ==2){
                        LocalTime temp_time3 = LocalTime.now();
                        returnBook(returned_bookid , time_book2 , temp_time3)  ;
                        System.out.println("Book returning second" + temp_time3.getSecond());
                        break;
                    }
                    //have to change it a lot.
                    
                case 5:
                    System.out.println("paying a fine");
                    System.out.println("----------------");
                    System.out.println("Your fine amount is: " + Fine);
                    Fine = 0;
                    System.out.println("Fine paid");
                    break;
                case 6:
                    //System.out.println("inside case 6");
                    scanf.close();
                    break;
                default:
                    System.out.println("Invalid choice");
            }
            System.out.println("----------------");
            System.out.println("1. List Available Books");
            System.out.println("2. List my book");
            System.out.println("3. Issue a book");
            System.out.println("4. Return a book");
            System.out.println("5. Pay a fine");
            System.out.println("6. Back");
            System.out.println("----------------");
            choice = scanf.nextInt();
            scanf.nextLine();
            }
    }
    



}
