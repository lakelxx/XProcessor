import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.w3c.dom.Node;

import java.util.List;

public class App {
    public static void main(String[] args) {
        try {
            String test = "doc(\"j_caesar.xml\")//(ACT,PERSONAE)/TITLE";
            ANTLRInputStream input = new ANTLRInputStream(test);
            XPathLexer lexer = new XPathLexer(input);
            CommonTokenStream tokens = new CommonTokenStream(lexer);
            XPathParser parser = new XPathParser(tokens);
            parser.removeErrorListeners();
            ParseTree tree = parser.ap();
            EvalXPath myXPath = new EvalXPath();
            List<Node> ret = myXPath.visit(tree);
            for(Node n : ret) {
                System.out.println("ret name: " + n.getNodeName() + " " + "ret txt  " + n.getTextContent());
            }
            // output(ret.get(0));
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("Error: " + e.getMessage());
        }
    }
    public static void output (Node x) {
        System.out.println('<' + x.getNodeName() + '>');
        if (x.getChildNodes()!=null) {
            for (int i = 0; i<x.getChildNodes().getLength(); i++) {
                if (x.getChildNodes().item(i).getNodeName()=="#text") {
                    System.out.println(x.getTextContent());
                }
                else output(x.getChildNodes().item(i));
            }
        }
        System.out.println("</" + x.getNodeName() + '>');
    }
}