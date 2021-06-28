package com.model;

import com.controller.SorterName;
import org.springframework.stereotype.Component;

import java.net.SocketImplFactory;
import com.controller.SorterName;
@Component
class QuickSort implements SorterName
{
    private int arr[];
    int partition(int arr[], int low, int high)
    {
        int pivot = arr[high];
        int i = (low-1); // index of smaller element
        for (int j=low; j<high; j++)
        {

            if (arr[j] <= pivot)
            {
                i++;

                // swap arr[i] and arr[j]
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }

        // swap arr[i+1] and arr[high] (or pivot)
        int temp = arr[i+1];
        arr[i+1] = arr[high];
        arr[high] = temp;

        return i+1;
    }



    void sort(int arr[], int low, int high)
    {
        if (low < high)
        {

            int pi = partition(arr, low, high);

            sort(arr, low, pi-1);
            sort(arr, pi+1, high);
        }
    }

    /* A utility function to print array of size n */
    static void printArray(int arr[])
    {
        int n = arr.length;
        for (int i=0; i<n; ++i)
            System.out.print(arr[i]+" ");
        System.out.println();
    }

    @Override
    public int[] getList(){
        return this.arr;
    }
    @Override
    public void sort(int[] arr)
    {
        this.arr = arr;
        int n = arr.length;

        QuickSort ob = new QuickSort();
        ob.sort(arr, 0, n-1);

    }
}

