/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aggregate;

/**
 *
 * @author code
 */
import java.util.Random;

import java.util.Scanner;

public class ClusterInstance {

    public ClusterInstance() {

    }

    public static int[] ClusterInstanceShuffling(int[] arr, int n) {

        int[] a = new int[n];

        int[] ind = new int[n];

        for (int i = 0; i < n; i++) {
            ind[i] = 0;
        }

        int index;

        Random rand = new Random();

        for (int i = 0; i < n; i++) {

            do {

                index = rand.nextInt(n);

            } while (ind[index] != 0);

            ind[index] = 1;

            a[i] = arr[index];

        }

        return a;

    }

}
