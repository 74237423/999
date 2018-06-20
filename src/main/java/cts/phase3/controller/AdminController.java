package cts.phase3.controller;

import cts.phase3.controller.utils.MissionUtil;
import cts.phase3.controller.utils.WeeklyUtil;
import cts.phase3.persistence.model.*;
import cts.phase3.service.*;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


import javax.annotation.Resource;
import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Resource
    private WorkerService workerService;

    @Resource
    private RaterService raterService;

    @Resource
    private AnnouncerService announcerService;

    @Resource
    private MissionService missionService;

    @Resource
    private MissionPictureService missionPictureService;

    @Resource
    private WeeklyLoginService weeklyLoginService;

    @ResponseBody
    @RequestMapping(value = "/announcerInfo", method = GET)
    public String announcerInfo(){
        List<Announcer> announcers = announcerService.allAnnouncer();
        String result = "";
        for(int i = 0; i < announcers.size(); i++){
            Announcer announcer = announcers.get(i);
            result += "{\"username\":\"" + announcer.getUsername() + "\", \"password\":\"" + announcer.getPassword()
                    + "\", \"points\":\"" + announcer.getPoints() + "\", \"sex\":\"" + announcer.getSex() + "\", \"area\":\""
                    + announcer.getArea() + "\", \"phone\":\"" + announcer.getPhone() + "\", \"email\":\"" + announcer.getEmail()
                    + "\"}";
            if(i != announcers.size()-1){
                result=result+'_';
            }
        }

        return result;
    }

    @ResponseBody
    @RequestMapping(value = "/workerInfo", method = GET)
    public String workerInfo(){
        List<Worker> workers = workerService.allWorker();
        String result = "";
        for(int i = 0; i < workers.size(); i++){
            Worker worker = workers.get(i);
            result += "{\"username\":\"" + worker.getUsername() + "\", \"password\":\"" + worker.getPassword()
                    + "\", \"points\":\"" + worker.getPoints() + "\", \"sex\":\"" + worker.getSex() + "\", \"area\":\""
                    + worker.getArea() + "\", \"phone\":\"" + worker.getPhone() + "\", \"email\":\"" + worker.getEmail()
                    + "\"}";
            if(i != workers.size()-1){
                result=result+'_';
            }
        }
        return result;
    }

    @ResponseBody
    @RequestMapping(value = "/raterInfo", method = GET)
    public String raterInfo(){
        List<Rater> raters = raterService.allRater();
        String result = "";
        for (int i = 0; i < raters.size(); i++){
            Rater rater = raters.get(i);
            result += "{\"username\":\"" + rater.getUsername() + "\", \"password\":\"" + rater.getPassword()
                    + "\", \"points\":\"" + rater.getPoints() + "\", \"sex\":\"" + rater.getSex() + "\", \"area\":\""
                    + rater.getArea() + "\", \"phone\":\"" + rater.getPhone() + "\", \"email\":\"" + rater.getEmail()
                    + "\"}";
            if(i != raters.size()-1){
                result=result+'_';
            }
        }

        return result;
    }

    @RequestMapping(value = "/allMissions", method = GET)
    @ResponseBody
    public String allMissions(){
        List<Mission> missions = missionService.allMission();
        MissionUtil missionUtil = new MissionUtil();
        return missionUtil.missionsToStr(missions, missionPictureService);
    }

    @RequestMapping(value = "/numOfUsers", method = GET)
    @ResponseBody
    public String numOfUsers(){
        String ret = "";
        int a = announcerService.allAnnouncer().size();
        int w = workerService.allWorker().size();
        int r = raterService.allRater().size();
        ret = "{\"announcers\":\"" + a + "\", \"workers\":\"" + w + "\", \"raters\":\""
                + r + "\"}";
        return ret;
    }

    @RequestMapping(value = "/usersLoginInfo", method = GET)
    @ResponseBody
    public String announcerLoginInfo(){
        WeeklyUtil loginUtil = new WeeklyUtil();
        Weekly weekly = loginUtil.getWeekly(1, weeklyLoginService);
        String ret = "{\"mon\":\"" + weekly.getMon() + "\", \"tue\":\"" + weekly.getTue()
                + "\", \"wed\":\"" + weekly.getWed() + "\", \"thu\":\"" + weekly.getThu()
                + "\", \"Fri\":\"" + weekly.getFri() + "\", \"sat\":\"" + weekly.getSat()
                + "\", \"sun\":\"" + weekly.getSun() + "\"}";
        weekly = loginUtil.getWeekly(2, weeklyLoginService);
        ret += "_{\"mon\":\"" + weekly.getMon() + "\", \"tue\":\"" + weekly.getTue()
                + "\", \"wed\":\"" + weekly.getWed() + "\", \"thu\":\"" + weekly.getThu()
                + "\", \"Fri\":\"" + weekly.getFri() + "\", \"sat\":\"" + weekly.getSat()
                + "\", \"sun\":\"" + weekly.getSun() + "\"}";
        loginUtil = new WeeklyUtil();
        weekly = loginUtil.getWeekly(3, weeklyLoginService);
        ret += "_{\"mon\":\"" + weekly.getMon() + "\", \"tue\":\"" + weekly.getTue()
                + "\", \"wed\":\"" + weekly.getWed() + "\", \"thu\":\"" + weekly.getThu()
                + "\", \"Fri\":\"" + weekly.getFri() + "\", \"sat\":\"" + weekly.getSat()
                + "\", \"sun\":\"" + weekly.getSun() + "\"}";
        return ret;
    }

    @RequestMapping(value = "/workerLoginInfo", method = GET)
    @ResponseBody
    public String workerLoginInfo(){
        WeeklyUtil loginUtil = new WeeklyUtil();
        Weekly weekly = loginUtil.getWeekly(3, weeklyLoginService);
        String ret = "{\"mon\":\"" + weekly.getMon() + "\", \"tue\":\"" + weekly.getTue()
                + "\", \"wed\":\"" + weekly.getWed() + "\", \"thu\":\"" + weekly.getThu()
                + "\", \"Fri\":\"" + weekly.getFri() + "\", \"sat\":\"" + weekly.getSat()
                + "\", \"sun\":\"" + weekly.getSun() + "\"}";
        return ret;
    }

    @RequestMapping(value = "/releasedInfo", method = GET)
    @ResponseBody
    public String releasedInfo(){
        WeeklyUtil loginUtil = new WeeklyUtil();
        Weekly weekly = loginUtil.getWeekly(4, weeklyLoginService);
        String ret = "{\"mon\":\"" + weekly.getMon() + "\", \"tue\":\"" + weekly.getTue()
                + "\", \"wed\":\"" + weekly.getWed() + "\", \"thu\":\"" + weekly.getThu()
                + "\", \"Fri\":\"" + weekly.getFri() + "\", \"sat\":\"" + weekly.getSat()
                + "\", \"sun\":\"" + weekly.getSun() + "\"}";
        return ret;
    }

    @RequestMapping(value = "/acceptedInfo", method = GET)
    @ResponseBody
    public String acceptedInfo(){
        WeeklyUtil loginUtil = new WeeklyUtil();
        Weekly weekly = loginUtil.getWeekly(5, weeklyLoginService);
        String ret = "{\"mon\":\"" + weekly.getMon() + "\", \"tue\":\"" + weekly.getTue()
                + "\", \"wed\":\"" + weekly.getWed() + "\", \"thu\":\"" + weekly.getThu()
                + "\", \"Fri\":\"" + weekly.getFri() + "\", \"sat\":\"" + weekly.getSat()
                + "\", \"sun\":\"" + weekly.getSun() + "\"}";
        return ret;
    }

    @RequestMapping(value = "/evaluatedInfo", method = GET)
    @ResponseBody
    public String evaluatedInfo(){
        WeeklyUtil loginUtil = new WeeklyUtil();
        Weekly weekly = loginUtil.getWeekly(6, weeklyLoginService);
        String ret = "{\"mon\":\"" + weekly.getMon() + "\", \"tue\":\"" + weekly.getTue()
                + "\", \"wed\":\"" + weekly.getWed() + "\", \"thu\":\"" + weekly.getThu()
                + "\", \"Fri\":\"" + weekly.getFri() + "\", \"sat\":\"" + weekly.getSat()
                + "\", \"sun\":\"" + weekly.getSun() + "\"}";
        return ret;
    }

}
