import java.util.ArrayList;

public class NumberListDriver {

    public static void main(String[] args) {
        NumberList nl = new NumberList();
        ArrayList<Integer> myList = nl.multiplesOf3();
        System.out.println(nl.toString(myList));
        nl.removeEvens(myList);
        System.out.println(nl.toString(myList));
    }
}
