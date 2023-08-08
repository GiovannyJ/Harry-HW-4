import java.util.Arrays;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class TreeDriver {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        Tree tree = null;

        while (true) {
            System.out.println("Menu:");
            System.out.println("L) Load input file and build a tree");
            System.out.println("H) Start help session");
            System.out.println("T) Traverse the tree in pre-order");
            System.out.println("Q) Quit");

            String choice = scanner.nextLine().toUpperCase();
            switch (choice) {
                case "L":
                    
                    System.out.print("Enter the filename: ");
                    String userIn = scanner.nextLine();

                    try {
                        File file = new File(userIn);
                        Scanner fileReader = new Scanner(file);

                        String parentLabel = null;
                        String label = null;
                        TreeNode rootNode = null; 

                        while (fileReader.hasNextLine()) {
                            String line = fileReader.nextLine();

                            if (line.startsWith("Label: ")) {
                                label = line.substring(7);

                                
                                String[] labelParts = label.split("-");

                               
                                if (labelParts.length > 1) {
                                    parentLabel = String.join("-", Arrays.copyOfRange(labelParts, 0, labelParts.length - 1));
                                } else if (!label.equals("root")) {
                                    parentLabel = "root";
                                }else {
                                    parentLabel = null; 
                                }
                            } else if (line.startsWith("Prompt: ")) {
                                String prompt = line.substring(8);
                                String message = fileReader.nextLine().substring(9);

                                if (parentLabel != null) {
                                    tree.addNode(label, prompt, message, parentLabel);
                                } else {
                                    TreeNode newNode = new TreeNode(label, message, prompt);
                                    if (rootNode == null) {
                                        rootNode = newNode;
                                        tree = new Tree(rootNode);
                                    }
                                }
                            }
                        }
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    } finally{
                        System.out.println("Tree built successfully!");
                    } 


                    break;
                case "H":
                    if (tree != null) {
                        tree.beginSession();
                    } else {
                        System.out.println("No tree set up. Please load a tree first.");
                    }
                    break;
                case "T":
                    if (tree != null) {
                        tree.preOrder();
                    } else {
                        System.out.println("No tree set up. Please load a tree first.");
                    }
                    break;
                case "Q":
                    System.out.println("Exiting...");
                    scanner.close();
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid choice. Please select a valid option.");
            }
        }
    }
}