package dev.sosasosa.basements.chat.files;

public class EvilEntity {

    private static String name;
    private static Integer kill_count;

    public static void construct() {
        name = "";
        kill_count = 0;
    }

    public void set(String name, Integer kill_count) {
        this.name = name;
        this.kill_count = kill_count;

    }

    public static String getName() {
        return name;
    }

    public static Integer getKillCount() {
        return kill_count;
    }
}
