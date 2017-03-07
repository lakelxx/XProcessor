import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.util.ArrayList;
import java.util.List;

public class EvalXPath extends XPathBaseVisitor<List<Node>>  implements XPathHelper {

    /*******************************************************************************************************************/
    /*******************************************Implementation of XPathHelper*******************************************/
    /*******************************************************************************************************************/

    public List<Node> RELATIVE(XPathParser.RpContext ctx, Node n){
        return null;
    }

    public boolean FILTER(XPathParser.FilterContext ctx, Node n){
        return false;
    }

    public Node ROOT(String fileName){
        return null;
    }

    public List<Node> CHILDREN(Node n){
        NodeList cList = n.getChildNodes();
        List<Node> nList = new ArrayList<>();
        for (int i=0; i<cList.getLength(); i++) {
            if (cList.item(i).getNodeType() == Node.ELEMENT_NODE) {
                nList.add(cList.item(i));
            }
        }
        return nList;
    }

    public List<Node> PARENT(Node n){
        List<Node> nList = new ArrayList<>();
        nList.add(n.getParentNode());
        return nList;
    }

    public String TAG(Node n){
        return n.getNodeName();
    }

    public List<Node> TXT(Node n){
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
        String fileName = ctx.FILENAME().getText();
        Node root = ROOT(fileName);
        List<Node> nList = RELATIVE(ctx.rp(), root);
        return nList;
    }

    public List<Node> visitAp2(XPathParser.Ap2Context ctx) {
        String fileName = ctx.FILENAME().getText();
        Node root = ROOT(fileName);
        List<Node> nList = RELATIVE(ctx.rp(), root);
        return nList;
    }

    @Override public List<Node> visitRpTag(XPathParser.RpTagContext ctx) { return visitChildren(ctx); }

    public List<Node> visitRpTag(XPathParser.RpTagContext ctx, Node n) {
        List<Node> nList = new ArrayList<>();
        List<Node> cList = visitRpStar(n);
        String tagName = ctx.NAME().getText();
        for(Node node : cList) {
            if(TAG(node).equals(tagName)) {
                nList.add(node);
            }
        }
        return nList;
    }

    @Override public List<Node> visitRpStar(XPathParser.RpStarContext ctx) { return visitChildren(ctx); }

    public List<Node> visitRpStar(Node n) {
        return CHILDREN(n);
    }

    @Override public List<Node> visitRpDot1(XPathParser.RpDot1Context ctx) { return visitChildren(ctx); }

    public List<Node> visitRpDot(XPathParser.RpDot1Context ctx, Node n) {
        List<Node> nList = new ArrayList<>();
        nList.add(n);
        return nList;
    }

    @Override public List<Node> visitRpDot2(XPathParser.RpDot2Context ctx) { return visitChildren(ctx); }

    public List<Node> visitRpParent(XPathParser.RpDot2Context ctx, Node n) {

        return visitChildren(ctx);
    }

    @Override public List<Node> visitRpText(XPathParser.RpTextContext ctx) { return visitChildren(ctx); }

    public List<Node> visitRpText(Node n) {
        return TXT(n);
    }

    @Override public List<Node> visitRpSlash2(XPathParser.RpSlash2Context ctx) { return visitChildren(ctx); }



    @Override public List<Node> visitRpSlash1(XPathParser.RpSlash1Context ctx) { return visitChildren(ctx); }





    @Override public List<Node> visitRpParens(XPathParser.RpParensContext ctx) { return visitChildren(ctx); }



    @Override public List<Node> visitRpFilter(XPathParser.RpFilterContext ctx) { return visitChildren(ctx); }

    @Override public List<Node> visitRpAttr(XPathParser.RpAttrContext ctx) { return visitChildren(ctx); }

    @Override public List<Node> visitRpComma(XPathParser.RpCommaContext ctx) { return visitChildren(ctx); }

    @Override public List<Node> visitFtOr(XPathParser.FtOrContext ctx) { return visitChildren(ctx); }

    @Override public List<Node> visitFtNot(XPathParser.FtNotContext ctx) { return visitChildren(ctx); }

    @Override public List<Node> visitFtIs(XPathParser.FtIsContext ctx) { return visitChildren(ctx); }

    @Override public List<Node> visitFtEq(XPathParser.FtEqContext ctx) { return visitChildren(ctx); }

    @Override public List<Node> visitFtAnd(XPathParser.FtAndContext ctx) { return visitChildren(ctx); }

    @Override public List<Node> visitFtRp(XPathParser.FtRpContext ctx) { return visitChildren(ctx); }

    @Override public List<Node> visitFtParens(XPathParser.FtParensContext ctx) { return visitChildren(ctx); }

}
