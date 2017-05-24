/**
 */
package es.ucm.fdi.emf.model.ed2;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Tree Object</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link es.ucm.fdi.emf.model.ed2.TreeObject#getIndex <em>Index</em>}</li>
 *   <li>{@link es.ucm.fdi.emf.model.ed2.TreeObject#getName <em>Name</em>}</li>
 *   <li>{@link es.ucm.fdi.emf.model.ed2.TreeObject#getType <em>Type</em>}</li>
 * </ul>
 *
 * @see es.ucm.fdi.emf.model.ed2.Ed2Package#getTreeObject()
 * @model
 * @generated
 */
public interface TreeObject extends EObject {
	/**
	 * Returns the value of the '<em><b>Index</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Index</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Index</em>' attribute.
	 * @see #setIndex(Integer)
	 * @see es.ucm.fdi.emf.model.ed2.Ed2Package#getTreeObject_Index()
	 * @model
	 * @generated
	 */
	Integer getIndex();

	/**
	 * Sets the value of the '{@link es.ucm.fdi.emf.model.ed2.TreeObject#getIndex <em>Index</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Index</em>' attribute.
	 * @see #getIndex()
	 * @generated
	 */
	void setIndex(Integer value);

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
	 * @see es.ucm.fdi.emf.model.ed2.Ed2Package#getTreeObject_Name()
	 * @model
	 * @generated
	 */
	String getName();

	/**
	 * Sets the value of the '{@link es.ucm.fdi.emf.model.ed2.TreeObject#getName <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Name</em>' attribute.
	 * @see #getName()
	 * @generated
	 */
	void setName(String value);

	/**
	 * Returns the value of the '<em><b>Type</b></em>' attribute.
	 * The literals are from the enumeration {@link es.ucm.fdi.emf.model.ed2.TreeElementType}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Type</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Type</em>' attribute.
	 * @see es.ucm.fdi.emf.model.ed2.TreeElementType
	 * @see #setType(TreeElementType)
	 * @see es.ucm.fdi.emf.model.ed2.Ed2Package#getTreeObject_Type()
	 * @model
	 * @generated
	 */
	TreeElementType getType();

	/**
	 * Sets the value of the '{@link es.ucm.fdi.emf.model.ed2.TreeObject#getType <em>Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Type</em>' attribute.
	 * @see es.ucm.fdi.emf.model.ed2.TreeElementType
	 * @see #getType()
	 * @generated
	 */
	void setType(TreeElementType value);

} // TreeObject
