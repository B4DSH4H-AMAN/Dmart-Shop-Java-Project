package abc.shop;

import java.sql.*;
import java.util.Scanner;
/**
 *
 * @author badsh
 */
public class Purchase {
    public static void main(String[] args) {
        Connection con = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        Scanner sc = new Scanner(System.in);
        String Answer;
        int number;

        do{
            System.out.println("**** WELCOME TO ABC SHOP PRODUCTS AND SERVICES ****");
            System.out.println("**** 1. SHOW THE STOCK OF PRODUCTS ****");
            System.out.println("**** 2. PURCHASE PRODUCT ****");
            System.out.println("**** PLEASE ENTER YOUR CHOICE: ****");

            number = sc.nextInt();

            switch (number) {

                case 1:
                System.out.println("PRODUCTS YOU CAN BUY: ");

                try{
                     Class.forName("oracle.jdbc.driver.OracleDriver"); 

                    //serverhost = localhost, port=3306, username=root, password=""
                    con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:abcshop","root","");

                    Statement smt=con.createStatement();

                    //query to display all records from table employee
                    String q="Select * from products";

                    //to execute query
                    rs=smt.executeQuery(q);

                    //to print the resultset on console
                    if(rs.next()){
                        do{
                        System.out.println(rs.getString(1)+", PRODUCT NAME: "
                        +rs.getString(2)+", PRODUCT COST: "+rs.getString(3));
                        }while(rs.next());
                    }
                    else{
                        System.out.println("WE NEED TO RE-ORDER THE STOCKS");
                    }
                }
                catch(Exception e){
                    System.out.println(e);
                }
                    break;

                case 2:
                System.out.println("ENTER PRODUCT ID, NAME, COST, YOUR NAME: ");

                // String upd = "UPDATE `sales` SET `prdSales` = prdSales+1 WHERE `sales`.`prdName` = ? ";
                String sql = "INSERT INTO SALES" + "(prdId, prdName, prdCost, cstName, prdSales)" + "VALUES (?,?,?,?,?)";
                try{
                     Class.forName("oracle.jdbc.driver.OracleDriver"); 
                    int id = sc.nextInt();
                    String name = sc.next();
                    double cost = sc.nextDouble();
                    String cstName = sc.next();
                    int sales = 1;
                    con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:abcshop","root","");

                        pst = con.prepareStatement(sql);
                        pst.setInt(1, id);
                        pst.setString(2, name);
                        pst.setDouble(3, cost);
                        pst.setString(4, cstName);
                        pst.setInt(5, sales);
                        pst.executeUpdate();
                        System.out.println("PRODUCT PURCHASED!");

                    } catch (Exception ex){
                        System.out.println(ex);
                    }
                    break;


                default: System.out.println("!******************!");
                    break;
            }
            System.out.println("**** Do you want to Continue YES/NO: ****");
            Answer = sc.next();
        } while (Answer.equals("Yes"));
    }
}
