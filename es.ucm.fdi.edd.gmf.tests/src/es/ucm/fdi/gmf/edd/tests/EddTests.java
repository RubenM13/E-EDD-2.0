/**
 */
package es.ucm.fdi.gmf.edd.tests;

import junit.framework.Test;
import junit.framework.TestSuite;

import junit.textui.TestRunner;

/**
 * <!-- begin-user-doc -->
 * A test suite for the '<em><b>edd</b></em>' package.
 * <!-- end-user-doc -->
 * @generated
 */
public class EddTests extends TestSuite {

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static void main(String[] args) {
		TestRunner.run(suite());
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static Test suite() {
		TestSuite suite = new EddTests("edd Tests");
		suite.addTestSuite(TreeElementTest.class);
		suite.addTestSuite(BlockTest.class);
		return suite;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EddTests(String name) {
		super(name);
	}

} //EddTests
