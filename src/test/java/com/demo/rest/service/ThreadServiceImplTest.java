package com.demo.rest.service;

import org.junit.Before;
import org.junit.Test;

import java.lang.management.ManagementFactory;

import static org.junit.Assert.*;

public class ThreadServiceImplTest {
    private ThreadServiceImpl threadService;

    @Before
    public void setUp() throws Exception {
        threadService = new ThreadServiceImpl();
    }

    @Test
    public void createDeadLock() throws Exception {
        final String output = threadService.createDeadLock();
        final long[] deadlockedThreads = ManagementFactory.getThreadMXBean().findDeadlockedThreads();
        assertEquals(deadlockedThreads.length, 2);
        assertTrue(output.contains("Successfully DeadLocked"));
        final String secondOutput = threadService.createDeadLock();
        assertTrue(secondOutput.contains("System Already Has DeadLock"));
    }

    @Test
    public void getDeadLockInfo() throws Exception {
        final String deadLockInfo = threadService.getDeadLockInfo();
        assertTrue(deadLockInfo.equals("No DeadLock Detected"));
    }

    @Test
    public void getDeadLockInfoWithActualDeadLock() throws Exception {
        threadService.createDeadLock();
        final String deadLockInfo = threadService.getDeadLockInfo();
        assertTrue(deadLockInfo.contains("DeadLock Detected."));
    }

}