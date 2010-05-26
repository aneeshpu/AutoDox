package autodox.views;

import org.eclipse.jdt.core.IMethod;
import org.eclipse.jdt.core.IType;
import org.eclipse.jdt.core.JavaModelException;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.ui.ISelectionListener;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.part.ViewPart;

import autodox.core.AutoDoxFile;
import autodox.core.AutoDoxModel;

/**
 * This sample class demonstrates how to plug-in a new workbench view. The view
 * shows data obtained from the model. The sample creates a dummy model on the
 * fly, but a real implementation would connect to the model available either in
 * this or another plug-in (e.g. the workspace). The view is connected to the
 * model using a content provider.
 * <p>
 * The view uses a label provider to define how model objects should be
 * presented in the view. Each view can present the same model objects using
 * different labels and icons, if needed. Alternatively, a single label provider
 * can be shared between views in order to ensure that objects of the same type
 * are presented in the same way everywhere.
 * <p>
 */

public class AutoDoxView extends ViewPart implements ISelectionListener {

	/**
	 * The ID of the view as specified by the extension.
	 */
	public static final String ID = "autodox.views.AutoDoxView";
	private AutoDoxModel autoDoxModel = new AutoDoxModel();
	private Label labelView;

	/*
	 * The content provider class is responsible for providing objects to the
	 * view. It can wrap existing objects in adapters or simply return objects
	 * as-is. These objects may be sensitive to the current input of the view,
	 * or ignore it and always show the same content (like Task List, for
	 * example).
	 */

	/**
	 * The constructor.
	 */
	public AutoDoxView() {
	}

	/**
	 * This is a callback that will allow us to create the viewer and initialize
	 * it.
	 */
	public void createPartControl(Composite parent) {
		labelView = new Label(parent, 0);
		getSite().getPage().addSelectionListener(this);
	}

	/**
	 * Passing the focus request to the viewer's control.
	 */
	public void setFocus() {
	}

	@Override
	public void selectionChanged(IWorkbenchPart workbenchPart, ISelection selection) {
		labelView.setText("Inside autodox view");
		if (!(selection instanceof IStructuredSelection))
			return;

		IStructuredSelection structuredSelection = (IStructuredSelection) selection;
		Object firstElement = structuredSelection.getFirstElement();
		if (!(firstElement instanceof IType))
			return;
		
		IType selectedType = (IType)firstElement;
		try {
			IMethod[] methods = selectedType.getMethods();
			labelView.setText("Success Dasa Success");
			
			for (IMethod iMethod : methods) {
				labelView.setText(labelView.getText() + "\n" + iMethod.toString());
			}
		} catch (JavaModelException e) {
			e.printStackTrace();
		}
		
		autoDoxModel.generateDox(new AutoDoxFile((IType)firstElement));
	}
}