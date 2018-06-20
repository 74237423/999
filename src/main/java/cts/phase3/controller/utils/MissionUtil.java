package cts.phase3.controller.utils;

import cts.phase3.persistence.model.Mission;
import cts.phase3.persistence.model.MissionPicture;
import cts.phase3.service.MissionPictureService;

import java.util.Base64;
import java.util.List;

public class MissionUtil {

    public String missionsToStr(List<Mission> missions, MissionPictureService missionPictureService){
        String result = "";
        for(int i = 0; i < missions.size(); i++){
            Mission mission = missions.get(i);
            String start = mission.getStart();
            start = start.substring(0, 4) + "-" + start.substring(4, 6) + "-" + start.substring(6);
            String end = mission.getEnd();
            end = end.substring(0, 4) + "-" + end.substring(4, 6) + "-" + end.substring(6);

            String missionName = "";
            if(mission.getName().contains("_")){
                missionName = mission.getName().split("_")[1];
            }
            else
                missionName = mission.getName();

            MissionPicture missionPicture = missionPictureService.selectMissionPicturesByMission(missionName).get(0);
            byte[] bytes = missionPicture.getPicture();
            Base64.Encoder encoder = Base64.getEncoder();
            String str = encoder.encodeToString(bytes);
            result += "{\"missionName\":\"" + mission.getName() + "\", \"start\":\"" + start + "\", \"end\":\"" + end
                    + "\", \"points\":\"" + mission.getPoints() + "\", \"needs\":\"" + mission.getNeeds()
                    + "\", \"description\":\"" + mission.getDescription() + "\", \"type\":\"" + mission.getType()
                    + "\", \"way\":\"" + mission.getWay() + "\", \"difficultyClass\":\"" + mission.getDifficultyClass()
                    + "\", \"accepts\":\"" + mission.getAccepts() + "\", \"picture\":\"" + str + "\"}";
            if(i != missions.size() - 1)
                result += "_";
        }
        return result;
    }

}
