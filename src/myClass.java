import edu.stanford.nlp.coref.data.CorefChain;
import edu.stanford.nlp.ie.machinereading.structure.EntityMention;
import edu.stanford.nlp.ie.machinereading.structure.MachineReadingAnnotations;
import edu.stanford.nlp.ie.machinereading.structure.RelationMention;
import edu.stanford.nlp.ling.*;
import edu.stanford.nlp.ie.util.*;
import edu.stanford.nlp.pipeline.*;
import edu.stanford.nlp.semgraph.*;
import edu.stanford.nlp.trees.*;
import edu.stanford.nlp.util.CoreMap;

import java.util.*;


public class myClass {

    public static String text = "Joe Smith was born in California. " +
            "In 2017, he went to Paris, France in the summer. " +
            "His flight left at 3:00pm on July 10th, 2017. " +
            "After eating some escargot for the first time, Joe said, \"That was delicious!\" " +
            "He sent a postcard to his sister Jane Smith. " +
            "After hearing about Joe's trip, Jane decided she might go to France one day.";

    public static String text1 = "Once a rich merchant’s house was robbed. The merchant suspected that the thief was one of his servants. So he went to Birbal and mentioned the incident. Birbal went to his house and assembled all of his servants and asked that who stole the merchant’s things. Everybody denied.\n" +
            "\n" +
            "Birbal thought for a moment, then gave a stick of equal length to all the servants of the merchant and said to them that the stick of the real thief will be longer by two inches tomorrow. All the servants should be present here again tomorrow with their sticks.\n" +
            "\n" +
            "All the servants went to their homes and gathered again at the same place the next day. Birbal asked them to show him their sticks. One of the servants had his stick shorter by two inches. Birbal said, “This is your thief, merchant.”\n" +
            "\n" +
            "Later the merchant asked Birbal, “How did you catch him?” Birbal said, “The thief had already cut his stick short by two inches in the night fearing that his stick will be longer by two inches by morning.”\n" +
            "\n" +
            "Moral: Truth will always Prevail.";

