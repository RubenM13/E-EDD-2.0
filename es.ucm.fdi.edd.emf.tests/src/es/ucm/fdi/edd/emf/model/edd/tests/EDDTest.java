/**
 */
package es.ucm.fdi.edd.emf.model.edd.tests;

import es.ucm.fdi.edd.emf.model.edd.EDD;
import es.ucm.fdi.edd.emf.model.edd.EddFactory;

import junit.framework.TestCase;

import junit.textui.TestRunner;

/**
 * <!-- begin-user-doc -->
 * A test case for the model object '<em><b>EDD</b></em>'.
 * <!-- end-user-doc -->
 * @generated
 */
public class EDDTest extends TestCase {

	/**
	 * The fixture for this EDD test case.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected EDD fixture = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static void main(String[] args) {
		TestRunner.run(EDDTest.class);
	}

	/**
	 * Constructs a new EDD test case with the given name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EDDTest(String name) {
		super(name);
	}

	/**
	 * Sets the fixture for this EDD test case.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void setFixture(EDD fixture) {
		this.fixture = fixture;
	}

	/**
	 * Returns the fixture for this EDD test case.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected EDD getFixture() {
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
		setFixture(EddFactory.eINSTANCE.createEDD());
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

} //EDDTest
