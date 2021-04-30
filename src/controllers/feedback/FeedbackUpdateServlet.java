package controllers.feedback;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.List;

import javax.persistence.EntityManager;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.Employee;
import models.Feedback;
import models.validators.FeedbackValidator;
import utils.DBUtil;

/**
 * Servlet implementation class FeedbackUpdateServlet
 */
@WebServlet("/feedback/update")
public class FeedbackUpdateServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public FeedbackUpdateServlet() {
        super();
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String _token = request.getParameter("_token");
        if(_token != null && _token.equals(request.getSession().getId())){
            EntityManager em = DBUtil.createEntityManager();

            Feedback f = em.find(Feedback.class, (Integer)(request.getSession().getAttribute("feedback_id")));

            f.setComment_employee((Employee)request.getSession().getAttribute("login_employee"));
            f.setReport_id(f.getReport_id());
            f.setContent(request.getParameter("content"));
            f.setUpdated_at(new Timestamp(System.currentTimeMillis()));

            List<String> errors = FeedbackValidator.validate(f);
            if(errors.size() > 0){
                em.close();

                request.setAttribute("_token", request.getSession().getId());
                request.setAttribute("feedback", f);
                request.setAttribute("errors", errors);

                RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/views/feedback/edit.jsp");
                rd.forward(request, response);
            }else{
                em.getTransaction().begin();
                em.getTransaction().commit();
                em.close();
                request.getSession().setAttribute("flush", "更新が完了しました。");

                request.getSession().removeAttribute("feedback_id");

                response.sendRedirect(request.getContextPath() + "/feedback/index?id=" + f.getReport_id().getId());
            }
        }
    }

}
