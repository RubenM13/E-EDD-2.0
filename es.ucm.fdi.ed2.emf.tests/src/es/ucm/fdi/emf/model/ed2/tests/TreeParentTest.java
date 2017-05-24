/**
 */
package es.ucm.fdi.emf.model.ed2.tests;

import es.ucm.fdi.emf.model.ed2.Ed2Factory;
import es.ucm.fdi.emf.model.ed2.TreeParent;

import junit.framework.TestCase;

import junit.textui.TestRunner;

/**
 * <!-- begin-user-doc -->
 * A test case for the model object '<em><b>Tree Parent</b></em>'.
 * <!-- end-user-doc -->
 * @generated
 */
public class TreeParentTest extends TestCase {

	/**
	 * The fixture for this Tree Parent test case.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected TreeParent fixture = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static void main(String[] args) {
		TestRunner.run(TreeParentTest.class);
	}

	/**
	 * Constructs a new Tree Parent test case with the given name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TreeParentTest(String name) {
		super(name);
	}

	/**
	 * Sets the fixture for this Tree Parent test case.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void setFixture(TreeParent fixture) {
		this.fixture = fixture;
	}

	/**
	 * Returns the fixture for this Tree Parent test case.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected TreeParent getFixture() {
		return fixture;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see junit.framework.TestCase#setUp()
	 * @generated
	 */
	@Override
	protected void setUp() throws Exception {
		setFixture(Ed2Factory.eINSTANCE.createTreeParent());
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see junit.framework.TestCase#tearDown()
	 * @generated
	 */
	@Override
	protected void tearDown() throws Exception {
		setFixture(null);
	}

} //TreeParentTest
