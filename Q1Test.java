public class Q1Test {
     public static void main(String[] args) {

          String result, traversal1, traversal2, traversal3;

          traversal1 = "1-2";
          traversal2 = "2-1-3";
          traversal3 = "1-3-2";
          result = verify(traversal1, traversal2, traversal3);
          System.out.println("Traversal 1 : " + traversal1);
          System.out.println("Traversal 2 : " + traversal2);
          System.out.println("Traversal 3 : " + traversal3);
          System.out.println("Expected Result : Invalid traversals");
          System.out.println("  Actual Result : " + result);
          System.out.println();

          traversal1 = "1-2-3";
          traversal2 = "2-1-3";
          traversal3 = "1-3-2";
          result = verify(traversal1, traversal2, traversal3);
          System.out.println("Traversal 1 : " + traversal1);
          System.out.println("Traversal 2 : " + traversal2);
          System.out.println("Traversal 3 : " + traversal3);
          System.out.println(
                    "Expected Result : Traversal 1 - Inorder, Traversal 2 - Preorder, Traversal 3 - Postorder");
          System.out.println("  Actual Result : " + result);
          System.out.println();

          traversal1 = "1-2-3";
          traversal2 = "3-2-1";
          traversal3 = "2-3-1";
          result = verify(traversal1, traversal2, traversal3);
          System.out.println("Traversal 1 : " + traversal1);
          System.out.println("Traversal 2 : " + traversal2);
          System.out.println("Traversal 3 : " + traversal3);
          System.out.println("Expected Result : Invalid traversals");
          System.out.println("  Actual Result : " + result);
          System.out.println();

          traversal1 = "3-1-2-5-4";
          traversal2 = "2-1-4-5-3";
          traversal3 = "1-2-3-4-5";
          result = verify(traversal1, traversal2, traversal3);
          System.out.println("Traversal 1 : " + traversal1);
          System.out.println("Traversal 2 : " + traversal2);
          System.out.println("Traversal 3 : " + traversal3);
          System.out.println(
                    "Expected Result : Traversal 1 - Preorder, Traversal 2 - Postorder, Traversal 3 - Inorder");
          System.out.println("  Actual Result : " + result);
          System.out.println();

          traversal1 = "10-20-30-40-50";
          traversal2 = "20-10-30-40-50";
          traversal3 = "10-50-40-30-20";
          result = verify(traversal1, traversal2, traversal3);
          System.out.println("Traversal 1 : " + traversal1);
          System.out.println("Traversal 2 : " + traversal2);
          System.out.println("Traversal 3 : " + traversal3);
          System.out.println(
                    "Expected Result : Traversal 1 - Inorder, Traversal 2 - Preorder, Traversal 3 - Postorder");
          System.out.println("  Actual Result : " + result);
          System.out.println();
     } 

      // write your codes here
     private static class Node {
          int val;
          Node left, right;
          Node(int val) { this.val = val; }
     }

     private static Node insert(Node root, int val) {
          if (root == null) return new Node(val);
          if (val < root.val) root.left = insert(root.left, val);
          else root.right = insert(root.right, val);
          return root;
     }

     private static void inorder(Node n, StringBuilder sb) {
          if (n == null) return;
          inorder(n.left, sb);
          append(sb, n.val);
          inorder(n.right, sb);
     }

     private static void postorder(Node n, StringBuilder sb) {
          if (n == null) return;
          postorder(n.left, sb);
          postorder(n.right, sb);
          append(sb, n.val);
     }

     private static void append(StringBuilder sb, int val) {
          if (sb.length() > 0) sb.append('-');
          sb.append(val);
     }

     // returns true if (in, pre, post) describe the same BST
     private static boolean matches(String in, String pre, String post) {
          Node root = null;
          try {
               for (String s : pre.split("-")) root = insert(root, Integer.parseInt(s.trim()));
          } catch (NumberFormatException e) {
               return false;
          }
          StringBuilder inSb = new StringBuilder();
          StringBuilder postSb = new StringBuilder();
          inorder(root, inSb);
          postorder(root, postSb);
          return inSb.toString().equals(in.trim()) && postSb.toString().equals(post.trim());
     }

     public static String verify(String traversal1, String traversal2, String traversal3){
          String[] t = {traversal1, traversal2, traversal3};
          String[] names = {"Inorder", "Preorder", "Postorder"};
          int[][] perms = {{0,1,2},{0,2,1},{1,0,2},{1,2,0},{2,0,1},{2,1,0}};
          // perm[k] = index of the traversal acting as inorder / preorder / postorder
          for (int[] p : perms) {
               if (matches(t[p[0]], t[p[1]], t[p[2]])) {
                    String[] label = new String[3];
                    for (int k = 0; k < 3; k++) label[p[k]] = names[k];
                    return "Traversal 1 - " + label[0] + ", Traversal 2 - " + label[1]
                              + ", Traversal 3 - " + label[2];
               }
          }
          return "Invalid traversals";
     }
}