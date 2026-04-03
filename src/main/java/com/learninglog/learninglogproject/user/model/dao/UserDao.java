package com.learninglog.learninglogproject.user.model.dao;

import com.learninglog.learninglogproject.user.model.User;
import com.learninglog.learninglogproject.utils.DbConnection;
import org.mindrot.jbcrypt.BCrypt;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDao {

    public boolean insertUser(User user) throws SQLException {
        String query = "INSERT INTO user(name, email, password)"+
                "VALUES(?,?,?)";
        try(Connection conn = DbConnection.getConnection();
            PreparedStatement st = conn.prepareStatement(query)
        ){
            st.setString(1,user.getName());
            st.setString(2,user.getEmail());
            st.setString(3,user.getPassword());

            int insertedRows = st.executeUpdate();
            if(insertedRows>0){
                return true;
            }else{
                return false;
            }

        }
    }

    public User loginUser(String email, String password)
            throws  SQLException{
        String sqlQuery="SELECT * FROM user WHERE email=?";
        try(Connection conn = DbConnection.getConnection();
            PreparedStatement st = conn.prepareStatement(sqlQuery)){
            st.setString(1, email);
            // execute the query
            ResultSet rs = st.executeQuery();
            if(rs.next()){
                String hashedPwDb = rs.getString("password");
                boolean isValidPw= BCrypt.checkpw(password, hashedPwDb);
                if(isValidPw){
                    int id = rs.getInt("id");
                    String name= rs.getString("name");
                    User obj = new User(id, name, email, password);
                    return  obj;
                }
                else{
                    return null;
                }
            }
            else{
                return null;
            }
        }
    }
}