import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.w3c.dom.Node;

import java.util.List;

public class App {
    public static void main(String[] args) {
        try {
            String test = "<acts> \n" +
                    " {\tfor $a in doc(\"j_caesar.xml\")//ACT\n" +
                    "where empty ( for $sp in $a/SCENE/SPEECH/SPEAKER\n" +
                    " where $sp/text() = \"CASCA\" \n" +
                    "   return <speaker> {$sp/text()}</speaker> \n" +
                    "     )\n" +
                    "\n" +
                    "            return <act>{$a/TITLE/text()}</act>\n" +
                    "\n" +
                    "        }\n" +
                    "</acts>";
            ANTLRInputStream input = new ANTLRInputStream(test);
            XQueryLexer lexer = new XQueryLexer(input);
            CommonTokenStream tokens = new CommonTokenStream(lexer);
            XQueryParser parser = new XQueryParser(tokens);
            parser.removeErrorListeners();
            ParseTree tree = parser.xq();
            EvalXQuery myXPath = new EvalXQuery();
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