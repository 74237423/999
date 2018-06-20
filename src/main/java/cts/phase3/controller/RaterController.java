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


import javax.annotation.Resource;

import java.util.*;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@Controller
@RequestMapping("/{username}")
public class RaterController {

    @Resource
    private ReleaseService releaseService;

    @Resource
    private MissionService missionService;

    @Resource
    private RaterService raterService;

    @Resource
    private WorkerPictureService workerPictureService;

    @Resource
    private AcceptService acceptService;

    @Resource
    private EvaluateService evaluateService;

    @Resource
    private EvaluatePictureService evaluatePictureService;

    @Resource
    private WorkerService workerService;

    @Resource
    private AnnouncerService announcerService;

    @Resource
    private WeeklyLoginService weeklyLoginService;

    @RequestMapping(value = "/raterInfo", method = GET)
    @ResponseBody
    public String info(@PathVariable("username") String username){
        List<Evaluate> evaluates = evaluateService.getAllEvaluateByRater(username);
        int numOfFinished = 0;
        for(int i = 0; i < evaluates.size(); i++){
            Evaluate evaluate = evaluates.get(i);
            if(evaluate.getState() == 1)
                numOfFinished++;
        }
        Rater rater = raterService.getRaterByName(username);
        int rank = getRanking(username);
        String result = "{\"username\":\"" + rater.getUsername() + "\", \"password\":\"" + rater.getPassword()
                + "\", \"points\":\"" + rater.getPoints() + "\", \"sex\":\"" + rater.getSex() + "\", \"area\":\""
                + rater.getArea() + "\", \"phone\":\"" + rater.getPhone() + "\", \"email\":\"" + rater.getEmail()
                + "\", \"numOfFinished\":\"" + numOfFinished + "\", \"rank\":\"" + rank+ "\"}";
        return result;
    }

    @RequestMapping(value = "/updateRaterInfo/{updateStr}", method = POST)
    @ResponseBody
    public String updateInfo(@PathVariable("username") String username, @PathVariable("updateStr") String updateStr){
        Rater rater = raterService.getRaterByName(username);
        String[] str = updateStr.split("_");
        if(!str[0].equals("email")){
            str[0] = str[0].replaceAll(",", ".");
            rater.setEmail(str[0]);
        }
        if(!str[1].equals("password"))
            rater.setPassword(str[1]);
        if(!str[2].equals("sex")){
            if(str[2].equals("male"))
                rater.setSex(0);
            else
                rater.setSex(1);
        }
        if(!str[3].equals("area"))
            rater.setArea(str[3]);
        if(!str[4].equals("phone"))
            rater.setPhone(str[4]);
        raterService.updateById(rater);
        return "Success";
    }

    @RequestMapping(value = "/rater/loadAllMissions", method = GET)
    @ResponseBody
    public String loadAllMissions(@PathVariable("username") String username){
        List<Mission> allMissions = missionService.allMission();
        List<Mission> missions = new ArrayList<>();
        for(int i = 0; i < allMissions.size(); i++){
            Mission mission = allMissions.get(i);
            if(mission.getName().contains("_"))
                missions.add(mission);
        }
        String result = "";
        for(int i = 0; i < missions.size(); i++){
            Mission mission = missions.get(i);

            String start = mission.getStart();
            start = start.substring(0, 4) + "-" + start.substring(4, 6) + "-" + start.substring(6);
            String end = mission.getEnd();
            end = end.substring(0, 4) + "-" + end.substring(4, 6) + "-" + end.substring(6);

            String workerName = mission.getName().split("_")[0];
            String missionName = mission.getName().split("_")[1];

            Accept accept = acceptService.getAcceptByMissionNameAndWorkerName(missionName, workerName);
            WorkerPicture workerPicture = workerPictureService.selectWorkerPictureByAccept(accept).get(0);
            byte[] bytes = workerPicture.getPicture();
            Base64.Encoder encoder = Base64.getEncoder();
            String str = encoder.encodeToString(bytes);

            String name = mission.getName().replaceAll("_", ".");;
            result += "{\"missionName\":\"" + name + "\", \"start\":\"" + start + "\", \"end\":\"" + end
                    + "\", \"points\":\"" + mission.getPoints() + "\", \"needs\":\"" + mission.getNeeds()
                    + "\", \"description\":\"" + mission.getDescription() + "\", \"type\":\"" + mission.getType()
                    + "\", \"way\":\"" + mission.getWay() + "\", \"difficultyClass\":\"" + mission.getDifficultyClass()
                    + "\", \"accepts\":\"" + mission.getAccepts() + "\", \"picture\":\"" + str + "\"}";
            if(i != missions.size() - 1)
                result += "_";
        }
        return result;
    }

