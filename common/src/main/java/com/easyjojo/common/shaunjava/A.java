package com.easyjojo.common.shaunjava;

import com.easyjojo.common.utils.PrintUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class A {
    private static int a;
    private static final ThreadLocal<String> threadLocal = new ThreadLocal<>();

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        PrintUtils.printGreen("主线程"+Thread.currentThread().getId());


        List<Runnable> runnables = new ArrayList<>();
        for(int i=0;i<10;i++){
            Thread thread1 = new Thread(() -> {
                try {
                    Thread.sleep(4000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                if(a==1)
                    threadLocal.set("VIPA");
                a++;
                PrintUtils.printRed("当前线程ID"+Thread.currentThread().getId()+"，ThreadLocal值："+threadLocal.get());
            });
            runnables.add(thread1);
        }
        System.out.println(threadLocal.get());
        ThreadPoolExecutor pool = new ThreadPoolExecutor(
                2,
                2,
                60,
                java.util.concurrent.TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(8),
                (r, executor) -> PrintUtils.printRed("线程池已满，拒绝执行任务")
                );
        for(int i=0;i<10;i++){
            pool.execute(runnables.get(i));
        }
            pool.shutdown();
    }
}
