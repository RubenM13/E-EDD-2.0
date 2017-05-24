/**
 */
package es.ucm.fdi.edd.emf.model.edd;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

/**
 * <!-- begin-user-doc -->
 * The <b>Package</b> for the model.
 * It contains accessors for the meta objects to represent
 * <ul>
 *   <li>each class,</li>
 *   <li>each feature of each class,</li>
 *   <li>each operation of each class,</li>
 *   <li>each enum,</li>
 *   <li>and each data type</li>
 * </ul>
 * <!-- end-user-doc -->
 * @see es.ucm.fdi.edd.emf.model.edd.EddFactory
 * @model kind="package"
 * @generated
 */
public interface EddPackage extends EPackage {
	/**
	 * The package name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNAME = "edd";

	/**
	 * The package namespace URI.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_URI = "http://edd/1.0";

	/**
	 * The package namespace name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_PREFIX = "edd";

	/**
	 * The singleton instance of the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	EddPackage eINSTANCE = es.ucm.fdi.edd.emf.model.edd.impl.EddPackageImpl.init();

	/**
	 * The meta object id for the '{@link es.ucm.fdi.edd.emf.model.edd.impl.EDDImpl <em>EDD</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see es.ucm.fdi.edd.emf.model.edd.impl.EDDImpl
	 * @see es.ucm.fdi.edd.emf.model.edd.impl.EddPackageImpl#getEDD()
	 * @generated
	 */
	int EDD = 0;

	/**
	 * The feature id for the '<em><b>Elements</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EDD__ELEMENTS = 0;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EDD__NAME = 1;

	/**
	 * The number of structural features of the '<em>EDD</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EDD_FEATURE_COUNT = 2;

	/**
	 * The number of operations of the '<em>EDD</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EDD_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link es.ucm.fdi.edd.emf.model.edd.impl.TreeElementImpl <em>Tree Element</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see es.ucm.fdi.edd.emf.model.edd.impl.TreeElementImpl
	 * @see es.ucm.fdi.edd.emf.model.edd.impl.EddPackageImpl#getTreeElement()
	 * @generated
	 */
	int TREE_ELEMENT = 3;

	/**
	 * The feature id for the '<em><b>Index</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TREE_ELEMENT__INDEX = 0;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TREE_ELEMENT__NAME = 1;

	/**
	 * The number of structural features of the '<em>Tree Element</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TREE_ELEMENT_FEATURE_COUNT = 2;

	/**
	 * The number of operations of the '<em>Tree Element</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TREE_ELEMENT_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link es.ucm.fdi.edd.emf.model.edd.impl.NodeImpl <em>Node</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see es.ucm.fdi.edd.emf.model.edd.impl.NodeImpl
	 * @see es.ucm.fdi.edd.emf.model.edd.impl.EddPackageImpl#getNode()
	 * @generated
	 */
	int NODE = 1;

	/**
	 * The feature id for the '<em><b>Index</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NODE__INDEX = TREE_ELEMENT__INDEX;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NODE__NAME = TREE_ELEMENT__NAME;

	/**
	 * The feature id for the '<em><b>Children</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NODE__CHILDREN = TREE_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Node</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NODE_FEATURE_COUNT = TREE_ELEMENT_FEATURE_COUNT + 1;

	/**
	 * The number of operations of the '<em>Node</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NODE_OPERATION_COUNT = TREE_ELEMENT_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link es.ucm.fdi.edd.emf.model.edd.impl.LeafImpl <em>Leaf</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see es.ucm.fdi.edd.emf.model.edd.impl.LeafImpl
	 * @see es.ucm.fdi.edd.emf.model.edd.impl.EddPackageImpl#getLeaf()
	 * @generated
	 */
	int LEAF = 2;

	/**
	 * The feature id for the '<em><b>Index</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LEAF__INDEX = TREE_ELEMENT__INDEX;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LEAF__NAME = TREE_ELEMENT__NAME;

	/**
	 * The number of structural features of the '<em>Leaf</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LEAF_FEATURE_COUNT = TREE_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The number of operations of the '<em>Leaf</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LEAF_OPERATION_COUNT = TREE_ELEMENT_OPERATION_COUNT + 0;


	/**
	 * Returns the meta object for class '{@link es.ucm.fdi.edd.emf.model.edd.EDD <em>EDD</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>EDD</em>'.
	 * @see es.ucm.fdi.edd.emf.model.edd.EDD
	 * @generated
	 */
	EClass getEDD();

	/**
	 * Returns the meta object for the containment reference list '{@link es.ucm.fdi.edd.emf.model.edd.EDD#getElements <em>Elements</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Elements</em>'.
	 * @see es.ucm.fdi.edd.emf.model.edd.EDD#getElements()
	 * @see #getEDD()
	 * @generated
	 */
	EReference getEDD_Elements();

	/**
	 * Returns the meta object for the attribute '{@link es.ucm.fdi.edd.emf.model.edd.EDD#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see es.ucm.fdi.edd.emf.model.edd.EDD#getName()
	 * @see #getEDD()
	 * @generated
	 */
	EAttribute getEDD_Name();

