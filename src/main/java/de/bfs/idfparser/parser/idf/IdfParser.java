// Generated from de/bfs/idfparser/parser/idf/Idf.g4 by ANTLR 4.9.2
package de.bfs.idfparser.parser.idf;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class IdfParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.9.2", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		BLOCK_PREFIX=1, DATA=2, SITE=3, TEXT=4, ATTN=5, TEST=6, COMMENT_PREFIX=7, 
		WS=8, NEWLINE=9, STRING_ESC=10, STRING=11, C=12, ENDOFIDF=13;
	public static final int
		RULE_idfdatei = 0, RULE_idf_header = 1, RULE_measure_block = 2, RULE_location_block = 3, 
		RULE_text_block = 4, RULE_data_line = 5, RULE_site_line = 6, RULE_comment = 7, 
		RULE_measure_line = 8, RULE_location_line = 9;
	private static String[] makeRuleNames() {
		return new String[] {
			"idfdatei", "idf_header", "measure_block", "location_block", "text_block", 
			"data_line", "site_line", "comment", "measure_line", "location_line"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, null, "'DATA'", "'SITE'", "'TEXT'", "'ATTN'", "'TEST'", "'!'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, "BLOCK_PREFIX", "DATA", "SITE", "TEXT", "ATTN", "TEST", "COMMENT_PREFIX", 
			"WS", "NEWLINE", "STRING_ESC", "STRING", "C", "ENDOFIDF"
		};
	}
	private static final String[] _SYMBOLIC_NAMES = makeSymbolicNames();
	public static final Vocabulary VOCABULARY = new VocabularyImpl(_LITERAL_NAMES, _SYMBOLIC_NAMES);

	/**
	 * @deprecated Use {@link #VOCABULARY} instead.
	 */
	@Deprecated
	public static final String[] tokenNames;
	static {
		tokenNames = new String[_SYMBOLIC_NAMES.length];
		for (int i = 0; i < tokenNames.length; i++) {
			tokenNames[i] = VOCABULARY.getLiteralName(i);
			if (tokenNames[i] == null) {
				tokenNames[i] = VOCABULARY.getSymbolicName(i);
			}

			if (tokenNames[i] == null) {
				tokenNames[i] = "<INVALID>";
			}
		}
	}

	@Override
	@Deprecated
	public String[] getTokenNames() {
		return tokenNames;
	}

	@Override

	public Vocabulary getVocabulary() {
		return VOCABULARY;
	}

	@Override
	public String getGrammarFileName() { return "Idf.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public IdfParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	public static class IdfdateiContext extends ParserRuleContext {
		public Idf_headerContext idf_header() {
			return getRuleContext(Idf_headerContext.class,0);
		}
		public TerminalNode ENDOFIDF() { return getToken(IdfParser.ENDOFIDF, 0); }
		public List<Measure_blockContext> measure_block() {
			return getRuleContexts(Measure_blockContext.class);
		}
		public Measure_blockContext measure_block(int i) {
			return getRuleContext(Measure_blockContext.class,i);
		}
		public List<Location_blockContext> location_block() {
			return getRuleContexts(Location_blockContext.class);
		}
		public Location_blockContext location_block(int i) {
			return getRuleContext(Location_blockContext.class,i);
		}
		public List<Text_blockContext> text_block() {
			return getRuleContexts(Text_blockContext.class);
		}
		public Text_blockContext text_block(int i) {
			return getRuleContext(Text_blockContext.class,i);
		}
		public IdfdateiContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_idfdatei; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof IdfListener ) ((IdfListener)listener).enterIdfdatei(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof IdfListener ) ((IdfListener)listener).exitIdfdatei(this);
		}
	}

	public final IdfdateiContext idfdatei() throws RecognitionException {
		IdfdateiContext _localctx = new IdfdateiContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_idfdatei);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(20);
			idf_header();
			setState(24); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				setState(24);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,0,_ctx) ) {
				case 1:
					{
					setState(21);
					measure_block();
					}
					break;
				case 2:
					{
					setState(22);
					location_block();
					}
					break;
				case 3:
					{
					setState(23);
					text_block();
					}
					break;
				}
				}
				setState(26); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << BLOCK_PREFIX) | (1L << DATA) | (1L << SITE) | (1L << TEXT))) != 0) );
			setState(28);
			match(ENDOFIDF);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Idf_headerContext extends ParserRuleContext {
		public List<TerminalNode> STRING() { return getTokens(IdfParser.STRING); }
		public TerminalNode STRING(int i) {
			return getToken(IdfParser.STRING, i);
		}
		public TerminalNode NEWLINE() { return getToken(IdfParser.NEWLINE, 0); }
		public Idf_headerContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_idf_header; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof IdfListener ) ((IdfListener)listener).enterIdf_header(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof IdfListener ) ((IdfListener)listener).exitIdf_header(this);
		}
	}

	public final Idf_headerContext idf_header() throws RecognitionException {
		Idf_headerContext _localctx = new Idf_headerContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_idf_header);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(30);
			match(STRING);
			setState(31);
			match(STRING);
			setState(32);
			match(STRING);
			setState(33);
			match(STRING);
			setState(34);
			match(NEWLINE);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Measure_blockContext extends ParserRuleContext {
		public Data_lineContext data_line() {
			return getRuleContext(Data_lineContext.class,0);
		}
		public List<Measure_lineContext> measure_line() {
			return getRuleContexts(Measure_lineContext.class);
		}
		public Measure_lineContext measure_line(int i) {
			return getRuleContext(Measure_lineContext.class,i);
		}
		public Measure_blockContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_measure_block; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof IdfListener ) ((IdfListener)listener).enterMeasure_block(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof IdfListener ) ((IdfListener)listener).exitMeasure_block(this);
		}
	}

	public final Measure_blockContext measure_block() throws RecognitionException {
		Measure_blockContext _localctx = new Measure_blockContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_measure_block);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			{
			setState(36);
			data_line();
			setState(38); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(37);
				measure_line();
				}
				}
				setState(40); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==STRING );
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Location_blockContext extends ParserRuleContext {
		public Site_lineContext site_line() {
			return getRuleContext(Site_lineContext.class,0);
		}
		public List<Location_lineContext> location_line() {
			return getRuleContexts(Location_lineContext.class);
		}
		public Location_lineContext location_line(int i) {
			return getRuleContext(Location_lineContext.class,i);
		}
		public Location_blockContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_location_block; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof IdfListener ) ((IdfListener)listener).enterLocation_block(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof IdfListener ) ((IdfListener)listener).exitLocation_block(this);
		}
	}

	public final Location_blockContext location_block() throws RecognitionException {
		Location_blockContext _localctx = new Location_blockContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_location_block);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			{
			setState(42);
			site_line();
			setState(44); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(43);
				location_line();
				}
				}
				setState(46); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==STRING );
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Text_blockContext extends ParserRuleContext {
		public TerminalNode TEXT() { return getToken(IdfParser.TEXT, 0); }
		public List<TerminalNode> NEWLINE() { return getTokens(IdfParser.NEWLINE); }
		public TerminalNode NEWLINE(int i) {
			return getToken(IdfParser.NEWLINE, i);
		}
		public List<TerminalNode> STRING() { return getTokens(IdfParser.STRING); }
		public TerminalNode STRING(int i) {
			return getToken(IdfParser.STRING, i);
		}
		public Text_blockContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_text_block; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof IdfListener ) ((IdfListener)listener).enterText_block(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof IdfListener ) ((IdfListener)listener).exitText_block(this);
		}
	}

	public final Text_blockContext text_block() throws RecognitionException {
		Text_blockContext _localctx = new Text_blockContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_text_block);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(48);
			match(TEXT);
			setState(49);
			match(NEWLINE);
			setState(53);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==NEWLINE || _la==STRING) {
				{
				{
				setState(50);
				_la = _input.LA(1);
				if ( !(_la==NEWLINE || _la==STRING) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				}
				}
				setState(55);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Data_lineContext extends ParserRuleContext {
		public TerminalNode DATA() { return getToken(IdfParser.DATA, 0); }
		public TerminalNode NEWLINE() { return getToken(IdfParser.NEWLINE, 0); }
		public TerminalNode BLOCK_PREFIX() { return getToken(IdfParser.BLOCK_PREFIX, 0); }
		public Data_lineContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_data_line; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof IdfListener ) ((IdfListener)listener).enterData_line(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof IdfListener ) ((IdfListener)listener).exitData_line(this);
		}
	}

	public final Data_lineContext data_line() throws RecognitionException {
		Data_lineContext _localctx = new Data_lineContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_data_line);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(57);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==BLOCK_PREFIX) {
				{
				setState(56);
				match(BLOCK_PREFIX);
				}
			}

			setState(59);
			match(DATA);
			setState(60);
			match(NEWLINE);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Site_lineContext extends ParserRuleContext {
		public TerminalNode SITE() { return getToken(IdfParser.SITE, 0); }
		public TerminalNode NEWLINE() { return getToken(IdfParser.NEWLINE, 0); }
		public TerminalNode BLOCK_PREFIX() { return getToken(IdfParser.BLOCK_PREFIX, 0); }
		public Site_lineContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_site_line; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof IdfListener ) ((IdfListener)listener).enterSite_line(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof IdfListener ) ((IdfListener)listener).exitSite_line(this);
		}
	}

	public final Site_lineContext site_line() throws RecognitionException {
		Site_lineContext _localctx = new Site_lineContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_site_line);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(63);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==BLOCK_PREFIX) {
				{
				setState(62);
				match(BLOCK_PREFIX);
				}
			}

			setState(65);
			match(SITE);
			setState(66);
			match(NEWLINE);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class CommentContext extends ParserRuleContext {
		public TerminalNode COMMENT_PREFIX() { return getToken(IdfParser.COMMENT_PREFIX, 0); }
		public List<TerminalNode> STRING() { return getTokens(IdfParser.STRING); }
		public TerminalNode STRING(int i) {
			return getToken(IdfParser.STRING, i);
		}
		public CommentContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_comment; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof IdfListener ) ((IdfListener)listener).enterComment(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof IdfListener ) ((IdfListener)listener).exitComment(this);
		}
	}

	public final CommentContext comment() throws RecognitionException {
		CommentContext _localctx = new CommentContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_comment);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(68);
			match(COMMENT_PREFIX);
			setState(72);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==STRING) {
				{
				{
				setState(69);
				match(STRING);
				}
				}
				setState(74);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Measure_lineContext extends ParserRuleContext {
		public List<TerminalNode> STRING() { return getTokens(IdfParser.STRING); }
		public TerminalNode STRING(int i) {
			return getToken(IdfParser.STRING, i);
		}
		public TerminalNode NEWLINE() { return getToken(IdfParser.NEWLINE, 0); }
		public CommentContext comment() {
			return getRuleContext(CommentContext.class,0);
		}
		public Measure_lineContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_measure_line; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof IdfListener ) ((IdfListener)listener).enterMeasure_line(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof IdfListener ) ((IdfListener)listener).exitMeasure_line(this);
		}
	}

	public final Measure_lineContext measure_line() throws RecognitionException {
		Measure_lineContext _localctx = new Measure_lineContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_measure_line);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(75);
			match(STRING);
			setState(76);
			match(STRING);
			setState(77);
			match(STRING);
			setState(78);
			match(STRING);
			setState(79);
			match(STRING);
			setState(80);
			match(STRING);
			setState(84);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,8,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(81);
					match(STRING);
					}
					} 
				}
				setState(86);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,8,_ctx);
			}
			setState(90);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,9,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(87);
					match(STRING);
					}
					} 
				}
				setState(92);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,9,_ctx);
			}
			setState(96);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,10,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(93);
					match(STRING);
					}
					} 
				}
				setState(98);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,10,_ctx);
			}
			setState(102);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,11,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(99);
					match(STRING);
					}
					} 
				}
				setState(104);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,11,_ctx);
			}
			setState(108);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,12,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(105);
					match(STRING);
					}
					} 
				}
				setState(110);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,12,_ctx);
			}
			setState(114);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,13,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(111);
					match(STRING);
					}
					} 
				}
				setState(116);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,13,_ctx);
			}
			setState(120);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,14,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(117);
					match(STRING);
					}
					} 
				}
				setState(122);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,14,_ctx);
			}
			setState(126);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==STRING) {
				{
				{
				setState(123);
				match(STRING);
				}
				}
				setState(128);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(130);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==COMMENT_PREFIX) {
				{
				setState(129);
				comment();
				}
			}

			setState(132);
			match(NEWLINE);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Location_lineContext extends ParserRuleContext {
		public List<TerminalNode> STRING() { return getTokens(IdfParser.STRING); }
		public TerminalNode STRING(int i) {
			return getToken(IdfParser.STRING, i);
		}
		public TerminalNode NEWLINE() { return getToken(IdfParser.NEWLINE, 0); }
		public CommentContext comment() {
			return getRuleContext(CommentContext.class,0);
		}
		public List<TerminalNode> STRING_ESC() { return getTokens(IdfParser.STRING_ESC); }
		public TerminalNode STRING_ESC(int i) {
			return getToken(IdfParser.STRING_ESC, i);
		}
		public Location_lineContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_location_line; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof IdfListener ) ((IdfListener)listener).enterLocation_line(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof IdfListener ) ((IdfListener)listener).exitLocation_line(this);
		}
	}

	public final Location_lineContext location_line() throws RecognitionException {
		Location_lineContext _localctx = new Location_lineContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_location_line);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(134);
			match(STRING);
			setState(135);
			match(STRING);
			setState(139);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,17,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(136);
					match(STRING);
					}
					} 
				}
				setState(141);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,17,_ctx);
			}
			setState(145);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,18,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(142);
					match(STRING);
					}
					} 
				}
				setState(147);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,18,_ctx);
			}
			setState(151);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,19,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(148);
					match(STRING);
					}
					} 
				}
				setState(153);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,19,_ctx);
			}
			setState(157);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,20,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(154);
					_la = _input.LA(1);
					if ( !(_la==STRING_ESC || _la==STRING) ) {
					_errHandler.recoverInline(this);
					}
					else {
						if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
						_errHandler.reportMatch(this);
						consume();
					}
					}
					} 
				}
				setState(159);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,20,_ctx);
			}
			setState(163);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==STRING_ESC || _la==STRING) {
				{
				{
				setState(160);
				_la = _input.LA(1);
				if ( !(_la==STRING_ESC || _la==STRING) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				}
				}
				setState(165);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(167);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==COMMENT_PREFIX) {
				{
				setState(166);
				comment();
				}
			}

			setState(169);
			match(NEWLINE);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static final String _serializedATN =
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3\17\u00ae\4\2\t\2"+
		"\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13"+
		"\t\13\3\2\3\2\3\2\3\2\6\2\33\n\2\r\2\16\2\34\3\2\3\2\3\3\3\3\3\3\3\3\3"+
		"\3\3\3\3\4\3\4\6\4)\n\4\r\4\16\4*\3\5\3\5\6\5/\n\5\r\5\16\5\60\3\6\3\6"+
		"\3\6\7\6\66\n\6\f\6\16\69\13\6\3\7\5\7<\n\7\3\7\3\7\3\7\3\b\5\bB\n\b\3"+
		"\b\3\b\3\b\3\t\3\t\7\tI\n\t\f\t\16\tL\13\t\3\n\3\n\3\n\3\n\3\n\3\n\3\n"+
		"\7\nU\n\n\f\n\16\nX\13\n\3\n\7\n[\n\n\f\n\16\n^\13\n\3\n\7\na\n\n\f\n"+
		"\16\nd\13\n\3\n\7\ng\n\n\f\n\16\nj\13\n\3\n\7\nm\n\n\f\n\16\np\13\n\3"+
		"\n\7\ns\n\n\f\n\16\nv\13\n\3\n\7\ny\n\n\f\n\16\n|\13\n\3\n\7\n\177\n\n"+
		"\f\n\16\n\u0082\13\n\3\n\5\n\u0085\n\n\3\n\3\n\3\13\3\13\3\13\7\13\u008c"+
		"\n\13\f\13\16\13\u008f\13\13\3\13\7\13\u0092\n\13\f\13\16\13\u0095\13"+
		"\13\3\13\7\13\u0098\n\13\f\13\16\13\u009b\13\13\3\13\7\13\u009e\n\13\f"+
		"\13\16\13\u00a1\13\13\3\13\7\13\u00a4\n\13\f\13\16\13\u00a7\13\13\3\13"+
		"\5\13\u00aa\n\13\3\13\3\13\3\13\2\2\f\2\4\6\b\n\f\16\20\22\24\2\4\4\2"+
		"\13\13\r\r\3\2\f\r\2\u00bb\2\26\3\2\2\2\4 \3\2\2\2\6&\3\2\2\2\b,\3\2\2"+
		"\2\n\62\3\2\2\2\f;\3\2\2\2\16A\3\2\2\2\20F\3\2\2\2\22M\3\2\2\2\24\u0088"+
		"\3\2\2\2\26\32\5\4\3\2\27\33\5\6\4\2\30\33\5\b\5\2\31\33\5\n\6\2\32\27"+
		"\3\2\2\2\32\30\3\2\2\2\32\31\3\2\2\2\33\34\3\2\2\2\34\32\3\2\2\2\34\35"+
		"\3\2\2\2\35\36\3\2\2\2\36\37\7\17\2\2\37\3\3\2\2\2 !\7\r\2\2!\"\7\r\2"+
		"\2\"#\7\r\2\2#$\7\r\2\2$%\7\13\2\2%\5\3\2\2\2&(\5\f\7\2\')\5\22\n\2(\'"+
		"\3\2\2\2)*\3\2\2\2*(\3\2\2\2*+\3\2\2\2+\7\3\2\2\2,.\5\16\b\2-/\5\24\13"+
		"\2.-\3\2\2\2/\60\3\2\2\2\60.\3\2\2\2\60\61\3\2\2\2\61\t\3\2\2\2\62\63"+
		"\7\6\2\2\63\67\7\13\2\2\64\66\t\2\2\2\65\64\3\2\2\2\669\3\2\2\2\67\65"+
		"\3\2\2\2\678\3\2\2\28\13\3\2\2\29\67\3\2\2\2:<\7\3\2\2;:\3\2\2\2;<\3\2"+
		"\2\2<=\3\2\2\2=>\7\4\2\2>?\7\13\2\2?\r\3\2\2\2@B\7\3\2\2A@\3\2\2\2AB\3"+
		"\2\2\2BC\3\2\2\2CD\7\5\2\2DE\7\13\2\2E\17\3\2\2\2FJ\7\t\2\2GI\7\r\2\2"+
		"HG\3\2\2\2IL\3\2\2\2JH\3\2\2\2JK\3\2\2\2K\21\3\2\2\2LJ\3\2\2\2MN\7\r\2"+
		"\2NO\7\r\2\2OP\7\r\2\2PQ\7\r\2\2QR\7\r\2\2RV\7\r\2\2SU\7\r\2\2TS\3\2\2"+
		"\2UX\3\2\2\2VT\3\2\2\2VW\3\2\2\2W\\\3\2\2\2XV\3\2\2\2Y[\7\r\2\2ZY\3\2"+
		"\2\2[^\3\2\2\2\\Z\3\2\2\2\\]\3\2\2\2]b\3\2\2\2^\\\3\2\2\2_a\7\r\2\2`_"+
		"\3\2\2\2ad\3\2\2\2b`\3\2\2\2bc\3\2\2\2ch\3\2\2\2db\3\2\2\2eg\7\r\2\2f"+
		"e\3\2\2\2gj\3\2\2\2hf\3\2\2\2hi\3\2\2\2in\3\2\2\2jh\3\2\2\2km\7\r\2\2"+
		"lk\3\2\2\2mp\3\2\2\2nl\3\2\2\2no\3\2\2\2ot\3\2\2\2pn\3\2\2\2qs\7\r\2\2"+
		"rq\3\2\2\2sv\3\2\2\2tr\3\2\2\2tu\3\2\2\2uz\3\2\2\2vt\3\2\2\2wy\7\r\2\2"+
		"xw\3\2\2\2y|\3\2\2\2zx\3\2\2\2z{\3\2\2\2{\u0080\3\2\2\2|z\3\2\2\2}\177"+
		"\7\r\2\2~}\3\2\2\2\177\u0082\3\2\2\2\u0080~\3\2\2\2\u0080\u0081\3\2\2"+
		"\2\u0081\u0084\3\2\2\2\u0082\u0080\3\2\2\2\u0083\u0085\5\20\t\2\u0084"+
		"\u0083\3\2\2\2\u0084\u0085\3\2\2\2\u0085\u0086\3\2\2\2\u0086\u0087\7\13"+
		"\2\2\u0087\23\3\2\2\2\u0088\u0089\7\r\2\2\u0089\u008d\7\r\2\2\u008a\u008c"+
		"\7\r\2\2\u008b\u008a\3\2\2\2\u008c\u008f\3\2\2\2\u008d\u008b\3\2\2\2\u008d"+
		"\u008e\3\2\2\2\u008e\u0093\3\2\2\2\u008f\u008d\3\2\2\2\u0090\u0092\7\r"+
		"\2\2\u0091\u0090\3\2\2\2\u0092\u0095\3\2\2\2\u0093\u0091\3\2\2\2\u0093"+
		"\u0094\3\2\2\2\u0094\u0099\3\2\2\2\u0095\u0093\3\2\2\2\u0096\u0098\7\r"+
		"\2\2\u0097\u0096\3\2\2\2\u0098\u009b\3\2\2\2\u0099\u0097\3\2\2\2\u0099"+
		"\u009a\3\2\2\2\u009a\u009f\3\2\2\2\u009b\u0099\3\2\2\2\u009c\u009e\t\3"+
		"\2\2\u009d\u009c\3\2\2\2\u009e\u00a1\3\2\2\2\u009f\u009d\3\2\2\2\u009f"+
		"\u00a0\3\2\2\2\u00a0\u00a5\3\2\2\2\u00a1\u009f\3\2\2\2\u00a2\u00a4\t\3"+
		"\2\2\u00a3\u00a2\3\2\2\2\u00a4\u00a7\3\2\2\2\u00a5\u00a3\3\2\2\2\u00a5"+
		"\u00a6\3\2\2\2\u00a6\u00a9\3\2\2\2\u00a7\u00a5\3\2\2\2\u00a8\u00aa\5\20"+
		"\t\2\u00a9\u00a8\3\2\2\2\u00a9\u00aa\3\2\2\2\u00aa\u00ab\3\2\2\2\u00ab"+
		"\u00ac\7\13\2\2\u00ac\25\3\2\2\2\31\32\34*\60\67;AJV\\bhntz\u0080\u0084"+
		"\u008d\u0093\u0099\u009f\u00a5\u00a9";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}