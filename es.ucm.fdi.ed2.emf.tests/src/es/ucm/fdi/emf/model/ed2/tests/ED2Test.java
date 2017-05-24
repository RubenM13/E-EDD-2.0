/**
 */
package es.ucm.fdi.emf.model.ed2.tests;

import es.ucm.fdi.emf.model.ed2.ED2;
import es.ucm.fdi.emf.model.ed2.Ed2Factory;

import junit.framework.TestCase;

import junit.textui.TestRunner;

/**
 * <!-- begin-user-doc -->
 * A test case for the model object '<em><b>ED2</b></em>'.
 * <!-- end-user-doc -->
 * @generated
 */
public class ED2Test extends TestCase {

	/**
	 * The fixture for this ED2 test case.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ED2 fixture = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static void main(String[] args) {
		TestRunner.run(ED2Test.class);
	}

	/**
	 * Constructs a new ED2 test case with the given name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ED2Test(String name) {
		super(name);
	}

	/**
	 * Sets the fixture for this ED2 test case.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void setFixture(ED2 fixture) {
		this.fixture = fixture;
	}

	/**
	 * Returns the fixture for this ED2 test case.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ED2 getFixture() {
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
		setFixture(Ed2Factory.eINSTANCE.createED2());
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

} //ED2Test
