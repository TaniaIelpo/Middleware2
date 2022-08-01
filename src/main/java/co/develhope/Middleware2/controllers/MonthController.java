package co.develhope.Middleware2.controllers;

import co.develhope.Middleware2.entities.Month;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Tania Ielpo
 */
@RestController
@RequestMapping("/months")
public class MonthController {

    /**
     * returns a Month using a specific request attribute
     * @param request
     * @return a month
     */

    @GetMapping
    public Month getMonth(HttpServletRequest request){

        return (Month) request.getAttribute("monthNumber");
    }
}
