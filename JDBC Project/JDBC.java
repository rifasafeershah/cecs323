package cecs.pkg323.java.term.project;

import java.sql.*;
import java.util.Scanner;


public class CECS323JavaTermProject {
    //  Database credentials
    static String USER;
    static String PASS;
    static String DBNAME;
    
    //display format should be adjusted for each function according to the VARCHAR size of each column
    static final String displayFormat="%-5s%-15s%-15s%-15s\n";
    
// JDBC driver name and database URL
    static final String JDBC_DRIVER = "org.apache.derby.jdbc.ClientDriver";
    static String DB_URL = "jdbc:derby://localhost:1527/";
//            + "testdb;user=";
/**
 * Takes the input string and outputs "N/A" if the string is empty or null.
 * @param input The string to be mapped.
 * @return  Either the input string or "N/A" as appropriate.
 * 
 * 
 */
    public static String dispNull (String input) {
        //because of short circuiting, if it's null, it never checks the length.
        if (input == null || input.length() == 0)
            return "N/A";
        else
            return input;
    }
    
    //handle year missing
    public static String dispNull (int input) {
        if (input == 0)
            return "N/A";
        else
            return Integer.toString(input);
    }
    
    public static boolean isConstraintViolation(SQLException se) {
        return se.getSQLState().startsWith("23");
    }
    public static void queryPublisherData (Connection conn) {
        //list ALL publisher data
        String displayFormat="%-25s\n";
        try {
            Statement stmt = conn.createStatement();
            String sql = "SELECT publisherName FROM Publishers";

            ResultSet rs = stmt.executeQuery(sql);
            
            
                
            //STEP 5: Extract data from result set
            System.out.printf(displayFormat, "Publisher Name");

            while (rs.next()) {

                    //Retrieve by column name
                    String name = rs.getString("PUBLISHERNAME");
                    

                    //Display values
                    System.out.printf(displayFormat, 
                            dispNull(name));
                } 
            rs.close();
            stmt.close();
            
            
            
        } catch (SQLException se) {
            //Handle errors for JDBC
            se.printStackTrace();
        } 
        
    }
    
    public static void queryPublisherData (Connection conn, String publisher) {
        //list specific publisher data
        String displayFormat="%-25s%-30s%-15s%-25s\n";
        try {
            
            
            System.out.printf("Querying %s now...\n",publisher);
            
            
            
            String stmt = "SELECT publisherName, publisherAddress, publisherPhone, publisherEmail FROM Publishers  WHERE publisherName = ?";
            PreparedStatement pstmt = conn.prepareStatement(stmt);
            pstmt.setString(1, publisher);


            ResultSet rs = pstmt.executeQuery();
            
            

            if (rs.next()) {
                    //STEP 5: Extract data from result set

                    System.out.printf(displayFormat, "Name", "Address", "Phone", "Email");
                    
                    //Retrieve by column name
                    String address = rs.getString("PUBLISHERADDRESS");
                    String phone = rs.getString("PUBLISHERPHONE");
                    String email = rs.getString("PUBLISHEREMAIL");

                    //Display values
                    System.out.printf(displayFormat, 
                            dispNull(publisher), dispNull(address), dispNull(phone), dispNull(email));
                } else {
                System.out.println("\nPublisher not found!");
            }


            rs.close();
            pstmt.close();
        } catch (SQLException se) {
            //Handle errors for JDBC
            se.printStackTrace();
        } 
        
    }
   
    public static void queryWritingGroups (Connection conn) {
        //list all writing groups
        String displayFormat="%-20s\n";
        try {
            
            Statement stmt = conn.createStatement();
            String sql = "SELECT groupName FROM WritingGroups";
            
            ResultSet rs = stmt.executeQuery(sql);
            
            //STEP 5: Extract data from result set
            System.out.printf(displayFormat, "Writing Group Name");

            while (rs.next()) {

                    //Retrieve by column name
                    String name = rs.getString("groupName");
                    

                    //Display values
                    System.out.printf(displayFormat, 
                            dispNull(name));
                } 


            rs.close();
            stmt.close();
        } catch (SQLException se) {
            //Handle errors for JDBC
            se.printStackTrace();
            String state = se.getSQLState();
            
        } 
        
   
    }
    