    @RequestMapping(value = "/rater/loadEvaluated", method = GET)
    @ResponseBody
    public String loadEvaluated(@PathVariable("username") String username){
        Rater rater = raterService.getRaterByName(username);
        List<Evaluate> evaluates = evaluateService.getAllEvaluateByRater(rater.getUsername());
        System.out.println(evaluates.get(0).getMissionName());
        List<Mission> missions = new ArrayList<>();
        for(int i = 0; i < evaluates.size(); i++){
            String name = evaluates.get(i).getMissionName();
            Mission mission = missionService.getMissionByName(name);
            missions.add(mission);
        }
        String result = "";
        for(int i = 0; i < missions.size(); i++){
            Mission mission = missions.get(i);

            System.out.println(mission);

            String start = mission.getStart();
            start = start.substring(0, 4) + "-" + start.substring(4, 6) + "-" + start.substring(6);
            String end = mission.getEnd();
            end = end.substring(0, 4) + "-" + end.substring(4, 6) + "-" + end.substring(6);

            String workerName = mission.getName().split("_")[0];
            String missionName = mission.getName().split("_")[1];
            Accept accept = acceptService.getAcceptByMissionNameAndWorkerName(missionName, workerName);
            WorkerPicture workerPicture = workerPictureService.selectWorkerPictureByAccept(accept).get(0);
            byte[] bytes = workerPicture.getPicture();
            Base64.Encoder encoder = Base64.getEncoder();
            String str = encoder.encodeToString(bytes);

            String name = mission.getName();
            name = name.replaceAll("_", ".");
            result += "{\"missionName\":\"" + name + "\", \"start\":\"" + start + "\", \"end\":\"" + end
                    + "\", \"points\":\"" + mission.getPoints() + "\", \"needs\":\"" + mission.getNeeds()
                    + "\", \"description\":\"" + mission.getDescription() + "\", \"type\":\"" + mission.getType()
                    + "\", \"way\":\"" + mission.getWay() + "\", \"difficultyClass\":\"" + mission.getDifficultyClass()
                    + "\", \"accepts\":\"" + mission.getAccepts() + "\", \"picture\":\"" + str + "\"}";
            if(i != missions.size() - 1)
                result += "_";
        }
        return result;
    }

    @RequestMapping(value = "/load/evaluate/{missionName}", method = POST)
    @ResponseBody
    public int loadEvaluate(@PathVariable("username") String username, @PathVariable("missionName") String missionName){
        String[] ns = missionName.split("_");
        Accept accept = acceptService.getAcceptByMissionNameAndWorkerName(ns[1], ns[0]);
        return workerPictureService.selectWorkerPictureByAccept(accept).size();
    }

    @RequestMapping(value = "/load/evaluate/{missionName}/{order}", method = POST)
    @ResponseBody
    public String loadEvaluatePicture(@PathVariable("username") String username, @PathVariable("missionName") String missionName,
                                   @PathVariable("order") String order){
        String[] ns = missionName.split("_");
        Accept accept = acceptService.getAcceptByMissionNameAndWorkerName(ns[1], ns[0]);
        int o = Integer.parseInt(order);
        WorkerPicture workerPicture = workerPictureService.selectWorkerPictureByAccept(accept).get(o);
        byte[] bytes = workerPicture.getPicture();
        Base64.Encoder encoder = Base64.getEncoder();
        String str = encoder.encodeToString(bytes);
        return str;
    }

    @RequestMapping(value = "/evaluate/{missionName}", method = POST)
    @ResponseBody
    public boolean addEvaluate(@PathVariable("username") String username, @PathVariable("missionName") String missionName){
        Evaluate evaluate = new Evaluate();
        Mission mission = missionService.getMissionByName(missionName);
        if(mission.getAccepts() == 1){
            return false;
        }
        evaluate.setMissionName(missionName);
        evaluate.setStart(mission.getStart());
        evaluate.setEnd(mission.getEnd());
        evaluate.setRaterName(username);
        mission.setAccepts(mission.getAccepts() + 1);

        System.out.println(missionName);
        missionName = missionName.split("_")[1];
        List<EvaluatePicture> evaluatePictures =
                evaluatePictureService.selectEvaluatePictureByRaterAndMission("r", missionName);
        System.out.println("size: " + evaluatePictures.size());
        for (int i = 0; i < evaluatePictures.size(); i++){
            EvaluatePicture evaluatePicture = evaluatePictures.get(i);
            evaluatePicture.setRatername(username);
            System.out.println(username);
            evaluatePictureService.updateEvaluatePicture(evaluatePicture);
        }
        return (evaluateService.addEvaluate(evaluate) && missionService.updateById(mission));
    }

