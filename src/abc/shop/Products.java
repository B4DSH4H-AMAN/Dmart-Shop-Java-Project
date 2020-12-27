package abc.shop;

import java.sql.*;
import java.util.Scanner;

/**
 *
 * @author badsh
 */
public class Products {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Connection con = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        Scanner sc = new Scanner(System.in);
        String Answer;
        int number;

        do{
            System.out.println("**** ABC SHOP PRODUCTS AND SERVICES ****");
            System.out.println("**** 1. SEE ALL THE SALES ****");
            System.out.println("**** 2. SALES OF PRODUCTS ****");
            System.out.println("**** PLEASE ENTER YOUR CHOICE: ****");

            number = sc.nextInt();

            switch (number) {

                case 1:
                System.out.println("TOTAL SALES MADE THIS MONTH: ");

                try{
                     Class.forName("oracle.jdbc.driver.OracleDriver"); 

                    //serverhost = localhost, port=3306, username=root, password=""
                    con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:abcshop","root","");
                    Statement smt=con.createStatement();

                    //query to display all records from table employee
                    String q="Select * from sales";

                    //to execute query
                    rs=smt.executeQuery(q);

                    //to print the resultset on console
                    if(rs.next()){
                        do{
                        System.out.println(rs.getString(1)+", PRODUCT NAME: "
                        +rs.getString(2)+", PRODUCT COST: "+rs.getString(3)
                        +", NUMBER OF SOLD ITEAM: "+rs.getString(4));
                        }while(rs.next());
                    }
                    else{
                        System.out.println("WE NEED TO RE-ORDER THE STOCKS");
                    }
                    con.close();
                }
                catch(Exception e){
                    System.out.println(e);
                }
                    break;

                case 2:
                System.out.println("SALES DETAILS WITH CUSTOMER NAMES: ");

                try{
                     Class.forName("oracle.jdbc.driver.OracleDriver"); 

                    //serverhost = localhost, port=3306, username=root, password=123
                    con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:abcshop","root","");
                    Statement smt=con.createStatement();

                    //query to display all records from table employee
                    String q="Select * from sales";

                    //to execute query
                    rs=smt.executeQuery(q);

                    //to print the resultset on console
                    if(rs.next()){
                        do{
                        System.out.println(rs.getString(1)+", PRODUCT NAME: "+rs.getString(2)+", PRODUCT COST: "+rs.getString(3)+", CUSTOMER NAME: "+rs.getString(6));
                        }while(rs.next());
                    }
                    else{
                        System.out.println("Record Not Found...");
                    }
                    con.close();
                }
                catch(Exception e){
                    System.out.println(e);
                }

                default: System.out.println("!******************!");
                    break;
            }
            System.out.println("**** Do you want to Continue YES/NO: ****");
            Answer = sc.next();
        } while (Answer.equals("Yes"));
    }

}
