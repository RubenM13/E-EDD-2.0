package es.ucm.fdi.edd.ui.views;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.text.BadLocationException;
import org.eclipse.jface.text.IDocument;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.ide.IDE;
import org.erlide.engine.internal.model.erlang.ErlFunction;
import org.erlide.engine.model.erlang.IErlFunctionClause;
import org.erlide.engine.model.erlang.IErlModule;
import org.erlide.engine.model.erlang.ISourceRange;
import org.erlide.engine.model.root.ErlElementKind;
import org.erlide.engine.model.root.IErlElement;
import org.erlide.ui.editors.erl.ErlangEditor;
import org.erlide.ui.editors.util.EditorUtility;
import org.erlide.ui.util.ErlModelUtils;

import es.ucm.fdi.edd.core.exception.EDDException;
import es.ucm.fdi.edd.ui.views.utils.EDDHelper;

/**
 * Clase encargada de gestionar las respuestas realizadas por EddDebugView y EddTreeView
 * @author Ruben
 *
 */
@SuppressWarnings("restriction")
public class EddDebugControler {

	private static EddDebugControler controler;

	private EDDHelper helper;
	private EDDebugView debugView;
	private boolean concurrent;
	private boolean lock = false;

	private EddDebugControler() {}

	/**
	 * Envía la respuesta de la pregunta actual al servidor erlang y actualiza la información mostrada
	 * @param sentence Respuesta a la pregunta
	 */
	public void setAnswer(String sentence) {
		if (!this.isLock()) {
			try {
				this.setAnswer(helper.setAnswer(sentence));
			} catch (EDDException e) {
				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				this.unlock();
			}
		}
	}

	/**
	 * Envía la respuesta de la pregunta dada al servidor erlang y actualiza la información mostrada
	 * @param question Respuesta a la pregunta
	 * @param sentence Número de la pregunta a la que se responde
	 */
	public void setAnswer(int question, String sentence) {
		if (!this.isLock()) {
			try {
				if (this.helper.getCurrentQuestion() != question) {
					this.setAnswer(helper.setAnswer(String.valueOf(question)));
					TimeUnit.MILLISECONDS.sleep(100);
				}
				this.setAnswer(helper.setAnswer(sentence));
			} catch (EDDException e) {
				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				this.unlock();
			}
		}
	}

	/**
	 * Comunica si se esta respondiendo a una pregunta actualmente, en caso negativo se comunica de que se va a poner a responder una pregunta 
	 * @return
	 */
	private synchronized boolean isLock() {
		if (!this.lock) {
			this.lock = true;
			return false;
		} else {
			return true;
		}
	}

	/**
	 * Se cambia el valor de una variable para indicar que se ha terminado el proceso de contestar a la pregunta
	 */
	private synchronized void unlock() {
		this.lock = false;
	}

