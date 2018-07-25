import edu.stanford.nlp.coref.data.CorefChain;
import edu.stanford.nlp.ie.machinereading.structure.EntityMention;
import edu.stanford.nlp.ie.machinereading.structure.MachineReadingAnnotations;
import edu.stanford.nlp.ie.machinereading.structure.RelationMention;
import edu.stanford.nlp.ling.CoreAnnotations;
import edu.stanford.nlp.pipeline.*;
import edu.stanford.nlp.util.CoreMap;

import java.util.List;
import java.util.Map;
import java.util.Properties;

public class Test_Coref {
    public static String text = "Joe Smith was born in California. " +
            "In 2017, he went to Paris, France in the summer. " +
            "His flight left at 3:00pm on July 10th, 2017. " +
            "After eating some escargot for the first time, Joe said, \"That was delicious!\" " +
            "He sent a postcard to his sister Jane Smith. " +
            "After hearing about Joe's trip, Jane decided she might go to France one day.";

    public static void main(String[] args) {
        Properties props = new Properties();
        props.setProperty("annotators","tokenize,ssplit,pos,lemma,ner,parse,depparse,coref");
        props.setProperty("coref.algorithm", "statistical");
        StanfordCoreNLP pipeline = new StanfordCoreNLP(props);
        CoreDocument document = new CoreDocument(text);
        pipeline.annotate(document);



        int i = 0;
        int offset = 0; //offset to be added in the updated text, recomputed
        String updatedText = text;

        for(CoreSentence sentence : document.sentences()) {
            //System.out.println("Sentence #"+(i++) + " : "+sentence.text());
            List<CoreEntityMention> originalEntityMentions = sentence.entityMentions();
            for (CoreEntityMention originalEntityMention : originalEntityMentions) {

                //Replace each entity mention with canonical mention
                int beg = originalEntityMention.tokens().get(0).beginPosition() + offset;
                int numTok = originalEntityMention.tokens().size();
                int end = originalEntityMention.tokens().get(numTok-1).endPosition()+offset;
                updatedText = updatedText.substring(0,beg) + originalEntityMention.canonicalEntityMention().get() + updatedText.substring(end,updatedText.length());

                /*System.out.println("Example: original entity mention");
                System.out.println(originalEntityMention);
                System.out.println("Example: canonical entity mention");
                System.out.println(originalEntityMention.canonicalEntityMention().get());*/
                offset += originalEntityMention.canonicalEntityMention().get().toString().length() - originalEntityMention.toString().length();

                /*System.out.println("Updated text : ");
                System.out.println(updatedText);*/
            }
        }

        System.out.println("Text with canonical entities : ");
        System.out.println(updatedText);

        //relation extraction on updated text
        try {

            Annotation doc = new Annotation(updatedText);
           // doc = document.annotation();
            pipeline.annotate(doc);
            RelationExtractorAnnotator r = new RelationExtractorAnnotator(props);
            r.annotate(doc);
            for (CoreMap s : doc.get(CoreAnnotations.SentencesAnnotation.class)) {
                System.out.println("Sentence : " + s.get(CoreAnnotations.TextAnnotation.class));
                List<RelationMention> rls = s.get(MachineReadingAnnotations.RelationMentionsAnnotation.class);
                for (RelationMention r1 : rls) {
//                    r1.getArgNames();
                    System.out.println("Relation Type = " + r1.getType());

                    System.out.println("Entities : ");
                    for(EntityMention arg : r1.getEntityMentionArgs()){
                        System.out.println(arg.getValue());
//                        System.out.println(arg);
                    }

//                    System.out.println(r1.toString());
                }
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
