package com.study.listener.controller;

import com.study.listener.service.IListenerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * ClassName: ListenerController
 * Description: 各种监听器测试
 *
 * @Author: luohx
 * Date: 2022/1/12 上午10:20
 * History:
 * <author>          <time>          <version>          <desc>
 * luohx            修改时间           1.0           各种监听器测试
 */
@RestController
@RequestMapping("/demo")
public class ListenerController {

    @Autowired
    private IListenerService listenerService;

    @RequestMapping("/spring/listener")
    public ResponseEntity<String> springListener() {
        listenerService.springListener();
        return new ResponseEntity<>("", HttpStatus.OK);
    }


}
