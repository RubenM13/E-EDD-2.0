/**
 */
package es.ucm.fdi.emf.model.ed2.util;

import es.ucm.fdi.emf.model.ed2.*;

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notifier;

import org.eclipse.emf.common.notify.impl.AdapterFactoryImpl;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * The <b>Adapter Factory</b> for the model.
 * It provides an adapter <code>createXXX</code> method for each class of the model.
 * <!-- end-user-doc -->
 * @see es.ucm.fdi.emf.model.ed2.Ed2Package
 * @generated
 */
public class Ed2AdapterFactory extends AdapterFactoryImpl {
	/**
	 * The cached model package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected static Ed2Package modelPackage;

	/**
	 * Creates an instance of the adapter factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Ed2AdapterFactory() {
		if (modelPackage == null) {
			modelPackage = Ed2Package.eINSTANCE;
		}
	}

	/**
	 * Returns whether this factory is applicable for the type of the object.
	 * <!-- begin-user-doc -->
	 * This implementation returns <code>true</code> if the object is either the model's package or is an instance object of the model.
	 * <!-- end-user-doc -->
	 * @return whether this factory is applicable for the type of the object.
	 * @generated
	 */
	@Override
	public boolean isFactoryForType(Object object) {
		if (object == modelPackage) {
			return true;
		}
		if (object instanceof EObject) {
			return ((EObject)object).eClass().getEPackage() == modelPackage;
		}
		return false;
	}

	/**
	 * The switch that delegates to the <code>createXXX</code> methods.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected Ed2Switch<Adapter> modelSwitch =
		new Ed2Switch<Adapter>() {
			@Override
			public Adapter caseEDD(EDD object) {
				return createEDDAdapter();
			}
			@Override
			public Adapter caseTreeElement(TreeElement object) {
				return createTreeElementAdapter();
			}
			@Override
			public Adapter caseNode(Node object) {
				return createNodeAdapter();
			}
			@Override
			public Adapter caseLeaf(Leaf object) {
				return createLeafAdapter();
			}
			@Override
			public Adapter caseTreeParent(TreeParent object) {
				return createTreeParentAdapter();
			}
			@Override
			public Adapter caseTreeObject(TreeObject object) {
				return createTreeObjectAdapter();
			}
			@Override
			public Adapter caseED2(ED2 object) {
				return createED2Adapter();
			}
			@Override
			public Adapter caseModel(Model object) {
				return createModelAdapter();
			}
			@Override
			public Adapter defaultCase(EObject object) {
				return createEObjectAdapter();
			}
		};

	/**
	 * Creates an adapter for the <code>target</code>.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param target the object to adapt.
	 * @return the adapter for the <code>target</code>.
	 * @generated
	 */
	@Override
	public Adapter createAdapter(Notifier target) {
		return modelSwitch.doSwitch((EObject)target);
	}


	/**
	 * Creates a new adapter for an object of class '{@link es.ucm.fdi.emf.model.ed2.EDD <em>EDD</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see es.ucm.fdi.emf.model.ed2.EDD
	 * @generated
	 */
	public Adapter createEDDAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link es.ucm.fdi.emf.model.ed2.TreeElement <em>Tree Element</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see es.ucm.fdi.emf.model.ed2.TreeElement
	 * @generated
	 */
	public Adapter createTreeElementAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link es.ucm.fdi.emf.model.ed2.Node <em>Node</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see es.ucm.fdi.emf.model.ed2.Node
	 * @generated
	 */
	public Adapter createNodeAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link es.ucm.fdi.emf.model.ed2.Leaf <em>Leaf</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see es.ucm.fdi.emf.model.ed2.Leaf
	 * @generated
	 */
	public Adapter createLeafAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link es.ucm.fdi.emf.model.ed2.TreeParent <em>Tree Parent</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see es.ucm.fdi.emf.model.ed2.TreeParent
	 * @generated
	 */
	public Adapter createTreeParentAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link es.ucm.fdi.emf.model.ed2.TreeObject <em>Tree Object</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see es.ucm.fdi.emf.model.ed2.TreeObject
	 * @generated
	 */
	public Adapter createTreeObjectAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link es.ucm.fdi.emf.model.ed2.ED2 <em>ED2</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see es.ucm.fdi.emf.model.ed2.ED2
	 * @generated
	 */
	public Adapter createED2Adapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link es.ucm.fdi.emf.model.ed2.Model <em>Model</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see es.ucm.fdi.emf.model.ed2.Model
	 * @generated
	 */
	public Adapter createModelAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for the default case.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @generated
	 */
	public Adapter createEObjectAdapter() {
		return null;
	}

} //Ed2AdapterFactory
