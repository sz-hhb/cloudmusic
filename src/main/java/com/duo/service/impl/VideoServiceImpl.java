package com.duo.service.impl;

import com.duo.entity.Music;
import com.duo.entity.Musictype;
import com.duo.entity.Singer;
import com.duo.entity.Video;
import com.duo.mapper.VideoMapper;
import com.duo.service.VideoService;
import com.duo.util.EncodingTool;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

@Service("videoService")
@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT)
public class VideoServiceImpl implements VideoService {

    @Autowired
    private VideoMapper videoMapper;

    public List<Video> getVideoList() {
        return videoMapper.getVideoList();
    }

    public String insertVideo(String videoName, String videoAuthor, MultipartFile file1, MultipartFile file2) throws IOException {

        Video video = new Video();
        video.setVideoname(EncodingTool.encodeStr(videoName));
        if (videoMapper.selectByName(video) != null) {
            return "error";
        }
        String fimage = "images/" + EncodingTool.encodeStr(file1.getOriginalFilename());
        //创建file类  来确定上传文件的路径
        File file = new File("D:\\musicstatic", fimage);
        //上传文件的功能
        FileUtils.copyInputStreamToFile(file1.getInputStream(), file);

        String fvideo = "video/" + EncodingTool.encodeStr(file2.getOriginalFilename());
        //创建file类  来确定上传文件的路径
        File file3 = new File("D:\\musicstatic", fvideo);
        //上传文件的功能
        FileUtils.copyInputStreamToFile(file2.getInputStream(), file3);


        video.setVideoauthor(EncodingTool.encodeStr(videoAuthor));
        video.setVideotime(new Date());
        video.setVideophoto(fimage);
        video.setVideourl(fvideo);

        videoMapper.insert(video);
        return "success";
    }

    public String deleteVideo(Integer[] ids) {
        for (Integer id : ids) {
            videoMapper.deleteByPrimaryKey(id);
        }
        return "success";
    }

    public Video findVideoById(Video video) {
        return videoMapper.selectByPrimaryKey(video.getVideoid());
    }

    public String deleteVideoById(Integer videoid) {
        videoMapper.deleteByPrimaryKey(videoid);
        return "success";
    }

    public String updateVideo(Integer videoid, String videoName, String videoAuthor, MultipartFile file1, MultipartFile file2) throws IOException {
        Video video = new Video();
        video.setVideoid(videoid);
        video.setVideoname(EncodingTool.encodeStr(videoName));
        if (videoMapper.selectByName(video).getVideoid().equals(videoid)) {
            return "error";
        }
        Video oldVideo = videoMapper.selectByPrimaryKey(videoid);

        File f1 = new File("D:\\musicstatic", oldVideo.getVideophoto());
        if (f1.exists()) {
            f1.delete();
        }
        String fimage = "images/" + EncodingTool.encodeStr(file1.getOriginalFilename());
        File f = new File("D:\\musicstatic", fimage);
        FileUtils.copyInputStreamToFile(file1.getInputStream(), f);
        video.setVideophoto(fimage);

        File f2 = new File("D:\\musicstatic", oldVideo.getVideourl());
        if (f2.exists()) {
            f2.delete();
        }
        String fvideo = "video/" + EncodingTool.encodeStr(file1.getOriginalFilename());
        File fs = new File("D:\\musicstatic", fvideo);
        FileUtils.copyInputStreamToFile(file1.getInputStream(), fs);

        video.setVideourl(fvideo);

        video.setVideoauthor(EncodingTool.encodeStr(videoAuthor));
        video.setVideotime(new Date());

        videoMapper.updateByPrimaryKey(video);
        return "success";

    }

    public List<Video> randomVideo() {
        Random r = new Random();
//        获取所有视频
        List<Video> videos = videoMapper.getVideoList();
//        存放视频的id
        List<Integer> lists = new ArrayList<Integer>();
//        产生的随机数
        List<Integer> index = new ArrayList<Integer>();

        List<Video> video = new ArrayList<Video>();

        for (int i = 0; i < videos.size(); i++) {
            lists.add(videos.get(i).getVideoid());
        }

        for (int i = 0; i < 10; i++) {
//            产生0-lists.size()-1之间的随机数
            int num = r.nextInt(lists.size());
            int num1 = -1;
//            随机数去重，不重复则添加至数组
            if (!index.contains(num)) {
                index.add(num);
            } else {
//                如果这次产生的随机数是重复的，则进入循环
                do {
                    num1 = r.nextInt(lists.size());
//                    判断这次产生的随机数有没有重复
                    if (!index.contains(num1)) {
                        index.add(num1);
                        break;
                    }
                } while (index.contains(num1));
            }
        }
        for (int i = 0; i < index.size(); i++) {
            video.add(videoMapper.selectByPrimaryKey(lists.get(index.get(i))));
        }
        return video;
    }

    public Video searchVideo(Video video) {
        return videoMapper.selectByName(video);
    }

}
