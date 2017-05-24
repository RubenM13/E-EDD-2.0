/**
 */
package es.ucm.fdi.edd.emf.model.edd;

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
 *   <li>{@link es.ucm.fdi.edd.emf.model.edd.Node#getChildren <em>Children</em>}</li>
 * </ul>
 *
 * @see es.ucm.fdi.edd.emf.model.edd.EddPackage#getNode()
 * @model
 * @generated
 */
public interface Node extends TreeElement {
	/**
	 * Returns the value of the '<em><b>Children</b></em>' reference list.
	 * The list contents are of type {@link es.ucm.fdi.edd.emf.model.edd.TreeElement}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Children</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Children</em>' reference list.
	 * @see es.ucm.fdi.edd.emf.model.edd.EddPackage#getNode_Children()
	 * @model
	 * @generated
	 */
	EList<TreeElement> getChildren();

} // Node
