package com.duo.service;

import com.duo.entity.Message;
import com.duo.entity.Video;

import java.util.List;

public interface MessageService {

    List<Message> findMessagesByVid(Video video);

    String insertMessage(Message message);

    String deleteMessage(Message message);
}
