package cts.phase3.controller.utils;

import cts.phase3.persistence.model.Weekly;
import cts.phase3.service.WeeklyLoginService;
import javax.annotation.Resource;

public class WeeklyUtil {

    public Weekly getWeekly(int id, WeeklyLoginService weeklyLoginService){
        return weeklyLoginService.findById(id);
    }

    public Weekly updateByDis(int dis, Weekly weekly){
        if(dis == 1){
            weekly.setMon(weekly.getTue());
            weekly.setTue(weekly.getWed());
            weekly.setWed(weekly.getThu());
            weekly.setThu(weekly.getFri());
            weekly.setFri(weekly.getSat());
            weekly.setSat(weekly.getSun());
            weekly.setSun(1);
        }
        else if(dis == 2){
            weekly.setMon(weekly.getWed());
            weekly.setTue(weekly.getThu());
            weekly.setWed(weekly.getFri());
            weekly.setThu(weekly.getSat());
            weekly.setFri(weekly.getFri());
            weekly.setSat(0);
            weekly.setSun(1);
        }
        else if(dis == 3){
            weekly.setMon(weekly.getThu());
            weekly.setTue(weekly.getFri());
            weekly.setWed(weekly.getSat());
            weekly.setThu(weekly.getSun());
            weekly.setFri(0);
            weekly.setSat(0);
            weekly.setSun(1);
        }
        else if(dis == 4){
            weekly.setMon(weekly.getFri());
            weekly.setTue(weekly.getSat());
            weekly.setWed(weekly.getSun());
            weekly.setThu(0);
            weekly.setFri(0);
            weekly.setSat(0);
            weekly.setSun(1);
        }
        else if(dis == 5){
            weekly.setMon(weekly.getSat());
            weekly.setTue(weekly.getSun());
            weekly.setWed(0);
            weekly.setThu(0);
            weekly.setFri(0);
            weekly.setSat(0);
            weekly.setSun(1);
        }
        else if(dis == 6){
            weekly.setMon(weekly.getSun());
            weekly.setTue(0);
            weekly.setWed(0);
            weekly.setThu(0);
            weekly.setFri(0);
            weekly.setSat(0);
            weekly.setSun(1);
        }
        else {
            weekly.setMon(0);
            weekly.setTue(0);
            weekly.setWed(0);
            weekly.setThu(0);
            weekly.setFri(0);
            weekly.setSat(0);
            weekly.setSun(1);
        }
        return weekly;
    }

    public void updateWeekly(int id, WeeklyLoginService weeklyLoginService){
        Weekly weekly = getWeekly(id, weeklyLoginService);
        DateCompare dateCompare = new DateCompare();
        String currentDateStr = dateCompare.getDate();
        int currentDate = Integer.parseInt(currentDateStr);
        String lastLoginStr = weekly.getLastStr();
        int lastLogin = Integer.parseInt(lastLoginStr);

        if(currentDateStr.equals(lastLoginStr)){
            weekly.setSun(weekly.getSun() + 1);
        }
        else {
            String currentYMStr = currentDateStr.substring(0, 6);
            String lastYMStr = lastLoginStr.substring(0, 6);
            int currentYM = Integer.parseInt(currentYMStr);
            int lastYM = Integer.parseInt(lastYMStr);
            if(currentYM - lastYM == 0){
                int dis = currentDate - lastLogin;
                weekly = updateByDis(dis, weekly);
            }
            else if(currentYM - lastYM == 1){
                int dis = (31 - (lastLogin % 100)) + (currentDate % 100);
                weekly = updateByDis(dis, weekly);
            }
            else
                weekly = updateByDis(7, weekly);
            weekly.setLastStr(currentDateStr);
        }
        weeklyLoginService.updateWeeklyLogin(weekly);
    }

}
