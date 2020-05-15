package ru.barancev.addressbook.tests;

import org.testng.annotations.Test;
import ru.barancev.addressbook.model.ContactData;
import ru.barancev.addressbook.model.Contacts;
import ru.barancev.addressbook.model.GroupData;
import ru.barancev.addressbook.model.Groups;

import java.sql.*;

public class DbConnectionTest {

    @Test
    public void testDbConnectionGroups(){
        Connection conn = null;
        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/addressbook?user=root&password=");
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery("select group_id,group_name,group_header," +
                    "group_footer from group_list");
            Groups groups = new Groups();
            while(rs.next()){
                GroupData gD = new GroupData().withId(rs.getInt("group_id"))
                        .withName(rs.getString("group_name"))
                        .withHeader(rs.getString("group_header"))
                        .withFooter(rs.getString("group_footer"));
                groups.add(gD);
            }
            rs.close();
            st.close();
            conn.close();

            System.out.println(groups);

            // Do something with the Connection

        } catch (SQLException ex) {
            // handle any errors
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }
    }

    @Test
    public void testDbConnectionContacts(){
        Connection conn = null;
        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/addressbook?user=root&password=");
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery("select id,firstname,middlename,lastname,nickname,company," +
                    "title,address,home,mobile,work,email,email2,email3 from addressbook");
            Contacts contacts = new Contacts();
            while(rs.next()){
                ContactData cD = new ContactData().withId(rs.getInt("id"))
                        .withFirstname(rs.getString("firstname"))
                        .withMiddlename(rs.getString("middlename"))
                        .withLastname(rs.getString("lastname"))
                        .withNickname(rs.getString("nickname"))
                        .withCompany(rs.getString("company"))
                        .withTitle(rs.getString("title"))
                        .withAddress(rs.getString("address"))
                        .withHomePhone(rs.getString("home"))
                        .withMobilePhone(rs.getString("mobile"))
                        .withWorkPhone(rs.getString("work"))
                        .withEmail(rs.getString("email"))
                        .withEmail2(rs.getString("email2"))
                        .withEmail3(rs.getString("email3"));
                contacts.add(cD);
            }
            rs.close();
            st.close();
            conn.close();

            System.out.println(contacts);

            // Do something with the Connection

        } catch (SQLException ex) {
            // handle any errors
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }
    }
}
