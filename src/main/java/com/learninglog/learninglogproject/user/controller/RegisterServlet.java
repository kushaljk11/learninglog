package com.learninglog.learninglogproject.user.controller;

// Import necessary servlet classes
import com.learninglog.learninglogproject.user.model.User;
import com.learninglog.learninglogproject.user.model.dao.UserDao;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.mindrot.jbcrypt.BCrypt;

import java.io.IOException;

// Map this servlet to the URL pattern "/register"
@WebServlet("/register")
public class RegisterServlet extends HttpServlet {

    // This method handles GET requests (when user visits /register in browser)
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        // Create a RequestDispatcher to forward request to register.jsp page
        RequestDispatcher rd = req.getRequestDispatcher("pages/register.jsp");

        // Forward the request and response to the JSP page
        // This means the URL stays the same but content is loaded from JSP
        rd.forward(req, resp);
    }

    @Override
    protected  void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        String name = req.getParameter("name");
        String  email = req.getParameter("email");
        String password = req.getParameter("password");

        if(name.isEmpty() || email.isEmpty() || password.isEmpty()){
            req.setAttribute("error","Please fill the all fields");
            req.getRequestDispatcher("pages/register.jsp").forward(req,resp);
        }

        User userObj = new User();
        userObj.setName(name);
        userObj.setEmail(email);
        userObj.setPassword(password);
        String hashedPassword = BCrypt.hashpw(password, BCrypt.gensalt());
        userObj.setPassword(hashedPassword);

        UserDao dao = new UserDao();

        try{
            boolean result = dao.insertUser(userObj);
            if(result == true){
                resp.sendRedirect("login");
            }else{
                req.setAttribute("error","Unable to register a user");
                req.getRequestDispatcher("pages/register.jsp").forward(req, resp);
            }
        }catch (Exception e){
            req.setAttribute("error", e.getMessage());
            req.getRequestDispatcher("pages/register.jsp").forward(req, resp);

        }
    }

}