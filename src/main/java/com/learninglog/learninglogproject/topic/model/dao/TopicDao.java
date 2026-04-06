package com.learninglog.learninglogproject.topic.model.dao;

import com.learninglog.learninglogproject.topic.model.Topic;
import com.learninglog.learninglogproject.utils.DbConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TopicDao {
    public boolean insertTopic(
            String name, int userId, Timestamp createdDate
    )throws SQLException {
        // Note: adjust column names to match your DB. This assumes table `topic` has columns:
        // `name`, `user_id`, `createdDate` (use createdDate if that's the column in your schema).
        String query = "INSERT INTO topic (name, user_id, createdDate) VALUES (?,?,?)";
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
    public static List<Topic> fetchTopics()throws SQLException {
        String query = "SELECT * FROM topic";
        try (Connection conn = DbConnection.getConnection();
             PreparedStatement st = conn.prepareStatement(query)){
            ResultSet rs = st.executeQuery();
            List<Topic> topicList = new ArrayList<>();
            while (rs.next()){
                int id = rs.getInt(1);
                String name = rs.getString(2);
                int userId = 3;
                Timestamp createdDate = rs.getTimestamp(4);
                Topic obj = new Topic(id, name, userId, createdDate);
                topicList.add(obj);
            }
            return topicList;
        }
    }
}
