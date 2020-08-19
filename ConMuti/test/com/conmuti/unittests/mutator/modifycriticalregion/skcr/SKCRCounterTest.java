package com.conmuti.unittests.mutator.modifycriticalregion.skcr;

import com.conmuti.conmuti.mutator.modifycriticalregion.shcr.SHCRDownCounter;
import com.conmuti.conmuti.mutator.modifycriticalregion.skcr.SKCRCounter;
import com.github.javaparser.JavaParser;
import com.github.javaparser.ast.CompilationUnit;
import org.junit.Test;

import static org.junit.Assert.*;

public class SKCRCounterTest {

    @Test
    public void visit() {
        CompilationUnit cu = JavaParser.parse("package com.example.example;\n" +
                "\n" +
                "public class TestClass {\n" +
                "    public void testMethod(){\n" +
                "        System.out.println(\"outside scope\");\n" +
                "        synchronized(this){\n" +
                "            System.out.println(\"inside scope\");\n" +
                "            System.out.println(\"inside scope\");\n" +
                "        }\n" +
                "        System.out.println(\"outside scope\");\n" +
                "    }\n" +
                "}");
        SKCRCounter skcr = new SKCRCounter();
        skcr.visit(cu,null);
        assertEquals(0,skcr.getMutantCount());

        cu = JavaParser.parse("package com.example.example;\n" +
                "\n" +
                "public class TestClass {\n" +
                "    public void testMethod(){\n" +
                "        System.out.println(\"outside scope\");\n" +
                "        synchronized(this){\n" +
                "            System.out.println(\"inside scope\");\n" +
                "            System.out.println(\"inside scope\");\n" +
                "            System.out.println(\"inside scope\");\n" +
                "        }\n" +
                "        System.out.println(\"outside scope\");\n" +
                "    }\n" +
                "}");
        skcr = new SKCRCounter();
        skcr.visit(cu,null);
        assertEquals(1,skcr.getMutantCount());
    }
}