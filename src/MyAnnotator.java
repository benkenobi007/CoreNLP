
import edu.stanford.nlp.ling.CoreAnnotation;
import edu.stanford.nlp.ling.CoreAnnotations;
import edu.stanford.nlp.ling.CoreLabel;
import edu.stanford.nlp.pipeline.Annotation;
import edu.stanford.nlp.pipeline.Annotator;
import edu.stanford.nlp.pipeline.CoreDocument;
import edu.stanford.nlp.util.ArraySet;

import java.util.*;


//annotator for searching for a specified string
public class MyAnnotator implements Annotator {
//    String KT_SEARCH = "search";

    String sch;  //search string

    //ArrayList<Integer> positions = new ArrayList<>();

    public MyAnnotator(String name, Properties props){
        sch = props.getProperty("Search.string","the");  //gets search string if specified, else defaults to "the"
    }

    //Override for annotate with CoreDocument as input
    public void annotate(CoreDocument document){
        for(int i=0;i<document.tokens().size();i++){
            CoreLabel token = document.tokens().get(i);
            if(token.word().equals(sch))
                token.set(SearchAnnotation.class,i);
        }
    }

    //Search for the key in the document
    //Annotate matching tokens with the corresponding token index
    @Override
    public void annotate(Annotation annotation) {
//        System.out.println("Tokens");
//        for(CoreLabel token : annotation.get(CoreAnnotations.TokensAnnotation.class)){
        for(int i=0;i<annotation.get(CoreAnnotations.TokensAnnotation.class).size();i++){
            CoreLabel token = annotation.get(CoreAnnotations.TokensAnnotation.class).get(i);
//            System.out.println(token.word() +", tokenIndex = "+i);
            if(token.word().equals(sch)) {
//                positions.add(token.index());
                token.set(SearchAnnotation.class,i); //index of the token is saved

            }
        }
    }

    @Override
    public Set<Class<? extends CoreAnnotation>> requires() {
        return Collections.unmodifiableSet(new ArraySet<>(Arrays.asList(
                CoreAnnotations.TextAnnotation.class,
                CoreAnnotations.TokensAnnotation.class
        )));
    }

    @Override
    public Set<Class<? extends CoreAnnotation>> requirementsSatisfied() {
        return Collections.unmodifiableSet(new ArraySet<>());  //no requirements satisfied
    }

    /*public ArrayList<Integer> getPositions(){
        return positions;
    }*/
}

//Class for annotating the token with th index
class SearchAnnotation implements CoreAnnotation<Integer>{
    @Override
    public Class<Integer> getType(){
        return Integer.class;
    }
}
