/**
 */
package es.ucm.fdi.emf.model.ed2.impl;

import es.ucm.fdi.emf.model.ed2.ED2;
import es.ucm.fdi.emf.model.ed2.Ed2Package;
import es.ucm.fdi.emf.model.ed2.Model;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Model</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link es.ucm.fdi.emf.model.ed2.impl.ModelImpl#getEd2 <em>Ed2</em>}</li>
 * </ul>
 *
 * @generated
 */
public class ModelImpl extends MinimalEObjectImpl.Container implements Model {
	/**
	 * The cached value of the '{@link #getEd2() <em>Ed2</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getEd2()
	 * @generated
	 * @ordered
	 */
	protected ED2 ed2;
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ModelImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return Ed2Package.Literals.MODEL;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ED2 getEd2() {
		if (ed2 != null && ed2.eIsProxy()) {
			InternalEObject oldEd2 = (InternalEObject)ed2;
			ed2 = (ED2)eResolveProxy(oldEd2);
			if (ed2 != oldEd2) {
				InternalEObject newEd2 = (InternalEObject)ed2;
				NotificationChain msgs = oldEd2.eInverseRemove(this, EOPPOSITE_FEATURE_BASE - Ed2Package.MODEL__ED2, null, null);
				if (newEd2.eInternalContainer() == null) {
					msgs = newEd2.eInverseAdd(this, EOPPOSITE_FEATURE_BASE - Ed2Package.MODEL__ED2, null, msgs);
				}
				if (msgs != null) msgs.dispatch();
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, Ed2Package.MODEL__ED2, oldEd2, ed2));
			}
		}
		return ed2;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ED2 basicGetEd2() {
		return ed2;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetEd2(ED2 newEd2, NotificationChain msgs) {
		ED2 oldEd2 = ed2;
		ed2 = newEd2;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, Ed2Package.MODEL__ED2, oldEd2, newEd2);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setEd2(ED2 newEd2) {
		if (newEd2 != ed2) {
			NotificationChain msgs = null;
			if (ed2 != null)
				msgs = ((InternalEObject)ed2).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - Ed2Package.MODEL__ED2, null, msgs);
			if (newEd2 != null)
				msgs = ((InternalEObject)newEd2).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - Ed2Package.MODEL__ED2, null, msgs);
			msgs = basicSetEd2(newEd2, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, Ed2Package.MODEL__ED2, newEd2, newEd2));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case Ed2Package.MODEL__ED2:
				return basicSetEd2(null, msgs);
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
			case Ed2Package.MODEL__ED2:
				if (resolve) return getEd2();
				return basicGetEd2();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case Ed2Package.MODEL__ED2:
				setEd2((ED2)newValue);
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
			case Ed2Package.MODEL__ED2:
				setEd2((ED2)null);
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
			case Ed2Package.MODEL__ED2:
				return ed2 != null;
		}
		return super.eIsSet(featureID);
	}

} //ModelImpl
