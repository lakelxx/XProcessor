import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

public class EvalXPath extends XPathBaseVisitor<List<Node>> {

    /*******************************************************************************************************************/
    /*******************************************Implementation of XPathHelper*******************************************/
    /*******************************************************************************************************************/

    public List<Node> visitRelative(XPathParser.RpContext ctx, Node n){
        if (ctx instanceof XPathParser.RpTagContext) {
            return visitRpTag((XPathParser.RpTagContext)ctx, n);
        }
        if (ctx instanceof XPathParser.RpStarContext) {
            return visitRpStar(n);
        }
        if (ctx instanceof XPathParser.RpDot1Context) {
            return visitRpDot1(n);
        }
        if (ctx instanceof XPathParser.RpDot2Context) {
            return visitRpDot2(n);
        }
        if (ctx instanceof XPathParser.RpTextContext) {
            return visitRpText(n);
        }
        if (ctx instanceof XPathParser.RpAttrContext) {
            return visitRpAttr((XPathParser.RpAttrContext) ctx, n);
        }
        if (ctx instanceof XPathParser.RpParensContext) {
            return visitRpParens((XPathParser.RpParensContext) ctx, n);
        }
        if (ctx instanceof XPathParser.RpSlash1Context) {
            XPathParser.RpContext rp0 = ((XPathParser.RpSlash1Context) ctx).rp(0);
            XPathParser.RpContext rp1 = ((XPathParser.RpSlash1Context) ctx).rp(1);
            return visitRpSlash1(rp0, rp1, n);
        }
        if (ctx instanceof XPathParser.RpSlash2Context) {
            XPathParser.RpContext rp0 = ((XPathParser.RpSlash2Context) ctx).rp(0);
            XPathParser.RpContext rp1 = ((XPathParser.RpSlash2Context) ctx).rp(1);
            return visitRpSlash2(rp0, rp1, n);
        }
        if (ctx instanceof XPathParser.RpFilterContext) {
            return visitRpFilter((XPathParser.RpFilterContext) ctx, n);
        }
        if (ctx instanceof XPathParser.RpCommaContext) {
            return visitRpComma((XPathParser.RpCommaContext) ctx, n);
        }
        return null;
    }

    public boolean visitFilter(XPathParser.FilterContext ctx, Node n){
        if (ctx instanceof XPathParser.FtRpContext) {
            return visitFtRp((XPathParser.FtRpContext) ctx, n);
        }
        if(ctx instanceof XPathParser.FtEqContext) {
            return visitFtEq((XPathParser.FtEqContext) ctx, n);
        }
        if(ctx instanceof XPathParser.FtIsContext) {
            return visitFtIs((XPathParser.FtIsContext) ctx, n);
        }
        if(ctx instanceof XPathParser.FtParensContext) {
            return visitFtParens((XPathParser.FtParensContext) ctx, n);
        }
        if(ctx instanceof XPathParser.FtAndContext) {
            return visitFtAnd((XPathParser.FtAndContext) ctx, n);
        }
        if(ctx instanceof XPathParser.FtOrContext) {
            return visitFtOr((XPathParser.FtOrContext) ctx, n);
        }
        if(ctx instanceof XPathParser.FtNotContext) {
            return visitFtNot((XPathParser.FtNotContext) ctx, n);
        }
        return false;
    }

