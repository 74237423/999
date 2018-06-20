package cts.phase3.controller.utils;

import cts.phase3.persistence.model.MissionPicture;
import cts.phase3.service.MissionPictureService;
import org.apache.commons.io.FileUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.stream.Stream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

public class UploadUtil {

    public String uploadFile(MultipartFile multipartFile, String missionName, String announcerName,
                             MissionPictureService missionPictureService){
        try {
            File file = new File(multipartFile.getOriginalFilename());
            FileUtils.writeByteArrayToFile(file, multipartFile.getBytes());

            OutputStream outputStream = new FileOutputStream(file);
            BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(outputStream);
            bufferedOutputStream.write(multipartFile.getBytes());

            uploadPictures(file, missionName, announcerName, missionPictureService);

            outputStream.close();
            bufferedOutputStream.close();
            file.delete();
            return "success";
        } catch (IOException e) {
            e.printStackTrace();
            return "error";
        }
    }

    public boolean uploadPictures(File mFile, String missionName, String announcerName,
                                  MissionPictureService missionPictureService) throws IOException {

        final InputStream[] inputStream = {null};
        //final OutputStream[] outputStream = {null};
        //final BufferedOutputStream[] bufferedOutputStream = {null};
        ZipFile zipFile = new ZipFile(mFile);
        Stream<? extends ZipEntry> entryStream = zipFile.stream();
        final int[] i = {0};
        entryStream.forEach(entry ->{
            try {
                inputStream[0] = zipFile.getInputStream(entry);
                byte[] bytes = inputStream[0].readAllBytes();

                String fileName = entry.getName().replaceAll("\\\\", "/");
                if(fileName.endsWith("/")){
                }
                else {
                    i[0] = i[0] + 1;
                    int index = fileName.indexOf('/');
                    fileName = fileName.substring(index + 1);
                    MissionPicture missionPicture = new MissionPicture();
                    missionPicture.setMissionName(missionName);
                    missionPicture.setPicture(bytes);
                    missionPicture.setName(missionName + i[0]);
                    missionPictureService.addMissionPicture(missionPicture);
                    /*int index = fileName.indexOf('/');
                    fileName = fileName.substring(index + 1);
                    System.out.println(fileName);
                    File file = new File(fileName);
                    outputStream[0] = new FileOutputStream(file);
                    bufferedOutputStream[0] = new BufferedOutputStream(outputStream[0]);
                    bufferedOutputStream[0].write(bytes);*/
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        inputStream[0].close();
        //outputStream[0].close();
        //bufferedOutputStream[0].close();
        zipFile.close();
        entryStream.close();
        return true;
    }


    /**
     * 判断任务是否过期
     * @param dataDate
     * @return
     */
    public boolean isDue(String dataDate) {
        if (dataDate.compareTo(getDate()) < 0) {
            return true; // 过期
        }
        return false;
    }

    public String getDate() {
        SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");
        return df.format(new Date());
    }


    // 判断两天后是否到期
    public boolean TowDayDDL(String ddl) {
        String today = getDate();
        Calendar calendar = Calendar.getInstance();
        Date date = null;

        SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");
        try {
            date = (Date)df.parse(today);
        } catch (ParseException e) {
            e.getStackTrace();
        }

        calendar.setTime(date);
        calendar.add(Calendar.DAY_OF_MONTH, 2);

        date = calendar.getTime();

        String d = df.format(date);

        // 两天内到期
        if (d.compareTo(ddl) >= 0) {
            return true;
        }

        return false;
    }

}
