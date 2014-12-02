package se.lina.model;

public interface TimeEventPublisher {

	void publishStopTime();
	
	void register(TimeObserver timeObserver);
	
}
