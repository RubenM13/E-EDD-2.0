package es.ucm.fdi.edd.core.xml;

import java.util.LinkedList;

import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import es.ucm.fdi.edd.core.json.model2.Pregunta;

public class XmlHelper {

	private XMLParser xmlParser;
	private Element root;

	public XmlHelper() {
		initialize();
	}

	public void initialize() {
		// Creating XML Parser instance
		xmlParser = new XMLParser(Constants.XML_URL);
		try {
			root = xmlParser.getRoot();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public String getGuideName() {
		NodeList guideNode = root.getElementsByTagName("guia");
		if (guideNode.getLength() == 1)
			return guideNode.item(0).getNodeValue();

		return null;
	}

	public int getQuestionSize() {
		NodeList questionNodes = root
				.getElementsByTagName(Constants.TAG_QUESTION);
		return questionNodes.getLength();
	}

	public LinkedList<Pregunta> getQuestions() {
		LinkedList<Pregunta> itemList = new LinkedList<Pregunta>();

		NodeList questionNodes = root
				.getElementsByTagName(Constants.TAG_QUESTION);
		if (questionNodes != null) {
			for (int i = 0; i < questionNodes.getLength(); i++) {
				Node questionNode = questionNodes.item(i);

				// Contacts
				Long idHelp = Long.parseLong(xmlParser.getAttribute(
						questionNode, Constants.ATTR_QUESTION_HELP));
				Long idQuestionType = Long.parseLong(xmlParser.getAttribute(
						questionNode, Constants.ATTR_QUESTION_QUESTION_TYPE));
				String questionMsg = xmlParser.getAttribute(questionNode,
						Constants.ATTR_QUESTION);

				Pregunta question = new Pregunta(Long.valueOf(i), idHelp, idQuestionType,
						questionMsg, Constants.DEFAULT_IMAGE);

				itemList.add(question);
			}
		}

		return itemList;
	}
}
