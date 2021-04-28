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
import models.Report;
import models.validators.FeedbackValidator;
import utils.DBUtil;

/**
 * Servlet implementation class FeedbackCreateServlet
 */
@WebServlet("/feedback/create")
public class FeedbackCreateServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public FeedbackCreateServlet() {
        super();
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String _token = request.getParameter("_token");
            if(_token != null && _token.equals(request.getSession().getId())){
                EntityManager em = DBUtil.createEntityManager();

                Feedback f = new Feedback();
                Report r = em.find(Report.class, Integer.parseInt(request.getParameter("id")));

                f.setComment_employee((Employee)request.getSession().getAttribute("login_employee"));
                f.setReport_id(r);
                f.setContent(request.getParameter("content"));

                Timestamp currentTime = new Timestamp(System.currentTimeMillis());
                f.setCreated_at(currentTime);
                f.setUpdated_at(currentTime);

                List<String> errors = FeedbackValidator.validate(f);
                if(errors.size() > 0){
                    em.close();

                    request.setAttribute("_token", request.getSession().getId());
                    request.setAttribute("feedback", f);
                    request.setAttribute("report", r);
                    request.setAttribute("errors",errors);

                    RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/feedback/new.jsp");
                    rd.forward(request, response);
                }else{
                    em.getTransaction().begin();
                    em.persist(f);
                    em.getTransaction().commit();
                    em.close();

                    request.setAttribute("report", r);
                    request.getSession().setAttribute("flush", "登録が完了しました。");

                    response.sendRedirect(request.getContextPath() + "/feedback/index?id=" + r.getId());
                }

            }
    }

}
