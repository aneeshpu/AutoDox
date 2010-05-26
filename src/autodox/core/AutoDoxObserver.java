package autodox.core;

import java.util.List;

public interface AutoDoxObserver {

	void notify(List<String> testNames);
}
