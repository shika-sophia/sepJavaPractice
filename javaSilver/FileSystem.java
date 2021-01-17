/**
 * @title javaSilver / FileSystem.java
 * @author shika
 * @date 2021-01-15
 */
package javaSilver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FileSystem {

    public void commandExecutor(){
        List<String> cmdList = new ArrayList<>(
                Arrays.asList("cmd","/c","@cd"));

        ProcessBuilder prcBld = new ProcessBuilder(cmdList);
        prcBld.redirectErrorStream(true);

        Process prc = null;
        try {
            prc = prcBld.start();

            BufferedReader reader =
                new BufferedReader(new InputStreamReader(prc.getInputStream()));

            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        int result = prc.exitValue();
        System.out.printf("result=%d%n", result);
    }//commandExecutor

    public void readFile(String className) {
        //String className = this.getClass().getName();

        String path = buildPath(className);

    }//readFile()

    private String buildPath(String className) {
        className = className.replace('.', '\\')
                             .substring(0, className.length() - 2);

        var bldPath = new StringBuilder();
            bldPath.append("C:\\Program Files\\pleiades\\workspace-web")
                   .append("\\sepJavaRecurrent\\src\\");
            bldPath.append(className);
            bldPath.append(".java");

        String path = bldPath.toString();

        //---- Test print ----
        //System.out.println("className: " + className);
        //System.out.println("path: " + path);

        return path;
    }//buildPath()

    //====== Test main() ======
    public static void main(String[] args) {
        String className = new Object(){ }.getClass().getName();

        var here = new FileSystem();
        //here.readFile(className);
        here.commandExecutor();

        //System.out.println("className @main(): " + className);
    }//main()

//    //====== Test console ======
//    //１～90行までコンソールに表示するテスト
//    public static void main(String[] args) {
//        final int ROW_BOUND = 90;
//        var bld = new StringBuilder();
//
//        for (int i = 1; i <= ROW_BOUND; i++) {
//            bld.append(i).append(": ----------\n");
//        }
//
//        System.out.println(bld.toString());
//    }//main()

}//class

/*
//====== Result of Test main ======
1: ----------
2: ----------
3: ----------
     :
88: ----------
89: ----------
90: ----------

//====== Test main()#className, buildPath() ======
className @main(): javaSilver.FileSystem$1
className: javaSilver\FileSystem
path: C:\Program Files\pleiades\workspace-web\sepJavaRecurrent\src\javaSilver\FileSystem.java

//====== Test commandExecutor() ======
◆JavaのProcessBuilderを使ってバッチファイルを実行する方法【初心者向け】
https://techacademy.jp/magazine/19751

"java -version"
openjdk version "11.0.5" 2019-10-15
OpenJDK Runtime Environment AdoptOpenJDK (build 11.0.5+10)
OpenJDK 64-Bit Server VM AdoptOpenJDK (build 11.0.5+10, mixed mode)
result=0

"@cd"
C:\Program Files\pleiades\workspace-web\sepJavaRecurrent
result=0

*/
