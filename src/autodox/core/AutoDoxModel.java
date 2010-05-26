package autodox.core;

import java.util.List;

public class AutoDoxModel {

	public boolean generateDox(AutoDoxFile autoDoxFile) {
		if(!autoDoxFile.isTest()) return false;
		
		List<String> allTests = autoDoxFile.tests();
		return false;
	}

}
