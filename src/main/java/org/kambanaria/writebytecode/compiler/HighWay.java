package org.kambanaria.writebytecode.compiler;

import java.net.URI;
import java.util.Arrays;

import javax.tools.Diagnostic;
import javax.tools.DiagnosticCollector;
import javax.tools.JavaCompiler;
import javax.tools.JavaCompiler.CompilationTask;
import javax.tools.JavaFileManager;
import javax.tools.JavaFileObject;
import javax.tools.SimpleJavaFileObject;
import javax.tools.ToolProvider;

public class HighWay {

    private static final String I_STRING = "                        " //
            + "public class I {                                     "
            + "    public boolean singOutOfTune() {                 "
            + "        return new java.util.Random().nextBoolean(); "
            + "    }                                                "
            + "}                                                    ";

    private static final String YOU_STRING = "                      " //
            + "public class You {                                   "
            + "    public CharSequence wouldDo(boolean iF) {        "
            + "    return iF ? \"StandUp&WalkOutOnMe\" :            "
            + "              new StringBuilder(\"LendMeAnEar\");    "
            + "    }                                                "
            + "}                                                    ";

    static class StringSourceCodeObject extends SimpleJavaFileObject {
        final String _source;

        public StringSourceCodeObject(String fqName, String source) {
            super(URI.create("string:///" + fqName.replaceAll("\\.", "/") //
                    + Kind.SOURCE.extension), Kind.SOURCE);
            _source = source;
        }

        @Override
        public CharSequence getCharContent(boolean ignoreEncodingErrors) {
            return _source;
        }
    }

    public static void main(String args[]) throws Exception {
        
        /* Creating dynamic java source code file object */
        JavaFileObject iObject = new StringSourceCodeObject("I", I_STRING);
        JavaFileObject youObject = new StringSourceCodeObject("You", YOU_STRING);
        JavaFileObject jfObjects[] = new JavaFileObject[] { iObject, youObject };
        /* Units to compile */
        Iterable<JavaFileObject> units = Arrays.asList(jfObjects);
        /* Instantiating the java compiler */
        JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
        /* Get compiler file manager to show what to read. */
        // (DEFAULT LISTENER, Locale.getDefault(), Charset.defaultCharset() )
        JavaFileManager manager = compiler.getStandardFileManager(null, null, null);
        /* Compilation options - here: place in bin directory */
        String[] compileOptions = new String[] { "-d", "bin" };
        Iterable<String> options = Arrays.asList(compileOptions);
        /* Diagnostic placeholder */
        DiagnosticCollector<JavaFileObject> sink = new DiagnosticCollector<JavaFileObject>();
        /* 1st null: where to write (default), 2nd null: no annotation */
        CompilationTask task = compiler.getTask(null, manager, sink, options, null, units);
        /* Go, go, go */
        boolean status = task.call();
        if (!status) {
            for (Diagnostic<? extends JavaFileObject> d : sink.getDiagnostics()) {
                System.out.format("Error on line %d in %s", d.getLineNumber(), d);
        }    }
        manager.close();// TRY to close the file manager
        Object i = Class.forName("I").newInstance();
        System.out.println(i.getClass().getName());
}    }
