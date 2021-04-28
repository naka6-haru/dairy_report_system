package controllers.feedback;

import java.io.IOException;

import javax.persistence.EntityManager;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.Feedback;
import models.Report;
import utils.DBUtil;

/**
 * Servlet implementation class FeedbackNewServlet
 */
@WebServlet("/feedback/new")
public class FeedbackNewServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public FeedbackNewServlet() {
        super();
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("_token", request.getSession().getId());

        EntityManager em = DBUtil.createEntityManager();
        Report r = em.find(Report.class, Integer.parseInt(request.getParameter("id")));
        em.close();

        Feedback f = new Feedback();

        request.setAttribute("feedback", f);
        request.setAttribute("report", r);

        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/feedback/new.jsp");
        rd.forward(request, response);
    }

}
