package co.develhope.Middleware2.interceptors;

import co.develhope.Middleware2.entities.Month;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

/**
 * @autor Tania Ielpo
 */

@Component
public class MonthInterceptor implements HandlerInterceptor {

    /**
     * a list of first 6 Months
     */
    List<Month> months= new ArrayList<>(Arrays.asList(
            new Month(1,"January","Gennaio","Januar"),
            new Month(2,"February","Febbraio","Februar"),
            new Month(3,"March","Marzo","Marsch"),
            new Month(4,"April","Aprile","April"),
            new Month(5,"May","Maggio","Kann"),
            new Month(6,"June","Giugno","Juni")
    ));


    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response,
                             Object handler) throws Exception {
        if(!request.getRequestURL().toString().contains("/months")) return true;
        //takes the header monthNumber from the request
       String monthHeader =request.getHeader("monthNumber");
        //if monthNumber is null/empty then returns an HTTP Bad Request error
        if(monthHeader==null || monthHeader.isEmpty()){
            response.setStatus(400);
        }
        else{
            //looks if the passed monthNumber is present in the list
            int monthId=Integer.parseInt(monthHeader);
            Optional<Month> month=months.stream().filter(singleMonth -> {
                return singleMonth.getMonthNumber()==monthId;
            }).findFirst();
            //if present, returns it using a specific request attribute
            if(month.isPresent()){
                request.setAttribute("monthNumber", month.get());
            }
            //returns an empty Month with all the string values set to nope
            else
                request.setAttribute("monthNumber",
                        new Month(monthId,"nope","nope", "nope"));
            //returns an HTTP OK status
            response.setStatus(200);
        }
        return true;
    }

    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable ModelAndView modelAndView) throws Exception {
    }

    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable Exception ex) throws Exception {
    }
}
