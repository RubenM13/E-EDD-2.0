/**
 */
package es.ucm.fdi.emf.model.ed2.impl;

import es.ucm.fdi.emf.model.ed2.EDD;
import es.ucm.fdi.emf.model.ed2.Ed2Package;
import es.ucm.fdi.emf.model.ed2.TreeObject;
import es.ucm.fdi.emf.model.ed2.TreeParent;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>EDD</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link es.ucm.fdi.emf.model.ed2.impl.EDDImpl#getName <em>Name</em>}</li>
 *   <li>{@link es.ucm.fdi.emf.model.ed2.impl.EDDImpl#getTreeObjects <em>Tree Objects</em>}</li>
 *   <li>{@link es.ucm.fdi.emf.model.ed2.impl.EDDImpl#getTreeParents <em>Tree Parents</em>}</li>
 * </ul>
 *
 * @generated
 */
public class EDDImpl extends MinimalEObjectImpl.Container implements EDD {
	/**
	 * The default value of the '{@link #getName() <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getName()
	 * @generated
	 * @ordered
	 */
	protected static final String NAME_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getName() <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getName()
	 * @generated
	 * @ordered
	 */
	protected String name = NAME_EDEFAULT;

	/**
	 * The cached value of the '{@link #getTreeObjects() <em>Tree Objects</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTreeObjects()
	 * @generated
	 * @ordered
	 */
	protected EList<TreeObject> treeObjects;

	/**
	 * The cached value of the '{@link #getTreeParents() <em>Tree Parents</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTreeParents()
	 * @generated
	 * @ordered
	 */
	protected EList<TreeParent> treeParents;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected EDDImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return Ed2Package.Literals.EDD;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getName() {
		return name;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setName(String newName) {
		String oldName = name;
		name = newName;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, Ed2Package.EDD__NAME, oldName, name));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<TreeObject> getTreeObjects() {
		if (treeObjects == null) {
			treeObjects = new EObjectContainmentEList.Resolving<TreeObject>(TreeObject.class, this, Ed2Package.EDD__TREE_OBJECTS);
		}
		return treeObjects;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<TreeParent> getTreeParents() {
		if (treeParents == null) {
			treeParents = new EObjectContainmentEList.Resolving<TreeParent>(TreeParent.class, this, Ed2Package.EDD__TREE_PARENTS);
		}
		return treeParents;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case Ed2Package.EDD__TREE_OBJECTS:
				return ((InternalEList<?>)getTreeObjects()).basicRemove(otherEnd, msgs);
			case Ed2Package.EDD__TREE_PARENTS:
				return ((InternalEList<?>)getTreeParents()).basicRemove(otherEnd, msgs);
		}
		return super.eInverseRemove(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case Ed2Package.EDD__NAME:
				return getName();
			case Ed2Package.EDD__TREE_OBJECTS:
				return getTreeObjects();
			case Ed2Package.EDD__TREE_PARENTS:
				return getTreeParents();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case Ed2Package.EDD__NAME:
				setName((String)newValue);
				return;
			case Ed2Package.EDD__TREE_OBJECTS:
				getTreeObjects().clear();
				getTreeObjects().addAll((Collection<? extends TreeObject>)newValue);
				return;
			case Ed2Package.EDD__TREE_PARENTS:
				getTreeParents().clear();
				getTreeParents().addAll((Collection<? extends TreeParent>)newValue);
				return;
		}
		super.eSet(featureID, newValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eUnset(int featureID) {
		switch (featureID) {
			case Ed2Package.EDD__NAME:
				setName(NAME_EDEFAULT);
				return;
			case Ed2Package.EDD__TREE_OBJECTS:
				getTreeObjects().clear();
				return;
			case Ed2Package.EDD__TREE_PARENTS:
				getTreeParents().clear();
				return;
		}
		super.eUnset(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean eIsSet(int featureID) {
		switch (featureID) {
			case Ed2Package.EDD__NAME:
				return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
			case Ed2Package.EDD__TREE_OBJECTS:
				return treeObjects != null && !treeObjects.isEmpty();
			case Ed2Package.EDD__TREE_PARENTS:
				return treeParents != null && !treeParents.isEmpty();
		}
		return super.eIsSet(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String toString() {
		if (eIsProxy()) return super.toString();

		StringBuffer result = new StringBuffer(super.toString());
		result.append(" (name: ");
		result.append(name);
		result.append(')');
		return result.toString();
	}

} //EDDImpl
