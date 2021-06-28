package com.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;

@RestController
public class SortController {

    @Autowired
    private static ApplicationContext context;

    @RequestMapping(value="/sorter/{givenArray}",method = RequestMethod.GET)
    @ResponseBody
    public int[] sorter(
            @PathVariable String[] givenArray,
            @RequestParam("sorterName") String sorterName) {
        SorterName mySorter = context.getBean(sorterName, SorterName.class);
        int[] intArray = new int[givenArray.length];
        for (int i = 0; i < givenArray.length; i++)
            intArray[i] = Integer.parseInt(givenArray[i]);
        mySorter.sort(intArray);
        return mySorter.getList();

    }

    @RequestMapping(value="/hello",method = RequestMethod.GET)
    @ResponseBody
    public String sayHello(){
        return "Hello";
    }

}