    public Node visitRoot(String fileName){
        File xmlFile = new File("/Users/caleb/ProjHW/XProcessor/lib/" + fileName);
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        try {
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(xmlFile);
            doc.getDocumentElement().normalize();
            Node root = doc.getDocumentElement();
            return root;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Node> visitChildren(Node n){
        NodeList cList = n.getChildNodes();
        List<Node> nList = new ArrayList<>();
        for (int i=0; i<cList.getLength(); i++) {
            if (cList.item(i).getNodeType() == Node.ELEMENT_NODE) {
                nList.add(cList.item(i));
            }
        }
        return nList;
    }

    public List<Node> visitParent(Node n){
        List<Node> nList = new ArrayList<>();
        nList.add(n.getParentNode());
        return nList;
    }

    public String visitTag(Node n){
        return n.getNodeName();
    }

    public List<Node> visitTxt(Node n){
        NodeList cList = n.getChildNodes();
        List<Node> nList = new ArrayList<>();
        for(int i=0; i<cList.getLength(); i++) {
            Node cNode = cList.item(i);
            if(cNode.getNodeType() == Node.TEXT_NODE) {
                nList.add(cNode);
            }
        }
        return nList;
    }

    /*******************************************************************************************************************/
    /*******************************************Extension of XPathBaseVisitor*******************************************/
    /*******************************************************************************************************************/

    public List<Node> visitAp1(XPathParser.Ap1Context ctx) {
        String fileName = ctx.sConstant().NAME().getText();
        Node root = visitRoot(fileName);
        List<Node> nList = visitRelative(ctx.rp(), root);
        return nList;
    }

    public List<Node> visitAp2(XPathParser.Ap2Context ctx) {
        String fileName = ctx.sConstant().NAME().getText();
        Node root = visitRoot(fileName);
        List<Node> nList = visitRpSlash2(new XPathParser.RpDot1Context(new XPathParser.RpContext()), ctx.rp(), root);
        return nList;
    }

    public List<Node> visitRpTag(XPathParser.RpTagContext ctx, Node n) {
        List<Node> nList = new ArrayList<>();
        List<Node> cList = visitRpStar(n);
        String tagName = ctx.NAME().getText();
        for(Node node : cList) {
            if(visitTag(node).equals(tagName)) {
                nList.add(node);
            }
        }
        return nList;
    }

    public List<Node> visitRpStar(Node n) {
        return visitChildren(n);
    }

    public List<Node> visitRpDot1(Node n) {
        List<Node> nList = new ArrayList<>();
        nList.add(n);
        return nList;
    }

    public List<Node> visitRpDot2(Node n) {
        return visitParent(n);
    }

    public List<Node> visitRpText(Node n) {
        return visitTxt(n);
    }

    public List<Node> visitRpAttr(XPathParser.RpAttrContext ctx, Node n) {
        String nodeName = ctx.NAME().getText();
        List<Node> nList = new ArrayList<>();
        nList.add(n.getAttributes().getNamedItem(nodeName));
        return nList;
    }

    public List<Node> visitRpParens(XPathParser.RpParensContext ctx, Node n) {
        return visitRelative(ctx.rp(), n);
    }

    public List<Node> visitRpSlash1(XPathParser.RpContext ctx1, XPathParser.RpContext ctx2, Node n) {
        Set<Node> nSet = new LinkedHashSet<>();
        List<Node> l1 = visitRelative(ctx1, n);
        for (Node node : l1) {
            nSet.addAll(visitRelative(ctx2, node));
        }
        return new ArrayList<>(nSet);
    }

    public List<Node> visitRpSlash2(XPathParser.RpContext ctx1, XPathParser.RpContext ctx2, Node n) {
        Set<Node> nSet = new LinkedHashSet<>();
        List<Node> l0 = visitRpSlash1(ctx1, ctx2, n);
        nSet.addAll(l0);
        List<Node> l1 = visitRelative(ctx1, n);
        for(Node node : l1) {
            nSet.addAll(visitRpSlash2(new XPathParser.RpStarContext(new XPathParser.RpContext()), ctx2, node));
        }
        return new ArrayList<>(nSet);
    }

    public List<Node> visitRpFilter(XPathParser.RpFilterContext ctx, Node n) {
        List<Node> nList = new ArrayList<>();
        List<Node> l1 = visitRelative(ctx.rp(), n);
        for (Node node : l1) {
            if (visitFilter(ctx.filter(), node)) {
                nList.add(node);
            }
        }
        return nList;
    }

    public List<Node> visitRpComma(XPathParser.RpCommaContext ctx, Node n) {
        List<Node> nList = new ArrayList<>();
        nList.addAll(visitRelative(ctx.rp(0), n));
        nList.addAll(visitRelative(ctx.rp(1), n));
        return nList;
    }

    public boolean visitFtRp(XPathParser.FtRpContext ctx, Node n) {
        return !visitRelative(ctx.rp(), n).isEmpty();
    }

    public boolean visitFtEq(XPathParser.FtEqContext ctx, Node n) {
        List<Node> l1 = visitRelative(ctx.rp(0), n);
        List<Node> l2 = visitRelative(ctx.rp(1), n);
        for (Node node1 : l1) {
            for (Node node2 : l2) {
                if (node1.isEqualNode(node2)) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean visitFtIs(XPathParser.FtIsContext ctx, Node n) {
        List<Node> l1 = visitRelative(ctx.rp(0), n);
        List<Node> l2 = visitRelative(ctx.rp(1), n);
        for (Node node1 : l1) {
            for (Node node2 : l2) {
                if (node1.isSameNode(node2)) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean visitFtParens(XPathParser.FtParensContext ctx, Node n) {
        return visitFilter(ctx.filter(), n);
    }


    public boolean visitFtAnd(XPathParser.FtAndContext ctx, Node n) {
        return visitFilter(ctx.filter(0), n) && visitFilter(ctx.filter(1), n);
    }

    public boolean visitFtOr(XPathParser.FtOrContext ctx, Node n) {
        return visitFilter(ctx.filter(0), n) || visitFilter(ctx.filter(1), n);
    }

    public boolean visitFtNot(XPathParser.FtNotContext ctx, Node n) {
        return !visitFilter(ctx.filter(), n);
    }

}