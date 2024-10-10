package vn.loh.controllers.admin;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import vn.loh.entity.Video;
import vn.loh.services.IVideoService;
import vn.loh.services.impl.CategoryServiceImpl;
import vn.loh.services.impl.VideoServiceImpl;

import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = {"/admin/category/videos", "/admin/category/video/add", "/admin/category/video/edit",
        "/admin/category/video/update", "/admin/category/video/delete", "/admin/category/video/search"})
public class VideosController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    public IVideoService videoService = new VideoServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String url = req.getRequestURI();

        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");

        if (url.contains("/admin/category/videos")) {
            // Get Category ID from URL
            int categoryId = Integer.parseInt(req.getParameter("categoryId"));
            // Set Category ID to form
            req.setAttribute("categoryId", categoryId);
            // Show list categories
            showVideos(req, resp);
        } else if (url.contains("/admin/category/video/add")) {
            // Get Category ID from URL
            int categoryId = Integer.parseInt(req.getParameter("categoryId"));
            // Set Category ID to form
            req.setAttribute("categoryId", categoryId);
            // Show form add category
            req.getRequestDispatcher("/views/admin/category/videos/video-add.jsp").forward(req, resp);
        } else if (url.contains("/admin/category/video/edit")) {
            // Get id from url
            String id = req.getParameter("id");
            // Get data from database
            Video video = videoService.findById(id);
            // Set data to form
            req.setAttribute("video", video);
            // Show form edit category
            req.getRequestDispatcher("/views/admin/category/videos/video-edit.jsp").forward(req, resp);
        } else if (url.contains("/admin/category/video/delete")) {
            // Get id from url
            String id = req.getParameter("id");
            // Get Category ID from URL
            int categoryId = Integer.parseInt(req.getParameter("categoryId"));
            // Delete data from database
            try {
                videoService.delete(id);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
            // Show list categories
            resp.sendRedirect(req.getContextPath() + "/admin/category/videos?categoryId=" + req.getParameter("categoryId"));
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String url = req.getRequestURI();
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");

        if (url.contains("video/add")) {
            addVideo(req, resp);
        } else if (url.contains("video/edit")) {
            editVideo(req, resp);
        } else if (url.contains("video/delete")) {
            try {
                deleteVideo(req, resp);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }

    private void addVideo(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Get data from form
        String id = req.getParameter("id");
        int active = Integer.parseInt(req.getParameter("active"));
        String description = req.getParameter("description");
        String poster = req.getParameter("poster");
        String title = req.getParameter("title");
        int views = Integer.parseInt(req.getParameter("views"));
        int categoryId = Integer.parseInt(req.getParameter("categoryId"));
        // Create new object
        Video video = new Video();
        video.setId(id);
        video.setActive(active);
        video.setDescription(description);
        video.setPoster(poster);
        video.setTitle(title);
        video.setViews(views);
        video.setCategory(new CategoryServiceImpl().findById(categoryId));
        // Save to database
        videoService.insert(video);
        // Redirect to list categories
        resp.sendRedirect(req.getContextPath() + "/admin/category/videos?categoryId=" + categoryId);
    }

    private void editVideo(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Get data from form
        String id = req.getParameter("id");
        int active = Integer.parseInt(req.getParameter("active"));
        String description = req.getParameter("description");
        String poster = req.getParameter("poster");
        String title = req.getParameter("title");
        int views = Integer.parseInt(req.getParameter("views"));
        int categoryId = Integer.parseInt(req.getParameter("categoryId"));
        // Create new object
        Video video = videoService.findById(id);
        video.setActive(active);
        video.setDescription(description);
        video.setPoster(poster);
        video.setTitle(title);
        video.setViews(views);
        // Save to database
        videoService.update(video);
        // Redirect to list categories
        resp.sendRedirect(req.getContextPath() + "/admin/category/videos?categoryId=" + categoryId);
    }

    private void deleteVideo(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        // Get id from url
        String id = req.getParameter("id");
        // Delete data from database
        videoService.delete(id);
        // Show list categories
        resp.sendRedirect(req.getContextPath() + "/admin/category/videos");
    }

    private void showVideos(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Get Category ID from URL
        int categoryId = Integer.parseInt(req.getParameter("categoryId"));
        List<Video> videos = videoService.findByCategoryID(categoryId);
        req.setAttribute("videos", videos);
        req.getRequestDispatcher("/views/admin/category/videos/videos.jsp").forward(req, resp);
    }
}
