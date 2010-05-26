package autodox.core;

import java.util.ArrayList;
import java.util.List;

public class AutoDoxModel {

	private List<AutoDoxObserver> observers = new ArrayList<AutoDoxObserver>();

	public boolean generateDox(AutoDoxFile autoDoxFile) {
		if(!autoDoxFile.isTest()) return false;
		
		List<String> allTests = autoDoxFile.tests();
		notifyObservers(allTests);
		return false;
	}
	
	public void addObserver(AutoDoxObserver autoDoxObserver){
		this.observers .add(autoDoxObserver);
	}

	private void notifyObservers(List<String> allTests) {
		for (AutoDoxObserver observer : observers) {
			observer.notify(allTests);
		}
	}

}
