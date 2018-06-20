package cts.phase3.controller;

import cts.phase3.persistence.model.Mission;
import cts.phase3.persistence.model.User;
import cts.phase3.service.MissionService;
import cts.phase3.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @program: phase3
 * @author: Lijie
 * @description: ${description}
 * @create: 2018-05-28 21:13
 **/

@Controller
@RequestMapping("/user")
public class UserController {
    @Resource
    private UserService userService;

    @Resource
    private MissionService missionService;

    @RequestMapping("/showUser")
    @ResponseBody
    public User toIndex(HttpServletRequest request, Model model){
        int userId = Integer.parseInt(request.getParameter("id"));
        User user = this.userService.getUserById(userId);
        return user;
    }

    @RequestMapping("/showMission")
    @ResponseBody
    public Mission toIndex0(HttpServletRequest request, Model model){
        Mission m = new Mission();
        m.setId(1126);
        m.setName("mission1");
        m.setNeeds(1235);
        m.setPoints(30020);
        m.setDescription("asdfgasg");
        m.setStart("2018-05-26");
        m.setEnd("2018-06-10");
//        this.missionService.addMission(m);
//        this.missionService.deleteMission(214);
//        this.missionService.updateById(m);
//        this.missionService.addMission(m);
//        this.missionService.deleteMission(112);
        int userId = Integer.parseInt(request.getParameter("id"));
        Mission mission = this.missionService.getMissionById(userId);
        return mission;
    }

    @RequestMapping("/allMission")
    @ResponseBody
    public List<Mission> test() {
        Mission m = new Mission();
        m.setId(1126);

        m.setName("mission2");
        m.setNeeds(1235);
        m.setPoints(30020);
        m.setDescription("asdfgasg");
        m.setStart("2018-05-29");
        m.setEnd("2018-06-12");
        this.missionService.addMission(m);
        return this.missionService.allMission();
    }



}