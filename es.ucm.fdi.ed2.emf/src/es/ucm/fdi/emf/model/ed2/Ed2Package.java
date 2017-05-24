/**
 */
package es.ucm.fdi.emf.model.ed2;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EEnum;
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
 * @see es.ucm.fdi.emf.model.ed2.Ed2Factory
 * @model kind="package"
 * @generated
 */
public interface Ed2Package extends EPackage {
	/**
	 * The package name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNAME = "ed2";

	/**
	 * The package namespace URI.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_URI = "http://ed2/1.0";

	/**
	 * The package namespace name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_PREFIX = "ed2";

	/**
	 * The singleton instance of the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	Ed2Package eINSTANCE = es.ucm.fdi.emf.model.ed2.impl.Ed2PackageImpl.init();

	/**
	 * The meta object id for the '{@link es.ucm.fdi.emf.model.ed2.impl.EDDImpl <em>EDD</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see es.ucm.fdi.emf.model.ed2.impl.EDDImpl
	 * @see es.ucm.fdi.emf.model.ed2.impl.Ed2PackageImpl#getEDD()
	 * @generated
	 */
	int EDD = 0;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EDD__NAME = 0;

	/**
	 * The feature id for the '<em><b>Tree Objects</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EDD__TREE_OBJECTS = 1;

	/**
	 * The feature id for the '<em><b>Tree Parents</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EDD__TREE_PARENTS = 2;

	/**
	 * The number of structural features of the '<em>EDD</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EDD_FEATURE_COUNT = 3;

	/**
	 * The number of operations of the '<em>EDD</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EDD_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link es.ucm.fdi.emf.model.ed2.impl.TreeElementImpl <em>Tree Element</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see es.ucm.fdi.emf.model.ed2.impl.TreeElementImpl
	 * @see es.ucm.fdi.emf.model.ed2.impl.Ed2PackageImpl#getTreeElement()
	 * @generated
	 */
	int TREE_ELEMENT = 1;

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
	 * The feature id for the '<em><b>Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TREE_ELEMENT__TYPE = 2;

	/**
	 * The number of structural features of the '<em>Tree Element</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TREE_ELEMENT_FEATURE_COUNT = 3;

	/**
	 * The number of operations of the '<em>Tree Element</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TREE_ELEMENT_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link es.ucm.fdi.emf.model.ed2.impl.NodeImpl <em>Node</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see es.ucm.fdi.emf.model.ed2.impl.NodeImpl
	 * @see es.ucm.fdi.emf.model.ed2.impl.Ed2PackageImpl#getNode()
	 * @generated
	 */
	int NODE = 2;

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
	 * The feature id for the '<em><b>Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NODE__TYPE = TREE_ELEMENT__TYPE;

	/**
	 * The feature id for the '<em><b>Leaves</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NODE__LEAVES = TREE_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Nodes</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NODE__NODES = TREE_ELEMENT_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Node</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NODE_FEATURE_COUNT = TREE_ELEMENT_FEATURE_COUNT + 2;

	/**
	 * The number of operations of the '<em>Node</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NODE_OPERATION_COUNT = TREE_ELEMENT_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link es.ucm.fdi.emf.model.ed2.impl.LeafImpl <em>Leaf</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see es.ucm.fdi.emf.model.ed2.impl.LeafImpl
	 * @see es.ucm.fdi.emf.model.ed2.impl.Ed2PackageImpl#getLeaf()
	 * @generated
	 */
	int LEAF = 3;

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
	 * The feature id for the '<em><b>Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LEAF__TYPE = TREE_ELEMENT__TYPE;

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
	 * The meta object id for the '{@link es.ucm.fdi.emf.model.ed2.impl.TreeParentImpl <em>Tree Parent</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see es.ucm.fdi.emf.model.ed2.impl.TreeParentImpl
	 * @see es.ucm.fdi.emf.model.ed2.impl.Ed2PackageImpl#getTreeParent()
	 * @generated
	 */
	int TREE_PARENT = 4;

	/**
	 * The feature id for the '<em><b>Tree Objects</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TREE_PARENT__TREE_OBJECTS = 0;

	/**
	 * The feature id for the '<em><b>Tree Parents</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TREE_PARENT__TREE_PARENTS = 1;

	/**
	 * The feature id for the '<em><b>Index</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TREE_PARENT__INDEX = 2;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TREE_PARENT__NAME = 3;

	/**
	 * The feature id for the '<em><b>Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TREE_PARENT__TYPE = 4;

	/**
	 * The number of structural features of the '<em>Tree Parent</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TREE_PARENT_FEATURE_COUNT = 5;

	/**
	 * The number of operations of the '<em>Tree Parent</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TREE_PARENT_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link es.ucm.fdi.emf.model.ed2.impl.TreeObjectImpl <em>Tree Object</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see es.ucm.fdi.emf.model.ed2.impl.TreeObjectImpl
	 * @see es.ucm.fdi.emf.model.ed2.impl.Ed2PackageImpl#getTreeObject()
	 * @generated
	 */
	int TREE_OBJECT = 5;

