package com.duo.service.impl;

import com.duo.entity.Message;
import com.duo.entity.Video;
import com.duo.mapper.MessageMapper;
import com.duo.mapper.UserMapper;
import com.duo.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service("messageservice")
@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT)
public class MessageServiceImpl implements MessageService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private MessageMapper messageMapper;

    public List<Message> findMessagesByVid(Video video) {
        List<Message> messages = messageMapper.selectByVid(video.getVideoid());
        for (int i = 0; i < messages.size(); i++) {
            messages.get(i).setUser(userMapper.selectByPrimaryKey(messages.get(i).getUid()).get(0));
        }
        return messages;
    }

    public String insertMessage(Message message) {
        Message messages = new Message();
        messages.setMessagecomment(message.getMessagecomment());
        messages.setUid(message.getUid());
        messages.setVideoid(message.getVideoid());
        messages.setMessagetime(new Date());
        messageMapper.insert(messages);
        return "success";
    }

    public String deleteMessage(Message message) {
        int result = messageMapper.deleteByPrimaryKey(message.getMessageid());
        if (result != 0) {
            return "success";
        } else {
            return "error";
        }
    }
}
