package main;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

@RestController
public class DefaultController {

    @RequestMapping("/")
    public String index() {
        return LocalDate.now().toString();
    }
}
