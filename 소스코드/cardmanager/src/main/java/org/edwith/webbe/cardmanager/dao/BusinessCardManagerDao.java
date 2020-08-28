package org.edwith.webbe.cardmanager.dao;

import com.mysql.cj.protocol.Resultset;
import org.edwith.webbe.cardmanager.dto.BusinessCard;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

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

    public BusinessCard addBusinessCard(BusinessCard businessCard){
    // mysql workbench 설치
        return null;
    }
}
