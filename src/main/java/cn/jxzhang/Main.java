package cn.jxzhang;

import java.util.concurrent.TimeUnit;

public class Main {

    /**
     * 按顺序打印线程
     * Q: 为什么在 Junit 中此段代码不能按顺序打印线程名？
     * A：com.intellij.rt.junit.JUnitStarter 在主线程执行完毕后会调用 {@code System.exit()} 退出虚拟机。
     */
    public static void main(String[] args) {
        Thread currentThread = Thread.currentThread();

        for (int i = 0; i < 10; i++) {
            DominoRunner runner = new DominoRunner(currentThread);
            Thread t = new Thread(runner, String.valueOf(i));
            // 线程默认为 非 daemon 线程，即 JVM 会等待该线程退出后再退出
            // 如果 设置为 true，则不等所有线程打印完毕 JVM 就会退出
            // t.setDaemon(true);
            t.start();
            currentThread = t;
        }

        System.out.println(Thread.currentThread().getName() + " terminate");
    }

    public static class DominoRunner implements Runnable {
        private final Thread thread;

        public DominoRunner(Thread thread) {
            this.thread = thread;
        }

        @Override
        public void run() {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println(Thread.currentThread().getName() + " terminate");
        }
    }
}
