package autodox.core;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.eclipse.jdt.core.IAnnotation;
import org.eclipse.jdt.core.IMethod;
import org.eclipse.jdt.core.IType;
import org.eclipse.jdt.core.JavaModelException;

public class AutoDoxFile {

	private final IType file;

	public AutoDoxFile(IType file) {
		this.file = file;

	}

	public boolean isTest() {

		try {
			IMethod[] allMethods = file.getMethods();
			for (IMethod iMethod : allMethods) {

				if (iMethod.getAnnotation("Test").exists())
					return true;
			}
		} catch (JavaModelException e) {
			e.printStackTrace();
		}

		return false;
	}

	public List<String> tests() {
		ArrayList<String> methodNames = new ArrayList<String>();
		try {
			IMethod[] methods = file.getMethods();
			for (IMethod iMethod : methods) {

				IAnnotation testAnnotation = iMethod.getAnnotation("Test");
				if (testAnnotation != null && testAnnotation.exists()) {
					methodNames.add(iMethod.getElementName());
				}
			}

			return methodNames;
		} catch (JavaModelException e) {
			e.printStackTrace();
		}
		return Collections.emptyList();
	}
}