	/**
	 * The feature id for the '<em><b>Index</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TREE_OBJECT__INDEX = 0;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TREE_OBJECT__NAME = 1;

	/**
	 * The feature id for the '<em><b>Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TREE_OBJECT__TYPE = 2;

	/**
	 * The number of structural features of the '<em>Tree Object</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TREE_OBJECT_FEATURE_COUNT = 3;

	/**
	 * The number of operations of the '<em>Tree Object</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TREE_OBJECT_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link es.ucm.fdi.emf.model.ed2.impl.ED2Impl <em>ED2</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see es.ucm.fdi.emf.model.ed2.impl.ED2Impl
	 * @see es.ucm.fdi.emf.model.ed2.impl.Ed2PackageImpl#getED2()
	 * @generated
	 */
	int ED2 = 6;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ED2__NAME = 0;

	/**
	 * The feature id for the '<em><b>Tree Elements</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ED2__TREE_ELEMENTS = 1;

	/**
	 * The number of structural features of the '<em>ED2</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ED2_FEATURE_COUNT = 2;

	/**
	 * The number of operations of the '<em>ED2</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ED2_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link es.ucm.fdi.emf.model.ed2.impl.ModelImpl <em>Model</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see es.ucm.fdi.emf.model.ed2.impl.ModelImpl
	 * @see es.ucm.fdi.emf.model.ed2.impl.Ed2PackageImpl#getModel()
	 * @generated
	 */
	int MODEL = 7;

	/**
	 * The feature id for the '<em><b>Ed2</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MODEL__ED2 = 0;

	/**
	 * The number of structural features of the '<em>Model</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MODEL_FEATURE_COUNT = 1;

	/**
	 * The number of operations of the '<em>Model</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MODEL_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link es.ucm.fdi.emf.model.ed2.TreeElementType <em>Tree Element Type</em>}' enum.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see es.ucm.fdi.emf.model.ed2.TreeElementType
	 * @see es.ucm.fdi.emf.model.ed2.impl.Ed2PackageImpl#getTreeElementType()
	 * @generated
	 */
	int TREE_ELEMENT_TYPE = 8;


	/**
	 * Returns the meta object for class '{@link es.ucm.fdi.emf.model.ed2.EDD <em>EDD</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>EDD</em>'.
	 * @see es.ucm.fdi.emf.model.ed2.EDD
	 * @generated
	 */
	EClass getEDD();

	/**
	 * Returns the meta object for the attribute '{@link es.ucm.fdi.emf.model.ed2.EDD#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see es.ucm.fdi.emf.model.ed2.EDD#getName()
	 * @see #getEDD()
	 * @generated
	 */
	EAttribute getEDD_Name();

	/**
	 * Returns the meta object for the containment reference list '{@link es.ucm.fdi.emf.model.ed2.EDD#getTreeObjects <em>Tree Objects</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Tree Objects</em>'.
	 * @see es.ucm.fdi.emf.model.ed2.EDD#getTreeObjects()
	 * @see #getEDD()
	 * @generated
	 */
	EReference getEDD_TreeObjects();

	/**
	 * Returns the meta object for the containment reference list '{@link es.ucm.fdi.emf.model.ed2.EDD#getTreeParents <em>Tree Parents</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Tree Parents</em>'.
	 * @see es.ucm.fdi.emf.model.ed2.EDD#getTreeParents()
	 * @see #getEDD()
	 * @generated
	 */
	EReference getEDD_TreeParents();

	/**
	 * Returns the meta object for class '{@link es.ucm.fdi.emf.model.ed2.TreeElement <em>Tree Element</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Tree Element</em>'.
	 * @see es.ucm.fdi.emf.model.ed2.TreeElement
	 * @generated
	 */
	EClass getTreeElement();

	/**
	 * Returns the meta object for the attribute '{@link es.ucm.fdi.emf.model.ed2.TreeElement#getIndex <em>Index</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Index</em>'.
	 * @see es.ucm.fdi.emf.model.ed2.TreeElement#getIndex()
	 * @see #getTreeElement()
	 * @generated
	 */
	EAttribute getTreeElement_Index();

