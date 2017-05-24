/**
 */
package es.ucm.fdi.edd.emf.model.edd;

import org.eclipse.emf.ecore.EFactory;

/**
 * <!-- begin-user-doc -->
 * The <b>Factory</b> for the model.
 * It provides a create method for each non-abstract class of the model.
 * <!-- end-user-doc -->
 * @see es.ucm.fdi.edd.emf.model.edd.EddPackage
 * @generated
 */
public interface EddFactory extends EFactory {
	/**
	 * The singleton instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	EddFactory eINSTANCE = es.ucm.fdi.edd.emf.model.edd.impl.EddFactoryImpl.init();

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
	 * Returns a new object of class '<em>Tree Element</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Tree Element</em>'.
	 * @generated
	 */
	TreeElement createTreeElement();

	/**
	 * Returns the package supported by this factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the package supported by this factory.
	 * @generated
	 */
	EddPackage getEddPackage();

} //EddFactory
