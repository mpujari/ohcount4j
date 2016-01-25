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
import java.util.Arrays;
import java.util.List;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.blackducksoftware.ohcount4j.Language;
import com.blackducksoftware.ohcount4j.OhcountException;
import com.blackducksoftware.ohcount4j.SourceFile;

public class DetectorTest {

    @Test(dataProvider = "detectByExtensions")
    public void detectByExtensionTest(Language language, List<String> fileNames) throws IOException {
        for (String fileName : fileNames) {
            assertDetect(fileName, language);
        }
    }

    @DataProvider
    public Object[][] detectByExtensions() {
        return new Object[][] {
                { Language.C, Arrays.asList("main.c") },
                { Language.CSS, Arrays.asList("main.css") },
                { Language.HTML, Arrays.asList("main.htm", "main.html") },
                { Language.JAVA, Arrays.asList("main.java") },
                { Language.JAVASCRIPT, Arrays.asList("main.js") },
                { Language.RUBY, Arrays.asList("main.rb", "config.ru") },
                { Language.AUTOMAKE, Arrays.asList("make.am", "make.AM") },
                { Language.AUTOCONF, Arrays.asList("configuration.ac", "configuration.autoconf") },
                { Language.GOLANG, Arrays.asList("main.go") },
                { Language.AUGEAS, Arrays.asList("main.aug") },
                { Language.AWK, Arrays.asList("main.awk") },
                { Language.BRAINFUCK, Arrays.asList("main.bf") },
                { Language.BFPP, Arrays.asList("main.bfpp") },
                { Language.CMake, Arrays.asList("CMakeLists.txt", "file.cmake") },
                { Language.CHAISCRIPT, Arrays.asList("main.chai") },
                { Language.COQ, Arrays.asList("foo.v") },
                { Language.CUDA, Arrays.asList("foo.cu", "foo.cuh") },
                { Language.D, Arrays.asList("foo.d") },
                { Language.DYLAN, Arrays.asList("foo.dylan") },
                { Language.BLITZMAX, Arrays.asList("main.bmx") },
                { Language.EBUILD, Arrays.asList("foo.ebuild", "foo.kdebuild-1", "foo.eclass") },
                { Language.EC, Arrays.asList("foo.ec", "foo.eh") },
                { Language.FORTH, Arrays.asList("foo.fr", "foo.4th") },
                { Language.DCL, Arrays.asList("foo.COM") },
                { Language.GLSL, Arrays.asList("foo.frag", "foo.glsl", "foo.vert") },
                { Language.HAXE, Arrays.asList("main.hx") },
        };
    }

    @Test
    public void detectByFilenameTest() throws IOException {
        assertDetect("Makefile", Language.MAKE);
        assertDetect("Gemfile", Language.RUBY);
        assertDetect("Rakefile", Language.RUBY);
        assertDetect("CMakeLists.txt", Language.CMake);
    }

    protected void assertDetect(String filename, Language language) throws IOException {
        assertEquals(language, Detector.detect(new SourceFile(filename, "")));
    }

    @Test
    public void isBinaryTest() {
        assertFalse(Detector.getInstance().isBinary(""));
        assertFalse(Detector.getInstance().isBinary("txt"));
        assertFalse(Detector.getInstance().isBinary("am"));
        assertFalse(Detector.getInstance().isBinary("awk"));

        assertTrue(Detector.getInstance().isBinary("jpg"));
        assertTrue(Detector.getInstance().isBinary("JPG"));
    }

    @Test
    public void getResolverTest() throws OhcountException {
        assertTrue(Detector.getResolver("h") instanceof ExtnHResolver);
        assertTrue(Detector.getResolver("m") instanceof ExtnMResolver);
        assertTrue(Detector.getResolver("inc") instanceof ExtnINCResolver);
        assertTrue(Detector.getResolver("pp") instanceof ExtnPPResolver);
        assertTrue(Detector.getResolver("aspx") instanceof ExtnASPXResolver);
        assertTrue(Detector.getResolver("asx") instanceof ExtnASXResolver);
        assertTrue(Detector.getResolver("cs") instanceof ExtnCSResolver);

        assertTrue(Detector.getResolver("f") instanceof FortranResolver);
        assertTrue(Detector.getResolver("f90") instanceof FortranResolver);
    }

}
