/**
 *
 * $Id$
 */
package es.ucm.fdi.edd.emf.model.edd.validation;

import es.ucm.fdi.edd.emf.model.edd.TreeElement;

import org.eclipse.emf.common.util.EList;

/**
 * A sample validator interface for {@link es.ucm.fdi.edd.emf.model.edd.Node}.
 * This doesn't really do anything, and it's not a real EMF artifact.
 * It was generated by the org.eclipse.emf.examples.generator.validator plug-in to illustrate how EMF's code generator can be extended.
 * This can be disabled with -vmargs -Dorg.eclipse.emf.examples.generator.validator=false.
 */
public interface NodeValidator {
	boolean validate();

	boolean validateChildren(EList<TreeElement> value);
}