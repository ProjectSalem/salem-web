package com.project.salem.web.controller;

import java.io.File;
import java.io.IOException;

import org.quartz.InterruptableJob;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.UnableToInterruptJobException;

public class MyJob implements InterruptableJob  {
   
	private Thread thread;

    @SuppressWarnings("unused")
	public void execute(JobExecutionContext context) throws JobExecutionException {
    	thread = Thread.currentThread();
    	try {
			 Process process = new ProcessBuilder(new String[] {"/bin/bash", "-c", "sudo python servo.py"})
                    .redirectErrorStream(true)
                    .directory(new File("/home/pi"))
                    .start();
		}
		catch (IOException e){
			e.printStackTrace();
		}
    }

	@Override
	public void interrupt() throws UnableToInterruptJobException {
		thread.interrupt();
        try {
            thread.join();
        } catch (InterruptedException e) {
            throw new UnableToInterruptJobException(e);
        } finally {
            // ... do cleanup
        }
	}
}