    public static void queryWritingGroups (Connection conn, String group) {
        //query specific writing group
        String displayFormat="%-20s%-18s%-15s%-15s\n";
        try {
            System.out.printf("Querying %s now...\n", group);
            String stmt = "SELECT groupName, headWriter, yearFormed, subject FROM WritingGroups WHERE groupName = ?";
            PreparedStatement pstmt = conn.prepareStatement(stmt);
            pstmt.setString(1, group);


            ResultSet rs = pstmt.executeQuery();
            
            //STEP 5: Extract data from result set
            

            if (rs.next()) {
                
                    System.out.printf(displayFormat, "Group Name", "Head Writer", "Year Formed", "Subject");
                    //Retrieve by column name
                    String head = rs.getString("headWriter");
                    int year = rs.getInt("yearFormed");
                    String subject = rs.getString("subject");

                    //Display values
                    System.out.printf(displayFormat, 
                            dispNull(group), dispNull(head), dispNull(year), dispNull(subject));
                } else {
                System.out.println("Writing Group not found!");
            }


            rs.close();
            pstmt.close();
            
        } catch (SQLException se) {
            //Handle errors for JDBC
            se.printStackTrace();
        } 
        
   
    }
    
    public static void queryBookData (Connection conn) {
        //list ALL book data
        String displayFormat="%-25s%-25s%-25s\n";
        try {
            
            Statement stmt = conn.createStatement();
            String sql = "SELECT bookTitle, publisherName, groupName FROM Books";
            
            ResultSet rs = stmt.executeQuery(sql);
            
            //STEP 5: Extract data from result set
            System.out.printf(displayFormat, "Book Title", "Published By", "Written By");

            while (rs.next()) {

                    //Retrieve by column name
                    String title = rs.getString("bookTitle");
                    String publisher = rs.getString("publisherName");
                    String group = rs.getString("groupName");
                    
                    //Display values
                    System.out.printf(displayFormat, 
                            dispNull(title), dispNull(publisher), dispNull(group));
                } 


            rs.close();
            stmt.close();
        } catch (SQLException se) {
            //Handle errors for JDBC
            se.printStackTrace();
        } 
        
    }
    
    public static void queryBookData (Connection conn, String book, String publisher) {
        //query specific book

        String displayFormat="%-25s%-25s%-20s%-20s%-10s\n";
        try {
            
          
            System.out.printf("\nQuerying %s published by %s now...\n", book, publisher);
            String stmt = "SELECT groupName, bookTitle, publisherName, yearPublished, numberPages FROM Books WHERE bookTitle = ? and publisherName = ?";
            PreparedStatement pstmt = conn.prepareStatement(stmt);
            pstmt.setString(1, book);
            pstmt.setString(2, publisher);


            ResultSet rs = pstmt.executeQuery();
            
            //STEP 5: Extract data from result set

            if (rs.next()) {
                    System.out.printf(displayFormat, "Book Title","Group Name", "Publisher", "Publication Year", "Number of Pages");

                    //Retrieve by column name
                    String groupName = rs.getString("groupName");
                    int year = rs.getInt("yearPublished");
                    int pages = rs.getInt("numberPages");

                    //Display values
                    System.out.printf(displayFormat, 
                            dispNull(book), dispNull(groupName), dispNull(publisher), dispNull(year), dispNull(pages));
                } else {
                System.out.printf("The book %s published by %s not found!", book, publisher);
            }
            
            
            rs.close();
            pstmt.close();
            
        } catch (SQLException se) {
            //Handle errors for JDBC
            se.printStackTrace();
        } 
        
   
    }
    
