//Huang Qing A0221170Y  LAB07
import java.io.*;


public class GalacticCollegiate {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));

        String[] input = br.readLine().split(" ");
        //number of teams
        int n = Integer.parseInt(input[0]);

        // number of events
        int m = Integer.parseInt(input[1]);

        AVLTree data = new AVLTree(n);


        for (int i = 0; i < m; i ++) {
            String[] temp = br.readLine().split(" ");
            int teamNum = Integer.parseInt(temp[0]);
            int totalPenalty = Integer.parseInt(temp[1]);

            int totalQnSolve = 1;

            TeamNode node = data.find(teamNum);

            if (node != null) {
                totalQnSolve = node.qnSolved + 1;
                totalPenalty = totalPenalty + node.penalty;

                data.remove(node);
            }

            TeamNode addNode = new TeamNode(teamNum, totalQnSolve, totalPenalty);
            data.insert(addNode);


            int teamCurRanking = data.sortByRank(1);
            pw.println(teamCurRanking);

        }
        br.close();
        pw.flush();

    }

}
class TeamNode {
    TeamNode left;
    TeamNode right;
    TeamNode parent;
    int h;
    int rank;
    int groupNum;
    int penalty;
    int qnSolved;

    public TeamNode (int groupNum, int qnSolved, int penalty) {
        this.h = 0;
        this.rank = 0;
        this.groupNum = groupNum;
        this.penalty =  penalty;
        this.qnSolved = qnSolved;
    }

    public boolean isEqual(TeamNode node) {
        return node.groupNum == this.groupNum;
    }


}

class AVLTree {
    TeamNode root;
    int[][] arr;

    public AVLTree (int n) {
        // stores all teams in AVL tree and corresponding penalty and qnSolved
        arr = new int[n+1][2];
        root = null;


    }

    int height(TeamNode n) {
        if (n == null) {
            return -1;
        } else {
            return n.h;
        }
    }

    void leftRotate(TeamNode node) {
        TeamNode p = node.parent;
        TeamNode x = node.right;
        TeamNode y = x.left;

        node.parent = x;
        node.right = y;
        if (y != null) {
            y.parent = node;
        }
        x.left = node;

        if(p == null) {
            root = x;
            x.parent = null;
        } else {
            if(p.left != null && p.left.isEqual(node)) {
                p.left = x;
            } else {
                p.right = x;
            }

            x.parent = p;

        }

        updateHeight(node);
        updateRank(node);
        updateHeight(x);
        updateRank(x);
    }

    void rightRotate(TeamNode node) {
        TeamNode p = node.parent;
        TeamNode x = node.left;
        TeamNode y = x.right;

        node.parent = x;
        node.left = y;
        if (y != null) {
            y.parent = node;
        }
        x.right = node;

        if(p == null) {
            root = x;
            x.parent = null;
        } else {
            if(p.left != null && p.left.isEqual(node)) {
                p.left = x;
            } else {
                p.right = x;
            }

            x.parent = p;

        }

        updateHeight(node);
        updateRank(node);
        updateHeight(x);
        updateRank(x);
    }

    public void newHeight (TeamNode node) {
        node.h = 1 + Math.max(height(node.left), height(node.right));
    }

    int balanceFactor (TeamNode node) {
        if (node == null) {
            return 0;
        } else {
            return height(node.left) - height(node.right);
        }
    }

    public boolean isHigherScore (TeamNode first, TeamNode second) {

        //first group has solved more qn
        if (second.qnSolved > first.qnSolved) {
            return true;
        }

        //both group solved equal qn, but first group has less penalty points
        if (second.qnSolved == first.qnSolved && second.penalty < first.penalty) {
            return true;
        }

        //both group solved equal qn and penalty points, compare group number
        if (second.qnSolved == first.qnSolved
                && second.penalty == first.penalty && second.groupNum < first.groupNum) {
            return true;
        }
        // all other cases return false
        return false;
    }

    void insert (TeamNode node) {
        int teamNum = node.groupNum;
        int qnSolve = node.qnSolved;
        int penalty = node.penalty;

        int [] adder = {qnSolve, penalty};
        arr[teamNum] = adder;
        insert(root, node);
    }


    //inserting new node to the tree
    void insert (TeamNode node, TeamNode newNode) {
        if (root == null) {
            root = newNode;
            return;
        }

        if (!isHigherScore(node, newNode)) {
            if (node.left == null) {
                node.left = newNode;
                newNode.parent = node;
            } else {
                insert(node.left, newNode);
            }

        } else  {
            if (node.right == null) {
                node.right = newNode;
                newNode.parent = node;
            } else {
                insert(node.right, newNode);
            }
        }

        updateRank(node);

        updateHeight(node);

        reBalance(node);
    }


    public int getR(TeamNode node) {
        if(node == null) {
            return -1;
        }
        return  node.rank;
    }

    public void updateRank (TeamNode node) {

        int lSide = getR(node.left) + 1;
        int rSide = getR(node.right) + 1;
        node.rank = lSide + rSide;
    }

    public int getH(TeamNode node) {
        if (node == null) {
            return -1;
        }
        return  node.h;
    }

    public void updateHeight (TeamNode node) {
        int newHeight;
        if(node == null) {
            newHeight = -1;
        } else {
            newHeight = Math.max(getH(node.left), getH(node.right)) + 1;
        }
        node.h = newHeight;
    }



