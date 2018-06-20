package cts.phase3.controller;


import cts.phase3.controller.utils.WeeklyUtil;
import cts.phase3.persistence.model.Announcer;
import cts.phase3.persistence.model.Rater;
import cts.phase3.persistence.model.User;
import cts.phase3.persistence.model.Worker;
import cts.phase3.service.AnnouncerService;
import cts.phase3.service.RaterService;
import cts.phase3.service.WeeklyLoginService;
import cts.phase3.service.WorkerService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


import javax.annotation.Resource;

import static org.springframework.web.bind.annotation.RequestMethod.POST;

@Controller
@RequestMapping("/lr")
public class RLController {

    @Resource
    private AnnouncerService announcerService;

    @Resource
    private RaterService raterService;

    @Resource
    private WorkerService workerService;

    @Resource
    private WeeklyLoginService weeklyLoginService;

    @ResponseBody
    @RequestMapping(value = "/register/{dataStr}", method = POST)
    public String register(@PathVariable("dataStr") String dataStr){
        String[] data = dataStr.split("_");
        int type = Integer.valueOf(data[2]);
        User user = new User();
        user.setUsername(data[0]);
        user.setPassword(data[1]);
        if(type == 0){
            Announcer announcer = new Announcer();
            announcer.setUsername(user.getUsername());
            announcer.setPassword(user.getPassword());
            if(announcerService.getAnnouncerByName(announcer.getUsername()) == null) {
                announcerService.addAnnouncer(announcer);
                return "announcer register success";
            }
            else {
                return "announcer register fail";
            }
        }
        else if(type == 1){
            Rater rater = new Rater();
            rater.setUsername(user.getUsername());
            rater.setPassword(user.getPassword());
            if(raterService.getRaterByName(rater.getUsername()) == null) {
                raterService.addRater(rater);
                return "rater register success";
            }
            else {
                return "rater register fail";
            }
        }
        else if(type == 2){
            Worker worker = new Worker();
            worker.setId(23);
            worker.setUsername(user.getUsername());
            worker.setPassword(user.getPassword());
            if(workerService.getWorkerByName(worker.getUsername()) == null) {
                workerService.addWorker(worker);
                return "worker register success";
            }
            else {
                return "worker register fail";
            }
        }
        else {
            return "something wrong";
        }
    }

    @ResponseBody
    @RequestMapping(value = "/login/{dataStr}", method = POST)
    public String login(@PathVariable("dataStr") String dataStr){

        WeeklyUtil weeklyUtil = new WeeklyUtil();
        String[] data = dataStr.split("_");
        int type = Integer.valueOf(data[2]);
        User user = new User();
        user.setUsername(data[0]);
        user.setPassword(data[1]);
        if(data[0].equals("admin") && data[1].equals("admin")){
            return "admin";
        }
        if(type == 0){
            Announcer announcer = announcerService.getAnnouncerByName(user.getUsername());
            if(announcer != null && announcer.getUsername().equals(user.getUsername())){
                if(announcer.getPassword().equals(user.getPassword())){

                    weeklyUtil.updateWeekly(1, weeklyLoginService);

                    return "match";
                }
                else{
                    return "a not match";
                }
            }
            else
                System.out.println("error");
        }
        else if(type == 1){
            Rater rater = raterService.getRaterByName(user.getUsername());
            if(rater != null && rater.getUsername().equals(user.getUsername())){
                if(rater.getPassword().equals(user.getPassword())){

                    weeklyUtil.updateWeekly(2, weeklyLoginService);

                    return "match";
                }
                else{
                    System.out.println("not match");
                    return "r not match";
                }
            }
            else
                return "error";
        }
        else if(type == 2){
            Worker worker = workerService.getWorkerByName(user.getUsername());
            if(worker != null && worker.getUsername().equals(user.getUsername())) {
                if(worker.getPassword().equals(user.getPassword())){

                    weeklyUtil.updateWeekly(3, weeklyLoginService);

                    return "match";
                }
                else{
                    return "w not match";
                }
            }
            else
                return "error";
        }
        else {
            System.out.println("wrong");
        }
        return "wrong";
    }

}
