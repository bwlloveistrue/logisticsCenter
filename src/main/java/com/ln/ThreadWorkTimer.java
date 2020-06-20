package com.ln;

import com.threadPool.ThreadWork;

public class ThreadWorkTimer extends Thread {
    public boolean start = true;
    private long m_timer = 5000L;
    private ThreadWork work = null;

    ThreadWorkTimer(long checktime, ThreadWork work) {
        this.m_timer = checktime * 1000L;
        this.work = work;
    }

    public void run() {
        while(this.start) {
            try {
                sleep(this.m_timer);
                this.work.doThreadWork();
            } catch (Exception var2) {
                ;
            }
        }

    }
}
