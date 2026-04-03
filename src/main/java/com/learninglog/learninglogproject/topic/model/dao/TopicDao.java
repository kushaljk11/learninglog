package com.learninglog.learninglogproject.topic.model.dao;

import com.learninglog.learninglogproject.utils.DbConnection;

import java.sql.Timestamp;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class TopicDao {
    public boolean insertTopic(
            String name, int userId, Timestamp createdDate
    )throws SQLException {
        String query="INSERT INTO topic(name, user_id, createdat)"+
                "VALUES (?,?,?)";
        try(Connection conn = DbConnection.getConnection();
            PreparedStatement st = conn.prepareStatement(query)){
            st.setString(1, name);
            st.setInt(2, userId);
            st.setTimestamp(3, createdDate);
            int insertedRows = st.executeUpdate();
            if(insertedRows > 0){return  true;}
            else {return  false;}
        }
    }
}
