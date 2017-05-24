/**
 */
package es.ucm.fdi.gmf.edd;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EOperation;
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
 * @see es.ucm.fdi.gmf.edd.EddFactory
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
	String eNS_URI = "http://es/ucm/fdi/gmf/1.0";

	/**
	 * The package namespace name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_PREFIX = "es.ucm.fdi.gmf";

	/**
	 * The singleton instance of the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	EddPackage eINSTANCE = es.ucm.fdi.gmf.edd.impl.EddPackageImpl.init();

	/**
	 * The meta object id for the '{@link es.ucm.fdi.gmf.edd.impl.DiagramImpl <em>Diagram</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see es.ucm.fdi.gmf.edd.impl.DiagramImpl
	 * @see es.ucm.fdi.gmf.edd.impl.EddPackageImpl#getDiagram()
	 * @generated
	 */
	int DIAGRAM = 0;

	/**
	 * The feature id for the '<em><b>Model</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DIAGRAM__MODEL = 0;

	/**
	 * The number of structural features of the '<em>Diagram</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DIAGRAM_FEATURE_COUNT = 1;

	/**
	 * The number of operations of the '<em>Diagram</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DIAGRAM_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link es.ucm.fdi.gmf.edd.impl.ModelImpl <em>Model</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see es.ucm.fdi.gmf.edd.impl.ModelImpl
	 * @see es.ucm.fdi.gmf.edd.impl.EddPackageImpl#getModel()
	 * @generated
	 */
	int MODEL = 1;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MODEL__NAME = 0;

	/**
	 * The feature id for the '<em><b>Blocks</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MODEL__BLOCKS = 1;

	/**
	 * The number of structural features of the '<em>Model</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MODEL_FEATURE_COUNT = 2;

	/**
	 * The number of operations of the '<em>Model</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MODEL_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link es.ucm.fdi.gmf.edd.impl.TreeElementImpl <em>Tree Element</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see es.ucm.fdi.gmf.edd.impl.TreeElementImpl
	 * @see es.ucm.fdi.gmf.edd.impl.EddPackageImpl#getTreeElement()
	 * @generated
	 */
	int TREE_ELEMENT = 2;

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
	 * The feature id for the '<em><b>Links</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TREE_ELEMENT__LINKS = 2;

	/**
	 * The number of structural features of the '<em>Tree Element</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TREE_ELEMENT_FEATURE_COUNT = 3;

	/**
	 * The operation id for the '<em>Validate</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TREE_ELEMENT___VALIDATE = 0;

	/**
	 * The number of operations of the '<em>Tree Element</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TREE_ELEMENT_OPERATION_COUNT = 1;

	/**
	 * The meta object id for the '{@link es.ucm.fdi.gmf.edd.impl.BlockImpl <em>Block</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see es.ucm.fdi.gmf.edd.impl.BlockImpl
	 * @see es.ucm.fdi.gmf.edd.impl.EddPackageImpl#getBlock()
	 * @generated
	 */
	int BLOCK = 3;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BLOCK__NAME = 0;

	/**
	 * The feature id for the '<em><b>Items</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BLOCK__ITEMS = 1;

	/**
	 * The number of structural features of the '<em>Block</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BLOCK_FEATURE_COUNT = 2;

	/**
	 * The operation id for the '<em>Validate</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BLOCK___VALIDATE = 0;

	/**
	 * The number of operations of the '<em>Block</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BLOCK_OPERATION_COUNT = 1;


	/**
	 * Returns the meta object for class '{@link es.ucm.fdi.gmf.edd.Diagram <em>Diagram</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Diagram</em>'.
	 * @see es.ucm.fdi.gmf.edd.Diagram
	 * @generated
	 */
	EClass getDiagram();

	/**
	 * Returns the meta object for the containment reference '{@link es.ucm.fdi.gmf.edd.Diagram#getModel <em>Model</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Model</em>'.
	 * @see es.ucm.fdi.gmf.edd.Diagram#getModel()
	 * @see #getDiagram()
	 * @generated
	 */
	EReference getDiagram_Model();

	/**
	 * Returns the meta object for class '{@link es.ucm.fdi.gmf.edd.Model <em>Model</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Model</em>'.
	 * @see es.ucm.fdi.gmf.edd.Model
	 * @generated
	 */
	EClass getModel();

	/**
	 * Returns the meta object for the attribute '{@link es.ucm.fdi.gmf.edd.Model#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see es.ucm.fdi.gmf.edd.Model#getName()
	 * @see #getModel()
	 * @generated
	 */
	EAttribute getModel_Name();

	/**
	 * Returns the meta object for the containment reference list '{@link es.ucm.fdi.gmf.edd.Model#getBlocks <em>Blocks</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Blocks</em>'.
	 * @see es.ucm.fdi.gmf.edd.Model#getBlocks()
	 * @see #getModel()
	 * @generated
	 */
	EReference getModel_Blocks();

	/**
	 * Returns the meta object for class '{@link es.ucm.fdi.gmf.edd.TreeElement <em>Tree Element</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Tree Element</em>'.
	 * @see es.ucm.fdi.gmf.edd.TreeElement
	 * @generated
	 */
	EClass getTreeElement();

	/**
	 * Returns the meta object for the attribute '{@link es.ucm.fdi.gmf.edd.TreeElement#getIndex <em>Index</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Index</em>'.
	 * @see es.ucm.fdi.gmf.edd.TreeElement#getIndex()
	 * @see #getTreeElement()
	 * @generated
	 */
	EAttribute getTreeElement_Index();

	/**
	 * Returns the meta object for the attribute '{@link es.ucm.fdi.gmf.edd.TreeElement#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see es.ucm.fdi.gmf.edd.TreeElement#getName()
	 * @see #getTreeElement()
	 * @generated
	 */
	EAttribute getTreeElement_Name();

	/**
	 * Returns the meta object for the reference list '{@link es.ucm.fdi.gmf.edd.TreeElement#getLinks <em>Links</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Links</em>'.
	 * @see es.ucm.fdi.gmf.edd.TreeElement#getLinks()
	 * @see #getTreeElement()
	 * @generated
	 */
	EReference getTreeElement_Links();

	/**
	 * Returns the meta object for the '{@link es.ucm.fdi.gmf.edd.TreeElement#validate() <em>Validate</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the '<em>Validate</em>' operation.
	 * @see es.ucm.fdi.gmf.edd.TreeElement#validate()
	 * @generated
	 */
	EOperation getTreeElement__Validate();

	/**
	 * Returns the meta object for class '{@link es.ucm.fdi.gmf.edd.Block <em>Block</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Block</em>'.
	 * @see es.ucm.fdi.gmf.edd.Block
	 * @generated
	 */
	EClass getBlock();

	/**
	 * Returns the meta object for the attribute '{@link es.ucm.fdi.gmf.edd.Block#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see es.ucm.fdi.gmf.edd.Block#getName()
	 * @see #getBlock()
	 * @generated
	 */
	EAttribute getBlock_Name();

	/**
	 * Returns the meta object for the containment reference list '{@link es.ucm.fdi.gmf.edd.Block#getItems <em>Items</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Items</em>'.
	 * @see es.ucm.fdi.gmf.edd.Block#getItems()
	 * @see #getBlock()
	 * @generated
	 */
	EReference getBlock_Items();

	/**
	 * Returns the meta object for the '{@link es.ucm.fdi.gmf.edd.Block#validate() <em>Validate</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the '<em>Validate</em>' operation.
	 * @see es.ucm.fdi.gmf.edd.Block#validate()
	 * @generated
	 */
	EOperation getBlock__Validate();

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
		 * The meta object literal for the '{@link es.ucm.fdi.gmf.edd.impl.DiagramImpl <em>Diagram</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see es.ucm.fdi.gmf.edd.impl.DiagramImpl
		 * @see es.ucm.fdi.gmf.edd.impl.EddPackageImpl#getDiagram()
		 * @generated
		 */
		EClass DIAGRAM = eINSTANCE.getDiagram();

		/**
		 * The meta object literal for the '<em><b>Model</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DIAGRAM__MODEL = eINSTANCE.getDiagram_Model();

		/**
		 * The meta object literal for the '{@link es.ucm.fdi.gmf.edd.impl.ModelImpl <em>Model</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see es.ucm.fdi.gmf.edd.impl.ModelImpl
		 * @see es.ucm.fdi.gmf.edd.impl.EddPackageImpl#getModel()
		 * @generated
		 */
		EClass MODEL = eINSTANCE.getModel();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute MODEL__NAME = eINSTANCE.getModel_Name();

		/**
		 * The meta object literal for the '<em><b>Blocks</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference MODEL__BLOCKS = eINSTANCE.getModel_Blocks();

		/**
		 * The meta object literal for the '{@link es.ucm.fdi.gmf.edd.impl.TreeElementImpl <em>Tree Element</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see es.ucm.fdi.gmf.edd.impl.TreeElementImpl
		 * @see es.ucm.fdi.gmf.edd.impl.EddPackageImpl#getTreeElement()
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

		/**
		 * The meta object literal for the '<em><b>Links</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference TREE_ELEMENT__LINKS = eINSTANCE.getTreeElement_Links();

		/**
		 * The meta object literal for the '<em><b>Validate</b></em>' operation.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EOperation TREE_ELEMENT___VALIDATE = eINSTANCE.getTreeElement__Validate();

		/**
		 * The meta object literal for the '{@link es.ucm.fdi.gmf.edd.impl.BlockImpl <em>Block</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see es.ucm.fdi.gmf.edd.impl.BlockImpl
		 * @see es.ucm.fdi.gmf.edd.impl.EddPackageImpl#getBlock()
		 * @generated
		 */
		EClass BLOCK = eINSTANCE.getBlock();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute BLOCK__NAME = eINSTANCE.getBlock_Name();

		/**
		 * The meta object literal for the '<em><b>Items</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference BLOCK__ITEMS = eINSTANCE.getBlock_Items();

		/**
		 * The meta object literal for the '<em><b>Validate</b></em>' operation.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EOperation BLOCK___VALIDATE = eINSTANCE.getBlock__Validate();

	}

} //EddPackage
