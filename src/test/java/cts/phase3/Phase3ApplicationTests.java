package cts.phase3;

import cts.phase3.dataenum.Type;
import cts.phase3.persistence.dao.*;
import cts.phase3.persistence.model.*;
import cts.phase3.service.WorkerService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;


import java.io.*;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
@MapperScan("cts.phase3.persistence.dao")
public class Phase3ApplicationTests {

    @Autowired
    private UserDao userDao;

    @Autowired
    private WorkerDao workerDao;

    @Autowired
    private AnnouncerDao announcerDao;

    @Autowired
    private MissionDao missionDao;

    @Autowired
    private MissionPictureDao missionPictureDao;

    @Autowired
    private WorkerPictureDao workerPictureDao;

    @Autowired
    private ReleaseDao releaseDao;

    @Autowired
    private AcceptDao acceptDao;

    @Autowired
    private WorkerService workerService;

    @Test
    @Transactional
    public void test1() {
        Worker worker = new Worker();
        worker.setUsername("test1");
        worker.setPassword("ccna");
        worker.setSex(1);
        worker.setPoints(123);
        worker.setEmail("123@qq.com");
        worker.setArea("NanJing");
        worker.setMissions(null);
        workerDao.insert(worker);

        Worker worker1 = new Worker();
        worker1.setUsername("test2");
        worker1.setPassword("ccna");
        worker1.setSex(1);
        worker1.setPoints(123);
        worker1.setEmail("123@qq.com");
        worker1.setArea("NanJing");
        worker1.setMissions(null);
        workerDao.insert(worker1);

        Worker w1 = workerService.allWorker().get(0);
        Worker w2 = workerService.allWorker().get(1);
        System.out.println(w1.getUsername());
        System.out.println(w2.getUsername());
        Assert.assertEquals("test2", w2.getUsername());
    }

    @Test
    @Transactional
    public void test2() {
        Announcer announcer = new Announcer();
        announcer.setUsername("announcer1");
        announcer.setPassword("ccna");
        announcer.setSex(1);
        announcer.setPoints(123);
        announcer.setEmail("123@qq.com");
        announcer.setArea("NanJing");
        announcer.setMissions(null);
        announcerDao.insert(announcer);
        Announcer a = this.announcerDao.findByName("announcer1");
        a.setUsername("123456789");
        announcerDao.updateById(a);
        Announcer b = this.announcerDao.findById(a.getId());
        Assert.assertEquals("123456789", b.getUsername());
    }

    @Test
    @Transactional
    public void test3() {
        Mission mission = new Mission();
        mission.setType("风景");
        mission.setName("mission1");
        mission.setPoints(100);
        mission.setNeeds(20);
        mission.setStart("20180825");
        mission.setEnd("20180926");
        mission.setDescription("This is a test!");

        missionDao.insert(mission);
        Mission m = this.missionDao.findByName("mission1");

        Assert.assertEquals(mission.getName(), m.getName());
    }

    @Test
    @Transactional
    public void test4() throws IOException {

        MissionPicture missionPicture = new MissionPicture();
        String path = "F://test.jpg";
        FileInputStream fin = new FileInputStream(new File(path));
        //可能溢出,简单起见就不考虑太多,如果太大就要另外想办法，比如一次传入固定长度byte[]
        byte[] bytes  = new byte[fin.available()];
        //将文件内容写入字节数组，提供测试的case
        fin.read(bytes);
        fin.close();

        missionPicture.setName("dsakl");
        missionPicture.setMissionName("picture1");
        missionPicture.setPicture(bytes);

        missionPictureDao.insert(missionPicture);

        List<MissionPicture> mp = missionPictureDao.findByMissionName("picture1");

        FileOutputStream os = new FileOutputStream("G://1.jpg");
        os.write(mp.get(0).getPicture());
        os.close();

        Assert.assertEquals(missionPicture.getMissionName(), mp.get(0).getMissionName());
    }

