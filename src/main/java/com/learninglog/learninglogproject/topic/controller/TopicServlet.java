package com.learninglog.learninglogproject.topic.controller;

import com.learninglog.learninglogproject.topic.model.dao.TopicDao;
import com.learninglog.learninglogproject.topic.model.Topic;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.List;

@WebServlet("/topic")
public class TopicServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String page = req.getParameter("page"); //page=list
        if("list".equals(page)){
            // fetch data from TopicDao and send to topicList.jsp
            try {
                List<Topic> topicList = TopicDao.fetchTopics();
                req.setAttribute("topics", topicList);

            } catch (Exception e) {
                // on error, leave topics empty and continue
                req.setAttribute("error", "unable to fetch topic list");
            }
            req.getRequestDispatcher("pages/topicList.jsp").forward(req, resp);
            return;
        }

        req.getRequestDispatcher("pages/addTopic.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        super.doPost(req, resp);
        String action = req.getParameter("action");
        if (action.equals("add")) {

            String topicName = req.getParameter("topic-name");
            int userId = Integer.parseInt(req.getParameter("user-id"));
            System.out.println(req.getParameter("user-id"));
            Timestamp createdDate = new Timestamp(System.currentTimeMillis());

            try {
                TopicDao dao = new TopicDao();
                boolean result = dao.insertTopic(topicName, userId, createdDate);
                if (result) {
                    //success
                } else {
                    //failed
                }
            } catch (Exception e) {
                // failed
            }

        } else if (action.equals("edit")) {

        }
    }
}