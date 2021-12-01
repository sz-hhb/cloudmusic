package com.duo.service;

import com.duo.entity.Video;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;


public interface VideoService {

    List<Video> getVideoList();

    String insertVideo(String videoName, String videoAuthor, MultipartFile file1, MultipartFile file2) throws IOException;

    String deleteVideo(Integer[] ids);

    Video findVideoById(Video video);

    String deleteVideoById(Integer videoid);

    String updateVideo(Integer videoid, String videoName, String videoAuthor, MultipartFile file1, MultipartFile file2) throws IOException;

    List<Video> randomVideo();

    Video searchVideo(Video video);
}
