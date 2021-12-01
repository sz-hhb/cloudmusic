package com.duo.controller;

import com.duo.entity.Video;
import com.duo.service.VideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping(value = "/video")
public class VideoController {

    @Autowired
    private VideoService videoService;

    @RequestMapping(value = "/list", method = RequestMethod.POST)
    @ResponseBody
    public List<Video> getVideoList() {
        return videoService.getVideoList();
    }

    @RequestMapping(value = "/insertVideo", method = RequestMethod.POST)
    @ResponseBody
    public String insertVideo(@RequestParam("videoname") String videoName, @RequestParam("videoauthor") String videoAuthor, @RequestParam("img") MultipartFile file1, @RequestParam("video") MultipartFile file2) throws IOException {
        return videoService.insertVideo(videoName, videoAuthor, file1, file2);
    }

    @RequestMapping(value = "/deletesVideo", method = RequestMethod.POST)
    @ResponseBody
    public String deletesVideo(@RequestBody Integer[] ids) {
        return videoService.deleteVideo(ids);
    }

    @RequestMapping(value = "/findVideo", method = RequestMethod.POST)
    @ResponseBody
    public Video findVideoById(@RequestBody Video video) {
        return videoService.findVideoById(video);
    }

    @RequestMapping(value = "/deleteVideo", method = RequestMethod.POST)
    @ResponseBody
    public String deleteVideoById(@RequestBody Video video) {
        return videoService.deleteVideoById(video.getVideoid());
    }

    @RequestMapping(value = "/updateVideo", method = RequestMethod.POST)
    @ResponseBody
    public String updateVideo(@RequestParam("videoid") Integer videoid, @RequestParam("videoname") String videoName, @RequestParam("videoauthor") String videoAuthor, @RequestParam("img") MultipartFile file1, @RequestParam("video") MultipartFile file2) throws IOException {
        return videoService.updateVideo(videoid, videoName, videoAuthor, file1, file2);
    }

    @RequestMapping(value = "/randomVideo", method = RequestMethod.POST)
    @ResponseBody
    public List<Video> randomVideo() {
        return videoService.randomVideo();
    }

    @RequestMapping(value = "/searchVideo", method = RequestMethod.POST)
    @ResponseBody
    public Video searchVideo(@RequestBody Video video) {
        return videoService.searchVideo(video);
    }
}