	/**
	 * Returns the meta object for the attribute '{@link es.ucm.fdi.emf.model.ed2.TreeElement#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see es.ucm.fdi.emf.model.ed2.TreeElement#getName()
	 * @see #getTreeElement()
	 * @generated
	 */
	EAttribute getTreeElement_Name();

	/**
	 * Returns the meta object for the attribute '{@link es.ucm.fdi.emf.model.ed2.TreeElement#getType <em>Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Type</em>'.
	 * @see es.ucm.fdi.emf.model.ed2.TreeElement#getType()
	 * @see #getTreeElement()
	 * @generated
	 */
	EAttribute getTreeElement_Type();

	/**
	 * Returns the meta object for class '{@link es.ucm.fdi.emf.model.ed2.Node <em>Node</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Node</em>'.
	 * @see es.ucm.fdi.emf.model.ed2.Node
	 * @generated
	 */
	EClass getNode();

	/**
	 * Returns the meta object for the containment reference list '{@link es.ucm.fdi.emf.model.ed2.Node#getLeaves <em>Leaves</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Leaves</em>'.
	 * @see es.ucm.fdi.emf.model.ed2.Node#getLeaves()
	 * @see #getNode()
	 * @generated
	 */
	EReference getNode_Leaves();

	/**
	 * Returns the meta object for the containment reference list '{@link es.ucm.fdi.emf.model.ed2.Node#getNodes <em>Nodes</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Nodes</em>'.
	 * @see es.ucm.fdi.emf.model.ed2.Node#getNodes()
	 * @see #getNode()
	 * @generated
	 */
	EReference getNode_Nodes();

	/**
	 * Returns the meta object for class '{@link es.ucm.fdi.emf.model.ed2.Leaf <em>Leaf</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Leaf</em>'.
	 * @see es.ucm.fdi.emf.model.ed2.Leaf
	 * @generated
	 */
	EClass getLeaf();

	/**
	 * Returns the meta object for class '{@link es.ucm.fdi.emf.model.ed2.TreeParent <em>Tree Parent</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Tree Parent</em>'.
	 * @see es.ucm.fdi.emf.model.ed2.TreeParent
	 * @generated
	 */
	EClass getTreeParent();

	/**
	 * Returns the meta object for the reference list '{@link es.ucm.fdi.emf.model.ed2.TreeParent#getTreeObjects <em>Tree Objects</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Tree Objects</em>'.
	 * @see es.ucm.fdi.emf.model.ed2.TreeParent#getTreeObjects()
	 * @see #getTreeParent()
	 * @generated
	 */
	EReference getTreeParent_TreeObjects();

	/**
	 * Returns the meta object for the reference list '{@link es.ucm.fdi.emf.model.ed2.TreeParent#getTreeParents <em>Tree Parents</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Tree Parents</em>'.
	 * @see es.ucm.fdi.emf.model.ed2.TreeParent#getTreeParents()
	 * @see #getTreeParent()
	 * @generated
	 */
	EReference getTreeParent_TreeParents();

	/**
	 * Returns the meta object for the attribute '{@link es.ucm.fdi.emf.model.ed2.TreeParent#getIndex <em>Index</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Index</em>'.
	 * @see es.ucm.fdi.emf.model.ed2.TreeParent#getIndex()
	 * @see #getTreeParent()
	 * @generated
	 */
	EAttribute getTreeParent_Index();

	/**
	 * Returns the meta object for the attribute '{@link es.ucm.fdi.emf.model.ed2.TreeParent#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see es.ucm.fdi.emf.model.ed2.TreeParent#getName()
	 * @see #getTreeParent()
	 * @generated
	 */
	EAttribute getTreeParent_Name();

	/**
	 * Returns the meta object for the attribute '{@link es.ucm.fdi.emf.model.ed2.TreeParent#getType <em>Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Type</em>'.
	 * @see es.ucm.fdi.emf.model.ed2.TreeParent#getType()
	 * @see #getTreeParent()
	 * @generated
	 */
	EAttribute getTreeParent_Type();

	/**
	 * Returns the meta object for class '{@link es.ucm.fdi.emf.model.ed2.TreeObject <em>Tree Object</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Tree Object</em>'.
	 * @see es.ucm.fdi.emf.model.ed2.TreeObject
	 * @generated
	 */
	EClass getTreeObject();

