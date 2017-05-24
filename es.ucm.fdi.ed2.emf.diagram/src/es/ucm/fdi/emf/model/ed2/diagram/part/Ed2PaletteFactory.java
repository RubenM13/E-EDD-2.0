package es.ucm.fdi.emf.model.ed2.diagram.part;

import java.util.Collections;

import org.eclipse.gef.palette.PaletteContainer;
import org.eclipse.gef.palette.PaletteDrawer;
import org.eclipse.gef.palette.PaletteRoot;
import org.eclipse.gef.palette.ToolEntry;
import org.eclipse.gmf.tooling.runtime.part.DefaultLinkToolEntry;
import org.eclipse.gmf.tooling.runtime.part.DefaultNodeToolEntry;

import es.ucm.fdi.emf.model.ed2.diagram.providers.Ed2ElementTypes;

/**
 * @generated
 */
public class Ed2PaletteFactory {

	/**
	 * @generated
	 */
	public void fillPalette(PaletteRoot paletteRoot) {
		paletteRoot.add(createEd2nodes1Group());
		paletteRoot.add(createEd2relations2Group());
	}

	/**
	 * Creates "ed2 nodes" palette tool group
	 * @generated
	 */
	private PaletteContainer createEd2nodes1Group() {
		PaletteDrawer paletteContainer = new PaletteDrawer(
				Messages.Ed2nodes1Group_title);
		paletteContainer.setId("createEd2nodes1Group"); //$NON-NLS-1$
		paletteContainer.setDescription(Messages.Ed2nodes1Group_desc);
		paletteContainer.add(createED21CreationTool());
		paletteContainer.add(createNode2CreationTool());
		paletteContainer.add(createLeaf3CreationTool());
		return paletteContainer;
	}

	/**
	 * Creates "ed2 relations" palette tool group
	 * @generated
	 */
	private PaletteContainer createEd2relations2Group() {
		PaletteDrawer paletteContainer = new PaletteDrawer(
				Messages.Ed2relations2Group_title);
		paletteContainer.setId("createEd2relations2Group"); //$NON-NLS-1$
		paletteContainer.setDescription(Messages.Ed2relations2Group_desc);
		paletteContainer.add(createED2TreeElements1CreationTool());
		paletteContainer.add(createNodeNodes2CreationTool());
		paletteContainer.add(createNodeLeaves3CreationTool());
		return paletteContainer;
	}

	/**
	 * @generated
	 */
	private ToolEntry createED21CreationTool() {
		DefaultNodeToolEntry entry = new DefaultNodeToolEntry(
				Messages.ED21CreationTool_title,
				Messages.ED21CreationTool_desc,
				Collections.singletonList(Ed2ElementTypes.ED2_2008));
		entry.setId("createED21CreationTool"); //$NON-NLS-1$
		entry.setSmallIcon(Ed2ElementTypes
				.getImageDescriptor(Ed2ElementTypes.ED2_2008));
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
				Collections.singletonList(Ed2ElementTypes.Node_2006));
		entry.setId("createNode2CreationTool"); //$NON-NLS-1$
		entry.setSmallIcon(Ed2ElementTypes
				.getImageDescriptor(Ed2ElementTypes.Node_2006));
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
				Collections.singletonList(Ed2ElementTypes.Leaf_2007));
		entry.setId("createLeaf3CreationTool"); //$NON-NLS-1$
		entry.setSmallIcon(Ed2ElementTypes
				.getImageDescriptor(Ed2ElementTypes.Leaf_2007));
		entry.setLargeIcon(entry.getSmallIcon());
		return entry;
	}

	/**
	 * @generated
	 */
	private ToolEntry createED2TreeElements1CreationTool() {
		DefaultLinkToolEntry entry = new DefaultLinkToolEntry(
				Messages.ED2TreeElements1CreationTool_title,
				Messages.ED2TreeElements1CreationTool_desc,
				Collections.singletonList(Ed2ElementTypes.ED2TreeElements_4001));
		entry.setId("createED2TreeElements1CreationTool"); //$NON-NLS-1$
		entry.setSmallIcon(Ed2DiagramEditorPlugin
				.findImageDescriptor("/es.ucm.fdi.ed2.emf.diagram/icons/obj16/Edge.gif")); //$NON-NLS-1$
		entry.setLargeIcon(Ed2DiagramEditorPlugin
				.findImageDescriptor("/es.ucm.fdi.ed2.emf.diagram/icons/obj16/Edge.gif")); //$NON-NLS-1$
		return entry;
	}

	/**
	 * @generated
	 */
	private ToolEntry createNodeNodes2CreationTool() {
		DefaultLinkToolEntry entry = new DefaultLinkToolEntry(
				Messages.NodeNodes2CreationTool_title,
				Messages.NodeNodes2CreationTool_desc,
				Collections.singletonList(Ed2ElementTypes.NodeNodes_4002));
		entry.setId("createNodeNodes2CreationTool"); //$NON-NLS-1$
		entry.setSmallIcon(Ed2DiagramEditorPlugin
				.findImageDescriptor("/es.ucm.fdi.ed2.emf.diagram/icons/obj16/Edge1.gif")); //$NON-NLS-1$
		entry.setLargeIcon(Ed2DiagramEditorPlugin
				.findImageDescriptor("/es.ucm.fdi.ed2.emf.diagram/icons/obj16/Edge1.gif")); //$NON-NLS-1$
		return entry;
	}

	/**
	 * @generated
	 */
	private ToolEntry createNodeLeaves3CreationTool() {
		DefaultLinkToolEntry entry = new DefaultLinkToolEntry(
				Messages.NodeLeaves3CreationTool_title,
				Messages.NodeLeaves3CreationTool_desc,
				Collections.singletonList(Ed2ElementTypes.NodeLeaves_4003));
		entry.setId("createNodeLeaves3CreationTool"); //$NON-NLS-1$
		entry.setSmallIcon(Ed2DiagramEditorPlugin
				.findImageDescriptor("/es.ucm.fdi.ed2.emf.diagram/icons/obj16/Edge2.gif")); //$NON-NLS-1$
		entry.setLargeIcon(Ed2DiagramEditorPlugin
				.findImageDescriptor("/es.ucm.fdi.ed2.emf.diagram/icons/obj16/Edge2.gif")); //$NON-NLS-1$
		return entry;
	}

}
