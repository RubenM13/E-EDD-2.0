/**
 */
package es.ucm.fdi.edd.emf.model.edd;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>EDD</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link es.ucm.fdi.edd.emf.model.edd.EDD#getElements <em>Elements</em>}</li>
 *   <li>{@link es.ucm.fdi.edd.emf.model.edd.EDD#getName <em>Name</em>}</li>
 * </ul>
 *
 * @see es.ucm.fdi.edd.emf.model.edd.EddPackage#getEDD()
 * @model
 * @generated
 */
public interface EDD extends EObject {
	/**
	 * Returns the value of the '<em><b>Elements</b></em>' containment reference list.
	 * The list contents are of type {@link es.ucm.fdi.edd.emf.model.edd.TreeElement}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Elements</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Elements</em>' containment reference list.
	 * @see es.ucm.fdi.edd.emf.model.edd.EddPackage#getEDD_Elements()
	 * @model containment="true"
	 * @generated
	 */
	EList<TreeElement> getElements();

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
	 * @see es.ucm.fdi.edd.emf.model.edd.EddPackage#getEDD_Name()
	 * @model
	 * @generated
	 */
	String getName();

	/**
	 * Sets the value of the '{@link es.ucm.fdi.edd.emf.model.edd.EDD#getName <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Name</em>' attribute.
	 * @see #getName()
	 * @generated
	 */
	void setName(String value);

} // EDD
