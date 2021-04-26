package controllers.follows;

import java.io.IOException;
import java.util.List;

import javax.persistence.EntityManager;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.Employee;
import models.Follow;
import models.validators.FollowValidator;
import utils.DBUtil;

/**
 * Servlet implementation class FollowsCreateServlet
 */
@WebServlet("/follows/create")
public class FollowsCreateServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public FollowsCreateServlet() {
        super();
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String _token = request.getParameter("_token");
        if(_token != null && _token.equals(request.getSession().getId())){
            EntityManager em = DBUtil.createEntityManager();

            Follow f = new Follow();
            Employee e = em.find(Employee.class,Integer.parseInt(request.getParameter("id")));


            f.setFollowor_employee((Employee)request.getSession().getAttribute("login_employee"));
            f.setFollow_employee(e);

            List<String> errors = FollowValidator.validate(request.getSession().getAttribute("login_employee"),f, true);
            if(errors.size() > 0){
                em.close();

                request.setAttribute("_token", request.getSession().getId());
                request.setAttribute("follow", f);
                request.getSession().setAttribute("errors", errors);

                response.sendRedirect(request.getContextPath() + "/follows/index");
            }else{
                em.getTransaction().begin();
                em.persist(f);
                em.getTransaction().commit();
                em.close();

                request.getSession().setAttribute("flush", "フォローしました。");
                response.sendRedirect(request.getContextPath() + "/follows/index");
            }

        }
    }

}