	/**
	 * Returns the meta object for class '{@link es.ucm.fdi.edd.emf.model.edd.Node <em>Node</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Node</em>'.
	 * @see es.ucm.fdi.edd.emf.model.edd.Node
	 * @generated
	 */
	EClass getNode();

	/**
	 * Returns the meta object for the reference list '{@link es.ucm.fdi.edd.emf.model.edd.Node#getChildren <em>Children</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Children</em>'.
	 * @see es.ucm.fdi.edd.emf.model.edd.Node#getChildren()
	 * @see #getNode()
	 * @generated
	 */
	EReference getNode_Children();

	/**
	 * Returns the meta object for class '{@link es.ucm.fdi.edd.emf.model.edd.Leaf <em>Leaf</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Leaf</em>'.
	 * @see es.ucm.fdi.edd.emf.model.edd.Leaf
	 * @generated
	 */
	EClass getLeaf();

	/**
	 * Returns the meta object for class '{@link es.ucm.fdi.edd.emf.model.edd.TreeElement <em>Tree Element</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Tree Element</em>'.
	 * @see es.ucm.fdi.edd.emf.model.edd.TreeElement
	 * @generated
	 */
	EClass getTreeElement();

	/**
	 * Returns the meta object for the attribute '{@link es.ucm.fdi.edd.emf.model.edd.TreeElement#getIndex <em>Index</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Index</em>'.
	 * @see es.ucm.fdi.edd.emf.model.edd.TreeElement#getIndex()
	 * @see #getTreeElement()
	 * @generated
	 */
	EAttribute getTreeElement_Index();

	/**
	 * Returns the meta object for the attribute '{@link es.ucm.fdi.edd.emf.model.edd.TreeElement#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see es.ucm.fdi.edd.emf.model.edd.TreeElement#getName()
	 * @see #getTreeElement()
	 * @generated
	 */
	EAttribute getTreeElement_Name();

	/**
	 * Returns the factory that creates the instances of the model.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the factory that creates the instances of the model.
	 * @generated
	 */
	EddFactory getEddFactory();

	/**
	 * <!-- begin-user-doc -->
	 * Defines literals for the meta objects that represent
	 * <ul>
	 *   <li>each class,</li>
	 *   <li>each feature of each class,</li>
	 *   <li>each operation of each class,</li>
	 *   <li>each enum,</li>
	 *   <li>and each data type</li>
	 * </ul>
	 * <!-- end-user-doc -->
	 * @generated
	 */
	interface Literals {
		/**
		 * The meta object literal for the '{@link es.ucm.fdi.edd.emf.model.edd.impl.EDDImpl <em>EDD</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see es.ucm.fdi.edd.emf.model.edd.impl.EDDImpl
		 * @see es.ucm.fdi.edd.emf.model.edd.impl.EddPackageImpl#getEDD()
		 * @generated
		 */
		EClass EDD = eINSTANCE.getEDD();
		/**
		 * The meta object literal for the '<em><b>Elements</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference EDD__ELEMENTS = eINSTANCE.getEDD_Elements();
		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute EDD__NAME = eINSTANCE.getEDD_Name();
		/**
		 * The meta object literal for the '{@link es.ucm.fdi.edd.emf.model.edd.impl.NodeImpl <em>Node</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see es.ucm.fdi.edd.emf.model.edd.impl.NodeImpl
		 * @see es.ucm.fdi.edd.emf.model.edd.impl.EddPackageImpl#getNode()
		 * @generated
		 */
		EClass NODE = eINSTANCE.getNode();
		/**
		 * The meta object literal for the '<em><b>Children</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference NODE__CHILDREN = eINSTANCE.getNode_Children();
		/**
		 * The meta object literal for the '{@link es.ucm.fdi.edd.emf.model.edd.impl.LeafImpl <em>Leaf</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see es.ucm.fdi.edd.emf.model.edd.impl.LeafImpl
		 * @see es.ucm.fdi.edd.emf.model.edd.impl.EddPackageImpl#getLeaf()
		 * @generated
		 */
		EClass LEAF = eINSTANCE.getLeaf();
		/**
		 * The meta object literal for the '{@link es.ucm.fdi.edd.emf.model.edd.impl.TreeElementImpl <em>Tree Element</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see es.ucm.fdi.edd.emf.model.edd.impl.TreeElementImpl
		 * @see es.ucm.fdi.edd.emf.model.edd.impl.EddPackageImpl#getTreeElement()
		 * @generated
		 */
		EClass TREE_ELEMENT = eINSTANCE.getTreeElement();
		/**
		 * The meta object literal for the '<em><b>Index</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TREE_ELEMENT__INDEX = eINSTANCE.getTreeElement_Index();
		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TREE_ELEMENT__NAME = eINSTANCE.getTreeElement_Name();

	}

} //EddPackage