    public static void removeBook(Connection conn, String book, String publisher) throws SQLException {
        String stmt = "SELECT COUNT(*) AS COUNT FROM Books WHERE bookTitle = ?";
        PreparedStatement pstmt = conn.prepareStatement(stmt);
        pstmt.setString(1, book);
        ResultSet rs = pstmt.executeQuery();
        
        if (rs.next()) {
            int count = rs.getInt("COUNT");
            if (count != 0) {
                stmt = "SELECT COUNT(*) AS COUNT FROM Books WHERE bookTitle = ? and publisherName = ?";
                pstmt = conn.prepareStatement(stmt);
                pstmt.setString(1, book);
                pstmt.setString(2, publisher);
                rs = pstmt.executeQuery();
                if (rs.next()) {
                    count = rs.getInt("COUNT");
                    if (count == 1) {
                        stmt = "DELETE FROM Books WHERE bookTitle = ? and publisherName = ?";
                        pstmt = conn.prepareStatement(stmt);
                        pstmt.setString(1, book);
                        pstmt.setString(2, book);

                        pstmt.executeUpdate();
                        System.out.printf("\nSuccessfully deleted the book %s published by %s from our records!", book, publisher);
                    } else {
                        System.out.printf("Although a book by the title of %s exists in our records, it is not recorded under %s's IP", book, publisher);
                    }
                }
                
            } else
                System.out.printf("\nUnfortunately there is no book by the title %s in our records", book);
        }
        
        
        
    }
    
    
    public static boolean validateBook(Connection conn, String book) throws SQLException {
        String stmt = "SELECT COUNT(*) AS COUNT FROM Books WHERE bookTitle = ?";
        
        PreparedStatement pstmt = conn.prepareStatement(stmt);
        pstmt.setString(1, book);
        ResultSet rs = pstmt.executeQuery();
        if (rs.next()) {
            int count = rs.getInt("COUNT");
            if (count!=0)
                return true;
            else
                return false;
        }
        return false;
    }
    public static boolean validateBook(Connection conn, String book, String publisher) throws SQLException {
        String stmt = "SELECT COUNT(*) AS COUNT FROM Books WHERE bookTitle = ? and publisherName = ?";
        PreparedStatement pstmt = conn.prepareStatement(stmt);
        pstmt.setString(1, book);
        pstmt.setString(2, publisher);
        ResultSet rs = pstmt.executeQuery();
        if (rs.next()) {
            int count = rs.getInt("COUNT");
            if (count == 1)
                return true;
            else
                return false;
        }
        return false;
    }
    
    public static boolean validatePublisher(Connection conn, String publisher) throws SQLException {
        
        String stmt = "SELECT COUNT(*) AS COUNT FROM Publishers WHERE publisherName = ?";
        PreparedStatement pstmt = conn.prepareStatement(stmt);
        pstmt.setString(1, publisher);
        ResultSet rs = pstmt.executeQuery();
        if (rs.next()) {
            int count = rs.getInt("COUNT");
            if (count == 1)
                return true;
            else
                return false;
        }
        return false;
   }
    
    public static boolean validateGroup(Connection conn, String group) throws SQLException {
        
        String stmt = "SELECT COUNT(*) AS COUNT FROM WritingGroups WHERE groupName = ?";
        PreparedStatement pstmt = conn.prepareStatement(stmt);
        pstmt.setString(1, group);
        ResultSet rs = pstmt.executeQuery();
        
        if (rs.next()) {
            int count = rs.getInt("COUNT");
            if (count == 1)
                return true;
            else
                return false;
        }
        return false;
   }
    
    
    
    public static void insertBookData(Connection conn, Scanner scnr, String book, String publisher, String group) throws SQLException {
        //publisher and writing group have been validated as existing, but
        //need to check both primary key(title, publisher) and unique constraint(title, group)
        
              
        String stmt = "SELECT COUNT(*) AS COUNT FROM Books WHERE bookTitle = ? and publisherName = ?";
        PreparedStatement pstmt = conn.prepareStatement(stmt);
        pstmt.setString(1, book);
        pstmt.setString(2, publisher);

        ResultSet rs = pstmt.executeQuery();
        
        if (rs.next()) {
            int count = rs.getInt("COUNT");
            if (count == 1) {
                System.out.printf("\nThat particular book %s published by %s already exists! No duplicates allowed.", book, publisher);
                pstmt.close();
                rs.close();
            } else {
                stmt = "SELECT COUNT(*) AS COUNT FROM Books WHERE bookTitle = ? and groupName = ?";
                pstmt = conn.prepareStatement(stmt);
                pstmt.setString(1, book);
                pstmt.setString(2, group);
                rs = pstmt.executeQuery();
                
                if (rs.next()) {
                    count = rs.getInt("COUNT");
                    if (count == 1) {
                        System.out.printf("\nThe book %s already exists within the intellectual property of %s", book, group);
                        pstmt.close();
                        rs.close();
                    } else {
                        System.out.println("\nYear of Publication: ");
                        int year = scnr.nextInt();
                        System.out.println("\nNumber of Pages: ");
                        int pages = scnr.nextInt();
                        try {
                            stmt = "INSERT INTO Books (groupName, bookTitle, publisherName, yearPublished, numberPages) VALUES (?, ?, ?, ?, ?)";
                            pstmt = conn.prepareStatement(stmt);
                            pstmt.setString(1, group);
                            pstmt.setString(2, book);
                            pstmt.setString(3, publisher);
                            pstmt.setInt(4, year);
                            pstmt.setInt(5, pages);

                            pstmt.executeUpdate();
                            System.out.printf("%s, published by %s, written by %s, has been successfully recorded!", book, publisher, group);
                        }
                        catch (SQLException se) {
                            System.out.print(se.getSQLState());
                        }
                        finally {
                            rs.close();
                            pstmt.close();
                        }


                    }
                }
            }
        }


        rs.close();
        pstmt.close();
            
        
        
    }
    
