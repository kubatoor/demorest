package com.demo.rest.service;

import com.demo.rest.exception.DeadLockCreationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.lang.management.ManagementFactory;
import java.lang.management.ThreadInfo;
import java.lang.management.ThreadMXBean;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;

@Service
public class ThreadServiceImpl implements ThreadService{

    private final Object resource1 = new Object();
    private final Object resource2 = new Object();
    private Logger LOG = LoggerFactory.getLogger(ThreadService.class);

    /**
     * This method creates the deadlock in the system. Before deadlock creation it checks if the deadlock already exists.
     * If the deadlock exists then it return the existing deadlock information including Thread Names, Resource etc.
     * If Deadlock doesnt exist then it creates one and returns the information of the deadlock created
     *
     * @return information of the deadlock created or existing deadlock information
     */

    @Override
    public String createDeadLock() {
        if(deadLockExists()){
            return "System Already Has DeadLock. Here is the Info"+"\n"+getDeadLockInfo();
        }

        Thread t1 = new Thread(() -> {
            String threadName = Thread.currentThread().getName();
            synchronized (resource1) {
                LOG.info(threadName + "Acquired Lock on Resource1");
                sleepForSpecifiedSeconds(3);
                synchronized (resource2) {
                    LOG.info(threadName + "Acquired Lock on Resource2");
                }
            }
        });


        Thread t2 = new Thread(() -> {
            String threadName = Thread.currentThread().getName();
            synchronized (resource2){
                LOG.info(threadName+"Acquired Lock on Resource2");
                sleepForSpecifiedSeconds(3);
                synchronized (resource1){
                    LOG.info(threadName+"Acquired Lock on Resource1");
                }
            }

        });

        t1.start();
        t2.start();
        sleepForSpecifiedSeconds(5);
        if(deadLockExists()){
            return "Successfully DeadLocked"+"\n"+getDeadLockInfo();
        } else{
            throw new DeadLockCreationException("Could not create a deadlock in 10 secs");
        }
    }

    @Override
    public String getDeadLockInfo() {
        StringBuilder stringBuilder = new StringBuilder();
        ThreadMXBean threadMXBean = ManagementFactory.getThreadMXBean();
        long[] threadIds = threadMXBean.findDeadlockedThreads();
        if(threadIds!=null && threadIds.length>0){
            ThreadInfo[] threadInfos = threadMXBean.getThreadInfo(threadIds);
            stringBuilder.append("DeadLock Detected." + threadInfos.length + " threads are currently deadLocked")
                    .append("\n");
            Arrays.stream(threadInfos).forEach(threadInfo -> stringBuilder.append(getThreadInfo(threadInfo)));
            return stringBuilder.toString().trim();
        }

        return "No DeadLock Detected";

    }

    private String getThreadInfo(ThreadInfo threadInfo){
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(threadInfo.getThreadName()+" ")
                .append("is waiting on Lock "+threadInfo.getLockInfo()+" ")
                .append("held by "+threadInfo.getLockOwnerName())
                .append("\n");
        Arrays.stream(threadInfo.getStackTrace()).forEach(stackTraceElement -> stringBuilder.append(stackTraceElement.toString()).append("\n"));
        return stringBuilder.toString();
    }

    private boolean deadLockExists(){
        final long[] deadlockedThreads = ManagementFactory.getThreadMXBean().findDeadlockedThreads();
        return (deadlockedThreads != null && deadlockedThreads.length > 0);
    }

    private void sleepForSpecifiedSeconds(int n) {
        try {
            TimeUnit.SECONDS.sleep(n);
        } catch (InterruptedException e) {
            LOG.warn("Sleep Interrupted",e);
        }
    }
}
