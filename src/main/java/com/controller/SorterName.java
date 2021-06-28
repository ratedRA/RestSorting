package com.controller;

import org.springframework.stereotype.Component;

@Component
public interface SorterName {
    void sort(int[] arr);
    int[] getList();
}