	/**
	 * Returns the meta object for the attribute '{@link es.ucm.fdi.emf.model.ed2.TreeObject#getIndex <em>Index</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Index</em>'.
	 * @see es.ucm.fdi.emf.model.ed2.TreeObject#getIndex()
	 * @see #getTreeObject()
	 * @generated
	 */
	EAttribute getTreeObject_Index();

	/**
	 * Returns the meta object for the attribute '{@link es.ucm.fdi.emf.model.ed2.TreeObject#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see es.ucm.fdi.emf.model.ed2.TreeObject#getName()
	 * @see #getTreeObject()
	 * @generated
	 */
	EAttribute getTreeObject_Name();

	/**
	 * Returns the meta object for the attribute '{@link es.ucm.fdi.emf.model.ed2.TreeObject#getType <em>Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Type</em>'.
	 * @see es.ucm.fdi.emf.model.ed2.TreeObject#getType()
	 * @see #getTreeObject()
	 * @generated
	 */
	EAttribute getTreeObject_Type();

	/**
	 * Returns the meta object for class '{@link es.ucm.fdi.emf.model.ed2.ED2 <em>ED2</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>ED2</em>'.
	 * @see es.ucm.fdi.emf.model.ed2.ED2
	 * @generated
	 */
	EClass getED2();

	/**
	 * Returns the meta object for the attribute '{@link es.ucm.fdi.emf.model.ed2.ED2#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see es.ucm.fdi.emf.model.ed2.ED2#getName()
	 * @see #getED2()
	 * @generated
	 */
	EAttribute getED2_Name();

	/**
	 * Returns the meta object for the containment reference list '{@link es.ucm.fdi.emf.model.ed2.ED2#getTreeElements <em>Tree Elements</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Tree Elements</em>'.
	 * @see es.ucm.fdi.emf.model.ed2.ED2#getTreeElements()
	 * @see #getED2()
	 * @generated
	 */
	EReference getED2_TreeElements();

	/**
	 * Returns the meta object for class '{@link es.ucm.fdi.emf.model.ed2.Model <em>Model</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Model</em>'.
	 * @see es.ucm.fdi.emf.model.ed2.Model
	 * @generated
	 */
	EClass getModel();

	/**
	 * Returns the meta object for the containment reference '{@link es.ucm.fdi.emf.model.ed2.Model#getEd2 <em>Ed2</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Ed2</em>'.
	 * @see es.ucm.fdi.emf.model.ed2.Model#getEd2()
	 * @see #getModel()
	 * @generated
	 */
	EReference getModel_Ed2();

	/**
	 * Returns the meta object for enum '{@link es.ucm.fdi.emf.model.ed2.TreeElementType <em>Tree Element Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for enum '<em>Tree Element Type</em>'.
	 * @see es.ucm.fdi.emf.model.ed2.TreeElementType
	 * @generated
	 */
	EEnum getTreeElementType();

	/**
	 * Returns the factory that creates the instances of the model.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the factory that creates the instances of the model.
	 * @generated
	 */
	Ed2Factory getEd2Factory();

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
		 * The meta object literal for the '{@link es.ucm.fdi.emf.model.ed2.impl.EDDImpl <em>EDD</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see es.ucm.fdi.emf.model.ed2.impl.EDDImpl
		 * @see es.ucm.fdi.emf.model.ed2.impl.Ed2PackageImpl#getEDD()
		 * @generated
		 */
		EClass EDD = eINSTANCE.getEDD();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute EDD__NAME = eINSTANCE.getEDD_Name();

		/**
		 * The meta object literal for the '<em><b>Tree Objects</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference EDD__TREE_OBJECTS = eINSTANCE.getEDD_TreeObjects();

		/**
		 * The meta object literal for the '<em><b>Tree Parents</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference EDD__TREE_PARENTS = eINSTANCE.getEDD_TreeParents();

		/**
		 * The meta object literal for the '{@link es.ucm.fdi.emf.model.ed2.impl.TreeElementImpl <em>Tree Element</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see es.ucm.fdi.emf.model.ed2.impl.TreeElementImpl
		 * @see es.ucm.fdi.emf.model.ed2.impl.Ed2PackageImpl#getTreeElement()
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
		 * The meta object literal for the '<em><b>Type</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TREE_ELEMENT__TYPE = eINSTANCE.getTreeElement_Type();

		/**
		 * The meta object literal for the '{@link es.ucm.fdi.emf.model.ed2.impl.NodeImpl <em>Node</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see es.ucm.fdi.emf.model.ed2.impl.NodeImpl
		 * @see es.ucm.fdi.emf.model.ed2.impl.Ed2PackageImpl#getNode()
		 * @generated
		 */
		EClass NODE = eINSTANCE.getNode();

