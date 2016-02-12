
package multimatch;

/**
 *
 * @author Ethan Hotz
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        ClassLoader loader = Main.class.getClassLoader();
        System.out.println(loader.getResource("multimatch/Main.class"));
        UserInterface UI = new UserInterface();
        
    }
    
}
