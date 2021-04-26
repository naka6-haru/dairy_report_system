package models.validators;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;

import models.Employee;
import models.Follow;
import utils.DBUtil;

public class FollowValidator {
    public static List<String> validate(Object e,Follow f,Boolean follow_employeeDuplicateCheckFlag){
        List<String> errors = new ArrayList<String>();

        String follow_error = validateFollow(e,f.getFollow_employee(),follow_employeeDuplicateCheckFlag);

        if(!follow_error.equals("") ){
            errors.add(follow_error);
        }
        return errors;

    }

    private static String validateFollow(Object e,Employee follow_employee, Boolean follow_employeeDuplicateCheckFlag){
        if(follow_employeeDuplicateCheckFlag){

            EntityManager em = DBUtil.createEntityManager();
            long follows_count = (long)em.createNamedQuery("follow_employeeDuplicateCheckFlag",Long.class)
                                              .setParameter("followor_employee", e)
                                              .setParameter("follow_employee", follow_employee)
                                              .getSingleResult();
            em.close();
            if(follows_count > 0){
                return "すでにフォローしています。";
            }
        }
        return "";
    }

}
