import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.util.*;

public class EvalXQuery extends XQueryBaseVisitor<List<Node>> {

    /*******************************************************************************************************************/
    /******************************************Implementation of XQueryHelper*******************************************/
    /*******************************************************************************************************************/

    private Document resDoc;
    private Map<String, List<Node>> context = new HashMap<>();

    public void createDoc() {
        if (resDoc == null) {
            try {
                resDoc = DocumentBuilderFactory.newInstance().newDocumentBuilder().newDocument();
            } catch (ParserConfigurationException e) {
                e.printStackTrace();
                System.err.println("Error: " + e.getMessage());
            }
        }
    }

    public List<Node> makeElem(String t, List<Node> l) {
        createDoc();
        Node ret = resDoc.createElement(t);
        for (Node n : l) {
            if (n != null) {
                ret.appendChild(resDoc.importNode(n, true));
            }
        }
        List<Node> nList = new ArrayList<>();
        nList.add(ret);
        return nList;
    }

    public List<Node> makeText(String s) {
        createDoc();
        List<Node> nList = new ArrayList<>();
        nList.add(resDoc.createTextNode(s));
        return nList;
    }

    public List<Node> XQ(XQueryParser.XqContext ctx, Context context) {
        return null;
    }

    /*******************************************************************************************************************/
    /******************************************Extension of XQueryBaseVisitor*******************************************/
    /*******************************************************************************************************************/

    public List<Node> visitXqVar(XQueryParser.XqVarContext ctx, Context context) {
        String s = ctx.var().NAME().getText();
        return context.getValue(s);
    }

    public List<Node> visitXqSc(XQueryParser.XqScContext ctx) {
        String s = ctx.sConstant().NAME().getText();
        return makeText(s);
    }

    public List<Node> visitXqAp(XQueryParser.XqApContext ctx) {
        if(ctx.ap() instanceof XQueryParser.Ap1Context) {
            return visitAp1((XQueryParser.Ap1Context) ctx.ap());
        }
        else {
            return visitAp2((XQueryParser.Ap2Context) ctx.ap());
        }
    }

    public List<Node> visitXqParens(XQueryParser.XqParensContext ctx, Context context) {
        return XQ(ctx.xq(), context);
    }

    public List<Node> visitXqComma(XQueryParser.XqCommaContext ctx, Context context) {
        List<Node> l1 = XQ(ctx.xq(0), context);
        List<Node> l2 = XQ(ctx.xq(1), context);
        List<Node> nList = new ArrayList<>();
        nList.addAll(l1);
        nList.addAll(l2);
        return nList;
    }

    public List<Node> visitXqSlash1(XQueryParser.XqSlash1Context ctx, Context context) {
        List<Node> l = XQ(ctx.xq(), context);
        Set<Node> nSet = new LinkedHashSet<>();
        for (Node n : l) {
            XPathParser.RpContext rp = new XPathParser.RpContext();
            rp.copyFrom(ctx.rp());
            nSet.addAll(new EvalXPath().visitRelative(rp, n));
        }
        return new ArrayList<>(nSet);
    }

    public List<Node> visitXqSlash2(XQueryParser.XqSlash2Context ctx, Context context) {
        List<Node> l = XQ(ctx.xq(), context);
        Set<Node> nSet = new LinkedHashSet<>();
        for (Node n : l) {
            XPathParser.RpContext rp = new XPathParser.RpContext();
            rp.copyFrom(ctx.rp());
            nSet.addAll(new EvalXPath().visitRpSlash2(new XPathParser.RpStarContext(new XPathParser.RpContext()), rp, n));
        }
        return new ArrayList<>(nSet);
    }

    public List<Node> visitXqTag(XQueryParser.XqTagContext ctx, Context context) {
        String tagName = ctx.NAME(0).getText();
        return makeElem(tagName, XQ(ctx.xq(), context));
    }

    public List<Node> visitXqFLWR(XQueryParser.XqFLWRContext ctx, Context context) {
        List<XQueryParser.VarContext> varList = ctx.forClause().var();
        



        return visitChildren(ctx);
    }











    public boolean visitCondEq(XQueryParser.CondEqContext ctx, Context context) {
        List<Node> l1 = XQ(ctx.xq(0), context);
        List<Node> l2 = XQ(ctx.xq(1), context);
        for (Node n1 : l1) {
            for (Node n2 : l2) {
                if (n1.isEqualNode(n2)) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean visitCondIs(XQueryParser.CondEqContext ctx, Context context) {
        List<Node> l1 = XQ(ctx.xq(0), context);
        List<Node> l2 = XQ(ctx.xq(1), context);
        for (Node n1 : l1) {
            for (Node n2 : l2) {
                if (n1.isSameNode(n2)) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean visitCondEmpty(XQueryParser.CondEmptyContext ctx, Context context) {
        return XQ(ctx.xq(), context).isEmpty();
    }

    public boolean visitCondSatis(XQueryParser.CondSatisContext ctx, Context context) {

        return false;
    }


/*

    @Override public List<Node> visitXqLet(XQueryParser.XqLetContext ctx) { return visitChildren(ctx); }

    @Override public List<Node> visitXqTag(XQueryParser.XqTagContext ctx) { return visitChildren(ctx); }

    @Override public List<Node> visitCondParens(XQueryParser.CondParensContext ctx) { return visitChildren(ctx); }

    @Override public List<Node> visitCondAnd(XQueryParser.CondAndContext ctx) { return visitChildren(ctx); }

    @Override public List<Node> visitCondOr(XQueryParser.CondOrContext ctx) { return visitChildren(ctx); }

    @Override public List<Node> visitCondNot(XQueryParser.CondNotContext ctx) { return visitChildren(ctx); }
*/

}
