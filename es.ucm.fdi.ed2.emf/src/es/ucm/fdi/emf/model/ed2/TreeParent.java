/**
 */
package es.ucm.fdi.emf.model.ed2;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Tree Parent</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link es.ucm.fdi.emf.model.ed2.TreeParent#getTreeObjects <em>Tree Objects</em>}</li>
 *   <li>{@link es.ucm.fdi.emf.model.ed2.TreeParent#getTreeParents <em>Tree Parents</em>}</li>
 *   <li>{@link es.ucm.fdi.emf.model.ed2.TreeParent#getIndex <em>Index</em>}</li>
 *   <li>{@link es.ucm.fdi.emf.model.ed2.TreeParent#getName <em>Name</em>}</li>
 *   <li>{@link es.ucm.fdi.emf.model.ed2.TreeParent#getType <em>Type</em>}</li>
 * </ul>
 *
 * @see es.ucm.fdi.emf.model.ed2.Ed2Package#getTreeParent()
 * @model
 * @generated
 */
public interface TreeParent extends EObject {
	/**
	 * Returns the value of the '<em><b>Tree Objects</b></em>' reference list.
	 * The list contents are of type {@link es.ucm.fdi.emf.model.ed2.TreeObject}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Tree Objects</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Tree Objects</em>' reference list.
	 * @see es.ucm.fdi.emf.model.ed2.Ed2Package#getTreeParent_TreeObjects()
	 * @model
	 * @generated
	 */
	EList<TreeObject> getTreeObjects();

	/**
	 * Returns the value of the '<em><b>Tree Parents</b></em>' reference list.
	 * The list contents are of type {@link es.ucm.fdi.emf.model.ed2.TreeParent}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Tree Parents</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Tree Parents</em>' reference list.
	 * @see es.ucm.fdi.emf.model.ed2.Ed2Package#getTreeParent_TreeParents()
	 * @model
	 * @generated
	 */
	EList<TreeParent> getTreeParents();

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
	 * @see es.ucm.fdi.emf.model.ed2.Ed2Package#getTreeParent_Index()
	 * @model
	 * @generated
	 */
	Integer getIndex();

	/**
	 * Sets the value of the '{@link es.ucm.fdi.emf.model.ed2.TreeParent#getIndex <em>Index</em>}' attribute.
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
	 * @see es.ucm.fdi.emf.model.ed2.Ed2Package#getTreeParent_Name()
	 * @model
	 * @generated
	 */
	String getName();

	/**
	 * Sets the value of the '{@link es.ucm.fdi.emf.model.ed2.TreeParent#getName <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Name</em>' attribute.
	 * @see #getName()
	 * @generated
	 */
	void setName(String value);

	/**
	 * Returns the value of the '<em><b>Type</b></em>' attribute.
	 * The default value is <code>"empty"</code>.
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
	 * @see es.ucm.fdi.emf.model.ed2.Ed2Package#getTreeParent_Type()
	 * @model default="empty"
	 * @generated
	 */
	TreeElementType getType();

	/**
	 * Sets the value of the '{@link es.ucm.fdi.emf.model.ed2.TreeParent#getType <em>Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Type</em>' attribute.
	 * @see es.ucm.fdi.emf.model.ed2.TreeElementType
	 * @see #getType()
	 * @generated
	 */
	void setType(TreeElementType value);

} // TreeParent
