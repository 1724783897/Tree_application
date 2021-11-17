import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class TreeNode {
    // id为代码。除根为0外，其余编码均为2-2-2-3格式，见文件
    int rootId = 0;

    int level = 0;
    int id;
    static int hMax;
    // 区域划分
    String name;
    // 双亲
    TreeNode parent;
    // 左兄弟
    TreeNode left;
    // 右兄弟
    TreeNode right;
    // 长子
    TreeNode first;

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getId() {
        return id;
    }

    public TreeNode getParent() {
        return parent;
    }

    public void setParent(TreeNode parent) {
        this.parent = parent;
    }

    public TreeNode getFirst() {
        return first;
    }

    public void setFirst(TreeNode first) {
        this.first = first;
    }

    public TreeNode getRight() {
        return right;
    }

    public void setRight(TreeNode right) {
        this.right = right;
    }

    public TreeNode getLeft() {
        return right;
    }

    public void setLeft(TreeNode left) {
        this.left = left;
    }

    /*
    *
    * 创建一颗空树
    *
    * */
    public TreeNode(){
        this.id = 0;
        this.name = "中华人民共和国";
        this.level = 1;
    }

    /*
    *
    * 创建根节点
    *
    * */
    public TreeNode(int id,String name){
        this.id = id;
        this.name = name;
        parent = first = right = null;
    }



    /*
    *
    * 从格式与area2016.csv相同的文件中读取数据，创建一棵树，
    * 若成功，返回根结点，
    * 若失败，返回null。
    * 要求每个结点的孩子结点均按id升序排列。
    *
    * */
    public TreeNode createTree1(String fromFile){
        String s;
        try {
            BufferedReader br = new BufferedReader(new FileReader(fromFile));
            while ((s=br.readLine())!=null){
                int pos = s.indexOf(",");
                int id = Integer.parseInt(s.substring(0,pos));
                String name = s.substring(pos+1);
                if(id%10000000==0){
                    this.add(0,id,name);
                    continue;
                }
                if(id%100000==0){
                    this.add(id/10000000*10000000,id,name);
                    continue;
                }
                if (id%1000==0) {
                    this.add(id/100000*100000, id, name);
                    continue;
                }

                    this.add(id/1000*1000,id,name);
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }


        return null;
    }

    private void visit(){
        System.out.println(id +"---->"+ name);
    }
    public void preOrder(){
        int k = (level - 1) * 2;
        while (k>0){
            System.out.print(" ");
            k--;
        }
        visit();
        TreeNode p = first;
        while(p != null){
            p.preOrder();
            p = p.right;
        }

    }


    /*
    *
    * 从调用save2()保存的数据文件中读取数据，创建一棵树，
    * 成功 ，返回根结点，
    * 若失败，返回null。
    * 要求每个结点的孩子结点均按id升序排列。
    *
    * */
    public static TreeNode createTree2(String fromFile){
        return null;
    }

    /*
    *
    * 保存树
    *
    * */
    public Status save1(String toFile){
        return Status.OK;
    }


    /*
    *
    * Status save2(String toFile)与save1()类似，但文件中每行格式为：父id，孩子id，孩子名称根结点的父id为-1
    *
    * */
    public Status save2(String toFile){
        return Status.OK;
    }

    /*
    *
    * 查找功能(id)
    *
    * */
    public TreeNode find(int id){
        if(this.id == id)
            return this;

        TreeNode p = first, r = null;
        while(p != null){
            r = p.find(id);
            if(r != null)
                break;
            p = p.right;
        }
        return r;
    }

    /*
    *
    * 添加操作
    * 在某个结点下添加孩子结点，
    * 若成功，应使parentId的所有孩子结点按id升序排列，
    * 即其长子的id最小，幼子的id最大。不允许树中存在id值相同的结点。
    *
    * */
    public Status add(int parentId, int childId, String childName){
        TreeNode pn, cn;

        cn = find(childId);
        if(cn != null){
            return Status.EXIST;}

        pn = find(parentId);
        if(pn == null){
            return Status.PARENT_NOT_EXIST;}

        cn = new TreeNode(childId,childName);
        cn.setParent(pn);
        cn.setLevel(pn.getLevel() + 1);

        TreeNode ln = null,rn = null, p = pn.first;

        while(p != null){
            ln = p;
            p = p.getRight();
        }

        if(ln == null){
            pn.setFirst(cn);
        }else{
            ln.setRight(cn);
            cn.setLeft(ln);
        }
        return Status.OK;
    }

    /*
    *
    * add(TreeNode childNode) 添加新的子树，结点的所有孩子按id升序排列。
    *
    * */
    public Status add(TreeNode childNode){
        return Status.OK;
    }


    /*
    *
    *   删除树
    *   删除根为id的子树。该操作类似于课堂中所讲的delete()。
    * */
    public Status deleteTree(int id){
        TreeNode pn = find(id);
        System.out.println(pn.getLeft().name);
        return Status.OK;
    }

    /*
    *
    * 删除id结点，若该结点有子树，则自行决定这些子树的归属，但不能删除。
    *
    * */
    public Status  deleteNode(int id){
        return Status.OK;
    }

    /*
    *
    * 将树中结点id的名字改为newName。
    *
    * */
    public Status setName(int id, String newName){
        TreeNode currentNode = find(id);
        currentNode.name = newName;
        return Status.OK;
    }

    /*
    *
    * 子树的高度（深度）。
    *
    * */
    public int height(){
        int tNum = 0;
        TreeNode p = first;
        while(p != null){
            tNum = p.getLevel();
            if(tNum>hMax ){
                hMax = tNum;
                p.height();
            }
            p = p.right;
        }
        return hMax;
    }

    /*
    *
    * 计算当前子树的度。
    *
    * */
    public int width(int id){
        TreeNode p = find(id);
        int width = 0;
        p = p.first;
        while (p!=null){
            width++;
            p=p.right;
        }
        return width;
    }


}