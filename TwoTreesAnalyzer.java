package twotreesanalyzer;

import java.io.FileReader;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.function.Function;

/**
 * Performs insertions and searches, using the same data set,on a binary search
 * tree and an AVL tree to empirically compare the performance of these
 * operations on the trees.
 * 
 * @author Dylan Dubois
 * @SEE AVLTree, AVLTreeException, BSTree, BSTreeException,
 * @since 10-15-20117
 */
public class TwoTreesAnalyzer {
	// Define auxiliary/helper method(s) for the main method, if any, here

	/**
	 * @param args
	 *            the command line arguments
	 * @throws AVLTreeException
	 * @throws BSTreeException
	 * @throws java.io.IOException
	 */
	public static void main(String[] args) throws AVLTreeException, BSTreeException, IOException {
		int bsDepths = 0, avlDepths = 0; // tracks the sum of the depths of every node
		Scanner inFile = new Scanner(new FileReader(args[0]));
		AVLTree<String> avltree = new AVLTree<>();
		BSTree<String> bstree = new BSTree<>();
		Function<String, PrintStream> printUpperCase = x -> System.out.printf("%S\n", x); // function example from Duncan. Hope this is okay :)
		ArrayList<String> words = new ArrayList<>(); // Way to store the words without opening the file twice
		while (inFile.hasNext()) { // inserting words into each tree without using an extra loop
			words.add(inFile.next().toUpperCase());
			bstree.insert(words.get(words.size() - 1));
			avltree.insert(words.get(words.size() - 1));
		}
		inFile.close();
		// VVV Prints table 1 VVV
		System.out.printf("Table 1: Binary Search Tree [%s]\n" + "Level-Order Traversal\n"
				+ "=========================================\n" + "Word\n"
				+ "-----------------------------------------\n", args[0]);
		bstree.levelTraverse(printUpperCase);
		System.out.println("-----------------------------------------\n");
		// VVV Prints table 2 VVV
		System.out.printf(
				"Table 2: AVL Tree [%s]\n" + "Level-Order Traversal\n" + "=========================================\n"
						+ "Word\n" + "-----------------------------------------\n",
				args[0]);
		avltree.levelTraverse(printUpperCase);
		System.out.println("-----------------------------------------\n");
		// VVV Prints table 3 VVV
		System.out.printf("Table 3:Number of Nodes vs Height vs Diameter\n" + "Using Data in [%s]\n"
				+ "=========================================\n" + "Tree  # Nodes   Height   Diameter\n"
				+ "-----------------------------------------\n" + "BST\t%d\t  %d\t  %d\n" + "AVL\t%d\t  %d\t  %d\n",
				args[0], bstree.size(), bstree.height(), bstree.diameter(), avltree.size(), avltree.height(),
				avltree.diameter());
		System.out.println("-----------------------------------------\n");
		for (int i = 0; i < words.size(); i++) { //searches the trees for each word, totaling their depths
			bsDepths += 1 + bstree.depth(words.get(i));
			avlDepths += 1 + avltree.depth(words.get(i));
		}
		// VVV Prints table 4nn VVV
		System.out.printf("Table 4:Total Number of Node Accesses\n" + "Searching for all the Words in [%s]\n"
				+ "=========================================\n" + "Tree        # Nodes\n"
				+ "-----------------------------------------\n" + "BST             %d\n" + "AVL             %d\n",
				args[0], bsDepths, avlDepths);
		System.out.println("-----------------------------------------\n");
	}
}
