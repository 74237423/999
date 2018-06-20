package cts.phase3.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@Controller
@RequestMapping("/")
public class HomeController {

    @RequestMapping(value = "admin", method = GET)
    public String adminHome() {
        return "pages/adminer_home.html";
    }

    @RequestMapping(value = "announcer", method = GET)
    public String bossHome() {
        return "pages/boss_home.html";
    }

    @RequestMapping(value = "home", method = GET)
    public String home() {
        return "pages/first_home.html";
    }

    @RequestMapping(value = "login", method = GET)
    public String login() {
        return "pages/login.html";
    }

    @RequestMapping(value = "register", method = GET)
    public String register () {
        return "pages/register.html";
    }

    @RequestMapping(value = "set", method = GET)
    public String set () {
        return "pages/set.html";
    }

    @RequestMapping(value = "users_tables", method = GET)
    public String users_tables(){
        return "pages/users_tables.html";
    }

    @RequestMapping(value = "boss_tasks_pics", method = GET)
    public String boss_tasks_pics () {
        return "pages/boss_tasks_pics.html";
    }

    @RequestMapping(value = "tasks_tables", method = GET)
    public String tasks_tables () {
        return "pages/tasks_tables.html";
    }

    @RequestMapping(value = "{userStr}", method = GET)
    public String workerHome (@PathVariable("userStr") String userStr) {
        String[] str = userStr.split("_");
        if(str.length == 2) {
            if (str[0].equals("worker"))
                return "pages/worker_home.html";
            else if (str[0].equals("boss"))
                return "pages/boss_home.html";
            else if (str[0].equals("estimator"))
                return "pages/estimator_home.html";
            else if (str[0].equals("admin"))
                return "pages/adminer_home.html";
            else
                return "pages/first_home.html";
        }
        else if(str.length == 3){
            if(str[1].equals("draw"))
                return "pages/draw.html";
            else if(str[1].equals("tag"))
                return "pages/tag.html";
            else if(str[0].equals("boss")){
                return "pages/pay.html";
            }
            else
                return "pages/first_home.html";
        }
        else if(str.length == 5){
            if(str[1].equals("draw"))
                return "pages/estimate_draw.html";
            else if(str[1].equals("tag"))
                return "pages/estimate_tag.html";
            else
                return "pages/first_home.html";
        }
        else
            return "pages/first_home.html";
    }

    @RequestMapping(value = "test", method = GET)
    public String test () {
        return "pages/test.html";
    }

    @RequestMapping(value = "str", method = GET)
    public String draw(String str){
        return "pages/draw.html";
    }
}