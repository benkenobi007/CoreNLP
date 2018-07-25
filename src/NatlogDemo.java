import edu.stanford.nlp.ling.CoreAnnotations;
import edu.stanford.nlp.ling.CoreLabel;
import edu.stanford.nlp.ling.tokensregex.types.Value;
import edu.stanford.nlp.naturalli.NaturalLogicAnnotations;
import edu.stanford.nlp.naturalli.OperatorSpec;
import edu.stanford.nlp.naturalli.Polarity;
import edu.stanford.nlp.pipeline.Annotation;
import edu.stanford.nlp.pipeline.CoreDocument;
import edu.stanford.nlp.pipeline.StanfordCoreNLP;
import edu.stanford.nlp.util.CoreMap;

import java.util.Collection;
import java.util.Properties;

public class NatlogDemo {
    public static String text = "Once a rich merchant’s house was robbed. The merchant suspected that the thief was one of his servants. So he went to Birbal and mentioned the incident. Birbal went to his house and assembled all of his servants and asked that who stole the merchant’s things. Everybody denied.\n" +
            "\n" +
            "Birbal thought for a moment, then gave a stick of equal length to all the servants of the merchant and said to them that the stick of the real thief will be longer by two inches tomorrow. All the servants should be present here again tomorrow with their sticks.\n" +
            "\n" +
            "All the servants went to their homes and gathered again at the same place the next day. Birbal asked them to show him their sticks. One of the servants had his stick shorter by two inches. Birbal said, “This is your thief, merchant.”\n" +
            "\n" +
            "Later the merchant asked Birbal, “How did you catch him?” Birbal said, “The thief had already cut his stick short by two inches in the night fearing that his stick will be longer by two inches by morning.”\n" +
            "\n" +
            "Moral: Truth will always Prevail.";

    public static void main(String[] args) {
        Properties props = new Properties();
        props.setProperty("annotators","tokenize,ssplit,pos,lemma,depparse,natlog");
        StanfordCoreNLP pipeline = new StanfordCoreNLP(props);
        Annotation doc =new Annotation(text);

        pipeline.annotate(doc);

        for(CoreLabel token : doc.get(CoreAnnotations.TokensAnnotation.class)){
//            System.out.println("Sentence : "+ token.get(CoreAnnotations.TextAnnotation.class));
//            NaturalLogicAnnotations.OperatorAnnotation oa = new NaturalLogicAnnotations.OperatorAnnotation();

//            Collection<Polarity> = sentence.get(NaturalLogicAnnotations.)
            OperatorSpec OSpec = token.get(NaturalLogicAnnotations.OperatorAnnotation.class);
            Polarity pol = token.get(NaturalLogicAnnotations.PolarityAnnotation.class);
            if(OSpec!=null) {
                System.out.println("Token : "+ token.get(CoreAnnotations.TextAnnotation.class));
                System.out.println("Operator Annotation : " + OSpec.toString());
            }
            else if(pol!=null){
                System.out.println("Token : "+ token.get(CoreAnnotations.TextAnnotation.class));
                System.out.println("Polarity Annotation : " + pol.toString());
            }


        }

    }
}
