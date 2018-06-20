package cts.phase3.controller;

import cts.phase3.controller.utils.DateCompare;
import cts.phase3.controller.utils.UploadUtil;
import cts.phase3.persistence.model.*;
import cts.phase3.service.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@Controller
@RequestMapping("/{username}")
public class SystemController {

    @Resource
    private AnnouncerService announcerService;

    @Resource
    private ReleaseService releaseService;

    @Resource
    private WorkerService workerService;

    @Resource
    private MissionService missionService;

    @Resource
    private AcceptService acceptService;

    @Resource
    private RaterService raterService;

    @Resource
    private EvaluateService evaluateService;

    @Resource
    private EvaluatePictureService evaluatePictureService;

    @RequestMapping("/worker/overDue")
    @ResponseBody
    public String getOverDue(@PathVariable("username") String username){
        StringBuilder sb = new StringBuilder();
        List<String> list = this.workerService.getAllMissions(username);
        for (int i = 0; i < list.size(); i++) {
            String name = list.get(i);
            Mission m = this.missionService.getMissionByName(name);
            String end = m.getEnd();
            UploadUtil uploadUtil = new UploadUtil();

            Accept accept = this.workerService.findByMissionNameAndWorkerName(username, name);

            // 如果过期且没有被查看过
            if (uploadUtil.isDue(end) && accept.getCheckFlag()==0) {
                sb.append(name);
                accept.setCheckFlag(1);
                acceptService.updateAccept(accept);
                if (i == list.size())
                    break;
                sb.append("_");
            }

        }
        return sb.toString();
    }

    /**
     * 返回还有据ddl还有两天以内的任务
     * @param username
     * @return
     */
    @RequestMapping("/worker/dayToDDL2")
    @ResponseBody
    public String dayToDDL2(@PathVariable("username") String username) {
        StringBuilder sb = new StringBuilder();
        List<String> list = this.workerService.getAllMissions(username);
        for (int i = 0; i < list.size(); i++) {
            String name = list.get(i);
            Mission m = this.missionService.getMissionByName(name);
            String end = m.getEnd();
            UploadUtil uploadUtil = new UploadUtil();
            // 如果两天内到期
            if (!uploadUtil.isDue(end) && uploadUtil.TowDayDDL(end)) {
                sb.append(name);
                if (i == list.size())
                    break;
                sb.append("_");
            }

        }
        return sb.toString();
    }

    @RequestMapping("/worker/evaluatedInfo")
    @ResponseBody
    public String getWorkerMissionEvaluated(@PathVariable("username") String username){
        String ret = "";
        return ret;
    }

    @RequestMapping(value = "/rater/evaluatedInfo", method = POST)
    @ResponseBody
    public String getRaterEvaluated(@PathVariable("username") String usernmae){
        String ret = "";
        Rater rater = raterService.getRaterByName(usernmae);
        List<Evaluate> evaluates = evaluateService.getAllEvaluateByRater(usernmae);
        for(int i = 0; i < evaluates.size(); i++){
            Evaluate evaluate = evaluates.get(i);
            if(evaluate.getState() == 1){
                ret = ret + evaluate.getMissionName();
                ret += "_";
                evaluate.setState(2);
                evaluateService.updateEvaluate(evaluate);
            }
        }
        return ret;
    }

    @RequestMapping(value = "/announcer/announcedInfo", method = POST)
    @ResponseBody
    public String getAnnouncedInfo(@PathVariable("username") String username){
        Announcer announcer = announcerService.getAnnouncerByName(username);
        List<Release> releases = releaseService.getAllReleaseByAnnouncer(username);
        List<Mission> missions = new ArrayList<>();
        for(int i = 0; i < releases.size(); i++){
            String missionName = releases.get(i).getMissionName();
            Mission mission = missionService.getMissionByName(missionName);
            missions.add(mission);
        }
        return "";
    }

    @RequestMapping(value = "/announcer/releasedMessage", method = POST)
    @ResponseBody
    public String releasedMessage(@PathVariable("username") String username){
        Announcer announcer = announcerService.getAnnouncerByName(username);

        return "";
    }

    @RequestMapping(value = "/worker/finishedMessage", method = POST)
    @ResponseBody
    public String finishedMessage(@PathVariable("username") String username){
        Worker worker = workerService.getWorkerByName(username);
        List<Accept> accepts = acceptService.getAllAccept(worker.getUsername());
        String ret = "";
        for(int i = 0; i < accepts.size(); i++){
            Accept accept = new Accept();
            if(accept.getCheckFlag() == 1){
                accept.setCheckFlag(2);
                ret += accept.getMissionName();
                ret += "_";
            }
        }
        ret = ret.substring(0, ret.length() - 1);
        return ret;
    }

