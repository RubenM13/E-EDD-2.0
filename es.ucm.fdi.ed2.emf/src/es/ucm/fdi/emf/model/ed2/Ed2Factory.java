/**
 */
package es.ucm.fdi.emf.model.ed2;

import org.eclipse.emf.ecore.EFactory;

/**
 * <!-- begin-user-doc -->
 * The <b>Factory</b> for the model.
 * It provides a create method for each non-abstract class of the model.
 * <!-- end-user-doc -->
 * @see es.ucm.fdi.emf.model.ed2.Ed2Package
 * @generated
 */
public interface Ed2Factory extends EFactory {
	/**
	 * The singleton instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	Ed2Factory eINSTANCE = es.ucm.fdi.emf.model.ed2.impl.Ed2FactoryImpl.init();

	/**
	 * Returns a new object of class '<em>EDD</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>EDD</em>'.
	 * @generated
	 */
	EDD createEDD();

	/**
	 * Returns a new object of class '<em>Node</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Node</em>'.
	 * @generated
	 */
	Node createNode();

	/**
	 * Returns a new object of class '<em>Leaf</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Leaf</em>'.
	 * @generated
	 */
	Leaf createLeaf();

	/**
	 * Returns a new object of class '<em>Tree Parent</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Tree Parent</em>'.
	 * @generated
	 */
	TreeParent createTreeParent();

	/**
	 * Returns a new object of class '<em>Tree Object</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Tree Object</em>'.
	 * @generated
	 */
	TreeObject createTreeObject();

	/**
	 * Returns a new object of class '<em>ED2</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>ED2</em>'.
	 * @generated
	 */
	ED2 createED2();

	/**
	 * Returns a new object of class '<em>Model</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Model</em>'.
	 * @generated
	 */
	Model createModel();

	/**
	 * Returns the package supported by this factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the package supported by this factory.
	 * @generated
	 */
	Ed2Package getEd2Package();

} //Ed2Factory
