// Generated from de/bfs/idfparser/parser/idf/Idf.g4 by ANTLR 4.9.2
package de.bfs.idfparser.parser.idf;
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link IdfParser}.
 */
public interface IdfListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link IdfParser#idfdatei}.
	 * @param ctx the parse tree
	 */
	void enterIdfdatei(IdfParser.IdfdateiContext ctx);
	/**
	 * Exit a parse tree produced by {@link IdfParser#idfdatei}.
	 * @param ctx the parse tree
	 */
	void exitIdfdatei(IdfParser.IdfdateiContext ctx);
	/**
	 * Enter a parse tree produced by {@link IdfParser#idf_header}.
	 * @param ctx the parse tree
	 */
	void enterIdf_header(IdfParser.Idf_headerContext ctx);
	/**
	 * Exit a parse tree produced by {@link IdfParser#idf_header}.
	 * @param ctx the parse tree
	 */
	void exitIdf_header(IdfParser.Idf_headerContext ctx);
	/**
	 * Enter a parse tree produced by {@link IdfParser#measure_block}.
	 * @param ctx the parse tree
	 */
	void enterMeasure_block(IdfParser.Measure_blockContext ctx);
	/**
	 * Exit a parse tree produced by {@link IdfParser#measure_block}.
	 * @param ctx the parse tree
	 */
	void exitMeasure_block(IdfParser.Measure_blockContext ctx);
	/**
	 * Enter a parse tree produced by {@link IdfParser#location_block}.
	 * @param ctx the parse tree
	 */
	void enterLocation_block(IdfParser.Location_blockContext ctx);
	/**
	 * Exit a parse tree produced by {@link IdfParser#location_block}.
	 * @param ctx the parse tree
	 */
	void exitLocation_block(IdfParser.Location_blockContext ctx);
	/**
	 * Enter a parse tree produced by {@link IdfParser#text_block}.
	 * @param ctx the parse tree
	 */
	void enterText_block(IdfParser.Text_blockContext ctx);
	/**
	 * Exit a parse tree produced by {@link IdfParser#text_block}.
	 * @param ctx the parse tree
	 */
	void exitText_block(IdfParser.Text_blockContext ctx);
	/**
	 * Enter a parse tree produced by {@link IdfParser#data_line}.
	 * @param ctx the parse tree
	 */
	void enterData_line(IdfParser.Data_lineContext ctx);
	/**
	 * Exit a parse tree produced by {@link IdfParser#data_line}.
	 * @param ctx the parse tree
	 */
	void exitData_line(IdfParser.Data_lineContext ctx);
	/**
	 * Enter a parse tree produced by {@link IdfParser#site_line}.
	 * @param ctx the parse tree
	 */
	void enterSite_line(IdfParser.Site_lineContext ctx);
	/**
	 * Exit a parse tree produced by {@link IdfParser#site_line}.
	 * @param ctx the parse tree
	 */
	void exitSite_line(IdfParser.Site_lineContext ctx);
	/**
	 * Enter a parse tree produced by {@link IdfParser#comment}.
	 * @param ctx the parse tree
	 */
	void enterComment(IdfParser.CommentContext ctx);
	/**
	 * Exit a parse tree produced by {@link IdfParser#comment}.
	 * @param ctx the parse tree
	 */
	void exitComment(IdfParser.CommentContext ctx);
	/**
	 * Enter a parse tree produced by {@link IdfParser#measure_line}.
	 * @param ctx the parse tree
	 */
	void enterMeasure_line(IdfParser.Measure_lineContext ctx);
	/**
	 * Exit a parse tree produced by {@link IdfParser#measure_line}.
	 * @param ctx the parse tree
	 */
	void exitMeasure_line(IdfParser.Measure_lineContext ctx);
	/**
	 * Enter a parse tree produced by {@link IdfParser#location_line}.
	 * @param ctx the parse tree
	 */
	void enterLocation_line(IdfParser.Location_lineContext ctx);
	/**
	 * Exit a parse tree produced by {@link IdfParser#location_line}.
	 * @param ctx the parse tree
	 */
	void exitLocation_line(IdfParser.Location_lineContext ctx);
}