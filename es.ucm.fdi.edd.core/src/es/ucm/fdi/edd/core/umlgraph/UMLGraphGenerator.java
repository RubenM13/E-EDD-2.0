package es.ucm.fdi.edd.core.umlgraph;

import java.io.IOException;

import es.ucm.fdi.edd.core.util.FileManager;

public class UMLGraphGenerator {
	
	private static final String PATH = "./umlgraph";
	
	private static final int MAX = 10;
	
	public static void main(String[] args) {
		UMLGraphGenerator generator = new UMLGraphGenerator();
		try {
			StringBuffer data = generator.createPIC();
			FileManager.writeToFile(PATH + "/LongSample.pic",data);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
        /*try {
            StringBuffer data = new StringBuffer("test data in StringBuffer");
            // write it to file
            writeToFile("test.txt",data);
            // read it back from file
            data = readFromFile("test.txt");
            System.out.println("data = " + data.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }*/
    }
	
    private StringBuffer createPIC() {
    	StringBuffer sb = new StringBuffer();
    	sb.append(".PS\n");
    	sb.append("copy \"sequence.pic\";\n");

    	sb.append("\n# Object definition\n");
//    	sb.append("object(S,\"s:Switch\");\n");
//    	sb.append("object(P,\"p:Pump\");\n");
    	for (int i=0; i<MAX; i++) {
			sb.append("object(S"+i+",\"s"+i+":T"+i+"\");\n");
		}
    	
    	sb.append("\n# Message exchange\n");
//    	sb.append("message(S,P,\"run()\");\n");
//    	sb.append("message(S,P,\"stop()\");\n");
    	for (int i=0; i<MAX; i++) {
    		int j = (i+1)%MAX;
    		int k = (i+2)%MAX;
    		String source = "S"+i;
    		String target = "S"+j;
    		sb.append("message("+source+","+target+",\"execute()\");\n");
    	}

    	sb.append("\n# Object lifeline completion\n");
//    	sb.append("complete(S);\n");
//    	sb.append("complete(P);\n");
    	for (int i=0; i<MAX; i++) {
    		sb.append("complete(S"+i+");\n");
		}

    	sb.append("\n.PE\n");
    	
    	return sb;
	}
}
