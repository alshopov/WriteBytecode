package org.kambanaria.writebytecode.compiler;

import java.util.Arrays;
import javax.tools.Diagnostic;
import javax.tools.DiagnosticCollector;
import javax.tools.JavaCompiler;
import javax.tools.JavaCompiler.CompilationTask;
import javax.tools.JavaFileManager;
import javax.tools.JavaFileObject;
import javax.tools.ToolProvider;

public class CompilerAPI {

    public static void main(String args[]) throws Exception {

        /* Creating dynamic java source code file object */
        JavaFileObject iObject = new StringSourceCodeObject("I", SourceStrings.I);
        JavaFileObject youObject = new StringSourceCodeObject("You", SourceStrings.YOU);
        JavaFileObject jfObjects[] = new JavaFileObject[]{iObject, youObject};
        /* Units to compile */
        Iterable<JavaFileObject> units = Arrays.asList(jfObjects);
        /* Instantiating the java compiler */
        JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
        /* Get compiler file manager to show what to read. */
        // (DEFAULT LISTENER, Locale.getDefault(), Charset.defaultCharset() )
        JavaFileManager manager = compiler.getStandardFileManager(null, null, null);
        /* Compilation options - here: place in target directory */
        String[] compileOptions = new String[]{"-d", "target/classes"};
        Iterable<String> options = Arrays.asList(compileOptions);
        /* Diagnostic placeholder */
        DiagnosticCollector<JavaFileObject> sink = new DiagnosticCollector<JavaFileObject>();
        /* 1st null: where to write (default), 2nd null: no annotations processed */
        CompilationTask task = compiler.getTask(null, manager, sink, options, null, units);
        /* Go, go, go */
        boolean status = task.call();
        if (!status) {
            for (Diagnostic<? extends JavaFileObject> d : sink.getDiagnostics()) {
                System.err.format("Error on line %d in %s", d.getLineNumber(), d);
            }
        }
        manager.close();// TRY to close the file manager
    }
}
