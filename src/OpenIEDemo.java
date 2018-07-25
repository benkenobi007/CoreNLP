import edu.stanford.nlp.ie.util.RelationTriple;
import edu.stanford.nlp.ling.CoreAnnotations;
import edu.stanford.nlp.naturalli.NaturalLogicAnnotations;
import edu.stanford.nlp.naturalli.OpenIE;
import edu.stanford.nlp.pipeline.Annotation;
import edu.stanford.nlp.pipeline.StanfordCoreNLP;
import edu.stanford.nlp.util.CoreMap;

import java.awt.image.BufferedImageFilter;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.InputStream;
import java.util.Collection;
import java.util.Properties;

public class OpenIEDemo {
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

    public static void main(String[] args) throws Exception{
        System.out.println(OpenIEDemo.class);
        String fileText = "";
        String line;
        FileReader reader = new FileReader("D:\\NLP\\olliejars\\KG_sentences\\Set4\\SentenceSet4.txt");
        BufferedReader fs = new BufferedReader(reader);
        while(( line = fs.readLine())!= null){
            fileText+=line+" ";
        }
        Properties props = new Properties();
        props.setProperty("annotators","tokenize, ssplit, pos, lemma, depparse, natlog, openie");
        props.setProperty("openie.format","ollie");
//        props.setProperty("openie.splitter.nomodel","true");
//        props.setProperty("openie.affinity_probability_cap","0.4");
//        props.setProperty("openie.triple.all_nominals","true");
//        props.setProperty("openie.splitter.model","D:\\NLP\\ollie jars\\engmalt.linear-1.7.mco");
//        props.setProperty("openie.resolve_coref","true");
//        props.setProperty("openie.splitter.disable","true");
//        props.setProperty("openie.triple.strict","false");
//        props.setProperty("openie.ignore_affinity","true");
//        props.setProperty("openie.max_entailments_per_clause","100");
        StanfordCoreNLP pipeline = new StanfordCoreNLP(props);
        Annotation doc = new Annotation(fileText);

        pipeline.annotate(doc);

        for(CoreMap sentence : doc.get(CoreAnnotations.SentencesAnnotation.class)){
            System.out.println("Sentence : "+ sentence.get(CoreAnnotations.TextAnnotation.class));
            Collection<RelationTriple> triples = sentence.get(NaturalLogicAnnotations.RelationTriplesAnnotation.class);
            for(RelationTriple t : triples){
                System.out.println("Textual mention : "+t.confidence+"\t" + t.subjectGloss()+"\t\t"+t.relationGloss()+"\t\t"+t.objectGloss());
//                System.out.println("Canonical Mention : "+t.confidence+"\t\t" + t.subjectLemmaGloss()+"\t\t"+t.relationLemmaGloss()+"\t\t"+t.objectLemmaGloss());
            }
            System.out.println();
        }
    }
}