    public TeamNode find(int num) {
        if (arr[num][0] == 0) {
            return null;
        }
        TeamNode current = root;
        TeamNode newNode = new TeamNode(num, arr[num][0], arr[num][1]);
        return find(root, newNode);
    }

    public TeamNode find(TeamNode current, TeamNode node) {
        if (current == null) {
            return null;
        }

        if (current.groupNum == node.groupNum) {
            return current;
        } else if (isHigherScore(current, node)) {
            return find(current.right, node);
        } else {
            return  find(current.left, node);
        }

    }



    public TeamNode predecessor(TeamNode node) {
        node = node.left;
        if (node == null) {
            return node;
        }
        while(node.right != null) {
            node = node.right;
        }
        return node;
    }

    public TeamNode successor(TeamNode node) {
        node = node.right;
        if (node == null) {
            return node;
        }
        while(node.left != null) {
            node = node.left;
        }
        return node;
    }

    public void reBalance (TeamNode node) {
        if (node == null) {
            return;
        }
        int hLeft = getH(node.left);
        int hRight = getH(node.right);

        int differenceInH = hLeft - hRight;

        //it is balanced, do nothing
        if (differenceInH <= 1 && -1 <= differenceInH) {
            return;
        }

        //left tree longer
        if (differenceInH > 1) {
            if (getH(node.left.left) > getH(node.left.right)) {
                rightRotate(node);
            } else {
                leftRotate(node.left);
                rightRotate(node);
            }

            //right tree longer
        } else {
            if (getH(node.right.right) > getH(node.right.left)) {
                leftRotate(node);
            } else {
                rightRotate(node.right);
                leftRotate(node);
            }
        }
        updateRank(node);
        updateHeight(node);
    }

    public int sortByRank (int favoriteTeam) {
        int allNodes = getR(root) + 1;
        if (arr [favoriteTeam][0] == 0) {
            return allNodes + 1;
        }
        TeamNode n = find(favoriteTeam);

        //Team at root
        if (root.isEqual(n)) {
            //add right child +1 and next rank +1
            return getR(root.right) + 2;
        }

        //Team at right side
        if (!isHigherScore(root, n)) {
            TeamNode cur = n;
            TeamNode p = n.parent;

            int previousRanks = getR(n.left) +1;
            while (!p.isEqual(root)) {
                if (p.right != null && p.right.isEqual(cur)) {
                    //add parent and left node
                    previousRanks = previousRanks + getR(p.left) + 2;
                }
                cur = p;
                p = p.parent;
            }
            return allNodes - previousRanks;

            // Team at left side
        } else {
            TeamNode cur = n;
            TeamNode p = n.parent;

            int nextRank = getR(n.right) + 1;
            while (!p.isEqual(root)) {
                if(p.left != null && p.left.isEqual(cur)) {
                    //add parent and right node
                    nextRank = nextRank + getR(p.right) + 2;
                }
                cur = p;
                p = p.parent;
            }
            return  nextRank + 1;

        }
    }


    public void remove(TeamNode node) {
        if (node == null) {
            return;
        }

        int teamNumber = node.groupNum;
        //remove this node from the arr
        arr[teamNumber][0] = 0;
        arr[teamNumber][1] = 0;

        if (node.isEqual(root)) {
            TeamNode cur;
            if(root.right != null) {
                cur = successor(root);
            } else  if (root.left != null) {
                cur = predecessor(root);
            } else {
                root = null;
                return;
            }

            remove(cur);
            //update new data in the note
            node.groupNum = cur.groupNum;
            node.qnSolved = cur.qnSolved;
            node.penalty = cur.penalty;

            //update 2d arr
            int[] array = {cur.qnSolved, cur.penalty};
            arr[cur.groupNum] = array;
            return;
        }

        //case when deleting leaf
        TeamNode p = node.parent;
        if (node.right == null && node.left == null) {
            if (p.left != null && p.left.isEqual(node)) {
                p.left = null;
            } else if (p.right.isEqual(node)) {
                p.right = null;
            }

            TeamNode previous = p;
            while (p != null) {
                updateRank(p);
                updateHeight(p);
                p = p.parent;
            }

            node.parent = null;
            reBalance(previous);

            //one side of the tree is empty
        } else if (node.right == null || node.left == null) {

            if (node.right != null) {
                if (p.left != null && p.left.isEqual(node)) {
                    p.left = node.right;
                    node.right.parent = p;
                } else {
                    p.right = node.right;
                    node.right.parent = p;
                }
            } else if(node.left != null) {
                if (p.left != null && p.left.isEqual(node)) {
                    p.left = node.left;
                    node.left.parent = p;
                } else {
                    p.right = node.left;
                    node.left.parent = p;
                }
            }

            TeamNode previous = p;
            while (p != null) {
                updateRank(p);
                updateHeight(p);
                p = p.parent;
            }
            reBalance(previous);

        } else {
            TeamNode next;
            next = successor(node);

            int[] array = {next.qnSolved, next.penalty};
            remove(next);
            updateRank(p);
            updateHeight(p);
            arr[next.groupNum] = array;

            //update new data in the note
            node.groupNum = next.groupNum;
            node.qnSolved = next.qnSolved;
            node.penalty = next.penalty;
        }


    }

}