package fr.ircf.humanity.job;

import java.util.HashMap;

public class JobFactory {

	public static Class<?>[] JOBS = new Class[] {
		Farmers.class,
		Astronomers.class,
		Physicists.class,
		Biologists.class,
		Army.class,
		Merchants.class
	};
	
	public static HashMap<Class<?>, Job> getJobs(){
		HashMap<Class<?>, Job> jobs = new HashMap<Class<?>, Job>();
		for (Class<?> jobClass : JOBS){
			try {
				jobs.put(jobClass, (Job)jobClass.newInstance());
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return jobs;
	}
	
	public static HashMap<Class<?>, JobMenuItem> getJobMenuItems(){
		HashMap<Class<?>, JobMenuItem> jobMenuItems = new HashMap<Class<?>, JobMenuItem>();
		for (Class<?> jobClass : JOBS){
			try {
				jobMenuItems.put(jobClass, new JobMenuItem());
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return jobMenuItems;
	}
}
