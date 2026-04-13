package com.learninglog.learninglogproject.topic.controller;

import com.learninglog.learninglogproject.topic.model.dao.TopicDao;
import com.learninglog.learninglogproject.topic.model.Topic;
import com.learninglog.learninglogproject.user.model.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.List;

@WebServlet("/topic")
public class TopicServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        String page = req.getParameter("page");

        if ("list".equals(page)) {
            try {
                List<Topic> topicList = TopicDao.fetchTopics();
                req.setAttribute("topics", topicList);
            } catch (Exception e) {
                req.setAttribute("error", "Unable to fetch topic list");
            }

            req.getRequestDispatcher("pages/topicList.jsp").forward(req, resp);
            return;
        }

        if ("edit".equals(page)) {
            Integer id = parseInteger(req.getParameter("id"));
            if (id == null || id <= 0) {
                req.setAttribute("error", "Invalid topic id");
                req.getRequestDispatcher("pages/topicList.jsp").forward(req, resp);
                return;
            }

            try {
                TopicDao dao = new TopicDao();
                Topic obj = dao.fetchTopicById(id);

                if (obj != null) {
                    req.setAttribute("topic", obj);
                    req.getRequestDispatcher("pages/edit-topic.jsp").forward(req, resp);
                    return;
                } else {
                    req.setAttribute("error", "Topic not found");
                }

            } catch (Exception e) {
                req.setAttribute("error", e.getMessage());
            }
        }

        req.getRequestDispatcher("pages/addTopic.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if ("add".equals(action)) {
            handleAdd(req, resp);
            return;
        }

        if ("edit".equals(action)) {
            handleEdit(req, resp);
            return;
        }

        req.setAttribute("error", "Invalid action");
        req.getRequestDispatcher("pages/addTopic.jsp").forward(req, resp);
    }

    private void handleAdd(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String topicName = req.getParameter("topic-name");
        if (topicName == null || topicName.trim().isEmpty()) {
            req.setAttribute("error", "Topic name is required");
            req.getRequestDispatcher("pages/addTopic.jsp").forward(req, resp);
            return;
        }

        Integer userId = getUserId(req);
        if (userId == null || userId <= 0) {
            req.setAttribute("error", "Invalid user. Please login again.");
            req.getRequestDispatcher("pages/addTopic.jsp").forward(req, resp);
            return;
        }

        Timestamp createdDate = new Timestamp(System.currentTimeMillis());
        try {
            TopicDao dao = new TopicDao();
            boolean result = dao.insertTopic(topicName.trim(), userId, createdDate);
            if (result) {
                resp.sendRedirect("topic?page=list");
                return;
            }

            req.setAttribute("error", "Unable to add topic");
            req.getRequestDispatcher("pages/addTopic.jsp").forward(req, resp);
        } catch (Exception e) {
            req.setAttribute("error", e.getMessage());
            req.getRequestDispatcher("pages/addTopic.jsp").forward(req, resp);
        }
    }

    private void handleEdit(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Integer id = parseInteger(req.getParameter("id"));
        String topicName = req.getParameter("topic-name");

        if (id == null || id <= 0) {
            resp.sendRedirect("topic?page=list");
            return;
        }

        if (topicName == null || topicName.trim().isEmpty()) {
            req.setAttribute("error", "Topic name is required");
            try {
                TopicDao dao = new TopicDao();
                Topic topic = dao.fetchTopicById(id);
                req.setAttribute("topic", topic);
            } catch (Exception ignored) {
                // No-op: we still forward with validation error
            }
            req.getRequestDispatcher("pages/edit-topic.jsp").forward(req, resp);
            return;
        }

        try {
            TopicDao dao = new TopicDao();
            boolean result = dao.updateTopic(id, topicName.trim());
            if (result) {
                resp.sendRedirect("topic?page=list");
                return;
            }

            req.setAttribute("error", "Unable to update topic");
            Topic topic = dao.fetchTopicById(id);
            req.setAttribute("topic", topic);
            req.getRequestDispatcher("pages/edit-topic.jsp").forward(req, resp);
        } catch (Exception e) {
            req.setAttribute("error", e.getMessage());
            req.getRequestDispatcher("pages/edit-topic.jsp").forward(req, resp);
        }
    }

    private Integer getUserId(HttpServletRequest req) {
        HttpSession session = req.getSession(false);
        if (session != null) {
            Object userObj = session.getAttribute("user");
            if (userObj instanceof User) {
                return ((User) userObj).getId();
            }
        }
        return parseInteger(req.getParameter("user-id"));
    }

    private Integer parseInteger(String value) {
        try {
            return Integer.parseInt(value);
        } catch (Exception e) {
            return null;
        }
    }
}