	private void setAnswer(boolean band) {
		try {
			if (!this.helper.isZoomEnabled()) {
				// Normal
				if (band) {
					//FIXME Revisar buggy_node...
					TimeUnit.MILLISECONDS.sleep(100);
					int buggyNodeIndex = this.helper.getBuggyNode();
					if (!concurrent) {
						boolean startZoomDbg = MessageDialog.openQuestion(this.debugView.getSite().getShell(), "EDD - Buggy node: " + buggyNodeIndex, 
								"Call to a function that contains an error: \n\t'" + this.helper.getInfoQuestionUnformated(buggyNodeIndex) + "'" + 
								"\nPlease, revise the fail clause: \n\t'" + getBuggyClause(buggyNodeIndex) + "'" +
								"\n\nDo you want to follow the debugging session inside this function?");
						if (startZoomDbg) {
							this.debugView.startZoomDebugger();
						} else {
							this.helper.stopEDDebugger();	
						}
					} else {
						String buggyErrorCall = this.helper.getBuggyErrorCall();
						this.debugView.highlightLine(this.helper.getInfoLine(buggyNodeIndex));
						MessageDialog.openError(debugView.getSite().getShell(), "EDD - Buggy node: " + buggyNodeIndex, 
								"This is the reason for the error: \n\r" + buggyErrorCall);
						this.helper.stopEDDebugger();
					}
				}
				
				TimeUnit.MILLISECONDS.sleep(100);
				int goToIndex = this.helper.getCurrentQuestion();
				this.debugView.updateSelection(goToIndex);
			}
			else {
				// Zoom
				if (band) {
					//FIXME Revisar buggy_node...
					TimeUnit.MILLISECONDS.sleep(100);
					int buggyNodeIndex = this.helper.getBuggyNode();
					String buggyErrorCall = this.helper.getBuggyErrorCall();
					MessageDialog.openError(debugView.getSite().getShell(), "EDD - Buggy node: " + buggyNodeIndex, 
							"This is the reason for the error: \n\t'" + buggyErrorCall + "'");
				}
				
				TimeUnit.MILLISECONDS.sleep(100);
				int goToIndex = this.helper.getCurrentQuestion();
				this.debugView.updateZoomSelection(goToIndex);
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (EDDException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private String getBuggyClause(int buggyNodeIndex) throws EDDException, CoreException, BadLocationException {
		IFile erlFile = ResourcesPlugin.getWorkspace().getRoot().getFile(this.debugView.getDebugFile().getFullPath());
		IWorkbenchPage page = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage();
		IEditorPart editorPart = IDE.openEditor(page, erlFile);
		ErlangEditor editor = (ErlangEditor) editorPart;
		
		int clause = this.helper.getInfoClause(buggyNodeIndex);
//		String file = this.helper.getInfoFile(buggyNodeIndex);
//		int line = this.helper.getInfoLine(buggyNodeIndex);
//		
//		String m = this.helper.getModule(buggyNodeIndex);
//		String f = this.helper.getFunction(buggyNodeIndex);
		int a = this.helper.getArity(buggyNodeIndex);
		
		IErlModule fModule = ErlModelUtils.getModule(editor.getEditorInput());
        if (fModule != null) {
            fModule.open(null);
        }
        String buggyClause = null;
        List<IErlElement> children = fModule.getChildren();
        for (IErlElement erlElement : children) {
        	ErlElementKind kind = erlElement.getKind();
        	switch (kind) {
				case FUNCTION: {
					ErlFunction erlFunction = (ErlFunction) erlElement;
					if (erlFunction.getArity() == a) {
						List<IErlFunctionClause> erlClauses = erlFunction.getClauses();
						if (erlClauses != null && !erlClauses.isEmpty()) {
							int erlClausesSize = erlClauses.size();
							if (clause <= erlClausesSize) {
								IErlFunctionClause iErlFunctionClause = erlClauses.get(clause);
								ISourceRange sourceRange = iErlFunctionClause.getSourceRange();

								IDocument document = editor.getDocument();
								int offset = sourceRange.getOffset();
								int length = sourceRange.getLength();
								buggyClause = document.get(offset, length);

								EditorUtility.revealInEditor(editorPart, iErlFunctionClause.getSourceRange());
							}
						} else {
							ISourceRange sourceRange = erlFunction.getSourceRange();

							IDocument document = editor.getDocument();
							int offset = sourceRange.getOffset();
							int length = sourceRange.getLength();
							buggyClause = document.get(offset, length);

							EditorUtility.revealInEditor(editorPart, erlFunction.getSourceRange());
						}
					}
				}

				default:
					break;
			}
        }

		return buggyClause;
	}

	public void setDebugView(EDDebugView debugView) {
		this.debugView = debugView;
	}

	public void setHelper(EDDHelper helper) {
		this.helper = helper;
	}

	public void setConcurrent(boolean concurrent) {
		this.concurrent = concurrent;
	}

	public static EddDebugControler getEddDebugControler() {
		if (controler == null) {
			controler = new EddDebugControler();
		}
		return controler;
	}

}
