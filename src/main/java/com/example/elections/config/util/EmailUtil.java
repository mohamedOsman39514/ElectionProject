package com.example.elections.config.util;//package com.example.CRUD.with.postgresql.config.util;
//
//import com.example.CRUD.with.postgresql.model.Employee;
//import org.springframework.mail.SimpleMailMessage;
//import org.springframework.security.core.userdetails.User;
//import org.springframework.stereotype.Component;
//
//import java.util.Locale;
//
//@Component
//public class EmailUtil {
//
//    public SimpleMailMessage constructResetTokenEmail(
//            /*String contextPath,*/ Locale locale, String token, Employee user) {
////        String url = contextPath + "/user/changePassword?token=" + token;
////        String message = messages.getMessage("message.resetPassword",
////                null, locale);
//        return constructEmail("Reset Password", "message.resetPassword" + " \r\n" /*+ url*/, user);
//    }
//
//    private SimpleMailMessage constructEmail(String subject, String body,
//                                             Employee user) {
//        SimpleMailMessage email = new SimpleMailMessage();
//        email.setSubject(subject);
//        email.setText(body);
//        email.setTo(user.getEmail());
//        //email.setFrom(env.getProperty("support.email"));
//        return email;
//    }
//}
