package cts.phase3.controller;

import cts.phase3.controller.utils.MissionUtil;
import cts.phase3.controller.utils.WeeklyUtil;
import cts.phase3.persistence.model.*;
import cts.phase3.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import cts.phase3.controller.utils.UploadUtil;


import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

/**
 * @program: phase3
 * @author: Lijie
 * @description: ${description}
 * @create: 2018-05-31 19:25
 **/
@Controller
@RequestMapping("/{username}")
public class WorkerController {

    @Resource
    private MissionService missionService;

    @Resource
    private WorkerService workerService;

    @Resource
    private MissionPictureService missionPictureService;

    @Resource
    private AcceptService acceptService;

    @Resource
    private WorkerPictureService workerPictureService;

    @Resource
    private WeeklyLoginService weeklyLoginService;

    @Resource
    private EvaluatePictureService evaluatePictureService;

    @RequestMapping(value = "/accept/{missionName}", method = POST)
    @ResponseBody
    public String accept(@PathVariable("username") String username, @PathVariable("missionName") String missionName) {
        Mission mission = missionService.getMissionByName(missionName);
        if(mission.getAccepts() == mission.getNeeds())
            return "人数已满";
        mission.setAccepts(mission.getAccepts() + 1);
        Accept oldAccept = acceptService.getAcceptByMissionNameAndWorkerName(missionName, username);
        if(oldAccept != null)
            return "重复";
        Accept accept = new Accept();
        accept.setMissionName(missionName);
        accept.setWorkerName(username);
        accept.setStart(mission.getStart());
        accept.setEnd(mission.getEnd());
        accept.setCheckFlag(0);
        accept.setIsFinished(0);
        return String.valueOf(acceptService.addAccept(accept) && missionService.updateById(mission));
    }

    @RequestMapping(value = "/workerInfo", method = GET)
    @ResponseBody
    public String info(@PathVariable("username") String username) {
        Worker worker = workerService.getWorkerByName(username);
        List<Accept> accepts = acceptService.getAllAccept(worker.getUsername());
        int numOfFinished = 0;
        for(int i = 0; i < accepts.size(); i++){
            Accept accept = accepts.get(i);
            if(accept.getIsFinished() == 1)
                numOfFinished++;
        }
        WorkQualityController workQualityController = new WorkQualityController();
        int rank = workQualityController.ranking(worker.getUsername(), workerService);
        String result = "{\"username\":\"" + worker.getUsername() + "\", \"password\":\"" + worker.getPassword()
                + "\", \"points\":\"" + worker.getPoints() + "\", \"sex\":\"" + worker.getSex() + "\", \"area\":\""
                + worker.getArea() + "\", \"phone\":\"" + worker.getPhone() + "\", \"email\":\"" + worker.getEmail()
                + "\", \"numOfFinished\":\"" + numOfFinished + "\", \"rank\":\"" + rank +  "\"}";
        return result;
    }

    @RequestMapping(value = "/updateWorkerInfo/{updateStr}", method = POST)
    @ResponseBody
    public String updateInfo(@PathVariable("username") String username, @PathVariable("updateStr") String updateStr){
        Worker worker = workerService.getWorkerByName(username);
        String[] str = updateStr.split("_");
        if(!str[0].equals("email")){
            str[0] = str[0].replaceAll(",", ".");
            worker.setEmail(str[0]);
        }
        if(!str[1].equals("password"))
            worker.setPassword(str[1]);
        if(!str[2].equals("sex")){
            if(str[2].equals("male"))
                worker.setSex(0);
            else
                worker.setSex(1);
        }
        if(!str[3].equals("area"))
            worker.setArea(str[3]);
        if(!str[4].equals("phone"))
            worker.setPhone(str[4]);
        workerService.updateById(worker);
        return "Success";
    }

    @RequestMapping(value = "/worker/loadAllMissions", method = GET)
    @ResponseBody
    public String loadAllMissions(@PathVariable("username") String username){
        List<Mission> allMissions = missionService.allMission();
        List<Mission> missions = new ArrayList<>();
        for(int i = 0; i < allMissions.size(); i++){
            Mission mission = allMissions.get(i);
            if(!mission.getName().contains("_"))
                missions.add(mission);
        }
        MissionUtil missionUtil = new MissionUtil();
        return missionUtil.missionsToStr(missions, missionPictureService);
    }

