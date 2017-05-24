/**
 */
package es.ucm.fdi.emf.model.ed2.impl;

import es.ucm.fdi.emf.model.ed2.Ed2Factory;
import es.ucm.fdi.emf.model.ed2.Ed2Package;
import es.ucm.fdi.emf.model.ed2.Leaf;
import es.ucm.fdi.emf.model.ed2.Model;
import es.ucm.fdi.emf.model.ed2.Node;
import es.ucm.fdi.emf.model.ed2.TreeElement;
import es.ucm.fdi.emf.model.ed2.TreeElementType;
import es.ucm.fdi.emf.model.ed2.TreeObject;
import es.ucm.fdi.emf.model.ed2.TreeParent;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

import org.eclipse.emf.ecore.impl.EPackageImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Package</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class Ed2PackageImpl extends EPackageImpl implements Ed2Package {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass eddEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass treeElementEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass nodeEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass leafEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass treeParentEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass treeObjectEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass ed2EClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass modelEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EEnum treeElementTypeEEnum = null;

	/**
	 * Creates an instance of the model <b>Package</b>, registered with
	 * {@link org.eclipse.emf.ecore.EPackage.Registry EPackage.Registry} by the package
	 * package URI value.
	 * <p>Note: the correct way to create the package is via the static
	 * factory method {@link #init init()}, which also performs
	 * initialization of the package, or returns the registered package,
	 * if one already exists.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.emf.ecore.EPackage.Registry
	 * @see es.ucm.fdi.emf.model.ed2.Ed2Package#eNS_URI
	 * @see #init()
	 * @generated
	 */
	private Ed2PackageImpl() {
		super(eNS_URI, Ed2Factory.eINSTANCE);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static boolean isInited = false;

	/**
	 * Creates, registers, and initializes the <b>Package</b> for this model, and for any others upon which it depends.
	 * 
	 * <p>This method is used to initialize {@link Ed2Package#eINSTANCE} when that field is accessed.
	 * Clients should not invoke it directly. Instead, they should simply access that field to obtain the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #eNS_URI
	 * @see #createPackageContents()
	 * @see #initializePackageContents()
	 * @generated
	 */
	public static Ed2Package init() {
		if (isInited) return (Ed2Package)EPackage.Registry.INSTANCE.getEPackage(Ed2Package.eNS_URI);

		// Obtain or create and register package
		Ed2PackageImpl theEd2Package = (Ed2PackageImpl)(EPackage.Registry.INSTANCE.get(eNS_URI) instanceof Ed2PackageImpl ? EPackage.Registry.INSTANCE.get(eNS_URI) : new Ed2PackageImpl());

		isInited = true;

		// Create package meta-data objects
		theEd2Package.createPackageContents();

		// Initialize created meta-data
		theEd2Package.initializePackageContents();

		// Mark meta-data to indicate it can't be changed
		theEd2Package.freeze();

  
		// Update the registry and return the package
		EPackage.Registry.INSTANCE.put(Ed2Package.eNS_URI, theEd2Package);
		return theEd2Package;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getEDD() {
		return eddEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getEDD_Name() {
		return (EAttribute)eddEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getEDD_TreeObjects() {
		return (EReference)eddEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getEDD_TreeParents() {
		return (EReference)eddEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getTreeElement() {
		return treeElementEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTreeElement_Index() {
		return (EAttribute)treeElementEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTreeElement_Name() {
		return (EAttribute)treeElementEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTreeElement_Type() {
		return (EAttribute)treeElementEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getNode() {
		return nodeEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getNode_Leaves() {
		return (EReference)nodeEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getNode_Nodes() {
		return (EReference)nodeEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getLeaf() {
		return leafEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getTreeParent() {
		return treeParentEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getTreeParent_TreeObjects() {
		return (EReference)treeParentEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getTreeParent_TreeParents() {
		return (EReference)treeParentEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTreeParent_Index() {
		return (EAttribute)treeParentEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTreeParent_Name() {
		return (EAttribute)treeParentEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTreeParent_Type() {
		return (EAttribute)treeParentEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getTreeObject() {
		return treeObjectEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTreeObject_Index() {
		return (EAttribute)treeObjectEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTreeObject_Name() {
		return (EAttribute)treeObjectEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTreeObject_Type() {
		return (EAttribute)treeObjectEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getED2() {
		return ed2EClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getED2_Name() {
		return (EAttribute)ed2EClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getED2_TreeElements() {
		return (EReference)ed2EClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getModel() {
		return modelEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getModel_Ed2() {
		return (EReference)modelEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EEnum getTreeElementType() {
		return treeElementTypeEEnum;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Ed2Factory getEd2Factory() {
		return (Ed2Factory)getEFactoryInstance();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private boolean isCreated = false;

	/**
	 * Creates the meta-model objects for the package.  This method is
	 * guarded to have no affect on any invocation but its first.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void createPackageContents() {
		if (isCreated) return;
		isCreated = true;

		// Create classes and their features
		eddEClass = createEClass(EDD);
		createEAttribute(eddEClass, EDD__NAME);
		createEReference(eddEClass, EDD__TREE_OBJECTS);
		createEReference(eddEClass, EDD__TREE_PARENTS);

		treeElementEClass = createEClass(TREE_ELEMENT);
		createEAttribute(treeElementEClass, TREE_ELEMENT__INDEX);
		createEAttribute(treeElementEClass, TREE_ELEMENT__NAME);
		createEAttribute(treeElementEClass, TREE_ELEMENT__TYPE);

		nodeEClass = createEClass(NODE);
		createEReference(nodeEClass, NODE__LEAVES);
		createEReference(nodeEClass, NODE__NODES);

		leafEClass = createEClass(LEAF);

		treeParentEClass = createEClass(TREE_PARENT);
		createEReference(treeParentEClass, TREE_PARENT__TREE_OBJECTS);
		createEReference(treeParentEClass, TREE_PARENT__TREE_PARENTS);
		createEAttribute(treeParentEClass, TREE_PARENT__INDEX);
		createEAttribute(treeParentEClass, TREE_PARENT__NAME);
		createEAttribute(treeParentEClass, TREE_PARENT__TYPE);

		treeObjectEClass = createEClass(TREE_OBJECT);
		createEAttribute(treeObjectEClass, TREE_OBJECT__INDEX);
		createEAttribute(treeObjectEClass, TREE_OBJECT__NAME);
		createEAttribute(treeObjectEClass, TREE_OBJECT__TYPE);

		ed2EClass = createEClass(ED2);
		createEAttribute(ed2EClass, ED2__NAME);
		createEReference(ed2EClass, ED2__TREE_ELEMENTS);

		modelEClass = createEClass(MODEL);
		createEReference(modelEClass, MODEL__ED2);

		// Create enums
		treeElementTypeEEnum = createEEnum(TREE_ELEMENT_TYPE);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private boolean isInitialized = false;

	/**
	 * Complete the initialization of the package and its meta-model.  This
	 * method is guarded to have no affect on any invocation but its first.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void initializePackageContents() {
		if (isInitialized) return;
		isInitialized = true;

		// Initialize package
		setName(eNAME);
		setNsPrefix(eNS_PREFIX);
		setNsURI(eNS_URI);

		// Create type parameters

		// Set bounds for type parameters

		// Add supertypes to classes
		nodeEClass.getESuperTypes().add(this.getTreeElement());
		leafEClass.getESuperTypes().add(this.getTreeElement());

		// Initialize classes, features, and operations; add parameters
		initEClass(eddEClass, es.ucm.fdi.emf.model.ed2.EDD.class, "EDD", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getEDD_Name(), ecorePackage.getEString(), "name", null, 0, 1, es.ucm.fdi.emf.model.ed2.EDD.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getEDD_TreeObjects(), this.getTreeObject(), null, "treeObjects", null, 0, -1, es.ucm.fdi.emf.model.ed2.EDD.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getEDD_TreeParents(), this.getTreeParent(), null, "treeParents", null, 0, -1, es.ucm.fdi.emf.model.ed2.EDD.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(treeElementEClass, TreeElement.class, "TreeElement", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getTreeElement_Index(), ecorePackage.getEIntegerObject(), "index", null, 0, 1, TreeElement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getTreeElement_Name(), ecorePackage.getEString(), "name", null, 0, 1, TreeElement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getTreeElement_Type(), this.getTreeElementType(), "type", "empty", 0, 1, TreeElement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(nodeEClass, Node.class, "Node", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getNode_Leaves(), this.getLeaf(), null, "leaves", null, 0, -1, Node.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getNode_Nodes(), this.getNode(), null, "nodes", null, 0, -1, Node.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(leafEClass, Leaf.class, "Leaf", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(treeParentEClass, TreeParent.class, "TreeParent", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getTreeParent_TreeObjects(), this.getTreeObject(), null, "treeObjects", null, 0, -1, TreeParent.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getTreeParent_TreeParents(), this.getTreeParent(), null, "treeParents", null, 0, -1, TreeParent.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getTreeParent_Index(), ecorePackage.getEIntegerObject(), "index", null, 0, 1, TreeParent.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getTreeParent_Name(), ecorePackage.getEString(), "name", null, 0, 1, TreeParent.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getTreeParent_Type(), this.getTreeElementType(), "type", "empty", 0, 1, TreeParent.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(treeObjectEClass, TreeObject.class, "TreeObject", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getTreeObject_Index(), ecorePackage.getEIntegerObject(), "index", null, 0, 1, TreeObject.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getTreeObject_Name(), ecorePackage.getEString(), "name", null, 0, 1, TreeObject.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getTreeObject_Type(), this.getTreeElementType(), "type", null, 0, 1, TreeObject.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(ed2EClass, es.ucm.fdi.emf.model.ed2.ED2.class, "ED2", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getED2_Name(), ecorePackage.getEString(), "name", null, 0, 1, es.ucm.fdi.emf.model.ed2.ED2.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getED2_TreeElements(), this.getTreeElement(), null, "treeElements", null, 0, -1, es.ucm.fdi.emf.model.ed2.ED2.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(modelEClass, Model.class, "Model", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getModel_Ed2(), this.getED2(), null, "ed2", null, 0, 1, Model.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		// Initialize enums and add enum literals
		initEEnum(treeElementTypeEEnum, TreeElementType.class, "TreeElementType");
		addEEnumLiteral(treeElementTypeEEnum, TreeElementType.EMPTY);
		addEEnumLiteral(treeElementTypeEEnum, TreeElementType.YES);
		addEEnumLiteral(treeElementTypeEEnum, TreeElementType.NO);
		addEEnumLiteral(treeElementTypeEEnum, TreeElementType.TRUSTED);
		addEEnumLiteral(treeElementTypeEEnum, TreeElementType.DONT_KNOW);
		addEEnumLiteral(treeElementTypeEEnum, TreeElementType.INADMISSIBLE);

		// Create resource
		createResource(eNS_URI);

		// Create annotations
		// root
		createRootAnnotations();
	}

	/**
	 * Initializes the annotations for <b>root</b>.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void createRootAnnotations() {
		String source = "root";	
		addAnnotation
		  (modelEClass, 
		   source, 
		   new String[] {
		   });
	}

} //Ed2PackageImpl
