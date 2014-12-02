package se.lina.model;

public interface GameEventPublisher {

	public void publishUpdatedBoard();

	public void register(GameObserver gameObserver);
	
}