    @RequestMapping(value = "/worker/loadAccepted", method = GET)
    @ResponseBody
    public String loadAccepted(@PathVariable("username") String username){
        List<String> names = workerService.getAllMissions(username);
        ArrayList<Mission> missions = new ArrayList<>();
        for(int i = 0; i < names.size(); i++){
            String missionName = names.get(i);
            Mission mission = missionService.getMissionByName(missionName);
            missions.add(mission);
        }
        MissionUtil missionUtil = new MissionUtil();
        return missionUtil.missionsToStr(missions, missionPictureService);
    }

    @RequestMapping(value = "/loadDraw/{missionName}", method = POST)
    @ResponseBody
    public int loadDrawMission(@PathVariable("username") String username, @PathVariable("missionName") String missionName){
        Accept accept = workerService.findByMissionNameAndWorkerName(username, missionName);
        Mission mission = missionService.getMissionByName(missionName);
        int num = workerPictureService.selectWorkerPictureByAccept(accept).size();
        return num;
    }

    @RequestMapping(value = "/loadDrawCanvas/{missionName}/{order}", method = POST)
    @ResponseBody
    public String loadDrawCanvasPicture(@PathVariable("username") String username, @PathVariable("missionName") String missionName,
                                        @PathVariable("order") String order){
        Accept accept = workerService.findByMissionNameAndWorkerName(username, missionName);
        int o = Integer.parseInt(order);
        WorkerPicture workerPicture = workerPictureService.selectWorkerPictureByAccept(accept).get(o);
        byte[] bytes = workerPicture.getPicture();
        Base64.Encoder encoder = Base64.getEncoder();
        String str = encoder.encodeToString(bytes);

        /*Base64.Decoder decoder = Base64.getDecoder();
        byte[] bytes1 = decoder.decode(str);
        System.out.println(encoder.encodeToString(bytes1));
        System.out.println(str);*/

        return str;
    }

    @RequestMapping(value = "/loadDrawBackground/{missionName}/{order}", method = POST)
    @ResponseBody
    public String loadDrawBackgroundPicture(@PathVariable("username") String username, @PathVariable("missionName") String missionName,
                                            @PathVariable("order") String order){
        int o = Integer.parseInt(order);
        MissionPicture workerPicture = missionPictureService.selectMissionPicturesByMission(missionName).get(o);
        byte[] bytes = workerPicture.getPicture();
        Base64.Encoder encoder = Base64.getEncoder();
        String str = encoder.encodeToString(bytes);

        /*Base64.Decoder decoder = Base64.getDecoder();
        byte[] bytes1 = decoder.decode(str);
        System.out.println(encoder.encodeToString(bytes1));
        System.out.println(str);*/

        return str;
    }


    @RequestMapping(value = "/saveDraw/{missionName}/{order}/{pic}", method = POST)
    @ResponseBody
    public boolean saveDrawMissionPicture(@PathVariable("username") String username, @PathVariable("missionName") String missionName,
                                          @PathVariable("order") String order, @PathVariable("pic") String pic) {
        Accept accept = workerService.findByMissionNameAndWorkerName(username, missionName);
        int o = Integer.parseInt(order);
        WorkerPicture workerPicture = workerPictureService.selectWorkerPictureByAccept(accept).get(o);
        Base64.Decoder decoder = Base64.getDecoder();
        pic = pic.replaceAll("_", "/");
        byte[] bytes = decoder.decode(pic);
        workerPicture.setPicture(bytes);

        EvaluatePicture evaluatePicture = new EvaluatePicture();
        evaluatePicture.setMissionname(workerPicture.getMissionName());
        evaluatePicture.setName(workerPicture.getName());
        evaluatePicture.setRatername("r");
        evaluatePicture.setState(0);
        evaluatePictureService.addEvaluatePicture(evaluatePicture);

        return workerPictureService.updateWorkerPictureByName(workerPicture);
    }