    @RequestMapping(value = "/rater/evaluatedMessage", method = POST)
    @ResponseBody
    public String evaluatedMessage(@PathVariable("username") String username){
        return "";
    }

    /**
     * 评分员的系统消息
     * 返回过期的任务且没有被查看的任务
     * @param username
     * @return
     */
    @RequestMapping(value = "/rater/overDue", method = GET)
    @ResponseBody
    public String getRaterOverDue(@PathVariable("username") String username){
        StringBuilder sb = new StringBuilder();
        List<Mission> list = new ArrayList<>();
        List<Evaluate> evaluates = this.evaluateService.getAllEvaluateByRater(username);
        for (Evaluate e : evaluates) {
            Mission m = this.missionService.getMissionByName(e.getMissionName());
            list.add(m);
        }
        for (int i = 0; i < list.size(); i++) {
            Mission m = list.get(i);
            UploadUtil uploadUtil = new UploadUtil();

            Evaluate evaluate = this.evaluateService.selectEvaluateByRaterAndMission(username, m.getName());


            // 如果过期且没有被查看过
            if (uploadUtil.isDue(m.getEnd()) && evaluate.getCheckFlag()==0) {
                sb.append(m.getName());
                evaluate.setCheckFlag(1);
                this.evaluateService.updateEvaluate(evaluate);
                if (i == list.size()) break;
                sb.append("_");
            }

        }
        return sb.toString();
    }


    /**
     * 评分员的系统消息
     * 返回还有据ddl还有两天以内的任务
     * @param username
     * @return
     */
    @RequestMapping(value = "/rater/dayToDDL2", method = GET)
    @ResponseBody
    public String raterDayToDDL2(@PathVariable("username") String username) {
        StringBuilder sb = new StringBuilder();
        List<Mission> list = new ArrayList<>();
        List<Evaluate> evaluates = this.evaluateService.getAllEvaluateByRater(username);
        for (Evaluate e : evaluates) {
            Mission m = this.missionService.getMissionByName(e.getMissionName());
            list.add(m);
        }

        for (int i = 0; i < list.size(); i++) {
            Mission m = list.get(i);
            String end = m.getEnd();
            UploadUtil uploadUtil = new UploadUtil();
            // 如果两天内到期
            if (!uploadUtil.isDue(end) && uploadUtil.TowDayDDL(m.getEnd())) {
                sb.append(m.getName());
                if (i == list.size()) break;
                sb.append("_");
            }

        }
        return sb.toString();
    }


    /**
     * 发布者的系统消息
     * 返回还有据ddl还有两天以内的任务
     * @param username
     * @return
     */
    @RequestMapping(value = "/announcer/dayToDDL2", method = GET)
    @ResponseBody
    public String announcerDayToDDL2(@PathVariable("username") String username) {
        StringBuilder sb = new StringBuilder();
        List<Mission> list = this.getMissionsByAnnouncer(username);

        for (int i = 0; i < list.size(); i++) {
            Mission m = list.get(i);
            String end = m.getEnd();
            UploadUtil uploadUtil = new UploadUtil();

            // 如果两天内到期
            if (!uploadUtil.isDue(m.getEnd()) && uploadUtil.TowDayDDL(end)) {
                sb.append(m.getName());
                if (i == list.size()) break;
                sb.append("_");
            }

        }
        return sb.toString();
    }


    /**
     * 发布者的系统消息
     * 返回过期的任务且没有被查看的任务
     * @param username
     * @return
     */
    @RequestMapping(value = "/announcer/overDue", method = GET)
    @ResponseBody
    public String getAnnouncerOverDue(@PathVariable("username") String username){
        StringBuilder sb = new StringBuilder();
        List<Mission> list = this.getMissionsByAnnouncer(username);

        for (int i = 0; i < list.size(); i++) {
            Mission m = list.get(i);
            UploadUtil uploadUtil = new UploadUtil();

            Release release = this.releaseService.selectReleaseByMissionName(m.getName());

            // 如果过期且没有被查看过
            if (uploadUtil.isDue(m.getEnd()) && release.getCheckFlag()==0) {
                sb.append(m.getName());
                release.setCheckFlag(1);
                this.releaseService.updateRelease(release);
                if (i == list.size()) break;
                sb.append("_");
            }

        }
        return sb.toString();
    }

    public List<Mission> getMissionsByAnnouncer(String username) {
        List<Mission> list = new ArrayList<>();
        List<Release> releases = this.releaseService.getAllReleaseByAnnouncer(username);
        for (Release e : releases) {
            Mission m = this.missionService.getMissionByName(e.getMissionName());
            list.add(m);
        }
        return list;
    }
}
