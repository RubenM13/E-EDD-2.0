/**
 */
package es.ucm.fdi.emf.model.ed2.impl;

import es.ucm.fdi.emf.model.ed2.Ed2Package;
import es.ucm.fdi.emf.model.ed2.Leaf;
import es.ucm.fdi.emf.model.ed2.Node;
import java.util.Collection;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Node</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link es.ucm.fdi.emf.model.ed2.impl.NodeImpl#getLeaves <em>Leaves</em>}</li>
 *   <li>{@link es.ucm.fdi.emf.model.ed2.impl.NodeImpl#getNodes <em>Nodes</em>}</li>
 * </ul>
 *
 * @generated
 */
public class NodeImpl extends TreeElementImpl implements Node {
	/**
	 * The cached value of the '{@link #getLeaves() <em>Leaves</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getLeaves()
	 * @generated
	 * @ordered
	 */
	protected EList<Leaf> leaves;

	/**
	 * The cached value of the '{@link #getNodes() <em>Nodes</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getNodes()
	 * @generated
	 * @ordered
	 */
	protected EList<Node> nodes;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected NodeImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return Ed2Package.Literals.NODE;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Leaf> getLeaves() {
		if (leaves == null) {
			leaves = new EObjectContainmentEList.Resolving<Leaf>(Leaf.class, this, Ed2Package.NODE__LEAVES);
		}
		return leaves;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Node> getNodes() {
		if (nodes == null) {
			nodes = new EObjectContainmentEList.Resolving<Node>(Node.class, this, Ed2Package.NODE__NODES);
		}
		return nodes;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case Ed2Package.NODE__LEAVES:
				return ((InternalEList<?>)getLeaves()).basicRemove(otherEnd, msgs);
			case Ed2Package.NODE__NODES:
				return ((InternalEList<?>)getNodes()).basicRemove(otherEnd, msgs);
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
			case Ed2Package.NODE__LEAVES:
				return getLeaves();
			case Ed2Package.NODE__NODES:
				return getNodes();
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
			case Ed2Package.NODE__LEAVES:
				getLeaves().clear();
				getLeaves().addAll((Collection<? extends Leaf>)newValue);
				return;
			case Ed2Package.NODE__NODES:
				getNodes().clear();
				getNodes().addAll((Collection<? extends Node>)newValue);
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
			case Ed2Package.NODE__LEAVES:
				getLeaves().clear();
				return;
			case Ed2Package.NODE__NODES:
				getNodes().clear();
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
			case Ed2Package.NODE__LEAVES:
				return leaves != null && !leaves.isEmpty();
			case Ed2Package.NODE__NODES:
				return nodes != null && !nodes.isEmpty();
		}
		return super.eIsSet(featureID);
	}

} //NodeImpl