    @RequestMapping(value = "/finishDraw/{missionName}", method = POST)
    @ResponseBody
    public boolean finishDrawMissionPicture(@PathVariable("username") String username, @PathVariable("missionName") String missionName) {
        Accept accept = workerService.findByMissionNameAndWorkerName(username, missionName);
        accept.setIsFinished(1);
        Mission workerMission = missionService.getMissionByName(accept.getMissionName());
        Mission mission = new Mission();
        mission.setName(accept.getWorkerName() + "_" + accept.getMissionName());

        Mission oldMission = missionService.getMissionByName(mission.getName());
        if(oldMission != null)
            return false;

        mission.setType(workerMission.getType());
        mission.setDifficultyClass(workerMission.getDifficultyClass());
        mission.setDescription(workerMission.getDescription());
        mission.setNeeds(1);
        mission.setWay(workerMission.getWay());
        mission.setPoints(workerMission.getPoints());
        mission.setStart(workerMission.getEnd());

        String startTime = workerMission.getStart();
        String endTime = workerMission.getEnd();
        int sy = Integer.parseInt(startTime.substring(0, 4));
        int ey = Integer.parseInt(endTime.substring(0, 4));
        int sm;
        if(startTime.charAt(4) == '0')
            sm = Integer.parseInt(startTime.substring(5, 6));
        else
            sm = Integer.parseInt(startTime.substring(4, 6));
        int em;
        if(endTime.charAt(4) == '0')
            em = Integer.parseInt(endTime.substring(5, 6));
        else
            em = Integer.parseInt(endTime.substring(4, 6));
        int sd;
        if(startTime.charAt(6) == '0')
            sd = Integer.parseInt(startTime.substring(7));
        else
            sd = Integer.parseInt(startTime.substring(6));
        int ed;
        if(endTime.charAt(6) == '0')
            ed = Integer.parseInt(endTime.substring(7));
        else
            ed = Integer.parseInt(endTime.substring(6));
        int d = (ey - sy) * 365 + (em - sm) * 30 + (ed - sd);

        int nd = (ed + d) % 30;
        d = d - nd;
        int nm = d / 30 + em;
        int ny = ey + (d / 365);
        if(ny == ey && nm < em)
            ny++;

        String nds = nd + "";
        if(nds.length() == 1)
            nds = "0" + nds;
        String nms = nm + "";
        if(nms.length() == 1)
            nms = "0" + nms;
        mission.setEnd(ny + nms + nds);
        mission.setAccepts(0);

        WeeklyUtil weeklyUtil = new WeeklyUtil();
        weeklyUtil.updateWeekly(5, weeklyLoginService);

        return (acceptService.updateAccept(accept) && missionService.addMission(mission));
    }



    @RequestMapping(value = "/loadTag/{missionName}")
    @ResponseBody
    public int loadTagMission(@PathVariable("username") String username, @PathVariable("missionName") String missionName){
        Accept accept = workerService.findByMissionNameAndWorkerName(username, missionName);
        //Mission mission = missionService.getMissionByName(missionName);
        int num = workerPictureService.selectWorkerPictureByAccept(accept).size();
        return num;
    }

    @RequestMapping(value = "/loadTagCanvas/{missionName}/{order}", method = POST)
    @ResponseBody
    public String loadTagCanvasPicture(@PathVariable("username") String username, @PathVariable("missionName") String missionName,
                                       @PathVariable("order") String order){
        Accept accept = workerService.findByMissionNameAndWorkerName(username, missionName);
        int o = Integer.parseInt(order);
        WorkerPicture workerPicture = workerPictureService.selectWorkerPictureByAccept(accept).get(o);
        String str = workerPicture.getTag();
        return str;
    }

    @RequestMapping(value = "/loadTagBackground/{missionName}/{order}", method = POST)
    @ResponseBody
    public String loadTagBackgroundPicture(@PathVariable("username") String username, @PathVariable("missionName") String missionName,
                                           @PathVariable("order") String order){
        int o = Integer.parseInt(order);
        MissionPicture workerPicture = missionPictureService.selectMissionPicturesByMission(missionName).get(o);
        byte[] bytes = workerPicture.getPicture();
        Base64.Encoder encoder = Base64.getEncoder();
        return encoder.encodeToString(bytes);
    }

