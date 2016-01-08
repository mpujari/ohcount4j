/**
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

package com.blackducksoftware.ohcount4j;

import java.io.IOException;

/**
 * @author mpujari
 *
 */
public final class SourceFileUtils {

    public static final int HEAD_SIZE = 100;

    private SourceFileUtils() {
    }

    /**
     * NOTE: if we contents are needed to be fetched then we just read 100 bytes from it.
     *
     * @param sourceFile
     * @return
     * @throws IOException
     */
    public static String head(SourceFile sourceFile) throws IOException {
        if (sourceFile.isContentsFromFile()) {
            /*
             * we have a reader initialized, we should not use it as it will
             * forward the reader and we don't want that
             */
            try (SourceFile srcFile = new SourceFile(sourceFile.getPath())) {
                return srcFile.head(HEAD_SIZE);
            }
        } else if (sourceFile.getContents() != null) {
            // the source is not from the file, so we got to have content initialized
            return new String(sourceFile.getContents());
        } else {
            return null;
        }

    }

    public static CharSequence getCharSequence(SourceFile sourceFile) throws IOException {
        return sourceFile.getCharSequence();
    }

    public static char[] getContents(SourceFile sourceFile) throws IOException {
        return sourceFile.getContents();
    }

}
