package es.ucm.fdi.gmf.edd.diagram.part;

import java.util.Collections;

import org.eclipse.gef.palette.PaletteContainer;
import org.eclipse.gef.palette.PaletteGroup;
import org.eclipse.gef.palette.PaletteRoot;
import org.eclipse.gef.palette.ToolEntry;
import org.eclipse.gmf.tooling.runtime.part.DefaultLinkToolEntry;
import org.eclipse.gmf.tooling.runtime.part.DefaultNodeToolEntry;

import es.ucm.fdi.gmf.edd.diagram.providers.EddElementTypes;

/**
 * @generated
 */
public class EddPaletteFactory {

	/**
	 * @generated
	 */
	public void fillPalette(PaletteRoot paletteRoot) {
		paletteRoot.add(createEdd1Group());
	}

	/**
	 * Creates "edd" palette tool group
	 * @generated
	 */
	private PaletteContainer createEdd1Group() {
		PaletteGroup paletteContainer = new PaletteGroup(
				Messages.Edd1Group_title);
		paletteContainer.setId("createEdd1Group"); //$NON-NLS-1$
		paletteContainer.add(createBlock1CreationTool());
		paletteContainer.add(createTreeElement2CreationTool());
		paletteContainer.add(createTreeElementLinks3CreationTool());
		return paletteContainer;
	}

	/**
	 * @generated
	 */
	private ToolEntry createBlock1CreationTool() {
		DefaultNodeToolEntry entry = new DefaultNodeToolEntry(
				Messages.Block1CreationTool_title,
				Messages.Block1CreationTool_desc,
				Collections.singletonList(EddElementTypes.Block_3001));
		entry.setId("createBlock1CreationTool"); //$NON-NLS-1$
		entry.setSmallIcon(EddElementTypes
				.getImageDescriptor(EddElementTypes.Block_3001));
		entry.setLargeIcon(entry.getSmallIcon());
		return entry;
	}

	/**
	 * @generated
	 */
	private ToolEntry createTreeElement2CreationTool() {
		DefaultNodeToolEntry entry = new DefaultNodeToolEntry(
				Messages.TreeElement2CreationTool_title,
				Messages.TreeElement2CreationTool_desc,
				Collections.singletonList(EddElementTypes.TreeElement_3002));
		entry.setId("createTreeElement2CreationTool"); //$NON-NLS-1$
		entry.setSmallIcon(EddElementTypes
				.getImageDescriptor(EddElementTypes.TreeElement_3002));
		entry.setLargeIcon(entry.getSmallIcon());
		return entry;
	}

	/**
	 * @generated
	 */
	private ToolEntry createTreeElementLinks3CreationTool() {
		DefaultLinkToolEntry entry = new DefaultLinkToolEntry(
				Messages.TreeElementLinks3CreationTool_title,
				Messages.TreeElementLinks3CreationTool_desc,
				Collections
						.singletonList(EddElementTypes.TreeElementLinks_4001));
		entry.setId("createTreeElementLinks3CreationTool"); //$NON-NLS-1$
		entry.setSmallIcon(EddDiagramEditorPlugin
				.findImageDescriptor("/es.ucm.fdi.edd.gmf.diagram/icons/obj16/Edge.gif")); //$NON-NLS-1$
		entry.setLargeIcon(EddDiagramEditorPlugin
				.findImageDescriptor("/es.ucm.fdi.edd.gmf.diagram/icons/obj16/Edge.gif")); //$NON-NLS-1$
		return entry;
	}

}
