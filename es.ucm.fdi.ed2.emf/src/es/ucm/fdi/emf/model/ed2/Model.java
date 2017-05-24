/**
 */
package es.ucm.fdi.emf.model.ed2;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Model</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link es.ucm.fdi.emf.model.ed2.Model#getEd2 <em>Ed2</em>}</li>
 * </ul>
 *
 * @see es.ucm.fdi.emf.model.ed2.Ed2Package#getModel()
 * @model
 * @generated
 */
public interface Model extends EObject {
	/**
	 * Returns the value of the '<em><b>Ed2</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Ed2</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Ed2</em>' containment reference.
	 * @see #setEd2(ED2)
	 * @see es.ucm.fdi.emf.model.ed2.Ed2Package#getModel_Ed2()
	 * @model containment="true" resolveProxies="true"
	 * @generated
	 */
	ED2 getEd2();

	/**
	 * Sets the value of the '{@link es.ucm.fdi.emf.model.ed2.Model#getEd2 <em>Ed2</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Ed2</em>' containment reference.
	 * @see #getEd2()
	 * @generated
	 */
	void setEd2(ED2 value);

} // Model
