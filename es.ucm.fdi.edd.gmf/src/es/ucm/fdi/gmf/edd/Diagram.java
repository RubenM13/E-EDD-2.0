/**
 */
package es.ucm.fdi.gmf.edd;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Diagram</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link es.ucm.fdi.gmf.edd.Diagram#getModel <em>Model</em>}</li>
 * </ul>
 *
 * @see es.ucm.fdi.gmf.edd.EddPackage#getDiagram()
 * @model
 * @generated
 */
public interface Diagram extends EObject {
	/**
	 * Returns the value of the '<em><b>Model</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Model</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Model</em>' containment reference.
	 * @see #setModel(Model)
	 * @see es.ucm.fdi.gmf.edd.EddPackage#getDiagram_Model()
	 * @model containment="true"
	 * @generated
	 */
	Model getModel();

	/**
	 * Sets the value of the '{@link es.ucm.fdi.gmf.edd.Diagram#getModel <em>Model</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Model</em>' containment reference.
	 * @see #getModel()
	 * @generated
	 */
	void setModel(Model value);

} // Diagram
