package controllers.feedback;

import java.io.IOException;

import javax.persistence.EntityManager;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.Employee;
import models.Feedback;
import utils.DBUtil;

/**
 * Servlet implementation class FeedbackEditServlet
 */
@WebServlet("/feedback/edit")
public class FeedbackEditServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public FeedbackEditServlet() {
        super();
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        EntityManager em = DBUtil.createEntityManager();

        Feedback f = em.find(Feedback.class, Integer.parseInt(request.getParameter("id")));

        em.close();

        Employee login_employee = (Employee)request.getSession().getAttribute("login_employee");
        if(f != null && login_employee.getId() == f.getComment_employee().getId()){
            request.setAttribute("feedback", f);
            request.setAttribute("_token", request.getSession().getId());
            request.setAttribute("report", f.getReport_id());
        }

        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/feedback/edit.jsp");
        rd.forward(request, response);
    }
}
