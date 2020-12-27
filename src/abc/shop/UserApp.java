package abc.shop;
import java.util.Scanner;


import java.sql.*;
/**
 *
 * @author badsh
 */

public class UserApp {

    public static void main(String[] args) {
        Connection con = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        Scanner sc = new Scanner(System.in);
        String Answer;
        int number;

        do{
            System.out.println("**** ABC SHOP PRODUCTS AND SERVICES ****");
            System.out.println("**** 1. ADD PRODUCTS ****");
            System.out.println("**** 2. DELETE PRODUCTS ****");
            System.out.println("**** PLEASE ENTER YOUR CHOICE: ****");

            number = sc.nextInt();

            switch (number) {

                case 1:
                System.out.println("ENTER PRODUCTS DETAILS: ID, NAME, COST");

                String sql = "INSERT INTO products" + "(prdId, prdName, prdCost)" + "VALUES (?,?,?)";
                try{
                    Class.forName("oracle.jdbc.driver.OracleDriver"); 
                    int id = sc.nextInt();
                    String name = sc.next();
                    double cost = sc.nextDouble();
                    con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:abcshop","root","");
                        pst = con.prepareStatement(sql);
                        pst.setInt(1, id);
                        pst.setString(2, name);
                        pst.setDouble(3, cost);
                        pst.executeUpdate();
                        System.out.println("PRODUCT INSERTED!");

                    } catch(Exception e){ 
                        System.out.println(e);
                    }
                    break;

                case 2:
                System.out.println("ENTER PRODUCTS DETAILS: ID, NAME, COST");

                String Sql = "DELETE FROM `products` WHERE `products`.`prdId` = ?";
                try{
                    Class.forName("oracle.jdbc.driver.OracleDriver");
                    int id = sc.nextInt();
                    con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:abcshop","root","");
                        pst = con.prepareStatement(Sql);
                        pst.setInt(1, id);
                        pst.execute();
                        System.out.println("PRODUCT DELETED!");

                    } catch (Exception ex){
                        System.out.println(ex);
                    }
                    break;
                    
                default: System.out.println("!! ENTER PROPER CHOICE !!");
                    break;
            }

            System.out.println("**** Do you want to Continue YES/NO: ****");
            Answer = sc.next();
        } while (Answer.equals("Yes"));
    }
}
