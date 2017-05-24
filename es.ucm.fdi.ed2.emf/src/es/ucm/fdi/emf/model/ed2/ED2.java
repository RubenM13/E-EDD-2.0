/**
 */
package es.ucm.fdi.emf.model.ed2;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>ED2</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link es.ucm.fdi.emf.model.ed2.ED2#getName <em>Name</em>}</li>
 *   <li>{@link es.ucm.fdi.emf.model.ed2.ED2#getTreeElements <em>Tree Elements</em>}</li>
 * </ul>
 *
 * @see es.ucm.fdi.emf.model.ed2.Ed2Package#getED2()
 * @model
 * @generated
 */
public interface ED2 extends EObject {
	/**
	 * Returns the value of the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Name</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Name</em>' attribute.
	 * @see #setName(String)
	 * @see es.ucm.fdi.emf.model.ed2.Ed2Package#getED2_Name()
	 * @model
	 * @generated
	 */
	String getName();

	/**
	 * Sets the value of the '{@link es.ucm.fdi.emf.model.ed2.ED2#getName <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Name</em>' attribute.
	 * @see #getName()
	 * @generated
	 */
	void setName(String value);

	/**
	 * Returns the value of the '<em><b>Tree Elements</b></em>' containment reference list.
	 * The list contents are of type {@link es.ucm.fdi.emf.model.ed2.TreeElement}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Tree Elements</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Tree Elements</em>' containment reference list.
	 * @see es.ucm.fdi.emf.model.ed2.Ed2Package#getED2_TreeElements()
	 * @model containment="true" resolveProxies="true"
	 * @generated
	 */
	EList<TreeElement> getTreeElements();

} // ED2
