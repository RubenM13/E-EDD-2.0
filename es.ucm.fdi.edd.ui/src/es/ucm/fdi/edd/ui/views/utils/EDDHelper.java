package es.ucm.fdi.edd.ui.views.utils;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.TimeUnit;

import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.commands.operations.OperationHistoryFactory;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Path;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.xmi.XMIResource;
import org.eclipse.emf.ecore.xmi.XMLResource;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceImpl;
import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.emf.transaction.util.TransactionUtil;
import org.eclipse.gmf.runtime.common.core.command.CommandResult;
import org.eclipse.gmf.runtime.diagram.core.services.ViewService;
import org.eclipse.gmf.runtime.emf.commands.core.command.AbstractTransactionalCommand;
import org.eclipse.gmf.runtime.emf.core.GMFEditingDomainFactory;
import org.eclipse.gmf.runtime.notation.Bounds;
import org.eclipse.gmf.runtime.notation.Diagram;
import org.eclipse.gmf.runtime.notation.Edge;
import org.eclipse.gmf.runtime.notation.NotationFactory;
import org.eclipse.gmf.runtime.notation.NotationPackage;
import org.eclipse.gmf.runtime.notation.RelativeBendpoints;
import org.eclipse.gmf.runtime.notation.datatype.RelativeBendpoint;
import org.eclipse.jface.dialogs.ProgressMonitorDialog;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.actions.WorkspaceModifyOperation;
import org.eclipse.ui.ide.undo.CreateFileOperation;
import org.eclipse.ui.ide.undo.WorkspaceUndoUtil;

import es.ucm.fdi.edd.core.erlang.Erlang2Java;
import es.ucm.fdi.edd.core.erlang.concurrent.model.ErlangMessage;
import es.ucm.fdi.edd.core.erlang.concurrent.model.ErlangThread;
import es.ucm.fdi.edd.core.erlang.model.EddEdge;
import es.ucm.fdi.edd.core.erlang.model.EddInfo;
import es.ucm.fdi.edd.core.erlang.model.EddModel;
import es.ucm.fdi.edd.core.erlang.model.EddState;
import es.ucm.fdi.edd.core.erlang.model.MFA;
import es.ucm.fdi.edd.core.erlang.model.ZoomDebugTree;
import es.ucm.fdi.edd.core.erlang.model.iDebugTree;
import es.ucm.fdi.edd.core.erlang.model.iEddVertex;
import es.ucm.fdi.edd.core.exception.EDDException;
import es.ucm.fdi.edd.core.graphviz.GraphViz;
import es.ucm.fdi.edd.core.json.model.JsonDocument;
import es.ucm.fdi.edd.core.json.utils.JsonHelper;
import es.ucm.fdi.edd.core.json.utils.JsonUtils;
import es.ucm.fdi.edd.ui.Activator;
import es.ucm.fdi.edd.ui.preferences.PreferenceConstants;
import es.ucm.fdi.emf.model.ed2.ED2;
import es.ucm.fdi.emf.model.ed2.Ed2Factory;
import es.ucm.fdi.emf.model.ed2.Leaf;
import es.ucm.fdi.emf.model.ed2.Model;
import es.ucm.fdi.emf.model.ed2.Node;
import es.ucm.fdi.emf.model.ed2.TreeElement;
import es.ucm.fdi.emf.model.ed2.TreeElementType;
import es.ucm.fdi.emf.model.ed2.diagram.edit.parts.ModelEditPart;
import es.ucm.fdi.emf.model.ed2.diagram.part.Ed2DiagramEditorPlugin;
import es.ucm.fdi.emf.model.ed2.diagram.part.Ed2DiagramEditorUtil;
import es.ucm.fdi.emf.model.ed2.diagram.part.Ed2VisualIDRegistry;
import es.ucm.fdi.emf.model.ed2.presentation.Ed2EditorPlugin;

/**
 * EDD Helper 
 */
public class EDDHelper {
	
	private static final int TMAX = 10; // 10 seconds.
	
	private Erlang2Java e2j;
	private EddModel eddModel;
	
	/**
	 * 
	 */
	public EDDHelper() {}
	
	protected void restartDebugger(String buggyCall, String location) {
		e2j.restartDebugger(buggyCall, location);
	}

