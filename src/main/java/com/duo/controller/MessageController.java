package com.duo.controller;


import com.duo.entity.Message;
import com.duo.entity.Video;
import com.duo.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/message")
public class MessageController {

    @Autowired
    private MessageService messageService;

    @RequestMapping(value = "/messages", method = RequestMethod.POST)
    @ResponseBody
    public List<Message> findMessagesByVid(@RequestBody Video video) {
        return messageService.findMessagesByVid(video);
    }

    @RequestMapping(value = "/insertMessage", method = RequestMethod.POST)
    @ResponseBody
    public String insertMessage(@RequestBody Message message) {
        return messageService.insertMessage(message);
    }

    @RequestMapping(value = "/deleteMessage", method = RequestMethod.POST)
    @ResponseBody
    public String deleteMessage(@RequestBody Message message) {
        return messageService.deleteMessage(message);
    }

}
