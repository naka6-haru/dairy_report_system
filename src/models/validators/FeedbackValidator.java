package models.validators;

import java.util.ArrayList;
import java.util.List;

import models.Feedback;

public class FeedbackValidator {
    public static List<String> validate(Feedback f){
        List<String> errors = new ArrayList<String>();

        String content_error = _validateContent(f.getContent());
        if(!content_error.equals("")){
            errors.add(content_error);
        }
        return errors;
    }

    public static String _validateContent(String content){
        if(content == null || content.equals("")){
            return "コメントを入力してください。";
        }
        return "";
    }

}
