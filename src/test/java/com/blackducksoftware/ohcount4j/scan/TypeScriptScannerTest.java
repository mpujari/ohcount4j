/*
 * Copyright 2016 Black Duck Software, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.blackducksoftware.ohcount4j.scan;

import static com.blackducksoftware.ohcount4j.Entity.BLANK;
import static com.blackducksoftware.ohcount4j.Entity.CODE;
import static com.blackducksoftware.ohcount4j.Entity.COMMENT;

import org.testng.annotations.Test;

import com.blackducksoftware.ohcount4j.Language;

public class TypeScriptScannerTest extends AbstractBaseScannerTest {

    @Test
    public void basic() {
        assertLine(Language.TYPESCRIPT, new Line(Language.TYPESCRIPT, BLANK), "\n");
        assertLine(Language.TYPESCRIPT, new Line(Language.TYPESCRIPT, BLANK), "     \n");
        assertLine(Language.TYPESCRIPT, new Line(Language.TYPESCRIPT, BLANK), "\t\n");
        assertLine(Language.TYPESCRIPT, new Line(Language.TYPESCRIPT, CODE), "class Person {}\n");
        assertLine(Language.TYPESCRIPT, new Line(Language.TYPESCRIPT, COMMENT), "/* comment */\n");
        assertLine(Language.TYPESCRIPT, new Line(Language.TYPESCRIPT, COMMENT), "/** comment */\n");
        assertLine(Language.TYPESCRIPT, new Line(Language.TYPESCRIPT, COMMENT), "/*** comment */\n");
        assertLine(Language.TYPESCRIPT, new Line(Language.TYPESCRIPT, COMMENT), "/**** comment */\n");
        assertLine(Language.TYPESCRIPT, new Line(Language.TYPESCRIPT, CODE), "function add(left: number, right: number): number { /* with comment */\n");
    }

    @Test
    public void eofHandling() {
        // Note lack of trailing \n in all cases below
        assertLine(Language.TYPESCRIPT, new Line(Language.TYPESCRIPT, BLANK), "     ");
        assertLine(Language.TYPESCRIPT, new Line(Language.TYPESCRIPT, BLANK), "\t");
        assertLine(Language.TYPESCRIPT, new Line(Language.TYPESCRIPT, CODE), "declare module arithmetics {");
        assertLine(Language.TYPESCRIPT, new Line(Language.TYPESCRIPT, COMMENT), "/* comment */");
        assertLine(Language.TYPESCRIPT, new Line(Language.TYPESCRIPT, CODE), "toString(): string {}; /* with comment */");
    }

    @Test
    public void escapedCharsInStrings() {
        // A literal newline character embedded within a one-line string should not be
        // incorrectly counted as two lines of code
        assertLine(Language.TYPESCRIPT, new Line(Language.TYPESCRIPT, CODE),
                "let str = \"a newline literal \\n in a string\";");
    }

    @Test
    public void helloWorld() {
        String code = "/* Hello World */\n"
                + "\n"
                + "let fullName: string = `Bob Bobbington`;\n"
                + "\tlet aget: string = \"Hello, world!\\n\");\n"
                + "\tlet sentence: string = \"Hello, my name is \" + fullName + \".\n\n\" +" 
                + "\t\"I'll be \" + (age + 1) + \" years old next month.\"\n" 
                + "});\n";

        Line[] expected = {
                new Line(Language.TYPESCRIPT, COMMENT),
                new Line(Language.TYPESCRIPT, BLANK),
                new Line(Language.TYPESCRIPT, CODE),
                new Line(Language.TYPESCRIPT, CODE),
                new Line(Language.TYPESCRIPT, CODE),
                new Line(Language.TYPESCRIPT, BLANK),
                new Line(Language.TYPESCRIPT, CODE),
                new Line(Language.TYPESCRIPT, CODE)
        };
        assertLines(Language.TYPESCRIPT, expected, code);
    }

}
