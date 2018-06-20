package cts.phase3.controller;

import cts.phase3.controller.utils.DateCompare;
import cts.phase3.dataenum.Type;
import cts.phase3.persistence.model.Accept;
import cts.phase3.persistence.model.Mission;
import cts.phase3.persistence.model.Worker;
import cts.phase3.service.AcceptService;
import cts.phase3.service.MissionService;
import cts.phase3.service.WorkerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


import javax.annotation.Resource;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @program: phase3
 * @author: Lijie
 * @description: 对工人工作质量进行评估, 标注的准确性、效率、偏好
 * @create: 2018-05-31 19:05
 **/
@Controller
@RequestMapping("/test")
public class WorkQualityController {

/*
    @Resource
    private WorkerService workerService;
*/

    @Resource
    private MissionService missionService;

    @Resource
    private AcceptService acceptService;

    private DateCompare compare;



    /**
     * 工人的准确率
     */
    public void accuracy() {

    }


    /**
     * 工人的效率
     */
    public void efficiency() {

    }


    /**
     * 对每个工人的答案进行整合
     * 使用聚类算法
     */
    public void integrationAnswer() {

    }

    /**
     *  全部工人的总积分排名
     * @param
     * @return 一个排序好的 List<Map.Entry<String, Integer>>
     */
    @RequestMapping("/allWorker0")
    @ResponseBody
    public List<Map.Entry<String, Integer>> rankings(WorkerService workerService) {

        List<Worker> workerList = workerService.allWorker();
        HashMap<String, Integer> map = new HashMap<>();
        for (Worker w : workerList) {
            map.put(w.getUsername(), w.getPoints());
        }

        List<Map.Entry<String, Integer>> entryList = new ArrayList<>(map.entrySet());
        Collections.sort(entryList, (o1, o2) -> o2.getValue() - o1.getValue());

        return entryList;
    }


    @RequestMapping("/allWorker")
    @ResponseBody
    public List<Worker> test(WorkerService workerService) {
        Worker worker = new Worker();
        worker.setUsername("test1");
        worker.setPassword("ccna");
        worker.setSex(1);
        worker.setPoints(123);
        worker.setEmail("123@qq.com");
        worker.setArea("NanJing");
        worker.setMissions(null);
        workerService.addWorker(worker);

        Worker worker1 = new Worker();
        worker1.setUsername("test2");
        worker1.setPassword("ccna");
        worker1.setSex(1);
        worker1.setPoints(123);
        worker1.setEmail("123@qq.com");
        worker1.setArea("NanJing");
        worker1.setMissions(null);
        workerService.addWorker(worker1);
        return workerService.allWorker();
    }

    /**
     * 单个工人的积分排名，按名字查找
     * @param workername
     * @return
     */
    public int ranking(String workername, WorkerService workerService) {
        List<Map.Entry<String, Integer>> entryList = rankings(workerService);
        for (int i = 0; i < entryList.size(); i++) {
            if (entryList.get(i).getKey().equals(workername)) {
                return i+1;
            }
        }

        // 如果工人不存在， 返回0
        return 0;
    }

    /**
     * 工人对某种任务类型的偏好
     * @param workername
     * @return 工人最常标注任务的两种类型
     */
    public ArrayList<String> preferncer(String workername, WorkerService workerService) {
        ArrayList<String> list = new ArrayList<>();
        List<String> missions = workerService.getAllMissions(workername);

        String lastType = null;
        String lastMissionName = "";
        boolean isNull = true;
        if (missions.size() >= 1) {
            lastMissionName = missions.get(missions.size() - 1); // 该工人接受的最后一个任务
            Mission mission = this.missionService.getMissionByName(lastMissionName);
            lastType = mission.getType();
            isNull = false;
        }

        for (String s : missions) {
            Mission mission = this.missionService.getMissionByName(s);
            list.add(mission.getType());
        }

        HashMap<String, Integer> map = new HashMap<>();
        for (String t : list) {
            if (map.containsKey(t)) {
                int temp = map.get(t);
                temp += 1;
                map.put(t, temp);
            }
            else {
                map.put(t, 1);
            }
        }


        List<Map.Entry<String, Integer>> info = new ArrayList<Map.Entry<String, Integer>>(map.entrySet());

        Collections.sort(info, (o1, o2) -> o2.getValue() - o1.getValue());

        // 返回该用户最喜欢的三种任务类型
        ArrayList<String> result = new ArrayList<>();
        if (info.size() > 0) {
            result.add(info.get(0).getKey());
        }
        if (info.size() > 1) {
            result.add(info.get(1).getKey());
        }

        if (!isNull) result.add(lastType);
        return result;
    }

    /**
     * 根据用户偏好推荐距离ddl至少还有3天的任务
     * @param workername
     * @return
     */
    public List<Mission> recommend(String workername, WorkerService workerService) {
        compare = new DateCompare();
        List<Mission> list0 = this.acceptService.getMissionsByWorkerName(workername);
        List<Mission> result = new ArrayList<>();
        String localDate = getDate();
        // 如果该工人之前任务记录为零，则推荐所有不过期的任务
        if (list0 == null && list0.size() == 0) {
            List<Mission> allMission = this.missionService.allMission();
            String newDate = compare.DatePlus(localDate, 2);
            for (Mission m : allMission) {
                //查找据ddl至少还有2天的任务
                if (m.getEnd().compareTo(newDate) >= 0) {
                     result.add(m);
                }


                if (result.size() >= 10) {
                    return result;
                }
            }
        }


        ArrayList<String> types = preferncer(workername, workerService);

        // 查找至少还有3天到ddl的任务
        List<Mission> missions = new ArrayList<>();
        // 该工人已经接的所有任务
        List<String> allMission = workerService.getAllMissions(workername);

        for (String t : types) {
            List<Mission> missionList = this.missionService.getMissionByType(t);
            missions.addAll(missionList);
        }

        for (Mission m : missions) {
            String endDate = m.getEnd();
            String mname = m.getName();
            String newDate = compare.DatePlus(localDate, 3);
            // 如果任务ddl大于3天且该工人从来没有做过该任务
            if (newDate.compareTo(endDate) <= 0 && !allMission.contains(mname)) {
                result.add(m);
            }
        }

        return result;
    }


    public String getDate() {
        SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");
        return df.format(new Date());
    }

    public String getDate2() {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        return df.format(new Date());
    }

}
