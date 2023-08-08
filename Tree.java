import java.util.Scanner;

public class Tree {
    private TreeNode root;

    public Tree(TreeNode root) {
        this.root = root;
    }

    public boolean addNode(String label, String prompt, String message, String parentLabel) {
        TreeNode parentNode = getNodeReference(parentLabel);
        if (parentNode != null) {
            TreeNode newNode = new TreeNode(label, message, prompt);
            if (parentNode.getLeft() == null) {
                parentNode.setLeft(newNode);
            } else if (parentNode.getMiddle() == null) {
                parentNode.setMiddle(newNode);
            } else if (parentNode.getRight() == null) {
                parentNode.setRight(newNode);
            } else {
                return false; 
            }
            return true;
        }
        return false; 
    }

    public TreeNode getNodeReference(String label) {
        return getNodeReference(root, label);
    }

    private TreeNode getNodeReference(TreeNode currentNode, String label) {
        if (currentNode == null) {
            return null;
        }
        if (currentNode.getLabel().equals(label)) {
            return currentNode;
        }
        TreeNode foundNode = getNodeReference(currentNode.getLeft(), label);
        if (foundNode == null) {
            foundNode = getNodeReference(currentNode.getMiddle(), label);
        }
        if (foundNode == null) {
            foundNode = getNodeReference(currentNode.getRight(), label);
        }
        return foundNode;
    }

    public void preOrder() {
        preOrder(root);
    }

    private void preOrder(TreeNode node) {
        if (node != null) {
            System.out.println("Label: " + node.getLabel());
            System.out.println("Prompt: " + node.getPrompt());
            System.out.println("Message: " + node.getMessage());
            preOrder(node.getLeft());
            preOrder(node.getMiddle());
            preOrder(node.getRight());
        }
    }

    public void beginSession() {
        beginSession(root);
    }

    private void beginSession(TreeNode node) {
        if (node != null) {
            Scanner sessionIn = new Scanner(System.in);
            while (true) {
                System.out.println(node.getMessage());
                if (node.isLeaf()) {
                    System.out.println("Thank you for using our automated help system.");
                    break;
                }
                System.out.println("Choose an option:");
                System.out.println("1) " + node.getLeft().getPrompt());
                System.out.println("2) " + node.getMiddle().getPrompt());
                System.out.println("3) " + node.getRight().getPrompt());
                System.out.println("0) Exit Session.");
                
                int option = sessionIn.nextInt();
                TreeNode nextNode = null;
    
                switch (option) {
                    case 0:
                        System.out.println("Exiting session.");
                        return;
                    case 1:
                        nextNode = node.getLeft();
                        break;
                    case 2:
                        nextNode = node.getMiddle();
                        break;
                    case 3:
                        nextNode = node.getRight();
                        break;
                    default:
                        System.out.println("Invalid option. Please choose a valid option.");
                        continue;
                }
    
                if (nextNode != null) {
                    beginSession(nextNode);
                } else {
                    System.out.println("Invalid option. Please choose a valid option.");
                }
            }
        }
    }    
}