// Generated from XPath.g4 by ANTLR 4.6
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link XPathParser}.
 */
public interface XPathListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by the {@code ap1}
	 * labeled alternative in {@link XPathParser#ap}.
	 * @param ctx the parse tree
	 */
	void enterAp1(XPathParser.Ap1Context ctx);
	/**
	 * Exit a parse tree produced by the {@code ap1}
	 * labeled alternative in {@link XPathParser#ap}.
	 * @param ctx the parse tree
	 */
	void exitAp1(XPathParser.Ap1Context ctx);
	/**
	 * Enter a parse tree produced by the {@code ap2}
	 * labeled alternative in {@link XPathParser#ap}.
	 * @param ctx the parse tree
	 */
	void enterAp2(XPathParser.Ap2Context ctx);
	/**
	 * Exit a parse tree produced by the {@code ap2}
	 * labeled alternative in {@link XPathParser#ap}.
	 * @param ctx the parse tree
	 */
	void exitAp2(XPathParser.Ap2Context ctx);
	/**
	 * Enter a parse tree produced by the {@code rpText}
	 * labeled alternative in {@link XPathParser#rp}.
	 * @param ctx the parse tree
	 */
	void enterRpText(XPathParser.RpTextContext ctx);
	/**
	 * Exit a parse tree produced by the {@code rpText}
	 * labeled alternative in {@link XPathParser#rp}.
	 * @param ctx the parse tree
	 */
	void exitRpText(XPathParser.RpTextContext ctx);
	/**
	 * Enter a parse tree produced by the {@code rpSlash2}
	 * labeled alternative in {@link XPathParser#rp}.
	 * @param ctx the parse tree
	 */
	void enterRpSlash2(XPathParser.RpSlash2Context ctx);
	/**
	 * Exit a parse tree produced by the {@code rpSlash2}
	 * labeled alternative in {@link XPathParser#rp}.
	 * @param ctx the parse tree
	 */
	void exitRpSlash2(XPathParser.RpSlash2Context ctx);
	/**
	 * Enter a parse tree produced by the {@code rpSlash1}
	 * labeled alternative in {@link XPathParser#rp}.
	 * @param ctx the parse tree
	 */
	void enterRpSlash1(XPathParser.RpSlash1Context ctx);
	/**
	 * Exit a parse tree produced by the {@code rpSlash1}
	 * labeled alternative in {@link XPathParser#rp}.
	 * @param ctx the parse tree
	 */
	void exitRpSlash1(XPathParser.RpSlash1Context ctx);
	/**
	 * Enter a parse tree produced by the {@code rpDot2}
	 * labeled alternative in {@link XPathParser#rp}.
	 * @param ctx the parse tree
	 */
	void enterRpDot2(XPathParser.RpDot2Context ctx);
	/**
	 * Exit a parse tree produced by the {@code rpDot2}
	 * labeled alternative in {@link XPathParser#rp}.
	 * @param ctx the parse tree
	 */
	void exitRpDot2(XPathParser.RpDot2Context ctx);
	/**
	 * Enter a parse tree produced by the {@code rpDot1}
	 * labeled alternative in {@link XPathParser#rp}.
	 * @param ctx the parse tree
	 */
	void enterRpDot1(XPathParser.RpDot1Context ctx);
	/**
	 * Exit a parse tree produced by the {@code rpDot1}
	 * labeled alternative in {@link XPathParser#rp}.
	 * @param ctx the parse tree
	 */
	void exitRpDot1(XPathParser.RpDot1Context ctx);
	/**
	 * Enter a parse tree produced by the {@code rpTag}
	 * labeled alternative in {@link XPathParser#rp}.
	 * @param ctx the parse tree
	 */
	void enterRpTag(XPathParser.RpTagContext ctx);
	/**
	 * Exit a parse tree produced by the {@code rpTag}
	 * labeled alternative in {@link XPathParser#rp}.
	 * @param ctx the parse tree
	 */
	void exitRpTag(XPathParser.RpTagContext ctx);
	/**
	 * Enter a parse tree produced by the {@code rpParens}
	 * labeled alternative in {@link XPathParser#rp}.
	 * @param ctx the parse tree
	 */
	void enterRpParens(XPathParser.RpParensContext ctx);
	/**
	 * Exit a parse tree produced by the {@code rpParens}
	 * labeled alternative in {@link XPathParser#rp}.
	 * @param ctx the parse tree
	 */
	void exitRpParens(XPathParser.RpParensContext ctx);
	/**
	 * Enter a parse tree produced by the {@code rpStar}
	 * labeled alternative in {@link XPathParser#rp}.
	 * @param ctx the parse tree
	 */
	void enterRpStar(XPathParser.RpStarContext ctx);
	/**
	 * Exit a parse tree produced by the {@code rpStar}
	 * labeled alternative in {@link XPathParser#rp}.
	 * @param ctx the parse tree
	 */
	void exitRpStar(XPathParser.RpStarContext ctx);
	/**
	 * Enter a parse tree produced by the {@code rpFilter}
	 * labeled alternative in {@link XPathParser#rp}.
	 * @param ctx the parse tree
	 */
	void enterRpFilter(XPathParser.RpFilterContext ctx);
	/**
	 * Exit a parse tree produced by the {@code rpFilter}
	 * labeled alternative in {@link XPathParser#rp}.
	 * @param ctx the parse tree
	 */
	void exitRpFilter(XPathParser.RpFilterContext ctx);
	/**
	 * Enter a parse tree produced by the {@code rpAttr}
	 * labeled alternative in {@link XPathParser#rp}.
	 * @param ctx the parse tree
	 */
	void enterRpAttr(XPathParser.RpAttrContext ctx);
	/**
	 * Exit a parse tree produced by the {@code rpAttr}
	 * labeled alternative in {@link XPathParser#rp}.
	 * @param ctx the parse tree
	 */
	void exitRpAttr(XPathParser.RpAttrContext ctx);
	/**
	 * Enter a parse tree produced by the {@code rpComma}
	 * labeled alternative in {@link XPathParser#rp}.
	 * @param ctx the parse tree
	 */
	void enterRpComma(XPathParser.RpCommaContext ctx);
	/**
	 * Exit a parse tree produced by the {@code rpComma}
	 * labeled alternative in {@link XPathParser#rp}.
	 * @param ctx the parse tree
	 */
	void exitRpComma(XPathParser.RpCommaContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ftOr}
	 * labeled alternative in {@link XPathParser#filter}.
	 * @param ctx the parse tree
	 */
	void enterFtOr(XPathParser.FtOrContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ftOr}
	 * labeled alternative in {@link XPathParser#filter}.
	 * @param ctx the parse tree
	 */
	void exitFtOr(XPathParser.FtOrContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ftNot}
	 * labeled alternative in {@link XPathParser#filter}.
	 * @param ctx the parse tree
	 */
	void enterFtNot(XPathParser.FtNotContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ftNot}
	 * labeled alternative in {@link XPathParser#filter}.
	 * @param ctx the parse tree
	 */
	void exitFtNot(XPathParser.FtNotContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ftIs}
	 * labeled alternative in {@link XPathParser#filter}.
	 * @param ctx the parse tree
	 */
	void enterFtIs(XPathParser.FtIsContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ftIs}
	 * labeled alternative in {@link XPathParser#filter}.
	 * @param ctx the parse tree
	 */
	void exitFtIs(XPathParser.FtIsContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ftEq}
	 * labeled alternative in {@link XPathParser#filter}.
	 * @param ctx the parse tree
	 */
	void enterFtEq(XPathParser.FtEqContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ftEq}
	 * labeled alternative in {@link XPathParser#filter}.
	 * @param ctx the parse tree
	 */
	void exitFtEq(XPathParser.FtEqContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ftAnd}
	 * labeled alternative in {@link XPathParser#filter}.
	 * @param ctx the parse tree
	 */
	void enterFtAnd(XPathParser.FtAndContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ftAnd}
	 * labeled alternative in {@link XPathParser#filter}.
	 * @param ctx the parse tree
	 */
	void exitFtAnd(XPathParser.FtAndContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ftRp}
	 * labeled alternative in {@link XPathParser#filter}.
	 * @param ctx the parse tree
	 */
	void enterFtRp(XPathParser.FtRpContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ftRp}
	 * labeled alternative in {@link XPathParser#filter}.
	 * @param ctx the parse tree
	 */
	void exitFtRp(XPathParser.FtRpContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ftParens}
	 * labeled alternative in {@link XPathParser#filter}.
	 * @param ctx the parse tree
	 */
	void enterFtParens(XPathParser.FtParensContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ftParens}
	 * labeled alternative in {@link XPathParser#filter}.
	 * @param ctx the parse tree
	 */
	void exitFtParens(XPathParser.FtParensContext ctx);
}