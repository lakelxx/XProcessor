import org.w3c.dom.Document;
import org.w3c.dom.Node;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.util.*;

public class EvalXQuery extends XQueryBaseVisitor<List<Node>> {

    /*******************************************************************************************************************/
    /******************************************Implementation of XQueryHelper*******************************************/
    /*******************************************************************************************************************/

    private Document resDoc;

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
        Node node = resDoc.createElement(t);
        for (Node n : l) {
            if (n != null) {
                node.appendChild(resDoc.importNode(n, true));
            }
        }
        List<Node> nList = new ArrayList<>();
        nList.add(node);
        return nList;
    }

    public List<Node> makeText(String s) {
        createDoc();
        List<Node> nList = new ArrayList<>();
        nList.add(resDoc.createTextNode(s));
        return nList;
    }

    public List<Node> visitXQ(XQueryParser.XqContext ctx, Context context) {
        if (ctx instanceof XQueryParser.XqVarContext) {
            return visitXqVar((XQueryParser.XqVarContext) ctx, context);
        }
        if (ctx instanceof XQueryParser.XqScContext) {
            return visitXqSc((XQueryParser.XqScContext) ctx);
        }
        if (ctx instanceof XQueryParser.XqApContext) {
            return visitXqAp((XQueryParser.XqApContext) ctx);
        }
        if (ctx instanceof XQueryParser.XqCommaContext) {
            return visitXqComma((XQueryParser.XqCommaContext) ctx, context);
        }
        if (ctx instanceof XQueryParser.XqSlash1Context) {
            return visitXqSlash1((XQueryParser.XqSlash1Context) ctx, context);
        }
        if (ctx instanceof XQueryParser.XqSlash2Context) {
            return visitXqSlash2((XQueryParser.XqSlash2Context) ctx, context);
        }
        if (ctx instanceof XQueryParser.XqParensContext) {
            return visitXqParens((XQueryParser.XqParensContext) ctx, context);
        }
        if (ctx instanceof XQueryParser.XqTagContext) {
            return visitXqTag((XQueryParser.XqTagContext) ctx, context);
        }
        if (ctx instanceof XQueryParser.XqFLWRContext) {
            return visitXqFLWR((XQueryParser.XqFLWRContext) ctx, context);
        }
        if (ctx instanceof XQueryParser.XqLetContext) {
            return visitXqLet((XQueryParser.XqLetContext) ctx, context);
        }
        return null;
    }

    public boolean visitCond(XQueryParser.CondContext ctx, Context context) {
        if (ctx instanceof XQueryParser.CondEqContext) {
            return visitCondEq((XQueryParser.CondEqContext) ctx, context);
        }
        if (ctx instanceof XQueryParser.CondIsContext) {
            return visitCondIs((XQueryParser.CondIsContext) ctx, context);
        }
        if (ctx instanceof XQueryParser.CondEmptyContext) {
            return visitCondEmpty((XQueryParser.CondEmptyContext) ctx, context);
        }
        if (ctx instanceof XQueryParser.CondSatisContext) {
            return visitCondSatis((XQueryParser.CondSatisContext) ctx, context);
        }
        if (ctx instanceof XQueryParser.CondParensContext) {
            return visitCondParens((XQueryParser.CondParensContext) ctx, context);
        }
        if (ctx instanceof XQueryParser.CondAndContext) {
            return visitCondAnd((XQueryParser.CondAndContext) ctx, context);
        }
        if (ctx instanceof XQueryParser.CondOrContext) {
            return visitCondOr((XQueryParser.CondOrContext) ctx, context);
        }
        if (ctx instanceof XQueryParser.CondNotContext) {
            return visitCondNot((XQueryParser.CondNotContext) ctx, context);
        }
        return false;
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
        return visitXQ(ctx.xq(), context);
    }

    public List<Node> visitXqComma(XQueryParser.XqCommaContext ctx, Context context) {
        List<Node> l1 = visitXQ(ctx.xq(0), context);
        List<Node> l2 = visitXQ(ctx.xq(1), context);
        List<Node> nList = new ArrayList<>();
        nList.addAll(l1);
        nList.addAll(l2);
        return nList;
    }

    public List<Node> visitXqSlash1(XQueryParser.XqSlash1Context ctx, Context context) {
        List<Node> l = visitXQ(ctx.xq(), context);
        Set<Node> nSet = new LinkedHashSet<>();
        for (Node n : l) {
            XPathParser.RpContext rp = new XPathParser.RpContext();
            rp.copyFrom(ctx.rp());
            nSet.addAll(new EvalXPath().visitRelative(rp, n));
        }
        return new ArrayList<>(nSet);
    }

    public List<Node> visitXqSlash2(XQueryParser.XqSlash2Context ctx, Context context) {
        List<Node> l = visitXQ(ctx.xq(), context);
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
        return makeElem(tagName, visitXQ(ctx.xq(), context));
    }

    public List<Node> visitXqFLWR(XQueryParser.XqFLWRContext ctx, Context context) {
        List<XQueryParser.VarContext> forVarList = ctx.forClause().var();
        List<XQueryParser.XqContext> forXqList = ctx.forClause().xq();
        List<XQueryParser.VarContext> letVarList = ctx.letClause()==null? new ArrayList<>() : ctx.letClause().var();
        List<XQueryParser.XqContext> letXqList = ctx.letClause()==null? new ArrayList<>() : ctx.letClause().xq();
        XQueryParser.CondContext whereCond = ctx.whereClause() == null? null : ctx.whereClause().cond();
        XQueryParser.XqContext returnXq = ctx.returnClause().xq();

        for (int i=0; i<forVarList.size(); i++) {
            XQueryParser.VarContext forVar = forVarList.get(i);
            XQueryParser.XqContext forXq = forXqList.get(i);
            context.assign(forVar, visitXQ(forXq, context));
        }

        for (int i=0; i<letVarList.size(); i++) {
            XQueryParser.VarContext letVar = letVarList.get(i);
            XQueryParser.XqContext letXq = letXqList.get(i);
            context.assign(letVar, visitXQ(letXq, context));
        }

        if (whereCond == null || visitCond(whereCond, context)) {
            return visitXQ(returnXq, context);
        }

        return new ArrayList<Node>();
    }

    public List<Node> visitXqLet(XQueryParser.XqLetContext ctx, Context context) {
        List<XQueryParser.VarContext> varList = ctx.letClause().var();
        List<XQueryParser.XqContext> xqList = ctx.letClause().xq();

        for(int i=0; i<varList.size(); i++) {
            XQueryParser.VarContext var = varList.get(i);
            XQueryParser.XqContext xq = xqList.get(i);
            context.assign(var, visitXQ(xq, context));
        }
        return visitXQ(ctx.xq(), context);
    }

    public boolean visitCondEq(XQueryParser.CondEqContext ctx, Context context) {
        List<Node> l1 = visitXQ(ctx.xq(0), context);
        List<Node> l2 = visitXQ(ctx.xq(1), context);
        for (Node n1 : l1) {
            for (Node n2 : l2) {
                if (n1.isEqualNode(n2)) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean visitCondIs(XQueryParser.CondIsContext ctx, Context context) {
        List<Node> l1 = visitXQ(ctx.xq(0), context);
        List<Node> l2 = visitXQ(ctx.xq(1), context);
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
        return visitXQ(ctx.xq(), context).isEmpty();
    }

    public boolean visitCondSatis(XQueryParser.CondSatisContext ctx, Context context) {
        List<XQueryParser.VarContext> varList = ctx.var();
        List<XQueryParser.XqContext> xqList = ctx.xq();
        XQueryParser.CondContext cond = ctx.cond();
        return satisHelper(varList, xqList, context, cond);
    }

    // Helper Method for visitCondSatis
    public boolean satisHelper(List<XQueryParser.VarContext> varList, List<XQueryParser.XqContext> xqList,
                               Context context, XQueryParser.CondContext cond) {
        if (varList.isEmpty()) {
            return visitCond(cond, context);
        }
        XQueryParser.VarContext var = varList.get(0);
        XQueryParser.XqContext xq = xqList.get(0);
        varList.remove(0);
        xqList.remove(0);
        List<Node> nList = visitXQ(xq, context);
        boolean b= false;

        for (Node n : nList) {
            List<Node> temp = new ArrayList<>();
            temp.add(n);
            context.assign(var, temp);
            b = b || satisHelper(varList, xqList, context, cond);
        }

        return b;
    }

    public boolean visitCondParens(XQueryParser.CondParensContext ctx, Context context) {
        return visitCond(ctx.cond(), context);
    }

    public boolean visitCondAnd(XQueryParser.CondAndContext ctx, Context context) {
        return visitCond(ctx.cond(0), context) && visitCond(ctx.cond(1), context);
    }

    public boolean visitCondOr(XQueryParser.CondOrContext ctx, Context context) {
        return visitCond(ctx.cond(0), context) || visitCond(ctx.cond(1), context);
    }

    public boolean visitCondNot(XQueryParser.CondNotContext ctx, Context context) {
        return !visitCond(ctx, context);
    }

}
