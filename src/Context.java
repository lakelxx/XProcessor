import org.w3c.dom.Node;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Context {

    private Map<XQueryParser.VarContext, List<Node>> context;

    public Context() {
        context = new HashMap<>();
    }

    public Map<XQueryParser.VarContext, List<Node>> assign(XQueryParser.VarContext var, List<Node> v) {
        context.put(var, v);
        return context;
    }

    public List<Node> getValue(String var) {
        return context.get(var);
    }

}
