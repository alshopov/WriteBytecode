package org.kambanaria.writebytecode.compiler;

import java.net.URI;
import javax.tools.SimpleJavaFileObject;

class StringSourceCodeObject extends SimpleJavaFileObject {

    final String _source;

    public StringSourceCodeObject(String fqName, String source) {
        super(URI.create("string:///" + fqName.replaceAll("\\.", "/") + Kind.SOURCE.extension), Kind.SOURCE);
        _source = source;
    }

    @Override
    public CharSequence getCharContent(boolean ignoreEncodingErrors) {
        return _source;
    }
}
