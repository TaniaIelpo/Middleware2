package co.develhope.Middleware2.controllers;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Tania Ielpo
 */

@RestController
@RequestMapping
public class BasicController {

    /**
     * welcomes the user on the root mapping
     * @return A welcome String
     */
    @GetMapping
    public String sayHello(){
        return "Welcome here";
    }
}
