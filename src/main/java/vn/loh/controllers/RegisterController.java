package vn.loh.controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import vn.loh.services.IUserService;
import vn.loh.services.impl.UserServiceImpl;

import java.io.IOException;

@WebServlet(urlPatterns = {"/register", "/dang-ky"})
public class RegisterController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static final String REGISTER = "/views/register.jsp";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(false);
        if (session != null && session.getAttribute("username") != null) {
            resp.sendRedirect(req.getContextPath() + "/admin");
            return;
        }

        // Check cookie
        Cookie[] cookies = req.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("username")) {
                    session = req.getSession(true);
                    session.setAttribute("username", cookie.getValue());
                    resp.sendRedirect(req.getContextPath() + "/admin");
                    return;
                }
            }
        }
        req.getRequestDispatcher(REGISTER).forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setCharacterEncoding("UTF-8");
        req.setCharacterEncoding("UTF-8");

        // Get data from form
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String password_repeat = req.getParameter("password-repeat");
        String email = req.getParameter("email");
        String fullname = req.getParameter("fullname");
        String phone = req.getParameter("phone");
        String image = req.getParameter("image");

        IUserService userService = new UserServiceImpl();
        String alertMsg = "";

        // Check exist email and username and phone
        if (userService.checkExistEmail(email)) {
            alertMsg = "Email đã tồn tại!";
            req.setAttribute("alertMsg", alertMsg);
            req.getRequestDispatcher(REGISTER).forward(req, resp);
            return;
        }
        if (userService.checkExistUsername(username)) {
            alertMsg = "Username đã tồn tại!";
            req.setAttribute("alertMsg", alertMsg);
            req.getRequestDispatcher(REGISTER).forward(req, resp);
            return;
        }
        if (userService.checkExistPhone(phone)) {
            alertMsg = "Số điện thoại đã tồn tại!";
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

        boolean isSuccess = userService.register(username, password, email, fullname, image, phone);
        if (isSuccess) {
            alertMsg = "Đăng ký thành công!";
            req.setAttribute("alertMsg", alertMsg);
            resp.sendRedirect(req.getContextPath() + "/login");
        } else {
            alertMsg = "Đăng ký thất bại!";
            req.setAttribute("alertMsg", alertMsg);
            req.getRequestDispatcher(REGISTER).forward(req, resp);
        }
    }
}
