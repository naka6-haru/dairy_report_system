package controllers.feedback;

import java.io.IOException;
import java.util.List;

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
 * Servlet implementation class FeedbackIndexServlet
 */
@WebServlet("/feedback/index")
public class FeedbackIndexServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public FeedbackIndexServlet() {
        super();
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        EntityManager em = DBUtil.createEntityManager();


        int page;
        try {
            page = Integer.parseInt(request.getParameter("page"));
        } catch (Exception e) {
            page = 1;
        }

        Report r = em.find(Report.class, Integer.parseInt(request.getParameter("id")));

        if(null == r){
            em.close();
            RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/feedback/index.jsp");
            rd.forward(request, response);

        }else{
            List<Feedback> feedback = em.createNamedQuery("getMyAllFeedback",Feedback.class)
                                        .setParameter("report_id",r)
                                        .setFirstResult(10 * (page - 1))
                                        .setMaxResults(10)
                                        .getResultList();

            long feedback_count = (long)em.createNamedQuery("getMyFeedbackCount",Long.class)
                                           .setParameter("report_id",r)
                                           .getSingleResult();

            em.close();

            request.setAttribute("report", r);
            request.setAttribute("feedback", feedback);
            request.setAttribute("feedback_count", feedback_count);
            request.setAttribute("page", page);
            if(request.getSession().getAttribute("flush") != null){
                request.setAttribute("flush", request.getSession().getAttribute("flush"));
                request.getSession().removeAttribute("flush");
            }

            RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/feedback/index.jsp");
            rd.forward(request, response);
        }
    }

}