    @RequestMapping(value = "/evaluate/{missionName}/{order}/{state}", method = POST)
    @ResponseBody
    public boolean updateEvaluate(@PathVariable("username") String username, @PathVariable("missionName") String missionName,
                                  @PathVariable("order") String order, @PathVariable("state") String state){
        String[] ns = missionName.split("_");
        Accept accept = acceptService.getAcceptByMissionNameAndWorkerName(ns[1], ns[0]);
        int o = Integer.parseInt(order);
        WorkerPicture workerPicture = workerPictureService.selectWorkerPictureByAccept(accept).get(o);
        int sta = Integer.parseInt(state);
        workerPicture.setState(sta);

        String mName = missionName.split("_")[1];
        EvaluatePicture evaluatePicture =
                evaluatePictureService.selectEvaluatePictureByRaterAndMission(username, mName).get(o);
        evaluatePicture.setIsRight(sta);
        evaluatePictureService.updateEvaluatePicture(evaluatePicture);

        Evaluate evaluate = evaluateService.selectEvaluateByRaterAndMission(username, missionName);
        return (workerPictureService.updateWorkerPictureByName(workerPicture) &&
                evaluateService.updateEvaluate(evaluate));
    }

    @RequestMapping(value = "/finish/evaluate/{missionName}", method = POST)
    @ResponseBody
    public boolean finishEvaluate(@PathVariable("username") String username, @PathVariable("missionName") String missionName){
        Evaluate evaluate = evaluateService.selectEvaluateByRaterAndMission(username, missionName);
        System.out.println(evaluate.getState());
        if(evaluate.getState() == 1)
            return false;
        evaluate.setState(1);
        String mName = evaluate.getMissionName().split("_")[1];
        List<EvaluatePicture> evaluatePictures =
                evaluatePictureService.selectEvaluatePictureByRaterAndMission(username, mName);

        String[] ns = evaluate.getMissionName().split("_");
        Accept accept = acceptService.getAcceptByMissionNameAndWorkerName(ns[1], ns[0]);
        accept.setCheckFlag(1);
        Mission mission = missionService.getMissionByName(accept.getMissionName());
        Release release = releaseService.selectReleaseByMissionName(mission.getName());
        int ps = mission.getPoints();

        int waPoints = 0;
        int raPoints = 0;
        System.out.println(evaluatePictures.size());
        for(int i = 0; i < evaluatePictures.size(); i++){
            EvaluatePicture evaluatePicture = evaluatePictures.get(i);
            if(evaluatePicture.getIsRight() == 1)
                waPoints++;
        }
        System.out.println(waPoints);
        waPoints = (int) (((waPoints * 1.00) / evaluatePictures.size()) * ps);
        raPoints = waPoints / 2;

        System.out.println(waPoints);
        System.out.println(raPoints);
        Worker worker = workerService.getWorkerByName(accept.getWorkerName());
        worker.setPoints(worker.getPoints() + waPoints);

        Rater rater = raterService.getRaterByName(username);
        rater.setPoints(rater.getPoints() + raPoints);

        Announcer announcer = announcerService.getAnnouncerByName(release.getAnnouncerName());
        announcer.setPoints(announcer.getPoints() - waPoints - raPoints);

        WeeklyUtil weeklyUtil = new WeeklyUtil();
        weeklyUtil.updateWeekly(6, weeklyLoginService);

        return (evaluateService.updateEvaluate(evaluate) && acceptService.updateAccept(accept) &&
                missionService.updateById(mission) && raterService.updateById(rater) &&
                workerService.updateById(worker) && announcerService.updateById(announcer));
    }

    /**
     * 返回节分派排名列表
     * @return
     */
    public List<Map.Entry<String, Integer>> rankings() {

        List<Rater> raters = this.raterService.allRater();
        HashMap<String, Integer> map = new HashMap<>();
        for (Rater r : raters) {
            map.put(r.getUsername(), r.getPoints());
        }

        List<Map.Entry<String, Integer>> entryList = new ArrayList<>(map.entrySet());
        Collections.sort(entryList, (o1, o2) -> o2.getValue() - o1.getValue());

        return entryList;
    }

    public int getRanking(String raterName) {
        List<Map.Entry<String, Integer>> list = rankings();
        for (int i = 0; i < list.size(); i++) {
            if (raterName.equals(list.get(i).getKey())) {
                return i + 1;
            }
        }
        return 0;
    }
}
