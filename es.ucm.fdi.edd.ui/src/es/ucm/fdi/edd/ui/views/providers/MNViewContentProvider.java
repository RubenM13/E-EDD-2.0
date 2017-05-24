package es.ucm.fdi.edd.ui.views.providers;

import java.io.IOException;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IResourceChangeEvent;
import org.eclipse.core.resources.IResourceChangeListener;
import org.eclipse.core.resources.IResourceDelta;
import org.eclipse.core.resources.IResourceDeltaVisitor;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.emf.common.util.BasicEList;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryContentProvider;

import es.ucm.fdi.emf.model.ed2.Leaf;
import es.ucm.fdi.emf.model.ed2.Node;
import es.ucm.fdi.emf.model.ed2.TreeElement;

/**
 *  Provides the possibility to display a <code>Node</code> including its child objects.
 */
public class MNViewContentProvider extends AdapterFactoryContentProvider implements IResourceChangeListener, IResourceDeltaVisitor {
	
	private static ResourceSetImpl resourceSet = new ResourceSetImpl();

	/**
     * Creates a new instance of {@link MNViewContentProvider}.
     */
    public MNViewContentProvider() {
		super(MNComposedAdapterFactory.getAdapterFactory());
		ResourcesPlugin.getWorkspace().addResourceChangeListener(this, IResourceChangeEvent.POST_CHANGE);
	}

	/* (non-Javadoc)
	 * @see org.eclipse.emf.edit.ui.provider.AdapterFactoryContentProvider#getChildren(java.lang.Object)
	 */
	public Object[] getChildren(Object parentElement) {
		if (parentElement instanceof IFile) {
			String path = ((IFile) parentElement).getFullPath().toString();
			URI uri = URI.createPlatformResourceURI(path, true);
			parentElement = resourceSet.getResource(uri, true);
		} else if (parentElement instanceof Node) {
			Node node = (Node) parentElement;
			EList<Node> nodes = node.getNodes();
			EList<Leaf> leaves = node.getLeaves();
			EList<TreeElement> list = new BasicEList<TreeElement>();
			list.addAll(nodes);
			list.addAll(leaves);
			return list.toArray();
		}
		
		return super.getChildren(parentElement);
	}

	/* (non-Javadoc)
	 * @see org.eclipse.emf.edit.ui.provider.AdapterFactoryContentProvider#getParent(java.lang.Object)
	 */
	public Object getParent(Object element) {
		if (element instanceof IFile) {
			return ((IResource) element).getParent();
		} else if (element instanceof Node) {
			//return ((Node) element).
		} else if (element instanceof Leaf) {
			//return ((Leaf) element).
		}
		
		return super.getParent(element);
	}

	/* (non-Javadoc)
	 * @see org.eclipse.emf.edit.ui.provider.AdapterFactoryContentProvider#hasChildren(java.lang.Object)
	 */
	public boolean hasChildren(Object element) {
		if (element instanceof IFile) {
			return true;
		} else if (element instanceof Node) {
			Node node = (Node) element;
			EList<Node> nodes = node.getNodes();
			EList<Leaf> leaves = node.getLeaves();
			EList<TreeElement> result = new BasicEList<TreeElement>();
			result.addAll(nodes);
			result.addAll(leaves);
			return result.size() > 0;
		} else if (element instanceof Leaf) {
//			Leaf leaf = (Leaf) element;
			return false;
		}
		
		return super.hasChildren(element);
	}

	/* (non-Javadoc)
	 * @see org.eclipse.emf.edit.ui.provider.AdapterFactoryContentProvider#getElements(java.lang.Object)
	 */
	public Object[] getElements(Object inputElement) {
		return getChildren(inputElement);
	}

	/* (non-Javadoc)
	 * @see org.eclipse.emf.edit.ui.provider.AdapterFactoryContentProvider#dispose()
	 */
	public void dispose() {
		super.dispose();
		ResourcesPlugin.getWorkspace().removeResourceChangeListener(this);
	}

	/* (non-Javadoc)
	 * @see org.eclipse.core.resources.IResourceChangeListener#resourceChanged(org.eclipse.core.resources.IResourceChangeEvent)
	 */
	public void resourceChanged(IResourceChangeEvent event) {
		try {
			IResourceDelta delta = event.getDelta();
			delta.accept(this);
		} catch (CoreException e) {
			System.out.println("Resource Changed Fail - " + e.toString());
		}
	}

	/* (non-Javadoc)
	 * @see org.eclipse.core.resources.IResourceDeltaVisitor#visit(org.eclipse.core.resources.IResourceDelta)
	 */
	public boolean visit(IResourceDelta delta) throws CoreException {
		IResource changedResource = delta.getResource();
		if (changedResource.getType() == IResource.FILE	&& changedResource.getFileExtension().equals("ecore")) {
			try {
				String path = ((IFile) changedResource).getFullPath().toString();
				URI uri = URI.createPlatformResourceURI(path, true);
				Resource res = resourceSet.getResource(uri, true);
				res.unload();
				res.load(resourceSet.getLoadOptions());
			} catch (IOException ie) {
				System.out.println("Error reloading resource - " + ie.toString());
			}
			return false;
		}
		return true;
	}
}