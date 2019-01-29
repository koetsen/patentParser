package org.koet;


import org.apache.commons.io.FilenameUtils;

import java.io.File;
import java.io.FilenameFilter;
import java.util.ArrayList;

public class App {

    public static void main(String[] args) {


        File patentDir = new File("/home/koet/programmieren/patente");

        FilenameFilter htmlFilter = (File dir, String name) -> {
            if (name.endsWith(".html")) {
                return true;
            }
            return false;
        };


        String[] files = patentDir.list(htmlFilter);

        for (String fileName : files) {

            File htmlFile = new File(FilenameUtils.concat(patentDir.toString(), fileName));
            ParsePatent patParser = new ParsePatent(htmlFile); //
            // String patAbstract = patParser.getAbstract();

            // System.out.println(htmlFile.toString());

            // Map<String, String> cpcClasses = patParser.getCPC();

            /*
             * if (cpcClasses.isEmpty()) { System.out.printf("%s ist leer%n",
             * htmlFile.toString()); }
             *
             * for (String key : cpcClasses.keySet()) { System.out.printf("%s --> %s%n",
             * key, cpcClasses.get(key)); }
             */

            // System.out.println(htmlFile.toString());
            // System.out.println(patParser.getInventionTitle());

            System.out.println(htmlFile.toString());
            ArrayList<String> blaBlubb = patParser.getInventors();


           System.out.println();

            // if (patAbstract.isEmpty()) { //System.out.println(htmlFile.toString());
            // }

            // System.out.println(patParser.getTitle());

            // System.exit(0); }
        }

        /*
         * File htmlFile = new File(FilenameUtils.concat(patentDir.toString(),
         * files[files.length - 1])); System.out.println(htmlFile.toString());
         * ParsePatent patParser = new ParsePatent(htmlFile); ArrayList<String> blaBlubb
         * = patParser.getDescription();
         */

        // System.out.println(
        // patParser.getCPC();
        // );

        /*
         * Map<String, String> cpcClasses = patParser.getCPC();
         *
         * for (String key : cpcClasses.keySet()) { System.out.printf("%s --> %s%n",
         * key, cpcClasses.get(key));
         *
         * }
         */
        // System.out.println(num);
        
        System.out.println("Fertig");


    }


}
