/**
 */
package es.ucm.fdi.emf.model.ed2;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Node</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link es.ucm.fdi.emf.model.ed2.Node#getLeaves <em>Leaves</em>}</li>
 *   <li>{@link es.ucm.fdi.emf.model.ed2.Node#getNodes <em>Nodes</em>}</li>
 * </ul>
 *
 * @see es.ucm.fdi.emf.model.ed2.Ed2Package#getNode()
 * @model
 * @generated
 */
public interface Node extends TreeElement {
	/**
	 * Returns the value of the '<em><b>Leaves</b></em>' containment reference list.
	 * The list contents are of type {@link es.ucm.fdi.emf.model.ed2.Leaf}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Leaves</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Leaves</em>' containment reference list.
	 * @see es.ucm.fdi.emf.model.ed2.Ed2Package#getNode_Leaves()
	 * @model containment="true" resolveProxies="true"
	 * @generated
	 */
	EList<Leaf> getLeaves();

	/**
	 * Returns the value of the '<em><b>Nodes</b></em>' containment reference list.
	 * The list contents are of type {@link es.ucm.fdi.emf.model.ed2.Node}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Nodes</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Nodes</em>' containment reference list.
	 * @see es.ucm.fdi.emf.model.ed2.Ed2Package#getNode_Nodes()
	 * @model containment="true" resolveProxies="true"
	 * @generated
	 */
	EList<Node> getNodes();

} // Node
