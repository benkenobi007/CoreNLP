import edu.stanford.nlp.ling.CoreAnnotations;
import edu.stanford.nlp.ling.CoreLabel;
import edu.stanford.nlp.pipeline.Annotation;
import edu.stanford.nlp.pipeline.CoreDocument;
import edu.stanford.nlp.pipeline.StanfordCoreNLP;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Properties;

public class testMyAnnotator {
    public static String text = "Once a rich merchant’s house was robbed. The merchant suspected that the thief was one of his servants. So he went to Birbal and mentioned the incident. Birbal went to his house and assembled all of his servants and asked that who stole the merchant’s things. Everybody denied.\n" +
            "\n" +
            "Birbal thought for a moment, then gave a stick of equal length to all the servants of the merchant and said to them that the stick of the real thief will be longer by two inches tomorrow. All the servants should be present here again tomorrow with their sticks.\n" +
            "\n" +
            "All the servants went to their homes and gathered again at the same place the next day. Birbal asked them to show him their sticks. One of the servants had his stick shorter by two inches. Birbal said, “This is your thief, merchant.”\n" +
            "\n" +
            "Later the merchant asked Birbal, “How did you catch him?” Birbal said, “The thief had already cut his stick short by two inches in the night fearing that his stick will be longer by two inches by morning.”\n" +
            "\n" +
            "Moral: Truth will always Prevail.";
    static ArrayList<Integer> posList = new ArrayList<>();   //pos List for annotation doc
    static ArrayList<Integer> posList_coreDoc = new ArrayList<Integer>(); //pos List for coreDocument
    //TODO: not able to add tag "search"
    public static void main(String[] args) {

        try {
            Properties props = new Properties();
            InputStream is = new FileInputStream("MyAnnotator.properties");
            props.load(is);
//            props.setProperty("customAnnotatorClass.search","MyAnnotator");
//            props.setProperty("annotators","tokenize,ssplit,search");
            props.setProperty("Search.string","Birbal");  //Search.string <- string to be searched


            StanfordCoreNLP pipeline = new StanfordCoreNLP(props);
            Annotation doc = new Annotation(text);
            pipeline.annotate(doc);


//            MyAnnotator ma = new MyAnnotator("search", props);
//            ma.annotate(doc);

            //Annotate CoreDocument
            CoreDocument doc2 = new CoreDocument(text);
            pipeline.annotate(doc2);
//            ma.annotate(doc2);

            //posList for CoreDocument
            for(CoreLabel token: doc2.tokens()){
                Integer pos;
                if ((pos = token.get(SearchAnnotation.class)) != null) {
                    posList_coreDoc.add(pos);
                    System.out.println(token.word()+"\t"+token);
                }
            }

            //posList for annotation document
            for (CoreLabel token : doc.get(CoreAnnotations.TokensAnnotation.class)) {
                Integer pos;
                if ((pos = token.get(SearchAnnotation.class)) != null) {
                    posList.add(pos);
//                    System.out.println(token.word()+"\t"+token);
                }
            }

            System.out.println("Annotation Document");
            System.out.println("Search string found at indices : ");
            for (int a : posList) {
                System.out.println(a);
            }

            /*System.out.println("CoreDocument");
            System.out.println("Search string found at indices : ");
            for (int a : posList_coreDoc) {
                System.out.println(a);
            }*/
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
}
