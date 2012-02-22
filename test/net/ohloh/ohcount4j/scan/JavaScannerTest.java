package net.ohloh.ohcount4j.scan;

import org.testng.annotations.Test;

import net.ohloh.ohcount4j.scan.JavaScanner;
import static net.ohloh.ohcount4j.Entity.*;
import static net.ohloh.ohcount4j.Language.*;

public class JavaScannerTest extends BaseScannerTest {

	@Test
	public void basic() {
		assertLine(new JavaScanner(), new Line(LANG_JAVA, BLANK),   "\n");
		assertLine(new JavaScanner(), new Line(LANG_JAVA, BLANK),   "     \n");
		assertLine(new JavaScanner(), new Line(LANG_JAVA, BLANK),   "\t\n");
		assertLine(new JavaScanner(), new Line(LANG_JAVA, CODE),    "import java.util.List;\n");
		assertLine(new JavaScanner(), new Line(LANG_JAVA, COMMENT), "/* Block Comment */\n");
		assertLine(new JavaScanner(), new Line(LANG_JAVA, COMMENT), "// Line comment\n");
		assertLine(new JavaScanner(), new Line(LANG_JAVA, CODE),    "import java.util.List; // with comment\n");
	}

	@Test
	public void helloWorld() {
		String code
			= "/* Hello World\n"
			+ " * with multi-line comment */\n"
			+ "\n"
			+ "class HelloWorldApp {\n"
			+ "\tpublic static void main(String[] args) {\n"
			+ "\t\tSystem.out.println(\"Hello world!\");\n"
			+ "\t}\n"
			+ "}\n";

		Line[] expected = {
			new Line(LANG_JAVA, COMMENT),
			new Line(LANG_JAVA, COMMENT),
			new Line(LANG_JAVA, BLANK),
			new Line(LANG_JAVA, CODE),
			new Line(LANG_JAVA, CODE),
			new Line(LANG_JAVA, CODE),
			new Line(LANG_JAVA, CODE),
			new Line(LANG_JAVA, CODE)
		};
		assertLines(new JavaScanner(), expected, code);
	}
}