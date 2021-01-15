/**
 * @title javaSilver / FileSystem.java
 * @author shika
 * @date 2021-01-15
 */
package javaSilver;

public class FileSystem {

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
        here.readFile(className);

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

*/
