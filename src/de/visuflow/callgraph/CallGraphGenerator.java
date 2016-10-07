package de.visuflow.callgraph;

import java.util.HashMap;
import java.util.Map;

import soot.Body;
import soot.BodyTransformer;
import soot.G;
import soot.Main;
import soot.PackManager;
import soot.SootMethod;
import soot.Transform;
import soot.options.Options;

public class CallGraphGenerator {

    public void runAnalysis(final HashMap<SootMethod, GraphStructure> hashMap) {
        G.reset();
        Transform transform = new Transform("jtp.analysis", new BodyTransformer() {

            @Override
            protected void internalTransform(Body b, String phaseName, Map<String, String> options) {

                Options.v().set_keep_line_number(true);
                Options.v().debug();
                IntraproceduralAnalysis ipa = new IntraproceduralAnalysis(b, hashMap);
                ipa.doAnalyis();
            }

        });
        PackManager.v().getPack("jtp").add(transform);
        //		String rtJar = System.getProperty("java.home") + File.separator + "lib" + File.separator + "rt.jar";
        Main.main(new String[] { "-pp", "-process-dir", "C:/Users/Shashank B S/Documents/Projects/visuflow-plugin/targetBin2/", "-src-prec", "class", "-output-format", "none", "-keep-line-number","de.visuflow.analyzeMe.ex2.TargetClass2" });
    }
}