	/**
	 * @throws Exception 
	 */
	public boolean startEDDebugger(String buggyCall, String location) {
		this.initErlangServer();
		e2j.initialize(buggyCall, location);
//		if (e2j.isLoaded()) {
		{
			// FIXME Revisar éste algoritmo, espera 10s. 
			boolean finished = false;
			int wait = 0;
			while(!finished && wait<TMAX) {
				eddModel = e2j.getEddModel();
				if (eddModel != null && eddModel.getDebugTree() != null) { 
					finished = true;
					return true;
				}
				
				try {
					TimeUnit.SECONDS.sleep(1);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				wait++;
			}
		}
		return false;
	}

	/**
	 * Comunica la ejecución dela petición del diagrama de secuencia
	 * @param buggyCall
	 * @param location
	 * @param threads
	 * @param messages
	 * @throws Exception
	 */
	public void startConcurrent(String buggyCall, String location, List<ErlangThread> threads, List<ErlangMessage> messages) throws Exception {
		this.initErlangServer();
		this.e2j.initConcurrent(buggyCall, location, threads, messages, 500);
		eddModel = e2j.getEddModel();
	}

	private void initErlangServer() {
		if (this.e2j == null) {
			IPreferenceStore store = Activator.getDefault().getPreferenceStore();
			String eddPath = store.getString(PreferenceConstants.JSERVER_PATH); 
			e2j = new Erlang2Java(eddPath);
		}
	}

	public void stopEDDebugger() throws Exception {
		if (e2j != null) {
			e2j.stopServer();
			e2j = null;
		}
	}
	
	public boolean startZoomDebugger() throws EDDException {
		validateEddModel();
		
		String buggyErrorCall = eddModel.getBuggyErrorCall();
		if (e2j != null && buggyErrorCall != null) {
			e2j.startZoomDebug(buggyErrorCall);
		}
		else {
			System.err.println("The zoom debugger cannot be initialized correctly");
			return false;
		}
		
		return true;
	}

	/**
	 * @param content
	 * @param path
	 */
	public void writeFile(String content, IPath path) {
		try {
			IFile iFile = Activator.getRoot().getFile(path);
			if (iFile.exists()) {
				iFile.delete(IResource.NONE, new NullProgressMonitor());
			}
			
			InputStream source = new ByteArrayInputStream(content.getBytes(StandardCharsets.UTF_8));
			iFile.create(source, IResource.NONE, new NullProgressMonitor());
			iFile.refreshLocal(IResource.DEPTH_INFINITE, null);			
		} catch (CoreException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * @return
	 * @throws EDDException 
	 */
	public Integer getQuestionsSize() throws EDDException {
		validateEddModel();
		
		List<EddEdge> edgesMap;
		if (isZoomEnabled()) {
			iDebugTree zoomDebugTree = eddModel.getZoomDebugTree();
			edgesMap = zoomDebugTree.getEdgesMap();
		}
		else {
			iDebugTree debugTree = eddModel.getDebugTree();
			edgesMap = debugTree.getEdgesMap();
		}
		
		if (edgesMap != null) {
			return edgesMap.size();
		}
		
		return 0;
	}
	
	/**
	 * @param i
	 * @return
	 * 
	 * @throws EDDException 
	 */
	public String getQuestion(int index) throws EDDException {
		validateEddModel();
		Map<Integer, ? extends iEddVertex> vertices;
		if (isZoomEnabled()) {
			vertices = eddModel.getZoomDebugTree().getVertexesMap();	
		} 
		else {
			vertices = eddModel.getDebugTree().getVertexesMap();	
		}
		iEddVertex vertex = vertices.get(index);
		if (vertex != null) {
			String question = vertex.getQuestionText();
			String fixQuestion = question.replace("\\l", "\n");
			return fixQuestion;
		}
		
		return "";
	}
	
	public Integer getCurrentQuestion() throws EDDException {
		validateEddModel();
		if (isZoomEnabled()) {
			return eddModel.getCurrentZoomQuestionIndex();	
		}
		else {
			return eddModel.getCurrentQuestionIndex();	
		}
	}
	
	public String getCurrentQuestionText() throws EDDException {
		validateEddModel();
		if (isZoomEnabled()) {
			return eddModel.getCurrentZoomQuestion();	
		}
		else {
			return null;	
		}
	}
	
	public boolean isZoomEnabled() throws EDDException {
		validateEddModel();
		
		return eddModel.getZoomDebugTree() != null;
	}

	private EddInfo getInfo(int index) throws EDDException {
		validateEddModel();
		
		iEddVertex vertex;
		if (isZoomEnabled()) {
			vertex = eddModel.getZoomDebugTree().getVertexesMap().get(index);
		}
		else {
			vertex = eddModel.getDebugTree().getVertexesMap().get(index);	
		}
		
		return vertex.getInfo();
	}
	
	private MFA getMfa(int index) throws EDDException {
		validateEddModel();
		
		iEddVertex vertex;
		if (isZoomEnabled()) {
			vertex = eddModel.getZoomDebugTree().getVertexesMap().get(index);
		}
		else {
			vertex = eddModel.getDebugTree().getVertexesMap().get(index);	
		}
		
		return vertex.getMfa();
	}
	
	public String getInfoQuestionUnformated(int index) throws EDDException {
		EddInfo info = getInfo(index);
		if (info != null) {
			return info.getQuestionUnformatted();
		}
		
		return null;
	}
	
	public int getInfoClause(int index) throws EDDException {
		EddInfo info = getInfo(index);
		if (info != null) {
			return info.getClause().intValue()-1;
		}
		
		return -1;
	}
	
	public String getInfoFile(int index) throws EDDException {
		EddInfo info = getInfo(index);
		if (info != null) {
			return info.getFile();
		}
		
		return null;
	}
	
	public int getInfoLine(int index) throws EDDException {
		EddInfo info = getInfo(index);
		if (info != null) {
			return info.getLine().intValue()-1;
		}
		
		return -1;
	}
	
	public String getModule(int index) throws EDDException {
		MFA mfa = getMfa(index);
		if (mfa != null) {
			return mfa.getModule();
		}
		
		return null;
	}

	public String getFunction(int index) throws EDDException {
		MFA mfa = getMfa(index);
		if (mfa != null) {
			return mfa.getFunction();
		}
		
		return null;
	}

	public int getArity(int index) throws EDDException {
		MFA mfa = getMfa(index);
		if (mfa != null) {
			return mfa.getArity().intValue();
		}
		
		return -1;
	}
	
	public boolean setAnswer(String reply) throws EDDException {
		e2j.sendAnswer(reply);
		
		try {
			TimeUnit.MILLISECONDS.sleep(500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		Integer buggyNode = getBuggyNode();
		if (buggyNode != null && buggyNode != -1) {
			return true;
		}
		
		return false;
	}
	
	public boolean isBuggyNode() throws EDDException {
		validateEddModel();
		Integer buggyNode = eddModel.getBuggyNodeIndex();
		return buggyNode != null && buggyNode != -1  ? true : false;
	}
	
	public Integer getBuggyNode() throws EDDException {
		validateEddModel();
		
		return eddModel.getBuggyNodeIndex();
	}
	
	public String getBuggyErrorCall() throws EDDException {
		validateEddModel();
		
		return eddModel.getBuggyErrorCall();
	}
	
	public EddState getState() throws EDDException {
		validateEddModel();
		
		return eddModel.getState();
	}
	
	public LinkedHashMap<String, String> getAnswers(int i) {
		iEddVertex vertex = this.eddModel.getDebugTree().getVertexesMap().get(i);
		if (vertex != null) {
			List<String> answersList = vertex.getActualAnswerTextList();
			if (answersList != null && !answersList.isEmpty()) {
				LinkedHashMap<String, String> answersMap = new LinkedHashMap<String, String>();
				for (String answer : answersList) {
					answersMap.put(answer, answer);
				}
				answersMap.put("undo", "undo");
				answersMap.put("abort", "abort");
				return answersMap;
			}
		}
		return null;
	}
	
	public LinkedHashMap<String, String> getZoomAnswers() {
		try {
			validateEddModel();
		} catch (EDDException e) {
			e.printStackTrace();
		}
		
		List<String> answerList = eddModel.getAnswerList();
		if (answerList != null) {
			
			LinkedList<String> answerListTmp = new LinkedList<String>();
			for (String key : new LinkedList<String>(answerList)) {
				try {
					Integer.parseInt(key);
					answerList.remove(key);
					answerListTmp.add(key);
				}
				catch (NumberFormatException e) {
					// continue
				}
			}
			Collections.sort(answerListTmp);
			answerList.addAll(0, answerListTmp);
			
			LinkedHashMap<String, String> answersMap = new LinkedHashMap<String, String>();
			for (String key : answerList) {
				switch (key) {
					case "y":
						answersMap.put("y", "Yes");		
						break;
					case "n":	
						answersMap.put("n", "No");
						break;
					case "t":
						answersMap.put("t", "Trusted");
						break;
					case "d":
						answersMap.put("d", "Don't know");
						break;
					case "i":
						answersMap.put("i", "Inadmissible");
						break;
					case "u":
						answersMap.put("u", "Undo ");
						break;
					case "a":
						answersMap.put("a", "Abort");
						break;
						
					case "s":
						answersMap.put("s", "Change strategy");
						break;
						
					default:
						answersMap.put(key, key);
						break;
				}
			}
			
			return answersMap;
		}
		
		return null;
	}

	private void validateEddModel() throws EDDException {
		if (eddModel == null) {
			throw new EDDException("The 'eddModel' must not be null");
		}
	}
	
	/**
	 * @throws IOException 
	 * @throws EDDException 
	 */
	public String buildDOT(boolean isZoom, Integer highlightNode, boolean cw) throws IOException, EDDException {
		validateEddModel();
		
		GraphViz gv = new GraphViz();
		gv.addln(gv.start_graph());
		gv.addln("\tgraph [dpi = 200]; ");
//		gv.addln("\tratio=\"fill\"; ");
		gv.addln("\tsize=\"8.3,11.7!\"; ");
		gv.addln("\tmargin = 0; ");
//		gv.addln("\tnode [shape=circle]; ");
		gv.addln("\tnode [style=filled]; ");
		gv.addln("\tnode [fillcolor=\"#EEEEEE\"]; "); // Relleno gris
//		gv.addln("\tnode [color=\"#EEEEEE\"]; "); // Borde gris
		gv.addln("\tedge [color=\"#7092BE\"]; ");
		
		EddState state = eddModel.getState();
		List<Integer> vertexList = null;
		List<Integer> correctList = null;
		List<Integer> notCorrectList = null;
		List<Integer> unknownList = null;
		if (state != null) {
			vertexList = state.getVertexList();
			correctList = state.getCorrectList();
			notCorrectList = state.getNotCorrectList();
			unknownList = state.getUnknownList();
		}
		
		
		Map<Integer, ? extends iEddVertex> vertexesMap;
		List<EddEdge> edgesMap;
		if (isZoom) {
			iDebugTree debugTree = eddModel.getZoomDebugTree();
			vertexesMap = debugTree.getVertexesMap();
			edgesMap = debugTree.getEdgesMap();
		}
		else {
			iDebugTree debugTree = eddModel.getDebugTree();
			vertexesMap = debugTree.getVertexesMap();
			edgesMap = debugTree.getEdgesMap();
		}
			
		for (Entry<Integer, ? extends iEddVertex> entry : vertexesMap.entrySet()) {
			Integer key = entry.getKey();
			iEddVertex vertice = entry.getValue();
			Integer node = vertice.getIdNode();
			String question = vertice.getQuestionText();
			question = question.replace("\"", "\\\"");
			if (highlightNode !=null && node == highlightNode) {
				gv.addln(node + " [label=\"" + node + ". " + question + "\", shape=rectangle, style=filled, fillcolor=\"#C0C0FF\"];");
			}
			/*if (vertexList.contains(node)) {
				gv.addln(node + " [label=\"" + node + ". " + question + "\", style=filled, fillcolor=\"#ED1C3A\"];");
			}*/ else if (correctList!= null && correctList.contains(node)) {
				gv.addln(node + " [label=\"" + node + ". " + question + "\", style=filled, fillcolor=\"#80FF80\"];"); // Verde
			} else if (notCorrectList!= null && notCorrectList.contains(node)) {
				gv.addln(node + " [label=\"" + node + ". " + question + "\", style=filled, fillcolor=\"#ED1C3A\"];"); // Rojo
			} else if (unknownList!= null && unknownList.contains(node)) {
				gv.addln(node + " [label=\"" + node + ". " + question + "\", style=filled, fillcolor=\"#FFFF80\"];"); // Amarillo
			} else {
				gv.addln(node + " [label=\"" + node + ". " + question + "\"];");
			}
		}
		
		for (EddEdge edge : edgesMap) {
			int from = edge.getFrom();
			int to = edge.getTo();
			
			if (highlightNode !=null && from == highlightNode) {
				gv.addln(from + " -> "+ to + " [style=filled, color=\"#ED1C3A\", fillcolor=\"#C0C0FF\"];");
			}
			/*if (vertexList.contains(node)) {
				gv.addln(from + " -> "+ to + " [style=filled, color=\"#ED1C3A\", fillcolor=\"#ED1C3A\"];");
			}*/ else if (correctList!= null && correctList.contains(from)) {
				gv.addln(from + " -> "+ to + " [style=filled, color=\"#80FF80\", fillcolor=\"#80FF80\"];"); // Verde
			} else if (notCorrectList!= null && notCorrectList.contains(from)) {
				gv.addln(from + " -> "+ to + " [style=filled, color=\"#ED1C3A\", fillcolor=\"#ED1C3A\"];"); // Rojo
			} else if (unknownList!= null && unknownList.contains(from)) {
				gv.addln(from + " -> "+ to + " [style=filled, color=\"#FFFF80\", fillcolor=\"#FFFF80\"];"); // Amarillo
			} else {
				gv.addln(from + " -> "+ to + ";");
			}
		}
		
		// Para cambiar el sentido
		if (cw) {
			gv.addln("rankdir=LR;"); 
		}
		gv.addln(gv.end_graph());
		
		String dotSource = gv.getDotSource();
//		FileManager.writeToFile(file.getAbsolutePath(), dotSource);
		
//		String type = "gif";
//		File out = new File("files/output/images/sample1." + type);
//		gv.writeGraphToFile(gv.getGraph(gv.getDotSource(), type), out);
		
		return dotSource;
	}

	public String buildDOT(List<ErlangThread> list) throws IOException, EDDException {
		GraphViz gv = new GraphViz();
		gv.addln(gv.start_graph());
		gv.addln("\tgraph [dpi = 200]; ");
//		gv.addln("\tratio=\"fill\"; ");
		gv.addln("\tsize=\"8.3,11.7!\"; ");
		gv.addln("\tmargin = 0; ");
//		gv.addln("\tnode [shape=circle]; ");
		gv.addln("\tnode [style=filled]; ");
		gv.addln("\tnode [fillcolor=\"#EEEEEE\"]; "); // Relleno gris
		gv.addln("\tedge [color=\"#7092BE\"]; ");

		for(ErlangThread thread : list) {
			gv.addln(thread.getId() + " [label=\"" + thread.getId() + "\n" + thread.getFullName() + "\", style=filled]");
		}

		for(ErlangThread thread : list) {
			if (!thread.getCreator().isEmpty()) {
				gv.addln(thread.getCreator() + " -> "+ thread.getId() + " [style=filled, color=\"#ED1C3A\", fillcolor=\"#C0C0FF\"];");
			}
		}

		gv.addln(gv.end_graph());
		String dotSource = gv.getDotSource();
		
		return dotSource;
	}

	public Model buildEMF(boolean isZoom, String modelName, IPath ed2Path, IPath ed2DiagramPath) throws EDDException {
		WorkspaceModifyOperation operation = new WorkspaceModifyOperation() {
			@Override
			protected void execute(IProgressMonitor progressMonitor) {
				try {
					ResourceSet resourceSet = new ResourceSetImpl();
					URI fileURI = URI.createPlatformResourceURI(ed2Path.toPortableString(), true);
					Resource resource = resourceSet.createResource(fileURI);
					EObject rootObject = createInitialModel();
					if (rootObject != null) {
						resource.getContents().add(rootObject);
					}
					
					Diagram diagram = ViewService.createDiagram(rootObject,
							ModelEditPart.MODEL_ID,
							Ed2DiagramEditorPlugin.DIAGRAM_PREFERENCES_HINT);
					if (diagram != null) {
						resource.getContents().add(diagram);
						diagram.setName(fileURI.lastSegment());
						diagram.setElement(rootObject);
					}

					Map<Object, Object> options = new HashMap<Object, Object>();
					options.put(XMLResource.OPTION_ENCODING, "UTF-8");
					resource.save(options);
				}
				catch (Exception exception) {
					Ed2EditorPlugin.INSTANCE.log(exception);
				}
				finally {
					progressMonitor.done();
				}
			}

			private EObject createInitialModel() {
				Model model = Ed2Factory.eINSTANCE.createModel();
				ED2 ed2 = Ed2Factory.eINSTANCE.createED2();
				ed2.setName(modelName);
				model.setEd2(ed2);
				
				Map<Integer, ? extends iEddVertex> vertexesMap;
				List<EddEdge> edgesMap;
				if (isZoom) {
					iDebugTree debugTree = eddModel.getZoomDebugTree();
					vertexesMap = debugTree.getVertexesMap();
					edgesMap = debugTree.getEdgesMap();
				}
				else {
					iDebugTree debugTree = eddModel.getDebugTree();
					vertexesMap = debugTree.getVertexesMap();
					edgesMap = debugTree.getEdgesMap();
				}
				
				Map<Integer, Node> nodesMap = new HashMap<Integer, Node>();
				for (Entry<Integer, ? extends iEddVertex> entry : vertexesMap.entrySet()) {
					Integer key = entry.getKey();
					iEddVertex vertice = entry.getValue();
					Node node = Ed2Factory.eINSTANCE.createNode();
					int index = vertice.getIdNode();
					String question = vertice.getQuestionText();
					String fixQuestion = question.replace("\\l", "\n");
					node.setIndex(index);
					node.setName(index + ": " + fixQuestion);
					
					nodesMap.put(index, node);
				}
				
				List<EddEdge> edges = edgesMap;
				for (EddEdge edge : edges) {
					int from = edge.getFrom();
					int to = edge.getTo();
					
					Node source = nodesMap.get(from);
					Node target = nodesMap.get(to);
					source.getNodes().add(target);
				}
				
				int root = Collections.max(nodesMap.keySet()); 
				EList<TreeElement> elements = ed2.getTreeElements();
				Node eRoot = nodesMap.get(root);
				eRoot.setType(TreeElementType.NO);
				elements.add(eRoot);
				
				return model;
			}
		};

		Model model = null;
		try {
			new ProgressMonitorDialog(new Shell()).run(false, false, operation);
			IFile ed2File = Activator.getRoot().getFile(ed2Path);  
			List<EObject> root = loadModel(ed2File.getLocation().toPortableString());
			for (EObject eObject : root) {
				if (eObject instanceof Model) {
					model = (Model) root.get(0);
					break;
				}
			}
			performFinish(ed2Path, ed2DiagramPath);
		} catch (InvocationTargetException | InterruptedException | IOException e) {
			e.printStackTrace();
		}
		
		return model;
	}
	
	protected Model buildEMF2(String modelName, IPath ed2Path, IPath ed2DiagramPath) throws EDDException {
		validateEddModel();
		
		Model model = Ed2Factory.eINSTANCE.createModel();
		ED2 ed2 = Ed2Factory.eINSTANCE.createED2();
		ed2.setName(modelName);
		model.setEd2(ed2);
		
		Map<Integer, Node> nodesMap = new HashMap<Integer, Node>();
		Map<Integer, ? extends iEddVertex> vertices = eddModel.getDebugTree().getVertexesMap();
		for (Entry<Integer, ? extends iEddVertex> entry : vertices.entrySet()) {
			iEddVertex vertice = entry.getValue();
			Node node = Ed2Factory.eINSTANCE.createNode();
			int index = vertice.getIdNode();
			String question = vertice.getQuestionText();
			String fixQuestion = question.replace("\\l", "\n");
			node.setIndex(index);
			node.setName(index + ": " +fixQuestion);
			
			nodesMap.put(index, node);
		}
		
		List<EddEdge> edges = eddModel.getDebugTree().getEdgesMap();
		for (EddEdge edge : edges) {
			int from = edge.getFrom();
			int to = edge.getTo();
			
			Node source = nodesMap.get(from);
			Node target = nodesMap.get(to);
			source.getNodes().add(target);
		}
		
		
		int root = nodesMap.size();
		EList<TreeElement> elements = ed2.getTreeElements();
		Node eRoot = nodesMap.get(root);
		eRoot.setType(TreeElementType.NO);
		elements.add(eRoot);
		
		try {
			saveModel(ed2Path.toPortableString(), model);
			performFinish(ed2Path, ed2DiagramPath);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return model;
	}
	
	/**
	 * 
	 */
	public boolean performFinish(IPath ed2Path, IPath ed2DiagramPath) {
		List<EObject> root = null;
		try {
			IFile ed2File = Activator.getRoot().getFile(ed2Path);
			if (ed2File.exists()) {
				if (false) {
					ed2File.delete(IResource.NONE, new NullProgressMonitor());
				}
			}
			root = loadModel(ed2File.getLocation().toPortableString());
		} catch (IOException e1) {
			e1.printStackTrace();
			return false;
		} catch (CoreException e) {
			e.printStackTrace();
			return false;
		}
		
		final Model model = (Model) root.get(0);
		LinkedList<IFile> affectedFiles = new LinkedList<IFile>();
		IFile diagramFile = createNewFile(ed2DiagramPath.toPortableString());
		Ed2DiagramEditorUtil.setCharset(diagramFile);
		affectedFiles.add(diagramFile);
		URI diagramModelURI = URI.createPlatformResourceURI(diagramFile.getFullPath().toString(), true);
		TransactionalEditingDomain editingDomain = GMFEditingDomainFactory.INSTANCE.createEditingDomain();
		ResourceSet resourceSet = editingDomain.getResourceSet();
		final Resource diagramResource = resourceSet.createResource(diagramModelURI);
		AbstractTransactionalCommand command = new AbstractTransactionalCommand(editingDomain, "Initializing diagram contents", affectedFiles) {
			@Override
			protected CommandResult doExecuteWithResult(IProgressMonitor monitor, IAdaptable info) throws ExecutionException {
				int diagramVID = Ed2VisualIDRegistry.getDiagramVisualID(model);
				if (diagramVID != ModelEditPart.VISUAL_ID) {
					return CommandResult.newErrorCommandResult("Incorrect model object stored as a root resource object");
				}
				Diagram diagram = ViewService.createDiagram(model, ModelEditPart.MODEL_ID, Ed2DiagramEditorPlugin.DIAGRAM_PREFERENCES_HINT);
				diagramResource.getContents().add(diagram);
				return CommandResult.newOKCommandResult();
			}
		};
		
		try {
			OperationHistoryFactory.getOperationHistory().execute(command, new NullProgressMonitor(), null);
			diagramResource.save(Ed2DiagramEditorUtil.getSaveOptions());
			Ed2DiagramEditorUtil.openDiagram(diagramResource);
		} catch (ExecutionException e) {
			Ed2DiagramEditorPlugin.getInstance().logError("Unable to create model and diagram", e);
		} catch (IOException ex) {
			Ed2DiagramEditorPlugin.getInstance().logError("Save operation failed for: " + diagramModelURI, ex);
		} catch (PartInitException ex) {
			Ed2DiagramEditorPlugin.getInstance().logError("Unable to open editor", ex);
		}
		
		return true;
	}
	
	public static List<EObject> loadModel(String fileName) throws IOException {
		File source = new File(fileName);
		FileInputStream fis = new FileInputStream(source);
		
		URI uri = URI.createFileURI(fileName);
		Resource resource = RegisterPackage.resourceSet.createResource(uri);
		assert resource != null : "No se ha creado un resource para la ruta: " + fileName;
		resource.load(null);
		return resource.getContents();
	}
	
	public static void saveModel(String fileName, Model model) throws IOException {
		List<EObject> contents = new ArrayList<EObject>();
		contents.add(model);
		
		URI uri = URI.createFileURI(fileName);
		XMIResource resource = (XMIResource) RegisterPackage.resourceSet.createResource(uri);
		assert resource != null : "No se ha creado un resource para la ruta: " + fileName;
		List<EObject> contentsCopy = (List<EObject>) EcoreUtil.copyAll(contents);
		//generateIds(contentsCopy, (XMIResource) resource);
		resource.setEncoding(StandardCharsets.UTF_8.toString());
		resource.getContents().addAll(contentsCopy);
		resource.save(Collections.EMPTY_MAP);
	}
	
	protected EObject loadData(String fileName) throws FileNotFoundException, IOException {
		XMIResourceImpl resource = new XMIResourceImpl();
		File source = new File(fileName);
		System.out.println(source.getAbsolutePath());
		resource.load(new FileInputStream(source), new HashMap<Object, Object>());
		EObject data = resource.getContents().get(0);
		
		return data;
	}
	
	protected void saveData(String fileName, EObject data) throws IOException {
		Resource.Factory.Registry reg = Resource.Factory.Registry.INSTANCE;
		Map<String, Object> m = reg.getExtensionToFactoryMap();
		m.put("key", new XMIResourceFactoryImpl());

		ResourceSet resSet = new ResourceSetImpl();
		Resource resource = resSet.createResource(URI.createFileURI(fileName));
		resource.getContents().add(data);
		resource.save(Collections.EMPTY_MAP);
	}
	
	/**
	 * @param path
	 * @return
	 */
	public IFile createNewFile(String path) {
		final IPath filePath = new Path(path);
		final IFile newFile = Activator.getRoot().getFile(filePath);
		final InputStream initialContents = new ByteArrayInputStream(new byte[0]);
		
		try {
			CreateFileOperation op = new CreateFileOperation(newFile, null, initialContents, "New File");
			op.execute(new NullProgressMonitor(), WorkspaceUndoUtil.getUIInfoAdapter(new Shell()));
		} catch (ExecutionException e) {
			e.printStackTrace();
		}
		
		return newFile;
	}
	
	
	/*
	 * ======================================================================================================================= 
	 */
	protected JsonDocument getJsonDocument(String debugTree) {
		try {
			JsonDocument document = (JsonDocument) JsonHelper.readJsonFromString(debugTree, JsonDocument.class);	
			return document;
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	protected void writeJsonDocument(JsonDocument document, IPath path) throws EDDException {
		if (document == null) {
			throw new EDDException("The json document must not be null");
		}
		
		JsonUtils jsu = new JsonUtils();
		String content = jsu.toString(document);
		writeFile(content, path);
	}
	
	private Resource buildGMF(String name) {
//		URI diagramURI = URI.createPlatformResourceURI("platform:/resource/E-EDD/ed2/test.ed2_diagram", false);
//		URI modelURI = URI.createPlatformResourceURI("platform:/resource/E-EDD/ed2/test.ed2", false);
		URI diagramURI = URI.createPlatformResourceURI("/E-EDD/ed2/testGMF.ed2_diagram", false);
		URI modelURI = URI.createPlatformResourceURI("/E-EDD/ed2/testGMF.ed2", false);
		Resource diagramResource = Ed2DiagramEditorUtil.createDiagram(diagramURI, modelURI, new NullProgressMonitor());
		
		Diagram diagram = (Diagram) diagramResource.getContents().get(0);
		
		// ---------------------------------------------------------------------------
		TransactionalEditingDomain editingDomain = GMFEditingDomainFactory.INSTANCE.createEditingDomain();
		AbstractTransactionalCommand command = new AbstractTransactionalCommand(editingDomain, "Create content...", Collections.EMPTY_LIST) {
			protected CommandResult doExecuteWithResult(IProgressMonitor monitor, IAdaptable info) throws ExecutionException {
				int numberOfNodes = 10;
				for (int i = 0; i < numberOfNodes; i++) {
					diagram.createChild(NotationPackage.eINSTANCE.getNode());
				}
		
				for (int i = 0; i < 5; i++) {
					org.eclipse.gmf.runtime.notation.Node nodeA = (org.eclipse.gmf.runtime.notation.Node) diagram.getChildren().get(i);
					org.eclipse.gmf.runtime.notation.Node nodeB = (org.eclipse.gmf.runtime.notation.Node) diagram.getChildren().get(numberOfNodes - 1 - i);
		
					Edge edge = diagram.createEdge(NotationPackage.eINSTANCE.getEdge());
		
					edge.setSource(nodeA);
					edge.setTarget(nodeB);
					System.out.println("break");
				}
				
				return CommandResult.newOKCommandResult();
			}
		};
		
		TransactionalEditingDomain editDomain = TransactionUtil.getEditingDomain(diagramResource); //UMLModeler.getEditingDomain();
		editDomain.getCommandStack().execute(new RecordingCommand(editDomain, "Modify Operation") {
			@Override
			protected void doExecute() {
//				Model newModel = Ed2Factory.eINSTANCE.createModel();
				ED2 newED2 = Ed2Factory.eINSTANCE.createED2();
				Node newNode = Ed2Factory.eINSTANCE.createNode();
				Leaf newElement = Ed2Factory.eINSTANCE.createLeaf();
				
				diagramResource.getContents().add(newED2);
				diagramResource.getContents().add(newNode);
				diagramResource.getContents().add(newElement);
			}
		});

		// ----------------------------------------------------
		try {
			OperationHistoryFactory.getOperationHistory().execute(command, new NullProgressMonitor(), null);
			
			boolean openDiagram = Ed2DiagramEditorUtil.openDiagram(diagramResource);
//			 IWorkbenchPage.openEditor(URIEditorInput(), <editorID>); 
			
			diagramResource.save(Ed2DiagramEditorUtil.getSaveOptions());
		} catch (PartInitException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ExecutionException e) {
			Ed2DiagramEditorPlugin.getInstance().logError("Unable to create diagram content...", e);
		}
		
		
		return diagramResource;
	}
	
	private org.eclipse.gmf.runtime.notation.Node createExampleNode() {
		org.eclipse.gmf.runtime.notation.Node node = NotationFactory.eINSTANCE.createNode();
		Bounds bounds = NotationFactory.eINSTANCE.createBounds();
	    bounds.setHeight(1503);
	    bounds.setWidth(1979);
	    bounds.setX(777);
	    bounds.setY(888);

	    node.setVisible(true);
	    node.setMutable(false);
	    node.setLayoutConstraint(bounds);
	    return node;
	}
	
	private Edge createExampleEdge() {
		Edge edge = NotationFactory.eINSTANCE.createEdge();
	    edge.setVisible(true);
	    edge.setMutable(false);
	    
	    RelativeBendpoints bendpoints = NotationFactory.eINSTANCE.createRelativeBendpoints();
	    edge.setBendpoints(bendpoints);

	    List<RelativeBendpoint> points = new ArrayList<RelativeBendpoint>();
	    for (int i = 0; i < 10; i++) {
	      RelativeBendpoint bendPoint = new RelativeBendpoint(1, 2, 3, 4);
	      points.add(bendPoint);
	    }
	    
	    bendpoints.setPoints(points);
	    edge.setBendpoints(bendpoints);
	    
	    return edge;
	}
	
	/**
	 * @param model
	 * @param diagramPath
	 * @throws CoreException
	 */
	private void generateDiagram(Model model, String diagramPath) throws CoreException {
//		URI modelURI = URI.createPlatformResourceURI("/E-EDD/ed2/testAutoGMF.ed2", false);
//		URI diagramURI = URI.createPlatformResourceURI("/E-EDD/ed2/testAutoGMF.ed2_diagram", false);
//		Resource diagramResource = Ed2DiagramEditorUtil.createDiagram(diagramURI, modelURI, new NullProgressMonitor());
		
		LinkedList<IFile> affectedFiles = new LinkedList<IFile>();
		IFile diagramFile = createNewFile(diagramPath);
		System.out.println("Existe: " + diagramFile.exists());
		Ed2DiagramEditorUtil.setCharset(diagramFile);
		affectedFiles.add(diagramFile);
		
		TransactionalEditingDomain editingDomain = GMFEditingDomainFactory.INSTANCE.createEditingDomain();
		URI diagramModelURI = URI.createPlatformResourceURI(diagramFile.getFullPath().toString(), true);
		ResourceSet resourceSet = editingDomain.getResourceSet();
		final Resource diagramResource = resourceSet.createResource(diagramModelURI);
		AbstractTransactionalCommand command = new AbstractTransactionalCommand(editingDomain, "Initializing diagram contents", affectedFiles) {
			protected CommandResult doExecuteWithResult(IProgressMonitor monitor, IAdaptable info) throws ExecutionException {
				int diagramVID = Ed2VisualIDRegistry.getDiagramVisualID(model);
				if (diagramVID != ModelEditPart.VISUAL_ID) {
					return CommandResult.newErrorCommandResult("Incorrect model object stored as a root resource object");
				}
				Diagram diagram = ViewService.createDiagram(model, ModelEditPart.MODEL_ID, Ed2DiagramEditorPlugin.DIAGRAM_PREFERENCES_HINT);
				diagramResource.getContents().add(diagram);
				return CommandResult.newOKCommandResult();
			}
		};
		try {
			OperationHistoryFactory.getOperationHistory().execute(command, new NullProgressMonitor(), null);
			diagramResource.save(Ed2DiagramEditorUtil.getSaveOptions());
			Ed2DiagramEditorUtil.openDiagram(diagramResource);
		} catch (ExecutionException e) {
			Ed2DiagramEditorPlugin.getInstance().logError("Unable to create model and diagram", e);
		} catch (IOException ex) {
			Ed2DiagramEditorPlugin.getInstance().logError("Save operation failed for: " + diagramModelURI, ex);
		} catch (PartInitException ex) {
			Ed2DiagramEditorPlugin.getInstance().logError("Unable to open editor", ex);
		}
	}
}