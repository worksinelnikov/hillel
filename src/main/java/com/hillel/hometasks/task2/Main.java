package com.hillel.hometasks.task2;

public class Main {
    public static void main(String[] args) {
        FilePropertyService filePropertyService = new FilePropertyServiceImpl();
        String path = "e:/license.txt";

        int count1 = filePropertyService.CountMaxStringLengthWay1(path);
        System.out.println(count1);


        int count2 = filePropertyService.CountMaxStringLengthWay2(path);
        System.out.println(count2);

        System.out.println("Cold Start:");
        getTimeForWay(filePropertyService, path);
        System.out.println("Warm Start:");
        getTimeForWay(filePropertyService, path);
    }

    private static void getTimeForWay(FilePropertyService filePropertyService, String path) {
        Long timeStart = System.nanoTime();
        for (int i = 0; i < 1000; i++) {
            filePropertyService.CountMaxStringLengthWay1(path);
        }
        Long timeFinish = System.nanoTime();
        System.out.println(String.format("Way 1 (lambda): %,12d", (timeFinish - timeStart)));
        timeStart = System.nanoTime();
        for (int i = 0; i < 1000; i++) {
            filePropertyService.CountMaxStringLengthWay2(path);
        }
        timeFinish = System.nanoTime();
        System.out.println(String.format("Way 2 (simple): %,12d", (timeFinish - timeStart)));
    }
}