    @Test
    @Transactional
    public void test5() throws IOException {

        WorkerPicture workerPicture = new WorkerPicture();
        String path = "F://test.jpg";
        FileInputStream fin = new FileInputStream(new File(path));
        //可能溢出,简单起见就不考虑太多,如果太大就要另外想办法，比如一次传入固定长度byte[]
        byte[] bytes  = new byte[fin.available()];
        //将文件内容写入字节数组，提供测试的case
        fin.read(bytes);

        fin.close();

        workerPicture.setName("dd");
        workerPicture.setWorkername("worker1");
        workerPicture.setMissionName("mission1");
        workerPicture.setPicture(bytes);

        workerPictureDao.insert(workerPicture);

        List<WorkerPicture> wp = workerPictureDao.findByMissionName("mission1");

        FileOutputStream os = new FileOutputStream("G://2.jpg");
        os.write(wp.get(0).getPicture());
        os.close();

        Assert.assertEquals(workerPicture.getMissionName(), wp.get(0).getMissionName());
    }

    @Test
    @Transactional
    public void test6() throws IOException {

        WorkerPicture workerPicture = new WorkerPicture();
        String path = "F://test.jpg";
        FileInputStream fin = new FileInputStream(new File(path));
        //可能溢出,简单起见就不考虑太多,如果太大就要另外想办法，比如一次传入固定长度byte[]
        byte[] bytes  = new byte[fin.available()];
        //将文件内容写入字节数组，提供测试的case
        fin.read(bytes);
        fin.close();

        workerPicture.setName("fsdl");
        workerPicture.setWorkername("worker1");
        workerPicture.setMissionName("mission1");
        workerPicture.setPicture(bytes);

        workerPictureDao.insert(workerPicture);


        List<WorkerPicture> wp0 = workerPictureDao.findByMissionName("mission1");

        WorkerPicture w = wp0.get(0);
        // 更新任务名字
        w.setMissionName("mission2");
        workerPictureDao.update(w);

        List<WorkerPicture> wp1 = workerPictureDao.findByMissionName("mission2");

        FileOutputStream os = new FileOutputStream("G://3.jpg");
        os.write(wp1.get(0).getPicture());
        os.close();

        Assert.assertEquals("mission2", wp1.get(0).getMissionName());
    }


    @Test
    @Transactional
    public void test7() throws IOException {

        WorkerPicture workerPicture = new WorkerPicture();
        String path = "F://test.jpg";
        FileInputStream fin = new FileInputStream(new File(path));
        //可能溢出,简单起见就不考虑太多,如果太大就要另外想办法，比如一次传入固定长度byte[]
        byte[] bytes  = new byte[fin.available()];
        //将文件内容写入字节数组，提供测试的case
        fin.read(bytes);
        fin.close();

        workerPicture.setName("sf");
        workerPicture.setWorkername("worker1");
        workerPicture.setMissionName("mission1");
        workerPicture.setPicture(bytes);

        workerPictureDao.insert(workerPicture);


        List<WorkerPicture> wp0 = workerPictureDao.findByMissionName("mission1");

        WorkerPicture w = wp0.get(0);
        // 更新任务名字
        w.setMissionName("mission2");
        workerPictureDao.update(w);

        List<WorkerPicture> wp1 = workerPictureDao.findByWorkerNameAndMissionName("worker1","mission2");

        FileOutputStream os = new FileOutputStream("G://4.jpg");
        os.write(wp1.get(0).getPicture());
        os.close();

        Assert.assertEquals("mission2", wp1.get(0).getMissionName());
    }

    @Test
    @Transactional
    public void test8() {
        Release release = new Release();
        release.setMissionName("mission4");
        release.setAnnouncerName("announcer4");
        release.setStart("20180701");
        release.setEnd("20190101");

        this.releaseDao.insert(release);

        List<Release> releaseList = this.releaseDao.
                findByAnnouncerNameAndMissionName("announcer4", "mission4");
        Release r = releaseList.get(0);

        Assert.assertEquals("mission4", r.getMissionName());
    }

    @Test
    @Transactional
    public void test9() {
        Accept release = new Accept();
        release.setMissionName("mission4");
        release.setWorkerName("worker4");
        release.setStart("20180701");
        release.setEnd("20190101");
        release.setCheckFlag(0);
        release.setIsFinished(0);
        this.acceptDao.insert(release);

        Accept accept = this.acceptDao.
                findByMissionNameAndWorkerName("worker4", "mission4");


        Assert.assertEquals("mission4", accept.getMissionName());
    }
}
