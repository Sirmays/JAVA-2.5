import java.util.Arrays;

public class Main {

    public static void main(String[] args) {

        method1();

        method2();


    }


    static void method1() {
        final int size = 10000000;
        final int h = size / 2;
        float[] arr = new float[size];
        Arrays.fill(arr, 1);

        long a = System.currentTimeMillis();
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (float) (arr[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
        }
        System.currentTimeMillis();
        System.out.println("Operation time1: " + (System.currentTimeMillis() - a) + " ms");

    }

    static void method2() {
        final int size = 10000000;
        final int h = size / 2;
        float[] arr = new float[size];
        Arrays.fill(arr, 1);

        long a = System.currentTimeMillis();


        float[] a1 = new float[h];
        float[] a2 = new float[h];
        System.arraycopy(arr, 0, a1, 0, h);
        System.arraycopy(arr, h, a2, 0, h);

        Thread thread1 = new Thread(() -> {
            count(a1);
        });

        Thread thread2 = new Thread(() -> {
            count(a2);
        });

        thread1.start();
        thread2.start();


        System.arraycopy(a1, 0, arr, 0, h);
        System.arraycopy(a2, 0, arr, h, h);


        System.currentTimeMillis();
        System.out.println("Operation time2: " + (System.currentTimeMillis() - a) + " ms");
    }

    public static void count(float[] arr) {
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (float) (arr[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
        }
    }

}
