package com.learninglog.learninglogproject.topic.controller;

import com.learninglog.learninglogproject.topic.model.dao.TopicDao;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.Timestamp;

@WebServlet("/topic")
public class TopicServlet extends HttpServlet {
    @Override
    protected  void doGet(HttpServletRequest req, HttpServletResponse resp)
        throws ServletException, IOException {
        req.getRequestDispatcher("pages/addTopic.jsp").forward(req,resp);
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        super.doPost(req, resp);
        String action = req.getParameter("action");
        if(action.equals("addTopic")){
            String topicName = req.getParameter("topicName");
            int userId = Integer.parseInt(req.getParameter("userId"));
            Timestamp createdDate = Timestamp.valueOf(req.getParameter("createdDate"));

            try {
                TopicDao dao = new TopicDao();
                boolean result = dao.insertTopic(topicName, userId, createdDate);
                if(result){}
                else {}
            } catch (Exception e) {

            }
        }
    }
}
