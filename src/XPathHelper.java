import org.w3c.dom.Node;
import java.util.List;

public interface XPathHelper {

  List<Node> RELATIVE(XPathParser.RpContext ctx, Node n);

  boolean FILTER(XPathParser.FilterContext ctx, Node n);

  Node ROOT(String fileName);

  List<Node> CHILDREN(Node n);

  List<Node> PARENT(Node n);

  String TAG(Node n);

  List<Node> TXT(Node n);
}
