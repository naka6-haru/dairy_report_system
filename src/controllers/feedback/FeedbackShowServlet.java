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
import utils.DBUtil;

/**
 * Servlet implementation class FeedbackShowServlet
 */
@WebServlet("/feedback/show")
public class FeedbackShowServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public FeedbackShowServlet() {
        super();
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        EntityManager em = DBUtil.createEntityManager();

        Feedback f = em.find(Feedback.class, Integer.parseInt(request.getParameter("id")));

        em.close();

        if(null == f){
            RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/feedback/show.jsp");
            rd.forward(request, response);
        }else{
            request.setAttribute("feedback", f);
            request.setAttribute("report",f.getReport_id());
            request.getSession().setAttribute("feedback_id", f.getId());
            request.setAttribute("_token", request.getSession().getId());

            RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/feedback/show.jsp");
            rd.forward(request, response);
        }
    }

}
