package main.controller;

import main.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class DefaultController {

    @Autowired
    TaskService taskService;

    @RequestMapping("/")
    public String index(Model model) {
        model.addAttribute("tasksCount", taskService.getAllTasksAnyUsersCount());
        model.addAttribute("tasks", taskService.getAllTasksAnyUsers());
        return "index";
    }
}
