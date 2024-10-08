package vn.loh.controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import vn.loh.services.IUserService;
import vn.loh.services.impl.UserServiceImpl;

import java.io.IOException;

@WebServlet(urlPatterns = {"/forgot-password"})
public class ForgotPasswordController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static final String REGISTER = "/views/forgotPassword.jsp";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("views/forgotPassword.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setCharacterEncoding("UTF-8");
        req.setCharacterEncoding("UTF-8");

        // Get data from form
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String password_repeat = req.getParameter("password-repeat");

        IUserService userService = new UserServiceImpl();
        String alertMsg = "";

        // Check username
        if (!userService.checkExistUsername(username)) {
            alertMsg = "Username does not exist!";
            req.setAttribute("alertMsg", alertMsg);
            req.getRequestDispatcher(REGISTER).forward(req, resp);
            return;
        }
        // Check password
        if (!password.equals(password_repeat)) {
            alertMsg = "Mật khẩu không khớp!";
            req.setAttribute("alertMsg", alertMsg);
            req.getRequestDispatcher(REGISTER).forward(req, resp);
            return;
        }

        boolean isSuccess = userService.updatePassword(username, password);
        if (isSuccess) {
            alertMsg = "Change Password thành công!";
            req.setAttribute("alertMsg", alertMsg);
            resp.sendRedirect(req.getContextPath() + "/login");
        } else {
            alertMsg = "Change Password thất bại!";
            req.setAttribute("alertMsg", alertMsg);
            req.getRequestDispatcher(REGISTER).forward(req, resp);
        }
    }
}