    public static void insertPublisher(Connection conn, Scanner scnr, String publisher, String oldpublisher) throws SQLException {
            
            System.out.println("\nPlease enter the publisher address: ");
            String address = scnr.nextLine();
            System.out.println("\nPlease enter the publisher phone: ");
            String phone = scnr.nextLine();
            System.out.println("\nPlease enter the publisher email: ");
            String email = scnr.nextLine();
            
            
            String stmt = "INSERT INTO Publishers (publisherName, publisherAddress, publisherPhone, publisherEmail) VALUES (?, ?, ?, ?)";
            PreparedStatement pstmt = conn.prepareStatement(stmt);
            pstmt.setString(1, publisher);
            pstmt.setString(2, address);
            pstmt.setString(3, phone);
            pstmt.setString(4, email);
            pstmt.executeUpdate();
            System.out.printf("\n%s has been successfully recorded!", publisher);
            System.out.printf("\nNow updating %s's book ownership to %s", oldpublisher, publisher);
            
            stmt = "UPDATE Books SET Books.publisherName = ? WHERE books.publisherName = ?";
            pstmt = conn.prepareStatement(stmt);
            pstmt.setString(1, publisher);
            pstmt.setString(2, oldpublisher);
            pstmt.executeUpdate();
            
            System.out.printf("\nAll of the books belonging to %s are now considered the intellectual property of %s! Have a nice day!", oldpublisher, publisher);
            
            pstmt.close();
        
    }
    
    
    public static void main(String[] args) {
        //Prompt the user for the database name, and the credentials.
        //If your database has no credentials, you can update this code to 
        //remove that from the connection string.
        Scanner scnr = new Scanner(System.in);
        System.out.print("Name of the database is jdbcwritinggroupsproject.");
        DBNAME = "jdbcwritinggroupsproject";
        System.out.print("\nNo credentials required for this database...");
        
        //Constructing the database URL connection string
        DB_URL = DB_URL + DBNAME;
        Connection conn = null; //initialize the connection
        Statement stmt = null;  //initialize the statement that we're using
        try {
            //STEP 2: Register JDBC driver
            Class.forName("org.apache.derby.jdbc.ClientDriver");

            //STEP 3: Open a connection
            System.out.println("\nConnecting to database...");
            conn = DriverManager.getConnection(DB_URL);
            
            //testing publisher query with user input
            Boolean input = true;
            String publisher, book, group;
            
            while (input) {
                System.out.println("\nWhat would you like to do?"
                        + "\n1) List all writing groups"
                        + "\n2) Query specific writing group data"
                        + "\n3) List all publishers"
                        + "\n4) Query specific publisher data"
                        + "\n5) List all books"
                        + "\n6) Query specific book data"
                        + "\n7) Insert a new book"
                        + "\n8) Insert a new publisher to replace an old one"
                        + "\n9) Remove a book"
                        + "\n10) Quit");
                int choice = scnr.nextInt();
                scnr.nextLine(); //handles the carriage return bug of scanner
                switch (choice) {
                    case 1: //list all groups
                        System.out.println("\nListing all writing groups...");
                        queryWritingGroups(conn);
                        break;
                        
                    case 2: //query specific group
                        System.out.println("\nEnter name of Writing Group");
                        group = scnr.nextLine();
                        queryWritingGroups(conn, group);
                        break;
                        
                    case 3: //list all publishers
                        System.out.println("\nListing all publishers");
                        queryPublisherData(conn);
                        break;
                        
                    case 4: //query specific publisher
                        System.out.println("\nPlease enter the name of the publisher");
                        publisher = scnr.nextLine();
                        queryPublisherData(conn, publisher);
                        break;
                    case 5: //list all books
                        System.out.println("\nListing all books");
                        queryBookData(conn);
                        break;
                    
                    case 6: //query specific book
                        System.out.println("\nQuery a specific book");                        
                        //enter publisher first, check if its a valid publisher
                        System.out.println("\nEnter publisher: ");
                        publisher = scnr.nextLine();
                        if (validatePublisher(conn, publisher)) {
                            //check if book title is valid or duplicate
                            System.out.println("\nPlease enter a book title: ");
                            book = scnr.nextLine();
                            if (validateBook(conn, book)) {
                                if (validateBook(conn, book, publisher)) {
                                    queryBookData(conn, book, publisher);
                                    break;
                                } else {
                                    System.out.printf("\nAlthough we do have %s recorded, it is not with the publisher %s.", book, publisher);
                                    break;
                                }
                            } else {
                                System.out.printf("\nSorry, there is no book by that the title %s in our database.", book);
                                break;
                            }
                            
                        } else {
                            System.out.println("\nSorry that publisher isn't in our records. Please record that Publisher first.");
                            break;
                        }
                        
                    case 7: //insert a book
                        System.out.println("\nInsert a new book");
                        System.out.println("\nPlease enter the name of the publisher: ");
                        publisher = scnr.nextLine();
                        if (validatePublisher(conn, publisher)) {
                            System.out.println("\nPlease enter the Writing Group name: ");
                            group = scnr.nextLine();
                            if (validateGroup(conn, group)) {
                                System.out.println("\nPlease enter the book title: ");
                                book = scnr.nextLine();
                                insertBookData(conn, scnr, book, publisher, group);
                                break;
                            } else {
                                System.out.println("\nSorry, that Writing Group does not exist. Please record the Writing Group first.");
                                break;
                            }
                        } else {
                            System.out.println("\nSorry, that publisher has not been recorded yet. Please insert that particular publisher first before attempting to record their published work.");
                            break;
                        }
                    case 8: //insert a new publisher, updating old one
                        System.out.println("Insert a new publisher");
                        System.out.println("\nPlease enter the name of the OLD publisher: ");
                        String oldpublisher = scnr.nextLine();
                        if (validatePublisher(conn, oldpublisher)) { //if old publisher is in database, continue
                            System.out.println("\nPlease enter the name of the NEW publisher to be inserted: ");
                            publisher = scnr.nextLine();
                            if (!validatePublisher(conn, publisher)) { //checking if new publisher is in database. if it is not, we go ahead, otherwise, its a duplicate
                                insertPublisher(conn, scnr, publisher, oldpublisher);
                                break;
                            } else {
                                System.out.println("\nSorry, that publisher is already in our database.");
                                break;
                            }
                        } else {
                            System.out.println("\nSorry, that publisher is not in our database!");
                            break;
                        }
                    
                    case 9:
                        System.out.println("\nFirst enter the publisher of the book you would like to remove: ");
                        publisher = scnr.nextLine();
                        if (validatePublisher(conn, publisher)) {
                            System.out.println("\nPlease enter the name of the book you wish to remove: ");
                            book = scnr.nextLine();
                            removeBook(conn, book, publisher);
                            break;
                        } else {
                            System.out.printf("\nSorry, there is no publisher by the name of %s in our records.", publisher);
                            break;
                        }
                    case 10: 
                        System.out.println("\nShutting down.");
                        input = false;
                        break;
                        
                    default: 
                        System.out.println("\nInvalid input, please enter one of the menu choices as an integer");
                    

                }
          
            
            }
            
            //STEP 6: Clean-up environment
            
            conn.close();
        } catch (SQLException se) {
            //Handle errors for JDBC
            se.printStackTrace();
        } catch (Exception e) {
            //Handle errors for Class.forName
            e.printStackTrace();
        } finally {
            //finally block used to close resources
            try {
                if (stmt != null) {
                    stmt.close();
                }
            } catch (SQLException se2) {
            }// nothing we can do
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException se) {
                se.printStackTrace();
            }//end finally try
        }//end try
        System.out.println("Goodbye!");
    }//end main
}//end FirstExample}
