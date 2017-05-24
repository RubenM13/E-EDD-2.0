package es.ucm.fdi.edd.ui.views;

import java.io.File;

import org.eclipse.core.resources.IFile;

import com.abstratt.content.IContentProviderRegistry.IProviderDescription;

public interface IGraphvizView extends IToolBarAction{

	public void updateGraphContent(File file);

	public void updateGraphContent(IFile iFile);

	public IProviderDescription getContentProviderDescription();
	
	public IFile getSelectedFile();
}
