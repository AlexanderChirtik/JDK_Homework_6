package org.example;

import java.util.*;

public class Main {
    static Random random = new Random();

    public static void main(String[] args) {
        int numberOfGames = 1000;
        System.out.println(leaveUserChoice(numberOfGames));
        System.out.println(changeUserChoice(numberOfGames));
    }

    /**
     * Получение номера двери, которую предлагает открыть ведущий
     * @param userSelectedDoor
     * @param doors
     * @return
     */
    private static int getHostsDoor(int userSelectedDoor, boolean[] doors) {
        int hostsDoor;
        if (doors[userSelectedDoor]){
            while (true){
                hostsDoor = random.nextInt(3);
                if(hostsDoor!=userSelectedDoor) return hostsDoor;
            }
        }else{
            while (true){
                for (int i = 0; i < 3; i++) {
                    if(doors[i]) return i;
                }
            }
        }
    }

    /**
     * Получение 3 дверей с призом за одной из них
     * @return
     */
    private static boolean[] getDoors() {
        boolean[] doors = new boolean[]{false, false, false};
        doors[random.nextInt(3)] = true;
        return doors;
    }

    /**
     * Игра, при которой игрок не меняет свой выбор
     * @param numberOfGames
     * @return
     */
    private static String leaveUserChoice(int numberOfGames) {
        double countWin = 0;
        Map<Integer, Boolean> result = new HashMap<>();
        for (int i = 0; i < numberOfGames; i++) {
            boolean[] doors = getDoors();
            int userSelectedDoor = random.nextInt(3);
            if (doors[userSelectedDoor]) countWin++;
            result.put(i, doors[userSelectedDoor]);
        }
        String gameReport =  ("Игрок в " + numberOfGames + " играх не менял свой выбор и вот их результат: процент выйгрыша - " + (countWin / numberOfGames * 100));
        return  gameReport;
    }

    /**
     * Игра, при которой игрок меняет свой выбор
     * @param numberOfGames
     * @return
     */
    private static String changeUserChoice(int numberOfGames) {
        double countWin = 0;
        Map<Integer, Boolean> result = new HashMap<>();
        for (int i = 0; i < numberOfGames; i++) {
            boolean[] doors = getDoors();
            int userSelectedDoor = random.nextInt(3);
            userSelectedDoor = getHostsDoor(userSelectedDoor, doors);
            if (doors[userSelectedDoor]) countWin++;
            result.put(i, doors[userSelectedDoor]);
        }
        String gameReport = ("Игрок в " + numberOfGames + " играх уже менял свой выбор и вот их результат: процент выйгрыша - " + (countWin / numberOfGames * 100));
        return gameReport;
    }
}