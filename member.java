import java.util.Scanner;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.time.LocalTime;
import java.time.Duration;



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
    private LocalTime time , time_r;
    private ArrayList<Book> issuedBooks;
    private Integer Fine =0;
   // private member[] members;
   //identifiers of members;
    String name;
    String phoneno;

        //constructor for member
    //thros Exce is for displaymenu method as Library.main(null) is used inside it.
    public member(String name, String phoneno ){
        //Scanner scanf = new Scanner(System.in);
        this.name = name;
        this.phoneno = phoneno;
        this.issuedBooks = new ArrayList<>();
        // long currentTimeSeconds = System.currentTimeMillis() / 1000;
        // System.out.println("Current time in seconds: " + currentTimeSeconds);
        //System.out.println("Constructor of member class is created");
        //displaymenu();
    }

    public ArrayList<Book> getIssuedBooks() {
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
        Library.Books.forEach((b)->System.out.println(b.toString()));
    }
    public void issuebook(Book book , List<Book> Books){
        issuedBooks.add(book);
        Integer count = book.getCopies();
        book.setCopies(count-1);
        //System.out.println("inside the issuebook , count of issuing book " + count);
        if(count-1==0){
            //System.out.println("inside the if-else statment");
            Iterator<Book> iterator = Books.iterator();
            boolean found = false;
            while (iterator.hasNext()) {
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
        Iterator<Book> iterator = issuedBooks.iterator();
        boolean found = false;
        while (iterator.hasNext()) {
            Book b1 = iterator.next();
            if (b1.getId() == bookid) {
                Book b2 = issuedBooks.get(issuedBooks.indexOf(b1));
                Library.Books.add(b2);
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
        return String.format("\nName: " + this.getName() + "\nPhone no: " + this.phoneno +"\nFine: "+ this.getFine() +"\n");
    }


    public void displaymenu() throws Exception{

        

        Scanner scanf = new Scanner(System.in);
        //ss1 : substring1
        String ss1 = name.substring(0,3);
        String ss2 = phoneno.substring(0,5);
        String ID = ss1 + ss2;
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
                    System.out.println(issuedBooks.toString());
                    break;
                case 3:
                //check that currunet n_booko are less than 2
                //check no fine due already
                    boolean condition = true;
                    if(issuedBooks.size()>=2){
                        System.out.println("Cannot issue more than two books");
                        condition = false;
                        break;
                    }
                    if(this.Fine !=0){
                        System.out.println("Fine is due, can't issue books");
                        condition = false;
                        break;
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

                        time = LocalTime.now();
                        
                        System.out.println("book issued second: " + time.getSecond());
                        break;
                    }
                    
                case 4:
                    System.out.println("returning a boook");
                    System.out.println("----------------");
                    System.out.println("Book ID: ");
                    Integer returned_bookid = scanf.nextInt();
                    scanf.nextLine();

                    time_r = LocalTime.now();
                    returnBook(returned_bookid , time , time_r)  ;
                    System.out.println("Book returning second" + time_r.getSecond());
                    break;
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