    @RequestMapping(value = "/saveTag/{missionName}/{order}/{tag}", method = POST)
    @ResponseBody
    public boolean saveTagMissionPicture(@PathVariable("username") String username, @PathVariable("missionName") String missionName,
                                         @PathVariable("order") String order, @PathVariable("tag") String tag) {
        Accept accept = workerService.findByMissionNameAndWorkerName(username, missionName);
        int o = Integer.parseInt(order);
        WorkerPicture workerPicture = workerPictureService.selectWorkerPictureByAccept(accept).get(o);
        workerPicture.setTag(tag);

        EvaluatePicture evaluatePicture = new EvaluatePicture();
        evaluatePicture.setMissionname(workerPicture.getMissionName());
        evaluatePicture.setName(workerPicture.getName());
        evaluatePicture.setRatername("r");
        evaluatePicture.setState(0);
        evaluatePictureService.addEvaluatePicture(evaluatePicture);

        return workerPictureService.updateWorkerPictureByName(workerPicture);
    }

    @RequestMapping(value = "/finishTag/{missionName}", method = POST)
    @ResponseBody
    public boolean finishTagMissionPicture(@PathVariable("username") String username, @PathVariable("missionName") String missionName) {
        Accept accept = workerService.findByMissionNameAndWorkerName(username, missionName);
        Mission workerMission = missionService.getMissionByName(accept.getMissionName());
        Mission mission = new Mission();
        mission.setName(accept.getWorkerName() + "_" + accept.getMissionName());

        Mission oldMission = missionService.getMissionByName(mission.getName());

        if(oldMission != null)
            return false;

        mission.setType(workerMission.getType());
        mission.setDifficultyClass(workerMission.getDifficultyClass());
        mission.setDescription(workerMission.getDescription());
        mission.setNeeds(1);
        mission.setWay(workerMission.getWay());
        mission.setPoints(workerMission.getPoints());
        mission.setStart(workerMission.getEnd());

        String startTime = workerMission.getStart();
        String endTime = workerMission.getEnd();
        int sy = Integer.parseInt(startTime.substring(0, 4));
        int ey = Integer.parseInt(endTime.substring(0, 4));
        int sm;
        if(startTime.charAt(4) == '0')
            sm = Integer.parseInt(startTime.substring(5, 6));
        else
            sm = Integer.parseInt(startTime.substring(4, 6));
        int em;
        if(endTime.charAt(4) == '0')
            em = Integer.parseInt(endTime.substring(5, 6));
        else
            em = Integer.parseInt(endTime.substring(4, 6));
        int sd;
        if(startTime.charAt(6) == '0')
            sd = Integer.parseInt(startTime.substring(7));
        else
            sd = Integer.parseInt(startTime.substring(6));
        int ed;
        if(endTime.charAt(6) == '0')
            ed = Integer.parseInt(endTime.substring(7));
        else
            ed = Integer.parseInt(endTime.substring(6));
        int d = (ey - sy) * 365 + (em - sm) * 30 + (ed - sd);

        int nd = (ed + d) % 30;
        d = d - nd;
        int nm = d / 30 + em;
        int ny = ey + (d / 365);
        if(ny == ey && nm < em)
            ny++;

        String nds = nd + "";
        if(nds.length() == 1)
            nds = "0" + nds;
        String nms = nm + "";
        if(nms.length() == 1)
            nms = "0" + nms;
        mission.setEnd(ny + nms + nds);
        mission.setAccepts(0);

        accept.setIsFinished(0);

        acceptService.updateAccept(accept);
        missionService.addMission(mission);

        return true;

    }

    @RequestMapping(value = "/saveDrawXY/{missionName}/{order}/{xy}", method = POST)
    @ResponseBody
    public boolean xy(@PathVariable("username") String username, @PathVariable("missionName") String missionName,
                     @PathVariable("order") String order, @PathVariable("xy") String xy){
        System.out.println(xy);
        return true;
    }



}