    public static String text2 = "One fine day, Akbar lost his ring. When Birbal arrived in the court, Akbar told him “I have lost my ring. My father had given it to me as a gift. Please help me find it.”  Birbal said, ‘do not worry your Majesty, I will find your ring right now.’\n" +
            "\n" +
            "He said, ”Your Majesty the ring is here in this court itself, it is with one of the courtiers.  The courtier who has a straw in his beard has your ring.”  The courtier who had the emperors ring was shocked and immediately moved his hand over his beard.  Birbal noticed this act of the courtier.  He immediately pointed towards the courtier and said, ”Please search this man. He has the emperors ring.”\n" +
            "\n" +
            "Akbar could not understand how Birbal had Managed to find the ring.  Birbal then told Akbar that a guilty person is always scared.\n" +
            "\n" +
            "Moral: A Guilty Conscience need No Accuser.\n";
    public static String text3 = "One day Emperor Akbar and Birbal were taking a walk in the palace gardens. It was a nice summer morning and there were plenty of crows happily playing around the pond. While watching the crows, a question came into Akbar’s head. He wondered how many crows were there in his kingdom.\n" +
            "\n" +
            "Since Birbal was accompanying him, he asked Birbal this question. After a moment’s thought, Birbal replied, “There are ninety-five thousand four hundred and sixty-three crows in the Kingdom”.\n" +
            "\n" +
            "Amazed by his quick response, Akbar tried to test him again, “What if there are more crows than you answered?” Without hesitating, Birbal replied, “If there are more crows than my answer, then some crows are visiting from other neighboring kingdoms”. “And what if there are less crows”, Akbar asked. “Then some crows from our kingdom have gone on holidays to other places”.\n" +
            "\n" +
            "Moral: There is always a way if you think with ease.\n";
    public static String text4 = "Once a man sold his well to a farmer. Next day when a farmer went to draw the water from that well, the man did not allow him to draw the water from it. He said, “I have sold you the well, not the water, so you cannot draw the water from the well.”\n" +
            "\n" +
            "The farmer became very sad and came to the Emperor’s court. He described everything to the Emperor and asked for the justice.\n" +
            "\n" +
            "The Emperor called Birbal and handed over this case to him. Birbal called the man who sold the well to the farmer. Birbal asked, “Why don’t you let him use the water of the well. You have sold the well to the farmer.” The man replied, “Birbal, I have sold the well to the farmer, not the water. He has no right to draw the water from the well.”\n" +
            "\n" +
            "Then Birbal smiled and said to him, “Good, but look, since you have sold the well to this farmer, and you claim that water is yours, then you have no right to keep your water in the farmer’s well. Either you pay rent to the farmer to keep your water in his well, or you take that out of his well immediately.”\n" +
            "\n" +
            "The man understood, that his trick has failed. Birbal has outwitted him.\n" +
            "\n" +
            "Moral: Don’t Try to Cheat. You will end up paying for it regardless of how smart you think you are.\n";
    public static String text5 = "Once upon a time, a tortoise named Kambugriva lived near a lake. It was friends with two swans that also lived in the lake. One summer, the lake began to dry up, and there was little water for the animals. The swans told the tortoise that there was another lake in another forest, where they should go to survive. They came up with a plan to take the tortoise along. They made the tortoise bite the center of a stick and told it not to open its mouth, no matter what. The swans then held each end of the stick and flew, with the tortoise in between. People in the villages along the way saw a tortoise flying and were awestruck. There was a commotion on the ground about two birds taking a tortoise with the help of a stick. In spite of warnings from the swans, the tortoise opened its mouth and said: “what’s that commotion all about?” And then, it fell to its death.\n";
    public static String text6 = "Once Emperor Akbar became very angry at his favorite minister Birbal. He asked Birbal to leave the kingdom and go away. Accepting the command of the Emperor, Birbal left the kingdom and started working in a farmer’s farm in an unknown village far away under a different identity.\n" +
            "\n" +
            "As months passed, Akbar started to miss Birbal. He was struggling to solve many issues in the empire without Birbal’s advice. He regretted a decision, asking Birbal to leave the empire in anger. So Akbar sent his soldiers to find Birbal, but they failed to find him. No one knew where Birbal was. Akbar finally found a trick. He sent a message to the head of every village to send a pot full of the wit to the Emperor. If the pot full of wit can not be sent, fill the pot with diamonds and jewels.\n" +
            "\n" +
            "This message also reached Birbal, who lived in one of the villages. The people of the village got together. All started talking about what to do now? The wit is not a thing, which can be filled in the pot. How will we arrange for diamonds and jewels to fill the pot and send to the Emperor? Birbal who was sitting among the villagers said, “Give me the pot, I will fill the wit in one month’s end”. Everyone trusted Birbal and agreed to give him a chance. They still didn’t know his identity.\n" +
            "\n" +
            "Birbal took the pot with him and went back to the farm. He had planted watermelons on his farm. He selected a small watermelon and without cutting it from the plant, he put that in the pot. He started looking after it by providing water and fertilizer regularly. Within a few days, the watermelon grew into a pot so much that it was impossible to get it out of the pot.\n" +
            "\n" +
            "Soon, the watermelon reached to the same size as the pot from inside. Birbal then cut the watermelon from the vine and separated it with the pot. Later, he sent a pot to Emperor Akbar with a message that “Please remove the wit without cutting it from the pot and without breaking the pot”.\n" +
            "\n" +
            "Akbar watched the watermelon in the pot and realized that this can only be Birbal’s Work. Akbar himself came to the village, took Birbal back with him.\n" +
            "\n" +
            "Moral: Don’t hasten the decision. Think hard to find a solution for the strangest situations.\n";
    public static String text7 = "Raghuram Rajan was born on 3 February 1963 in Bhopal, Madhya Pradesh into a Tamil Brahmin family. He is the third of four children of R Govindarajan, an Indian Police Service officer who topped his 1953 batch.";
    public static String text8 = "From 1974 to 1981 Rajan attended Delhi Public School, RK Puram, In 1981 he enrolled at Indian Institute of Technology Delhi for a bachelor's degree in electrical engineering. In the final year of his four-year degree, he headed the Student Affairs Council. He graduated in 1985 and was awarded the Director's Gold Medal as the best all-round student. In 1987, he earned a Post Graduate Diploma in Business Administration from the Indian Institute of Management Ahmedabad, graduating with a gold medal for academic performance. He joined the Tata Administrative Services as a management trainee, but left after a few months to join the doctoral program at the MIT Sloan School of Management.";
    public static void main(String[] args) {
        CoreSentence sentence;

        // set up pipeline properties
        Properties props = new Properties();
        // set the list of annotators to run
//        props.setProperty("annotators", "tokenize,ssplit,pos,lemma,ner,parse,depparse,coref,kbp");
        //props.setProperty("annotators","tokenize,ssplit,pos,lemma,ner,parse,coref,quote");
//        props.setProperty("annotators", "tokenize,ssplit,pos,lemma,ner,parse,depparse,coref,kbp,relation");
//        props.setProperty("annotators","tokenize,ssplit,pos,lemma,ner,parse,depparse,relation");
        //props.setProperty("annotators","tokenize,ssplit,pos,lemma,ner");
        props.setProperty("annotators","tokenize,ssplit,pos,lemma,ner,depparse,coref");
        // set a property for an annotator, in this case the coref annotator is being set to use the neural algorithm
        props.setProperty("coref.algorithm", "statistical");

        // build pipeline
        StanfordCoreNLP pipeline = new StanfordCoreNLP(props);
        // create a document object
        CoreDocument document = new CoreDocument(text);
        // annnotate the document
        pipeline.annotate(document);

        // examples


/*
        // 10th token of the document
        List<CoreLabel> tokens = document.tokens();
        //CoreLabel token = document.tokens().get(10);
        for(CoreLabel token : tokens) {
           // System.out.println("Example: token");
            System.out.println(token);
            //System.out.println();
        }

        // text of the first sentence
        List<CoreSentence> phrases = document.sentences();
        String sentenceText = document.sentences().get(0).text();
        System.out.println("Example: sentence");
        for(CoreSentence sentenceText : phrases) {
            System.out.println(sentenceText);
        }
        System.out.println();

        // second sentence
        CoreSentence sentence = document.sentences().get(1);

        // list of the part-of-speech tags for the second sentence
        List<String> posTags = sentence.posTags();
        System.out.println("Example: pos tags");
        System.out.println(posTags);
        System.out.println();

        //get the lemmas
        List<String> lemmas = new LinkedList<String>();
        Annotation doc2 = new Annotation(text7);
        pipeline.annotate(doc2);
        List<CoreMap> sentences = doc2.get(CoreAnnotations.SentencesAnnotation.class);
        for(CoreMap sent: sentences) {
            // Iterate over all tokens in a sentence
            for (CoreLabel token: sent.get(CoreAnnotations.TokensAnnotation.class)) {
                // Retrieve and add the lemma for each word into the list of lemmas
                lemmas.add(token.get(CoreAnnotations.LemmaAnnotation.class));
            }
        }
        System.out.println("Lemmatization");
        for(String lemma : lemmas){
            System.out.println(lemma);
        }

         // list of the ner tags for the first sentence
        sentence = document.sentences().get(0);
        List<String> nerTags = sentence.nerTags();
        System.out.println("Example: ner tags");
        System.out.println(nerTags);
        System.out.println();



        // constituency parse for the second sentence
        sentence = document.sentences().get(1);
        Tree constituencyParse = sentence.constituencyParse();
        System.out.println("Example: constituency parse");
        System.out.println(constituencyParse);
        System.out.println();


        // dependency parse for the second sentence
        sentence = document.sentences().get(0);
        SemanticGraph dependencyParse = sentence.dependencyParse();
        System.out.println("Example: dependency parse");
        System.out.println(dependencyParse);
        System.out.println();


        // kbp relations found in fifth sentence
        System.out.println("Example: relations");
        for(int i=0;i<document.sentences().size();i++) {
            List<RelationTriple> relations =
                    document.sentences().get(i).relations();

            for (RelationTriple r : relations) {
                System.out.println(r);
            }
            //System.out.println(relations.get(0));
            System.out.println();
        }

        // entity mentions in the second sentence
        List<CoreEntityMention> entityMentions = document.sentences().get(2).entityMentions();
        System.out.println("Example: entity mentions");
        System.out.println(entityMentions);
        System.out.println();
*/

       // coreference between entity mentions
        List<CoreEntityMention> originalEntityMentions = document.sentences().get(0).entityMentions();
        for(CoreEntityMention originalEntityMention : originalEntityMentions) {
            //CoreEntityMention originalEntityMention = document.sentences().get(3).entityMentions().get(1);
            System.out.println("Example: original entity mention");
            System.out.println(originalEntityMention);
            System.out.println("Example: canonical entity mention");
            System.out.println(originalEntityMention.canonicalEntityMention().get());
           // System.out.println();
        }

        // get document wide coref info
        Map<Integer, CorefChain> corefChains = document.corefChains();
        System.out.println("Example: coref chains for document");
        System.out.println(corefChains);
        System.out.println();
/*
        // get quotes in document
        List<CoreQuote> quotes = document.quotes();
        CoreQuote quote = quotes.get(0);
        System.out.println("Example: quote");
        System.out.println(quote);
        System.out.println();

        // original speaker of quote
        // note that quote.speaker() returns an Optional
        System.out.println("Example: original speaker of quote");
        System.out.println(quote.speaker().get());
        System.out.println();

        // canonical speaker of quote
        System.out.println("Example: canonical speaker of quote");
        System.out.println(quote.canonicalSpeaker().get());
        System.out.println();

        //Relation Extraction
        try {

            Annotation doc = new Annotation("Barack Obama lives in America. Obama works for the Federal Goverment.");
            doc = document.annotation();
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
*/

    }


}
