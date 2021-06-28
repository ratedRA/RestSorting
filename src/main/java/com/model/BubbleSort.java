package com.model;


import com.controller.SorterName;
import org.springframework.stereotype.Component;

@Component
class BubbleSort implements SorterName
{
    private int[] arr;
    void bubbleSort(int arr[])
    {
        int n = arr.length;
        for (int i = 0; i < n-1; i++)
            for (int j = 0; j < n-i-1; j++)
                if (arr[j] > arr[j+1])
                {
                    int temp = arr[j];
                    arr[j] = arr[j+1];
                    arr[j+1] = temp;
                }
    }

    void printArray(int arr[])
    {
        int n = arr.length;
        for (int i=0; i<n; ++i)
            System.out.print(arr[i] + " ");
        System.out.println();
    }
    @Override
    public int[] getList(){
        return this.arr;
    }

    @Override
    public void sort(int[] arr)
    {
        BubbleSort ob = new BubbleSort();
        this.arr = arr;
        ob.bubbleSort(arr);

    }
}


