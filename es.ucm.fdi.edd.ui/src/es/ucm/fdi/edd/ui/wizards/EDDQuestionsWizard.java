package es.ucm.fdi.edd.ui.wizards;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.util.LinkedList;

import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Status;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.wizard.IWizardPage;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.ui.INewWizard;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchWizard;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.ide.IDE;

import es.ucm.fdi.edd.core.graphviz.GraphViz;
import es.ucm.fdi.edd.core.json.model.Edges;
import es.ucm.fdi.edd.core.json.model.JsonDocument;
import es.ucm.fdi.edd.core.json.model.Vertices;
import es.ucm.fdi.edd.core.json.utils.JsonHelper;

public class EDDQuestionsWizard extends Wizard implements INewWizard {

	private static final String JSON_PATH = "C:\\Test2.json";
	private EDDQuestionsWizardPage initialPage;
	private EDDQuestionsWizardPageFinal finalPage;
	private ISelection selection;
	
	private JsonDocument document;
	private LinkedList<Vertices> vertices;

	/**
	 * Constructor for SampleNewWizard.
	 */
	public EDDQuestionsWizard() {
		super();
		setNeedsProgressMonitor(true);
		setForcePreviousAndNextButtons(true);
	}

	/**
	 * Adding the page to the wizard.
	 */
	public void addPages() {
		super.addPages();
		initialPage = new EDDQuestionsWizardPage(selection);
		finalPage = new EDDQuestionsWizardPageFinal(selection);
		addPage(initialPage);
		
	}
	
	@Override
	public IWizardPage getNextPage(IWizardPage page) {
		IWizardPage nextPage = super.getNextPage(page);
		if (nextPage == null && getPageCount() == 1) {
			
			try {
				// FIXME Ejemplo de uso servidor...
				String[] args = new String[] {"ackermann:main([3,4])", "../examples/ackermann/"};
//				EDDJInterface.main(args);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			for (int i=0; i<vertices.size(); i++) {   
				EDDQuestionsWizardQuestionPage dynamic = new EDDQuestionsWizardQuestionPage(selection, i);
				addPage(dynamic);
			}
			addPage(finalPage);
			return getPage("dynamicPage_0");
		}
	   
		return nextPage;
	}

	/**
	 * This method is called when 'Finish' button is pressed in the wizard. We
	 * will create an operation and run it using wizard as execution context.
	 */
	public boolean performFinish() {
		final String containerName = finalPage.getContainerName();
		final String fileName = finalPage.getFileName();
		IRunnableWithProgress op = new IRunnableWithProgress() {
			public void run(IProgressMonitor monitor)
					throws InvocationTargetException {
				try {
					doFinish(containerName, fileName, monitor);
				} catch (CoreException e) {
					throw new InvocationTargetException(e);
				} finally {
					monitor.done();
				}
			}
		};

		try {
			getContainer().run(true, false, op);
		} catch (InterruptedException e) {
			return false;
		} catch (InvocationTargetException e) {
			Throwable realException = e.getTargetException();
			MessageDialog.openError(getShell(), "Error", realException.getMessage());
			return false;
		}
		return true;
	}

	/**
	 * The worker method. It will find the container, create the file if missing
	 * or just replace its contents, and open the editor on the newly created
	 * file.
	 */
	private void doFinish(String containerName, String fileName, IProgressMonitor monitor) throws CoreException {
		// create a sample file
		monitor.beginTask("Creating " + fileName, 2);
		IWorkspaceRoot root = ResourcesPlugin.getWorkspace().getRoot();
		IResource resource = root.findMember(new Path(containerName));
		if (!resource.exists() || !(resource instanceof IContainer)) {
			throwCoreException("Container \"" + containerName + "\" does not exist.");
		}
		IContainer container = (IContainer) resource;
		final IFile file = container.getFile(new Path(fileName));
		try {
			InputStream stream = openContentStream();
			if (file.exists()) {
				file.setContents(stream, true, true, monitor);
			} else {
				file.create(stream, true, monitor);
			}
			stream.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		monitor.worked(1);
		monitor.setTaskName("Opening file for editing...");
		getShell().getDisplay().asyncExec(new Runnable() {
			public void run() {
				IWorkbenchPage page = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage();
				try {
					IDE.openEditor(page, file, true);
				} catch (PartInitException e) {
					e.printStackTrace();
				}
			}
		});
		monitor.worked(1);
	}

	/**
	 * We will initialize file contents with a sample text.
	 */
	private InputStream openContentStream() {
		String contents = "This is the initial file contents for *.mpe file that should be word-sorted in the Preview page of the multi-page editor";
		return new ByteArrayInputStream(contents.getBytes());
	}

	private void throwCoreException(String message) throws CoreException {
		IStatus status = new Status(IStatus.ERROR, "es.ucm.fdi.edd.ui",	IStatus.OK, message, null);
		throw new CoreException(status);
	}

	/**
	 * We will accept the selection in the workbench to see if we can initialize
	 * from it.
	 * 
	 * @see IWorkbenchWizard#init(IWorkbench, IStructuredSelection)
	 */
	public void init(IWorkbench workbench, IStructuredSelection selection) {
		this.selection = selection;
		this.document = readJson();
		vertices = document.getVertices();

		// FIXME Generate dot file...
		buildDOT();
	}

	private JsonDocument readJson() {
		try {
			JsonDocument document = (JsonDocument) JsonHelper.readJsonFromFile(JSON_PATH, JsonDocument.class);
			return document;
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	private void buildDOT() {
		// FIXME Ejemplo de uso DOT ...
		GraphViz gv = new GraphViz();
		gv.addln(gv.start_graph());
		gv.addln("\tgraph [dpi = 400]; ");
		gv.addln("\tratio=\"fill\"; ");
		gv.addln("\tsize=\"8.3,11.7!\"; ");
		gv.addln("\tmargin = 0; ");
//		gv.addln("\tnode [shape=circle]; ");
		gv.addln("\tnode [style=filled]; ");
		gv.addln("\tnode [fillcolor=\"#EEEEEE\"]; ");
		gv.addln("\tnode [color=\"#EEEEEE\"]; ");
		gv.addln("\tedge [color=\"#31CEF0\"]; ");
		for (int i=0; i<vertices.size(); i++) {
			Vertices vertice = vertices.get(i);
			String node = vertice.getNode();
			gv.addln(node + " [label=\"" + node + ". " + vertice.getQuestion() + "\"];");
		}
		LinkedList<Edges> edges = document.getEdges();
		for (Edges edge : edges) {
			gv.addln(edge.getFrom() + " -> "+ edge.getTo() + ";");
//			int from = edge.getFrom();
//			gv.addln(from + " -> "+ edge.getTo() + " [label=\"" + vertices.get(from).getQuestion() + "\"];");
		}
//		gv.addln("rankdir=LR;"); // Para cambiar el sentido 
		gv.addln(gv.end_graph());
		String dotSource = gv.getDotSource();
		System.out.println(dotSource);
		
//		String type = "gif";
//		File out = new File("files/output/images/sample1." + type);
//		gv.writeGraphToFile(gv.getGraph(gv.getDotSource(), type), out);
	}
	
	public LinkedList<Vertices> getVertices() {
		return vertices;
	}
}