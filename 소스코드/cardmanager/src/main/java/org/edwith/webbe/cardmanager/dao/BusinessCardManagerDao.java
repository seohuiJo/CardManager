package org.edwith.webbe.cardmanager.dao;

import com.mysql.cj.protocol.Resultset;
import org.edwith.webbe.cardmanager.dto.BusinessCard;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Date;


public class BusinessCardManagerDao {
    private static String dburl="jdbc:mysql://localhost:3306/CardManager";
    private static String dbUser="root";
    private static String dbpasswd="csb7256!";
    public List<BusinessCard> searchBusinessCard(String keyword) {
        List<BusinessCard> cards=new ArrayList<>();
        Connection conn=null;
        PreparedStatement ps=null;
        ResultSet rs=null;

        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn= DriverManager.getConnection(dburl, dbUser, dbpasswd);
            String sql="SELECT * FROM BUSINESS_CARD WHERE NAME LIKE %?%";
            ps=conn.prepareStatement(sql);
            ps.setString(1, keyword);
            rs=ps.executeQuery();

            while(rs.next()){
                BusinessCard card=null;
                String name=rs.getString("NAME");
                String phone=rs.getString("PHONE");
                String companyName=rs.getString("COMPANY_NAME");
                Date createDate=rs.getDate("CREATE_DATE");
                card=new BusinessCard(name, phone, companyName, createDate);
                cards.add(card);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return cards;
    }

    public int addBusinessCard(BusinessCard businessCard){
        int insertCount=0;

        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        String sql="INSERT INTO BUSINESS_CARD VALUES(?,?,?,?)";

        try {
            Connection conn=DriverManager.getConnection(dburl, dbUser, dbpasswd);
            PreparedStatement ps=conn.prepareStatement(sql);

            ps.setString(1, businessCard.getName());
            ps.setString(2, businessCard.getPhone());
            ps.setString(3, businessCard.getCompanyName());
            ps.setDate(4, (java.sql.Date) businessCard.getCreateDate());

            insertCount=ps.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return insertCount;
    }
}