		/**
		 * The meta object literal for the '<em><b>Leaves</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference NODE__LEAVES = eINSTANCE.getNode_Leaves();

		/**
		 * The meta object literal for the '<em><b>Nodes</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference NODE__NODES = eINSTANCE.getNode_Nodes();

		/**
		 * The meta object literal for the '{@link es.ucm.fdi.emf.model.ed2.impl.LeafImpl <em>Leaf</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see es.ucm.fdi.emf.model.ed2.impl.LeafImpl
		 * @see es.ucm.fdi.emf.model.ed2.impl.Ed2PackageImpl#getLeaf()
		 * @generated
		 */
		EClass LEAF = eINSTANCE.getLeaf();

		/**
		 * The meta object literal for the '{@link es.ucm.fdi.emf.model.ed2.impl.TreeParentImpl <em>Tree Parent</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see es.ucm.fdi.emf.model.ed2.impl.TreeParentImpl
		 * @see es.ucm.fdi.emf.model.ed2.impl.Ed2PackageImpl#getTreeParent()
		 * @generated
		 */
		EClass TREE_PARENT = eINSTANCE.getTreeParent();

		/**
		 * The meta object literal for the '<em><b>Tree Objects</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference TREE_PARENT__TREE_OBJECTS = eINSTANCE.getTreeParent_TreeObjects();

		/**
		 * The meta object literal for the '<em><b>Tree Parents</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference TREE_PARENT__TREE_PARENTS = eINSTANCE.getTreeParent_TreeParents();

		/**
		 * The meta object literal for the '<em><b>Index</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TREE_PARENT__INDEX = eINSTANCE.getTreeParent_Index();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TREE_PARENT__NAME = eINSTANCE.getTreeParent_Name();

		/**
		 * The meta object literal for the '<em><b>Type</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TREE_PARENT__TYPE = eINSTANCE.getTreeParent_Type();

		/**
		 * The meta object literal for the '{@link es.ucm.fdi.emf.model.ed2.impl.TreeObjectImpl <em>Tree Object</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see es.ucm.fdi.emf.model.ed2.impl.TreeObjectImpl
		 * @see es.ucm.fdi.emf.model.ed2.impl.Ed2PackageImpl#getTreeObject()
		 * @generated
		 */
		EClass TREE_OBJECT = eINSTANCE.getTreeObject();

		/**
		 * The meta object literal for the '<em><b>Index</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TREE_OBJECT__INDEX = eINSTANCE.getTreeObject_Index();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TREE_OBJECT__NAME = eINSTANCE.getTreeObject_Name();

		/**
		 * The meta object literal for the '<em><b>Type</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TREE_OBJECT__TYPE = eINSTANCE.getTreeObject_Type();

		/**
		 * The meta object literal for the '{@link es.ucm.fdi.emf.model.ed2.impl.ED2Impl <em>ED2</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see es.ucm.fdi.emf.model.ed2.impl.ED2Impl
		 * @see es.ucm.fdi.emf.model.ed2.impl.Ed2PackageImpl#getED2()
		 * @generated
		 */
		EClass ED2 = eINSTANCE.getED2();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ED2__NAME = eINSTANCE.getED2_Name();

		/**
		 * The meta object literal for the '<em><b>Tree Elements</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ED2__TREE_ELEMENTS = eINSTANCE.getED2_TreeElements();

		/**
		 * The meta object literal for the '{@link es.ucm.fdi.emf.model.ed2.impl.ModelImpl <em>Model</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see es.ucm.fdi.emf.model.ed2.impl.ModelImpl
		 * @see es.ucm.fdi.emf.model.ed2.impl.Ed2PackageImpl#getModel()
		 * @generated
		 */
		EClass MODEL = eINSTANCE.getModel();

		/**
		 * The meta object literal for the '<em><b>Ed2</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference MODEL__ED2 = eINSTANCE.getModel_Ed2();

		/**
		 * The meta object literal for the '{@link es.ucm.fdi.emf.model.ed2.TreeElementType <em>Tree Element Type</em>}' enum.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see es.ucm.fdi.emf.model.ed2.TreeElementType
		 * @see es.ucm.fdi.emf.model.ed2.impl.Ed2PackageImpl#getTreeElementType()
		 * @generated
		 */
		EEnum TREE_ELEMENT_TYPE = eINSTANCE.getTreeElementType();

	}

} //Ed2Package
