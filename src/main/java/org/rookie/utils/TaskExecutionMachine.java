package org.rookie.utils;

import com.google.common.util.concurrent.ThreadFactoryBuilder;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

public class TaskExecutionMachine {

    private final AtomicInteger taskCount = new AtomicInteger(0);

    // 重试次数
    private static final int RETRY_NUMBER = 3;

    // 任务执行失败概率
    private static final int EXECUTION_FAILURE_RATE = 30;

    private enum Status {INIT, RUNNING, FAILURE, SUCCESS}

    public static void main(String[] args) {
        TaskExecutionMachine machine = new TaskExecutionMachine();
        machine.execute();
    }

    public void execute() {
        int totalNumberOfTasks = 10000;
        int numberOfPerBatch = 100;
        int totalThreadNumber = 10;

        // 准备需要执行的任务
        List<Task> tasks = prepareTask(totalNumberOfTasks);

        // 创建线程工厂
        ThreadFactoryBuilder builder = new ThreadFactoryBuilder();
        builder.setNameFormat("execute-task-%d");
        ThreadFactory threadFactory = builder.build();
        CountDownLatch latch = new CountDownLatch(totalThreadNumber);
        for (int i = 0; i < totalThreadNumber; i++) {
            threadFactory.newThread(() -> {
                while (true) {
                    List<Task> subTasks = tasks.stream()
                            .filter(task -> Objects.equals(Status.INIT, task.status.get()))
                            .limit(numberOfPerBatch)
                            .collect(Collectors.toList());
                    if (subTasks.isEmpty()) {
                        break;
                    }
                    // 打乱序列，避免多线程同时执行一个任务
                    Collections.shuffle(subTasks);
                    for (Task task : subTasks) {
                        if (task.status.compareAndSet(Status.INIT, Status.RUNNING)) {
                            executeTask(task);
                        }
                    }
                }
                System.out.println(Thread.currentThread() + " finished.");
                latch.countDown();
            }).start();
        }
        try {
            latch.await();
            System.out.printf("All thread finished. Success: %d, Fail: %d", taskCount.get(),
                    totalNumberOfTasks - taskCount.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private List<Task> prepareTask(int count) {
        List<Task> tasks = new ArrayList<>(count);
        for (int i = 0; i < count; i++) {
            tasks.add(new Task(i));
        }
        // todo: 如果需要重试失败的任务，则需要将状态为FAILURE、RUNNING改为INIT。
        return tasks;
    }

    public void executeTask(Task task) {
        int tryCount = -1;
        do {
            tryCount++;
            try {
                // 模拟任务执行
                Thread.sleep(task.speedTime);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            // 模拟任务失败概率
            if ((int) (Math.random() * 100) >= EXECUTION_FAILURE_RATE) {
                task.status.set(Status.SUCCESS);
                System.out.println("Task " + task.taskId + " finished.");
                taskCount.addAndGet(1);
                return;
            }
        } while (tryCount < RETRY_NUMBER);
        task.status.set(Status.FAILURE);
    }

    private static class Task {
        public int taskId;
        public int speedTime;
        public AtomicReference<Status> status;

        public Task(int taskId) {
            this.taskId = taskId;
            this.speedTime = (int) (Math.random() * 10);
            this.status = new AtomicReference<>(Status.INIT);
        }
    }
}
