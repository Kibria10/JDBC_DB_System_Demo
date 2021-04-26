import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

/**
 *
 * @author Maharab Kibria
 */
public class JDBC {
    /**
     * @param args the command line arguments
     * @throws java.lang.Exception
     */
    public static void main(String[] args) throws Exception {
//        Class.forName("com.mysql.jdbc.Driver");
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost/cse_310_demo_project?", "root", "");
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery("SELECT ID, FirstName, Email FROM user_information");
        while (rs.next()) {
            System.out.println(rs.getInt(1) + "  " + rs.getString(2) + "  " + rs.getString(3));
        }

        Scanner scn = new Scanner(System.in);
        System.out.println("\nPress \n1 for registration \n2 for Login \n3 for Edit \n4 for Delete");
        int choise = scn.nextInt();
        PreparedStatement ps;
        if (choise == 1) {
            scn.nextLine();
            System.out.println("Please Enter Your First Name");
            String fName = scn.nextLine();
            System.out.println("Please Enter Your Last Name");
            String lName = scn.nextLine();
            System.out.println("Please Enter Your Phone No");
            String pno = scn.nextLine();
            System.out.println("Please Enter Your Address");
            String add = scn.nextLine();
            System.out.println("Please Enter Your Email");
            String emailAdd = scn.nextLine();
            System.out.println("Please Enter Your Password");
            String pwd = scn.nextLine();
            System.out.println("Please Enter Your Gender");
            String gender = scn.nextLine();
            System.out.println("Please Enter Your Date of Birth");
            String dob = scn.nextLine();

            ps = con.prepareStatement("SELECT email FROM user_information WHERE email = ?");
            ps.setString(1, emailAdd);
            rs = ps.executeQuery();

            if (rs.next() == true) {
                System.out.println("Your Email Address Already Exists");
            } else {
                ps = con.prepareStatement("INSERT INTO user_information (FirstName,LastName,PhoneNo,Address,Email,Password,Gender,DOB) VALUES (?,?,?,?,?,?,?,?)");
                ps.setString(1, fName);
                ps.setString(2, lName);
                ps.setString(3, pno);
                ps.setString(4, add);
                ps.setString(5, emailAdd);
                ps.setString(6, pwd);
                ps.setString(7, gender);
                ps.setString(8, dob);
                ps.executeUpdate();
            }
        } else if (choise == 2) {
            System.out.println("Please Enter Email");
            String userName = scn.next();
            System.out.println("Please Enter Password");
            String pass = scn.next();

            ps = con.prepareStatement("SELECT email, password FROM user_information WHERE email = ? AND password = ?");

            ps.setString(1, userName);
            ps.setString(2, pass);
            rs = ps.executeQuery();
            //boolean loginCredential = false;
            if (rs.next() == true) {
                //loginCredential = true;
                ps = con.prepareStatement("SELECT * FROM user_information WHERE email = ? AND password = ?");
                ps.setString(1, userName);
                ps.setString(2, pass);
                rs = ps.executeQuery();
                rs.next();

                String firstName = rs.getString(2);
                String lastName = rs.getString(3);
                String email = rs.getString(6);
                String userID = rs.getString(1);
                String phoneNo = rs.getString(4);
                String address = rs.getString(5);
                String dateOfBirth = rs.getString(9);

                System.out.println("");
                System.out.println("Hello " + firstName + " " + lastName);
                System.out.println("Email         : " + email);
                System.out.println("User ID       : " + userID);
                System.out.println("Phone No      : 0" + phoneNo);
                System.out.println("Address       : " + address);
                System.out.println("Date of Birth : " + dateOfBirth);
            } else {
                System.out.println("Username or Password doesn't match");
            }
        } else if (choise == 3) {

            System.out.println("Please Enter Email");
            String userName = scn.next();
            System.out.println("Please Enter Password");
            String pass = scn.next();

            ps = con.prepareStatement("SELECT email, password FROM user_information WHERE email = ? AND password = ?");

            ps.setString(1, userName);
            ps.setString(2, pass);
            rs = ps.executeQuery();
            if (rs.next() == true) {

                ps = con.prepareStatement("SELECT * FROM user_information WHERE email = ? AND password = ?");
                ps.setString(1, userName);
                ps.setString(2, pass);
                rs = ps.executeQuery();
                rs.next();

                String firstName = rs.getString(2);
                String lastName = rs.getString(3);


                System.out.println("Hello Mr." + firstName + " " + lastName + ". " + "Here you can change your personal information?");


//                Statement stmt;
//                PreparedStatement pss;
//                stmt = con.createStatement();
                System.out.println(userName);
                System.out.println(pass);

                scn.nextLine();
                System.out.println("Please Enter Your New First Name:");
                String fname = scn.nextLine();
                System.out.println("Please Enter Your New Last Name:");
                String lname = scn.nextLine();
                System.out.println("New PhoneNo:");
                String phn = scn.nextLine();
                System.out.println("New Address:");
                String add = scn.nextLine();
                System.out.println("New Gender:");
                String gen = scn.nextLine();
                System.out.println("Change. If your previous DOB date was wrong:");
                String dob = scn.nextLine();

                ps = con.prepareStatement("UPDATE user_information SET FirstName = ?, LastName = ?, PhoneNo =?, Address =?,Gender =? ,DOB=? WHERE Email= ? AND Password = ?");
                ps.setString(1, fname);
                ps.setString(2, lname);
                ps.setString(3, phn);
                ps.setString(4, add);
                ps.setString(5, gen);
                ps.setString(6, dob);
                ps.setString(7, userName);
                ps.setString(8, pass);
                ps.executeUpdate();

                st.close();
                con.close();
                System.out.println(userName);
                System.out.println(pass);
            } else {
                System.out.println("Username or Password doesn't match");
            }
        }
        else if (choise == 4) {


            System.out.println(" \n Press 1 if you want to continue on deleting your account.\n Press 2 to leave the procedure.");

            int stay = scn.nextInt();
            if (stay == 1) {

                System.out.println("Please enter your email address and password to delete your account and informations");

                System.out.println("Please Enter Email");
                String userName = scn.next();
                System.out.println("Please Enter Password");
                String pass = scn.next();

                ps = con.prepareStatement("SELECT email, password FROM user_information WHERE email = ? AND password = ?");

                ps.setString(1, userName);
                ps.setString(2, pass);
                rs = ps.executeQuery();
                if (rs.next()==true) {

                    ps = con.prepareStatement("SELECT * FROM user_information WHERE email = ? AND password = ?");
                    ps.setString(1, userName);
                    ps.setString(2, pass);
                    rs = ps.executeQuery();
                    rs.next();

                    String firstName = rs.getString(2);
                    String lastName = rs.getString(3);


                    ps = con.prepareStatement("DELETE FROM `user_information` WHERE Email = 'userName' AND Password = 'pass'");;
                    ps.executeUpdate();

                    System.out.println("Hello " + firstName + " " + lastName + " " + "Your account is now being deleted. Goodbye! Adios.");
                } else {
                    System.out.println("Username or Password doesn't match");
                }
            } else if (stay == 2) {
                System.out.println("Thanks for staying with us and not deleting your account");
            }


            st.close();
            con.close();
        }
    }
}
