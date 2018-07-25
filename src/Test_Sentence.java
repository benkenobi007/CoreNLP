import edu.stanford.nlp.pipeline.*;

import java.util.List;

public class Test_Sentence {
    public static String text = "Joe Smith was born in California. " +
            "In 2017, he went to Paris, France in the summer. " +
            "His flight left at 3:00pm on July 10th, 2017. " +
            "After eating some escargot for the first time, Joe said, \"That was delicious!\" " +
            "He sent a postcard to his sister Jane Smith. " +
            "After hearing about Joe's trip, Jane decided she might go to France one day.";
    public static void main(String[] args) {
        Annotation doc = new Annotation(text);
        TokenizerAnnotator ta = new TokenizerAnnotator();
        ta.annotate(doc);
        new Test_Sentence().sentenceSplitter(doc);
    }

    void sentenceSplitter(Annotation doc){
        WordsToSentencesAnnotator wsa = new WordsToSentencesAnnotator();
        wsa.annotate(doc);
        CoreDocument document = new CoreDocument(doc);
        List<CoreSentence> sentences = document.sentences();
        for(CoreSentence s : sentences){
            System.out.println(s);
        }
    }
}
