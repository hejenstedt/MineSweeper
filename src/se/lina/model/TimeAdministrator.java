package se.lina.model;

import java.util.ArrayList;

public class TimeAdministrator implements TimeEventPublisher {
	ArrayList<TimeObserver> observers;
	private long stop;
	private long time;
	private String timeAsString;
	private long start;

	public TimeAdministrator() {
		observers = new ArrayList<>();
	}

	public void startTimer() {
		start = System.currentTimeMillis();
	}

	public void stopTime() {
		stop = System.currentTimeMillis();
		calculateTime();
	}

	private void calculateTime() {

		time = stop - start;
		long rest = time % 60000;
		long min = (time - rest) / 60000;
		long sec = Math.round(rest / 1000);
		timeAsString = min + " min " + sec + " sec";
		publishStopTime();
	}

	@Override
	public void publishStopTime() {
		observers.forEach(t -> t.showEndTime(timeAsString));
	}

	@Override
	public void register(TimeObserver timeObserver) {
		observers.add(timeObserver);
	}

	public void resetValues() {
		stop=0;
		time=0;
		timeAsString="";
		start=0;		
	}

}
