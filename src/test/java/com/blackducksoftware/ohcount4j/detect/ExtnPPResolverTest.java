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

package com.blackducksoftware.ohcount4j.detect;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;
import static org.testng.AssertJUnit.assertEquals;

import java.io.IOException;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.blackducksoftware.ohcount4j.Language;
import com.blackducksoftware.ohcount4j.SourceFile;

public class ExtnPPResolverTest {

    private ExtnPPResolver r;

    @BeforeTest()
    public void setup() {
        r = new ExtnPPResolver();
    }

    @Test
    public void canResolvetest() {
        assertFalse(r.canResolve(Language.RUBY));
        assertTrue(r.canResolve(Language.PASCAL));
        assertTrue(r.canResolve(Language.PUPPET));
    }

    @Test
    public void puppetExamples() throws IOException {
        assertEquals(Language.PUPPET, r.resolve(new SourceFile("foo.pp",
                "package { \"foo\":\n" +
                        "    ensure => installed\n" +
                        "}\n")));

        assertEquals(Language.PUPPET, r.resolve(new SourceFile("foo.pp",
                "node foo {\n" +
                        "    include bar\n" +
                        "}\n")));

        assertEquals(Language.PUPPET, r.resolve(new SourceFile("foo.pp",
                "class foo {\n" +
                        "}\n")));

        assertEquals(Language.PUPPET, r.resolve(new SourceFile("foo.pp",
                "define foo (\n" +
                        ")\n")));
    }

    @Test
    public void pascalExamples() throws IOException {
        assertEquals(Language.PASCAL, r.resolve(new SourceFile("foo.pp",
                "Program FooDemo;\n" +
                        "Const Foo = {$INCLUDE %FOO%};\n")));

        assertEquals(Language.PASCAL, r.resolve(new SourceFile("foo.pp",
                "Program FooDemo;\n" +
                        "Const Foo = {$include %FOO%};\n")));

        assertEquals(Language.PASCAL, r.resolve(new SourceFile("foo.pp",
                "foo begin\n" +
                        "end.\n")));
    }

    @Test
    public void pascalByDefault() throws IOException {
        assertEquals(Language.PASCAL, r.resolve(new SourceFile("foo.pp", "")));
    }
}
