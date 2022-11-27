import java.util.*;

public class Main {


    public static void main(String[] args) {

        Map<Integer, Integer> frequency = new TreeMap<>();
        final String[] texts = new String[1000];

        new Thread(() -> {
            synchronized (frequency) {
            for (int i = 0; i < texts.length; i++) {
                    texts[i] = generateRoute("RLRFR", 100);
                    int count = texts[i].length() - texts[i].replace("R", "").length();

                    if (frequency.containsKey(count)) {
                        frequency.put(count, frequency.get(count) + 1);
                    } else {
                        frequency.put(count, 1);
                    }
                }
            }
        }).start();

        new Thread(() -> {
            synchronized (frequency) {
                System.out.print("Самое большое значение: ");
                System.out.print(getKeyMap(frequency, Collections.max(frequency.values())));
                System.out.println(" (встретилось " + Collections.max(frequency.values()) + " раз)");
                System.out.println("Другие размеры:");
                frequency.forEach((key, value) -> System.out.println(" - " + key + " (" + value + " раз)"));
            }
        }).start();
    }


    public static String generateRoute(String letters, int length) {
        Random random = new Random();
        StringBuilder route = new StringBuilder();
        for (int i = 0; i < length; i++) {
            route.append(letters.charAt(random.nextInt(letters.length())));
        }
        return route.toString();
    }

    public static int getKeyMap(Map<Integer, Integer> map, Integer value) {
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            if (entry.getValue().equals(value)) {
                return entry.getKey();
            }
        }
        return 0;
    }

}