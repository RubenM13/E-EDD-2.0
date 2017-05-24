package es.ucm.fdi.edd.emf.model.edd.diagram.part;

import java.util.Collections;

import org.eclipse.gef.palette.PaletteContainer;
import org.eclipse.gef.palette.PaletteDrawer;
import org.eclipse.gef.palette.PaletteRoot;
import org.eclipse.gef.palette.ToolEntry;
import org.eclipse.gmf.tooling.runtime.part.DefaultLinkToolEntry;
import org.eclipse.gmf.tooling.runtime.part.DefaultNodeToolEntry;

import es.ucm.fdi.edd.emf.model.edd.diagram.providers.EddElementTypes;

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
		PaletteDrawer paletteContainer = new PaletteDrawer(
				Messages.Edd1Group_title);
		paletteContainer.setId("createEdd1Group"); //$NON-NLS-1$
		paletteContainer.add(createTreeElement1CreationTool());
		paletteContainer.add(createNode2CreationTool());
		paletteContainer.add(createLeaf3CreationTool());
		paletteContainer.add(createNodeChildren4CreationTool());
		return paletteContainer;
	}

	/**
	 * @generated
	 */
	private ToolEntry createTreeElement1CreationTool() {
		DefaultNodeToolEntry entry = new DefaultNodeToolEntry(
				Messages.TreeElement1CreationTool_title,
				Messages.TreeElement1CreationTool_desc,
				Collections.singletonList(EddElementTypes.TreeElement_2003));
		entry.setId("createTreeElement1CreationTool"); //$NON-NLS-1$
		entry.setSmallIcon(EddElementTypes
				.getImageDescriptor(EddElementTypes.TreeElement_2003));
		entry.setLargeIcon(entry.getSmallIcon());
		return entry;
	}

	/**
	 * @generated
	 */
	private ToolEntry createNode2CreationTool() {
		DefaultNodeToolEntry entry = new DefaultNodeToolEntry(
				Messages.Node2CreationTool_title,
				Messages.Node2CreationTool_desc,
				Collections.singletonList(EddElementTypes.Node_2001));
		entry.setId("createNode2CreationTool"); //$NON-NLS-1$
		entry.setSmallIcon(EddElementTypes
				.getImageDescriptor(EddElementTypes.Node_2001));
		entry.setLargeIcon(entry.getSmallIcon());
		return entry;
	}

	/**
	 * @generated
	 */
	private ToolEntry createLeaf3CreationTool() {
		DefaultNodeToolEntry entry = new DefaultNodeToolEntry(
				Messages.Leaf3CreationTool_title,
				Messages.Leaf3CreationTool_desc,
				Collections.singletonList(EddElementTypes.Leaf_2002));
		entry.setId("createLeaf3CreationTool"); //$NON-NLS-1$
		entry.setSmallIcon(EddElementTypes
				.getImageDescriptor(EddElementTypes.Leaf_2002));
		entry.setLargeIcon(entry.getSmallIcon());
		return entry;
	}

	/**
	 * @generated
	 */
	private ToolEntry createNodeChildren4CreationTool() {
		ToolEntry entry = new ToolEntry(
				Messages.NodeChildren4CreationTool_title,
				Messages.NodeChildren4CreationTool_desc, null, null) {
		};
		entry.setId("createNodeChildren4CreationTool"); //$NON-NLS-1$
		entry.setSmallIcon(EddDiagramEditorPlugin
				.findImageDescriptor("/es.ucm.fdi.edd.emf.diagram/icons/obj16/Edge.gif")); //$NON-NLS-1$
		entry.setLargeIcon(EddDiagramEditorPlugin
				.findImageDescriptor("/es.ucm.fdi.edd.emf.diagram/icons/obj16/Edge.gif")); //$NON-NLS-1$
		return entry;
	}

}
