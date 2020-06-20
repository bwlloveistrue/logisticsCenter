package com.ln;


import com.threadPool.ThreadWork;

public class LnTimer implements ThreadWork {
    private LN ln = null;

    public LnTimer() {
        this.ln = new LN();
    }

    public void doThreadWork() {
        this.ln.OutLicensecode();
    }
}
