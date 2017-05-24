/**
 */
package es.ucm.fdi.emf.model.ed2.impl;

import es.ucm.fdi.emf.model.ed2.Ed2Package;
import es.ucm.fdi.emf.model.ed2.TreeElementType;
import es.ucm.fdi.emf.model.ed2.TreeObject;
import es.ucm.fdi.emf.model.ed2.TreeParent;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

import org.eclipse.emf.ecore.util.EObjectResolvingEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Tree Parent</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link es.ucm.fdi.emf.model.ed2.impl.TreeParentImpl#getTreeObjects <em>Tree Objects</em>}</li>
 *   <li>{@link es.ucm.fdi.emf.model.ed2.impl.TreeParentImpl#getTreeParents <em>Tree Parents</em>}</li>
 *   <li>{@link es.ucm.fdi.emf.model.ed2.impl.TreeParentImpl#getIndex <em>Index</em>}</li>
 *   <li>{@link es.ucm.fdi.emf.model.ed2.impl.TreeParentImpl#getName <em>Name</em>}</li>
 *   <li>{@link es.ucm.fdi.emf.model.ed2.impl.TreeParentImpl#getType <em>Type</em>}</li>
 * </ul>
 *
 * @generated
 */
public class TreeParentImpl extends MinimalEObjectImpl.Container implements TreeParent {
	/**
	 * The cached value of the '{@link #getTreeObjects() <em>Tree Objects</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTreeObjects()
	 * @generated
	 * @ordered
	 */
	protected EList<TreeObject> treeObjects;

	/**
	 * The cached value of the '{@link #getTreeParents() <em>Tree Parents</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTreeParents()
	 * @generated
	 * @ordered
	 */
	protected EList<TreeParent> treeParents;

	/**
	 * The default value of the '{@link #getIndex() <em>Index</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getIndex()
	 * @generated
	 * @ordered
	 */
	protected static final Integer INDEX_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getIndex() <em>Index</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getIndex()
	 * @generated
	 * @ordered
	 */
	protected Integer index = INDEX_EDEFAULT;

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
	 * The default value of the '{@link #getType() <em>Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getType()
	 * @generated
	 * @ordered
	 */
	protected static final TreeElementType TYPE_EDEFAULT = TreeElementType.EMPTY;

	/**
	 * The cached value of the '{@link #getType() <em>Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getType()
	 * @generated
	 * @ordered
	 */
	protected TreeElementType type = TYPE_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected TreeParentImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return Ed2Package.Literals.TREE_PARENT;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<TreeObject> getTreeObjects() {
		if (treeObjects == null) {
			treeObjects = new EObjectResolvingEList<TreeObject>(TreeObject.class, this, Ed2Package.TREE_PARENT__TREE_OBJECTS);
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
			treeParents = new EObjectResolvingEList<TreeParent>(TreeParent.class, this, Ed2Package.TREE_PARENT__TREE_PARENTS);
		}
		return treeParents;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Integer getIndex() {
		return index;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setIndex(Integer newIndex) {
		Integer oldIndex = index;
		index = newIndex;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, Ed2Package.TREE_PARENT__INDEX, oldIndex, index));
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
			eNotify(new ENotificationImpl(this, Notification.SET, Ed2Package.TREE_PARENT__NAME, oldName, name));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TreeElementType getType() {
		return type;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setType(TreeElementType newType) {
		TreeElementType oldType = type;
		type = newType == null ? TYPE_EDEFAULT : newType;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, Ed2Package.TREE_PARENT__TYPE, oldType, type));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case Ed2Package.TREE_PARENT__TREE_OBJECTS:
				return getTreeObjects();
			case Ed2Package.TREE_PARENT__TREE_PARENTS:
				return getTreeParents();
			case Ed2Package.TREE_PARENT__INDEX:
				return getIndex();
			case Ed2Package.TREE_PARENT__NAME:
				return getName();
			case Ed2Package.TREE_PARENT__TYPE:
				return getType();
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
			case Ed2Package.TREE_PARENT__TREE_OBJECTS:
				getTreeObjects().clear();
				getTreeObjects().addAll((Collection<? extends TreeObject>)newValue);
				return;
			case Ed2Package.TREE_PARENT__TREE_PARENTS:
				getTreeParents().clear();
				getTreeParents().addAll((Collection<? extends TreeParent>)newValue);
				return;
			case Ed2Package.TREE_PARENT__INDEX:
				setIndex((Integer)newValue);
				return;
			case Ed2Package.TREE_PARENT__NAME:
				setName((String)newValue);
				return;
			case Ed2Package.TREE_PARENT__TYPE:
				setType((TreeElementType)newValue);
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
			case Ed2Package.TREE_PARENT__TREE_OBJECTS:
				getTreeObjects().clear();
				return;
			case Ed2Package.TREE_PARENT__TREE_PARENTS:
				getTreeParents().clear();
				return;
			case Ed2Package.TREE_PARENT__INDEX:
				setIndex(INDEX_EDEFAULT);
				return;
			case Ed2Package.TREE_PARENT__NAME:
				setName(NAME_EDEFAULT);
				return;
			case Ed2Package.TREE_PARENT__TYPE:
				setType(TYPE_EDEFAULT);
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
			case Ed2Package.TREE_PARENT__TREE_OBJECTS:
				return treeObjects != null && !treeObjects.isEmpty();
			case Ed2Package.TREE_PARENT__TREE_PARENTS:
				return treeParents != null && !treeParents.isEmpty();
			case Ed2Package.TREE_PARENT__INDEX:
				return INDEX_EDEFAULT == null ? index != null : !INDEX_EDEFAULT.equals(index);
			case Ed2Package.TREE_PARENT__NAME:
				return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
			case Ed2Package.TREE_PARENT__TYPE:
				return type != TYPE_EDEFAULT;
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
		result.append(" (index: ");
		result.append(index);
		result.append(", name: ");
		result.append(name);
		result.append(", type: ");
		result.append(type);
		result.append(')');
		return result.toString();
	}

} //TreeParentImpl
