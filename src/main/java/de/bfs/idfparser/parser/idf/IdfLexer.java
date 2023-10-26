// Generated from de/bfs/idfparser/parser/idf/Idf.g4 by ANTLR 4.9.2
package de.bfs.idfparser.parser.idf;
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.*;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class IdfLexer extends Lexer {
	static { RuntimeMetaData.checkVersion("4.9.2", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		BLOCK_PREFIX=1, DATA=2, SITE=3, TEXT=4, ATTN=5, TEST=6, COMMENT_PREFIX=7, 
		WS=8, NEWLINE=9, STRING_ESC=10, STRING=11, C=12, ENDOFIDF=13;
	public static String[] channelNames = {
		"DEFAULT_TOKEN_CHANNEL", "HIDDEN"
	};

	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	private static String[] makeRuleNames() {
		return new String[] {
			"BLOCK_PREFIX", "DATA", "SITE", "TEXT", "ATTN", "TEST", "COMMENT_PREFIX", 
			"WS", "NEWLINE", "STRING_ESC", "STRING", "C", "CHAR", "ENDOFIDF"
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


	public IdfLexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "Idf.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public String[] getChannelNames() { return channelNames; }

	@Override
	public String[] getModeNames() { return modeNames; }

	@Override
	public ATN getATN() { return _ATN; }

	public static final String _serializedATN =
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\2\17d\b\1\4\2\t\2\4"+
		"\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t"+
		"\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\3\2\3\2\5\2\"\n\2\3\3\3\3\3\3"+
		"\3\3\3\3\3\4\3\4\3\4\3\4\3\4\3\5\3\5\3\5\3\5\3\5\3\6\3\6\3\6\3\6\3\6\3"+
		"\7\3\7\3\7\3\7\3\7\3\b\3\b\3\t\3\t\3\t\3\t\3\n\3\n\3\n\5\nF\n\n\3\13\3"+
		"\13\7\13J\n\13\f\13\16\13M\13\13\3\13\3\13\3\f\6\fR\n\f\r\f\16\fS\3\r"+
		"\3\r\3\16\3\16\3\17\3\17\3\17\3\17\3\17\3\17\3\17\5\17a\n\17\3\17\3\17"+
		"\2\2\20\3\3\5\4\7\5\t\6\13\7\r\b\17\t\21\n\23\13\25\f\27\r\31\16\33\2"+
		"\35\17\3\2\6\4\2\13\13\"\"\4\2\f\f\17\17\3\2$$\5\2\13\f\17\17\"$\2g\2"+
		"\3\3\2\2\2\2\5\3\2\2\2\2\7\3\2\2\2\2\t\3\2\2\2\2\13\3\2\2\2\2\r\3\2\2"+
		"\2\2\17\3\2\2\2\2\21\3\2\2\2\2\23\3\2\2\2\2\25\3\2\2\2\2\27\3\2\2\2\2"+
		"\31\3\2\2\2\2\35\3\2\2\2\3!\3\2\2\2\5#\3\2\2\2\7(\3\2\2\2\t-\3\2\2\2\13"+
		"\62\3\2\2\2\r\67\3\2\2\2\17<\3\2\2\2\21>\3\2\2\2\23E\3\2\2\2\25G\3\2\2"+
		"\2\27Q\3\2\2\2\31U\3\2\2\2\33W\3\2\2\2\35Y\3\2\2\2\37\"\5\13\6\2 \"\5"+
		"\r\7\2!\37\3\2\2\2! \3\2\2\2\"\4\3\2\2\2#$\7F\2\2$%\7C\2\2%&\7V\2\2&\'"+
		"\7C\2\2\'\6\3\2\2\2()\7U\2\2)*\7K\2\2*+\7V\2\2+,\7G\2\2,\b\3\2\2\2-.\7"+
		"V\2\2./\7G\2\2/\60\7Z\2\2\60\61\7V\2\2\61\n\3\2\2\2\62\63\7C\2\2\63\64"+
		"\7V\2\2\64\65\7V\2\2\65\66\7P\2\2\66\f\3\2\2\2\678\7V\2\289\7G\2\29:\7"+
		"U\2\2:;\7V\2\2;\16\3\2\2\2<=\7#\2\2=\20\3\2\2\2>?\t\2\2\2?@\3\2\2\2@A"+
		"\b\t\2\2A\22\3\2\2\2BC\7\17\2\2CF\7\f\2\2DF\t\3\2\2EB\3\2\2\2ED\3\2\2"+
		"\2F\24\3\2\2\2GK\7$\2\2HJ\n\4\2\2IH\3\2\2\2JM\3\2\2\2KI\3\2\2\2KL\3\2"+
		"\2\2LN\3\2\2\2MK\3\2\2\2NO\7$\2\2O\26\3\2\2\2PR\5\31\r\2QP\3\2\2\2RS\3"+
		"\2\2\2SQ\3\2\2\2ST\3\2\2\2T\30\3\2\2\2UV\5\33\16\2V\32\3\2\2\2WX\n\5\2"+
		"\2X\34\3\2\2\2YZ\7\\\2\2Z[\7\\\2\2[\\\7\\\2\2\\]\7\\\2\2]^\7\\\2\2^`\3"+
		"\2\2\2_a\5\23\n\2`_\3\2\2\2`a\3\2\2\2ab\3\2\2\2bc\7\2\2\3c\36\3\2\2\2"+
		"\b\2!EKS`\3\b\2\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}