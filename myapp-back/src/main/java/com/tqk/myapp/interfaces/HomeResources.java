package com.tqk.myapp.interfaces;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Quang-Khai TRAN
 * @date 02/04/2025
 */



@RestController
@RequestMapping(path = "/")
public class HomeResources {

    @GetMapping()
    public String hello() {
        return "OK";
    }
}
