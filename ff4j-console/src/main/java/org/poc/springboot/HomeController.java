package org.poc.springboot;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    @RequestMapping(value = "/", method = RequestMethod.GET, produces = "text/html")
    public String home() {
        StringBuilder response = new StringBuilder("<html><body>");
        response.append("<H1>OK Testando !</h1>");
        response.append("</span></body></html>");
        return response.toString();
    }
}
