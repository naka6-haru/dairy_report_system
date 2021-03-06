package controllers.feedback;

import java.io.IOException;

import javax.persistence.EntityManager;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.Feedback;
import models.Report;
import utils.DBUtil;

/**
 * Servlet implementation class FeedbackDestroyServlet
 */
@WebServlet("/feedback/destroy")
public class FeedbackDestroyServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public FeedbackDestroyServlet() {
        super();
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        EntityManager em = DBUtil.createEntityManager();

        Feedback f = em.find(Feedback.class, Integer.parseInt(request.getParameter("id")));

        Report r = f.getReport_id();

        em.getTransaction().begin();
        em.remove(f);
        em.getTransaction().commit();
        em.close();

        response.sendRedirect(request.getContextPath() + "/feedback/index?id=" + r.getId());
    }

}
